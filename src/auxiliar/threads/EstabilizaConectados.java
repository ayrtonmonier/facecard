/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;

import auxiliar.*;
import auxiliar.sockets.ClienteUDP;
import formularios.Aguardando;
import formularios.Moniersn;

/**
 *
 * @author Ayrton Monier
 */
public class EstabilizaConectados implements Runnable{

    private int[] c_codUsuarios;
    private String[] c_hosts;
    private int c_codUsuario;
    private int c_tipo;
    private int[] c_portasTCP;
    private int c_qtdConectados;

    public EstabilizaConectados(int l_codUsuario, int l_tipo){

        c_codUsuario = l_codUsuario;
        c_tipo = l_tipo;
        
        c_qtdConectados = SolicitarDados.s_SolicitarDados.pegaQtdConectados(c_codUsuario);

    }

    public void run(){
        
//        String l_apelidoPesquisado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(Moniersn.tf_pesquisaUsuario.getText().toLowerCase());

            if(c_qtdConectados > 0){

                int l_qtd = 0;
//                Socket l_socketClienteTCP = null;

                c_hosts = new String[c_qtdConectados];
                c_portasTCP = new int[c_qtdConectados];
                c_codUsuarios = new int[c_qtdConectados];

                String l_sqlSelect = "SELECT cod_usuario, status_contato, host, porta_udp, porta_tcp "
                                   + "FROM usuario "
                                   + "WHERE status_contato = 'conectado' "
                                   + "AND ativo = 'S' "
                                   + "AND cod_usuario != "+c_codUsuario+" "
//                                   + "AND moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                                   + "ORDER BY status_contato ASC, moniersn ASC ";
//                                   + "LIMIT 0, "+LogUsuario.s_qtdUsuMostrar;      

                

                Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                        try{

                            if(Dados.s_conexaoBanco.c_resultset.first()){

                                do{
                                    c_hosts[l_qtd] = Dados.s_conexaoBanco.c_resultset.getString("host");
                                    c_portasTCP[l_qtd] = Dados.s_conexaoBanco.c_resultset.getInt("porta_tcp");
                                    c_codUsuarios[l_qtd] = Dados.s_conexaoBanco.c_resultset.getInt("cod_usuario");

                                    l_qtd++;

                                }while(Dados.s_conexaoBanco.c_resultset.next());
                            
                            }else
                                return;

                        }catch(Exception e){

                            System.err.println("As informações de usuários conectados nao puderam ser buscadas.  \nErro: "+e);
                            return;
                        }

                            for(l_qtd = 0; l_qtd < c_qtdConectados; l_qtd++){
                                
//                                System.err.println("Usuário: "+c_codUsuarios[l_qtd]+" Host: "+c_hosts[l_qtd]+" Porta tcp: "+c_portasTCP[l_qtd]);
                                SolicitarDados.s_SolicitarDados.definicaoDeUsuarioConectado(c_codUsuarios[l_qtd], c_hosts[l_qtd], c_portasTCP[l_qtd]);

                            }
        }
            
            //PREENCHER LISTA DE AMIGOS
            MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(c_codUsuario, LogUsuario.s_listaAmigosAtual);

            //verificar local de arquivos
            SolicitarDados.s_SolicitarDados.verificaLocalArquivos(c_codUsuario, LogUsuario.s_localDeArquivosDoUsuario, "atu");

            
                //caso seja ao iniciar
                if(c_tipo == 0){
                                

                    //ENVIO DE DATAGRAMAS PARA AMIGOS CONECTADOS E NÃƒO BLOQUEADOS
                    System.out.println("Iniciando envio de datagramas de aviso para amigos conectados...");
                    ClienteUDP.enviaAvisoParaTodosConectados(1, "geral");

                    //MOSTRA A TELA PRINCIPAL
                    System.out.println("Tornando tela inicial visivel...");
                    Moniersn.s_telaMsn.setVisible(true);

                        if(LogUsuario.s_emitirAvisoSonovo.equals("S"))
                            new Thread(new Audio("bem-vindo.wav")).start();

                    System.out.println("Inicializacao concluida com exito");                

                }

            //esconde tela carregando
            Aguardando.s_telaAguardando.setVisible(false); 
            
    }


}

