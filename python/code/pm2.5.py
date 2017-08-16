def main():
    pm = int(input("请输入pm2.5指数"))
    i = 1
    while(4):
        if(i > 1):
            pm = int(input("请输入pm2.5指数"))
        i = i + 1
        if pm < 0:
            break
        elif pm > 75:
            print('今天空气质量为污染')
        elif pm < 35:
            print('今天空气质量为优')
        else:
            print('今天空气质量为良')
main()
