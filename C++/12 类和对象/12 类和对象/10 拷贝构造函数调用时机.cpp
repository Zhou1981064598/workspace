#include<iostream>
using namespace std;
//C++中拷贝构造函数调用时机通常有三种情况
//·使用一个已经创建完毕的对象来初始化一个新对象
//·值传递的方式给函数参数传值
//·以值方式返回局部对象
class Table
{
public:
	Table();
	Table(int age);//有参
	Table(const Table &p);//拷贝构造
	~Table();//析构
	int age;

private:

};

Table::Table()
{
	cout << "Table的默认构造函数调用" << endl;
}
Table::Table(int a)
{
	age = a;
	cout << "Table的有参构造函数调用" << endl;
}
Table::Table( const Table &p)
{
	age = p.age;
	cout << "Table的拷贝构造函数调用" << endl;
}
Table::~Table()
{
	cout << "Table析构函数调用" << endl;
}
void test04() {
	Table t1(20);
	Table t2(t1);
	cout << "t2的年龄是" << t2.age<< endl;
}
void doWork(Table t) {

}
void test05() {
	Table t;
	doWork(t);//值传递给函数传值的时候调用了拷贝构造函数
}
Table doWork2() {
	Table t1;
	cout << &t1<<endl;
	return t1;
}
void test06() {
	Table t= doWork2();
	cout << &t << endl;
}
int main10() {
	//test04();
	//test05();
	//test06();
	system("pause");

	return 0;
}