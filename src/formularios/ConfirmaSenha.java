/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConfirmaSenha.java
 *
 * Created on 22/04/2010, 12:53:05
 */

package formularios;
import auxiliar.Avisos;
import auxiliar.LogUsuario;
import auxiliar.MostrarDados;
/**
 *
 * @author ayrton monier
 */
public class ConfirmaSenha extends javax.swing.JDialog {

    public static ConfirmaSenha s_telaConfirmaSenha;
    public String c_acao = null;
    public boolean c_senhaCorreta  = false;



    /** Creates new form ConfirmaSenha */
    public ConfirmaSenha(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        
        initComponents();
        
        this.setTitle(Avisos.TIT_JAN_CONF_SENHA);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lb_imagemAviso = new javax.swing.JLabel();
        lb_esqueciSenha = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pf_trocaSenha = new javax.swing.JPasswordField();
        bt_ok = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        lb_nome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lb_imagemAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/grandes/logo-seguranca-habilitada-trayicon.png"))); // NOI18N

        lb_esqueciSenha.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        lb_esqueciSenha.setForeground(new java.awt.Color(0, 0, 255));
        lb_esqueciSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/confuso.png"))); // NOI18N
        lb_esqueciSenha.setText("Esqueci...");
        lb_esqueciSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_esqueciSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_esqueciSenhaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_esqueciSenhaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_esqueciSenhaMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/seguranca.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        pf_trocaSenha.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        pf_trocaSenha.setForeground(new java.awt.Color(255, 0, 0));
        pf_trocaSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pf_trocaSenhaActionPerformed(evt);
            }
        });

        bt_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ok.png"))); // NOI18N
        bt_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_okActionPerformed(evt);
            }
        });

        bt_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/cancelar.png"))); // NOI18N
        bt_cancelar.setText("Cancelar");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        lb_nome.setFont(new java.awt.Font("Comic Sans MS", 3, 17)); // NOI18N
        lb_nome.setForeground(new java.awt.Color(255, 0, 0));
        lb_nome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/novato.png"))); // NOI18N
        lb_nome.setText("nome");
        lb_nome.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Confirmar senha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lb_imagemAviso))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pf_trocaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_esqueciSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lb_nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_imagemAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_nome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_esqueciSenha))
                    .addComponent(bt_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pf_trocaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_okActionPerformed
        if(pf_trocaSenha.getText().isEmpty())
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_INFORME_SENHA_LOGIN, "aviso");
       
        else{
        
            if(pf_trocaSenha.getText().toLowerCase().equals(LogUsuario.s_senha)){
            
                c_senhaCorreta = true;
                s_telaConfirmaSenha.dispose();
            
            }else{
            
                pf_trocaSenha.setText("");
                pf_trocaSenha.requestFocus();
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ERRO_SENHA, "erro");
                c_senhaCorreta = false;
                
            }

        }
            
    }//GEN-LAST:event_bt_okActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        c_senhaCorreta = false;
        s_telaConfirmaSenha.dispose();
        System.out.println("tela de confirmação de senha fechada e null");
    }//GEN-LAST:event_formWindowClosing

    private void lb_esqueciSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_esqueciSenhaMouseClicked
        RecuperaSenha.s_telaRecuperaSenha = new RecuperaSenha(new javax.swing.JFrame(), true);
        RecuperaSenha.s_telaRecuperaSenha.tf_nomeUsuario.setText(LogUsuario.s_moniersn);
        RecuperaSenha.s_telaRecuperaSenha.tf_nomeUsuario.setEditable(false);
        RecuperaSenha.s_telaRecuperaSenha.setVisible(true);
    }//GEN-LAST:event_lb_esqueciSenhaMouseClicked

    private void pf_trocaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pf_trocaSenhaActionPerformed
        if(pf_trocaSenha.getText().isEmpty())
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_INFORME_SENHA_LOGIN, "aviso");
       
        else{
        
            if(pf_trocaSenha.getText().toLowerCase().equals(LogUsuario.s_senha)){
            
                c_senhaCorreta = true;
                s_telaConfirmaSenha.dispose();
            
            }else{
            
                pf_trocaSenha.setText("");
                pf_trocaSenha.requestFocus();
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ERRO_SENHA, "erro");
                c_senhaCorreta = false;
                
            }

        }
    }//GEN-LAST:event_pf_trocaSenhaActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void lb_esqueciSenhaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_esqueciSenhaMouseExited
        lb_esqueciSenha.setForeground(new java.awt.Color(0, 0, 255));
    }//GEN-LAST:event_lb_esqueciSenhaMouseExited

    private void lb_esqueciSenhaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_esqueciSenhaMouseEntered
        lb_esqueciSenha.setForeground(new java.awt.Color(255, 0, 0));
    }//GEN-LAST:event_lb_esqueciSenhaMouseEntered

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
       
        c_senhaCorreta = false;
        s_telaConfirmaSenha.dispose();
        System.out.println("tela de confirmação de senha fechada e null");
}//GEN-LAST:event_bt_cancelarActionPerformed

    /**
    * @param args the command line arguments
    */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_ok;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_esqueciSenha;
    public static javax.swing.JLabel lb_imagemAviso;
    public javax.swing.JLabel lb_nome;
    public javax.swing.JPasswordField pf_trocaSenha;
    // End of variables declaration//GEN-END:variables



}
