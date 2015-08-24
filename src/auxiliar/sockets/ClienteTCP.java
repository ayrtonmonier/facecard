/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.sockets;

import auxiliar.Avisos;
import auxiliar.Diretorios;
import auxiliar.GravarDados;
import auxiliar.Icones;
import auxiliar.LogUsuario;
import auxiliar.MostrarDados;
import auxiliar.SolicitarDados;
import formularios.BaixaArquivo;
import formularios.Moniersn;
import java.net.*;
import java.io.*;
import java.io.InputStream;

/**
 *
 * @author Ayrton Monier
 */
public class ClienteTCP implements Runnable {

    private String c_hostServidor;
//    private byte[] c_bytesArquivo;
    private String c_nomeArquivo = "";
    private String c_diretorioBaixados;
    private int c_codArquivo;
    private String c_codArquivoEmpacotado = null;
    private BaixaArquivo c_telaRecebeArq = null;
    private int c_porta = 0;
    private int c_codEmissor;
    private int c_tamanhoArquivo = 0;
    private String c_apelidoEmissor;


    public ClienteTCP(String l_host, int l_porta, int l_codArquivo){


        try{

            c_hostServidor = l_host;
            c_porta = l_porta;

            c_codArquivo = l_codArquivo;

            //empacota o cod do arquivo
            c_codArquivoEmpacotado = l_codArquivo+"|";

            //busca informações do arquivo
            c_codEmissor = Integer.parseInt(SolicitarDados.s_SolicitarDados.pegaInfoArquivo("cod_emissor", c_codArquivo));
            c_tamanhoArquivo = Integer.parseInt(SolicitarDados.s_SolicitarDados.pegaInfoArquivo("tamanho", c_codArquivo));
            c_nomeArquivo = SolicitarDados.s_SolicitarDados.pegaInfoArquivo("nome", c_codArquivo);
//          c_bytesArquivo = SolicitarDados.s_SolicitarDados.pegaBytesArquivoBD(c_codArquivo);
            c_apelidoEmissor = SolicitarDados.pegaApelidoPeloCodigo(c_codEmissor);
            c_diretorioBaixados = Diretorios.pegaDiretorio(Diretorios.PASTA_ARQUIVOS, Diretorios.PASTA_BAIXADOS);

            //cria telinha de acompanhamento
            c_telaRecebeArq = new BaixaArquivo(c_nomeArquivo, c_tamanhoArquivo);
            
            c_telaRecebeArq.lb_logoBaixando.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LOGO_BAIXANDO_ARQ)));
            c_telaRecebeArq.bt_abrirArquivo.setVisible(false);
            //seta o texo connectando ao amigo...
            c_telaRecebeArq.lb_acompanhaRecebimento.setText(Avisos.TEXTO_CONECTANDO_A+c_apelidoEmissor+"...");
            c_telaRecebeArq.lb_apelidoEmissor.setText(c_apelidoEmissor);
            c_telaRecebeArq.lb_apelidoReceptor.setText(LogUsuario.s_moniersn);
            c_telaRecebeArq.setVisible(true);

        }
        catch(Exception e){
            System.err.println("Erro na preparação para recebimento de arquivo.     \nErro: "+e);

        }

    }

    public void run(){

        int bytesRead;
        int current = 0;

        try{
            
            Socket l_socketClienteTCP = new Socket(c_hostServidor, c_porta);

            System.out.println("Conectado ao servidor TCP do usuario!");

            //saida do CLIENTE PARA SERVIDOR
            OutputStream fluxoSaidaClienteTCP = l_socketClienteTCP.getOutputStream();

            //saída do SERVIDOR PARA O CLIENTE
            InputStream fluxoEntradaClienteTCP = l_socketClienteTCP.getInputStream();

            //envia informações do arquivo para o server tratar...
            System.out.println("enviando informações do arquivo...");
            fluxoSaidaClienteTCP.write(c_codArquivoEmpacotado.getBytes());

            //recebe info. do arquivo a tratar
            byte[] l_bufferStatusArq = new byte[512];
            String l_statusArquivo = null;
            
                try{

                    //recebe status do arquivo tratado pelo servidor
                    fluxoEntradaClienteTCP.read(l_bufferStatusArq);
                    l_statusArquivo = new String(l_bufferStatusArq);

                }catch(Exception e){

                    System.err.println("Erro ao tentar ler status do arquivo solicitado.    \nErro: "+e);
                    l_statusArquivo = null;

                }

            int l_codStatus = SolicitarDados.s_SolicitarDados.pegaCodStatusArquivoSolicitado(l_statusArquivo);


                if(l_codStatus == 1){


                    System.out.println("Preparando para receber...");
                    
                    //recebendo arquivo
                    byte[] myByteArray = new byte[c_tamanhoArquivo];

                    File l_arquivoRecebido = new File(c_diretorioBaixados+"\\"+c_nomeArquivo);

                    FileOutputStream fos = new FileOutputStream(l_arquivoRecebido);

                    BufferedOutputStream bos = new BufferedOutputStream(fos);

                    System.out.println("Lendo bytes...");
                    bytesRead = fluxoEntradaClienteTCP.read(myByteArray, 0, myByteArray.length);

                    current = bytesRead;

                    c_telaRecebeArq.c_contador = 0;
                    c_telaRecebeArq.threadTempoTarefa.stop();
                    
                    c_telaRecebeArq.pb_recebendoArquivo.setMaximum(c_tamanhoArquivo);
                    c_telaRecebeArq.pb_recebendoArquivo.setValue(0);
                    c_telaRecebeArq.lb_iconeRecebendoArq.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.STATUS_BAIXANDO_ARQ)));

                        do{

                            c_telaRecebeArq.pb_recebendoArquivo.setValue(current);
                            c_telaRecebeArq.lb_acompanhaRecebimento.setText(Avisos.TEXTO_RECEBENDO_ARQ_DE+c_apelidoEmissor+"... ["+current+" "+Avisos.TEXTO_DE+" "+c_tamanhoArquivo+" byte(s)]");

                            bytesRead = fluxoEntradaClienteTCP.read(myByteArray, current, (myByteArray.length -current));

                            if(bytesRead >= 0)
                                current += bytesRead;

                        }while(bytesRead != 0);

                    bos.write(myByteArray, 0, current);
                    bos.close();

                        //Grava no banco
                        if(GravarDados.s_GravarDados.gravarRecebimentoArquivo(c_codArquivo, c_nomeArquivo, c_codEmissor)){

                            c_telaRecebeArq.pb_recebendoArquivo.setValue(c_tamanhoArquivo);
                            c_telaRecebeArq.lb_logoBaixando.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LOGO_ARQ_BAIXADO)));
                            c_telaRecebeArq.lb_iconeRecebendoArq.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.STATUS_ARQUIVO_BAIXADO)));
                            c_telaRecebeArq.lb_acompanhaRecebimento.setText(Avisos.TEXTO_ARQ_RECEBIDO_DE+c_apelidoEmissor+" ["+current+" "+Avisos.TEXTO_DE+" "+c_tamanhoArquivo+" byte(s)]");
                            c_telaRecebeArq.bt_abrirArquivo.setVisible(true);
                            c_telaRecebeArq.bt_verDepois.setText(Avisos.TEXTO_FECHAR);
                            
                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_BAIXADO, "atu");
                        
                        }else{

                            c_telaRecebeArq.dispose();
                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_ERRO_BAIXAR, "erro");

                        }


                    l_socketClienteTCP.close();


                }else{

//                    //puxa do banco....
//                    if(l_codStatus == 2){
//
//                        System.err.println(c_bytesArquivo);
//
//                        InputStream l_bytesArquivo = new ByteArrayInputStream(c_bytesArquivo);
//
//                       //recebendo arquivo
//                        byte[] myByteArray = new byte[c_tamanhoArquivo];
//
//                        FileOutputStream fos = new FileOutputStream(c_diretorioBaixados+"\\"+c_nomeArquivo);
//
//                        BufferedOutputStream bos = new BufferedOutputStream(fos);
//
//                        System.out.println("Lendo bytes...");
//                        bytesRead = l_bytesArquivo.read(myByteArray, 0, myByteArray.length);
//
//                        current = bytesRead;
//
//                        int l_valorMaximoProgessBar = c_tamanhoArquivo / current;
//                        c_telaRecebeArq.pb_recebendoArquivo.setMaximum(l_valorMaximoProgessBar);
//
//                            do{
//
//                                c_telaRecebeArq.pb_recebendoArquivo.setValue(current);
//                                c_telaRecebeArq.lb_acompanhaRecebimento.setText("Recebendo arquivo de "+c_apelidoEmissor+"... ["+current+" de "+c_tamanhoArquivo+" byte(s)]");
//
//                                bytesRead = l_bytesArquivo.read(myByteArray, current, (myByteArray.length -current));
//
//                                    if(bytesRead >= 0)
//                                        current += bytesRead;
//
//                            }while(bytesRead != 0);
//
//                        bos.write(myByteArray, 0, current);
//
//                        bos.flush();
//
//                        bos.close();
//
//                        l_bytesArquivo.close();
//
//                            System.err.println("GRAVADO");
//
//                            c_telaRecebeArq.pb_recebendoArquivo.setMaximum(c_tamanhoArquivo);
//                            c_telaRecebeArq.pb_recebendoArquivo.setValue(c_tamanhoArquivo);
//                            c_telaRecebeArq.lb_logoBaixando.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LOGO_ARQ_BAIXADO)));
//                            c_telaRecebeArq.lb_iconeRecebendoArq.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.STATUS_ARQUIVO_BAIXADO)));
//                            c_telaRecebeArq.lb_acompanhaRecebimento.setText("Arquivo recebido de "+c_apelidoEmissor+" ["+c_tamanhoArquivo+" de "+c_tamanhoArquivo+" byte(s)]");
//                            c_telaRecebeArq.bt_abrirArquivo.setVisible(true);
//                            c_telaRecebeArq.bt_verDepois.setText("Fechar");
//
//                            System.out.println("Arquivo recebido!");
////                           MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_ERRO_NAO_ENCONTRADO, "erro");
//                        }

//                        else {

                            c_telaRecebeArq.dispose();

                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_ERRO_BAIXAR, "erro");

//                        }

                    l_socketClienteTCP.close();

                }

        }
        catch(Exception e){
            System.out.println("Erro no cliente TCP.\n\nErro: "+e);
        }


    }
}
