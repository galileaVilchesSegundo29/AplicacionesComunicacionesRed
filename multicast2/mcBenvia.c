/*ENVIA.
JAVA-C - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/

#include <sys/types.h>  /* Para definición de tipos */
#include <sys/socket.h> /* Para llamadas a las APIs de socket */
#include <netinet/in.h> /* Estructuras de datos */
#include <arpa/inet.h>  /* Para sockaddr_in */
#include <stdio.h>      /* Para printf() y fprintf() */
#include <stdlib.h>     /* Para atoi() */
#include <string.h>     /* Para strlen() */
#include <unistd.h>     /* Para close() */

#define MAX_LEN  1024   /* Tamaño máximo de lectura */
#define MIN_PORT 1024   /* Primer puerto asignable*/
#define MAX_PORT 65535  /* Último puerto asignable */

int main(int argc, char *argv[]) {
	
	int sock;                     /* Descriptor de socket */
	int flag_on = 1;              /* Banderas de opción del socket */
	struct sockaddr_in mc_addr;   /* Estructura de la dirección del socket */
	char recv_str[MAX_LEN+1];     /* Bufer de lectura*/
	int recv_len;                 /* Longitud de la cadena recibida */
	struct ip_mreq mc_req;        /* Estructura de la solicitud multicast */
	char* mc_addr_str;            /* Dirección IP multicast */
	unsigned short mc_port;       /* Puerto multicast */
	struct sockaddr_in from_addr; /* Paquete origen */
	unsigned int from_len;        /* Longitd dirección fuente */
	
	/* Validación de parámetros */
	if (argc != 3) {
		fprintf(stderr, 
		"Uso: %s <Multicast IP> <Multicast Puerto>\n", 
		argv[0]);
		exit(1);
	}
	
	mc_addr_str = argv[1];      /* arg 1: dirección ip de multicast*/
	mc_port = atoi(argv[2]);    /* arg 2: número de puerto multicast */
	
	/* Validar el número de puerto */
	if ((mc_port < MIN_PORT) || (mc_port > MAX_PORT)) {
		fprintf(stderr, "Número de puerto inválido %d.\n",mc_port);
		fprintf(stderr, "El rángo válido esta entre %d y %d.\n",MIN_PORT, MAX_PORT);
		exit(1);
	}
	
	/* Se crea un socket para conectarse a un canal multicast */
	if ((sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0) {
		perror("Error en socket().");
		exit(1);
	}
	
	/* Permite la reutilización del socket */
	if ((setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &flag_on,sizeof(flag_on))) < 0) {
		perror("Error en setsockopt()");
		exit(1);
	}
	
	/* Se construye una estructura de dirección sock_addr */
	memset(&mc_addr, 0, sizeof(mc_addr));
	mc_addr.sin_family      = AF_INET;
	mc_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	mc_addr.sin_port        = htons(mc_port);
	
	/* Liga la dirección con el socket */
	if ((bind(sock, (struct sockaddr *) &mc_addr, 
		sizeof(mc_addr))) < 0) {
		perror("Error en bind()");
		exit(1);
	}
	
	/* Estructura para unirse al grupo */
	mc_req.imr_multiaddr.s_addr = inet_addr(mc_addr_str);
	mc_req.imr_interface.s_addr = htonl(INADDR_ANY);
	
	/* Se envia el mensaje de unirse al grupo via setsockopt */
	if ((setsockopt(sock, IPPROTO_IP, IP_ADD_MEMBERSHIP, (void*) &mc_req, sizeof(mc_req))) < 0) {
		perror("Error en setsockopt()");
		exit(1);
	}
	
	for (;;) { // lazo infinito
		/* Se limpia el buffer y la estructura de lectura */
		memset(recv_str, 0, sizeof(recv_str));
		from_len = sizeof(from_addr);
		memset(&from_addr, 0, from_len);
		
		/* Bloqueo para la recepción de paquetes */
		if ((recv_len = recvfrom(sock, recv_str, MAX_LEN, 0, (struct sockaddr*)&from_addr, &from_len)) < 0) {
			perror("Error en recvfrom()");
			exit(1);
		}
		
		/* Inprimimos lo que recibimos */
		printf("\nSe recibieron %d bytes desde %s: ", recv_len, inet_ntoa(from_addr.sin_addr));
		printf("%s", recv_str);
	}
	/* Envia un mensaje para dejar el grupo via setsockopt */
	if ((setsockopt(sock, IPPROTO_IP, IP_DROP_MEMBERSHIP, (void*) &mc_req, sizeof(mc_req))) < 0) {
		perror("Error en setsockopt()");
		exit(1);
	}
	close(sock);  
}
