#include "linkedlist.h"

MachineList* create_machineList()
{
    // "ml" means machine list
    MachineList* machineL = (MachineList*) sMalloc(sizeof(MachineList));
    machineL->size = 0;

    // Head is dummy node
    machineL->machineH = (Machine*) sMalloc(sizeof(Machine));
    machineL->machineH->next = NULL;

    machineL->machineT = machineL->machineH;

    return machineL;
}

Machine* create_machine()
{
    Machine* new_machine = (Machine*) sMalloc(sizeof(Machine));
    new_machine->next = NULL;
    return new_machine;
}

void append_machine(MachineList* machineL)
{
    machineL->machineT->next = create_machine();
    machineL->machineT = machineL->machineT->next;
    machineL->machineT->id = machineL->size;

    machineL->machineT->carsFinished = create_carQueue();
    machineL->machineT->carsInProcess = create_carQueue();

    machineL->size++;
}

int is_idle(Machine* machine)
{
    return !(machine->carsInProcess->size);
}

Car* car_in_process(Machine* machine)
{
    if( is_idle(machine) )
        return NULL;

    return machine->carsInProcess->front->next;
}

Machine* get_first_machine(MachineList* machineL)
{
    return machineL->machineH->next;
}
