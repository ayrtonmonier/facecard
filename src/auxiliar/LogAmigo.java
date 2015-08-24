/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;
import javax.swing.JPanel;
import formularios.Moniersn;

/**
 *
 * @author Ayrton Monier
 */
public class LogAmigo {


    //INFORMAÇÕES BÁSICAS DO USUÁRIO
    public static int    s_cod;
    public static String s_moniersn;
    public static String s_nome;
    public static String s_sobrenome;
    public static String s_iconeHumor;

    //INFORMAÇÕES DE CONEXAO
    public static String s_status;
    public static String s_host;
    public static int    s_portaUDP;
    public static int    s_portaTCP;

    //CONFIGURAÇÕES
    public static String s_quemBatePapoComigo;
    public static String s_quemVeRecPub;
    public static String s_quemVeOco;

    public static String s_tipoAmigo;

    public static JPanel s_abaAtualSelecionada;

    public static String s_listaAmigoSelecionada = Avisos.TEXTO_AMIGOS;

    public static int s_codUltimaOcorrencia;
    public static String s_dataHoraUltimaOcorrencia;
    
    public static int s_qtdTotOcoEnviadas = 0;
    
    public static int s_qtdTotAtuEnviadas = 0;
    
    public static int s_qtdTotRecPubEnviados = 0;
    
    public static int s_qtdTotArqEnviados = 0;
    
    public static int s_qtdUsuMostrar = 4;
    public static int s_qtdOcoAMostrar = 4;
    public static int s_qtdAtuAMostrar = 4;
    public static int s_qtdRecPubAMostrar = 4;
    public static int s_qtdArqAMostrar = 4;


}
