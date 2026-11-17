/*
跳转语句有break、continue、goto;
break和continue的区别是break会退出循环，而continue不会。
continue可以筛选条件，代码执行到这里就不会往下执行了，而
是执行下一次循环。goto语句则是无条件跳转标记位置，不推荐使用。
*/
#include<iostream>
using namespace std;

int main() {
	//continue语句
	/*for (int i = 0; i <=100; i++)
	{
		if (i%2==0)
		{
			continue;
		}
		cout << i << endl;
	}*/
	//goto语句
	cout << "1、XXXXX" << endl;
	cout << "2、XXXXX" << endl;
	goto FLAG;
	cout << "3、XXXXX" << endl;
	FLAG:;
	cout << "4、XXXXX" << endl;
	cout << "5、XXXXX" << endl;

	return 0;

	system("pause");
}
