#include"circle.h"
void Circle1::setR(int r) {
	m_R = r;
}
//获取半径
int Circle1::getR() {
	return m_R;
}
//设置圆心
void Circle1::setCenter(int x, int y) {
	m_Center.setX(x);
	m_Center.setY(y);
}
//获取圆心
Point Circle1::getCenter() {
	return m_Center;
}
