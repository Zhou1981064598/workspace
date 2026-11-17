import sys
sys.path.append(r"C:\Python27")
sys.path.append(r"C:\Python27\Lib")
sys.path.append(r'C:\Python27\Lib\site-packages')
import urllib
import urllib2
import json
import pymysql

def GetID():
	# 打开数据库连接
	connect = pymysql.connect(
		host="10.8.70.231",
		user="userXB",
		password="Xb123654",
		port=3306,
		charset="utf8")
	cursor = connect.cursor()
	sql_use = "use huike_cloud_edge"
	cursor.execute(sql_use)
	
	rowNums = 0
	try:			
		cursor.execute("SELECT COUNT(*) FROM xb_evdjgj3")
		result = cursor.fetchone()
		rowNums = result[0]
	except Exception as e:
		return "查询出错：case%s" % e
	finally:
		# 关闭游标连接
		cursor.close()
		# 关闭数据库连接
		connect.close()
	return rowNums

# function - EV电检柜接口1上传测试数据
# input - VIN,测试时间,TCS测试结果,TCS控制器名称,TCS控制器名称检测结果,TCS零件号,TCS零件号检测结果,TCS供应商代码,TCS供应商代码检测结果,TCS故障码_1,TCS故障码_2,TCS软件版本标准,TCS软件版本,TCS软件版本合格,TCS硬件版本标准,TCS硬件版本,
#         TCS硬件版本合格,TCS_VIN写入,TCSVIN写入检测结果,TCS整车配置字,TCS配置字检测结果,TCS ECU流水号,ACCU测试结果,ACCU控制器名称,ACCU控制器名称检测结果,ACCU零件号,ACCU零件号检测结果,ACCU供应商代码,ACCU供应商代码检测结果,ACCU故障码_1,
#         ACCU故障码_2,ACCU软件版本标准,ACCU软件版本,ACCU软件版本合格,ACCU硬件版本标准,ACCU硬件版本,ACCU硬件版本合格,ACCU_VIN写入,ACCUVIN写入检测结果,ACCU整车配置字,ACCU配置字检测结果,ACCU ECU流水号,PTC测试结果,PTC控制器名称,
#         PTC控制器名称检测结果,PTC零件号,PTC零件号检测结果,PTC供应商代码,PTC供应商代码检测结果,PTC故障码_1,PTC故障码_2,PTC软件版本标准,PTC软件版本,PTC软件版本合格,PTC硬件版本标准,PTC硬件版本,PTC硬件版本合格,PTC_VIN写入,PTCVIN写入检测结果,
#         PTC整车配置字,PTC配置字检测结果,PTC ECU流水号,GW测试结果,GW控制器名称,GW控制器名称检测结果,GW零件号,GW零件号检测结果,GW供应商代码,GW供应商代码检测结果,GW故障码_1,GW故障码_2,GW软件版本标准,GW软件版本,GW软件版本合格,GW硬件版本标准,GW硬件版本,
#         GW硬件版本合格,GW_VIN写入,GWVIN写入检测结果,GW整车配置字,GW配置字检测结果,GW ECU流水号,SDR测试结果,SDR控制器名称,SDR控制器名称检测结果,SDR零件号,SDR零件号检测结果,SDR供应商代码,SDR供应商代码检测结果,SDR故障码_1,SDR故障码_2,SDR软件版本标准,
#         SDR软件版本,SDR软件版本合格,SDR硬件版本标准,SDR硬件版本,SDR硬件版本合格,SDR_VIN写入,SDRVIN写入检测结果,SDR整车配置字,SDR配置字检测结果,SDR ECU流水号,VCU测试结果,VCU控制器名称,VCU控制器名称检测结果,VCU零件号,VCU零件号检测结果,VCU供应商代码,
#         VCU供应商代码检测结果,VCU故障码_1,VCU故障码_2,VCU软件版本标准,VCU软件版本,VCU软件版本合格,VCU硬件版本标准,VCU硬件版本,VCU硬件版本合格,VCU_VIN写入,VCUVIN写入检测结果,VCU整车配置字,VCU配置字检测结果,VCU ECU流水号,GSM测试结果,GSM控制器名称,
#         GSM控制器名称检测结果,GSM零件号,GSM零件号检测结果,GSM供应商代码,GSM供应商代码检测结果,GSM故障码_1,GSM故障码_2,GSM软件版本标准,GSM软件版本,GSM软件版本合格,GSM硬件版本标准,GSM硬件版本,GSM硬件版本合格,GSM_VIN写入,GSMVIN写入检测结果,
#         GSM整车配置字,GSM配置字检测结果,质量系统同步标志（整型）
# output- 上传结果信息 成功:OK  失败：具体失败信息
def Convert(input):
	server ="10.8.70.231"
	port=3306
	database ="huike_cloud_edge"
	UserID = "userXB"
	password = "Xb123654"	
	
	mesUrl = "server%3D{};port%3D{};User%3D{};password%3D{};Database%3D{};".format(server, port, UserID, password,database)
	pluginUrl = "http://127.0.0.1:10002/api/pyOutStation"
	url = pluginUrl + "?MesUrl=" + mesUrl + "&ModuleCode=" + input[0]	
	
	id = GetID()+1
	cmdText = "insert into xb_evdjgj3 values('{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
	                                         '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}')".format(
												id,input[0],input[1],input[2],input[3],input[4],input[5],input[6],input[7],input[8],input[9],
												input[10],input[11],input[12],input[13],input[14],input[15],input[16],input[17],input[18],input[19],
												input[20],input[21],input[22],input[23],input[24],input[25],input[26],input[27],input[28],input[29],
												input[30],input[31],input[32],input[33],input[34],input[35],input[36],input[37],input[38],input[39],
												input[40],input[41],input[42],input[43],input[44],input[45],input[46],input[47],input[48],input[49],
												input[50],input[51],input[52],input[53],input[54],input[55],input[56],input[57],input[58],input[59],
												input[60],input[61],input[62],input[63],input[64],input[65],input[66],input[67],input[68],input[69],
												input[70],input[71],input[72],input[73],input[74],input[75],input[76],input[77],input[78],input[79],
												input[80],input[81],input[82],input[83],input[84],input[85],input[86],input[87],input[88],input[89],
												input[90],input[91],input[92],input[93],input[94],input[95],input[96],input[97],input[98],input[99],
												input[100],input[101],input[102],input[103],input[104],input[105],input[106],input[107],input[108],input[109],
												input[110],input[111],input[112],input[113],input[114],input[115],input[116],input[117],input[118],input[119],
												input[120],input[121],input[122],input[123],input[124],input[125],input[126],input[127],input[128],input[129],
												input[130],input[131],input[132],input[133],input[134],input[135],input[136],input[137],input[138],input[139],
												input[140],int(input[141])).encode('utf-8')

	req = urllib2.Request(url, cmdText,headers={'content-type':'application/json'})
	response = urllib2.urlopen(req)
	retJson = response.read().decode('utf-8')	
		
	return retJson