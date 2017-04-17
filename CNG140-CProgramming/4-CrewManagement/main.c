/*
 * Furkan TOKAC 2016145
 * Ubuntu 14.04
 * Assignment 4
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int id;
    int level;
    int cost;
    int *jobs; // jobs[0] is amount of job
}Crew;

typedef struct
{
    int aircraftId;
    int level;
    int hours;
}Maintenance;

Crew * getCrewData(Crew *crew, int *i);
Maintenance * getMaintenanceData(Maintenance *maint, int *i);
int checkCrew(int level, int num, Crew *crew); // Num is number of crews
void checkMaintenanceLevel(Crew *crew, int noc, Maintenance *maint, int nom);
int crewHoursCalculator(Maintenance maint);
Appends item to
int exportStructure(Crew *crew, int noc, Maintenance *maint, int nom, int type); // type=1:Cheapest --- type=2:Fastest
int * append(int *a, int item); // Add item end of a
int * pop(int *a); // delete last item of a
int * expandArray(int *a, int currentSize, int expandSize); // expand/shrink array(EX: if you put -1 to expandSize, it will shrink array
void exchange(int *a, int *b);

int main()
{
    Crew *crew =(Crew *) malloc(sizeof(Crew));
    Maintenance *maintenance =(Maintenance *) malloc(sizeof(Maintenance));
    int numOfCrew=0, numOfMaint=0;
    
    crew = getCrewData(crew, &numOfCrew);
    maintenance = getMaintenanceData(maintenance, &numOfMaint);
    checkMaintenanceLevel(crew, numOfCrew, maintenance, numOfMaint);
    
    return 0;
}

void checkMaintenanceLevel(Crew *crew, int noc, Maintenance *maint, int nom) // noc:number of crew nom:number of maintenance
{
    int i=0, j=0, cheapest=0;
    // Cheapest
    for(i=0; i<nom; i++)
    {
        cheapest =checkCrew( maint[i].level, noc, crew );
        for(j=0; j<maint[i].hours; j++)
            crew[cheapest].jobs = append( crew[cheapest].jobs, i);
    }
    int average=exportStructure(crew, noc, maint, nom, 1);
    // Earliest.txt
    for(i=0; i<noc-1; i++)
        while( crew[i].jobs[0]>average )
        {
            crew[i+1].jobs =append( crew[i+1].jobs, crew[i].jobs[ crew[i].jobs[0] ] );
            crew[i].jobs =pop( crew[i].jobs );
        }
    //getCrewData(crew, NULL);
    exportStructure(crew, noc, maint, nom, 2);
}

int exportStructure(Crew *crew, int noc, Maintenance *maint, int nom, int type)
{
    FILE *fo;
    if( type==1 ){ fo = fopen("cheapest.txt", "w"); fprintf(fo,"\t\tCHEAPEST SCHEDULE"); }
            else {  fo = fopen("fastest.txt", "w"); fprintf(fo,"\t\tFASTEST SCHEDULE"); }
    
    int i=0, j=0, counter=0, average=0; //Average of job hours to use in quickest schedule
    
    for(i=0; i<noc; i++)
    {
        if( crew[i].jobs[0]>0 )
            fprintf(fo,"\n----Crew%d\n", crew[i].id);
        for(j=1, counter=1; j<=crew[i].jobs[0]; j++, counter++)
        {
            fprintf(fo,"%2d- %d\n", counter, maint[crew[i].jobs[j]].aircraftId);
            average++;
        }
    }
    fclose(fo);
    
    if( ((1.0*average)/noc)-((average)/noc) > 0.5 )
        return average/noc+1;
    else
        return average/noc;
}

int checkCrew(int level, int num, Crew *crew)
{
    int cheapest=-999, i=0;
    
    for(i=0; i<num; i++)
        if( crew[i].level==level ) // find first crew whose level is "level"
        {
            cheapest=i;
            break;
        }
    
    if( cheapest==-999 ) // If there is no "level" level of crew, return -999
    {
        printf("There is no %d level crew.\n", level);
        exit(0);
    }
    
    for(i++; i<num; i++)
        if( crew[i].level==level && crew[i].cost <= crew[cheapest].cost )
        {
            if( crew[i].cost < crew[cheapest].cost)
                cheapest = i;
            else if( crew[cheapest].jobs[0]>crew[i].jobs[0] )
                cheapest = i;
        }
    
    return cheapest;
}

Crew * getCrewData(Crew *crew, int *i)
{
    int j=0;
    FILE *fo;
    *i=0;
    fo = fopen("crew.txt", "r");
    
    while( fscanf(fo, "%d %d %d", &crew[*i].id, &crew[*i].level, &crew[*i].cost) != EOF )
    {
        crew[*i].jobs = (int*) malloc(sizeof(int));
        crew[*i].jobs[0] = 0;
        j=*i-1;
        
        if( j>0 && crew[*i].level<crew[j].level ) // This is to keep crew datas sorted according to their level
        {
            while( j>=0 && crew[j+1].level<crew[j].level )
            {
                exchange(&crew[j].id, &crew[j+1].id);
                exchange(&crew[j].level, &crew[j+1].level);
                exchange(&crew[j].cost, &crew[j+1].cost);
                j--;
            }
            if( crew[j].cost>crew[j+1].cost )
            {
                exchange(&crew[j].id, &crew[j+1].id);
                exchange(&crew[j].level, &crew[j+1].level);
                exchange(&crew[j].cost, &crew[j+1].cost);
            }
        }
        
        crew = (Crew *) realloc(crew, (*i+2)*sizeof(Crew));
        (*i)++;
    }
    fclose(fo);
    return crew;
}

void exchange(int *a, int *b)
{
    int temp=*a;
    *a = *b;
    *b = temp;
}

Maintenance * getMaintenanceData(Maintenance *maint, int *i)
{
    *i=0;
    FILE *fo;
    fo = fopen("maintenance.txt", "r");
    
    while( fscanf(fo, "%d %d %d", &maint[*i].aircraftId, &maint[*i].level, &maint[*i].hours) != EOF )
    {
        maint = (Maintenance *) realloc(maint, (*i+2)*sizeof(Maintenance));
        (*i)++;
    }
    fclose(fo);
    return maint;
}

int * pop(int *a) // Deletes last item of a
{
    a = expandArray(a, a[0], 0); // a[0] keeps size of a-1
    a[0]--; // 
    return a;
}

int * append(int *a, int item) // Appends item to end of a
{
    a = expandArray(a, a[0]+1, 1);
    a[0]++; //first item keeps size of array so we increase it because of extendArray
    a[ a[0] ] =item; // a[ a[0] ] will be last item
    return a;
}

int * expandArray(int *a, int currentSize, int expandSize)
{
    return (int *) realloc(a, (currentSize+expandSize)*sizeof(int));
}

int crewHoursCalculator(Maintenance maint)
{
    return maint.hours;
}

/*
 * ALGORITHM EXPLANATIONS
 * 
 * CHEAPEST
 *    Basically I choose the cheapest crew for appropriate level of maintanence.
 *    Plus to that, if there are 2 cheapest crew, it seperate jobs to decrease time.
 * 
 * FASTEST
 *    I found rate of JOBS/CREW then started from first crew[0]. if the jobs which more than the rate,
 * be sent to next crew. It goes like that till the amount of crews' jobs equal/close themself.
 * 
 * 
int * expandArray1D(int *a, int currentSize, int expandSize)
{
    return (int *) realloc(a, (currentSize+expandSize)*sizeof(int));
}

int ** expandArray2D(int **a, int currentSize, int expandSize)
{
    return (int **) realloc(a, (currentSize+expandSize)*sizeof(int *));
} 
 */
