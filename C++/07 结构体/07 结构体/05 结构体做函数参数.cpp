#include<iostream>
using namespace std;
struct student
{
	//成员列表

	//姓名
	string name;

	//年龄
	int age;

	//分数
	int score;
};
//打印学生信息函数
//值传递
void printStuInf1(struct student s) {
	s.age = 100;
	cout << "子函数中 姓名: " << s.name
		<< "\t年龄： " << s.age<<" "
		<< "\t分数： " << s.score
		<< endl;
}
//地址传递
void printStuInf2(struct student *p) {
	p->age = 100;
	cout << "子函数中 姓名: " << p->name
		<< "年龄： " << p->age
		<< "分数： " << p->score
		<< endl;
}
int main5() {
	//结构体做函数参数
	//将学生传入到一个参数中，打印学生身上的所有信息
	
	//创建结构体变量
	struct student s1
	{
		"张三",
		18,
		90
	};

	//printStuInf1(s1);
	printStuInf2(&s1);
	cout << "姓名: " << s1.name
		<< "\t年龄： " << s1.age
		<< "\t分数： " << s1.score
		<< endl;

	system("pause");

	return 0;
}