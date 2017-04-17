#ifndef TOOLBOX_H
#define TOOLBOX_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define TRUE 1
#define FALSE 0

FILE* sFopen(const char *fileName, const char *mode); // Secure open file
void flush(); // flush stdin
void sFree(void *ptr); // Secure free
void* sMalloc(const size_t size); // Secure malloc

int is_between(const int n, const int low, const int top); // low and top are not included
void strip(char* s);
void make_lowercase(char* str);
int is_valid_ipv4(char* ipAddress); // return true if ipv4 format is valid
int max_between(int first, int second);

#endif // TOOLBOX_H
