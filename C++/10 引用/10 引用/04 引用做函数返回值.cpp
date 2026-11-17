#include<iostream>
using namespace std;
//作用:引用是可以作为函数的返回值存在的
//注意 : 不要返回局部变量引用
int & test01() {
	int a = 10;
	return a;
}
//用法 : 函数调用作为左值
int& test02() {

	static int a = 10;//静态变量存放在全局区，全局区数据在程序结束释放
	return a;

}
	int main4() {
	int& ref = test01();
	int& ref2 = test02();
	
	test02() = 1000;//如果函数返回的是一个引用，那么函数调用可以作为左值

	cout << "ref=" << ref << endl;//结果错误，因为a的内存已经被释放
	cout << "ref2=" << ref2 << endl;


	system("pause");

	return 0;
}