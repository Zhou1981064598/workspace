# -*- coding: utf-8-*-

import sys

sys.path.append(u"") # 添加Python2.7Lib文件夹路径，不需要可删除
sys.path.append(r"C:\Python27")
sys.path.append(r"C:\Python27\Lib")
sys.path.append(r"C:\Python27\Lib\site-packages")
import struct

T =[
	0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee,
	0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501, 0x698098d8,
	0x8b44f7af, 0xffff5bb1, 0x895cd7be, 0x6b901122, 0xfd987193,
	0xa679438e, 0x49b40821, 0xf61e2562, 0xc040b340, 0x265e5a51,
	0xe9b6c7aa, 0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
	0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed, 0xa9e3e905,
	0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a, 0xfffa3942, 0x8771f681,
	0x6d9d6122, 0xfde5380c, 0xa4beea44, 0x4bdecfa9, 0xf6bb4b60,
	0xbebfbc70, 0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05,
	0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665, 0xf4292244,
	0x432aff97, 0xab9423a7, 0xfc93a039, 0x655b59c3, 0x8f0ccc92,
	0xffeff47d, 0x85845dd1, 0x6fa87e4f, 0xfe2ce6e0, 0xa3014314,
	0x4e0811a1, 0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391 
]
    
def F(X, Y, Z):
	return (X & Y) | ((~X) & Z)
	
def G(X, Y, Z):
	return (X & Z) | (Y & (~Z))
	
def H(X, Y, Z):
	return X ^ Y ^ Z

def I(X, Y, Z):
	return Y ^ (X | (~Z))

        
def FF(a, b, c, d, Mj, s, ti):
	fVal = F(b, c, d) & 0x0ffffffff
	sum1 = (a+fVal+Mj+ti) & 0x0ffffffff
	rotateLeftVal = RotateLeft(sum1,s)
	sum2 = (b + rotateLeftVal) & 0x0ffffffff
	return sum2
	
def GG(a, b, c, d, Mj, s, ti):
	gVal = G(b, c, d) & 0x0ffffffff
	sum1 = (a+gVal+Mj+ti) & 0x0ffffffff
	rotateLeftVal = RotateLeft(sum1,s)
	sum2 = (b + rotateLeftVal) & 0x0ffffffff
	return sum2
	
def HH(a, b, c, d, Mj, s, ti):
	hVal = H(b, c, d) & 0x0ffffffff
	sum1 = (a+hVal+Mj+ti) & 0x0ffffffff
	rotateLeftVal = RotateLeft(sum1,s)
	sum2 = (b + rotateLeftVal) & 0x0ffffffff
	return sum2
	
def II(a, b, c, d, Mj, s, ti):
	iVal = I(b, c, d) & 0x0ffffffff
	sum1 = (a+iVal+Mj+ti) & 0x0ffffffff
	rotateLeftVal = RotateLeft(sum1,s)
	sum2 = (b + rotateLeftVal) & 0x0ffffffff
	return sum2

def RotateLeft(x, n):
	return (((x << n) & 0x0ffffffff) | (x >> (32 - n))) & 0x0ffffffff

# AESSK密钥计算
# input - 车辆VIN码
# 16个AESSK字节
def Convert(input):
	SNLen = 64
	AesList = list()
	byteList = list()
	for c in input:
		byteList.append(ord(c))
	
	len1 = len(byteList)
	if len1 < SNLen:
		for i in range(SNLen - len1-8):
			if i == 0:
				byteList.append(0x80)
			else:
				byteList.append(0x00)
	
	dataLenBit = len1*8
	array=struct.pack("i",dataLenBit)
	
	for i in range(4):
		byteList.append(ord(array[i]))
	for i in range(4):
		byteList.append(0x00)
	
	listM = list()
	M = [0x0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	for i in range(16):
		M[i] = (byteList[i * 4 + 3] << 24) | (byteList[i * 4 + 2] << 16) | (byteList[i * 4 + 1] << 8) | byteList[i * 4 + 0]

	A = 0x67452301
	B = 0xEFCDAB89
	C = 0x98BADCFE
	D = 0x10325476
	a = A
	b = B
	c = C
	d = D

	# 第一轮
	for i in range(16):
		m = i % 4
		if m == 0:
			a = FF(a, b, c, d, M[i], 7, T[i])			
		elif m == 1:
			d = FF(d, a, b, c, M[i], 12, T[i])			
		elif m == 2:			
			c = FF(c, d, a, b, M[i], 17, T[i])
		else:
			b = FF(b, c, d, a, M[i], 22, T[i])			
		
	# 第二轮
	mIdx = 1
	tIdx = 16
	for i in range(4):
		a = GG(a, b, c, d, M[mIdx], 5, T[tIdx])
		mIdx = (mIdx + 5) % 16
		tIdx = tIdx + 1
		d = GG(d, a, b, c, M[mIdx], 9, T[tIdx])
		mIdx = (mIdx + 5) % 16
		tIdx = tIdx + 1
		c = GG(c, d, a, b, M[mIdx], 14, T[tIdx])
		mIdx = (mIdx + 5) % 16
		tIdx = tIdx + 1
		b = GG(b, c, d, a, M[mIdx], 20, T[tIdx])
		mIdx = (mIdx + 5) % 16
		tIdx = tIdx + 1

	# 第三轮
	mIdx = 5
	tIdx = 32
	for i in range(4):
		a = HH(a, b, c, d, M[mIdx], 4, T[tIdx])
		mIdx = (mIdx + 3) % 16
		tIdx = tIdx + 1
		d = HH(d, a, b, c, M[mIdx], 11, T[tIdx])
		mIdx = (mIdx + 3) % 16
		tIdx = tIdx + 1
		c = HH(c, d, a, b, M[mIdx], 16, T[tIdx])
		mIdx = (mIdx + 3) % 16
		tIdx = tIdx + 1
		b = HH(b, c, d, a, M[mIdx], 23, T[tIdx])
		mIdx = (mIdx + 3) % 16
		tIdx = tIdx + 1

	# 第四轮
	mIdx = 0
	tIdx = 48
	for i in range(4):
		a = II(a, b, c, d, M[mIdx], 6, T[tIdx])
		mIdx = (mIdx + 7) % 16
		tIdx = tIdx + 1
		d = II(d, a, b, c, M[mIdx], 10, T[tIdx])
		mIdx = (mIdx + 7) % 16
		tIdx = tIdx + 1
		c = II(c, d, a, b, M[mIdx], 15, T[tIdx])
		mIdx = (mIdx + 7) % 16
		tIdx = tIdx + 1
		b = II(b, c, d, a, M[mIdx], 21, T[tIdx])
		mIdx = (mIdx + 7) % 16
		tIdx = tIdx + 1

	a+=A
	b+=B
	c+=C
	d+=D
	SK0 = (d >> 24) & 0xff
	SK1 = d & 0xff
	SK2 = (c >> 16) & 0xff
	SK3 = (b >> 8) & 0xff
	SK4 = (a >> 24) & 0xff
	SK5 = (a >> 4) & 0xff


	MaskValue = 0xAA
	AesList.append(int(SK0))
	AesList.append(int(((SK1 * SK4) ^ MaskValue) & 0xff))
	AesList.append(int((SK1 * SK2) & 0xff))
	AesList.append(int(SK3 ^ MaskValue))
	AesList.append(int((SK1 * SK4) & 0xff))
	AesList.append(int(SK5))
	AesList.append(int(SK5 ^ MaskValue))
	AesList.append(int((SK0 * SK4) & 0xff))
	AesList.append(int(SK3))
	AesList.append(int((SK2 * SK5) & 0xff))
	AesList.append(int(SK1 ^ MaskValue))
	AesList.append(int(((SK0 * SK4) ^ MaskValue) & 0xff))
	AesList.append(int(SK1))
	AesList.append(int(SK2))
	AesList.append(int((SK1 * SK3) & 0xff))
	AesList.append(int(SK4))

	return AesList