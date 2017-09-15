'''
Created on 2017年9月8日

@author: XX
'''
import math

def is_sqr(x):
    flag = math.sqrt(x) % 1 == 0
    print(type(flag),":",flag)
    return flag

print(list(filter(is_sqr, range(1, 101))))