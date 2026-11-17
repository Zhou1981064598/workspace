#include<iostream>
using namespace std;

int main2() {
	//一维数组名称用途:
	int arr[] = { 10,20,30,40,50 };

//1.可以统计整个数组在内存中的长度
   	cout<<"整个数组占用内存空间为"<<sizeof(arr)<<endl;
	cout << "数组每个元素占用内存空间为" << sizeof(arr[0]) << endl;
	cout << "数组元素个数为" << sizeof(arr)/ sizeof(arr[0]) << endl;

	//2．可以获取数组在内存中的首地址
	cout << "数组首地址是"<<arr << endl;
	cout << "数组中第一个元素的地址为" << &arr[0] << endl;
	cout << "数组中第二个元素的地址为" << &arr[1] << endl;
	system("pause");
	//数组名是一个常量，不可以进行赋值操作
	//错误：arr=100
	return 0;
}