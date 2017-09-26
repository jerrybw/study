'''
Created on 2017年9月20日

@author: XX
'''
def main():
    while True:
        nameOfIngredients = input("please enter the name of ingredients,and split with ','(exit whit enter '-1'):")
        if "-1" == nameOfIngredients:
            return None
        Separator = "，"
        if "," in nameOfIngredients:
            nameOfIngredients=nameOfIngredients.replace(",", "，")
        if not Separator in nameOfIngredients:
            print("please enter at least two name of ingredients,and split with '，'")
            continue
        splits = nameOfIngredients.split(Separator)
        for i in range(len(splits)):
            for j in range(len(splits)):
                if i == j:
                    continue
                print(splits[i].strip()+splits[j].strip())
main()
print("bye!")
    