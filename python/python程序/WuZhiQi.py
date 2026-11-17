import pickle

maxx=10 #最大列数
maxy=10 #最大行数

Checkboard=[[0,0,0,0,0,0,0,0,0,0],
       [0,0,0,0,0,0,0,0,0,0],
       [0,0,0,0,0,0,0,0,0,0],
       [0,0,0,0,0,0,0,0,0,0],
       [0,0,0,0,0,0,0,0,0,0],
       [0,0,0,0,0,0,0,0,0,0],
       [0,0,0,0,0,0,0,0,0,0],
       [0,0,0,0,0,0,0,0,0,0],
       [0,0,0,0,0,0,0,0,0,0],
       [0,0,0,0,0,0,0,0,0,0],]

class Save_load():
    def __init__(self, maxx,maxy):
        self.maxx, self.maxy = maxx,maxy
        #self.Checkboard=[[0]*maxy for i in range(maxx)]
        self.who = False;
    def save(self,Checkboard,who):
        fpath=input("请输入保存路径！")
        file=open(fpath,"wb")
        self.Checkboard=Checkboard
        self.who =who
        pickle.dump(self,file)
        file.close()
    def load(self):
        fpath = input("请输入棋盘路径！")
        import os
        if os.access(fpath, os.F_OK):
            file = open(fpath,"rb+")
            status=pickle.load(file)
            file.close()
            return status
        else:
            print("文件不存在！")

#显示棋盘
def PrintCheckboard(Checkboard):
    print("         五子棋      ")
    print("       O 一 二 三 四 五 六 七 八 九      ")
    #print(len(Checkboard),len(Checkboard[0]))

    for i in range(maxx):
        print(i, end="")
        for j in range(maxy):
            if Checkboard[i][j] == 0:
                print("  空  ",end="")
            if Checkboard[i][j] == 1:
                print("  黑  ",end="")
            if Checkboard[i][j] == 2:
                print("  白  ",end="")
        print("\n")
#显示棋盘

#记录棋局状态
def Record(Checkboard):
    End_chess=False
    who = True
    while not End_chess:
        t=input("请下子（x,y）,现在由"+"黑方下子" if who else "请下子（x,y）,现在由"+"白方下子" ).split(",")
        if len(t)==2:
            x = int(t[0])
            y = int(t[1])
            if Checkboard[x][y]==0:
                Checkboard[x][y]=1 if who else 2
                #判读棋局
                Result=isWin(Checkboard, x, y)
                # 判读棋局
                if Result:
                    print("黑方赢！" if who else "白方赢！")
                    End_chess= True
                else:
                    who=not who
                    End_chess = False
                    PrintCheckboard(Checkboard)
            else:
                print("当前位置已有棋子，不允许落子！")
        elif len(t)==1:
            if t[0]=="S":
                status=End_play.save(Checkboard,who)
            if t[0] == "L":
                status=End_play.load()
                #maxx = status.maxx
                #maxy = status.maxy
                Checkboard= status.Checkboard
                who=status.who
                PrintCheckboard(Checkboard)
        else:
            print("输入位置有误，请重新输入！")
#记录棋局状态

#棋局判断胜负
def inRang(Checkboard,xPoint,yPoint):
    return xPoint<maxx and xPoint>=0 and yPoint<maxy and yPoint>=0
def checkFiveRow(Checkboard,xPoint,yPoint,xDir,yDir):
    count=0
    t=Checkboard[xPoint][yPoint]
    x,y=xPoint,yPoint
    while inRang(Checkboard,x,y) and t==Checkboard[x][y]:
        count+=1
        x += xDir
        y += yDir
    x, y = xPoint, yPoint
    while inRang(Checkboard,x,y) and t == Checkboard[x][y]:
        count += 1
        x -= xDir
        y -= yDir
    if count>5:
        return True
    else:
        return False
def isWin(Checkboard,xPoint,yPoint):
    Result1=checkFiveRow(Checkboard, xPoint, yPoint,1, 0)
    Result2=checkFiveRow(Checkboard, xPoint, yPoint, 0, 1)
    Result3=checkFiveRow(Checkboard, xPoint, yPoint, 1, 1)
    Result4=checkFiveRow(Checkboard, xPoint, yPoint, -1, -1)

    if Result1 or Result2 or Result3 or Result4:
        return True
#棋局判断胜负

#主程序
if __name__=='__main__':
    End_play=Save_load(maxx,maxy)
    Record(Checkboard)
#主程序