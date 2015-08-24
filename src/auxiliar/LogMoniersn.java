/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;



import auxiliar.sockets.ClienteUDP;
import formularios.Moniersn;
import formularios.Login;
import auxiliar.sockets.ServidorTCP;
import auxiliar.sockets.ServidorUDP;
import formularios.Aguardando;
import formularios.Inicio;
/**
 *
 * @author Ayrton Monier
 */
public class LogMoniersn {

    public static LogMoniersn s_LogMoniersn = new LogMoniersn();

    //verifica a autenticidade do usuario para pegar informações que estarão disponíveis durante a execuçã da aplicação
    public void conectar(String l_moniersn, String l_senha){

        boolean l_tudoOk = false;
        String l_registrado = null;
        int l_efetuado = 0;

        try{

            String l_sqlSelect = "SELECT u.*, c.*, cc.*  "
                               + "FROM usuario u, configuracoes_usuario c, configuracoes_cores cc "
                               + "WHERE u.cod_usuario = c.cod_usuario "
                               + "AND u.cod_usuario = cc.cod_usuario "
                               + "AND u.moniersn = '"+l_moniersn+"'";

            //executa query que pega informações
            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

            //caso usuario exista
            if(Dados.s_conexaoBanco.c_resultset.first()){


                l_registrado = Dados.s_conexaoBanco.c_resultset.getString("u.ativo");

                //guarda o cod do usuário
                LogUsuario.s_cod = Dados.s_conexaoBanco.c_resultset.getInt("u.cod_usuario");

                //guarda a senha original
                LogUsuario.s_senha = Dados.s_conexaoBanco.c_resultset.getString("u.senha");

                //guarda o status atual
                LogUsuario.s_status = Dados.s_conexaoBanco.c_resultset.getString("u.status_contato");

                    //verifica se a senha que foi pega no banco e a mesma que esta no campo senha
                    if(LogUsuario.s_senha.equals(l_senha)){

                        //se o usuário estiver logado em outro lugar (se o usuário nã)
                        if(LogUsuario.s_status.equals("conectado")){

                            //pega nome do host e porta ao qual esta conectado atualmente
                            LogUsuario.s_host = Dados.s_conexaoBanco.c_resultset.getString("u.host");
                            LogUsuario.s_portaUDP = Dados.s_conexaoBanco.c_resultset.getInt("u.porta_udp");

                            //pergunta se deseja deslogar o usuário e entrar novamente
                            boolean confirm = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_ENTRAR_NOVAMENTE, Avisos.ENTRAR_NOVAMENTE, Icones.STATUS_CONECTADO_16);

                                //caso responda SIM, manda um datagrama fazendo a aplicação que está em execução fechar.
                                if(confirm){
                                    //aviso de datagrama para derrubar usuario logado
                                    System.out.println("Iniciando datagrama de desativação de usuário logado...");
                                    //envia datagrama
                                    ClienteUDP.s_socketClienteDatagrama = new ClienteUDP(LogUsuario.s_host, LogUsuario.s_portaUDP, 12, LogUsuario.s_cod);
                                }
                                    //caso responda NÃO
                                    else{
                                    
                                        //esconde tela carregando
                                        Aguardando.s_telaAguardando.setVisible(false);
                                        
                                        //fecha a telinha e desconecta do banco de dados
                                        return;

                                    }
                        }//fim if(S_STATUS.equals("conectado"))

                        if(l_registrado.equals("N")){

                            //pergunta se deseja deslogar o usuário e entrar novamente
                            boolean l_confirma = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_ATIVAR_FACECARD, Avisos.ATIVAR_FACECARD, Icones.NOVO_16);

                                //CASO SIM, ATUALIZA REGISTRADO = S e segue...
                               if(l_confirma){

                                    String sql = "UPDATE usuario "
                                               + "SET ativo = 'S' "
                                               + "WHERE cod_usuario = "+LogUsuario.s_cod;

                                        try{
                                            l_efetuado = Dados.s_conexaoBanco.c_statement.executeUpdate(sql);
                                        }
                                        catch(Exception e){
                                            System.out.println("Erro ao tentar registrar o usuário. \nErro: "+e);
                                            Aguardando.s_telaAguardando.setVisible(false); 
                                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                                            return;
                                        }
                                            if(l_efetuado == 1){
                                                System.out.println("Facecard ativado com sucesso!");
                                                Aguardando.s_telaAguardando.setVisible(false); 
                                                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FACECARD_ATIVADO, "atu");
                                                return;
                                            }
                                }
                                    //caso responda NÃO
                                    else{
                                        //fecha a telinha
                                        Aguardando.s_telaAguardando.setVisible(false); 
                                        return;
                                    }
                        }


                        //INFORMAÇÕES BÁSICAS DO USUÁRIO
                        LogUsuario.s_cod = Dados.s_conexaoBanco.c_resultset.getInt("u.cod_usuario");
                        LogUsuario.s_moniersn = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                        LogUsuario.s_nome = Dados.s_conexaoBanco.c_resultset.getString("u.nome");
                        LogUsuario.s_sobrenome = Dados.s_conexaoBanco.c_resultset.getString("u.sobrenome");
                        LogUsuario.s_sexo = Dados.s_conexaoBanco.c_resultset.getString("u.sexo");
                        LogUsuario.s_dataHoraCadastroBD = Dados.s_conexaoBanco.c_resultset.getString("u.data_hora_cadastro");

                        //CONFIGURAÇÕES
                        //privacidade
                        LogUsuario.s_mostrarNome = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_nome");
                        LogUsuario.s_mostrarBalaoAviso = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_balao_aviso");
                        LogUsuario.s_emitirAvisoSonovo = Dados.s_conexaoBanco.c_resultset.getString("c.emitir_aviso_sonoro");
                        LogUsuario.s_solicitarSenhaAoConfig = Dados.s_conexaoBanco.c_resultset.getString("c.solicitar_senha_ao_config");
                        LogUsuario.s_solicitarSenhaAoMostrar = Dados.s_conexaoBanco.c_resultset.getString("c.solicitar_senha_ao_mostrar");
                        LogUsuario.s_quemBatePapoComigo = Dados.s_conexaoBanco.c_resultset.getString("c.quem_bate_papo_comigo");
                        LogUsuario.s_quemVeListaAmigos = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_lista_amigos");
                        LogUsuario.s_quemVeInfoBasicas = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_basicas");
                        LogUsuario.s_quemVeInfoContato = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_contato");
                        LogUsuario.s_quemVeInfoPessoal = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_pessoal");
                        LogUsuario.s_quemVeInfoEducacional = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_educ");
                        LogUsuario.s_quemVeInfoProfissional = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_prof");
                        LogUsuario.s_quemVeOcorrencias = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_ocorrencia");
                        LogUsuario.s_quemVeRecPub = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_lista_rec_pub");
                        LogUsuario.s_quemMeEnviaArquivo = Dados.s_conexaoBanco.c_resultset.getString("c.quem_envia_arquivo");

                        String l_dirBanco = Dados.s_conexaoBanco.c_resultset.getString("c.local_arquivos");
                        LogUsuario.s_localDeArquivosDoUsuario = SolicitarDados.s_SolicitarDados.recolocaBarraDiretorioParaSO(l_dirBanco);

                        //INFORMAÇÕES DE SEGURANÇA
                        LogUsuario.s_pergutaSecreta = Dados.s_conexaoBanco.c_resultset.getString("u.pergunta_secreta");
                        LogUsuario.s_respostaSecreta = Dados.s_conexaoBanco.c_resultset.getString("u.resposta_secreta");

                        //INFORMAÇÕES DE CONEXAO (caso já esteja logado pega informações para envio de datagrama de logof)
                        LogUsuario.s_host = SolicitarDados.s_SolicitarDados.pegaHostUsuario();

                        l_tudoOk = true;

                       //a telinha de login é fechada
                        Login.s_telaLogin.dispose();
                        Login.s_telaLogin = null;
                        
                    }//fim (S_SENHA.equals(pf_senha.getText()))

                   //caso a senha não for igual a do banco
                    else{
                        //limpa os campos de usuario e senha
                        Login.cb_apelido.setSelectedItem("*");
                        Login.pf_senha.setText("");
                        Login.cb_apelido.requestFocus();

                        //esconde tela carregando
                        Aguardando.s_telaAguardando.setVisible(false);
                        
                        //apresenta mensagem de aviso
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ERRO_SENHA, "erro");
                    }

                 }
                  //caso usuaario não exista
                 else{

                      Login.cb_apelido.setSelectedItem("*");
                      Login.pf_senha.setText("");
                      Login.cb_apelido.requestFocus();

                      //esconde tela carregando
                      Aguardando.s_telaAguardando.setVisible(false);
                      
                      //apresenta mensagem de aviso
                      MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ERRO_SENHA, "erro");
                 }
        }
        catch(Exception e){
                System.err.println("Erro ao tentar carregar informações do usuário.   \nErro: "+e);
        }

        try{
            if(l_tudoOk){

                //tela principal
                Moniersn.s_telaMsn = new Moniersn(new javax.swing.JFrame(), true);
                
                
            }else
                 Aguardando.s_telaAguardando.setVisible(false);
            
        }
        catch(Exception e){
                Aguardando.s_telaAguardando.setVisible(false);
                System.err.println("Erro ao tentar inicializar.    \nErro: "+e);
        }
    }

    //desconecta do serviço
    public void desconectar(int l_tipo){

       boolean l_desconecta = false;

       if(l_tipo == 0)
           l_desconecta = true;
       else
           l_desconecta = MostrarDados.s_MostrarDados.mostraAvisoDesconectar();


           if(l_desconecta){

                System.out.println("\n---FINALIZAÇÃO DA APLICAÇÃO---\n");

                //AÇÕES NO BANCO
                
                
                //define usuário ocomo desconectado
                AtualizarDados.s_AtualizarDados.atualizaStatus(LogUsuario.s_cod, "desconectado");
                System.out.println("Status do usuario definido como desconectado.");

                //define que o campo host fica com valor host e o campo portA UDP fique com o valor 0;
                System.out.println("Padronizando host e porta no banco de dados...");
                padronizaHostPortaNoBanco();

                    if(l_tipo == 1){
                        //envia aviso de saida aos amigos on line
                        System.out.println("Enviando datagramas de aviso de desconexão...");
                        ClienteUDP.enviaAvisoParaTodosConectados(0, "geral");
                    }

                //grava log de desconexao
                System.out.println("Gravando log de desconexão...");
                logDesconectar(LogUsuario.s_codLogConexao);

                //desconecta usuario do banco
                System.out.println("Fechando conexão com o banco de dados...");
                Dados.s_conexaoBanco.desconecta();

                //remove icone e notificação
                System.out.println("Removendo ícone de notificação...");
                AreaNotificacao.removeIconeNotificacao();

                    //somente de o usuário solicitar desconexao(1)
                    /*obs: o socket estará ocupado como a execução deste método
                           e por isso nao posso parar a thread e o socket*/
                    if(l_tipo == 1){

                        //stopando o socket server
//                        ServidorUDP.s_socketServidor.close();
                        ServidorUDP.s_socketServidor = null;
                        System.out.println("Socket servidor UDP finalizado.");

                        ServidorTCP.s_socketServidor = null;
                        System.out.println("Socket servidor TCP finalizado.");

                   }

                //finaliza o sistema
                System.out.println("Aplicação finalizada com exito!\n");
                System.exit(0);

           }
           else
               return;
           
    }

    public void logDesconectar(int l_codLogConexao){

        Data.atualizaDataHora();

        String l_sqlInsert = "UPDATE log_usuario "
                            +" SET data_hora_logout = '"+Data.s_dataAtualAAAAMMDDHHMMSSParaBD+"'"
                            +"WHERE cod_log = "+l_codLogConexao;
        try{
            //executa query  e guarda o valor 1 se fro executado com sucesso
            int add = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlInsert);

            if(add == 1){

                //manda datagrama para avisar o amigo da aceitação e preencherá lista dele
                System.out.println("Log de desconexão gravado com exito no cod: "+LogUsuario.s_codLogConexao);

            }
        }
        catch(Exception e){
               System.err.println("Não foi gravar log de desconexao.    \nErro: "+e);
        }
    }

    public void logConectar(){

        Data.atualizaDataHora();
        String l_dataHoraConAtual = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;

        String l_sqlInsert = "INSERT INTO log_usuario(cod_usuario" +
                                              ", data_hora_login"+
                                              ", data_hora_logout)"+
                                              " VALUES("+LogUsuario.s_cod+
                                              ", '"+l_dataHoraConAtual+"'"+
                                              ", '"+l_dataHoraConAtual+"')";
        try{
            //executa query  e guarda o valor 1 se fro executado com sucesso
            int add = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlInsert);

            if(add == 1){

                LogUsuario.s_codLogConexao = SolicitarDados.s_SolicitarDados.buscaLogConexaoAtual(LogUsuario.s_cod);
                //manda datagrama para avisar o amigo da aceitação e preencherá lista dele
                System.out.println("log de conexao gravado com exito. cod: "+LogUsuario.s_codLogConexao);

            }
        }
        catch(Exception e){
               System.err.println("Erro ao tentar gravar log de conexao.    \nErro: "+e);
        }
    }

    public void logErro(String l_nomeMetodo, String l_erroTecnico, String l_descricaoErro){

        Data.atualizaDataHora();
        String l_dataHoraErro = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;

        String l_sqlInsert = "INSERT INTO log_erro(cod_usuario" +
                                              ", nome_do_metodo"+
                                              ", erro_tecnico" +
                                              ", descricao_do_erro" +
                                              ", data" +
                                              ", hora)" +
                                              " VALUES("+LogUsuario.s_cod+
                                              ", '"+l_nomeMetodo+"'"+
                                              ", '"+l_erroTecnico+"'"+   //aceito
                                              ", '"+l_descricaoErro+"'"+
                                              ", '"+l_dataHoraErro+"')";
        try{
            //executa query  e guarda o valor 1 se fro executado com sucesso
            int add = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlInsert);

            if(add == 1){
                //manda datagrama para avisar o amigo da aceitação e preencherá lista dele
                System.out.println("log de erro gravado com exito!");

            }
        }
        catch(Exception e){

               System.err.println("Não foi gravar log de erro.  \nErro: "+e);
        }
    }

    public void padronizaHostPortaNoBanco(){
        //da um select na tabela de usuário
//        Dados.s_conexaoBanco.executeSELECT("SELECT * "
//                                           + "FROM usuarios ");

        //padroniza o host e a porta no banco
        String l_sqlUpdate = "UPDATE usuario "
                           + "SET host = 'host', porta_udp = 0, porta_tcp = 0 "
                           + "WHERE cod_usuario = "+LogUsuario.s_cod;

        try{
            //caso atualize retornará "1"
            int ok = Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlUpdate);

            //caso 1 e porque a query foi executada com sucesso
            if(ok == 1)
                System.out.println("Padronização de host e porta aplicada no usuário de cód.: "+LogUsuario.s_cod);
            else
                System.err.println("Erro ao tentar aplicar padronização de host e porta");

        }
        catch(Exception e){
            System.err.println("Erro ao tentar aplicar padronização de host e porta.    \nErro: "+e);
        }
    }




}
