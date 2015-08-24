/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;
import javax.swing.*;
import formularios.*;
import auxiliar.sockets.ClienteUDP;


/**
 *
 * @author Ayrton Monier
 */
public class GravarDados {

    public static GravarDados s_GravarDados = new GravarDados();

 //adiciona o amigo na minha lista
    public void convidarPessoa(int l_codAmigo){

        Data.atualizaDataHora();
        String l_dataHoraAdd = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;

        String sql = "INSERT into contato_de_usuario(cod_usuario" +
                                                    ", cod_amigo" +
                                                    ", aceito" +
                                                    ", bloqueado"+
                                                    ", data_hora_add)" +
                                                    "VALUES ("+LogUsuario.s_cod+ //codigo do usuario que esta add
                                                    ", "+l_codAmigo+         //codigo do usuario que esta sendo add
                                                    ", 'N'"+                      //Não bloqueado
                                                    ", 'N'"+                      //Não aceito
                                                    ", '"+l_dataHoraAdd+"')";    //data hora add atual
        try{
            //caso execute a query add recebera 1 caso não recebera 0
            int add = Dados.s_conexaoBanco.c_statement.executeUpdate(sql);

            //caso 1...
            if(add == 1){

                //fecha tela de convite
                LogUsuario.s_mostrandoTelaConvite = false;
                SolicitacaoDeAmizade.s_telaConvite.dispose();
                SolicitacaoDeAmizade.s_telaConvite = null;

                //CONTAGEM DE ÍTENS
                SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                //ATUALIZA LISTA DE AMIGOS
                MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

                //DEFINE INFORMAÇÕES DO AMIGO
                MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codAmigo);

                //ENVIA AVISO DE CONVITE
                ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codAmigo, 8);

                //MOSTRA MSG DE ATUALIZAÇÃO
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_SOL_AMI_ENV, "atu");

            }else{
                System.err.println("Não foi possível convidar a pessoa");
            }
        }
        catch(Exception e){
               MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
               System.err.println("Não foi possível convidar esta pessoa.   \nErro: "+e);
        }

    }//fim adicionarAmigoNaLista()


    //ACEITAR SOLICITAÇÃO DE AMIGO
    public void aceitarSolicitacaoAmizade(int l_codAmigo){

        Data.atualizaDataHora();
        Boolean addAmigo = false;
        String l_dataHora = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;
        String l_sqlInsert;
        String l_sqlUpdate;
        String l_apelidoAmigo = SolicitarDados.pegaApelidoPeloCodigo(l_codAmigo);

        //seleciona a tabela de contato de usuario
        Dados.s_conexaoBanco.executeSELECT("SELECT * FROM contato_de_usuario");

        //ACEITA NA MINHA LISTA O CONVITE
        l_sqlUpdate = "UPDATE contato_de_usuario "
                       + "SET aceito = 'S' "
                       + "WHERE cod_usuario = "+l_codAmigo+" "
                       + "AND cod_amigo = "+LogUsuario.s_cod+" "
                       + "AND aceito = 'N'";
        try{
            //executa query >> Gravando o contato na minha lista...
            //caso gravado pode gravar o amigo
            if(Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlUpdate) == 1)
                addAmigo = true;
            else
                addAmigo = false;

        }

        catch(Exception e){
               addAmigo = false;
               System.err.println("Não foi possível adicionar o amigo na minha lista.   \nErro: "+e);
               MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
        }


        if(addAmigo){

            //CRIA NA LISTA DO AMIGO JÁ ACEITO
            l_sqlInsert = "INSERT INTO contato_de_usuario(cod_usuario" +
                                                        ", cod_amigo" +
                                                        ", aceito"+
                                                        ", bloqueado" +
                                                        ", data_hora_add)" +
                                                        " VALUES("+LogUsuario.s_cod+
                                                        ", "+l_codAmigo+
                                                        ", 'S'"+    //aceito
                                                        ", 'N'"+    //não boqueado
                                                        ", '"+l_dataHora+"')";
            try{
                //executa query  e guarda o valor 1 se fro executado com sucesso
                int add = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlInsert);

                if(add == 1){

                        //CONTAGEM DE ÍTENS
                        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);   

                        //ATUALIZA LISTA DE AMIGOS
                        MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

                        //GRAVA A ATUALIZAÇÃO DE QUEM ACEITOU
                        gravarAtualizacao("amigos", "lista_amigos", Avisos.COMECOU_AMIZADE+l_apelidoAmigo);


                            //CASO MOSTRANDO AMIGO
                            if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codAmigo) &&
                               LogAmigo.s_abaAtualSelecionada.equals(Moniersn.jp_inicioAmigo)){
                                    //ESCONDE PAINEL DO AMIGO e DEFINE INFORMAÇÕES
                                    MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codAmigo);
                            }

                        //MOSTRA MSG DE ATUALIZAÇÃO
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_SOL_AMI_ACEITA, "atu");

                }
            }
            catch(Exception e){

                   System.err.println("Não foi possível adicionar o amigo na minha lista.   \nErro: "+e);
                   MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
            }
        }
    }


//  REMOVER AMIGO NO BANCO
    public void removerAmigo(int l_codUsuario, int l_codAmigo){

       boolean l_confirma = false;

        l_confirma = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_REMOCAO_AMIGO, Avisos.REMOCAO_AMIGO, Icones.AMIGO_REMOCAO_16);

        if(l_confirma){

            String sql = "DELETE FROM contato_de_usuario "
                       + "WHERE cod_usuario = "+l_codUsuario+" "
                       + "AND cod_amigo = "+l_codAmigo;

            try{
                int l_efetuado = Dados.s_conexaoBanco.c_statement.executeUpdate(sql);

                if(l_efetuado == 1){

                    sql ="DELETE FROM contato_de_usuario "
                       + "WHERE cod_usuario = "+l_codAmigo+" "
                       + "AND cod_amigo = "+l_codUsuario;

                    l_efetuado = Dados.s_conexaoBanco.c_statement.executeUpdate(sql);

                    if(l_efetuado == 1){

                        //CONTAGEM DE ÍTENS
                        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                        //ATUALIZA LISTA DE AMIGOS
                        MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

                        //ESCONDE PAINEL DO AMIGO e DEFINE INFORMAÇÕES
                        MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codAmigo);

                        //AVISA AO AMIGO CONECTADO
                        ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codAmigo, 7);

                        //MOSTRA MSG DE ATUALIZAÇÃO
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_AMIGO_REMOV, "atu");

                    }
                }
            }
            catch(Exception e){
                System.err.println("Erro ao tentar remover amigo.   \nErro: "+e);
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
            }
        }
    }

//  CANCELAR SOLICITAÇÃO DE AMIZADE ENVIADA
    public void cancelarSolicitacaoAmigo(int l_codAmigo){

       boolean l_confirma = false;

        l_confirma = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_CANCELA_SOLIC_AO_AMIGO, Avisos.CANCELA_SOLICITACAO_AO_AMIGO, Icones.AMIGO_ADD_CANCELAR_16);

        if(l_confirma){

            String sql = "DELETE FROM contato_de_usuario "
                       + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                       + "AND cod_amigo = "+l_codAmigo;

            try{
                int l_efetuado = Dados.s_conexaoBanco.c_statement.executeUpdate(sql);

                if(l_efetuado == 1){

                        //CONTAGEM DE ÍTENS
                        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                        //ATUALIZA LISTA DE AMIGOS
                        MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

                        //ESCONDE PAINEL DO AMIGO e DEFINE INFORMAÇÕES
                        MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codAmigo);

                        //MANDA O AMIGO DEFINIR AS INFORMAÇÕES
                        ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codAmigo, 7);

                        //MOSTRA MSG DE ATUALIZAÇÃO
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_SOL_AMI_CANCEL, "atu");

                }
            }
            catch(Exception e){
                System.err.println("Erro ao tentar cancelar solicitação de amigo.   \nErro: "+e);
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
            }
        }
    }

    //IGNORAR SOLICITAÇÃO DE AMIZADE RECEBIDA
    public void ignorarSolicitacaoAmizade(int l_codAmigo){

       boolean l_confirma = false;

        l_confirma = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_CANCELA_SOLIC_DO_AMIGO, Avisos.CANCELA_SOLICITACAO_DO_AMIGO, Icones.AMIGO_REMOCAO_16);

        if(l_confirma){

            try{                                                                   //QUEM ME ADD                           //EU
                String l_sqlDeleta = "DELETE FROM contato_de_usuario "
                                   + "WHERE cod_usuario = "+l_codAmigo+" "
                                   + "AND cod_amigo = "+LogUsuario.s_cod+" "
                                   + "AND aceito = 'N'";
                //executa query
                int exclui = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlDeleta);

                        if(exclui == 1){

                            //CONTAGEM DE ÍTENS
                            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                            //ATUALIZA LISTA DE AMIGOS
                            MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

                                //CASO MOSTRANDO AMIGO
                                if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codAmigo) &&
                                   LogAmigo.s_abaAtualSelecionada.equals(Moniersn.jp_inicioAmigo)){
                                        //ESCONDE PAINEL DO AMIGO e DEFINE INFORMAÇÕES
                                        MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codAmigo);
                                }

                            //MANDA O AMIGO DEFINIR AS INFORMAÇÕES
                            ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codAmigo, 7);

                            //MOSTRA MSG DE ATUALIZAÇÃO
                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_SOL_AMI_IGNOR, "atu");

                        }
            }
            catch(Exception e){
                    System.err.println("Não foi possível add o contato em sua lista.    \nErro: "+e);
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
            }
       }
    }

   // gravar dados
   public void cadastrarUsuario(){

       //faz a verificação geral do formulário (ve se ta faltando algum campo)
       boolean l_podeGravar = SolicitarDados.s_SolicitarDados.validarCamposDeSeguranca();

   if(l_podeGravar){
           Data.atualizaDataHora();

           String l_novoNova = null;

           String l_nome = CadastroUsuario.s_telaCadastroMoniersn.tf_nome.getText().toUpperCase();
           String l_sobrenome = CadastroUsuario.s_telaCadastroMoniersn.tf_sobrenome.getText().toUpperCase();
           String l_iconeHumor = CadastroUsuario.s_telaCadastroMoniersn.cb_humor.getSelectedItem().toString();
           String l_sexo = SolicitarDados.s_SolicitarDados.pegaSexoSelecionado();
           String l_frase = null;
           String l_dataDDMMAAAA = CadastroUsuario.s_telaCadastroMoniersn.cb_dia.getSelectedItem().toString()+"-"+CadastroUsuario.s_telaCadastroMoniersn.cb_mes.getSelectedItem().toString()+"-"+CadastroUsuario.s_telaCadastroMoniersn.cb_ano.getSelectedItem().toString();
           String l_dataNascimento = Data.ajustaDataParaAAAAMMDD(Data.pegaDataTipoDateDDMMAAAA(l_dataDDMMAAAA, Data.DD_MM_AAAA));
           String l_apelido = CadastroUsuario.tf_moniersn.getText().toLowerCase();
           String l_senha = CadastroUsuario.pf_senha.getText().toLowerCase();
           String l_perguntaSecreta = CadastroUsuario.cb_pergunta_secreta.getSelectedItem().toString();
           String l_respostaSecreta = CadastroUsuario.tf_resposta_secreta.getText().toLowerCase();
           String l_dataHoraCadastro = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;


                if(l_sexo.equals("M"))
                    l_novoNova = Avisos.TEXTO_NOVO;
                else
                    l_novoNova = Avisos.TEXTO_NOVA;
           l_frase = Avisos.FRASE_INICIAL+l_novoNova+" "+Avisos.TEXTO_POR_AQUI;


           String l_SqlInsert = "INSERT INTO usuario("+
                                //informações básicas
                                "moniersn"+
                                ", ativo"+
                                ", nome"+
                                ", sobrenome"+
                                ", minha_frase"+
                                ", icone_humor"+
                                ", data_nascimento" +
                                ", sexo"+
                                ", data_hora_cadastro"+
                                //informações de segurança
                                ", senha"+
                                ", pergunta_secreta"+
                                ", resposta_secreta"+
                                //informações de conexao
                                ", status_contato"+
                                ", host"+
                                ", porta_udp"+
                                ", porta_tcp"+
                                //informações pessoais
                                ", naturalidade"+
                                ", nacionalidade" +
                                ", fisico" +
                                ", estado_civil" +
                                ", quem_eu_sou" +
                                ", adoro" +
                                ", odeio" +
                                ", filme" +
                                ", musica" +
                                ", esporte" +
                                ", lugar" +
                                ", cantada" +
                                ", mania" +
                                ", cor" +
                                ", conselho"+
                                ", time_futebol"+
                                ", sonho"+
                                ", pesadelo"+
                                ", bebida"+
                                ", prato"+
                                ", passeio"+
                                ", viagem"+
                                ", desejo"+
                                ", vontade"+
                                ", esperanca"+
                                ", hobby"+
                                //informações de contato
                                ", rua" +
                                ", numero" +
                                ", bairro" +
                                ", cidade" +
                                ", uf_endereco"+
                                ", fone" +
                                ", cel_oi"+
                                ", cel_tim"+
                                ", cel_vivo"+
                                ", cel_claro"+
                                ", cel_outro"+
                                ", email_hotmail"+
                                ", email_gmail"+
                                ", email_yahoo"+
                                ", email_ig"+
                                ", email_bol"+
                                ", email_outro"+
                                ", orkut"+
                                ", twitter"+
                                ", facebook"+
                                ", myspace"+
                                ", skype"+
                                ", linkedin"+
                                ", blog"+
                                //informações educacionais
                                ", instituicao_ensino"+
                                ", curso"+
                                ", turma"+
                                ", nao_gosto_inst"+
                                ", sempre_gosto_inst"+
                                ", vou_melhorar_inst"+
                                ", a_instituicao_e"+
                                ", esta_faltando_inst"+
                                ", nas_horas_vagas_inst"+
                                ", melhor_disciplina"+
                                ", pior_disciplina"+
                                ", minha_turma_e"+
                                //informações profissionais
                                ", empresa"+
                                ", cargo"+
                                ", setor"+
                                ", atividade_emp"+
                                ", nao_gosto_emp"+
                                ", sempre_gosto_emp"+
                                ", vou_melhorar_emp"+
                                ", a_empresa_e"+
                                ", esta_faltando_emp"+
                                ", email_corp_emp"+
                                ", fone_emp"+
                                ", ramal_emp"+
                                ", fax_emp"+
                                ", meu_setor_e)"+
                                "  VALUES(" +
                                "'"+l_apelido+"'"+                              //INFO. BÁSICAS
                                ", 'S'"+                                          //HABILITADO = S
                                ", '"+l_nome+"'"+                               //nome
                                ", '"+l_sobrenome+"'"+                          //sobrenome
                                ", '"+l_frase+"'"+                              //frase
                                ", '"+l_iconeHumor+"'"+                         //icone_humor
                                ", '"+l_dataNascimento+"'"+                     //data nascimento
                                ", '"+l_sexo+"'"+                               //sexo
                                ", '"+l_dataHoraCadastro+"'"+                   //data hora cadastro
                                ", '"+l_senha+"'"+        //INFO. SEGURANÇA     //senha
                                ", '"+l_perguntaSecreta+"'"+                    //pergunta secreta
                                ", '"+l_respostaSecreta+"'"+                    //resposta secreta
                                ", 'desconectado'"+       //INFO. CONEXÃO       //status contato
                                ", 'host'"+                                     //host name
                                ", 0"+                                          //host port UDP
                                ", 0"+                                          //host port TCP
                                ", '*'"+                //INFO. PESSOAL         //naturalidade
                                ", 'BRASILEIRO'"+                               //nacionalidade
                                ", '*'"+                                        //fisico
                                ", '*'"+                                        //estado civil
                                ", '*'"+                                      //quem eu sou
                                ", '*'"+                                      //adoro
                                ", '*'"+                                      //odeio
                                ", '*'"+                                      //filme
                                ", '*'"+                                      //musica
                                ", '*'"+                                      //esporte
                                ", '*'"+                                      //lugar
                                ", '*'"+                                      //cantada
                                ", '*'"+                                      //mania
                                ", '*'"+                                      //cor
                                ", '*'"+                                      //coselho
                                ", '*'"+                                      //time de futebol
                                ", '*'"+                                      //sonho
                                ", '*'"+                                      //pesadelo
                                ", '*'"+                                      //bebida
                                ", '*'"+                                      //prato
                                ", '*'"+                                      //passeio
                                ", '*'"+                                      //viagem
                                ", '*'"+                                      //desejo
                                ", '*'"+                                      //vontade
                                ", '*'"+                                      //esperanca
                                ", '*'"+                                      //hobby
                                ", '*'"+                //CONTATO             //rua
                                ", '*'"+                                      //numero
                                ", '*'"+                                      //bairro
                                ", '*'"+                                      //cidade
                                ", '*'"+                                        //uf endereco
                                ", '*'"+                                      //fone
                                ", '*'"+                                      //cel oi
                                ", '*'"+                                      //cel tim
                                ", '*'"+                                      //cel vivo
                                ", '*'"+                                      //cel claro
                                ", '*'"+                                      //cel outro
                                ", '*'"+                                      //email hotmail
                                ", '*'"+                                      //email gmail
                                ", '*'"+                                      //email yahoo
                                ", '*'"+                                      //email ig
                                ", '*'"+                                      //email bol
                                ", '*'"+                                      //email outro
                                ", '*'"+                                      //orkut
                                ", '*'"+                                      //twitter
                                ", '*'"+                                      //facebook
                                ", '*'"+                                      //myspace
                                ", '*'"+                                      //skype
                                ", '*'"+                                      //linkedin
                                ", '*'"+                                      //blog
                                ", '*'"+                //INFO. EDUCACIONAL   //instituição de ensino
                                ", '*'"+                                      //curso
                                ", '*'"+                                      //turma
                                ", '*'"+                                      //não gosto de... (inst)
                                ", '*'"+                                      //sempre gosto de... (inst)
                                ", '*'"+                                      //vou melhorar... (inst)
                                ", '*'"+                                      //a instituição é... (inst)
                                ", '*'"+                                      //esta faltando... (inst)
                                ", '*'"+                                      //nas horas vagas... (inst)
                                ", '*'"+                                      //melhor disciplina... (inst)
                                ", '*'"+                                      //pior disciplina...
                                ", '*'"+                                      //minha turma é...
                                ", '*'"+               //EMPRESA              //empresa
                                ", '*'"+                                      //cargo
                                ", '*'"+                                      //setor
                                ", '*'"+                                      //atividade... (emp)
                                ", '*'"+                                      //nao gosto de... (emp)
                                ", '*'"+                                      //sempre gosto de... (emp)
                                ", '*'"+                                      //vou melhorar em... (emp)
                                ", '*'"+                                      //a empresa é...
                                ", '*'"+                                      //esta faltando... (emp)
                                ", '*'"+                                      //email corp (emp)
                                ", '*'"+                                      //fone (emp)
                                ", '*'"+                                      //ramal (emp)
                                ", '*'"+                                      //fax (emp)
                                ", '*')";                                     //meu setor é...

           try{
               int l_gravado = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_SqlInsert);
               
               
                if(l_gravado == 1){

                        CadastroUsuario.s_telaCadastroMoniersn.dispose();

                        //aplica configuração padrão de usuário
                        aplicaConfiguracaoPadraoDoUsuario(l_apelido);

                        //aplica configurações de cores padrão
                        aplicaCoresPadrao(l_apelido);

                        CadastroUsuario.s_telaCadastroMoniersn = null;
                        System.out.println("Tela de cadastro fechada e null");

                        //coloca o email cadastrado no campo de login
                        LogMoniersn.s_LogMoniersn.conectar(l_apelido, l_senha);

                    }

           }
           catch(Exception e){
                    System.err.println("O registro não pôde ser gravado.    \nErro: "+e);
           
                    //esconde tela carregando
                    Aguardando.s_telaAguardando.setVisible(false);
                    
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
           
           }
   }
    else
       //esconde tela carregando
        Aguardando.s_telaAguardando.setVisible(false);
        System.err.println("Não permitido gravar. Inconsistencia nos campos...");

   }//fim gravar dados

   //aplica configurações de cores padrão (preto e branco)
   public void aplicaCoresPadrao(String l_apelido){

        try{

          auxiliar.Dados.s_conexaoBanco.executeSELECT("select * from usuario where moniersn = '"+l_apelido+"'");
          auxiliar.Dados.s_conexaoBanco.c_resultset.first();

          int l_codUsuario = auxiliar.Dados.s_conexaoBanco.c_resultset.getInt("cod_usuario");

          System.out.println("Aplicando configurações de cores padrão no cod de usuário: "+l_codUsuario);

          String l_SqlInsert = "insert into configuracoes_cores(cod_usuario"+
                                ", cor_painel" +
                                ", cor_nome"+
                                ", cor_frase"+
                                ", cor_ocorrencia"+
                                ", cor_sexo"+
                                ", cor_aniversario"+
                                ", cor_data_cadastro"+
                                ", cor_lista_recado" +
                                ", cor_recado"+
                                ", cor_lista_amigos"+
                                ", cor_nome_amigo)"+
                                " values("+l_codUsuario+ //cod do usuario
                                ", '255, 255, 255'"+     //cor do painel
                                ", '0, 0, 0'"+           //cor do nome do usuario
                                ", '0, 0, 0'"+           //cor da frase do usuario
                                ", '0, 0, 0'"+           //cor do estado atual
                                ", '0, 0, 0'"+           //cor do sexo do usuário
                                ", '0, 0, 0'"+           //cor do aniversario do usuario
                                ", '0, 0, 0'"+           //cor da data de cadastro
                                ", '255, 255, 255'"+     //cor de fundo da lista de recados
                                ", '0, 0, 0'"+           //cor do recado na lista
                                ", '255, 255, 255'"+     //cor de fundo lista de amigos
                                ", '0, 0, 0')";          //cor do nome do amigo na lista


               int l_gravado = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_SqlInsert);

               if(l_gravado == 1)
                    System.out.println("Configurações de cores padrão aplicada com sucesso!");

           }
           catch(Exception e){

                    System.err.println("Configurações padrão de cores não pôde ser aplicada.    \nErro: "+e);

           }
        }

   //aplica configurações de usuário padrão
   public void aplicaConfiguracaoPadraoDoUsuario(String l_apelido){

        try{

          auxiliar.Dados.s_conexaoBanco.executeSELECT("select * from usuario where moniersn = '"+l_apelido+"'");
          auxiliar.Dados.s_conexaoBanco.c_resultset.first();

          int l_codUsuario = auxiliar.Dados.s_conexaoBanco.c_resultset.getInt("cod_usuario");

          System.out.println("Aplicando configurações no cod de usuário: "+l_codUsuario);

          String l_SqlInsert = "INSERT INTO configuracoes_usuario(cod_usuario"+
                                ", visualizar_info_basicas"+
                                ", mostrar_nome"+
                                ", mostrar_minha_frase"+
                                ", mostrar_sexo"+
                                ", mostrar_data_niver"+
                                ", mostrar_data_cadastro"+
                                ", visualizar_ocorrencia"+
                                ", visualizar_info_pessoal"+
                                ", visualizar_info_contato"+
                                ", visualizar_info_educ"+
                                ", visualizar_info_prof"+
                                ", visualizar_lista_amigos"+
                                ", visualizar_lista_rec_pub"+
                                ", quem_bate_papo_comigo" +
                                ", quem_envia_arquivo" +
                                ", mostrar_balao_aviso"+
                                ", emitir_aviso_sonoro"+
                                ", solicitar_senha_ao_mostrar"+
                                ", solicitar_senha_ao_config"+
                                ", local_arquivos) "+
                                " values("+l_codUsuario+ //cod do usuario
                                ", 'amigos'"+           //quem visualiza informações basicas
                                ", 'N'"+                //mostrar meu nome? S ou N
                                ", 'S'"+                //mostrar minha frase? S ou N
                                ", 'S'"+                //mostrar sexo? S ou N
                                ", 'S'"+                //mostrar data de aniversário? S ou N
                                ", 'S'"+                //mostrar data de cadastro? S ou N
                                ", 'todos'"+           //quem visualiza ocorrencia? S ou N
                                ", 'todos'"+           //quem v info pessoal
                                ", 'todos'"+           //quem v info contato
                                ", 'todos'"+           //quem v info educ
                                ", 'todos'"+           //quem v info prof
                                ", 'todos'"+           //quem v lista de amigos
                                ", 'todos'"+           //quem v lista de recados públicos
                                ", 'todos'"+           //quem bate papo comigo
                                ", 'todos'"+           //quem envia - me arquivo
                                ", 'S'"+                //mostrar balao aviso
                                ", 'S'"+                //mostrar balao aviso
                                ", 'S'"+                //solicitar senha ao mostrar da bandeja
                                ", 'S'"+                //solicitar senha ao configurar
                                ", '*')";               //local de arquivos


               int l_gravado = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_SqlInsert);

               if(l_gravado == 1)
                    System.out.println("Configurações de usuario padrão aplicada com sucesso!");

           }
           catch(Exception e){
                    System.err.println("Configurações padrão de usuário não pôde ser aplicada.  \nErro: "+e);
           }
   }

//grava o recado n banco
    public void enviarRecado(JTextField l_campoRecado){

       Data.atualizaDataHora();

       String l_dataHoraPost = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;
       String l_recado = l_campoRecado.getText().toString();
       String l_tipoRecado = null;
       int l_codEmissor = LogUsuario.s_cod;
       int l_codDestinatario = LogAmigo.s_cod;



       //teste de campo vazio
       if(l_campoRecado.getText().isEmpty()){
           MostrarDados.s_MostrarDados.preencherListaRecadosPubAmigo(l_codDestinatario);
           return;
       }
           //teste de qtd de caracteres
           else if(l_campoRecado.getText().length() > 50){
               MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_EXCESSO_CARACT_50, "aviso");
               l_campoRecado.requestFocus();
           }   //caso passe nos testes
               else{

                    l_recado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_recado).toUpperCase();

                    if(l_campoRecado.equals(Moniersn.s_telaMsn.tf_recadoPriv)){
                        l_tipoRecado = "priv";
                    }
                         else{
                             l_tipoRecado = "pub";
                         }


                          String l_SqlInsert = "INSERT INTO recado(cod_emissor"+
                                                ", cod_destinatario"+
                                                ", conteudo"+
                                                ", tipo_recado"+
                                                ", recado_emissor_gravado"+
                                                ", recado_destinatario_gravado"+
                                                ", visto"+
                                                ", data_hora_post"+
                                                ", data_hora_atu)"+
                                                " VALUES("+l_codEmissor+
                                                ", "+l_codDestinatario+
                                                ", '"+l_recado+"'"+
                                                ", '"+l_tipoRecado+"'"+
                                                ", 'S'"+//recado_emissor_gravado
                                                ", 'S'"+//recado_destinatario_gravado
                                                ", 'N'"+//visto do receptor(sempre nao)
                                                ", '"+l_dataHoraPost+"'"+
                                                ", '"+l_dataHoraPost+"')";

                       try{

                           int enviado = Dados.s_conexaoBanco.c_statement.executeUpdate(l_SqlInsert);

                           if(enviado == 1){

                                //CONTAGEM DE ITENS
                                SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(l_codEmissor);


                                //CASO RECADO PRIVADO...
                                if(l_tipoRecado.equals("priv")){

                                       //CASO LISTA DE REC. PRIV ENVIADA SELECIONADA
                                       if(LogUsuario.s_listaRecPrivSelecionada.equals("env")){
                                           //PREENCHE A LISTA DE RECADOS PRIV ENIADO
                                           MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(l_codEmissor, Moniersn.jt_listaRecadosPriv, "env");
                                       }

                                     //ENVIA AVISO PARA UNICO AMIGO
                                     ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codDestinatario, 4);
                                       
                                }
                                   //CASO RECADO PÚBLICO
                                   else{
                                      
                                        MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod);
                                        Moniersn.jtp_painelTabuladoAmigo.setSelectedComponent(Moniersn.jp_recPublicosAmigo); 

                                    
                                           //CASO LISTA DE REC. PUB. ENVIADA ESTIVER SELEIONADA
                                           if(LogUsuario.s_listaRecPubSelecionada.equals("env")){
                                                //PREENCHE LISTA DE REC. PUB ENV.
                                                MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(l_codEmissor, Moniersn.jt_listaRecadosPubUsuario, "env");
                                           }
                                           
                                           //ENVIA AVISO AOS AMIGOS CONECTADOS DO AMIGO (inclusive para o o amigo)
                                           if(!LogAmigo.s_quemVeRecPub.equals("ninguem"))
                                                ClienteUDP.enviaAvisoParaTodosConectados(l_codDestinatario, 3, LogAmigo.s_quemVeRecPub);

                                   }

                                   //limpa o campo
                                   l_campoRecado.setText("");

                                   MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_REC_ENV, "atu");

                            }
                       }
                       catch(Exception e){
                                System.err.println("O recado não pôde ser enviado.  \nErro: "+e);
                                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");

                       }
                }//fim else
    }//fim gravar dados

    public void gravarOcorrencia(String l_ocorrencia, boolean l_anonimo){   

         Data.atualizaDataHora();

         int l_codEmissor = LogUsuario.s_cod;
         int l_tipoAviso;
         String l_ocorrenciaAnonima = null;
         String l_SqlInsert = null;

            //teste de recado anônimo
            if(l_anonimo)
                l_ocorrenciaAnonima = "S";
                    else
                       l_ocorrenciaAnonima = "N";

           //teste de campo em branco
           if(l_ocorrencia.isEmpty())
                Moniersn.tf_ocorrencia.requestFocus();

              //teste de limite de caracteres
               else if(l_ocorrencia.length() > 50){

                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_EXCESSO_CARACT_50, "aviso");
                    Moniersn.tf_ocorrencia.requestFocus();
               }
                    //gravação
                    else{

                           l_ocorrencia = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_ocorrencia).toUpperCase();

                           l_SqlInsert = "INSERT INTO ocorrencia(cod_emissor"+
                                                    ", conteudo"+
                                                    ", anonimo"+
                                                    ", gravado"+
                                                    ", data_hora_post"+
                                                    ", data_hora_atu)"+
                                                    "  VALUES("+l_codEmissor+
                                                    ", '"+l_ocorrencia+"'"+
                                                    ", '"+l_ocorrenciaAnonima+"'"+
                                                    ", 'S'"+
                                                    ", '"+Data.s_dataAtualAAAAMMDDHHMMSSParaBD+"'"+
                                                    ", '"+Data.s_dataAtualAAAAMMDDHHMMSSParaBD+"')";
                            try{

                               int enviado = Dados.s_conexaoBanco.c_statement.executeUpdate(l_SqlInsert);

                               if(enviado == 1){

                                       //CONTAGEM DE ITENS
                                       SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(l_codEmissor);

                                      //PREENCHE LISTA E OCORRENCIAS
                                       if(LogUsuario.s_listaOcorrenciaSelecionada.equals("env"))
                                            MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(l_codEmissor, Moniersn.jt_listaOcorrenciaUsuario, LogUsuario.s_listaOcorrenciaSelecionada);

                                       //LIMPA O CAMPO DE ESCREVER OCO
                                       Moniersn.tf_ocorrencia.setText("");

                                       //SETA ULTIMA OCORRENCIA
                                       MostrarDados.s_MostrarDados.setaUltimaOcorrencia(l_codEmissor, "usuario");

                                           //CASO NAO ANONIMA...
                                           if(l_ocorrenciaAnonima.equals("N"))
                                               l_tipoAviso = 13;
                                               //CASO ANÔNIMA
                                                else
                                                   l_tipoAviso = 14;

                                           //CASO MOSTRANDO OCORRENCIAS....
                                           if(!LogUsuario.s_quemVeOcorrencias.equals("ninguem")){
                                               //avisa à todos amigos conectados a nova ocorrêencia anonima
                                               ClienteUDP.enviaAvisoParaTodosConectados(l_tipoAviso, LogUsuario.s_quemVeOcorrencias);//13-nova ocorrência  anonima
                                           }

                                       //MOSTRA MSG ATUALIZAÇÃO
                                       MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_OCO_ENV, "atu");
                               }
                            }
                            catch(Exception e){
                                
                                   System.err.println("A ocorrência não pôde ser gravada.   \nErro: "+e);
                                   MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");

                            }
                    }//fim else gravação
             
    }//fim gravar ocorrencia


    public void gravarComentario(int l_codConteudo, String l_tipoComentario, String l_conteudo, boolean l_anonimo, JTable l_tabela, String l_emissor){

           String l_dataHoraPost = null;
           String l_SqlInsert = null;
           String l_comentarioAnonimo = null;

           //Se vazio
           if(l_conteudo.isEmpty()){
               Comentario.s_telaComentario.tf_comentario.requestFocus();

           }
            //Testa a qtd de caracteres
            else if(l_conteudo.length() > 50){

                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_EXCESSO_CARACT_50, "aviso");
                Comentario.s_telaComentario.tf_comentario.requestFocus();

            }
            else{

                   //TESTE DE COMENTÁRIO ANONIMO
                   if(l_anonimo)
                       l_comentarioAnonimo = "S";
                   else
                       l_comentarioAnonimo = "N";

                   //ATUALIZA DATA
                   Data.atualizaDataHora();

                   l_dataHoraPost = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;

                   //TRATA CONTEUDO DE ASPAS SIMPLES
                   l_conteudo = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_conteudo);

                   //SQL INSERT
                   l_SqlInsert = "INSERT INTO comentario(cod_usuario"+
                                        ", cod_conteudo"+
                                        ", tipo_comentario"+
                                        ", conteudo"+
                                        ", anonimo"+
                                        ", gravado"+
                                        ", data_hora_post)"+
                                        " VALUES("+LogUsuario.s_cod+//cod_usuario
                                        ", "+l_codConteudo+//conteudo
                                        ", '"+l_tipoComentario+"'"+//tipo_comentario
                                        ", '"+l_conteudo+"'"+//conteudo
                                        ", '"+l_comentarioAnonimo+"'"+//anonimo
                                        ", 'S'"+//gravado
                                        ", '"+l_dataHoraPost+"')";//hora

                   try{

                       //INSERE NO BANCO
                       int l_gravado = Dados.s_conexaoBanco.c_statement.executeUpdate(l_SqlInsert);

                       //CASO INSERIDO...
                       if(l_gravado == 1){

                           
                            //INABILITA BOTAO DE REMOVER COMENTARIO
                            Comentario.s_telaComentario.bt_removerComent.setEnabled(false);

                            //ATUALIZA A DATA DE ULTIMA ATUALIZAÇÃO
                            Comentario.lb_ultimaAtu.setText(Avisos.TEXTO_ULT_ATU+" » "+Avisos.TEXTO_AGORA);

                            //LIMPA CAMPO DE TEXTO DE COMENTAR
                            Comentario.s_telaComentario.tf_comentario.setText("");

                            //ATUALIZA A DATA_ATU DO CONTEÚDO
                            AtualizarDados.s_AtualizarDados.atualizaDataAtuConteudo(l_dataHoraPost, l_tipoComentario, l_anonimo, l_codConteudo, l_tabela, l_emissor);

                            //PREENCHE LISTA DE COMENTÁRIOS
                            MostrarDados.s_MostrarDados.preencherListaComentarios(l_codConteudo, l_tipoComentario);

                            //ATUALIZA QTD. DE COMENT
                            int qtdComentarios = SolicitarDados.s_SolicitarDados.qtdComentarioPorConteudo(l_tipoComentario, l_codConteudo);
                            Comentario.lb_qtdComentarios.setText("("+qtdComentarios+") "+Avisos.TEXTO_COMENTARIOS);

                                //caso ultima ocorrencia...
                                if(l_tipoComentario.equals("ocorrencia")){
                                    //ATUALIZA QTD. DE COMENTs ultima ocorrencia
                                    if(l_codConteudo == LogUsuario.s_codUltimaOcorrencia)
                                        MostrarDados.s_MostrarDados.setaUltimaOcorrencia(LogUsuario.s_cod, "usuario");
                                }

                            //MOSTRA MSG ATUALIZAÇÃO
                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_COMENT_ENV, "atu");

                       }
                   }
                   catch(Exception e){
                            System.err.println("O comentario nao pode ser enviado.  \nErro: "+e);
                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                   }
           }
    }

    public void gravarAtualizacao(String l_perfil, String l_atributo, String l_descricao){

         Data.atualizaDataHora();


         int l_codUsuario = LogUsuario.s_cod;
         String l_SqlInsert = null;
         String l_dataPost = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;

            l_SqlInsert = "INSERT INTO atualizacoes_usuario(cod_usuario"+
                                    ", perfil"+
                                    ", atributo"+
                                    ", conteudo"+
                                    ", gravado"+
                                    ", data_hora_post"+
                                    ", data_hora_atu)"+
                                    "  values("+l_codUsuario+
                                    ", '"+l_perfil+"'"+
                                    ", '"+l_atributo+"'"+
                                    ", '"+l_descricao+"'"+
                                    ", 'S'"+
                                    ", '"+l_dataPost+"'"+
                                    ", '"+l_dataPost+"')";

            try{

               int enviado = Dados.s_conexaoBanco.c_statement.executeUpdate(l_SqlInsert);

               if(enviado == 1){

                       //decide se amigos devem saber da atualização
                       if(l_perfil.equals("amigos")){

                            //GRAVA A ATUALIZAÇÃO DO SOLICITANTE
                            gravarAtualizacaoNovaAmizadeAmigo(Avisos.COMECOU_AMIZADE+LogUsuario.s_moniersn);

                               if(!LogUsuario.s_quemVeListaAmigos.equals("ninguem"))
                                    ClienteUDP.enviaAvisoParaTodosConectados(15, LogUsuario.s_quemVeListaAmigos);
                       }

                       //decide se amigos devem saber da atualização
                       if(l_perfil.equals("basico") && !LogUsuario.s_quemVeInfoBasicas.equals("ninguem")){
                           
                           if(l_atributo.equals("nome") || l_atributo.equals("sobrenome") && LogUsuario.s_mostrarNome.equals("S"))
                               ClienteUDP.enviaAvisoParaTodosConectados(15, LogUsuario.s_quemVeInfoBasicas);

                           if(l_atributo.equals("icone_humor"))
                               ClienteUDP.enviaAvisoParaTodosConectados(15, LogUsuario.s_quemVeInfoBasicas);

                       }

                       if(l_perfil.equals("pessoal") && !LogUsuario.s_quemVeInfoPessoal.equals("ninguem"))
                           ClienteUDP.enviaAvisoParaTodosConectados(15, LogUsuario.s_quemVeInfoPessoal);

                       if(l_perfil.equals("contato") && !LogUsuario.s_quemVeInfoContato.equals("ninguem"))
                           ClienteUDP.enviaAvisoParaTodosConectados(15, LogUsuario.s_quemVeInfoContato);

                       if(l_perfil.equals("educacional") && !LogUsuario.s_quemVeInfoEducacional.equals("ninguem"))
                           ClienteUDP.enviaAvisoParaTodosConectados(15, LogUsuario.s_quemVeInfoEducacional);

                       if(l_perfil.equals("profissional") && !LogUsuario.s_quemVeInfoProfissional.equals("ninguem"))
                           ClienteUDP.enviaAvisoParaTodosConectados(15, LogUsuario.s_quemVeInfoProfissional);

                        //contagem de itens
                       SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(l_codUsuario);

                       //preenche lista de atualizações (atualizacoes)
                       MostrarDados.s_MostrarDados.preencherListaAtualizacoes(l_codUsuario, Moniersn.jt_listaAtualizacoesUsuario, LogUsuario.s_listaAtualizacoesSelecionada);
               }
            }
            catch(Exception e){
                   System.err.println("A atualização não pôde ser gravada.  \nErro: "+e);
            }

    }//fim gravar atualizacao

    public void gravarAtualizacaoNovaAmizadeAmigo(String l_descricao){

         Data.atualizaDataHora();

         int l_codAmigo = LogAmigo.s_cod;
         String l_SqlInsert = null;
         String l_dataPost = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;

            l_SqlInsert = "INSERT INTO atualizacoes_usuario(cod_usuario"+
                                    ", perfil"+
                                    ", atributo"+
                                    ", conteudo"+
                                    ", gravado"+
                                    ", data_hora_post"+
                                    ", data_hora_atu)"+
                                    "  VALUES("+l_codAmigo+
                                    ", 'amigos'"+
                                    ", 'lista_amigos'"+
                                    ", '"+l_descricao+"'"+
                                    ", 'S'"+
                                    ", '"+l_dataPost+"'"+
                                    ", '"+l_dataPost+"')";

            try{

               int enviado = Dados.s_conexaoBanco.c_statement.executeUpdate(l_SqlInsert);

               if(enviado == 1){

                    //AVISA AO AMIGO CONECTADO
                    ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codAmigo, 9);
               }
            }
            catch(Exception e){
                   System.err.println("A atualização de nova amizade não pôde ser gravada.  \nErro: "+e);
            }

    }//fim gravar atualizacao

       //aplica configurações de cores padrão (preto e branco)
   public void gravarCompartilhamentoArquivo(int l_codEmissor, String l_comQuem, int l_codDestinatario, String l_nomeArquivo, String l_novoNome, String l_extensao, long l_tamanho,  String l_diretorioArquivoSis){

       String l_diretorioArquivoBD = null;
       String l_gravado = "S";
       String l_dataHoraPost = null;
       String l_nomeArqOriginalTratado = null;
       String l_novoNomeArqTratado = null;

       int l_gravadoOk = 0;

       //trata diretorio para bd
       l_diretorioArquivoBD = SolicitarDados.s_SolicitarDados.trataBarraDiretorioParaBD(l_diretorioArquivoSis);
       l_diretorioArquivoBD = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_diretorioArquivoBD);
       
       //trata nome do arquivo para o bd
       l_nomeArqOriginalTratado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_nomeArquivo);
       l_novoNomeArqTratado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_novoNome);

       //pega dta e hora atual
       Data.atualizaDataHora();

       l_dataHoraPost = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;

          String l_SqlInsert = "INSERT INTO arquivo(cod_emissor"+
                                ", quem_baixa_arquivo"+
                                ", cod_destinatario"+
                                ", nome"+
                                ", nome_original"+
                                ", extensao"+
                                ", tamanho"+
                                ", diretorio"+
                                ", bytes "+
                                ", gravado"+
                                ", data_hora_post"+
                                ", data_hora_atu)"+
                                " VALUES("+l_codEmissor+             //cod do usuario emissor
                                ", '"+l_comQuem+"'"+                 //com quem compartilhará
                                ", '"+l_codDestinatario+"'"+         //se > 0 só será pra 1 pessoa
                                ", '"+l_novoNomeArqTratado+"'"+      //novo nome do arquivo
                                ", '"+l_nomeArqOriginalTratado+"'"+  //nome do arquivo original
                                ", '"+l_extensao+"'"+                //extensao do arquivo
                                ", "+l_tamanho+                      //tamanho do arquivo
                                ", '"+l_diretorioArquivoBD+"'"+      //diretrio do arquivo para o banco '/'
                                ", '"+null+"'"+                      //bytes do arquivo
                                ", '"+l_gravado+"'"+                 //está gravado
                                ", '"+l_dataHoraPost+"'"+            //data de postagem
                                ", '"+l_dataHoraPost+"')";           //hora de atualizacao

           try{
                l_gravadoOk = Dados.s_conexaoBanco.c_statement.executeUpdate(l_SqlInsert);
           }
           catch(Exception e){

                    System.err.println("Erro ao tentar gravar arquivo compartilhado.    \nErro: "+e);
                    CompartilhaArquivo.s_telaCompArquivo.dispose();
                    CompartilhaArquivo.s_telaCompArquivo = null;

                    String l_caminhoArquivo = Diretorios.pegaCaminhoArquivo(Diretorios.PASTA_ENVIADOS, l_novoNome);

                    SolicitarDados.s_SolicitarDados.manipulaArquivo(l_caminhoArquivo, null, Diretorios.PASTA_REMOVIDOS);

                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_ERRO_COMPART, "erro");
           }


           if(l_gravadoOk == 1){

               System.out.println("arquivo a compartilhado gravado no banco!");
                System.out.println("Nome: "+l_novoNome+"\nTam.: "+l_tamanho+" bytes");

                


//             grava bytes do arquivo no banco        
//                try{
//
//                       Dados.s_conexaoBanco.c_statement.execute("SELECT cod_arquivo, bytes FROM arquivo");
//
//                       PreparedStatement pstmt = Dados.s_conexaoBanco.c_statement.getConnection().prepareStatement("UPDATE arquivo SET bytes = '?' WHERE cod_arquivo = 133 ");
//                       pstmt.setBytes(1, l_bytesArquivo);
//
//                   }catch(Exception e){
//                        System.err.println("erro ao tentar inserir bytes.   \nErro: "+e);
//                   }


                CompartilhaArquivo.s_telaCompArquivo.setDefaultCloseOperation(CompartilhaArquivo.DISPOSE_ON_CLOSE);

                CompartilhaArquivo.s_telaCompArquivo.dispose();

                CompartilhaArquivo.s_telaCompArquivo = null;

                Moniersn.s_telaMsn.setVisible(true);

                //CONTAGEM DE ÍTENS
                SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                   //CASO LISTA DE ARQUIVOS ENVIADA ESTIVER SELEIONADA
                    if(LogUsuario.s_listaArquivoSelecionada.equals("enviados")){
                        //PREENCHE LISTA DE ARQUIVOS ENVIADOS
                        MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, Moniersn.jt_listaArquivosUsuario, "enviados");
                        
                        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(Moniersn.jt_listaArquivosUsuario); 
                    }

                    //ENVIA AVISO AOS AMIGOS CONECTADOS DO AMIGO (inclusive para o o amigo)
                    if(l_codDestinatario == 0){

                        String l_praQuem = null;
                        
                            if(l_comQuem.equals("todos"))
                                l_praQuem = "todos-rec-arq";  
                            else
                                l_praQuem = "amigos-rec-arq";  
                            
                        //envia pra todos ou so amigos conectados
                        ClienteUDP.enviaAvisoParaTodosConectados(6, l_praQuem);
                    
                    //caso seja pra uma pessoa
                    }else{  
                       
                        //envia para unico conectado
                        ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codDestinatario, 6);
                        
                    }
                           

                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_COMPART, "atu");

           }

        }

          //aplica configurações de cores padrão (preto e branco)
   public boolean gravarRecebimentoArquivo(int l_codArquivo, String l_nomeArq, int l_codEmissor){

       boolean l_recebGravado = false;
       int l_codUsuario = LogUsuario.s_cod;
       String l_gravado = "S";
       String l_dataHoraRec = null;
       int registrado = 0;

       //pega dta e hora atual
       Data.atualizaDataHora();

       l_dataHoraRec = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;

               //QUERY DE UPDATE
        String l_sqlAtualiza = "UPDATE arquivo_usuario "
                             + "SET gravado = 'S' "
                             + "WHERE cod_arquivo = "+l_codArquivo+" "
                             + "AND cod_usuario = "+l_codUsuario;

            try{                    

                registrado = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlAtualiza);

                if(registrado == 0){
                
                      System.err.println("Arquivo nunca foi baixado. Registrando novo arquivo...");

                      String l_SqlInsert = "INSERT INTO arquivo_usuario(cod_arquivo"+
                                            ", cod_usuario" +
                                            ", gravado"+
                                            ", data_hora_recebido) "+
                                            "VALUES("+l_codArquivo+//cod do arquivo
                                            ", "+l_codUsuario+     //cod do dono do arquivo
                                            ", '"+l_gravado+"'"+   //gravado
                                            ", '"+l_dataHoraRec+"')"; //data hora recebido

                       try{

                           registrado = Dados.s_conexaoBanco.c_statement.executeUpdate(l_SqlInsert);
                           l_recebGravado = true;

                       }catch (Exception erro_insert){

                                l_recebGravado = false;
                                System.err.println("Erro ao tentar registrar recebimento de arquivo.    \nErro: "+erro_insert);

                                String l_caminhoArquivo = Diretorios.pegaCaminhoArquivo(Diretorios.PASTA_BAIXADOS, l_nomeArq);

                                SolicitarDados.s_SolicitarDados.manipulaArquivo(l_caminhoArquivo, null, Diretorios.PASTA_REMOVIDOS);

                       }
                
                }
                    

            }catch(Exception erro_update){
            
                  System.err.println("Arquivo nunca foi baixado. Registrando novo arquivo...");
                
                  String l_SqlInsert = "INSERT INTO arquivo_usuario(cod_arquivo"+
                                        ", cod_usuario" +
                                        ", gravado"+
                                        ", data_hora_recebido) "+
                                        "VALUES("+l_codArquivo+//cod do arquivo
                                        ", "+l_codUsuario+     //cod do dono do arquivo
                                        ", '"+l_gravado+"'"+   //gravado
                                        ", '"+l_dataHoraRec+"')"; //data hora recebido

                   try{

                       registrado = Dados.s_conexaoBanco.c_statement.executeUpdate(l_SqlInsert);
                       l_recebGravado = true;

                   }catch (Exception erro_insert){

                            l_recebGravado = false;
                            
                            System.err.println("Erro ao tentar registrar recebimento de arquivo.    \nErro: "+erro_insert);

                            String l_caminhoArquivo = Diretorios.pegaCaminhoArquivo(Diretorios.PASTA_BAIXADOS, l_nomeArq);

                            SolicitarDados.s_SolicitarDados.manipulaArquivo(l_caminhoArquivo, null, Diretorios.PASTA_REMOVIDOS);

                   }
            
            }
       
                //caso registrado
                if(registrado == 1){

                    l_recebGravado = true;
                    System.out.println("recebimento registrado no banco!");

                    //contagem de itens
                    SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(l_codUsuario);
                    
                    SolicitarDados.s_SolicitarDados.verificaLocalArquivos(LogUsuario.s_cod, LogUsuario.s_localDeArquivosDoUsuario, "atu");


                        //preenche lista de arquivos disponiveis
                        if(!LogUsuario.s_listaArquivoSelecionada.equals("enviados"))
                            MostrarDados.s_MostrarDados.preencherListaDeArquivos(l_codUsuario, Moniersn.jt_listaArquivosUsuario, LogUsuario.s_listaArquivoSelecionada);

                        if(LogUsuario.s_mostrandoPainelAmigo && (LogAmigo.s_cod == l_codEmissor)){
                            
                                MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod); 
                                
                                Moniersn.jtp_painelTabuladoAmigo.setSelectedComponent(Moniersn.jp_arquivosAmigo); 
                        }
                        
                    //habilita desabilita botoes
                    MostrarDados.s_MostrarDados.habilitaBotoesConteudo(Moniersn.jt_listaArquivosUsuario);

                }

          return l_recebGravado;

        }

}
