#include "queue.h"

CarQueue* create_carQueue()
{
    CarQueue* carQ;
    carQ = (CarQueue*) sMalloc(sizeof(CarQueue));

    carQ->front = (Car*) sMalloc(sizeof(Car));
    carQ->front->next = NULL;
    carQ->rear = carQ->front; // front is dummy node
    carQ->queueNo = 0;
    carQ->size = 0;

    return carQ;
}

void enqueue(CarQueue* carQ, Car* car)
{
    if( !car )
    {
        printf("[!] There is no car to add.\n");
        return;
    }
    carQ->size++;
    car->next = NULL;
    car->carWashNumber = carQ->queueNo;
    carQ->rear->next = car;
    carQ->rear = carQ->rear->next;
    carQ->queueNo++;
}

Car* dequeue(CarQueue *carQ)
{
    if( is_queue_empty(carQ) )
    {
        printf("[!] Queue is already empty.\n");
        return NULL;
    }
    carQ->size--;

    Car* trash = first_car(carQ);

    // If dequeue rear
    if( is_queue_empty(carQ) )
    {
        carQ->rear = carQ->front;
        carQ->rear->next = NULL;
        carQ->size = 0;
        return trash;
    }

    carQ->front->next = carQ->front->next->next;
    return trash;
}

void destroy_car(Car* trash_car)
{
    if( !trash_car )
    {
        printf("[!] Car is already empty.\n");
        return;
    }

    // Free car node
    sFree(trash_car);
}

int is_queue_empty(CarQueue* carQ)
{
    return !(carQ->size);
}

Car* create_car()
{
    Car* new_car = (Car*) sMalloc(sizeof(Car));
    new_car->next = NULL;
    return new_car;
}

Car* first_car(CarQueue* carQ)
{
    return carQ->front->next;
}
