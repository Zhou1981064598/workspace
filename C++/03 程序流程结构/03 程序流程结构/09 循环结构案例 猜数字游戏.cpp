#include<iostream>
using namespace std;

int main9() {
	//1、系统生成随机数
	//添加随机数种子作用利用当前系统时间生成随机数，防止每次随机数都一样
	srand(time(0));
	int num = rand() % 100 + 1;// rand()%100,生成0~99的随机数 rand()%100+1则代表生成1~100的随机数字
	cout << num << endl;

	num = rand() % 100 + 1;
	cout << num << endl;

	//2、玩家进行猜测
	int val = 0; 
	cout << "请进行猜测" << endl;
	while(1){
	cin >> val;
	//3、判断玩家的猜测
	//猜对 退出游戏
	//猜错 提示猜的结果过大或者过小重新返回第2步

	if (val > num) {
		cout << "猜测过大,请重新猜测" << endl;
	}
	else if(val < num) {
		cout << "猜测过小" << endl;
	}
	else {
		cout << "恭喜你猜对了" << endl;
		break;//break在循环中，可以利用该关键字来退出
	}
	}

	system("pause");

	return 0;
}