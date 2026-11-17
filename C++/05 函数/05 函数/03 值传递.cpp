#include<iostream>
using namespace std;
/*所谓值传递，就是函数调用时实参将数值传入给形参值传递时，
如果形参发生改变，并不会影响实参*/
void swap1(int num1, int num2) {
	cout << "交换前：" << endl;
	cout << "num1= " << num1 << " ";
	cout << "num2= " << num2<<endl;
	int temp = num1;
	num1 = num2;
	num2 = temp;
	cout << "交换后： "<< endl;
		cout << "num1= " << num1<<" ";
	cout << "num2= " << num2<<endl;
}

int main3() {
	int a = 10, b = 20;
	cout << "a= " << a << endl;
	cout << "b= " << b << endl;
	swap1(a, b);
	cout << "a= " << a << endl;
	cout << "b= " << b << endl;
	return 0;

}