# -*- coding: utf-8-*-


import sys
sys.path.append(u"")
sys.path.append(r"C:\Python27")
sys.path.append(r"C:\Python27\Lib")
import os
# 从文本文件读数据
# input - TASII执行器程序路径
# 返回整数值
def Convert(input):
	path = input
	retlist = list()
	
	for line in open(path):
		line = line.strip("\n")
		tmpData=line.split(",")		
		for key in tmpData:
			retlist.append(key)		

	return retlist