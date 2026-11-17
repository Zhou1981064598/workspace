import numpy as np2
import pandas as pd
import matplotlib.pyplot as plt
def show():
    df = pd.read_csv('./热门视频.csv',encoding='gb18030')
    df1 = pd.DataFrame(pd.read_csv('./热门视频.csv',encoding='gb18030'))
    # df1
    # df1.info()
    df2 = df1.sort_values(by=['观看人数'], ascending=False)
    # df2[['用户昵称', '标题', '归属类别', '观看人数']].head(10)
    df3 = df1.groupby('归属类别').size().sort_values(ascending=False)
    hang = df3.index.to_list()
    # lie=df5.value.to_list()
    lie = list(df3.values)
    plt.rcParams["font.sans-serif"] = "SimHei"
    plt.figure(figsize=(10, 5))
    plt.title('视频分类', fontsize=20)
    plt.xlabel(u'视频类型',fontsize=14)
    plt.ylabel(u'数量', fontsize=14)
    plt.bar(hang, lie, alpha=0.6, width=0.8)

    # # 存储
    # save_path = "./test"
    # df3.to_csv(save_path + ".csv", mode="a", encoding="utf_8_sig", header=1, index=0)
    plt.show();
if __name__ == '__main__':

    show()