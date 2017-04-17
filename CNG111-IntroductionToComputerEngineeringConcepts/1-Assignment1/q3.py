#$ python q3.py

num = int(raw_input("Enter> "))
i   = 2

while i <= num:
   if num%i == 0:
      print i
      while (num%i) == 0:
         num/=i
   else:
      i+=1
