#include<iostream>
using namespace std;

int main3() {
	//1、单精度(4字节)
	float num1 = 3.1415926f;
	//2、双精度(8字节)
	double num2 = 3.1415926;
	//默认情况下输出一个小数会显示6位有效数字

	cout << "num1=" << num1 << endl;

	cout << "num2=" << num2 << endl;

	//科学计数法
	float f2 = 3e2;

	cout << "f2=" << f2 << endl;

	float f3 = 3e-2;

	cout << "f3=" << f3 << endl;

	system("pause");
	
	return 0;
}