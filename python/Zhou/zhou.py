import pandas as pd
import matplotlib.pyplot as plt
import datetime  # 时间获取

def show():
    df1 = pd.DataFrame(pd.read_csv('./5.26/B站热门视频_全站_2022-05-26.csv', encoding='utf-8'))
    # 查看数据类型
    df1.info()
    # 删除重复数据
    df1.drop_duplicates(keep='first', inplace=True)
    # 将视频分类
    import os
    video_type = list()
    video_type.append("视频类型")
    pwd = os.getcwd() + '\\' + os.listdir()[6]
    name = os.listdir(pwd)
    name.remove('B站热门视频_全站_2022-05-26.csv')
    # print(name)
    for i in df1['视频链接']:
        exist = ' '
        for j in range(len(name)):
            with open(pwd + "\\" + name[j], encoding="utf-8") as d:
                for k in d.readlines():
                    import re
                    patt = r'' + i
                    pattern = re.compile(patt)
                    res = pattern.findall(k)
                    if len(res) > 0:
                        exist = exist + name[j].split('_')[1] + ' '
                        break
            d.close()
        video_type.append(exist)
    df1.insert(len(df1.keys()), video_type[0], video_type[1:], allow_duplicates=False)
    import numpy as np
    df1["视频类型"] = df1["视频类型"].apply(lambda x: np.NaN if str(x).isspace() else x)
    df1["视频类型"] = df1["视频类型"].fillna('全站')
    # 将播放量转换为数值型,并按照播放量排序
    df1['播放量'] = df1['播放量'].str.replace("万", "")
    df1['播放量'] = df1['播放量'].astype(float)
    df1['播放量'] = df1['播放量'] * 10000
    df1 = (df1.sort_values(by='播放量', ascending=False))
    # 将评论数转换为数值型,并按照评论数排序
    df1['评论数'] = df1['评论数'].str.replace("万", "")
    df1['评论数'] = df1['评论数'].astype(float)
    df1['评论数'] = df1['评论数'] * 10000
    df2 = (df1.sort_values(by='评论数', ascending=False))
    df1[['UP主', '视频名字', '播放量', '评论数', '视频类型']].head(10).to_csv('./前十热门视频/5.26.csv', encoding="utf_8_sig")
    # df1.info()
    # 存储文件
    df1.to_csv('./各类型视频播放量及评论数/5.26播放量排行榜.csv', encoding="utf_8_sig")
    df2.to_csv('./各类型视频播放量及评论数/5.26评论数排行榜.csv', encoding="utf_8_sig")
    df3 = df1.groupby(['视频类型']).agg({'播放量': sum, '评论数': sum})
    # 插入日期
    data = list()
    data.append('日期')
    for i in range(len(df3)):
        data.append(str(datetime.date.today()))
    df3.insert(len(df3.keys()), data[0], data[1:], allow_duplicates=False)
    df3.to_csv('./5.26各类视频播放量及评论数.csv', encoding="utf_8_sig")

if __name__ == "__main__":
     show()
    # # demo()
    # import os
    # pwd = os.getcwd()
    # path=os.listdir(pwd)
    # # for i in os.listdir(pwd):
    # print(path)