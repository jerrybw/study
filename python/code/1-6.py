diet = ['番茄','西红柿','柿子','鸡蛋','鸡子儿']
for i in range(5):
    for j in range(5):
        if(i != j):
            print("{}{}".format(diet[i],diet[j]))
