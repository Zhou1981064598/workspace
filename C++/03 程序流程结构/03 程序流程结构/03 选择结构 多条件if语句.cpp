#include<iostream>
using namespace std;

int main3() {
	//1、用户输入分数
	int score = 0;
	cout << "请输入一个分数" << endl;
	cin >> score;
	//2、打印用户输入分数
	cout << "您输入的分数是" << score << endl;
	/*
	3、判断分数是否大于600，如果大于，那么输出，如果大于500小于600，
	 则打印考上二本大学,如果大于400小于500，则打印考上三本大学，否、
	 则打印未考上本科大学
	 */
	if (score > 600) {
		cout << "恭喜您考上了一本大学" << endl;
	}
	else if(500<score<600)
	{
		cout << "您考上二本大学" << endl;
	}
	else if(400<score<500)
	{
		cout << "您考上三本大学" << endl;
	}
	else
	{
		cout << "您未考上本科大学" << endl;

	}

	system("pause");

	return	0;
}