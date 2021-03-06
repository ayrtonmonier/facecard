/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EscolhaCorListaAmigos.java
 *
 * Created on 23/05/2010, 19:53:38
 */

package formularios;
import auxiliar.Avisos;
import auxiliar.Icones;
import auxiliar.SolicitarDados;


/**
 *
 * @author ayrton monier
 */
public class EscolhaCorListaUsuarios extends javax.swing.JDialog {

    public static EscolhaCorListaUsuarios s_telaEscolhaCorListaAmigos;

    /** Creates new form EscolhaCorListaAmigos */
    public EscolhaCorListaUsuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setTitle(Avisos.TIT_JAN_ESC_O_Q_COLORIR); 
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo_amigos = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        bt_ok = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        rb_corFundoListaAmigos = new javax.swing.JRadioButton();
        rb_corNomeAmigo = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("");
        setAlwaysOnTop(true);
        setIconImage(null);
        setIconImages(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18));
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/cores.png"))); // NOI18N
        jLabel1.setText("Escolha um ítem para alterar a cor ¬");

        bt_ok.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bt_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ok.png"))); // NOI18N
        bt_ok.setText("Confirmar");
        bt_ok.setEnabled(false);
        bt_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_okActionPerformed(evt);
            }
        });

        bt_cancelar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bt_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/cancelar.png"))); // NOI18N
        bt_cancelar.setText("Cancelar");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        rb_corFundoListaAmigos.setBackground(new java.awt.Color(255, 255, 255));
        grupo_amigos.add(rb_corFundoListaAmigos);
        rb_corFundoListaAmigos.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        rb_corFundoListaAmigos.setForeground(new java.awt.Color(255, 0, 0));
        rb_corFundoListaAmigos.setText("Cor de FUNDO da lista de usuários.");
        rb_corFundoListaAmigos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_corFundoListaAmigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_corFundoListaAmigosActionPerformed(evt);
            }
        });

        rb_corNomeAmigo.setBackground(new java.awt.Color(255, 255, 255));
        grupo_amigos.add(rb_corNomeAmigo);
        rb_corNomeAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        rb_corNomeAmigo.setForeground(new java.awt.Color(255, 0, 0));
        rb_corNomeAmigo.setText("Cor do APELIDO na lista.");
        rb_corNomeAmigo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_corNomeAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_corNomeAmigoActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/32/cor-fundo-lista.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/32/cor-fonte-lista.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rb_corFundoListaAmigos, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bt_ok, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_cancelar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rb_corNomeAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(rb_corFundoListaAmigos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(rb_corNomeAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_ok, 0, 20, Short.MAX_VALUE)
                    .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        dispose();
        s_telaEscolhaCorListaAmigos = null;
        System.out.println("tela de escolha cor lista amigos fechada e null");

        //caso cor de fundo da lista de amigos estiver selecionada...
        if(rb_corFundoListaAmigos.isSelected()){
            SolicitarDados.s_SolicitarDados.escolherCorObjeto(Icones.COR_FUNDO_LISTA_32, "Cor de FUNDO da lista de usuários", Moniersn.jt_listaAmigosUsuario.getForeground(),Moniersn.jt_listaAmigosUsuario.getBackground(), "cor_lista_amigos");
        }

        else{
            SolicitarDados.s_SolicitarDados.escolherCorObjeto(Icones.COR_FONTE_LISTA_32, "Cor do APELIDO na lista", Moniersn.jt_listaAmigosUsuario.getForeground(), Moniersn.jt_listaAmigosUsuario.getBackground(),"cor_nome_amigo");
        }
        
    }//GEN-LAST:event_bt_okActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        dispose();
        s_telaEscolhaCorListaAmigos = null;
        System.out.println("tela de escolha cor lista amigos fechada e null");
}//GEN-LAST:event_bt_cancelarActionPerformed

    private void rb_corFundoListaAmigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_corFundoListaAmigosActionPerformed
        bt_ok.setEnabled(true);
    }//GEN-LAST:event_rb_corFundoListaAmigosActionPerformed

    private void rb_corNomeAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_corNomeAmigoActionPerformed
        bt_ok.setEnabled(true);
    }//GEN-LAST:event_rb_corNomeAmigoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dispose();
        s_telaEscolhaCorListaAmigos = null;
        System.out.println("tela de escolha cor lista amigos fechada e null");
    }//GEN-LAST:event_formWindowClosing

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        rb_corFundoListaAmigos.setSelected(true);
        bt_ok.setEnabled(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        rb_corNomeAmigo.setSelected(true);
        bt_ok.setEnabled(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EscolhaCorListaUsuarios dialog = new EscolhaCorListaUsuarios(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_ok;
    private javax.swing.ButtonGroup grupo_amigos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JRadioButton rb_corFundoListaAmigos;
    private javax.swing.JRadioButton rb_corNomeAmigo;
    // End of variables declaration//GEN-END:variables

}
