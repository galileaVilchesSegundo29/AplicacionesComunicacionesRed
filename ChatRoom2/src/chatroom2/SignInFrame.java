package chatroom2;

import javax.swing.JOptionPane;

public class SignInFrame extends javax.swing.JFrame {
    String User;

    public SignInFrame() {
        initComponents();
    }


    private void initComponents() {

        chatRoomL = new javax.swing.JLabel();
        usrNameTF = new javax.swing.JTextField();
        usrNameSI = new javax.swing.JLabel();
        signInButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chatRoomL.setFont(new java.awt.Font("Caviar Dreams", 0, 36)); // NOI18N
        chatRoomL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chatRoomL.setText("Chat Room");

        usrNameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usrNameTFKeyTyped(evt);
            }
        });

        usrNameSI.setFont(new java.awt.Font("Caviar Dreams", 0, 18)); // NOI18N
        usrNameSI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usrNameSI.setText("User Name:");

        signInButton.setFont(new java.awt.Font("Caviar Dreams", 0, 14)); // NOI18N
        signInButton.setText("Sign In");
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(chatRoomL, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addGap(136, 136, 136))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(usrNameSI, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usrNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(signInButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(chatRoomL, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(usrNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(usrNameSI, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(signInButton)
                .addGap(103, 103, 103))
        );

        pack();
    }// </editor-fold>   
    
    private void usrNameTFKeyTyped(java.awt.event.KeyEvent evt){
        if(evt.getKeyChar()=='\n')
            signInButton.doClick();
    }

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(!(User = usrNameTF.getText()).equalsIgnoreCase("")){
            CFrame CF = new CFrame(User);
            CF.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null,"No valid user. Try again.");
        }
    }

    private void usrNameTFActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignInFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chatRoomL;
    private javax.swing.JButton signInButton;
    private javax.swing.JLabel usrNameSI;
    private javax.swing.JTextField usrNameTF;
    // End of variables declaration//GEN-END:variables
}
