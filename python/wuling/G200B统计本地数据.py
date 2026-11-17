# encoding=utf-8
import sys

sys.path.append(u"")  # 添加Python2.7Lib文件夹路径，不需要可删除
sys.path.append(r"C:\Python27\Lib")
import datetime
import string
import os
import codecs


# 写测试数据到文本文件
# input[0] - 保存文件目录
# input[1] - 日期
# input[2] - 数据头
# input[3] - 数据
# 返回 true 
def Convert(input):
    fileName = '{0}.csv'.format(input[1])
    filePath = '{0}\\{1}'.format(input[0], fileName)

    if os.path.exists(filePath):
        with open(filePath, "a") as f:
            f.write(codecs.BOM_UTF8)
            f.write(input[3].encode('utf8'))
        return 1

    with open(filePath, "w") as f:
        f.write(codecs.BOM_UTF8)
        f.write(input[2].encode('utf8'))
        f.write(input[3].encode('utf8'))
    f.close()

    return 2
