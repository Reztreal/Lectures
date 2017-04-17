#$ python q4.py
#formul num = 133 x counter + 1

num     = 133
counter = 1

while True:
   num = counter * 133 + 3
   i = 2
   factors = 0 #Number of prime factors
   temp = num  #We must not lost value of num
   while i <= temp:
      if temp%i == 0:
         factors+=1
         while (temp%i) == 0:
            temp/=i
      else:
         i+=1
   
   print "factors :", factors, "num :", num
   
   if factors >= 6:
      print "{} has {} prime factors and {}%133=3".format(num, factors, num)
      break
   
   counter+=1
