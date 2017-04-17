#ifndef TOOLBOX_H
#define TOOLBOX_H

#include <stdlib.h>
#include <stdio.h>

void flush(); // fflush has problem in linux
void sFree(void *ptr); // Secure free
void* sMalloc(const size_t size); // Secure malloc

int is_between(const int n, const int low, const int top);
void strip(char* s, const int size);
void make_lowercase(char* str);

#endif // TOOLBOX_H
