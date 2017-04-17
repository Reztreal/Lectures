/*
 * Title     : Car Wash System V1.0
 * Developer : Furkan TOKAC
 * Compiler  : gcc version 5.4.0
 * OS        : Ubuntu 16.04 LTS
 * IDE       : Qt Creator 4.1.0
 * License   : The MIT License (MIT)
 * Indent    : 4 spaces
 *
 *#Compiling with gcc
 * gcc Project_Lib/queue.c Project_Lib/carwashsystem.c Project_Lib/toolbox.c Project_Lib/linkedlist.c Project_Lib/debugging.c main.c -o main
 *
 *#Compiling with Qt Creator
 * Open the project by clicking .pro file ==> build by ctrl+b ==> run by ctrl+r
 */

#include <stdio.h>
#include "Project_Lib/carwashsystem.h"
#include "Project_Lib/debugging.h"

#define ARGSIZE 3
#define SHIFTTIME 0
#define MAXWASHTIME 1
#define NUMOFMACHINES 2

void argparser(int argc, char *argv[], int args[])
{
    if( argc-1!=ARGSIZE )
    {
        printf("[!] Please enter %d arguments.\n", ARGSIZE);
        printf("Usage : ./CNF213_Assignment_2 shiftTime maxWashTime numberOfMachines\n");
        exit(EXIT_FAILURE);
    }

    args[SHIFTTIME] = atoi(argv[SHIFTTIME+1]);
    args[MAXWASHTIME] = atoi(argv[MAXWASHTIME+1]);
    args[NUMOFMACHINES] = atoi(argv[NUMOFMACHINES+1]);
}

int main(int argc, char *argv[])
{
    int args[ ARGSIZE ];
    argparser(argc, argv, args);

    run_simulator(args[SHIFTTIME], args[MAXWASHTIME], args[NUMOFMACHINES]);

    return 0;
}
