
import re
import time
from pathlib import Path
from urllib.parse import urlsplit, urlunsplit, urljoin

import pandas as pd
from bs4 import BeautifulSoup
from playwright.sync_api import sync_playwright, TimeoutError as PlaywrightTimeoutError


# ==================================================
# 1. 基本配置
# ==================================================

BASE_URL = "https://hz.lianjia.com/ershoufang/rs/"

OUTPUT_FILE = "杭州二手房全部房源.xlsx"

# 每页之间的等待时间
PAGE_DELAY = 3

# 页面加载超时时间（毫秒）
WAIT_TIMEOUT = 30000

# 浏览器是否显示
HEADLESS = False


# ==================================================
# 2. 生成分页 URL
# ==================================================

def get_page_url(page_number):
    """
    根据页码生成对应页面地址。
    第1页：https://xiangtan.anjuke.com/sale/xiangtanxianb/
    第2页：https://xiangtan.anjuke.com/sale/xiangtanxianb/p2/
    第3页：https://xiangtan.anjuke.com/sale/xiangtanxianb/p3/
    """

    if page_number == 1:
        return BASE_URL

    return urljoin(BASE_URL, f"p{page_number}/")


# ==================================================
# 3. 获取总页数
# ==================================================

def get_total_pages(html):

    soup = BeautifulSoup(html, "lxml")

    page_numbers = []

    # 只分析分页区域
    pagination = soup.select_one("ul.page")

    if pagination:
        for link in pagination.select("a[href]"):

            href = link.get("href", "")
            text = link.get_text(strip=True)

            # 从链接地址提取页码
            match = re.search(r"/p(\d+)(?:/|$)", href)

            if match:
                page_numbers.append(int(match.group(1)))

            # 从链接文字提取数字
            if text.isdigit():
                page_numbers.append(int(text))

    # 如果分页区域没有找到，再从整个页面的链接中提取
    if not page_numbers:

        for link in soup.select("a[href]"):

            href = link.get("href", "")

            match = re.search(r"/p(\d+)(?:/|$)", href)

            if match:
                page_numbers.append(int(match.group(1)))

    if page_numbers:
        return max(page_numbers)

    return 1


# ==================================================
# 4. 等待房源加载
# ==================================================

def wait_for_listings(page, page_number):

    while True:

        try:

            page.wait_for_selector(
                "a.property-ex",
                state="attached",
                timeout=WAIT_TIMEOUT
            )

            count = page.locator("a.property-ex").count()

            if count > 0:

                print(
                    f"第 {page_number} 页加载成功，"
                    f"发现 {count} 条房源。"
                )

                return True

        except PlaywrightTimeoutError:

            count = page.locator("a.property-ex").count()

            if count > 0:

                print(
                    f"第 {page_number} 页已加载，"
                    f"发现 {count} 条房源。"
                )

                return True

            print()
            print(f"第 {page_number} 页暂时没有检测到房源。")
            print("请检查页面是否出现验证码、访问限制或加载错误。")

            input("检查并正常处理页面后，按回车重新等待...")

        except Exception as e:

            print(f"等待页面时发生错误：{e}")

            input("检查浏览器后，按回车重新尝试...")


# ==================================================
# 5. 解析房源数据
# ==================================================

def parse_page(html, page_number):

    soup = BeautifulSoup(html, "lxml")

    cards = soup.select("a.property-ex")

    page_data = []

    for card in cards:

        # 房源标题
        title_element = card.select_one(
            ".property-content-title-name"
        )

        title = (
            title_element.get_text(" ", strip=True)
            if title_element else ""
        )

        # 房源链接
        href = card.get("href", "")

        detail_url = urljoin(BASE_URL, href)

        parsed_url = urlsplit(detail_url)

        unique_url = urlunsplit((
            parsed_url.scheme,
            parsed_url.netloc,
            parsed_url.path,
            "",
            ""
        ))

        # 基本信息
        house_type = ""
        area = ""
        orientation = ""
        floor = ""
        build_year = ""

        info_blocks = card.select(
            ".property-content-info"
        )

        for block in info_blocks:

            if (
                block.select_one(".property-content-info-comm-name")
                or block.select_one(".property-content-info-comm-address")
            ):
                continue

            info_texts = [
                item.get_text(" ", strip=True)
                for item in block.select(
                    ".property-content-info-text"
                )
            ]

            info_texts = [
                x for x in info_texts if x
            ]

            if info_texts:

                values = info_texts

                house_type = values[0] if len(values) > 0 else ""
                area = values[1] if len(values) > 1 else ""
                orientation = values[2] if len(values) > 2 else ""
                floor = values[3] if len(values) > 3 else ""
                build_year = values[4] if len(values) > 4 else ""

                break

        # 小区名称
        community_element = card.select_one(
            ".property-content-info-comm-name"
        )

        community = (
            community_element.get_text(" ", strip=True)
            if community_element else ""
        )

        # 小区地址
        address_element = card.select_one(
            ".property-content-info-comm-address"
        )

        address = (
            address_element.get_text(" ", strip=True)
            if address_element else ""
        )

        # 总价
        total_element = card.select_one(
            ".property-price-total-num"
        )

        total_price = (
            total_element.get_text(" ", strip=True)
            if total_element else ""
        )

        # 单价
        unit_element = card.select_one(
            ".property-price-average"
        )

        unit_price = (
            unit_element.get_text(" ", strip=True)
            if unit_element else ""
        )

        item = {

            "房源标题": title,
            "户型": house_type,
            "面积": area,
            "朝向": orientation,
            "楼层": floor,
            "建筑年代": build_year,
            "小区名称": community,
            "小区地址": address,
            "总价": total_price,
            "单价": unit_price,
            "房源链接": unique_url,
            "所在页码": page_number

        }

        page_data.append(item)

    return page_data


# ==================================================
# 6. 保存 Excel
# ==================================================

def save_to_excel(data):

    if not data:
        return

    df = pd.DataFrame(data)

    columns = [
        "房源标题",
        "户型",
        "面积",
        "朝向",
        "楼层",
        "建筑年代",
        "小区名称",
        "小区地址",
        "总价",
        "单价",
        "房源链接",
        "所在页码"
    ]

    df = df[columns]

    df.to_excel(
        OUTPUT_FILE,
        index=False
    )


# ==================================================
# 7. 主程序
# ==================================================

def main():

    all_data = []

    seen_urls = set()

    # 记录每页的数据指纹，辅助判断重复页面
    page_fingerprints = {}

    with sync_playwright() as p:

        browser = p.chromium.launch(
            headless=HEADLESS
        )

        context = browser.new_context(
            viewport={
                "width": 1440,
                "height": 900
            }
        )

        page = context.new_page()

        try:

            # -------------------------------
            # 打开第一页
            # -------------------------------

            print("正在打开安居客湘潭县二手房页面...")

            try:

                page.goto(
                    BASE_URL,
                    wait_until="domcontentloaded",
                    timeout=60000
                )

            except PlaywrightTimeoutError:

                print("首页导航等待超时，检查当前页面。")

            wait_for_listings(page, 1)

            first_html = page.content()

            # 获取总页数
            total_pages = get_total_pages(first_html)

            print()
            print("=" * 55)
            print(f"检测到的总页数：{total_pages}")
            print("=" * 55)

            if total_pages <= 1:

                print("当前页面没有识别到多页分页信息。")
                print("请检查分页区域是否正常显示。")
                return

            # -------------------------------
            # 逐页抓取
            # -------------------------------

            for page_num in range(1, total_pages + 1):

                print()
                print("-" * 55)
                print(f"正在处理第 {page_num}/{total_pages} 页")
                print("-" * 55)

                target_url = get_page_url(page_num)

                # 第一页已经打开
                if page_num > 1:

                    print(f"目标地址：{target_url}")

                    try:

                        page.goto(
                            target_url,
                            wait_until="domcontentloaded",
                            timeout=60000
                        )

                    except PlaywrightTimeoutError:

                        print("页面导航超时，继续检查当前页面。")

                    # 等待房源加载
                    wait_for_listings(page, page_num)

                # 检查实际 URL
                actual_url = page.url

                print(f"实际访问地址：{actual_url}")

                # 检查是否跳转到了其他页
                expected_path = urlsplit(target_url).path.rstrip("/")
                actual_path = urlsplit(actual_url).path.rstrip("/")

                if expected_path != actual_path:

                    print("警告：实际页面地址与目标地址不一致！")
                    print(f"目标路径：{expected_path}")
                    print(f"实际路径：{actual_path}")
                    print("程序暂停，避免将错误页面记为当前页。")

                    input(
                        "请检查浏览器页面，正常处理后按回车继续..."
                    )

                    # 再次检查实际地址
                    actual_path = urlsplit(page.url).path.rstrip("/")

                    if expected_path != actual_path:

                        print("页面地址仍然不一致，停止抓取。")
                        break

                # 获取 HTML
                html = page.content()

                # 解析
                page_data = parse_page(
                    html,
                    page_num
                )

                if not page_data:

                    print(f"第 {page_num} 页没有解析到房源。")
                    print("为避免遗漏，停止抓取。")
                    break

                # 页面指纹
                fingerprint = tuple(
                    sorted(
                        item["房源链接"]
                        for item in page_data
                    )
                )

                if fingerprint in page_fingerprints.values():

                    old_page = next(
                        k for k, v in page_fingerprints.items()
                        if v == fingerprint
                    )

                    print(
                        f"警告：第 {page_num} 页与第 {old_page} 页"
                        "的房源列表完全相同。"
                    )

                    print("为避免重复抓取，程序停止。")
                    break

                page_fingerprints[page_num] = fingerprint

                # 去重并添加数据
                new_count = 0

                for item in page_data:

                    url = item["房源链接"]

                    if url and url not in seen_urls:

                        seen_urls.add(url)

                        all_data.append(item)

                        new_count += 1

                print(f"本页解析房源：{len(page_data)} 条")
                print(f"本页新增房源：{new_count} 条")
                print(f"目前累计房源：{len(all_data)} 条")

                # 每页保存一次
                save_to_excel(all_data)

                print(f"数据已保存到：{OUTPUT_FILE}")

                # 控制访问间隔
                if page_num < total_pages:

                    print(
                        f"等待 {PAGE_DELAY} 秒后继续下一页..."
                    )

                    time.sleep(PAGE_DELAY)

            # -------------------------------
            # 完成
            # -------------------------------

            print()
            print("=" * 55)
            print("抓取流程结束")
            print(f"累计获取房源：{len(all_data)} 条")
            print(f"Excel 文件：{Path(OUTPUT_FILE).resolve()}")
            print("=" * 55)

        finally:

            browser.close()


if __name__ == "__main__":

    main()
