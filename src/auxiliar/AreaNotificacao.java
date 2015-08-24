/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import formularios.Moniersn;
import formularios.ConfirmaSenha;

/**
 *
 * @author Ayrton Monier
 */
public class AreaNotificacao {

    //variável estática da classe AreaNotificação que está no pacote auxiliar
    public static AreaNotificacao s_areaNotific = null;

    //criaçãod o icone, instancia da classe java.awt.TrayIcon
    public static TrayIcon trayIcon = null;

    static SystemTray tray = null;

    public static boolean s_mostrarTela;


    public AreaNotificacao(){

        super();

        criaAreaNotificacao();
    }

    static void criaAreaNotificacao(){

        //verifica se e possivel trabalhar com trayIcon

        if(!SystemTray.isSupported()){
            System.err.println(Avisos.MSG_TRAY_ICON_NAO_SUPORTADO);
            return;
        }

        String l_msgBoasVindas = null;

        //instancia de um objeto pop up menu
        final PopupMenu pop = new PopupMenu();


        //instancia do objeto systemTray
        tray = SystemTray.getSystemTray();


        //criação dos itens do menun popup
        MenuItem mi_mostrar = new MenuItem("Mostrar");
        MenuItem mi_sair = new MenuItem("sair");

        //add os ites ao menu popup
        pop.add(mi_mostrar);
        pop.addSeparator();//separador
        pop.add(mi_sair);

        //criaçao do objeto tray Icon informando uma imagem e um título e popup
        trayIcon = new TrayIcon(createImage(Icones.ICONE_NOTIFICACAO, Avisos.FACECARD_FULL));

        //coloca  menu no tray Icone
        trayIcon.setPopupMenu(pop);

        //add o objeto trayIcon na area de notificaçao
        try{

            Data.atualizaDataHora();

            tray.add(trayIcon);

            atualizaTipTextTrayIcon();

            l_msgBoasVindas = mensagemBoasVindas();

            //balãozinho com iformação de concluído
            trayIcon.displayMessage(Avisos.FACECARD_FULL, l_msgBoasVindas, TrayIcon.MessageType.INFO);

            System.out.println("Ícone de notificação criado!");

        }
        catch(AWTException e ){
            System.out.println("Erro ao tentar add tray icon.   \nErro: "+e);
            return;
        }
        
    //ação do item de menu do systray
    mi_mostrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                
                boolean l_podeEntrar = false;

                //CASO SOLICITE SENHA AO ABRIR CONFIGURAÇOES...
                if(LogUsuario.s_solicitarSenhaAoMostrar.equals("S") && !Moniersn.s_telaMsn.isVisible()){

                    l_podeEntrar = MostrarDados.s_MostrarDados.mostraConfirmacaoSenha("mostrar");

                }else
                    l_podeEntrar = true;


                    if(l_podeEntrar){

                        //deixa a janela visível
                        Moniersn.s_telaMsn.setVisible(true);

                    }

            }
        });


        //ação do botao sair
        mi_sair.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                        LogMoniersn.s_LogMoniersn.desconectar(1);
                }
        });


        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                
                boolean l_podeEntrar = false;

                //CASO SOLICITE SENHA AO ABRIR CONFIGURAÇOES...
                if(LogUsuario.s_solicitarSenhaAoMostrar.equals("S") && !Moniersn.s_telaMsn.isVisible()){

                    l_podeEntrar = MostrarDados.s_MostrarDados.mostraConfirmacaoSenha("mostrar");

                }else
                    l_podeEntrar = true;


                    if(l_podeEntrar){

                        //deixa a janela visível
                        Moniersn.s_telaMsn.setVisible(true);

                    }

            }
        });

    }

    public static void atualizaTipTextTrayIcon(){

        
        String l_tipText = LogUsuario.s_nome+" "+LogUsuario.s_sobrenome;
        String l_icone = Icones.ICONE_NOTIFICACAO;

        if(LogUsuario.s_qtdRecPrivRecNaoLido>0){
               l_tipText += "\n("+LogUsuario.s_qtdRecPrivRecNaoLido+") "+Avisos.TEXTO_TRAY_REC_PRIV;
               l_icone = Icones.ICONE_NOTIFICACAO_AVISO;
        }
        if(LogUsuario.s_qtdRecPubRecNaoLido > 0){
               l_tipText += "\n("+LogUsuario.s_qtdRecPubRecNaoLido+") "+Avisos.TEXTO_TRAY_REC_PUB;
               l_icone = Icones.ICONE_NOTIFICACAO_AVISO;
        }
        if(LogUsuario.s_qtdRecInstRec > 0){
               l_tipText += "\n("+LogUsuario.s_qtdRecInstRec+") "+Avisos.TEXTO_TRAY_REC_INST;
               l_icone = Icones.ICONE_NOTIFICACAO_AVISO;
        }
        if(LogUsuario.s_qtdTotArqDisponiveis > 0){
               l_tipText += "\n("+LogUsuario.s_qtdTotArqDisponiveis+") "+Avisos.TEXTO_TRAY_ARQ;
               l_icone = Icones.ICONE_NOTIFICACAO_AVISO;
        }
        if(LogUsuario.s_qtdOcoRecRecente > 0){
               l_tipText += "\n("+LogUsuario.s_qtdOcoRecRecente+") "+Avisos.TEXTO_TRAY_OCO;
               l_icone = Icones.ICONE_NOTIFICACAO_AVISO;
        }
        if(LogUsuario.s_qtdAtuRecRecenteDeEstranhos > 0){
               l_tipText += "\n("+LogUsuario.s_qtdAtuRecRecenteDeEstranhos+") "+Avisos.TEXTO_TRAY_ATU_EST;
               l_icone = Icones.ICONE_NOTIFICACAO_AVISO;
        }
        if(LogUsuario.s_qtdAtuRecRecenteDeAmigos > 0){
               l_tipText += "\n("+LogUsuario.s_qtdAtuRecRecenteDeAmigos+") "+Avisos.TEXTO_TRAY_ATU_AMI;
               l_icone = Icones.ICONE_NOTIFICACAO_AVISO;
        }
        if(LogUsuario.s_qtdTotConvRec > 0){
               l_tipText += "\n("+LogUsuario.s_qtdTotConvRec+") "+Avisos.TEXTO_TRAY_CONV;
               l_icone = Icones.ICONE_NOTIFICACAO_AVISO;
        }

        trayIcon.setToolTip(l_tipText);
        setaIconeTrayIcon(l_icone);

    }

public static String mensagemBoasVindas(){

        Data.atualizaDataHora();

        String l_comprimento = Data.comprimentacao();

        String l_textoMsg = l_comprimento+", "+LogUsuario.s_nome+"! "+Avisos.TEXTO_BEM_VINDO_AO_FACE_CARD+"\n\n";

        if(LogUsuario.s_qtdOcoRecDesdUltLogout>0 ||
           LogUsuario.s_qtdAtuRecDesdUltLogoutDeEstranhos>0 ||
           LogUsuario.s_qtdAtuRecDesdUltLogoutDeAmigos>0){

               l_textoMsg += Avisos.TEXTO_ENQUANTO_DESCONECT+"\n";

                if(LogUsuario.s_qtdOcoRecDesdUltLogout>0){

                   l_textoMsg += Avisos.TEXTO_RECEBEU+" ("+LogUsuario.s_qtdOcoRecDesdUltLogout+") "+Avisos.TEXTO_OCORRENCIAS+"\n";

                }

                if(LogUsuario.s_qtdAtuRecDesdUltLogoutDeEstranhos>0 && LogUsuario.s_qtdAtuRecDesdUltLogoutDeAmigos>0){

                    int l_somaAtus = LogUsuario.s_qtdAtuRecDesdUltLogoutDeEstranhos + LogUsuario.s_qtdAtuRecDesdUltLogoutDeAmigos;

                    LogUsuario.s_qtdTotAtuRecDesdUltLogout = l_somaAtus;

                    l_textoMsg += Avisos.TEXTO_RECEBEU+" ("+LogUsuario.s_qtdTotAtuRecDesdUltLogout+") "+Avisos.TEXTO_ATUALIZAÇÕES+".\n";

                    l_textoMsg += Avisos.TEXTO_SENDO+" ("+LogUsuario.s_qtdAtuRecDesdUltLogoutDeEstranhos+") "+Avisos.TEXTO_ATUALIZAÇÕES_ESTR+".\n";
                    l_textoMsg += Avisos.TEXTO_SENDO+" ("+LogUsuario.s_qtdAtuRecDesdUltLogoutDeAmigos+") "+Avisos.TEXTO_ATUALIZAÇÕES_AMIG+".\n";

                }else{
                    if(LogUsuario.s_qtdAtuRecDesdUltLogoutDeEstranhos>0){
                        l_textoMsg += Avisos.TEXTO_RECEBEU+" ("+LogUsuario.s_qtdAtuRecDesdUltLogoutDeEstranhos+") "+Avisos.TEXTO_ATUALIZAÇÕES_ESTR+".\n";
                        LogUsuario.s_qtdTotAtuRecDesdUltLogout = LogUsuario.s_qtdAtuRecDesdUltLogoutDeEstranhos;
                    }
                    if (LogUsuario.s_qtdAtuRecDesdUltLogoutDeAmigos > 0){
                        l_textoMsg += Avisos.TEXTO_RECEBEU+" ("+LogUsuario.s_qtdAtuRecDesdUltLogoutDeAmigos+") "+Avisos.TEXTO_ATUALIZAÇÕES_AMIG+".\n";
                        LogUsuario.s_qtdTotAtuRecDesdUltLogout = LogUsuario.s_qtdAtuRecDesdUltLogoutDeAmigos;
                    }
                }

        }else{

            l_textoMsg += Avisos.TEXTO_NENHUMA_NOV_ENQ_DESCONECT;

        }

        return l_textoMsg;

    }


    
    public static void setaIconeTrayIcon(String l_icone){

        trayIcon.setImage(createImage(l_icone, Avisos.FACECARD_FULL));

    }
    
    protected static Image createImage(String path, String description){

    URL imageURL = AreaNotificacao.class.getResource(path);

        if(imageURL == null){
            System.err.println("Caminho do icone nao encontrado.    \nCaminho: "+path);
            return null;

        }
        else
            return (new ImageIcon(imageURL, description)).getImage();

     }

    public static void removeIconeNotificacao(){

              //remover o icone da area de notificação
                try{
                    tray.remove(trayIcon);
                    trayIcon = null;

                    //limpa as referencias ao Systemtray da classe janelinha
                    s_areaNotific = null;

                    System.out.println("Icone de notificação removido.");

                }
                catch(Throwable e){

                    System.out.println("Erro ao tentar remover ícone de notificação.    \nErro: "+e);
                    
                }
    }

 }




