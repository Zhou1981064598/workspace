import requests
from bs4 import BeautifulSoup
import re


def get_pdf_url(url):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'}
    try:
        response = requests.get(url, headers=headers)
        response.raise_for_status()
        soup = BeautifulSoup(response.text, 'html.parser')
        # 这里需要根据实际网页结构修改正则表达式
        pdf_url_pattern = re.compile(r'https://.*\.pdf')
        pdf_url = pdf_url_pattern.search(response.text)
        if pdf_url:
            return pdf_url.group(0)
    except requests.RequestException as e:
        print(f"请求出错: {e}")
    return None


def download_pdf(pdf_url, save_path):
    try:
        response = requests.get(pdf_url)
        response.raise_for_status()
        with open(save_path, 'wb') as f:
            f.write(response.content)
        print(f"PDF 文件已保存到 {save_path}")
    except requests.RequestException as e:
        print(f"下载出错: {e}")


if __name__ == "__main__":
    baiduwenku_url = 'https://wenku.baidu.com/view/19fadbc62079168884868762caaedd3382c4b552?aggId=8e6b0bbe084e767f5acfa1c7aa00b52acfc79c9f&fr=catalogMain_text_ernie_recall_v1%3Awk_recommend_main3&_wkts_=1742185486249&bdQuery=%E6%B9%98%E6%BD%AD%E5%A4%A7%E5%AD%A6%E5%B7%A5%E7%A8%8B%E5%8A%9B%E5%AD%A6%E8%80%83%E7%BA%B2&needWelcomeRecommand=1'  # 替换为实际的百度文库链接
    pdf_url = get_pdf_url(baiduwenku_url)
    if pdf_url:
        save_path = 'downloaded.pdf'
        download_pdf(pdf_url, save_path)