/*SOCKET CLIENTE.
PRACTICA 1.1 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;

public class ClientP1V4{
    public static void main(String[] args){
        String host =  "127.0.0.1";         //SERVIDOR POR DEFAULT
        int pto = 7000;                     //PUERTO POR DEFAULT
        try{
            System.out.println("\nDireccion del servidor: " + host);
            System.out.println("\n\nPuerto: " + pto);

            System.out.println("\n\nSeleccione los archivos:");
            JFileChooser file = new JFileChooser();     //Se declara un JFileChooser para escoger los archivos que se quieran.		
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);	    //Se especifica que solo se pueden escoger archivos.
            file.setMultiSelectionEnabled(true);        //Permite seleccionar multiples archivos
            int selection = file.showOpenDialog(null);
            if(selection == JFileChooser.APPROVE_OPTION){
                File[] listOfFiles = file.getSelectedFiles();   //Array que tendrá los archivos seleccionados.
                int numFiles = listOfFiles.length;


                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("\n\nEscriba el tamanio del buffer:");   
                int bufferSize = Integer.parseInt(br.readLine());       //Se lee el tamanio deseado para el buffer.
                byte[] b = new byte[bufferSize];
                

                System.out.println("\n\nARCHIVOS SELECCIONADOS:");
                for(int count=0;count<numFiles;count++){
                    //Creamos el socket y nos conectamos 
                    Socket cl = new Socket(host, pto);

                    DataOutputStream das = new DataOutputStream(cl.getOutputStream());  //Se declara un DataOutputStream para envio de datos al Server.

                    das.writeInt(bufferSize);     das.flush();
                    das.writeInt(numFiles);     das.flush();

                    String archivo = listOfFiles[count].getAbsolutePath();//Dirección
                    String nombre = listOfFiles[count].getName();//nombre
                    long tam = listOfFiles[count].length(); //Tamaño

                    DataOutputStream dos = new DataOutputStream(cl.getOutputStream());  //Se declara un DataOutputStream para envio de datos al Server.
                    DataInputStream dis = new DataInputStream(new FileInputStream(archivo));

                    dos.writeUTF(nombre);   dos.flush();
                    dos.writeLong(tam);     dos.flush();

                    long enviados = 0;
                    int porcentaje, n;
                    System.out.println("\n\nArchivo "+(count+1));
                    long resta = tam-enviados; 
                    while (enviados < tam) {
                        n = dis.read(b,0,Math.min(bufferSize,(int)resta));
                        dos.write(b, 0, n);
                        dos.flush();
                        enviados = enviados + n;                        
                        porcentaje = (int)(enviados*100/tam);
                        System.out.println("Enviado: " + porcentaje + "%\r");
                    }//while
                    System.out.println("\n\nArchivo "+ (count+1) + " enviado");  
                    dos.close();    dis.close();
                    das.close();    cl.close();
                }      
            }
        }catch(Exception e){    //Manejo de excepciones
            e.printStackTrace();
        }
    }
}