#include<iostream>
using namespace std;
//常量的定义方式
//1、#define宏常量
//2、const修饰的常量

//1、#define宏常量
#define Day 7
int main4()
{
	//Day = 14;错误：常量不可修改

	cout << "一周总共有" << Day<<"天"<< endl;
	//2、const修饰的变量
	const int months = 12;
	
	cout << "一年有" << months << "个月份" << endl;
	
	system("pause"); 
	return 0;
}