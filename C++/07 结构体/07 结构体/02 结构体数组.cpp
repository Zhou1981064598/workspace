#include<iostream>
using namespace std;
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
int main2() {
	//创建结构体数组
	struct Student stuArray[3] = {
		{"张三",18,100},
		{"李四",19,90},
		{"王五",20,95}
	
	};
	//给结构体数组中的元素赋值
	stuArray[2].name = "赵六";
	system("pause");

	//遍历结构体数组
	for (int i = 0; i < 3; i++)
	{
		cout << "\t姓名： " << stuArray[i].name
			<< "\t年龄： " << stuArray[i].age
			<< "\t分数： " << stuArray[i].score << endl;
	}
	return 0;
}