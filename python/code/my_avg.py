def average(*args):
    if(len(args) == 0):
        return 0;
    sum = 0
    for i in args:
        sum = sum + i
    return sum/len(args)
print (average())
print (average(1, 2))
print (average(1, 2, 2, 3, 4))
