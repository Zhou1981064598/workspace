import requests
import time
import csv
import json
import numpy as np2
import pandas as pd

headers={'cookie': '{}',
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36',
        'referer': 'https://www.bilibili.com/',
        'sec-fetch-dest': 'script',
        'sec-fetch-mode': 'no-cors',
        'sec-fetch-site': 'same-site'
         }


def get_parse(result):
    D=[]
    items=result['data']['list']
    for item in items:
        # 内容id
        id=item['aid']
        # 用户id
        ids=item['owner']['mid']
        # 用户昵称
        name=item['owner']['name']
        # 标题
        title=item['title']
        # 归属
        tname=item['tname']
        # 封面
        pic=item['pic']
        # 发布时间
        times=item['pubdate']
        timearray2 = time.localtime(times)
        time2 = time.strftime('%Y-%m-%d', timearray2)
        # 观看人数
        view=item['stat']['view']
        # 弹幕数
        danmaku=item['stat']['danmaku']
        # 点赞数
        like=item['stat']['like']
        # 投币数
        coin=item['stat']['coin']
        # 收藏数
        favorite=item['stat']['favorite']
        # 分享数
        share=item['stat']['share']
        # 评论数
        reply=item['stat']['reply']
        # 当前时间
        time3=time.strftime('%Y-%m-%d',time.localtime(time.time()))
        data=[id,ids,name,title,tname,pic,time2,view,danmaku,like,coin,favorite,share,reply,time3]
        print(data)
        D.append(data)
    save(D)


def save(data):
    with open('热门视频.csv', 'a', newline='', encoding='gb18030')as file:
        write=csv.writer(file)
        write.writerows(data)


def main():
    li=['视频id','用户id','用户昵称','标题','归属类别','图片','发布时间','观看人数','弹幕数','点赞数','投币数','收藏数','分享数','评论数','当前时间']
    with open('热门视频.csv', 'a', newline='', encoding='gb18030')as file:
        write=csv.writer(file)
        write.writerow(li)
    for i in range(1,11):
        url='https://api.bilibili.com/x/web-interface/popular?ps=20&pn={}'.format(i)
        print(url)
        response=requests.get(url,headers=headers).text
        result=json.loads(response)
        get_parse(result)
        # print()
# def show():
#     df = pd.read_csv('./热门视频.csv',encoding='gb18030')
#     df1 = pd.DataFrame(pd.read_csv('./热门视频.csv',encoding='gb18030'))
#     # df1
#     # df1.info()
#     df2 = df1.sort_values(by=['观看人数'], ascending=False)
#     df2[['用户昵称', '标题', '归属类别', '观看人数']].head(10)
#     df3 = df1.groupby('归属类别').size().sort_values(ascending=False)
#     hang = df3.index.to_list()
#     # # lie=df5.value.to_list()
#     lie = list(df3.values)
#
#     # c = (
#     #     # 设置主题
#     #     Bar(init_opts=opts.InitOpts(theme=ThemeType.DARK))
#     #         .add_xaxis(['单机游戏', '搞笑', '短片·手书·配音', '日常', '综合', '电子竞技', '手机游戏', '原创音乐', '美食制作', '影视杂谈'])
#     #         .add_yaxis('数量', [26, 19, 15, 14, 12, 10, 9, 8, 8, 7]).reversal_axis()
#     #         .set_global_opts(title_opts=opts.TitleOpts(title='归属类别排名', pos_top='top'))
#     # )
#     # c.render_notebook()
#     save_path = "./test"
#     df3.to_csv(save_path + ".csv", mode="a", encoding="utf_8_sig", header=0, index=0)
#
#     print(df3)
#     # print(lie)
if __name__ == '__main__':
    main()