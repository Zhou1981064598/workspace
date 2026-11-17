#include<iostream>
using namespace std;

/*
设计一个学生类，属性有姓名和学号，
可以给姓名和学号赋值，可以显示学生
的姓名和学号
*/
class student
{
public:
	string m_name;
	string sno;
	void showName() {
		cout << "学生的姓名是：" << m_name << endl;
	}
	void showSno() {
		cout << "学生的学号是是：" << sno << endl;
	}
	void setName(string name) {
		m_name = name;
	}
};
int main2() {
	student s1;
	s1.m_name = "张三", s1.sno = "001";
	//s1.showName();
	//s1.showSno();
	student s2;
	s2.setName("李四");
	s2.showName();
	system("pause");

	return 0;
}