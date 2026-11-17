#include<iostream>
using namespace std;
/*
作用:函数传参时，可以利用引用的技术让形参修
饰实参优点:可以简化指针修改实参
*/

//交换函数
//值传递
void mySwap01(int a, int b) {
	int temp = a;
	a = b;
	b = temp;
	//cout << "a=:" << a << endl;
	//cout << "b=:" << b << endl;
}
//地址传递
void mySwap02(int *a, int *b) {
	int temp = *a;
	*a = *b;
	*b = temp;
	//cout << "a=:" << *a << endl;
	//cout << "b=:" << *b << endl;
}
//引用传递
void mySwap03(int &a, int &b) {
	int temp = a;
	a = b;
	b = temp;
	cout << "a=:" << a << endl;
	cout << "b=:" << b << endl;
}
int main3() {
	int a = 10;

	int b = 20;
	
	//mySwap01(a, b);//值传递，形参不修改实参
	//cout << "a=:" << a << endl;
	//cout << "b=:" << b << endl;

	//mySwap02(&a, &b);//地址传递，形参修改实参
	//cout << "a=:" << a << endl;
	//cout << "b=:" << b << endl;

	mySwap03(a,b);//引用传递，形参也修改实参
	cout << "a=:" << a << endl;
	cout << "b=:" << b << endl;

	system("pause");

	return 0;
}