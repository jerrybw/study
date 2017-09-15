'''
Created on 2017年9月11日

@author: XX
'''
from functools import reduce
import time
def performance(f):
    def fn(*args,**kw):
        t1 = time.time()
        r = f(*args,**kw)
        t2 = time.time()
        use = t2 - t1
        print("call %s() in %fs"%(f.__name__,use))
        return r
    return fn

@performance
def factorial(n):
    return reduce(lambda x,y: x*y, range(1, n+1))

print(factorial(1000))