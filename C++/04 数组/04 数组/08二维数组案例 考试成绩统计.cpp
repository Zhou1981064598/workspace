#include<iostream>
using namespace std;
//考试成域统计:
/*
案例描述:有三名同学(张三，李四，王五)，
请分别输出三名同学的总成绩
*/

int main() {

	int arr[3][3];

	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < 3; j++) {
			cin >> arr[i][j];
		}
	}
	for (int n = 0; n < 3; n++)
	{
		int sum = 0;
		for (int m = 0; m < 3; m++) {
				sum+=arr[n][m];
		}
		switch (n)
		{
		case 0:
			cout << "张三的总成绩是" << sum << endl; break;
		case 1:
			cout << "李四的总成绩是" << sum << endl; break;
		case 2:
			cout << "王五的总成绩是" << sum << endl; break;
		default:
			break;
		}
	}
	system("pause");

	return 0;

}