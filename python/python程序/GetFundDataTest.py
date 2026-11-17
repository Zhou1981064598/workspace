import os
import re
import webbrowser as web
import csv
import random
import time
from collections import OrderedDict
import numpy as np
import requests
from bs4 import BeautifulfulSoup
from header_tools.get_ua import get_ua
import pandas
from sklearn.model_selection import train_test_split
from pyecharts import options as opts
from pycharts.charts import Page,WordCloud,Grid,Line
#爬取数据
def get_rank_data(url, page_index, max_page, fund_type, max_rank, Year, Quarterly1):
    try_cnt=0#尝试的次数
    rank_data=[]#存放本类型基金所有的数据
    #若当前起始页面小于总页数或者超时3次就退出
    while page_index<max_page and try_cnt<3:
        #根据每页数据的条数确定起始下标
        new_url = url + '?ft=' + fund_type + '&sc=1n&st=desc&pi=' + str(page_index) + '&pn=100&fl=0&isab=1'
        print('计划爬取第{0}页的数据：{1}'.format(page_index,new_url))
        response=requests.get(url=new_url,header={'User-Agent':get_ua()},timeout=10)
        if len(response.text)>100:
            res_data=re.findall("\[{1}\S+\]{1}")[0],response.text
        else:
            try_cnt+=1
        page_index+=1
        #随机休眠3~5s
        time.sleep(random.randint(3,5))
        df_rank_data=pd.DataFrame(rank_data)#将rank_data转换为DataFrame
        print(df_rank_data)
        return
def Get_Data(url, page_index, max_page, fund_type, max_rank, Year, Quarterly1):
    # 获得详细的季度、月、年的收益
    df_rang_data=get_rank_data(url,page_index,max_page,fund_type)
    #获得详细的季度、月、年的收益
if __name__ == '__main__':
    url = 'https://fundapi.eastmoney.com/fundtradenew.aspx'
    Year='2022';Quarterly1='第一季度';Quarterly2='第二季度';
    page_index=1;max_page=5;Rate_return_top_number=200;
    max_rank=300;
    #基金的类型
    fund_type='gp'
    Position_Area='重仓股票前10名'
    #爬取数据
