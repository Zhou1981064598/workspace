import os
import webbrowser as web
import csv
import random
import time
from collections import OrderedDict
import numpy as np
import requests
from bs4 import BeautifulSoup
from header_tools.get_ua import get_ua
import pandas as pd
import re

from sklearn.model_selection import train_test_split

from pyecharts import options as opts
from pyecharts.charts import Page, WordCloud, Grid, Line


# 爬取数据
def resolve_rank_info(data):
    '''解析每一页所有基金的收益情况'''
    rank_pages_data=[]#存储处理号的字典
    data=data.replace("[","").replace("]","").replace.('\"',"")
    for data_row in data.split(","):
        rank_info =OrderedDict()
        row_arr=data_row.split("|")
        if len(row_arr)>16:
            rank_info[]
            rank_info['基金代码']="d"+row_arr[0]
            rank_info['基金名称'] = row_arr[1]
            rank_info['截止日期'] = row_arr[2]
            rank_info['单位净值'] = row_arr[3]
            rank_info['日增长率'] =  row_arr[5]
            rank_info['近一周'] =  row_arr[6]
            rank_info['近1月'] =  row_arr[7]
            rank_info['近3月'] =  row_arr[8]
            rank_info['近6月'] =  row_arr[9]
            rank_info['近1年'] =  row_arr[10]
            rank_info['近2年'] = row_arr[11]
            rank_info['近3年'] =  row_arr[12]
            rank_info['今年来'] =  row_arr[13]
            rank_info['成立来'] =  row_arr[14]
            rank_info['起购金额'] =  row_arr[-5]
            rank_info['原手续费'] =  row_arr[-4]
            rank_info['现手续费'] =  row_arr[-3]
            #保存当前rank信息
            return rank_pages_data
def get_rank_data(url, page_index, max_page, fund_type):
    try_cnt = 0  # 尝试的次数
    rank_data = []  # 存放本类型基金所有的数据
    # 若当前起始页面小于总页数或者超时3次就退出
    while page_index < max_page and try_cnt < 3:
        # 根据每页数据条数确定起始下标
        new_url = url + '?ft=' + fund_type + '&sc=1n&st=desc&pi=' + str(page_index) + '&pn=100&fl=0&isab=1'
        print('计划爬取第{0}页数据：{1}'.format(page_index, new_url))
        response = requests.get(url=new_url, header={'User-Agent': get_ua()}, timeout=10)
        if len(response.text) > 100:
            res_data = re.findall("\[{1}\S+\]{1}", response.text)[0]
            rank_pages_data=resolve_rank_info(res_data)
            rank_data.extend(rank_page_data for rank_page_data in rank_pages_data)
        else:
            try_cnt += 1
        page_index += 1
        # 随机休眠3-5s
        time.sleep(random.randint(3, 5))

        df_rank_data = pd.DataFrame(rank_data)  # 将rank_data转换为DataFrame对象（列表包含字典）
        print(df_rank_data)
        return df_rank_data

def get_position_data(data,rank):
    '''根据起始页获得当前页面的所有的数据，并进行筛选'''
    data=data.replace('',np.NaN,regex=True)#处理空值
    data_notna=data.dropna(subset=['近一年'])#去除近一年的收益率空值，返回清洗后的数据
    data_notna=data_notna.reset_index(drop=True)
    data_notna['近1年']=data_notna['近一年'].astype(float)
    data_sort=data_notna.sort_values(by="近一年",ascending=False)
    data_sort.reset_index(inplace=True)#重置索引使其连续，并作用在前面的数据上
    data_rank=data.sort.loc[0:rank-1,:]
    #爬取每个基金的数据
    rank_detail_data=[]#保存所有基金的字典(基金经理，管理人，基金代码，成立日期等)
    position_data=[]#保存所有持仓数据（持股数、市值、持仓的股票持仓比）
    error_fund_list=[]
    for row_index,data_row in data_rank.iterrows():
        fund_code=str(data_row['基金代码'])
        try :
        #爬取页面，获得该基金持仓的持仓数据
        position_title_url="http://fundf10.eastmoney.com/ccmx_" + str(fund_code[1:]) + ".html"
        print('正在爬取第 {0}/{1} 个基金 {2} 的详细数据中...'.format(row_index + 1, len(data_rank), fund_code[1:]))
        response_title=requests.get(url=position_title_url,headers={'User-Agent':get_ua()},timeout=10)
        #解析基金的详细数据
        rank_detail_info=resolve_rank_detail_info(fund_code[1:],response_title)
        #保存数据
        rank_detail_data.append(rank_detail_info)
        #爬取页面，获取该基金的持仓数据
        position_data_url = "http://fundf10.eastmoney.com/FundArchivesDatas.aspx?type=jjcc&code=" + \
                            str(fund_code[1:]) + "&topline=10&year=&month=&rt=" + str(random.uniform(0, 1))
        # position_data_url = "http://fundf10.eastmoney.com/FundArchivesDatas.aspx?type=jjcc&code=" + \
        # str(fund_code[1:]) + "&topline=10&year=2020&month=&rt=" + str(random.uniform(0, 1))
        print('正在爬取第 {0}/{1} 个基金 {2} 的持仓情况中...'.format(row_index + 1, len(data_rank), fund_code[1:]))
        #解析基金的持仓情况
        response_data=requests(url=position_title_url,headers={'User-Agent':get_ua()},timeout=10)
        dund_position_data=resolve_position_info(fund_code[1:],response_data.text)
        #保存数据
        position_data.extend(fund_position_data for fund_position_data in fund_position_data)
        except:

# 爬取数据

def Get_Data(url, page_index, max_page, fund_type, max_rank, Year, Quarterly1):
    # 获得详细的季度、月、年的收益
    df_rang_data = get_rank_data(url, page_index, max_page, fund_type)
    # 获得详细的季度、月、年的收益
    max_rank=300 if int(df_rang_data/100)>3 else 100
    #获得每个类型中的基金，得到详细的收益情况及持仓情况，将TOP10的基金选出来，并将其前10大持仓选出
    da_rank_detail_data,df_position_data=get_position_data(df_rang_data,max_rank)

if __name__ == '__main__':
    url = 'https://fundapi.eastmoney.com/fundtradenew.aspx'
    Year = '2022';
    Quarterly1 = '第一季度';
    Quarterly2 = '第二季度';
    Quarterly3 = '第三季度';
    Quarterly4 = '第四季度';
    page_index = 1;
    max_page = 5;
    Rate_return_top_number = 200;
    max_rank = 300;
    # 基金的类型
    fund_type = 'gp'
    Position_Area = '重仓股票前10名';

    # 爬取数据
    max_rank = Get_Data(url, page_index, max_page, fund_type, max_rank, Year, Quarterly1)
    # 爬取数据