#include <stdio.h>
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

int main() {
    printf("lf", profit(50));
    return 0;
}
