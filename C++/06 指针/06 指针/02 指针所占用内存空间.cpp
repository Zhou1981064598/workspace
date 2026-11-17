#include<iostream>
using namespace std;

int main2() {
	//在32位系统中，指针占用4个字节，64位中占8字节
	int a = 10;

	int* p;

	cout << "指针占用内存为："<<sizeof(p) << endl;

	system("pause");

	return 0;
}