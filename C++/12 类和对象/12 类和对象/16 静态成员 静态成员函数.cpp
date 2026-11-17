#include<iostream>
using namespace std;

//静态成员函数
//所有对象共享同一个函敬
//静态成员函数只能访问静态成员变量
class Person_s1
{
public:
	//静态成员函数
	static void func_s() {
		m_A = 100;//静态成员函数只能访问静态成员变量
		/*m_B = 200;*///静态成员函数不能访问非静态变量，因为无法区分到底是哪个对象的
		cout << "static void func 调用"<<endl;
	};
	static int m_A;//静态成员变量
	int m_B;
private:
	static void func2() {
		cout << "static void func2的调用" << endl;
	}
};
int Person_s1::m_A = 0;
void test_s2() {
	//1、通过对象访问
	Person_s1 p;
	p.func_s();

	//2、通过类名访问
	Person_s1::func_s();
	//Person_s1::func2();
}
int main() {
	test_s2();
	system("pause");

	return 0;
}