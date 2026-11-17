#include<iostream>
#define MAX 1000
using namespace std;
//联系人结构体
struct Person
{
	//姓名
	string m_Name;
	//性别 1 男 2 女
	int m_Sex;
	//年龄
	int m_Age;
	//电话
	string m_Phone;
	//住址
	string m_Addr;

};

//通讯录结构体
struct Addressbooks
{
	//通讯录中保持的联系人数组
	struct Person personArray[MAX];

	//通讯录中当前记录联系人个数
	int m_Size;
};
//1、添加联系人
void addPerson(Addressbooks* abs) {
	//判断通讯录是否已满，如果满了就不再添加
	if (abs->m_Size == MAX)
	{
		cout << "通讯录已满，无法添加！" << endl;
		return;
	}
	else
	{
		//添加具体联系人

		//姓名
		string name;
		cout << "请输入姓名：" << endl;
		cin >> name;
		abs->personArray[abs->m_Size].m_Name = name;
		//性别
		int m_Sex = 0;
		cout << "请输入性别：" << endl;
		cout << "1--男 2--女" << endl;
		while (true)
		{
			cin >> m_Sex;
			//如果输入的是1或2可以退出循环否则继续输入
			if (m_Sex == 1 || m_Sex == 2)
			{
				abs->personArray[abs->m_Size].m_Sex = m_Sex;
				break;
			}
			cout << "输入有误，请重新输入" << endl;
			cin.clear();//修复输入流
			cin.ignore();//取走刚才流中的字符
		}

		//年龄
		int m_Age = 0;
		cout << "请输入年龄：" << endl;
		while (true)
		{
			cin >> m_Age;
			//如果输入的是正常年龄可以退出循环否则继续输入
			if (m_Age > 0 && m_Age < 200)
			{
				abs->personArray[abs->m_Size].m_Age = m_Age;
				break;
			}
			cout << "输入有误，请重新输入" << endl;
			cin.clear();//修复输入流
			cin.ignore();//取走刚才流中的字符
		}
		//电话
		string m_Phone = "";
		cout << "请输入联系电话：" << endl;
		while (true)
		{
			cin >> m_Phone;
			//如果输入的是正确电话可以退出循环否则继续输入
			if (m_Phone.length() == 11)
			{
				abs->personArray[abs->m_Size].m_Phone = m_Phone;
				break;
			}
			cout << "输入有误，请输入十一位数字号码" << endl;
			cin.clear();//修复输入流
			cin.ignore();//取走刚才流中的字符
		}
	}
		//住址
		string m_Address;
		cout << "请输入家庭住址：" << endl;
		cin >> m_Address;
		abs->personArray[abs->m_Size].m_Addr = m_Address;
		//更新通讯录人数
		abs->m_Size++;
		cout << "添加成功" << endl;

		system("pause");//请按任意键继续
		system("cls");//清屏
	
}
//2、显示所有联系人
void showPerson(Addressbooks* abs) {
	//判断通讯录中人数是否为0，如果为0，提示记录为空
	//如果不为0，显示所有记录的联系人信息
	if (abs->m_Size == 0) {
		cout << "当前记录为空" << endl;
	}
	else
	{
		for (int i = 0; i < abs->m_Size; i++)
		{
			cout << "姓名： " << abs->personArray[i].m_Name << "\t"
				<< "性别： " << (abs->personArray[i].m_Sex == 1?"男": "女") << "\t"
				<< "年龄： " << abs->personArray[i].m_Age << "\t"
				<< "电话： " << abs->personArray[i].m_Phone << "\t"
				<< "住址： " << abs->personArray[i].m_Addr << endl;

		}
	}
	system("pause");
	system("cls");
}
//检测联系人是否存在，如果存在，返回联系人所在数组中的具体位置,不存在返回-1
int isExist(Addressbooks *abs,string name) {
	for (int i = 0; i < abs->m_Size; i++)
	{
		//找到用户输入的姓名
		if (abs->personArray[i].m_Name==name)
		{
			//如果找到，返回用户名所在下标
			return i;
		}
	
	}
		//找不到返回-1
		return -1;
}
//3、删除联系人
void deletePerson(Addressbooks *abs) {
	cout << "请输入要删除的联系人" << endl;
	string name;
	cin >> name;
	int ret = isExist(abs, name);
	if (ret != -1) {
		//查到此人，进行删除
		//要删除联系人，将后面的数据往前移动一位，覆盖前一位数据，
		// 并让通讯录人员个数-1即可
		for (int i = ret; i < abs->m_Size; i++)
		{
			//数据前移
			abs->personArray[i] = abs->personArray[i + 1];

		}
		//更新通讯录人数
		abs->m_Size--;
		cout << "删除成功" << endl;
	}		
	else
	{
		cout << "查无此人" << endl;
	}
	system("pause");
	system("cls");
}
//4、查找联系人
void findPerson(Addressbooks* abs) {
	cout << "请输入您要查找的联系人" << endl;
	string name;
	cin >> name;

	//判定指定的联系人是否存在通讯录汇总
	int ret = isExist(abs, name);
	isExist(abs, name)==-1?cout<<"未找到此人"<<endl:
		cout << "姓名： " << abs->personArray[ret].m_Name << "\t"
		<< "性别： " << (abs->personArray[ret].m_Sex == 1 ? "男" : "女") << "\t"
		<< "年龄： " << abs->personArray[ret].m_Age << "\t"
		<< "电话： " << abs->personArray[ret].m_Phone << "\t"
		<< "住址： " << abs->personArray[ret].m_Addr << endl;
	system("pause");
	system("cls");
}
//5、修改联系人
void modifyPerson(Addressbooks* abs) {
	cout << "请输入您要修改的联系人" << endl;
	string name;
	cin >> name;
	int ret = isExist(abs, name);
	if (ret != -1)//找到指定联系人并修改信息
	{
		//姓名
		string name;
		cout << "请输入姓名：" << endl;
		cin >> name;
		abs->personArray[ret].m_Name = name;
		//性别

		while (true) {
			cout << "请输入性别：" << endl;
			cout << "1--男 2--女" << endl;
			int sex;
			cin >> sex;
			if (sex == 1 || sex == 2)
			{
				abs->personArray[ret].m_Sex = sex;
				break;
			}
			else
			{
				cout << "请重新输入" << endl;
			}
		}

		//年龄
		int age;
		cout << "请输入姓年龄：" << endl;
		cin >> age;
		abs->personArray[ret].m_Age = age;
		//电话
		string phone;
		cout << "请输入号码：" << endl;
		cin >> phone;
		abs->personArray[ret].m_Phone = phone;
		//住址
		string address;
		cout << "请输入地址：" << endl;
		cin >> address;
		abs->personArray[ret].m_Addr = address;
		cout << "修改成功" << endl;
	}
	else //未找到联系人
	{
		cout << "查无此人" << endl;
}
	system("pause");
	system("cls");
}
//6、清空联系人
void clearPerson(Addressbooks *abs) {
	abs->m_Size = 0;//将当前记录联系人人数置0，做逻辑清空操作
	cout << "通讯录已清空" << endl;

	system("pause");
	system("cls");
}
//显示菜单界面
void showMenu(){
	cout << "* * * * * * * * * * * * * * * * * * " << endl
		<< "* * * * * ""1、添加联系人 ""* * * * * * " << endl
		<< "* * * * * ""2、显示联系人 ""* * * * * * " << endl
		<< "* * * * * ""3、删除联系人 ""* * * * * * " << endl
		<< "* * * * * ""4、查找联系人 ""* * * * * * " << endl
		<< "* * * * * ""5、修改联系人 ""* * * * * * " << endl
		<< "* * * * * ""6、清空联系人 ""* * * * * * " << endl
		<< "* * * * * ""0、退出通讯录 ""* * * * * * " << endl;
}

int main() {
	//创建通讯录结构体变量
	Addressbooks abs;

	//初始化通讯录当前人员个数
	abs.m_Size = 0;

	//用户选择输入的变量
	int select = 0;

	while (true)
	{	//菜单调用
		showMenu();
		cin >> select;
		switch (select)
		{
		case 1://添加联系人
			addPerson(&abs);//利用地址传递可以修改实参

			break;

		case 2://显示联系人
			showPerson(&abs);
			break;

		case 3://删除联系人
		{string deleteName;
	/*	cout << "请输入删除联系人姓名:" << endl;
		cin >> deleteName;
	
		isExist(&abs, seleteName) == -1 ? cout<<"查无此人"<<endl : cout<<"找到此人"<<endl;*/
		deletePerson(&abs);
		break;
		}
		case 4://查找联系人
		findPerson(&abs);
			break;

		case 5://修改联系人
		modifyPerson(&abs);
			break;

		case 6://清空联系人
			clearPerson(&abs);
			break;

		case 0://退出通讯录
			cout << "欢迎下次使用" << endl;

			system("pause");

			return 0;

			break;

		default:
			break;
		}
	}


	
}

