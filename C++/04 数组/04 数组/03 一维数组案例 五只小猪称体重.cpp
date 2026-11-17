#include<iostream>
using namespace std;
//在一个数组中记录了五只小猪的体重，
//如:int arr[5] = { 300,350,200,400,250 };
// 找出并打印最重的小猪体重。

int main3() {

	int arr[5];
	int max = arr[0];
	int num = 0;
	cout << "请输入五个小猪体重" << endl;
	for (int i = 0; i < 5; i++)
	{
		cin >> arr[i];
	}
	for (int j = 0; j < 5; j++)
	{
		arr[j] > max ? max = arr[j], num = j : NULL;
	/*	if (arr[j] > max)
		{
			max = arr[j];
			num = j;
		}
	*/
	}
	cout << "体重最重的小猪是第" << num + 1 << "只小猪" << endl;
	system("pause");
	return 0;
}