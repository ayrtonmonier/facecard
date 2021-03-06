/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BannerFC.java
 *
 * Created on 19/03/2011, 18:39:08
 */

package formularios;

import java.io.File;
import auxiliar.Avisos;
import javax.swing.ImageIcon;


/**
 *
 * @author Ayrton Monier
 */
public class BannerFC extends javax.swing.JFrame {

    /** Creates new form BannerFC */
    public BannerFC(String l_caminhoImagem) {
        initComponents();
        lb_imagem.setIcon(new javax.swing.ImageIcon(l_caminhoImagem));
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
        lb_imagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novidades Facecard™ by Monier®");
        setAlwaysOnTop(true);
        setIconImage(new ImageIcon(BannerFC.class.getResource("/imagens/icones/16/icone-notificacao.png"), Avisos.FACECARD_FULL).getImage());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lb_imagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_imagem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_imagem, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_imagem, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
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
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lb_imagem;
    // End of variables declaration//GEN-END:variables

}
