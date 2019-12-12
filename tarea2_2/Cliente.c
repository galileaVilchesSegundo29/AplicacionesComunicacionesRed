#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include "MensajeError.h"
#define TAMBUFER 3000

int main(int argc, char** argv) {
        if (argc < 3|| argc >4)
              mensajeFinalError(
            "Uso: EcoTCPCliente <Dirección del servidor> <Palabra de eco> [<Puerto>]");
        char *servIP = argv[1];
        char *cadenaEco = argv[2];
        //Argumento opcional, se agrega por defecto
        in_port_t puerto = (argc == 4) ? atoi(argv[3]) : 7;
        //Crea el socket del cliente TCP
        int s = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
        if(s < 0){
                mensajeFinalError("Error de apertura del conector");
        }
        //Creamos la dirección del servidor de entrada
        struct sockaddr_in dirServ;
        memset(&dirServ,0,sizeof(dirServ));
        dirServ.sin_family = AF_INET;
        int valRet = inet_pton(AF_INET,servIP,&dirServ.sin_addr.s_addr);
        if(valRet == 0)
                mensajeFinalError(
		"Dirección del servidor erronea");
        else if(valRet < 0)
                mensajeFinalError("Error en el inet_pton()");
        dirServ.sin_port = htons(puerto);
        //Establecemos la comunicación con el servidor de eco
        if(connect(s, (struct sockaddr*) &dirServ,sizeof(dirServ))<0)
                mensajeFinalError("Error en la conexión");        
        //Recibimos de vuelta la cadena desde el servidor        
        
	int dato1;
        long dato2;
        signed char dato3;
        float dato31;
        char dato4[TAMBUFER];

        ssize_t numBytes;
        
        //Recibe int
        numBytes = recv(s,&dato1,sizeof(dato1), 0);

        if(numBytes<0)
            mensajeFinalError("Recepción fallida");
        else if(numBytes==0)
            mensajeFinalError("Conexión cerrada prematuramente");
            
        printf("Int recibido: %d\n",dato1);

        //Recibe long
        
        numBytes = recv(s,&dato2,sizeof(dato2), 0);

         if(numBytes<0)
                mensajeFinalError("Recepción fallida");
        else if(numBytes==0)
                mensajeFinalError(
                      "Conexión cerrada prematuramente");
            
        printf("Long recibido: %ld\n",dato2);

        //Recibe float        
        numBytes = recv(s,&dato31,sizeof(dato31), 0);
         if(numBytes<0)
                mensajeFinalError("Recepción fallida");
        else if(numBytes==0)
                mensajeFinalError(
                      "Conexión cerrada prematuramente");
 
            
        printf("Float recibido: %f\n",dato31);

        //Recibe char*
        
        numBytes = recv(s,dato4,TAMBUFER, 0);

         if(numBytes<0)
                mensajeFinalError("Recepción fallida");
        else if(numBytes==0)
                mensajeFinalError(
                      "Conexión cerrada prematuramente");
            
        printf("Mensaje recibido: %s\n",dato4);
        
        close(s);
        return 0;
}
