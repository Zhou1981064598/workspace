#include<iostream>
using namespace std;

int add1(int num1, int num2) {
	int sum = 0;
	return sum = num1 + num2;
}

int main2() {
	int nums[2];
	for (int i = 0; i < 2; i++)
	{
		cin >> nums[i];
	}
	cout << add1(nums[0], nums[1]) << endl;
	system("pause");

	return 0;

}