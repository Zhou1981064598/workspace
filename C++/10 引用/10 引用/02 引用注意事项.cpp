#include<iostream>
using namespace std;
//引用必须初始化
//引用在初始化后，不可以改变

int main2() {
	int a = 10;

	//int& b;//错误，必须初始化
	//引用一旦初始化后就不可以更改
	int& b = a;

	int c = 20;

	b = c;//赋值操作，而不是更改引用，所以引用不能修改

	cout << "a=" << a<< endl;
	cout << "b=" << b << endl;
	cout << "c=" << c << endl;
	system("pause");

	return 0;
}