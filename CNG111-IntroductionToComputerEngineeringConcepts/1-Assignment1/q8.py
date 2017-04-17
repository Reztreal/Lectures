#$ python q8.py

n = int(raw_input("Enter> "))
i = 0.0
j = 1.0
print "Seeking the square root of {}".format(n)

while j*j < n:
   j*=2.0

print "For j = {}, square of j exceeds {}".format(j, n)

g = 0.0
while True:
   g = (i+j)/2
   d = g*g-n
   if d < 0:
      d *= -1
   
   if d < 0.001:
      print "The square root of {} is {}".format(n, g)
      break
   print "i = {}, j = {}, g = {}".format(i,j,g)
   
   if g*g < n:
      i = g
   elif g*g > n:
      j = g
