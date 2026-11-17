#include<iostream>
using namespace std;

//函数的声明
//比较函数，实现两个整型数字进行比较，返回较大的值
int sum(int a, int b);

int main5() {
	int a = 10, b = 20;

	cout << sum(a, b) << endl;

	system("pause");

	return 0;

}

int sum(int a, int b) {
	return a > b ? a : b;
}