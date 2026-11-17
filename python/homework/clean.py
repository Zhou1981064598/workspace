import numpy as np2
import pandas as pd
col = ("视频链接", "视频名字", "播放量", "评论数", "UP个人空间链接", "UP主")
def show():
    # 全站热门视频文件路径
    # All="D:\\workspace\python\\homework\\B站热门视频_全站_2022-05-20.csv"
    df1=pd.DataFrame(pd.read_csv('全站/B站热门视频_全站_2022-05-20.csv', header=0))
    # df1
    # df1.info()
    # print(df1)
    # 删除重复数据
    # df.drop_duplicates(keep='first',inplace=True)
    # print(df)
    df3=df1.sort_values(by=['播放量'],ascending=False)
    df3['播放量'] = df3['播放量'].apply(lambda x: x[0:-2])
    df3[['视频链接','UP主','视频名字','播放量']].head(10)
    print(df3)
def cat
if __name__ == '__main__':
    show()