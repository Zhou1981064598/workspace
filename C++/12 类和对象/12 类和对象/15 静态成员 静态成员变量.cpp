#include<iostream>
using namespace std;
//静态成员就是在成员变量和成员函数前加上关键字static，称为静态成员
//静态成员分为:
//1、静态成员变量
//。所有对象共享同一份数据。
//。在编译阶段分配内存
//。类内声明，类外初始化

//2、静态成员函数
//。所有对象共享同一个函敬
//。静态成员函数只能访问静态成员变量

//静态成员变量
class Person_s
{
public:
	Person_s();
	~Person_s();
	//静态成员变量也有访问权限
	static int m_A;//类内声明
private:
	static int m_B;
};

Person_s::Person_s()
{
}

Person_s::~Person_s()
{
}
 int Person_s::m_A=100;//类外初始化
 int Person_s::m_B = 200;
void test_s() {
	Person_s p;
	cout << p.m_A << endl;

	Person_s p2;
	p2.m_A = 200;//所有对象共享同一份数据
	cout << p.m_A << endl;

}
void test_s1() {
	//静态成员变量不属于某个对象上，所有对象都共享同一份数据
	//因此静态成员变量有两种访问方式
	//1、通过对象进行访问
/*	Person_s p;
	cout << p.m_A << endl*/;

	//2、通过类名进行访问
	/*cout << Person_s::m_A << endl ;*/
	/*cout << Person_s::m_B << endl;类外访问不到私有静态成员*/

}
int main15() {
	//test_s();
	test_s1();
	system("pause");

	return 0;
}