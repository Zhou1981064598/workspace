# coding=utf-8
import sys

sys.path.append(r"C:\Python27")
sys.path.append(r"C:\Python27\Lib")
sys.path.append(r'C:\Python27\Lib\site-packages')
import urllib2
import pymysql


# 获取表中元组个数，但是意义不明
def GetID():
    # 打开数据库连接
    connect = pymysql.connect(
        # 主机IP地址
        host="10.8.70.231",
        # 用户名
        user="userXB",
        # 密码
        password="Xb123654",
        # 端口号 数据库默认是3306
        port=3306,
        # 编码格式
        charset="utf8")
    # 创建游标
    cursor = connect.cursor()
    # 创建sql,使用huike_cloud_edge数据库
    sql_use = "use huike_cloud_edge"
    # 执行sql
    cursor.execute(sql_use)

    rowNums = 0
    try:
        # 创建sql 查询表xb_evdjgj1中元组数量
        cursor.execute("SELECT COUNT(*) FROM xb_evdjgj1")
        # 查询单个数据，返回数据为元组格式
        result = cursor.fetchone()
        # rowNums代表表中数据个数
        rowNums = result[0]
    # 异常处理，不需要修改
    except Exception as e:
        return "查询出错：case%s" % e
    finally:
        # 关闭游标连接
        cursor.close()
        # 关闭数据库连接
        connect.close()
    # 返回表中数据个数
    return rowNums


# function - EV电检柜接口1上传测试数据
# input - VIN,VSN码,测试人员,测试时间,测试结果,HECU测试结果,HECU控制器名称,HECU控制器名称检测结果,HECU零件号,HECU零件号检测结果,HECU供应商代码,HECU供应商代码检测结果,HECU故障码_1,HECU故障码_2,HECU软件版本标准,HECU软件版本,
#         HECU软件版本合格,HECU硬件版本标准,HECU硬件版本,HECU硬件版本合格,HECU_VIN写入,HECUVIN写入检测结果,HECU整车配置字,HECU配置字检测结果,HECU动力防盗SK码,HECU ECU流水号,HECU读生产日期,HECU读系统供应商ECU硬件号,
#         HECU读系统供应商ECU软件号,HECU读排放型式认证号,HECU读程序更新日期,HECU读标定数据号,HECU标定数据管理版本号,HECUAPP版本号,HECU标定数据支持的硬件版本号,HECUECU硬件版本号和boot状态,ABS测试结果,ABS控制器名称,ABS控制器名称检测结果,
#         ABS零件号,ABS零件号检测结果,ABS供应商代码,ABS供应商代码检测结果,ABS故障码_1,ABS故障码_2,ABS软件版本标准,ABS软件版本,ABS软件版本合格,ABS硬件版本标准,ABS硬件版本,ABS硬件版本合格,ABS_VIN写入,ABSVIN写入检测结果,ABS整车配置字,
#         ABS配置字检测结果,ABS ECU流水号,ESC测试结果,ESC控制器名称,ESC控制器名称检测结果,ESC零件号,ESC零件号检测结果,ESC供应商代码,ESC供应商代码检测结果,ESC故障码_1,ESC故障码_2,ESC软件版本标准,ESC软件版本,ESC软件版本合格,ESC硬件版本标准,
#         ESC硬件版本,ESC硬件版本合格,ESC_VIN写入,ESCVIN写入检测结果,ESC整车配置字,ESC配置字检测结果,ESC ECU流水号,BMS测试结果,BMS控制器名称,BMS控制器名称检测结果,BMS零件号,BMS零件号检测结果,BMS供应商代码,BMS供应商代码检测结果,BMS故障码-1,
#         BMS故障码-2,BMS软件版本标准,BMS软件版本,BMS软件版本合格,BMS硬件版本标准,BMS硬件版本,BMS硬件版本合格,BMS_VIN写入,BMSVIN写入检测结果,BMS整车配置字,BMS配置字检测结果,BMS ECU流水号,BMS读动力电池编码,BMS读绝缘检测功能配置,APU测试结果,
#         APU控制器名称,APU控制器名称检测结果,APU零件号,APU零件号检测结果,APU供应商代码,APU供应商代码检测结果,APU故障码_1,APU故障码_2,APU软件版本标准,APU软件版本,APU软件版本合格,APU硬件版本标准,APU硬件版本,APU硬件版本合格,APU_VIN写入,
#         APUVIN写入检测结果,APU整车配置字,APU配置字检测结果,APU ECU流水号,MCU测试结果,MCU控制器名称,MCU控制器名称检测结果,MCU零件号,MCU零件号检测结果,MCU供应商代码,MCU供应商代码检测结果,MCU故障码_1,MCU故障码_2,MCU软件版本标准,MCU软件版本,
#         MCU软件版本合格,MCU硬件版本标准,MCU硬件版本,MCU硬件版本合格,MCU_VIN写入,MCUVIN写入检测结果,MCU整车配置字,MCU配置字检测结果,DCDC测试结果,DCDC控制器名称,DCDC控制器名称检测结果,DCDC零件号,DCDC零件号检测结果,DCDC供应商代码,
#         DCDC供应商代码检测结果,DCDC故障码_1,DCDC故障码_2,DCDC软件版本标准,DCDC软件版本,DCDC软件版本合格,DCDC硬件版本标准,DCDC硬件版本,DCDC硬件版本合格,DCDC_VIN读取,DCDCVIN读取检测结果,质量系统同步标志（整型） 
# output- 上传结果信息 成功:OK  失败：具体失败信息

def Convert(input):
    server = "10.8.70.231"
    port = 3306
    database = "huike_cloud_edge"
    UserID = "userXB"
    password = "Xb123654"
    # mes数据库地址及账号密码
    mesUrl = "server%3D{};port%3D{};User%3D{};password%3D{};Database%3D{};".format(server, port, UserID, password,
                                                                                   database)
    # 服务器api接口地址
    pluginUrl = "http://127.0.0.1:10002/api/pyOutStation"
    # 拼接url即请求地址
    url = pluginUrl + "?MesUrl=" + mesUrl + "&ModuleCode=" + input[0]
    # id=表总数加1
    id = GetID() + 1
    # 插入语句
    cmdText = "insert into xb_evdjgj1 values('{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
'{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
'{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
'{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
'{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
'{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
'{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}','{}','{}','{}','{}','{}','{}',\
'{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}', '{}','{}','{}',{})".format(id,
                                                                                   input[0], input[1], input[2],
                                                                                   input[3], input[4], input[5],
                                                                                   input[6], input[7], input[8],
                                                                                   input[9],
                                                                                   input[10], input[11], input[12],
                                                                                   input[13], input[14], input[15],
                                                                                   input[16], input[17], input[18],
                                                                                   input[19],
                                                                                   input[20], input[21], input[22],
                                                                                   input[23], input[24], input[25],
                                                                                   input[26], input[27], input[28],
                                                                                   input[29],
                                                                                   input[30], input[31], input[32],
                                                                                   input[33], input[34], input[35],
                                                                                   input[36], input[37], input[38],
                                                                                   input[39],
                                                                                   input[40], input[41], input[42],
                                                                                   input[43], input[44], input[45],
                                                                                   input[46], input[47], input[48],
                                                                                   input[49],
                                                                                   input[50], input[51], input[52],
                                                                                   input[53], input[54], input[55],
                                                                                   input[56], input[57], input[58],
                                                                                   input[59],
                                                                                   input[60], input[61], input[62],
                                                                                   input[63], input[64], input[65],
                                                                                   input[66], input[67], input[68],
                                                                                   input[69],
                                                                                   input[70], input[71], input[72],
                                                                                   input[73], input[74], input[75],
                                                                                   input[76], input[77], input[78],
                                                                                   input[79],
                                                                                   input[80], input[81], input[82],
                                                                                   input[83], input[84], input[85],
                                                                                   input[86], input[87], input[88],
                                                                                   input[89],
                                                                                   input[90], input[91], input[92],
                                                                                   input[93], input[94], input[95],
                                                                                   input[96], input[97], input[98],
                                                                                   input[99],
                                                                                   input[100], input[101], input[102],
                                                                                   input[103], input[104], input[105],
                                                                                   input[106], input[107], input[108],
                                                                                   input[109],
                                                                                   input[110], input[111], input[112],
                                                                                   input[113], input[114], input[115],
                                                                                   input[116], input[117], input[118],
                                                                                   input[119],
                                                                                   input[120], input[121], input[122],
                                                                                   input[123], input[124], input[125],
                                                                                   input[126], input[127], input[128],
                                                                                   input[129],
                                                                                   input[130], input[131], input[132],
                                                                                   input[133], input[134], input[135],
                                                                                   input[136], input[137], input[138],
                                                                                   input[139],
                                                                                   input[140], input[141], input[142],
                                                                                   input[143], input[144], input[145],
                                                                                   input[146], input[147], input[148],
                                                                                   input[149],
                                                                                   input[150], input[151], input[152],
                                                                                   input[153], int(input[154])).encode(
        'utf-8')
    # 传入一个url，实例化url，返回一个Request对象
    req = urllib2.Request(url, cmdText, headers={'content-type': 'application/json'})
    # 传入一个Request对象或者一个url，打开url，返回一个HTTPResposne类型的对象
    response = urllib2.urlopen(req)
    # 获取响应码，解码
    retJson = response.read().decode('utf-8')

    return retJson
