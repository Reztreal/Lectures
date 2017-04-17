#include "toolbox.h"

void* sMalloc(const size_t size) // Secure malloc
{
    void *ptr = malloc(size);
    if(!ptr)
    {
        printf("[!] No enough memory.\n");
        exit(EXIT_FAILURE);
    }
    return ptr;
}

void sFree(void *ptr) // Secure free
{
    if(!ptr)
    {
        printf("[!] The pointer is NULL.\n");
        exit(EXIT_FAILURE);
    }
    free(ptr);
}

void flush() // fflush has problem in linux
{
    while(getchar() != '\n');
}

int is_between(const int n, const int low, const int top)
{
    return (n>low&&n<top);
}
void strip(char* s, const int size)
{
    char* r = (char*) sMalloc(sizeof(char)*size);
    int i=0;

    for(i=0; is_between(s[i], 32, 127) ; i++)
        r[i] = s[i];

    s[i] = '\0';

    for(i--; i>=0; i--)
        s[i] = r[i];

    sFree(r);
}
void make_lowercase(char* str)
{
    int i=0;
    for(i = 0; str[i]; i++)
        if( is_between(str[i], 64,91) )
            str[i] = (char) ( (int)str[i] + 32 );
}
