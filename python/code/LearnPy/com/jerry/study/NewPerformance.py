'''
Created on 2017年9月11日

@author: XX
'''
import time
from functools import reduce
def performance(unit):
    def performance_decorator(f):
        def fn(*args,**kw):
            times = 1
            if unit == "ms":
                times = 1000
            t1 = times * time.time()
            r = f(*args,**kw)
            t2 = times * time.time()
            use = t2 - t1
            print("call %s() in %f%s"%(f.__name__,use,unit))
            return r
        return fn
    return performance_decorator
@performance('ms')
def factorial(n):
    return reduce(lambda x,y: x*y, range(1, n+1))

print(factorial(1000))