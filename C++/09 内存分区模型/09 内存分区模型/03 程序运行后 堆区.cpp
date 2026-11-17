#include<iostream>
using namespace std;
/*堆区的内存由程序员来分配和释放, 
程序结束后如果还未释放，操作系统自动回收*/
int * func() {
	//利用new关键字，可以将数据开辟到堆区
	//指针本质也是局部变量，放在栈上，指针保存的数据放在堆区
	
	int *p=new int(10);//new 返回的是一个地址
	return p;
}
//在堆区开辟数据
void test01() {
	//创建10个整型数据的数组，在堆区
	int *arr=new int[10];//10代表数组有10个元素
	for (int i = 0; i < 10; i++)
	{
		arr[i] = 100 + i;
	}
	for (int i = 0; i < 10; i++)
	{
		cout << arr[i] << endl;
	}
	//释放堆区数组
	//释放数组的时候，要加[]才可以
	delete[]arr;
}
int main() {

	//在堆区开辟数据
		int *p =func();
	//堆区开辟的数据，由程序员手动开辟，手动释放，释放利用操作符delete
		cout << *p << endl;
		cout << *p << endl;
		delete p;
		//test01();

	system("pause");

	return 0;
}