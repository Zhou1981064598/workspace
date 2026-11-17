#pragma once
#include<iostream>
#include"point.h"
using namespace std;

class Circle1
{
public:
	//设置半径
	void setR(int r);
	//获取半径
	int getR();
	//设置圆心
	void setCenter(int x, int y);
	//获取圆心
	Point getCenter();
private:
	int m_R;//半径

	//在类中可以让另一个类作为本类中的成员
	Point m_Center;//圆心
};