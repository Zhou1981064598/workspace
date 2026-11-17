#include<iostream>
using namespace std;
//在C++中，函数的形参列表中的形参是可以有默认值的。
//语法:返回值类型函数名(参数=默认值）{}
//设置默认值
int func(int a,int b=20,int c=30) {
	return a + b + c;
}

//注意事项：
// 1、如果某个位置已经有了默认参数，那么这个位置往后，从左到右都必须有默认值
/*int func2(int a=10, int b, int c) {
	return a + b + c;
}*///错误，后面两个没有默认值

//2、如果函数声明有默认参数，函数实现就不能有默认参数
//函数声明和实现只能有一方有默认参数

int fun3(int a=10, int b=10);
int main1() {
	//如果没传入参数，则使用默认值，如果传了，则用传入的
	cout<<"相加结果是："<<func(10,30)<<endl;

	system("pause");
	
	return 0;
}
/*int fun3(int a=10, int b=10) {
	return a + b;
}*///错误，声明已经有了默认参数，实现不能再次定义默认参数