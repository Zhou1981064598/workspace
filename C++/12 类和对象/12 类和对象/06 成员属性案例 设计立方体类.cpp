#include<iostream>
using namespace std;


//设计立方体类(Cube)求出重方体的面积和体积
//分别用全局函数和成员函数判断两个立方体是否相等。
class Cube {
private:
	int m_H;

	int m_W;

	int m_L;
public:
	//设置高
	void setH(int h) {
		m_H = h;
	}
	//设置宽
	void setW(int w) {
		m_W = w;
	}
	//设置长
	void setL(int l) {
		m_L = l;
	}
	//获取高
	int getH() {
		return m_H;
	}
	//获取宽
	int getW() {
		return m_W;
	}
	//获取长
	int getL() {
		return m_L;
	}
	//获取立方体面积
	int calculateS() {
		return m_L * m_W * 2 + m_H * m_W * 2 + m_L * m_H * 2;
	}
	//获取立方体体积
	int calculateV() {
		return m_L * m_W * m_H;
	}
	//利用成员函数判断两个立方体是否相等
	bool isSameByClass(Cube& c) {
		if (c.getH() == m_H && c.getL() == m_L && c.getW() == m_W) {

			return true;
		}
		else
		{
			return false;
		}

	}
};
//利用全局函数判断两个立方体是否相等
bool isSame(Cube& c1, Cube& c2) {
	if (c1.getH() == c2.getH() && c1.getL() == c2.getL() && c1.getW() == c2.getW()) {

		return true;
	}
	else
	{
		return false;
	}

}
int main6() {

	Cube c1;
	c1.setH(10);
	c1.setL(15);
	c1.setW(10);
	
	Cube c2;
	c2.setH(101);
	c2.setL(15);
	c2.setW(10);

	//if (isSame(c1,c2))
	//{
	//	cout << "c1和c2相等" << endl;
	//}
	//else {
	//	cout << "c1和c2不相等" << endl;
	//}
	if (c1.isSameByClass(c2))
	{
		cout << "c1和c2相等" << endl;
	}
	else {
		cout << "c1和c2不相等" << endl;
	}
	cout << "c1的面积为:" << c1.calculateS() << endl;
	cout << "c1的体积为:" << c1.calculateV() << endl;

	system("pause");

	return 0;
}