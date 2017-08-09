months = "JanFebMarAprMayJunJulAugSepOctNovDec"
i = input("请输入月份")
pos = (int(i)-1)*3
month = months[pos:pos+3]
print("您输入的月份的英文简写："+month)
