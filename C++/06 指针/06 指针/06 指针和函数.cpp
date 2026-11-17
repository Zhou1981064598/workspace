#include<iostream>
using namespace std;
//地址传递,传递的是地址，会改变实参的值
void swap02(int * p1, int* p2) {
	int temp = *p1;
	*p1 = *p2;
	*p2 = temp;
}
int main6() {
	int a = 10, b = 20;

	swap02(&a, &b);

	cout << "a=" << a <<" " << "b=" << b << endl;
	
	system("pause");

	return 0;
}