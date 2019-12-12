/*SOCKET CLIENTE.
MULTICAST NO BLOQUEANTE - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
SEPTIEMBRE 2019.*/

import java.net.*;
//import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class cMulticastNB{
    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses))
            System.out.printf("InetAddress: %s\n", inetAddress);
        System.out.printf("\n");
    }
    
    public static void main(String[] args){
        int pto=2000,z=0;
        String hhost="230.0.0.1";
        SocketAddress remote=null;
        try{
            try{
                remote = new InetSocketAddress(hhost, pto);
            }catch(Exception e){
             e.printStackTrace();
            }//catch
           
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)){
                //System.out.print("[Interfaz "+ ++z +"]:");
                displayInterfaceInformation(netint);
            }
            
            //System.out.print("\nElige la interfaz multicast:");
            //int interfaz = Integer.parseInt(br.readLine());
            NetworkInterface ni = NetworkInterface.getByName("lo");
            //NetworkInterface ni = NetworkInterface.getByIndex(interfaz);
            
            DatagramChannel cl = DatagramChannel.open(StandardProtocolFamily.INET);
            cl.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            cl.setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
            cl.configureBlocking(false);
            Selector sel = Selector.open();
            cl.register(sel, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
            InetAddress group = InetAddress.getByName(hhost);
            cl.join(group, ni);
            ByteBuffer b = ByteBuffer.allocate(4);
            int n=0;

            while(n<100){
                sel.select();  /*Se realiza un evento de escritura porque las localidades de memoria están en blanco.*/ 
                Iterator<SelectionKey>it = sel.selectedKeys().iterator();
                while(it.hasNext()){
                    SelectionKey k = (SelectionKey)it.next();
                    it.remove();
                    if(k.isWritable()){
                        DatagramChannel ch = (DatagramChannel)k.channel();
                        b.clear();
                        b.putInt(n++);
                        b.flip();
                        ch.send(b, remote);
                        continue;
                    }
                }//while
            }//while
            cl.close();
            System.out.println("Termina envio de datagramas");
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}