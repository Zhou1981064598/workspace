#include<iostream>
using namespace std;
/*
函数常见样式
无参无返、有参无返、无参有返、有参有返。
*/
 void test() {
	cout << "test";
}
 void test1(int a) {
	 cout << "test"<<a<<endl;

 }
 int test2() {
	 return 2;
 }
 int test3(int a) {
	 return a;
 }
int main4() {
	int a = 10, b = 20;
	cout << "a= " << a << endl;
	cout << "b= " << b << endl;
	swap(a, b);
	cout << "a= " << a << endl;
	cout << "b= " << b << endl;
	return 0;

}