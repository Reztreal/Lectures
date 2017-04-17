#include "linkedlist.h"

IpList* create_ipList()
{
    IpList* ipList = (IpList*) sMalloc( sizeof(IpList) );
    ipList->size = 0;

    // Head is dummy node
    ipList->ipH = create_ip("\0");

    ipList->ipT = ipList->ipH;

    return ipList;
}

Ip* create_ip(char* ipAddress)
{
    Ip* ip = (Ip*) sMalloc( sizeof(Ip) );
    ip->next = NULL;

    strcpy( ip->ipAddress, ipAddress );

    return ip;
}

void append_ip_to_ipList(IpList* ipList, char* ipAddress)
{
    Ip* newIp = create_ip(ipAddress);
    newIp->next = NULL;

    ipList->ipT->next = newIp;
    ipList->ipT = ipList->ipT->next;
    ipList->size++;
}

Ip* get_first_ip(IpList* ipList)
{
    return ipList->ipH->next;
}

void display_ipList(IpList* ipList)
{
    Ip* ip = get_first_ip(ipList);

    while( ip )
    {
        printf("%s", ip->ipAddress);
        if( ip->next!=NULL )
            printf(", ");

        ip = ip->next;
    }
    printf("\n");
}

int is_ip_exist(IpList* ipList, char* ipAddress)
{
    Ip* ip = get_first_ip(ipList);

    while( ip )
    {
        if( !strcmp(ip->ipAddress, ipAddress) )
            return TRUE;
        ip = ip->next;
    }

    return FALSE;
}
