#ifndef CARWASHSYSTEM_H
#define CARWASHSYSTEM_H

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "linkedlist.h"
#include "queue.h"

#define TRUE  1
#define FALSE 0

#define THRESHOLD 5

struct Statistics
{
    int totalCarsWashed;
    int totalMachines;
    int totalIdleTime;
    int totalWaitTime;
    int totalServiceTime;
    int maxQueueSize;
    float avgWaitTime;
    float avgServiceTime;
};
typedef struct Statistics Statistics;

// RUN SIMULATOR
void run_simulator(int totalTimeHours, int maxWashTimeMin, int numOfMachines);
    void add_machines_to(MachineList* machineL, int numOfMachines);
    int is_shift_finish(int clock, int shiftTimeHours);
    int is_queues_empty(MachineList* machineL);

// CAR TO WASH SIMULATOR
void car_to_wash_simulator(MachineList* machineL, int clock);
    int is_car_arrived();
    Machine* shortest_car_queue(MachineList* machineL);
    Machine* select_machine_randomly(Machine* first, Machine* second);
    void add_new_car_to(Machine* machine, Car* newCar);

// START WASHING NEXT CAR
void start_washing_next_car(MachineList* machineL, int clock);
    int is_new_car(Car* car);

// PROCESS CAR WASH
int process_car_wash(MachineList* machineL, int clock, int maxWashTime);
    int is_car_process_finished();
    int is_exceed_max_wash_time(Car* car, int clock, int maxWashTime);
    void finish_washing_car(Machine* machine);
    Car* delete_processing_car_from(Machine *machine);
    void add_finished_car_to(Machine *machine, Car* finishedCar);

// FINALISE REPORT SIMULATOR
void finalise_report_simulator(Statistics *stats, MachineList* machineL);
    void find_statistics(Statistics* stats, MachineList* machineL);
    int max_queue_size(MachineList* machineL);
    int max_queue_size_queue(Machine* machine);
    int total_service_time(MachineList* machineL);
    int total_service_time_queue(Machine* machine);
    int total_wait_time(MachineList* machineL);
    int total_wait_time_queue(Machine* machine);
    int total_car_washed(MachineList* machineL);

// PRINT FUNCTIONS
void print_data(Car* car);
void print_data_title();

#endif // CARWASHSYSTEM_H
