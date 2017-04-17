#include "carwashsystem.h"
#include "debugging.h"

// BEG RESTRICTED FUNCS_________________________________________________
void run_simulator(int shiftTimeHours, int maxWashTime, int numOfMachines)
{
    MachineList* machineList = create_machineList();
    Statistics stats;
    int clock=1;

    srand(time(NULL));

    stats.totalIdleTime = 0;
    
    add_machines_to(machineList, numOfMachines);

    // Prints title of the table of finished car statistics
    print_data_title();

    // Main loop runs while shift is not finished or there are
    // waiting cars to be washed
    for(clock=1;
        !is_shift_finish(clock, shiftTimeHours) || !is_queues_empty(machineList);
        clock++)
    {
        // if shift is finished, don't accept new cars
        if( !is_shift_finish(clock, shiftTimeHours) )
            car_to_wash_simulator(machineList, clock);

        start_washing_next_car(machineList, clock);
        stats.totalIdleTime += process_car_wash(machineList, clock, maxWashTime);

    }
    finalise_report_simulator(&stats, machineList);
}

void car_to_wash_simulator(MachineList* machineL, int clock)
{
    static int isArrivedLastMin = TRUE; // car must arrive every 2 minuthes
    if( is_car_arrived() || !isArrivedLastMin )
    {
        isArrivedLastMin = TRUE;

        Machine* machine = shortest_car_queue(machineL);
        Car* newCar = create_car();
        newCar->arrivalTime    = clock;
        newCar->washingMachine = machine->id;
        newCar->startTime = -1; // means not started to process

        add_new_car_to(machine, newCar);

        return;
    }
    isArrivedLastMin = FALSE;
}

void start_washing_next_car(MachineList* machineL, int clock)
{
    Machine* machine = get_first_machine(machineL);

    while(machine)
    {
        if( is_idle(machine) )
        {
            machine = machine->next;
            continue;
        }

        Car* carInProcess = car_in_process(machine);
        if( is_new_car(carInProcess) )
            carInProcess->startTime = clock;

        machine = machine->next;
    }
}

// Return 1 if all machines idle else return 0
int process_car_wash(MachineList* machineL, int clock, int maxWashTime)
{
    Machine* machine = get_first_machine(machineL);
    Car* processing_car;
    int numOfIdle = 0; // if there is any non-idle machine, increase this

    while( machine )
    {
        if( !is_idle(machine) )
        {
            numOfIdle++;
            processing_car = car_in_process(machine);
            if( is_car_process_finished() || !is_exceed_max_wash_time(processing_car, clock, maxWashTime) )
            {
                // [BEG] Statistics
                processing_car-> clockTime  = clock;
                processing_car-> waitTime   = processing_car->startTime - processing_car->arrivalTime;
                processing_car-> serviceTime= clock                     - processing_car->startTime + 1;
                processing_car-> queueSize  = machine->carsInProcess->size;
                // [END] Statistics

                // Print statistics of finished car
                print_data(processing_car);

                // Move finished car to carFinished queue
                finish_washing_car(machine);
            }
        }
        machine = machine->next;
    }
    return numOfIdle==0;
}

void finalise_report_simulator(Statistics *stats, MachineList* machineL)
{
    find_statistics(stats, machineL);
    printf("\n# Final Statistics\n");
    //        1      2      3      4      5      6      7      8
    printf( "%s%d \n%s%d \n%s%d \n%s%d \n%s%d \n%s%d \n%s%.3f \n%s%.3f \n",
            /*1*/ " Total cars washed      : ", stats->totalCarsWashed,
            /*2*/ " Total washing machines : ", stats->totalMachines,
            /*3*/ " Total idle time        : ", stats->totalIdleTime,
            /*4*/ " Total wait time        : ", stats->totalWaitTime,
            /*5*/ " Total service time     : ", stats->totalServiceTime,
            /*6*/ " Maximum queue size     : ", stats->maxQueueSize,
            /*7*/ " Average wait time      : ", stats->avgWaitTime,
            /*8*/ " Average service time   : ", stats->avgServiceTime
    );
}
// _________________________________________________ENG RESTRICTED FUNCS
// BEG MY FUNCS_________________________________________________________
void add_machines_to(MachineList* machineL, int numOfMachines)
{
    int i=0;
    for(i=0; i<numOfMachines; i++)
        append_machine(machineL);
}

int is_shift_finish(int clock, int shiftTimeHours)
{
    return !(clock <= shiftTimeHours*60);
}

int is_queues_empty(MachineList* machineL)
{
    Machine* machine = get_first_machine(machineL);

    while(machine)
    {
        if( !is_queue_empty(machine->carsInProcess) )
            return FALSE;
        machine = machine->next;
    }
    return TRUE;
}
// ###############################
int is_car_arrived()
{
    if( rand()%10<THRESHOLD )
        return TRUE;
    return FALSE;
}

Machine* shortest_car_queue(MachineList* machineL)
{
    Machine* current = get_first_machine(machineL);
    Machine* shortest = current;

    current = current->next;
    while( current )
    {
        if(shortest->carsInProcess->size > current->carsInProcess->size)
            shortest = current;
        else if(shortest->carsInProcess->size == current->carsInProcess->size)
            shortest = select_machine_randomly(shortest, current);
        current = current->next;
    }
    return shortest;
}

Machine* select_machine_randomly(Machine* first, Machine* second)
{
    if( rand()%2 )
        return first;
    return second;
}

void add_new_car_to(Machine* machine, Car* newCar)
{
    enqueue(machine->carsInProcess, newCar);
}
// ###############################
int is_car_process_finished()
{
    if( rand()%10<THRESHOLD )
        return TRUE;
    return FALSE;
}

int is_exceed_max_wash_time(Car* car, int clock, int maxWashTime)
{
    return !((clock-car->startTime+1)>=maxWashTime);
}

void finish_washing_car(Machine* machine)
{
    Car* finishedCar = delete_processing_car_from(machine);
    add_finished_car_to(machine, finishedCar);
}

Car* delete_processing_car_from(Machine *machine)
{
    return dequeue(machine->carsInProcess);
}

void add_finished_car_to(Machine *machine, Car* finishedCar)
{
    enqueue(machine->carsFinished, finishedCar);
}
// ###############################
int is_new_car(Car* car)
{
    return car->startTime==-1; // means not started to process
}
// ###############################
void find_statistics(Statistics* stats, MachineList* machineL)
{
    stats->totalCarsWashed  = total_car_washed(machineL);
    stats->totalMachines    = machineL->size;
    stats->totalIdleTime    = stats->totalIdleTime;
    stats->totalWaitTime    = total_wait_time(machineL);
    stats->totalServiceTime = total_service_time(machineL);
    stats->maxQueueSize     = max_queue_size(machineL);
    stats->avgWaitTime      = (float) stats->totalWaitTime/stats->totalCarsWashed;
    stats->avgServiceTime   = (float) stats->totalServiceTime/stats->totalCarsWashed;
}

int max_queue_size(MachineList* machineL)
{
    int max = -1;
    int current = 0;
    Machine* machine = get_first_machine(machineL);

    while(machine)
    {
        current = max_queue_size_queue(machine);
        if(max < current)
            max = current;

        machine = machine->next;
    }
    return max;
}
int max_queue_size_queue(Machine* machine)
{
    int max = -1;
    Car* car = first_car(machine->carsFinished);
    while(car)
    {
        if(max < car->queueSize)
            max = car->queueSize;
        car = car->next;
    }
    return max;
}
int total_service_time(MachineList* machineL)
{
    int totalTime = 0;
    Machine* machine = get_first_machine(machineL);

    while(machine)
    {
        totalTime += total_service_time_queue(machine);
        machine = machine->next;
    }
    return totalTime;
}
int total_service_time_queue(Machine* machine)
{
    int totalTime = 0;
    Car* car = first_car(machine->carsFinished);
    while(car)
    {
        totalTime += car->serviceTime;
        car = car->next;
    }
    return totalTime;
}
int total_wait_time(MachineList* machineL)
{
    int totalTime = 0;
    Machine* machine = get_first_machine(machineL);

    while(machine)
    {
        totalTime += total_wait_time_queue(machine);
        machine = machine->next;
    }
    return totalTime;
}
int total_wait_time_queue(Machine* machine)
{
    int totalTime = 0;
    Car* car = first_car(machine->carsFinished);
    while(car)
    {
        totalTime += car->waitTime;
        car = car->next;
    }
    return totalTime;
}
int total_car_washed(MachineList* machineL)
{
    int totalCar = 0;
    Machine* machine = get_first_machine(machineL);

    while(machine)
    {
        totalCar += machine->carsFinished->size;
        machine = machine->next;
    }
    return totalCar;
}
// _________________________________________________________END MY FUNCS
// BEG PRINT FUNCS______________________________________________________
void print_data(Car* car)
{
    //       1  2   3    4    5   6   7    8
    printf( "%4d %9d %13d %11d %8d %9d %11d %14d\n",
            /*1*/ car->clockTime,
            /*2*/ car->carWashNumber,
            /*3*/ car->arrivalTime,
            /*4*/ car->waitTime,
            /*5*/ car->startTime,
            /*6*/ car->serviceTime,
            /*7*/ car->washingMachine,
            /*8*/ car->queueSize
    );
}
void print_data_title()
{
    printf( /*1*/ "ClockTime "      // 04 offset
            /*2*/ "CarWashNumber "  // 09 offset
            /*3*/ "ArrivalTime "    // 13 offset
            /*4*/ "WaitTime "       // 11 offset
            /*5*/ "StartTime "      // 08 offset
            /*6*/ "ServiceTime "    // 09 offset
            /*7*/ "WashingMachine " // 11 offset
            /*8*/ "QueueSize\n"     // 14 offset
    );
}
