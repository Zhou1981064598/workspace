#include<iostream>
using namespace std;

//案例描述:封装一个函数，利用冒泡排序，实现对整型数组的升序排序
void bubbleSort(int *arr,int len) {
	int temp = arr[0];
	for (int i = 0; i < len -1 ; i++)
	{
		for (int j = 0; j < len -1-i; j++)
		{
			if (arr[j]>arr[j+1])
			{
				temp = arr[j+1];
				arr[j + 1] = arr[j];
				arr[j] = temp;
			}
		}
	}
}

int main() {
	int arr [10] = { 5,8,16,80,89,44,50,60,844,446 };
	int length = sizeof(arr) / sizeof(arr[0]);

	cout << "排序前："<<endl;

	for (int i = 0; i < length; i++)
	{
		cout << arr[i] << " ";

	}
	cout << endl;
	bubbleSort(arr, length);

	cout << "排序后：" << endl;

	for (int i = 0; i < length; i++)
	{
		cout << arr[i] << " ";

	}
	system("pause");

	return 0;
}