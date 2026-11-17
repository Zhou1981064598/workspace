#include<iostream>
using namespace std;
//可以通过结构体指针访问绊构体属性
struct Student
{
	//成员列表

	//姓名
	string name;

	//年龄
	int age;

	//分数
	int score;
};
int main3() {
	//1、创建学生结构体变量
	struct Student s=
	{
		"张三",
		18,
		100
	};

	//2、通过指针指向结构体变量
	struct Student * p = &s;

	//3、通过指针访问结构体变量中的数据

	cout << "\t姓名： " << p->name
		<< "\t年龄： " << p->age
		<< "\t分数： " << p->score << endl;
	system("pause");

	return 0;
}