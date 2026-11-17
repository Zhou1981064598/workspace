# -*- coding: utf-8-*-

import sys
sys.path.append(u"") # 添加Python2.7Lib文件夹路径，不需要可删除
sys.path.append(r"C:\Python27")
sys.path.append(r"C:\Python27\Lib")
import os

# 写数据到文本文件
# input[0] - 待写的文本文件
# input[1] - 待保存的计数
# 返回字符串（没实际意义）
def Convert(input):
	filePath=input[0]
	data = "{},{},{},{},{}".format(input[1],input[2],input[3],input[4],input[5]).encode('utf-8')
	if os.path.exists(filePath) == False:
		with open( filePath, 'w') as f:
			f.write(data)
	else:
		with open(filePath,"w") as f:
			f.write(str(data))
			
	f.close()		
	return data
