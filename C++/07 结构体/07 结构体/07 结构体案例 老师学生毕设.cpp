#include<iostream>
using namespace std;
/*
学校正在做毕设项目，每名老师带领5个学生，总共有3名老师，需求如下
设计学生和老师的结构体。其中在老师的结构体中，有老师姓名和一个存
放5名学生的数组作为成员学生的成员有姓名、考试分数，创建数组存放3
名老师，通过函数给每个老师及所带的学生赋值最终打印出老师数据以及
老师所带的学生数据。

*/

struct Student
{

	//学生姓名
	string sName;

	//分数
	int score;


};
struct Teacher
{
	//老师姓名
	string tName;

	//学生数组
	struct Student sArray[5];


};
void allocateSpace(struct Teacher teaArray[], int len) {
	string nameSeed = "ABCED";

	for (int i = 0; i < len; i++)
	{
		//给老师进行赋值
		teaArray[i].tName = "Teacher_";

		teaArray[i].tName += nameSeed[i];

		for (int j = 0; j < 5; j++)
		{
			//给3名老师带的学生赋值
			teaArray[i].sArray[j].sName = "Student_";

			teaArray[i].sArray[j].sName += nameSeed[j];
			int random = rand() % 61+40;
			teaArray[i].sArray[j].score = random;

		}
	}

}
void printInf(struct Teacher teaArr[],int len) {


	for (int i = 0; i < len; i++)
	{
		cout << "老师姓名：" << teaArr[i].tName << endl;
		
		for (int j = 0; j < 5; j++)
		{
			cout << "\t学生姓名：" << teaArr[i].sArray[j].sName <<
				" 考试分数"<<teaArr[i].sArray[j].score<<endl;

		}
	}
	
}
int main7() {
	//随机数种子
	srand(time(NULL));
	//创建三名老师数组
	struct Teacher tArray [3];

	int len = sizeof(tArray) / sizeof(tArray[0]);
	
	//通过函数给三名老师赋值
	allocateSpace(tArray,len);

	printInf(tArray,len);

	system("pause");

	return 0;
}