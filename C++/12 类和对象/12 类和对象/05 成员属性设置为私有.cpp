#include<iostream>
using namespace std;
//成员属性设置为私有

//1、可以控制自己控制读写权限
//2、对于写可以检测数据有效性

class Person1
{


public:
	//设置姓名
	void setName(string name) {
		m_Name = name;
	}

	//获取姓名
	string getName()
	{
		return m_Name;
	}

	//获取年龄
	int getAge() {
		return m_Age;
	}
	void setIdol(string Iodl) {
		m_Idol = Iodl;
	}
	void setAge(int age) {
		if (age > 0 && age <= 150)
		{
			m_Age = age;

		}
		else {
			cout << "年龄输入有误" << endl;
		}
	}

private:

	string m_Name;//姓名 可读可写

	int m_Age = 18;//年龄 只读 也可以写（年龄必须在0~150之间）


	string m_Idol;//偶像 只写



};
int main5() {
	Person1 p;
	//姓名设置

	p.setName("张三");
	//获取姓名

	cout << "姓名：" << p.getName() << endl;


	//年龄设置:无法设置
	//p.setAge(20);
	//p.m_Age = 20;

	//获取年龄
	cout << "年龄：" << p.getAge() << endl;
	//偶像设置
	p.setIdol("小明");
	p.setAge(160);

	system("pause");

	return 0;
}