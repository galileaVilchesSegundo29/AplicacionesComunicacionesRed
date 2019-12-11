/*DEMO.
EXTERNALIZACION - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.io.*;
import java.util.*;

class DemoExternalizable{
    public static void main(String []args) throws IOException, ClassNotFoundException{
        System.out.println("Creando objetos");
        String[] usuarios = {"A", "B", "C"};
        String[] passwords = {"1", "2", "3"};
        listaUsuarios ip = new listaUsuarios(usuarios, passwords);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("objeto.out"));
        o.writeObject(ip);  o.close();
        System.out.println("Recuperando objeto");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("objeto.out"));
        ip = (listaUsuarios)in.readObject();
        ip.muestraUsuario();
    }
}