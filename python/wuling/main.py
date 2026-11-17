# coding=utf-8
import urllib
import urllib2
import json
import pymysql
import sys
reload(sys)
sys.setdefaultencoding('utf8')

def test():
    # 打开数据库连接
    connect = pymysql.connect(
        # 主机IP地址
        host="127.0.0.1",
        # 用户名
        user="root",
        # 密码
        password="123456",
        # 端口号 数据库默认是3306
        port=3306,
        # 编码格式
        charset="utf8")
    # 创建游标
    cursor = connect.cursor()
    # 创建sql,使用huike_cloud_edge数据库
    sql_use = "use worktest"
    # 执行sql
    cursor.execute(sql_use)

    rowNums = 0
    try:
        # 创建sql 查询表xb_evdjgj1中元组数量
        cursor.execute("SELECT COUNT(*) FROM g200b_bms_dtc")
        # 查询单个数据，返回数据为元组格式
        result = cursor.fetchone()
        rowNums = result[0]
    # 异常处理，不需要修改
    except Exception as e:
        return "查询出错：case%s" % e
    finally:
        # 关闭游标连接
        cursor.close()
        # 关闭数据库连接
        connect.close()
    return rowNums


def Convert(input):
    server = "127.0.0.1"
    port = 3306
    database = "cloudlibrary"
    UserID = "root"
    password = "123456"

    mesUrl = "server%3D{};port%3D{};User%3D{};password%3D{};Database%3D{};".format(server, port, UserID, password,
                                                                                   database)
    pluginUrl = "http://127.0.0.1:8080/cloudlibrary/book/addBook"

    # http://127.0.0.1:10002/api/pyOutStation?MesUrl=server%3D{};port%3D{};User%3D{};password%3D{};Database%3D{}&ModuleCode=VIN
    url = pluginUrl + "?MesUrl=" + mesUrl + "&ModuleCode=" + input[0]

    # id = test() + 1

    cmdText = "insert into book values('{}','{}','{}','{}','{}','{}','{}','{}','{}')".format(
        int(input[0]),
        input[1],
        input[2],
        input[3],
        input[4],
        int(input[5]),
        input[6],
        input[7],
        input[8])\
        .encode('utf-8')
    # 传入一个url，实例化url，返回一个Request对象
    req = urllib2.Request(url, cmdText, headers={'content-type': 'application/json'})
    # 传入一个Request对象或者一个url，打开url，返回一个HTTPResposne类型的对象,即返回响应对象
    response = urllib2.urlopen(req)
    # 获取响应对象中的
    retJson = response.read().decode('utf-8')

    return retJson


if __name__ == '__main__':
    print (Convert(['15', '围城', '9787020127894', '人民文学出版社', '钱钟书', '382', '25', '2020-12-18', '0']))

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
