package chatroom2;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class Sending extends Thread{
    MulticastSocket ms;
    int port;
    InetAddress group;
    JTextPane chatMessagesTP;
    Message sendM;
    SocketAddress remote=null;

    public Sending(JTextPane chatMessagesTP, MulticastSocket ms, InetAddress group, int port, Message sendM){
        this.ms = ms;
        this.group = group;
        this.port = port;
        this.sendM = sendM;
    }
    
    public Sending(MulticastSocket ms, InetAddress group, int port, Message sendM){
        this.ms = ms;
        this.group = group;
        this.port = port;
        this.sendM = sendM;
    }

    public void run(){
        try{
            sendingMessagesTo();
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
    
    public void sendingMessagesTo(){
        try {
            try{
                remote = new InetSocketAddress(group, port);
            }catch(Exception e){
             e.printStackTrace();
            }

            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

            NetworkInterface ni = NetworkInterface.getByName("lo");

            DatagramChannel cl = DatagramChannel.open(StandardProtocolFamily.INET);
            cl.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            cl.setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
            cl.configureBlocking(false);
            Selector sel = Selector.open();
            cl.register(sel, SelectionKey.OP_WRITE);
            cl.join(group, ni);

            sel.select();
            Iterator<SelectionKey>it = sel.selectedKeys().iterator();
            while(it.hasNext()){
                SelectionKey k = (SelectionKey)it.next();
                it.remove();
                if(k.isWritable()){
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(sendM);     oos.flush();
                    ByteBuffer b = ByteBuffer.wrap(baos.toByteArray());
                    DatagramChannel ch = (DatagramChannel)k.channel();
                    ch.send(b, remote);
                    continue;
                }
            }//while

            // System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
            // System.out.println("Sent datagram. :)");
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
