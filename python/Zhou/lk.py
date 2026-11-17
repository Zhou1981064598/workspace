import pandas as pd
from pyecharts import options as opts
from pyecharts.charts import  Bar
from pyecharts.charts import  Pie
from pyecharts.charts import  WordCloud
from pyecharts.charts import  Page
from pyecharts.options import ComponentTitleOpts
from pyecharts.components import  Table
import matplotlib.pyplot as plt
import jieba

plt.rcParams['font.sans-serif'] = ['SimHei']

def table_rank() -> Table:
    df = pd.DataFrame(pd.read_csv('./前十热门视频/5.20.csv', encoding='utf-8'))
    x1 = df["视频名字"]
    x2 = df["播放量"]
    x3 = df["评论数"]
    x4 = df["UP主"]
    x5 = df["视频类型"]
    header = ["视频名字", "播放量", "评论数", "UP主", "视频类型"]
    data = []
    for i in range(len(x1)):
        line = [x1[i], x2[i], x3[i], x4[i], x5[i]]
        data.append(line)
    table = Table()
    headers = header
    rows = data
    table.add(headers, rows)
    table.set_global_opts(
        title_opts=ComponentTitleOpts(title="b站前十数据", subtitle="按播放量排名")
    )

    return table


def bars() -> Bar:
    df = pd.DataFrame(pd.read_csv('./各类视频播放量及评论数/5.20播放量排行榜.csv', encoding='utf-8'))
    x = list(df['视频类型'])
    y1 = list(df['播放量'])
    y2 = list(df['评论数'])

    bar = Bar()
    bar.add_xaxis(x)
    bar.add_yaxis("播放量", y1)
    bar.add_yaxis("评论数", y2)
    bar.set_global_opts(
            title_opts=opts.TitleOpts(title="播放量和评论数", subtitle="本数据来自于b站"),
            brush_opts=opts.BrushOpts(),
        )
    return bar

# 饼图
def pies() -> Pie:
    df = pd.DataFrame(pd.read_csv('./排行榜/5.20播放量排行榜.csv', encoding='utf-8'))
    x = list(df['UP主'])
    y = list(df['播放量'])
    data = [[x[i], y[i]] for i in range(len(y))]
    pie = Pie()
    pie.add(
            "",
            data,
            center=["43%", "50%"],
            radius=["40%", "75%"],
            rosetype="radius"
        )
    pie.set_colors(["blue", "green", "yellow", "red", "pink", "orange", "purple"])
    pie.set_global_opts(
            title_opts=opts.TitleOpts(title="各类视频评论数图"),
            legend_opts=opts.LegendOpts(pos_top="15%", pos_left="80%", orient="vertical"),
        )
    pie.set_series_opts(
            label_opts=opts.LabelOpts(formatter="{b}:{c} ({d}%)"),
            tooltip_opts=opts.TooltipOpts(
                trigger="item", formatter="{a} <br/>{b}: {c} ({d}%)"
            )
        )

    return pie

# 词云1
def wordClouds() -> WordCloud:
    df = pd.DataFrame(pd.read_csv('./排行榜/5.20播放量排行榜.csv', encoding='utf-8'))
    data = []
    for i in range(len(df)):
        value = df.iloc[i]
        x = value['视频名字']
        y = value['播放量']
        item = (x, y)
        data.append(item)

    wc =  WordCloud()
    wc.add(series_name="吸引标题", data_pair=data, word_size_range=[6, 66],mask_image="./e123.jpg")
    wc.set_global_opts(
            title_opts=opts.TitleOpts(
                title="吸引标题", subtitle_textstyle_opts=opts.TextStyleOpts
            ),
            tooltip_opts=opts.TooltipOpts(is_show=True)
        )
    return wc
#词云2
def wordcloud():
    jieba.setLogLevel(jieba.logging.INFO)
    # 载入数据
    df = pd.read_csv('./排行榜/5.20播放量排行榜.csv', header=0, encoding='utf-8', dtype=str)  # DataFrame

    # 去重并转换为list
    ls_data = df.drop_duplicates().values.tolist()  # df_data.values为array类型
    ls_data = ";".join([str(j) for i in ls_data for j in i])  # 将列表元素转换为一个字符串，供lcut函数使用


    seg_list = jieba.lcut(ls_data, cut_all=False, HMM=True)
    seg = '/'.join(seg_list)  # str类型

    # 关键词抽取
    tags = jieba.analyse.extract_tags(seg, topK=300, withWeight=True, allowPOS=('ns', 'n', 'vn', 'v'),)

    #定义生成词云
    g = WordCloud()
    g.add(series_name="高频词汇", data_pair=tags, word_size_range=[3, 30],mask_image='./e123.jpg')
    g.set_global_opts(
        title_opts=opts.TitleOpts(
            title="高频词条", title_textstyle_opts=opts.TextStyleOpts(font_size=23)
        ),
        tooltip_opts=opts.TooltipOpts(is_show=True),
    )
    return g

# 可视化大屏
def page_main():
    page = Page(layout=Page.DraggablePageLayout)
    page.add(
        table_rank(),
        bars(),
        wordClouds(),
        wordcloud(),
        pies(),
    )
    page.render("5.20可视化大屏.html")

if __name__ == '__main__':
    #生成html大屏，可移动
    page_main()
    #由已经生成的html文件和json文件生成新的固定的大屏html
    # Page.save_resize_html("可视化大屏.html",cfg_file="大屏.json",dest="固定大屏.html")