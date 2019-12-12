package chatroom2;

import java.net.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class privateFrame extends javax.swing.JFrame {
    
    String originUser;
    String destinyUser;
    MulticastSocket ms;
    int port;
    InetAddress group;
    
    public privateFrame(String originUser, String destinyUser, MulticastSocket ms, int port, InetAddress group){
        initComponents();
        this.originUser=originUser;
        this.destinyUser=destinyUser;
        this.ms=ms;
        this.port=port;
        this.group=group;
        originUsr.setText(originUser);
        destinyUsr.setText(destinyUser);

        Thread rece = new Receiving(watchMess,ms,group,port);
        rece.start();
        
    }

    public privateFrame() {
        initComponents();
        Thread rece = new Receiving(watchMess,ms,group,port);
        rece.start();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        originUsr = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        destinyUsr = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        watchMess = new javax.swing.JTextPane();
        writeMessL = new javax.swing.JLabel();
        wMessSP = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        messTA = new javax.swing.JTextArea();
        sendButtonP = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        //format1 = new Format();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        originUsr.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        originUsr.setText("@origin");

        label2.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        label2.setText("on private chat with");

        destinyUsr.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        destinyUsr.setText("@destiny");

        jScrollPane1.setViewportView(watchMess);

        watchMess.setContentType("text/html");
        watchMess.setEditable(false);

        writeMessL.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        writeMessL.setText("Write message");

        messTA.setColumns(20);
        messTA.setRows(5);
        messTA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                messTAKeyTyped(evt);
            }
        });

        jScrollPane3.setViewportView(messTA);

        wMessSP.setViewportView(jScrollPane3);

        sendButtonP.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        sendButtonP.setText("Send");
        sendButtonP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonPActionPerformed(evt);
            }
        });

        closeButton.setFont(new java.awt.Font("Caviar Dreams", 1, 13)); // NOI18N
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(writeMessL, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(originUsr, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(destinyUsr, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(closeButton)))
                        .addContainerGap(13, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wMessSP, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendButtonP, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originUsr)
                    .addComponent(label2)
                    .addComponent(destinyUsr)
                    .addComponent(closeButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(writeMessL)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(wMessSP, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendButtonP)
                        .addGap(76, 76, 76))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void messTAKeyTyped(java.awt.event.KeyEvent evt){
        messTA.setText(emojis(messTA.getText()));
        if(evt.getKeyChar()=='\n')
            sendButtonP.doClick();
    }

    public String emojis(String text){
        text=text.replace(":o","<Img\\imagen_beso.jpg>");
        text=text.replace("o:","<Img\\imagen_beso.jpg>");
        text=text.replace(";)","<Img\\imagen_guino.jpg>");
        text=text.replace("(;","<Img\\imagen_guino.jpg>");
        text=text.replace(":s","<Img\\imagen_gesto.jpg>");
        text=text.replace("s:","<Img\\imagen_gesto.jpg>");
        text=text.replace("_Perro_","<Img\\perro.gif>");
        //text=text.replace("_Homero_","<http://tusimagenesde.com/wp-content/uploads/2015/01/gifs-animados-5.gif>");
        return text;
    }

    private void sendButtonPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonPActionPerformed
        if(!messTA.getText().equalsIgnoreCase("")){
            String mess = messTA.getText();

            Message sendM = new Message(originUser,mess,"privateMessage");
            
            Thread sendT = new Sending(ms,group,port,sendM);
            sendT.start();

            messTA.setText(" ");
        }
        else{
            JOptionPane.showMessageDialog(null,"No valid message. Try again.");
        }
    }//GEN-LAST:event_sendButtonPActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
            this.setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed


    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new privateFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel destinyUsr;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label2;
    private javax.swing.JTextArea messTA;
    private javax.swing.JLabel originUsr;
    private javax.swing.JButton sendButtonP;
    private javax.swing.JScrollPane wMessSP;
    private javax.swing.JTextPane watchMess;
    private javax.swing.JLabel writeMessL;
    // End of variables declaration//GEN-END:variables
}
