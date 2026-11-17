#include<iostream>
using namespace std;
/*水仙花数是指一个3位数它的每个
位上的数字的3次幂之和等于它本身*/
//求出三位数中的所有水仙花数
int main11() {
	int num = 100;
	do
	{
		int a = num / 100;
		int b = (num/10)%10;
		int c = num%10;
		if (a*a*a+b*b*b+c*c*c==num)
		{
			cout << num << endl;
		}
		num++;

	} while (num<1000);
	return 0;
}
