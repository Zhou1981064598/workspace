#include<iostream>
using namespace std;
//const的使用场景
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
//将函数中的形参改为指针，可以减少内存空间，而且不会复制新的副本出来。
void printStuInf(const struct student *s) {
	//s->age=150;加入const后，一旦有修改的操作就好报错，可以防止我们误操作
	cout << "子函数中 姓名: " << s->name << " "
		<< "年龄：" << s->age << " "
		<< "分数：" << s->score
		<< endl;
}
int main6() {

	student s = { "张三",15,70 };

	printStuInf(&s);

	cout << "张三的年龄是:" << s.age<<endl;

	system("pause");
	
	return 0;
}