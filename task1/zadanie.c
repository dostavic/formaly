#include <stdio.h>
#include <string.h>
#include "zadanie.h"

int state = 0;

int isAccepted(char* in_str){
    state = 0;
    for(int i = 0; i < strlen(in_str); i++){
        if(state == 0)
            start(in_str[i]);
        else if(state == 1)
            q1(in_str[i]);
        else if(state == 2)
            q2(in_str[i]);
        else if(state == 3)
            q3(in_str[i]);
        else if(state == 4)
            q4(in_str[i]);
    }

    if(state == 1 || state == 4)
        return ACCEPT;
    else
        return NON_ACCEPT;
}

void start(char c){
    if(c == 'a')
        state = 1;
    else if(c == 'b')
        state = 2;
    else
        state = UNDEF;
}

void q1(char c){
    state = UNDEF;
}

void q2(char c){
    if(c == 'a')
        state = 3;
    else if(c == 'b')
        state = 3;
    else if(c == 'c')
        state = 2;
    else
        state = UNDEF;
}

void q3(char c){
    if(c == 'c')
        state = 4;
    else
        state = UNDEF;
}

void q4(char c){
    if(c == 'a')
        state = 1;
    else
        state = UNDEF;
}