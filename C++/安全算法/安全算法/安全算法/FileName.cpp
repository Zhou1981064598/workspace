#include<iostream>
using namespace std;

int main3() {

	char a;
	a = 0xA2;
	cout << (a  & 0xff) << endl;
	cout << a << endl;
	a = '1';
	cout << a << endl;
	a = 75;
	cout << a << endl;

	system("pause");


	return 0;
}