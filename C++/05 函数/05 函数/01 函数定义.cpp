#include<iostream>
using namespace std;

int add(int num1 ,int num2) {
	int sum = 0;
	return sum = num1 + num2;
}

int main1() {
	int nums[2];
	for (int i = 0; i < 2; i++)
	{
		cin >> nums[i];
	}
	cout<<add(nums[0], nums[1]) << endl;
	system("pause");

	return 0;

}