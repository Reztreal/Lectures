#ifndef INDEXINGWEBSITES_H
#define INDEXINGWEBSITES_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "avltree.h"
#include "linkedlist.h"
#include "toolbox.h"

#define TRUE 1
#define FALSE 0

void run(Page* page);
void display_menu(); // returns the user selection
int get_selection(); // get menu selection

Page* read_ip_data(char* fileName);

Page* search_ip (Page* page, char* ipAddress);
IpList* search_url (Page* page, char* pageName);

#endif // INDEXINGWEBSITES_H
