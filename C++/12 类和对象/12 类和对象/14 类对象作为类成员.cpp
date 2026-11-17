#include<iostream>
using namespace std;
//类对象作为类成员

class Phone
{
public:
	Phone(string pName) {
		m_PName = pName;
		cout << "Phones的构造函数被调用"<<endl;

	};
	~Phone() {
		cout << "Phones的析构函数被调用" << endl;

	}
	string m_PName;//手机品牌名称

};
class A
{
public:
	A(string name, string pname) :m_Name(name), m_Phone(pname) {
		cout << "A的构造函数被调用" << endl; 
	}
	//姓名
	string m_Name;

	//手机
	Phone m_Phone;
	//当其他类对象作为本类成员，构造时候先构造类对象，再构造自身，
	//析构的顺序和构造顺序相反
	~A() {
		cout << "A的析构函数被调用" << endl;
	}
};
void test_1() {
	A a("张三","苹果MAX");
	cout << a.m_Name << "拿着" << a.m_Phone.m_PName << endl;
}
int main14() {
	test_1();
	system("pause");
	return 0;
}