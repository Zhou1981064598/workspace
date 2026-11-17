#include<iostream>
using namespace std;

/*
作用:函数名可以相同,提高复用性
函数重载满足条件 :
·同一个作用域下
·函数名称相同
·函数参数类型不同或者个数不同或者顺序不同

注意 : 函数的返回值不可以作为函数重载的条件
*/
void func1(int a,int b) {
	cout << "func的调用" << endl;
}
/*int func1(int a, int b) {
	cout << "func的调用" << endl;
}*///错误：函数的返回值不可以作为函数重载的条件
void func1(int a, int b,int c) {
	cout << "func(int a, int b,int c)的调用" << endl;
}
void func1(int a, double b) {
	cout << "func(int a, double b)的调用" << endl;
}
void func1(double b,int a ) {
	cout << "func(double b,int a)的调用" << endl;
}
int main3() {
	func1(1,2);
	func1(1,2,3);
	func1(1,2.1);
	func1(2.1,1);
	system("pause");

	return 0;
}