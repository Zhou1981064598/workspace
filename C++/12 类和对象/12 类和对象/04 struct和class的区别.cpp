#include<iostream>
using namespace std;
/*
在C++中struct和class唯一的区别
就在于默认的访问权限不同
区别:
struct默认权限为公共 
class默认权限为私有
*/
class  C1
{
	int m_A;//默认权限是私有
};
struct C2
{
	int m_A;//默认权限公共
};

int main4() {
	//struct
	//struct 默认权限是	公共 public
	//class 默认权限是	私有 private
	//C1 c1;

	//c1.m_A = 100;//报错，私有不可访问

	C2 c2;

	c2.m_A = 100;//正确，公共可以访问

	system("pause");

	return 0;
}