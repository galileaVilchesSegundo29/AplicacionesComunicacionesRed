/*MENSAJE ERROR.
ECO EN C - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
SEPTIEMBRE 2019.*/
#include <stdio.h>
#include <stdlib.h>

void  mensajeFinalError(const char *mensaje){
    fputs(mensaje,stderr);
    fputc('\n',stderr);
    exit(1);
}
void mensajeFinalSistema(const char *mensaje){
    perror(mensaje);
    exit(1);
}