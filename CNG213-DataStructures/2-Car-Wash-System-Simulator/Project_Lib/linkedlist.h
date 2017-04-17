// This is the linkedlist header file of
// washing machine system

#ifndef LINKEDLIST_H
#define LINKEDLIST_H

#include "queue.h"
#include "toolbox.h"

// Node of a washing machine
struct Machine
{
    int id;
    // if no car in the process,
    // carsInProcess->size will be zero
    CarQueue* carsInProcess;// First car is in the process
    CarQueue* carsFinished; // Washed cars
    struct Machine* next;   // Next machine
};

// Linked list of washing machines
struct MachineList
{
    struct Machine* machineH; // Head
    struct Machine* machineT; // Tail
    int size;
};

typedef struct Machine Machine;
typedef struct MachineList MachineList;

MachineList* create_machineList();
Machine*     create_machine();

void append_machine(MachineList* machineL);
int is_idle(Machine* machine);
Car* car_in_process(Machine* machine);
Machine* get_first_machine(MachineList* machineL);

#endif // LINKEDLIST_H
