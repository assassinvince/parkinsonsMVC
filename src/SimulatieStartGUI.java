public class SimulatieStartGUI extends javax.swing.JFrame {

    /**
     * Creates new form SimulatieStartup
     */
    public SimulatieStartGUI() {
        initComponents();
        setVisible(true);
        rowError.setVisible(false);
        errorEmptyFields.setVisible(false);
        rowAmount = -1;
        placeAmount = -1;
        floorAmount = -1;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rowAmountLabel = new javax.swing.JLabel();
        placesAmountLabel = new javax.swing.JLabel();
        floorAmountLabel = new javax.swing.JLabel();
        startSimulation = new javax.swing.JButton();
        placeInput = new javax.swing.JTextField();
        rowsInput = new javax.swing.JTextField();
        floorInput = new javax.swing.JTextField();
        rowError = new javax.swing.JLabel();
        errorEmptyFields = new javax.swing.JLabel();
        backGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("DooM", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Parkin'Sons Simulator");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 10, 280, 22);

        rowAmountLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rowAmountLabel.setForeground(new java.awt.Color(255, 255, 255));
        rowAmountLabel.setText("Aantal rijen");
        jPanel1.add(rowAmountLabel);
        rowAmountLabel.setBounds(20, 50, 79, 19);

        placesAmountLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        placesAmountLabel.setForeground(new java.awt.Color(255, 255, 255));
        placesAmountLabel.setText("Aantal plaatsen");
        jPanel1.add(placesAmountLabel);
        placesAmountLabel.setBounds(20, 100, 150, 19);

        floorAmountLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        floorAmountLabel.setForeground(new java.awt.Color(255, 255, 255));
        floorAmountLabel.setText("Aantal verdiepingen");
        jPanel1.add(floorAmountLabel);
        floorAmountLabel.setBounds(20, 140, 160, 19);

        startSimulation.setText("Begin Simulatie");
        startSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSimulationActionPerformed(evt);
            }
        });
        jPanel1.add(startSimulation);
        startSimulation.setBounds(70, 210, 140, 32);

        placeInput.setText("35");
        placeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeInputActionPerformed(evt);
            }
        });
        placeInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                placeInputKeyTyped(evt);
            }
        });
        jPanel1.add(placeInput);
        placeInput.setBounds(170, 140, 30, 30);

        rowsInput.setText("8");
        rowsInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rowsInputActionPerformed(evt);
            }
        });
        rowsInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rowsInputKeyTyped(evt);
            }
        });
        jPanel1.add(rowsInput);
        rowsInput.setBounds(170, 50, 30, 30);

        floorInput.setText("3");
        floorInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floorInputActionPerformed(evt);
            }
        });
        floorInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                floorInputKeyTyped(evt);
            }
        });
        jPanel1.add(floorInput);
        floorInput.setBounds(170, 94, 30, 30);

        rowError.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rowError.setForeground(new java.awt.Color(255, 51, 0));
        rowError.setText("FOUT: Houd het getal onder 999!");
        jPanel1.add(rowError);
        rowError.setBounds(10, 170, 270, 16);

        errorEmptyFields.setBackground(new java.awt.Color(255, 0, 51));
        errorEmptyFields.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        errorEmptyFields.setForeground(new java.awt.Color(255, 51, 51));
        errorEmptyFields.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorEmptyFields.setText("FOUT: Niet alle velden hebben een nummer");
        jPanel1.add(errorEmptyFields);
        errorEmptyFields.setBounds(0, 190, 270, 16);

        backGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/garageBackground.jpg"))); // NOI18N
        jPanel1.add(backGround);
        backGround.setBounds(0, 0, 280, 270);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSimulationActionPerformed

        try {
            rowAmount = Integer.parseInt(rowsInput.getText());
            floorAmount = Integer.parseInt(floorInput.getText());
            placeAmount = Integer.parseInt(placeInput.getText());

            if (rowAmount == 0 || floorAmount == 0 || placeAmount == 0) {
                errorEmptyFields.setVisible(true);
                errorEmptyFields.setText("FOUT: 0 is niet toegestaan!");
                System.out.println("ERROR");
            } else {
                Simulator simulator = new Simulator();
            }
        } catch(IllegalArgumentException e)
        {

        }
    }//GEN-LAST:event_startSimulationActionPerformed



    private void placeInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_placeInputKeyTyped

        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
        if (placeInput.getText().length()>=3) {
            evt.consume();
        }

    }//GEN-LAST:event_placeInputKeyTyped

    private void rowsInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rowsInputKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();

        }
        if (rowsInput.getText().length()>=3) {
            evt.consume();
            rowError.setVisible(true);
            rowError.setText("FOUT: Niet meer dan 3 getallen.");
        } else if (rowsInput.getText().length()<=3) {
            rowError.setVisible(false);
        }

    }//GEN-LAST:event_rowsInputKeyTyped

    private void floorInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_floorInputKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
        if (floorInput.getText().length()>=3) {
            evt.consume();
        }
    }//GEN-LAST:event_floorInputKeyTyped

    private void floorInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_floorInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_floorInputActionPerformed

    private void rowsInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rowsInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rowsInputActionPerformed

    private void placeInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placeInputActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SimulatieStartGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimulatieStartGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimulatieStartGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimulatieStartGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimulatieStartGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backGround;
    private javax.swing.JLabel errorEmptyFields;
    private javax.swing.JLabel floorAmountLabel;
    private javax.swing.JTextField floorInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField placeInput;
    private javax.swing.JLabel placesAmountLabel;
    private javax.swing.JLabel rowAmountLabel;
    private javax.swing.JLabel rowError;
    private javax.swing.JTextField rowsInput;
    private javax.swing.JButton startSimulation;
    // End of variables declaration//GEN-END:variables
    static int rowAmount;
    static int floorAmount;
    static int placeAmount;
    private Simulator simulator;
}
