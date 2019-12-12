/*SOCKET SERVIDOR.
MULTICAST NO BLOQUEANTE - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
SEPTIEMBRE 2019.*/

import java.net.*;
//import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class sMulticastNB{
    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses))
            System.out.printf("InetAddress: %s\n", inetAddress);
        System.out.printf("\n");
    }
    public static void main(String[] args){
        try{
            int pto=2000,z=0;
            String hhost="230.0.0.1";

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
            
            InetSocketAddress dir = new InetSocketAddress(pto);
            DatagramChannel s = DatagramChannel.open(StandardProtocolFamily.INET);
            s.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            s.setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
            InetAddress group = InetAddress.getByName(hhost);
            s.join(group, ni);
            s.configureBlocking(false);
            s.socket().bind(dir);
            Selector sel = Selector.open();
            s.register(sel, SelectionKey.OP_READ);
            ByteBuffer b = ByteBuffer.allocate(4);
            System.out.println("Servidor listo.. Esperando datagramas...");

            while(true){
                sel.select();
                Iterator<SelectionKey>it = sel.selectedKeys().iterator();
                while(it.hasNext()){
                    SelectionKey k = (SelectionKey)it.next();
                    it.remove();
                    if(k.isReadable()){
                        DatagramChannel ch = (DatagramChannel)k.channel();
                        b.clear();
                        SocketAddress emisor = ch.receive(b);
                        b.flip();
                        InetSocketAddress d = (InetSocketAddress)emisor;
                        System.out.println("Datagrama recibido desde "+ d.getAddress()+":"+d.getPort());
                        System.out.println("Dato: "+b.getInt());
                        continue;
                    }else if (k.isReadable()){

                    }//else
                }//while
            }//while
            //cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}