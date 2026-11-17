#include <stdio.h>
#include<windows.h>
BOOL Security_WULING_G200B(int iLevel)//安全算法调用层
{
	BOOL	bFlag = TRUE;
	DWORD   uiSEED = 0;
	DWORD   uiMask = 0;
	DWORD   uiKEY = 0;
	CBinary binReciver;

	CBinary bin10("\x10\x03", 2);
	CBinary bin2701("\x27\x01", 2);
	CBinary bin2702("\x27\x02\x00\x00\x00\x00", 6);

	switch (iLevel)
	{
	case 1: //扩展诊断会话 Level
		bin10.SetAt(1, 0x03);
		bin2701.SetAt(1, 0x01);
		bin2702.SetAt(1, 0x02);
		break;
	case 2: //LEVEL_FBL 编程会话
		bin10.SetAt(1, 0x02);//10 02
		bin2701.SetAt(1, 0x09);//27 09 
		bin2702.SetAt(1, 0x0A);//27 0A 00 00 00 00
		break;
	case 3: //Leve51 
		bin10.SetAt(1, 0x03);//10 03
		bin2701.SetAt(1, 0x51);//27 51 
		bin2702.SetAt(1, 0x52);//27 52 00 00 00 00
		break;
	default:
		break;
	}

	StatusBox(TEXT_SECURITY_ING);//去掉“TEXT_”后，可以在TEXT文本中查询到

	CCommEcu::SetTimeout(15000);
	CCommEcu::SetCommTime(5, 1, 10000, 50);
	binReciver = SendReceiveFrame(g_set.default_flag, bin10);
	if ((binReciver.IsEmpty()) || (binReciver[0] == 0x7F))
	{
		MessageBox(TEXT_SECURITY_FAILED);
		return FALSE;
	}

	//延时1s再执行27解锁
	Delay(1000);

	binReciver = SendReceiveFrame(g_set.default_flag, bin2701);

	if (binReciver.IsEmpty())
	{
		MessageBox(TEXT_COMM_FAIL);
		return FALSE;
	}

	if (binReciver[0] == 0x7F)
	{
		NegativeResponse_0x7F(binReciver);
		return FALSE;
	}

	if (binReciver.GetSize() < 6)
	{
		MessageBox(TEXT_SECURITY_FAILED);
		return false;
	}

	uiSEED = (binReciver[2] << 24) + (binReciver[3] << 16) + (binReciver[4] << 8) + binReciver[5];
	if ((uiSEED == 0) || (uiSEED == 0xFFFFFFFF))
	{
		return TRUE;
	}

	if (!Get_SecurityMask(uiMask))
	{
		MessageBox(TEXT_SECURITY_FAILED);//读取掩码失败
		return FALSE;
	}

	if (iLevel == 1)
	{
		uiKEY = SeedToKey_G200B(uiSEED, uiMask);//iLevel
	}
	else if (iLevel == 2)
	{
		//if (!Get_SecurityMask(uiMask))//临时添加测试，暂时屏蔽LLP-2023-1205
		//{
		//	MessageBox(TEXT_SECURITY_FAILED);//读取掩码失败
		//	return FALSE;
		//}

		uiKEY = SeedToKey_FBL_G200B(uiSEED, uiMask);
	}
	else if (iLevel == 3)
	{
		uiKEY = SeedToKey_Leve51_G200B(uiSEED, uiMask);
	}

	bin2702[2] = (BYTE)((uiKEY & 0xFF000000) >> 24);
	bin2702[3] = (BYTE)((uiKEY & 0x00FF0000) >> 16);
	bin2702[4] = (BYTE)((uiKEY & 0x0000FF00) >> 8);
	bin2702[5] = (BYTE)((uiKEY & 0x000000FF));

	binReciver = SendReceiveFrame(g_set.default_flag, bin2702);
	Delay(500);

	if (binReciver.IsEmpty())
	{
		MessageBox(TEXT_COMM_FAIL);
		return FALSE;
	}

	if ((binReciver[0] == 0x7F))
	{
		NegativeResponse_0x7F(binReciver);
		MessageBox(TEXT_SECURITY_FAILED);
		return FALSE;
	}

	return TRUE;
}
DWORD SeedToKey_G200B(UINT dwSeed, UINT dwMask) {
	DWORD dwKey = 0;
	BYTE seed[4] = { 0 };//测试，给数组赋初始值；20231208
	BYTE Arrayxor[4] = { 0 };//测试，给数组赋初始值；20231208
	BYTE Temp[4] = { 0 };
	BYTE val[4] = { 0 };
	BYTE valKey[4] = { 0 };

	seed[0] = (dwSeed >> 24) & 0xff;
	seed[1] = (dwSeed >> 16) & 0xff;
	seed[2] = (dwSeed >> 8) & 0xff;
	seed[3] = (dwSeed >> 0) & 0xff;

	Arrayxor[3] = (dwMask >> 24) & 0xff;
	Arrayxor[2] = (dwMask >> 16) & 0xff;
	Arrayxor[1] = (dwMask >> 8) & 0xff;
	Arrayxor[0] = (dwMask >> 0) & 0xff;

	Temp[0] = seed[0] ^ Arrayxor[1];//临时变量
	Temp[1] = seed[1] + Arrayxor[0];
	Temp[2] = seed[2] ^ Arrayxor[3];
	Temp[3] = seed[3] + (Arrayxor[2] >> 2);

	for (int i = 0; i < 4; i++)
	{
		if (i == 0) {
			Arrayxor[i] = Arrayxor[i] ^ Temp[1];
		}

		else {
			Arrayxor[i] = Arrayxor[i - 1] + (Arrayxor[i] ^ 0x79);
		}
		val[i] = seed[i] ^ Arrayxor[i];
	}

	//val对应buffer变量，seed对应iSeedArray，Arrayxor对应Mask;
	valKey[0] = ((Temp[0] & 0x0F)) | ((val[1] & 0xF0));
	valKey[1] = ((Temp[1] & 0x3C)) | ((val[2] & 0xC3));
	valKey[2] = ((Temp[3] & 0xAC)) | ((val[0] & 0x53));
	valKey[3] = ((Temp[2] & 0x3F)) | (val[3] & 0xFC);

	dwKey = (valKey[0] << 24) + (valKey[1] << 16) + (valKey[2] << 8) + valKey[3];

	return dwKey;
}
int main1()
{
	

	return 0;
}
