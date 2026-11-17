#include<iostream>
using namespace std;
/*
给电影进行打分
10~9经典
8~7非常好
6~5一般
5以下烂片
*/
//swich判断条件和if比较是swich不能是区间，
// 只能是整数或者字符，执行效率swich高于if
int main7() {
	//1、提示用户给电影评分
	cout << "请给电影进行打分" << endl;
	//2、用户开始进行打分
	int score = 0;
	cin >> score;
	cout << "您的评分是" << score << endl;
	//3、根据用户输入的分数来提示用户最后的结果
	switch (score)
	{
	case 10:
		cout << "您评分为经典电影" << endl; break; 
	case 9:
		cout << "您评分为经典电影" << endl; break;
	case 8:
		cout << "您评分为非常好的电影" << endl; break;
	case 7:
		cout << "您评分为非常好的电影" << endl; break;
	case 6:
		cout << "您评分为一般的电影" << endl; break;
	case 5:
		cout << "您评分为一般的电影" << endl; break;
	default:
		cout << "您评分为烂片" << endl;
		break;
	}

	system("pause");

	return 0;
}