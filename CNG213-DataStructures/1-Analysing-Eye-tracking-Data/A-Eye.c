/*
 * Title     : A-Eye V1.0
 * Developer : Furkan TOKAC
 * Compiler  : gcc version 5.4.0
 * OS        : Ubuntu 16.04 LTS
 * IDE       : Geany 1.27
 * License   : The MIT License (MIT)
 * Indent    : 4 spaces
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// BEG REDEFINATIONS____________________________________________________
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
FILE* sFopen(const char *fileName, const char *mode) // Secure open file
{
    FILE* fo;
    fo = fopen(fileName, mode);
    if( !fo ) {
        printf("[!] File is not found. Please check file name or file.\n");
        exit(EXIT_FAILURE);
    }
    return fo;
}
void flush() // fflush has problem in linux
{ 
    while(getchar() != '\n');
}
// ____________________________________________________END REDEFINATIONS

struct Data {
    int index;
    int timestamp;
    int duration;
    int x;
    int y;
    
    struct Data* next;
};

struct Stimuli {
    char name[50];
    struct Data* dataH; // Head of Data
    struct Data* dataT; // Tail of Data
    
    struct Stimuli* next;
    int size;
};

struct Stimulist { // stimuli list
    struct Stimuli* stimuliH; // Head of Stimuli
    struct Stimuli* stimuliT; // Tail of Stimuli
    int size;
};

typedef struct Data Data;
typedef struct Stimuli Stimuli;
typedef struct Stimulist Stimulist;

Stimuli* first_stimuli(Stimulist* sl) { return sl->stimuliH->next; }
Data* first_data(Stimuli* s) { return s->dataH->next; }

// RESTRICTED FUNCTIONS
void show_page_statistics(Stimulist* sl);
void show_AOI_statistics(Stimuli* s, int topX, int topY, int botX, int botY);
int count_fixation_points(Stimuli* s);
int total_fixation_duration(Stimuli* s);
void clean_data_points(Stimulist* sl, const int threshold);
    void clean_data_below_threshold(Stimuli* s, const int threshold);
Stimulist* load_data_points(const char *fileName);
// MY FUNCTIONS
Stimuli* is_stimuli_exist(Stimulist* sl, const char* name);
Stimuli* is_stimuli_exist_keyword(Stimulist* sl, const char* keyword);
Stimuli* find_stimuli(Stimulist* sl);
Data* append_data(Stimuli* s, const int index, const int timestamp, const int duration, const int x, const int y);
Stimuli* append_stimuli(Stimulist* sl, const char *stimuliName);
Stimulist* create_stimulist();
// TOOLBOX
int is_between(const int n, const int low, const int top);
void strip(char* s, const int size);
void make_lowercase(char* str);
// DEBUGGING
void print_stimulist(Stimulist *sl);
void print_stimuli(Stimuli *s);
int verify_stimulist(Stimulist *sl, int verbose);
int verify_data(Stimuli *s, int verbose);
// MAIN FUNCTIONS
void run_gui(Stimulist* sl);
void print_menu();

int main(int argc, char **argv)
{
    Stimulist* sl = load_data_points("P1.txt");
    run_gui(sl);
    
	return 0;
}

void run_gui(Stimulist* sl)
{
    Stimuli* s = NULL;
    int choice=0;
    while(choice!=4)
    {
        print_menu();
        printf("Enter your option: ");
        scanf("%d", &choice);
        flush();
        
        while(!is_between(choice, 0, 5))
        {
            printf("Unknown option. Please enter again: ");
            scanf("%d", &choice);
            flush();
        }
        switch(choice)
        {
            case 1:
                printf("Enter a threshold value in milliseconds: ");
                int threshold=0;
                scanf("%d", &threshold);
                flush();
                clean_data_points(sl, threshold);
                break;
            case 2:
                show_page_statistics( sl );
                break;
            
            case 3:
                s = find_stimuli(sl);
                if(!s) break;
                printf("Please enter the top [X,Y] and bottom [X,Y] coordinates of the AOI: ");
                int topX=0, topY=0, botX=0, botY=0;
                scanf("[%d,%d][%d,%d]", &topX, &topY, &botX, &botY);
                flush();
                show_AOI_statistics(s, topX, topY, botX, botY);
                break;
            
            case 4:
                printf("Goodbye!\n");
                break;
        }
        
        printf("\n");
    }
}
void print_menu()
{
    printf( "------------------------   \n"
            "A-Eye Menu                 \n"
            "------------------------   \n"
            "1. Clean Data Points       \n"
            "2. Show Page Statistics    \n"
            "3. Show AOI Statistics     \n"
            "4. Exit from A-Eye         \n"
            "------------------------   \n"
        );
}

// BEG RESTRICTED FUNCS_________________________________________________
void show_page_statistics(Stimulist* sl)
{
    Stimuli* s = find_stimuli(sl);
    if(!s) return;
    
    printf("The number of fixations: %d\n", count_fixation_points(s));
    printf("The total duration of fixations: %d milliseconds\n", total_fixation_duration(s));
}
void show_AOI_statistics(Stimuli* s, int topX, int topY, int botX, int botY)
{
    Data* d = first_data(s);
    int totalDuration = 0;
    int numOfFixation = 0;
    
    while( d->next )
    {
        if( is_between(d->x, topX, botX) && is_between(d->y, topY, botY) )
        {
            numOfFixation++;
            totalDuration+= d->duration;
        }
        d = d->next;
    }
    
    printf("The number of fixations: %d\n", numOfFixation);
    printf("The total duration of fixation: %d\n", totalDuration);
}

int count_fixation_points(Stimuli* s) { return s->size; }
int total_fixation_duration(Stimuli* s)
{
    Data* d = first_data(s);
    int total=0;
    
    while( d->next )
    {
        total+=d->duration;
        d = d->next;
    }
    
    return total;
}

void clean_data_points(Stimulist* sl, const int threshold)
{   
    Stimuli* s = first_stimuli(sl);
    
    while( s->next )
    {
        clean_data_below_threshold(s, threshold);
        s = s->next;
    }
}
void clean_data_below_threshold(Stimuli* s, const int threshold)
{
    Data* last = s->dataH;
    Data* d = first_data(s);
    
    while( d->next )
    {
        if( d->duration < threshold )
        {
            last->next = d->next;
            sFree(d);
            d = last;
            s->size--;
        }
        last = d;
        d = d->next;
    }
}

Stimulist* load_data_points(const char* fileName)
{
    Stimulist* sl = create_stimulist();;
    Stimuli* s;
    int index, timestamp, duration, x, y;
    char name[50];
    char tmp[100];
    
    FILE* fo = sFopen(fileName, "r");
    if(!fo) return NULL;
    
    fscanf(fo, "%[^\n]", tmp); // Skip the first line
    while( fscanf(fo, "%d %d %d %d %d %[^\n]", &index, &timestamp, &duration, &x, &y, name)!=-1 )
    {
        strip(name, 50);
        if( !strcmp(name, "No") || !strcmp(name, "ScreenRec") ) continue;
        s = is_stimuli_exist(sl, name);
        if(!s) s = append_stimuli(sl, name);
        append_data(s, index, timestamp, duration, x, y);
    }
    
    fclose(fo);
    return sl;
}
// _________________________________________________END RESTRICTED FUNCS

// BEG MY FUNCS_________________________________________________________
// If stimuli exist, return the stimuli
Stimuli* is_stimuli_exist(Stimulist* sl, const char* name)
{
    Stimuli* s = first_stimuli(sl);
    
    while( s->next )
    {
        if( !strcmp(s->name, name) )
            return s;
        s = s->next;
    }
    return NULL;
}
// If stimuli exist, return the stimuli (search by keyword)
Stimuli* is_stimuli_exist_keyword(Stimulist* sl, const char* keyword)
{
    Stimuli* s = first_stimuli(sl);
    
    while( s->next )
    {
        if( strstr(s->name, keyword) )
            return s;
        s = s->next;
    }
    
    return NULL;
}
Stimuli* find_stimuli(Stimulist* sl)
{
    Stimuli* s = NULL;
    
    char keyword[50];
    printf("Enter a page name: ");
    scanf("%s", keyword);
    flush();
    
    make_lowercase(keyword);
    
    s = is_stimuli_exist_keyword(sl, keyword);
    
    if(!s) printf("Couldn't find \"%s\".\n", keyword);
    
    return s;
}

// This function adds new data to stimuli.dataT and
// mallocs new dataT as dummy tail in stimuli.
Data* append_data(Stimuli* s, const int index, const int timestamp, const int duration, const int x, const int y)
{
    // Create new data and initialize
    Data* dT = s->dataT;
    
    dT->index = index;
    dT->timestamp = timestamp;
    dT->duration = duration;
    dT->x = x;
    dT->y = y;
    
    // stimuli.size should increase
    s->size++;
    
    // Allocate new dummy tail for stimuli.dataT
    s->dataT->next = (Data*) sMalloc(sizeof(Data));
    s->dataT = s->dataT->next;
    s->dataT->next = NULL;
    
    return dT;
}

// This function adds new stimuli to stimulist.stimuliT
// and mallocs new stimuliT as dummy tail in stimulist.
Stimuli* append_stimuli(Stimulist* sl, const char *stimuliName)
{
    // Create new stimuli and initialize
    Stimuli* sT = sl->stimuliT;
    
    strcpy(sT->name, stimuliName);
    
    // Dummy data head and data tail
    sT->dataH = (Data*) sMalloc(sizeof(Data));
    sT->dataT = (Data*) sMalloc(sizeof(Data));
    sT->dataH->next = sT->dataT;
    sT->dataT->next = NULL;
    
    // stimulist.size should increase
    sl->size++;
    
    // Allocate new dummy tail for stimulist.stimuliT
    sl->stimuliT->next = (Stimuli*) sMalloc(sizeof(Stimuli));
    sl->stimuliT = sl->stimuliT->next;
    sl->stimuliT->next = NULL;
    
    return sT;
}

// Create new stimulist
Stimulist* create_stimulist()
{
    Stimulist* sl = (Stimulist*) sMalloc(sizeof(Stimulist));
    sl->size = 0;
    
    // Dummy stimuli head and stimuli tail
    sl->stimuliH = (Stimuli*) sMalloc(sizeof(Stimuli));
    sl->stimuliT = (Stimuli*) sMalloc(sizeof(Stimuli));
    sl->stimuliH->next = sl->stimuliT;
    sl->stimuliT->next = NULL;
    
    return sl;
}
// _________________________________________________________END MY FUNCS
// BEG TOOLBOX__________________________________________________________
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
// __________________________________________________________END TOOLBOX
// BEG DEBUGGING________________________________________________________
int verify_stimulist(Stimulist *sl, int verbose)
{
    Stimuli* s = sl->stimuliH;
    int i=1;
    
    if( !verbose ) 
    {
        s = s->next;
        while( s->next ) { s = s->next; i++; }
        if( sl->stimuliT == s && (i-1)==sl->size )
            return 1;
        return 0;
    }
    
    printf("------------------[DEBUG BEG]\n");
    printf("Verification for Stimulist is starting...\n");
    printf("Amount of Stimuli : %d\n", sl->size);
    printf("  Dummy Head : %p\n", s);
    printf("stimuli.head : %p\n\n", sl->stimuliH);
    s = s->next;
    while( s->next )
    {
        printf("Data%d : %p\n", i++, s);
        s = s->next;
    }
    printf("\n  Dummy Tail : %p\n", s);
    printf("stimuli.tail : %p\n", sl->stimuliT);
    if( sl->stimuliT==s && (i-1)==sl->size )
    {
        printf("[REPORT] Verificaion is successfull.\n");
        printf("------------------[DEBUG END]\n");
        return 1;
    }
    printf("[REPORT] Verification has failed. Please debug your code.\n");
    printf("------------------[DEBUG END]\n");
    return 0;
}
int verify_data(Stimuli *s, int verbose)
{
    Data* d = s->dataH;
    int i=1;
    
    if( !verbose ) 
    {
        d = d->next;
        while( d->next ) { d = d->next; i++; }
        if( s->dataT == d && (i-1)==s->size )
            return 1;
        return 0;
    }
    
    printf("------------------[DEBUG BEG]\n");
    printf("Verification for data of \"%s\" is starting...\n", s->name);
    printf("Data amount of \"%s\" : %d\n", s->name, s->size);
    printf("Dummy Head : %p\n", d);
    printf(" data.head : %p\n\n", s->dataH);
    d = d->next;
    while( d->next )
    {
        printf("Data%d : %p\n", i++, d);
        d = d->next;
    }
    printf("\nDummy Tail : %p\n", d);
    printf(" data.tail : %p\n", s->dataT);
    if( s->dataT == d && (i-1)==s->size )
    {
        printf("[REPORT] Verificaion is successfull.\n");
        printf("------------------[DEBUG END]\n");
        return 1;
    }
    printf("[REPORT] Verification has failed. Please debug your code.\n");
    printf("------------------[DEBUG END]\n");
    return 0;
}
// ________________________________________________________END DEBUGGING
