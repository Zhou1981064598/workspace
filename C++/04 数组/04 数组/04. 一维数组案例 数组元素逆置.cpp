#include<iostream>
using namespace std;
//案例描述:请声明一个5个元素的数组，
//并且将元素逆置.(如原数组元素为 : 1, 3, 2, 5.4; 
//逆置后输出结果为:4, 5.2.3., 1);

int main4() {

	int arr[11];
	int temp = 0;
	cout << "请输入数组十一个元素" << endl;
	for (int i = 0; i < 11; i++)
	{
		cin >> arr[i];
	}
	cout << "数组逆置前" << endl;
	int start = 0;
	int end = sizeof(arr) / sizeof(arr[0])-1;
	for (int j = 0; j < 11; j++)
	{
		cout  << arr[j] << " ";
	}

	while (start<end) {
		temp = arr[start];
		arr[start] = arr[end];
		arr[end] = temp;
		start++;
		end--;
	}
	cout << endl << "数组逆置后" << endl;

	for (int k = 0;k< 11; k++)
	{
		cout  << arr[k] << " ";
	}	
	system("pause");
	return 0;
}