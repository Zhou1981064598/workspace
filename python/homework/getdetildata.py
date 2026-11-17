from bs4 import BeautifulSoup  # 网页解析，获取数据
import re  # 正则表达式，进行文字匹配
import urllib.request, urllib.error  # 指定URL，获取网页数据
import xlwt  # 进行excel操作
import datetime  # 时间获取
import pandas as pd
import random
import time


def main():
    # "bangumi": "番剧","variety": "综艺",,  "guochan": "国产动漫", "movie": "电影", "tv": "电视剧" "documentary": "纪律片",
    #     1. 获取网页
    category = {"all": "全站", "guochuang": "国创相关",
                 "douga": "动画", "music": "音乐", "dance": "舞蹈",
                "game": "游戏", "knowledge": "知识", "tech": "科技", "sports": "运动", "car": "汽车",
                "life": "生活", "food": "美食", "animal": "动物圈", "kichiku": "鬼畜", "fashion": "时尚",
                "ent": "娱乐", "cinephile": "影视",
                "origin": "原创", "rookie": "新人"}

    baseurl = "https://www.bilibili.com/v/popular/rank/"

    # 2. 获取和解析数据
    for i in category:
        datalist = getData(baseurl, i)


        save_path = "./B站热门视频_" + category.get(i) + "_" + str(datetime.date.today())
        #     3. 保存数据
        saveData(datalist, save_path)
        for j in range(0, random.randint(1, 5)):
            time.sleep(random.randint(1, 5))
            print("防止访问过于频繁,而被封")


findLink = re.compile(r'<a href="(.*?)" target="_blank">')
findName = re.compile(r'<a class="title" href=".*?" target="_blank">(.*)</a>')
findPlay = re.compile(r'<span class="data-box"><img alt="play" src=".*?"/>(.*?)</span> ', re.S)
findView = re.compile(r'<span class="data-box"><img alt="like" src=".*?"/>(.*?)</span>', re.S)
findUP = re.compile(r'<span class="data-box up-name"><img alt="up" src=".*?"/>(.*?) </span>', re.S)
findGrades = re.compile(r'<div class="pts"><div>(.*?)</div>')


def getData(baseurl, category):
    datalist = []
    html = askURL(baseurl + category)
    # print(html)
    soup = BeautifulSoup(html, "html.parser")  # 形成树形结构对象

    for item in soup.find_all("div", class_="content"):
        data = []
        item = str(item)
        # print(item)
        # print()
        # print()
        # print()
        # 视频链接
        link = re.findall(findLink, item)[0]

        data.append(link)
        # 视频名字
        name = re.findall(findName, item)[0]
        data.append(name)
        # 播放量
        play = re.findall(findPlay, item)[0]
        # print(play)
        data.append(play)
        # 评论数
        view = re.findall(findView, item)[0]
        # print(view)
        data.append(view)
        # UP个人空间链接
        uplink = re.findall(findLink, item)[1]
        # print(uplink)
        data.append(uplink)
        # UP主
        UP = re.findall(findUP, item)[0]
        # print(UP)
        data.append(UP)
        datalist.append(data)

    return datalist


def saveData(datalist, save_path):
    book = xlwt.Workbook(encoding="utf-8")
    sheet = book.add_sheet("B站热门", cell_overwrite_ok=True)
    col = ("视频链接", "视频名字", "播放量", "评论数", "UP个人空间链接", "UP主")

    for i in range(0, len(col)):
        sheet.write(0, i, col[i])
    for i in range(0, 100):
        print("第%d条" % i)
        data = datalist[i]
        # sheet.write(i + 1, 0, i + 1)
        for j in range(0, len(data)):
            sheet.write(i + 1, j, data[j])


    # csv
    df = pd.DataFrame(columns=col, data=datalist)
    df.to_csv(save_path + ".csv", mode="a", encoding="utf_8_sig", header=1, index=0)
    # # csv
    # # xls
    book.save(save_path + '.xls')  # 保存数据表
    # xls


def askURL(url):
    head = {
        "user-agent": "Mozilla / 5.0(Windows NT 10.0;WOW64) AppleWebKit / 537.36(KHTML, like Gecko) Chrome / 88.0.4324.104 Safari / 537.36",
        "referer": "https: // www.bilibili.com /",
        "cookie": "_uuid = DFE59F2A - D16B - 327E - D501 - 9CA14392FF8059467infoc;buvid3 = 0041FC1C - 2C18 - 4DDE - 965F - 9DEF43D88176155809infoc;rpdid = | (YYR~ | uklm0J'ul))u~JRuJ; sid=9n9kd7a4; LIVE_BUVID=AUTO2815885539756041; blackside_state=1; CURRENT_FNVAL=80; PVID=1; CURRENT_QUALITY=80; DedeUserID=434641640; DedeUserID__ckMd5=1b9357ca56a49330; SESSDATA=ab62e58a%2C1619879365%2C80832*b1; bili_jct=01ecd2f4c1f3d0e94aa31b03a7bea6ec; bp_t_offset_434641640=491560557515702800; bp_video_offset_434641640=491834756822300005; finger=1777945899"
    }
    request = urllib.request.Request(url, headers=head)
    html = ""
    try:
        response = urllib.request.urlopen(request)
        html = response.read().decode("utf-8")
    except urllib.error.URLError as e:
        if hasattr(e, "code"):
            print(e, "code")
        if hasattr(e, "reason"):
            print(e, "reason")
    return html


if __name__ == "__main__":  # 程序执行时
    # 调用函数
    main()
    print("爬取完毕！")
