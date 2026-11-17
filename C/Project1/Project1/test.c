#define  _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>

static void revert(int  A[], int length) {
	int i, j, temp; // temp为临时变量
	for (i = 0, j = length - 1; i <= j; i++, j--) {
		temp = A[i];
		A[i] = A[j];
		A[j] = temp; // 调换
	}
}
typedef struct node {
	int data;
	struct node* next;
}LNode,*LinkList;  //�ṹ��Ķ���

struct LNode* reverseList(LinkList head) {
	if (head == NULL || head->next == NULL) {
		return head;
	}

	//head = head->next;
	LinkList newHead = reverseList(head->next);
	printf("%d ", head->data);
	return head;
}


LinkList create_Link() {  //�������������Ҫ���루β�巨�����������
	LinkList head = NULL;
	LNode *temp = NULL;
	LNode *tail = NULL;
	head = (LinkList*)malloc(sizeof(LinkList));
	head->next = NULL;
	int x;
	scanf("%d", &x);
	while (x != -1) {  //ѭ��ֹͣ���������԰����Լ�Ҫ�����
		temp = (LNode*)malloc(sizeof(LNode));
		temp->data = x;
		temp->next = NULL;
		if (head->next == NULL) {


			head->next = temp;
		}
		else {
			tail->next = temp;
		}
		tail = temp;
		scanf("%d", &x);
	}
	return head;
}

LinkList create_Link1() {       //������������
	LNode* s;
	int x;
	LinkList head = (LinkList*)malloc(sizeof(LinkList));     //����ͷ���
	head->next = NULL;                          //��ʼΪ������,�ⲽ�����٣�

	scanf("%d", &x);                         //����Ҫ����Ľ���ֵ
	while (x != -1) {                          //����9999�����
		s = (LNode*)malloc(sizeof(LNode));  //�����½��
		s->data = x;
		s->next = head->next;
		head->next = s;                         //���½�������У�LΪͷָ��
		scanf("%d", &x);
	}
	return head;

}
int delete_repeat(int a[],int length) {
	int k = 0;
	for (int i = 0; i < length-1; i++)
	{
		if (a[i+1]=a[i])		{
			k++;
		}
		a[i + 1 - k] = a[i];
	}
	length -= k;
	return a;
}

void print_Link(LinkList L) {  //��ӡ��������
	L = L->next;
	while (L != NULL) {
		printf("%d ", L->data);
		L = L->next;
	}
};
void Merge(int A[], int low, int mid, int high,int B[]) {
	int i, j, k;
	for (k = low, k < high; k++;) {
		B[k] = A[k];
	}
	for ( i= low,j=mid+1,k=i;i<=mid&&j<=high;k++)
	{
		if (B[i] <= B[j])A[k] = B[i++];
		else A[k] = B[j++];
	}
	while (i < mid)
	{
		A[k++] = B[i++];
	}
	while (j < high)
	{
		A[k++] = B[j++];
	}
}

int main() {  //������
	int a[8] = {1,2,3,4,5,6,7,8};
	int B[8] = { 2,7,12,15,18,20,25,30 };
	int C[5];
	int i = 0,j;
	for (; i < 8; ++i)
	{
		if (a[i] > 0)break;
	}
	if (i == 8||a[i]>1){
		printf("%d ", 1); return 0;
	}
	for ( j = i; j < 7; j++)
	{
		if (a[j] != a[j + 1] && a[j + 1] != a[j] + 1) {
			break;
		}
	}
	if (j == 7) {
		if (a[j - 1] != a[i]) { printf("%d ", a[j]+1); }
		else  printf("%d ", a[i]);
	}
	else printf("%d ", a[i] + 1);
	return 0;
}
