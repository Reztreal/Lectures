TEMPLATE = app
CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.c \
    Project_Lib/toolbox.c \
    Project_Lib/carwashsystem.c \
    Project_Lib/queue.c \
    Project_Lib/linkedlist.c \
    Project_Lib/debugging.c

HEADERS += \
    Project_Lib/toolbox.h \
    Project_Lib/carwashsystem.h \
    Project_Lib/queue.h \
    Project_Lib/linkedlist.h \
    Project_Lib/debugging.h
