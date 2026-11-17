#include<iostream>
using namespace std;

int main14() {
	int i = 1;
	
	for (i; i < 10; i++)
	{
		for (int j = 1; j <=i; j++)
		{
			cout << j << "*" << i << "="<<i*j<<" \t";
		}
		cout << endl;
	}
	return 0;

	system("pause");
}
