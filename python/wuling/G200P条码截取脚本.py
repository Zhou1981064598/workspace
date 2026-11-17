# -*- coding: utf-8-*-

import sys
sys.path.append(u"") # 添加Python2.7Lib文件夹路径，不需要可删除


# 分割字符串
# input[0] - 待截取的字符串
# input[1] - 截取数组的起始下标
# input[2] - 截取长度
# 返回截取后的字符串数组
def Convert(input):
    return input[0][input[1]:input[1]+input[2]]