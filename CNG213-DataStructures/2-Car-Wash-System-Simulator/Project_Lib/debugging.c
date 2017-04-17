#include "debugging.h"

int dbg_unit_test0(int numOfMachines, int numOfCars)
{
    MachineList* ml = create_machineList();

    dbg_fill_machineList(ml, numOfMachines);
    dbg_print_machineList(ml);

    dbg_fill_machinesQueues(ml, numOfCars);

    Car* car = create_car();
    enqueue(ml->machineH->next->carsInProcess, car);

    car = create_car();
    enqueue(ml->machineT->carsInProcess, car);
    car = create_car();
    enqueue(ml->machineH->next->carsInProcess, car);
    //washing_finished(ml->machineH->next);
    dbg_print_machinesQueues(ml);

    printf("Shortest queue size      : %d\n", shortest_car_queue(ml)->carsInProcess->size);
    printf("Shortest queue machineId : %d\n", shortest_car_queue(ml)->id );

    return 0;
}

void dbg_fill_machinesQueues(MachineList* ml, int numOfCars)
{
    Machine* machine = ml->machineH->next;
    int i=0;
    while(machine)
    {
        printf("----Filling machine id : %d\n", machine->id);
        i++;
        dbg_fill_carQueue(machine->carsInProcess, numOfCars);
        machine = machine->next;
    }
    printf("Number of machines filled : %d\n", i);
    printf("\n");
}

void dbg_fill_machineList(MachineList* ml, int numOfMachines)
{
    int i=0;
    for(i=0; i<numOfMachines; i++)
        append_machine(ml);
    printf("Number of machines added : %d\n", i);
    printf("\n");
}

void dbg_fill_carQueue(CarQueue* q, int numOfCars)
{
    int i=0;
    Car* new_car=NULL;
    for(i=0; i<numOfCars; i++)
    {
        new_car = create_car();
        enqueue(q, new_car);
    }
    printf("Number of cars added : %d\n", i);
    printf("\n");
}

int dbg_verify_carQueue(CarQueue* q, int verbose)
{
    if( is_queue_empty(q) )
    {
        printf("[!] Empty Queue.\n");
        return 1;
    }
    Car* car = q->front;
    int i=1;

    if( !verbose )
    {
        car = car->next;
        while( car->next ) { car = car->next; i++; }
        if( q->rear == car && i==q->size )
            return 1;
        return 0;
    }

    printf("Verification for CarQueue is starting...\n");
    printf("Amount of Car : %d\n", q->size);
    printf("Rear          : %p\n", q->rear);
    printf("Front(Dummy)  : %p\n", q->front);
    printf("Data Starts   : %p\n\n", q->front->next);
    car = car->next;
    while( car->next )
    {
        printf("Data%d : %p\n", i++, car);
        car = car->next;
    }
    printf("Data%d : %p\n", i, car);
    if( q->rear == car && i==q->size )
    {
        printf("[REPORT] Verificaion is successfull.\n");
        printf("\n");
        return 1;
    }
    printf("[REPORT] Verification has failed. Please debug your code.\n");
    printf("\n");
    return 0;
}

void dbg_print_carQueue(CarQueue* q)
{
    Car* car = q->front->next;
    int i=0;
    printf("QueueNo : %d\nPrinting : car->carWashNumber\n", q->queueNo);
    printf("FrontDummy->");
    while(car)
    {
        i++;
        printf("%d->", car->carWashNumber);
        car = car->next;
    }
    printf("NULLrear\n");
    printf("Number of cars : %d\n", i);
    printf("\n");
}

void dbg_print_machineList(MachineList* ml)
{

    Machine* machine = ml->machineH->next;
    int i=0;
    printf("Printing : machine->id\n");
    printf("HeadDummy->");
    while(machine)
    {
        i++;
        printf("%d->", machine->id);
        machine = machine->next;
    }
    printf("NULLtail\n");
    printf("Number of machines : %d\n", i);
    printf("\n");
}

void dbg_print_machineQueues(Machine* m)
{
    printf("--------Printing machine id : %d\n", m->id);
    printf("----carsInProcess\n");
    dbg_print_carQueue(m->carsInProcess);
    printf("----carsFinished\n");
    dbg_print_carQueue(m->carsFinished);
}

void dbg_print_machinesQueues(MachineList* ml)
{
    Machine* machine = ml->machineH->next;
    while(machine)
    {
        printf("--------Printing machine id : %d\n", machine->id);
        printf("----carsInProcess\n");
        dbg_print_carQueue(machine->carsInProcess);
        printf("----carsFinished\n");
        dbg_print_carQueue(machine->carsFinished);
        machine = machine->next;
    }
    printf("\n");
}
