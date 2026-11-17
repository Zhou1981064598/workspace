#include<iostream>
using namespace std;
/*
作用:常量引用主要用来修饰形参，防止误操作
在函数形参列表中，可以加const修饰形参
防止形参改变实参
*/

//打印数据//加入const 防止修改val值)
void showValue(const int& val) {
	//val = 1000;//错误，不可修改常量引用
	cout << "val=" << val << endl;
}
int main() {
	//常量引用
	//int a1 = 10;
	
	//int& ref = 10;//错误：引用必须是一块合法的内存空间
	
	//加上const之后 编译器将代码修改 int temp=10;const int &ref=temp;
	const int& ref = 10;

	//ref = 20;//错误：加入const之后变为只读，不可修改
	int a = 100;

	showValue(a);
	system("pause");

	

	return 0;
}