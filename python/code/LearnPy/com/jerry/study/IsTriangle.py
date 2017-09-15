'''
Created on 2017年8月28日

@author: XX
'''
from math import sqrt
def isTriangle(x1,y1,x2,y2,x3,y3):
    isTri = (x1-x2)*(y3-y2)+(x3-x2)*(y2-y1) != 0
    return isTri

def getLength(x1,y1,x2,y2):
    return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))

def getPerimeter():
    param = [];
    for i in range(1,4):
        coordinate = input("please input the {}st point`s coordinate:".format(i))
        x,y = eval(coordinate)
        param.append(x)
        param.append(y)
    isTri = isTriangle(param[0],param[1],param[2],param[3],param[4],param[5])
    if not isTri:
        print("It`s not a triangle")
        return
    return getLength(param[0],param[1],param[2],param[3]) + getLength(param[0],param[1],param[4],param[5]) + getLength(param[4],param[5],param[2],param[3])

print("perimeter is {}".format(getPerimeter()))