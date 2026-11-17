#include<iostream>
using namespace std;

//初始化列表
class Person_
{
public:
	//Person_(int a,int b,int c);
	~Person_();
	int m_A;
	int m_B;
	int m_C;
	//初始化列表赋初值
Person_(int a,int b,int c) :m_A(a), m_B(b), m_C(c) {

	}
private:

};

//Person_::Person_(int a, int b, int c)
//{
//	m_A = a;
//	m_B = b;
//	m_C = c;
//}

Person_::~Person_()
{
}
void test_01(){
	//Person_ p(1, 2, 3);
	Person_ p(30,20,10);
	cout << "m_A=" << p.m_A << endl;
	cout << "m_B=" << p.m_B << endl;
	cout << "m_C=" << p.m_C << endl;
}
int main13() {
	test_01();
	system("pause");
	return 0;
}