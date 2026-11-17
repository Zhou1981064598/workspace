#include<iostream>
using namespace std;

int main1() {
/*	
	数组定义方式：
	1.数据类型 数组名 [数组长度];
	2.数据类型 数组名 [数组长度]={值1，值2 ...} ;
	3.数据类型 数组名 [] ={值1，值2 ...] ;
*/
	//方式一
	int arr [5];
	arr[0] = 10;
	arr[1] = 20;
	arr[2] = 30;
	arr[3] = 40;
	arr[4] = 50;

	//方式二,如果没有填写完元素，会按照0来填充数组剩余元素
	int arr2[5] = { 10,20,30,40,50 };
	
	//方式三，
	int arr3[] = { 10,20,30,40,50,60};
	//访问数组元素
	for (int i = 0; i < 5; i++)
	{
		cout << arr3[i] << endl; 

	}
	system("pause");
	return 0;
}