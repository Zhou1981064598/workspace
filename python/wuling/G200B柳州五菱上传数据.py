import sys

sys.path.append(r"C:\Python27")
sys.path.append(r"C:\Python27\Lib")
import urllib
import urllib2
import json
import pymysql


# function - 上传测试数据
# input - 和数据库表字段顺序完全一致
# VIN、VSN码、测试人员、测试时间、测试结果、HECU测试结果、HECU控制器名称、HECU控制器名称检测结果、HECU零件号、HECU零件号检测结果、HECU供应商代码、HECU供应商代码检测结果、
# HECU故障码_1、HECU故障码_2、HECU软件版本标准、HECU软件版本、HECU软件版本合格、HECU硬件版本标准、HECU硬件版本、HECU硬件版本合格、HECU_VIN写入、HECUVIN写入检测结果、
# HECU整车配置字、HECU配置字检测结果、HECU动力防盗SK码、HECU ECU流水号、BCM测试结果、BCM控制器名称、BCM控制器名称检测结果、BCM零件号、BCM零件号检测结果、BCM供应商代码、
# BCM供应商代码检测结果、BCM故障码_1、BCM故障码_2、BCM软件版本合格、BCM软件版本标准、BCM软件版本、BCM硬件版本合格、BCM硬件版本标准、BCM硬件版本、BCM_VIN写入、BCMVIN写入检测结果、
# BCM整车配置字、BCM配置字测试结果、BCM ECU流水号、MCU测试结果、MCU控制器名称、MCU控制器名称检测结果、MCU零件号、MCU零件号检测结果、MCU供应商代码、MCU供应商代码检测结果、
# MCU故障码_1、MCU故障码_2、MCU软件版本标准、MCU软件版本、MCU软件版本合格、MCU硬件版本标准、MCU硬件版本、MCU硬件版本合格、MCU_VIN写入、MCUVIN写入检测结果、MCU整车配置字、
# MCU配置字检测结果、EPS测试结果、EPS控制器名称、EPS控制器名称检测结果、EPS零件号、EPS零件号检测结果、EPS供应商代码、EPS供应商代码检测结果、EPS故障码_1、EPS故障码_2、
# EPS软件版本标准、EPS软件版本、EPS软件版本合格、EPS硬件版本标准、EPS硬件版本、EPS硬件版本合格、EPS_VIN写入、EPSVIN写入检测结果、ABS测试结果、ABS控制器名称、ABS控制器名称检测结果、
# ABS零件号、ABS零件号检测结果、ABS供应商代码、ABS供应商代码检测结果、ABS故障码_1、ABS故障码_2、ABS软件版本标准、ABS软件版本、ABS软件版本合格、ABS硬件版本标准、ABS硬件版本、
# ABS硬件版本合格、ABS_VIN写入、ABSVIN写入检测结果、ABS整车配置字、ABS配置字检测结果、ABS ECU流水号、ACCU测试结果、ACCU控制器名称、ACCU控制器名称检测结果、ACCU零件号、
# ACCU零件号检测结果、ACCU供应商代码、ACCU供应商代码检测结果、ACCU故障码_1、ACCU故障码_2、ACCU软件版本标准、ACCU软件版本、ACCU软件版本合格、ACCU硬件版本标准、ACCU硬件版本、
# ACCU硬件版本合格、ACCU_VIN写入、ACCUVIN写入检测结果、ACCU整车配置字、ACCU配置字检测结果、ACCU ECU流水号、APU测试结果、APU控制器名称、APU控制器名称检测结果、APU零件号、
# APU零件号检测结果、APU供应商代码、APU供应商代码检测结果、APU故障码_1、APU故障码_2、APU软件版本标准、APU软件版本、APU软件版本合格、APU硬件版本标准、APU硬件版本、APU硬件版本合格、
# APU_VIN写入、APUVIN写入检测结果、APU整车配置字、APU配置字检测结果、APU ECU流水号、BMS测试结果、BMS控制器名称、BMS控制器名称检测结果、BMS零件号、BMS零件号检测结果、
# BMS供应商代码、BMS供应商代码检测结果、BMS故障码-1、BMS故障码-2、BMS软件版本标准、BMS软件版本、BMS软件版本合格、BMS硬件版本标准、BMS硬件版本、BMS硬件版本合格、BMS_VIN写入、
# BMSVIN写入检测结果、BMS整车配置字、BMS配置字检测结果、BMS ECU流水号、GW测试结果、GW控制器名称、GW控制器名称检测结果、GW零件号、GW零件号检测结果、GW供应商代码、
# GW供应商代码检测结果、GW故障码_1、GW故障码_2、GW软件版本标准、GW软件版本、GW软件版本合格、GW硬件版本标准、GW硬件版本、GW硬件版本合格、GW_VIN写入、GWVIN写入检测结果、GW整车配置字、
# GW配置字检测结果、GW ECU流水号、IC测试结果、IC控制器名称、IC控制器名称检测结果、IC零件号、IC零件号检测结果、IC供应商代码、IC供应商代码检测结果、IC故障码_1、IC故障码_2、
# IC软件版本标准、IC软件版本、IC软件版本合格、IC硬件版本标准、IC硬件版本、IC硬件版本合格、IC_VIN写入、ICVIN写入检测结果、IC整车配置字、IC配置字检测结果、LAM测试结果、LAM控制器名称、
# AM控制器名称检测结果、LAM零件号、LAM零件号检测结果、LAM供应商代码、LAM供应商代码检测结果、LAM故障码_1、LAM故障码_2、LAM软件版本标准、LAM软件版本、LAM软件版本合格、LAM硬件版本标准、
# LAM硬件版本、LAM硬件版本合格、LAM_VIN写入、LAMVIN写入检测结果、LAM整车配置字、LAM配置字检测结果、LAM ECU流水号、PTC测试结果、PTC控制器名称、PTC控制器名称检测结果、PTC零件号、
# PTC零件号检测结果、PTC供应商代码、PTC供应商代码检测结果、PTC故障码_1、PTC故障码_2、PTC软件版本标准、PTC软件版本、PTC软件版本合格、PTC硬件版本标准、PTC硬件版本、PTC硬件版本合格、
# PTC_VIN写入、PTCVIN写入检测结果、PTC整车配置字、PTC配置字检测结果、PTC ECU流水号、TCS测试结果、TCS控制器名称、TCS控制器名称检测结果、TCS零件号、TCS零件号检测结果、TCS供应商代码、
# TCS供应商代码检测结果、TCS故障码_1、TCS故障码_2、TCS软件版本标准、TCS软件版本、TCS软件版本合格、TCS硬件版本标准、TCS硬件版本、TCS硬件版本合格、TCS_VIN写入、TCSVIN写入检测结果、
# TCS整车配置字、TCS配置字检测结果、TCS ECU流水号、TBOX测试结果、TBOX控制器名称、TBOX控制器名称检测结果、TBOX零件号、TBOX零件号检测结果、TBOX供应商代码、
# TBOX供应商代码检测结果、TBOX故障码_1、TBOX故障码_2、TBOX软件版本标准、TBOX软件版本、TBOX软件版本合格、TBOX硬件版本标准、TBOX硬件版本、TBOX硬件版本合格、TBOX_VIN写入、
# TBOXVIN写入检测结果、TBOX整车配置字、TBOX配置字检测结果、TBOX ECU流水号、TBOX登签认证、TUID码、SIM卡号、IMEI号码、ICCID号码、网络状态、数字证书编号、第一链路地址、第一链路端口、
# 第二链路地址、第二链路端口、第三链路地址、第三链路端口、OBC测试结果、OBC供应商代码、OBC供应商代码检测结果、OBC故障码_1、OBC故障码_2、OBC软件版本标准、OBC软件版本、OBC软件版本合格、
# OBC硬件版本标准、OBC硬件版本、OBC硬件版本合格、OBC整车配置字、OBC配置字检测结果、OBC_VIN写入、OBCVIN写入检测结果、
# output- 上传结果信息 成功:OK  失败：具体失败信息
def Convert(input):
    server = "10.8.82.37"
    database = "SQLServer"
    uid = "VHC"
    pwd = "VHC"

    mesUrl = "server%3D{};database%3D{};uid%3D{};pwd%3D{};".format(server, database, uid, pwd)
    pluginUrl = "http://127.0.0.1:10002/api/pyOutStation"
    url = pluginUrl + "?MesUrl=" + mesUrl + "&ModuleCode=" + input[0]

    cmdText = "insert into IF_VHC_DIANJIAN values('{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}',\
												'{}', '{}', '{}', '{}','{}','{}','{}','{}','{}','{}','{}', '{}', '{}', '{}','{}','{}','{}','{}','{}',\
												'{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}')".format(
        input[0], input[1], input[2], input[3], input[4], input[5], input[6], input[7], input[8], input[9],
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
        input[149],
        input[150], input[151], input[152], input[153], input[154], input[155], input[156], input[157], input[158],
        input[159],
        input[160], input[161], input[162], input[163], input[164], input[165], input[166], input[167], input[168],
        input[169],
        input[170], input[171], input[172], input[173], input[174], input[175], input[176], input[177], input[178],
        input[179],
        input[180], input[181], input[182], input[183], input[184], input[185], input[186], input[187], input[188],
        input[189],
        input[190], input[191], input[192], input[193], input[194], input[195], input[196], input[197], input[198],
        input[199],
        input[200], input[201], input[202], input[203], input[204], input[205], input[206], input[207], input[208],
        input[209],
        input[210], input[211], input[212], input[213], input[214], input[215], input[216], input[217], input[218],
        input[219],
        input[220], input[221], input[222], input[223], input[224], input[225], input[226], input[227], input[228],
        input[229],
        input[230], input[231], input[232], input[233], input[234], input[235], input[236], input[237], input[238],
        input[239],
        input[240], input[241], input[242], input[243], input[244], input[245], input[246], input[247], input[248],
        input[249],
        input[250], input[251], input[252], input[253], input[254], input[255], input[256], input[257], input[258],
        input[259],
        input[260], input[261], input[262], input[263], input[264], input[265], input[266], input[267], input[268],
        input[269],
        input[270], input[271], input[272], input[273], input[274], input[275], input[276], input[277], input[278],
        input[279],
        input[280], input[281], input[282], input[283], input[284], input[285], input[286], input[287], input[288],
        input[289],
        input[290], input[291], input[292], input[293], input[294], input[295], input[296], input[297], input[298],
        input[299],
        input[300], input[301], input[302], input[303], input[304], input[305], input[306], input[307], input[308],
        input[309],
        input[310], input[311], input[312], input[313], input[314], input[315], input[316], input[317], input[318],
        input[319],
        input[320], input[321], input[322], input[323], input[324], input[325], input[326], input[327],
        input[328], ).encode('utf-8')

    req = urllib2.Request(url, cmdText, headers={'content-type': 'application/json'})
    response = urllib2.urlopen(req)
    retJson = response.read().decode('utf-8')

    return retJson
