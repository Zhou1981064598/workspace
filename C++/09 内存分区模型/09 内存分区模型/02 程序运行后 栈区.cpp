#include<iostream>
using namespace std;
//栈区数据的注意事项
//栈区的数据由编译器管理开辟和释放

int * func(int b) {
	int a = 10;//局部变量,存放再栈区， 栈区数据再函数运行玩后自动释放
	b = 10;//形参也会放在栈区
	return &a;//返回局部变量地址(不要这样写)
}
int main2() {

	//接受func函数的返回值
	int*p =func(1);

	cout << *p << endl;//第一次可以打印正确的数字，是因为编译器做了保留
	cout << *p << endl;//第二次这个数据就不再保留了

	system("pause");

	return 0;
}