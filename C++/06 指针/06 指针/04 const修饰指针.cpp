#include<iostream>
using namespace std;
/*
const修饰指针有三种情况:
1.const修饰指针 -- 常量指针
2.const修饰常量 -- 指针常量
3.const即修饰指针，又修饰常题
*/


int main4() {
	int a = 0;
	 //常量指针
	//特点: 指针的指向可以修改, 但是指针指向的值不可以改
	const int* p1 = &a;
	 //*p = 20;//错误：指针指向的值不可以改
	 p1 = &a;//正确：指针的指向可以修改

	//指针常量
	//特点: 指针的指向不可以修改, 但是指针指向的值可以改
	int* const p2 = &a;
	*p2 = 20;//正确：指针指向的值可以修改
	//p = &a;//错误：指针的指向不可以改
	
	//const即修饰指针，又修饰常题
	//特点: 指针的指向和指针指向的值都不可以改
	const int* const p3 = &a;

	system("pause");

	return 0;
}