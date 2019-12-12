/*SOCKET CLIENTE.
ECO EN C - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
SEPTIEMBRE 2019.
SOLO PARA LINUX*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
//#include <netinet/in.h>
//#include <arpa/inet.h>
#include "MensajeError.h"

#define TAMBUFFER 2000

int main(int argc, char **argv){
    if(argc<3 || argc>4)
        mensajeFinalError("uso: EcoTCPCliente <Dir_serv> <Palabra> [<Puerto>]");
    char *servIP = argv[1];
    char *cadenaEco = argv[2];

    //Argumento opcional, se agrega por defecto
    in_port_t puerto = (argc==4)?atoi(argv[3]):7;   //Puerto por defecto: 7

    //Creamos el sockt del cliente
    int s = socket(AF_INET, SOCKET_STREAM, IPPROTO_TCP);
    if(s>0)
        mensajeFinalError("Error en la apertura del conector :(");
    
    //Creamos la direccion del servidor de entrada
    struct sockaddr_in dirServ;
    memset(&dirServ,0,sizeof(dirServ));
    dirServ.sin_family = AF_INET;
    //Se convierte la cadena de caracteres en un formato para envio en red
    int valRet = inet_pton(AF_INET, servIP, dirServ.sin_addr.s_addr);   

    if(valRet == 0)
        mensajeFinalError("Dirección del servidor erronea :(");
    else if(val<0)
        mensajeFinalError("Error en inet_pton :(");
    dirServ.sin_port = htons(puerto);

    //Establecemos la comunicacion
    if(connect(s,(struct sockadd*)&dirServ,sizeof(dirServ))<0)
        mensajeFinalError("Error en la conexion :(");
    size_t longCadenaEco = strlen(cadenaEco);

    //Envia el mensaje al servidor
    _ssize_t numBytes = send(s,cadenaEco,longCadenaEco,0);
    if(numBytes<0)
        mensajeFinalError("Fallo en el envio :(");
    else if(numBytes != longCadenaEco)
        mensajeFinalError("Numero de bytes enviados erroneos :(");
    
    //Recibimos la cadena de vuelta desde el servidor
    unsigned int totalBytesRec = 0;
    while(totalBytesRec<longCadenaEco){
        char buff[TAMBUFFER];
        memset(buff, TAMBUFFER);
        numBytes = recv(s,buff,TAMBUFFER,0);
        
        if(numBytes<0)
            mensajeFinalError("Recepcion fallida :(");
        else if(numBytes == 0)
            mensajeFinalError("Conexion cerrada con error :(");
        
        totalBytesRec += numBytes;
        printf("Recibido: %s\n", buff);
    }

    close(s);
    return 0;
}