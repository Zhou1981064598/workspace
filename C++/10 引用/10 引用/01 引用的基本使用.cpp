#include<iostream>
using namespace std;
//引用:给变量起别名
//语法: 数据类型 & 别名 = 原名

int main1() {
	int a = 10;
	//创建引用
	int& b = a;

	cout << "a= " << a << endl;
	cout << "b= " << b << endl;

	b = 20;

	cout <<"a= " << a << endl;
	cout <<"b= " << b << endl;
	system("pause");

	return 0;
}