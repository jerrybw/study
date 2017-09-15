'''
Created on 2017年8月31日

@author: XX
'''
import turtle
def draw(pen,fd,lr,cc,color):
    pen.pencolor(color)
    pen.fd(fd)
    if lr == 0:
        pen.left(cc)
    else:
        pen.right(cc)
def openFile(fileName):
    file = open(fileName,"r")
    turtle.title('数据驱动的动态路径绘制')
    turtle.setup(800,600,0,0)
    pen = turtle.Turtle()
    pen.color("red")
    pen.pensize(5)
    pen.left(36)
    pen.speed(5)
    pen.shape("turtle")
    for line in file:
        fd,lr,cc,r,y,b = eval(line)
        color = "#"
        if r == 1:
            color += "FF"
        else:
            color += "00"
        if y == 1:
            color += "FF"
        else:
            color += "00"
        if b == 1:
            color += "FF"
        else:
            color += "00"
        draw(pen,fd,lr,cc,color)
    file.close()
openFile("draw.txt")
turtle.done()