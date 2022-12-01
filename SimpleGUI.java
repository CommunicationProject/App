import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleGUI extends javax.swing.JFrame {

    /** Creates new form ClientGUI */
    public SimpleGUI() {
        setVisible(true);
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        convoArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        sendArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat Room");
        setResizable(false);

        jScrollPane1.setAutoscrolls(true);

        convoArea.setEditable(false);
        convoArea.setColumns(20);
        convoArea.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        convoArea.setLineWrap(true);
        convoArea.setRows(5);
        convoArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(convoArea);

        sendArea.setColumns(20);
        sendArea.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        sendArea.setLineWrap(true);
        sendArea.setRows(5);
        sendArea.setText("");
        sendArea.setWrapStyleWord(true);
        sendArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(sendArea);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // TODO add your handling code here:
        String str = sendArea.getText();
        
        if(str.trim().equals(""))
        {
        
        }
        else
        {
            try {
            PracticeClient.OUT.writeUTF(str.trim());
            } catch (IOException ex) {
            Logger.getLogger(SimpleGUI.class.getName()).log(Level.SEVERE,
                    null, ex);
            }
        }
        
        sendArea.setText("");
        
    }//GEN-LAST:event_sendButtonActionPerformed

  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea convoArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea sendArea;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}