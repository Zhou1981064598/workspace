#include<iostream>
using namespace std;
//深浅拷贝是面试经典问题，也是常见的一个坑
//浅拷贝 : 简单的赋值拷贝操作
//深拷贝 : 在堆区重新申请空间，进行拷贝操作
class Person0
{
public:
	Person0();//默认
	Person0(int age,int height);//有参
	Person0(const Person0 &p);//拷贝
	~Person0();
	int m_Age;//年龄
	int * m_Height;//身高
private:

};

Person0::Person0()
{
	cout << "Person默认构造函数调用" << endl;
}
Person0::Person0(int age, int height)
{
	m_Age = age;
	m_Height=new int(height);
	cout << "Person有参构造函数调用" << endl;

}
Person0::Person0(const Person0& p) {
	cout << "拷贝构造函数的调用" << endl;
	m_Age = p.m_Age;
	m_Height=new int(*p.m_Height);
	//m_Height = p.m_Height;编译器默认实现代码就是这行
	
}
void test0() {
	Person0 p1(18,160);
	cout << "p1的年龄" << p1.m_Age<<endl<<"p1身高为："<<*p1.m_Height<<endl;
	Person0 p2(p1);//浅拷贝带来的问题：堆区的内存被重复释放
	cout << "p2的年龄" << p2.m_Age <<endl<< "p2身高为："<<*p2.m_Height<<endl;
	
}
Person0::~Person0()
{
	//析构代码，将堆区开辟的数据做释放操作
	if (m_Height!=NULL)
	{
		delete m_Height;
		m_Height = NULL;
	}
	cout << "Person析构构造函数调用" << endl;

}
//总结:如果属性有在堆区开辟时，一定要自己提供拷贝构造函数，防止浅拷贝带来的问题

int main12() {
	test0();
	system("pause");
	return 0;
}