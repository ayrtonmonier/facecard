/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;



import auxiliar.threads.AbreArquivoViaRunTime;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import formularios.PaletaEscolhaCor;
import formularios.CadastroUsuario;
import formularios.Comentario;
import formularios.CompartilhaArquivo;
import formularios.Moniersn;
import formularios.Configuracoes;
import formularios.ConfirmaSenha;
import java.awt.Desktop;
import auxiliar.sockets.ClienteTCP;
import auxiliar.sockets.ServidorTCP;
import auxiliar.sockets.ServidorUDP;
import auxiliar.threads.EstabilizaConectados;
import formularios.*;
import java.net.*;
import java.io.*;
import java.nio.channels.FileChannel;
import javax.swing.*;



/**
 *
 * @author Ayrton Monier
 */
public class SolicitarDados {

    public static SolicitarDados s_SolicitarDados = new SolicitarDados();

    //atualiza o status no banco de dados
    public static String pegaSituacaoRecado(int l_codRecado){

            String l_situacao = null;

            //executa query
            Dados.s_conexaoBanco.executeSELECT("SELECT visto "
                                               + "FROM recado "
                                               + "WHERE cod_recado = "+l_codRecado);
            try{
                Dados.s_conexaoBanco.c_resultset.first();
                l_situacao =  Dados.s_conexaoBanco.c_resultset.getString("visto");
            }
            catch(Exception e){
                System.err.println("A situação não pôde ser buscada.    \nErro: "+e);

            }

            return l_situacao;
    }

        //atualiza o status no banco de dados
    public static int pegaCodigoPeloApelido(String l_moniersn){

            int l_codigo = 0;

            //executa query
            Dados.s_conexaoBanco.executeSELECT("SELECT cod_usuario "
                                               + "FROM usuario "
                                               + "WHERE moniersn = '"+l_moniersn+"'");
            try{
                    Dados.s_conexaoBanco.c_resultset.first();

                    l_codigo =  Dados.s_conexaoBanco.c_resultset.getInt("cod_usuario");
            }
            catch(Exception e){
                System.err.println("O código não pôde ser buscado.  \nErro: "+e);
            }

            return l_codigo;
    }
    
    //atualiza o status no banco de dados
    public static String pegaApelidoPeloCodigo(int l_codUsuario){

            String l_apelido = null;

            //executa query
            Dados.s_conexaoBanco.executeSELECT("SELECT moniersn "
                                               + "FROM usuario "
                                               + "WHERE cod_usuario = "+l_codUsuario);
            try{
                Dados.s_conexaoBanco.c_resultset.first();
                l_apelido =  Dados.s_conexaoBanco.c_resultset.getString("moniersn");
            }
            catch(Exception e){
                System.err.println("O apelido não pôde ser buscado.   \nErro: "+e);
            }

            return l_apelido;
    }

    //atualiza o status no banco de dados
    public static String pegaApelidoPorEndereco(String l_host, int l_porta){

            String l_apelido = null;

            //executa query
            Dados.s_conexaoBanco.executeSELECT("SELECT moniersn "
                                               + "FROM usuario "
                                               + "WHERE host = '"+l_host+"'"
                                               + "AND porta_udp = "+l_porta);
            try{
                Dados.s_conexaoBanco.c_resultset.first();
                l_apelido =  Dados.s_conexaoBanco.c_resultset.getString("l_moniersn");
            }
            catch(Exception e){
                System.err.println("O apelido não pôde ser buscado.     \nErro: "+e);
            }

            return l_apelido;
    }


    //O clique no JTable pega o moniersn (usuário) referente a linha que foi clicada
    public int pegaCodDoAmigoClicadoNaLista(String l_tipoLista){

        //pega a linha que foi clicada na TABELA
        int linha;
        int l_codAmigo = 0;

        //pega apelido
        String l_apelido = null;

        //pega o modelo da tabela
        TableModel modelo;

            if(l_tipoLista.equals("amigos")){
                //pega a linha que foi clicada na TABELA
                linha = Moniersn.jt_listaAmigosUsuario.getSelectedRow();
                //pega o modelo da tabela
                modelo = (TableModel) Moniersn.jt_listaAmigosUsuario.getModel();
                //pega o valor da célula da linha selecionada e  coluna 0
                l_codAmigo = pegaCodOuStatusListaUsuario((String)modelo.getValueAt(linha, 1), "cod"); 
            }

            else if(l_tipoLista.equals("bate-papo")){
                //pega a linha que foi clicada na TABELA
                linha = Moniersn.jt_listaAmigosConectados.getSelectedRow();
                //pega o modelo da tabela
                modelo = (TableModel) Moniersn.jt_listaAmigosConectados.getModel();

                l_apelido = pegaApelidoOuHumor(modelo.getValueAt(linha, 0).toString(), "apelido");

                l_codAmigo = pegaCodOuStatusListaUsuario((String)modelo.getValueAt(linha, 1), "cod");

            }

        return l_codAmigo;

    }//fim click grid

    //O clique no JTable pega o moniersn (usuário) referente a linha que foi clicada
    public void defineIconeRecadoLidoNaoLido(JTable l_tabela){

        //Pega o valor de marcação do recado
        String l_valor = pegaValorMarcacaoRecado(l_tabela);
        
        if(l_tabela.equals(Moniersn.jt_listaRecadosPubUsuario)){

     
                if(l_valor.equals("N")){
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.REC_PUB_OK_16))); // NOI18N
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setToolTipText("Marcar como lido");
                }
                    else{
                        Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.REC_PUB_NOVO_16))); // NOI18N
                        Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setToolTipText("Marcar como não lido");
                    }

                Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setText("");
             
        }

        else if(l_tabela.equals(Moniersn.jt_listaRecadosPriv)){

                if(l_valor.equals("N")){
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.REC_PRIV_OK_16))); // NOI18N
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setToolTipText("Marcar como lido");
                }
                else{
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.REC_PRIV_NOVO_16))); // NOI18N
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setToolTipText("Marcar como não lido");
                }

                Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setText("");
        }

    }//fim click grid

    
    //O clique no JTable pega o moniersn (usuário) referente a linha que foi clicada
    public String pegaValorMarcacaoRecado(JTable l_tabela){

        //pega a linha que foi clicada na TABELA
        int linha;
        //pega o modelo da tabela
        TableModel modelo;

        String l_valor = null;
        //pega a linha que foi clicada na TABELA
        linha = l_tabela.getSelectedRow();
        //pega o modelo da tabela
        modelo = (TableModel) l_tabela.getModel();
        //pega o valor da célula da linha selecionada e  coluna 0
        l_valor = (String)modelo.getValueAt(linha, 0);

        return l_valor;

    }

    //conta a quantidade de recados do usuário
    public void contagemDeItensDoUsuario(int l_codUsuario){

            LogUsuario.s_abaAtualSelecionada = (JPanel) Moniersn.jtp_painelTabuladoUsuario.getSelectedComponent();

            String l_sqlContador = null;

            //CONTAGEM DE USUARIOS(total)
            try{

                l_sqlContador =  "SELECT COUNT(cod_usuario) AS 'qtd' "
                               + "FROM usuario "
                               + "WHERE ativo = 'S'";

                //executa
                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdTotUsu = Dados.s_conexaoBanco.c_resultset.getInt("qtd");


             try{
                //preenche array com apelido|humor dos usuarios
                preencheArrayComHumorUsuarios();
             
             }catch(Exception e){
                 System.err.println("   Erro: "+e);
                 
             }

                
            }
            catch(Exception e){
                System.err.println("Erro na contagem total de usuários.   \nErro: "+e);
            }

            //CONTAGEM DE USUARIOS(conectados)
            try{
                l_sqlContador = "SELECT COUNT(cod_usuario) AS 'qtd' "
                              + "FROM usuario "
                              + "WHERE (cod_usuario) NOT IN (SELECT cod_amigo "
                                                           + "FROM contato_de_usuario "
                                                           + "WHERE cod_usuario = "+l_codUsuario+" "
                                                           + "AND aceito = 'S' "
                                                           + "AND bloqueado = 'S') "
                              + "AND (cod_usuario) NOT IN (SELECT cod_usuario "
                                                         + "FROM contato_de_usuario "
                                                         + "WHERE cod_amigo = "+l_codUsuario+" "
                                                         + "AND aceito = 'S' "
                                                         + "AND bloqueado = 'S') "
                             + "AND status_contato = 'conectado' "
                             + "AND ativo = 'S' "
                             + "AND cod_usuario != "+l_codUsuario;


                //executa
                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdTotUsuConect = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

                //PREENCHE LISTA DE BATE PAPO
                defineGuiaDeBatePapo();

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de usuários conectados.  \nErro: "+e);
            }

            //CONTAGEM DE USUARIOS(convidados)
            try{

                l_sqlContador = "SELECT COUNT(u.cod_usuario) AS 'qtd' "
                               +"FROM usuario u, contato_de_usuario c "
                               +"WHERE u.cod_usuario = c.cod_amigo "
                               +"AND c.cod_usuario = "+l_codUsuario+" "
                               +"AND c.aceito = 'N' "
                               +"AND u.ativo = 'S'";

                //executa
                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdTotUsuConv = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de usuários convidados.  \nErro: "+e);
            }

            //CONTAGEM DE USUARIOS(convites)
            try{

                l_sqlContador = "SELECT COUNT(u.cod_usuario) AS 'qtd' "
                               +"FROM usuario u, contato_de_usuario c "
                               +"WHERE u.cod_usuario = c.cod_usuario "
                               +"AND c.cod_amigo = "+l_codUsuario+" "
                               +"AND c.aceito = 'N' "
                               +"AND u.ativo = 'S'";

                //executa
                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdTotConvRec = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

                    if(LogUsuario.s_qtdTotConvRec > 0){
                        Moniersn.lb_qtdConvites.setVisible(true);
                        Moniersn.lb_qtdConvites.setText("("+LogUsuario.s_qtdTotConvRec+")");
                    }else{
                        Moniersn.lb_qtdConvites.setVisible(false);
                        Moniersn.lb_qtdConvites.setText("(0)");
                    }
                
            }
            catch(Exception e){
                System.err.println("Erro na contagem total de convites.\nErro: "+e);
            }


            //CONTAGEM DE AMIGOS (todos)
            try{

                l_sqlContador = "SELECT COUNT(u.cod_usuario) AS 'qtd' "
                               + "FROM usuario u, contato_de_usuario c "
                               + "WHERE u.cod_usuario = c.cod_amigo "
                               + "AND c.aceito = 'S' "
                               + "AND c.bloqueado = 'N' "
                               + "AND c.cod_usuario = "+l_codUsuario+" "
                               + "AND u.ativo = 'S'";


                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdTotAmi = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de amigos.   \nErro: "+e);
            }

         //QTD. DE AMIGOS CONECTADOS (que não tenham me bloqueado)
            try{

                l_sqlContador = "SELECT COUNT(cod_usuario) AS 'qtd' "
                              + "FROM usuario "
                              + "WHERE (cod_usuario) IN (SELECT cod_amigo "
                                                       + "FROM contato_de_usuario "
                                                       + "WHERE cod_usuario = "+l_codUsuario+" "
                                                       + "AND aceito = 'S' "
                                                       + "AND bloqueado = 'N') "
                              + "AND (cod_usuario) NOT IN (SELECT cod_usuario "
                                                         + "FROM contato_de_usuario "
                                                         + "WHERE cod_amigo = "+l_codUsuario+" "
                                                         + "AND aceito = 'S' "
                                                         + "AND bloqueado = 'S') "
                             + "AND status_contato = 'conectado' "
                             + "AND ativo = 'S' "
                             + "AND cod_usuario != "+l_codUsuario;

                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdTotAmiConect = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de amigos conectados.    \nErro: "+e);
            }

            //QTD. AMIGOS BLOQUEADOS
            try{

                l_sqlContador = "SELECT COUNT(u.cod_usuario) AS 'qtd' "
                               + "FROM usuario u, contato_de_usuario c "
                               + "WHERE u.cod_usuario = c.cod_amigo "
                               + "AND c.aceito = 'S' "
                               + "AND c.bloqueado = 'S' "
                               + "AND u.ativo = 'S' "
                               + "AND c.cod_usuario = "+l_codUsuario;

                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);

                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdTotAmiBloq = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de amigos bloqueados.    \nErro: "+e);
            }


    //CONTAGEM DE RECADOS PÚBLICOS

        //QTD. RECADOS PÚBLICOS NÃO LIDOS
            try{

                if(!LogUsuario.s_quemVeRecPub.equals("ninguem")){

                    //QUEM_VE_REC_PUB = TODOS
                    if(LogUsuario.s_quemVeRecPub.equals("todos")){


                        //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM e Ñ AMIGOS Q SÓ AMIGOS VEEM LISTA REC. PUB.
                        l_sqlContador = "SELECT COUNT(r.cod_recado) AS 'qtd' "
                                      + "FROM usuario u, configuracoes_usuario c, recado r "
                                      + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (u.cod_usuario) NOT IN (SELECT u.cod_usuario "
                                                                 + "FROM usuario u, configuracoes_usuario c "
                                                                 + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo  "
                                                                                                + "FROM contato_de_usuario "
                                                                                                + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                                                + "AND aceito = 'S') "
                                                                 + "AND u.cod_usuario = c.cod_usuario "
                                                                 + "AND c.visualizar_lista_rec_pub = 'amigos') "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.cod_usuario = r.cod_emissor "
                                     + "AND u.ativo = 'S' "
                                     + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                     + "AND r.cod_destinatario = "+LogUsuario.s_cod+" "
                                     + "AND r.recado_destinatario_gravado = 'S' "
                                     + "AND r.tipo_recado = 'pub' "
                                     + "AND r.visto = 'N' "
                                     + "AND u.cod_usuario != "+LogUsuario.s_cod;

                    }

                    //QUEM_VE_REC_PUB = AMIGOS
                    if(LogUsuario.s_quemVeRecPub.equals("amigos")){


                    //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM
                    l_sqlContador = "SELECT COUNT(r.cod_recado) AS 'qtd' "
                                  + "FROM usuario u, configuracoes_usuario c, recado r "
                                  + "WHERE (u.cod_usuario) IN (SELECT cod_amigo "
                                                             + "FROM contato_de_usuario "
                                                             + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                             + "AND aceito = 'S' "
                                                             + "AND bloqueado = 'N') "
                                  + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "
                                                             + "FROM contato_de_usuario "
                                                             + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                             + "AND aceito = 'S' "
                                                             + "AND bloqueado = 'S') "
                                 + "AND u.cod_usuario = c.cod_usuario "
                                 + "AND u.cod_usuario = r.cod_emissor "
                                 + "AND u.ativo = 'S' "
                                 + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                 + "AND r.cod_destinatario = "+LogUsuario.s_cod+" "
                                 + "AND r.recado_destinatario_gravado = 'S' "
                                 + "AND r.tipo_recado = 'pub' "
                                 + "AND r.visto = 'N' "
                                 + "AND u.cod_usuario != "+LogUsuario.s_cod;
                    }

                    Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                    Dados.s_conexaoBanco.c_resultset.first();
                    LogUsuario.s_qtdRecPubRecNaoLido = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

                //caso ninguem v rec. pub
                }else
                    LogUsuario.s_qtdRecPubRecNaoLido = 0;
                    
                                    
                    //seta a qtd de recado publicos nao lidos na guia 2
                    Moniersn.jtp_painelTabuladoUsuario.setTitleAt(3, "("+LogUsuario.s_qtdRecPubRecNaoLido+")");
                    //seta a qtd de recado publicos nao lidos no radio
                    Moniersn.rb_recadosPubNaoLidos.setText(Avisos.TEXTO_NAO_LIDOS+" ("+LogUsuario.s_qtdRecPubRecNaoLido+")");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de recados públicos nao lidos.   \nErro: "+e);
            }


        //QTD. TOTAL DE RECADOS PÚBLICOS RECEBIDOS
            try{

                if(!LogUsuario.s_quemVeRecPub.equals("ninguem")){

                    //RECEBIDO
                    //QUEM_VE_REC_PUB = TODOS
                    if(LogUsuario.s_quemVeRecPub.equals("todos")){

                      //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM e Ñ AMIGOS Q SÓ AMIGOS VEEM LISTA REC. PUB.
                      l_sqlContador = "SELECT COUNT(r.cod_recado) AS 'qtd' "
                                      + "FROM usuario u, configuracoes_usuario c, recado r "
                                      + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (u.cod_usuario) NOT IN (SELECT u.cod_usuario "
                                                                 + "FROM usuario u, configuracoes_usuario c "
                                                                 + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo  "
                                                                                                + "FROM contato_de_usuario "
                                                                                                + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                                                + "AND aceito = 'S') "
                                                                 + "AND u.cod_usuario = c.cod_usuario "
                                                                 + "AND c.visualizar_lista_rec_pub = 'amigos') "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.cod_usuario = r.cod_emissor "
                                     + "AND u.ativo = 'S' "
                                     + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                     + "AND r.cod_destinatario = "+LogUsuario.s_cod+" "
                                     + "AND r.recado_destinatario_gravado = 'S' "
                                     + "AND r.tipo_recado = 'pub' "
                                     + "AND u.cod_usuario != "+LogUsuario.s_cod;
                    }

                    //QUEM_VE_REC_PUB = AMIGOS
                    if(LogUsuario.s_quemVeRecPub.equals("amigos")){

                          //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM
                          l_sqlContador = "SELECT COUNT(r.cod_recado) AS 'qtd' "
                                          + "FROM usuario u, configuracoes_usuario c, recado r "
                                          + "WHERE (u.cod_usuario) IN (SELECT cod_amigo "
                                                                     + "FROM contato_de_usuario "
                                                                     + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                     + "AND aceito = 'S' "
                                                                     + "AND bloqueado = 'N') "
                                          + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "
                                                                     + "FROM contato_de_usuario "
                                                                     + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                                     + "AND aceito = 'S' "
                                                                     + "AND bloqueado = 'S') "
                                         + "AND u.cod_usuario = c.cod_usuario "
                                         + "AND u.cod_usuario = r.cod_emissor "
                                         + "AND u.ativo = 'S' "
                                         + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                         + "AND r.cod_destinatario = "+LogUsuario.s_cod+" "
                                         + "AND r.recado_destinatario_gravado = 'S' "
                                         + "AND r.tipo_recado = 'pub' "
                                         + "AND u.cod_usuario != "+LogUsuario.s_cod;
                    }

                    Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                    Dados.s_conexaoBanco.c_resultset.first();
                    LogUsuario.s_qtdTotRecPubRecebidos = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

                }else
                    LogUsuario.s_qtdTotRecPubRecebidos = 0;

                //seta a qtd total de recados publicos recebidos no rb
                Moniersn.rb_todosRecadosPubRecebidos.setText(Avisos.TEXTO_RECEBIDOS+" ("+LogUsuario.s_qtdTotRecPubRecebidos+")");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de recados públicos recebidos.   \nErro: "+e);
            }

        //QTD. TOTAL DE RECADOS PÚBLICOS ENVIADOS
            try{

                if(!LogUsuario.s_quemVeRecPub.equals("ninguem")){

                    //ENVIADO
                    //QUEM_VE_REC_PUB = TODOS
                    if(LogUsuario.s_quemVeRecPub.equals("todos")){

                        //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM e Ñ AMIGOS Q SÓ AMIGOS VEEM LISTA REC. PUB.
                        l_sqlContador = "SELECT COUNT(r.cod_recado) AS qtd "
                                      + "FROM usuario u, configuracoes_usuario c, recado r "
                                      + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (u.cod_usuario) NOT IN (SELECT u.cod_usuario "
                                                                 + "FROM usuario u, configuracoes_usuario c "
                                                                 + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo  "
                                                                                                + "FROM contato_de_usuario "
                                                                                                + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                                                + "AND aceito = 'S') "
                                                                 + "AND u.cod_usuario = c.cod_usuario "
                                                                 + "AND c.visualizar_lista_rec_pub = 'amigos') "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.cod_usuario = r.cod_destinatario "
                                     + "AND u.ativo = 'S' "
                                     + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                     + "AND r.cod_emissor = "+LogUsuario.s_cod+" "
                                     + "AND r.recado_emissor_gravado = 'S' "
                                     + "AND r.tipo_recado = 'pub' "
                                     + "AND u.cod_usuario != "+LogUsuario.s_cod;
                    }

                    //QUEM_VE_REC_PUB = AMIGOS
                    if(LogUsuario.s_quemVeRecPub.equals("amigos")){

                        //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM
                        l_sqlContador = "SELECT COUNT(r.cod_recado) AS qtd "
                                      + "FROM usuario u, configuracoes_usuario c, recado r "
                                      + "WHERE (u.cod_usuario) IN (SELECT cod_amigo "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'N') "
                                      + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.cod_usuario = r.cod_destinatario "
                                     + "AND u.ativo = 'S' "
                                     + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                     + "AND r.cod_emissor = "+LogUsuario.s_cod+" "
                                     + "AND r.recado_emissor_gravado = 'S' "
                                     + "AND r.tipo_recado = 'pub' "
                                     + "AND u.cod_usuario != "+LogUsuario.s_cod;
                    }

                    Dados.s_conexaoBanco.executeSELECT(l_sqlContador);

                    Dados.s_conexaoBanco.c_resultset.first();
                    LogUsuario.s_qtdTotRecPubEnviados = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

                }else
                    LogUsuario.s_qtdTotRecPubEnviados = 0;

                //seta a qtd de recados publicos enviados no rb
                Moniersn.rb_recadosPubEnviados.setText(Avisos.TEXTO_ENVIADOS+" ("+LogUsuario.s_qtdTotRecPubEnviados+")");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de recados públicos enviados.    \nErro: "+e);
            }



    //CONTAGEM DE RECADOS PRIVADOS

       //QTD. RECADOS PRIVADOS NÃO LIDOS
            try{

                l_sqlContador = "SELECT COUNT(r.cod_recado) AS 'qtd' "
                               + "FROM usuario u, recado r "
                               + "WHERE u.cod_usuario = r.cod_emissor "
                               + "AND u.ativo = 'S' "
                               + "AND r.tipo_recado = 'priv' "
                               + "AND r.recado_destinatario_gravado = 'S' "
                               + "AND r.visto = 'N' "
                               + "AND r.cod_destinatario = "+l_codUsuario;

                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdRecPrivRecNaoLido = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

                //seta a qtd de recados privados na lidos na guia
                Moniersn.jtp_painelTabuladoUsuario.setTitleAt(4, "("+LogUsuario.s_qtdRecPrivRecNaoLido+")");
                //seta a qtd de recados privados na lidos no rb
                Moniersn.rb_recadosPrivNaoLidos.setText(Avisos.TEXTO_NAO_LIDOS+" ("+LogUsuario.s_qtdRecPrivRecNaoLido+")");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de recados privados nao lidos.   \nErro: "+e);
            }


       //QTD. TOTAL DE RECADOS PRIVADOS RECEBIDOS
            try{

                l_sqlContador = "SELECT COUNT(r.cod_recado) AS 'qtd' "
                               + "FROM usuario u, recado r "
                               + "WHERE u.cod_usuario = r.cod_emissor "
                               + "AND u.ativo = 'S' "
                               + "AND r.tipo_recado = 'priv' "
                               + "AND r.recado_destinatario_gravado = 'S' "
                               + "AND r.cod_destinatario = "+l_codUsuario;

                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);

                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdTotRecPrivRecebidos = Dados.s_conexaoBanco.c_resultset.getInt("qtd");
                //seta a qtd de recados privados recebidos no rb
                Moniersn.rb_todosRecadosPrivRecebidos.setText(Avisos.TEXTO_RECEBIDOS+" ("+ LogUsuario.s_qtdTotRecPrivRecebidos+")");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de recados privados recebidos.   \nErro: "+e);
            }


            //QTD. TOTAL DE RECADOS PRIVADOS ENVIADOS
            try{

                l_sqlContador = "SELECT COUNT(r.cod_recado) AS 'qtd' "
                               + "FROM usuario u, recado r "
                               + "WHERE u.cod_usuario = r.cod_emissor "
                               + "AND u.ativo = 'S' "
                               + "AND r.tipo_recado = 'priv' "
                               + "AND r.recado_emissor_gravado = 'S' "
                               + "AND r.cod_emissor = "+l_codUsuario;

                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);

                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdTotRecPrivEnviados = Dados.s_conexaoBanco.c_resultset.getInt("qtd");
                //seta a qtd de recados privados enviados no rb
                Moniersn.rb_recadosPrivEnviados.setText(Avisos.TEXTO_ENVIADOS+" ("+LogUsuario.s_qtdTotRecPrivEnviados+")");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de recados privados enviados.    \nErro: "+e);
            }

        //CONTAGEM DE OCORRÊNCIAS

            try{

                 //RECEBIDAS
                    LogUsuario.s_qtdTotOcoRecebidas = defineTotalDeOcorrencias(LogUsuario.s_cod, "rec", "geral");
                    //seta a qtd de atualizaçãoes recebidas de todos no rb
                    Moniersn.rb_ocoRecebidas.setText(Avisos.TEXTO_RECEBIDAS+" ("+LogUsuario.s_qtdTotOcoRecebidas+")");

                 //ENVIADAS
                    LogUsuario.s_qtdTotOcoEnviadas = defineTotalDeOcorrencias(LogUsuario.s_cod, "env", "geral");
                    //seta a qtd de atualizaçãoes recebidas de todos no rb
                    Moniersn.rb_ocoEnviadas.setText(Avisos.TEXTO_ENVIADAS+" ("+LogUsuario.s_qtdTotOcoEnviadas+")");

            }catch (Exception e) {
                System.err.println("Erro na contagem de ocorrências.    \nErro: "+e);
            }


            try{

            //CONTAGEM DE ATUALIZAÇÕES

                //PARA TODOS
                    LogUsuario.s_qtdTotAtuRecebidasDeEstranhos = defineTotalDeAtualizacoes(LogUsuario.s_cod, "recEstranhos", "geral");
                    //seta a qtd de atualizaçãoes recebidas de todos no rb
                    Moniersn.rb_atuParaTodos.setText(Avisos.TEXTO_ATU_DE_EST+" ("+LogUsuario.s_qtdTotAtuRecebidasDeEstranhos+")");

                //PARA AMIGOS
                    LogUsuario.s_qtdTotAtuRecebidasDeAmigos = defineTotalDeAtualizacoes(LogUsuario.s_cod, "recAmigos", "geral");
                    //seta a qtd de atualizaçãoes recebidas de todos no rb
                    Moniersn.rb_atuDeAmigos.setText(Avisos.TEXTO_ATU_DE_AMI+" ("+LogUsuario.s_qtdTotAtuRecebidasDeAmigos+")");

                //MINHAS ATUALIZAÇÕES
                    LogUsuario.s_qtdTotalAtuEnviadas = defineTotalDeAtualizacoes(LogUsuario.s_cod, "env", "geral");
                    //seta a qtd de atualizaçãoes recebidas de todos no rb
                    Moniersn.rb_atualizacoesMinhas.setText(Avisos.TEXTO_ATU_MINHAS+" ("+LogUsuario.s_qtdTotalAtuEnviadas+")");


            }catch(Exception e){
                 System.err.println("Erro na contagem de atualzações.    \nErro: "+e);
            }


            try{

        //CONTAGEM DE ARQUIVOS
                if(!LogUsuario.s_quemMeEnviaArquivo.equals("ninguem")){

                    if(LogUsuario.s_quemMeEnviaArquivo.equals("amigos")){

                        //ARQUIVOS DISPONIVEIS (so amigos me enviam)
                        l_sqlContador = "SELECT COUNT(a.cod_arquivo) AS qtd "
                                      + "FROM usuario u, arquivo a "
                                      + "WHERE (a.cod_emissor) IN (SELECT cod_amigo "
                                                                   + "FROM contato_de_usuario "
                                                                   + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                   + "AND aceito = 'S' "
                                                                   + "AND bloqueado = 'N') "
                                      + "AND (a.cod_emissor) NOT IN (SELECT cod_usuario "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (a.cod_arquivo) NOT IN (SELECT cod_arquivo "
                                                                 + "FROM arquivo_usuario "
                                                                 + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                 + "AND gravado = 'S') "
                                     + "AND (a.cod_arquivo) IN (SELECT cod_arquivo "
                                                                +"FROM arquivo "
                                                                +"WHERE cod_destinatario = "+LogUsuario.s_cod+" "
                                                                +"OR cod_destinatario = 0) "
                                     + "AND u.cod_usuario = a.cod_emissor "
                                     + "AND u.ativo = 'S' "
                                     + "AND u.status_contato = 'conectado' "
                                     + "AND a.gravado = 'S' "
                                     + "AND a.cod_emissor != "+LogUsuario.s_cod;
                    }

                    else if (LogUsuario.s_quemMeEnviaArquivo.equals("todos")){

                        //ARQUIVOS DISPONIVEIS (todos me enviam)
                        l_sqlContador = "SELECT COUNT(a.cod_arquivo) as qtd "
                                      + "FROM usuario u, arquivo a "
                                      + "WHERE (a.cod_emissor) NOT IN (SELECT cod_amigo "
                                                                   + "FROM contato_de_usuario "
                                                                   + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                   + "AND aceito = 'S' "
                                                                   + "AND bloqueado = 'S') "
                                      + "AND (a.cod_emissor) NOT IN (SELECT cod_usuario "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (a.cod_arquivo) NOT IN (SELECT cod_arquivo "
                                                                 + "FROM arquivo_usuario "
                                                                 + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                 + "AND gravado = 'S') "
                                      + "AND (a.cod_arquivo) NOT IN (SELECT cod_arquivo "
                                                                +"FROM arquivo "
                                                                +"WHERE (cod_emissor) NOT IN (SELECT cod_amigo "
                                                                                            +"FROM contato_de_usuario "
                                                                                            +"WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                                            +"AND aceito = 'S') "
                                                                +"AND quem_baixa_arquivo = 'amigos') "
                                     + "AND (a.cod_arquivo) IN (SELECT cod_arquivo "
                                                               + "FROM arquivo "
                                                                +"WHERE cod_destinatario = "+LogUsuario.s_cod+" "
                                                                +"OR cod_destinatario = 0) "
                                     + "AND u.cod_usuario = a.cod_emissor "
                                     + "AND u.ativo = 'S' "
                                     + "AND u.status_contato = 'conectado' "
                                     + "AND a.gravado = 'S' "
                                     + "AND a.cod_emissor != "+LogUsuario.s_cod;
                    }

                    //executa
                    Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                    Dados.s_conexaoBanco.c_resultset.first();
                    LogUsuario.s_qtdTotArqDisponiveis = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

                }else
                    LogUsuario.s_qtdTotArqDisponiveis = 0;

                //seta a qtd de recado publicos nao lidos na guia 2
                Moniersn.jtp_painelTabuladoUsuario.setTitleAt(5, "("+LogUsuario.s_qtdTotArqDisponiveis+")");
                //seta a qtd de ocorrencias recebidas no rb
                Moniersn.rb_todosArquivosDisponiveis.setText(Avisos.TEXTO_ARQ_DISPONIVEIS+" ("+LogUsuario.s_qtdTotArqDisponiveis+")");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de arquivos disponíveis.     \nErro: "+e);
            }


            try{

                //ARQUIVOS BAIXADOS
                    l_sqlContador = "SELECT COUNT(au.cod_arquivo) AS qtd "
                                  + "FROM usuario u, arquivo a, arquivo_usuario au "
                                  + "WHERE u.cod_usuario = a.cod_emissor "
                                  + "AND a.cod_arquivo = au.cod_arquivo "
                                  + "AND au.gravado = 'S' "
                                  + "AND au.cod_usuario = "+LogUsuario.s_cod+" "
                                  + "AND a.cod_emissor != "+LogUsuario.s_cod+" "
                                  + "ORDER BY a.cod_arquivo "
                                  + "ASC";

                //executa
                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdTotArqBaixados = Dados.s_conexaoBanco.c_resultset.getInt("qtd");
                //seta a qtd de ocorrencias recebidas no rb
                Moniersn.rb_todosArquivosBaixados.setText(Avisos.TEXTO_ARQ_BAIXADOS+" ("+LogUsuario.s_qtdTotArqBaixados+")");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de arquivos baixados.        \nErro: "+e);
            }


            //ARQUIVOS ENVIADOS
            try{

                l_sqlContador = "SELECT COUNT(a.cod_arquivo) AS qtd "
                              + "FROM usuario u, arquivo a "
                              + "WHERE (a.cod_emissor) NOT IN (SELECT cod_amigo "
                                                           + "FROM contato_de_usuario "
                                                           + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                           + "AND aceito = 'S' "
                                                           + "AND bloqueado = 'S') "
                              + "AND (a.cod_emissor) NOT IN (SELECT cod_usuario "
                                                         + "FROM contato_de_usuario "
                                                         + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                         + "AND aceito = 'S' "
                                                         + "AND bloqueado = 'S') "
                              + "AND (a.cod_arquivo) NOT IN (SELECT cod_arquivo "
                                                         + "FROM arquivo_usuario "
                                                         + "WHERE cod_usuario = "+LogUsuario.s_cod+") "
                             + "AND u.cod_usuario = a.cod_emissor "
                             + "AND u.ativo = 'S' "
                             + "AND a.gravado = 'S' "
                             + "AND a.cod_emissor = "+LogUsuario.s_cod;

                //executa
                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                Dados.s_conexaoBanco.c_resultset.first();
                LogUsuario.s_qtdTotArqEnviados = Dados.s_conexaoBanco.c_resultset.getInt("qtd");
                //seta a qtd de ocorrencias recebidas no rb
                Moniersn.rb_todosArquivosEnviados.setText(Avisos.TEXTO_ARQ_ENVIADOS+" ("+LogUsuario.s_qtdTotArqEnviados +")");

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de arquivos enviados.    \nErro: "+e);
            }


            //atualiza informações do trayIcon
            AreaNotificacao.atualizaTipTextTrayIcon();


    }//fim conta recados de usuário

     public int defineTotalDeAtualizacoes(int l_codUsuario, String l_tipoAtu, String l_periodo){

        String l_mostraInfoBasica = null;
        String l_mostraNome = null;
        String l_mostraSexo = null;
        String l_mostraFrase = null;
        String l_mostraDataNiver = null;
        String l_mostraListaAmigos = null;
        String l_mostraInfoPessoal = null;
        String l_mostraInfoContato = null;
        String l_mostraInfoEduc = null;
        String l_mostraInfoProf = null;
        String l_perfil = null;
        String l_atributo = null;
        String l_dataHoraInicio = null;
        String l_dataHoraFim = null;

        int l_contadorAtu  = 0;

        boolean l_podeMostrar = true;
        String l_sqlSelect = null;

        Data.atualizaDataHora();

        l_dataHoraFim = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;


            if(l_periodo.equals("geral")){
                l_dataHoraInicio = "2012/01/01 00:00:00";
            }else{
                l_dataHoraInicio = LogUsuario.s_dataHoraUltimoLogBD;
            }


        try {

            //do usuario
            if(!l_tipoAtu.isEmpty()){

                if(l_tipoAtu.equals("recEstranhos")){

//                    System.err.println(l_dataHoraInicio+" "+l_dataHoraFim);

                      //ATUALIZAÇÕES DE NÃO AMIGOS
                      l_sqlSelect = "SELECT u.cod_usuario, a.*, c.* "//SELECIONA TODAS ATU
                                  + "FROM usuario u, configuracoes_usuario c, atualizacoes_usuario a "
                                  + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo "//SOMENTE DE AMIGOS NÃO BLOQUEADOS
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_usuario = "+l_codUsuario+" "
                                                                 + "AND aceito = 'S') "
                                  + "AND u.cod_usuario = c.cod_usuario "
                                  + "AND u.cod_usuario = a.cod_usuario "
                                  + "AND u.ativo = 'S' "
                                  + "AND a.gravado = 'S' "
                                  + "AND u.cod_usuario != "+l_codUsuario+" "
                                  + "AND a.data_hora_post BETWEEN '"+l_dataHoraInicio+"' AND '"+l_dataHoraFim+"' "
                                  + "ORDER BY a.cod_atu "
                                  + "DESC ";
                }

                else if(l_tipoAtu.equals("recAmigos")){

                      //ATUALIZAÇÕES DE AMIGOS
                      l_sqlSelect = "SELECT u.cod_usuario, a.*, c.* "//SELECIONA TODAS ATU
                                  + "FROM usuario u, configuracoes_usuario c, atualizacoes_usuario a "
                                  + "WHERE (u.cod_usuario) IN (SELECT cod_amigo "//SOMENTE DE AMIGOS NÃO BLOQUEADOS
                                                             + "FROM contato_de_usuario "
                                                             + "WHERE cod_usuario = "+l_codUsuario+" "
                                                             + "AND aceito = 'S' "
                                                             + "AND bloqueado = 'N') "
                                  + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "//E QUE NÃO ME BLOQUEARAM
                                                             + "FROM contato_de_usuario "
                                                             + "WHERE cod_amigo = "+l_codUsuario+" "
                                                             + "AND aceito = 'S' "
                                                             + "AND bloqueado = 'S') "
                                 + "AND u.cod_usuario = c.cod_usuario "
                                 + "AND u.cod_usuario = a.cod_usuario "
                                 + "AND u.ativo = 'S' "
                                 + "AND a.gravado = 'S' "
                                 + "AND a.data_hora_post BETWEEN '"+l_dataHoraInicio+"' AND '"+l_dataHoraFim+"' "
                                 + "AND u.cod_usuario != "+l_codUsuario+" "
                                 + "ORDER BY a.cod_atu "
                                 + "DESC";
                }

                //MINHAS ATUALIZACOES
                else if(l_tipoAtu.equals("env")){

                        l_sqlSelect = "SELECT u.cod_usuario, a.*, c.* "//SELECIONA TODAS ATU
                                     + "FROM usuario u, atualizacoes_usuario a, configuracoes_usuario c "
                                     + "WHERE u.cod_usuario = a.cod_usuario "
                                     + "AND u.ativo = 'S' "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.cod_usuario = "+l_codUsuario+" "
                                     + "AND a.gravado = 'S' "
                                     + "ORDER BY a.cod_atu "
                                     + "DESC";
                }
            }    //ENVIADAS do amigo
                 else{

                    l_sqlSelect = "SELECT u.cod_usuario, a.*, c.* "
                                 + "FROM usuario u, atualizacoes_usuario a, configuracoes_usuario c "
                                 + "WHERE u.cod_usuario = a.cod_usuario "
                                 + "AND u.cod_usuario = c.cod_usuario "
                                 + "AND u.cod_usuario = "+l_codUsuario+" "
                                 + "AND a.gravado = 'S' "
                                 + "ORDER BY a.cod_atu "
                                 + "DESC";
                 }

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                //CASO TENHA RESULTADO
                if(Dados.s_conexaoBanco.c_resultset.first()){

                    do{

                        //Pega informações de configuração
                        l_mostraInfoBasica = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_basicas");
                        l_mostraNome = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_nome");
                        l_mostraSexo = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_sexo");
                        l_mostraFrase = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_minha_frase");
                        l_mostraDataNiver = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_data_niver");
                        l_mostraListaAmigos = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_lista_amigos");
                        l_mostraInfoContato = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_contato");
                        l_mostraInfoPessoal = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_pessoal");
                        l_mostraInfoEduc = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_educ");
                        l_mostraInfoProf = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_prof");

                        //Pega informações da atualizacao
                        l_perfil = Dados.s_conexaoBanco.c_resultset.getString("a.perfil");
                        l_atributo = Dados.s_conexaoBanco.c_resultset.getString("a.atributo");

//                      TESTE PRA MOSTRAR ATU DE INFO. BASICA DE NÃO AMIGOS

                        if(!l_tipoAtu.isEmpty()){

                                if(l_tipoAtu.equals("recEstranhos")){

                                        if(l_perfil.equals("basico") && l_mostraInfoBasica.equals("todos")){

                                                if((l_atributo.equals("nome") || l_atributo.equals("sobrenome"))  && l_mostraNome.equals("S"))
                                                    l_podeMostrar = true;
                                                else if(l_atributo.equals("icone_humor"))
                                                    l_podeMostrar = true;
                                                else if(l_atributo.equals("sexo") && l_mostraSexo.equals("S"))
                                                    l_podeMostrar = true;
                                                else if(l_atributo.equals("data_niver") && l_mostraDataNiver.equals("S"))
                                                    l_podeMostrar = true;
                                                else if(l_atributo.equals("minha_frase") && l_mostraFrase.equals("S"))
                                                    l_podeMostrar = true;
                                                else
                                                    l_podeMostrar = false;
                                        }

                //                      TESTE PRA MOSTRAR ATU DE NOVA AMIZADE
                                        else if (l_perfil.equals("amigos") && l_mostraListaAmigos.equals("todos"))
                                                l_podeMostrar = true;

                //                       TESTE PRA MOSTRAR ATU DE INFO. PESSOAL
                                        else if(l_perfil.equals("pessoal") && l_mostraInfoPessoal.equals("todos"))
                                                l_podeMostrar = true;

                //                       TESTE PRA MOSTRAR ATU DE INFO. CONTATO
                                        else if(l_perfil.equals("contato") && l_mostraInfoContato.equals("todos"))
                                                l_podeMostrar = true;

                //                       TESTE PRA MOSTRAR ATU DE INFO. EDUCACIONAL
                                        else if(l_perfil.equals("educacional") && l_mostraInfoEduc.equals("todos"))
                                                l_podeMostrar = true;

                //                       TESTE PRA MOSTRAR ATU DE INFO. PROFISSIONAL
                                        else if(l_perfil.equals("profissional") && l_mostraInfoProf.equals("todos"))
                                                l_podeMostrar = true;

                                        else{
                                                l_podeMostrar = false;
                                        }
                                }


        //                      TESTE PRA MOSTRAR ATU DE INFO. BASICA DE AMIGOS

                                else if (l_tipoAtu.equals("recAmigos") ||
                                         l_tipoAtu.equals("env")){

                                        if(l_perfil.equals("basico") && !l_mostraInfoBasica.equals("ninguem")){

                                                if((l_atributo.equals("nome") || l_atributo.equals("sobrenome")) && l_mostraNome.equals("S"))
                                                    l_podeMostrar = true;
                                                else if(l_atributo.equals("icone_humor"))
                                                    l_podeMostrar = true;
                                                else if(l_atributo.equals("sexo") && l_mostraSexo.equals("S"))
                                                    l_podeMostrar = true;
                                                else if(l_atributo.equals("data_niver") && l_mostraDataNiver.equals("S"))
                                                    l_podeMostrar = true;
                                                else if(l_atributo.equals("minha_frase") && l_mostraFrase.equals("S"))
                                                    l_podeMostrar = true;
                                                else
                                                    l_podeMostrar = false;

                                        }

                //                      TESTE PRA MOSTRAR ATU DE NOVA AMIZADE
                                        else if (l_perfil.equals("amigos") && !l_mostraListaAmigos.equals("ninguem")){
                                                l_podeMostrar = true;
                                        }

                //                       TESTE PRA MOSTRAR ATU DE INFO. PESSOAL
                                        else if(l_perfil.equals("pessoal") && !l_mostraInfoPessoal.equals("ninguem")){
                                                l_podeMostrar = true;
                                        }
                //                       TESTE PRA MOSTRAR ATU DE INFO. CONTATO
                                        else if(l_perfil.equals("contato") && !l_mostraInfoContato.equals("ninguem")){
                                                l_podeMostrar = true;
                                        }
                //                       TESTE PRA MOSTRAR ATU DE INFO. EDUCACIONAL
                                        else if(l_perfil.equals("educacional") && !l_mostraInfoEduc.equals("ninguem")){
                                                l_podeMostrar = true;
                                        }
                //                       TESTE PRA MOSTRAR ATU DE INFO. PROFISSIONAL
                                        else if(l_perfil.equals("profissional") && !l_mostraInfoProf.equals("ninguem")){
                                                l_podeMostrar = true;
                                        }
                                        else{
                                                l_podeMostrar = false;
                                        }
                                }
                        }

//                      TESTE PRA MOSTRAR ATU DE INFO. DO AMIGOS
                        else{

                                if(l_perfil.equals("basico") && (l_mostraInfoBasica.equals("todos") ||
                                   l_mostraInfoBasica.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){

                                        if((l_atributo.equals("nome") || l_atributo.equals("sobrenome")) && l_mostraNome.equals("S"))
                                            l_podeMostrar = true;
                                        else if(l_atributo.equals("icone_humor"))
                                            l_podeMostrar = true;
                                        else if(l_atributo.equals("sexo") && l_mostraSexo.equals("S"))
                                            l_podeMostrar = true;
                                        else if(l_atributo.equals("data_niver") && l_mostraDataNiver.equals("S"))
                                            l_podeMostrar = true;
                                        else if(l_atributo.equals("minha_frase") && l_mostraFrase.equals("S"))
                                            l_podeMostrar = true;
                                        else
                                            l_podeMostrar = false;

                                }

        //                      TESTE PRA MOSTRAR ATU DE NOVA AMIZADE
                                else if(l_perfil.equals("amigos") && (l_mostraListaAmigos.equals("todos") ||
                                   l_mostraListaAmigos.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){
                                        l_podeMostrar = true;
                                }

        //                       TESTE PRA MOSTRAR ATU DE INFO. PESSOAL
                                else if((l_perfil.equals("pessoal") && (l_mostraInfoPessoal.equals("todos")) ||
                                   (l_mostraInfoPessoal.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok")))){
                                        l_podeMostrar = true;
                                }
        //                       TESTE PRA MOSTRAR ATU DE INFO. CONTATO
                                else if(l_perfil.equals("contato") && (l_mostraInfoContato.equals("todos") ||
                                   l_mostraInfoContato.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){
                                        l_podeMostrar = true;
                                }
        //                       TESTE PRA MOSTRAR ATU DE INFO. EDUCACIONAL
                                else if(l_perfil.equals("educacional") && (l_mostraInfoEduc.equals("todos") ||
                                   l_mostraInfoEduc.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){
                                        l_podeMostrar = true;
                                }
        //                       TESTE PRA MOSTRAR ATU DE INFO. PROFISSIONAL
                                else if(l_perfil.equals("profissional") && (l_mostraInfoProf.equals("todos") ||
                                   l_mostraInfoProf.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){
                                        l_podeMostrar = true;
                                }
                                else{
                                        l_podeMostrar = false;
                                }
                        }

                        if(l_podeMostrar){
                            l_contadorAtu++;
                        }

                    }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto tiver dados preenche o jtable com os valores

//                    System.err.println(l_tipoAtu+" : "+l_contadorAtu);

                        if(l_periodo.equals("desdUltLogout")){

                                if(l_tipoAtu.equals("recAmigos"))
                                    LogUsuario.s_qtdAtuRecRecenteDeAmigos += l_contadorAtu;
                                else if(l_tipoAtu.equals("recEstranhos"))
                                    LogUsuario.s_qtdAtuRecRecenteDeEstranhos += l_contadorAtu;

                            LogUsuario.s_qtdTotAtuRecRecente += l_contadorAtu;
                            Moniersn.jtp_painelTabuladoUsuario.setTitleAt(2, "("+LogUsuario.s_qtdTotAtuRecRecente+")");

                        }
                }
        }

        catch (Exception e) {

            System.err.println("Erro na contagem de atualizações.   \nErro: "+e);
        }

        return l_contadorAtu;

    }//fim preenche lista de ocorrências do usuário

    public int defineGuiaDeBatePapo(){
        
        
        String sql = null;
        String l_iconePraQuemEnv = null;
        String l_quem = null;
        int l_totalDisponiveisBatePapo = 0;
            
            
            if(!LogUsuario.s_quemBatePapoComigo.equals("ninguem")){


                //SELECIONA TODOS AMIGOS, EXCETO(OS QUE BLOQUEEI E OS QUE ME BLOQUEARAM)
                if(LogUsuario.s_quemBatePapoComigo.equals("amigos")){

                        l_iconePraQuemEnv = Icones.AMIGO_BATE_PAPO_16;
                        l_quem = Avisos.TEXTO_AMIGOS_CONECTADOS;

                        sql = "SELECT COUNT(u.cod_usuario) as 'qtd' "
                              + "FROM usuario u, configuracoes_usuario c "
                              + "WHERE (u.cod_usuario) IN (SELECT cod_amigo "
                                                       + "FROM contato_de_usuario "
                                                       + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                       + "AND aceito = 'S' "
                                                       + "AND bloqueado = 'N') "
                              + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "
                                                         + "FROM contato_de_usuario "
                                                         + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                         + "AND aceito = 'S' "
                                                         + "AND bloqueado = 'S') "
                             + "AND u.cod_usuario = c.cod_usuario "
                             + "AND u.ativo = 'S' "
                             + "AND c.quem_bate_papo_comigo != 'ninguem' "
                             + "AND u.status_contato = 'conectado' "
                             + "AND u.cod_usuario != "+LogUsuario.s_cod;
                }

                //SELECIONA TODOS USUARIO , EXCETO(OS Q BLOQUEEI, OS QUE ME BLOQUEARAM E OS NÃO AMIGOS QUE SO BATEM PAPO COM AMIGOS)
                else if(LogUsuario.s_quemBatePapoComigo.equals("todos")){

                        l_iconePraQuemEnv = Icones.TODOS_BATE_PAPO_16;
                        l_quem = Avisos.TEXTO_TODOS_CONECTADOS;

                        sql = "SELECT COUNT(u.cod_usuario) as 'qtd' "
                              + "FROM usuario u, configuracoes_usuario c "
                              + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo "
                                                           + "FROM contato_de_usuario "
                                                           + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                           + "AND aceito = 'S' "
                                                           + "AND bloqueado = 'S') "
                              + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "
                                                         + "FROM contato_de_usuario "
                                                         + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                         + "AND aceito = 'S' "
                                                         + "AND bloqueado = 'S') "
                              + "AND (u.cod_usuario) NOT IN (SELECT u.cod_usuario "
                                                         + "FROM usuario u, configuracoes_usuario c "
                                                         + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo  "
                                                                                        + "FROM contato_de_usuario "
                                                                                        + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                                        + "AND aceito = 'S') "
                                                         + "AND u.cod_usuario = c.cod_usuario "
                                                         + "AND c.quem_bate_papo_comigo = 'amigos') "
                             + "AND u.cod_usuario = c.cod_usuario "
                             + "AND u.ativo = 'S' "
                             + "AND c.quem_bate_papo_comigo != 'ninguem' "
                             + "AND u.status_contato = 'conectado' "
                             + "AND u.cod_usuario != "+LogUsuario.s_cod;
                }

                Dados.s_conexaoBanco.executeSELECT(sql);

                    try{

                            Dados.s_conexaoBanco.c_resultset.first();
                            l_totalDisponiveisBatePapo = Dados.s_conexaoBanco.c_resultset.getInt("qtd"); 
                            
                            //SETA TOTAL DE USUARIOS DO BATE PAPO NO GERAL
                            LogUsuario.s_qtdTotUsuBatePapo = l_totalDisponiveisBatePapo;

                    }
                    //caso ocorra uma exceçao...
                    catch (Exception e){

                        Moniersn.lb_qtdRecInst.setVisible(false);
                        LogUsuario.s_qtdRecInstRec = 0;

                        System.err.println("Erro ao tentar definir lista de amigos do bate papo.    \nErro: "+e);
                    }
                    
            //preenche lista de amigos do batepapo
            MostrarDados.s_MostrarDados.preencherListaDeAmigosBatePapo();

            }   
                //CASO NAO BATA PAPO COM NINGUEM...
                else{

                        Moniersn.lb_qtdRecInst.setVisible(false);
                        LogUsuario.s_qtdRecInstRec = 0;

                        l_totalDisponiveisBatePapo = 0;
                        
                        //SETA TOTAL DE USUARIOS DO BATE PAPO NO GERAL
                        LogUsuario.s_qtdTotUsuBatePapo = l_totalDisponiveisBatePapo;
                            
                        l_iconePraQuemEnv = Icones.NINGUEM_BATE_PAPO_COMIGO_16;
                        l_quem = Avisos.TEXTO_NINGUEM;

                }
        

        //SETA ICONE DA GUIA
        Moniersn.jtp_painelTabuladoUsuario.setIconAt(6, new javax.swing.ImageIcon(getClass().getResource(l_iconePraQuemEnv)));

        //SETA QUANTIDADE DE CONECTADOS NA GUIA
        Moniersn.jtp_painelTabuladoUsuario.setTitleAt(6, "("+l_totalDisponiveisBatePapo+")");

        //SETA ICONE NO LABEL (pra quem enviar)
        Moniersn.lb_nomeIconeAmigoBatePapo.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_iconePraQuemEnv)));

        //SELECIONA QUEM BATE PAPO COMIGO
            if(l_quem.equals(Avisos.TEXTO_TODOS_CONECTADOS)){
               
                //SELECIONA O RADIO (todos(CONECTADOS)
                Moniersn.rb_todosBatemPapoComigo.setSelected(true);
             
                //SETA TEXTO NO COMBO 'TODOS'... 
                Moniersn.cb_enviarParaTodosConectados.setText(Avisos.TEXTO_TODOS_CONECTADOS);  
                
                Moniersn.lb_ninguemBatePapoComigoInicio.setVisible(true); 
                Moniersn.lb_ninguemBatePapoComigo.setVisible(true);
            }

            else if(l_quem.equals(Avisos.TEXTO_AMIGOS_CONECTADOS)){
               
                //SELECIONA O RADIO (AMIGOS(CONECTADOS))
                Moniersn.rb_soAmigosBatemPapoComigo.setSelected(true);
                
                //SETA TEXTO NO COMBO 'AMIGOS'... 
                Moniersn.cb_enviarParaTodosConectados.setText(Avisos.TEXTO_AMIGOS_CONECTADOS); 
                
                Moniersn.lb_ninguemBatePapoComigoInicio.setVisible(true); 
                Moniersn.lb_ninguemBatePapoComigo.setVisible(true);
            }
            
            else{
                //LIMPA SELEÇÃO DOS RADIOS
                Moniersn.bg_quemBatePapoComigo.clearSelection();
            
                //desabilita campos de envio de rec inst
                Moniersn.tf_recadoInst.setEditable(false); 
                Moniersn.bt_enviarRecInst.setEnabled(false);
                Moniersn.bt_limpaCampoRecInst.setEnabled(false); 
                
                //SETA TEXTO NO COMBO 'NINGUEM' E SELECIONA... 
                Moniersn.cb_enviarParaTodosConectados.setText(Avisos.TEXTO_NINGUEM); 
                Moniersn.cb_enviarParaTodosConectados.setSelected(true);
                
                //inabilita botao de ver info amigo...
                Moniersn.bt_visuPainelAmigo.setEnabled(false);  
                
                Moniersn.lb_ninguemBatePapoComigoInicio.setVisible(false); 
                Moniersn.lb_ninguemBatePapoComigo.setVisible(false);
                
                
                Moniersn.tf_pesquisaUsuarioBatePapo.setForeground(new java.awt.Color(255, 0, 0));
                Moniersn.lb_qtdUsuarioListaBatePapo.setText("("+l_totalDisponiveisBatePapo+")");
                Moniersn.lb_qtdUsuarioListaBatePapo.setToolTipText("("+l_totalDisponiveisBatePapo+") "+Avisos.TEXTO_PESSOAS_NA_LISTA);
                
                //ajusta a largura das colunas 0 e 1
                Moniersn.jt_listaAmigosConectados.getColumnModel().getColumn(0).setPreferredWidth(130);
                Moniersn.jt_listaAmigosConectados.getColumnModel().getColumn(1).setPreferredWidth(20);

                //pega o model (dados) da minha tabela lista de amigos
                DefaultTableModel modelo = (DefaultTableModel) Moniersn.jt_listaAmigosConectados.getModel();
                
                //toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados
                modelo.setNumRows(0);
                
            }


        //SETA ICONE NO LABEL (... conectados no geral)
        Moniersn.lb_qtdTotalUsuarioBatePapo.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_iconePraQuemEnv)));
        Moniersn.lb_qtdTotalUsuarioBatePapo.setText("("+l_totalDisponiveisBatePapo+") "+Avisos.TEXTO_PESSOAS_NO_GERAL); 


        //SETA PRA QUEM ENTA ENV.
        Moniersn.lb_nomeIconeAmigoBatePapo.setText(l_quem);

        //SETA TITULO DA LISTA DE USUARIOS
        Moniersn.jt_listaAmigosConectados.getColumnModel().getColumn(0).setHeaderValue(l_quem);

        //REPINTA COLUNA
        Moniersn.jt_listaAmigosConectados.getTableHeader().resizeAndRepaint();
        
        return l_totalDisponiveisBatePapo;

    }
     
    public int defineTotalDeOcorrencias(int l_codUsuario, String l_tipoOcorrencia, String l_periodo){

        int l_total = 0;
        String l_sqlContador = null;
        String l_dataHoraInicio = null;

        Data.atualizaDataHora();
        String l_dataHoraFim = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;


            if(l_periodo.equals("geral")){
                l_dataHoraInicio = "2012/01/01 00:00:00";
            }else{
                l_dataHoraInicio = LogUsuario.s_dataHoraUltimoLogBD;
            }


            //CONTAGEM DE OCORRÊNCIAS

            //QTD. TOTAL DE OCORRÊNCIAS RECEBIDAS
            try{

                    if(l_tipoOcorrencia.equals("rec")){


                        //CONTAGEM DE TODAS OCORRÊNCIAS, EXCETO(DE AMIGOS QUE NAO BLOQ., Q NÃO ME BLOQ e OS NÃO AMIGOS Q SÓ MOSTRAM OCO. PRA AMIGOS)
                        l_sqlContador = "SELECT COUNT(u.cod_usuario) AS 'qtd' "
                                      + "FROM usuario u, ocorrencia o, configuracoes_usuario c "
                                      + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo "
                                                                   + "FROM contato_de_usuario "
                                                                   + "WHERE cod_usuario = "+l_codUsuario+" "
                                                                   + "AND aceito = 'S' "
                                                                   + "AND bloqueado = 'S') "
                                      + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_amigo = "+l_codUsuario+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (u.cod_usuario) NOT IN (SELECT u.cod_usuario "
                                                                 + "FROM usuario u, configuracoes_usuario c "
                                                                 + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo "
                                                                                                + "FROM contato_de_usuario "
                                                                                                + "WHERE cod_usuario = "+l_codUsuario+" "
                                                                                                + "AND aceito = 'S') "
                                                                 + "AND u.cod_usuario = c.cod_usuario "
                                                                 + "AND c.visualizar_ocorrencia = 'amigos') "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.cod_usuario = o.cod_emissor "
                                     + "AND u.ativo = 'S' "
                                     + "AND o.data_hora_post BETWEEN '"+l_dataHoraInicio+"' AND '"+l_dataHoraFim+"' "
                                     + "AND o.gravado = 'S' "
                                     + "AND c.visualizar_ocorrencia != 'ninguem' "
                                     + "AND u.cod_usuario != "+l_codUsuario;

                    }

                    else if(l_tipoOcorrencia.equals("env")){


                        //QTD. TOTAL DE OCORRÊNCIAS ENVIADAS (minhas ocorrencias)

                        l_sqlContador =  "SELECT COUNT(cod_ocorrencia) AS 'qtd' "
                                       + "FROM ocorrencia "
                                       + "WHERE cod_emissor = "+l_codUsuario+" "
                                       + "AND gravado = 'S'";

                    }else if(l_tipoOcorrencia.equals("envAmigo")){


                        //QTD. TOTAL DE OCORRÊNCIAS ENVIADAS (minhas ocorrencias)

                        l_sqlContador =  "SELECT COUNT(cod_ocorrencia) AS 'qtd' "
                                       + "FROM ocorrencia "
                                       + "WHERE cod_emissor = "+l_codUsuario+" "
                                       + "AND anonimo = 'N'"
                                       + "AND gravado = 'S'";

                    }

                Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                Dados.s_conexaoBanco.c_resultset.first();
                l_total = Dados.s_conexaoBanco.c_resultset.getInt("qtd");


                        if(l_tipoOcorrencia.equals("rec")){

                            if(l_periodo.equals("desdUltLogout")){

                                LogUsuario.s_qtdOcoRecDesdUltLogout = l_total;
                                LogUsuario.s_qtdOcoRecRecente = l_total;
                                Moniersn.jtp_painelTabuladoUsuario.setTitleAt(1, "("+l_total+")");

                            }else if(l_periodo.equals("geral")){

                                LogUsuario.s_qtdTotOcoRecebidas = l_total;
//                                Moniersn.jtp_painelTabuladoUsuario.setTitleAt(1, "(0)");

                            }
                        }
            }
            catch(Exception e){
                l_total = 0;
                System.err.println("Erro na contagem de ocorrencias "+l_tipoOcorrencia+".    \nErro: "+e);
            }

        return l_total;

    }
    
    
    
     public int defineTotalDeRecPubEnvAmigo(int l_codAmigo){

        int l_total = 0;
        String l_sqlContador = null;

            //CONTAGEM DE RECADOS PÚBLICOS

            //QTD. TOTAL DE RECADOS PUB RECEBIDOS
             try{

                if(!LogAmigo.s_quemVeRecPub.equals("ninguem")){

                    //RECEBIDO
                    //QUEM_VE_REC_PUB = TODOS
                    if(LogAmigo.s_quemVeRecPub.equals("todos")){

                      //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM e Ñ AMIGOS Q SÓ AMIGOS VEEM LISTA REC. PUB.
                      l_sqlContador = "SELECT COUNT(r.cod_recado) AS 'qtd' "
                                      + "FROM usuario u, configuracoes_usuario c, recado r "
                                      + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_usuario = "+l_codAmigo+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_amigo = "+l_codAmigo+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (u.cod_usuario) NOT IN (SELECT u.cod_usuario "
                                                                 + "FROM usuario u, configuracoes_usuario c "
                                                                 + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo  "
                                                                                                + "FROM contato_de_usuario "
                                                                                                + "WHERE cod_usuario = "+l_codAmigo+" "
                                                                                                + "AND aceito = 'S') "
                                                                 + "AND u.cod_usuario = c.cod_usuario "
                                                                 + "AND c.visualizar_lista_rec_pub = 'amigos') "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.cod_usuario = r.cod_emissor "
                                     + "AND u.ativo = 'S' "
                                     + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                     + "AND r.cod_destinatario = "+l_codAmigo+" "
                                     + "AND r.recado_destinatario_gravado = 'S' "
                                     + "AND r.tipo_recado = 'pub' "
                                     + "AND u.cod_usuario != "+l_codAmigo;
                    }

                    //QUEM_VE_REC_PUB = AMIGOS
                    if(LogAmigo.s_quemVeRecPub.equals("amigos")){

                          //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM
                          l_sqlContador = "SELECT COUNT(r.cod_recado) AS 'qtd' "
                                          + "FROM usuario u, configuracoes_usuario c, recado r "
                                          + "WHERE (u.cod_usuario) IN (SELECT cod_amigo "
                                                                     + "FROM contato_de_usuario "
                                                                     + "WHERE cod_usuario = "+l_codAmigo+" "
                                                                     + "AND aceito = 'S' "
                                                                     + "AND bloqueado = 'N') "
                                          + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "
                                                                     + "FROM contato_de_usuario "
                                                                     + "WHERE cod_amigo = "+l_codAmigo+" "
                                                                     + "AND aceito = 'S' "
                                                                     + "AND bloqueado = 'S') "
                                         + "AND u.cod_usuario = c.cod_usuario "
                                         + "AND u.cod_usuario = r.cod_emissor "
                                         + "AND u.ativo = 'S' "
                                         + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                         + "AND r.cod_destinatario = "+l_codAmigo+" "
                                         + "AND r.recado_destinatario_gravado = 'S' "
                                         + "AND r.tipo_recado = 'pub' "
                                         + "AND u.cod_usuario != "+l_codAmigo;
                    }

                    Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                    Dados.s_conexaoBanco.c_resultset.first();
                    l_total = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

                }else
                    l_total = 0;

            }
            catch(Exception e){
                l_total = 0;
                System.err.println("Erro na contagem total de recados públicos recebidos do amigo.   \nErro: "+e);
            }

        return l_total;

    }

     public int defineTotalDeArqEnvAmigo(int l_codAmigo){

        int l_qtdArq = 0;
        String l_sqlContador = null;

            //CONTAGEM DE ARQUIVOS

            //QTD. TOTAL DE ARQUIVOS RECEBIDOS 
            
        
                       try{

        //CONTAGEM DE ARQUIVOS
                if(!LogUsuario.s_quemMeEnviaArquivo.equals("ninguem")){

                    if(LogUsuario.s_quemMeEnviaArquivo.equals("amigos")){

                        //ARQUIVOS DISPONIVEIS (so amigos me enviam)
                        l_sqlContador = "SELECT COUNT(a.cod_arquivo) AS qtd "
                                      + "FROM usuario u, arquivo a "
                                      + "WHERE (a.cod_emissor) IN (SELECT cod_amigo "
                                                                   + "FROM contato_de_usuario "
                                                                   + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                   + "AND aceito = 'S' "
                                                                   + "AND bloqueado = 'N') "
                                      + "AND (a.cod_emissor) NOT IN (SELECT cod_usuario "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (a.cod_arquivo) NOT IN (SELECT cod_arquivo "
                                                                 + "FROM arquivo_usuario "
                                                                 + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                 + "AND gravado = 'S') "
                                     + "AND (a.cod_arquivo) IN (SELECT cod_arquivo "
                                                                +"FROM arquivo "
                                                                +"WHERE cod_destinatario = "+LogUsuario.s_cod+" "
                                                                +"OR cod_destinatario = 0) "
                                     + "AND u.cod_usuario = a.cod_emissor "
                                     + "AND u.ativo = 'S' "
                                     + "AND u.status_contato = 'conectado' "
                                     + "AND a.gravado = 'S' "
                                     + "AND a.cod_emissor = "+l_codAmigo;
                    }

                    else if (LogUsuario.s_quemMeEnviaArquivo.equals("todos")){

                        //ARQUIVOS DISPONIVEIS (todos me enviam)
                        l_sqlContador = "SELECT COUNT(a.cod_arquivo) as qtd "
                                      + "FROM usuario u, arquivo a "
                                      + "WHERE (a.cod_emissor) NOT IN (SELECT cod_amigo "
                                                                   + "FROM contato_de_usuario "
                                                                   + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                   + "AND aceito = 'S' "
                                                                   + "AND bloqueado = 'S') "
                                      + "AND (a.cod_emissor) NOT IN (SELECT cod_usuario "
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                                 + "AND aceito = 'S' "
                                                                 + "AND bloqueado = 'S') "
                                      + "AND (a.cod_arquivo) NOT IN (SELECT cod_arquivo "
                                                                 + "FROM arquivo_usuario "
                                                                 + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                 + "AND gravado = 'S') "
                                      + "AND (a.cod_arquivo) NOT IN (SELECT cod_arquivo "
                                                                +"FROM arquivo "
                                                                +"WHERE (cod_emissor) NOT IN (SELECT cod_amigo "
                                                                                            +"FROM contato_de_usuario "
                                                                                            +"WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                                            +"AND aceito = 'S') "
                                                                +"AND quem_baixa_arquivo = 'amigos') "
                                     + "AND (a.cod_arquivo) IN (SELECT cod_arquivo "
                                                               + "FROM arquivo "
                                                                +"WHERE cod_destinatario = "+LogUsuario.s_cod+" "
                                                                +"OR cod_destinatario = 0) "
                                     + "AND u.cod_usuario = a.cod_emissor "
                                     + "AND u.ativo = 'S' "
                                     + "AND u.status_contato = 'conectado' "
                                     + "AND a.gravado = 'S' "
                                     + "AND a.cod_emissor = "+l_codAmigo;
                    }

                    //executa
                    Dados.s_conexaoBanco.executeSELECT(l_sqlContador);
                    Dados.s_conexaoBanco.c_resultset.first();
                    l_qtdArq = Dados.s_conexaoBanco.c_resultset.getInt("qtd");

                }else
                    l_qtdArq = 0;

            }
            catch(Exception e){
                System.err.println("Erro na contagem total de arquivos disponíbilizados por usuario de cod: "+l_codAmigo+".     \nErro: "+e);
            }

        return l_qtdArq;

    }


     
    public void contaAmigosAmigos(int l_codAmigo){

            //ocorrencias
            int l_totalAmigos = 0;
            int l_amigosComuns = 0;
//            int l_qtdTotalOcorrenciasAnonimas = 0;

        try{

            //AMIGOS
            //QTD. DE AMIGOS
            Dados.s_conexaoBanco.executeSELECT("SELECT COUNT(u.cod_usuario) AS 'qtd_amigos' "
                                               + "FROM usuario u, contato_de_usuario c "
                                               + "WHERE u.cod_usuario = c.cod_amigo "
                                               + "AND u.ativo = 'S' "
                                               + "AND c.aceito = 'S' "
                                               + "AND c.bloqueado = 'N' "
                                               + "AND c.cod_usuario = "+l_codAmigo);

            Dados.s_conexaoBanco.c_resultset.first();

            l_totalAmigos = Dados.s_conexaoBanco.c_resultset.getInt("qtd_amigos");

            //QTD. DE AMIGOS Comuns
            Dados.s_conexaoBanco.executeSELECT("SELECT COUNT(uUsuario.cod_usuario) AS 'qtd_amigos_comuns' "
                                               + "FROM usuario uUsuario, usuario uAmigo, contato_de_usuario cUsuario, contato_de_usuario cAmigo "
                                               + "WHERE uUsuario.cod_usuario = cUsuario.cod_amigo "
                                               + "AND uAmigo.cod_usuario = cAmigo.cod_amigo "
                                               + "AND uUsuario.ativo = 'S' "
                                               + "AND uAmigo.ativo = 'S' "
                                               + "AND cUsuario.aceito = 'S' and cAmigo.aceito = 'S' "
                                               + "AND cAmigo.cod_amigo = cUsuario.cod_amigo "
                                               + "AND cUsuario.cod_usuario = "+LogUsuario.s_cod+" "
                                               + "AND cAmigo.cod_usuario = "+l_codAmigo+" "
                                               + "AND cAmigo.cod_usuario != "+LogUsuario.s_cod);

            Dados.s_conexaoBanco.c_resultset.first();

            l_amigosComuns = Dados.s_conexaoBanco.c_resultset.getInt("qtd_amigos_comuns");

            //seta a quantidade total de amigos do amigo
            Moniersn.rb_qtdTotalAmigosAmigo.setText("Amigos ("+l_totalAmigos+")");

            //seta a quantidade comuns de amigos
            Moniersn.rb_qtdAmigosComuns.setText("Comuns ("+l_amigosComuns+")");
            


        }
        catch(Exception e){
            System.err.println("Erro ao tentar fazer a contagem de amigos em comum.     \nErro :"+e);
        }
    }
    
    //atualiza o status no banco de dados
    public boolean verificaSeOcorrenciaAnonima(int l_codOcorrencia){

            String l_select = null;
            String l_SN = null;
            boolean l_anonimo = true;

            
            l_select = "SELECT anonimo "
                     + "FROM ocorrencia "
                     + "WHERE cod_ocorrencia = "+l_codOcorrencia;
            
            //executa query
            Dados.s_conexaoBanco.executeSELECT(l_select);

                try{
                        Dados.s_conexaoBanco.c_resultset.first();

                        l_SN =  Dados.s_conexaoBanco.c_resultset.getString("anonimo");
                        
                            if(l_SN.equals("S"))
                                l_anonimo = true;
                            else
                                l_anonimo = false;
                        
                        
                }
                catch(Exception e){
                    System.err.println("Não foi possível verificar se ocorrência é anônima.  \nErro: "+e);
                }

            return l_anonimo;
    }
    
    //atualiza o status no banco de dados
    public boolean verificaSeRecadoGravado(int l_codRecado, String l_emissorOuDestinatario){

            String l_select = null;
            String l_SN = null;
            boolean l_agravado = true;
            String l_atributoEmiDest = null;
            
                if(l_emissorOuDestinatario.equals("emissor"))
                    l_atributoEmiDest = "recado_emissor_gravado";
                else
                    l_atributoEmiDest = "recado_destinatario_gravado";

            
            l_select = "SELECT "+l_atributoEmiDest+" "
                     + "FROM recado "
                     + "WHERE cod_recado = "+l_codRecado;
            
            //executa query
            Dados.s_conexaoBanco.executeSELECT(l_select);

                try{
                        Dados.s_conexaoBanco.c_resultset.first();

                        l_SN =  Dados.s_conexaoBanco.c_resultset.getString(l_atributoEmiDest);
                        
                            if(l_SN.equals("S"))
                                l_agravado = true;
                            else
                                l_agravado = false;
                        
                        
                }
                catch(Exception e){
                    System.err.println("Não foi possível verificar se recado ainda está gravado.  \nErro: "+e);
                }

            return l_agravado;
    }
    
    //atualiza o status no banco de dados
    public boolean verificaSeComentarioOcoAnonimo(int l_codComentario){

            String l_select = null;
            String l_SN = null;
            boolean l_anonimo = true;

            
            l_select = "SELECT anonimo "
                     + "FROM comentario "
                     + "WHERE cod_comentario = "+l_codComentario;
            
            //executa query
            Dados.s_conexaoBanco.executeSELECT(l_select);

                try{
                        Dados.s_conexaoBanco.c_resultset.first();

                        l_SN =  Dados.s_conexaoBanco.c_resultset.getString("anonimo");
                        
                            if(l_SN.equals("S"))
                                l_anonimo = true;
                            else
                                l_anonimo = false;
                        
                        
                }
                catch(Exception e){
                    System.err.println("Não foi possível verificar se comentário é anônimo.  \nErro: "+e);
                }

            return l_anonimo;
    }

    public String pegaDataUltimoLogOut(int l_codUsuario){

        String l_sqlSelect = null;
        String l_dataHora = null;
        Data.atualizaDataHora();


        try{
                //VERIFICA SE ESTOU NA LISTA DO AMIGO
                l_sqlSelect = "SELECT data_hora_logout "+
                              "FROM log_usuario "+
                              "WHERE cod_usuario = "+l_codUsuario+" "+
                              "ORDER BY cod_log "+
                              "ASC";    

                Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                //CASO ESTEJA NA LISTA DO AMIGO... (SOU CONVIDADO)
                if(Dados.s_conexaoBanco.c_resultset.last()){

                    l_dataHora = Dados.s_conexaoBanco.c_resultset.getString("data_hora_logout");

                    LogUsuario.s_dataHoraUltimoLogBD = l_dataHora;

                    System.out.println("    Ultimo logout: "+l_dataHora);

                }else{

                    System.out.println("    Primeiro log!");
                    l_dataHora = LogUsuario.s_dataHoraCadastroBD;

                }
        }
        catch(Exception e){
            l_dataHora = null;
            System.err.println("Erro ao tentar pegar data de ultimo logout.    \nErro: "+e);
        }

        return l_dataHora;

    }

    public String buscaTempoPostagemConteudo(String l_tipoConteudo, int l_codConteudo){

        Data.atualizaDataHora();

        String l_data = null;
        String l_hora = null;
        String l_dataHoraPost = null;
        String l_tempoPost = null;
        String l_tabelaBanco = null;
        String l_atributoCodConteudo = null;
        String sqlSelect;

        if(l_tipoConteudo.equals("recado")){
            l_tabelaBanco = "recado";
            l_atributoCodConteudo = "cod_recado";
        }
        if(l_tipoConteudo.equals("ocorrencia")){
            l_tabelaBanco = "ocorrencia";
            l_atributoCodConteudo = "cod_ocorrencia";
        }
        if(l_tipoConteudo.equals("atualizacao")){
            l_tabelaBanco = "atualizacoes_usuario";
            l_atributoCodConteudo = "cod_atu";
        }
        if(l_tipoConteudo.equals("arquivo")){
            l_tabelaBanco = "arquivo";
            l_atributoCodConteudo = "cod_arquivo";
        }

        sqlSelect = "SELECT data_post, hora_post "
                    +"FROM "+l_tabelaBanco+" "
                    +"WHERE "+l_atributoCodConteudo+" = "+l_codConteudo;

        Dados.s_conexaoBanco.executeSELECT(sqlSelect);

        try {

            //se conseguir ir para a primeira linha do c_resultset...
            Dados.s_conexaoBanco.c_resultset.first();

            l_data = Data.ajustaDataParaDDMMAAAA(Dados.s_conexaoBanco.c_resultset.getString("data_post"));
            l_hora = Dados.s_conexaoBanco.c_resultset.getString("hora_post");

            l_dataHoraPost = l_data+" "+l_hora;

            l_tempoPost = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraPost);

        }
        catch(Exception e){
            System.err.println("Nao foi possível verificar tempo de postagem de conteúdo.   \nErro: "+e);
        }

        return l_tempoPost;

    }

    public String pegaConteudo(int l_codConteudo, String l_tipoConteudo){

        Data.atualizaDataHora();

        String l_conteudo = null;
        String l_tabelaBanco = null;
        String l_atributoCodConteudo = null;
        String sqlSelect;

        if(l_tipoConteudo.equals("recado")){
            l_tabelaBanco = "recado";
            l_atributoCodConteudo = "cod_recado";
        }
        if(l_tipoConteudo.equals("ocorrencia")){
            l_tabelaBanco = "ocorrencia";
            l_atributoCodConteudo = "cod_ocorrencia";
        }
        if(l_tipoConteudo.equals("atualizacao")){
            l_tabelaBanco = "atualizacoes_usuario";
            l_atributoCodConteudo = "cod_atu";
        }
        if(l_tipoConteudo.equals("arquivo")){
            l_tabelaBanco = "arquivo";
            l_atributoCodConteudo = "cod_arquivo";
        }

        sqlSelect = "SELECT conteudo "
                    +"FROM "+l_tabelaBanco+" "
                    +"WHERE "+l_atributoCodConteudo+" = "+l_codConteudo;

        Dados.s_conexaoBanco.executeSELECT(sqlSelect);

        try{

            //se conseguir ir para a primeira linha do c_resultset...
            Dados.s_conexaoBanco.c_resultset.first();

            l_conteudo = Dados.s_conexaoBanco.c_resultset.getString("conteudo");

        }
        catch(Exception e){
            System.err.println("Nao foi possível buscar o conteúdo.     \nErro: "+e);
        }

        return l_conteudo;

    }
    
// public String defineTipoAmigo(int l_codUsuario, int l_codAmigo){
//
//        /*
//         tipos de amigos possíveis
//         * - aceito-ok
//         * - aceito-bloqueei
//         * - aceito-bloqueou-me
//         * - aceito-bloqueados
//         * - nao-aceito-convidou-me
//         * - nao-aceito-convidei
//         * - nao-amigo
//         */
//
//        String l_aceitei;
//        String l_aceitou;
//        String l_bloqueou;
//        String l_bloqueado;
//        String l_tipoUsuario = null;
//        String sqlSelect;
//
//        //VERIFICA SE É AMIGO (se está na lista de contatos_de_usuario)
//        sqlSelect = "SELECT a.*, b.* "
//                   +"FROM contato_de_usuario a, contato_de_usuario b "
//                   +"WHERE a.cod_usuario = b.cod_amigo "
//                   +"AND b.cod_usuario = a.cod_amigo "
//                   +"AND a.cod_usuario = "+l_codUsuario+" "
//                   +"AND a.cod_amigo = "+l_codAmigo;
//
//
//        Dados.s_conexaoBanco.executeSELECT(sqlSelect);
//
//        try{
//            
//            //CASO JA ESTEJA NO SELECT
//            //pode ser: aceito-ok, aceito-bloqueei, aceito-bloqueou-me, aceito-bloqueados, nao-aceito-convidei, nao-aceito-convidou-me)
//            if(Dados.s_conexaoBanco.c_resultset.first()){
//
//                //PEGA VALOR DE ACEITACAO
//                l_aceitei = Dados.s_conexaoBanco.c_resultset.getString("a.aceito");
//                l_aceitou = Dados.s_conexaoBanco.c_resultset.getString("b.aceito");
//                l_bloqueado = Dados.s_conexaoBanco.c_resultset.getString("a.bloqueado");
//                l_bloqueou = Dados.s_conexaoBanco.c_resultset.getString("b.bloqueado");
//
//                //CASO ACEITEI E A PESSOA ACEITOU
//                //nao-aceito-convidei
//                if(l_aceitei.equals("S") && l_aceitou.equals("S")){       
//                    
//                        //CASO BLOQUEEI E A PESSOA NAO ME BLOQUEOU
//                        //aceito-bloqueei
//                        if(l_bloqueado.equals("S") && l_bloqueou.equals("N"))
//                            l_tipoUsuario = "aceito-bloqueei";
//
//                        //CASO NAO BLOQUEEI E A PESSOA ME BLOQUEOU
//                        //aceito-bloqueou-me
//                        if(l_bloqueado.equals("N") && l_bloqueou.equals("S"))
//                        l_tipoUsuario = "aceito-bloqueou-me";
//
//
//                        //CASO BLOQUEEI E A PESSOA ME BLOQUEOU
//                        //aceito-bloqueados
//                        if(l_bloqueado.equals("S") && l_bloqueou.equals("S"))
//                        l_tipoUsuario = "aceito-bloqueados";
//
//                        //CASO NAO BLOQUEEI E A PESSOA NAO ME BLOQUEOU
//                        //aceitook
//                        if(l_bloqueado.equals("N") && l_bloqueou.equals("N"))
//                        l_tipoUsuario = "aceito-ok";
//                    
//                }
//
//                //CASO ACEITEI E A PESSOA NAO ACEITOU
//                //nao-aceito-convidei
//                else if(l_aceitei.equals("S") && l_aceitou.equals("N"))       
//                    l_tipoUsuario = "nao-aceito-convidei";
//                    
//                
//                //CASO NAO ACEITEI E A PESSOA ACEITOU
//                //nao-aceito-convidou-me
//                else if(l_aceitei.equals("N") && l_aceitou.equals("S"))       
//                    l_tipoUsuario = "nao-aceito-convidou-me";          
//          
//
//            //CASO NAO ESTEJA NA LISTA DE NINGUEM É = NAO AMIGO
//            }else{
//                    l_tipoUsuario = "nao-amigo";
//            }
//
//        }
//        catch(Exception e){
//            System.err.println("Nao foi possível definir o tipo de amigo.\nErro: "+e);
//        }
//
//        return l_tipoUsuario;
//        
//    }


    public String defineTipoAmigo(int l_codUsuario, int l_codAmigo){

        /*
         tipos de amigos possíveis
         * - aceito-ok
         * - aceito-bloqueei
         * - aceito-bloqueou-me
         * - aceito-bloqueados
         * - nao-aceito-convidou-me
         * - nao-aceito-convidei
         * - nao-amigo
         */

        String l_aceitou;
        String l_bloqueado;
        String l_tipoUsuario = null;
        String sqlSelect;

        //VERIFICA SE É AMIGO (se está na lista de contatos_de_usuario)
        sqlSelect = "SELECT * "
                   +"FROM contato_de_usuario "
                   +"WHERE cod_usuario = "+l_codUsuario+" "
                   +"AND cod_amigo = "+l_codAmigo;

        Dados.s_conexaoBanco.executeSELECT(sqlSelect);

        try {
            //CASO JA ESTEJA NA LISTA DO USUÁRIO....
            //pode ser: aceito-ok, aceito-bloqueei, aceito-bloqueou-me, aceito-bloqueados, nao-aceito-convidei)
            if(Dados.s_conexaoBanco.c_resultset.first()){

                //PEGA VALOR DE ACEITACAO
                l_aceitou = Dados.s_conexaoBanco.c_resultset.getString("aceito");
                l_bloqueado = Dados.s_conexaoBanco.c_resultset.getString("bloqueado");

                    //CASO ACEITO
                    //pode ser: aceito-bloqueei, aceito-bloqueados, ceito-bloqueou-me, aceito-ok
                    if(l_aceitou.equals("S")){

                            //CASO BLOQUEADO...
                            //pode ser: aceito-bloqueado, aceito-bloqueei
                            if(l_bloqueado.equals("S")){

                                //ok, sabemos que foi bloqueado, vamos ver se bloqueou-me
                                sqlSelect = "SELECT * "
                                            +"FROM contato_de_usuario "
                                            +"WHERE cod_usuario = "+l_codAmigo+" "
                                            + "AND cod_amigo = "+l_codUsuario;

                                Dados.s_conexaoBanco.executeSELECT(sqlSelect);
                                Dados.s_conexaoBanco.c_resultset.first();

                                l_bloqueado = Dados.s_conexaoBanco.c_resultset.getString("bloqueado");

                                    if(l_bloqueado.equals("S"))
                                        l_tipoUsuario = "aceito-bloqueados";
                                    else
                                        l_tipoUsuario = "aceito-bloqueei";
                            }
                            //CASO NÃO BLOQUEADO...
                            //pode ser: aceito-bloqueou-me, aceito-ok
                            else{

                                //VERIFICA SE BLOQUEOU-ME
                                //ok, sabemos que é nao foi bloqueado, vamos ver se bloqueou-me
                                sqlSelect =  "SELECT * "
                                            +"FROM contato_de_usuario "
                                            +"WHERE cod_usuario = "+l_codAmigo+" "
                                            +"AND cod_amigo = "+l_codUsuario;

                                Dados.s_conexaoBanco.executeSELECT(sqlSelect);
                                Dados.s_conexaoBanco.c_resultset.first();

                                l_bloqueado = Dados.s_conexaoBanco.c_resultset.getString("bloqueado");

                                    if(l_bloqueado.equals("S"))
                                        l_tipoUsuario = "aceito-bloqueou-me";
                                    else
                                        l_tipoUsuario = "aceito-ok";
                            }

                    }
                    //CASO NAO ACEITO
                    //é: nao-aceito-convidei
                    else{
                        l_tipoUsuario = "nao-aceito-convidei";
                    }
            }
            //CASO NAO ESTEJA NA LISTA DO USUARIO
            //pode ser: nao-aceito-convidou-me, nao-amigo
            else{

                //VERIFICA SE ESTOU NA LISTA DO AMIGO
                sqlSelect = "SELECT * "
                           +"FROM contato_de_usuario "
                           +"WHERE cod_usuario = "+l_codAmigo+" "
                           + "AND cod_amigo = "+l_codUsuario;

                Dados.s_conexaoBanco.executeSELECT(sqlSelect);

                    //CASO ESTEJA NA LISTA DO AMIGO... (SOU CONVIDADO)
                    if(Dados.s_conexaoBanco.c_resultset.first())
                        l_tipoUsuario = "nao-aceito-convidou-me";
                    //CASO NAO ESTEJA LISTA DELE TAMBEM... (NAO SOU AMIGO)
                    else
                        l_tipoUsuario = "nao-amigo";

            }
        }
        catch(Exception e){
            System.err.println("Nao foi possível definir o tipo de amigo.\nErro: "+e);
        }

        return l_tipoUsuario;
    }

    public String pegaIconeOuTipoExtensao(String l_extensao, String l_iconeOuTipo){
    
        String l_icone = null;
        String l_tipo = null;
        
            if(l_extensao.equals(".txt")){
                l_icone = Icones.ARQUIVO_TEXTO;
                l_tipo = "TEXTO";
            }

            else if(l_extensao.equals(".doc") || l_extensao.equals(".docx") ||
               l_extensao.equals(".xls") || l_extensao.equals(".xlsx") ||
               l_extensao.equals(".ppt") || l_extensao.equals(".pptx")){
                l_icone = Icones.ARQUIVO_OFFICE;
                l_tipo = "OFFICE";
            }
            
            else if(l_extensao.equals(".mp3") || l_extensao.equals(".wav") ||
                    l_extensao.equals(".wma")){
                l_icone = Icones.ARQUIVO_MUSICA;
                l_tipo = "MÚSICA";
            }
            
            else if(l_extensao.equals(".avi") || l_extensao.equals(".3gp") ||
               l_extensao.equals(".mp4") || l_extensao.equals(".wmv") ||
               l_extensao.equals(".mpeg")){
                l_icone = Icones.ARQUIVO_VIDEO;
                l_tipo = "VÍDEO";
            }
            
            else if(l_extensao.equals(".jpg") || l_extensao.equals(".png") ||
                    l_extensao.equals(".bmp")|| l_extensao.equals(".gif")){
                l_icone = Icones.ARQUIVO_IMAGEM;
                l_tipo = "IMAGEM";
            }

            else if(l_extensao.equals(".pdf")){
                l_icone = Icones.ARQUIVO_PDF;
                l_tipo = "PDF";
            }
            else if(l_extensao.equals(".rar") || l_extensao.equals(".zip")){
                l_icone = Icones.ARQUIVO_ZIP;
                l_tipo = "ZIPADO";
            }
            else{
                l_icone = Icones.ARQUIVO_DESC;
                l_tipo = "DESCON.";
            }
        
        if(l_iconeOuTipo.equals("icone"))
            return l_icone;

        else
            return l_tipo;

    }

    public String pegaExtensaoOuNomeArquivo(String l_nomeArquivo, String l_extensaoOuNome){

        int l_posicaoInicial;
        int l_posicaoFinal;
        String l_extensao = null;
        String l_nome = null;
        String l_retorno = null;

            if(l_extensaoOuNome.equals("extensao")){

                l_posicaoInicial = l_nomeArquivo.lastIndexOf(".");
                l_posicaoFinal   = l_nomeArquivo.length();

                l_extensao = l_nomeArquivo.substring(l_posicaoInicial, l_posicaoFinal);

                l_retorno = l_extensao.toLowerCase();
            }

            else if(l_extensaoOuNome.equals("nome")){

                l_posicaoInicial = 0;
                l_posicaoFinal   = l_nomeArquivo.lastIndexOf(".");

                l_nome = l_nomeArquivo.substring(l_posicaoInicial, l_posicaoFinal);

                l_retorno = l_nome;

            }

        return l_retorno;

    }

    public boolean verificaCaracteresInvalidosNomeArquivo(String l_nomeArquivo){

        boolean l_ok = false;

        if(l_nomeArquivo.contains("\\") ||
            l_nomeArquivo.contains("/") ||
            l_nomeArquivo.contains("*") ||
            l_nomeArquivo.contains("?") ||
            l_nomeArquivo.contains("\"") ||
            l_nomeArquivo.contains("<") ||
            l_nomeArquivo.contains(">") ||
            l_nomeArquivo.contains(":") ||
            l_nomeArquivo.contains("|") ||
            l_nomeArquivo.contains("'") ||
            l_nomeArquivo.contains("´") ||
            l_nomeArquivo.contains("`") ||
            l_nomeArquivo.contains("!") ||
            l_nomeArquivo.contains(";") ||
            l_nomeArquivo.contains("{") ||
            l_nomeArquivo.contains("}") ||
            l_nomeArquivo.contains("+") ||
            l_nomeArquivo.contains("§") ||
            l_nomeArquivo.contains("@") ||
            l_nomeArquivo.contains("#") ||
            l_nomeArquivo.contains("%") ||
            l_nomeArquivo.contains("$") ||
            l_nomeArquivo.contains("¨") ||
            l_nomeArquivo.contains("&") ||
            l_nomeArquivo.contains("*") ||
            l_nomeArquivo.contains("(") ||
            l_nomeArquivo.contains(")") ||
            l_nomeArquivo.contains("~") ||
            l_nomeArquivo.contains("_") ||
            l_nomeArquivo.contains("+") ||
            l_nomeArquivo.contains("=") ||
            l_nomeArquivo.contains(","))
            l_ok = false;
       else
           l_ok = true;

        return l_ok;

    }

    public boolean verificaCaracteresInvalidosCadastro(String l_nomeUsuario){

        boolean l_tudoOk = false;

        if(l_nomeUsuario.contains("\\") ||
            l_nomeUsuario.contains("/") ||
            l_nomeUsuario.contains("*") ||
            l_nomeUsuario.contains("?") ||
            l_nomeUsuario.contains("\"") ||
            l_nomeUsuario.contains("<") ||
            l_nomeUsuario.contains(">") ||
            l_nomeUsuario.contains(":") ||
            l_nomeUsuario.contains("|") ||
            l_nomeUsuario.contains("'") ||
            l_nomeUsuario.contains("´") ||
            l_nomeUsuario.contains("`") ||
            l_nomeUsuario.contains(" ") ||
            l_nomeUsuario.contains("!") ||
            l_nomeUsuario.contains(";") ||
            l_nomeUsuario.contains("{") ||
            l_nomeUsuario.contains("}") ||
            l_nomeUsuario.contains("+") ||
            l_nomeUsuario.contains("§") ||
            l_nomeUsuario.contains("$") ||
            l_nomeUsuario.contains("@") ||
            l_nomeUsuario.contains("#") ||
            l_nomeUsuario.contains("%") ||
            l_nomeUsuario.contains("¨") ||
            l_nomeUsuario.contains("&") ||
            l_nomeUsuario.contains("*") ||
            l_nomeUsuario.contains("(") ||
            l_nomeUsuario.contains(")") ||
            l_nomeUsuario.contains("~") ||
            l_nomeUsuario.contains("_") ||
            l_nomeUsuario.contains("+") ||
            l_nomeUsuario.contains("=") ||
            l_nomeUsuario.contains("-") ||
            l_nomeUsuario.contains(",") ||
            l_nomeUsuario.contains("1") ||
            l_nomeUsuario.contains("2") ||
            l_nomeUsuario.contains("3") ||
            l_nomeUsuario.contains("4") ||
            l_nomeUsuario.contains("5") ||
            l_nomeUsuario.contains("6") ||
            l_nomeUsuario.contains("7") ||
            l_nomeUsuario.contains("8") ||
            l_nomeUsuario.contains("9") ||
            l_nomeUsuario.contains("0"))
             l_tudoOk = false;
       else
             l_tudoOk = true;

        return l_tudoOk;

    }


//    public boolean renomeiaArquivo(String l_caminhoArquivoOriginal, String l_novoNome, String l_extensao){
//
//       String l_msg = null;
//
//       //arquivo a ser movido
//       File l_arquivoOriginal = new File(l_caminhoArquivoOriginal);
//
//       //pega local do arquivo a ser movido
//       String l_diretorioArquivoOriginal = l_arquivoOriginal.getParent().toString();
//
//       boolean l_renomeado = false;
//
//           if(l_novoNome.contains("\\") ||
//              l_novoNome.contains("/") ||
//              l_novoNome.contains("*") ||
//              l_novoNome.contains("?") ||
//              l_novoNome.contains("\"") ||
//              l_novoNome.contains("<") ||
//              l_novoNome.contains(">") ||
//              l_novoNome.contains(":") ||
//              l_novoNome.contains("|")){
//
//                l_renomeado = false;
//                l_msg = Avisos.MSG_ARQ_CARACT_INV;
//           }
//
//            else{
//
//
//                 File l_novoArquivo = new File(l_diretorioArquivoOriginal+"\\"+l_novoNome+l_extensao);
//
//                    System.err.println(l_diretorioArquivoOriginal+"\\"+l_novoNome+l_extensao);
//
//                    //caso exista
//                    if(l_novoArquivo.exists()){
//                        l_renomeado = false;
//                        l_msg = Avisos.MSG_ARQ_NOME_EXISTE;
//                    }
//
//                    else{
//
//                         if(l_arquivoOriginal.renameTo(l_novoArquivo)){
//                            l_renomeado = true;
//                            l_msg = Avisos.MSG_ARQ_RENOMEADO;
//
//                         }else{
//                             l_renomeado = false;
//                             l_msg = Avisos.MSG_ARQ_ERRO_RENOMEAR;
//                         }
//                    }
//            }
//
//               if(l_renomeado)
//                   System.out.println(l_msg);
//               else
//                   System.err.println(l_msg);
//
//       return l_renomeado;
//
//    }

    public int qtdComentarioPorConteudo(String l_tipoComentario, int l_codConteudo){

        int l_qtdComents = 0;
        String l_sqlSelect = null;

        l_sqlSelect = "SELECT COUNT(cod_comentario) AS 'qtd_comentario'"
                    + " FROM comentario"
                    + " WHERE tipo_comentario = '"+l_tipoComentario+"'"
                    + " AND cod_conteudo = "+l_codConteudo
                    + " AND gravado = 'S'";

        //SELECIONA NO BANCO
        Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

        try{
            //CASO TENHA REGISTRO NA PRIMEIRA LINHA....
            if(Dados.s_conexaoBanco.c_resultset.first()){
                //PEGA O TOTAL
                l_qtdComents = Dados.s_conexaoBanco.c_resultset.getInt("qtd_comentario");
            }
                //CASO NAO TENHA REGISTRO...
                else
                    l_qtdComents = 0;
        }
        //EXCEÇÕES
        catch(Exception e){
            System.err.println("Não foi possível fazer a contagem de comentários da ocor/rec/atu n°: "+l_codConteudo+"  \nErro: "+e);
        }

        return l_qtdComents;

    }

    public String buscaIconeHumorBD(String l_usuario){

            String l_iconeHumor = null, l_sqlSelect;

            if((l_usuario.equals(Avisos.TEXTO_ANONIMO)) ||
              (l_usuario.equals(Avisos.TEXTO_TODOS_GERAL)) ||
              (l_usuario.equals(Avisos.TEXTO_AMIGOS_GERAL)) ||
              (l_usuario.equals(Avisos.TEXTO_NINGUEM)))
                l_iconeHumor = l_usuario.toLowerCase();

            else{
                l_sqlSelect = "SELECT icone_humor "
                            + "FROM usuario "
                            + "WHERE moniersn = '"+l_usuario+"'";

                Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                try{
                    Dados.s_conexaoBanco.c_resultset.first();
                    l_iconeHumor = Dados.s_conexaoBanco.c_resultset.getString("icone_humor");
                }
                catch(Exception e){
                    System.err.println("Não foi possível buscar o ícone de humor do usuário "+l_usuario+".  \nErro: "+e);
                }
            }

            return l_iconeHumor;
    }

    public void reiniciarServidor(String l_tipoServidor){

        
        
        
          
    }

    
    
    public String pegaApelidoOuHumor(String l_apelidoEhumor, String l_apelidoOuHumor){

//        apelido|humor

            String l_valorRetorno = null;
            int l_posicaoInicial = 0;
            int l_posicaoFinal = 0;

                //apelido
                if(l_apelidoOuHumor.equals("apelido")){

                    l_posicaoInicial = 0;
                    l_posicaoFinal = l_apelidoEhumor.lastIndexOf("|");

                //humor
                }else{

                    l_posicaoInicial = l_apelidoEhumor.lastIndexOf("|")+1;
                    l_posicaoFinal = l_apelidoEhumor.length();

                }

            l_valorRetorno = l_apelidoEhumor.substring(l_posicaoInicial, l_posicaoFinal);


            return l_valorRetorno;
    }

    public int pegaCodOuStatusListaUsuario(String l_codStatus, String l_codOuStatus){

//        apelido|humor

            int l_valorRetorno = 0;
            int l_posicaoInicial = 0;
            int l_posicaoFinal = 0;

                //apelido
                if(l_codOuStatus.equals("cod")){

                    l_posicaoInicial = 0;
                    l_posicaoFinal = l_codStatus.lastIndexOf("|");

                //status
                }else if(l_codOuStatus.equals("status")){

                    l_posicaoInicial = l_codStatus.lastIndexOf("|")+1;
                    l_posicaoFinal = l_codStatus.length();

                }

            l_valorRetorno = Integer.parseInt(l_codStatus.substring(l_posicaoInicial, l_posicaoFinal));

            return l_valorRetorno;
    }
    
    //BUSCA O STATUS DO AMIGO
    public String buscaStatus(int l_codAmigo){

        String l_bloqueado = null;
        String l_status = null;
        String l_sqlSelect;

        try{
        //pega status
        l_sqlSelect = "SELECT status_contato FROM usuario WHERE cod_usuario = "+l_codAmigo;
        Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
        Dados.s_conexaoBanco.c_resultset.first();

        l_status = Dados.s_conexaoBanco.c_resultset.getString("status_contato");

        //verifica se esta bloqueado pelo amigo
        l_sqlSelect = "SELECT bloqueado FROM contato_de_usuario WHERE cod_usuario = "+l_codAmigo+" AND cod_amigo = "+LogUsuario.s_cod;
        Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

            //se for amigo...
            if(Dados.s_conexaoBanco.c_resultset.first()){

                l_bloqueado = Dados.s_conexaoBanco.c_resultset.getString("bloqueado");

                    if(l_bloqueado.equals("S"))
                        l_status = "desconectado";
            }
        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o status do usuário "+l_codAmigo+".     \nErro: "+e);
        }


        return l_status;
    }

 //BUSCA O FISICO ATUAL DO USUÁRIOS
    public int buscaPortaAtualDoUsuarioNoBanco(String l_tipoServer){

        int l_porta = 0;
        String l_atributoPorta = null;
        String l_sqlSelect;
        
        
            if(l_tipoServer.equals("UDP"))
                l_atributoPorta = "porta_udp";
            else
                l_atributoPorta = "porta_tcp";

        try{

            //pega status
            l_sqlSelect = "SELECT "+l_atributoPorta+" "
                        + "FROM usuario "
                        + "WHERE cod_usuario = "+LogUsuario.s_cod;

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

            Dados.s_conexaoBanco.c_resultset.first();

            l_porta = Dados.s_conexaoBanco.c_resultset.getInt(l_atributoPorta);

        }
        catch(Exception e){
            System.err.println("Não foi possível buscar porta "+l_tipoServer+" atual do usuário no banco.    \nErro: "+e);
        }

        return l_porta;
    }
    
 //BUSCA O FISICO ATUAL DO USUÁRIOS
    public String buscaFisico(int l_codUsuario){

        String l_fisico = null;
        String l_sqlSelect;

        try{

            //pega status
            l_sqlSelect = "SELECT fisico "
                        + "FROM usuario "
                        + "WHERE cod_usuario = "+l_codUsuario;

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

            Dados.s_conexaoBanco.c_resultset.first();

            l_fisico = Dados.s_conexaoBanco.c_resultset.getString("fisico");

        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o fisico do usuário "+l_codUsuario+".    \nErro: "+e);
        }

        return l_fisico;
    }


    //BUSCA O FISICO ATUAL DO USUÁRIOS
    public String buscaEstadoCivil(int l_codUsuario){

        String l_ec = null;
        String l_sqlSelect;

        try{

            //pega status
            l_sqlSelect = "SELECT estado_civil "
                        + "FROM usuario "
                        + "WHERE cod_usuario = "+l_codUsuario;

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

            Dados.s_conexaoBanco.c_resultset.first();

            l_ec = Dados.s_conexaoBanco.c_resultset.getString("estado_civil");

        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o estado civil do usuário "+l_codUsuario+".     \nErro: "+e);
        }

        return l_ec;
    }

    
    //BUSCA O STATUS DO AMIGO
    public String pegaConfPrivacidade(int l_codUsuario,String l_atributo){

        String l_valor = null;
        String l_sqlSelect;

        try{
        //pega status
        l_sqlSelect = "SELECT "+l_atributo+" "
                    + "FROM configuracoes_usuario "
                    + "WHERE cod_usuario = "+l_codUsuario;
        
            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            l_valor = Dados.s_conexaoBanco.c_resultset.getString(l_atributo);
        
        }
        catch(Exception e){
           System.err.println("Não foi possível buscar o valor de configuracão de privacidade.  \nErro: "+e);
        }


        return l_valor;
    }

    //BUSCA O STATUS DO AMIGO
    public String pegaNomeCompleto(int l_codUsuario){

        String l_nomeCompleto = null;
        String l_nome, l_sobrenome;
        String l_sqlSelect;


        //pega status
        l_sqlSelect = "SELECT nome, sobrenome "
                    + "FROM usuario "
                    + "WHERE cod_usuario = "+l_codUsuario;

        try{
            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

            Dados.s_conexaoBanco.c_resultset.first();

            l_nome = Dados.s_conexaoBanco.c_resultset.getString("nome");
            l_sobrenome = Dados.s_conexaoBanco.c_resultset.getString("sobrenome");

            l_nomeCompleto = l_nome+" "+l_sobrenome;

        }
        catch(Exception e){
           System.err.println("Não foi possível buscar o nome e sobrenome.      \nErro: "+e);
        }


        return l_nomeCompleto;
    }

    //BUSCA O STATUS DO AMIGO
    public String pegaHostPeloApelido(String l_apelido){

        String l_host = null;
        String l_sqlSelect;

        //pega status
        l_sqlSelect = "SELECT host "
                    + "FROM usuario "
                    + "WHERE moniersn = '"+l_apelido+"'";

        try{
            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

            Dados.s_conexaoBanco.c_resultset.first();

            l_host = Dados.s_conexaoBanco.c_resultset.getString("host");

        }
        catch(Exception e){
           System.err.println("Não foi possível buscar o HOST pelo apelido.      \nErro: "+e);
        }

        return l_host;
    }

    //BUSCA O STATUS DO AMIGO
    public String pegaInfoArquivo(String l_atributoInfo, int l_codArquivo){

        String l_infoArquivo = null;
        String l_sqlSelect;

        //pega status
        l_sqlSelect = "SELECT "+l_atributoInfo+" "
                    + "FROM arquivo "
                    + "WHERE cod_arquivo = "+l_codArquivo;

        try{

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

            Dados.s_conexaoBanco.c_resultset.first();

            l_infoArquivo = Dados.s_conexaoBanco.c_resultset.getString(l_atributoInfo);

                if(l_atributoInfo.equals("nome"))
                    l_infoArquivo = SolicitarDados.s_SolicitarDados.recolocaAspasSimplesParaSO(l_infoArquivo);


        }
        catch(Exception e){
           System.err.println("Não foi possível buscar informações deste arquivo de cod.:"+l_codArquivo+"   \nErro: "+e);
        }

        return l_infoArquivo;
    }

//    public byte[] pegaBytesArquivoBD(int l_codArquivo){
//
//        byte[] l_bytesArquivo = new byte[1024];
//        String l_sqlSelect;
//
//        //pega status
//        l_sqlSelect = "SELECT bytes "
//                    + "FROM arquivo "
//                    + "WHERE cod_arquivo = "+l_codArquivo;
//
//        try{
//
//            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
//
//            Dados.s_conexaoBanco.c_resultset.first();
//
//            l_bytesArquivo = Dados.s_conexaoBanco.c_resultset.getBytes("bytes");
//        }
//        catch(Exception e){
//           System.err.println("Não foi possível buscar informações deste arquivo de cod.:"+l_codArquivo+"   \nErro: "+e);
//        }
//
//        return l_bytesArquivo;
//
//    }

    public boolean nomeArquivoJaFoiUsado(String l_nomeArquivo){

        boolean l_podeUsar = false;

        //pega status
        String l_sqlSelect = "SELECT cod_arquivo "
                           + "FROM arquivo "
                           + "WHERE nome = '"+l_nomeArquivo+"'";

        try{

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

            if(Dados.s_conexaoBanco.c_resultset.first()){
                l_podeUsar = false;

                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_NOME_EXISTE, "aviso");
            }
            else{
                System.out.println("O nome do arquivo pode ser utilizado!");
                l_podeUsar = true;
            }

        }
        catch(Exception e){

           l_podeUsar = false;
           System.err.println("Não foi possível saber se o nome do arquivo ja foi usado.     \nErro: "+e);
           MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");

        }

        return l_podeUsar;

    }
    
    public int pegaPortatPeloApelido(String l_apelido, String l_tipoServer){

        int l_porta = 0;
        String l_atributoPortaServidor = null;
        String l_sqlSelect;


            if(l_tipoServer.equals("UDP"))
                   l_atributoPortaServidor = "porta_udp";
            else
                   l_atributoPortaServidor = "porta_tcp";


        //pega status
        l_sqlSelect = "SELECT "+l_atributoPortaServidor+" "
                    + "FROM usuario "
                    + "WHERE moniersn = '"+l_apelido+"'";

        try{
            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            l_porta = Dados.s_conexaoBanco.c_resultset.getInt(l_atributoPortaServidor);

        }
        catch(Exception e){
           System.err.println("Não foi possível buscar a porta pelo apelido.     \nErro: "+e);
        }

        return l_porta;
    }

    //BUSCA O STATUS DO AMIGO
    public String pegaApelidoDeAtuNovaAmizade(String l_atu){

        String l_apelido = null;

        //começou uma amizade com << a ultima posição é 25  (inicial)
        int l_posicaoInicial = 25;
        int l_posicaoFinal = l_atu.length();

        l_apelido = l_atu.substring(l_posicaoInicial, l_posicaoFinal).toString();

        return l_apelido;
    }


//    //pesquisar um amigo
//    public void pesquisarAmigo(){
//
//
//        try{
//
//            String l_pesquisado, l_nomeEncontrado = "";
//            //pega o nome do amigo digitado
//            String l_nomeParaPesquisa = tf_localizaAmigos.getText().toLowerCase();
//            //pega o tamanho do nome digitado
//            int l_tamanhoDaPesquisa = tf_localizaAmigos.getText().length();
//            //guardara o tamanho l_pesquisado no banco
//            int l_tamanhoPesquisado;
//
//            String l_pesquisaIgual = "n";
//
//                if(Dados.s_conexaoBanco.c_resultset.first()){
//                    //entra no laço
//                    do{
//
//                        //pega o tamanho do nome que foi l_pesquisado no banco
//                        l_tamanhoPesquisado = Dados.s_conexaoBanco.c_resultset.getString("moniersn").length();
//
//                        //se o tamanho do nome l_pesquisado for menor que o tanho da pesquisa já passa para o proximo registro
//                        if(l_tamanhoPesquisado < l_tamanhoDaPesquisa)
//                            Dados.s_conexaoBanco.c_resultset.next();
//                        //caso contrário...
//                        else{
//                            //pega uma substring do tamanho do nome l_pesquisado
//                            l_pesquisado = Dados.s_conexaoBanco.c_resultset.getString("moniersn").substring(0 , (l_tamanhoDaPesquisa)).toLowerCase();
//                            //System.out.println("l_pesquisado no bd: "+l_pesquisado);
//                            if(l_nomeParaPesquisa.equals(l_pesquisado)){
//                                l_pesquisaIgual = "s";//sai do laço e mostra dados
//                                MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario("filtro");
//                            }
//                            else
//                                Dados.s_conexaoBanco.c_resultset.next();
//                        }
//                    } while(l_pesquisaIgual.equals("n"));//fica no laço enquanto l_pesquisaIgual for igual a "n"
//                }//fim if
//        }
//        catch(Exception e){
//            JOptionPane.showMessageDialog(null, "Nenhum amigo com estas iniciais. Verifique a digitação.", "MonierSN", JOptionPane.WARNING_MESSAGE);
//            System.out.println("Erro ao tentar pesquisar amigo.\nErro:"+e);
//        }
//
//    }//fim pesquisar cliente por nome

    
    public String tratarSexo(String l_sx){

       String l_sexo = null;

        if(l_sx.equals("M"))
            l_sexo = "MASCULINO";
        else
            l_sexo = "FEMININO";

       return l_sexo;

    }


// setar radioButton sexo do determinado usuário
   public void setarSexo(String l_sexo){
       if(l_sexo.equals("M")){
           Configuracoes.s_telaConfiguracoes.rb_masculino.setSelected(true);
           Configuracoes.s_telaConfiguracoes.lb_sexoInfoBasica.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.SEX_MASC_16))); // NOI18N
       }
       else{
           Configuracoes.s_telaConfiguracoes.rb_feminino.setSelected(true);
           Configuracoes.s_telaConfiguracoes.lb_sexoInfoBasica.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.SEX_FEM_16))); // NOI18N
       }
   }// fim seta sexo

   public String pegaSexoSelecionado(){
       
       String l_sexo = null;
       
       if(CadastroUsuario.s_telaCadastroMoniersn.rb_masculino.isSelected()){
            l_sexo = "M";
       }
       else
           l_sexo = "F";
       
       return l_sexo;
       
   }// fim seta sexo

   //define o ícone de humor
   public void setaIconeHumor(String l_iconeHumor){
        Configuracoes.s_telaConfiguracoes.lb_apelido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+l_iconeHumor+".png")));
   }

   //habilitar e limpar campos de senha
   public void habilitaCamposSenha(boolean h){

                    //limpa e inabilita campo de senha e confirmação de senha
                    Configuracoes.s_telaConfiguracoes.pf_senha.setText("");
                    Configuracoes.s_telaConfiguracoes.pf_confSenha.setText("");
                    Configuracoes.s_telaConfiguracoes.pf_senha.setEditable(h);
                    Configuracoes.s_telaConfiguracoes.pf_confSenha.setEditable(h);

                    Configuracoes.s_telaConfiguracoes.cb_perguntaSecreta.setSelectedItem("");
                    Configuracoes.s_telaConfiguracoes.cb_perguntaSecreta.setEnabled(h);

                    Configuracoes.s_telaConfiguracoes.tf_respostaSecreta.setText("");
                    Configuracoes.s_telaConfiguracoes.tf_respostaSecreta.setEditable(h);
   }

   public String trataAspasSimplesParaBD(String l_texto){

       int l_posicaoAspa;
       String l_textoOriginal = l_texto;
       String l_textoFormatado = "";

       if(l_texto.contains("'")){

            do{
                l_posicaoAspa = l_texto.indexOf("'", 0);

                l_textoFormatado += l_texto.substring(0, l_posicaoAspa).concat("´");

                l_texto = l_texto.substring(l_posicaoAspa+1, l_texto.length());

            }while(l_texto.contains("'"));

            l_textoFormatado += l_texto;
       }
         else
             l_textoFormatado = l_textoOriginal;

       return l_textoFormatado;

   }

   public String recolocaAspasSimplesParaSO(String l_texto){

       int l_posicaoAspa;
       String l_textoOriginal = l_texto;

       System.out.println("TRATAMENTO DE ASPAS SIMPLES\n    Texto original: "+l_texto);

       String l_textoFormatado = "";

       if(l_texto.contains("´")){

           System.out.println("   Contem aspas simples. Tratando...");

            do{
                l_posicaoAspa = l_texto.indexOf("´", 0);

                l_textoFormatado += l_texto.substring(0, l_posicaoAspa).concat("'");

                l_texto = l_texto.substring(l_posicaoAspa+1, l_texto.length());

            }while(l_texto.contains("´"));

            l_textoFormatado += l_texto;

            System.out.println("    Texto tratado: "+l_textoFormatado);

       }
         else{
           System.out.println("    Não contem aspas simples.");
           l_textoFormatado = l_textoOriginal;
       }
       

       return l_textoFormatado;

   }

      public String trataBarraDiretorioParaBD(String l_diretorioSO){

       int l_posicaoBarra;
       String l_diretorioOriginal = l_diretorioSO;
       String l_diretorioBD = "";

       if(l_diretorioOriginal.contains("\\")){

            do{
                l_posicaoBarra = l_diretorioOriginal.indexOf("\\", 0);

                l_diretorioBD += l_diretorioOriginal.substring(0, l_posicaoBarra).concat("/");

                l_diretorioOriginal = l_diretorioOriginal.substring(l_posicaoBarra+1, l_diretorioOriginal.length());

            }while(l_diretorioOriginal.contains("\\"));

            l_diretorioBD += l_diretorioOriginal;
       }
         else
             l_diretorioBD = l_diretorioOriginal;
    
      
       return l_diretorioBD;

   }

      public String recolocaBarraDiretorioParaSO(String l_diretorioGravadoNoBD){

       int l_posicaoBarra;
       String l_diretorioOriginal = l_diretorioGravadoNoBD;
       String l_diretorioBD = "";
          
            if(!l_diretorioGravadoNoBD.equals("*")){

                 if(l_diretorioOriginal.contains("/")){

                    do{
                        l_posicaoBarra = l_diretorioOriginal.indexOf("/", 0);

                        l_diretorioBD += l_diretorioOriginal.substring(0, l_posicaoBarra).concat("\\");

                        l_diretorioOriginal = l_diretorioOriginal.substring(l_posicaoBarra+1, l_diretorioOriginal.length());

                    }while(l_diretorioOriginal.contains("/"));

                    l_diretorioBD += l_diretorioOriginal;
                 }
                 else
                     l_diretorioBD = l_diretorioOriginal;

            }else{
                l_diretorioBD = l_diretorioGravadoNoBD;
            }
          
       return l_diretorioBD;

   }

      public String trataNomeArquivoPara50caracteres(String l_nomeArquivo){

           String l_extensao = SolicitarDados.s_SolicitarDados.pegaExtensaoOuNomeArquivo(l_nomeArquivo, "extensao");
           String l_nomeOriginal = l_nomeArquivo;
           String l_nome50carac = "";


           if(l_nomeOriginal.length() >= 50){

                l_nome50carac = l_nomeOriginal.substring(0, 43).concat("..."+l_extensao);

           }
             else
                 l_nome50carac = l_nomeArquivo;

           return l_nome50carac;

   }

   public boolean manipulaArquivo(String l_caminhoArquivo, String l_novoNome, String l_pastaDestino){

       boolean l_manipulado = false;

       String l_urlDiretorioDestino = Diretorios.pegaDiretorio(Diretorios.PASTA_ARQUIVOS, l_pastaDestino);

        //caso a pasta nao seja \\removidos
        if(!l_pastaDestino.equals(Diretorios.PASTA_REMOVIDOS)){

            // copia o arquivo para destino
            System.out.println("Preparando para copiar o arquivo...");
            if(copiaArquivo(l_caminhoArquivo, l_novoNome, l_urlDiretorioDestino))
                l_manipulado = true;
                else
                    l_manipulado = false;

       }

            //caso seja o diretorio \\removidos...
            else{
                System.out.println("Preparando para mover arquivo...");
                // move arquivo para pasta removidos
                if(moveArquivo(l_caminhoArquivo, l_urlDiretorioDestino))
                    l_manipulado = true;
                    else
                        l_manipulado = false;
           }

        return l_manipulado;

   }

    public boolean criarPastasFacecard(String path) {

        boolean l_criado = false;

        String[] pastas = path.split("\\\\");
        String raiz = pastas[0].toString() + "\\";

            try{
                for (int i = 0; i<pastas.length; i++) {
                    if (i>0) {
                        File dir = new File(raiz + pastas[i].toString());
                            if (!dir.exists()) {
                                if(dir.mkdir())
                                    l_criado = true;
                                else
                                    l_criado = false;
                            }

                        raiz = raiz + pastas[i].toString() + "\\";
                    }
                }

                if(l_criado)
                    System.out.println("Estrutura de diretórios criada com sucesso!\nLocal: "+path);
                else
                    System.err.println("Estrutura de diretórios facecard nao pode ser criada!");

            }catch(Exception e){
                 l_criado = false;
                System.err.println("Estrutura de diretórios facecard nao pode ser criada!\nLocal: "+path+"\nErro: "+e);
            }

        return l_criado;

    }
    
    public boolean removerPastasFacecard(String path){

        boolean l_deletado = false;

        String[] pastas = path.split("\\\\");
        String raiz = pastas[0].toString() + "\\";

            try{
                for(int i = 0; i<pastas.length; i++){
                    if (i>0) {
                        File dir = new File(raiz + pastas[i].toString());
                            if (dir.exists()){
                                if(dir.delete())
                                    l_deletado = true;
                                else
                                    l_deletado = false;
                            }

                        raiz = raiz + pastas[i].toString() + "\\";
                    }
                }

                if(l_deletado)
                    System.out.println("Estrutura de diretórios deletada com sucesso!\nLocal: "+path);
                else
                    System.err.println("Estrutura de diretórios facecard nao pode ser deletada!");

            }catch(Exception e){
                 l_deletado = false;
                System.err.println("Estrutura de diretórios facecard nao pode ser deletada!\nLocal: "+path+"\nErro: "+e);
            }

        return l_deletado;

    }

    public boolean copiaArquivo(String l_urlArquivoOriginal, String l_novoNome, String l_urlDiretorioDestino) {

        boolean l_copiado = false;

        try {

           String l_urlDiretorioEnviados   = Diretorios.pegaDiretorio("arquivos", Diretorios.PASTA_ENVIADOS);

            File l_arquivoOriginal = new File(l_urlArquivoOriginal);
            long l_tamanhoArquivo = l_arquivoOriginal.length();
            double l_div = l_tamanhoArquivo / 1024;
            int l_cont = 0;

            String l_urlArquivoDestino = Diretorios.pegaCaminhoArquivo(Diretorios.PASTA_ENVIADOS, l_novoNome);
//            l_urlDiretorioDestino+"\\"+l_novoNome;

            File l_arquivoDestino = new File(l_urlArquivoDestino);

                //CASO ARQUIVO NAO EXISTA...
                if(!l_arquivoDestino.exists()){

                    CompartilhaArquivo.pb_barraPreparandoArquivo.setMaximum((int)l_div);

                    InputStream in = new FileInputStream(l_arquivoOriginal);

                    //For Overwrite the file.
                    OutputStream out = new FileOutputStream(l_arquivoDestino);

                    byte[] buf = new byte[1024];
                    int len;

                        while ((len = in.read(buf)) > 0){
                            out.write(buf, 0, len);

                            l_cont ++;

                            if(l_urlDiretorioDestino.equals(l_urlDiretorioEnviados))
                                CompartilhaArquivo.pb_barraPreparandoArquivo.setValue(l_cont);
                        }

                    out.flush();
                    in.close();
                    out.close();

                    System.out.println("Arquivo copiado com sucesso!    \nPath: "+l_urlArquivoDestino);
                    l_copiado = true;


            //CASO ARQUIVO JÁ EXISTA...
            }else{

                l_copiado = false;

                CompartilhaArquivo.s_telaCompArquivo.setDefaultCloseOperation(CompartilhaArquivo.DISPOSE_ON_CLOSE);
                CompartilhaArquivo.s_telaCompArquivo.bt_confirmarRenomear.setEnabled(true);
                CompartilhaArquivo.s_telaCompArquivo.bt_editarCampo.setEnabled(false);
                CompartilhaArquivo.s_telaCompArquivo.bt_selecionarArquivo.setEnabled(true);
                CompartilhaArquivo.s_telaCompArquivo.tf_arquivo.setEditable(true);
                CompartilhaArquivo.s_telaCompArquivo.tf_arquivo.requestFocus();


                CompartilhaArquivo.s_telaCompArquivo.bt_cancelar.setEnabled(true);
                CompartilhaArquivo.s_telaCompArquivo.lb_preparando.setVisible(false);
                

                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_NOME_EXISTE, "aviso");

            }

        } catch (FileNotFoundException ex) {
            l_copiado = false;
            System.err.println(ex.getMessage() + " neste diretório.");
            CompartilhaArquivo.s_telaCompArquivo.dispose();
            Moniersn.s_telaMsn.setVisible(true);
            CompartilhaArquivo.s_telaCompArquivo = null;
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_ERRO_COPIAR, "erro");

        } catch (IOException e) {

            l_copiado = false;
            System.err.println(e.getMessage());
            CompartilhaArquivo.s_telaCompArquivo.dispose();
            Moniersn.s_telaMsn.setVisible(true);
            CompartilhaArquivo.s_telaCompArquivo = null;
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_ERRO_COPIAR, "erro");

        }

        return l_copiado;

    }
    


    public boolean moveArquivo (String l_urlArquivoOriginal, String l_urlDiretorioDestino) {

        boolean l_movido = false;
        int l_sufixoNomeArquivo = 1;

        try {

            System.out.println("Movendo arquivo: "+l_urlArquivoOriginal+"\nPara: "+l_urlDiretorioDestino);

            File l_arquivoOriginal = new File(l_urlArquivoOriginal);
            String l_nomeArquivoOriginal = l_arquivoOriginal.getName();

            String l_urlArquivoDestino = l_urlDiretorioDestino+"\\"+l_nomeArquivoOriginal;

            File l_diretorioDestino = new File(l_urlDiretorioDestino);

            File l_arquivoMovido = new File(l_diretorioDestino, l_nomeArquivoOriginal);


                if(l_arquivoMovido.exists()){
                    do{
                       l_arquivoMovido.renameTo(new File(l_diretorioDestino, l_sufixoNomeArquivo+"."+l_nomeArquivoOriginal));
                       l_sufixoNomeArquivo++;
                    }while(l_arquivoMovido.exists());
                }

            // move o arquivo para o novo diretorio
            boolean ok = l_arquivoOriginal.renameTo(l_arquivoMovido);

                if(ok){
                    System.out.println("Arquivo movido com sucesso!\nLocal: "+l_urlArquivoDestino);
                    l_movido = true;

                }
                else{
                    System.err.println("Nao foi possivel mover o arquivo");
                    l_movido = false;
                }
        }
        catch (Exception e) {
            l_movido = false;
            System.err.println(e.getMessage() + " neste diretório.");
        }

        return l_movido;

    }



   public void escolherCorObjeto(String l_icone, String l_texto, Color l_corFonte, Color l_fundo, String l_atributoBanco){

        PaletaEscolhaCor.s_telaPaletaEscolhaCor = new PaletaEscolhaCor(new javax.swing.JFrame(), true);

        PaletaEscolhaCor.s_telaPaletaEscolhaCor.lb_objeto.setText(l_texto);
        PaletaEscolhaCor.s_telaPaletaEscolhaCor.lb_objeto.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_icone))); // NOI18N);
        PaletaEscolhaCor.s_telaPaletaEscolhaCor.lb_objeto.setForeground(l_corFonte);
        PaletaEscolhaCor.s_telaPaletaEscolhaCor.jp_painelTeste.setBackground(l_fundo);
        PaletaEscolhaCor.s_telaPaletaEscolhaCor.c_nomeCampoNoBanco = l_atributoBanco;

        PaletaEscolhaCor.s_telaPaletaEscolhaCor.setVisible(true);

   }

     public void habilitaDesabilitaInfoBasicas(boolean l_habilita){

        //HABILITA DESABILITA  INFORMAÇÃOES basicas
        Configuracoes.s_telaConfiguracoes.lb_quaisInfoBasicaVisualizar.setEnabled(l_habilita);
        Configuracoes.s_telaConfiguracoes.cb_mostrarMinhaFrase.setEnabled(l_habilita);
        Configuracoes.s_telaConfiguracoes.cb_mostrarSexo.setEnabled(l_habilita);
        Configuracoes.s_telaConfiguracoes.cb_mostrarDataNiver.setEnabled(l_habilita);
        Configuracoes.s_telaConfiguracoes.cb_mostrarDataCadastro.setEnabled(l_habilita);
        //HABILITA DESABILITA  ICONES INFORMAÇÃOES basicas
        Configuracoes.s_telaConfiguracoes.lb_iconeMinhaFrase.setEnabled(l_habilita);
        Configuracoes.s_telaConfiguracoes.lb_iconeMeuSexo.setEnabled(l_habilita);
        Configuracoes.s_telaConfiguracoes.lb_iconeMeuNiver.setEnabled(l_habilita);
        Configuracoes.s_telaConfiguracoes.lb_iconeDataCadastro.setEnabled(l_habilita);

        //SELECIONA
        Configuracoes.s_telaConfiguracoes.cb_mostrarMinhaFrase.setSelected(l_habilita);
        Configuracoes.s_telaConfiguracoes.cb_mostrarSexo.setSelected(l_habilita);
        Configuracoes.s_telaConfiguracoes.cb_mostrarDataNiver.setSelected(l_habilita);
        Configuracoes.s_telaConfiguracoes.cb_mostrarDataCadastro.setSelected(l_habilita);

        //ATUALIZA  NO BANCO
        AtualizarDados.s_AtualizarDados.atualizaValorCheckBoxNoBanco(Configuracoes.s_telaConfiguracoes.cb_mostrarMinhaFrase);
        AtualizarDados.s_AtualizarDados.atualizaValorCheckBoxNoBanco(Configuracoes.s_telaConfiguracoes.cb_mostrarSexo);
        AtualizarDados.s_AtualizarDados.atualizaValorCheckBoxNoBanco(Configuracoes.s_telaConfiguracoes.cb_mostrarDataNiver);
        AtualizarDados.s_AtualizarDados.atualizaValorCheckBoxNoBanco(Configuracoes.s_telaConfiguracoes.cb_mostrarDataCadastro);

    }

    public void retiraFocoDaListaAmigosRecados(JTable l_tabela){

            String l_texto = null;

            //PEGA O MODELO
            DefaultTableModel modelo = (DefaultTableModel) l_tabela.getModel();
            modelo.setNumRows(0);

            //TESTE DE TABELA
                if(l_tabela.equals(Configuracoes.jt_listaAmigosConf))
                    l_texto = "Cor do APELIDO na lista";
                        else if(l_tabela.equals(Configuracoes.jt_listaRecadosConf))
                            l_texto = "Cor do CONTEÚDO na lista";

            try{
                   //linha de recado
                    modelo.addRow(new Object[]{l_texto});
            }
            catch(Exception e){
                    System.out.println("Erro ao tentar retirar foco da ");
            }
    }

        public void selecionaApelidoListaBatePapo(String l_apelido){

            String l_valorCelula = null;
            String l_apelidoCelula = null;
            int l_qtdLinhas;

            //PEGA O MODELO
            DefaultTableModel modelo = (DefaultTableModel)Moniersn.jt_listaAmigosConectados.getModel();
            l_qtdLinhas = modelo.getRowCount();

            try{
                System.out.println("Selecionado apelido na lista do bate papo...");
                for(int cont = 0; cont <= l_qtdLinhas; cont++){

                    l_valorCelula = String.valueOf(modelo.getValueAt(cont, 0));
                    l_apelidoCelula = pegaApelidoOuHumor(l_valorCelula, "apelido");

                        if(l_apelidoCelula.equals(l_apelido)){
                            System.out.println("encontrado na linha: "+cont);
                            //seleciona a célula com o apelido
                            Moniersn.jt_listaAmigosConectados.setRowSelectionInterval(cont, cont);
                            break;
                        }else{
                    System.out.println("este nao...");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao tentar selecionar o amigo na lista do bate-papo.\nErro: " + e);
        }
    }


        //preenche o array com o apelido|humor dos usuario para osterior acesso de emergencia!!
        public void preencheArrayComHumorUsuarios(){

            LogUsuario.s_humorUsuarios = null;
            
            LogUsuario.s_humorUsuarios = new String[LogUsuario.s_qtdTotUsu+20];
            String l_apelidoHumor = null;
            String l_apelido = null;
            String l_humor = null;
            String l_sqlSelect = null;
            int l_cont = 0;

            //PEGA O MODELO
            try {

               l_sqlSelect = "SELECT moniersn, icone_humor "+
                             "FROM usuario ";

                //caso recado publico recebido...
                Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
                    if(Dados.s_conexaoBanco.c_resultset.first()){

                          do{  
                            l_apelido = Dados.s_conexaoBanco.c_resultset.getString("moniersn");
                            l_humor = Dados.s_conexaoBanco.c_resultset.getString("icone_humor");
                            l_apelidoHumor = l_apelido+"|"+l_humor;

                            LogUsuario.s_humorUsuarios[l_cont] = l_apelidoHumor;

                            l_cont++;

                          }while(Dados.s_conexaoBanco.c_resultset.next());

                    System.out.println("Lista com apelidoHumor carregada.");  

                    }else{
                        System.err.println("Nenhum usuário ainda para preencher array com apelidoHumor.");
                    }

            }
            catch (Exception e) {
                    System.err.println("Erro ao tentar preencher array com apelidoHumor.    \nErro: "+e);
            }
    }
            
    public String pegaHumorDoArrayHumorUsuarios(String l_apelido){        
            
        int l_maxContador = LogUsuario.s_humorUsuarios.length;
        String l_valorSlotArray = null;
        String l_apelidoSlot = null;
        String l_humor = null;
        
            try{                
            
//                System.out.println("buscando humor de "+l_apelido+" na lista de usuários...");
                for(int cont = 0; cont <= l_maxContador; cont++){

                    l_valorSlotArray = LogUsuario.s_humorUsuarios[cont];
                    l_apelidoSlot = pegaApelidoOuHumor(l_valorSlotArray, "apelido");

                        if(l_apelidoSlot.equals(l_apelido)){
//                            System.out.println("encontrado no Slot: "+cont);
                            //seleciona a célula com o apelido
                            l_humor = pegaApelidoOuHumor(l_valorSlotArray, "humor");
                            break;
                        }
//                        else{
//                            System.out.println("este nao...");
//                        }
                }

            }
            catch(Exception e){
                    System.err.println("Erro ao tentar selecionar o amigo na lista do bate-papo.\nErro: "+e);
            }

        return l_humor;

    }

//        //preenche o array com o apelido|humor dos usuario para osterior acesso de emergencia!!
//        public void preencheArrayComCodTipoUsuario(){
//
//            LogUsuario.s_codTipoUsuarios = null;
//            
//            LogUsuario.s_codTipoUsuarios = new String[LogUsuario.s_qtdTotUsu+20];
//            String l_apelidoHumor = null;
//            String l_apelido = null;
//            String l_humor = null;
//            String l_sqlSelect = null;
//            int l_cont = 0;
//
//            //PEGA O MODELO
//            try {
//
//        l_sqlSelect = "SELECT a.*, b.* "
//                    +"FROM contato_de_usuario a, contato_de_usuario b "
//                    +"WHERE a.cod_usuario = "+l_codUsuario+" "
//                    +"AND a.cod_amigo = "+l_codAmigo+" "
//                    +"AND b.cod_usuario = "+l_codAmigo+" "
//                    +"AND b.cod_amigo = "+l_codUsuario;
//
//                //caso recado publico recebido...
//                Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
//                    if(Dados.s_conexaoBanco.c_resultset.first()){
//
//                          do{  
//                            l_apelido = Dados.s_conexaoBanco.c_resultset.getString("moniersn");
//                            l_humor = Dados.s_conexaoBanco.c_resultset.getString("icone_humor");
//                            l_apelidoHumor = l_apelido+"|"+l_humor;
//
//                            LogUsuario.s_humorUsuarios[l_cont] = l_apelidoHumor;
//
//                            l_cont++;
//
//                          }while(Dados.s_conexaoBanco.c_resultset.next());
//
//                    System.out.println("Lista com apelidoHumor carregada.");  
//
//                    }else{
//                        System.err.println("Nenhum usuário ainda para preencher array com apelidoHumor.");
//                    }
//
//            }
//            catch (Exception e) {
//                    System.err.println("Erro ao tentar preencher array com apelidoHumor.    \nErro: "+e);
//            }
//    }
    
    
    public void preparaParaInserir(JComboBox l_combo){

             l_combo.setEditable(true);
             l_combo.setSelectedItem("");
             l_combo.requestFocus();

    }

    public boolean verificaSeItemJaExiste(JComboBox l_combo, String l_tabela, String l_atributo, String l_valor){

        boolean l_podeGravar = true;

            try {

             String l_sqlSelect = "SELECT "+l_atributo+
                                  " FROM "+l_tabela+
                                  " WHERE "+l_atributo+" = '"+l_valor+"'";

            //caso recado publico recebido...
            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
                if(Dados.s_conexaoBanco.c_resultset.first()){
                    l_podeGravar = false;
                    //JOptionPane.showMessageDialog(null, "Este ítem já foi adicionado. Selecione e atualize!", "MonierSN", JOptionPane.WARNING_MESSAGE);
                    l_combo.showPopup();
                    l_combo.setSelectedItem(l_valor);
                    l_combo.setEditable(false);
                }

                }
            catch (Exception e) {
                System.err.println("Erro ao tentar verificar se item a ser gravado já existe!   \nErro: "+e);
            }

        return l_podeGravar;

     }//FIM PREENCHER LISTA


   public void getAreaNotificacao(){

         AreaNotificacao.s_areaNotific = new AreaNotificacao();

   }

   // validar campos
   public void validarCamposParaCadastroDeSenha(){

    //inicia podendo gravar pois esta tudo ok
    boolean l_camposOk = true;
    String l_msg = null;
    String l_apelido = CadastroUsuario.tf_moniersn.getText().toLowerCase();
    String l_nome = CadastroUsuario.s_telaCadastroMoniersn.tf_nome.getText();
    String l_sobrenome = CadastroUsuario.s_telaCadastroMoniersn.tf_sobrenome.getText();


            //caso o ususario não preencha o campo nome
            if(!verificaCaracteresInvalidosCadastro(l_nome)){
                l_msg = Avisos.MSG_USE_PRIM_NOME_SEM_ESP;
                l_camposOk = false;
                CadastroUsuario.s_telaCadastroMoniersn.tf_nome.requestFocus();
            }
            //caso o ususario não preencha o campo nome
            if(!verificaCaracteresInvalidosCadastro(l_sobrenome)){
                l_msg = Avisos.MSG_USE_SEG_NOME_SEM_ESP;
                l_camposOk = false;
                CadastroUsuario.s_telaCadastroMoniersn.tf_sobrenome.requestFocus();
            }
            //caso o ususario não preencha o campo nome
            if(CadastroUsuario.s_telaCadastroMoniersn.tf_nome.getText().isEmpty()){
                l_msg = Avisos.MSG_INFORME_NOME;
                l_camposOk = false;
                CadastroUsuario.s_telaCadastroMoniersn.tf_nome.requestFocus();
            }
            if(CadastroUsuario.s_telaCadastroMoniersn.tf_nome.getText().length() > 12){
                l_msg = Avisos.MSG_EXCESSO_CARACT_12;
                l_camposOk = false;
                CadastroUsuario.s_telaCadastroMoniersn.tf_nome.requestFocus();
            }
            //caso o usuário não preencha o campo sobrenome
            else if(CadastroUsuario.s_telaCadastroMoniersn.tf_sobrenome.getText().isEmpty()){
                l_msg = Avisos.MSG_INFORME_SOBRENOME;
                l_camposOk = false;
                CadastroUsuario.s_telaCadastroMoniersn.tf_sobrenome.requestFocus();
            }
            //caso o usuário não preencha o campo sobrenome
            else if(CadastroUsuario.s_telaCadastroMoniersn.tf_sobrenome.getText().length() > 15){
                l_msg = Avisos.MSG_EXCESSO_CARACT_15;
                l_camposOk = false;
                CadastroUsuario.s_telaCadastroMoniersn.tf_sobrenome.requestFocus();
            }
            //caso o sexo não seja selecionado
            else if(CadastroUsuario.s_telaCadastroMoniersn.rb_masculino.isSelected() == false && CadastroUsuario.s_telaCadastroMoniersn.rb_feminino.isSelected() == false){
                l_msg = Avisos.MSG_SELECIONE_SEXO;
                l_camposOk = false;
                CadastroUsuario.s_telaCadastroMoniersn.rb_masculino.requestFocus();
            }
            //caso o dia de nascimento não seja selecionado
            else if(CadastroUsuario.s_telaCadastroMoniersn.cb_dia.getSelectedItem().toString().isEmpty()){
                l_msg = Avisos.MSG_SELECIONE_DIA_NASC;
                l_camposOk = false;
                CadastroUsuario.s_telaCadastroMoniersn.cb_dia.requestFocus();
            }
            //caso o dia de nascimento não seja selecionado
            else if(CadastroUsuario.s_telaCadastroMoniersn.cb_mes.getSelectedItem().toString().isEmpty()){
                l_msg = Avisos.MSG_SELECIONE_MES_NASC;
                l_camposOk = false;
                CadastroUsuario.s_telaCadastroMoniersn.cb_mes.requestFocus();
            }
            //caso o dia de nascimento não seja selecionado
            else if(CadastroUsuario.s_telaCadastroMoniersn.cb_ano.getSelectedItem().toString().isEmpty()){
                l_msg = Avisos.MSG_SELECIONE_ANO_NASC;
                l_camposOk = false;
                CadastroUsuario.s_telaCadastroMoniersn.cb_ano.requestFocus();
            }

            //caso o ususario não preencha o campo nome
            if(!SolicitarDados.s_SolicitarDados.verificaCaracteresInvalidosCadastro(l_apelido)){
                l_msg = Avisos.MSG_APELIDO_SEM_SIMB_OU_ESP;
                l_camposOk = false;
                CadastroUsuario.tf_moniersn.requestFocus();
            }

            //caso o moniersn não seja informado
            else if(CadastroUsuario.tf_moniersn.getText().isEmpty()){
                l_msg = Avisos.MSG_INFORME_APELIDO;
                l_camposOk = false;
                CadastroUsuario.tf_moniersn.requestFocus();
            }
            //caso o moniersn não seja informado
            else if(CadastroUsuario.tf_moniersn.getText().length()< 3){
                l_msg = Avisos.MSG_APELIDO_MIN_3_CARACT;
                l_camposOk = false;
                CadastroUsuario.tf_moniersn.requestFocus();
            }
            //caso o moniersn não seja informado
            else if(CadastroUsuario.tf_moniersn.getText().length() > 15){
                l_msg = Avisos.MSG_EXCESSO_CARACT_15;
                l_camposOk = false;
                CadastroUsuario.tf_moniersn.requestFocus();
            }

         //se tudo ok...
            if(l_camposOk)
                verificarDisponibilidadeDeApelido(l_apelido);
            else
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(l_msg, "aviso");

   }//fim validarCamposParaCadastroDeSenha()

   // verificar email existente
   public void verificarDisponibilidadeDeApelido(String l_apelido){


       String l_sqlSelect = "SELECT moniersn "
                          + "FROM usuario "
                          + "WHERE moniersn = '"+l_apelido+"'";
       try{
            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            
            if(Dados.s_conexaoBanco.c_resultset.first()){

                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_APELIDO_JA_USADO, "aviso");
                CadastroUsuario.tf_moniersn.setText("");
                CadastroUsuario.tf_moniersn.requestFocus();

            }
            //caso o moniersn não exista...
            else{

                //inabilita o campo moniersn por segurança
                CadastroUsuario.tf_moniersn.setEditable(false);

                //muda o titulo e icone do botao ok pois o usuário poderá decidir mudar de loguin em ultima hora
                CadastroUsuario.bt_confirmar.setText(Avisos.TEXTO_EDITAR);
                CadastroUsuario.bt_confirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LIMPAR_CAMPO_16))); // NOI18N

                habilitaCamposCadastro(false);

                //extende o formulário para aparecer os campos de segurança
                MostrarDados.s_MostrarDados.mostraInfoSeguranca(true);

            }
       }

       catch(Exception e){
           System.err.println("Erro ao verificar apelidos existentes.   \nErro: "+e);
       }

    }//fim emails existentes
   
   
   public boolean verificaLocalArquivos(int l_codUsuario, String l_urlDiretorioArquivos, String l_tipoOp){
   
       boolean l_localOk;
       
            //caso o usuário nao tenha definido o local dos arquivos
            if(l_urlDiretorioArquivos.equals("*")){
                
                 //o diretorio do usuario esta ok
                 LogUsuario.s_localDeArquivosDoUsuarioConsistente = true;
                 
                 System.out.println("   O  diretório de arquivos do usuário ainda não definifido.");
                 System.out.println("   Verificação de arquivos finalizada.");
            
            //caso o usuário já tenha definido o local de arquivos
            }else{
                
                
                File l_dirAtual = new File(l_urlDiretorioArquivos);

                    //caso o dir exista
                    if(l_dirAtual.exists()){

                        //o diretorio do usuario esta ok
                        LogUsuario.s_localDeArquivosDoUsuarioConsistente = true;
                        
                        System.out.println("O  diretório de arquivos do usuário já foi definido e está ok!\n"
                                         + "Verificando arquivos enviados e baixados...");


                    //caso dir nao exista no local
                    }else{
                        
                        //o diretorio do usuario NAO esta ok
                        LogUsuario.s_localDeArquivosDoUsuarioConsistente = false;
                        //esconde tela principal
                        System.err.println("    O  diretório de arquivos do usuário já foi definido, "
                                              + "mas não foi encontrado no local.");
                        
                    }
                    
                //VERIFICA ARQUIVOS ENVIADOS
                verificaDiretorioArquivos(l_urlDiretorioArquivos, Diretorios.PASTA_ENVIADOS, l_codUsuario);

                //VERIFICA ARQUIVOS BAIXADOS
                verificaDiretorioArquivos(l_urlDiretorioArquivos, Diretorios.PASTA_BAIXADOS, l_codUsuario);

                //contagem de ítens
                contagemDeItensDoUsuario(l_codUsuario);
                
                if(!l_tipoOp.equals("baixar") && !l_tipoOp.equals("abrir"))
                    MostrarDados.s_MostrarDados.preencherListaDeArquivos(l_codUsuario, Moniersn.jt_listaArquivosUsuario, LogUsuario.s_listaArquivoSelecionada);

                MostrarDados.s_MostrarDados.habilitaBotoesConteudo(Moniersn.jt_listaArquivosUsuario);

            }
            
            l_localOk = LogUsuario.s_localDeArquivosDoUsuarioConsistente;
            
            return l_localOk;
   
   }
   
   public void verificaDiretorioArquivos(String l_urlDirUsuario, String l_pasta, int l_codUsuario){     
   
        int l_qtdArquivos = 0;
        String l_sqlSelect = null;
        String l_urlDirArq = null;
        String l_nomeArq[];
        int l_codArq[];
        int l_posicaoSlotArray = 0;
        boolean l_arqAtualizados = false;
        
            try{
                atualizaArquivosExistentesDoDiretorioNoBanco(l_urlDirUsuario, l_pasta, l_codUsuario);
                l_arqAtualizados = true;
                
            }catch(Exception e){
                l_arqAtualizados = false;
                System.err.println("Erro na atualização de arquivos existentes.     \nErro: "+e);
            }
        
                if(l_arqAtualizados){

                        //caso o diretorio verificado seja a pasta enviados
                        if(l_pasta.equals(Diretorios.PASTA_ENVIADOS)){

                            l_qtdArquivos = LogUsuario.s_qtdTotArqEnviados;

                            //pega lista de arquivo enviados
                            l_sqlSelect = "SELECT a.cod_arquivo, a.nome "
                                        + "FROM arquivo a "
                                        + "WHERE a.gravado = 'S' "
                                        + "AND a.cod_emissor = "+l_codUsuario+" "
                                        + "ORDER BY a.cod_arquivo "
                                        + "ASC";

                        }


                        else if(l_pasta.equals(Diretorios.PASTA_BAIXADOS)){

                            l_qtdArquivos = LogUsuario.s_qtdTotArqBaixados;

                            //pega lista de arquivo enviados
                            l_sqlSelect = "SELECT a.cod_arquivo, a.nome "
                                          + "FROM usuario u, arquivo a, arquivo_usuario au "
                                          + "WHERE u.cod_usuario = a.cod_emissor "
                                          + "AND a.cod_arquivo = au.cod_arquivo "
                                          + "AND au.gravado = 'S' "
                                          + "AND au.cod_usuario = "+l_codUsuario+" "
                                          + "AND a.cod_emissor != "+l_codUsuario+" "
                                          + "GROUP BY a.nome "
                                          + "ORDER BY a.data_hora_atu "
                                          + "ASC";

                        }


                    if(l_qtdArquivos > 0){

                        String l_urlArqVerificado = null;
                        int l_codArqVerificado = 0;

                        l_urlDirArq = l_urlDirUsuario+"\\"+Diretorios.PASTA_ARQUIVOS+"\\"+l_pasta;

                        l_nomeArq = new String[l_qtdArquivos];
                        l_codArq = new int[l_qtdArquivos];


                        Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                                try{

                                    Dados.s_conexaoBanco.c_resultset.first();

                                    do{

                                        l_nomeArq[l_posicaoSlotArray] = Dados.s_conexaoBanco.c_resultset.getString("a.nome");
                                        l_codArq[l_posicaoSlotArray] = Dados.s_conexaoBanco.c_resultset.getInt("a.cod_arquivo");

                                        l_posicaoSlotArray++;

                                    }while(Dados.s_conexaoBanco.c_resultset.next());

                                }catch(Exception e){
                                    System.err.println("As informações de arquivos enviados do usuário não puderam ser buscadas.  \nErro: "+e);
                                    return;
                                }

                            for(l_posicaoSlotArray = 0; l_posicaoSlotArray < l_qtdArquivos; l_posicaoSlotArray++){

                                l_urlArqVerificado = l_urlDirArq+"\\"+l_nomeArq[l_posicaoSlotArray];
                                l_codArqVerificado = l_codArq[l_posicaoSlotArray];


                                File l_arquivoEnv = new File(l_urlArqVerificado);

                                    //caso nao exista...
                                    if(!l_arquivoEnv.exists()){

                                        System.err.println("Arquivo: "+l_nomeArq[l_posicaoSlotArray]+" não encontrado.\nAtualizando no bd...");
                                        //atualiza no banco

                                            if(AtualizarDados.s_AtualizarDados.atualizaArquivoRemovido(l_codArqVerificado, l_pasta)){
                                               System.out.println("arquivo: "+l_nomeArq[l_posicaoSlotArray]+" Atualizado!");
                                            }else{
                                               System.err.println("Falha ao tentar atualizar!");
                                            }

                                    }else{
                                        System.out.println("Arquivo "+l_nomeArq[l_posicaoSlotArray]+" está ok.");
                                    }
                            }

                        System.out.println("Verificação de arquivos "+l_pasta+" finalizada!");


                    }else{
                        System.out.println("    Nenhum arquivo "+l_pasta+" ainda!");
                    }
                }

   }
   
   

public void atualizaArquivosExistentesDoDiretorioNoBanco(String l_urlDirUsuario, String l_pasta, int l_codUsuario){     

        File diretorio = new File(l_urlDirUsuario+"\\"+Diretorios.PASTA_ARQUIVOS+"\\"+l_pasta); 
        
            if(diretorio.exists()){
        
                File[] arquivos = diretorio.listFiles(); 

                if(arquivos != null){ 

                    int l_qtdArquivos = arquivos.length; 

                    for(int i = 0; i < l_qtdArquivos; ++i){ 

                        File arquivo = arquivos[i]; 
                        String l_nomeArquivo = arquivo.getName();

                        AtualizarDados.s_AtualizarDados.atualizaArquivoEncontrado(l_nomeArquivo, l_pasta);

                    } 

                    //conta itens
                    contagemDeItensDoUsuario(l_codUsuario); 
                }
            }else{
                System.out.println("A pasta "+l_pasta+" não foi criada ainda!");
            }

   }
   
   
   //verifica se as informações de segurança estão corretas
   public boolean validarCamposDeSeguranca(){

        //de início autoriza a gravar
        boolean l_podeGravar = true;
        String l_msg = null;

                //caso o campo de senha não seja preenchido
                if(CadastroUsuario.pf_senha.getText().isEmpty()){
                    l_msg = Avisos.MSG_INFORME_SENHA_ACESSO;
                    l_podeGravar = false;
                    CadastroUsuario.pf_senha.requestFocus();
                }
                //caso o campo de confirmação de senha não seja preenchido
                else if(CadastroUsuario.pf_conf_senha.getText().isEmpty()){
                    l_msg = Avisos.MSG_CONFIRME_SENHA_ACESSO;
                    l_podeGravar = false;
                    CadastroUsuario.pf_conf_senha.requestFocus();
                }
                //caso o campo de pergunta secreta não seja selecionado
                else if(CadastroUsuario.cb_pergunta_secreta.getSelectedItem().equals("")){
                    l_msg = Avisos.MSG_SELECIONE_PERG_SECRETA;
                    l_podeGravar = false;
                    CadastroUsuario.cb_pergunta_secreta.requestFocus();
                }
                //caso o campo de resposta secreta não fro preenchido
                else if(CadastroUsuario.tf_resposta_secreta.getText().isEmpty()){
                    l_msg = Avisos.MSG_RESP_PERG_SECRET;
                    l_podeGravar = false;
                    CadastroUsuario.tf_resposta_secreta.requestFocus();
                }
                //entrando no teste de senhas
                if(l_podeGravar == true){
                    //testa se as senhas são iguais
                    if(CadastroUsuario.pf_senha.getText().equals(CadastroUsuario.pf_conf_senha.getText()))
                        l_podeGravar = true;
                    else{
                        l_msg = Avisos.MSG_SENHAS_N_IGUAIS;
                        l_podeGravar = false;
                        CadastroUsuario.pf_senha.setText("");
                        CadastroUsuario.pf_conf_senha.setText("");
                        CadastroUsuario.pf_senha.requestFocus();
                    }//fim else
                }//fim if

            if(l_podeGravar == false){
                
                //esconde tela carregando
                Aguardando.s_telaAguardando.setVisible(false);
                
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(l_msg, "aviso");
            }

        return l_podeGravar;

   }//fim validar campos

   public void confirmarEditarInfoBasicas(){

        if(CadastroUsuario.bt_confirmar.getText().equals(Avisos.TEXTO_EDITAR)){
            //inabilita o campo moniersn por segurança
            CadastroUsuario.tf_moniersn.setEditable(true);
            CadastroUsuario.tf_moniersn.setSelectionColor(Color.green);
            CadastroUsuario.pf_senha.setText("");
            CadastroUsuario.pf_conf_senha.setText("");
            CadastroUsuario.cb_pergunta_secreta.setSelectedItem("");
            CadastroUsuario.tf_resposta_secreta.setText("");
            CadastroUsuario.bt_confirmar.setText(Avisos.TEXTO_OK);
            CadastroUsuario.bt_confirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.OK_16))); // NOI18N

            //deixa o form com um tamanho que não apareça logo os campos de senha
            MostrarDados.s_MostrarDados.mostraInfoSeguranca(false);

            habilitaCamposCadastro(true);

        }

        else
            validarCamposParaCadastroDeSenha();

   }

   public void habilitaCamposCadastro(boolean l_habilita){

        CadastroUsuario.s_telaCadastroMoniersn.tf_nome.setEditable(l_habilita);
        CadastroUsuario.s_telaCadastroMoniersn.tf_sobrenome.setEditable(l_habilita);
        CadastroUsuario.s_telaCadastroMoniersn.rb_masculino.setEnabled(l_habilita);
        CadastroUsuario.s_telaCadastroMoniersn.rb_feminino.setEnabled(l_habilita);
        CadastroUsuario.s_telaCadastroMoniersn.cb_dia.setEnabled(l_habilita);
        CadastroUsuario.s_telaCadastroMoniersn.cb_mes.setEnabled(l_habilita);
        CadastroUsuario.s_telaCadastroMoniersn.cb_ano.setEnabled(l_habilita);
        CadastroUsuario.s_telaCadastroMoniersn.cb_humor.setEnabled(l_habilita);
        
        CadastroUsuario.jp_infoSeguranca.setVisible(!l_habilita);

   }

    public int buscaCodEmissorOcorrencia(int l_codOcorrencia){

        int l_codEmissor = 0;
        String l_sqlSelect;

        try{
            //pega status
            l_sqlSelect = "SELECT cod_emissor "
                        + "FROM ocorrencia WHERE cod_ocorrencia = "+l_codOcorrencia;

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            l_codEmissor = Dados.s_conexaoBanco.c_resultset.getInt("cod_emissor");

        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o código do emissor da ocorrencia.  \nErro: "+e);
        }

        return l_codEmissor;
    }

    public int buscaCodEmissorRecado(int l_codRecado){

        int l_codEmissor = 0;
        String l_sqlSelect;

        try{
            //pega status
            l_sqlSelect = "SELECT cod_emissor "
                        + "FROM recado WHERE cod_recado = "+l_codRecado;

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            l_codEmissor = Dados.s_conexaoBanco.c_resultset.getInt("cod_emissor");

        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o código do emissor do recado.     \nErro: "+e);
        }

        return l_codEmissor;
    }
    
    public int buscaCodDestinatarioRecado(int l_codRecado){

        int l_codDest = 0;
        String l_sqlSelect;

        try{
            //pega status
            l_sqlSelect = "SELECT cod_destinatario "
                        + "FROM recado WHERE cod_recado = "+l_codRecado;

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            l_codDest = Dados.s_conexaoBanco.c_resultset.getInt("cod_destinatario");

        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o código do emissor do recado.     \nErro: "+e);
        }

        return l_codDest;
    }

    public int buscaCodEmissorAtualizacao(int l_codAtu){

        int l_codEmissor = 0;
        String l_sqlSelect;

        try{
            //pega status
            l_sqlSelect = "SELECT cod_usuario "
                        + "FROM atualizacoes_usuario "
                        + "WHERE cod_atu = "+l_codAtu;

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            l_codEmissor = Dados.s_conexaoBanco.c_resultset.getInt("cod_usuario");

        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o código do emissor da atualizacao.     \nErro: "+e);
        }

        return l_codEmissor;
    }

    public int buscaCodEmissorArquivo(int l_codArquivo){

        int l_codEmissor = 0;
        String l_sqlSelect;

        try{
            //pega status
            l_sqlSelect = "SELECT cod_emissor "
                        + "FROM arquivo "
                        + "WHERE cod_arquivo = "+l_codArquivo;

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            l_codEmissor = Dados.s_conexaoBanco.c_resultset.getInt("cod_emissor");

        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o código do emissor do arquivo.    \nErro: "+e);
        }

        return l_codEmissor;
    }

    public int buscaCodEmissorComentario(int l_codComentario){

        int l_codEmissor = 0;
        String l_sqlSelect;


        try{
            //pega status
            l_sqlSelect = "SELECT cod_usuario "
                        + "FROM comentario "
                        + "WHERE cod_comentario = "+l_codComentario;

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            l_codEmissor = Dados.s_conexaoBanco.c_resultset.getInt("cod_usuario");

        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o código do emissor do comentário.      \nErro: "+e);
        }

        return l_codEmissor;
    }
    
    public String buscaPerfilAtualizado(int l_codAtu){

        String l_perfil = null;
        String l_sqlSelect;

        try{
            //pega status
            l_sqlSelect = "SELECT perfil "
                        + "FROM atualizacoes_usuario "
                        + "WHERE cod_atu = "+l_codAtu;

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            l_perfil = Dados.s_conexaoBanco.c_resultset.getString("perfil");

        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o perfil atualizado.    \nErro: "+e);
        }

        return l_perfil;
    }

    public String buscaAtributoAtualizado(int l_codAtu){

        String l_atributo = null;
        String l_sqlSelect;

        try{
            //pega status
            l_sqlSelect = "SELECT atributo "
                        + "FROM atualizacoes_usuario "
                        + "WHERE cod_atu = "+l_codAtu;

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            l_atributo = Dados.s_conexaoBanco.c_resultset.getString("atributo");

        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o atributo atualizado.      \nErro: "+e);
        }

        return l_atributo;
    }

    public int buscaLogConexaoAtual(int l_codUsuario){

        int l_codLogConexao = 0;
        String l_sqlSelect;

        try{
            //pega status
            l_sqlSelect = "SELECT cod_log "
                        + "FROM log_usuario "
                        + "WHERE cod_usuario = "+l_codUsuario+" "
                        + "ORDER BY cod_log "
                        + "ASC";

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

            if(Dados.s_conexaoBanco.c_resultset.last()){

                l_codLogConexao = Dados.s_conexaoBanco.c_resultset.getInt("cod_log");
            }
        }
        catch(Exception e){
            System.err.println("Não foi possível buscar o código do emissor da atualizacao.     \nErro: "+e);
        }

        return l_codLogConexao;
    }
        
    

    public int pegaCodigoDoConteudoSelecionadoNaLista(int l_linha, JTable l_tabela){
    
        int l_codigo = 0;
        TableModel l_modelo;

        //RECADOS PUBLICOS || RECADOS PRIVADOS DO USUARIO
        if(l_tabela.equals(Moniersn.jt_listaRecadosPubUsuario) || l_tabela.equals(Moniersn.jt_listaRecadosPriv)){

            l_modelo= l_tabela.getModel();
//            l_linha = l_tabela.getSelectedRow();

            l_codigo = Integer.parseInt(String.valueOf(l_modelo.getValueAt(l_linha, 4)));

        }

        //RECADOS PUBLICOS DO AMIGO
        if(l_tabela.equals(Moniersn.jt_listaRecadosPubAmigo)){

            l_modelo= l_tabela.getModel();
//            l_linha = l_tabela.getSelectedRow();

            l_codigo = Integer.parseInt(String.valueOf(l_modelo.getValueAt(l_linha, 3)));

        }

//        int l_linha;

        //OCORREENCIAS DO USUARIO ou ATUALIZACOES
        if(l_tabela.equals(Moniersn.jt_listaOcorrenciaUsuario) ||
           l_tabela.equals(Moniersn.jt_listaOcorrenciaAmigo) ||
           l_tabela.equals(Moniersn.jt_listaAtualizacoesUsuario) ||
           l_tabela.equals(Moniersn.jt_listaAtualizacoesAmigo)){
        
            l_modelo= l_tabela.getModel();
//            l_linha = l_tabela.getSelectedRow();
            
            l_codigo = Integer.parseInt(String.valueOf(l_modelo.getValueAt(l_linha, 3)));
            
        }

        //LISTA DE COMENTARIOS
        if(l_tabela.equals(Comentario.jt_listaComentarios)){

            l_modelo= l_tabela.getModel();
//            l_linha = l_tabela.getSelectedRow();

            l_codigo = Integer.parseInt(String.valueOf(l_modelo.getValueAt(l_linha, 0)));

        }

        //ARQUIVOS DO USUARIO OU DO AMIGO
        if(l_tabela.equals(Moniersn.jt_listaArquivosUsuario) || l_tabela.equals(Moniersn.jt_listaArquivosAmigo)){

            l_modelo= l_tabela.getModel();
//            l_linha = l_tabela.getSelectedRow();

            l_codigo = Integer.parseInt(String.valueOf(l_modelo.getValueAt(l_linha, 5)));

        }

        return l_codigo;
    
    }

    public String pegaNomeArquivoSelecionado(int l_linha){

        String l_nome = null;
        String l_nomeTratado = null;
        TableModel l_modelo;

        l_modelo= Moniersn.jt_listaArquivosUsuario.getModel();

        l_nome = String.valueOf(l_modelo.getValueAt(l_linha, 2));

        l_nomeTratado = SolicitarDados.s_SolicitarDados.recolocaAspasSimplesParaSO(l_nome);

        return l_nomeTratado;

    }


//    public String empacotaDadosDoArquivo(String l_nomeArq, int l_tamanhoArq){
//
//        String l_dadosEmpacotados = null;
//
//        l_dadosEmpacotados = l_nomeArq+"|"+l_tamanhoArq+"^"+LogUsuario.s_moniersn;
//
//        return l_dadosEmpacotados;
//    }

//    public String pegaInfomacaoDoArquivo(File l_arquivo){
//
//        String l_dadosDoArquivo = null;
//        String l_nome;
//        long l_tamanho;
//
//        if(l_arquivo.exists() && l_arquivo.isFile()){
//
//            if(l_arquivo.canRead()){
//
//                l_nome = l_arquivo.getName();
//                l_tamanho = l_arquivo.length();
//
//                l_dadosDoArquivo = "\n·Nome: "+l_nome+"\n·Tamanho: "+l_tamanho+" byte(s)";
//
//            }else{
//
//                l_dadosDoArquivo = "erro";
//                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_PROTEGIDO, "erro");
//            }
//
//        }
//        else{
//
//                l_dadosDoArquivo = "erro";
//                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_PROTEGIDO, "erro");
//            }
//
//        return l_dadosDoArquivo;
//    }

        //separa os dados recebidos pegando apenas o tipo de aviso recebido
    public int pegaCodigoDoAvisoUDP(String c_dadosRecebidos){

        int l_codAviso = 0;

        int l_posicaoInicial = 0;

        //a posição final é a barra reta
        int l_posicaoFinal = c_dadosRecebidos.lastIndexOf('|');

        //o tipo de aviso vai do começo até a barra reta
        l_codAviso = Integer.parseInt(c_dadosRecebidos.substring(l_posicaoInicial, l_posicaoFinal));

        return l_codAviso;

    }

        //separa os dados recebidos pegando apenas o cod do emissor
    public int pegaCodigoEmissorAvisoUDP(String c_dadosRecebidos){

        int l_posicaoFinal;
        int l_codEmissor;

        //a posição inicial é uma posição apos a barra reta
        int l_posicaoInicial = c_dadosRecebidos.lastIndexOf('|')+1;

        l_posicaoFinal = c_dadosRecebidos.lastIndexOf('$');

        //o id do usuario emissor começa da barra reta
        l_codEmissor = Integer.parseInt(c_dadosRecebidos.substring(l_posicaoInicial, l_posicaoFinal));

        return l_codEmissor;

    }

        //separa os dados recebidos pegando apenas o tipo de aviso recebido
    public String pegaRecadoInstantaneoUDP(String c_dadosRecebidos){

        String l_recado = "";

        //a posição inicial é após o cifrão
        int l_posicaoInicial = c_dadosRecebidos.lastIndexOf('$')+1;

        //quantos de caracteres tem na string de dados é a posição final
        int l_posicaoFinal = c_dadosRecebidos.length();

        //o tipo de aviso vai do começo até a barra reta
        l_recado = c_dadosRecebidos.substring(l_posicaoInicial, l_posicaoFinal);

        return l_recado;

    }

    //separa os dados recebidos pegando apenas o tipo de aviso recebido
    public int pegaCodDestinatarioAvisoUDP(String c_dadosRecebidos){

        int l_codDestinatario = 0;

        //a posição inicial é após o cifrão
        int l_posicaoInicial = c_dadosRecebidos.lastIndexOf('$')+1;

        //quantos de caracteres tem na string de dados é a posição final
        int l_posicaoFinal = c_dadosRecebidos.length();

        //o tipo de aviso vai do começo até a barra reta
        l_codDestinatario = Integer.parseInt(c_dadosRecebidos.substring(l_posicaoInicial, l_posicaoFinal));

        return l_codDestinatario;

    }

    public int pegaCodArquivoClienteTCP(String l_dadosRecebidos){

        int l_codArquivo = 0;

        int l_posicaoInicial = 0;

        //a posição final é a barra reta
        int l_posicaoFinal = 0;

        if(l_dadosRecebidos.isEmpty())
            l_codArquivo = 0;

        else{

            l_posicaoInicial = 0;

            //a posição final é a barra reta
            l_posicaoFinal = l_dadosRecebidos.lastIndexOf('|');

            //o codigo do arquivo vai do começo até a barra reta
            l_codArquivo = Integer.parseInt(l_dadosRecebidos.substring(l_posicaoInicial, l_posicaoFinal));

        }

        return l_codArquivo;
    }

    public int pegaCodStatusArquivoSolicitado(String l_dadosRecebidos){

        int l_codStatus = 0;

        int l_posicaoInicial = 0;

        //a posição final é a barra reta
        int l_posicaoFinal = 0;

        if(l_dadosRecebidos.isEmpty())
            l_codStatus = 0;

        else{

            l_posicaoInicial = 0;

            //a posição final é a barra reta
            l_posicaoFinal = l_dadosRecebidos.lastIndexOf('|');

            //o tipo de aviso vai do começo até a barra reta
            l_codStatus = Integer.parseInt(l_dadosRecebidos.substring(l_posicaoInicial, l_posicaoFinal));

        }

        return l_codStatus;
    }

//    public String pegaApelidoUsuarioClienteTCP(String c_dadosRecebidos){
//
//        String l_apelido = null;
//
//        int l_posicaoInicial = c_dadosRecebidos.lastIndexOf('^')+1;
//
//        //a posição final é a barra reta
//        int l_posicaoFinal = c_dadosRecebidos.lastIndexOf(" ");
//
//        //o tipo de aviso vai do começo até a barra reta
//        l_apelido = c_dadosRecebidos.substring(l_posicaoInicial, l_posicaoFinal);
//
//        return l_apelido;
//
//    }

    public String pegaHostUsuario(){

        //guardará o núemro da porta definido
        String l_hostUsuario = null;

            //pega o nome do host
            try{
                //pega o nome do host
                System.out.println("Solicitando informacoes do HOST...");

                l_hostUsuario = String.valueOf(InetAddress.getLocalHost().getHostName());

                //guarda na variavel global statica
                LogUsuario.s_host = l_hostUsuario;

                System.out.println("Host identificado: "+LogUsuario.s_host);

            }catch(Exception e){

                    System.err.println("Não foi possível obter nome do HOST.    \nErro: "+e);
            }

        return l_hostUsuario;
    }


    public static int geraPortaAutomaticamente(String l_tipoServer){

        int l_portaGerada = 0;
        int l_portaAtual;
        int l_codUsuario = LogUsuario.s_cod;
        String l_hostUsuario = LogUsuario.s_host;
        
        

        String l_atributPortaServer = null;

            if(l_tipoServer.equals("UDP"))
                l_atributPortaServer = "porta_udp";
            else
                l_atributPortaServer = "porta_tcp";

        try{

        System.out.println("Definindo randomicamente o número de porta "+l_tipoServer);
        l_portaGerada = (int)(Math.random()*65535);

        System.out.println("A porta escolhida randomicamente é: "+l_portaGerada);
            //ao definir a porta, testa no loop se alguem está utilizando a mesma porta e host
            do{
                //consulta no banco se tem alguma porta com o mesmo ip e host ja conectados
                String l_sqlSelect = "SELECT host, porta_udp, porta_tcp FROM usuario "
                                   + "WHERE host = '"+l_hostUsuario+"' "
                                   + "AND porta_udp  = "+l_portaGerada+" "
                                   + "OR porta_tcp = "+l_portaGerada;

                //executa a query >>
                Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                    //caso exista host e porta identicos aos definidos...
                    if(Dados.s_conexaoBanco.c_resultset.first()){

                        l_portaAtual = Dados.s_conexaoBanco.c_resultset.getInt(l_atributPortaServer);
                        //faz uma nova definiçao de porta
                        System.out.println("Esta porta e host já estão em uso. Redefinindo...");

                        //define um novo numero aleatório de porta
                        l_portaGerada = (int)(Math.random()*65535);

                        System.out.println("A nova porta é: "+l_portaGerada);
                    }

                    else{
                        System.out.println("Porta livre definida com exito!");
                        break;
                    }
            //repete ate o número de porta for diferente do que está no banco
            }while((l_portaGerada == l_portaAtual) || (l_portaGerada <= 1500));

            //Atualiza a nova porta do servidor no banco
            AtualizarDados.s_AtualizarDados.atualizaHostPortaNoBanco(l_codUsuario, l_hostUsuario, l_portaGerada, l_tipoServer);
            
        }
        catch(Exception e){
                System.err.println("Erro ao tentar definir a porta para socket o servidor "+l_tipoServer+"   \nErro:"+e);      }

        return l_portaGerada;

    }//fim defineHostPorta


    
    
    public void baixaArquivoSelecionado(JTable l_tabela){

        boolean l_podeContinuar = false;
        
            SolicitarDados.s_SolicitarDados.verificaLocalArquivos(LogUsuario.s_cod, LogUsuario.s_localDeArquivosDoUsuario, "baixar");
        

            if(LogUsuario.s_localDeArquivosDoUsuario.equals("*")){

                l_podeContinuar = MostrarDados.s_MostrarDados.mostraTelaEscolhaDiretorio("definir");

            //caso o local de arquivos do usuário nao esteja no lugar...
            }else if(!LogUsuario.s_localDeArquivosDoUsuarioConsistente){
                
                l_podeContinuar = MostrarDados.s_MostrarDados.mostraTelaEscolhaDiretorio("localizar");
                
            }else
                l_podeContinuar = true;

            
        if(l_podeContinuar){

                boolean l_aceitou = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_BAIXAR_ARQUIVO, Avisos.BAIXAR_ARQUIVO, Icones.ARQUIVO_BAIXAR);

                    //caso aceitou baixar...
                    if(l_aceitou){              
            
                        //l_tabela arquivo
                        TableModel l_tabelaArquivo = l_tabela.getModel();
                        String l_apelido = null;

                        //linha selecionada
                        int l_linhaSelecionada = l_tabela.getSelectedRow();

                        //nome do arquivo
                        int l_codArquivo = Integer.parseInt(l_tabelaArquivo.getValueAt(l_linhaSelecionada, 5).toString());
                        String l_nomeArquivo = l_tabelaArquivo.getValueAt(l_linhaSelecionada, 2).toString();

                            //caso lista de arquivo do amigo...
                            if(l_tabela.equals(Moniersn.jt_listaArquivosAmigo))
                                l_apelido = LogAmigo.s_moniersn;
                            else
                                //apelido
                                l_apelido = SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(l_tabelaArquivo.getValueAt(l_linhaSelecionada, 0).toString(), "apelido");


                                //pega o nome do host do usuario
                                String l_hostServidorTCP = SolicitarDados.s_SolicitarDados.pegaHostPeloApelido(l_apelido);

                                int l_portaServidorTCP = SolicitarDados.s_SolicitarDados.pegaPortatPeloApelido(l_apelido, "TCP");

                                //inicia cliente
                                ClienteTCP cliTCP = new ClienteTCP(l_hostServidorTCP, l_portaServidorTCP, l_codArquivo);

                                //nova thread
                                new Thread(cliTCP).start();

                                //mostra msg no sys tray
                                MostrarDados.s_MostrarDados.mostraAvisoProcedimento(Avisos.FACECARD_FULL, Avisos.AVISO_BAIXANDO_ARQUIVO+l_nomeArquivo, "INFO");


                        }else{ 
                            Moniersn.s_telaMsn.setVisible(true); 
                            return;
                        }
            
        }else{
            Moniersn.s_telaMsn.setVisible(true); 
        }
    }



//    public byte[] pegaBytesArquivo(File file){
//
//
//        int MAXLENGTH = 100000000; // se for > 100 M então vamos evitar carregar tudo!
//        InputStream is = null;
//        byte[] l_bufferArquivo = null;
//
//            try {
//                long l_tamArquivo = file.length();
//                    if (l_tamArquivo > MAXLENGTH)
//                        throw new IllegalArgumentException ("File is too big");
//
//                l_bufferArquivo = new byte [(int) l_tamArquivo];
//
//                is = new FileInputStream(file);
//
//                is.read(l_bufferArquivo);
//
//            } catch (IOException ex) {
//                System.err.println("Erro ao tentar pegar bytes do arquivo.  \nErro: "+ex);
//            }
//
//
//        return l_bufferArquivo;
//    }

    public int pegaQtdConectados(int l_excetoCodUsuario){

         int l_qtdConectados = 0;

         String l_sqlSelectCount = "SELECT COUNT(cod_usuario) AS qtd "
                                   + "FROM usuario "
                                   + "WHERE ativo = 'S' "
                                   + "AND status_contato = 'conectado' "
                                   + "AND cod_usuario != "+l_excetoCodUsuario+" "
                                   + "LIMIT 0, "+LogUsuario.s_qtdUsuMostrar; 

                    //executa query
                    Dados.s_conexaoBanco.executeSELECT(l_sqlSelectCount);

                    try{
                           if(Dados.s_conexaoBanco.c_resultset.first()){

                                l_qtdConectados =  Dados.s_conexaoBanco.c_resultset.getInt("qtd");

                                System.out.println("Qtd. de usuários conectados: "+l_qtdConectados);

                            }else{
                                l_qtdConectados = 0;
                            }

                    }catch(Exception e){
                            System.err.println("Erro na contagem de usuários conectados.    \nErro: "+e);
                    }

          return l_qtdConectados;

    }


    public String pegaInformacoesAtuais(){

        Data.atualizaDataHora();

        String l_comprimento = Data.comprimentacao();

        String l_textoMsg = l_comprimento+", "+LogUsuario.s_nome+"! "+Avisos.TEXTO_BEM_VINDO_AO_FACE_CARD+"\n\n";

        if(LogUsuario.s_qtdOcoRecRecente > 0 ||
           LogUsuario.s_qtdTotAtuRecRecente > 0 ||
           LogUsuario.s_qtdRecPrivRecNaoLido >0 ||
           LogUsuario.s_qtdRecPubRecNaoLido > 0 ||
           LogUsuario.s_qtdTotArqDisponiveis > 0){

               l_textoMsg += Avisos.TEXTO_VOCE_TEM_NOVIDADES+"\n\n";

                if(LogUsuario.s_qtdOcoRecRecente > 0){

                   l_textoMsg += "# ("+LogUsuario.s_qtdOcoRecRecente+") "+Avisos.TEXTO_OCORRENCIAS+".\n";

                }

                if(LogUsuario.s_qtdAtuRecRecenteDeAmigos > 0 && LogUsuario.s_qtdAtuRecRecenteDeEstranhos > 0){

                     l_textoMsg += "# ("+LogUsuario.s_qtdTotAtuRecRecente+") "+Avisos.TEXTO_ATUALIZAÇÕES+".\n";

                    l_textoMsg += Avisos.TEXTO_SENDO+" ("+LogUsuario.s_qtdAtuRecRecenteDeEstranhos+") "+Avisos.TEXTO_ATUALIZAÇÕES_ESTR+".\n";
                    l_textoMsg += Avisos.TEXTO_SENDO+" ("+LogUsuario.s_qtdAtuRecRecenteDeAmigos+") "+Avisos.TEXTO_ATUALIZAÇÕES_AMIG+" .\n";

                }else{
                    if(LogUsuario.s_qtdAtuRecRecenteDeEstranhos>0){
                        l_textoMsg += "# ("+LogUsuario.s_qtdAtuRecRecenteDeEstranhos+")  "+Avisos.TEXTO_ATUALIZAÇÕES_ESTR+".\n";
                    }
                    if (LogUsuario.s_qtdAtuRecRecenteDeAmigos > 0){
                        l_textoMsg += "# ("+LogUsuario.s_qtdAtuRecRecenteDeAmigos+") "+Avisos.TEXTO_ATUALIZAÇÕES_AMIG+" .\n";
                    }
                }

                if(LogUsuario.s_qtdRecPrivRecNaoLido > 0){

                   l_textoMsg += "# ("+LogUsuario.s_qtdRecPrivRecNaoLido+") "+Avisos.TEXTO_REC_PRIVS+".\n";

                }

                if(LogUsuario.s_qtdRecPubRecNaoLido > 0){

                   l_textoMsg += "# ("+LogUsuario.s_qtdRecPubRecNaoLido+") "+Avisos.TEXTO_REC_PUBS+".\n";

                }

                if(LogUsuario.s_qtdTotArqDisponiveis > 0){

                   l_textoMsg += "# ("+LogUsuario.s_qtdTotArqDisponiveis+") "+Avisos.TEXTO_ARQ_DISPS+".\n";

                }
        }else{

            l_textoMsg += Avisos.TEXTO_NENHUMA_NOV_Q_N_TENHA_VISTO;

        }

//        if(LogUsuario.s_qtdTotOcoEnviadas > 0 ||
//           LogUsuario.s_qtdTotAtuRecebidasDeAmigos > 0 ||
//           LogUsuario.s_qtdTotAtuRecebidasDeEstranhos > 0 ||
//           LogUsuario.s_qtdTotRecPubRecebidos > 0 ||
//           LogUsuario.s_qtdTotRecPrivRecebidos > 0 ||
//           LogUsuario.s_qtdTotArqBaixados > 0){
//
//            l_textoMsg += "# Confira suas informações de conteúdos recebidos:\n";
//
//                if(LogUsuario.s_qtdTotRecPrivRecebidos>0){
//                       l_textoMsg += "  ·("+LogUsuario.s_qtdTotRecPrivRecebidos+") Recado(s) privado(s) recebidos no total.\n";
//                }
//                if(LogUsuario.s_qtdTotRecPubRecebidos > 0){
//                       l_textoMsg += "  ·("+LogUsuario.s_qtdTotRecPubRecebidos+") Recado(s) publico(s) recebidos no total.\n";
//                }
//                if(LogUsuario.s_qtdTotArqBaixados > 0){
//                       l_textoMsg += "  ·("+LogUsuario.s_qtdTotArqBaixados+") Arquivo(s) baixado(s) no total.\n";
//                }
//                if(LogUsuario.s_qtdTotOcoEnviadas > 0){
//                       l_textoMsg += "  ·("+LogUsuario.s_qtdTotOcoEnviadas+") Ocorrencias recebidas no total.\n";
//                }
//                if(LogUsuario.s_qtdTotAtuRecebidasDeEstranhos > 0){
//                       l_textoMsg += "  ·("+LogUsuario.s_qtdTotAtuRecebidasDeEstranhos+") Atualização(ões) de estranho(s) no total.\n";
//                }
//                if(LogUsuario.s_qtdTotAtuRecebidasDeAmigos > 0){
//                       l_textoMsg += "  ·("+LogUsuario.s_qtdTotAtuRecebidasDeAmigos+") Atualização(ões) de amigo(s) no total.\n";
//                }
//
//        }else{
//
//            l_textoMsg += "# Nenhuma informação de conteúdo para conferir\n!";
//
//        }


//        if(LogUsuario.s_qtdTotUsuConect > 0 ||
//           LogUsuario.s_qtdTotUsuConv > 0 ||
//           LogUsuario.s_qtdTotConvRec > 0){
//
//            l_textoMsg += "# Confira as informaçoes de usuários:\n";
//
//            if(LogUsuario.s_qtdTotUsuConect > 0 )
//                l_textoMsg += "  ·("+LogUsuario.s_qtdTotUsuConect+") Usuário(s) conectados neste momento o total.\n";
//
//            if(LogUsuario.s_qtdTotUsuConv > 0 )
//                l_textoMsg += "  ·("+LogUsuario.s_qtdTotUsuConv+") Solicitação(ões) de amizade enviada(s) por mim no total.\n";
//
//            if(LogUsuario.s_qtdTotConvRec > 0 )
//                l_textoMsg += "  ·("+LogUsuario.s_qtdTotConvRec+") Solicitação(ões) de amizade pendente(s) de aceitação.\n";
//

//        }else{
//
//            l_textoMsg += "# Nenhuma informação de usuários para conferir!";
//
//        }


        return l_textoMsg;

    }

    public boolean definicaoDeUsuarioConectado(int l_codUsuario, String l_host, int l_portaTCP){

        boolean l_conectado = false;
        OutputStream fluxoSaidaClienteTCP = null;
        InputStream fluxoEntradaClienteTCP = null;
        Socket l_socketClienteTCP = null;
        
            try{
                System.err.println("Tentando conectar ao usuário de código: "+l_codUsuario);
                l_socketClienteTCP = new Socket(l_host, l_portaTCP);
                System.out.println("Conectado ao servidor TCP do amigo!");
                l_conectado = true;

            //CASO O USUÁRIO NA ESTEJA CONECTADO CAI AQUI
            }catch(Exception e){

                    System.err.println("Não foi possível conectar o usuário");
                    l_conectado = false;

            }


            if(l_conectado){

                try{
                    //saida do CLIENTE PARA SERVIDOR
                    fluxoSaidaClienteTCP = l_socketClienteTCP.getOutputStream();

                    //saída do SERVIDOR PARA O CLIENTE
                    fluxoEntradaClienteTCP = l_socketClienteTCP.getInputStream();

                    //envia informações do arquivo para o server tratar...
                    System.out.println("enviando código de teste (0)...");

                    //codigo de teste 0 para saber se estar no ar
                    fluxoSaidaClienteTCP.write("0|".getBytes());
                    
                    //CASO haja erro na leitura das informaçoes apos conectado
                }catch(Exception e){

                    //atualiza host e porta no banco deste usuário
                    System.err.println("Erro ao tentar ler status do server.    \nErro: "+e);
                    l_conectado = false;

                }

                //recebe info. do server
                byte[] l_bufferStatusArq = new byte[512];
                String l_status = null;

                    try{

                        //recebe status do arquivo tratado pelo servidor
                        fluxoEntradaClienteTCP.read(l_bufferStatusArq);
                        l_status = new String(l_bufferStatusArq);
                        System.out.println("Usuário de cód.: "+l_codUsuario+" ok.");

                    }catch(Exception e){

                        System.err.println("Erro ao tentar ler status do server.    \nErro: "+e);
                        AtualizarDados.s_AtualizarDados.atualizaHostPortaNoBanco(l_codUsuario, "host" , 0, 0, "desconectado");

                        l_status = null;
                    }
            }else{

                //atualiza host e porta no banco deste usuário
                AtualizarDados.s_AtualizarDados.atualizaHostPortaNoBanco(l_codUsuario, "host" , 0, 0, "desconectado");

            }

        return l_conectado;

    }
    
    public double pegaTamanhoArquivoEmMB(int l_tamanhoEmBytes){
    
         double l_tamanhoMb = (l_tamanhoEmBytes / 1024) / 1024;
        
         return l_tamanhoMb;
    }


    public void copiaArquivoFCIni(){

            String l_urlArquivoOrigem = null;
            String l_urlArquivoFCInicializar = null;
            String l_urlDiretorioInicializar = null; 
            String l_urlArquivoFCDesktop = null;
            String l_urlDiretorioDesktop = null;
            String l_appFC = "FacecardByMonier.jar";
            String osName = System.getProperty("os.name"); 
            String l_nomeUser = System.getProperty("user.name");

                if (osName.contains("Windows")){
                 
                    //diretorio appfc atual
                    l_urlArquivoOrigem = System.getProperty("user.dir")+"\\"+l_appFC;
                    
                    //dir inicializar 
                    l_urlDiretorioInicializar = "C:\\Documents and Settings\\"+l_nomeUser+"\\Menu Iniciar\\Programas\\Startup"; 
                    
                    //dir area de trabalho windows
                    l_urlDiretorioDesktop = "C:\\users\\"+l_nomeUser+"\\Desktop"; 
                    
                    //caminho arquivo fc destino (inicializar)
                    l_urlArquivoFCInicializar = l_urlDiretorioInicializar+"\\"+l_appFC;
                    
                    //caminho arquivo fc destino (desktop)
                    l_urlArquivoFCDesktop = l_urlDiretorioDesktop+"\\"+l_appFC;
   
                
                //linux
                }else{

                    //diretorio inicializar (linux)
                    l_urlDiretorioInicializar = "dir ini linux";
                    
                    l_urlArquivoOrigem = "dir app fc linux";
                    
                    l_urlArquivoFCInicializar = "url arq fc ini linux";
                    
                } 

            //arquivo fc origem
            File l_arquivoFC = new File(l_urlArquivoOrigem);
            
            //arquiv fc destino (atalho inicializar)
            File l_arqTempFCIni = new File(l_urlArquivoFCInicializar);
            
            //arquiv fc destino (atalho desktop)
            File l_arqTempFCDesktop = new File(l_urlArquivoFCDesktop);


            try{
                
                System.err.println(l_urlArquivoOrigem+"\n"+l_urlArquivoFCInicializar);
                
                //caso exista \startup e seja iniciado de outro lugar...
                if(!l_arquivoFC.equals(l_arqTempFCIni)){

                        if(l_arqTempFCIni.exists())
                            //deleta o existente
                            l_arqTempFCIni.delete();
                    
                    FileChannel l_arquivoFCOrigemParaIni;  
                    FileChannel l_arquivoFCDestinoIni;  
                    
                    l_arquivoFCOrigemParaIni = new FileInputStream(l_arquivoFC).getChannel(); 

                    //atalho inicializar
                    l_arquivoFCDestinoIni = new FileOutputStream(l_arqTempFCIni).getChannel();  
                    l_arquivoFCOrigemParaIni.transferTo(0, l_arquivoFCOrigemParaIni.size(), l_arquivoFCDestinoIni);  

                    if (l_arquivoFCOrigemParaIni != null && l_arquivoFCOrigemParaIni.isOpen())  
                        l_arquivoFCOrigemParaIni.close();  
                    if (l_arquivoFCDestinoIni != null && l_arquivoFCDestinoIni.isOpen())  
                        l_arquivoFCDestinoIni.close(); 

                    System.out.println("Atalho (inicializar) criado com sucesso!    \nPath: "+l_urlArquivoFCInicializar);

                }
                
                System.err.println(l_urlArquivoOrigem+"\n"+l_urlArquivoFCDesktop);
                
                
                //caso exista no \desktop e seja iniciado de outro lugar...
                if(!l_arquivoFC.equals(l_arqTempFCDesktop)){

                        if(l_arqTempFCDesktop.exists())
                            l_arqTempFCDesktop.delete();
                    
                    FileChannel l_arquivoFCOrigemParaDesk;
                    FileChannel l_arquivoFCDestinoDesk;  

                    l_arquivoFCOrigemParaDesk = new FileInputStream(l_arquivoFC).getChannel();

                    //atalho desktop
                    l_arquivoFCDestinoDesk = new FileOutputStream(l_arqTempFCDesktop).getChannel();  
                    l_arquivoFCOrigemParaDesk.transferTo(0, l_arquivoFCOrigemParaDesk.size(), l_arquivoFCDestinoDesk);  
                    System.out.println("Atalho (desktop) criado com sucesso!    \nPath: "+l_urlArquivoFCDesktop);

                        if (l_arquivoFCOrigemParaDesk != null && l_arquivoFCOrigemParaDesk.isOpen())  
                            l_arquivoFCOrigemParaDesk.close();  
                        if (l_arquivoFCDestinoDesk != null && l_arquivoFCDestinoDesk.isOpen())  
                            l_arquivoFCDestinoDesk.close(); 

                 }
 
            }catch (IOException e){
                System.err.println("Erro ao tentar criar atalhos");
            }

    }
    
    public boolean removeArquivoFCIni(){

        
        boolean l_removido = false;

            
            String l_urlArquivoFCDestino = null;
            String l_urlDiretorioInicializar = null; 
            String l_appFC = "FacecardByMonier.jar";
            String osName = System.getProperty("os.name"); 
            String l_nomeUser = System.getProperty("user.name");
                
            
            
                if (osName.contains("Windows")){
                    
                    //diretorio inicializar (para usuario atual usuarios windows)
                    l_urlDiretorioInicializar = "C:\\Documents and Settings\\"+l_nomeUser+"\\Menu Iniciar\\Programas\\startup"; 
                    
                    //caminho arquivo fc destino (inicializar)
                    l_urlArquivoFCDestino = l_urlDiretorioInicializar+"\\"+l_appFC;
   
                
                //linux
                }else{

                    //diretorio inicializar (linux)
                    l_urlDiretorioInicializar = "dir ini linux";
                    
                    l_urlArquivoFCDestino = "url arq fc ini linux";
                    
                } 


            //arquiv fc destino
            File l_arquivoDestino = new File(l_urlArquivoFCDestino);
            
                if(l_arquivoDestino.exists()){
                    if(l_arquivoDestino.delete())
                       System.out.println("removido.");
                    }
                    
         return l_removido;
                
    }
    
    
    public void estabilizaServidores(int l_tipo){
        
    
            //verifica o meu status no banco
            String l_meuStatus = buscaStatus(LogUsuario.s_cod);    
            int l_minhaPortaUDP = buscaPortaAtualDoUsuarioNoBanco("UDP"); 
            int l_minhaPortaTCP = buscaPortaAtualDoUsuarioNoBanco("TCP");

                //caso desconectado
                if(l_meuStatus.equals("desconectado"))
                    AtualizarDados.s_AtualizarDados.atualizaStatus(LogUsuario.s_cod, "conectado");

                
                //caso servidor UDP fora do ar...
                if(auxiliar.sockets.ServidorUDP.s_socketServidor == null ||
                   l_minhaPortaUDP == 0){

                        //THREAD DO SOCKET SERVIDOR UDP INICIADO
                        System.out.println("Iniciando thread do socket Servidor UDP...");
                        int l_portaUDP = SolicitarDados.geraPortaAutomaticamente("UDP");
                        ServidorUDP sUDP = new ServidorUDP(l_portaUDP);
                        new Thread(sUDP).start();   

                }

                //caso servidor TCP fora do ar...
                if(auxiliar.sockets.ServidorTCP.s_socketServidor == null ||
                   l_minhaPortaTCP == 0){
                 
                        //THREAD DO SOCKET SERVIDOR UDP INICIADO
                        System.out.println("Iniciando thread do socket Servidor TCP...");
                        int l_portaTCP = SolicitarDados.geraPortaAutomaticamente("TCP");
                        ServidorTCP sTCP = new ServidorTCP(l_portaTCP);
                        new Thread(sTCP).start();   

                }
                
                //ESTABILIZADOR DE CONECTADOS
                System.out.println("Thread do Estabilizador de conectados iniciada...");
                EstabilizaConectados eCON = new EstabilizaConectados(LogUsuario.s_cod, l_tipo);
                new Thread(eCON).start();

    }

}


            
        