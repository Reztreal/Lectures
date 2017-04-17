#$ python q6.py

num = int(raw_input("Enter> "))

temp = num
while temp > 9:
   if (temp%10) > ((temp/10)%10):
      break
   temp/=10

if temp > 9:
   print "{} is NOT non-increasing".format(num)
else:
   print "{} is non-increasing".format(num)
