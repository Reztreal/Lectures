/*
 * Title     : Question1 V1.0
 * Developer : Furkan TOKAC ftokac@acm.org
 * StudentID : e2016145
 * Compiler  : gcc version 5.4.0
 * OS        : KDE Neon LTS 5.8
 * IDE       : Qt Creator 4.4.1
 * License   : The MIT License (MIT)
 * Indent    : 4 spaces
*/

#include <stdio.h>
#include <stdlib.h>

#define TRUE 1
#define FALSE 0
#define SIZE 100


// Coordinate structure to use with coordinates
struct Coordinate {
    int x;
    int y;
    int z;
    int val;
};
typedef struct Coordinate Coordinate;


// Secure file open function just opens file by handling
// basic checks like if the file exist.
FILE*
sFopen(const char *fileName,
       const char *mode)
{
    FILE* fo;
    fo = fopen(fileName, mode);
    if( !fo ) {
        printf("[!] File is not found. Please check file name or file.\n");
        exit(EXIT_FAILURE);
    }
    return fo;
}



// ---> Coordinate functions

// cord = cord1;
void
equalizeCords(Coordinate *cord,
              const Coordinate cord1)
{
    cord->x = cord1.x;
    cord->y = cord1.y;
    cord->z = cord1.z;
    cord->val = cord1.val;
}

// cord = (x,y,z) as x,y and z are coordinates
void
cordEquals(Coordinate *cord,
           const int x,
           const int y,
           const int z)
{
    cord->x = x;
    cord->y = y;
    cord->z = z;
}

// cord1 == cord2
int
areCordsEqual(const Coordinate cord1,
              const Coordinate cord2)
{
    if(cord1.x == cord2.x)
        if(cord1.y == cord2.y)
            if(cord1.z == cord2.z)
                return TRUE;
    return FALSE;
}
// cord1 == (x,y,z)
int
isCordEqual(const Coordinate cord1,
            const int x,
            const int y,
            const int z)
{
    if(cord1.x == x)
        if(cord1.y == y)
            if(cord1.z == z)
                return TRUE;
    return FALSE;
}
// Prints the cord in the format "X Y Z VALUE\n"
void
printCord(const Coordinate cord)
{
    printf("%d %d %d %d\n", cord.x, cord.y, cord.z, cord.val);
}



// ---> ALGORITHMS

/**
 * Version 2
 * Complexity : x*(y>z?y:z)
 *
 * In this algorithm, in each loop, one layer is calculated
 * as 2d rectangle. Thats why its more efficient than v1.
 */
int
partA_v2(const int values[][SIZE][SIZE],
         const Coordinate cord1,
         const Coordinate cord2)
{
    int sum=0;          // results will be stored here
    Coordinate cordRef; // Reference point. This will be used in algorithm.
    Coordinate cord;    // Variable to use temporary value holder

    equalizeCords(&cordRef, cord1); // means cordRef = cord1

    while(cordRef.x <= cord2.x)
    {
        for(cord.y=cordRef.y; cord.y<=cord2.y; cord.y+=1)
            sum += values[cordRef.x][cord.y][cordRef.z];
        cord.y = cordRef.y; // reset cord.y becuase we used it

        for(cord.z=cordRef.z+1; cord.z<=cord2.z; cord.z+=1)
            sum += values[cordRef.x][cordRef.y][cord.z];
        cord.z = cordRef.z; // reset cord.z becuase we used it

        cordRef.y += 1;
        cordRef.z += 1;

        // move the reference point forward
        if(cordRef.y>cord2.y || cordRef.z>cord2.z )
            cordEquals(&cordRef, cordRef.x+1, cord1.y, cord1.z);
    }

    return sum;
}

/**
 * Version 1
 * Complexity : x*y*z
 *
 * This algorithm is using the "boxsum" array
 * that is produced in part B.
 */
int
partA_v1(const int values[][SIZE][SIZE],
         const Coordinate cord1,
         const Coordinate cord2)
{
    Coordinate cord;    // Variable to use temporary value holder
    int sum=0;          // results will be stored here

    for(cord.x=cord1.x; cord.x<=cord2.x; cord.x+=1)
        for(cord.y=cord1.y; cord.y<=cord2.y; cord.y+=1)
            for(cord.z=cord1.z; cord.z<=cord2.z; cord.z+=1)
                sum += values[cord.x][cord.y][cord.z];

    return sum;
}



// ---> Main program

// Fill "values" and "dimensions" with values in "fileName" file.
void
loadValuesFromFile(const char *fileName,
                   int values[][SIZE][SIZE],
                   Coordinate *dimensions)
{
    Coordinate cord; // Variable to use temporary value holder

    FILE *fo = sFopen(fileName, "r");

    // Get dimensions from first line as "x y z \n"
    fscanf(fo, "%d %d %d \n", &(dimensions->x), &(dimensions->y), &(dimensions->z));

    // Get values for each coordinate
    while( fscanf(fo, "%d %d %d %d \n", &cord.x, &cord.y, &cord.z, &cord.val) != -1 )
        values[cord.x][cord.y][cord.z] = cord.val;
    fclose(fo);
}

// PartB : Fill "boxsum" with sums by using algorithms
void
fillBoxsumArray(int boxsum[][SIZE][SIZE],
                const int values[][SIZE][SIZE],
                const Coordinate dimensions)
{
    Coordinate cord;        // Variable to use temporary value holder
    Coordinate cordZero;    // Will be used to use written algorithms in part A

    cordEquals(&cordZero, 0,0,0);
    cordEquals(&cord, 0,0,0);

    for(cord.x=0; cord.x<=dimensions.x; cord.x+=1)
        for(cord.y=0; cord.y<=dimensions.y; cord.y+=1)
            for(cord.z=0; cord.z<=dimensions.z; cord.z+=1)
                boxsum[cord.x][cord.y][cord.z] = partA_v2(values, cordZero, cord);
}


int
main2()
{
    int values[SIZE][SIZE][SIZE];
    int boxsum[SIZE][SIZE][SIZE];

    Coordinate dim;     // Dimensions of the box. (first line)
    Coordinate cord1;   // User input min (x1,y1,z1)
    Coordinate cord2;   // User input max (x2,y2,z2)

    loadValuesFromFile("values.txt", values, &dim);
    fillBoxsumArray(boxsum,values,dim);

    // Also by entering the values below, program can easily be tested.
    //cordEquals(&cord1, 0,0,1);
    //cordEquals(&cord2, 4,4,4);

    scanf("(%d,%d,%d) (%d,%d,%d)", &cord1.x, &cord1.y, &cord1.z, &cord2.x, &cord2.y, &cord2.z);
    printf("The sum of pixels : %d\n", partA_v2(values, cord1, cord2));

    return 0;
}
