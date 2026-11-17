#include<iostream>
using namespace std;

/*
访问权限三种：
1.public公共权限	成员类内可以访问,类外也可以
2.protected保护权限	成员类内可以访问,类外不可以，子类也可以访问
3.private私有权限	成员类内可以访问,类外不可以，子类不能访问
*/
class Person
{
public:
	string m_Name;//姓名
	void func() {
		m_Name = "张三";
		m_Car = "拖拉机";
		m_Password = "123456";//私有权限内容，类外访问不到
	}
protected:
	string m_Car;//汽车
private:
	string m_Password;//银行卡密码
};

int main3() {
	//实例化具体对象
	Person p1;
	p1.m_Name = "李四";
	//p1.m_Car = "奔驰";//保护权限内容，类外访问不到
	//pl.m_Password = "123456";//私有权限内容，类外访问不到
	system("pause");

	return 0;
}