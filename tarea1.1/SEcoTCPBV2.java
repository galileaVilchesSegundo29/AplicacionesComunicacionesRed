/*SOCKET SERVIDOR.
EJERCICIO 1 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.net.*;
import java.io.*;

public class SEcoTCPBV2{
    public static void main(String[] args) {
       try{
            //Se crea el socket servidor.
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Esperando cliente...");

            //Iniciamos ciclo infinito.
            for(;;){
               //Tenemos un bloqueo y se hace la conexión entre cliente y servidor.
                Socket cl = s.accept();
                System.out.println("\n\nConexion establecida desde" + cl.getInetAddress()+ ":" + cl.getPort());

                //Se escribe el mensaje que se desea enviar.
                String mensaje = " Hola mundo";
                //Se declara un PrintWriter para poder imprimir los datos en el lado del cliente.
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                //Se envía el mensaje y se limpia el PrintWriter
                pw.println(mensaje);    pw.flush();

                //Se declara un BufferedReader para poder leer datos del cliente.
                BufferedReader br2 = new BufferedReader (new InputStreamReader(cl.getInputStream()));
                //Leemos los datos recibidos
                String mensajeCliente = br2.readLine();
                System.out.println("\n\nRecibimos un mensaje desde el cliente");
                System.out.println("\nMensaje: " + mensajeCliente);

                //Se limpia el flujo
                pw.flush(); pw.close(); cl.close();
            }
        }catch(Exception e){    //Manejo de excepciones
            e.printStackTrace();
        }
    }
}
