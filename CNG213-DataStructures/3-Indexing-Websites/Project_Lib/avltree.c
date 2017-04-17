#include "avltree.h"

Page* create_page(char* pageName, char* ipAddress)
{
    Page* page = (Page*) sMalloc( sizeof(Page) );

    strcpy( page->pageName, pageName );
    page->left = page->right = NULL;
    page->ipList = create_ipList();

    append_ip_to_page(page, ipAddress);
    page->height = 0;

    return page;
}

Page* insert_page(Page* page, char* pageName, char* ipAddress)
{
    if (page == NULL) // adding new webpage
    {
        page = create_page(pageName, ipAddress);
    }
    else if (strcmp(page->pageName, pageName) > 0) // go left
    {
        page->left = insert_page(page->left, pageName, ipAddress);

        if( height_left(page) - height_right(page) == 2 )
        {
            if( strcmp( page->right->pageName, pageName ) < 0 )
                page = single_rotate_left(page);
            else
                page = double_rotate_left(page);
        }
    }
    else if (strcmp(page->pageName, pageName) < 0) // go right
    {
        page->right = insert_page(page->right, pageName, ipAddress);

        if( height_right(page) - height_left(page) == 2 )
        {
            if( strcmp( page->right->pageName, pageName ) > 0 )
                page = single_rotate_right(page);
            else
                page = double_rotate_right(page);
        }
    }
    else if ( !strcmp(page->pageName, pageName) ) // already exists
    {
        if( !is_ip_exist(page->ipList, ipAddress) )
            append_ip_to_page(page, ipAddress);
    }

    return page;
}

void append_ip_to_page(Page* page, char* ipAddress)
{
    append_ip_to_ipList(page->ipList, ipAddress);
}

void display_index(Page *page)
{
    if (page != NULL)
    {
        display_index(page->left);

        printf("%s, IP addresses: ", page->pageName);
        display_ipList(page->ipList);

        display_index(page->right);
    }
}

int height_left(Page* page)
{
    return height(page->left);
}

int height_right(Page* page)
{
    return height(page->right);
}

int height(Page* page)
{
    if( page==NULL)
        return -1;
    return page->height;
}

Page* single_rotate_left(Page* page)
{
    Page* left_side = page->left;
    page->left = left_side->right;
    left_side->right = page;

    left_side->height = max_height(left_side) +1;
    page->height = max_height(page) +1;


    return left_side;
}

Page* single_rotate_right(Page* page)
{
    Page* right_side = page->right;
    page->right = right_side->left;
    right_side->left = page;

    right_side->height = max_height( right_side ) +1;
    page->height = max_height(page) +1;

    return right_side;
}

Page* double_rotate_left(Page* page)
{
    page->left = single_rotate_right( page->left );
    page = single_rotate_left(page);

    return page;
}

Page* double_rotate_right(Page* page)
{
    page->right = single_rotate_left( page->right );
    page = single_rotate_right(page);

    return page;
}

int max_height(Page *page)
{
    return max_between( height_left(page), height_right(page) ) +1;
}

/*
// without balance
Page* insert_page(Page* page, char* pageName, char* ipAddress)
{
    if (page == NULL) // adding new webpage
    {
        page = create_page(pageName, ipAddress);
    }
    else if (strcmp(page->pageName, pageName) > 0) // go left
    {
        page->left = insert_page(page->left, pageName, ipAddress);
    }
    else if (strcmp(page->pageName, pageName) < 0) // go right
    {
        page->right = insert_page(page->right, pageName, ipAddress);
    }
    else if ( !strcmp(page->pageName, pageName) ) // already exists
    {
        if( !is_ip_exist(page->ipList, ipAddress) )
            append_ip_to_page(page, ipAddress);
    }

    return page;
}
*/
