/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;
import javax.swing.JPanel;
/**
 *
 * @author Ayrton Monier
 */

//guarda as informações do usuário e amigos
public class LogUsuario{

    //INFORMAÇÕES BÁSICAS DO USUÁRIO
    public static int    s_cod;
    public static String s_moniersn;
    public static String s_nome;
    public static String s_sobrenome;
    public static String s_sexo;

    //SEGURANÇA
    public static String s_senha;
    public static String s_pergutaSecreta;
    public static String s_respostaSecreta;

    //INFORMAÇÕES DE CONEXAO
    public static String s_status;
    public static String s_host;
    public static int    s_portaUDP;
    public static int    s_portaTCP;

    //CONFIGURAÇÕES
    public static String s_mostrarBalaoAviso;
    public static String s_emitirAvisoSonovo;
    public static String s_mostrarNome;
    public static String s_solicitarSenhaAoMostrar;
    public static String s_solicitarSenhaAoConfig;

    public static String s_quemVeInfoBasicas;
    public static String s_quemVeListaAmigos;
    public static String s_quemVeInfoContato;
    public static String s_quemVeInfoPessoal;
    public static String s_quemVeInfoEducacional;
    public static String s_quemVeInfoProfissional;
    public static String s_quemBatePapoComigo;
    public static String s_quemMeEnviaArquivo;
    public static String s_quemVeOcorrencias;
    public static String s_quemVeRecPub;

    //INFRMAÇÕES EM TEMPO DE EXECUÇÃO
    public static String s_listaAmigosAtual;
    public static String s_listaRecPubSelecionada;//recebidos, enviados, nao lidos
    public static String s_listaRecPrivSelecionada;//recebidos, enviados, nao lidos
    public static String s_listaOcorrenciaSelecionada;//recebidos, enviados
    public static String s_listaArquivoSelecionada;//recebidos, enviados
    public static String s_listaAtualizacoesSelecionada;//recebidos(amigos), enviados(minhas)
    public static JPanel s_abaAtualSelecionada;

    public static String s_dataHoraCadastroBD;
    public static String s_dataHoraUltimoLogBD;

    public static int    s_codLogConexao;    

    public static boolean s_mostrandoPainelAmigo;
    public static boolean s_mostrandoSolicitacaoAmizade;
    public static boolean s_mostrandoTelaConvite;
    public static int     s_codAmigoSolicitacao;
    public static int     s_codAmigoConvite;
    public static int     s_codUltimaOcorrencia;
    public static String  s_dataHoraUltimaOcorrencia;

    public static boolean s_conviteRecebido = false;

    public static int s_qtdRecInstRec = 0;

    //usuarios
    public static int s_qtdTotUsuBatePapo = 0;
    public static int s_qtdTotUsu = 0;
    public static int s_qtdTotUsuConect = 0;
    public static int s_qtdTotUsuConv = 0;
    public static int s_qtdTotConv = 0;
    public static int s_qtdTotConvRec = 0;
    public static int s_qtdTotAmi = 0;
    public static int s_qtdTotAmiConect = 0;
    public static int s_qtdTotAmiBloq = 0;

    public static String[] s_humorUsuarios;
    public static String[] s_codTipoUsuarios;

    //recados públicos
    public static int s_qtdRecPubRecNaoLido = 0;
    public static int s_qtdTotRecPubRecebidos = 0;
    public static int s_qtdTotRecPubEnviados = 0;

    //recados privados
    public static int s_qtdRecPrivRecNaoLido = 0;
    public static int s_qtdTotRecPrivRecebidos = 0;
    public static int s_qtdTotRecPrivEnviados = 0;

    //ocorrencias
    public static int s_qtdOcoRecRecente = 0;
    public static int s_qtdOcoRecDesdUltLogout = 0;
    public static int s_qtdTotOcoRecebidas = 0;
    public static int s_qtdTotOcoEnviadas = 0;

    //atualizacoes
    public static int s_qtdTotalAtuEnviadas = 0;
    public static int s_qtdTotAtuRecebidasDeEstranhos = 0;
    public static int s_qtdTotAtuRecebidasDeAmigos = 0;
    public static int s_qtdTotAtuRecebidas = 0;

    public static int s_qtdAtuRecDesdUltLogoutDeEstranhos = 0;
    public static int s_qtdAtuRecDesdUltLogoutDeAmigos = 0;
    public static int s_qtdTotAtuRecDesdUltLogout = 0;

    public static int s_qtdAtuRecRecenteDeEstranhos = 0;
    public static int s_qtdAtuRecRecenteDeAmigos = 0;
    public static int s_qtdTotAtuRecRecente = 0;

    //arquivos
    public static int s_qtdTotArqDisponiveis = 0;
    public static int s_qtdTotArqBaixados = 0;
    public static int s_qtdTotArqEnviados = 0;

    public static int s_qtdCliPuxandoArq;

    public static String s_localDeArquivosDoUsuario = null;
    public static boolean s_localDeArquivosDoUsuarioConsistente;

    public static int s_qtdComentAMostrar = 4;
    
    public static String s_tituloBordaEmissorConteudoComent;
    public static String s_tituloBordaDestinatarioConteudoComent;
    
    public static int s_qtdUsuBPMostrar = 4;
    public static int s_qtdUsuMostrar = 4;
    public static int s_qtdOcoAMostrar = 4;
    public static int s_qtdAtuAMostrar = 4;
    public static int s_qtdRecPubAMostrar = 4;
    public static int s_qtdRecPrivAMostrar = 4;
    public static int s_qtdArqAMostrar = 4;
    
}
