# -*- coding: utf-8-*-

import sys
sys.path.append(u"") # 添加Python2.7Lib文件夹路径，不需要可删除
sys.path.append(r"C:\Python27")
sys.path.append(r"C:\Python27\Lib")
sys.path.append(r"C:\Python27\Lib\site-packages")
import os
import xlwt
import xlrd
import xlutils.copy
import shutil
import datetime

# ASCII码数组
# input - 模板路径 目标路径 用户名 设备编号 条码 测试时间 测试总结果 报告数据
# output - 操作结果字符串
def Convert(input):		
	borders = xlwt.Borders() # Create Borders
 	borders.right = xlwt.Borders.THIN
	borders.left = xlwt.Borders.THIN
	borders.right = xlwt.Borders.THIN
	borders.top = xlwt.Borders.THIN
	borders.bottom =xlwt.Borders.THIN

	blackFont = xlwt.Font()
	blackFont.colour_index = 0x08
	blackFont.name="宋体"
	blackFont.height = 220
	
	redFont = xlwt.Font()
	redFont.colour_index = 0x0A
	blackFont.name="宋体"
	blackFont.height = 220
	
	greenFont = xlwt.Font()
	greenFont.colour_index = 0x11
	blackFont.name="宋体"
	blackFont.height = 220
	
	alignCC = xlwt.Alignment() # Create Alignment 中中
	alignCC.horz = xlwt.Alignment.HORZ_CENTER # May be: HORZ_GENERAL, HORZ_LEFT, HORZ_CENTER, HORZ_RIGHT, HORZ_FILLED, HORZ_JUSTIFIED, HORZ_CENTER_ACROSS_SEL, HORZ_DISTRIBUTED
	alignCC.vert = xlwt.Alignment.VERT_CENTER # May be: VERT_TOP, VERT_CENTER, VERT_BOTTOM, VERT_JUSTIFIED, VERT_DISTRIBUTED
	
	alignLC = xlwt.Alignment() 
	alignLC.horz = xlwt.Alignment.HORZ_LEFT # Create Alignment 左中
	alignLC.vert = xlwt.Alignment.VERT_CENTER
	
	blackCenterStyle = xlwt.XFStyle() 
	blackCenterStyle.alignment = alignCC
	blackCenterStyle.borders = borders
	blackCenterStyle.font = blackFont
	
	blackLeftStyle = xlwt.XFStyle() 
	blackLeftStyle.alignment = alignLC
	blackLeftStyle.borders = borders
	blackLeftStyle.font = blackFont
	blackLeftStyle.alignment.wrap=1
	
	redCenterStyle = xlwt.XFStyle()
	redCenterStyle.alignment = alignCC
	redCenterStyle.borders = borders
	redCenterStyle.font = redFont
	
	greenCenterStyle = xlwt.XFStyle()
	greenCenterStyle.alignment = alignCC
	greenCenterStyle.borders = borders
	greenCenterStyle.font = greenFont	
	
	sourcePath = input[0] #'C:\Users\superfine\Desktop\ReportTemplate\zxx.xls'
	desDir = input[1]
	userName = input[2]
	deviceCode = input[3]
	if input[4] == None:
		input[4] = ""
	barcode = input[4]
	testTime = input[5]
	result = "NG"
	if input[6] == "P" or input[6] == "OK" or input[6] == "True":
		result = "OK"

	subDir = datetime.datetime.now().strftime('\\%Y\\%m\\%d')
	desDir = desDir + subDir
	if not os.path.exists(desDir):
		os.makedirs(desDir)
		
	fileName = barcode +"_" + result + "_" +datetime.datetime.now().strftime('%Y%m%d%H%M%S')
	desPath = desDir + "\\" + fileName + ".xls"

	shutil.copy(sourcePath,desPath)
	workBook = xlrd.open_workbook(desPath,formatting_info=True)
	wBook = xlutils.copy.copy(workBook)
	wSheet = wBook.get_sheet(0)
	
	wSheet.write(2,2,userName,blackCenterStyle)
	wSheet.write(2,4,deviceCode,blackCenterStyle)
	wSheet.write(3,2,testTime,blackCenterStyle)
	wSheet.write(3,4,barcode,blackCenterStyle)
	
	testItem= list()
	itemNum=len(input)-7
	i=7
	j=1
	row=15
	for k in range(itemNum):
		testItem = input[i].split(",")
		wSheet.write(row,0,str(j),blackCenterStyle) #编号
		wSheet.write(row,1,testItem[0],blackLeftStyle) #测试项名称
		wSheet.write(row,2,testItem[1],blackLeftStyle)   #测试项条件
		wSheet.write(row,3,testItem[2],blackLeftStyle)   #测试项标准
		wSheet.write(row,4,testItem[3],blackLeftStyle)   #测试值
		if testItem[4] == "P" or testItem[4] == "OK" or testItem[4] == "True":   #测试结果
			wSheet.write(row,5,"通过",greenCenterStyle) 
		elif  testItem[4] == "N" or testItem[4] == "NG" or testItem[4] == "False":
			wSheet.write(row,5,"失败",redCenterStyle)
		elif testItem[4] == "null" or testItem[4] == "NULL" or testItem[4] == None:
			wSheet.write(row,5,'未测试',blackCenterStyle)
			wSheet.write(row,4,'',blackLeftStyle)   #测试值
		else:
			wSheet.write(row,5,testItem[4],blackLeftStyle)
		i+=1
		j+=1
		row+=1
		
	wBook.save(desPath)
	return "OK"