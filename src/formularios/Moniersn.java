/*
 * Moniersn.java
 *
 * Created on 19/04/2010, 13:59:10
 */

package formularios;

import auxiliar.*;
import auxiliar.sockets.ClienteUDP;
import auxiliar.threads.MostrarConfiguracoes;
import auxiliar.threads.LogoCarregando;
import javax.swing.ImageIcon;
import javax.swing.table.TableModel;
/**
 *
 * @author ayrton monier
 */


public class Moniersn extends javax.swing.JDialog {

    //VARIÁVEIS ESTÁTICAS DE CLASSE

    //variável estática da classe Moniersn
    public static Moniersn s_telaMsn = null;

    //define a lista de rec. public que está sendo comentado o recado (amigo / usuario)
    public static String s_listaRecPubComentada;

    //decide se abre configuracoes
    public static boolean s_podeAbrir;

    //guarda o caminho do arquivo
    public static String s_caminhoDoArquivo;

    public static boolean c_naFrente = true;

    /** Creates new form Moniersn */
    public Moniersn(java.awt.Frame parent, boolean modal){


        System.out.println("#INICIALIZANDO APLICAÇÃO#\n "
                          +"Usuario: "+LogUsuario.s_moniersn+"\n\n");

            try{
                System.out.println("Inicializando componentes...");
                initComponents();
            }catch(Exception e){
                System.err.println("Erro ao tentar inicializar componentes. \nErro: "+e);
            }
           

        //SETA VALORES PADRÃƒO
        System.out.println("Setando valores padroes de inicializacao...");

        lb_qtdRecInst.setVisible(false);
        
//        Aguardando.s_telaAguardando = new Aguardando(new javax.swing.JFrame(), true);
        LogUsuario.s_abaAtualSelecionada = jp_inicioUsuario;
        LogUsuario.s_listaAmigosAtual = "Todos(geral)";
        LogUsuario.s_listaRecPubSelecionada = "rec";//recebidos, enviados, nao lidos
        LogUsuario.s_listaRecPrivSelecionada = "rec";//recebidos, enviados, nao lidos
        LogUsuario.s_listaOcorrenciaSelecionada = "rec";//recebidos, enviados
        LogUsuario.s_listaAtualizacoesSelecionada = "recAmigos";//recebidos(amigos), enviados(minhas)
        LogUsuario.s_listaArquivoSelecionada = "disponiveis";//disponiveis, baixados, enviados
        LogUsuario.s_mostrandoPainelAmigo = false;
        LogUsuario.s_mostrandoSolicitacaoAmizade = false;
        
        //seta tip texts...
        bt_configuracoes.setToolTipText(Avisos.TIP_TEXT_CONFIGURACOES);
        bt_atualizar.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_INFO_GERAL);
        bt_compartilharArquivoGeral.setToolTipText(Avisos.TIP_TEXT_COMPARTILHAR_ARQUIVO);
        bt_desconectar.setToolTipText(Avisos.TIP_TEXT_DESCONECTAR);
        lb_ninguemBatePapoComigoInicio.setToolTipText(Avisos.TIP_TEXT_NINGUEM_BATE_PAPO_COMIGO); 
        
        cb_tipoAmigos.setToolTipText(Avisos.TIP_TEXT_QUE_TIPO_USUARIO_MOSTRAR_LISTA);
        
        //LISTA USUARIO
        tf_pesquisaUsuario.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_USUARIOS);
        bt_verMaisUsuarios.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_USUARIOS);
        bt_atualizarListaUsuarios.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_USUARIOS);
        
        //LISTA USUARIO BATE PAPO
        tf_pesquisaUsuarioBatePapo.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_USUARIOS);
        bt_verMaisUsuariosBatePapo.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_USUARIOS);
        bt_atualizarListaUsuariosBatePapo.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_USUARIOS);
     
        //LISTA USUARIO AMIGO
        tf_pesquisaListaAmigo.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_USUARIOS);
        bt_verMaisUsuariosListaAmigo.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_USUARIOS);
        bt_atuUsuariosListaAmigo.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_USUARIOS);
        
        
        //LISTA OCORRENCIAS USUÁRIO
        cb_anonimo.setToolTipText(Avisos.TIP_TEXT_OCORRENCIA_ANONIMA); 
        tf_ocorrencia.setToolTipText(Avisos.TIP_TEXT_ESCREVA_UMA_OCORRENCIA); 
        bt_addOcorrencia.setToolTipText(Avisos.TIP_TEXT_ENVIAR_OCORRENCIA); 
        tf_conteudoOcoPesquisado.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_OCORRENCIAS);
        bt_verMaisOcoUsuario.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_OCORRENCIAS);
        bt_atuListaOcoUsuario.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_OCORRENCIAS);
        bt_excluirOcorrencia.setToolTipText(Avisos.TIP_TEXT_REMOVER_OCORRENCIA);
        bt_comentarOcorrencia.setToolTipText(Avisos.TIP_TEXT_COMENTAR_OCORRENCIA);

        //LISTA OCORRENCIAS AMIGO
        tf_conteudoOcoPesquisadoAmigo.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_OCORRENCIAS);
        bt_verMaisOcoAmigo.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_OCORRENCIAS);
        bt_atuListaOcoAmigo.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_OCORRENCIAS);
        bt_comentarOcorrenciaAmigo.setToolTipText(Avisos.TIP_TEXT_COMENTAR_OCORRENCIA);
        
        
        //LISTA ATUALIZAÇÃO
        tf_conteudoAtuPesquisado.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_ATUALIZACOES);
        bt_verMaisAtuUsuario.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_ATUALIZACOES);
        bt_atuListaAtuUsuario.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_ATUALIZACOES);
        bt_excluirAtualizacoes.setToolTipText(Avisos.TIP_TEXT_REMOVER_ATUALIZACOES);
        bt_comentarAtuUsuario.setToolTipText(Avisos.TIP_TEXT_COMENTAR_ATUALIZACAO);
        
        //LISTA ATUALIZAÇÃO AMIGO
        tf_conteudoAtuPesquisadoAmigo.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_ATUALIZACOES);
        bt_verMaisAtuAmigo.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_ATUALIZACOES);
        bt_atuListaAtuAmigo.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_ATUALIZACOES);
        bt_comentarAtuAmigo.setToolTipText(Avisos.TIP_TEXT_COMENTAR_ATUALIZACAO);
        
        
        //LISTA REC PUBLICO
        tf_conteudoRecPubPesquisado.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_REC_PUBLICOS);
        bt_verMaisRecPubUsuario.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_REC_PUB);
        bt_atuListaRecPubUsuario.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_REC_PUB);
        bt_marcarComoLidoRecPub.setToolTipText(Avisos.TIP_TEXT_MARCAR_REC_PUB);
        bt_excluirRecPub.setToolTipText(Avisos.TIP_TEXT_REMOVER_REC_PUB);
        bt_comentarRecPublico.setToolTipText(Avisos.TIP_TEXT_COMENTAR_REC_PUB);
        

        //LISTA REC PUBLICO AMIGO
        tf_conteudoRecPubPesquisadoAmigo.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_REC_PUBLICOS);
        bt_verMaisRecPubAmigo.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_REC_PUB);
        bt_atuListaRecPubAmigo.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_REC_PUB);
        bt_comentarRecPubAmigo.setToolTipText(Avisos.TIP_TEXT_COMENTAR_REC_PUB);
        bt_enviarRecPub.setToolTipText(Avisos.TIP_TEXT_ENVIAR_REC_PUB);
        tf_recadoPub.setToolTipText(Avisos.TIP_TEXT_ESCREVA_UM_REC_PUB);
        
        //LISTA REC PRIVADO
        tf_conteudoRecPrivPesquisado.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_REC_PRIVADOS);
        bt_verMaisRecPrivUsuario.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_REC_PRIV);
        bt_atuListaRecPrivUsuario.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_REC_PRIV);
        bt_marcarComoLidoRecPriv.setToolTipText(Avisos.TIP_TEXT_MARCAR_REC_PRIV);
        bt_excluirRecPriv.setToolTipText(Avisos.TIP_TEXT_REMOVER_REC_PRIV);
        bt_enviarRecPriv.setToolTipText(Avisos.TIP_TEXT_ENVIAR_REC_PRIV);
        bt_comentarRecPriv.setToolTipText(Avisos.TIP_TEXT_COMENTAR_REC_PRIV);
        
        //ESCREVA REC PRIVADO AMIGO
        tf_recadoPriv.setToolTipText(Avisos.TIP_TEXT_ESCREVA_UM_REC_PRIV);
        
        //LISTA ARQUIVOS
        tf_nomeArqPesquisado.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_ARQUIVOS);
        bt_verMaisArquivosUsuario.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_ARQUIVOS);
        bt_atuListaArquivosUsuario.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_ARQUIVOS);
        bt_abrirLocalArquivos.setToolTipText(Avisos.TIP_TEXT_ABRIR_LOCAL_ARQ);
        bt_abrirArquivoSelecionado.setToolTipText(Avisos.TIP_TEXT_ABRIR_ARQ_SELECIONADO);
        bt_baixarArquivoSelecionado.setToolTipText(Avisos.TIP_TEXT_BAIXAR_ARQ_SELECIONADO);
        bt_encaminharArquivo.setToolTipText(Avisos.TIP_TEXT_COMPARTILHAR_ARQ_SELECIONADO);
        bt_removerArquivoSelecionado.setToolTipText(Avisos.TIP_TEXT_REMOVER_ARQ_SELECIONADO);
        bt_comentarArquivoSelecionadoUsuario.setToolTipText(Avisos.TIP_TEXT_COMENTAR_ARQ_SELECIONADO);   
        
        
        //LISTA ARQUIVOS AMIGO
        tf_nomeArqPesquisadoAmigo.setToolTipText(Avisos.TIP_TEXT_PESQUISAR_ARQUIVOS);
        bt_verMaisArqAmigo.setToolTipText(Avisos.TIP_TEXT_VISU_MAIS_ARQUIVOS);
        bt_atuListaArqAmigo.setToolTipText(Avisos.TIP_TEXT_ATUALIZAR_LISTA_ARQUIVOS);
        bt_comentarArquivoSelecionadoAmigo.setToolTipText(Avisos.TIP_TEXT_COMENTAR_ARQ_SELECIONADO);  
        bt_baixarArquivoSelecionadoAmigo.setToolTipText(Avisos.TIP_TEXT_BAIXAR_ARQ_SELECIONADO);
        
         //BATE PAPO
         bt_visuPainelAmigo.setToolTipText(Avisos.TIP_TEXT_VER_USU_SELECIONADO);
         bt_inicio.setToolTipText(Avisos.TIP_TEXT_INICIO);
         tf_recadoInst.setToolTipText(Avisos.TIP_TEXT_ESVREVA_UM_REC_INST);
         bt_enviarRecInst.setToolTipText(Avisos.TIP_TEXT_ENVIAR_REC_INST);
         bt_limpaCampoRecInst.setToolTipText(Avisos.TIP_TEXT_LIMPAR_REC_INST);
         bt_limparConversa.setToolTipText(Avisos.TIP_TEXT_REMOVER_CONVERSACAO);         
         lb_ninguemBatePapoComigo.setToolTipText(Avisos.TIP_TEXT_NINGUEM_BATE_PAPO_COMIGO); 
        
        
        //REDIMENSIONA FORMULÃRIO
        System.out.println("Dimensionando formulario...");
        this.setSize(jtp_painelTabuladoUsuario.getSize());
        
        //APLICA CONFIGURAÃ‡OES DE CORES NO PAINEL DO USUÃRIO
        System.out.println("Aplicando configuracoes de cores ao painel do usuario...");
        Cores.s_Cores.atualizaCores(LogUsuario.s_cod, "usuario");

        //SETA INFORMAÃ‡Ã•ES PADRÃƒO DO PAINEL DO USUÃRIO
        System.out.println("Setando informacoes basicas do usuario...");
        MostrarDados.s_MostrarDados.carregaInfoBasicas(LogUsuario.s_cod, "inicio-usuario");
 
        System.out.println("Pegando data do ultimo logout...");
        SolicitarDados.s_SolicitarDados.pegaDataUltimoLogOut(LogUsuario.s_cod);

        //GRAVA LOG DE CONEXAO
        System.out.println("Gravando log de conexao...");
        LogMoniersn.s_LogMoniersn.logConectar();

        //OCORRENCIAS RECEBIDAS DESDE O ULTIMO LOGOUT
        LogUsuario.s_qtdOcoRecDesdUltLogout = SolicitarDados.s_SolicitarDados.defineTotalDeOcorrencias(LogUsuario.s_cod, "rec", "desdUltLogout");

        //DE ESTRANHOS
        LogUsuario.s_qtdAtuRecDesdUltLogoutDeEstranhos = SolicitarDados.s_SolicitarDados.defineTotalDeAtualizacoes(LogUsuario.s_cod, "recEstranhos", "desdUltLogout");

        //DE AMIGOS
        LogUsuario.s_qtdAtuRecDesdUltLogoutDeAmigos = SolicitarDados.s_SolicitarDados.defineTotalDeAtualizacoes(LogUsuario.s_cod, "recAmigos", "desdUltLogout");

        //CRIA ÃCONE DE NOTIFICAÃ‡ÃƒO
        System.out.println("Criando icone de notificacao...");
        SolicitarDados.s_SolicitarDados.getAreaNotificacao();

        //PREENCHE LISTA DE RECADOS PRIVADOS DO USUÃRIO
        System.out.println("Preenchendo lista de recados privados...");
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPriv, "rec");

        //PREENCHE LISTA DE RECADOS PÃšBLICOS DO USUÃRIO
        System.out.println("Preenchendo lista de recados publicos...");
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPubUsuario, "rec");

        //PREENCHE LISTA DE OCORRÃŠNCIAS DO  USUARIO
        System.out.println("Preenchendo lista de ocorrencias...");
        MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, jt_listaOcorrenciaUsuario, "rec");

        //SETA ULTIMA OCORRENCIA
        System.out.println("Setando última ocorrencia...");
        MostrarDados.s_MostrarDados.setaUltimaOcorrencia(LogUsuario.s_cod, "usuario");

        //PREENCHE LISTA DE ATUALIZAÃ‡Ã•ES DO USUÃRIO - atualizacoes
        System.out.println("Preenchendo lista de atualizacoes...");
        MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, jt_listaAtualizacoesUsuario, "recAmigos");

        //MOSTRA A TELA PRINCIPAL
        System.out.println("Setando nome de usuario no titulo da tela...");
        this.setTitle(LogUsuario.s_nome+" "+LogUsuario.s_sobrenome+" - "+Avisos.FACECARD_FULL);

        //Contagem de ítens iniciada
        System.out.println("Contagem de ítens iniciada...");
        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);
        
        //verifica se os servidores estão ok no geral (0) na inicialização (1) qualquer momento
        SolicitarDados.s_SolicitarDados.estabilizaServidores(0);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_visuRecadosPub = new javax.swing.ButtonGroup();
        bg_visuRecadosPriv = new javax.swing.ButtonGroup();
        bg_visuOcorrencia = new javax.swing.ButtonGroup();
        bg_visuAmigos = new javax.swing.ButtonGroup();
        bg_visuAmigosAmigos = new javax.swing.ButtonGroup();
        bg_visuAtualizacoes = new javax.swing.ButtonGroup();
        bg_visuArquivos = new javax.swing.ButtonGroup();
        bg_quemBatePapoComigo = new javax.swing.ButtonGroup();
        jtp_painelTabuladoUsuario = new javax.swing.JTabbedPane();
        jp_inicioUsuario = new javax.swing.JPanel();
        bt_configuracoes = new javax.swing.JButton();
        bt_atualizar = new javax.swing.JButton();
        bt_compartilharArquivoGeral = new javax.swing.JButton();
        bt_desconectar = new javax.swing.JButton();
        lb_qtdConvites = new javax.swing.JLabel();
        lb_qtdRecInst = new javax.swing.JLabel();
        lb_nomeIconeHumorUsuario = new javax.swing.JLabel();
        lb_dataNiverUsuario = new javax.swing.JLabel();
        lb_ultimaOcorrenciaUsuario = new javax.swing.JLabel();
        lb_sexoUsuario = new javax.swing.JLabel();
        lb_dataCadastroUsuario = new javax.swing.JLabel();
        tf_ocorrencia = new javax.swing.JTextField();
        lb_iconeOcorrencia = new javax.swing.JLabel();
        bt_addOcorrencia = new javax.swing.JButton();
        lb_escrevaUmaOcorrencia = new javax.swing.JLabel();
        lb_fraseUsuario = new javax.swing.JLabel();
        sp_listaAmigosUsuario = new javax.swing.JScrollPane();
        jt_listaAmigosUsuario = new javax.swing.JTable();
        cb_anonimo = new javax.swing.JCheckBox();
        lb_qtdComentarioUltOcoUsuario = new javax.swing.JLabel();
        bt_atualizarListaUsuarios = new javax.swing.JButton();
        lb_mostrarNaoMostrarNome = new javax.swing.JLabel();
        lb_seguranca = new javax.swing.JLabel();
        lb_habilitarAudio = new javax.swing.JLabel();
        lb_prendeFormNaFrente = new javax.swing.JLabel();
        tf_pesquisaUsuario = new javax.swing.JTextField();
        cb_tipoAmigos = new javax.swing.JComboBox();
        bt_verMaisUsuarios = new javax.swing.JButton();
        lb_qtdUsuarioListaUsuario = new javax.swing.JLabel();
        lb_qtdTotalUsuario = new javax.swing.JLabel();
        lb_ninguemBatePapoComigoInicio = new javax.swing.JLabel();
        jp_ocorrenciaUsuario = new javax.swing.JPanel();
        sp_ocorrenciaUsuario = new javax.swing.JScrollPane();
        jt_listaOcorrenciaUsuario = new javax.swing.JTable();
        bt_comentarOcorrencia = new javax.swing.JButton();
        bt_excluirOcorrencia = new javax.swing.JButton();
        rb_ocoRecebidas = new javax.swing.JRadioButton();
        rb_ocoEnviadas = new javax.swing.JRadioButton();
        lb_prendeFormNaFrente1 = new javax.swing.JLabel();
        lb_prendeFormNaFrente2 = new javax.swing.JLabel();
        tf_conteudoOcoPesquisado = new javax.swing.JTextField();
        lb_pesquisarOco = new javax.swing.JLabel();
        lb_qtdOcoEncontrada = new javax.swing.JLabel();
        bt_atuListaOcoUsuario = new javax.swing.JButton();
        bt_verMaisOcoUsuario = new javax.swing.JButton();
        jp_atualizacoesUsuario = new javax.swing.JPanel();
        sp_atualizacoesUsuario = new javax.swing.JScrollPane();
        jt_listaAtualizacoesUsuario = new javax.swing.JTable();
        rb_atuParaTodos = new javax.swing.JRadioButton();
        rb_atuDeAmigos = new javax.swing.JRadioButton();
        bt_excluirAtualizacoes = new javax.swing.JButton();
        bt_comentarAtuUsuario = new javax.swing.JButton();
        rb_atualizacoesMinhas = new javax.swing.JRadioButton();
        lb_prendeFormNaFrente3 = new javax.swing.JLabel();
        lb_prendeFormNaFrente4 = new javax.swing.JLabel();
        lb_prendeFormNaFrente5 = new javax.swing.JLabel();
        bt_atuListaAtuUsuario = new javax.swing.JButton();
        lb_qtdAtuEncontrada = new javax.swing.JLabel();
        lb_pesquisarAtu = new javax.swing.JLabel();
        tf_conteudoAtuPesquisado = new javax.swing.JTextField();
        bt_verMaisAtuUsuario = new javax.swing.JButton();
        jp_recPublicosUsuario = new javax.swing.JPanel();
        sp_recadosPubUsuario = new javax.swing.JScrollPane();
        jt_listaRecadosPubUsuario = new javax.swing.JTable();
        bt_excluirRecPub = new javax.swing.JButton();
        bt_comentarRecPublico = new javax.swing.JButton();
        bt_marcarComoLidoRecPub = new javax.swing.JButton();
        rb_recadosPubEnviados = new javax.swing.JRadioButton();
        rb_recadosPubNaoLidos = new javax.swing.JRadioButton();
        rb_todosRecadosPubRecebidos = new javax.swing.JRadioButton();
        lb_prendeFormNaFrente6 = new javax.swing.JLabel();
        lb_prendeFormNaFrente7 = new javax.swing.JLabel();
        lb_prendeFormNaFrente8 = new javax.swing.JLabel();
        tf_conteudoRecPubPesquisado = new javax.swing.JTextField();
        lb_qtdRecPubEncontrado = new javax.swing.JLabel();
        lb_pesquisarRecPub = new javax.swing.JLabel();
        bt_atuListaRecPubUsuario = new javax.swing.JButton();
        bt_verMaisRecPubUsuario = new javax.swing.JButton();
        jp_recPrivados = new javax.swing.JPanel();
        sp_recadosPriv = new javax.swing.JScrollPane();
        jt_listaRecadosPriv = new javax.swing.JTable();
        bt_marcarComoLidoRecPriv = new javax.swing.JButton();
        bt_excluirRecPriv = new javax.swing.JButton();
        bt_comentarRecPriv = new javax.swing.JButton();
        rb_todosRecadosPrivRecebidos = new javax.swing.JRadioButton();
        rb_recadosPrivNaoLidos = new javax.swing.JRadioButton();
        rb_recadosPrivEnviados = new javax.swing.JRadioButton();
        lb_prendeFormNaFrente9 = new javax.swing.JLabel();
        lb_prendeFormNaFrente10 = new javax.swing.JLabel();
        lb_prendeFormNaFrente11 = new javax.swing.JLabel();
        bt_atuListaRecPrivUsuario = new javax.swing.JButton();
        lb_qtdRecPrivEncontrado = new javax.swing.JLabel();
        lb_pesquisarRecPriv = new javax.swing.JLabel();
        tf_conteudoRecPrivPesquisado = new javax.swing.JTextField();
        bt_verMaisRecPrivUsuario = new javax.swing.JButton();
        jp_arquivosUsuario = new javax.swing.JPanel();
        sp_arquivosUsuario = new javax.swing.JScrollPane();
        jt_listaArquivosUsuario = new javax.swing.JTable();
        bt_removerArquivoSelecionado = new javax.swing.JButton();
        bt_comentarArquivoSelecionadoUsuario = new javax.swing.JButton();
        rb_todosArquivosDisponiveis = new javax.swing.JRadioButton();
        bt_baixarArquivoSelecionado = new javax.swing.JButton();
        bt_encaminharArquivo = new javax.swing.JButton();
        lb_prendeFormNaFrente14 = new javax.swing.JLabel();
        rb_todosArquivosBaixados = new javax.swing.JRadioButton();
        lb_prendeFormNaFrente15 = new javax.swing.JLabel();
        rb_todosArquivosEnviados = new javax.swing.JRadioButton();
        lb_prendeFormNaFrente16 = new javax.swing.JLabel();
        bt_abrirArquivoSelecionado = new javax.swing.JButton();
        bt_atuListaArquivosUsuario = new javax.swing.JButton();
        lb_qtdArqEncontrado = new javax.swing.JLabel();
        lb_pesquisarArq = new javax.swing.JLabel();
        tf_nomeArqPesquisado = new javax.swing.JTextField();
        bt_verMaisArquivosUsuario = new javax.swing.JButton();
        bt_abrirLocalArquivos = new javax.swing.JButton();
        jp_batePapo = new javax.swing.JPanel();
        bt_enviarRecInst = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tp_recadosInstantaneos = new javax.swing.JTextPane();
        tf_recadoInst = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jt_listaAmigosConectados = new javax.swing.JTable();
        lb_nomeIconeAmigoBatePapo = new javax.swing.JLabel();
        bt_visuPainelAmigo = new javax.swing.JButton();
        bt_limparConversa = new javax.swing.JButton();
        bt_inicio = new javax.swing.JButton();
        lb_iconeBatePapo = new javax.swing.JLabel();
        cb_enviarParaTodosConectados = new javax.swing.JCheckBox();
        bt_limpaCampoRecInst = new javax.swing.JButton();
        jp_quemMeVe = new javax.swing.JPanel();
        rb_todosBatemPapoComigo = new javax.swing.JRadioButton();
        rb_soAmigosBatemPapoComigo = new javax.swing.JRadioButton();
        lb_ninguemBatePapoComigo = new javax.swing.JLabel();
        lb_qtdUsuarioListaBatePapo = new javax.swing.JLabel();
        tf_pesquisaUsuarioBatePapo = new javax.swing.JTextField();
        bt_verMaisUsuariosBatePapo = new javax.swing.JButton();
        bt_atualizarListaUsuariosBatePapo = new javax.swing.JButton();
        lb_qtdTotalUsuarioBatePapo = new javax.swing.JLabel();
        jtp_painelTabuladoAmigo = new javax.swing.JTabbedPane();
        jp_inicioAmigo = new javax.swing.JPanel();
        lb_nomeIconeHumorAmigo = new javax.swing.JLabel();
        lb_dataCadastroAmigo = new javax.swing.JLabel();
        sp_listaAmigosAmigo = new javax.swing.JScrollPane();
        jt_listaAmigosDoAmigo = new javax.swing.JTable();
        bt_enviarRecPriv = new javax.swing.JButton();
        lb_escrevaUmRecPriv = new javax.swing.JLabel();
        tf_recadoPriv = new javax.swing.JTextField();
        lb_ultOcorrenciaAmigo = new javax.swing.JLabel();
        lb_fraseAmigo = new javax.swing.JLabel();
        bt_batePapo = new javax.swing.JButton();
        bt_bloqDesblocAmigo = new javax.swing.JButton();
        bt_escondePainel = new javax.swing.JButton();
        lb_niverAmigo = new javax.swing.JLabel();
        lb_sexoAmigo = new javax.swing.JLabel();
        lb_iconeRecPriv = new javax.swing.JLabel();
        jp_opcoesAmigosAmigo = new javax.swing.JPanel();
        rb_qtdTotalAmigosAmigo = new javax.swing.JRadioButton();
        rb_qtdAmigosComuns = new javax.swing.JRadioButton();
        lb_qtdComentarioUltOcoAmigo = new javax.swing.JLabel();
        lb_mostrarNaoMostrarNomeAmigo = new javax.swing.JLabel();
        lb_qtdUsuariosListaAmigo = new javax.swing.JLabel();
        bt_verMaisUsuariosListaAmigo = new javax.swing.JButton();
        bt_atuUsuariosListaAmigo = new javax.swing.JButton();
        tf_pesquisaListaAmigo = new javax.swing.JTextField();
        lb_tipoUsuario = new javax.swing.JLabel();
        bt_atualizarInfoAmigo = new javax.swing.JButton();
        bt_enviarArquivo = new javax.swing.JButton();
        bt_addExcAceEspAmigo = new javax.swing.JButton();
        bt_comprimento = new javax.swing.JButton();
        jp_ocorrenciaAmigo = new javax.swing.JPanel();
        sp_ocorrenciasAmigo = new javax.swing.JScrollPane();
        jt_listaOcorrenciaAmigo = new javax.swing.JTable();
        bt_comentarOcorrenciaAmigo = new javax.swing.JButton();
        lb_qtdOcoEncontradaAmigo = new javax.swing.JLabel();
        tf_conteudoOcoPesquisadoAmigo = new javax.swing.JTextField();
        lb_pesquisarOcoAmigo = new javax.swing.JLabel();
        bt_atuListaOcoAmigo = new javax.swing.JButton();
        bt_verMaisOcoAmigo = new javax.swing.JButton();
        jp_atualizacoesAmigo = new javax.swing.JPanel();
        sp_atualizacoesAmigo = new javax.swing.JScrollPane();
        jt_listaAtualizacoesAmigo = new javax.swing.JTable();
        bt_comentarAtuAmigo = new javax.swing.JButton();
        bt_atuListaAtuAmigo = new javax.swing.JButton();
        lb_qtdAtuEncontradaAmigo = new javax.swing.JLabel();
        lb_pesquisarAtuAmigo = new javax.swing.JLabel();
        tf_conteudoAtuPesquisadoAmigo = new javax.swing.JTextField();
        bt_verMaisAtuAmigo = new javax.swing.JButton();
        jp_recPublicosAmigo = new javax.swing.JPanel();
        tf_recadoPub = new javax.swing.JTextField();
        lb_estadoAtualUsuario3 = new javax.swing.JLabel();
        bt_enviarRecPub = new javax.swing.JButton();
        sp_recadosPubAmigo = new javax.swing.JScrollPane();
        jt_listaRecadosPubAmigo = new javax.swing.JTable();
        bt_comentarRecPubAmigo = new javax.swing.JButton();
        bt_atuListaRecPubAmigo = new javax.swing.JButton();
        lb_qtdRecPubEncontradoAmigo = new javax.swing.JLabel();
        lb_pesquisarRecPubAmigo = new javax.swing.JLabel();
        tf_conteudoRecPubPesquisadoAmigo = new javax.swing.JTextField();
        bt_verMaisRecPubAmigo = new javax.swing.JButton();
        jp_arquivosAmigo = new javax.swing.JPanel();
        sp_arquivosAmigo = new javax.swing.JScrollPane();
        jt_listaArquivosAmigo = new javax.swing.JTable();
        bt_baixarArquivoSelecionadoAmigo = new javax.swing.JButton();
        bt_comentarArquivoSelecionadoAmigo = new javax.swing.JButton();
        lb_pesquisarArqAmigo = new javax.swing.JLabel();
        tf_nomeArqPesquisadoAmigo = new javax.swing.JTextField();
        lb_qtdArqEncontradoAmigo = new javax.swing.JLabel();
        bt_atuListaArqAmigo = new javax.swing.JButton();
        bt_verMaisArqAmigo = new javax.swing.JButton();
        jp_pessoal = new javax.swing.JPanel();
        painel_1_amigo = new javax.swing.JPanel();
        s_lb_quem_sou = new javax.swing.JLabel();
        s_lb_adoro = new javax.swing.JLabel();
        s_lb_fisico = new javax.swing.JLabel();
        s_lb_nacionalidade = new javax.swing.JLabel();
        s_lb_estado_civil = new javax.swing.JLabel();
        s_lb_naturalidade = new javax.swing.JLabel();
        lb_nacionalidade = new javax.swing.JLabel();
        lb_naturalidade = new javax.swing.JLabel();
        lb_estadoCivil = new javax.swing.JLabel();
        lb_fisico = new javax.swing.JLabel();
        lb_adoro = new javax.swing.JLabel();
        lb_quemSou = new javax.swing.JLabel();
        s_lb_odeio = new javax.swing.JLabel();
        lb_odeio = new javax.swing.JLabel();
        s_lb_filme = new javax.swing.JLabel();
        lb_filme = new javax.swing.JLabel();
        s_lb_musica = new javax.swing.JLabel();
        lb_musica = new javax.swing.JLabel();
        s_lb_esporte = new javax.swing.JLabel();
        lb_esporte = new javax.swing.JLabel();
        s_lb_lugar = new javax.swing.JLabel();
        lb_lugar = new javax.swing.JLabel();
        s_lb_cantada = new javax.swing.JLabel();
        lb_cantada = new javax.swing.JLabel();
        s_lb_mania = new javax.swing.JLabel();
        lb_mania = new javax.swing.JLabel();
        painel_2_amigo = new javax.swing.JPanel();
        s_lb_cor = new javax.swing.JLabel();
        lb_cor = new javax.swing.JLabel();
        s_lb_conselho = new javax.swing.JLabel();
        lb_conselho = new javax.swing.JLabel();
        s_lb_conselho1 = new javax.swing.JLabel();
        lb_sonho = new javax.swing.JLabel();
        s_lb_cor1 = new javax.swing.JLabel();
        lb_time = new javax.swing.JLabel();
        s_lb_cor2 = new javax.swing.JLabel();
        s_lb_conselho2 = new javax.swing.JLabel();
        lb_pesadelo = new javax.swing.JLabel();
        lb_bebida = new javax.swing.JLabel();
        s_lb_cor3 = new javax.swing.JLabel();
        lb_passeio = new javax.swing.JLabel();
        s_lb_conselho3 = new javax.swing.JLabel();
        lb_prato = new javax.swing.JLabel();
        lb_desejo = new javax.swing.JLabel();
        s_lb_cor4 = new javax.swing.JLabel();
        lb_viagem = new javax.swing.JLabel();
        s_lb_conselho4 = new javax.swing.JLabel();
        s_lb_conselho5 = new javax.swing.JLabel();
        s_lb_cor5 = new javax.swing.JLabel();
        lb_vontade = new javax.swing.JLabel();
        lb_esperanca = new javax.swing.JLabel();
        lb_hobby = new javax.swing.JLabel();
        s_lb_cor6 = new javax.swing.JLabel();
        jp_contato = new javax.swing.JPanel();
        painel_2_amigo1 = new javax.swing.JPanel();
        s_lb_email1 = new javax.swing.JLabel();
        lb_rua = new javax.swing.JLabel();
        s_lb_cidade1 = new javax.swing.JLabel();
        lb_cidade = new javax.swing.JLabel();
        s_lb_celular1 = new javax.swing.JLabel();
        s_lb_bairro1 = new javax.swing.JLabel();
        s_lb_fone1 = new javax.swing.JLabel();
        s_lb_rua1 = new javax.swing.JLabel();
        lb_bairro = new javax.swing.JLabel();
        lb_fone = new javax.swing.JLabel();
        lb_celOi = new javax.swing.JLabel();
        lb_emailHotmail = new javax.swing.JLabel();
        s_lb_rua3 = new javax.swing.JLabel();
        lb_numero = new javax.swing.JLabel();
        s_lb_celular3 = new javax.swing.JLabel();
        lb_celTim = new javax.swing.JLabel();
        lb_celVivo = new javax.swing.JLabel();
        s_lb_celular4 = new javax.swing.JLabel();
        s_lb_celular5 = new javax.swing.JLabel();
        lb_celClaro = new javax.swing.JLabel();
        lb_celOutro = new javax.swing.JLabel();
        s_lb_celular6 = new javax.swing.JLabel();
        s_lb_email3 = new javax.swing.JLabel();
        lb_emailGmail = new javax.swing.JLabel();
        painel_2_amigo2 = new javax.swing.JPanel();
        s_lb_blog2 = new javax.swing.JLabel();
        lb_facebook = new javax.swing.JLabel();
        lb_twitter = new javax.swing.JLabel();
        lb_orkut = new javax.swing.JLabel();
        lb_emailOutro = new javax.swing.JLabel();
        s_lb_orkut1 = new javax.swing.JLabel();
        s_lb_twiter1 = new javax.swing.JLabel();
        lb_emailIg = new javax.swing.JLabel();
        s_lb_email4 = new javax.swing.JLabel();
        s_lb_email6 = new javax.swing.JLabel();
        s_lb_email5 = new javax.swing.JLabel();
        s_lb_email7 = new javax.swing.JLabel();
        lb_emailBol = new javax.swing.JLabel();
        lb_emailYahoo = new javax.swing.JLabel();
        s_lb_blog5 = new javax.swing.JLabel();
        lb_myspace = new javax.swing.JLabel();
        s_lb_blog6 = new javax.swing.JLabel();
        lb_skype = new javax.swing.JLabel();
        lb_linkedin = new javax.swing.JLabel();
        s_lb_blog7 = new javax.swing.JLabel();
        lb_blog = new javax.swing.JLabel();
        s_lb_blog8 = new javax.swing.JLabel();
        jp_educacional = new javax.swing.JPanel();
        s_lb_odeio3 = new javax.swing.JLabel();
        s_lb_filme3 = new javax.swing.JLabel();
        s_lb_quem_sou3 = new javax.swing.JLabel();
        s_lb_adoro3 = new javax.swing.JLabel();
        s_lb_nacionalidade3 = new javax.swing.JLabel();
        s_lb_niver3 = new javax.swing.JLabel();
        s_lb_nome3 = new javax.swing.JLabel();
        s_lb_naturalidade5 = new javax.swing.JLabel();
        lb_curso = new javax.swing.JLabel();
        lb_dataEntradaCurso = new javax.swing.JLabel();
        lb_instituicaoEnsino = new javax.swing.JLabel();
        lb_horarioAula = new javax.swing.JLabel();
        lb_naoGostoDeInst = new javax.swing.JLabel();
        lb_horarioIntervalo = new javax.swing.JLabel();
        lb_vouMelhorarEmInst = new javax.swing.JLabel();
        lb_aInstituicaoEh = new javax.swing.JLabel();
        s_lb_musica3 = new javax.swing.JLabel();
        lb_nasHorasVagas = new javax.swing.JLabel();
        s_lb_esporte4 = new javax.swing.JLabel();
        lb_disciplinaPreferida = new javax.swing.JLabel();
        s_lb_naturalidade6 = new javax.swing.JLabel();
        lb_turma = new javax.swing.JLabel();
        s_lb_esporte5 = new javax.swing.JLabel();
        lb_minhaTurmaEh = new javax.swing.JLabel();
        lb_esporte5 = new javax.swing.JLabel();
        lb_piorDisciplina = new javax.swing.JLabel();
        s_lb_quem_sou4 = new javax.swing.JLabel();
        lb_dataTerminoCurso = new javax.swing.JLabel();
        s_lb_adoro4 = new javax.swing.JLabel();
        lb_sempreGostoDeInst = new javax.swing.JLabel();
        s_lb_filme4 = new javax.swing.JLabel();
        lb_estaFaltandoInst = new javax.swing.JLabel();
        jp_profissional = new javax.swing.JPanel();
        s_lb_odeio2 = new javax.swing.JLabel();
        s_lb_filme2 = new javax.swing.JLabel();
        s_lb_quem_sou2 = new javax.swing.JLabel();
        s_lb_adoro2 = new javax.swing.JLabel();
        s_lb_fisico2 = new javax.swing.JLabel();
        s_lb_nacionalidade2 = new javax.swing.JLabel();
        s_lb_niver2 = new javax.swing.JLabel();
        s_lb_nome2 = new javax.swing.JLabel();
        s_lb_naturalidade3 = new javax.swing.JLabel();
        lb_cargo = new javax.swing.JLabel();
        lb_dataAdmissao = new javax.swing.JLabel();
        lb_nomeEmpresa = new javax.swing.JLabel();
        lb_horarioServico = new javax.swing.JLabel();
        lb_horarioRefeicao = new javax.swing.JLabel();
        lb_naoGostoDeEmp = new javax.swing.JLabel();
        lb_atividadeEmp = new javax.swing.JLabel();
        lb_vouMelhorarEmEmp = new javax.swing.JLabel();
        lb_aEmpresaEh = new javax.swing.JLabel();
        s_lb_musica2 = new javax.swing.JLabel();
        lb_soEstaFaltandoEmp = new javax.swing.JLabel();
        s_lb_esporte2 = new javax.swing.JLabel();
        lb_emailCorpEmp = new javax.swing.JLabel();
        s_lb_naturalidade4 = new javax.swing.JLabel();
        lb_setor = new javax.swing.JLabel();
        s_lb_esporte3 = new javax.swing.JLabel();
        lb_foneEmp = new javax.swing.JLabel();
        lb_esporte4 = new javax.swing.JLabel();
        lb_ramalEmp = new javax.swing.JLabel();
        lb_esporte6 = new javax.swing.JLabel();
        lb_faxEmp = new javax.swing.JLabel();
        s_lb_adoro5 = new javax.swing.JLabel();
        lb_sempreGostoDeEmp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setIconImage(new ImageIcon(Moniersn.class.getResource(Icones.ICONE_NOTIFICACAO), Avisos.FACECARD_FULL).getImage());
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jp_inicioUsuario.setBackground(new java.awt.Color(204, 204, 204));
        jp_inicioUsuario.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_inicioUsuarioComponentShown(evt);
            }
        });

        bt_configuracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/configuracoes.png"))); // NOI18N
        bt_configuracoes.setToolTipText("");
        bt_configuracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_configuracoesActionPerformed(evt);
            }
        });

        bt_atualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-informacao.png"))); // NOI18N
        bt_atualizar.setToolTipText("");
        bt_atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atualizarActionPerformed(evt);
            }
        });

        bt_compartilharArquivoGeral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-add.png"))); // NOI18N
        bt_compartilharArquivoGeral.setToolTipText("");
        bt_compartilharArquivoGeral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_compartilharArquivoGeralActionPerformed(evt);
            }
        });

        bt_desconectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/desconectado.png"))); // NOI18N
        bt_desconectar.setToolTipText("");
        bt_desconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_desconectarActionPerformed(evt);
            }
        });

        lb_qtdConvites.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_qtdConvites.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/todos-convites.png"))); // NOI18N
        lb_qtdConvites.setText("(999)");
        lb_qtdConvites.setToolTipText("Nova solicitação de amizade");
        lb_qtdConvites.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_qtdConvites.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_qtdConvitesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_qtdConvitesMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_qtdConvitesMouseReleased(evt);
            }
        });

        lb_qtdRecInst.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_qtdRecInst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/novo-rec-bate-papo.png"))); // NOI18N
        lb_qtdRecInst.setText("(999)");
        lb_qtdRecInst.setToolTipText("Novo recado no bate papo");
        lb_qtdRecInst.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_qtdRecInst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_qtdRecInstMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_qtdRecInstMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_qtdRecInstMouseReleased(evt);
            }
        });

        lb_nomeIconeHumorUsuario.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_nomeIconeHumorUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_nomeIconeHumorUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/novato.png"))); // NOI18N
        lb_nomeIconeHumorUsuario.setText("NOME DO USUÁRIO");
        lb_nomeIconeHumorUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_nomeIconeHumorUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_nomeIconeHumorUsuarioMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_nomeIconeHumorUsuarioMouseEntered(evt);
            }
        });

        lb_dataNiverUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/aniversario.png"))); // NOI18N
        lb_dataNiverUsuario.setText("DATA DE ANIVERSÁRIO DO USUÁRIO");
        lb_dataNiverUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_dataNiverUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_dataNiverUsuarioMouseExited(evt);
            }
        });

        lb_ultimaOcorrenciaUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ocorrencia.png"))); // NOI18N
        lb_ultimaOcorrenciaUsuario.setText("ÚLTIMA OCORRÊNCIA EMITIDA DO USUÁRIO");
        lb_ultimaOcorrenciaUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_ultimaOcorrenciaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_ultimaOcorrenciaUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_ultimaOcorrenciaUsuarioMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_ultimaOcorrenciaUsuarioMouseReleased(evt);
            }
        });

        lb_sexoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/masculino.png"))); // NOI18N
        lb_sexoUsuario.setText("SEXO DO USUÁRIO");
        lb_sexoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_sexoUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_sexoUsuarioMouseExited(evt);
            }
        });

        lb_dataCadastroUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/data-cadastro.png"))); // NOI18N
        lb_dataCadastroUsuario.setText("DATA DE CADASTRO DO USUÁRIO NO FACECARD");
        lb_dataCadastroUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_dataCadastroUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_dataCadastroUsuarioMouseExited(evt);
            }
        });

        tf_ocorrencia.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        tf_ocorrencia.setForeground(new java.awt.Color(255, 0, 0));
        tf_ocorrencia.setToolTipText("");
        tf_ocorrencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_ocorrenciaActionPerformed(evt);
            }
        });

        lb_iconeOcorrencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ocorrencia.png"))); // NOI18N
        lb_iconeOcorrencia.setText("Estado atual");

        bt_addOcorrencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ocorrencia-add.png"))); // NOI18N
        bt_addOcorrencia.setToolTipText("");
        bt_addOcorrencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addOcorrenciaActionPerformed(evt);
            }
        });

        lb_escrevaUmaOcorrencia.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        lb_escrevaUmaOcorrencia.setText("Escreva aqui uma ocorrência ¬");
        lb_escrevaUmaOcorrencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_escrevaUmaOcorrenciaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_escrevaUmaOcorrenciaMouseExited(evt);
            }
        });

        lb_fraseUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/frase.png"))); // NOI18N
        lb_fraseUsuario.setText("FRASE DO USUÁRIO");
        lb_fraseUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_fraseUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_fraseUsuarioMouseExited(evt);
            }
        });

        jt_listaAmigosUsuario.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jt_listaAmigosUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Amigos", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaAmigosUsuario.setToolTipText("");
        jt_listaAmigosUsuario.setFocusTraversalPolicyProvider(true);
        jt_listaAmigosUsuario.setRowHeight(34);
        jt_listaAmigosUsuario.setRowMargin(2);
        jt_listaAmigosUsuario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_listaAmigosUsuario.setShowVerticalLines(false);
        jt_listaAmigosUsuario.setSurrendersFocusOnKeystroke(true);
        jt_listaAmigosUsuario.getTableHeader().setReorderingAllowed(false);
        //jTable1.getColumn(jt_listaAmigosUsuario.getRowHeight(0)).setCellEditor(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        jt_listaAmigosUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jt_listaAmigosUsuarioMouseReleased(evt);
            }
        });
        jt_listaAmigosUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_listaAmigosUsuarioKeyTyped(evt);
            }
        });
        sp_listaAmigosUsuario.setViewportView(jt_listaAmigosUsuario);

        cb_anonimo.setBackground(new java.awt.Color(204, 204, 204));
        cb_anonimo.setText("Anônimo");
        cb_anonimo.setToolTipText("Selecione e ninguem saberá que foi você");
        cb_anonimo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lb_qtdComentarioUltOcoUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_qtdComentarioUltOcoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/comentario-ocorrencia.png"))); // NOI18N
        lb_qtdComentarioUltOcoUsuario.setText("(999)");
        lb_qtdComentarioUltOcoUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_qtdComentarioUltOcoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_qtdComentarioUltOcoUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_qtdComentarioUltOcoUsuarioMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_qtdComentarioUltOcoUsuarioMouseReleased(evt);
            }
        });

        bt_atualizarListaUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atualizarListaUsuarios.setToolTipText("");
        bt_atualizarListaUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atualizarListaUsuariosActionPerformed(evt);
            }
        });

        lb_mostrarNaoMostrarNome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_mostrarNaoMostrarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/disfarce.png"))); // NOI18N
        lb_mostrarNaoMostrarNome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_mostrarNaoMostrarNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_mostrarNaoMostrarNomeMouseClicked(evt);
            }
        });

        lb_seguranca.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_seguranca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/liberado-systray.png"))); // NOI18N
        lb_seguranca.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_seguranca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_segurancaMouseReleased(evt);
            }
        });

        lb_habilitarAudio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_habilitarAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/com-audio.png"))); // NOI18N
        lb_habilitarAudio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_habilitarAudio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_habilitarAudioMouseClicked(evt);
            }
        });

        lb_prendeFormNaFrente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/fixo-frente.png"))); // NOI18N
        lb_prendeFormNaFrente.setToolTipText("O Facecard está fixado na frente");
        lb_prendeFormNaFrente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrenteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrenteMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrenteMouseReleased(evt);
            }
        });

        tf_pesquisaUsuario.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_pesquisaUsuario.setForeground(new java.awt.Color(0, 0, 255));
        tf_pesquisaUsuario.setToolTipText("");
        tf_pesquisaUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_pesquisaUsuarioKeyReleased(evt);
            }
        });

        cb_tipoAmigos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos(geral)", "Todos(conectados)", "Todos(convidados)", "Todos(convites)", "Amigos(geral)", "Amigos(conectados)", "Amigos(bloqueados)" }));
        cb_tipoAmigos.setToolTipText("");
        cb_tipoAmigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tipoAmigosActionPerformed(evt);
            }
        });
        // cb_tipoAmigos.setRenderer(new RenderePintorCombobox());

        bt_verMaisUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisUsuarios.setToolTipText("");
        bt_verMaisUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisUsuariosActionPerformed(evt);
            }
        });

        lb_qtdUsuarioListaUsuario.setText("(999)");

        lb_qtdTotalUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/amigo-ok.png"))); // NOI18N
        lb_qtdTotalUsuario.setText("(999) pessoa(s) no geral...");

        lb_ninguemBatePapoComigoInicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_ninguemBatePapoComigoInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ninguem-bate-papo-comigo.png"))); // NOI18N
        lb_ninguemBatePapoComigoInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_ninguemBatePapoComigoInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_ninguemBatePapoComigoInicioMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jp_inicioUsuarioLayout = new javax.swing.GroupLayout(jp_inicioUsuario);
        jp_inicioUsuario.setLayout(jp_inicioUsuarioLayout);
        jp_inicioUsuarioLayout.setHorizontalGroup(
            jp_inicioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_inicioUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_inicioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inicioUsuarioLayout.createSequentialGroup()
                        .addComponent(bt_configuracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_compartilharArquivoGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_desconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_ninguemBatePapoComigoInicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_qtdConvites)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_qtdRecInst, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_prendeFormNaFrente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_habilitarAudio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_seguranca, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_mostrarNaoMostrarNome)
                        .addGap(4, 4, 4))
                    .addComponent(lb_nomeIconeHumorUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_fraseUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_dataCadastroUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_dataNiverUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_sexoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inicioUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_ultimaOcorrenciaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_qtdComentarioUltOcoUsuario)
                        .addGap(4, 4, 4))
                    .addGroup(jp_inicioUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_iconeOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_ocorrencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_addOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inicioUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_escrevaUmaOcorrencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_anonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(8, 8, 8)
                .addGroup(jp_inicioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inicioUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_qtdUsuarioListaUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_pesquisaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atualizarListaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inicioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cb_tipoAmigos, javax.swing.GroupLayout.Alignment.TRAILING, 0, 193, Short.MAX_VALUE)
                        .addComponent(lb_qtdTotalUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(sp_listaAmigosUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jp_inicioUsuarioLayout.setVerticalGroup(
            jp_inicioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_inicioUsuarioLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jp_inicioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lb_seguranca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_habilitarAudio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_qtdRecInst, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_qtdConvites, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_ninguemBatePapoComigoInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_tipoAmigos, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_configuracoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_atualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_compartilharArquivoGeral, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_desconectar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_mostrarNaoMostrarNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_inicioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_inicioUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_qtdTotalUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sp_listaAmigosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jp_inicioUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_nomeIconeHumorUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_inicioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_ultimaOcorrenciaUsuario)
                            .addComponent(lb_qtdComentarioUltOcoUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_fraseUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_sexoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_dataNiverUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_dataCadastroUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(jp_inicioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_escrevaUmaOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_anonimo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jp_inicioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_addOcorrencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tf_ocorrencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lb_iconeOcorrencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inicioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_pesquisaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lb_qtdUsuarioListaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(bt_atualizarListaUsuarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bt_verMaisUsuarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtp_painelTabuladoUsuario.addTab("INÍCIO", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/inicio.png")), jp_inicioUsuario, "Guia inicial"); // NOI18N

        jp_ocorrenciaUsuario.setBackground(new java.awt.Color(204, 204, 204));
        jp_ocorrenciaUsuario.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_ocorrenciaUsuarioComponentShown(evt);
            }
        });

        jt_listaOcorrenciaUsuario.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        jt_listaOcorrenciaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "De", "Ocorrência(s)", "Quando", "Qtd. Cmts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaOcorrenciaUsuario.setToolTipText("Lista de ocorrências");
        jt_listaOcorrenciaUsuario.setFocusTraversalPolicyProvider(true);
        jt_listaOcorrenciaUsuario.setMaximumSize(new java.awt.Dimension(2147483647, 33));
        jt_listaOcorrenciaUsuario.setRowHeight(35);
        jt_listaOcorrenciaUsuario.setRowMargin(2);
        jt_listaOcorrenciaUsuario.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jt_listaOcorrenciaUsuario.setShowVerticalLines(false);
        jt_listaOcorrenciaUsuario.setSurrendersFocusOnKeystroke(true);
        jt_listaOcorrenciaUsuario.getTableHeader().setReorderingAllowed(false);
        jt_listaOcorrenciaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_listaOcorrenciaUsuarioMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jt_listaOcorrenciaUsuarioMouseReleased(evt);
            }
        });
        jt_listaOcorrenciaUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_listaOcorrenciaUsuarioKeyReleased(evt);
            }
        });
        sp_ocorrenciaUsuario.setViewportView(jt_listaOcorrenciaUsuario);

        bt_comentarOcorrencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/comentario-ocorrencia.png"))); // NOI18N
        bt_comentarOcorrencia.setToolTipText("");
        bt_comentarOcorrencia.setEnabled(false);
        bt_comentarOcorrencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_comentarOcorrenciaActionPerformed(evt);
            }
        });

        bt_excluirOcorrencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ocorrencia-remocao.png"))); // NOI18N
        bt_excluirOcorrencia.setToolTipText("");
        bt_excluirOcorrencia.setEnabled(false);
        bt_excluirOcorrencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirOcorrenciaActionPerformed(evt);
            }
        });

        rb_ocoRecebidas.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuOcorrencia.add(rb_ocoRecebidas);
        rb_ocoRecebidas.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        rb_ocoRecebidas.setSelected(true);
        rb_ocoRecebidas.setText("Recebida(s) (999)");
        rb_ocoRecebidas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_ocoRecebidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_ocoRecebidasActionPerformed(evt);
            }
        });

        rb_ocoEnviadas.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuOcorrencia.add(rb_ocoEnviadas);
        rb_ocoEnviadas.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        rb_ocoEnviadas.setText("Enviada(s) (999)");
        rb_ocoEnviadas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_ocoEnviadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_ocoEnviadasActionPerformed(evt);
            }
        });

        lb_prendeFormNaFrente1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ocorrencia-recebida.png"))); // NOI18N
        lb_prendeFormNaFrente1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lb_prendeFormNaFrente2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ocorrencia-add.png"))); // NOI18N
        lb_prendeFormNaFrente2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tf_conteudoOcoPesquisado.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_conteudoOcoPesquisado.setToolTipText("");
        tf_conteudoOcoPesquisado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_conteudoOcoPesquisadoKeyReleased(evt);
            }
        });

        lb_pesquisarOco.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_pesquisarOco.setText("Pesquisar ocorrência:");

        lb_qtdOcoEncontrada.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_qtdOcoEncontrada.setText("(999) Encontradas");

        bt_atuListaOcoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atuListaOcoUsuario.setToolTipText("");
        bt_atuListaOcoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atuListaOcoUsuarioActionPerformed(evt);
            }
        });

        bt_verMaisOcoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisOcoUsuario.setToolTipText("");
        bt_verMaisOcoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisOcoUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_ocorrenciaUsuarioLayout = new javax.swing.GroupLayout(jp_ocorrenciaUsuario);
        jp_ocorrenciaUsuario.setLayout(jp_ocorrenciaUsuarioLayout);
        jp_ocorrenciaUsuarioLayout.setHorizontalGroup(
            jp_ocorrenciaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_ocorrenciaUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_ocorrenciaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sp_ocorrenciaUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addGroup(jp_ocorrenciaUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_prendeFormNaFrente1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_ocoRecebidas, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_prendeFormNaFrente2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_ocoEnviadas, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                        .addComponent(bt_excluirOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_comentarOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_ocorrenciaUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_qtdOcoEncontrada, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_pesquisarOco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_conteudoOcoPesquisado, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisOcoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atuListaOcoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jp_ocorrenciaUsuarioLayout.setVerticalGroup(
            jp_ocorrenciaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_ocorrenciaUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_ocorrenciaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bt_excluirOcorrencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_ocoRecebidas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_ocoEnviadas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bt_comentarOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_ocorrenciaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_ocorrenciaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lb_pesquisarOco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_atuListaOcoUsuario, 0, 0, Short.MAX_VALUE)
                    .addComponent(bt_verMaisOcoUsuario, 0, 0, Short.MAX_VALUE)
                    .addComponent(tf_conteudoOcoPesquisado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_qtdOcoEncontrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtp_painelTabuladoUsuario.addTab("(0)", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ocorrencia-novo.png")), jp_ocorrenciaUsuario); // NOI18N

        jp_atualizacoesUsuario.setBackground(new java.awt.Color(204, 204, 204));
        jp_atualizacoesUsuario.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_atualizacoesUsuarioComponentShown(evt);
            }
        });

        jt_listaAtualizacoesUsuario.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        jt_listaAtualizacoesUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Quem", "Atualização(ões)", "Quando", "Qtd. Cmts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaAtualizacoesUsuario.setToolTipText("Lista de atualizações de usuários");
        jt_listaAtualizacoesUsuario.setMaximumSize(new java.awt.Dimension(2147483647, 33));
        jt_listaAtualizacoesUsuario.setRowHeight(35);
        jt_listaAtualizacoesUsuario.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jt_listaAtualizacoesUsuario.setShowHorizontalLines(false);
        jt_listaAtualizacoesUsuario.setShowVerticalLines(false);
        jt_listaAtualizacoesUsuario.getTableHeader().setReorderingAllowed(false);
        jt_listaAtualizacoesUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_listaAtualizacoesUsuarioMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jt_listaAtualizacoesUsuarioMouseReleased(evt);
            }
        });
        jt_listaAtualizacoesUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_listaAtualizacoesUsuarioKeyReleased(evt);
            }
        });
        sp_atualizacoesUsuario.setViewportView(jt_listaAtualizacoesUsuario);

        rb_atuParaTodos.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuAtualizacoes.add(rb_atuParaTodos);
        rb_atuParaTodos.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        rb_atuParaTodos.setText("Atu. de estranhos (999)");
        rb_atuParaTodos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_atuParaTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_atuParaTodosActionPerformed(evt);
            }
        });

        rb_atuDeAmigos.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuAtualizacoes.add(rb_atuDeAmigos);
        rb_atuDeAmigos.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        rb_atuDeAmigos.setSelected(true);
        rb_atuDeAmigos.setText("Atu. de amigos (999)");
        rb_atuDeAmigos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_atuDeAmigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_atuDeAmigosActionPerformed(evt);
            }
        });

        bt_excluirAtualizacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/remocao-atualizar.png"))); // NOI18N
        bt_excluirAtualizacoes.setToolTipText("Excluir minhas atualizações selecionadas...");
        bt_excluirAtualizacoes.setEnabled(false);
        bt_excluirAtualizacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirAtualizacoesActionPerformed(evt);
            }
        });

        bt_comentarAtuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/comentario-atualizar.png"))); // NOI18N
        bt_comentarAtuUsuario.setToolTipText("Comentar atualização selecionada...");
        bt_comentarAtuUsuario.setEnabled(false);
        bt_comentarAtuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_comentarAtuUsuarioActionPerformed(evt);
            }
        });

        rb_atualizacoesMinhas.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuAtualizacoes.add(rb_atualizacoesMinhas);
        rb_atualizacoesMinhas.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        rb_atualizacoesMinhas.setText("Minhas atu. (999)");
        rb_atualizacoesMinhas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_atualizacoesMinhas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_atualizacoesMinhasActionPerformed(evt);
            }
        });

        lb_prendeFormNaFrente3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/nao-amigo-atualizar.png"))); // NOI18N
        lb_prendeFormNaFrente3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente3MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente3MouseReleased(evt);
            }
        });

        lb_prendeFormNaFrente4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/amigo-atualizar.png"))); // NOI18N
        lb_prendeFormNaFrente4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente4MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente4MouseReleased(evt);
            }
        });

        lb_prendeFormNaFrente5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/add_atualizacao-eu.png"))); // NOI18N
        lb_prendeFormNaFrente5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente5MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente5MouseReleased(evt);
            }
        });

        bt_atuListaAtuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atuListaAtuUsuario.setToolTipText("");
        bt_atuListaAtuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atuListaAtuUsuarioActionPerformed(evt);
            }
        });

        lb_qtdAtuEncontrada.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_qtdAtuEncontrada.setText("(999) Encontradas");

        lb_pesquisarAtu.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_pesquisarAtu.setText("Pesquisar atu.:");

        tf_conteudoAtuPesquisado.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_conteudoAtuPesquisado.setToolTipText("");
        tf_conteudoAtuPesquisado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_conteudoAtuPesquisadoKeyReleased(evt);
            }
        });

        bt_verMaisAtuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisAtuUsuario.setToolTipText("");
        bt_verMaisAtuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisAtuUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_atualizacoesUsuarioLayout = new javax.swing.GroupLayout(jp_atualizacoesUsuario);
        jp_atualizacoesUsuario.setLayout(jp_atualizacoesUsuarioLayout);
        jp_atualizacoesUsuarioLayout.setHorizontalGroup(
            jp_atualizacoesUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_atualizacoesUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_atualizacoesUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sp_atualizacoesUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_atualizacoesUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_prendeFormNaFrente3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_atuParaTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_prendeFormNaFrente4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_atuDeAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_prendeFormNaFrente5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_atualizacoesMinhas, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_excluirAtualizacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_comentarAtuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_atualizacoesUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_qtdAtuEncontrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_pesquisarAtu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_conteudoAtuPesquisado, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisAtuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atuListaAtuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jp_atualizacoesUsuarioLayout.setVerticalGroup(
            jp_atualizacoesUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_atualizacoesUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_atualizacoesUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_comentarAtuUsuario, 0, 20, Short.MAX_VALUE)
                    .addComponent(bt_excluirAtualizacoes, 0, 20, Short.MAX_VALUE)
                    .addComponent(rb_atualizacoesMinhas, 0, 20, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente5, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(rb_atuDeAmigos, 0, 20, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente4, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(rb_atuParaTodos, 0, 20, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente3, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_atualizacoesUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_atualizacoesUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lb_qtdAtuEncontrada, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(lb_pesquisarAtu, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(tf_conteudoAtuPesquisado, 0, 20, Short.MAX_VALUE)
                    .addComponent(bt_verMaisAtuUsuario, 0, 20, Short.MAX_VALUE)
                    .addComponent(bt_atuListaAtuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtp_painelTabuladoUsuario.addTab("(0)", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/novo-atualizar.png")), jp_atualizacoesUsuario); // NOI18N

        jp_recPublicosUsuario.setBackground(new java.awt.Color(204, 204, 204));
        jp_recPublicosUsuario.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_recPublicosUsuarioComponentShown(evt);
            }
        });

        jt_listaRecadosPubUsuario.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        jt_listaRecadosPubUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "* / ˘", "Quem", "Recado(s)", "Quando", "Qtd. Cmts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaRecadosPubUsuario.setToolTipText("Lista de recados públicos");
        jt_listaRecadosPubUsuario.setFocusTraversalPolicyProvider(true);
        jt_listaRecadosPubUsuario.setMaximumSize(new java.awt.Dimension(2147483647, 33));
        jt_listaRecadosPubUsuario.setRowHeight(35);
        jt_listaRecadosPubUsuario.setRowMargin(2);
        jt_listaRecadosPubUsuario.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jt_listaRecadosPubUsuario.setShowVerticalLines(false);
        jt_listaRecadosPubUsuario.setSurrendersFocusOnKeystroke(true);
        jt_listaRecadosPubUsuario.getTableHeader().setReorderingAllowed(false);
        jt_listaRecadosPubUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_listaRecadosPubUsuarioMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jt_listaRecadosPubUsuarioMouseReleased(evt);
            }
        });
        jt_listaRecadosPubUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_listaRecadosPubUsuarioKeyReleased(evt);
            }
        });
        sp_recadosPubUsuario.setViewportView(jt_listaRecadosPubUsuario);

        bt_excluirRecPub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-publico-remocao.png"))); // NOI18N
        bt_excluirRecPub.setToolTipText("");
        bt_excluirRecPub.setEnabled(false);
        bt_excluirRecPub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirRecPubActionPerformed(evt);
            }
        });

        bt_comentarRecPublico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/comentario-recado-publico.png"))); // NOI18N
        bt_comentarRecPublico.setToolTipText("");
        bt_comentarRecPublico.setEnabled(false);
        bt_comentarRecPublico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_comentarRecPublicoActionPerformed(evt);
            }
        });

        bt_marcarComoLidoRecPub.setText("*/˘");
        bt_marcarComoLidoRecPub.setToolTipText("");
        bt_marcarComoLidoRecPub.setEnabled(false);
        bt_marcarComoLidoRecPub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_marcarComoLidoRecPubActionPerformed(evt);
            }
        });

        rb_recadosPubEnviados.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuRecadosPub.add(rb_recadosPubEnviados);
        rb_recadosPubEnviados.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        rb_recadosPubEnviados.setText("Enviado(s) (999)");
        rb_recadosPubEnviados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_recadosPubEnviados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_recadosPubEnviadosActionPerformed(evt);
            }
        });

        rb_recadosPubNaoLidos.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuRecadosPub.add(rb_recadosPubNaoLidos);
        rb_recadosPubNaoLidos.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        rb_recadosPubNaoLidos.setText("Não lido(s) (999)");
        rb_recadosPubNaoLidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_recadosPubNaoLidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_recadosPubNaoLidosActionPerformed(evt);
            }
        });

        rb_todosRecadosPubRecebidos.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuRecadosPub.add(rb_todosRecadosPubRecebidos);
        rb_todosRecadosPubRecebidos.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        rb_todosRecadosPubRecebidos.setSelected(true);
        rb_todosRecadosPubRecebidos.setText("Recebido(s) (999)");
        rb_todosRecadosPubRecebidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_todosRecadosPubRecebidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_todosRecadosPubRecebidosActionPerformed(evt);
            }
        });

        lb_prendeFormNaFrente6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-publico-recebido.png"))); // NOI18N
        lb_prendeFormNaFrente6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente6MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente6MouseReleased(evt);
            }
        });

        lb_prendeFormNaFrente7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-publico-novo.png"))); // NOI18N
        lb_prendeFormNaFrente7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente7MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente7MouseReleased(evt);
            }
        });

        lb_prendeFormNaFrente8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-publico-add.png"))); // NOI18N
        lb_prendeFormNaFrente8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente8MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente8MouseReleased(evt);
            }
        });

        tf_conteudoRecPubPesquisado.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_conteudoRecPubPesquisado.setToolTipText("");
        tf_conteudoRecPubPesquisado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_conteudoRecPubPesquisadoKeyReleased(evt);
            }
        });

        lb_qtdRecPubEncontrado.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_qtdRecPubEncontrado.setText("(999) Encontradas");

        lb_pesquisarRecPub.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_pesquisarRecPub.setText("Pesquisar rec. público:");

        bt_atuListaRecPubUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atuListaRecPubUsuario.setToolTipText("");
        bt_atuListaRecPubUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atuListaRecPubUsuarioActionPerformed(evt);
            }
        });

        bt_verMaisRecPubUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisRecPubUsuario.setToolTipText("");
        bt_verMaisRecPubUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisRecPubUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_recPublicosUsuarioLayout = new javax.swing.GroupLayout(jp_recPublicosUsuario);
        jp_recPublicosUsuario.setLayout(jp_recPublicosUsuarioLayout);
        jp_recPublicosUsuarioLayout.setHorizontalGroup(
            jp_recPublicosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_recPublicosUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_recPublicosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sp_recadosPubUsuario)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_recPublicosUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_prendeFormNaFrente6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_todosRecadosPubRecebidos, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_prendeFormNaFrente7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_recadosPubNaoLidos, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_prendeFormNaFrente8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_recadosPubEnviados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_marcarComoLidoRecPub, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_excluirRecPub, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_comentarRecPublico, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_recPublicosUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_qtdRecPubEncontrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_pesquisarRecPub)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_conteudoRecPubPesquisado, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisRecPubUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atuListaRecPubUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jp_recPublicosUsuarioLayout.setVerticalGroup(
            jp_recPublicosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_recPublicosUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_recPublicosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lb_prendeFormNaFrente6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_todosRecadosPubRecebidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_prendeFormNaFrente7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_excluirRecPub, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jp_recPublicosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bt_marcarComoLidoRecPub, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jp_recPublicosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lb_prendeFormNaFrente8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rb_recadosPubNaoLidos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rb_recadosPubEnviados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(bt_comentarRecPublico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_recadosPubUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_recPublicosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lb_qtdRecPubEncontrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_pesquisarRecPub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_atuListaRecPubUsuario, 0, 0, Short.MAX_VALUE)
                    .addComponent(bt_verMaisRecPubUsuario, 0, 0, Short.MAX_VALUE)
                    .addComponent(tf_conteudoRecPubPesquisado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtp_painelTabuladoUsuario.addTab("(0)", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-publico-novo.png")), jp_recPublicosUsuario); // NOI18N

        jp_recPrivados.setBackground(new java.awt.Color(204, 204, 204));
        jp_recPrivados.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_recPrivadosComponentShown(evt);
            }
        });

        jt_listaRecadosPriv.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        jt_listaRecadosPriv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "* / ˘", "Quem", "Recado(s)", "Quando", "Qtd. Cmts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaRecadosPriv.setToolTipText("Lista de recados privados");
        jt_listaRecadosPriv.setFocusTraversalPolicyProvider(true);
        jt_listaRecadosPriv.setMaximumSize(new java.awt.Dimension(2147483647, 33));
        jt_listaRecadosPriv.setRowHeight(35);
        jt_listaRecadosPriv.setRowMargin(2);
        jt_listaRecadosPriv.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jt_listaRecadosPriv.setShowVerticalLines(false);
        jt_listaRecadosPriv.setSurrendersFocusOnKeystroke(true);
        jt_listaRecadosPriv.getTableHeader().setReorderingAllowed(false);
        jt_listaRecadosPriv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_listaRecadosPrivMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jt_listaRecadosPrivMouseReleased(evt);
            }
        });
        jt_listaRecadosPriv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_listaRecadosPrivKeyReleased(evt);
            }
        });
        sp_recadosPriv.setViewportView(jt_listaRecadosPriv);

        bt_marcarComoLidoRecPriv.setText("*/˘");
        bt_marcarComoLidoRecPriv.setToolTipText("");
        bt_marcarComoLidoRecPriv.setEnabled(false);
        bt_marcarComoLidoRecPriv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_marcarComoLidoRecPrivActionPerformed(evt);
            }
        });

        bt_excluirRecPriv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-privado-remocao.png"))); // NOI18N
        bt_excluirRecPriv.setToolTipText("");
        bt_excluirRecPriv.setEnabled(false);
        bt_excluirRecPriv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirRecPrivActionPerformed(evt);
            }
        });

        bt_comentarRecPriv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/comentario-recado-privado.png"))); // NOI18N
        bt_comentarRecPriv.setToolTipText("");
        bt_comentarRecPriv.setEnabled(false);
        bt_comentarRecPriv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_comentarRecPrivActionPerformed(evt);
            }
        });

        rb_todosRecadosPrivRecebidos.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuRecadosPriv.add(rb_todosRecadosPrivRecebidos);
        rb_todosRecadosPrivRecebidos.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        rb_todosRecadosPrivRecebidos.setSelected(true);
        rb_todosRecadosPrivRecebidos.setText("Recebido(s) (999)");
        rb_todosRecadosPrivRecebidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_todosRecadosPrivRecebidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_todosRecadosPrivRecebidosActionPerformed(evt);
            }
        });

        rb_recadosPrivNaoLidos.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuRecadosPriv.add(rb_recadosPrivNaoLidos);
        rb_recadosPrivNaoLidos.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        rb_recadosPrivNaoLidos.setText("Não lido(s) (999)");
        rb_recadosPrivNaoLidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_recadosPrivNaoLidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_recadosPrivNaoLidosActionPerformed(evt);
            }
        });

        rb_recadosPrivEnviados.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuRecadosPriv.add(rb_recadosPrivEnviados);
        rb_recadosPrivEnviados.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        rb_recadosPrivEnviados.setText("Enviado(s) (999)");
        rb_recadosPrivEnviados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_recadosPrivEnviados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_recadosPrivEnviadosActionPerformed(evt);
            }
        });

        lb_prendeFormNaFrente9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-privado-recebido.png"))); // NOI18N
        lb_prendeFormNaFrente9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente9MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente9MouseReleased(evt);
            }
        });

        lb_prendeFormNaFrente10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-privado-novo.png"))); // NOI18N
        lb_prendeFormNaFrente10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente10MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente10MouseReleased(evt);
            }
        });

        lb_prendeFormNaFrente11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-privado-add.png"))); // NOI18N
        lb_prendeFormNaFrente11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente11MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente11MouseReleased(evt);
            }
        });

        bt_atuListaRecPrivUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atuListaRecPrivUsuario.setToolTipText("");
        bt_atuListaRecPrivUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atuListaRecPrivUsuarioActionPerformed(evt);
            }
        });

        lb_qtdRecPrivEncontrado.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_qtdRecPrivEncontrado.setText("(999) Encontradas");

        lb_pesquisarRecPriv.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_pesquisarRecPriv.setText("Pesquisar rec. privado:");

        tf_conteudoRecPrivPesquisado.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_conteudoRecPrivPesquisado.setToolTipText("");
        tf_conteudoRecPrivPesquisado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_conteudoRecPrivPesquisadoKeyReleased(evt);
            }
        });

        bt_verMaisRecPrivUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisRecPrivUsuario.setToolTipText("");
        bt_verMaisRecPrivUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisRecPrivUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_recPrivadosLayout = new javax.swing.GroupLayout(jp_recPrivados);
        jp_recPrivados.setLayout(jp_recPrivadosLayout);
        jp_recPrivadosLayout.setHorizontalGroup(
            jp_recPrivadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_recPrivadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_recPrivadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sp_recadosPriv, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jp_recPrivadosLayout.createSequentialGroup()
                        .addComponent(lb_prendeFormNaFrente9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_todosRecadosPrivRecebidos, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_prendeFormNaFrente10)
                        .addGap(1, 1, 1)
                        .addComponent(rb_recadosPrivNaoLidos, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_prendeFormNaFrente11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_recadosPrivEnviados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_marcarComoLidoRecPriv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_excluirRecPriv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_comentarRecPriv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_recPrivadosLayout.createSequentialGroup()
                        .addComponent(lb_qtdRecPrivEncontrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_pesquisarRecPriv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_conteudoRecPrivPesquisado, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisRecPrivUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atuListaRecPrivUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jp_recPrivadosLayout.setVerticalGroup(
            jp_recPrivadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_recPrivadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_recPrivadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bt_excluirRecPriv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bt_marcarComoLidoRecPriv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(rb_recadosPrivEnviados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_recadosPrivNaoLidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_todosRecadosPrivRecebidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_comentarRecPriv, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_recadosPriv, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_recPrivadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_atuListaRecPrivUsuario, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                    .addComponent(bt_verMaisRecPrivUsuario, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                    .addComponent(tf_conteudoRecPrivPesquisado, javax.swing.GroupLayout.Alignment.TRAILING, 0, 20, Short.MAX_VALUE)
                    .addComponent(lb_pesquisarRecPriv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_qtdRecPrivEncontrado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtp_painelTabuladoUsuario.addTab("(0)", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-privado-novo.png")), jp_recPrivados); // NOI18N

        jp_arquivosUsuario.setBackground(new java.awt.Color(204, 204, 204));
        jp_arquivosUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jp_arquivosUsuario.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_arquivosUsuarioComponentShown(evt);
            }
        });

        jt_listaArquivosUsuario.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        jt_listaArquivosUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Quem", "Tipo", "Nome", "Tam.(bytes)", "Quando", "Qtd. Cmts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaArquivosUsuario.setToolTipText("Lista de recados privados");
        jt_listaArquivosUsuario.setFocusTraversalPolicyProvider(true);
        jt_listaArquivosUsuario.setMaximumSize(new java.awt.Dimension(2147483647, 33));
        jt_listaArquivosUsuario.setRowHeight(35);
        jt_listaArquivosUsuario.setRowMargin(2);
        jt_listaArquivosUsuario.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jt_listaArquivosUsuario.setShowVerticalLines(false);
        jt_listaArquivosUsuario.setSurrendersFocusOnKeystroke(true);
        jt_listaArquivosUsuario.getTableHeader().setReorderingAllowed(false);
        jt_listaArquivosUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jt_listaArquivosUsuarioMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_listaArquivosUsuarioMouseClicked(evt);
            }
        });
        jt_listaArquivosUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_listaArquivosUsuarioKeyReleased(evt);
            }
        });
        sp_arquivosUsuario.setViewportView(jt_listaArquivosUsuario);

        bt_removerArquivoSelecionado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-remocao.png"))); // NOI18N
        bt_removerArquivoSelecionado.setToolTipText("");
        bt_removerArquivoSelecionado.setEnabled(false);
        bt_removerArquivoSelecionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_removerArquivoSelecionadoActionPerformed(evt);
            }
        });

        bt_comentarArquivoSelecionadoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/comentario-arquivo.png"))); // NOI18N
        bt_comentarArquivoSelecionadoUsuario.setToolTipText("");
        bt_comentarArquivoSelecionadoUsuario.setEnabled(false);
        bt_comentarArquivoSelecionadoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_comentarArquivoSelecionadoUsuarioActionPerformed(evt);
            }
        });

        rb_todosArquivosDisponiveis.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuArquivos.add(rb_todosArquivosDisponiveis);
        rb_todosArquivosDisponiveis.setFont(new java.awt.Font("Trebuchet MS", 1, 10)); // NOI18N
        rb_todosArquivosDisponiveis.setSelected(true);
        rb_todosArquivosDisponiveis.setText("Disponíveis (999)");
        rb_todosArquivosDisponiveis.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_todosArquivosDisponiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_todosArquivosDisponiveisActionPerformed(evt);
            }
        });

        bt_baixarArquivoSelecionado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-baixar.png"))); // NOI18N
        bt_baixarArquivoSelecionado.setToolTipText("");
        bt_baixarArquivoSelecionado.setEnabled(false);
        bt_baixarArquivoSelecionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_baixarArquivoSelecionadoActionPerformed(evt);
            }
        });

        bt_encaminharArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-add.png"))); // NOI18N
        bt_encaminharArquivo.setToolTipText("");
        bt_encaminharArquivo.setEnabled(false);
        bt_encaminharArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_encaminharArquivoActionPerformed(evt);
            }
        });

        lb_prendeFormNaFrente14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-novo.png"))); // NOI18N
        lb_prendeFormNaFrente14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente14MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente14MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente14MouseEntered(evt);
            }
        });

        rb_todosArquivosBaixados.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuArquivos.add(rb_todosArquivosBaixados);
        rb_todosArquivosBaixados.setFont(new java.awt.Font("Trebuchet MS", 1, 10)); // NOI18N
        rb_todosArquivosBaixados.setText("Baixados (999)");
        rb_todosArquivosBaixados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_todosArquivosBaixados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_todosArquivosBaixadosActionPerformed(evt);
            }
        });

        lb_prendeFormNaFrente15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-baixar.png"))); // NOI18N
        lb_prendeFormNaFrente15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente15MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente15MouseReleased(evt);
            }
        });

        rb_todosArquivosEnviados.setBackground(new java.awt.Color(204, 204, 204));
        bg_visuArquivos.add(rb_todosArquivosEnviados);
        rb_todosArquivosEnviados.setFont(new java.awt.Font("Trebuchet MS", 1, 10)); // NOI18N
        rb_todosArquivosEnviados.setText("Enviados (999)");
        rb_todosArquivosEnviados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_todosArquivosEnviados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_todosArquivosEnviadosActionPerformed(evt);
            }
        });

        lb_prendeFormNaFrente16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_prendeFormNaFrente16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-add.png"))); // NOI18N
        lb_prendeFormNaFrente16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_prendeFormNaFrente16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente16MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_prendeFormNaFrente16MouseReleased(evt);
            }
        });

        bt_abrirArquivoSelecionado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-abrir.png"))); // NOI18N
        bt_abrirArquivoSelecionado.setToolTipText("");
        bt_abrirArquivoSelecionado.setEnabled(false);
        bt_abrirArquivoSelecionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_abrirArquivoSelecionadoActionPerformed(evt);
            }
        });

        bt_atuListaArquivosUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atuListaArquivosUsuario.setToolTipText("");
        bt_atuListaArquivosUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atuListaArquivosUsuarioActionPerformed(evt);
            }
        });

        lb_qtdArqEncontrado.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_qtdArqEncontrado.setText("(999) Encontradas");

        lb_pesquisarArq.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_pesquisarArq.setText("Pesquisar arquivo:");

        tf_nomeArqPesquisado.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_nomeArqPesquisado.setToolTipText("");
        tf_nomeArqPesquisado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_nomeArqPesquisadoKeyReleased(evt);
            }
        });

        bt_verMaisArquivosUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisArquivosUsuario.setToolTipText("");
        bt_verMaisArquivosUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisArquivosUsuarioActionPerformed(evt);
            }
        });

        bt_abrirLocalArquivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/pasta-procurar.png"))); // NOI18N
        bt_abrirLocalArquivos.setToolTipText("");
        bt_abrirLocalArquivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_abrirLocalArquivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_arquivosUsuarioLayout = new javax.swing.GroupLayout(jp_arquivosUsuario);
        jp_arquivosUsuario.setLayout(jp_arquivosUsuarioLayout);
        jp_arquivosUsuarioLayout.setHorizontalGroup(
            jp_arquivosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_arquivosUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_arquivosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_arquivosUsuarioLayout.createSequentialGroup()
                        .addComponent(sp_arquivosUsuario)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_arquivosUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_prendeFormNaFrente14)
                        .addGap(4, 4, 4)
                        .addComponent(rb_todosArquivosDisponiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_prendeFormNaFrente15, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(rb_todosArquivosBaixados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_prendeFormNaFrente16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(rb_todosArquivosEnviados, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(bt_abrirLocalArquivos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_abrirArquivoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_baixarArquivoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_encaminharArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_removerArquivoSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_comentarArquivoSelecionadoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_arquivosUsuarioLayout.createSequentialGroup()
                        .addComponent(lb_qtdArqEncontrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_pesquisarArq)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_nomeArqPesquisado, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisArquivosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atuListaArquivosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );
        jp_arquivosUsuarioLayout.setVerticalGroup(
            jp_arquivosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_arquivosUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_arquivosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_comentarArquivoSelecionadoUsuario, 0, 20, Short.MAX_VALUE)
                    .addComponent(bt_removerArquivoSelecionado, 0, 20, Short.MAX_VALUE)
                    .addComponent(bt_encaminharArquivo, 0, 20, Short.MAX_VALUE)
                    .addComponent(bt_baixarArquivoSelecionado, 0, 20, Short.MAX_VALUE)
                    .addComponent(bt_abrirArquivoSelecionado, 0, 20, Short.MAX_VALUE)
                    .addComponent(bt_abrirLocalArquivos, 0, 20, Short.MAX_VALUE)
                    .addComponent(rb_todosArquivosEnviados, 0, 20, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente16, 0, 20, Short.MAX_VALUE)
                    .addComponent(rb_todosArquivosBaixados, 0, 20, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente15, 0, 20, Short.MAX_VALUE)
                    .addComponent(lb_prendeFormNaFrente14, 0, 20, Short.MAX_VALUE)
                    .addComponent(rb_todosArquivosDisponiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_arquivosUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_arquivosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_pesquisarArq, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_nomeArqPesquisado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_verMaisArquivosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_atuListaArquivosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_qtdArqEncontrado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtp_painelTabuladoUsuario.addTab("(0)", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-novo.png")), jp_arquivosUsuario); // NOI18N

        jp_batePapo.setBackground(new java.awt.Color(204, 204, 204));
        jp_batePapo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_batePapoComponentShown(evt);
            }
        });

        bt_enviarRecInst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/add-rec-bate-papo.png"))); // NOI18N
        bt_enviarRecInst.setToolTipText("");
        bt_enviarRecInst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_enviarRecInstActionPerformed(evt);
            }
        });

        tp_recadosInstantaneos.setEditable(false);
        tp_recadosInstantaneos.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        tp_recadosInstantaneos.setForeground(new java.awt.Color(0, 0, 204));
        tp_recadosInstantaneos.setText("## BATE PAPO Facecard® Full ##\nby Ayrton Monier \n\nEscolha conversar com ninguem ou escreva recados instantâneos para todos usuários conectados, todos amigos conectados ou selecione um amigos na lista ao lado e escreva somente para ele(a). Aproveite!");
        jScrollPane5.setViewportView(tp_recadosInstantaneos);

        tf_recadoInst.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        tf_recadoInst.setForeground(new java.awt.Color(255, 0, 0));
        tf_recadoInst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_recadoInstActionPerformed(evt);
            }
        });

        jt_listaAmigosConectados.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jt_listaAmigosConectados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Amigos conectados", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaAmigosConectados.setMaximumSize(new java.awt.Dimension(2147483647, 33));
        jt_listaAmigosConectados.setRowHeight(35);
        jt_listaAmigosConectados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_listaAmigosConectados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jt_listaAmigosConectadosMouseReleased(evt);
            }
        });
        jScrollPane6.setViewportView(jt_listaAmigosConectados);

        lb_nomeIconeAmigoBatePapo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/todos-bate-papo.png"))); // NOI18N
        lb_nomeIconeAmigoBatePapo.setText("Todos(conectados)");
        lb_nomeIconeAmigoBatePapo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enviando para...", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 1, 11))); // NOI18N

        bt_visuPainelAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/selecionar-usuario-lista.png"))); // NOI18N
        bt_visuPainelAmigo.setToolTipText("");
        bt_visuPainelAmigo.setEnabled(false);
        bt_visuPainelAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_visuPainelAmigoActionPerformed(evt);
            }
        });

        bt_limparConversa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/remocao-recs-bate-papo.png"))); // NOI18N
        bt_limparConversa.setToolTipText("");
        bt_limparConversa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limparConversaActionPerformed(evt);
            }
        });

        bt_inicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/inicio.png"))); // NOI18N
        bt_inicio.setToolTipText("");
        bt_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_inicioActionPerformed(evt);
            }
        });

        lb_iconeBatePapo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/rec-bate-papo.png"))); // NOI18N

        cb_enviarParaTodosConectados.setBackground(new java.awt.Color(204, 204, 204));
        cb_enviarParaTodosConectados.setSelected(true);
        cb_enviarParaTodosConectados.setText("Para todos...");
        cb_enviarParaTodosConectados.setToolTipText("");
        cb_enviarParaTodosConectados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cb_enviarParaTodosConectados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cb_enviarParaTodosConectadosMouseReleased(evt);
            }
        });

        bt_limpaCampoRecInst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/remocao-rec-bate-papo.png"))); // NOI18N
        bt_limpaCampoRecInst.setToolTipText("");
        bt_limpaCampoRecInst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpaCampoRecInstActionPerformed(evt);
            }
        });

        jp_quemMeVe.setBackground(new java.awt.Color(204, 204, 204));
        jp_quemMeVe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quem bate papo comigo?", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 1, 10))); // NOI18N
        jp_quemMeVe.setToolTipText("Escolha quem poderá ver você no bate papo");

        rb_todosBatemPapoComigo.setBackground(new java.awt.Color(204, 204, 204));
        bg_quemBatePapoComigo.add(rb_todosBatemPapoComigo);
        rb_todosBatemPapoComigo.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        rb_todosBatemPapoComigo.setSelected(true);
        rb_todosBatemPapoComigo.setText("Todos(conectados)");
        rb_todosBatemPapoComigo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_todosBatemPapoComigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_todosBatemPapoComigoActionPerformed(evt);
            }
        });

        rb_soAmigosBatemPapoComigo.setBackground(new java.awt.Color(204, 204, 204));
        bg_quemBatePapoComigo.add(rb_soAmigosBatemPapoComigo);
        rb_soAmigosBatemPapoComigo.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        rb_soAmigosBatemPapoComigo.setText("Amigos(conectados)");
        rb_soAmigosBatemPapoComigo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_soAmigosBatemPapoComigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_soAmigosBatemPapoComigoActionPerformed(evt);
            }
        });

        lb_ninguemBatePapoComigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_ninguemBatePapoComigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ninguem-bate-papo-comigo.png"))); // NOI18N
        lb_ninguemBatePapoComigo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_ninguemBatePapoComigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_ninguemBatePapoComigoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jp_quemMeVeLayout = new javax.swing.GroupLayout(jp_quemMeVe);
        jp_quemMeVe.setLayout(jp_quemMeVeLayout);
        jp_quemMeVeLayout.setHorizontalGroup(
            jp_quemMeVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_quemMeVeLayout.createSequentialGroup()
                .addGroup(jp_quemMeVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_quemMeVeLayout.createSequentialGroup()
                        .addComponent(rb_todosBatemPapoComigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_ninguemBatePapoComigo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_quemMeVeLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(rb_soAmigosBatemPapoComigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jp_quemMeVeLayout.setVerticalGroup(
            jp_quemMeVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_quemMeVeLayout.createSequentialGroup()
                .addGroup(jp_quemMeVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jp_quemMeVeLayout.createSequentialGroup()
                        .addComponent(rb_todosBatemPapoComigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jp_quemMeVeLayout.createSequentialGroup()
                        .addComponent(lb_ninguemBatePapoComigo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addComponent(rb_soAmigosBatemPapoComigo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lb_qtdUsuarioListaBatePapo.setText("(0)");

        tf_pesquisaUsuarioBatePapo.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_pesquisaUsuarioBatePapo.setForeground(new java.awt.Color(0, 0, 255));
        tf_pesquisaUsuarioBatePapo.setToolTipText("Pesquisar por usuários na lista...");
        tf_pesquisaUsuarioBatePapo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_pesquisaUsuarioBatePapoKeyReleased(evt);
            }
        });

        bt_verMaisUsuariosBatePapo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisUsuariosBatePapo.setToolTipText("Visualizar mais ocorrências na lista");
        bt_verMaisUsuariosBatePapo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisUsuariosBatePapoActionPerformed(evt);
            }
        });

        bt_atualizarListaUsuariosBatePapo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atualizarListaUsuariosBatePapo.setToolTipText("Limpar campo de pesquisa");
        bt_atualizarListaUsuariosBatePapo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atualizarListaUsuariosBatePapoActionPerformed(evt);
            }
        });

        lb_qtdTotalUsuarioBatePapo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/todos-bate-papo.png"))); // NOI18N
        lb_qtdTotalUsuarioBatePapo.setText("(999) pessoa(s) no geral...");

        javax.swing.GroupLayout jp_batePapoLayout = new javax.swing.GroupLayout(jp_batePapo);
        jp_batePapo.setLayout(jp_batePapoLayout);
        jp_batePapoLayout.setHorizontalGroup(
            jp_batePapoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_batePapoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_batePapoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(jp_batePapoLayout.createSequentialGroup()
                        .addComponent(lb_nomeIconeAmigoBatePapo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jp_quemMeVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_batePapoLayout.createSequentialGroup()
                        .addComponent(lb_iconeBatePapo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_recadoInst, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_enviarRecInst, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_limpaCampoRecInst, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_limparConversa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_batePapoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_batePapoLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lb_qtdUsuarioListaBatePapo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_pesquisaUsuarioBatePapo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisUsuariosBatePapo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atualizarListaUsuariosBatePapo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_batePapoLayout.createSequentialGroup()
                        .addComponent(cb_enviarParaTodosConectados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_visuPainelAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lb_qtdTotalUsuarioBatePapo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jp_batePapoLayout.setVerticalGroup(
            jp_batePapoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_batePapoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_batePapoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_batePapoLayout.createSequentialGroup()
                        .addGroup(jp_batePapoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_nomeIconeAmigoBatePapo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jp_quemMeVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                    .addGroup(jp_batePapoLayout.createSequentialGroup()
                        .addGroup(jp_batePapoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_batePapoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bt_visuPainelAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bt_inicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb_enviarParaTodosConectados, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_qtdTotalUsuarioBatePapo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_batePapoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_limpaCampoRecInst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_limparConversa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_pesquisaUsuarioBatePapo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_verMaisUsuariosBatePapo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_qtdUsuarioListaBatePapo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_enviarRecInst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_atualizarListaUsuariosBatePapo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_iconeBatePapo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_recadoInst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtp_painelTabuladoUsuario.addTab("(0)", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/todos-bate-papo.png")), jp_batePapo); // NOI18N

        jp_inicioAmigo.setBackground(new java.awt.Color(0, 0, 0));
        jp_inicioAmigo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_inicioAmigoComponentShown(evt);
            }
        });

        lb_nomeIconeHumorAmigo.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lb_nomeIconeHumorAmigo.setForeground(new java.awt.Color(255, 255, 255));
        lb_nomeIconeHumorAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/novato.png"))); // NOI18N
        lb_nomeIconeHumorAmigo.setText("NOME DO AMIGO");
        lb_nomeIconeHumorAmigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_nomeIconeHumorAmigoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_nomeIconeHumorAmigoMouseExited(evt);
            }
        });

        lb_dataCadastroAmigo.setForeground(new java.awt.Color(255, 255, 255));
        lb_dataCadastroAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/data-cadastro.png"))); // NOI18N
        lb_dataCadastroAmigo.setText("DATA DE CADASTRO DO AMIGO NO FACECARD");
        lb_dataCadastroAmigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_dataCadastroAmigoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_dataCadastroAmigoMouseExited(evt);
            }
        });

        jt_listaAmigosDoAmigo.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jt_listaAmigosDoAmigo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Status", "Nome do amigo"}
            },
            new String [] {
                "Amigos", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaAmigosDoAmigo.setToolTipText("LIsta de amigos do usuário visitado");
        jt_listaAmigosDoAmigo.setRowHeight(35);
        jt_listaAmigosDoAmigo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_listaAmigosDoAmigo.getTableHeader().setReorderingAllowed(false);
        //jTable1.getColumn(jt_listaAmigosUsuario.getRowHeight(0)).setCellEditor(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        sp_listaAmigosAmigo.setViewportView(jt_listaAmigosDoAmigo);

        bt_enviarRecPriv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-privado-add.png"))); // NOI18N
        bt_enviarRecPriv.setToolTipText("");
        bt_enviarRecPriv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_enviarRecPrivActionPerformed(evt);
            }
        });

        lb_escrevaUmRecPriv.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        lb_escrevaUmRecPriv.setForeground(new java.awt.Color(255, 255, 255));
        lb_escrevaUmRecPriv.setText("Escreva um recado privado para [apelido] ¬");
        lb_escrevaUmRecPriv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_escrevaUmRecPrivMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_escrevaUmRecPrivMouseExited(evt);
            }
        });

        tf_recadoPriv.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        tf_recadoPriv.setForeground(new java.awt.Color(255, 0, 0));
        tf_recadoPriv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_recadoPrivActionPerformed(evt);
            }
        });

        lb_ultOcorrenciaAmigo.setForeground(new java.awt.Color(255, 255, 255));
        lb_ultOcorrenciaAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ocorrencia.png"))); // NOI18N
        lb_ultOcorrenciaAmigo.setText("ÚLTIMA OCORRÊNCIA DO AMIGO");
        lb_ultOcorrenciaAmigo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_ultOcorrenciaAmigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_ultOcorrenciaAmigoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_ultOcorrenciaAmigoMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_ultOcorrenciaAmigoMouseReleased(evt);
            }
        });

        lb_fraseAmigo.setForeground(new java.awt.Color(255, 255, 255));
        lb_fraseAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/frase.png"))); // NOI18N
        lb_fraseAmigo.setText("FRASE DO AMIGO");
        lb_fraseAmigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_fraseAmigoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_fraseAmigoMouseExited(evt);
            }
        });

        bt_batePapo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/add-rec-bate-papo.png"))); // NOI18N
        bt_batePapo.setToolTipText("Bater papo com ele(a) agora!");
        bt_batePapo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_batePapoActionPerformed(evt);
            }
        });

        bt_bloqDesblocAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/amigo-bloqueio.png"))); // NOI18N
        bt_bloqDesblocAmigo.setToolTipText("Bloqueá - lo(a)");
        bt_bloqDesblocAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_bloqDesblocAmigoActionPerformed(evt);
            }
        });

        bt_escondePainel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/cancelar.png"))); // NOI18N
        bt_escondePainel.setToolTipText("Fechar Facecard");
        bt_escondePainel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_escondePainelActionPerformed(evt);
            }
        });

        lb_niverAmigo.setForeground(new java.awt.Color(255, 255, 255));
        lb_niverAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/aniversario.png"))); // NOI18N
        lb_niverAmigo.setText("DATA DE ANIVERSÁRIO DO AMIGO");
        lb_niverAmigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_niverAmigoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_niverAmigoMouseExited(evt);
            }
        });

        lb_sexoAmigo.setForeground(new java.awt.Color(255, 255, 255));
        lb_sexoAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/masculino.png"))); // NOI18N
        lb_sexoAmigo.setText("SEXO DO AMIGO");
        lb_sexoAmigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_sexoAmigoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_sexoAmigoMouseExited(evt);
            }
        });

        lb_iconeRecPriv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-privado.png"))); // NOI18N

        jp_opcoesAmigosAmigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bg_visuAmigosAmigos.add(rb_qtdTotalAmigosAmigo);
        rb_qtdTotalAmigosAmigo.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        rb_qtdTotalAmigosAmigo.setSelected(true);
        rb_qtdTotalAmigosAmigo.setText("Amigos (999)");
        rb_qtdTotalAmigosAmigo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_qtdTotalAmigosAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_qtdTotalAmigosAmigoActionPerformed(evt);
            }
        });

        bg_visuAmigosAmigos.add(rb_qtdAmigosComuns);
        rb_qtdAmigosComuns.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        rb_qtdAmigosComuns.setText("Comuns(999)");
        rb_qtdAmigosComuns.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rb_qtdAmigosComuns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_qtdAmigosComunsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_opcoesAmigosAmigoLayout = new javax.swing.GroupLayout(jp_opcoesAmigosAmigo);
        jp_opcoesAmigosAmigo.setLayout(jp_opcoesAmigosAmigoLayout);
        jp_opcoesAmigosAmigoLayout.setHorizontalGroup(
            jp_opcoesAmigosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_opcoesAmigosAmigoLayout.createSequentialGroup()
                .addComponent(rb_qtdTotalAmigosAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_qtdAmigosComuns, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jp_opcoesAmigosAmigoLayout.setVerticalGroup(
            jp_opcoesAmigosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_opcoesAmigosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rb_qtdTotalAmigosAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(rb_qtdAmigosComuns, javax.swing.GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE))
        );

        lb_qtdComentarioUltOcoAmigo.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        lb_qtdComentarioUltOcoAmigo.setForeground(new java.awt.Color(255, 255, 255));
        lb_qtdComentarioUltOcoAmigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_qtdComentarioUltOcoAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/comentario-ocorrencia.png"))); // NOI18N
        lb_qtdComentarioUltOcoAmigo.setText("(999)");
        lb_qtdComentarioUltOcoAmigo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lb_qtdComentarioUltOcoAmigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_qtdComentarioUltOcoAmigoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_qtdComentarioUltOcoAmigoMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_qtdComentarioUltOcoAmigoMouseReleased(evt);
            }
        });

        lb_mostrarNaoMostrarNomeAmigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_mostrarNaoMostrarNomeAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/disfarce.png"))); // NOI18N
        lb_mostrarNaoMostrarNomeAmigo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lb_qtdUsuariosListaAmigo.setForeground(new java.awt.Color(153, 153, 153));
        lb_qtdUsuariosListaAmigo.setText("(999)");

        bt_verMaisUsuariosListaAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisUsuariosListaAmigo.setToolTipText("");
        bt_verMaisUsuariosListaAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisUsuariosListaAmigoActionPerformed(evt);
            }
        });

        bt_atuUsuariosListaAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atuUsuariosListaAmigo.setToolTipText("");
        bt_atuUsuariosListaAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atuUsuariosListaAmigoActionPerformed(evt);
            }
        });

        tf_pesquisaListaAmigo.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_pesquisaListaAmigo.setForeground(new java.awt.Color(0, 0, 255));
        tf_pesquisaListaAmigo.setToolTipText("");
        tf_pesquisaListaAmigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_pesquisaListaAmigoKeyReleased(evt);
            }
        });

        lb_tipoUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_tipoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/amigo-ok.png"))); // NOI18N
        lb_tipoUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        bt_atualizarInfoAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-informacao.png"))); // NOI18N
        bt_atualizarInfoAmigo.setToolTipText("Bloqueá - lo(a)");
        bt_atualizarInfoAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atualizarInfoAmigoActionPerformed(evt);
            }
        });

        bt_enviarArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-add.png"))); // NOI18N
        bt_enviarArquivo.setToolTipText("Bloqueá - lo(a)");
        bt_enviarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_enviarArquivoActionPerformed(evt);
            }
        });

        bt_addExcAceEspAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/amigo-remocao.png"))); // NOI18N
        bt_addExcAceEspAmigo.setToolTipText("Bloqueá - lo(a)");
        bt_addExcAceEspAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addExcAceEspAmigoActionPerformed(evt);
            }
        });

        bt_comprimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/sino.png"))); // NOI18N
        bt_comprimento.setToolTipText("Bloqueá - lo(a)");
        bt_comprimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_comprimentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_inicioAmigoLayout = new javax.swing.GroupLayout(jp_inicioAmigo);
        jp_inicioAmigo.setLayout(jp_inicioAmigoLayout);
        jp_inicioAmigoLayout.setHorizontalGroup(
            jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_inicioAmigoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inicioAmigoLayout.createSequentialGroup()
                        .addComponent(bt_addExcAceEspAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt_bloqDesblocAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt_atualizarInfoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt_enviarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt_comprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt_batePapo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt_escondePainel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_tipoUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_mostrarNaoMostrarNomeAmigo))
                    .addGroup(jp_inicioAmigoLayout.createSequentialGroup()
                        .addGroup(jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lb_fraseAmigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_sexoAmigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_niverAmigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                    .addComponent(lb_nomeIconeHumorAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_dataCadastroAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inicioAmigoLayout.createSequentialGroup()
                        .addComponent(lb_ultOcorrenciaAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_qtdComentarioUltOcoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inicioAmigoLayout.createSequentialGroup()
                        .addComponent(lb_iconeRecPriv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_recadoPriv, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_enviarRecPriv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_escrevaUmRecPriv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inicioAmigoLayout.createSequentialGroup()
                        .addComponent(lb_qtdUsuariosListaAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_pesquisaListaAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisUsuariosListaAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atuUsuariosListaAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(sp_listaAmigosAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jp_opcoesAmigosAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jp_inicioAmigoLayout.setVerticalGroup(
            jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_inicioAmigoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_inicioAmigoLayout.createSequentialGroup()
                        .addGroup(jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_comprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_batePapo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_escondePainel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_atualizarInfoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_bloqDesblocAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_addExcAceEspAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_enviarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_inicioAmigoLayout.createSequentialGroup()
                                .addComponent(lb_nomeIconeHumorAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lb_ultOcorrenciaAmigo)
                                    .addComponent(lb_qtdComentarioUltOcoAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_fraseAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_sexoAmigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_niverAmigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_dataCadastroAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lb_escrevaUmRecPriv))
                            .addComponent(sp_listaAmigosAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bt_enviarRecPriv, 0, 20, Short.MAX_VALUE)
                            .addComponent(lb_iconeRecPriv, 0, 20, Short.MAX_VALUE)
                            .addComponent(bt_atuUsuariosListaAmigo, 0, 20, Short.MAX_VALUE)
                            .addComponent(bt_verMaisUsuariosListaAmigo, 0, 20, Short.MAX_VALUE)
                            .addGroup(jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tf_pesquisaListaAmigo, 0, 20, Short.MAX_VALUE)
                                .addComponent(lb_qtdUsuariosListaAmigo, 0, 20, Short.MAX_VALUE))
                            .addComponent(tf_recadoPriv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jp_inicioAmigoLayout.createSequentialGroup()
                        .addGroup(jp_inicioAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jp_opcoesAmigosAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_mostrarNaoMostrarNomeAmigo)
                            .addComponent(lb_tipoUsuario))
                        .addGap(217, 217, 217))))
        );

        jtp_painelTabuladoAmigo.addTab("apelido", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/desconectado.png")), jp_inicioAmigo, "Informações básicas"); // NOI18N

        jp_ocorrenciaAmigo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_ocorrenciaAmigoComponentShown(evt);
            }
        });

        jt_listaOcorrenciaAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        jt_listaOcorrenciaAmigo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Quem", "Lista de ocorrências enviadas", "Quando", "Qtd. Cmts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaOcorrenciaAmigo.setToolTipText("");
        jt_listaOcorrenciaAmigo.setFocusTraversalPolicyProvider(true);
        jt_listaOcorrenciaAmigo.setMaximumSize(new java.awt.Dimension(2147483647, 33));
        jt_listaOcorrenciaAmigo.setRowHeight(35);
        jt_listaOcorrenciaAmigo.setRowMargin(2);
        jt_listaOcorrenciaAmigo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_listaOcorrenciaAmigo.setShowVerticalLines(false);
        jt_listaOcorrenciaAmigo.setSurrendersFocusOnKeystroke(true);
        jt_listaOcorrenciaAmigo.getTableHeader().setReorderingAllowed(false);
        jt_listaOcorrenciaAmigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_listaOcorrenciaAmigoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jt_listaOcorrenciaAmigoMouseReleased(evt);
            }
        });
        jt_listaOcorrenciaAmigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_listaOcorrenciaAmigoKeyReleased(evt);
            }
        });
        sp_ocorrenciasAmigo.setViewportView(jt_listaOcorrenciaAmigo);

        bt_comentarOcorrenciaAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/comentario-ocorrencia.png"))); // NOI18N
        bt_comentarOcorrenciaAmigo.setToolTipText("");
        bt_comentarOcorrenciaAmigo.setEnabled(false);
        bt_comentarOcorrenciaAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_comentarOcorrenciaAmigoActionPerformed(evt);
            }
        });

        lb_qtdOcoEncontradaAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_qtdOcoEncontradaAmigo.setText("(999) Encontradas");

        tf_conteudoOcoPesquisadoAmigo.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_conteudoOcoPesquisadoAmigo.setToolTipText("");
        tf_conteudoOcoPesquisadoAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_conteudoOcoPesquisadoAmigoActionPerformed(evt);
            }
        });
        tf_conteudoOcoPesquisadoAmigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_conteudoOcoPesquisadoAmigoKeyReleased(evt);
            }
        });

        lb_pesquisarOcoAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_pesquisarOcoAmigo.setText("Pesquisar ocorrência:");

        bt_atuListaOcoAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atuListaOcoAmigo.setToolTipText("");
        bt_atuListaOcoAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atuListaOcoAmigoActionPerformed(evt);
            }
        });

        bt_verMaisOcoAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisOcoAmigo.setToolTipText("");
        bt_verMaisOcoAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisOcoAmigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_ocorrenciaAmigoLayout = new javax.swing.GroupLayout(jp_ocorrenciaAmigo);
        jp_ocorrenciaAmigo.setLayout(jp_ocorrenciaAmigoLayout);
        jp_ocorrenciaAmigoLayout.setHorizontalGroup(
            jp_ocorrenciaAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_ocorrenciaAmigoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_ocorrenciaAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sp_ocorrenciasAmigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addGroup(jp_ocorrenciaAmigoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_comentarOcorrenciaAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_ocorrenciaAmigoLayout.createSequentialGroup()
                        .addComponent(lb_qtdOcoEncontradaAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_pesquisarOcoAmigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_conteudoOcoPesquisadoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisOcoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atuListaOcoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jp_ocorrenciaAmigoLayout.setVerticalGroup(
            jp_ocorrenciaAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_ocorrenciaAmigoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_comentarOcorrenciaAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_ocorrenciasAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_ocorrenciaAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_verMaisOcoAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_atuListaOcoAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_conteudoOcoPesquisadoAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_ocorrenciaAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lb_pesquisarOcoAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_qtdOcoEncontradaAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jtp_painelTabuladoAmigo.addTab("", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/ocorrencia-add.png")), jp_ocorrenciaAmigo, ""); // NOI18N

        jp_atualizacoesAmigo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_atualizacoesAmigoComponentShown(evt);
            }
        });

        jt_listaAtualizacoesAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        jt_listaAtualizacoesAmigo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Quem", "Atualização(ões)", "Quando", "Qtd. Cmts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaAtualizacoesAmigo.setMaximumSize(new java.awt.Dimension(2147483647, 33));
        jt_listaAtualizacoesAmigo.setRowHeight(35);
        jt_listaAtualizacoesAmigo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_listaAtualizacoesAmigo.setShowHorizontalLines(false);
        jt_listaAtualizacoesAmigo.setShowVerticalLines(false);
        jt_listaAtualizacoesAmigo.getTableHeader().setReorderingAllowed(false);
        jt_listaAtualizacoesAmigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_listaAtualizacoesAmigoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jt_listaAtualizacoesAmigoMouseReleased(evt);
            }
        });
        jt_listaAtualizacoesAmigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_listaAtualizacoesAmigoKeyReleased(evt);
            }
        });
        sp_atualizacoesAmigo.setViewportView(jt_listaAtualizacoesAmigo);

        bt_comentarAtuAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/comentario-atualizar.png"))); // NOI18N
        bt_comentarAtuAmigo.setToolTipText("");
        bt_comentarAtuAmigo.setEnabled(false);
        bt_comentarAtuAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_comentarAtuAmigoActionPerformed(evt);
            }
        });

        bt_atuListaAtuAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atuListaAtuAmigo.setToolTipText("");
        bt_atuListaAtuAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atuListaAtuAmigoActionPerformed(evt);
            }
        });

        lb_qtdAtuEncontradaAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_qtdAtuEncontradaAmigo.setText("(999) Encontradas");

        lb_pesquisarAtuAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_pesquisarAtuAmigo.setText("Pesquisar atualização:");

        tf_conteudoAtuPesquisadoAmigo.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_conteudoAtuPesquisadoAmigo.setToolTipText("");
        tf_conteudoAtuPesquisadoAmigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_conteudoAtuPesquisadoAmigoKeyReleased(evt);
            }
        });

        bt_verMaisAtuAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisAtuAmigo.setToolTipText("");
        bt_verMaisAtuAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisAtuAmigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_atualizacoesAmigoLayout = new javax.swing.GroupLayout(jp_atualizacoesAmigo);
        jp_atualizacoesAmigo.setLayout(jp_atualizacoesAmigoLayout);
        jp_atualizacoesAmigoLayout.setHorizontalGroup(
            jp_atualizacoesAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_atualizacoesAmigoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_atualizacoesAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sp_atualizacoesAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_atualizacoesAmigoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_comentarAtuAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_atualizacoesAmigoLayout.createSequentialGroup()
                        .addComponent(lb_qtdAtuEncontradaAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_pesquisarAtuAmigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_conteudoAtuPesquisadoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisAtuAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atuListaAtuAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jp_atualizacoesAmigoLayout.setVerticalGroup(
            jp_atualizacoesAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_atualizacoesAmigoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_comentarAtuAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_atualizacoesAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_atualizacoesAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_verMaisAtuAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lb_pesquisarAtuAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_qtdAtuEncontradaAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_conteudoAtuPesquisadoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bt_atuListaAtuAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtp_painelTabuladoAmigo.addTab("", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/add_atualizacao-ele.png")), jp_atualizacoesAmigo); // NOI18N

        jp_recPublicosAmigo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_recPublicosAmigoComponentShown(evt);
            }
        });

        tf_recadoPub.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        tf_recadoPub.setForeground(new java.awt.Color(255, 0, 0));
        tf_recadoPub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_recadoPubActionPerformed(evt);
            }
        });

        lb_estadoAtualUsuario3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-publico.png"))); // NOI18N
        lb_estadoAtualUsuario3.setText("Estado atual");

        bt_enviarRecPub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-publico-add.png"))); // NOI18N
        bt_enviarRecPub.setToolTipText("");
        bt_enviarRecPub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_enviarRecPubActionPerformed(evt);
            }
        });

        jt_listaRecadosPubAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        jt_listaRecadosPubAmigo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"fuano", "oi", "ontem0", "(0)"}
            },
            new String [] {
                "Quem", "Recado", "Quando", "Qtd. Cmts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaRecadosPubAmigo.setToolTipText("Lista de recados");
        jt_listaRecadosPubAmigo.setFocusTraversalPolicyProvider(true);
        jt_listaRecadosPubAmigo.setMaximumSize(new java.awt.Dimension(2147483647, 33));
        jt_listaRecadosPubAmigo.setRowHeight(35);
        jt_listaRecadosPubAmigo.setRowMargin(2);
        jt_listaRecadosPubAmigo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_listaRecadosPubAmigo.setShowHorizontalLines(false);
        jt_listaRecadosPubAmigo.setShowVerticalLines(false);
        jt_listaRecadosPubAmigo.setSurrendersFocusOnKeystroke(true);
        jt_listaRecadosPubAmigo.getTableHeader().setReorderingAllowed(false);
        jt_listaRecadosPubAmigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_listaRecadosPubAmigoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jt_listaRecadosPubAmigoMouseReleased(evt);
            }
        });
        jt_listaRecadosPubAmigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_listaRecadosPubAmigoKeyReleased(evt);
            }
        });
        sp_recadosPubAmigo.setViewportView(jt_listaRecadosPubAmigo);

        bt_comentarRecPubAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/comentario-recado-publico.png"))); // NOI18N
        bt_comentarRecPubAmigo.setToolTipText("");
        bt_comentarRecPubAmigo.setEnabled(false);
        bt_comentarRecPubAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_comentarRecPubAmigoActionPerformed(evt);
            }
        });

        bt_atuListaRecPubAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atuListaRecPubAmigo.setToolTipText("");
        bt_atuListaRecPubAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atuListaRecPubAmigoActionPerformed(evt);
            }
        });

        lb_qtdRecPubEncontradoAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_qtdRecPubEncontradoAmigo.setText("(999) Encontradas");

        lb_pesquisarRecPubAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_pesquisarRecPubAmigo.setText("Pesquisar rec. público:");

        tf_conteudoRecPubPesquisadoAmigo.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_conteudoRecPubPesquisadoAmigo.setToolTipText("");
        tf_conteudoRecPubPesquisadoAmigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_conteudoRecPubPesquisadoAmigoKeyReleased(evt);
            }
        });

        bt_verMaisRecPubAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisRecPubAmigo.setToolTipText("");
        bt_verMaisRecPubAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisRecPubAmigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_recPublicosAmigoLayout = new javax.swing.GroupLayout(jp_recPublicosAmigo);
        jp_recPublicosAmigo.setLayout(jp_recPublicosAmigoLayout);
        jp_recPublicosAmigoLayout.setHorizontalGroup(
            jp_recPublicosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_recPublicosAmigoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_recPublicosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sp_recadosPubAmigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addGroup(jp_recPublicosAmigoLayout.createSequentialGroup()
                        .addComponent(lb_estadoAtualUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_recadoPub, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_enviarRecPub, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                        .addComponent(bt_comentarRecPubAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_recPublicosAmigoLayout.createSequentialGroup()
                        .addComponent(lb_qtdRecPubEncontradoAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_pesquisarRecPubAmigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_conteudoRecPubPesquisadoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisRecPubAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atuListaRecPubAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jp_recPublicosAmigoLayout.setVerticalGroup(
            jp_recPublicosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_recPublicosAmigoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_recPublicosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_comentarRecPubAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_recPublicosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_estadoAtualUsuario3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_recadoPub, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(bt_enviarRecPub, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_recadosPubAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_recPublicosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lb_qtdRecPubEncontradoAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_atuListaRecPubAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(tf_conteudoRecPubPesquisadoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(bt_verMaisRecPubAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(lb_pesquisarRecPubAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtp_painelTabuladoAmigo.addTab("", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/recado-publico-recebido.png")), jp_recPublicosAmigo); // NOI18N

        jt_listaArquivosAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        jt_listaArquivosAmigo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Quem", "Tipo", "Nome", "Tam.(bytes)", "Quando", "Qtd. Cmts"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_listaArquivosAmigo.setToolTipText("Lista de recados privados");
        jt_listaArquivosAmigo.setFocusTraversalPolicyProvider(true);
        jt_listaArquivosAmigo.setMaximumSize(new java.awt.Dimension(2147483647, 33));
        jt_listaArquivosAmigo.setRowHeight(35);
        jt_listaArquivosAmigo.setRowMargin(2);
        jt_listaArquivosAmigo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_listaArquivosAmigo.setShowVerticalLines(false);
        jt_listaArquivosAmigo.setSurrendersFocusOnKeystroke(true);
        jt_listaArquivosAmigo.getTableHeader().setReorderingAllowed(false);
        jt_listaArquivosAmigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_listaArquivosAmigoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jt_listaArquivosAmigoMouseReleased(evt);
            }
        });
        jt_listaArquivosAmigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_listaArquivosAmigoKeyReleased(evt);
            }
        });
        sp_arquivosAmigo.setViewportView(jt_listaArquivosAmigo);

        bt_baixarArquivoSelecionadoAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-baixar.png"))); // NOI18N
        bt_baixarArquivoSelecionadoAmigo.setToolTipText("");
        bt_baixarArquivoSelecionadoAmigo.setEnabled(false);
        bt_baixarArquivoSelecionadoAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_baixarArquivoSelecionadoAmigoActionPerformed(evt);
            }
        });

        bt_comentarArquivoSelecionadoAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/comentario-arquivo.png"))); // NOI18N
        bt_comentarArquivoSelecionadoAmigo.setToolTipText("");
        bt_comentarArquivoSelecionadoAmigo.setEnabled(false);
        bt_comentarArquivoSelecionadoAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_comentarArquivoSelecionadoAmigoActionPerformed(evt);
            }
        });

        lb_pesquisarArqAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_pesquisarArqAmigo.setText("Pesquisar arquivo:");

        tf_nomeArqPesquisadoAmigo.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        tf_nomeArqPesquisadoAmigo.setToolTipText("");
        tf_nomeArqPesquisadoAmigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_nomeArqPesquisadoAmigoKeyReleased(evt);
            }
        });

        lb_qtdArqEncontradoAmigo.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        lb_qtdArqEncontradoAmigo.setText("(999) Encontradas");

        bt_atuListaArqAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/atualizar-lista.png"))); // NOI18N
        bt_atuListaArqAmigo.setToolTipText("");
        bt_atuListaArqAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_atuListaArqAmigoActionPerformed(evt);
            }
        });

        bt_verMaisArqAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/mostra_mais_conteudo.png"))); // NOI18N
        bt_verMaisArqAmigo.setToolTipText("");
        bt_verMaisArqAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_verMaisArqAmigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_arquivosAmigoLayout = new javax.swing.GroupLayout(jp_arquivosAmigo);
        jp_arquivosAmigo.setLayout(jp_arquivosAmigoLayout);
        jp_arquivosAmigoLayout.setHorizontalGroup(
            jp_arquivosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_arquivosAmigoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_arquivosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sp_arquivosAmigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addGroup(jp_arquivosAmigoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_baixarArquivoSelecionadoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_comentarArquivoSelecionadoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_arquivosAmigoLayout.createSequentialGroup()
                        .addComponent(lb_qtdArqEncontradoAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_pesquisarArqAmigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_nomeArqPesquisadoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_verMaisArqAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_atuListaArqAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jp_arquivosAmigoLayout.setVerticalGroup(
            jp_arquivosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_arquivosAmigoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_arquivosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_comentarArquivoSelecionadoAmigo, 0, 20, Short.MAX_VALUE)
                    .addComponent(bt_baixarArquivoSelecionadoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_arquivosAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_arquivosAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_atuListaArqAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bt_verMaisArqAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lb_pesquisarArqAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_nomeArqPesquisadoAmigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_qtdArqEncontradoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtp_painelTabuladoAmigo.addTab("", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/arquivo-add.png")), jp_arquivosAmigo); // NOI18N

        jp_pessoal.setBackground(new java.awt.Color(255, 255, 255));
        jp_pessoal.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_pessoalComponentShown(evt);
            }
        });

        painel_1_amigo.setBackground(new java.awt.Color(255, 255, 255));

        s_lb_quem_sou.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_quem_sou.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_quem_sou.setText("Quem sou:");

        s_lb_adoro.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_adoro.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_adoro.setText("Adoro:");

        s_lb_fisico.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_fisico.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_fisico.setText("Físico:");

        s_lb_nacionalidade.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_nacionalidade.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_nacionalidade.setText("Naturalidade:");

        s_lb_estado_civil.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_estado_civil.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_estado_civil.setText("Estado civíl:");

        s_lb_naturalidade.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_naturalidade.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_naturalidade.setText("Nacionalidade:");

        lb_nacionalidade.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_nacionalidade.setForeground(new java.awt.Color(255, 0, 0));
        lb_nacionalidade.setText("Nome:");

        lb_naturalidade.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_naturalidade.setForeground(new java.awt.Color(255, 0, 0));
        lb_naturalidade.setText("Nome:");

        lb_estadoCivil.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_estadoCivil.setForeground(new java.awt.Color(255, 0, 0));
        lb_estadoCivil.setText("Nome:");

        lb_fisico.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_fisico.setForeground(new java.awt.Color(255, 0, 0));
        lb_fisico.setText("Nome:");

        lb_adoro.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_adoro.setForeground(new java.awt.Color(255, 0, 0));
        lb_adoro.setText("Nome:");

        lb_quemSou.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_quemSou.setForeground(new java.awt.Color(255, 0, 0));
        lb_quemSou.setText("Nome:");

        s_lb_odeio.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_odeio.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_odeio.setText("Odeio:");

        lb_odeio.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_odeio.setForeground(new java.awt.Color(255, 0, 0));
        lb_odeio.setText("Nome:");

        s_lb_filme.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_filme.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_filme.setText("Um filme:");

        lb_filme.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_filme.setForeground(new java.awt.Color(255, 0, 0));
        lb_filme.setText("Nome:");

        s_lb_musica.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_musica.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_musica.setText("Uma música:");

        lb_musica.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_musica.setForeground(new java.awt.Color(255, 0, 0));
        lb_musica.setText("Nome:");

        s_lb_esporte.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_esporte.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_esporte.setText("Um esporte:");

        lb_esporte.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_esporte.setForeground(new java.awt.Color(255, 0, 0));
        lb_esporte.setText("Nome:");

        s_lb_lugar.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_lugar.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_lugar.setText("Um lugar:");

        lb_lugar.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_lugar.setForeground(new java.awt.Color(255, 0, 0));
        lb_lugar.setText("Nome:");

        s_lb_cantada.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_cantada.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_cantada.setText("Uma cantada:");

        lb_cantada.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_cantada.setForeground(new java.awt.Color(255, 0, 0));
        lb_cantada.setText("Nome:");

        s_lb_mania.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_mania.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_mania.setText("Uma mania:");

        lb_mania.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_mania.setForeground(new java.awt.Color(255, 0, 0));
        lb_mania.setText("Nome:");

        javax.swing.GroupLayout painel_1_amigoLayout = new javax.swing.GroupLayout(painel_1_amigo);
        painel_1_amigo.setLayout(painel_1_amigoLayout);
        painel_1_amigoLayout.setHorizontalGroup(
            painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_1_amigoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(s_lb_mania)
                    .addComponent(s_lb_cantada)
                    .addComponent(s_lb_quem_sou)
                    .addComponent(s_lb_adoro)
                    .addComponent(s_lb_estado_civil)
                    .addComponent(s_lb_fisico)
                    .addComponent(s_lb_nacionalidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_lugar)
                    .addComponent(s_lb_esporte)
                    .addComponent(s_lb_filme)
                    .addComponent(s_lb_odeio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_musica)
                    .addComponent(s_lb_naturalidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_naturalidade, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_mania, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_cantada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_lugar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_esporte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_musica, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_filme, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_adoro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_quemSou, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_estadoCivil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_fisico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_odeio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_nacionalidade, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );
        painel_1_amigoLayout.setVerticalGroup(
            painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_1_amigoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_naturalidade)
                    .addComponent(lb_nacionalidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_nacionalidade)
                    .addComponent(lb_naturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_fisico)
                    .addComponent(lb_fisico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_estado_civil)
                    .addComponent(lb_estadoCivil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_quem_sou)
                    .addComponent(lb_quemSou))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_adoro)
                    .addComponent(lb_adoro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_odeio)
                    .addComponent(lb_odeio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_filme)
                    .addComponent(lb_filme))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_musica)
                    .addComponent(lb_musica, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_esporte)
                    .addComponent(lb_esporte, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_lugar)
                    .addComponent(lb_lugar, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_cantada)
                    .addComponent(lb_cantada, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_1_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_mania)
                    .addComponent(lb_mania, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painel_2_amigo.setBackground(new java.awt.Color(255, 255, 255));

        s_lb_cor.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_cor.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_cor.setText("Uma cor:");

        lb_cor.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_cor.setForeground(new java.awt.Color(255, 0, 0));
        lb_cor.setText("Nome:");

        s_lb_conselho.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_conselho.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_conselho.setText("Um conselho:");

        lb_conselho.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_conselho.setForeground(new java.awt.Color(255, 0, 0));
        lb_conselho.setText("Nome:");

        s_lb_conselho1.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_conselho1.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_conselho1.setText("Sonho:");

        lb_sonho.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_sonho.setForeground(new java.awt.Color(255, 0, 0));
        lb_sonho.setText("Nome:");

        s_lb_cor1.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_cor1.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_cor1.setText("Time:");

        lb_time.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_time.setForeground(new java.awt.Color(255, 0, 0));
        lb_time.setText("Nome:");

        s_lb_cor2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_cor2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_cor2.setText("Pesadelo:");

        s_lb_conselho2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_conselho2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_conselho2.setText("Bebida:");

        lb_pesadelo.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_pesadelo.setForeground(new java.awt.Color(255, 0, 0));
        lb_pesadelo.setText("Nome:");

        lb_bebida.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_bebida.setForeground(new java.awt.Color(255, 0, 0));
        lb_bebida.setText("Nome:");

        s_lb_cor3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_cor3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_cor3.setText("Prato:");

        lb_passeio.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_passeio.setForeground(new java.awt.Color(255, 0, 0));
        lb_passeio.setText("Nome:");

        s_lb_conselho3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_conselho3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_conselho3.setText("Passeio:");

        lb_prato.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_prato.setForeground(new java.awt.Color(255, 0, 0));
        lb_prato.setText("Nome:");

        lb_desejo.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_desejo.setForeground(new java.awt.Color(255, 0, 0));
        lb_desejo.setText("Nome:");

        s_lb_cor4.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_cor4.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_cor4.setText("Viagem:");

        lb_viagem.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_viagem.setForeground(new java.awt.Color(255, 0, 0));
        lb_viagem.setText("Nome:");

        s_lb_conselho4.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_conselho4.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_conselho4.setText("Desejo:");

        s_lb_conselho5.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_conselho5.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_conselho5.setText("Esperança:");

        s_lb_cor5.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_cor5.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_cor5.setText("Vontade:");

        lb_vontade.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_vontade.setForeground(new java.awt.Color(255, 0, 0));
        lb_vontade.setText("Nome:");

        lb_esperanca.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_esperanca.setForeground(new java.awt.Color(255, 0, 0));
        lb_esperanca.setText("Nome:");

        lb_hobby.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_hobby.setForeground(new java.awt.Color(255, 0, 0));
        lb_hobby.setText("Nome:");

        s_lb_cor6.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_cor6.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_cor6.setText("Hobby:");

        javax.swing.GroupLayout painel_2_amigoLayout = new javax.swing.GroupLayout(painel_2_amigo);
        painel_2_amigo.setLayout(painel_2_amigoLayout);
        painel_2_amigoLayout.setHorizontalGroup(
            painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_2_amigoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_lb_cor)
                    .addComponent(s_lb_conselho)
                    .addComponent(s_lb_conselho1)
                    .addComponent(s_lb_cor1)
                    .addComponent(s_lb_cor2)
                    .addComponent(s_lb_conselho2)
                    .addComponent(s_lb_cor3)
                    .addComponent(s_lb_conselho3)
                    .addComponent(s_lb_cor4)
                    .addComponent(s_lb_conselho4)
                    .addComponent(s_lb_conselho5)
                    .addComponent(s_lb_cor5)
                    .addComponent(s_lb_cor6))
                .addGap(23, 23, 23)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_conselho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_sonho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_pesadelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_bebida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_passeio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_prato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_desejo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_viagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_vontade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_esperanca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_cor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_hobby, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painel_2_amigoLayout.setVerticalGroup(
            painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_2_amigoLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_cor)
                    .addComponent(lb_cor, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_conselho)
                    .addComponent(lb_conselho, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_cor1)
                    .addComponent(lb_time, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_conselho1)
                    .addComponent(lb_sonho, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_cor2)
                    .addComponent(lb_pesadelo, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_conselho2)
                    .addComponent(lb_bebida, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_cor3)
                    .addComponent(lb_prato, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_conselho3)
                    .addComponent(lb_passeio, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_cor4)
                    .addComponent(lb_viagem, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_conselho4)
                    .addComponent(lb_desejo, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_cor5)
                    .addComponent(lb_vontade, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_conselho5)
                    .addComponent(lb_esperanca, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_cor6)
                    .addComponent(lb_hobby, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jp_pessoalLayout = new javax.swing.GroupLayout(jp_pessoal);
        jp_pessoal.setLayout(jp_pessoalLayout);
        jp_pessoalLayout.setHorizontalGroup(
            jp_pessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_pessoalLayout.createSequentialGroup()
                .addComponent(painel_1_amigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painel_2_amigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(180, 180, 180))
        );
        jp_pessoalLayout.setVerticalGroup(
            jp_pessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_pessoalLayout.createSequentialGroup()
                .addGroup(jp_pessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painel_2_amigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painel_1_amigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtp_painelTabuladoAmigo.addTab("", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/informacao-pessoal.png")), jp_pessoal); // NOI18N

        jp_contato.setBackground(new java.awt.Color(255, 255, 255));
        jp_contato.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_contatoComponentShown(evt);
            }
        });

        painel_2_amigo1.setBackground(new java.awt.Color(255, 255, 255));

        s_lb_email1.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_email1.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_email1.setText("Hotmail::");

        lb_rua.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_rua.setForeground(new java.awt.Color(255, 0, 0));
        lb_rua.setText("Nome:");

        s_lb_cidade1.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_cidade1.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_cidade1.setText("Cidade:");

        lb_cidade.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_cidade.setForeground(new java.awt.Color(255, 0, 0));
        lb_cidade.setText("cidade");

        s_lb_celular1.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_celular1.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_celular1.setText("Cel. Oi:");

        s_lb_bairro1.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_bairro1.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_bairro1.setText("Bairro:");

        s_lb_fone1.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_fone1.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_fone1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/telefone.png"))); // NOI18N
        s_lb_fone1.setText("Fone:");

        s_lb_rua1.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_rua1.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_rua1.setText("Rua/Qd./Bloco:");

        lb_bairro.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_bairro.setForeground(new java.awt.Color(255, 0, 0));
        lb_bairro.setText("Bairro:");

        lb_fone.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_fone.setForeground(new java.awt.Color(255, 0, 0));
        lb_fone.setText("Fone:");

        lb_celOi.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_celOi.setForeground(new java.awt.Color(255, 0, 0));
        lb_celOi.setText("Cel:");

        lb_emailHotmail.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_emailHotmail.setForeground(new java.awt.Color(255, 0, 0));
        lb_emailHotmail.setText("Email:");

        s_lb_rua3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_rua3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_rua3.setText("N°");

        lb_numero.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_numero.setForeground(new java.awt.Color(255, 0, 0));
        lb_numero.setText("Nome:");

        s_lb_celular3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_celular3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_celular3.setText("Cel. Tim:");

        lb_celTim.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_celTim.setForeground(new java.awt.Color(255, 0, 0));
        lb_celTim.setText("Cel:");

        lb_celVivo.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_celVivo.setForeground(new java.awt.Color(255, 0, 0));
        lb_celVivo.setText("Cel:");

        s_lb_celular4.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_celular4.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_celular4.setText("Cel. Vivo:");

        s_lb_celular5.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_celular5.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_celular5.setText("Cel, Claro:");

        lb_celClaro.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_celClaro.setForeground(new java.awt.Color(255, 0, 0));
        lb_celClaro.setText("Cel:");

        lb_celOutro.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_celOutro.setForeground(new java.awt.Color(255, 0, 0));
        lb_celOutro.setText("Cel:");

        s_lb_celular6.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_celular6.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_celular6.setText("Cel. Outro:");

        s_lb_email3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_email3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_email3.setText("Gmail:");

        lb_emailGmail.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_emailGmail.setForeground(new java.awt.Color(255, 0, 0));
        lb_emailGmail.setText("Email:");

        javax.swing.GroupLayout painel_2_amigo1Layout = new javax.swing.GroupLayout(painel_2_amigo1);
        painel_2_amigo1.setLayout(painel_2_amigo1Layout);
        painel_2_amigo1Layout.setHorizontalGroup(
            painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_2_amigo1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_lb_celular6)
                    .addComponent(s_lb_celular5)
                    .addComponent(s_lb_celular4)
                    .addComponent(s_lb_celular3)
                    .addComponent(s_lb_rua3)
                    .addComponent(s_lb_rua1)
                    .addComponent(s_lb_celular1)
                    .addComponent(s_lb_fone1)
                    .addComponent(s_lb_bairro1)
                    .addComponent(s_lb_cidade1)
                    .addComponent(s_lb_email1)
                    .addComponent(s_lb_email3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_cidade, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_celOutro, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_celClaro, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_celVivo, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_celOi, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_celTim, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_fone, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_bairro, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_emailHotmail, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_emailGmail, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_rua, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(lb_numero, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );
        painel_2_amigo1Layout.setVerticalGroup(
            painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_2_amigo1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_rua1)
                    .addComponent(lb_rua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_rua3)
                    .addComponent(lb_numero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_bairro, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_bairro1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lb_cidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_cidade1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_fone, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_fone1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_celOi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s_lb_celular1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_celTim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s_lb_celular3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_lb_celular4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_celVivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_celClaro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s_lb_celular5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_lb_celular6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_celOutro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_email1)
                    .addComponent(lb_emailHotmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_email3)
                    .addComponent(lb_emailGmail))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painel_2_amigo2.setBackground(new java.awt.Color(255, 255, 255));

        s_lb_blog2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_blog2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_blog2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/facebook.png"))); // NOI18N
        s_lb_blog2.setText("Fecebook");

        lb_facebook.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_facebook.setForeground(new java.awt.Color(255, 0, 0));
        lb_facebook.setText("facebook");
        lb_facebook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_facebookMouseReleased(evt);
            }
        });

        lb_twitter.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_twitter.setForeground(new java.awt.Color(255, 0, 0));
        lb_twitter.setText("Twiter:");

        lb_orkut.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_orkut.setForeground(new java.awt.Color(255, 0, 0));
        lb_orkut.setText("Orkut:");

        lb_emailOutro.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_emailOutro.setForeground(new java.awt.Color(255, 0, 0));
        lb_emailOutro.setText("Email:");

        s_lb_orkut1.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_orkut1.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_orkut1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/orkut.png"))); // NOI18N
        s_lb_orkut1.setText("Orkut:");

        s_lb_twiter1.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_twiter1.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_twiter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/twitter.png"))); // NOI18N
        s_lb_twiter1.setText("Twitter:");

        lb_emailIg.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_emailIg.setForeground(new java.awt.Color(255, 0, 0));
        lb_emailIg.setText("Email:");

        s_lb_email4.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_email4.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_email4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/yahoo.png"))); // NOI18N
        s_lb_email4.setText("Yahoo!:");

        s_lb_email6.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_email6.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_email6.setText("Bol:");

        s_lb_email5.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_email5.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_email5.setText("Ig:");

        s_lb_email7.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_email7.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_email7.setText("Email Outro:");

        lb_emailBol.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_emailBol.setForeground(new java.awt.Color(255, 0, 0));
        lb_emailBol.setText("Email:");

        lb_emailYahoo.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_emailYahoo.setForeground(new java.awt.Color(255, 0, 0));
        lb_emailYahoo.setText("Email:");

        s_lb_blog5.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_blog5.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_blog5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/myspace.png"))); // NOI18N
        s_lb_blog5.setText("Myspace:");

        lb_myspace.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_myspace.setForeground(new java.awt.Color(255, 0, 0));
        lb_myspace.setText("facebook");

        s_lb_blog6.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_blog6.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_blog6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/skype.png"))); // NOI18N
        s_lb_blog6.setText("Skype:");

        lb_skype.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_skype.setForeground(new java.awt.Color(255, 0, 0));
        lb_skype.setText("facebook");

        lb_linkedin.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_linkedin.setForeground(new java.awt.Color(255, 0, 0));
        lb_linkedin.setText("facebook");

        s_lb_blog7.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_blog7.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_blog7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/linkedin.png"))); // NOI18N
        s_lb_blog7.setText("LinkedIn:");

        lb_blog.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_blog.setForeground(new java.awt.Color(255, 0, 0));
        lb_blog.setText("facebook");

        s_lb_blog8.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_blog8.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_blog8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/blog.png"))); // NOI18N
        s_lb_blog8.setText("Blog:");

        javax.swing.GroupLayout painel_2_amigo2Layout = new javax.swing.GroupLayout(painel_2_amigo2);
        painel_2_amigo2.setLayout(painel_2_amigo2Layout);
        painel_2_amigo2Layout.setHorizontalGroup(
            painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_2_amigo2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(s_lb_blog2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_blog5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_email4)
                    .addComponent(s_lb_email5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_email6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_email7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_orkut1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_twiter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_blog6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_blog7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_blog8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_blog, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(lb_emailYahoo, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(lb_emailIg, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(lb_emailBol, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(lb_emailOutro, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(lb_orkut, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(lb_twitter, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(lb_facebook, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(lb_myspace, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(lb_skype, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(lb_linkedin, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        painel_2_amigo2Layout.setVerticalGroup(
            painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_2_amigo2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_emailYahoo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_email4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_email5)
                    .addComponent(lb_emailIg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_email6)
                    .addComponent(lb_emailBol))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_email7)
                    .addComponent(lb_emailOutro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_orkut1)
                    .addComponent(lb_orkut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_twiter1)
                    .addComponent(lb_twitter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_blog2)
                    .addComponent(lb_facebook))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_blog5)
                    .addComponent(lb_myspace))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_blog6)
                    .addComponent(lb_skype))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_blog7)
                    .addComponent(lb_linkedin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2_amigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_blog)
                    .addComponent(s_lb_blog8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jp_contatoLayout = new javax.swing.GroupLayout(jp_contato);
        jp_contato.setLayout(jp_contatoLayout);
        jp_contatoLayout.setHorizontalGroup(
            jp_contatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_contatoLayout.createSequentialGroup()
                .addComponent(painel_2_amigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painel_2_amigo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_contatoLayout.setVerticalGroup(
            jp_contatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_contatoLayout.createSequentialGroup()
                .addGroup(jp_contatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painel_2_amigo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painel_2_amigo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtp_painelTabuladoAmigo.addTab("", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/informacao-contato.png")), jp_contato); // NOI18N

        jp_educacional.setBackground(new java.awt.Color(255, 255, 255));
        jp_educacional.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_educacionalComponentShown(evt);
            }
        });

        s_lb_odeio3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_odeio3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_odeio3.setText("Vou melhorar em...:");

        s_lb_filme3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_filme3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_filme3.setText("A instituição  é...:");

        s_lb_quem_sou3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_quem_sou3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_quem_sou3.setText("Hr.de intervalo:");

        s_lb_adoro3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_adoro3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_adoro3.setText("Não gosto de...:");

        s_lb_nacionalidade3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_nacionalidade3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_nacionalidade3.setText("Hr. de aula:");

        s_lb_niver3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_niver3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_niver3.setText("Data que entrei:");

        s_lb_nome3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_nome3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_nome3.setText("Instituição de ensino:");

        s_lb_naturalidade5.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_naturalidade5.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_naturalidade5.setText("Curso:");

        lb_curso.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_curso.setForeground(new java.awt.Color(255, 0, 0));
        lb_curso.setText("curso");

        lb_dataEntradaCurso.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_dataEntradaCurso.setForeground(new java.awt.Color(255, 0, 0));
        lb_dataEntradaCurso.setText("data que entrei");

        lb_instituicaoEnsino.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_instituicaoEnsino.setForeground(new java.awt.Color(255, 0, 0));
        lb_instituicaoEnsino.setText("instituição de ensino");

        lb_horarioAula.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_horarioAula.setForeground(new java.awt.Color(255, 0, 0));
        lb_horarioAula.setText("Das tanta às tanta");

        lb_naoGostoDeInst.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_naoGostoDeInst.setForeground(new java.awt.Color(255, 0, 0));
        lb_naoGostoDeInst.setText("Não gosto");

        lb_horarioIntervalo.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_horarioIntervalo.setForeground(new java.awt.Color(255, 0, 0));
        lb_horarioIntervalo.setText("Das tanta às tanta");

        lb_vouMelhorarEmInst.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_vouMelhorarEmInst.setForeground(new java.awt.Color(255, 0, 0));
        lb_vouMelhorarEmInst.setText("vou melhorar");

        lb_aInstituicaoEh.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_aInstituicaoEh.setForeground(new java.awt.Color(255, 0, 0));
        lb_aInstituicaoEh.setText("sobre empresa");

        s_lb_musica3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_musica3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_musica3.setText("Nas horas vagas...:");

        lb_nasHorasVagas.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_nasHorasVagas.setForeground(new java.awt.Color(255, 0, 0));
        lb_nasHorasVagas.setText("Nas horas vagas");

        s_lb_esporte4.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_esporte4.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_esporte4.setText("Disciplina preferida:");

        lb_disciplinaPreferida.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_disciplinaPreferida.setForeground(new java.awt.Color(255, 0, 0));
        lb_disciplinaPreferida.setText("Disciplina preferida");

        s_lb_naturalidade6.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_naturalidade6.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_naturalidade6.setText("Turma:");

        lb_turma.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_turma.setForeground(new java.awt.Color(255, 0, 0));
        lb_turma.setText("turma");

        s_lb_esporte5.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_esporte5.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_esporte5.setText("Minha turma é...:");

        lb_minhaTurmaEh.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_minhaTurmaEh.setForeground(new java.awt.Color(255, 0, 0));
        lb_minhaTurmaEh.setText("sobre minha turma");

        lb_esporte5.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_esporte5.setForeground(new java.awt.Color(0, 0, 204));
        lb_esporte5.setText("Pior disciplina:");

        lb_piorDisciplina.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_piorDisciplina.setForeground(new java.awt.Color(255, 0, 0));
        lb_piorDisciplina.setText("Pior disciplina");

        s_lb_quem_sou4.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_quem_sou4.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_quem_sou4.setText("Termino em:");

        lb_dataTerminoCurso.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_dataTerminoCurso.setForeground(new java.awt.Color(255, 0, 0));
        lb_dataTerminoCurso.setText("termino ");

        s_lb_adoro4.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_adoro4.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_adoro4.setText("Sempre gosto de...:");

        lb_sempreGostoDeInst.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_sempreGostoDeInst.setForeground(new java.awt.Color(255, 0, 0));
        lb_sempreGostoDeInst.setText("sempre gosto");

        s_lb_filme4.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_filme4.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_filme4.setText("Esta faltando...:");

        lb_estaFaltandoInst.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_estaFaltandoInst.setForeground(new java.awt.Color(255, 0, 0));
        lb_estaFaltandoInst.setText("esta faltando");

        javax.swing.GroupLayout jp_educacionalLayout = new javax.swing.GroupLayout(jp_educacional);
        jp_educacional.setLayout(jp_educacionalLayout);
        jp_educacionalLayout.setHorizontalGroup(
            jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_educacionalLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_educacionalLayout.createSequentialGroup()
                        .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_lb_nacionalidade3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_lb_niver3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_lb_naturalidade5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_lb_naturalidade6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_lb_odeio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_lb_musica3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_lb_adoro3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_lb_filme3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_lb_esporte4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_lb_adoro4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_lb_filme4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(s_lb_nome3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jp_educacionalLayout.createSequentialGroup()
                        .addComponent(s_lb_esporte5)
                        .addGap(24, 24, 24)))
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_minhaTurmaEh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_instituicaoEnsino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_sempreGostoDeInst, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_educacionalLayout.createSequentialGroup()
                        .addComponent(lb_dataEntradaCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(s_lb_quem_sou4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_dataTerminoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_estaFaltandoInst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_naoGostoDeInst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jp_educacionalLayout.createSequentialGroup()
                        .addComponent(lb_horarioAula, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(s_lb_quem_sou3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_horarioIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_educacionalLayout.createSequentialGroup()
                        .addComponent(lb_disciplinaPreferida, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_esporte5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_piorDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_turma, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_aInstituicaoEh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_nasHorasVagas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_vouMelhorarEmInst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(97, 97, 97))
        );
        jp_educacionalLayout.setVerticalGroup(
            jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_educacionalLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_instituicaoEnsino, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s_lb_nome3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_niver3)
                    .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_dataEntradaCurso)
                        .addComponent(s_lb_quem_sou4)
                        .addComponent(lb_dataTerminoCurso)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_naturalidade5)
                    .addComponent(lb_curso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_turma)
                    .addComponent(s_lb_naturalidade6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_nacionalidade3)
                    .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_horarioAula)
                        .addComponent(s_lb_quem_sou3)
                        .addComponent(lb_horarioIntervalo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_adoro3)
                    .addComponent(lb_naoGostoDeInst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_adoro4)
                    .addComponent(lb_sempreGostoDeInst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_odeio3)
                    .addComponent(lb_vouMelhorarEmInst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_filme3)
                    .addComponent(lb_aInstituicaoEh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_filme4)
                    .addComponent(lb_estaFaltandoInst))
                .addGap(8, 8, 8)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lb_nasHorasVagas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_lb_musica3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_esporte4)
                    .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_disciplinaPreferida, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_piorDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_esporte5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_educacionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s_lb_esporte5)
                    .addComponent(lb_minhaTurmaEh, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtp_painelTabuladoAmigo.addTab("", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/informacao-educacional.png")), jp_educacional); // NOI18N

        jp_profissional.setBackground(new java.awt.Color(255, 255, 255));
        jp_profissional.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jp_profissionalComponentShown(evt);
            }
        });

        s_lb_odeio2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_odeio2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_odeio2.setText("Vou melhorar em...:");

        s_lb_filme2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_filme2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_filme2.setText("A empresa é...:");

        s_lb_quem_sou2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_quem_sou2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_quem_sou2.setText("Atividade:");

        s_lb_adoro2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_adoro2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_adoro2.setText("Não gosto de...:");

        s_lb_fisico2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_fisico2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_fisico2.setText("Hr. refeição:");

        s_lb_nacionalidade2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_nacionalidade2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_nacionalidade2.setText("Hr. serviço:");

        s_lb_niver2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_niver2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_niver2.setText("Dt. admis.:");

        s_lb_nome2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_nome2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_nome2.setText("Empresa:");

        s_lb_naturalidade3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_naturalidade3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_naturalidade3.setText("Cargo:");

        lb_cargo.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_cargo.setForeground(new java.awt.Color(255, 0, 0));
        lb_cargo.setText("cargo");

        lb_dataAdmissao.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_dataAdmissao.setForeground(new java.awt.Color(255, 0, 0));
        lb_dataAdmissao.setText("Data adm.");

        lb_nomeEmpresa.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_nomeEmpresa.setForeground(new java.awt.Color(255, 0, 0));
        lb_nomeEmpresa.setText("nome empresa");

        lb_horarioServico.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_horarioServico.setForeground(new java.awt.Color(255, 0, 0));
        lb_horarioServico.setText("Das tanta às tanta");

        lb_horarioRefeicao.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_horarioRefeicao.setForeground(new java.awt.Color(255, 0, 0));
        lb_horarioRefeicao.setText("Das tanta às tanta");

        lb_naoGostoDeEmp.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_naoGostoDeEmp.setForeground(new java.awt.Color(255, 0, 0));
        lb_naoGostoDeEmp.setText("não gosto");

        lb_atividadeEmp.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_atividadeEmp.setForeground(new java.awt.Color(255, 0, 0));
        lb_atividadeEmp.setText("atividade");

        lb_vouMelhorarEmEmp.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_vouMelhorarEmEmp.setForeground(new java.awt.Color(255, 0, 0));
        lb_vouMelhorarEmEmp.setText("melhorar");

        lb_aEmpresaEh.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_aEmpresaEh.setForeground(new java.awt.Color(255, 0, 0));
        lb_aEmpresaEh.setText("sobre empresa");

        s_lb_musica2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_musica2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_musica2.setText("Está faltando só...:");

        lb_soEstaFaltandoEmp.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_soEstaFaltandoEmp.setForeground(new java.awt.Color(255, 0, 0));
        lb_soEstaFaltandoEmp.setText("fatando");

        s_lb_esporte2.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_esporte2.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_esporte2.setText("E-mail (corp.):");

        lb_emailCorpEmp.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_emailCorpEmp.setForeground(new java.awt.Color(255, 0, 0));
        lb_emailCorpEmp.setText("email");

        s_lb_naturalidade4.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_naturalidade4.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_naturalidade4.setText("Setor:");

        lb_setor.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_setor.setForeground(new java.awt.Color(255, 0, 0));
        lb_setor.setText("setor");

        s_lb_esporte3.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_esporte3.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_esporte3.setText("Fone:");

        lb_foneEmp.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_foneEmp.setForeground(new java.awt.Color(255, 0, 0));
        lb_foneEmp.setText("fone");

        lb_esporte4.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_esporte4.setForeground(new java.awt.Color(0, 0, 204));
        lb_esporte4.setText("Ramal:");

        lb_ramalEmp.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_ramalEmp.setForeground(new java.awt.Color(255, 0, 0));
        lb_ramalEmp.setText("ramal");

        lb_esporte6.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_esporte6.setForeground(new java.awt.Color(0, 0, 204));
        lb_esporte6.setText("Fax:");

        lb_faxEmp.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_faxEmp.setForeground(new java.awt.Color(255, 0, 0));
        lb_faxEmp.setText("fax");

        s_lb_adoro5.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        s_lb_adoro5.setForeground(new java.awt.Color(0, 0, 204));
        s_lb_adoro5.setText("Sempre gosto de...:");

        lb_sempreGostoDeEmp.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
        lb_sempreGostoDeEmp.setForeground(new java.awt.Color(255, 0, 0));
        lb_sempreGostoDeEmp.setText("sempre gosto de");

        javax.swing.GroupLayout jp_profissionalLayout = new javax.swing.GroupLayout(jp_profissional);
        jp_profissional.setLayout(jp_profissionalLayout);
        jp_profissionalLayout.setHorizontalGroup(
            jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_profissionalLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(s_lb_odeio2)
                        .addComponent(s_lb_filme2)
                        .addComponent(s_lb_quem_sou2)
                        .addComponent(s_lb_adoro2)
                        .addComponent(s_lb_musica2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(s_lb_esporte2)
                        .addComponent(s_lb_esporte3)
                        .addComponent(s_lb_adoro5))
                    .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(s_lb_nacionalidade2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(s_lb_niver2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(s_lb_naturalidade3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(s_lb_naturalidade4))
                    .addGroup(jp_profissionalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(s_lb_nome2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_aEmpresaEh, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(lb_vouMelhorarEmEmp)
                    .addComponent(lb_atividadeEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(lb_naoGostoDeEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_nomeEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(lb_dataAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_sempreGostoDeEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_profissionalLayout.createSequentialGroup()
                        .addComponent(lb_horarioServico, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(s_lb_fisico2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_horarioRefeicao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_profissionalLayout.createSequentialGroup()
                        .addComponent(lb_foneEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lb_esporte4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_ramalEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_esporte6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_faxEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_emailCorpEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(lb_setor, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_soEstaFaltandoEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(lb_cargo, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );
        jp_profissionalLayout.setVerticalGroup(
            jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_profissionalLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_nomeEmpresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s_lb_nome2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_dataAdmissao)
                    .addComponent(s_lb_niver2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_naturalidade3)
                    .addComponent(lb_cargo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_lb_naturalidade4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_setor, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_horarioServico)
                        .addComponent(s_lb_fisico2)
                        .addComponent(lb_horarioRefeicao))
                    .addComponent(s_lb_nacionalidade2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_quem_sou2)
                    .addComponent(lb_atividadeEmp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_adoro2)
                    .addComponent(lb_naoGostoDeEmp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_adoro5)
                    .addComponent(lb_sempreGostoDeEmp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_odeio2)
                    .addComponent(lb_vouMelhorarEmEmp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_filme2)
                    .addComponent(lb_aEmpresaEh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_musica2)
                    .addComponent(lb_soEstaFaltandoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_esporte2)
                    .addComponent(lb_emailCorpEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(s_lb_esporte3)
                    .addComponent(lb_foneEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_esporte4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_profissionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_ramalEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_faxEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_esporte6, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtp_painelTabuladoAmigo.addTab("", new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/informacao-profissional.png")), jp_profissional); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtp_painelTabuladoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jtp_painelTabuladoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 619, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtp_painelTabuladoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtp_painelTabuladoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lb_nomeIconeHumorUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_nomeIconeHumorUsuarioMouseEntered
        //fonte em negrito quando o mouse pasa por cima
        lb_nomeIconeHumorUsuario.setFont(new java.awt.Font("Tahoma", 1, 25));

}//GEN-LAST:event_lb_nomeIconeHumorUsuarioMouseEntered

    private void lb_nomeIconeHumorUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_nomeIconeHumorUsuarioMouseExited
        //fonte normal quando o mouse sai do componente
        lb_nomeIconeHumorUsuario.setFont(new java.awt.Font("Tahoma", 0, 25));
}//GEN-LAST:event_lb_nomeIconeHumorUsuarioMouseExited

    private void jt_listaAmigosUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaAmigosUsuarioMouseReleased

        //logo carregando...
        new Thread(new LogoCarregando()).start();
        
        //extende o o form para aparecer o painel do amigo
        MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);

        //o clique na tabela pega o moniersn do amigo e guarda na variável STATICA LogUsuario.S_MONIERSN que esta na classe LogUsuario
        int l_codAmigo = SolicitarDados.s_SolicitarDados.pegaCodDoAmigoClicadoNaLista("amigos");

        AtualizarDados.s_AtualizarDados.atualizaLogAmigo(l_codAmigo);
        
            if(LogAmigo.s_status.equals("conectado"))
                SolicitarDados.s_SolicitarDados.definicaoDeUsuarioConectado(LogAmigo.s_cod, LogAmigo.s_host, LogAmigo.s_portaTCP);
        
        //Busca informações no banco do moniersn clicado
        MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codAmigo);

    }//GEN-LAST:event_jt_listaAmigosUsuarioMouseReleased

    private void jt_listaAmigosUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_listaAmigosUsuarioKeyTyped
       
        //logo carregando...
        new Thread(new LogoCarregando()).start();
        
        //extende o o form para aparecer o painel do amigo
        MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);        
        
        //PEGA CODIGO DI AMIGO NA LISTA SELECIONADA
        //o clique na tabela pega o moniersn do amigo e guarda na variável STATICA LogUsuario.S_MONIERSN que esta na classe LogUsuario
        int l_codAmigo = SolicitarDados.s_SolicitarDados.pegaCodDoAmigoClicadoNaLista("amigos");

        AtualizarDados.s_AtualizarDados.atualizaLogAmigo(l_codAmigo);

            if(LogAmigo.s_status.equals("conectado"))
                SolicitarDados.s_SolicitarDados.definicaoDeUsuarioConectado(LogAmigo.s_cod, LogAmigo.s_host, LogAmigo.s_portaTCP);

        //Busca informações no banco do moniersn clicado
        MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codAmigo);

    }//GEN-LAST:event_jt_listaAmigosUsuarioKeyTyped

    private void bt_configuracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_configuracoesActionPerformed

        boolean l_podeEntrar = false;
        
        //CASO SOLICITE SENHA AO ABRIR CONFIGURAÇOES...
        if(LogUsuario.s_solicitarSenhaAoConfig.equals("S")){
            
            s_telaMsn.setVisible(false);
            l_podeEntrar = MostrarDados.s_MostrarDados.mostraConfirmacaoSenha("config");

        }else
            l_podeEntrar = true;

        
            if(l_podeEntrar){

                //ESTABILIZADOR DE CONECTADOS
                System.out.println("Thread de carregar configurações iniciada");
                new Thread(new MostrarConfiguracoes()).start();
                
                //mostra tela carregando
                Aguardando.s_telaAguardando.setVisible(true); 

            }
            else{
                if(LogUsuario.s_solicitarSenhaAoConfig.equals("S"))
                    s_telaMsn.setVisible(true);

            }
}//GEN-LAST:event_bt_configuracoesActionPerformed

    private void bt_desconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_desconectarActionPerformed
        LogMoniersn.s_LogMoniersn.desconectar(1);
    }//GEN-LAST:event_bt_desconectarActionPerformed

    private void rb_todosRecadosPubRecebidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_todosRecadosPubRecebidosActionPerformed

        LogUsuario.s_qtdRecPubAMostrar = 4;
        tf_conteudoRecPubPesquisado.setText(""); 
                
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPubUsuario, "rec");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPubUsuario);
}//GEN-LAST:event_rb_todosRecadosPubRecebidosActionPerformed

    private void rb_recadosPubEnviadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_recadosPubEnviadosActionPerformed

        LogUsuario.s_qtdRecPubAMostrar = 4;
        tf_conteudoRecPubPesquisado.setText("");        
        
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPubUsuario, "env");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPubUsuario);
    }//GEN-LAST:event_rb_recadosPubEnviadosActionPerformed

    private void bt_enviarRecPrivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_enviarRecPrivActionPerformed
        GravarDados.s_GravarDados.enviarRecado(tf_recadoPriv);
}//GEN-LAST:event_bt_enviarRecPrivActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        s_telaMsn.setVisible(false);
        MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
        MostrarDados.s_MostrarDados.mostraAvisoProcedimento(Avisos.FACECARD_FULL, Avisos.AVISO_EXECUCAO_SYSTRAY, "NADA");

    }//GEN-LAST:event_formWindowClosing

    private void bt_batePapoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_batePapoActionPerformed
        
        //seleciona a guia de bate papo
        jtp_painelTabuladoUsuario.setSelectedComponent(jp_batePapo);
        
        //icone do amigo no bate bapo
        lb_nomeIconeAmigoBatePapo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+LogAmigo.s_iconeHumor+".png")));

        //apelido do amigo no bate papo
        lb_nomeIconeAmigoBatePapo.setText(LogAmigo.s_moniersn);

        //seleciona o apelido na lista do bate papo
        SolicitarDados.s_SolicitarDados.selecionaApelidoListaBatePapo(LogAmigo.s_moniersn);

        //habilita botao de bloqueio do amigo
        bt_visuPainelAmigo.setEnabled(true);

        //tira seleção do check [enviar para todos]
        cb_enviarParaTodosConectados.setSelected(false);

        //poe o foco no campo para escrever o recado
        tf_recadoInst.requestFocus();

        //esconde o painel do amigo
        MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);

        //preenche a lista de amigos
        MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

    }//GEN-LAST:event_bt_batePapoActionPerformed

    private void bt_visuPainelAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_visuPainelAmigoActionPerformed
        //Busca informações no banco do amigo selecionado na lisa do batepapo
        MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod);
}//GEN-LAST:event_bt_visuPainelAmigoActionPerformed

    private void tf_recadoInstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_recadoInstActionPerformed

        if(!tf_recadoInst.getText().isEmpty()){  

            if(cb_enviarParaTodosConectados.isSelected())
                ClienteUDP.enviarRecadoInstantaneo("varios", tf_recadoInst.getText());
            else
                ClienteUDP.enviarRecadoInstantaneo("unico", tf_recadoInst.getText());
        }
}//GEN-LAST:event_tf_recadoInstActionPerformed

    private void bt_enviarRecInstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_enviarRecInstActionPerformed

        if(!tf_recadoInst.getText().isEmpty()){

            if(cb_enviarParaTodosConectados.isSelected())
                ClienteUDP.enviarRecadoInstantaneo("varios", tf_recadoInst.getText());
            else
                ClienteUDP.enviarRecadoInstantaneo("unico", tf_recadoInst.getText());

        }
}//GEN-LAST:event_bt_enviarRecInstActionPerformed

    private void bt_bloqDesblocAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_bloqDesblocAmigoActionPerformed
        AtualizarDados.s_AtualizarDados.bloqueiaDesbloqueiaAmigo();
    }//GEN-LAST:event_bt_bloqDesblocAmigoActionPerformed

    private void bt_escondePainelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_escondePainelActionPerformed
        //seta o tamanho do painel do painel
        MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
        MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);
    }//GEN-LAST:event_bt_escondePainelActionPerformed

    private void bt_limparConversaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limparConversaActionPerformed
       boolean l_limpar = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_REMOVER_CONV_BP, Avisos.REMOVER_CONV_BP, Icones.REMOVER_CONVERSAÇÃO_BP);

       if(l_limpar){
            tp_recadosInstantaneos.setText(Avisos.INFORMACAO_BATE_PAPO);
            tf_recadoInst.requestFocus();
       }
        else{
           tf_recadoInst.requestFocus();
           return;
        }
    }//GEN-LAST:event_bt_limparConversaActionPerformed

    private void bt_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_inicioActionPerformed
        jtp_painelTabuladoUsuario.setSelectedComponent(jp_inicioUsuario); 
    }//GEN-LAST:event_bt_inicioActionPerformed

    private void bt_enviarRecPubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_enviarRecPubActionPerformed
        GravarDados.s_GravarDados.enviarRecado(tf_recadoPub);

        bt_comentarRecPubAmigo.setEnabled(false);

    }//GEN-LAST:event_bt_enviarRecPubActionPerformed

    private void rb_qtdTotalAmigosAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_qtdTotalAmigosAmigoActionPerformed
        LogAmigo.s_qtdUsuMostrar = 4;
        tf_pesquisaListaAmigo.setText(""); 
        
        //preenche a lista dde amigos dos meus amigos
        MostrarDados.s_MostrarDados.preencherListaAmigosDoAmigo(LogAmigo.s_cod, Avisos.TEXTO_AMIGOS);

    }//GEN-LAST:event_rb_qtdTotalAmigosAmigoActionPerformed

    private void rb_qtdAmigosComunsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_qtdAmigosComunsActionPerformed
        LogAmigo.s_qtdUsuMostrar = 4;
        tf_pesquisaListaAmigo.setText(""); 
        
        //preenche a lista dde amigos dos meus amigos
        MostrarDados.s_MostrarDados.preencherListaAmigosDoAmigo(LogAmigo.s_cod, Avisos.TEXTO_COMUNS);

    }//GEN-LAST:event_rb_qtdAmigosComunsActionPerformed

    private void rb_ocoRecebidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_ocoRecebidasActionPerformed
        LogUsuario.s_qtdOcoAMostrar = 4;
        tf_conteudoOcoPesquisado.setText("");
        
        //preenche lista de ocorrências enviadas
        MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, jt_listaOcorrenciaUsuario, "rec");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaOcorrenciaUsuario);
    }//GEN-LAST:event_rb_ocoRecebidasActionPerformed

    private void rb_ocoEnviadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_ocoEnviadasActionPerformed
        LogUsuario.s_qtdOcoAMostrar = 4;
        tf_conteudoOcoPesquisado.setText("");
        
        //preenche lista de ocorrênciaas recebidas
        MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, jt_listaOcorrenciaUsuario, "env");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaOcorrenciaUsuario);
    }//GEN-LAST:event_rb_ocoEnviadasActionPerformed

    private void rb_recadosPubNaoLidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_recadosPubNaoLidosActionPerformed

        LogUsuario.s_qtdRecPubAMostrar = 4;
        tf_conteudoRecPubPesquisado.setText("");
        
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPubUsuario, "naoLido");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPubUsuario);
    }//GEN-LAST:event_rb_recadosPubNaoLidosActionPerformed

    private void rb_todosRecadosPrivRecebidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_todosRecadosPrivRecebidosActionPerformed

        LogUsuario.s_qtdRecPrivAMostrar = 4;
        tf_conteudoRecPrivPesquisado.setText(""); 
        
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPriv, "rec");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPriv);
    }//GEN-LAST:event_rb_todosRecadosPrivRecebidosActionPerformed

    private void rb_recadosPrivNaoLidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_recadosPrivNaoLidosActionPerformed

        LogUsuario.s_qtdRecPrivAMostrar = 4;
        tf_conteudoRecPrivPesquisado.setText(""); 
        
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPriv, "naoLido");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPriv);
    }//GEN-LAST:event_rb_recadosPrivNaoLidosActionPerformed

    private void rb_recadosPrivEnviadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_recadosPrivEnviadosActionPerformed

        LogUsuario.s_qtdRecPrivAMostrar = 4;
        tf_conteudoRecPrivPesquisado.setText("");         
        
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPriv, "env");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPriv);
    }//GEN-LAST:event_rb_recadosPrivEnviadosActionPerformed

    private void tf_recadoPrivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_recadoPrivActionPerformed
        GravarDados.s_GravarDados.enviarRecado(tf_recadoPriv);
    }//GEN-LAST:event_tf_recadoPrivActionPerformed

    private void tf_recadoPubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_recadoPubActionPerformed
        GravarDados.s_GravarDados.enviarRecado(tf_recadoPub);

        bt_comentarRecPubAmigo.setEnabled(false);
    }//GEN-LAST:event_tf_recadoPubActionPerformed

    private void bt_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atualizarActionPerformed
        
            //atualiza informações gerais
            new Thread(new LogoCarregando()).start();
                
            LogUsuario.s_qtdUsuBPMostrar = 4;
            Moniersn.tf_pesquisaUsuarioBatePapo.setText(""); 
            
            LogUsuario.s_qtdUsuMostrar = 4;
            Moniersn.tf_pesquisaUsuario.setText("");
            
            LogUsuario.s_qtdOcoAMostrar = 4;
            Moniersn.tf_conteudoOcoPesquisado.setText("");

            LogUsuario.s_qtdAtuAMostrar = 4;
            Moniersn.tf_conteudoAtuPesquisado.setText("");

            LogUsuario.s_qtdRecPubAMostrar = 4;
            Moniersn.tf_conteudoRecPubPesquisado.setText("");

            LogUsuario.s_qtdRecPrivAMostrar = 4;
            Moniersn.tf_conteudoRecPrivPesquisado.setText("");

            LogUsuario.s_qtdArqAMostrar = 4;
            Moniersn.tf_nomeArqPesquisado.setText("");  
            
            Moniersn.cb_tipoAmigos.setSelectedItem(Avisos.TEXTO_TODOS_GERAL);
                
        try{

            //CONTAGEM DE REC. PUB/PRIV/OCORRENCIAS/ATU/ARQUIVOS
            SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

            //PREENCHER LISTA DE RECADOS PRIVADOS DO USUÁRIO
            MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPriv, LogUsuario.s_listaRecPrivSelecionada);

            //PREENCHER LISTA DE RECADOS PÚBLICOS DO USUÁRIO
            MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPubUsuario, LogUsuario.s_listaRecPubSelecionada);

            //PREENCHER LISTA DE OCORRÊNCIAS DO USUÁRIO
            MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, Moniersn.jt_listaOcorrenciaUsuario, LogUsuario.s_listaOcorrenciaSelecionada);

            //ULTIMA OCORRÊNCIA
            MostrarDados.s_MostrarDados.setaUltimaOcorrencia(LogUsuario.s_cod, "usuario");

            //PREENCHER LISTA DE ATUALIZAÇÕES DO USUÁRIO
            MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, Moniersn.jt_listaAtualizacoesUsuario, LogUsuario.s_listaAtualizacoesSelecionada);

            //PREENCHER LISTA DE ATUALIZAÇÕES DO USUÁRIO
            MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, Moniersn.jt_listaArquivosUsuario, LogUsuario.s_listaArquivoSelecionada);

            //HABILITAÇÃO DE BOTÕES DE CONTEÚDO DA LISTA DE OCORRECIAS
            MostrarDados.s_MostrarDados.habilitaBotoesConteudo(Moniersn.jt_listaOcorrenciaUsuario);

            //HABILITAÇÃO DE BOTÕES DE CONTEÚDO DA LISTA DE ATUALIZAÇÕES DO USUÁRIO
            MostrarDados.s_MostrarDados.habilitaBotoesConteudo(Moniersn.jt_listaAtualizacoesUsuario);

            //HABILITAÇÃO DE BOTÕES DE CONTEÚDO DA LISTA DE RECADOS PÚBLICOS
            MostrarDados.s_MostrarDados.habilitaBotoesConteudo(Moniersn.jt_listaRecadosPubUsuario);

            //HABILITAÇÃO DE BOTÕES DE CONTEÚDO DA LISTA DE RECADOS PRIVADOS
            MostrarDados.s_MostrarDados.habilitaBotoesConteudo(Moniersn.jt_listaRecadosPriv);

             //FECHA O PAINEL DO AMIGO
            MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);

             //MOSTRA BALAO COM INFORMAÇÕES ATUAIS
            String l_infoAtual = SolicitarDados.s_SolicitarDados.pegaInformacoesAtuais();
            MostrarDados.s_MostrarDados.mostraAvisoProcedimento(Avisos.FACECARD_FULL, l_infoAtual, "INFO");

            //verifica se os servidores estão ok
            SolicitarDados.s_SolicitarDados.estabilizaServidores(1);

        
        }catch(Exception e){
            
            System.err.println("Ocorreu um erro ao tentar atualizar informações.    \nErro: "+e);
            
            //esconde tela carregando
            Aguardando.s_telaAguardando.setVisible(false); 
            
            Moniersn.s_telaMsn.setVisible(true);

            MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
            
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ERRO_AO_VISU_INFO_USU, "erro");
        
        }
        
    }//GEN-LAST:event_bt_atualizarActionPerformed

    private void jt_listaAmigosConectadosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaAmigosConectadosMouseReleased
        //guarda o codigo do usuario clicado na tabela (bate papo)
        int l_codAmigo = SolicitarDados.s_SolicitarDados.pegaCodDoAmigoClicadoNaLista("bate-papo");

        //utilizao o codigo para atualizar os dados do amigo
        AtualizarDados.s_AtualizarDados.atualizaLogAmigo(l_codAmigo);

        //seta apelido na tela de bate papo
        lb_nomeIconeAmigoBatePapo.setText(LogAmigo.s_moniersn);

        //icone do amigo no bate bapo
        lb_nomeIconeAmigoBatePapo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+LogAmigo.s_iconeHumor+".png")));

        //desmarca o check [enviar para todos amigos]
        cb_enviarParaTodosConectados.setSelected(false);

        //habilita o botao de bloqueio
        bt_visuPainelAmigo.setEnabled(true);

        //manda o foco para o campo recado isnt
        tf_recadoInst.requestFocus();
    }//GEN-LAST:event_jt_listaAmigosConectadosMouseReleased

    private void cb_enviarParaTodosConectadosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_enviarParaTodosConectadosMouseReleased
        
        //ao selecionar o check [enviar para todos conectados]...

        String l_iconePraQuem = null;
        String l_quem = null;



        //ao clicar nao deixa disselecionar...
        cb_enviarParaTodosConectados.setSelected(true);

            if(LogUsuario.s_quemBatePapoComigo.equals("todos")){
                l_iconePraQuem = Icones.TODOS_BATE_PAPO_16;
                l_quem = Avisos.TEXTO_TODOS_CONECTADOS;
            }else if(LogUsuario.s_quemBatePapoComigo.equals("amigos")){
                l_iconePraQuem = Icones.AMIGO_BATE_PAPO_16;
                l_quem = Avisos.TEXTO_AMIGOS_CONECTADOS;
            }else{
                l_iconePraQuem = Icones.NINGUEM_BATE_PAPO_COMIGO_16;
                l_quem = Avisos.TEXTO_NINGUEM;
            }

        LogUsuario.s_qtdUsuBPMostrar = 4;
        tf_pesquisaUsuarioBatePapo.setText("");

        //set o icone especificando qe est aenviando para todos e texto tbm
        lb_nomeIconeAmigoBatePapo.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_iconePraQuem)));
        lb_nomeIconeAmigoBatePapo.setText(l_quem);

            if(!LogUsuario.s_quemBatePapoComigo.equals("ninguem"))
                //preenche a lista de amigos do bate papo
                MostrarDados.s_MostrarDados.preencherListaDeAmigosBatePapo();

        //desabilita o botao de bloqueio de amigo
        bt_visuPainelAmigo.setEnabled(false);

        //seleciona a guia bate papo
        jtp_painelTabuladoUsuario.setSelectedComponent(jp_batePapo);

        //foco para escrita
        tf_recadoInst.requestFocus();
    }//GEN-LAST:event_cb_enviarParaTodosConectadosMouseReleased

    private void bt_limpaCampoRecInstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpaCampoRecInstActionPerformed
        tf_recadoInst.setText("");
        tf_recadoInst.requestFocus();

    }//GEN-LAST:event_bt_limpaCampoRecInstActionPerformed

    private void jp_inicioUsuarioComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_inicioUsuarioComponentShown
        //a guia atual eh inicio usuario
        LogUsuario.s_abaAtualSelecionada = jp_inicioUsuario;

        //seta titulo nome+sobrenome/inicio
//        this.setTitle(LogUsuario.s_nome+" "+LogUsuario.s_sobrenome+" | INÍCIO - "+Avisos.FACECARD_FULL);
    }//GEN-LAST:event_jp_inicioUsuarioComponentShown

    private void jp_ocorrenciaUsuarioComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_ocorrenciaUsuarioComponentShown

        jtp_painelTabuladoUsuario.setTitleAt(1, "(0)");
        //zera a qtd de rec. inst recebidos
        LogUsuario.s_qtdOcoRecRecente = 0;

        AreaNotificacao.atualizaTipTextTrayIcon();

        //a guia atual eh ocorrencias usuario
        LogUsuario.s_abaAtualSelecionada = jp_ocorrenciaUsuario;

        //SETA TITULO
        MostrarDados.s_MostrarDados.setaTituloFrame("OCORRÊNCIAS", "moniersn");

    }//GEN-LAST:event_jp_ocorrenciaUsuarioComponentShown

    private void jp_recPublicosUsuarioComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_recPublicosUsuarioComponentShown
        //a guia atual eh recadosPublicos usuario
        LogUsuario.s_abaAtualSelecionada = jp_recPublicosUsuario;

        //SETA TITULO
        MostrarDados.s_MostrarDados.setaTituloFrame("RECADOS PÚBLICOS", "moniersn");
    }//GEN-LAST:event_jp_recPublicosUsuarioComponentShown

    private void jp_recPrivadosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_recPrivadosComponentShown
        //a guia atual eh recados Privados
        LogUsuario.s_abaAtualSelecionada = jp_recPrivados;

        //SETA TITULO
        MostrarDados.s_MostrarDados.setaTituloFrame("RECADOS PRIVADOS", "moniersn");
    }//GEN-LAST:event_jp_recPrivadosComponentShown

    private void jp_atualizacoesUsuarioComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_atualizacoesUsuarioComponentShown
        jtp_painelTabuladoUsuario.setTitleAt(2, "(0)");
        //zera a qtd de rec. inst recebidos
        LogUsuario.s_qtdTotAtuRecRecente = 0;
        LogUsuario.s_qtdAtuRecDesdUltLogoutDeEstranhos = 0;
        LogUsuario.s_qtdAtuRecDesdUltLogoutDeAmigos = 0;

        LogUsuario.s_qtdAtuRecRecenteDeAmigos = 0;
        LogUsuario.s_qtdAtuRecRecenteDeEstranhos = 0;

        AreaNotificacao.atualizaTipTextTrayIcon();

        //a guia atual eh atualizacoes usuario
        LogUsuario.s_abaAtualSelecionada = jp_atualizacoesUsuario;

        //SETA TITULO
        MostrarDados.s_MostrarDados.setaTituloFrame("ATUALIZAÇÕES", "moniersn");
    }//GEN-LAST:event_jp_atualizacoesUsuarioComponentShown

    private void jp_batePapoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_batePapoComponentShown

        //a guia atual eh bate papo
        LogUsuario.s_abaAtualSelecionada = jp_batePapo;

        //SETA TITULO
        MostrarDados.s_MostrarDados.setaTituloFrame("BATE PAPO", "moniersn");

        //Ao selecionar a aba o label [qtdRecIns] da tela principal fica invisivel
        lb_qtdRecInst.setVisible(false);

        //zera a qtd de rec. inst recebidos
        LogUsuario.s_qtdRecInstRec = 0;

        //Atualiza info no tray icon
        AreaNotificacao.atualizaTipTextTrayIcon();



    }//GEN-LAST:event_jp_batePapoComponentShown

    private void jp_inicioAmigoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_inicioAmigoComponentShown
        //a guia atual eh inicio amigo
        LogAmigo.s_abaAtualSelecionada = jp_inicioAmigo;
    }//GEN-LAST:event_jp_inicioAmigoComponentShown

    private void jp_ocorrenciaAmigoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_ocorrenciaAmigoComponentShown
        //a guia atual eh inicio amigo
        LogAmigo.s_abaAtualSelecionada = jp_ocorrenciaAmigo;
    }//GEN-LAST:event_jp_ocorrenciaAmigoComponentShown

    private void jp_pessoalComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_pessoalComponentShown
        //a guia atual eh pessoal amigo
        LogAmigo.s_abaAtualSelecionada = jp_pessoal;

        MostrarDados.s_MostrarDados.carregaInfoPessoal(LogAmigo.s_cod, "pessoal-amigo");
    }//GEN-LAST:event_jp_pessoalComponentShown

    private void jp_contatoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_contatoComponentShown
        //a guia atual eh contato amigo
        LogAmigo.s_abaAtualSelecionada = jp_contato;

        MostrarDados.s_MostrarDados.carregaInfoContato(LogAmigo.s_cod, "contato-amigo");
    }//GEN-LAST:event_jp_contatoComponentShown

    private void jp_educacionalComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_educacionalComponentShown
        //a guia atual eh educacional amigo
        LogAmigo.s_abaAtualSelecionada = jp_educacional;

        MostrarDados.s_MostrarDados.carregaInfoEducacional(LogAmigo.s_cod, "educ-amigo");
    }//GEN-LAST:event_jp_educacionalComponentShown

    private void jp_profissionalComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_profissionalComponentShown
        //a guia atual eh profissional amigo
        LogAmigo.s_abaAtualSelecionada = jp_profissional;

        MostrarDados.s_MostrarDados.carregaInfoProfissional(LogAmigo.s_cod, "prof-amigo");
    }//GEN-LAST:event_jp_profissionalComponentShown

    private void jp_recPublicosAmigoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_recPublicosAmigoComponentShown
        //a guia atual eh profissional amigo
        LogAmigo.s_abaAtualSelecionada = jp_recPublicosAmigo;

    }//GEN-LAST:event_jp_recPublicosAmigoComponentShown

    private void jp_atualizacoesAmigoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_atualizacoesAmigoComponentShown
        //a guia atual eh profissional amigo
        LogAmigo.s_abaAtualSelecionada = jp_atualizacoesAmigo;
    }//GEN-LAST:event_jp_atualizacoesAmigoComponentShown

    private void bt_comentarOcorrenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_comentarOcorrenciaActionPerformed
        int l_linha =  jt_listaOcorrenciaUsuario.getSelectedRow();

        int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaOcorrenciaUsuario);
        
        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "ocorrencia", jt_listaOcorrenciaUsuario);
    }//GEN-LAST:event_bt_comentarOcorrenciaActionPerformed

    private void jt_listaOcorrenciaUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaOcorrenciaUsuarioMouseReleased
        //DECIDE SE USUÁRIO PODERÁ APAGAR RECADO OU NAO
        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaOcorrenciaUsuario);
    }//GEN-LAST:event_jt_listaOcorrenciaUsuarioMouseReleased

    private void jt_listaRecadosPubUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaRecadosPubUsuarioMouseReleased
        //caso mais de uma linha selecionada (inabilita botoes de comentrar e marcar como lido)
        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPubUsuario);
    }//GEN-LAST:event_jt_listaRecadosPubUsuarioMouseReleased

    private void jt_listaRecadosPrivMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaRecadosPrivMouseReleased
        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPriv);
    }//GEN-LAST:event_jt_listaRecadosPrivMouseReleased

    private void jt_listaRecadosPubAmigoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaRecadosPubAmigoMouseReleased
        //HABILITA O BOTAP DE COMENTAR
        bt_comentarRecPubAmigo.setEnabled(true);
    }//GEN-LAST:event_jt_listaRecadosPubAmigoMouseReleased

    private void jt_listaOcorrenciaAmigoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaOcorrenciaAmigoMouseReleased
        bt_comentarOcorrenciaAmigo.setEnabled(true);
    }//GEN-LAST:event_jt_listaOcorrenciaAmigoMouseReleased

    private void rb_atuParaTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_atuParaTodosActionPerformed
        LogUsuario.s_qtdAtuAMostrar = 4;
        tf_conteudoAtuPesquisado.setText(""); 
        
        MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, jt_listaAtualizacoesUsuario, "recEstranhos");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaAtualizacoesUsuario);

    }//GEN-LAST:event_rb_atuParaTodosActionPerformed

    private void rb_atuDeAmigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_atuDeAmigosActionPerformed
        LogUsuario.s_qtdAtuAMostrar = 4;
        tf_conteudoAtuPesquisado.setText(""); 
        
        MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, jt_listaAtualizacoesUsuario, "recAmigos");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaAtualizacoesUsuario);
    }//GEN-LAST:event_rb_atuDeAmigosActionPerformed

    private void jt_listaAtualizacoesUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaAtualizacoesUsuarioMouseReleased
        //DECIDE SE USUÁRIO PODERÁ APAGAR ocorrencia(ATU)
        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaAtualizacoesUsuario);

    }//GEN-LAST:event_jt_listaAtualizacoesUsuarioMouseReleased

    private void jt_listaAtualizacoesAmigoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaAtualizacoesAmigoMouseReleased
        bt_comentarAtuAmigo.setEnabled(true);
    }//GEN-LAST:event_jt_listaAtualizacoesAmigoMouseReleased

    private void bt_excluirOcorrenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirOcorrenciaActionPerformed
        AtualizarDados.s_AtualizarDados.removerConteudo(jt_listaOcorrenciaUsuario);
    }//GEN-LAST:event_bt_excluirOcorrenciaActionPerformed

    private void bt_excluirAtualizacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirAtualizacoesActionPerformed
        AtualizarDados.s_AtualizarDados.removerConteudo(jt_listaAtualizacoesUsuario);
    }//GEN-LAST:event_bt_excluirAtualizacoesActionPerformed

    private void bt_excluirRecPubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirRecPubActionPerformed
        AtualizarDados.s_AtualizarDados.removerConteudo(jt_listaRecadosPubUsuario);
    }//GEN-LAST:event_bt_excluirRecPubActionPerformed

    private void bt_excluirRecPrivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirRecPrivActionPerformed
        AtualizarDados.s_AtualizarDados.removerConteudo(jt_listaRecadosPriv);
    }//GEN-LAST:event_bt_excluirRecPrivActionPerformed

    private void bt_marcarComoLidoRecPubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_marcarComoLidoRecPubActionPerformed
        AtualizarDados.s_AtualizarDados.marcarRecadoComoLidoNaoLido(jt_listaRecadosPubUsuario);
    }//GEN-LAST:event_bt_marcarComoLidoRecPubActionPerformed

    private void bt_marcarComoLidoRecPrivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_marcarComoLidoRecPrivActionPerformed
        AtualizarDados.s_AtualizarDados.marcarRecadoComoLidoNaoLido(jt_listaRecadosPriv);
    }//GEN-LAST:event_bt_marcarComoLidoRecPrivActionPerformed

    private void jt_listaOcorrenciaUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_listaOcorrenciaUsuarioKeyReleased
        //DECIDE SE USUÁRIO PODERÁ APAGAR RECADO OU NAO
        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaOcorrenciaUsuario);
    }//GEN-LAST:event_jt_listaOcorrenciaUsuarioKeyReleased

    private void jt_listaAtualizacoesUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_listaAtualizacoesUsuarioKeyReleased
        //DECIDE SE USUÁRIO PODERÁ APAGAR ocorrencia(ATU)
        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaAtualizacoesUsuario);
    }//GEN-LAST:event_jt_listaAtualizacoesUsuarioKeyReleased

    private void jt_listaRecadosPubUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_listaRecadosPubUsuarioKeyReleased
        //caso mais de uma linha selecionada (inabilita botoes de comentrar e marcar como lido)
        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPubUsuario);
    }//GEN-LAST:event_jt_listaRecadosPubUsuarioKeyReleased

    private void jt_listaRecadosPrivKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_listaRecadosPrivKeyReleased
        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPriv);
    }//GEN-LAST:event_jt_listaRecadosPrivKeyReleased

    private void lb_mostrarNaoMostrarNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_mostrarNaoMostrarNomeMouseClicked

        if(LogUsuario.s_mostrarNome.equals("S")){
            if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", "mostrar_nome", "N", LogUsuario.s_cod)){
                lb_mostrarNaoMostrarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/disfarce.png")));
                lb_mostrarNaoMostrarNome.setToolTipText(Avisos.TIP_TEXT_MOSTRAR_NOME_DESABILITADO);
                LogUsuario.s_mostrarNome = "N";
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_MOSTRAR_NOME_DES, "atu");
            }else{
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
            }
        }
        else{
            if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", "mostrar_nome", "S", LogUsuario.s_cod)){
                lb_mostrarNaoMostrarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/neutro.png")));
                lb_mostrarNaoMostrarNome.setToolTipText(Avisos.TIP_TEXT_MOSTRAR_NOME_HABILITADO);
                LogUsuario.s_mostrarNome = "S";
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_MOSTRAR_NOME_HAB, "atu");
            }else{
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
            }
        }
    }//GEN-LAST:event_lb_mostrarNaoMostrarNomeMouseClicked

    private void lb_habilitarAudioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_habilitarAudioMouseClicked

            if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", "emitir_aviso_sonoro", "N", LogUsuario.s_cod)){
                    lb_habilitarAudio.setToolTipText(Avisos.TIP_TEXT_AVISO_SON_DESABILITADO);
                    lb_habilitarAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AUDIO_OFF)));
                    LogUsuario.s_emitirAvisoSonovo = "N";
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_AVISO_SON_DES, "aviso");
                }else{
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                }
            }
            else{
                if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", "emitir_aviso_sonoro", "S", LogUsuario.s_cod)){
                    lb_habilitarAudio.setToolTipText(Avisos.TIP_TEXT_AVISO_SON_HABILITADO);
                    lb_habilitarAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AUDIO_ON)));
                    LogUsuario.s_emitirAvisoSonovo = "S";
                    new Thread(new Audio("som-habilitado.wav")).start();
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_AVISO_SON_HAB, "atu");
                }else{
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                }
            }
    }//GEN-LAST:event_lb_habilitarAudioMouseClicked

    private void lb_fraseUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_fraseUsuarioMouseEntered
        lb_fraseUsuario.setFont(new java.awt.Font("Tahoma", 1, 12));
    }//GEN-LAST:event_lb_fraseUsuarioMouseEntered

    private void lb_fraseUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_fraseUsuarioMouseExited
        lb_fraseUsuario.setFont(new java.awt.Font("Tahoma", 0, 12));
    }//GEN-LAST:event_lb_fraseUsuarioMouseExited

    private void lb_ultimaOcorrenciaUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ultimaOcorrenciaUsuarioMouseEntered
        Data.atualizaDataHora();
        lb_ultimaOcorrenciaUsuario.setFont(new java.awt.Font("Tahoma", 1, 12));
        lb_qtdComentarioUltOcoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12));
        lb_ultimaOcorrenciaUsuario.setToolTipText(Avisos.TEXTO_ULT_OCO_POSTADA+Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, LogUsuario.s_dataHoraUltimaOcorrencia));
    }//GEN-LAST:event_lb_ultimaOcorrenciaUsuarioMouseEntered

    private void lb_ultimaOcorrenciaUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ultimaOcorrenciaUsuarioMouseExited
        lb_ultimaOcorrenciaUsuario.setFont(new java.awt.Font("Tahoma", 0, 12));
        lb_qtdComentarioUltOcoUsuario.setFont(new java.awt.Font("Tahoma", 0, 12));
    }//GEN-LAST:event_lb_ultimaOcorrenciaUsuarioMouseExited

    private void lb_qtdComentarioUltOcoUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdComentarioUltOcoUsuarioMouseEntered
        lb_qtdComentarioUltOcoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12));
        lb_ultimaOcorrenciaUsuario.setFont(new java.awt.Font("Tahoma", 1, 12));
        lb_qtdComentarioUltOcoUsuario.setToolTipText(lb_qtdComentarioUltOcoUsuario.getText()+Avisos.TEXTO_COMENTARIOS_ULT_OCO);
    }//GEN-LAST:event_lb_qtdComentarioUltOcoUsuarioMouseEntered

    private void lb_qtdComentarioUltOcoUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdComentarioUltOcoUsuarioMouseExited
        lb_qtdComentarioUltOcoUsuario.setFont(new java.awt.Font("Tahoma", 0, 12));
        lb_ultimaOcorrenciaUsuario.setFont(new java.awt.Font("Tahoma", 0, 12));
    }//GEN-LAST:event_lb_qtdComentarioUltOcoUsuarioMouseExited

    private void lb_sexoUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_sexoUsuarioMouseEntered
        lb_sexoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12));
    }//GEN-LAST:event_lb_sexoUsuarioMouseEntered

    private void lb_sexoUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_sexoUsuarioMouseExited
        lb_sexoUsuario.setFont(new java.awt.Font("Tahoma", 0, 12));
    }//GEN-LAST:event_lb_sexoUsuarioMouseExited

    private void lb_dataNiverUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_dataNiverUsuarioMouseEntered
        lb_dataNiverUsuario.setFont(new java.awt.Font("Tahoma", 1, 12));
    }//GEN-LAST:event_lb_dataNiverUsuarioMouseEntered

    private void lb_dataNiverUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_dataNiverUsuarioMouseExited
        lb_dataNiverUsuario.setFont(new java.awt.Font("Tahoma", 0, 12));
    }//GEN-LAST:event_lb_dataNiverUsuarioMouseExited

    private void lb_dataCadastroUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_dataCadastroUsuarioMouseEntered
        lb_dataCadastroUsuario.setFont(new java.awt.Font("Tahoma", 1, 12));
    }//GEN-LAST:event_lb_dataCadastroUsuarioMouseEntered

    private void lb_dataCadastroUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_dataCadastroUsuarioMouseExited
        lb_dataCadastroUsuario.setFont(new java.awt.Font("Tahoma", 0, 12));
    }//GEN-LAST:event_lb_dataCadastroUsuarioMouseExited

    private void lb_qtdRecInstMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdRecInstMouseEntered
        lb_qtdRecInst.setFont(new java.awt.Font("Tahoma", 1, 12));
    }//GEN-LAST:event_lb_qtdRecInstMouseEntered

    private void lb_qtdRecInstMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdRecInstMouseExited
        lb_qtdRecInst.setFont(new java.awt.Font("Tahoma", 0, 12));
    }//GEN-LAST:event_lb_qtdRecInstMouseExited

    private void lb_qtdRecInstMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdRecInstMouseReleased
        jtp_painelTabuladoUsuario.setSelectedComponent(jp_batePapo);
    }//GEN-LAST:event_lb_qtdRecInstMouseReleased

    private void lb_prendeFormNaFrenteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrenteMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrenteMouseEntered

    private void lb_prendeFormNaFrenteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrenteMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrenteMouseExited

    private void lb_prendeFormNaFrenteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrenteMouseReleased

        if(c_naFrente){
            this.setAlwaysOnTop(false);
            c_naFrente = false;
            lb_prendeFormNaFrente.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.NAO_FIXO_FRENTE))); // NOI18N
            lb_prendeFormNaFrente.setToolTipText(Avisos.TIP_TEXT_FACECARD_ESCONDE);
        }
        else{
            this.setAlwaysOnTop(true);
            c_naFrente = true;
            lb_prendeFormNaFrente.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.FIXO_FRENTE))); // NOI18N
            lb_prendeFormNaFrente.setToolTipText(Avisos.TIP_TEXT_FACECARD_N_ESCONDE);
        }
    }//GEN-LAST:event_lb_prendeFormNaFrenteMouseReleased

    private void bt_comentarAtuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_comentarAtuUsuarioActionPerformed
        int l_linha =  jt_listaAtualizacoesUsuario.getSelectedRow();

        int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaAtualizacoesUsuario);

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "atualizacao", jt_listaAtualizacoesUsuario);
    }//GEN-LAST:event_bt_comentarAtuUsuarioActionPerformed

    private void bt_comentarRecPublicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_comentarRecPublicoActionPerformed
        int l_linha =  jt_listaRecadosPubUsuario.getSelectedRow();

        int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaRecadosPubUsuario);

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "recado", jt_listaRecadosPubUsuario);
    }//GEN-LAST:event_bt_comentarRecPublicoActionPerformed

    private void bt_comentarRecPrivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_comentarRecPrivActionPerformed
        int l_linha =  jt_listaRecadosPriv.getSelectedRow();

        int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaRecadosPriv);

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "recado", jt_listaRecadosPriv);
    }//GEN-LAST:event_bt_comentarRecPrivActionPerformed

    private void bt_comentarOcorrenciaAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_comentarOcorrenciaAmigoActionPerformed
        int l_linha =  jt_listaOcorrenciaAmigo.getSelectedRow();

        int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaOcorrenciaAmigo);

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "ocorrencia", jt_listaOcorrenciaAmigo);
    }//GEN-LAST:event_bt_comentarOcorrenciaAmigoActionPerformed

    private void bt_comentarAtuAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_comentarAtuAmigoActionPerformed
        int l_linha =  jt_listaAtualizacoesAmigo.getSelectedRow();

        int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaAtualizacoesAmigo);

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "atualizacao", jt_listaAtualizacoesAmigo);
    }//GEN-LAST:event_bt_comentarAtuAmigoActionPerformed

    private void bt_comentarRecPubAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_comentarRecPubAmigoActionPerformed
        int l_linha =  jt_listaRecadosPubAmigo.getSelectedRow();

        int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaRecadosPubAmigo);

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "recado", jt_listaRecadosPubAmigo);
    }//GEN-LAST:event_bt_comentarRecPubAmigoActionPerformed

    private void rb_todosArquivosDisponiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_todosArquivosDisponiveisActionPerformed

        LogUsuario.s_qtdArqAMostrar = 4;
        tf_nomeArqPesquisado.setText(""); 
        
        MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, jt_listaArquivosUsuario, "disponiveis");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaArquivosUsuario);
    }//GEN-LAST:event_rb_todosArquivosDisponiveisActionPerformed

    private void cb_tipoAmigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tipoAmigosActionPerformed
        LogUsuario.s_qtdUsuMostrar = 4;
        tf_pesquisaUsuario.setText("");   
        
        MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, cb_tipoAmigos.getSelectedItem().toString());
        MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
    }//GEN-LAST:event_cb_tipoAmigosActionPerformed

    private void tf_pesquisaUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_pesquisaUsuarioKeyReleased
       

        MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, cb_tipoAmigos.getSelectedItem().toString());

    }//GEN-LAST:event_tf_pesquisaUsuarioKeyReleased

    private void bt_addOcorrenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addOcorrenciaActionPerformed
        GravarDados.s_GravarDados.gravarOcorrencia(tf_ocorrencia.getText(), cb_anonimo.isSelected());
}//GEN-LAST:event_bt_addOcorrenciaActionPerformed

    private void tf_ocorrenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_ocorrenciaActionPerformed
        GravarDados.s_GravarDados.gravarOcorrencia(tf_ocorrencia.getText(), cb_anonimo.isSelected());
}//GEN-LAST:event_tf_ocorrenciaActionPerformed

    private void lb_qtdConvitesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdConvitesMouseEntered
       lb_qtdConvites.setFont(new java.awt.Font("Tahoma", 1, 12));
    }//GEN-LAST:event_lb_qtdConvitesMouseEntered

    private void lb_qtdConvitesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdConvitesMouseExited
       lb_qtdConvites.setFont(new java.awt.Font("Tahoma", 0, 12));
    }//GEN-LAST:event_lb_qtdConvitesMouseExited

    private void lb_qtdConvitesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdConvitesMouseReleased

        cb_tipoAmigos.setSelectedItem("Todos(convites)"); 
          //logo carregando...
        new Thread(new LogoCarregando()).start();

        LogUsuario.s_qtdUsuMostrar = 4;
        tf_pesquisaUsuario.setText("");   
        
        MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, cb_tipoAmigos.getSelectedItem().toString());
        MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
        
        
        Moniersn.jt_listaAmigosUsuario.setRowSelectionInterval(0, 0);
        
        //o clique na tabela pega o moniersn do amigo e guarda na variável STATICA LogUsuario.S_MONIERSN que esta na classe LogUsuario
        int l_codAmigo = SolicitarDados.s_SolicitarDados.pegaCodDoAmigoClicadoNaLista("amigos");

        AtualizarDados.s_AtualizarDados.atualizaLogAmigo(l_codAmigo);
        
            if(LogAmigo.s_status.equals("conectado"))
                SolicitarDados.s_SolicitarDados.definicaoDeUsuarioConectado(LogAmigo.s_cod, LogAmigo.s_host, LogAmigo.s_portaTCP);
        
        //Busca informações no banco do moniersn clicado
        MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(l_codAmigo);

    }//GEN-LAST:event_lb_qtdConvitesMouseReleased

    private void lb_ultOcorrenciaAmigoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ultOcorrenciaAmigoMouseEntered
        Data.atualizaDataHora();
        lb_ultOcorrenciaAmigo.setFont(new java.awt.Font("Tahoma", 1, 12));
        lb_qtdComentarioUltOcoAmigo.setFont(new java.awt.Font("Tahoma", 1, 12));
        lb_ultOcorrenciaAmigo.setToolTipText(Avisos.TEXTO_ULT_OCO_POSTADA+Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, LogAmigo.s_dataHoraUltimaOcorrencia));
    }//GEN-LAST:event_lb_ultOcorrenciaAmigoMouseEntered

    private void rb_atualizacoesMinhasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_atualizacoesMinhasActionPerformed
        LogUsuario.s_qtdAtuAMostrar = 4;
        tf_conteudoAtuPesquisado.setText(""); 
        
        MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, jt_listaAtualizacoesUsuario, "env");

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaAtualizacoesUsuario);
    }//GEN-LAST:event_rb_atualizacoesMinhasActionPerformed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus

        if(!c_naFrente){ 
                s_telaMsn.setVisible(false);
                MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
                MostrarDados.s_MostrarDados.mostraAvisoProcedimento(Avisos.FACECARD_FULL, Avisos.AVISO_EXECUCAO_SYSTRAY, "INFO");
        }
    }//GEN-LAST:event_formWindowLostFocus

    private void lb_prendeFormNaFrente3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente3MouseEntered

    private void lb_prendeFormNaFrente3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente3MouseExited

    private void lb_prendeFormNaFrente3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente3MouseReleased

    private void lb_prendeFormNaFrente4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente4MouseEntered

    private void lb_prendeFormNaFrente4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente4MouseExited

    private void lb_prendeFormNaFrente4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente4MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente4MouseReleased

    private void lb_prendeFormNaFrente5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente5MouseEntered

    private void lb_prendeFormNaFrente5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente5MouseExited

    private void lb_prendeFormNaFrente5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente5MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente5MouseReleased

    private void lb_prendeFormNaFrente6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente6MouseEntered

    private void lb_prendeFormNaFrente6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente6MouseExited

    private void lb_prendeFormNaFrente6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente6MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente6MouseReleased

    private void lb_prendeFormNaFrente7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente7MouseEntered

    private void lb_prendeFormNaFrente7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente7MouseExited

    private void lb_prendeFormNaFrente7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente7MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente7MouseReleased

    private void lb_prendeFormNaFrente8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente8MouseEntered

    private void lb_prendeFormNaFrente8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente8MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente8MouseExited

    private void lb_prendeFormNaFrente8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente8MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente8MouseReleased

    private void lb_prendeFormNaFrente9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente9MouseEntered

    private void lb_prendeFormNaFrente9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente9MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente9MouseExited

    private void lb_prendeFormNaFrente9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente9MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente9MouseReleased

    private void lb_prendeFormNaFrente10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente10MouseEntered

    private void lb_prendeFormNaFrente10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente10MouseExited

    private void lb_prendeFormNaFrente10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente10MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente10MouseReleased

    private void lb_prendeFormNaFrente11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente11MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente11MouseEntered

    private void lb_prendeFormNaFrente11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente11MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente11MouseExited

    private void lb_prendeFormNaFrente11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente11MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente11MouseReleased

    private void lb_prendeFormNaFrente14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente14MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente14MouseEntered

    private void lb_prendeFormNaFrente14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente14MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente14MouseExited

    private void lb_prendeFormNaFrente14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente14MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente14MouseReleased

    private void rb_todosArquivosBaixadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_todosArquivosBaixadosActionPerformed
        LogUsuario.s_qtdArqAMostrar = 4;
        tf_nomeArqPesquisado.setText(""); 
        
        MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, jt_listaArquivosUsuario, Diretorios.PASTA_BAIXADOS);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaArquivosUsuario);
    }//GEN-LAST:event_rb_todosArquivosBaixadosActionPerformed

    private void lb_prendeFormNaFrente15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente15MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente15MouseEntered

    private void lb_prendeFormNaFrente15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente15MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente15MouseExited

    private void lb_prendeFormNaFrente15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente15MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente15MouseReleased

    private void rb_todosArquivosEnviadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_todosArquivosEnviadosActionPerformed
      
        LogUsuario.s_qtdArqAMostrar = 4;
        tf_nomeArqPesquisado.setText(""); 
        
        MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, jt_listaArquivosUsuario, Diretorios.PASTA_ENVIADOS);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaArquivosUsuario);
    }//GEN-LAST:event_rb_todosArquivosEnviadosActionPerformed

    private void lb_prendeFormNaFrente16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente16MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente16MouseEntered

    private void lb_prendeFormNaFrente16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente16MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente16MouseExited

    private void lb_prendeFormNaFrente16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_prendeFormNaFrente16MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_prendeFormNaFrente16MouseReleased

    private void lb_segurancaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_segurancaMouseReleased

            if(LogUsuario.s_solicitarSenhaAoMostrar.equals("S")){
                if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", "solicitar_senha_ao_mostrar", "N", LogUsuario.s_cod)){
                    lb_seguranca.setToolTipText(Avisos.TIP_TEXT_SEGURANCA_DESABILITADA);
                    lb_seguranca.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LIBERADO_SYSTRAY)));
                    LogUsuario.s_solicitarSenhaAoMostrar = "N";
                        //CASO AVISO SONORO desabilitado
                        if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                            new Thread(new Audio("seguranca-desabilitada.WAV")).start();
                        }
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_SEG_DES, "aviso");
                }else{
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                }
            }
            else{
                if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", "solicitar_senha_ao_mostrar", "S", LogUsuario.s_cod)){
                    lb_seguranca.setToolTipText(Avisos.TIP_TEXT_SEGURANCA_HABILITADA);
                    lb_seguranca.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.FECHADO_SYSTRAY)));
                    LogUsuario.s_solicitarSenhaAoMostrar = "S";
                        //CASO AVISO SONORO habilitado
                        if(LogUsuario.s_emitirAvisoSonovo.equals("S")){
                            new Thread(new Audio("seguranca-habilitada.wav")).start();
                        }
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_SEG_HAB, "atu");
                        
                }else{
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                }
            }
    }//GEN-LAST:event_lb_segurancaMouseReleased

    private void rb_todosBatemPapoComigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_todosBatemPapoComigoActionPerformed
        //CASO SALVO
         if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", "quem_bate_papo_comigo", "todos", LogUsuario.s_cod)){

                LogUsuario.s_qtdUsuBPMostrar = 4;
                tf_pesquisaUsuarioBatePapo.setText("");

                //grava log
                LogUsuario.s_quemBatePapoComigo = "todos";
                
                //desabilita o botao de bloqueio de amigo
                bt_visuPainelAmigo.setEnabled(false);

                //CONTAGEM DE ÍTENS
                SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                //SETA ICONE DA GUIA
                jtp_painelTabuladoUsuario.setIconAt(6, new javax.swing.ImageIcon(getClass().getResource(Icones.TODOS_BATE_PAPO_16)));

                //SELECIONA CB TODOS
                cb_enviarParaTodosConectados.setSelected(true);

                //ENVIA A VISO PARA TODOS USUÁRIOS CONECTADOS
                ClienteUDP.enviaAvisoParaTodosConectados(7, "geral");
                
                //MOSTRA LABEL DE NINGUEM BATE PAPO COMIGO
                lb_ninguemBatePapoComigo.setVisible(true); 
                
                //MOSTRA LABEL DE NINGUEM BATE PAPO COMIGO (guia inicio)
                lb_ninguemBatePapoComigoInicio.setVisible(true);

                //mostra aviso de atualização
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");

         }else{
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
         }
    }//GEN-LAST:event_rb_todosBatemPapoComigoActionPerformed

    private void rb_soAmigosBatemPapoComigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_soAmigosBatemPapoComigoActionPerformed
        //CASO SALVO
         if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", "quem_bate_papo_comigo", "amigos", LogUsuario.s_cod)){

                LogUsuario.s_qtdUsuBPMostrar = 4;
                tf_pesquisaUsuarioBatePapo.setText("");
             
                //grava log
                LogUsuario.s_quemBatePapoComigo = "amigos";
                
                //desabilita o botao de bloqueio de amigo
                bt_visuPainelAmigo.setEnabled(false);

                //CONTAGEM DE ÍTENS
                SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                //SETA ICONE DA GUIA
                jtp_painelTabuladoUsuario.setIconAt(6, new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_BATE_PAPO_16)));

                //SELECIONA CB TODOS
                cb_enviarParaTodosConectados.setSelected(true);
                
                //MOSTRA LABEL DE NINGUEM BATE PAPO COMIGO
                lb_ninguemBatePapoComigo.setVisible(true); 
                
                //MOSTRA LABEL DE NINGUEM BATE PAPO COMIGO (guia inicio)
                lb_ninguemBatePapoComigoInicio.setVisible(true);

                //ENVIA A VISO PARA TODOS USUÁRIOS CONECTADOS
                ClienteUDP.enviaAvisoParaTodosConectados(7, "geral");
                

                //mostra aviso de atualização
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");
         }else{
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
         }
    }//GEN-LAST:event_rb_soAmigosBatemPapoComigoActionPerformed

    private void jp_arquivosUsuarioComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jp_arquivosUsuarioComponentShown
        //SETA TITULO
        MostrarDados.s_MostrarDados.setaTituloFrame("ARQUIVOS", "moniersn");

        //a guia atual eh bate papo
        LogUsuario.s_abaAtualSelecionada = jp_arquivosUsuario;

    }//GEN-LAST:event_jp_arquivosUsuarioComponentShown

    private void lb_ultimaOcorrenciaUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ultimaOcorrenciaUsuarioMouseReleased
        int l_codConteudo = LogUsuario.s_codUltimaOcorrencia;

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "ocorrencia", jt_listaOcorrenciaUsuario);
    }//GEN-LAST:event_lb_ultimaOcorrenciaUsuarioMouseReleased

    private void lb_qtdComentarioUltOcoUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdComentarioUltOcoUsuarioMouseReleased
        int l_codConteudo = LogUsuario.s_codUltimaOcorrencia;

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "ocorrencia", jt_listaOcorrenciaUsuario);
    }//GEN-LAST:event_lb_qtdComentarioUltOcoUsuarioMouseReleased

    private void lb_ultOcorrenciaAmigoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ultOcorrenciaAmigoMouseReleased
        int l_codConteudo = LogAmigo.s_codUltimaOcorrencia;

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "ocorrencia", jt_listaOcorrenciaAmigo);
    }//GEN-LAST:event_lb_ultOcorrenciaAmigoMouseReleased

    private void lb_qtdComentarioUltOcoAmigoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdComentarioUltOcoAmigoMouseReleased
        int l_codConteudo = LogAmigo.s_codUltimaOcorrencia;

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "ocorrencia", jt_listaOcorrenciaAmigo);
    }//GEN-LAST:event_lb_qtdComentarioUltOcoAmigoMouseReleased

    private void bt_atualizarListaUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atualizarListaUsuariosActionPerformed
        //atualiza informações gerais
        new Thread(new LogoCarregando()).start();
        
        LogUsuario.s_qtdUsuMostrar = 4;
        tf_pesquisaUsuario.setText("");         
 
        //CONTAGEM DE REC. PUB/PRIV/OCORRENCIAS/AMIGOS
        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);
        
        //verifica se os servidores estão ok
        SolicitarDados.s_SolicitarDados.estabilizaServidores(1);

        MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);     
    }//GEN-LAST:event_bt_atualizarListaUsuariosActionPerformed

    private void bt_encaminharArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_encaminharArquivoActionPerformed
        if(CompartilhaArquivo.s_telaCompArquivo != null) 
            CompartilhaArquivo.s_telaCompArquivo.setVisible(true);
        else{       
            
            //l_tabela arquivo
            TableModel l_tabelaArquivo = jt_listaArquivosUsuario.getModel();
            int l_linhaSelecionada = jt_listaArquivosUsuario.getSelectedRow();
            String l_nomeArquivo = l_tabelaArquivo.getValueAt(l_linhaSelecionada, 2).toString();
            int l_tamanho = Integer.parseInt(l_tabelaArquivo.getValueAt(l_linhaSelecionada, 3).toString());

            MostrarDados.s_MostrarDados.mostraTelaCompartilhamentoArquivo(l_nomeArquivo, l_tamanho);

        }
    }//GEN-LAST:event_bt_encaminharArquivoActionPerformed

    private void jt_listaArquivosUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaArquivosUsuarioMouseReleased
        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaArquivosUsuario);
    }//GEN-LAST:event_jt_listaArquivosUsuarioMouseReleased

    private void jt_listaArquivosUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_listaArquivosUsuarioKeyReleased
        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaArquivosUsuario);
    }//GEN-LAST:event_jt_listaArquivosUsuarioKeyReleased

    private void bt_abrirArquivoSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_abrirArquivoSelecionadoActionPerformed
        String l_pasta = null;
        String l_nomeArquivo = null;
        int l_linhaSelecionada = 0;
        TableModel l_tabelaArquivo = jt_listaArquivosUsuario.getModel();


            if(LogUsuario.s_listaArquivoSelecionada.equals("enviados")){
                l_pasta = Diretorios.PASTA_ENVIADOS;
            }else{
                l_pasta = Diretorios.PASTA_BAIXADOS;
            }           


        //Nome do arquivo
        l_linhaSelecionada = jt_listaArquivosUsuario.getSelectedRow();

        l_nomeArquivo = SolicitarDados.s_SolicitarDados.
                        recolocaAspasSimplesParaSO(l_tabelaArquivo.getValueAt(l_linhaSelecionada, 2).toString());

        boolean l_aceitou = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_ABRIR_ARQUIVO, Avisos.ABRIR_ARQUIVO, Icones.ARQUIVO_ABRIR);
               
            //caso aceitou baixar...
            if(l_aceitou)        
                MostrarDados.s_MostrarDados.abrirArquivoSelecionado(l_nomeArquivo, l_pasta);
            else 
                return;
    }//GEN-LAST:event_bt_abrirArquivoSelecionadoActionPerformed

    private void bt_removerArquivoSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_removerArquivoSelecionadoActionPerformed
        AtualizarDados.s_AtualizarDados.removerConteudo(jt_listaArquivosUsuario);
    }//GEN-LAST:event_bt_removerArquivoSelecionadoActionPerformed

    private void bt_comentarArquivoSelecionadoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_comentarArquivoSelecionadoUsuarioActionPerformed
        int l_linha =  jt_listaArquivosUsuario.getSelectedRow();

        int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaArquivosUsuario);

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "arquivo", jt_listaArquivosUsuario);
    }//GEN-LAST:event_bt_comentarArquivoSelecionadoUsuarioActionPerformed

    private void jt_listaArquivosAmigoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaArquivosAmigoMouseReleased
        bt_baixarArquivoSelecionadoAmigo.setEnabled(true);
        bt_comentarArquivoSelecionadoAmigo.setEnabled(true);
    }//GEN-LAST:event_jt_listaArquivosAmigoMouseReleased

    private void jt_listaOcorrenciaAmigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_listaOcorrenciaAmigoKeyReleased
        bt_comentarOcorrenciaAmigo.setEnabled(true);
    }//GEN-LAST:event_jt_listaOcorrenciaAmigoKeyReleased

    private void jt_listaRecadosPubAmigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_listaRecadosPubAmigoKeyReleased
        bt_comentarRecPubAmigo.setEnabled(true);
    }//GEN-LAST:event_jt_listaRecadosPubAmigoKeyReleased

    private void jt_listaAtualizacoesAmigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_listaAtualizacoesAmigoKeyReleased
        bt_comentarAtuAmigo.setEnabled(true);
    }//GEN-LAST:event_jt_listaAtualizacoesAmigoKeyReleased

    private void jt_listaArquivosAmigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_listaArquivosAmigoKeyReleased
        bt_baixarArquivoSelecionadoAmigo.setEnabled(true);
        bt_comentarArquivoSelecionadoAmigo.setEnabled(true);
    }//GEN-LAST:event_jt_listaArquivosAmigoKeyReleased

    private void bt_comentarArquivoSelecionadoAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_comentarArquivoSelecionadoAmigoActionPerformed
        int l_linha =  jt_listaArquivosAmigo.getSelectedRow();

        int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaArquivosAmigo);

        MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "arquivo", jt_listaArquivosAmigo);
    }//GEN-LAST:event_bt_comentarArquivoSelecionadoAmigoActionPerformed

    private void lb_qtdComentarioUltOcoAmigoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdComentarioUltOcoAmigoMouseEntered
        lb_ultOcorrenciaAmigo.setFont(new java.awt.Font("Tahoma", 1, 12));
        lb_qtdComentarioUltOcoAmigo.setFont(new java.awt.Font("Tahoma", 1, 12));
        lb_qtdComentarioUltOcoAmigo.setToolTipText(lb_qtdComentarioUltOcoAmigo.getText()+Avisos.TEXTO_COMENTARIOS_ULT_OCO);
    }//GEN-LAST:event_lb_qtdComentarioUltOcoAmigoMouseEntered

    private void bt_baixarArquivoSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_baixarArquivoSelecionadoActionPerformed
       
        SolicitarDados.s_SolicitarDados.baixaArquivoSelecionado(jt_listaArquivosUsuario);

    }//GEN-LAST:event_bt_baixarArquivoSelecionadoActionPerformed

    private void bt_baixarArquivoSelecionadoAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_baixarArquivoSelecionadoAmigoActionPerformed
        
        SolicitarDados.s_SolicitarDados.baixaArquivoSelecionado(jt_listaArquivosAmigo);

    }//GEN-LAST:event_bt_baixarArquivoSelecionadoAmigoActionPerformed

    private void bt_compartilharArquivoGeralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_compartilharArquivoGeralActionPerformed
        if(CompartilhaArquivo.s_telaCompArquivo != null) 
            CompartilhaArquivo.s_telaCompArquivo.setVisible(true);
        else
            MostrarDados.s_MostrarDados.mostraTelaCompartilhamentoArquivo("varios");
    }//GEN-LAST:event_bt_compartilharArquivoGeralActionPerformed

    private void lb_facebookMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_facebookMouseReleased

//        if(!lb_facebook.getText().equals("*")){
//
//            try{
//                Desktop.getDesktop().open("http://www.facebook.com/"+lb_facebook.getText());
//            }catch(Exception e){
//                System.err.println("Erro ao tentar abrir página do facebook do usuario.   \nErro "+e);
//
//            }
//
//
//        }
    }//GEN-LAST:event_lb_facebookMouseReleased

private void tf_conteudoOcoPesquisadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_conteudoOcoPesquisadoKeyReleased
        //preenche lista de ocorrências enviadas
        MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, jt_listaOcorrenciaUsuario, LogUsuario.s_listaOcorrenciaSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaOcorrenciaUsuario);
}//GEN-LAST:event_tf_conteudoOcoPesquisadoKeyReleased

private void bt_atuListaOcoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atuListaOcoUsuarioActionPerformed
        LogUsuario.s_qtdOcoAMostrar = 4;
        tf_conteudoOcoPesquisado.setText("");
    
        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod); 
        
        //preenche lista de ocorrências enviadas
        MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, jt_listaOcorrenciaUsuario, LogUsuario.s_listaOcorrenciaSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaOcorrenciaUsuario);  
}//GEN-LAST:event_bt_atuListaOcoUsuarioActionPerformed

private void tf_conteudoOcoPesquisadoAmigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_conteudoOcoPesquisadoAmigoKeyReleased
        //inabilitação de botao de comentário de ocorrencia
        bt_comentarOcorrenciaAmigo.setEnabled(false);       
    
        //preencher lista de ocorrências do amigo
        MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogAmigo.s_cod, Moniersn.jt_listaOcorrenciaAmigo, "");
}//GEN-LAST:event_tf_conteudoOcoPesquisadoAmigoKeyReleased

private void bt_atuListaOcoAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atuListaOcoAmigoActionPerformed
        LogAmigo.s_qtdOcoAMostrar = 4;
        tf_conteudoOcoPesquisadoAmigo.setText("");
    
        //inabilitação de botao de comentário de ocorrencia
        bt_comentarOcorrenciaAmigo.setEnabled(false);       
    
        //preencher lista de ocorrências do amigo
        MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogAmigo.s_cod, Moniersn.jt_listaOcorrenciaAmigo, "");

}//GEN-LAST:event_bt_atuListaOcoAmigoActionPerformed

private void tf_conteudoOcoPesquisadoAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_conteudoOcoPesquisadoAmigoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tf_conteudoOcoPesquisadoAmigoActionPerformed

private void bt_atuListaAtuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atuListaAtuUsuarioActionPerformed
        LogUsuario.s_qtdAtuAMostrar = 4;
        tf_conteudoAtuPesquisado.setText(""); 

        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod); 
        
        MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, jt_listaAtualizacoesUsuario, LogUsuario.s_listaAtualizacoesSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaAtualizacoesUsuario);
}//GEN-LAST:event_bt_atuListaAtuUsuarioActionPerformed

private void tf_conteudoAtuPesquisadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_conteudoAtuPesquisadoKeyReleased
    MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, jt_listaAtualizacoesUsuario, LogUsuario.s_listaAtualizacoesSelecionada);

    MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaAtualizacoesUsuario);
}//GEN-LAST:event_tf_conteudoAtuPesquisadoKeyReleased

private void bt_atuListaAtuAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atuListaAtuAmigoActionPerformed
   
    LogAmigo.s_qtdAtuAMostrar = 4;
    tf_conteudoAtuPesquisadoAmigo.setText("");
    
    bt_comentarAtuAmigo.setEnabled(false);
     
    //PREENCHE LISTA DE ATU DO AMIGO
    MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogAmigo.s_cod, Moniersn.jt_listaAtualizacoesAmigo, "");
}//GEN-LAST:event_bt_atuListaAtuAmigoActionPerformed

private void tf_conteudoAtuPesquisadoAmigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_conteudoAtuPesquisadoAmigoKeyReleased
    bt_comentarAtuAmigo.setEnabled(false);
     
    //PREENCHE LISTA DE ATU DO AMIGO
    MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogAmigo.s_cod, Moniersn.jt_listaAtualizacoesAmigo, "");
}//GEN-LAST:event_tf_conteudoAtuPesquisadoAmigoKeyReleased

private void bt_atuListaRecPubUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atuListaRecPubUsuarioActionPerformed
        LogUsuario.s_qtdRecPubAMostrar = 4;
        tf_conteudoRecPubPesquisado.setText(""); 
        
        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod); 
                
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPubUsuario, LogUsuario.s_listaRecPubSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPubUsuario);
}//GEN-LAST:event_bt_atuListaRecPubUsuarioActionPerformed

private void tf_conteudoRecPubPesquisadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_conteudoRecPubPesquisadoKeyReleased
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPubUsuario, LogUsuario.s_listaRecPubSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPubUsuario);
}//GEN-LAST:event_tf_conteudoRecPubPesquisadoKeyReleased

private void bt_atuListaRecPubAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atuListaRecPubAmigoActionPerformed

        LogAmigo.s_qtdRecPubAMostrar = 4;
        tf_conteudoRecPubPesquisadoAmigo.setText("");

        //inabilitação de botao de comentário de ocorrencia
        bt_comentarRecPubAmigo.setEnabled(false);       

        //preencher lista de ocorrências do amigo
        MostrarDados.s_MostrarDados.preencherListaRecadosPubAmigo(LogAmigo.s_cod);
}//GEN-LAST:event_bt_atuListaRecPubAmigoActionPerformed

private void tf_conteudoRecPubPesquisadoAmigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_conteudoRecPubPesquisadoAmigoKeyReleased
        //inabilitação de botao de comentário de ocorrencia
        bt_comentarRecPubAmigo.setEnabled(false);       
    
        //preencher lista de ocorrências do amigo
        MostrarDados.s_MostrarDados.preencherListaRecadosPubAmigo(LogAmigo.s_cod);
}//GEN-LAST:event_tf_conteudoRecPubPesquisadoAmigoKeyReleased

private void bt_atuListaRecPrivUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atuListaRecPrivUsuarioActionPerformed

        LogUsuario.s_qtdRecPrivAMostrar = 4;
        tf_conteudoRecPrivPesquisado.setText(""); 
        
        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod); 
        
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPriv, LogUsuario.s_listaRecPrivSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPriv);
}//GEN-LAST:event_bt_atuListaRecPrivUsuarioActionPerformed

private void tf_conteudoRecPrivPesquisadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_conteudoRecPrivPesquisadoKeyReleased
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPriv, LogUsuario.s_listaRecPrivSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPriv);
}//GEN-LAST:event_tf_conteudoRecPrivPesquisadoKeyReleased

private void bt_atuListaArquivosUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atuListaArquivosUsuarioActionPerformed
        //atualiza informações gerais
        new Thread(new LogoCarregando()).start();
    
        LogUsuario.s_qtdArqAMostrar = 4;
        tf_nomeArqPesquisado.setText("");
        
        //verificar local de arquivos
        SolicitarDados.s_SolicitarDados.verificaLocalArquivos(LogUsuario.s_cod, LogUsuario.s_localDeArquivosDoUsuario, "atu");
        
        //verifica se os servidores estão ok
        SolicitarDados.s_SolicitarDados.estabilizaServidores(1);
  
}//GEN-LAST:event_bt_atuListaArquivosUsuarioActionPerformed

private void tf_nomeArqPesquisadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nomeArqPesquisadoKeyReleased
        MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, jt_listaArquivosUsuario, LogUsuario.s_listaArquivoSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaArquivosUsuario);
}//GEN-LAST:event_tf_nomeArqPesquisadoKeyReleased

private void tf_nomeArqPesquisadoAmigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nomeArqPesquisadoAmigoKeyReleased
        //inabilitação de botao de comentário de ocorrencia
        bt_comentarArquivoSelecionadoAmigo.setEnabled(false);       
    
        //preencher lista de ocorrências do amigo
        MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogAmigo.s_cod, jt_listaArquivosAmigo, "");
}//GEN-LAST:event_tf_nomeArqPesquisadoAmigoKeyReleased

private void bt_atuListaArqAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atuListaArqAmigoActionPerformed
        LogAmigo.s_qtdArqAMostrar = 4;
        tf_nomeArqPesquisadoAmigo.setText("");
    
        //inabilitação de botao de comentário de ocorrencia
        bt_comentarArquivoSelecionadoAmigo.setEnabled(false);       
    
        //preencher lista de ocorrências do amigo
        MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogAmigo.s_cod, jt_listaArquivosAmigo, "");
}//GEN-LAST:event_bt_atuListaArqAmigoActionPerformed

private void bt_verMaisOcoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisOcoUsuarioActionPerformed
        
        LogUsuario.s_qtdOcoAMostrar += 4;
        
        //preenche lista de ocorrências enviadas
        MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, jt_listaOcorrenciaUsuario, LogUsuario.s_listaOcorrenciaSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaOcorrenciaUsuario);

        sp_ocorrenciaUsuario.getVerticalScrollBar().setValue(jt_listaOcorrenciaUsuario.getSize().height); 
}//GEN-LAST:event_bt_verMaisOcoUsuarioActionPerformed

private void bt_verMaisAtuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisAtuUsuarioActionPerformed
        
        LogUsuario.s_qtdAtuAMostrar += 4;
        
        MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, jt_listaAtualizacoesUsuario, LogUsuario.s_listaAtualizacoesSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaAtualizacoesUsuario);
        
        sp_atualizacoesUsuario.getVerticalScrollBar().setValue(jt_listaAtualizacoesUsuario.getSize().height); 
        
}//GEN-LAST:event_bt_verMaisAtuUsuarioActionPerformed

private void bt_verMaisRecPubUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisRecPubUsuarioActionPerformed
        LogUsuario.s_qtdRecPubAMostrar += 4;
                        
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPubUsuario, LogUsuario.s_listaRecPubSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPubUsuario);
        
        sp_recadosPubUsuario.getVerticalScrollBar().setValue(jt_listaRecadosPubUsuario.getSize().height); 
}//GEN-LAST:event_bt_verMaisRecPubUsuarioActionPerformed

private void bt_verMaisRecPrivUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisRecPrivUsuarioActionPerformed

        LogUsuario.s_qtdRecPrivAMostrar += 4;
                
        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, jt_listaRecadosPriv, LogUsuario.s_listaRecPrivSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaRecadosPriv);
        
        sp_recadosPriv.getVerticalScrollBar().setValue(jt_listaRecadosPriv.getSize().height); 
}//GEN-LAST:event_bt_verMaisRecPrivUsuarioActionPerformed

private void bt_verMaisArquivosUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisArquivosUsuarioActionPerformed

        LogUsuario.s_qtdArqAMostrar += 4;
                 
        MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, jt_listaArquivosUsuario, LogUsuario.s_listaArquivoSelecionada);

        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(jt_listaArquivosUsuario);
        
        sp_arquivosUsuario.getVerticalScrollBar().setValue(jt_listaArquivosUsuario.getSize().height); 
}//GEN-LAST:event_bt_verMaisArquivosUsuarioActionPerformed

private void bt_verMaisOcoAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisOcoAmigoActionPerformed
        LogAmigo.s_qtdOcoAMostrar += 4;
            
        //inabilitação de botao de comentário de ocorrencia
        bt_comentarOcorrenciaAmigo.setEnabled(false);       
    
        //preencher lista de ocorrências do amigo
        MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogAmigo.s_cod, Moniersn.jt_listaOcorrenciaAmigo, "");
        
        sp_ocorrenciasAmigo.getVerticalScrollBar().setValue(jt_listaOcorrenciaAmigo.getSize().height); 
}//GEN-LAST:event_bt_verMaisOcoAmigoActionPerformed

private void bt_verMaisAtuAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisAtuAmigoActionPerformed
   
    LogAmigo.s_qtdAtuAMostrar += 4;
        
    bt_comentarAtuAmigo.setEnabled(false);
     
    //PREENCHE LISTA DE ATU DO AMIGO
    MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogAmigo.s_cod, Moniersn.jt_listaAtualizacoesAmigo, "");
    
    sp_atualizacoesAmigo.getVerticalScrollBar().setValue(jt_listaAtualizacoesAmigo.getSize().height); 
}//GEN-LAST:event_bt_verMaisAtuAmigoActionPerformed

private void bt_verMaisRecPubAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisRecPubAmigoActionPerformed

        LogAmigo.s_qtdRecPubAMostrar += 4;
                
        //inabilitação de botao de comentário de ocorrencia
        bt_comentarRecPubAmigo.setEnabled(false);       

        //preencher lista de ocorrências do amigo
        MostrarDados.s_MostrarDados.preencherListaRecadosPubAmigo(LogAmigo.s_cod);
        
        sp_recadosPubAmigo.getVerticalScrollBar().setValue(jt_listaRecadosPubAmigo.getSize().height); 
}//GEN-LAST:event_bt_verMaisRecPubAmigoActionPerformed

private void bt_verMaisArqAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisArqAmigoActionPerformed
        LogAmigo.s_qtdArqAMostrar += 4;
            
        //inabilitação de botao de comentário de ocorrencia
        bt_comentarArquivoSelecionadoAmigo.setEnabled(false);       
    
        //preencher lista de ocorrências do amigo
        MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogAmigo.s_cod, jt_listaArquivosAmigo, "");
        
        sp_arquivosAmigo.getVerticalScrollBar().setValue(jt_listaArquivosAmigo.getSize().height); 
}//GEN-LAST:event_bt_verMaisArqAmigoActionPerformed

private void bt_abrirLocalArquivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_abrirLocalArquivosActionPerformed
        boolean l_podeContinuar = false;
        SolicitarDados.s_SolicitarDados.verificaLocalArquivos(LogUsuario.s_cod, LogUsuario.s_localDeArquivosDoUsuario, "abrir");


            if(LogUsuario.s_localDeArquivosDoUsuario.equals("*")){

                l_podeContinuar = MostrarDados.s_MostrarDados.mostraTelaEscolhaDiretorio("definir");

            //caso o local de arquivos do usuário nao esteja no lugar...
            }else if(!LogUsuario.s_localDeArquivosDoUsuarioConsistente){
                
                l_podeContinuar = MostrarDados.s_MostrarDados.mostraTelaEscolhaDiretorio("localizar");
                
            }else
                l_podeContinuar = true;

            
        if(l_podeContinuar){
            
            boolean l_aceitou = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_ABRIR_LOCAL_ARQUIVOS, Avisos.ABRIR_LOCAL_ARQUIVOS, Icones.PASTA_PROCURAR);
               
                //caso aceitou baixar...
                if(l_aceitou){
            
                    MostrarDados.s_MostrarDados.abrirLocalArquivos();
                
                }else{ 
                    Moniersn.s_telaMsn.setVisible(true); 
                    return;
                }
        }
}//GEN-LAST:event_bt_abrirLocalArquivosActionPerformed

private void bt_verMaisUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisUsuariosActionPerformed
        LogUsuario.s_qtdUsuMostrar += 4;
        
        MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, cb_tipoAmigos.getSelectedItem().toString());
        MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);  
        
        sp_listaAmigosUsuario.getVerticalScrollBar().setValue(jt_listaAmigosUsuario.getSize().height); 
}//GEN-LAST:event_bt_verMaisUsuariosActionPerformed

private void tf_pesquisaListaAmigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_pesquisaListaAmigoKeyReleased
        MostrarDados.s_MostrarDados.preencherListaAmigosDoAmigo(LogAmigo.s_cod, LogAmigo.s_listaAmigoSelecionada);
}//GEN-LAST:event_tf_pesquisaListaAmigoKeyReleased

private void bt_verMaisUsuariosListaAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisUsuariosListaAmigoActionPerformed
        LogAmigo.s_qtdUsuMostrar += 4;
        
        MostrarDados.s_MostrarDados.preencherListaAmigosDoAmigo(LogAmigo.s_cod, LogAmigo.s_listaAmigoSelecionada);
        
        sp_listaAmigosAmigo.getVerticalScrollBar().setValue(jt_listaAmigosDoAmigo.getSize().height); 
}//GEN-LAST:event_bt_verMaisUsuariosListaAmigoActionPerformed

private void bt_atuUsuariosListaAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atuUsuariosListaAmigoActionPerformed
        LogAmigo.s_qtdUsuMostrar = 4;
        tf_pesquisaListaAmigo.setText(""); 
        
        MostrarDados.s_MostrarDados.preencherListaAmigosDoAmigo(LogAmigo.s_cod, LogAmigo.s_listaAmigoSelecionada);
        
        sp_listaAmigosAmigo.getVerticalScrollBar().setValue(jt_listaAmigosDoAmigo.getSize().height); 
}//GEN-LAST:event_bt_atuUsuariosListaAmigoActionPerformed

private void tf_pesquisaUsuarioBatePapoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_pesquisaUsuarioBatePapoKeyReleased
        MostrarDados.s_MostrarDados.preencherListaDeAmigosBatePapo();

}//GEN-LAST:event_tf_pesquisaUsuarioBatePapoKeyReleased

private void bt_verMaisUsuariosBatePapoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_verMaisUsuariosBatePapoActionPerformed
        LogUsuario.s_qtdUsuBPMostrar += 4;
                 
        MostrarDados.s_MostrarDados.preencherListaDeAmigosBatePapo();
        
        jScrollPane6.getVerticalScrollBar().setValue(jt_listaAmigosConectados.getSize().height); 
}//GEN-LAST:event_bt_verMaisUsuariosBatePapoActionPerformed

private void bt_atualizarListaUsuariosBatePapoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atualizarListaUsuariosBatePapoActionPerformed
        //atualiza informações gerais
        new Thread(new LogoCarregando()).start();
    
        LogUsuario.s_qtdUsuBPMostrar = 4;
        tf_pesquisaUsuarioBatePapo.setText("");
        
        //verifica se os servidores estão ok
        SolicitarDados.s_SolicitarDados.estabilizaServidores(1);
        
        MostrarDados.s_MostrarDados.preencherListaDeAmigosBatePapo();

}//GEN-LAST:event_bt_atualizarListaUsuariosBatePapoActionPerformed

private void lb_ninguemBatePapoComigoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ninguemBatePapoComigoMouseReleased
        //CASO SALVO
         if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", "quem_bate_papo_comigo", "ninguem", LogUsuario.s_cod)){

                LogUsuario.s_quemBatePapoComigo = "ninguem";

                    //CONTAGEM DE ÍTENS
                    SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                        //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                        if(LogUsuario.s_mostrandoPainelAmigo){
                            MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod);
                        }
                        
                    //ENVIA A VISO PARA TODOS USUÁRIOS CONECTADOS
                    ClienteUDP.enviaAvisoParaTodosConectados(25, "geral");
                    
                    //ESCONDE LABEL DE NINGUEM BATE PAPO COMIGO (guia bate papo)
                    lb_ninguemBatePapoComigo.setVisible(false); 
                    
                    //ESCONDE LABEL DE NINGUEM BATE PAPO COMIGO (guia inicio)
                    lb_ninguemBatePapoComigoInicio.setVisible(false);
                    
                    //ATUALIZADO
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");
         }else{
         
                //ATUALIZADO
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                
                return;
         }
}//GEN-LAST:event_lb_ninguemBatePapoComigoMouseReleased

private void lb_ninguemBatePapoComigoInicioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ninguemBatePapoComigoInicioMouseReleased
        //CASO SALVO
         if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", "quem_bate_papo_comigo", "ninguem", LogUsuario.s_cod)){

                LogUsuario.s_quemBatePapoComigo = "ninguem";

                    //CONTAGEM DE ÍTENS
                    SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                        //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                        if(LogUsuario.s_mostrandoPainelAmigo){
                            MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod);
                        }
                        
                    //ENVIA A VISO PARA TODOS USUÁRIOS CONECTADOS
                    ClienteUDP.enviaAvisoParaTodosConectados(25, "geral");
                    
                    //ESCONDE LABEL DE NINGUEM BATE PAPO COMIGO (guia bate papo)
                    lb_ninguemBatePapoComigo.setVisible(false); 
                    
                    //ESCONDE LABEL DE NINGUEM BATE PAPO COMIGO (guia inicio)
                    lb_ninguemBatePapoComigoInicio.setVisible(false);
                    
                    //ATUALIZADO
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");
         }else{
         
                //ATUALIZADO
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                
                return;
         }
}//GEN-LAST:event_lb_ninguemBatePapoComigoInicioMouseReleased

private void lb_nomeIconeHumorAmigoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_nomeIconeHumorAmigoMouseEntered
        //fonte em negrito quando o mouse pasa por cima
        lb_nomeIconeHumorAmigo.setFont(new java.awt.Font("Tahoma", 1, 25));
}//GEN-LAST:event_lb_nomeIconeHumorAmigoMouseEntered

private void lb_nomeIconeHumorAmigoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_nomeIconeHumorAmigoMouseExited
        //fonte em negrito quando o mouse pasa por cima
        lb_nomeIconeHumorAmigo.setFont(new java.awt.Font("Tahoma", 0, 25));
}//GEN-LAST:event_lb_nomeIconeHumorAmigoMouseExited

private void lb_ultOcorrenciaAmigoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ultOcorrenciaAmigoMouseExited
        lb_ultOcorrenciaAmigo.setFont(new java.awt.Font("Tahoma", 0, 12));
        lb_qtdComentarioUltOcoAmigo.setFont(new java.awt.Font("Tahoma", 0, 12));
}//GEN-LAST:event_lb_ultOcorrenciaAmigoMouseExited

private void lb_qtdComentarioUltOcoAmigoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_qtdComentarioUltOcoAmigoMouseExited
        lb_ultOcorrenciaAmigo.setFont(new java.awt.Font("Tahoma", 0, 12));
        lb_qtdComentarioUltOcoAmigo.setFont(new java.awt.Font("Tahoma", 0, 12));
}//GEN-LAST:event_lb_qtdComentarioUltOcoAmigoMouseExited

private void lb_fraseAmigoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_fraseAmigoMouseEntered
        lb_fraseAmigo.setFont(new java.awt.Font("Tahoma", 1, 12));
}//GEN-LAST:event_lb_fraseAmigoMouseEntered

private void lb_fraseAmigoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_fraseAmigoMouseExited
        lb_fraseAmigo.setFont(new java.awt.Font("Tahoma", 0, 12));
}//GEN-LAST:event_lb_fraseAmigoMouseExited

private void lb_sexoAmigoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_sexoAmigoMouseEntered
        lb_sexoAmigo.setFont(new java.awt.Font("Tahoma", 1, 12));
}//GEN-LAST:event_lb_sexoAmigoMouseEntered

private void lb_sexoAmigoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_sexoAmigoMouseExited
        lb_sexoAmigo.setFont(new java.awt.Font("Tahoma", 0, 12));
}//GEN-LAST:event_lb_sexoAmigoMouseExited

private void lb_niverAmigoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_niverAmigoMouseEntered
        lb_niverAmigo.setFont(new java.awt.Font("Tahoma", 1, 12));
}//GEN-LAST:event_lb_niverAmigoMouseEntered

private void lb_niverAmigoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_niverAmigoMouseExited
        lb_niverAmigo.setFont(new java.awt.Font("Tahoma", 0, 12));
}//GEN-LAST:event_lb_niverAmigoMouseExited

private void lb_dataCadastroAmigoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_dataCadastroAmigoMouseEntered
        lb_dataCadastroAmigo.setFont(new java.awt.Font("Tahoma", 1, 12));
}//GEN-LAST:event_lb_dataCadastroAmigoMouseEntered

private void lb_dataCadastroAmigoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_dataCadastroAmigoMouseExited
        lb_dataCadastroAmigo.setFont(new java.awt.Font("Tahoma", 0, 12));
}//GEN-LAST:event_lb_dataCadastroAmigoMouseExited

private void lb_escrevaUmaOcorrenciaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_escrevaUmaOcorrenciaMouseEntered
        lb_escrevaUmaOcorrencia.setFont(new java.awt.Font("Tahoma", 1, 11));
}//GEN-LAST:event_lb_escrevaUmaOcorrenciaMouseEntered

private void lb_escrevaUmaOcorrenciaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_escrevaUmaOcorrenciaMouseExited
        lb_escrevaUmaOcorrencia.setFont(new java.awt.Font("Tahoma", 0, 11));
}//GEN-LAST:event_lb_escrevaUmaOcorrenciaMouseExited

private void lb_escrevaUmRecPrivMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_escrevaUmRecPrivMouseEntered
        lb_escrevaUmRecPriv.setFont(new java.awt.Font("Tahoma", 1, 11));
}//GEN-LAST:event_lb_escrevaUmRecPrivMouseEntered

private void lb_escrevaUmRecPrivMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_escrevaUmRecPrivMouseExited
        lb_escrevaUmRecPriv.setFont(new java.awt.Font("Tahoma", 0, 11));
}//GEN-LAST:event_lb_escrevaUmRecPrivMouseExited

    private void jt_listaArquivosUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaArquivosUsuarioMouseClicked
        if(evt.getClickCount() == 2){

            if(!LogUsuario.s_listaArquivoSelecionada.equals("disponiveis")){
                                
                    String l_pasta = null;
                    String l_nomeArquivo = null;
                    int l_linhaSelecionada = 0;
                    TableModel l_tabelaArquivo = jt_listaArquivosUsuario.getModel();


                        if(LogUsuario.s_listaArquivoSelecionada.equals("enviados")){
                            l_pasta = Diretorios.PASTA_ENVIADOS;
                        }else{
                            l_pasta = Diretorios.PASTA_BAIXADOS;
                        }           


                    //Nome do arquivo
                    l_linhaSelecionada = jt_listaArquivosUsuario.getSelectedRow();

                    l_nomeArquivo = SolicitarDados.s_SolicitarDados.
                                    recolocaAspasSimplesParaSO(l_tabelaArquivo.getValueAt(l_linhaSelecionada, 2).toString());
                    
                    boolean l_aceitou = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_ABRIR_ARQUIVO, Avisos.ABRIR_ARQUIVO, Icones.ARQUIVO_ABRIR);

                        //caso aceitou baixar...
                        if(l_aceitou){        
                            MostrarDados.s_MostrarDados.abrirArquivoSelecionado(l_nomeArquivo, l_pasta);
                            
                        }
                        else{ 
                            Moniersn.s_telaMsn.setVisible(true); 
                        }
            
            }else{
            
                    SolicitarDados.s_SolicitarDados.baixaArquivoSelecionado(jt_listaArquivosUsuario);
            }
                
        }
    }//GEN-LAST:event_jt_listaArquivosUsuarioMouseClicked

    private void jt_listaOcorrenciaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaOcorrenciaUsuarioMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){

            int l_linha =  jt_listaOcorrenciaUsuario.getSelectedRow();

            int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaOcorrenciaUsuario);

            MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "ocorrencia", jt_listaOcorrenciaUsuario);

        }  
    }//GEN-LAST:event_jt_listaOcorrenciaUsuarioMouseClicked

    private void jt_listaAtualizacoesUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaAtualizacoesUsuarioMouseClicked
        // TODO add your handling code here:
        
        if(evt.getClickCount() == 2){
            
            int l_linha =  jt_listaAtualizacoesUsuario.getSelectedRow();

            int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaAtualizacoesUsuario);

            MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "atualizacao", jt_listaAtualizacoesUsuario);
        
        }
    }//GEN-LAST:event_jt_listaAtualizacoesUsuarioMouseClicked

    private void jt_listaRecadosPubUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaRecadosPubUsuarioMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            
            int l_linha =  jt_listaRecadosPubUsuario.getSelectedRow();

            int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaRecadosPubUsuario);

            MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "recado", jt_listaRecadosPubUsuario);
        
        }
    }//GEN-LAST:event_jt_listaRecadosPubUsuarioMouseClicked

    private void jt_listaRecadosPrivMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaRecadosPrivMouseClicked
        // TODO add your handling code here:
 
        if(evt.getClickCount() == 2){
            
            int l_linha =  jt_listaRecadosPriv.getSelectedRow();

            int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaRecadosPriv);

            MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "recado", jt_listaRecadosPriv);   
        }

    }//GEN-LAST:event_jt_listaRecadosPrivMouseClicked

    private void jt_listaOcorrenciaAmigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaOcorrenciaAmigoMouseClicked
       
        if(evt.getClickCount() == 2){ 
            int l_linha =  jt_listaOcorrenciaAmigo.getSelectedRow();

            int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaOcorrenciaAmigo);

            MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "ocorrencia", jt_listaOcorrenciaAmigo);
        }
    }//GEN-LAST:event_jt_listaOcorrenciaAmigoMouseClicked

    private void jt_listaAtualizacoesAmigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaAtualizacoesAmigoMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
                int l_linha =  jt_listaAtualizacoesAmigo.getSelectedRow();

                int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaAtualizacoesAmigo);

                MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "atualizacao", jt_listaAtualizacoesAmigo);
        }
    }//GEN-LAST:event_jt_listaAtualizacoesAmigoMouseClicked

    private void jt_listaRecadosPubAmigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaRecadosPubAmigoMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){      
            int l_linha =  jt_listaRecadosPubAmigo.getSelectedRow();

            int l_codConteudo = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, jt_listaRecadosPubAmigo);

            MostrarDados.s_MostrarDados.mostraTelaComentarios(l_codConteudo, "recado", jt_listaRecadosPubAmigo);
        }
    }//GEN-LAST:event_jt_listaRecadosPubAmigoMouseClicked

    private void jt_listaArquivosAmigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_listaArquivosAmigoMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){   
            
                SolicitarDados.s_SolicitarDados.baixaArquivoSelecionado(jt_listaArquivosAmigo);
        }
    }//GEN-LAST:event_jt_listaArquivosAmigoMouseClicked

    private void bt_atualizarInfoAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_atualizarInfoAmigoActionPerformed
       
        //logo carregando...
        new Thread(new LogoCarregando()).start();

        LogAmigo.s_qtdUsuMostrar = 4;
        tf_pesquisaListaAmigo.setText(""); 
        
        LogAmigo.s_qtdOcoAMostrar = 4;
        tf_conteudoOcoPesquisadoAmigo.setText(""); 
        
        LogAmigo.s_qtdAtuAMostrar = 4;
        tf_conteudoAtuPesquisadoAmigo.setText(""); 
        
        LogAmigo.s_qtdRecPubAMostrar = 4;
        tf_conteudoRecPubPesquisadoAmigo.setText("");         
        
        LogAmigo.s_qtdArqAMostrar = 4;
        tf_nomeArqPesquisadoAmigo.setText(""); 
        
        //inabilitação de botao de comentário de ocorrencia
        bt_comentarOcorrenciaAmigo.setEnabled(false);

        //inabilitação de botao de comentário de atualização
        bt_comentarAtuAmigo.setEnabled(false);

        //inabilitação de botao de comentário de rec público
        bt_comentarRecPubAmigo.setEnabled(false);

        //inabilitação de botao de comentário de arquivo
        bt_comentarArquivoSelecionadoAmigo.setEnabled(false);

        //inabilitação de botao de baixar arquivo selecionado do amigo
        bt_baixarArquivoSelecionadoAmigo.setEnabled(false);
                

            if(LogAmigo.s_status.equals("conectado"))
                 SolicitarDados.s_SolicitarDados.definicaoDeUsuarioConectado(LogAmigo.s_cod, LogAmigo.s_host, LogAmigo.s_portaTCP);

        MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod);   

    }//GEN-LAST:event_bt_atualizarInfoAmigoActionPerformed

    private void bt_enviarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_enviarArquivoActionPerformed
        if(CompartilhaArquivo.s_telaCompArquivo != null) 
            CompartilhaArquivo.s_telaCompArquivo.setVisible(true);
        else
            MostrarDados.s_MostrarDados.mostraTelaCompartilhamentoArquivo("unico");
    }//GEN-LAST:event_bt_enviarArquivoActionPerformed

    private void bt_addExcAceEspAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addExcAceEspAmigoActionPerformed
       /*
         dependendo do tipo de usuário...
         * - se aceito-ok - excluir
         * - se aceito-bloqueei - excluir
         * - se aceito-bloqueou-me - excluir
         * - se aceito-bloqueados - excluir
         * - se nao-aceito-convidou-me - aceitar
         * - se nao-aceito-convidei - cancelar solicitacao
         * - se nao-amigo - convidar
         *
         */       
        
        String l_tipoUsuario = LogAmigo.s_tipoAmigo;

       if(l_tipoUsuario.equals("aceito-ok") ||
          l_tipoUsuario.equals("aceito-bloqueei") ||
          l_tipoUsuario.equals("aceito-bloqueou-me") ||
          l_tipoUsuario.equals("aceito-bloqueados"))
           //remover o amigo em questao
           GravarDados.s_GravarDados.removerAmigo(LogUsuario.s_cod, LogAmigo.s_cod);

       else if(l_tipoUsuario.equals("nao-aceito-convidei"))
           //remover o amigo em questao
           GravarDados.s_GravarDados.cancelarSolicitacaoAmigo(LogAmigo.s_cod);

       else if(l_tipoUsuario.equals("nao-amigo"))
           //convidar um amigo
           MostrarDados.s_MostrarDados.mostraTelaConvite(lb_nomeIconeHumorAmigo.getText(), lb_nomeIconeHumorAmigo.getIcon(), LogAmigo.s_cod);

       else if(l_tipoUsuario.equals("nao-aceito-convidou-me")){
            //CHAMA A TELA DE SOLICITAÇÃO DE AMIZADE
            MostrarDados.s_MostrarDados.mostraSolicitacaoDeAmizade(LogAmigo.s_cod);
        }
    }//GEN-LAST:event_bt_addExcAceEspAmigoActionPerformed

    private void bt_comprimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_comprimentoActionPerformed
        ClienteUDP.enviaAvisoParaUnicoAmigoConectado(LogAmigo.s_cod, 5);//5 = chamar atencao
    }//GEN-LAST:event_bt_comprimentoActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                s_telaMsn = new Moniersn(new javax.swing.JFrame(), true);
                s_telaMsn.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                s_telaMsn.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.ButtonGroup bg_quemBatePapoComigo;
    public javax.swing.ButtonGroup bg_visuAmigos;
    public javax.swing.ButtonGroup bg_visuAmigosAmigos;
    private javax.swing.ButtonGroup bg_visuArquivos;
    private javax.swing.ButtonGroup bg_visuAtualizacoes;
    private javax.swing.ButtonGroup bg_visuOcorrencia;
    private javax.swing.ButtonGroup bg_visuRecadosPriv;
    private javax.swing.ButtonGroup bg_visuRecadosPub;
    public javax.swing.JButton bt_abrirArquivoSelecionado;
    public javax.swing.JButton bt_abrirLocalArquivos;
    public static javax.swing.JButton bt_addExcAceEspAmigo;
    public static javax.swing.JButton bt_addOcorrencia;
    private javax.swing.JButton bt_atuListaArqAmigo;
    private javax.swing.JButton bt_atuListaArquivosUsuario;
    private javax.swing.JButton bt_atuListaAtuAmigo;
    private javax.swing.JButton bt_atuListaAtuUsuario;
    private javax.swing.JButton bt_atuListaOcoAmigo;
    private javax.swing.JButton bt_atuListaOcoUsuario;
    private javax.swing.JButton bt_atuListaRecPrivUsuario;
    private javax.swing.JButton bt_atuListaRecPubAmigo;
    private javax.swing.JButton bt_atuListaRecPubUsuario;
    public static javax.swing.JButton bt_atuUsuariosListaAmigo;
    private javax.swing.JButton bt_atualizar;
    public static javax.swing.JButton bt_atualizarInfoAmigo;
    private javax.swing.JButton bt_atualizarListaUsuarios;
    private javax.swing.JButton bt_atualizarListaUsuariosBatePapo;
    public javax.swing.JButton bt_baixarArquivoSelecionado;
    public javax.swing.JButton bt_baixarArquivoSelecionadoAmigo;
    public javax.swing.JButton bt_batePapo;
    public static javax.swing.JButton bt_bloqDesblocAmigo;
    public javax.swing.JButton bt_comentarArquivoSelecionadoAmigo;
    public javax.swing.JButton bt_comentarArquivoSelecionadoUsuario;
    public javax.swing.JButton bt_comentarAtuAmigo;
    public javax.swing.JButton bt_comentarAtuUsuario;
    public javax.swing.JButton bt_comentarOcorrencia;
    public javax.swing.JButton bt_comentarOcorrenciaAmigo;
    public javax.swing.JButton bt_comentarRecPriv;
    public javax.swing.JButton bt_comentarRecPubAmigo;
    public javax.swing.JButton bt_comentarRecPublico;
    public javax.swing.JButton bt_compartilharArquivoGeral;
    public javax.swing.JButton bt_comprimento;
    private javax.swing.JButton bt_configuracoes;
    private javax.swing.JButton bt_desconectar;
    public javax.swing.JButton bt_encaminharArquivo;
    public javax.swing.JButton bt_enviarArquivo;
    public static javax.swing.JButton bt_enviarRecInst;
    private javax.swing.JButton bt_enviarRecPriv;
    private javax.swing.JButton bt_enviarRecPub;
    public javax.swing.JButton bt_escondePainel;
    public javax.swing.JButton bt_excluirAtualizacoes;
    public javax.swing.JButton bt_excluirOcorrencia;
    public javax.swing.JButton bt_excluirRecPriv;
    public javax.swing.JButton bt_excluirRecPub;
    private javax.swing.JButton bt_inicio;
    public static javax.swing.JButton bt_limpaCampoRecInst;
    public static javax.swing.JButton bt_limparConversa;
    public javax.swing.JButton bt_marcarComoLidoRecPriv;
    public javax.swing.JButton bt_marcarComoLidoRecPub;
    public javax.swing.JButton bt_removerArquivoSelecionado;
    private javax.swing.JButton bt_verMaisArqAmigo;
    private javax.swing.JButton bt_verMaisArquivosUsuario;
    private javax.swing.JButton bt_verMaisAtuAmigo;
    private javax.swing.JButton bt_verMaisAtuUsuario;
    private javax.swing.JButton bt_verMaisOcoAmigo;
    private javax.swing.JButton bt_verMaisOcoUsuario;
    private javax.swing.JButton bt_verMaisRecPrivUsuario;
    private javax.swing.JButton bt_verMaisRecPubAmigo;
    private javax.swing.JButton bt_verMaisRecPubUsuario;
    private javax.swing.JButton bt_verMaisUsuarios;
    private javax.swing.JButton bt_verMaisUsuariosBatePapo;
    public static javax.swing.JButton bt_verMaisUsuariosListaAmigo;
    public static javax.swing.JButton bt_visuPainelAmigo;
    public static javax.swing.JCheckBox cb_anonimo;
    public static javax.swing.JCheckBox cb_enviarParaTodosConectados;
    public static javax.swing.JComboBox cb_tipoAmigos;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    public static javax.swing.JPanel jp_arquivosAmigo;
    public static javax.swing.JPanel jp_arquivosUsuario;
    public static javax.swing.JPanel jp_atualizacoesAmigo;
    public static javax.swing.JPanel jp_atualizacoesUsuario;
    public static javax.swing.JPanel jp_batePapo;
    public static javax.swing.JPanel jp_contato;
    public static javax.swing.JPanel jp_educacional;
    public static javax.swing.JPanel jp_inicioAmigo;
    public static javax.swing.JPanel jp_inicioUsuario;
    public static javax.swing.JPanel jp_ocorrenciaAmigo;
    public static javax.swing.JPanel jp_ocorrenciaUsuario;
    public static javax.swing.JPanel jp_opcoesAmigosAmigo;
    public static javax.swing.JPanel jp_pessoal;
    public static javax.swing.JPanel jp_profissional;
    public static javax.swing.JPanel jp_quemMeVe;
    public static javax.swing.JPanel jp_recPrivados;
    public static javax.swing.JPanel jp_recPublicosAmigo;
    public static javax.swing.JPanel jp_recPublicosUsuario;
    public static javax.swing.JTable jt_listaAmigosConectados;
    public static javax.swing.JTable jt_listaAmigosDoAmigo;
    public static javax.swing.JTable jt_listaAmigosUsuario;
    public static javax.swing.JTable jt_listaArquivosAmigo;
    public static javax.swing.JTable jt_listaArquivosUsuario;
    public static javax.swing.JTable jt_listaAtualizacoesAmigo;
    public static javax.swing.JTable jt_listaAtualizacoesUsuario;
    public static javax.swing.JTable jt_listaOcorrenciaAmigo;
    public static javax.swing.JTable jt_listaOcorrenciaUsuario;
    public static javax.swing.JTable jt_listaRecadosPriv;
    public static javax.swing.JTable jt_listaRecadosPubAmigo;
    public static javax.swing.JTable jt_listaRecadosPubUsuario;
    public static javax.swing.JTabbedPane jtp_painelTabuladoAmigo;
    public static javax.swing.JTabbedPane jtp_painelTabuladoUsuario;
    public static javax.swing.JLabel lb_aEmpresaEh;
    public static javax.swing.JLabel lb_aInstituicaoEh;
    public static javax.swing.JLabel lb_adoro;
    public static javax.swing.JLabel lb_atividadeEmp;
    public static javax.swing.JLabel lb_bairro;
    public static javax.swing.JLabel lb_bebida;
    public static javax.swing.JLabel lb_blog;
    public static javax.swing.JLabel lb_cantada;
    public static javax.swing.JLabel lb_cargo;
    public static javax.swing.JLabel lb_celClaro;
    public static javax.swing.JLabel lb_celOi;
    public static javax.swing.JLabel lb_celOutro;
    public static javax.swing.JLabel lb_celTim;
    public static javax.swing.JLabel lb_celVivo;
    public static javax.swing.JLabel lb_cidade;
    public static javax.swing.JLabel lb_conselho;
    public static javax.swing.JLabel lb_cor;
    public static javax.swing.JLabel lb_curso;
    public static javax.swing.JLabel lb_dataAdmissao;
    public static javax.swing.JLabel lb_dataCadastroAmigo;
    public static javax.swing.JLabel lb_dataCadastroUsuario;
    public static javax.swing.JLabel lb_dataEntradaCurso;
    public static javax.swing.JLabel lb_dataNiverUsuario;
    public static javax.swing.JLabel lb_dataTerminoCurso;
    public static javax.swing.JLabel lb_desejo;
    public static javax.swing.JLabel lb_disciplinaPreferida;
    public static javax.swing.JLabel lb_emailBol;
    public static javax.swing.JLabel lb_emailCorpEmp;
    public static javax.swing.JLabel lb_emailGmail;
    public static javax.swing.JLabel lb_emailHotmail;
    public static javax.swing.JLabel lb_emailIg;
    public static javax.swing.JLabel lb_emailOutro;
    public static javax.swing.JLabel lb_emailYahoo;
    public static javax.swing.JLabel lb_escrevaUmRecPriv;
    public static javax.swing.JLabel lb_escrevaUmaOcorrencia;
    public static javax.swing.JLabel lb_esperanca;
    public static javax.swing.JLabel lb_esporte;
    public static javax.swing.JLabel lb_esporte4;
    public static javax.swing.JLabel lb_esporte5;
    public static javax.swing.JLabel lb_esporte6;
    public static javax.swing.JLabel lb_estaFaltandoInst;
    private javax.swing.JLabel lb_estadoAtualUsuario3;
    public static javax.swing.JLabel lb_estadoCivil;
    public static javax.swing.JLabel lb_facebook;
    public static javax.swing.JLabel lb_faxEmp;
    public static javax.swing.JLabel lb_filme;
    public static javax.swing.JLabel lb_fisico;
    public static javax.swing.JLabel lb_fone;
    public static javax.swing.JLabel lb_foneEmp;
    public static javax.swing.JLabel lb_fraseAmigo;
    public static javax.swing.JLabel lb_fraseUsuario;
    public static javax.swing.JLabel lb_habilitarAudio;
    public static javax.swing.JLabel lb_hobby;
    public static javax.swing.JLabel lb_horarioAula;
    public static javax.swing.JLabel lb_horarioIntervalo;
    public static javax.swing.JLabel lb_horarioRefeicao;
    public static javax.swing.JLabel lb_horarioServico;
    private javax.swing.JLabel lb_iconeBatePapo;
    public static javax.swing.JLabel lb_iconeOcorrencia;
    private javax.swing.JLabel lb_iconeRecPriv;
    public static javax.swing.JLabel lb_instituicaoEnsino;
    public static javax.swing.JLabel lb_linkedin;
    public static javax.swing.JLabel lb_lugar;
    public static javax.swing.JLabel lb_mania;
    public static javax.swing.JLabel lb_minhaTurmaEh;
    public static javax.swing.JLabel lb_mostrarNaoMostrarNome;
    public static javax.swing.JLabel lb_mostrarNaoMostrarNomeAmigo;
    public static javax.swing.JLabel lb_musica;
    public static javax.swing.JLabel lb_myspace;
    public static javax.swing.JLabel lb_nacionalidade;
    public static javax.swing.JLabel lb_naoGostoDeEmp;
    public static javax.swing.JLabel lb_naoGostoDeInst;
    public static javax.swing.JLabel lb_nasHorasVagas;
    public static javax.swing.JLabel lb_naturalidade;
    public static javax.swing.JLabel lb_ninguemBatePapoComigo;
    public static javax.swing.JLabel lb_ninguemBatePapoComigoInicio;
    public static javax.swing.JLabel lb_niverAmigo;
    public static javax.swing.JLabel lb_nomeEmpresa;
    public static javax.swing.JLabel lb_nomeIconeAmigoBatePapo;
    public static javax.swing.JLabel lb_nomeIconeHumorAmigo;
    public static javax.swing.JLabel lb_nomeIconeHumorUsuario;
    public static javax.swing.JLabel lb_numero;
    public static javax.swing.JLabel lb_odeio;
    public static javax.swing.JLabel lb_orkut;
    public static javax.swing.JLabel lb_passeio;
    public static javax.swing.JLabel lb_pesadelo;
    public static javax.swing.JLabel lb_pesquisarArq;
    public static javax.swing.JLabel lb_pesquisarArqAmigo;
    public static javax.swing.JLabel lb_pesquisarAtu;
    public static javax.swing.JLabel lb_pesquisarAtuAmigo;
    public static javax.swing.JLabel lb_pesquisarOco;
    public static javax.swing.JLabel lb_pesquisarOcoAmigo;
    public static javax.swing.JLabel lb_pesquisarRecPriv;
    public static javax.swing.JLabel lb_pesquisarRecPub;
    public static javax.swing.JLabel lb_pesquisarRecPubAmigo;
    public static javax.swing.JLabel lb_piorDisciplina;
    public static javax.swing.JLabel lb_prato;
    public static javax.swing.JLabel lb_prendeFormNaFrente;
    public static javax.swing.JLabel lb_prendeFormNaFrente1;
    public static javax.swing.JLabel lb_prendeFormNaFrente10;
    public static javax.swing.JLabel lb_prendeFormNaFrente11;
    public static javax.swing.JLabel lb_prendeFormNaFrente14;
    public static javax.swing.JLabel lb_prendeFormNaFrente15;
    public static javax.swing.JLabel lb_prendeFormNaFrente16;
    public static javax.swing.JLabel lb_prendeFormNaFrente2;
    public static javax.swing.JLabel lb_prendeFormNaFrente3;
    public static javax.swing.JLabel lb_prendeFormNaFrente4;
    public static javax.swing.JLabel lb_prendeFormNaFrente5;
    public static javax.swing.JLabel lb_prendeFormNaFrente6;
    public static javax.swing.JLabel lb_prendeFormNaFrente7;
    public static javax.swing.JLabel lb_prendeFormNaFrente8;
    public static javax.swing.JLabel lb_prendeFormNaFrente9;
    public static javax.swing.JLabel lb_qtdArqEncontrado;
    public static javax.swing.JLabel lb_qtdArqEncontradoAmigo;
    public static javax.swing.JLabel lb_qtdAtuEncontrada;
    public static javax.swing.JLabel lb_qtdAtuEncontradaAmigo;
    public static javax.swing.JLabel lb_qtdComentarioUltOcoAmigo;
    public static javax.swing.JLabel lb_qtdComentarioUltOcoUsuario;
    public static javax.swing.JLabel lb_qtdConvites;
    public static javax.swing.JLabel lb_qtdOcoEncontrada;
    public static javax.swing.JLabel lb_qtdOcoEncontradaAmigo;
    public static javax.swing.JLabel lb_qtdRecInst;
    public static javax.swing.JLabel lb_qtdRecPrivEncontrado;
    public static javax.swing.JLabel lb_qtdRecPubEncontrado;
    public static javax.swing.JLabel lb_qtdRecPubEncontradoAmigo;
    public static javax.swing.JLabel lb_qtdTotalUsuario;
    public static javax.swing.JLabel lb_qtdTotalUsuarioBatePapo;
    public static javax.swing.JLabel lb_qtdUsuarioListaBatePapo;
    public static javax.swing.JLabel lb_qtdUsuarioListaUsuario;
    public static javax.swing.JLabel lb_qtdUsuariosListaAmigo;
    public static javax.swing.JLabel lb_quemSou;
    public static javax.swing.JLabel lb_ramalEmp;
    public static javax.swing.JLabel lb_rua;
    public static javax.swing.JLabel lb_seguranca;
    public static javax.swing.JLabel lb_sempreGostoDeEmp;
    public static javax.swing.JLabel lb_sempreGostoDeInst;
    public static javax.swing.JLabel lb_setor;
    public static javax.swing.JLabel lb_sexoAmigo;
    public static javax.swing.JLabel lb_sexoUsuario;
    public static javax.swing.JLabel lb_skype;
    public static javax.swing.JLabel lb_soEstaFaltandoEmp;
    public static javax.swing.JLabel lb_sonho;
    public static javax.swing.JLabel lb_time;
    public static javax.swing.JLabel lb_tipoUsuario;
    public static javax.swing.JLabel lb_turma;
    public static javax.swing.JLabel lb_twitter;
    public static javax.swing.JLabel lb_ultOcorrenciaAmigo;
    public static javax.swing.JLabel lb_ultimaOcorrenciaUsuario;
    public static javax.swing.JLabel lb_viagem;
    public static javax.swing.JLabel lb_vontade;
    public static javax.swing.JLabel lb_vouMelhorarEmEmp;
    public static javax.swing.JLabel lb_vouMelhorarEmInst;
    public static javax.swing.JPanel painel_1_amigo;
    public static javax.swing.JPanel painel_2_amigo;
    public static javax.swing.JPanel painel_2_amigo1;
    public static javax.swing.JPanel painel_2_amigo2;
    public static javax.swing.JRadioButton rb_atuDeAmigos;
    public static javax.swing.JRadioButton rb_atuParaTodos;
    public static javax.swing.JRadioButton rb_atualizacoesMinhas;
    public static javax.swing.JRadioButton rb_ocoEnviadas;
    public static javax.swing.JRadioButton rb_ocoRecebidas;
    public static javax.swing.JRadioButton rb_qtdAmigosComuns;
    public static javax.swing.JRadioButton rb_qtdTotalAmigosAmigo;
    public static javax.swing.JRadioButton rb_recadosPrivEnviados;
    public static javax.swing.JRadioButton rb_recadosPrivNaoLidos;
    public static javax.swing.JRadioButton rb_recadosPubEnviados;
    public static javax.swing.JRadioButton rb_recadosPubNaoLidos;
    public static javax.swing.JRadioButton rb_soAmigosBatemPapoComigo;
    public static javax.swing.JRadioButton rb_todosArquivosBaixados;
    public static javax.swing.JRadioButton rb_todosArquivosDisponiveis;
    public static javax.swing.JRadioButton rb_todosArquivosEnviados;
    public static javax.swing.JRadioButton rb_todosBatemPapoComigo;
    public static javax.swing.JRadioButton rb_todosRecadosPrivRecebidos;
    public static javax.swing.JRadioButton rb_todosRecadosPubRecebidos;
    public static javax.swing.JLabel s_lb_adoro;
    public static javax.swing.JLabel s_lb_adoro2;
    public static javax.swing.JLabel s_lb_adoro3;
    public static javax.swing.JLabel s_lb_adoro4;
    public static javax.swing.JLabel s_lb_adoro5;
    public static javax.swing.JLabel s_lb_bairro1;
    public static javax.swing.JLabel s_lb_blog2;
    public static javax.swing.JLabel s_lb_blog5;
    public static javax.swing.JLabel s_lb_blog6;
    public static javax.swing.JLabel s_lb_blog7;
    public static javax.swing.JLabel s_lb_blog8;
    public static javax.swing.JLabel s_lb_cantada;
    public static javax.swing.JLabel s_lb_celular1;
    public static javax.swing.JLabel s_lb_celular3;
    public static javax.swing.JLabel s_lb_celular4;
    public static javax.swing.JLabel s_lb_celular5;
    public static javax.swing.JLabel s_lb_celular6;
    public static javax.swing.JLabel s_lb_cidade1;
    public static javax.swing.JLabel s_lb_conselho;
    public static javax.swing.JLabel s_lb_conselho1;
    public static javax.swing.JLabel s_lb_conselho2;
    public static javax.swing.JLabel s_lb_conselho3;
    public static javax.swing.JLabel s_lb_conselho4;
    public static javax.swing.JLabel s_lb_conselho5;
    public static javax.swing.JLabel s_lb_cor;
    public static javax.swing.JLabel s_lb_cor1;
    public static javax.swing.JLabel s_lb_cor2;
    public static javax.swing.JLabel s_lb_cor3;
    public static javax.swing.JLabel s_lb_cor4;
    public static javax.swing.JLabel s_lb_cor5;
    public static javax.swing.JLabel s_lb_cor6;
    public static javax.swing.JLabel s_lb_email1;
    public static javax.swing.JLabel s_lb_email3;
    public static javax.swing.JLabel s_lb_email4;
    public static javax.swing.JLabel s_lb_email5;
    public static javax.swing.JLabel s_lb_email6;
    public static javax.swing.JLabel s_lb_email7;
    public static javax.swing.JLabel s_lb_esporte;
    public static javax.swing.JLabel s_lb_esporte2;
    public static javax.swing.JLabel s_lb_esporte3;
    public static javax.swing.JLabel s_lb_esporte4;
    public static javax.swing.JLabel s_lb_esporte5;
    public static javax.swing.JLabel s_lb_estado_civil;
    public static javax.swing.JLabel s_lb_filme;
    public static javax.swing.JLabel s_lb_filme2;
    public static javax.swing.JLabel s_lb_filme3;
    public static javax.swing.JLabel s_lb_filme4;
    public static javax.swing.JLabel s_lb_fisico;
    public static javax.swing.JLabel s_lb_fisico2;
    public static javax.swing.JLabel s_lb_fone1;
    public static javax.swing.JLabel s_lb_lugar;
    public static javax.swing.JLabel s_lb_mania;
    public static javax.swing.JLabel s_lb_musica;
    public static javax.swing.JLabel s_lb_musica2;
    public static javax.swing.JLabel s_lb_musica3;
    public static javax.swing.JLabel s_lb_nacionalidade;
    public static javax.swing.JLabel s_lb_nacionalidade2;
    public static javax.swing.JLabel s_lb_nacionalidade3;
    public static javax.swing.JLabel s_lb_naturalidade;
    public static javax.swing.JLabel s_lb_naturalidade3;
    public static javax.swing.JLabel s_lb_naturalidade4;
    public static javax.swing.JLabel s_lb_naturalidade5;
    public static javax.swing.JLabel s_lb_naturalidade6;
    public static javax.swing.JLabel s_lb_niver2;
    public static javax.swing.JLabel s_lb_niver3;
    public static javax.swing.JLabel s_lb_nome2;
    public static javax.swing.JLabel s_lb_nome3;
    public static javax.swing.JLabel s_lb_odeio;
    public static javax.swing.JLabel s_lb_odeio2;
    public static javax.swing.JLabel s_lb_odeio3;
    public static javax.swing.JLabel s_lb_orkut1;
    public static javax.swing.JLabel s_lb_quem_sou;
    public static javax.swing.JLabel s_lb_quem_sou2;
    public static javax.swing.JLabel s_lb_quem_sou3;
    public static javax.swing.JLabel s_lb_quem_sou4;
    public static javax.swing.JLabel s_lb_rua1;
    public static javax.swing.JLabel s_lb_rua3;
    public static javax.swing.JLabel s_lb_twiter1;
    private javax.swing.JScrollPane sp_arquivosAmigo;
    private javax.swing.JScrollPane sp_arquivosUsuario;
    private javax.swing.JScrollPane sp_atualizacoesAmigo;
    public javax.swing.JScrollPane sp_atualizacoesUsuario;
    public static javax.swing.JScrollPane sp_listaAmigosAmigo;
    private javax.swing.JScrollPane sp_listaAmigosUsuario;
    private javax.swing.JScrollPane sp_ocorrenciaUsuario;
    private javax.swing.JScrollPane sp_ocorrenciasAmigo;
    private javax.swing.JScrollPane sp_recadosPriv;
    private javax.swing.JScrollPane sp_recadosPubAmigo;
    private javax.swing.JScrollPane sp_recadosPubUsuario;
    public static javax.swing.JTextField tf_conteudoAtuPesquisado;
    public static javax.swing.JTextField tf_conteudoAtuPesquisadoAmigo;
    public static javax.swing.JTextField tf_conteudoOcoPesquisado;
    public static javax.swing.JTextField tf_conteudoOcoPesquisadoAmigo;
    public static javax.swing.JTextField tf_conteudoRecPrivPesquisado;
    public static javax.swing.JTextField tf_conteudoRecPubPesquisado;
    public static javax.swing.JTextField tf_conteudoRecPubPesquisadoAmigo;
    public static javax.swing.JTextField tf_nomeArqPesquisado;
    public static javax.swing.JTextField tf_nomeArqPesquisadoAmigo;
    public static javax.swing.JTextField tf_ocorrencia;
    public static javax.swing.JTextField tf_pesquisaListaAmigo;
    public static javax.swing.JTextField tf_pesquisaUsuario;
    public static javax.swing.JTextField tf_pesquisaUsuarioBatePapo;
    public static javax.swing.JTextField tf_recadoInst;
    public javax.swing.JTextField tf_recadoPriv;
    private javax.swing.JTextField tf_recadoPub;
    public static javax.swing.JTextPane tp_recadosInstantaneos;
    // End of variables declaration//GEN-END:variables
 

}



