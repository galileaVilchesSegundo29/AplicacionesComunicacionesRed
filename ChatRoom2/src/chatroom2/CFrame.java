package chatroom2;

import java.net.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class CFrame extends javax.swing.JFrame {
    String User;
    MulticastSocket ms;
    int port = 4000;
    String dir = "230.1.1.1";
    InetAddress group = null;
    
    public CFrame(String userName){
        initComponents();
        this.User=userName;
        usrNameL.setText("@"+User);

        try{
            ms = new MulticastSocket(port);
            ms.setReuseAddress(true);                   //Para correr varios clientes y no haya problemas en conexiones.
            ms.setTimeToLive(1);
            try{
                group = InetAddress.getByName(dir);   //Para ver si la dirección IP es válida.
            }catch(UnknownHostException u){
                System.err.println("Wrong dir. Try again\n");
            }
            ms.joinGroup(group);
            System.out.println(User+" has joined the group :)");
            System.out.println("<inicio> "+User);

            Thread rece = new Receiving(chatMessagesTP,ms,group,port,ComboPrivChat);
            rece.start();
            
       
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public CFrame() {
        initComponents();
        
        try{
            ms = new MulticastSocket(port);
            ms.setReuseAddress(true);                   //Para correr varios clientes y no haya problemas en conexiones.
            ms.setTimeToLive(1);
            try{
                group = InetAddress.getByName(dir);   //Para ver si la dirección IP es válida.
            }catch(UnknownHostException u){
                System.err.println("Wrong dir. Try again\n");
            }
            ms.joinGroup(group);
            System.out.println("You have joined the group :)\n");

            Thread rece  = new Receiving(chatMessagesTP,ms,group,port,ComboPrivChat);
            rece.start();

       
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        chatMessagesTP = new javax.swing.JTextPane();
        usrNameL = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        messagesTA = new javax.swing.JTextArea();
        ComboPrivChat = new javax.swing.JComboBox<>();
        getOutButton = new javax.swing.JButton();
        privateChatB = new javax.swing.JButton();
        sendB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setViewportView(chatMessagesTP);

        chatMessagesTP.setContentType("text/html");
        chatMessagesTP.setEditable(false);

        usrNameL.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        usrNameL.setText("@UsrName");

        jLabel1.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        jLabel1.setText("Write message");

        messagesTA.setColumns(20);
        messagesTA.setRows(5);
        messagesTA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                messagesTAKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(messagesTA);

        ComboPrivChat.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        ComboPrivChat.setModel(new javax.swing.DefaultComboBoxModel<>());

        getOutButton.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        getOutButton.setText("Close");
        getOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getOutButtonActionPerformed(evt);
            }
        });

        privateChatB.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        privateChatB.setText("Private chat");
        privateChatB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                privateChatBActionPerformed(evt);
            }
        });

        sendB.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        sendB.setText("Send");
        sendB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usrNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(privateChatB)
                            .addGap(16, 16, 16))
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sendB, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ComboPrivChat, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(getOutButton)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(usrNameL)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(getOutButton)
                        .addGap(35, 35, 35)
                        .addComponent(ComboPrivChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(privateChatB)))
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendB))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>   
    
    private void messagesTAKeyTyped(java.awt.event.KeyEvent evt){
       
        messagesTA.setText(emojis(messagesTA.getText()));
        if(evt.getKeyChar()=='\n')
            sendB.doClick();
    }

    public String emojis(String text){
        text=text.replace(":o","<img src=Img\\imagen_beso.jpg>");
        text=text.replace("o:","<img src=Img\\imagen_beso.jpg>");
        text=text.replace(";)","<img src=Img\\imagen_guino.jpg>");
        text=text.replace("(;","<img src=Img\\imagen_guino.jpg>");
        text=text.replace(":s","<img src=Img\\imagen_gesto.jpg>");
        text=text.replace("s:","<img src=Img\\imagen_gesto.jpg>");
        text=text.replace("_Perro_","<img src=Img\\perro.gif>");
        //text=text.replace("_Homero_","<http://tusimagenesde.com/wp-content/uploads/2015/01/gifs-animados-5.gif>");
        return text;
    }

    private void privateChatBActionPerformed(java.awt.event.ActionEvent evt) {
        if(ComboPrivChat.getSelectedIndex() != -1){
            String destinyUser = ComboPrivChat.getSelectedItem().toString();
            String originUser = usrNameL.getText();
            
            Message sendM = new Message(User," ",destinyUser,"privateMessageConnection");
            
            Thread sendT = new Sending(chatMessagesTP,ms,group,port,sendM);
            sendT.start();
           
            privateFrame PF =  new privateFrame(originUser,destinyUser,ms,port,group);
            PF.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null,"No selected friends.");
        } 
    }

    private void sendBActionPerformed(java.awt.event.ActionEvent evt) {
        if(!messagesTA.getText().equalsIgnoreCase("")){
            String mess = messagesTA.getText();
            
            Message sendM = new Message(User,mess,"publicMessage");
            
            Thread sendT = new Sending(chatMessagesTP,ms,group,port,sendM);
            sendT.start();
          
            messagesTA.setText(" ");
        }
        else{
            JOptionPane.showMessageDialog(null,"No valid message. Try again.");
        }
    }

    private void getOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboPrivChat;
    private javax.swing.JTextPane chatMessagesTP;
    private javax.swing.JButton getOutButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea messagesTA;
    private javax.swing.JButton privateChatB;
    private javax.swing.JButton sendB;
    private javax.swing.JLabel usrNameL;
    // End of variables declaration//GEN-END:variables
}
