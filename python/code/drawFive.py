import turtle
def draw(x,y):
    turtle.clear() 
    while True:
        turtle.forward(200)
        turtle.left(89)
        if abs(turtle.pos()) < 1:
            break
draw(0,0)
turtle.onscreenclick(draw)
turtle.mainloop()
