import random

def generateUniquePw(n=4):#if number of digit more than 10, it return 0 &&& default n is 4
   if n > 10: return 0
   s = str(random.randint(1,9))
   while len(s) < n:
      newDigit = str(random.randint(0,9))
      if not newDigit in s:
         s += str(newDigit)
   return s

def comparer(pw, gs):
   matches = 0
   members = 0
   
   for i in xrange(len(gs)):
      if pw[i] == gs[i]:
         matches+=1
      elif gs[i] in pw:
         members+=1
   
   return matches, members

def agent(password):
   turn = 0
   guess = ""
   while turn < 8 and guess != password:
      guess = raw_input("Enter guess> ")
      print "The Guess is", guess
      if not guess == password:
         macs, mems = comparer(password, guess)
         print "Turn  Guess  Matches Members"
         print "{}.    {}   {}       {}".format(turn+1, guess, macs, mems)
      else:
         turn = 10 #Use turn like key. This will end the loop end turn will be 11
      turn+=1
   if turn == 11: print "You Win :)"
   else:          print "You Lose :("
   return password


password = agent(generateUniquePw(4))
print "The Password was:", password
