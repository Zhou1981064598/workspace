#include<iostream>
#include"circle.h"
#include"point.h"
using namespace std;
//设计一个圆形类(Circle)，和一个点类(Point)，计算点和圆的关系。
//根据点到圆心的距离来判断
//class Point
//{
//public:
//	//设置x
//	void setX(int x) {
//		m_X=x;
//	}
//	//设置y
//	void setY(int y) {
//		m_Y = y;
//	}
//	//获取x
//	int getX() {
//		return m_X;
//	}
//	//获取y
//	int getY() {
//		return m_Y;
//	}
//
//private:
//	int m_X;
//	int m_Y;
//};


//class Circle1
//{
//public:
//	//设置半径
//	void setR(int r) {
//		m_R = r;
//	}
//	//获取半径
//	int getR() {
//		return m_R;
//	}
//	//设置圆心
//	void setCenter(int x,int y) {
//		m_Center.setX(x);
//		m_Center.setY(y);
//	}
//	//获取圆心
//	Point getCenter() {
//		return m_Center;
//	}
//private:
//	int m_R;//半径
//	
//	//在类中可以让另一个类作为本类中的成员
//	Point m_Center;//圆心
//};
//判断点和圆的关系
void isInCircle(Circle1& c, Point& p) {
	//计算两点之间距离的平方
	int distance = (p.getX()-c.getCenter().getX())* (p.getX() - c.getCenter().getX()) +(p.getY() - c.getCenter().getY())* (p.getY() - c.getCenter().getY());
	//cout << distance << endl;
	//计算半径的平方
	int rr = c.getR()* c.getR();
	//比较两者大小，从而得出点和圆的关系
	if (distance == rr) {
		cout << "点在圆上" << endl;
	}
	else if (distance>rr)
	{
		cout << "点在圆外" << endl;
	}else
	{
		cout << "点在圆内" << endl;
	}
	
}

int main7() {
	Circle1 c1;
	c1.setCenter(1, 1);
	c1.setR(2);

	Point p1;
	p1.setX(3);
	p1.setY(1);

	isInCircle(c1, p1);

	system("pause");
	return 0;
}