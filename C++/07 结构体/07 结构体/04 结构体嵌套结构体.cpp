#include<iostream>
using namespace std;
struct student
{
	string name;//姓名
	int age;//年龄
	int score;//分数
};
struct teacher
{
	int id;//教师编号
	string name;//教师姓名
	int age;//年龄
	struct student stu;//辅导的学生
};

int main4() {

	teacher t;
	t.id = 1000;
	t.age = 65;
	t.name = "王教授";
	t.stu.age = 19;
	t.stu.name = "李杰";
	t.stu.score = 90;


	cout << "\t老师姓名： " <<t.name<<endl
		<< "\t老师年龄： " << t.age << endl
		<< "\t老师编号： " << t.id << endl
		<< "\t老师辅导学生姓名： "<<t.stu.name << endl
		<< "\t老师辅导学生年龄： "<<t.stu.age << endl
		<< "\t老师辅导学生分数： "<<t.stu.score << endl
		<<
		endl;
	system("pause");

	return 0;
}