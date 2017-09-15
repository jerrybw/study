'''
Created on 2017年9月6日

@author: XX
'''
import turtle
count = 10
data=[]
words=[]
yScale = 6
xScale = 30
def replaceBiaoDian(line):
    BiaoDians = "`~!@#$%^&*()_+-={}[]|\:;"'<,>.?/'
    for ch in line:
        if ch in BiaoDians:
            line.replace(ch," ")
    return line

def countWords(line,countDict):
    line = replaceBiaoDian(line)
    wordsTuple = line.split()
    for word in wordsTuple:
        if word in countDict.keys():
            countDict[word] += 1
        else:
            countDict[word] = 1
    return countDict 

def drawLine(t,x1,y1,x2,y2):
    t.penup()
    t.goto(x1,y1)
    t.pendown()
    t.goto(x2,y2)
    
def writeText(t,x,y,text):
    t.penup()
    t.goto(x,y)
    t.pendown()
    t.write(text)
    
def drawRectangle(t,x,y):
    x=x*xScale
    y=y*yScale
    drawLine(t,x-5,0,x-5,y)             
    drawLine(t,x-5,y,x+5,y)             
    drawLine(t,x+5,y,x+5,0)             
    drawLine(t,x+5,0,x-5,0)
        
def drawBar(t):
    for i in range(count):
        drawRectangle(t,i+1,data[i])   
        
def drawPic():
    turtle.title('词频结果柱状图')
    turtle.setup(900,750,0,0)
    t = turtle.Turtle()
    t.hideturtle()
    t.width(3)
    drawLine(t, 0, 0, 360, 0)
    drawLine(t, 0, 300, 0, 0)
    for x in range(count):
        x=x+1
        writeText(t, x*xScale-4, -20, (words[x-1]))
        writeText(t, x*xScale-4, data[x-1]*yScale+10, (data[x-1]))
    drawBar(t)
    turtle.done()   
def printResult(items):
    for i in range(len(items) - 1,len(items)-count-1,-1):
        print(items[i][1] + " count tims = " + str(items[i][0]))
        data.append(items[i][0])
        words.append(items[i][1])   
def main():                   
    fileName = input("Please enter the fileName:").strip()
    infile = open(fileName,"r")
    countDict={}
    for line in infile:
        countDict = countWords(line, countDict)
    countTuple = list(countDict.items())
    items = [[x,y] for (y,x) in countTuple]
    items.sort()
    printResult(items)
    drawPic()
    infile.close()
if __name__ =='__main__':
    main()