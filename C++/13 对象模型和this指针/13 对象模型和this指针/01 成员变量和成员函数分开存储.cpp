#include<iostream>
using namespace std;
//成员变量和成员函数是分开存储的
class Person1
{
public:
	int m_A;//非静态成员变量，属于类的对象上

	static int m_B;//静态成员变量 不属于类对象上
	void func(){}//非静态成员函数 也不属于对象上
	static void func2(){}//静态成员函数 也不属于类对象
};
void test01() {
	Person1 p;
	//空对象占用内存空间为1字节
	//C++编译器会给每个空对象分配一个字节的空间是为了区分空对象占对象的位置
	//每个空对象都有一个独一无二的内存地址
	cout << "sizeof p= " << sizeof(p) << endl;
}
int main1() {
	test01();

	system("pause");

	return 0;
}