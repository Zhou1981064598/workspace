#include <stdio.h>
#include <math.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
double profit(double distance){
    double ans=0;
    if(distance<0)return ans;
    if(1<=distance||distance<=100) {ans=(50+distance*0.5);}
    else if(100<distance||distance<=300){
        ans=100+(distance-100)*0.8;
    } else if(300<distance||distance<=500){ans=80+(distance-100)*0.8;}
    else {ans=(400+distance-500)*1.2;}
    return ans;


}
bool isPrimes(int x){
    for (int i = 2; i*i <=x;i++) {
        if(x%i==0){
            return false;
        }
    }
    return true;
}
int countPrimes(int n) {
    int num=0;
    for (int i = 2; i <n;i++) {
        if(isPrimes(i))
        {num++;}
    }
    return num;
}

int maxsum() {
//    int n;
//    printf("请输入 n: ");
//    scanf("%d", &n);
//    int arr[n];
//    for (int i = 0; i < n; ++i) {
//        scanf("%d", &arr[i]);
//    }
//
//    int max_sum = arr[0]; // 初始化为第一个元素
//    for (int i = 0; i < n; ++i) {       // 起始点i
//        int current_sum = 0;
//        for (int j = i; j < n; ++j) {   // 结束点j
//            current_sum += arr[j];
//            if (current_sum > max_sum) {
//                max_sum = current_sum;
//            }
//        }
//    printf("最大的数组：%d\n", max_sum);
//    return 0;
//}
/*动态规划做法*/
    int n;
    scanf("%d",&n);
    int * arr = (int*)malloc(n * sizeof(int));
    for (int i = 0; i < n; ++i) {
        scanf("%d",&arr[i]);
    }
    if(n==0){return 0;}
    int cur_max=arr[0];
    int glo_max=arr[0];
    for (int i = 0; i < n; ++i) {
        cur_max=(arr[i]>cur_max+arr[i])?arr[i]:cur_max+arr[i];
        if(cur_max>glo_max){glo_max=cur_max;}
    }
    free(arr);
    printf("最大的数组：%d\n", glo_max);
    return glo_max;
}


void helloXX(){
    char name[100]; // 定义一个足够大的字符数组来存储输入的名字

    while (1) { // 持续循环直到输入有效名字
        fgets(name, sizeof(name), stdin); // 读取用户输入
        size_t len = strlen(name);
        // 去除末尾的换行符
        if (len > 0 && name[len - 1] == '\n') {
            name[len - 1] = '\0';
            len--;
        }
        if (len == 0) { // 检查输入是否为空
            printf("输入不能为空，请重新输入名字。\n");
        } else {
            printf("Hello，%s\n", name); // 输出问候语
            break; // 输入有效，退出循环
        }
    }
}
int  samnum(){
    int num;
    int size=5;
    int s [20]={0};
    for (int i = 0; i < size; ++i) {
        scanf("%d",&num);
        s[num]++;
    }
    int max=0;
    for (int i = 0; i < 20; ++i) {
        if(s[i]>max){
            max=s[i];
        }
    }
    printf("出现次数最多的数字为%d\n",max);
    return max;
}
 int thief(){
     int n;
     scanf("%d",&n);
     int * arr= (int*)malloc(sizeof (int )*n);
     int dp[n];
     for (int i = 0; i < n; ++i) {
         scanf("%d",&arr[i]);
     }
     dp[0] = arr[0];
     dp[1] = arr[0] > arr[1] ? arr[0] : arr[1];
     for (int i = 2; i < n; ++i) {
         dp[i] = (arr[i]+dp[i-2]>dp[i-1])?arr[i]+dp[i-2]:dp[i-1];
     }
     free(arr);
     int res=dp[n-1];
     printf("%d",res);
     return 0;
}


//那动态规划的思路应该是怎样的呢？
//假设我们有一个数组dp，其中dp[i]表示偷到第i个房子时的最大金额。
//那么对于第i个房子，有两种选择：偷或者不偷。
//如果偷的话，那么第i-1个房子就不能偷，所以此时的最大金额是dp[i-2]加上当前房子的钱。
//如果不偷的话，最大金额就是dp[i-1]。因此状态转移方程应该是dp[i] = max(dp[i-1], dp[i-2] + nums[i])。
int main() {
    //printf("%lf", profit(300));题目一
//    printf("%d", countPrimes(10));题目二
   // maxsum();/*题目三*/
//    helloXX();题目四
//    samnum();
    thief();
    return 0;

}
