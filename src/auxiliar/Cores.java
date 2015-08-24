/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import formularios.Moniersn;
import formularios.Configuracoes;
import formularios.Comentario;

/**
 *
 * @author Ayrton Monier
 */
public class Cores {

    public static Cores s_Cores = new Cores();

    //VARIÁVEIS ESTÁTICAS STRING

    //variáveis estaicas que pegam as cores RGB por inteiro que estão no banco de dados
    //Guarda cor RGB do painel de fundo
    private String c_corPainel;
    //Guarda cor RGB do nome do usuário
    private String c_corNome;
    //Guarda cor RGB da frase do usuário
    private String c_corFrase;
    //Guarda cor RGB da qtd de recados públicos
    private String c_corSexo;
    //Guarda cor RGB da qtd de recados privados
    private String c_corOcorrencia;
    //Guarda cor RGB da qtd de recados enviados
    private String c_corNiver;
    //Guarda cor RGB da qtd de recados excluídos
    private String c_corDataCadastro;
    //Guarda cor RGB do fundo da lista de recados
    private String c_corFundoListaRecado;
    //Guarda cor RGB da cor do recado
    private String c_corRecado;
    //Guarda cor RGB do fundo da lista de amigos
    private String c_corFundoListaAmigos;
    //Guarda cor RGB do nome dos amigos na lista
    private String c_corNomeAmigoNaLista;

  //variáveis estáticas que guardam separadamente os valores R G e B das variáveis que pegaram o a cor
    //RGB do banco (acima declaradas) para setar nas configurações do USUÁRIO
    //R, G, e B  do painel de fundo do usuário...
    private int c_rCorPainel, c_gCorPainel, c_bCorPainel;
    //R, G, e B  do nome do usuário...
    private int c_rCorNome, c_gCorNome, c_bCorNome;
    //R, G, e B  do ...
    private int c_rCorFrase, c_gCorFrase, c_bCorFrase;
    //R, G, e B  do ...
    private int c_rCorSexo, c_gCorSexo, c_bCorSexo;
    //R, G, e B  do ...
    private int c_rCorOcorrencia, c_gCorOcorrencia, c_bCorOcorrencia;
    //R, G, e B  do ...
    private int c_rCorNiver, c_gCorNiver, c_bCorNiver;
    //R, G, e B  do ...
    private int c_rCorDataCadastro, c_gCorDataCadastro, c_bCorDataCadastro;
    //R, G, e B  do ...
    private int c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado;
    //R, G, e B  do ...
    private int c_rCorRecado, c_gCorRecado, c_bCorRecado;
    //R, G, e B  do ...
    private int c_rCorFundoListaAmigos, c_gCorFundoListaAmigos, c_bCorFundoListaAmigos;
    //R, G, e B  do ...
    private int c_rCorNomeAmigoNaLista, c_gCorNomeAmigoNaLista, c_bCorNomeAmigoNaLista;



    public void atualizaCores(int l_codUsuario, String l_quem){

            String sql = "select * from configuracoes_cores where cod_usuario = "+l_codUsuario;

            try{

            Dados.s_conexaoBanco.executeSELECT(sql);

            Dados.s_conexaoBanco.c_resultset.first();

                //cor do painel
                c_corPainel = Dados.s_conexaoBanco.c_resultset.getString("cor_painel");
                c_corNome = Dados.s_conexaoBanco.c_resultset.getString("cor_nome");
                c_corFrase = Dados.s_conexaoBanco.c_resultset.getString("cor_frase");
                c_corOcorrencia = Dados.s_conexaoBanco.c_resultset.getString("cor_ocorrencia");
                c_corSexo = Dados.s_conexaoBanco.c_resultset.getString("cor_sexo");
                c_corNiver = Dados.s_conexaoBanco.c_resultset.getString("cor_aniversario");
                c_corDataCadastro = Dados.s_conexaoBanco.c_resultset.getString("cor_data_cadastro");
                c_corFundoListaRecado = Dados.s_conexaoBanco.c_resultset.getString("cor_lista_recado");
                c_corRecado = Dados.s_conexaoBanco.c_resultset.getString("cor_recado");
                c_corFundoListaAmigos = Dados.s_conexaoBanco.c_resultset.getString("cor_lista_amigos");
                c_corNomeAmigoNaLista = Dados.s_conexaoBanco.c_resultset.getString("cor_nome_amigo");

                //cor do painel
                separadorRGB(c_corPainel, "cor_painel");//envia as cores no tipo xxx, xxx, xxx para o metodo que vai separar r, b e g;

                //cor do nome do usuario
                separadorRGB(c_corNome, "cor_nome");//envia as cores no tipo xxx, xxx, xxx para o metodo que vai separar r, b e g;

                //cor da frase do usuário
                separadorRGB(c_corFrase, "cor_frase");//envia as cores no tipo xxx, xxx, xxx para o metodo que vai separar r, b e g;

                //cor do estado atual
                separadorRGB(c_corOcorrencia, "cor_ocorrencia");//envia as cores no tipo xxx, xxx, xxx para o metodo que vai separar r, b e g;

                //cor sexo usuário
                separadorRGB(c_corSexo, "cor_sexo");//envia as cores no tipo xxx, xxx, xxx para o metodo que vai separar r, b e g;

                //cor data de aniversário do usuário
                separadorRGB(c_corNiver, "cor_aniversario");//envia as cores no tipo xxx, xxx, xxx para o metodo que vai separar r, b e g;

                //cor da data de cadastro do usuário
                separadorRGB(c_corDataCadastro, "cor_data_cadastro");//envia as cores no tipo xxx, xxx, xxx para o metodo que vai separar r, b e g;

                //cor da lista de recados
                separadorRGB(c_corFundoListaRecado, "cor_lista_recado");//envia as cores no tipo xxx, xxx, xxx para o metodo que vai separar r, b e g;

                //cor do recado na lista
                separadorRGB(c_corRecado, "cor_recado");//envia as cores no tipo xxx, xxx, xxx para o metodo que vai separar r, b e g;

                //cor da lista de amigos
                separadorRGB(c_corFundoListaAmigos, "cor_lista_amigos");//envia as cores no tipo xxx, xxx, xxx para o metodo que vai separar r, b e g;

                //cor do nome do amigo na lista
                separadorRGB(c_corNomeAmigoNaLista, "cor_nome_amigo");//envia as cores no tipo xxx, xxx, xxx para o metodo que vai separar r, b e g;


                //SETA CONFIGURAÇÕES DE CORES DO USUÁRIO
                if(l_quem.equals("usuario")){
                        //setando as cores do banco nos objetos do painel

                    //System.out.println(l_quem+": "+c_rCorPainel+", "+c_gCorPainel+", "+c_bCorPainel);

                        //PAINEL (inicio) DO USUARIO
                        Moniersn.jp_inicioUsuario.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //NOME DO USUÁRIO
                        Moniersn.lb_nomeIconeHumorUsuario.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //QTD CONVITE BATE PAPO
                        Moniersn.lb_qtdConvites.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //QTD REC. INST. BATE PAPO
                        Moniersn.lb_qtdRecInst.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FRASE DO USUÁRIO
                        Moniersn.lb_fraseUsuario.setForeground(new java.awt.Color(c_rCorFrase, c_gCorFrase, c_bCorFrase));

                        //OCORRêNCIA
                        Moniersn.lb_ultimaOcorrenciaUsuario.setForeground(new java.awt.Color(c_rCorOcorrencia, c_gCorOcorrencia, c_bCorOcorrencia));

                        //QTD. COMENTS OCORRÊNCIA
                        Moniersn.lb_qtdComentarioUltOcoUsuario.setForeground(new java.awt.Color(c_rCorOcorrencia, c_gCorOcorrencia, c_bCorOcorrencia));

                        //SEXO DO USUÁRIO
                        Moniersn.lb_sexoUsuario.setForeground(new java.awt.Color(c_rCorSexo, c_gCorSexo, c_bCorSexo));

                        //ANIVERSÁRIO DO USUÁRIO
                        Moniersn.lb_dataNiverUsuario.setForeground(new java.awt.Color(c_rCorNiver, c_gCorNiver, c_bCorNiver));

                        //DATA DE CADASTRO DO USUÁRIO
                        Moniersn.lb_dataCadastroUsuario.setForeground(new java.awt.Color(c_rCorDataCadastro, c_gCorDataCadastro, c_bCorDataCadastro));

                        //TEXTO "ESCREVA AQUI SUA OCORRÊNCIA"
                        Moniersn.lb_escrevaUmaOcorrencia.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //TEXTO cb "anonimo"
                        Moniersn.cb_anonimo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "anonimo"
                        Moniersn.cb_anonimo.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));
                        
                        //COR LABEL QTD USUARIOS NO GERAL
                        Moniersn.lb_qtdTotalUsuario.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //COR LABEL QTD USUARIOS
                        Moniersn.lb_qtdUsuarioListaUsuario.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

            //LISTA DE OCORRÊNCIAS

                        //PAINEL (ocorrencia) DO USUARIO
                        Moniersn.jp_ocorrenciaUsuario.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "todas ocorrencias"
                        Moniersn.rb_ocoRecebidas.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "todas ocorrencias"
                        Moniersn.rb_ocoRecebidas.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "minhas ocorrencias"
                        Moniersn.rb_ocoEnviadas.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "minhas ocorrencias"
                        Moniersn.rb_ocoEnviadas.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //FUNDO DA LISTA DE OCORRENCIA
                        Moniersn.jt_listaOcorrenciaUsuario.setBackground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //GRID DA LISTA DE OCORRENCIA
                        Moniersn.jt_listaOcorrenciaUsuario.setGridColor(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //FONTE DA LISTA DE OCORRENCIA
                        Moniersn.jt_listaOcorrenciaUsuario.setForeground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FUNDO DA LINHA SELECIONADA DA LISTA DE OCORRENCIA (cor do recado)
                        Moniersn.jt_listaOcorrenciaUsuario.setSelectionBackground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FONTE DA LINHA SELECIONADA DA LISTA OCORRENCIA (cor de fundo da tabela)
                        Moniersn.jt_listaOcorrenciaUsuario.setSelectionForeground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //QT OCO ENCONTRADA
                        Moniersn.lb_qtdOcoEncontrada.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //PESQUISAR OCORRRENCIA
                        Moniersn.lb_pesquisarOco.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
            //LISTA DE ATUALIZACOES

                        //PAINEL (atualizacoes) DO USUARIO
                        Moniersn.jp_atualizacoesUsuario.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "atu de nao amigos"
                        Moniersn.rb_atuParaTodos.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "atu de amigos"
                        Moniersn.rb_atuParaTodos.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "atu de amigos"
                        Moniersn.rb_atuDeAmigos.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "atu de amigos"
                        Moniersn.rb_atuDeAmigos.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "minhas ocorrencias"
                        Moniersn.rb_atualizacoesMinhas.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "minhas ocorrencias"
                        Moniersn.rb_atualizacoesMinhas.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //FUNDO DA LISTA DE ATUALIZACOES
                        Moniersn.jt_listaAtualizacoesUsuario.setBackground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //GRID DA LISTA DE ATUALIZACOES
                        Moniersn.jt_listaAtualizacoesUsuario.setGridColor(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //FONTE DA LISTA DE ATUALIZACOES
                        Moniersn.jt_listaAtualizacoesUsuario.setForeground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FUNDO DA LINHA SELECIONADA DA LISTA DE ATUALIZACOES (cor da fonte)
                        Moniersn.jt_listaAtualizacoesUsuario.setSelectionBackground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FONTE DA LINHA SELECIONADA DA LISTA ATUALIZACOES (cor de fundo da tabela)
                        Moniersn.jt_listaAtualizacoesUsuario.setSelectionForeground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //QT ATU ENCONTRADA
                        Moniersn.lb_qtdAtuEncontrada.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //PESQUISAR ATUALIZAÇÃO
                        Moniersn.lb_pesquisarAtu.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        
           //LISTA DE RECADO PÚBLICOS

                        //PAINEL (rec. publico) DO USUARIO
                        Moniersn.jp_recPublicosUsuario.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "atu de nao amigos"
                        Moniersn.rb_todosRecadosPubRecebidos.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "atu de amigos"
                        Moniersn.rb_todosRecadosPubRecebidos.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "atu de amigos"
                        Moniersn.rb_recadosPubNaoLidos.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "atu de amigos"
                        Moniersn.rb_recadosPubNaoLidos.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "minhas ocorrencias"
                        Moniersn.rb_recadosPubEnviados.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "minhas ocorrencias"
                        Moniersn.rb_recadosPubEnviados.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //FUNDO DA LISTA DE RECADOS PÚBLICOS
                        Moniersn.jt_listaRecadosPubUsuario.setBackground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //GRID DA LISTA DE RECADOS PÚBLICOS
                        Moniersn.jt_listaRecadosPubUsuario.setGridColor(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //FONTE DA LISTA DE RECADO PÚBLICOS
                        Moniersn.jt_listaRecadosPubUsuario.setForeground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FUNDO DA LINHA SELECIONADA DA LISTA DE RECADOS PÚBLICOS  (cor do recado)
                        Moniersn.jt_listaRecadosPubUsuario.setSelectionBackground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FONTE DA LINHA SELECIONADA DA LISTA DE RECADOS PÚBLICOS (cor de fundo da tabela)
                        Moniersn.jt_listaRecadosPubUsuario.setSelectionForeground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //QT REC PUB ENCONTRADA
                        Moniersn.lb_qtdRecPubEncontrado.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //PESQUISAR REC PUB
                        Moniersn.lb_pesquisarRecPub.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        

          //LISTA DE RECADOS PRIVADOS

                        //PAINEL (rec. privado) DO USUARIO
                        Moniersn.jp_recPrivados.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "atu de nao amigos"
                        Moniersn.rb_todosRecadosPrivRecebidos.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "atu de amigos"
                        Moniersn.rb_todosRecadosPrivRecebidos.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "atu de amigos"
                        Moniersn.rb_recadosPrivNaoLidos.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "atu de amigos"
                        Moniersn.rb_recadosPrivNaoLidos.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "minhas ocorrencias"
                        Moniersn.rb_recadosPrivEnviados.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "minhas ocorrencias"
                        Moniersn.rb_recadosPrivEnviados.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //FUNDO DA LISTA DE RECADOS PRIVADOS
                        Moniersn.jt_listaRecadosPriv.setBackground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //GRID DA LISTA DE RECADOS PRIVADOS
                        Moniersn.jt_listaRecadosPriv.setGridColor(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //FONTE DA LISTA DE RECADO PRIVADOS
                        Moniersn.jt_listaRecadosPriv.setForeground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FUNDO DA LINHA SELECIONADA DA LISTA DE RECADOS PRIVADOS (cor do recado)
                        Moniersn.jt_listaRecadosPriv.setSelectionBackground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FONTE DA LINHA SELECIONADA DA LISTA DE RECADOS PRIVADOS (cor de fundo da tabela)
                        Moniersn.jt_listaRecadosPriv.setSelectionForeground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //QT REC PRIV ENCONTRADA
                        Moniersn.lb_qtdRecPrivEncontrado.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //PESQUISAR REC PRIV
                        Moniersn.lb_pesquisarRecPriv.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
          //LISTA DE ARQUIVOS

                        //PAINEL (rec. privado) DO USUARIO
                        Moniersn.jp_arquivosUsuario.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "atu de nao amigos"
                        Moniersn.rb_todosArquivosDisponiveis.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "atu de amigos"
                        Moniersn.rb_todosArquivosDisponiveis.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "atu de amigos"
                        Moniersn.rb_todosArquivosBaixados.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "atu de amigos"
                        Moniersn.rb_todosArquivosBaixados.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "minhas ocorrencias"
                        Moniersn.rb_todosArquivosEnviados.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "minhas ocorrencias"
                        Moniersn.rb_todosArquivosEnviados.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //FUNDO DA LISTA DE RECADOS PRIVADOS
                        Moniersn.jt_listaArquivosUsuario.setBackground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //GRID DA LISTA DE RECADOS PRIVADOS
                        Moniersn.jt_listaArquivosUsuario.setGridColor(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //FONTE DA LISTA DE RECADO PRIVADOS
                        Moniersn.jt_listaArquivosUsuario.setForeground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FUNDO DA LINHA SELECIONADA DA LISTA DE RECADOS PRIVADOS (cor do recado)
                        Moniersn.jt_listaArquivosUsuario.setSelectionBackground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FONTE DA LINHA SELECIONADA DA LISTA DE RECADOS PRIVADOS (cor de fundo da tabela)
                        Moniersn.jt_listaArquivosUsuario.setSelectionForeground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //QT ARQ ENCONTRADA
                        Moniersn.lb_qtdArqEncontrado.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //PESQUISAR ARQ
                        Moniersn.lb_pesquisarArq.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        

                 //LISTA DE AMIGOS

                        //FUNDO DA LISTA DE AMIGOS
                        Moniersn.jt_listaAmigosUsuario.setBackground(new java.awt.Color(c_rCorFundoListaAmigos, c_gCorFundoListaAmigos, c_bCorFundoListaAmigos));

                        //GRID DA LISTA DE AMIGOS
                        Moniersn.jt_listaAmigosUsuario.setGridColor(new java.awt.Color(c_rCorFundoListaAmigos, c_gCorFundoListaAmigos, c_bCorFundoListaAmigos));

                        //FONTE DA LISTA DE AMIGOS
                        Moniersn.jt_listaAmigosUsuario.setForeground(new java.awt.Color(c_rCorNomeAmigoNaLista, c_gCorNomeAmigoNaLista, c_bCorNomeAmigoNaLista));

                        //FUNDO DA LINHA SELECIONADA DA LISTA DE AMIGOS (cor do nome do amigo na lista)
                        Moniersn.jt_listaAmigosUsuario.setSelectionBackground(new java.awt.Color(c_rCorNomeAmigoNaLista, c_gCorNomeAmigoNaLista, c_bCorNomeAmigoNaLista));

                        //FONTE DA LINHA SELECIONADA DA LISTA DE AMIGOS (fundo da lista de amigos)
                        Moniersn.jt_listaAmigosUsuario.setSelectionForeground(new java.awt.Color(c_rCorFundoListaAmigos, c_gCorFundoListaAmigos, c_bCorFundoListaAmigos));

                 //LITA DE AMIGOS CONECTADOS DO BATE PAPO

                        //PAINEL (bate papo) DO USUARIO
                        Moniersn.jp_batePapo.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //BORDA label "enviando para..."
                        Moniersn.lb_nomeIconeAmigoBatePapo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enviando para...", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 1, 11), new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome))); // NOI18N

                        //TEXTO label "enviando para..."
                        Moniersn.lb_nomeIconeAmigoBatePapo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //BORDA painel "quem me vê"
                        Moniersn.jp_quemMeVe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quem bate papo comigo?", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 1, 11), new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome))); // NOI18N

                        //BORDA painel "quem me vê"
                        Moniersn.jp_quemMeVe.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO radio "todos(conectados)"
                        Moniersn.rb_todosBatemPapoComigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO radio "todos(conectados)"
                        Moniersn.rb_todosBatemPapoComigo.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO radio "amigos(conectados)"
                        Moniersn.rb_soAmigosBatemPapoComigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO radio "todos(conectados)"
                        Moniersn.rb_soAmigosBatemPapoComigo.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //TEXTO cb "atu de amigos"
                        Moniersn.cb_enviarParaTodosConectados.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FUNDO cb "atu de amigos"
                        Moniersn.cb_enviarParaTodosConectados.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //FUNDO DA LISTA DE AMIGOS CONECTADOS
                        Moniersn.jt_listaAmigosConectados.setBackground(new java.awt.Color(c_rCorFundoListaAmigos, c_gCorFundoListaAmigos, c_bCorFundoListaAmigos));

                        //GRID DA LISTA DE AMIGOS
                        Moniersn.jt_listaAmigosConectados.setGridColor(new java.awt.Color(c_rCorFundoListaAmigos, c_gCorFundoListaAmigos, c_bCorFundoListaAmigos));

                        //FONTE DA LISTA DE AMIGOS
                        Moniersn.jt_listaAmigosConectados.setForeground(new java.awt.Color(c_rCorNomeAmigoNaLista, c_gCorNomeAmigoNaLista, c_bCorNomeAmigoNaLista));

                        //FUNDO DA LINHA SELECIONADA DA LISTA DE AMIGOS (cor do nome do amigo na lista)
                        Moniersn.jt_listaAmigosConectados.setSelectionBackground(new java.awt.Color(c_rCorNomeAmigoNaLista, c_gCorNomeAmigoNaLista, c_bCorNomeAmigoNaLista));

                        //FONTE DA LINHA SELECIONADA DA LISTA DE AMIGOS (fundo da lista de amigos)
                        Moniersn.jt_listaAmigosConectados.setSelectionForeground(new java.awt.Color(c_rCorFundoListaAmigos, c_gCorFundoListaAmigos, c_bCorFundoListaAmigos));

                        //COR QTD TOTAL DE USUARIOS BATE PAPO...
                        Moniersn.lb_qtdTotalUsuarioBatePapo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //COR QTD TOTAL DE USUARIOS BATE PAPO MOSTRADOS NA LISTA...
                        Moniersn.lb_qtdUsuarioListaBatePapo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        
                }

                else if(l_quem.equals("amigo")){
                        //setando as cores do banco nos objetos do painel

                        //PAINEL DO AMIGO
                        Moniersn.jp_inicioAmigo.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //NOME DO AMIGO
                        Moniersn.lb_nomeIconeHumorAmigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                        //FRASE DO AMIGO
                        Moniersn.lb_fraseAmigo.setForeground(new java.awt.Color(c_rCorFrase, c_gCorFrase, c_bCorFrase));

                        //SEXO DO AMIGO
                        Moniersn.lb_sexoAmigo.setForeground(new java.awt.Color(c_rCorSexo, c_gCorSexo, c_bCorSexo));

                        //OCORRÊNCIAS
                        Moniersn.lb_ultOcorrenciaAmigo.setForeground(new java.awt.Color(c_rCorOcorrencia, c_gCorOcorrencia, c_bCorOcorrencia));

                        //QTD. COMENTS OCORRÊNCIA
                        Moniersn.lb_qtdComentarioUltOcoAmigo.setForeground(new java.awt.Color(c_rCorOcorrencia, c_gCorOcorrencia, c_bCorOcorrencia));

                        //ANIVERSÁRIO DO USUÁRIO
                        Moniersn.lb_niverAmigo.setForeground(new java.awt.Color(c_rCorNiver, c_gCorNiver, c_bCorNiver));

                        //DATA DE CADASTRO
                        Moniersn.lb_dataCadastroAmigo.setForeground(new java.awt.Color(c_rCorDataCadastro, c_gCorDataCadastro, c_bCorDataCadastro));

                        //DEIXE SEU RECADO PRIVADO
                        Moniersn.lb_escrevaUmRecPriv.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //COR LABEL QTD USUARIOS
                        Moniersn.lb_qtdUsuariosListaAmigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

              //RECADOS PÚBLICOS

                        //FUNDO DA LISTA DE RECADOS PÚBLICOS DO AMIGO
                        Moniersn.jt_listaRecadosPubAmigo.setBackground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //GRID DA LISTA DE RECADOS PÚBLICOS DO AMIGO
                        Moniersn.jt_listaRecadosPubAmigo.setGridColor(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //FONTE DA LISTA DE RECADOS PÚBLICOS DO AMIGO
                        Moniersn.jt_listaRecadosPubAmigo.setForeground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FUNDO DA LINHA SELECIONADA DA LISTA DE RECADOS PUBLICOS DO AMIGO (cor do recado na lista)
                        Moniersn.jt_listaRecadosPubAmigo.setSelectionBackground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FONTE DA LINHA SELECIONADA DA LISTA DE RECADOS PÚBLICOS DO AMIGO (cor de fundo da lista)
                        Moniersn.jt_listaRecadosPubAmigo.setSelectionForeground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //QT ATU ENCONTRADA
                        Moniersn.lb_qtdRecPubEncontradoAmigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //PESQUISAR ATUALIZAÇÃO
                        Moniersn.lb_pesquisarRecPubAmigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
             //LISTA DE OCORRÊNCIAS

                        //PAINEL (ocorrencia) DO USUARIO
                        Moniersn.jp_ocorrenciaAmigo.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //FUNDO DA LISTA DE RECADOS OCORRÊNCIAS DO AMIGO
                        Moniersn.jt_listaOcorrenciaAmigo.setBackground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //GRID DA LISTA DE RECADOS OCORRÊNCIAS DO AMIGO
                        Moniersn.jt_listaOcorrenciaAmigo.setGridColor(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //FONTE DA LISTA DE RECADOS OCORRÊNCIAS DO AMIGO
                        Moniersn.jt_listaOcorrenciaAmigo.setForeground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FUNDO DAS LINHA SELECIONADA DA LISTYA DE OCORRÊNCIAS DO AMIGO (cor da ocorrência na lista)
                        Moniersn.jt_listaOcorrenciaAmigo.setSelectionBackground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FONTE DA LINHA SELECIONADA DA LISTA DE OCORRÊNCIAS DO AMIGO (cor de fundo da lista)
                        Moniersn.jt_listaOcorrenciaAmigo.setSelectionForeground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //QT OCO ENCONTRADA
                        Moniersn.lb_qtdOcoEncontradaAmigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //PESQUISAR OCORRRENCIA
                        Moniersn.lb_pesquisarOcoAmigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        
             //LISTA DE ATUALIZACOES

                        //PAINEL (atualizações) DO USUARIO
                        Moniersn.jp_atualizacoesAmigo.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //FUNDO DA LISTA DE ATUALIZACOES DO AMIGO
                        Moniersn.jt_listaAtualizacoesAmigo.setBackground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //GRID DA LISTA DE ATUALIZACOES DO AMIGO
                        Moniersn.jt_listaAtualizacoesAmigo.setGridColor(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //FONTE DA LISTA DE ATUALIZACOES DO AMIGO
                        Moniersn.jt_listaAtualizacoesAmigo.setForeground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FUNDO DAS LINHA SELECIONADA DA LISTYA DE ATUALIZACOES DO AMIGO (cor da ocorrência na lista)
                        Moniersn.jt_listaAtualizacoesAmigo.setSelectionBackground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FONTE DA LINHA SELECIONADA DA LISTA DE ATUALIZACOES DO AMIGO (cor de fundo da lista)
                        Moniersn.jt_listaAtualizacoesAmigo.setSelectionForeground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //QT ATU ENCONTRADA
                        Moniersn.lb_qtdAtuEncontradaAmigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //PESQUISAR ATUALIZAÇÃO
                        Moniersn.lb_pesquisarAtuAmigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
             //LISTA DE ARQUIVOS

                        //PAINEL (ARQUIVOS DO AMIGO) DO USUARIO
                        Moniersn.jp_arquivosAmigo.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //FUNDO DA LISTA DE ARQ ARQUIVOS DO AMIGO
                        Moniersn.jt_listaArquivosAmigo.setBackground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //GRID DA LISTA DE ARQUIVOS DO AMIGO
                        Moniersn.jt_listaArquivosAmigo.setGridColor(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //FONTE DA LISTA DE ARQUIVOS DO AMIGO
                        Moniersn.jt_listaArquivosAmigo.setForeground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FUNDO DAS LINHA SELECIONADA DA LISTYA DE ARQUIVOS DO AMIGO (cor da ocorrência na lista)
                        Moniersn.jt_listaArquivosAmigo.setSelectionBackground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                        //FONTE DA LINHA SELECIONADA DA LISTA DE ARQUIVOS DO AMIGO (cor de fundo da lista)
                        Moniersn.jt_listaArquivosAmigo.setSelectionForeground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));

                        //QT ARQ ENCONTRADA
                        Moniersn.lb_qtdArqEncontradoAmigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        //PESQUISAR ARQ
                        Moniersn.lb_pesquisarArqAmigo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
                        
                        
              //LISTA DE AMIGOS

                        //PAINEL (atualizações) DO USUARIO
                        Moniersn.jp_recPublicosAmigo.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                        //FUNDO DA LISTA DE AMIGOS
                        Moniersn.jt_listaAmigosDoAmigo.setBackground(new java.awt.Color(c_rCorFundoListaAmigos, c_gCorFundoListaAmigos, c_bCorFundoListaAmigos));

                        //GRID DA LISTA DE AMIGOS
                        Moniersn.jt_listaAmigosDoAmigo.setGridColor(new java.awt.Color(c_rCorFundoListaAmigos, c_gCorFundoListaAmigos, c_bCorFundoListaAmigos));

                        //FONTE DA LISTA DE AMIGOS
                        Moniersn.jt_listaAmigosDoAmigo.setForeground(new java.awt.Color(c_rCorNomeAmigoNaLista, c_gCorNomeAmigoNaLista, c_bCorNomeAmigoNaLista));

                        //FUNDO DA LINHA SELECIONADA DA LISTA DE AMIGOS (cor do nome do amigo na lista)
                        Moniersn.jt_listaAmigosDoAmigo.setSelectionBackground(new java.awt.Color(c_rCorNomeAmigoNaLista, c_gCorNomeAmigoNaLista, c_bCorNomeAmigoNaLista));

                        //FONTE DA LINHA SELECIONADA DA LISTA DE AMIGOS (cor de fundo da lista)
                        Moniersn.jt_listaAmigosDoAmigo.setSelectionForeground(new java.awt.Color(c_rCorFundoListaAmigos, c_gCorFundoListaAmigos, c_bCorFundoListaAmigos));

                }

                //TELA DE CONFIGURAÇÕES DO USUÁRIO
                else if(l_quem.equals("conf-usuario")){
                
                    //COR DO PAINEL
                    Configuracoes.jp_painelTesteCor.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));

                    //NOME DO USUÁRIO
                    Configuracoes.lb_usuarioConf.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));

                    //FRASE DO USUÁRIO
                    Configuracoes.lb_fraseUsuarioConf.setForeground(new java.awt.Color(c_rCorFrase, c_gCorFrase, c_bCorFrase));

                    //ÍCONE USUÁRIO
                    Configuracoes.lb_fraseUsuarioConf.setIcon(Moniersn.lb_fraseUsuario.getIcon());

                    //OCORRÊNCIA
                    Configuracoes.lb_ocorrenciaUsuarioConf.setForeground(new java.awt.Color(c_rCorOcorrencia, c_gCorOcorrencia, c_bCorOcorrencia));

                    //ANIVERSÁRIO
                    Configuracoes.lb_niverConf.setForeground(new java.awt.Color(c_rCorNiver, c_gCorNiver, c_bCorNiver));

                    //SEXO
                    Configuracoes.lb_sexoConf.setForeground(new java.awt.Color(c_rCorSexo, c_gCorSexo, c_bCorSexo));

                    //DATA DE CADASTRO
                    Configuracoes.lb_dataCadastroConf.setForeground(new java.awt.Color(c_rCorDataCadastro, c_gCorDataCadastro, c_bCorDataCadastro));

                    //COR DA FONTE DA LISTA DE AMIGOS
                    Configuracoes.jt_listaAmigosConf.setForeground(new java.awt.Color(c_rCorNomeAmigoNaLista, c_gCorNomeAmigoNaLista, c_bCorNomeAmigoNaLista));
                    Configuracoes.jt_listaAmigosConf.isRequestFocusEnabled();

                    //COR DE FUNDO DA LISTA DE AMIGOS
                    Configuracoes.jt_listaAmigosConf.setBackground(new java.awt.Color(c_rCorFundoListaAmigos, c_gCorFundoListaAmigos, c_bCorFundoListaAmigos));

                    //COR DA FONTE DA LISTA DE RECADOS
                    Configuracoes.jt_listaRecadosConf.setForeground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));

                    //COR DE FUNDO DA LISTA DE RECADOS
                    Configuracoes.jt_listaRecadosConf.setBackground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));
                }

//                else if(l_quem.equals("comentario")){
//
//                    //PAINEL DE FUNDO
//                    Comentario.jp_fundoRecado.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));
//
//                    //PAINEL NOME EMISSOR
//                    Comentario.lb_emissorConteudo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
//
//                    //DESCRIÇÃO DO CONTEUDO
//                    Comentario.lb_descricaoConteudo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
//
//                    //DESTINATARIO
//                    Comentario.lb_destinatarioConteudo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
//
//                    //DATA DE POSTAGEM
//                    Comentario.lb_dataPostagem.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
//
//                    //CONTEUDO
//                    Comentario.lb_conteudo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
//
//                    //QTD DE COMENTARIOS
//                    Comentario.lb_qtdComentarios.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
//
//                    //ULTIMA ATUALIZAÇÃO
//                    Comentario.lb_ultimaAtu.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
//
//                    //DESCRIÇAO DE PRIV DO CONTEUDO
//                    Comentario.lb_descricaoPrivConteudo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
//
//                    //COMENTARIO ANONIMO
//                    Comentario.cb_comentarioAnonimo.setBackground(new java.awt.Color(c_rCorPainel, c_gCorPainel, c_bCorPainel));
//
//                    //COMENTARIO ANONIMO
//                    Comentario.cb_comentarioAnonimo.setForeground(new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome));
//
//                    //FUNDO DA LISTA DE COMENTARIOS
//                    Comentario.jt_listaComentarios.setBackground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));
//
//                    //GRID DA LISTA DE COMENTARIOS
//                    Comentario.jt_listaComentarios.setGridColor(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));
//
//                    //FONTE DA LISTA DE COMENTARIOS
//                    Comentario.jt_listaComentarios.setForeground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));
//
//                    //FUNDO DAS LINHA SELECIONADA DA LISTYA DE COMENTARIOS
//                    Comentario.jt_listaComentarios.setSelectionBackground(new java.awt.Color(c_rCorRecado, c_gCorRecado, c_bCorRecado));
//
//                    //FONTE DA LINHA SELECIONADA DA LISTA DE COMENTARIOS
//                    Comentario.jt_listaComentarios.setSelectionForeground(new java.awt.Color(c_rCorFundoListaRecado, c_gCorFundoListaRecado, c_bCorFundoListaRecado));
//
//                    Comentario.lb_emissorConteudo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, LogUsuario.s_tituloBordaEmissorConteudoComent, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 12), new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome))); // NOI18N
//
//                    Comentario.lb_destinatarioConteudo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, LogUsuario.s_tituloBordaDestinatarioConteudoComent, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 12), new java.awt.Color(c_rCorNome, c_gCorNome, c_bCorNome))); // NOI18N
//                }

            }
            catch(Exception e){
                System.err.println("Erro ao tentar configurar as cores do painel do "+l_quem+".   \nErro: "+e);
            }
    }


    public void separadorRGB(String l_StringCorRgb, String l_objeto){

       String l_corRGB;
       int l_posicaoVirg1, l_posicaoVirg2;//posição de cada virgula
       int r, g, b, l_tamanhoStringRgb;              //cores detalhadas


       //
       l_corRGB = l_StringCorRgb;

       //pega o l_tamanhoStringRgb da string de cor
       l_tamanhoStringRgb = l_corRGB.length();

       //pega a posição da virgula 1
       l_posicaoVirg1 = l_corRGB.indexOf(",");

       //pega R
       r = Integer.parseInt(l_corRGB.substring(0, l_posicaoVirg1));

       //pega a posição da virgula 2
       l_posicaoVirg2 = l_corRGB.substring((l_posicaoVirg1+1), l_tamanhoStringRgb).indexOf(",") + (l_posicaoVirg1+1);

       //pega G
       g = Integer.parseInt(l_corRGB.substring((l_posicaoVirg1+2), l_posicaoVirg2));

       //pega B
       b = Integer.parseInt(l_corRGB.substring((l_posicaoVirg2+2), l_tamanhoStringRgb));

       //JOptionPane.showMessageDialog(null,"COR RBG = "+l_corRGB+"\n\nPegando separadamente:\nR = "+r+"\nG = "+g+"\nB = "+b);

       //System.out.println(l_objeto+": "+r+", "+g+", "+b);
       
        if(l_objeto.equals("cor_painel")){

                c_rCorPainel = r;
                c_gCorPainel = g;
                c_bCorPainel = b;

            return;
        }
        else if(l_objeto.equals("cor_nome")){

                c_rCorNome = r;
                c_gCorNome = g;
                c_bCorNome = b;

            return;
        }
        else if(l_objeto.equals("cor_frase")){


                c_rCorFrase = r;
                c_gCorFrase = g;
                c_bCorFrase = b;

            return;
        }

        else if(l_objeto.equals("cor_ocorrencia")){


                c_rCorOcorrencia = r;
                c_gCorOcorrencia = g;
                c_bCorOcorrencia = b;

            return;
        }

        else if(l_objeto.equals("cor_sexo")){


                c_rCorSexo = r;
                c_gCorSexo = g;
                c_bCorSexo = b;

            return;
        }


        else if(l_objeto.equals("cor_aniversario")){


                c_rCorNiver = r;
                c_gCorNiver = g;
                c_bCorNiver = b;

            return;
        }

        else if(l_objeto.equals("cor_data_cadastro")){

                c_rCorDataCadastro = r;
                c_gCorDataCadastro = g;
                c_bCorDataCadastro = b;

            return;
        }

        else if(l_objeto.equals("cor_lista_recado")){


                c_rCorFundoListaRecado = r;
                c_gCorFundoListaRecado = g;
                c_bCorFundoListaRecado = b;

            return;
        }
        else if(l_objeto.equals("cor_recado")){


                c_rCorRecado = r;
                c_gCorRecado = g;
                c_bCorRecado = b;

            return;
        }
        else if(l_objeto.equals("cor_lista_amigos")){

                c_rCorFundoListaAmigos = r;
                c_gCorFundoListaAmigos = g;
                c_bCorFundoListaAmigos = b;

            return;
        }
        else if(l_objeto.equals("cor_nome_amigo")){


                c_rCorNomeAmigoNaLista = r;
                c_gCorNomeAmigoNaLista = g;
                c_bCorNomeAmigoNaLista = b;

            return;
        }

    }


}
