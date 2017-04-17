#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "toolbox.h"

#define TRUE 1
#define FALSE 0

#define IP_LEN 16 // No support for IPv6 so 16 bytes

struct Ip
{
    char ipAddress[ IP_LEN ];
    struct Ip* next;
};

struct IpList
{
    struct Ip* ipH; // Head
    struct Ip* ipT; // Tail
    int size;
};


typedef struct Ip Ip;
typedef struct IpList IpList;

IpList* create_ipList();
Ip* create_ip(char* ipAddress);
void append_ip_to_ipList(IpList* ipList, char* ipAddress);

Ip* get_first_ip(IpList* ipList);
void display_ipList(IpList* ipList);
int is_ip_exist(IpList* ipList, char* ipAddress);

#endif // LINKEDLIST_H
