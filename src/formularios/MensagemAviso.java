/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MensagemConfirmacao.java
 *
 * Created on 08/05/2011, 02:37:16
 */

package formularios;

import auxiliar.Avisos;

 /*
 * @author f113cpd02
 */
public class MensagemAviso extends javax.swing.JDialog {

    public static MensagemAviso s_telaAvisoConfirmacao;
    public static boolean s_confirmacao;

    /** Creates new form MensagemConfirmacao */
    public MensagemAviso(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setTitle(Avisos.TIT_JAN_MENS_AVISO); 
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lb_iconeTituloAviso = new javax.swing.JLabel();
        bt_cancelar = new javax.swing.JButton();
        bt_confirmar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tp_textoAviso = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("");
        setAlwaysOnTop(true);
        setIconImages(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lb_iconeTituloAviso.setFont(new java.awt.Font("Trebuchet MS", 1, 13));
        lb_iconeTituloAviso.setForeground(new java.awt.Color(255, 0, 0));
        lb_iconeTituloAviso.setText("DESCRIÇÃO DO AVISO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lb_iconeTituloAviso, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addGap(80, 80, 80))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lb_iconeTituloAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bt_cancelar.setFont(new java.awt.Font("Tahoma", 0, 10));
        bt_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/cancelar.png"))); // NOI18N
        bt_cancelar.setText("Cancelar");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        bt_confirmar.setFont(new java.awt.Font("Tahoma", 0, 10));
        bt_confirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ok.png"))); // NOI18N
        bt_confirmar.setText("Confirmar");
        bt_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_confirmarActionPerformed(evt);
            }
        });

        tp_textoAviso.setBackground(new java.awt.Color(255, 204, 0));
        tp_textoAviso.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tp_textoAviso.setEditable(false);
        tp_textoAviso.setFont(new java.awt.Font("Trebuchet MS", 0, 12));
        tp_textoAviso.setForeground(new java.awt.Color(0, 0, 204));
        tp_textoAviso.setText("Descrição do aviso...");
        tp_textoAviso.setCaretPosition(0);
        jScrollPane1.setViewportView(tp_textoAviso);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bt_confirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                        .addComponent(bt_cancelar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_confirmarActionPerformed
        s_confirmacao = true;
        dispose();

    }//GEN-LAST:event_bt_confirmarActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        s_confirmacao = false;
        dispose();
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.err.print("fechado e nul tela aviso conf.");
         s_confirmacao = false;
        s_telaAvisoConfirmacao = null;
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_confirmar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lb_iconeTituloAviso;
    public javax.swing.JTextPane tp_textoAviso;
    // End of variables declaration//GEN-END:variables

}
