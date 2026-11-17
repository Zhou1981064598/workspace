
#include<iostream>
using namespace std;
/*
·提示用户输入一个高考考试分数, 根据分数做如下判断
·分数如果大于600分视为考上一本，大于500分考上二本，大于400考上三本，其余视为未考上本科; ·在一本分数中，
如果大于700分，考入北大，大于650分，考入清华，大于600考入人大。
*/
int main4() {
	//1、用户输入分数
	int score = 0;
	cout << "请输入一个分数" << endl;
	cin >> score;
	//2、打印用户输入分数
	cout << "您输入的分数是" << score << endl;

	if (score > 600) {
		if (score>700)
		{
			cout << "恭喜您考上了北京大学" << endl;

		}
		else if(650<score<700) {
			cout << "恭喜您考上了清华大学" << endl;
		}else{
			cout << "恭喜您考上了一本大学" << endl;
		}
	}
	else if (500 < score < 600)
	{
		cout << "您考上二本大学" << endl;
	}
	else if (400 < score < 500)
	{
		cout << "您考上三本大学" << endl;
	}
	else
	{
		cout << "您未考上本科大学,请再接再厉" << endl;

	}

	system("pause");

	return	0;
}