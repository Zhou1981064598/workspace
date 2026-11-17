#include<iostream>
using namespace std;
//构造函数的分类
//两种分类方式:
//按参数分为:有参构造和无参构造
//按类型分为:普通构造和拷贝构造
//三种调用方式:
//括号法
//显示法
//隐式转换法
class Person3
{
public:
	Person3();
	Person3(int a);
	Person3(const Person3 &p);
	~Person3();
	int age;
private:

};
//无参构造（默认构造）
Person3::Person3()
{
	cout << "无参构造函数调用" << endl;
}
//有参构造
Person3::Person3(int a)
{
	age = a;
	cout << "有参构造函数调用" << endl;
}
//拷贝构造函数
Person3::Person3(const Person3 &p)
{
	//将传入的类的所有属性，拷贝到当前类中
	age = p.age;
	cout << "拷贝构造函数调用" << endl;
}
Person3::~Person3()
{
	cout << "析构函数调用" << endl;
}
void test03() {
	//1、括号调用法
	//Person3 p;//默认构造函数调用
	//Person3 p1(10);//有参构造函数
	//Person3 p2(p1);//拷贝构造函数调用

	//注意事项，Person3 p(),在调用默认构造函数时候不要加括号,因为编译器会认为是函数声明
	/*
	cout << "p1的年龄" << p1.age << endl;
	cout << "p2的年龄" << p2.age << endl;
	*/

	//2、显示法
	/*
	Person3 p1;//默认构造函数调用
	Person3 p2 = Person3(10);//有参构造
	Person3 p3 = Person3(p2);//拷贝构造
	*/

	//Person3(10);//匿名对象 特点：当前行执行结束后，系统就立即回收掉匿名对象
	//cout << "aaaaa" << endl;
	
	// 不要利用拷贝构造函数，初始化匿名对象,编译器会认为Person(p3)===Person p3:对象声明
	//Person3(p3);
	
	//3、隐式转换法
	Person3 p4 = 10;//有参构造法，相当于 写了 Person p4=Person(10)
	Person3 p5 = p4;//拷贝构造法
}
int main9() {
	//Person3 p;

	test03();

	system("pause");

	return 0;
}