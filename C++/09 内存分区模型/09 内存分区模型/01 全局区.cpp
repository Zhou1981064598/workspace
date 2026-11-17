/*
C++程序在执行时。将内存大方向划分为4个区域
代码区 :存放函数体的二进制代码，由操作系统进行管理的
代码区是共享的,也是只读的
全局区 : 存放全局变量和静态变量以及常量
栈区 : 由编译器自动分配释放, 存放函数的参数值, 局部变量等
堆区 : 由程序员分配和释放若程序员不释放, 程序结束时由操作系统回

内存四区意义;
不同区域存放的教据，赋予不同的生命周期,
给我们更大的灵活编程

*/
//全局变量
int g_a = 10;
int g_b = 10;

//const修饰的全局变量,全局常量
const int c_g_a = 10;
const int c_g_b = 10;

#include<iostream>
using namespace std;

int main1() {

	//全局区存放全局变量、静态变量、常量(全局常量、字符串常量)

	//创建普通局部变量
	int a = 10;
	int b = 10;
	cout << "局部变量a的地址是： " << (int) & a << endl;
	cout << "局部变量b的地址是： " << (int)&b << endl;
	
	cout << "全局变量g_a的地址是： " << (int)&g_a << endl;
	cout << "全局变量g_b的地址是： " << (int)&g_b << endl;
	
	//静态变量
	static int s_a = 10;
	static int s_b = 10;

	cout << "静态变量s_a的地址是： " << (int)&s_a << endl;
	cout << "静态变量s_b的地址是： " << (int)&s_b << endl;

	//常量
	//字符串常量
	//const修饰变量
	"hello world";
	cout << "字符串常量的地址为：" << (int)&"hello world" << endl;

	//const修饰的变量
	//const修饰的全局变量,const修饰的局部变量

	cout << "全局常量 c_g_a的地址为：" << (int)&c_g_a << endl;
	cout << "全局常量 c_g_b的地址为：" << (int)&c_g_b << endl;
	//const修饰的局部变量；
	int c_l_a = 10;
	int c_l_b = 10;
	cout << "局部常量 c_l_a的地址为：" << (int)&c_l_a << endl;
	cout << "局部常量 c_l_b的地址为：" << (int)&c_l_b << endl;

	system("pause");

	return 0;
}