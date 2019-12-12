package chatroom2;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;

public class Receiving extends Thread{
    MulticastSocket ms;
    int port;
    InetAddress group;
    ArrayList<String> listOfNames = new ArrayList<>();
    JTextPane chatMessagesTP;
    JTextArea messagesTA;
    Message recieveM;
    String User;
    JComboBox ComboPrivChat;
    boolean flagPoP = false;

    //Recibir en chat publico
    public Receiving(JTextPane chatMessagesTP, MulticastSocket ms, InetAddress group, int port, JComboBox ComboPrivChat){
        this.chatMessagesTP=chatMessagesTP;
        this.ms=ms;
        this.group=group;
        this.port=port;
        this.ComboPrivChat=ComboPrivChat;
        this.flagPoP=false;
    }

    //Recibir en chat privado
    public Receiving(JTextPane chatMessagesTP, MulticastSocket ms, InetAddress group, int port){
        this.chatMessagesTP=chatMessagesTP;
        this.ms=ms;
        this.group=group;
        this.port=port;
        this.flagPoP=true;
    }

    public void run(){
        try{
            for(;;){
                receivingMessagesFrom();
                Thread.sleep(3000);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } 
    }

    public boolean addIntoCombo(String user){
        int i;
        for(i=0; i<listOfNames.size();i++)
            if(listOfNames.get(i).equals(user))
                return false;
        return true;
    }

    // public String changeForEmoji(String text){
    //     String cad = text;
    //     for(int x=0;x<4;x++){
    //     File fl = new File("Img");
    //     String nombre = "<"+fl.getName().split(".jpg")[0]+">";
        
    //     cad=cad.replaceAll(nombre, "<img src=\"FILE:///"+fl.getAbsolutePath().replace("\\","\\\\")+"\" height=\"25\" width=\"25\">");
    //     }

    //     return cad;
    // }

    public void receivingMessagesFrom(){
        try{
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

            NetworkInterface ni = NetworkInterface.getByName("lo");

            InetSocketAddress dir = new InetSocketAddress(port);
            DatagramChannel s = DatagramChannel.open(StandardProtocolFamily.INET);
            s.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            s.setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
            s.join(group, ni);
            s.configureBlocking(false);
            s.socket().bind(dir);
            Selector sel = Selector.open();
            s.register(sel, SelectionKey.OP_READ);

            sel.select();
            Iterator<SelectionKey>it = sel.selectedKeys().iterator();

            while(it.hasNext()){
                SelectionKey k = (SelectionKey)it.next();
                it.remove();
                if(k.isReadable()){
                    DatagramChannel ch = (DatagramChannel)k.channel();
                    ByteBuffer b = ByteBuffer.allocate(10000);
                    b.clear();
                    SocketAddress emisor = ch.receive(b);
                    b.flip();
                    InetSocketAddress d = (InetSocketAddress)emisor;
                    if(b.hasArray()){
                        ObjectInputStream ois =  new ObjectInputStream(new ByteArrayInputStream(b.array()));
                        recieveM = (Message) ois.readObject();
                        // System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
                        // System.out.println("Received datagram :)");

                        String uUser = recieveM.getUser();
                        String uDestiny = recieveM.getDestiny();
                        String uType = recieveM.getType();
                        String uMessage = recieveM.getMessage();
                        //String uString = changeForEmoji(uMessage);

                        System.out.println("\n------------------------------------");
                        System.out.println("User: "+ uUser);
                        if(uDestiny.equals(""))
                            System.out.println("To: Everybody");
                        else
                            System.out.println("To: "+ uDestiny);
                        System.out.println("\n------------------------------------");

                        String text = chatMessagesTP.getText().split("<body>")[1].split("</body>")[0];
                        String x="<p><font size='4'><style =\"color: #229954\"><i>@"+uUser+" says:</i></style></font> <br><font size='5'>"+uMessage+" </font></p>";


                        //CASE 1: Se desea iniciar un chat privado.
                        if(uType.equals("privateMessageConnection") && uDestiny.equals(uUser)){
                            //System.out.println("\n*********************************");
                            System.out.println("A private connection wants to be created");
                            System.out.println("\nFrom: "+ uUser);
                            System.out.println("\nTo: "+uDestiny);

                            privateFrame PF =  new privateFrame(uDestiny,uUser,ms,port,group);
                            PF.setVisible(true);

                            System.out.println("The connection was created succesfully :)");
                        }

                        //CASE 2: Se desea enviar un mensaje publico.
                        if(uType.equals("publicMessage") && flagPoP==false){
                            //System.out.println("\n*********************************");
                            // System.out.println("A public message wants to be sent");
                            if(uMessage.length()!=0){
                                chatMessagesTP.setText(text+String.format("<div align=\"right\"> %s </div>", x));

                                // System.out.println("The public message was sent succesfully :)");
                                System.out.println("<msj><"+uUser+">"+uMessage);

                                chatMessagesTP.setCaretPosition(chatMessagesTP.getDocument().getLength());
                            }
                            //Para agregar al comboBox
                            if(uUser.equals(uUser)){
                                if(addIntoCombo(uUser)){
                                    this.listOfNames.add(uUser);
                                    this.ComboPrivChat.addItem(uUser);
                                }
                            }
                        }

                        //CASE 3: Se desea enviar un mensaje privado.
                        if(flagPoP==true && uType.equals("privateMessage")){
                            //System.out.println("\n*********************************");
                            // System.out.println("A private message wants to be sent");
                            if(uMessage.length()!=0){
                                chatMessagesTP.setText(text+String.format("<div align=\"right\"> %s </div>", x));

                                //System.out.println("The private message was sent succesfully :)");
                                System.out.println("<private><msj><"+uUser+">"+"<"+uDestiny+">"+uMessage);

                                chatMessagesTP.setCaretPosition(chatMessagesTP.getDocument().getLength());
                            }
                        }
                    }
                    continue;
                }
            }//while
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
