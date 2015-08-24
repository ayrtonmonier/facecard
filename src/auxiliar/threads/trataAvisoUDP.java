/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;



import auxiliar.AreaNotificacao;
import auxiliar.Audio;
import auxiliar.Avisos;
import auxiliar.Data;
import auxiliar.LogAmigo;
import auxiliar.LogMoniersn;
import auxiliar.LogUsuario;
import auxiliar.MostrarDados;
import auxiliar.SolicitarDados;
import formularios.SolicitacaoDeAmizade;
import formularios.Moniersn;
import formularios.DecisaoDeSolicitacaoDeAmizade;

/**
 *
 * @author Ayrton Monier
 */
public class trataAvisoUDP implements Runnable {


    private int l_codAviso;
    private int l_codEmissor;
    private String l_tipoAmigoEmissor;
    private String c_recadoInstantaneo;
    private String c_recadosInstantaneosRecebidos = "";
    private String c_apelidoEmissorRecInst;
    private int c_codDestinatarioAviso;
    private String c_tipoAmigoDestinatario;
    private String c_destinatarioAviso;
    private String c_dadosRecebidos;



    public trataAvisoUDP(String l_dadosRecebidos){

        this.c_dadosRecebidos = l_dadosRecebidos;

    }

    public void run(){

        //pega o tipo de aviso recebido
        this.l_codAviso = SolicitarDados.s_SolicitarDados.pegaCodigoDoAvisoUDP(c_dadosRecebidos);

        //pega id do usuário emissor
        this.l_codEmissor = SolicitarDados.s_SolicitarDados.pegaCodigoEmissorAvisoUDP(c_dadosRecebidos);

        //define o tipo de usuario que enviou o aviso
        this.l_tipoAmigoEmissor = SolicitarDados.s_SolicitarDados.defineTipoAmigo(LogUsuario.s_cod, l_codEmissor);

            System.out.println("#Aviso recebido\n>Cod. emissor: "+l_codEmissor+"\n>Cod. aviso: "+l_codAviso);

        //desconecta
        if(l_codAviso == 0){

            //CONTAGEM DE ´TENS DO USUÁRIO
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHER A LISTA DE AMIGOS
            MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

                //PREENCHER A LISTA DE ARQUIVOS DISPONIVEIS CASO VISIVEL
                if(LogUsuario.s_listaArquivoSelecionada.equals("disponiveis"))
                    MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, Moniersn.jt_listaArquivosUsuario, "disponiveis");


                //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor) &&
                   !l_tipoAmigoEmissor.equals("aceito-bloqueou-me") && !l_tipoAmigoEmissor.equals("aceito-bloqueados")){
                    MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
                    
                }

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO, A TELA TA INVISÍVEL E FOR ACEITO-OK
                if(LogUsuario.s_mostrarBalaoAviso.equals("S") && l_tipoAmigoEmissor.equals("aceito-ok")){
                       //mostra aviso de desconexao
                       MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.DESCONECTADO);
                }

                //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO, A TELA TA INVISÍVEL E FOR ACEITO-OK
                if(LogUsuario.s_emitirAvisoSonovo.equals("S") && l_tipoAmigoEmissor.equals("aceito-ok")){
                      new Thread(new Audio("pessoa-desconectou.wav")).start(); 
                }

            return;
        }

        //CASO CONECTOU
        else if(l_codAviso == 1){

                //CONTAGEM DE ´TENS DO USUÁRIO
                try{
                    SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);
                }catch(Exception e){
                    System.err.println("Errro ao solicitar contagem de itens.   Erro: "+e);
                }
                
            //PREENCHER A LISTA DE AMIGOS
            MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

                //PREENCHER A LISTA DE ARQUIVOS DISPONIVEIS CASO VISIVEL
                if(LogUsuario.s_listaArquivoSelecionada.equals("disponiveis"))
                    MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, Moniersn.jt_listaArquivosUsuario, "disponiveis");


                //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor) &&
                  !l_tipoAmigoEmissor.equals("aceito-bloqueou-me") && !l_tipoAmigoEmissor.equals("aceito-bloqueados")){
                    MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
            }

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO, A TELA TA INVISÍVEL E FOR ACEITO-OK
                if(LogUsuario.s_mostrarBalaoAviso.equals("S") && l_tipoAmigoEmissor.equals("aceito-ok")){
                       //mostra aviso de desconexao
                       MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.CONECTADO);
                }

                //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO, A TELA TA INVISÍVEL E FOR ACEITO-OK
                if(LogUsuario.s_emitirAvisoSonovo.equals("S") && l_tipoAmigoEmissor.equals("aceito-ok")){
                      new Thread(new Audio("pessoa-conectou.wav")).start();  
                }

        }

        //RECADO INSTANTANEO
        else if(l_codAviso == 2){


               Data.atualizaDataHora();

                //pega o APELIDO emissor
                c_apelidoEmissorRecInst = SolicitarDados.pegaApelidoPeloCodigo(l_codEmissor);

                //pega o recado
                c_recadoInstantaneo = SolicitarDados.s_SolicitarDados.pegaRecadoInstantaneoUDP(c_dadosRecebidos);

                //TODOS RECADOS INSTANTANEOS
                c_recadosInstantaneosRecebidos += Moniersn.tp_recadosInstantaneos.getText()+"\n\n";

                //MOSTRA MAIS UM
                c_recadosInstantaneosRecebidos += "("+Data.s_horaAtualHHMM+") - "+c_apelidoEmissorRecInst+" "+Avisos.TEXTO_DISSE+":\n"
                                                +">>"+c_recadoInstantaneo;

               //SETA O RECADO INST.
               Moniersn.tp_recadosInstantaneos.setText(c_recadosInstantaneosRecebidos);

               //COLOCA O ULTIMO NA VISUALIZAÇÃO
               Moniersn.tp_recadosInstantaneos.requestFocus();
               Moniersn.tp_recadosInstantaneos.setCaretPosition(Moniersn.tp_recadosInstantaneos.getText().length());

               //LIMPA A VARIAVEL DE RECADO INSTANTANEO
               c_recadosInstantaneosRecebidos = "";

                   //CASO A TELA ESTEJ AINVIÍVEL NA HORA DO RECADO 99888921
                   if(!Moniersn.jp_batePapo.isVisible() ||
                       Moniersn.jp_batePapo.isVisible() && !Moniersn.s_telaMsn.isVisible()){

                       //MOSTRA O ICONE DE NOVO RECADO NO BATE PAPO (AVISO)
                        LogUsuario.s_qtdRecInstRec++;
                        Moniersn.lb_qtdRecInst.setText("("+LogUsuario.s_qtdRecInstRec+")");
                        Moniersn.lb_qtdRecInst.setVisible(true);
                        AreaNotificacao.atualizaTipTextTrayIcon();

                   }
                   //CASO A TELA ESTEJ VISÍVEL
                   else{
                       //ZERA A VAR CONTADORA DE NOVO REC. INSTANT
                       Moniersn.lb_qtdRecInst.setVisible(false);
                       LogUsuario.s_qtdRecInstRec = 0;

                   }
                   
                    if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                        new Thread(new Audio("bate-papo.wav")).start();
                    }
                   
                   
        }

        //RECADO PUBLICO
        else if(l_codAviso == 3){

//            if(!l_tipoAmigoEmissor.equals("aceito-bloqueei") && !l_tipoAmigoEmissor.equals("aceito-bloqueou-me") &&
//               !l_tipoAmigoEmissor.equals("aceito-bloqueados")){

                //pega o código do destinatario do aviso
                c_codDestinatarioAviso = SolicitarDados.s_SolicitarDados.pegaCodDestinatarioAvisoUDP(c_dadosRecebidos);

                //DEFINIÇÃO DE TIPO DE AMIGO (destinatário do rec pub)
                if(c_codDestinatarioAviso != LogUsuario.s_cod)
                    c_tipoAmigoDestinatario = SolicitarDados.s_SolicitarDados.defineTipoAmigo(LogUsuario.s_cod, c_codDestinatarioAviso);
                else
                    c_tipoAmigoDestinatario = "aceito-ok";
                            
                //VERIFICA esquema de rec. pub
                if(LogUsuario.s_quemVeRecPub.equals("todos") ||
                   LogUsuario.s_quemVeRecPub.equals("amigos") && c_tipoAmigoDestinatario.equals("aceito-ok")){
                    
                        //SE IGUAL AO COD DO USUÁRIO (e pra ele)
                        if(c_codDestinatarioAviso == LogUsuario.s_cod){
                            c_destinatarioAviso = Avisos.TEXTO_PARA_VOCE;

                            //CONTAGEM DE ÍTENS
                            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                            //CASO LISTA DE REC. PUB. RECEBIDA SELECIONADA...
                            if(LogUsuario.s_listaRecPubSelecionada.equals("rec"))
                                MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPubUsuario, "rec");

                        }
                        else
                            c_destinatarioAviso = Avisos.TEXTO_PARA.toLowerCase()+SolicitarDados.pegaApelidoPeloCodigo(c_codDestinatarioAviso);

                        //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                        if(LogUsuario.s_mostrarBalaoAviso.equals("S"))
                            MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.REC_PUBLICO+" "+c_destinatarioAviso);


                        //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO, A TELA TA INVISÍVEL E FOR ACEITO-OK
                        if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                              new Audio("aviso.wav").run();
                        }
                }
//            }
        }

        //RECADO PRIVADO
        else if(l_codAviso == 4){

            //CONTAGEM DE ÍTENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //CASO LISTA DE REC. PRIV. RECEBIDA SELECIONADA...
            if(LogUsuario.s_listaRecPrivSelecionada.equals("rec") ||
               LogUsuario.s_listaRecPrivSelecionada.equals("naoLido"))
                MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPriv, LogUsuario.s_listaRecPrivSelecionada);

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                       //mostra aviso de desconexao
                       MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.REC_PRIVADO);
                }

                //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO, A TELA TA INVISÍVEL E FOR ACEITO-OK
                if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                      new Audio("aviso.wav").run();
                }

        }

        //COMPRIMENTO BOM DIA BOA TARDE E BOA NOITE...
        else if(l_codAviso == 5){
            //ATUALIZA DATA
            Data.atualizaDataHora();

            //PEGA VALOR DO COMPRIMENTO (BOM DIA, BOA TARDE ou BOA NOOITE)
            String l_comprimento = Data.comprimentacao()+", "+Avisos.COMPRIMENTO+", "+LogUsuario.s_nome+"!!!";

            //MOSTRA MSG
            MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, l_comprimento.toLowerCase()+"!");
            
            //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO, A TELA TA INVISÍVEL E FOR ACEITO-OK
            if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                new Audio("atencao.wav").run();
            }

        }

        //ENVIO DE ARQUIVO
        else if(l_codAviso == 6){


           if(LogUsuario.s_quemMeEnviaArquivo.equals("todos") ||
             (LogUsuario.s_quemMeEnviaArquivo.equals("amigos") && l_tipoAmigoEmissor.equals("aceito-ok"))){

                //CONTAGEM DE ÍTENS
                SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                //PREENCHE LISTA DE ARQUIVOS DISPONIVEIS CASO SELECIONADO
                if(LogUsuario.s_listaArquivoSelecionada.equals("disponiveis"))
                    MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, Moniersn.jt_listaArquivosUsuario, "disponiveis");


                //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor)){
                    
                    MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codEmissor);
                }

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                    //MOSTRA MSG
                    MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.COMPARTILHAMENTO_DE_ARQUIVO);
                }
                
                //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO, A TELA TA INVISÍVEL E FOR ACEITO-OK
                if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                      new Audio("aviso.wav").run();
                }

           }

        }


        //TUALIZA LISTA DE AMIGOS
        else if(l_codAviso == 7){

            //USADO PARA: CANCELAMENTO DE SOLICITAÇÃO, IGNORAR SOLICITAÇÃO, ATU. CONFIG. DE REC. PUB
            //ALTERACOES NA CONFIG BATEPAPO

            //CONTAGEM DE ÍTENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHE LISTA DE AMIGO
            MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

                //CASO O AVISO SEJA DE CANCELAMENTO DE SOLICITAÇÃO
                if(LogUsuario.s_mostrandoSolicitacaoAmizade && LogUsuario.s_codAmigoSolicitacao == l_codEmissor){
                       DecisaoDeSolicitacaoDeAmizade.s_telaSolicitacaoAmizade.dispose();
                       DecisaoDeSolicitacaoDeAmizade.s_telaSolicitacaoAmizade = null;
                }

                //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor) &&
                   !l_tipoAmigoEmissor.equals("aceito-bloqueou-me") && !l_tipoAmigoEmissor.equals("aceito-bloqueados")){
                    MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codEmissor);
                }

        }

        //SOLICITOU AMIZADE
        else if(l_codAviso == 8){

            //CONTAGEM DE ITENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHE LISTA DE AMIGO
            MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);


                //CASO A TELA DE CONVITE ESTIVER ABERTA
                if(LogUsuario.s_mostrandoTelaConvite && LogUsuario.s_codAmigoSolicitacao == l_codEmissor){
                        //fecha tela de convite
                        SolicitacaoDeAmizade.s_telaConvite.dispose();
                        LogUsuario.s_mostrandoTelaConvite = false;
                        SolicitacaoDeAmizade.s_telaConvite = null;
                }

                //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor) &&
                   LogAmigo.s_abaAtualSelecionada.equals(Moniersn.jp_inicioAmigo)){
                    MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codEmissor);
                }

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                       //mostra aviso de desconexao
                      MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.SOLICITOU_AMIZADE);
                }

                //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO, A TELA TA INVISÍVEL E FOR ACEITO-OK
                if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                      new Audio("aviso.wav").run();
                }

        }

        //ACEITOU SOLICITACAO
        else if(l_codAviso == 9){

            //CONTAGEM DE ITENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHE LISTA DE AMIGOS
            MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

            //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
            if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor) &&
               LogAmigo.s_abaAtualSelecionada.equals(Moniersn.jp_inicioAmigo)){
                    MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod);
            }

            //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
            if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                   //mostra aviso de desconexao
                  MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.ACEITOU_SOLICITACAO);
            }
            
            //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
            if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                  new Audio("aviso.wav").run();
            }

        }

        //MARCOU RECADO PUBLICO
        else if(l_codAviso == 10){

            //CONTAGEM DE ITENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                //CASO REC. PUB. ENV.SELECIONADA (PREENCHE LISTA DE REC. PUB. ENVIADA)
                if(LogUsuario.s_listaRecPubSelecionada.equals("env"))
                    MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPubUsuario, "env");

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                       //mostra aviso de desconexao
                      MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.MARCOU_REC_PUB);
                }
                
                //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
                if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                    new Audio("aviso.wav").run();
                }

                
        }

        //MARCOU RECADO PRIVADO
        else if(l_codAviso == 11){

            //CONTAGEM DE ITENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //CASO REC. PRIV. ENV.SELECIONADA (PREENCHE LISTA DE REC. PRIV. ENVIADA)
            if(LogUsuario.s_listaRecPrivSelecionada.equals("env"))
                MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPriv, "env");

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                       //mostra aviso de desconexao
                      MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.MARCOU_REC_PRIV);
                }
                
                //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
                if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                    new Audio("aviso.wav").run();
                }

        }

        //DERRUBAR USUARIO (emergencia)
        else if(l_codAviso == 12){

            //DESCONECTA SEM CONFIRMAÇÃO
            LogMoniersn.s_LogMoniersn.desconectar(0);
        }

        //NOVA OCORRÊNCIA NÃO ANÔNIMA
        else if(l_codAviso == 13){


            //CONTAGEM DE ITENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHER LISTA DE OCORRENCIAS RECEBIDAS
            if(LogUsuario.s_listaOcorrenciaSelecionada.equals("rec"))
                MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, Moniersn.jt_listaOcorrenciaUsuario, "rec");

            //CASO OCORRÊNCIA RECEBIDA E NAO VISUALIZANDO GUIA DE OCORRENCIA
            if(!Moniersn.jp_ocorrenciaUsuario.isVisible()){
                //SETA A QTD. DE OCORRENCIAS NA GUIA PRINCIPAL
                LogUsuario.s_qtdOcoRecRecente++;
                Moniersn.jtp_painelTabuladoUsuario.setTitleAt(1, "("+LogUsuario.s_qtdOcoRecRecente+")");
            }
                else{
                    //MAS CASO ESTEJA VISÍVEL ZERA TUDO
                    Moniersn.jtp_painelTabuladoUsuario.setTitleAt(1, "(0");
                    LogUsuario.s_qtdOcoRecRecente = 0;
                }

            //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
            if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor)){
                MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod);
            }

            //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
            if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                //mostra aviso de desconexao
                MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.NOVA_OCORRENCIA);
            }

            if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                //mostra aviso sonoro
                new Audio("aviso.wav").run();
            }

          //ATUALIZA INFO. TRAY ICON
          AreaNotificacao.atualizaTipTextTrayIcon();

        }

        //NOVA OCORRÊNCIA ANÔNIMA
        else if(l_codAviso == 14){

            //CONTAGEM DE ITENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHER LISTA DE OCORRENCIAS RECEBIDAS
            if(LogUsuario.s_listaOcorrenciaSelecionada.equals("rec"))
                MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, Moniersn.jt_listaOcorrenciaUsuario, "rec");


            //CASO OCORRÊNCIA RECEBIDA E NAO VISUALIZANDO GUIA DE OCORRENCIA
            if(!Moniersn.jp_ocorrenciaUsuario.isVisible()){
                //SETA A QTD. DE OCORRENCIAS NA GUIA PRINCIPAL
                LogUsuario.s_qtdOcoRecRecente++;
                Moniersn.jtp_painelTabuladoUsuario.setTitleAt(1, "("+LogUsuario.s_qtdOcoRecRecente+")");
            }
                else{
                    //MAS CASO ESTEJA VISÍVEL ZERA TUDO
                    Moniersn.jtp_painelTabuladoUsuario.setTitleAt(1, "(0");
                    LogUsuario.s_qtdOcoRecRecente = 0;
                }

            //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
            if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                //mostra aviso de desconexao
                MostrarDados.s_MostrarDados.mostraAvisoRecebido(0, Avisos.NOVA_OCORRENCIA);
            }

            if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                //mostra aviso sonoro
                new Audio("aviso.wav").run();
            }

              AreaNotificacao.atualizaTipTextTrayIcon();
      }

        //NOVA ATUALIZACAO
        else if(l_codAviso == 15){


            if(l_tipoAmigoEmissor.equals("aceito-ok")){

                //PREENCHE LISTA DE ATUALIZAÇÕES RECEBIDAS DE AMIGOS
                if(LogUsuario.s_listaAtualizacoesSelecionada.equals("recAmigos"))
                     MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, Moniersn.jt_listaAtualizacoesUsuario, LogUsuario.s_listaAtualizacoesSelecionada);

                //CONTAGEM DE ATU. RECEBIDAS
                if(!Moniersn.jp_atualizacoesUsuario.isVisible()){
                    LogUsuario.s_qtdTotAtuRecebidasDeAmigos++;
                    LogUsuario.s_qtdAtuRecRecenteDeAmigos++;
                    LogUsuario.s_qtdTotAtuRecRecente++;
                    Moniersn.jtp_painelTabuladoUsuario.setTitleAt(2, "("+LogUsuario.s_qtdTotAtuRecRecente+")");
                }else{
                       Moniersn.jtp_painelTabuladoUsuario.setTitleAt(2, "(0)");
                       LogUsuario.s_qtdTotAtuRecebidasDeAmigos = 0;
                       LogUsuario.s_qtdTotAtuRecRecente = 0;
                }

            }else{

                //PREENCHE LISTA DE ATUALIZAÇÕES RECEBIDAS DE ESTRANHOS
                if(LogUsuario.s_listaAtualizacoesSelecionada.equals("recEstranhos"))
                        MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, Moniersn.jt_listaAtualizacoesUsuario, LogUsuario.s_listaAtualizacoesSelecionada);

                    //CONTAGEM DE ATU. RECEBIDAS DE ESTRANHOS
                    if(!Moniersn.jp_atualizacoesUsuario.isVisible()){
                        LogUsuario.s_qtdTotAtuRecebidasDeEstranhos++;
                        LogUsuario.s_qtdAtuRecRecenteDeEstranhos++;
                        LogUsuario.s_qtdTotAtuRecRecente++;
                        Moniersn.jtp_painelTabuladoUsuario.setTitleAt(2, "("+LogUsuario.s_qtdTotAtuRecRecente+")");
                    }else{
                           Moniersn.jtp_painelTabuladoUsuario.setTitleAt(2, "(0)");
                           LogUsuario.s_qtdTotAtuRecebidasDeEstranhos = 0;
                           LogUsuario.s_qtdTotAtuRecRecente = 0;
                    }
            }


            //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
            if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                //mostra aviso de desconexao
                MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.NOVA_ATUALIZACAO);
            }

            if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                //mostra aviso sonoro
                new Audio("aviso.wav").run();
            }

            AreaNotificacao.atualizaTipTextTrayIcon();
       }

        //CONTEGEM DE ÍTENS e PREENCHER LISTAS
        else if(l_codAviso == 16){

            //CONTAGEM DE ÍTENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHER LISTA DE OCORRENCIAS RECEBIDAS
            if(LogUsuario.s_listaOcorrenciaSelecionada.equals("rec"))
                MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, Moniersn.jt_listaOcorrenciaUsuario, "rec");


            //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
            if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor) &&
               !l_tipoAmigoEmissor.equals("aceito-bloqueou-me") && !l_tipoAmigoEmissor.equals("aceito-bloqueados")){
                MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codEmissor);
            }



        }

        //REMARCOU RECADO PUBLICO
        else if(l_codAviso == 17){

            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            if(!LogUsuario.s_listaRecPubSelecionada.equals("env"))
                MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPubUsuario, LogUsuario.s_listaRecPubSelecionada);

            //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
            if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                //mostra aviso de desconexao
                MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.REMARCOU_REC_PUB);
            }
            
            //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
            if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                new Audio("aviso.wav").run();
            }
            
        }

        //REMARCOU RECADO PRIVADO
        else if(l_codAviso == 18){

            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            if(!LogUsuario.s_listaRecPrivSelecionada.equals("env"))
                MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPriv, LogUsuario.s_listaRecPrivSelecionada);

            //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
            if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                //mostra aviso de desconexao
                MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.REMARCOU_REC_PRIV);
            }
            
            //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
            if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                new Audio("aviso.wav").run();
            }

        }

        //COMENTOU RECADO PÚBLICO DO AMIGO
        else if(l_codAviso == 19){


            if(!l_tipoAmigoEmissor.equals("aceito-bloqueei") && !l_tipoAmigoEmissor.equals("aceito-bloqueou-me") &&
               !l_tipoAmigoEmissor.equals("aceito-bloqueados")){

                //pega o código do destinatario do aviso
                c_codDestinatarioAviso = SolicitarDados.s_SolicitarDados.pegaCodDestinatarioAvisoUDP(c_dadosRecebidos);

                //DEFINIÇÃO DE TIPO DE AMIGO (destinatário do rec pub)
                c_tipoAmigoDestinatario = SolicitarDados.s_SolicitarDados.defineTipoAmigo(LogUsuario.s_cod, c_codDestinatarioAviso);

                //VERIFICA esquema de rec. pub
                if(LogUsuario.s_quemVeRecPub.equals("todos") ||
                   LogUsuario.s_quemVeRecPub.equals("amigos") && c_tipoAmigoDestinatario.equals("aceito-ok")){

                        //SE IGUAL AO COD DO USUÁRIO (e pra ele)
                        if(c_codDestinatarioAviso == LogUsuario.s_cod){
                            c_destinatarioAviso = Avisos.TEXTO_SEU;

                            //CONTAGEM DE ÍTENS
                            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                            //CASO LISTA DE REC. PUB. RECEBIDA SELECIONADA...
                            MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPubUsuario, LogUsuario.s_listaRecPubSelecionada);

                        }
                        else
                            c_destinatarioAviso = Avisos.TEXTO_DE+" "+SolicitarDados.pegaApelidoPeloCodigo(c_codDestinatarioAviso);

                        //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                        if(LogUsuario.s_mostrarBalaoAviso.equals("S"))
                            MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.COMENTOU_REC_PUB_AMIGO+c_destinatarioAviso);
                        
                        //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
                        if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                            new Audio("aviso.wav").run();
                        }

                }
            }
        }

        //COMENTOU RECADO PÚBLICO DELE MESMO
        else if(l_codAviso == 20){

            if(!l_tipoAmigoEmissor.equals("aceito-bloqueei") && !l_tipoAmigoEmissor.equals("aceito-bloqueou-me") &&
               !l_tipoAmigoEmissor.equals("aceito-bloqueados")){

                //CONTAGEM DE ÍTENS
                SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                //PREENCHER LISTA DE RECADOS PUB
                MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPubUsuario, LogUsuario.s_listaRecPubSelecionada);

                    //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                    if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                        //mostra aviso de desconexao
                        MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.COMENTOU_REC_PUB_DELE);
                    }
                    
                    //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
                    if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                        new Audio("aviso.wav").run();
                    }
            }
        }

        //COMENTOU RECADO PRIVADO DO AMIGO
        else if(l_codAviso == 21){

            //CONTAGEM DE ÍTENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHER LISTA DE RECADOS PRIV
            MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPriv, LogUsuario.s_listaRecPrivSelecionada);

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                    //mostra aviso de desconexao
                    MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.COMENTOU_REC_PRIV_AMIGO);
                }
                
                //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
                if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                    new Audio("aviso.wav").run();
                }
        }


        //COMENTOU RECADO PRIVADO DELE
        else if(l_codAviso == 22){

            //CONTAGEM DE ÍTENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHER LISTA DE RECADOS PRIV
            MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPriv, LogUsuario.s_listaRecPrivSelecionada);

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                    //mostra aviso de desconexao
                    MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.COMENTOU_REC_PRIV_DELE);
                }
                
                //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
                if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                    new Audio("aviso.wav").run();
                }
        }

        //COMENTOU OCORRENCIA DELE
        else if(l_codAviso == 23){

            //CONTAGEM DE ÍTENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHER LISTA DE OCORRêNCIAS
            MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, Moniersn.jt_listaOcorrenciaUsuario, LogUsuario.s_listaOcorrenciaSelecionada);

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                    //mostra aviso de desconexao
                    MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.COMENTOU_OCO_DELE);
                }
                
                 //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
                if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                    new Audio("aviso.wav").run();
                }
        }

        //COMENTOU OCORRENCIA DE DO AMIGO
        else if(l_codAviso == 24){

            //CONTAGEM DE ÍTENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHER LISTA DE OCORRêNCIAS
            MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, Moniersn.jt_listaOcorrenciaUsuario, LogUsuario.s_listaOcorrenciaSelecionada);

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                    //mostra aviso de desconexao
                    MostrarDados.s_MostrarDados.mostraAvisoRecebido(l_codEmissor, Avisos.COMENTOU_OCO_AMIGO);
                }
                
                //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
                if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                    new Audio("aviso.wav").run();
                }
        }

        //ATUALIZA INFORMAÇÕES DO AMIGO EM QUESTAO
        else if(l_codAviso == 25){

                //CONTAGEM DE ÍTENS
                SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor) &&
                   !l_tipoAmigoEmissor.equals("aceito-bloqueou-me") && !l_tipoAmigoEmissor.equals("aceito-bloqueados")){
                    MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codEmissor);
                }
        }


        //BLOQUEIO E DESBLOQUEIO
        else if(l_codAviso == 26){

                //CONTAGEM DE ÍTENS
                SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor)){

                    MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codEmissor);

                }

                //PREENCHER A LISTA DE ARQUIVOS DISPONIVEIS CASO VISIVEL
                if(LogUsuario.s_listaArquivoSelecionada.equals("disponiveis"))
                    MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, Moniersn.jt_listaArquivosUsuario, "disponiveis");

                //PREENCHER LISTA DE OCORRENCIAS RECEBIDAS
                if(LogUsuario.s_listaOcorrenciaSelecionada.equals("rec"))
                    MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, Moniersn.jt_listaOcorrenciaUsuario, "rec");

                //PREENCHER LISTA DE RECADOS PÚBLICOS RECEBIDAS
                if(LogUsuario.s_listaRecPubSelecionada.equals("rec") ||
                   LogUsuario.s_listaRecPubSelecionada.equals("naoLido"))
                    MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPubUsuario, LogUsuario.s_listaRecPubSelecionada);

                //PREENCHER LISTA DE ATUALIZAÇÕES DE AMIGOS OU NAO AMIGOS
                if(LogUsuario.s_listaAtualizacoesSelecionada.equals("recEstranhos") ||
                   LogUsuario.s_listaAtualizacoesSelecionada.equals("recAmigos"))
                    MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, Moniersn.jt_listaAtualizacoesUsuario, LogUsuario.s_listaAtualizacoesSelecionada);

        }

        //NO CASO DE DESATIVAÇAO E O USUARIO ESTIVER OLHANDO O PAINEL DO DESATIVADOR...
        else if(l_codAviso == 27){

            //USADO PARA: CANCELAMENTO DE SOLICITAÇÃO, IGNORAR SOLICITAÇÃO, ATU. CONFIG. DE REC. PUB
            //ALTERACOES NA CONFIG BATEPAPO

            //CONTAGEM DE ÍTENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHE LISTA DE AMIGO
            MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

                //CASO O AVISO SEJA DE CANCELAMENTO DE SOLICITAÇÃO
                if(LogUsuario.s_mostrandoSolicitacaoAmizade && LogUsuario.s_codAmigoSolicitacao == l_codEmissor){
                       DecisaoDeSolicitacaoDeAmizade.s_telaSolicitacaoAmizade.dispose();
                       DecisaoDeSolicitacaoDeAmizade.s_telaSolicitacaoAmizade = null;
                }

                //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor) &&
                   !l_tipoAmigoEmissor.equals("aceito-bloqueou-me") && !l_tipoAmigoEmissor.equals("aceito-bloqueados")){
                    MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
                }

        }


        //ANÔNIMO COMENTOU OCORRENCIA...
        else if(l_codAviso == 28){

            //CONTAGEM DE ÍTENS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHER LISTA DE OCORRêNCIAS
            MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, Moniersn.jt_listaOcorrenciaUsuario, LogUsuario.s_listaOcorrenciaSelecionada);

                //CASO O USUÁRIO DESEJA RECEBER BALÃO AVISO
                if(LogUsuario.s_mostrarBalaoAviso.equals("S")){
                    //mostra aviso de desconexao
                    MostrarDados.s_MostrarDados.mostraAvisoRecebido(0, Avisos.COMENTOU_OCO);
                }
                
                //CASO O USUÁRIO DESEJA RECEBER AVISO SONORO
                if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                    new Audio("aviso.wav").run();
                }
        }
        
        
        

        
        
    }
}
