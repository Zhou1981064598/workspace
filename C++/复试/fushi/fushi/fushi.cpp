#include<vector>
#include<iostream>
#include<algorithm>
using namespace std;
int gcd(int a,int b){
	if(b==0)return a;
	else return(b,a%b);
}
int main(){
	int n;
	cin>>n;
	if(n=0)cout<<1;

	return 0;
	system("pause");
	//cout<<num[0]<<" "<<num[n-1]<<" "<<gcd(num[0],num[n-1]);


}