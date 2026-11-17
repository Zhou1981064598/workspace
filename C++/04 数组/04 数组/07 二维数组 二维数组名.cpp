#include<iostream>
using namespace std;
//二维数组名称用途
//查看二维数组所占内存空间
//获取二维数组首地址

int main7() {
	//查看二维数组所占内存空间
	int arr[2][3] = { {1,2,3},{4,5,6} };

	cout <<"数组占用内存空间为"<< sizeof(arr) << endl;

	cout << "二维数组第一行占用内存空间" << sizeof(arr[0]) << endl;
	
	cout << "二维数组第一个元素占用内存空间" << sizeof(arr[0][0]) << endl;
	//二维数组行数和列数获取
	cout << "二维数组行数为" << sizeof(arr) / sizeof(arr[0]) << endl;
	
	cout << "二维数组列数为" << sizeof(arr[0]) / sizeof(arr[0][0]) << endl;

	//获取二维数组首地址

	cout << "二维数组首地址为" << arr << endl;

	cout << "二维数组第一行首地址为" << arr[0] << endl;
	 
	cout << "二维数组第二行首地址为" << arr[1] << endl;

	cout << "二维数组第一个元素首地址" << &arr[0][0] << endl;

	cout << "二维数组第二个元素首地址" << &arr[0][1] << endl;
	system("pause");

	return 0;

}