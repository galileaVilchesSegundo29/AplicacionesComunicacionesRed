/*USUARIO.
EXTERNALIZACION - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.util.*;
// import jdk.nashorn.internal.runtime.ListAdapter;
import java.io.*;

class UsuarioE implements Externalizable{
    private String usuario, password;
    public UsuarioE(){
        System.out.println("Creando usuario vacio");
    }
    UsuarioE(String u, String p){
        System.out.println("\n\nCreando usuario ("+u+","+p+")");
        usuario = u;
        password = p;
    }
    public void writeExternal(ObjectOutput out) throws IOException{
        System.out.println("UsuarioE.writeExternal");
        //Explicitamente indicamos cuales atributos se almacenan.
        out.writeObject(usuario);
    }
    public void readExtern(ObjectInput in)throws IOException, ClassNotFoundException{
        System.out.println("UsuarioE.readExternal");
        //Explicitamente indicamos cuales atributos vamos a recuperar
        usuario = (String)in.readObject();
    }
    public void muestraUsuario(){
        String cad = "Usuario:" + usuario + "Password:";
        if(password == null)    cad=cad+"No disponible";
        else                    cad=cad+password;
        System.out.println(cad);
    }
}

class listaUsuarios implements Serializable{
    private LinkedList lista = new LinkedList();
    int valor;
    listaUsuarios(String[] usuarios, String[] passwords){
        for(int i = 0; i<usuarios.length;i++){
            lista.add(new UsuarioE(usuarios[i],passwords[i]));
        }
    }
    public void muestraUsuario(){
        ListIterator li = lista.listIterator();
        UsuarioE u;
        while(li.hasNext()){
            u = (UsuarioE)li.next();
            u.muestraUsuario();
        }
    }
}