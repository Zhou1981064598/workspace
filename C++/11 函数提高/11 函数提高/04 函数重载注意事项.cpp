#include<iostream>
using namespace std;
//引用作为重载条件
//函数重载碰到函数默认参数
void fun(int& a) {
	cout << "func(int &a)调用" << endl;

}
void fun(const int& a) {
	cout << "func(const int &a)调用" << endl;
}
void fun2(int a) {
	cout << "func2（int a）的调用" << endl;
}
void fun2(int a,int b=10) {
	cout << "func2（int a,int b）的调用" << endl;
}
int main() {
	int a = 10;

	fun(a);//调用变量引用

	fun(10);//调用常量引用，因为int &a=10是不合法的
	//fun2(10);//错误：当函数重载碰到默认参数，出现二义性，出现错误，要避免这种情况出现
	system("pause");

	return 0;
}