TEMPLATE = app
CONFIG += console
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.c \
    Project_Lib/linkedlist.c \
    Project_Lib/avltree.c \
    Project_Lib/indexingwebsites.c \
    Project_Lib/toolbox.c

HEADERS += \
    Project_Lib/linkedlist.h \
    Project_Lib/avltree.h \
    Project_Lib/indexingwebsites.h \
    Project_Lib/toolbox.h
