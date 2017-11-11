
The file "values.txt" contains a sample 3D matrix (5x5x5) values.
You can try your program with this data.
In fact you must adjust your program to read such a file.
That's how I will test your programs!
For debugging you can create an axample array with all values being 1.
Then it will be easy to verify sums. 
The volume of the box (xlen * ylen * zlen) = the sum of values in the box!


The format of the file (values.txt) is like this:
The first line gives you the x y z dimensions.
Please define a big int array (100x100x100) in C and 
read the values from file into that array.

After that each line has this format: 
x y z value



Here are some sample bounding boxes and sums on this data file:

Box coordinates          The sum of  
(x1,y1,z1) (x2,y2,z2)    pixels 
----------------------------------------------------------- 
(0,0,0) (4,4,4)           15548
(0,0,0) (1,1,1)           1282
(0,1,2) (3,2,3)           1412
(0,0,2) (2,3,4)           3688
(3,1,0) (4,3,2)           1773
(2,2,0) (4,4,2)           3216


Please email to me in case any error!
