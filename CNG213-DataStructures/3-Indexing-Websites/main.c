/*
 * Title     : Indexing Websites V1.0
 * Developer : Furkan TOKAC
 * Compiler  : gcc version 5.4.0
 * OS        : Ubuntu 16.04 LTS
 * IDE       : Qt Creator 4.1.0
 * License   : The MIT License (MIT)
 * Indent    : 4 spaces
 *
 *#Compiling with gcc
 * gcc Project_Lib/linkedlist.c Project_Lib/avltree.c Project_Lib/toolbox.c Project_Lib/indexingwebsites.c main.c -o main
 *
 *#Compiling with Qt Creator
 * Open the project by clicking .pro file ==> build by ctrl+b ==> run by ctrl+r
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Project_Lib/indexingwebsites.h"

#define ARGSIZE 1

void argparser(int argc, char *argv[], char* fileName)
{
    if( argc-1!=ARGSIZE )
    {
        printf("[!] No argument found.\n");
        printf("Usage : ./CNG213_Assignment_3 UrlFileName\n\n");

        printf("Would you like to enter file name ? (Y/N) ");
        scanf("%s", fileName); flush();

        if( fileName[0]=='Y' || fileName[0]=='y' )
        {
            printf("Enter file name : ");
            scanf("%s", fileName); flush();
            printf("\n");
            return;
        }
        printf("Bye!\n\n");
        exit(0);
    }

    strcpy(fileName, argv[1]);
}

int main(int argc, char *argv[])
{
    char fileName[64];
    argparser(argc, argv, fileName);

    Page* page = read_ip_data( fileName );
    printf("Indexing Websites!\n");
    printf("Your file %s has been loaded, the index has been created!\n", fileName);
    run(page);

    printf("\n");
    return 0;
}
