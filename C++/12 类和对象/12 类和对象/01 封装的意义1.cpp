#include<iostream>
using namespace std;
//C++面向对象的三大特性为:封装、继承、多态
//C++认为万事万物都皆为对象，对象上有其属性和行为
/*
封装的意义:
·将属性和行为作为一个整体，表现生活中的事物
·将属性和行为加以权限控制
*/

//设计一个圆类，求圆的周长2*PI*半径
const double PI = 3.14;
//class 代表设计一个类，类后面紧跟着的就是类名称
class Circle {
	//访问权限
	//公共权限
public:
	//属性
	//半径
	int m_r;
	//行为
	//获取圆的周长
	double caculateZC() {
		return 2 * PI * m_r;
	}
};
int main1() {
	//通过圆类 创还能具体的圆（对象）,即实例化
	Circle c1;
	//给圆对象的属性赋值
	c1.m_r = 10;
	cout << "圆的周长为:" << c1.caculateZC() << endl;
	
	system("pause");

	return 0;
}

