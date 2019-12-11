/*SOCKET SERVIDOR.
TAREA 1.2 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.net.*;
import java.io.*;
import java.util.*;

public class ServerTarea{
    public static void main(String[] args) {
       try{
            //Se crea el socket servidor.
            ServerSocket s = new ServerSocket(1200);
            System.out.println("Esperando cliente...");

            //Iniciamos ciclo infinito.
            for(;;){
               //Tenemos un bloqueo y se hace la conexión entre cliente y servidor.
                Socket cl = s.accept();
                System.out.println("\n\nConexion establecida desde" + cl.getInetAddress()+ ":" + cl.getPort());

                DataInputStream dis = new DataInputStream(cl.getInputStream());
                long boleta = dis.readLong();
                System.out.println("\n\nBoleta: "+boleta);
                String name = dis.readUTF();
                System.out.println("\n\nNombre: "+name);
                int age = dis.readInt();
                System.out.println("\n\nEdad: "+age);

                DataOutputStream das = new DataOutputStream(cl.getOutputStream());
                Random r = new Random();
                long size = (long)(r.nextInt(100));
                // long size = 20;
                System.out.println("\n\nTamanio: " + size);
                das.writeLong(size);    das.flush();
                byte[] b = new byte[(int)(size)];
                das.write(b);           das.flush();
                double value = 6.97542;
                das.writeDouble((int)value);    das.flush();
                System.out.println("\n\nValor enviado: " + value);
                double valueBack = dis.readDouble();
                System.out.println("\n\nValor recibido: " + valueBack);
                boolean result = true;
                System.out.println("\n\nResultado: " + result);
                das.writeBoolean(result);   das.flush();
                
                System.out.println("\n\n------------------------------------------");
                //Se limpia el flujo
                das.close();    dis.close();    cl.close();
            }
        }catch(Exception e){    //Manejo de excepciones
            e.printStackTrace();
        }
    }
}
