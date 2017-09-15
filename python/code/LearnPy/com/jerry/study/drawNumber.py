'''
Created on 2017年8月28日

@author: XX
'''
from turtle import *
def drawLine(p):
    p.fd(50)
    p.penup()
    p.fd(5)
    p.right(90)
    p.fd(5)
    p.pendown()
    x = p.getx()
    y = p.gety()
    if(x == -300 and y == 300):
        return
    drawLine(p)

p = Turtle()
p.pensize(5)
p.penup()
p.goto(-300, 300)
p.pendown()
p.hideturtle()
p.color("red")
drawLine(p)
done()