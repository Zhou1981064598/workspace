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
        cursor.execute("SELECT COUNT(*) FROM xb_evdjgj2")
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
# input - VIN,测试时间,BCM测试结果,BCM控制器名称,BCM控制器名称检测结果,BCM零件号,BCM零件号检测结果,BCM供应商代码,BCM供应商代码检测结果,BCM故障码_1,BCM故障码_2,BCM软件版本合格,BCM软件版本标准,BCM软件版本,BCM硬件版本合格,
#         BCM硬件版本标准,BCM硬件版本,BCM_VIN写入,BCMVIN写入检测结果,BCM整车配置字,BCM配置字测试结果,BCM车灯/车门/车窗功能测试,BCM ECU流水号,BCM动力防盗SK码,AC测试结果,AC控制器名称,AC控制器名称检测结果,AC零件号,AC零件号检测结果,
#         AC供应商代码,AC供应商代码检测结果,AC故障码_1,AC故障码_2,AC软件版本标准,AC软件版本,AC软件版本合格,AC硬件版本标准,AC硬件版本,AC硬件版本合格,AC_VIN写入,ACVIN写入检测结果,AC整车配置字,AC配置字检测结果,AC ECU流水号,OBC测试结果,
#         OBC供应商代码,OBC供应商代码检测结果,OBC故障码_1,OBC故障码_2,OBC软件版本标准,OBC软件版本,OBC软件版本合格,OBC硬件版本标准,OBC硬件版本,OBC硬件版本合格,OBC整车配置字,OBC配置字检测结果,OBC_VIN写入,OBCVIN写入检测结果,TBOX测试结果,
#         TBOX控制器名称,TBOX控制器名称检测结果,TBOX零件号,TBOX零件号检测结果,TBOX供应商代码,TBOX供应商代码检测结果,TBOX故障码_1,TBOX故障码_2,TBOX软件版本标准,TBOX软件版本,TBOX软件版本合格,TBOX硬件版本标准,TBOX硬件版本,TBOX硬件版本合格,
#         TBOX_VIN写入,TBOXVIN写入检测结果,TBOX整车配置字,TBOX配置字检测结果,TBOX ECU流水号,TBOX登签认证,TUID码,SIM卡号,IMEI号码,ICCID号码,网络状态,数字证书编号,第一链路地址,第一链路端口,第二链路地址,第二链路端口,第三链路地址,第三链路端口,
#         LAM测试结果,LAM控制器名称,LAM控制器名称检测结果,LAM零件号,LAM零件号检测结果,LAM供应商代码,LAM供应商代码检测结果,LAM故障码_1,LAM故障码_2,LAM软件版本标准,LAM软件版本,LAM软件版本合格,LAM硬件版本标准,LAM硬件版本,LAM硬件版本合格,LAM_VIN写入,
#         LAMVIN写入检测结果,LAM整车配置字,LAM配置字检测结果,LAM ECU流水号,IC测试结果,IC控制器名称,IC控制器名称检测结果,IC零件号,IC零件号检测结果,IC供应商代码,IC供应商代码检测结果,IC故障码_1,IC故障码_2,IC软件版本标准,IC软件版本,IC软件版本合格,
#         IC硬件版本标准,IC硬件版本,IC硬件版本合格,IC_VIN写入,ICVIN写入检测结果,IC整车配置字,IC配置字检测结果,EPS测试结果,EPS控制器名称,EPS控制器名称检测结果,EPS零件号,EPS零件号检测结果,EPS供应商代码,EPS供应商代码检测结果,EPS故障码_1,EPS故障码_2,
#         EPS软件版本标准,EPS软件版本,EPS软件版本合格,EPS硬件版本标准,EPS硬件版本,EPS硬件版本合格,EPS_VIN写入,EPSVIN写入检测结果,EPS ECU流水号,质量系统同步标志（整型）
# output- 上传结果信息 成功:OK  失败：具体失败信息
def Convert(input):
    server = "10.8.70.231"
    port = 3306
    database = "huike_cloud_edge"
    UserID = "userXB"
    password = "Xb123654"

    mesUrl = "server%3D{};port%3D{};User%3D{};password%3D{};Database%3D{};".format(server, port, UserID, password,
                                                                                   database)
    pluginUrl = "http://127.0.0.1:10002/api/pyOutStation"
    url = pluginUrl + "?MesUrl=" + mesUrl + "&ModuleCode=" + input[0]

    id = GetID() + 1
    cmdText = "insert into xb_evdjgj2 values('{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
	                                         '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
											 '{}','{}','{}','{}','{}','{}','{}','{}','{}','{}')".format(
        id, input[0], input[1], input[2], input[3], input[4], input[5], input[6], input[7], input[8], input[9],
        input[10], input[11], input[12], input[13], input[14], input[15], input[16], input[17], input[18], input[19],
        input[20], input[21], input[22], input[23], input[24], input[25], input[26], input[27], input[28], input[29],
        input[30], input[31], input[32], input[33], input[34], input[35], input[36], input[37], input[38], input[39],
        input[40], input[41], input[42], input[43], input[44], input[45], input[46], input[47], input[48], input[49],
        input[50], input[51], input[52], input[53], input[54], input[55], input[56], input[57], input[58], input[59],
        input[60], input[61], input[62], input[63], input[64], input[65], input[66], input[67], input[68], input[69],
        input[70], input[71], input[72], input[73], input[74], input[75], input[76], input[77], input[78], input[79],
        input[80], input[81], input[82], input[83], input[84], input[85], input[86], input[87], input[88], input[89],
        input[90], input[91], input[92], input[93], input[94], input[95], input[96], input[97], input[98], input[99],
        input[100], input[101], input[102], input[103], input[104], input[105], input[106], input[107], input[108],
        input[109],
        input[110], input[111], input[112], input[113], input[114], input[115], input[116], input[117], input[118],
        input[119],
        input[120], input[121], input[122], input[123], input[124], input[125], input[126], input[127], input[128],
        input[129],
        input[130], input[131], input[132], input[133], input[134], input[135], input[136], input[137], input[138],
        input[139],
        input[140], input[141], input[142], input[143], input[144], input[145], input[146], input[147], input[148],
        int(input[149])).encode('utf-8')

    req = urllib2.Request(url, cmdText, headers={'content-type': 'application/json'})
    response = urllib2.urlopen(req)
    retJson = response.read().decode('utf-8')

    return retJson
