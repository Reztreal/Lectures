#ifndef QUEUE_H
#define QUEUE_H

#include <stdio.h>
#include <stdlib.h>
#include "toolbox.h"

// Specific car
struct Car
{
    int clockTime;
    int carWashNumber;
    int arrivalTime;
    int waitTime;
    int startTime;
    int serviceTime;
    int washingMachine;
    int queueSize;
    struct Car* next;
};

// Queue of cars
struct CarQueue
{
    struct Car* front;
    struct Car* rear;
    int size;
    int queueNo;
};

typedef struct CarQueue CarQueue;
typedef struct Car Car;

CarQueue* create_carQueue();
Car* create_car();

void enqueue(CarQueue* carQ, Car* car);
Car* dequeue(CarQueue* carQ);

void destroy_car(Car* trash);
int is_queue_empty(CarQueue* carQ);
Car* first_car(CarQueue* carQ);

#endif // QUEUE_H
