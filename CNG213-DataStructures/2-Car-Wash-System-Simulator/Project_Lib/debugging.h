#ifndef DEBUGGING_H
#define DENUGGING_H

#include "linkedlist.h"
#include "queue.h"
#include "toolbox.h"
#include "carwashsystem.h"

int dbg_test1(int numOfMachines, int numOfCars);

void dbg_fill_machinesQueues(MachineList* ml, int numOfCars);
void dbg_fill_machineList(MachineList* ml, int numOfMachines);
void dbg_fill_carQueue(CarQueue* q, int numOfCars);

int  dbg_verify_carQueue(CarQueue* q, int verbose);
void dbg_print_carQueue(CarQueue* q);
void dbg_print_machineList(MachineList* ml);
void dbg_print_machineQueues(Machine* m);
void dbg_print_machinesQueues(MachineList* ml);

#endif // UNITTEST_H
