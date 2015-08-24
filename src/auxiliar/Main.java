/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;


import auxiliar.threads.FCBannerWeb;
import auxiliar.threads.LogoInicio;
import formularios.Aguardando;
import formularios.Inicio;
import formularios.Login;
import java.io.IOException;
import javax.swing.UIManager;

/**
 *
 * @author AYRTON MONIER
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args){
        
//        new Thread(new FCBannerWeb()).start();
        new Thread(new LogoInicio()).start();
         
        Aguardando.s_telaAguardando = new Aguardando(new javax.swing.JFrame(), true);
//        SolicitarDados.s_SolicitarDados.copiaArquivoFCIni();
        

            try{
//                        UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
//                        UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
//                        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//                        UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
//                        UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }catch(Exception e){
                System.err.println("Erro ao tentar setar o Look and fell.   \nErro: "+e);
            }

        

        Login.s_telaLogin = new Login();
        Login.s_telaLogin.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent e) {
                Dados.s_conexaoBanco.desconecta();
                System.exit(0);
            }
        });
                
        Inicio.s_telaInicio.dispose();
        Login.s_telaLogin.setVisible(true);

    }

}
