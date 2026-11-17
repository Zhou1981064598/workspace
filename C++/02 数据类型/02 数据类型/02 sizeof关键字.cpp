#include<iostream>
using namespace std;

int main2() {
	//可以用sizeof关键字求出数据类型的内存大小
	//语法：sizeof(数据类型/变量)
	short num1 = 10;

	cout << "short占用内存空间为：" << sizeof(num1) << endl;


	int num2 = 10;

	cout << "int占用内存空间为：" << sizeof(int) << endl;


	system("pause");

	return 0;
}