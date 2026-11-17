#include<iostream>
using namespace std;
//冒泡排序

int main5() {

	int arr[9];
	cout << "请输入数组九个元素" << endl;
	for (int i = 0; i <9; i++)
	{
		cin >> arr[i];
	}
	cout << "数组排序前" << endl;
	for (int j = 0; j < 9; j++)
	{
		cout << arr[j] << " ";
	}

	for (int i = 0; i < 9-1; i++)
	{
		for (int j = 0; j < 9-i-1; j++)
		{
			if (arr[j] > arr[j+1]) {
				int temp = arr[j];
				arr[j] = arr[j+1];
				arr[j + 1] = temp;
			}
		}
		
	}
	cout << endl << "数组排序后" << endl;

	for (int k = 0; k < 9; k++)
	{
		cout << arr[k] << " ";
	}
	system("pause");
	return 0;
}