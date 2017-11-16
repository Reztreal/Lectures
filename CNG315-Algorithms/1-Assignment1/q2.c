/*
 * Title     : Question2 V1.0
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
#include <time.h>

#define SIZE 1000

// fflush(stdin) has problem in linux
void flush()
{
    while(getchar() != '\n');
}

// Prints array in the format "1 2 3 4 \n"
void
printSortedArray(const int array[],
                 const int size)
{
    int leftest=0, i=0;
    for(leftest=SIZE/2; array[leftest]!=-1; leftest--); //find leftest

    for(i=leftest+1; array[i]!=-1; i++)
    {
        printf("%d ", array[i]);
    }

    printf("\n");
}

// Creates "distorted" array by using "elements"
void
distortArray(const int elements[],
             const int pivot,
             const int size,
             int distorted[])
{
    int i=0;
    int left=pivot-1, right=pivot+1;

    distorted[0] = elements[pivot];

    for(i=1; i<size; i++)
    {
        if(right > size)
        {
            for(; left>0; left--, i++)
                distorted[i] = elements[left];
            break;
        }

        if(left < 0)
        {
            for(; right<size; right++, i++)
                distorted[i] = elements[right];
            break;
        }

        distorted[i] = elements[left];
        i++;
        distorted[i] = elements[right];

        left -= 1;  // go one left
        right += 1; // go one right
    }
}


/*
 * Prints array in the format "1 2 3 4 \n"
 * This is algorithm that prints the sorted array.
 */
void
printArray(const int array[],
           const int size)
{
    int i=0;
    for(i=0; i<size; i++)
        printf("%d ", array[i]);
    printf("\n");
}

// Sorts the "array" an save it in "sorted"
void
mySortingAlgorithm(const int array[],
                   const int size,
                   int sorted[])
{
    int leftestIndex = 0, rightestIndex = 0;
    const int middleIndex = SIZE/2; // SIZE/2 = 500 in my case

    // place the middle value
    sorted[ middleIndex ] = array[0];
    leftestIndex = middleIndex;
    rightestIndex = middleIndex;

    for(int i=1; i<size; i++)
    {
        if( array[i]>sorted[middleIndex] )
        {
            if( array[i]>sorted[rightestIndex] )
            {
                rightestIndex++;
                sorted[rightestIndex] = array[i];
            }
        }
        else // means new value is smaller than middle value
        {
            if( array[i]<sorted[leftestIndex] )
            {
                leftestIndex--;
                sorted[leftestIndex] = array[i];
            }
        }
    }

    sorted[leftestIndex-1] = -1; // means end of the left part
    sorted[rightestIndex+1] = -1; // means end of the right part
}



int
main()
{
    int numOfElems = 0, i=0;
    int elements[SIZE];

    srand(time(NULL));


    // ---> Number of elements input from user
    printf("How many elements [10..2000]: ");
    scanf("%d", &numOfElems);

    while(numOfElems<10 || numOfElems>2000)
    {
        printf("ERROR! Please enter size between 10 and 2000: ");
        scanf("%d", &numOfElems);
    }
    flush();


    // ---> Elements from user
    printf("Enter the array elements:  ");
    for(i=0; i<numOfElems; i++)
        scanf("%d", &(elements[i]));
    flush();


    // ---> Distort array
    int distorted[SIZE];
    int pivot = 3 + rand()%(numOfElems-4);
    printf("Pivot is chosen as the element with index %d which is %d\n",pivot, elements[pivot]);
    distortArray(elements, pivot, numOfElems, distorted);

    printf("Distorted array: ");
    printArray(distorted, numOfElems);

    // ---> My cool sorting algorithm
    int sorted[SIZE];
    mySortingAlgorithm(distorted, numOfElems, sorted);

    // ---> Print the array
    printf("Sorted array   : ");
    printSortedArray(sorted, numOfElems);


    printf("\n\n");
    return 0;
}
