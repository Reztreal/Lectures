#include "toolbox.h"

FILE* sFopen(const char *fileName, const char *mode) // Secure open file
{
    FILE* fo;
    fo = fopen(fileName, mode);
    if( !fo )
    {
        printf("[!] File couldn't found. Please check file name or file.\n\n");
        exit(EXIT_FAILURE);
    }
    return fo;
}

void* sMalloc(const size_t size) // Secure malloc
{
    void *ptr = malloc(size);
    if(!ptr)
    {
        printf("[!] No enough memory.\n\n");
        exit(EXIT_FAILURE);
    }
    return ptr;
}

void sFree(void *ptr) // Secure free
{
    if(!ptr)
    {
        printf("[!] The pointer is NULL.\n\n");
        exit(EXIT_FAILURE);
    }
    free(ptr);
}

void flush() // flush stdin
{
    while(getchar() != '\n');
}

int is_between(const int n, const int low, const int top)
{
    return (n>low&&n<top);
}
void strip(char* s)
{
    int i=0;

    for(i=0; is_between(s[i], 32, 127) ; i++);
    s[i] = '\0';
}
void make_lowercase(char* str)
{
    int i=0;
    for(i = 0; str[i]; i++)
        if( is_between(str[i], 64,91) )
            str[i] = (char) ( (int)str[i] + 32 );
}

int is_valid_ipv4(char* ipAddress)
{
    int ipLen = strlen(ipAddress);

    if( !is_between(ipLen, 6, 16) )
        return FALSE;

    // it would be better to take stripted ipAddress
    // if it is, will not have char* tail
    char tail[16];
    tail[0] = 0;

    unsigned int parts[4]; // ignore minus sign
    int c = sscanf(ipAddress, "%3u.%3u.%3u.%3u%s", &parts[0], &parts[1], &parts[2], &parts[3], tail);

    if ( c!=4 || tail[0] )
        return FALSE;

    int i=0;
    for (i=0; i<4; i++)
        if (parts[i] > 255)
            return FALSE;

    return TRUE;
}

int max_between(int first, int second)
{
    if(first>second)
        return first;
    return second;
}
