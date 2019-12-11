/*SOCKET CLIENTE.
EJERCICIO 1 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.net.*;
import java.io.*;

public class CEcoTCPBV2{
    public static void main(String[] args) {
        try{
            //Se declara un BufferedReader para poder leer datos del teclado.
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escriba la direccion del servidor");
            //Se leen datos del teclado.
            String host = br1.readLine();
            System.out.println("\n\nEscriba el puerto: ");
            //Se leen datos del teclado.
            int pto = Integer.parseInt(br1.readLine());

            //Creamos el socket y nos conectamos 
            Socket cl = new Socket(host, pto);

            //Se declara un BufferedReader para poder leer datos del servidor.
            BufferedReader br2 = new BufferedReader (new InputStreamReader(cl.getInputStream()));
            //Leemos los datos recibidos
            String mensaje = br2.readLine();
            System.out.println("\n\nRecibimos un mensaje desde el servidor");
            System.out.println("\nMensaje: " + mensaje);

            //Se escribe la respuesta por parte del cliente para el servidor.
            System.out.println("\n\nEscriba mensaje de regreso: ");
            //Se leen datos del teclado.
            String mensajeServidor = br1.readLine();
            //Se declara un PrintWriter para poder imprimir los datos en el lado del servidor.
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
            //Se envía el mensaje y se limpia el PrintWriter
            pw.println(mensajeServidor);    pw.flush();

            //Cerramos flujos y socket
            br1.close();    br2.close();    cl.close();
        }catch(Exception e){    //Manejo de excepciones
            e.printStackTrace();
        }
    }
}