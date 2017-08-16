import math
def findResult(a,b,c):
    delta = b*b - 4*a*c
    if delta > 0:
        print('方程有两个实根')
        print('x1={},x2={}'.format((-b+math.sqrt(delta)/2*a),(-b-math.sqrt(delta)/2*a)))
    elif delta == 0:
        print('方程有一个实根')
        print('x={}'.format(-b/2*a))
    else:
        print('方程没有实根')    
a,b,c=input('请输入三个参数，中间用空格分隔').split(' ')
findResult(int(a),int(b),int(c))
