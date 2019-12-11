/*SOCKET CLIENTE.
EJEMPLO ENVIO ARCHIVOS - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;

public class CArchTCPB{
    public static void main(String[] args){
        try{
            //Se declara un BufferedReader para poder leer datos del teclado.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escriba la direccion del servidor");
            //Se leen datos del teclado.
            String host = br.readLine();
            System.out.println("\n\nEscriba el puerto: ");
            //Se leen datos del teclado.
            int pto = Integer.parseInt(br.readLine());

            //Creamos el socket y nos conectamos 
            Socket cl = new Socket(host, pto);


            JFileChooser jf = new JFileChooser();
            int r = jf.showOpenDialog(null);
            if(r==JFileChooser.APPROVE_OPTION){//Creamos el socket y nos conectamos 
                File f = jf.getSelectedFile();  //Manejador.
                String archivo = f.getAbsolutePath();  //Dirección.
                String nombre = f.getName();   //Nombre.
                long tam = f.length();  //Tamanio.
                
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                DataInputStream dis = new DataInputStream(new FileInputStream(archivo));
                dos.writeUTF(nombre);   dos.flush();
                dos.writeLong(tam); dos.flush();
                byte[] b = new byte[1024];
                long enviados = 0;
                int porcentaje, n;
                while(enviados<tam){
                    n = dis.read();
                    dos.write(b,0,n);
                    dos.flush();
                    enviados = enviados + n;
                    porcentaje = (int)(enviados*100/tam);
                    System.out.println("Enviado: " + porcentaje + "%\r");
                }
                System.out.println("\n\nArchivo enviado ");
                dos.close();    dis.close();    cl.close();
            }
        }catch(Exception e){    //Manejo de excepciones
            e.printStackTrace();
        }
    }
}