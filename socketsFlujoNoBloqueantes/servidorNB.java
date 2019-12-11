/*SOCKET SERVIDOR.
SOCKETS DE FLUJO NO BLOQUEANTES - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
SEPTIEMBRE 2019.*/

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
// import java.nio.channels.SelectionKey;
// import java.nio.channels.Selector;
// import java.nio.channels.ServerSocketChannel;
// import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class servidorNB{
    public static void main(String[] args){
        try{
            String EECO = " ";
            int pto = 9999;
            ServerSocketChannel s = ServerSocketChannel.open();
            s.configureBlocking(false);
            s.socket().bind(new InetSocketAddress(pto));
            System.out.println("\nEsperando cliente...\n");
            Selector sel = Selector.open();
            s.register(sel, SelectionKey.OP_ACCEPT);
            while(true){
                sel.select();
                Iterator<SelectionKey> it = sel.selectedKeys().iterator();
                while(it.hasNext()){
                    SelectionKey k = (SelectionKey)it.next();
                    it.remove();
                    if(k.isAcceptable()){
                        SocketChannel cl = s.accept();
                        //System.out.println("\nConexion establecida desde: " + cl.getInetAddress()+":"+cl.getPort());
                        cl.configureBlocking(false);
                        cl.register(sel, SelectionKey.OP_WRITE);
                        continue;
                    }
                    if(k.isReadable()){
                        try{
                            SocketChannel ch = (SocketChannel)k.channel();
                            ByteBuffer b = ByteBuffer.allocate(2000);
                            b.clear();
                            int n = 0;
                            String msj = "";
                            n = ch.read(b); 
                            b.flip();
                            if(n>0)
                                msj = new String(b.array(),0,n);
                            System.out.println("\nMensaje: " + n+" bytes recibidos: "+msj);
                            if(msj.equalsIgnoreCase("SALIR")){
                                k.interestOps(SelectionKey.OP_WRITE);
                                ch.close();
                            }
                            else{
                                EECO = "ECO ->"+msj;
                                k.interestOps(SelectionKey.OP_WRITE);
                            }
                        }catch(IOException io){
                            continue;
                        }
                    }
                    else if(k.isReadable()){
                        try{
                            SocketChannel ch = (SocketChannel)k.channel();
                            ByteBuffer bb = ByteBuffer.wrap(EECO.getBytes());
                            ch.write(bb);
                            System.out.println("\nMensaje de: " + EECO.length()+" bytes enviados: "+EECO);
                            EECO = "";
                        }catch(IOException io){
                            k.interestOps(SelectionKey.OP_READ);
                            continue;
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}