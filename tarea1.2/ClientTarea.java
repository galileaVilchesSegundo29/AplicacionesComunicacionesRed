/*SOCKET CLIENTE.
TAREA 1.2 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.net.*;
import java.io.*;

public class ClientTarea{
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

            DataOutputStream dis = new DataOutputStream(cl.getOutputStream());
            long boleta = 2014101780;
            System.out.println("\n\nBoleta: " + boleta);
            dis.writeLong(boleta);   dis.flush();
            String name = "Vilches Segundo Galilea Yanely";
            System.out.println("\n\nNombre: " + name);
            dis.writeUTF(name);     dis.flush();
            int age = 20;
            System.out.println("\n\nEdad: " + age);
            dis.writeInt(age);     dis.flush();

            DataInputStream das = new DataInputStream(cl.getInputStream());
            long size = das.readLong();
            System.out.println("\n\nTamanio: " + size);
            byte[] b = new byte[(int)(size)];
            das.read(b);   
            double value = das.readDouble();
            System.out.println("\n\nValor: " + value);
            dis.writeDouble((int)value);
            boolean result = das.readBoolean();
            System.out.println("\n\nResultado: " + result);

            dis.close();    das.close();  br1.close();  cl.close();
        }catch(Exception e){    //Manejo de excepciones
            e.printStackTrace();
        }
    }
}