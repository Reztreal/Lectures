#include "indexingwebsites.h"

void run(Page* page)
{
    int selection=0;
    char ipAddress[ IP_LEN];
    char pageName[ NAME_LEN ];

    while(selection!=4)
    {
        display_menu();
        selection = get_selection();

        if( selection==1 )
        {
            display_index(page);
        }
        else if( selection==2 )
        {
            printf("Please enter the page: ");
            scanf("%s", pageName); flush();
            IpList* ipList = search_url(page, pageName);
            if( ipList )
            {
                printf("IP addresses for %s: ", pageName);
                display_ipList(ipList);
            }
            else
                printf("Page couldn't find.\n");
        }
        else if( selection==3 )
        {
            printf("Please enter the URL: ");
            scanf("%s", ipAddress); flush();
            while( !is_valid_ipv4(ipAddress) )
            {
                printf("IP has to be in this format xxx.xxx.xxx.xxx\n");
                printf("Please enter the URL: ");
                scanf("%s", ipAddress); flush();
            }
            Page* correspondingPage = search_ip(page, ipAddress);
            if( correspondingPage )
                printf("It belongs to %s\n", correspondingPage->pageName);
            else
                printf("There is no page for %s\n", ipAddress);
        }
        else if( selection==4 )
        {
            printf("Bye!\n");
            break;
        }
        printf("\n");
    }
}

Page* read_ip_data(char* fileName)
{
    Page* page = NULL;
    char pageName[ NAME_LEN ];
    char ipAddress[ IP_LEN ];

    FILE* fo = sFopen(fileName, "r");
    if(!fo) return NULL;

    while( fscanf(fo, "%s > %s", pageName, ipAddress)!=-1 )
    {
        sscanf(pageName, "%[^.]", pageName); // means read till first dot
        make_lowercase(pageName);
        page = insert_page(page, pageName, ipAddress);
    }

    fclose(fo);

    return page;
}

Page* search_ip (Page* page, char* ipAddress)
{
    Page* i = NULL;

    if (page != NULL)
    {
        i = search_ip(page->left, ipAddress);
        if( is_ip_exist(page->ipList, ipAddress) )
            return page;
        if( i ) return i;

        i = search_ip(page->right, ipAddress);
        if( i ) return i;
    }
    return NULL;
}

IpList* search_url(Page* page, char* pageName)
{
    IpList* i = NULL;

    if (page != NULL)
    {
        i = search_url(page->left, pageName);
        if( !strcmp( page->pageName, pageName ) )
            return page->ipList;
        if( i ) return i;

        i = search_url(page->right, pageName);
        if( i ) return i;
    }
    return NULL;
}

void display_menu()
{
    printf("--------    MENU    --------\n"
           "1. Display the full index\n"
           "2. Search for a URL\n"
           "3. Search for an IP address\n"
           "4. Exit\n"
           "----------------------------\n"
           );
}

int get_selection()
{
    int selection=0;

    printf("Option: ");

    scanf("%d", &selection); flush();
    while( !is_between(selection, 0,5) )
    {
        printf("Please enter valid option: ");
        scanf("%d", &selection); flush();
    }

    printf("\n");

    return selection;
}
