#ifndef AVLTREE_H
#define AVLTREE_H

#include <stdlib.h>
#include <stdio.h>

#include "linkedlist.h"
#include "toolbox.h"

#define NAME_LEN 64

struct Page
{
    char pageName[NAME_LEN];
    IpList* ipList;

    int height;

    struct Page* left;
    struct Page* right;
};

typedef struct Page Page;

Page* create_page(char* name, char* ipAddress);
void append_ip_to_page(Page* page, char* ipAddress);
void display_index(Page* page);

Page* insert_page(Page* page, char* pageName, char* ipAddress);

int height_left(Page* page);
int height_right(Page* page);
int height(Page* page);
int max_height(Page* page); // max height between page->left and page->right

Page* single_rotate_left(Page* page);
Page* double_rotate_left(Page* page);
Page* single_rotate_right(Page* page);
Page* double_rotate_right(Page* page);

#endif // AVLTREE_H
