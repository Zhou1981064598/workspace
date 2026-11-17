#include<iostream>
using namespace std;
//默认情况下，C++编译器至少给一个类添加3个函数
//1．默认构造函数(无参，函数体为空)
//2．默认析构函数(无参，函数体为空)
//3．默认拷贝构造函敦，对属性进行值拷贝
//构造函数调用规则如下 :
//·如果用户定义有参构造函数，c++不在提供默认无参构造，但是会提供默认拷贝构造
//·如果用户定义拷贝构造函数，C++不会再提供其他构造函数
class Person5
{
public:
	Person5();
	Person5(int age);
	//Person5(const Person5 &p);
	~Person5();
	int m_Age;

};

Person5::Person5()
{
	cout << "Person默认构造函数调用" << endl;
}
Person5::Person5(int age)
{
	m_Age = age;
	cout << "Person有参构造函数调用" << endl;
}
//Person5::Person5(const Person5& p)
//{
//	m_Age = p.m_Age;
//	cout << "Person拷贝构造函数调用" << endl;
//}
void test09() {
	Person5 p;
	p.m_Age = 18;

	Person5 p2(p);
	cout << "p2的年龄是" << p2.m_Age << endl;
}
Person5::~Person5()
{
	cout << "Person析构函数调用" << endl;
}
int main11() {
	test09();
	system("pause");
	return 0;
}