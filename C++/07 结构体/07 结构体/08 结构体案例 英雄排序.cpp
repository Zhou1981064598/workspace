#include<iostream>
using namespace std;
struct Hero
{
	//英雄名字
	string name;

	//英雄年龄
	int age;

	//英雄性别
	string gender;
};
void inputHeroInf(Hero arr[],int len) {
	for (int i = 0; i < len; i++)
	{
		cout << "请输入英雄信息" << endl;
		cin >> arr[i].name;
		cin >> arr[i].age;
		cin >> arr[i].gender;
	}
}void bubbleSort(Hero arr[], int len) {
	for (int i = 0; i < len-1; i++)
	{
		for (int j = 0; j < len - i - 1; j++) {
			if (arr[j].age>arr[j+1].age)
			{
				Hero temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
	}
}
void printHeroInf(Hero arr[], int len) {
	for (int i = 0; i < len; i++)
	{
		cout << "英雄姓名：" << arr[i].name <<
			" 年龄：" << arr[i].age <<
			" 性别：" << arr[i].gender << endl;
	}
}
int main() {

	Hero arr[5];

	int len = sizeof(arr) / sizeof(arr[0]);

	inputHeroInf(arr, len);

	bubbleSort(arr, len);

	printHeroInf(arr, len);

	system("pause");

	return 0;
}