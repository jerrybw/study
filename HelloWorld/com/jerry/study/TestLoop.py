'''
Created on 2017年8月16日

@author: XX
'''
for i in range(100):
    if i % 3 == 0:
        continue
    print(i)
    if i % 31 ==0:
        break
    for j in range(i + 1):
        print(j)
    else:
        print(j + 1,"is not in 0 ~",i)
try:
    10 / 0
except ZeroDivisionError:
    print('can`t div by zero')
else:
    print('hahaha')
finally:
    print('我就是要执行')