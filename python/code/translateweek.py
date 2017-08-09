weeks = "MonTueWedThuFriSatSun"
i = input("请输入星期")
pos = (int(i) - 1) * 3
week = weeks[pos:pos+3]
print("您输入的星期的简写为："+week)
