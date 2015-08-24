/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.sockets;

import java.io.*;
import java.net.*;
import auxiliar.LogAmigo;
import auxiliar.LogUsuario;
import auxiliar.Dados;
import auxiliar.Data;
import formularios.Moniersn;

public class ClienteUDP{

    public static ClienteUDP s_socketClienteDatagrama;
    public static boolean s_datagramaRecInstEnviado = true;
    public static boolean s_datagramaAvisoEnviado = true;

    private DatagramPacket c_sendPacket;
    private InetAddress c_host;
    private String c_dados;
    private byte c_sendBuf[];
    private DatagramSocket c_socketCliente;


public ClienteUDP(String l_hostDestinatario, int l_porta, int l_aviso, int l_codEmissor, String l_recadoInstantaneo){

         try
         {

                System.out.println("Enviando datagrama ao servidor...");
                //cria o c_socketCliente cliente
                c_socketCliente = new DatagramSocket();

                //l_hostDestinatario de destino
                c_host = InetAddress.getByName(l_hostDestinatario);

                //junta o aviso como o cod do emissor e no servidor será tratado
                c_dados = l_aviso+"|"+l_codEmissor+"$"+l_recadoInstantaneo;

                //cria um array de bytes do tamanho da string a ser enviada
                c_sendBuf = new byte[c_dados.length()];

                //transforma a string de dados em bytes
                c_dados.getBytes(0,c_dados.length(),c_sendBuf,0);

                //criação do pacote de datagrama
                c_sendPacket = new DatagramPacket(c_sendBuf, c_sendBuf.length, c_host, l_porta);

                //envia o Datagrama para o servidor
                c_socketCliente.send(c_sendPacket);

                //fecha O SOCKET cliente
                c_socketCliente.close();

                s_socketClienteDatagrama = null;

                s_datagramaRecInstEnviado = true;
                System.out.println("Datagrama enviado e socket Cliente UDP fechado e null");
        }
        catch(SocketException se)
        {
          System.err.println("Erro no Cliente UDPse: "+se);
          s_datagramaRecInstEnviado = false;
        }
        catch(IOException ioe)
        {
          System.err.println("Erro no Cliente UDPioe: "+ioe);
          s_datagramaRecInstEnviado = false;
        }

}
   
 public ClienteUDP(String l_hostDestinatario, int l_porta, int l_codAviso, int l_codEmissor){

         try
         {
                System.out.println("Enviando datagrama ao servidor...");

                //cria o c_socketCliente cliente
                c_socketCliente = new DatagramSocket();

                //l_hostDestinatario de destino
                c_host = InetAddress.getByName(l_hostDestinatario);

                //junta o aviso como o cod do emissor e no servidor será tratado
                c_dados = l_codAviso+"|"+l_codEmissor+"$";

                //cria um array de bytes do tamanho da string a ser enviada
                c_sendBuf = new byte[c_dados.length()];

                //transforma a string de dados em bytes
                c_dados.getBytes(0,c_dados.length(),c_sendBuf,0);

                //criação do pacote de datagrama
                c_sendPacket = new DatagramPacket(c_sendBuf, c_sendBuf.length, c_host, l_porta);

                //envia o Datagrama para o servidor
                c_socketCliente.send(c_sendPacket);

                //fecha O SOCKET cliente
                c_socketCliente.close();

                //cliente datagrama nulo
                s_socketClienteDatagrama = null;

                s_datagramaAvisoEnviado = true;

                System.out.println("Datagrama enviado e socket Cliente UDP fechado e null");
        }
        catch(SocketException se)
        {
          System.err.println("Erro no Cliente UDP: "+se);
          s_datagramaAvisoEnviado = false;
        }
        catch(IOException ioe)
        {
          System.err.println("Erro no Cliente UDP: "+ioe);
          s_datagramaAvisoEnviado = false;

        }
    }

    public ClienteUDP(String l_hostDestinatario, int l_porta, int l_aviso, int l_codEmissor, int l_codDestinatario){

         try
         {
                System.out.println("Enviando datagrama ao servidor...");

                //cria o c_socketCliente cliente
                c_socketCliente = new DatagramSocket();

                //l_hostDestinatario de destino
                c_host = InetAddress.getByName(l_hostDestinatario);

                //junta o aviso como o cod do emissor e o cod do destinatário
                c_dados = l_aviso+"|"+l_codEmissor+"$"+l_codDestinatario;

                //cria um array de bytes do tamanho da string a ser enviada
                c_sendBuf = new byte[c_dados.length()];

                //transforma a string de dados em bytes
                c_dados.getBytes(0,c_dados.length(),c_sendBuf,0);

                //criação do pacote de datagrama
                c_sendPacket = new DatagramPacket(c_sendBuf, c_sendBuf.length, c_host, l_porta);

                //envia o Datagrama para o servidor
                c_socketCliente.send(c_sendPacket);

                //fecha O SOCKET cliente
                c_socketCliente.close();

                s_socketClienteDatagrama = null;

                s_datagramaRecInstEnviado = true;

                System.out.println("Datagrama enviado e socket Cliente UDP fechado e null");
        }
        catch(SocketException se)
        {
          System.err.println("Erro no Cliente UDPse: "+se);
          s_datagramaRecInstEnviado = false;
        }
        catch(IOException ioe)
        {
          System.err.println("Erro no Cliente UDPioe: "+ioe);
          s_datagramaRecInstEnviado = false;
        }

    }

     //envia aviso para unico amigo que está online (ex: aviso de recado publico enviado)
    public static void enviaAvisoParaUnicoAmigoConectado(int l_codDestinatario, int l_codAviso){

            String l_hostDestinatario = null;
            int l_porta = 0;
            int l_codEmissor = LogUsuario.s_cod;


            String sql = "SELECT status_contato, host, porta_udp "
                       + "FROM usuario "
                       + "WHERE status_contato = 'conectado' "
                       + "AND cod_usuario = "+l_codDestinatario;


            Dados.s_conexaoBanco.executeSELECT(sql);

                try{
                    if(Dados.s_conexaoBanco.c_resultset.first()){

                        l_hostDestinatario = Dados.s_conexaoBanco.c_resultset.getString("host");
                        l_porta = Dados.s_conexaoBanco.c_resultset.getInt("porta_udp");

                        //ENVIA AVISO AO DESTINATÁRIO CONECTADO
                        ClienteUDP.s_socketClienteDatagrama = new ClienteUDP(l_hostDestinatario, l_porta, l_codAviso, l_codEmissor);


                    }else{
                        System.out.println("o amigo não está conectado para enviar datagrama de aviso.");
                    }

                }catch(Exception e){
                    System.err.println("Erro ao tentar buscar host do destinatário.   \nErro: "+e);

                }
    }

    //ENVIA AVISO PARA TODOS CONECTADOS
    public static void enviaAvisoParaTodosConectados(int l_codAviso, String l_quem){

        String sql = null;
        String l_hostDestinatario;
        int l_portaDestinatario;
        int l_codEmissor = LogUsuario.s_cod;
        int l_contadorAvisos = 0;

        try{

            //À TODOS AMIGOS CONECTADOS (NAO BLOQUEADOS POR MIM e QUE NÃO ME BLOQUEARAM)
            if(l_quem.equals("amigos")){ 
                //sql amigos conectados
                sql = "SELECT host, porta_udp "
                      + "FROM usuario "
                      + "WHERE (cod_usuario) IN (SELECT cod_amigo "
                                               + "FROM contato_de_usuario "
                                               + "WHERE cod_usuario = "+l_codEmissor+" "
                                               + "AND aceito = 'S' "
                                               + "AND bloqueado = 'N') "
                      + "AND (cod_usuario) NOT IN (SELECT cod_usuario "
                                                 + "FROM contato_de_usuario "
                                                 + "WHERE cod_amigo = "+l_codEmissor+" "
                                                 + "AND aceito = 'S' "
                                                 + "AND bloqueado = 'S') "
                     + "AND status_contato = 'conectado' "
                     + "AND ativo = 'S' "
                     + "AND cod_usuario != "+l_codEmissor;
            }

            else if(l_quem.equals("todos")){
                //sql todos usuarios
                sql = "SELECT host, porta_udp "
                      + "FROM usuario "
                      + "WHERE (cod_usuario) NOT IN (SELECT cod_amigo "
                                                   + "FROM contato_de_usuario "
                                                   + "WHERE cod_usuario = "+l_codEmissor+" "
                                                   + "AND aceito = 'S' "
                                                   + "AND bloqueado = 'S') "
                      + "AND (cod_usuario) NOT IN (SELECT cod_usuario "
                                                 + "FROM contato_de_usuario "
                                                 + "WHERE cod_amigo = "+l_codEmissor+" "
                                                 + "AND aceito = 'S' "
                                                 + "AND bloqueado = 'S') "
                     + "AND status_contato = 'conectado' "
                     + "AND ativo = 'S' "
                     + "AND cod_usuario != "+l_codEmissor;
            }

            else if(l_quem.equals("geral")){
                
                //sql todos usuarios
                sql = "SELECT host, porta_udp "
                      + "FROM usuario "
                      + "WHERE status_contato = 'conectado' "
                      + "AND ativo = 'S' "
                      + "AND cod_usuario != "+l_codEmissor;
            }
            
            //geral que deseja receber arquivo
            else if(l_quem.equals("todos-rec-arq")){
                
                //sql todos usuarios q recebem arquivos e conectados
                sql = "SELECT u.host, u.porta_udp "
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
                    + "AND (u.cod_usuario) NOT IN (SELECT u.cod_usuario "//nao amigos q so amigos enviam arquivos
                                                + "FROM usuario u, configuracoes_usuario c "
                                                + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo  "//nao esteja entre meus amigos
                                                                            + "FROM contato_de_usuario "
                                                                            + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                                            + "AND aceito = 'S') "
                                                + "AND u.cod_usuario = c.cod_usuario "
                                                + "AND c.quem_envia_arquivo = 'amigos') "
                    + "AND u.cod_usuario = c.cod_usuario "
                    + "AND u.ativo = 'S' "
                    + "AND u.status_contato = 'conectado' "
                    + "AND c.quem_envia_arquivo != 'ninguem' "
                    + "AND u.cod_usuario != "+l_codEmissor;
            }
            
            //todos amigos que desejam receber arquivo
            else if(l_quem.equals("amigos-rec-arq")){
                
                //sql amigos q recebem arquivos e conectados
                sql = "SELECT u.host, u.porta_udp "
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
                    + "AND u.status_contato = 'conectado' "
                    + "AND c.quem_envia_arquivo != 'ninguem' "
                    + "AND u.cod_usuario != "+l_codEmissor;
            }   

            Dados.s_conexaoBanco.executeSELECT(sql);

            if(Dados.s_conexaoBanco.c_resultset.first()){

                System.out.println("Enviando datagramas de aviso para "+l_quem+" conectados e não bloqueados...");//e que não tenham me bloqueado
                do{
                        //PEGA HOST e PORTA
                        l_hostDestinatario = Dados.s_conexaoBanco.c_resultset.getString("host");
                        l_portaDestinatario = Dados.s_conexaoBanco.c_resultset.getInt("porta_udp");

                        //ENVIA AVISO
                        ClienteUDP.s_socketClienteDatagrama = new ClienteUDP(l_hostDestinatario, l_portaDestinatario, l_codAviso, l_codEmissor);

                        l_contadorAvisos++;

                }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto vai enviando datagramas de aviso

                System.out.println("ok, "+l_contadorAvisos+" datagramas de aviso enviado(s)...");

            }//fim if
            else
                System.err.println("Nenhum amigo conectado para enviar datagrama de aviso!");

        }
        catch(Exception e){
            System.err.println("Erro ao tentar enviar datagrama de aviso à "+l_quem+".    \nErro: "+e);
        }
    }

    //ENVIA AVISO PARA TODOS CONECTADOS
    public static void enviaAvisoParaTodosConectados(int l_codUsuarioDonoDaLista, int l_codAviso, String l_quem){

        String sql = null;
        String l_hostDestinatario;
        int l_portaDestinatario;
        int l_codEmissorDatagrama = LogUsuario.s_cod;
        int l_contadorAvisos = 0;

            try{    

                //À TODOS AMIGOS CONECTADOS (NAO BLOQUEADOS POR ELE e QUE NÃO O BLOQUEARAM)
                if(l_quem.equals("amigos")){

                    System.out.println("enviando aos amigos DO USUARIO DE COD: "+l_codUsuarioDonoDaLista);

                    //sql amigos conectados
                    sql = "SELECT host, porta_udp "
                        + "FROM usuario "
                        + "WHERE (cod_usuario) NOT IN (SELECT cod_amigo "
                                                + "FROM contato_de_usuario "
                                                + "WHERE cod_usuario = "+l_codUsuarioDonoDaLista+" "
                                                + "AND aceito = 'N') "
                         + "AND (cod_usuario) NOT IN (SELECT cod_usuario "
                                                   + "FROM contato_de_usuario "
                                                   + "WHERE cod_amigo = "+l_codUsuarioDonoDaLista+" "
                                                   + "AND aceito = 'S' "
                                                   + "AND bloqueado = 'S') "
                         + "AND status_contato = 'conectado' "
                         + "AND ativo = 'S' "
                         + "AND cod_usuario != "+l_codEmissorDatagrama;
                }

                else if(l_quem.equals("todos")){

                    System.out.println("enviando a todos");
                    //sql todos usuarios
                    sql = "SELECT host, porta_udp "
                          + "FROM usuario "
                          + "WHERE (cod_usuario) NOT IN (SELECT cod_amigo "
                                                       + "FROM contato_de_usuario "
                                                       + "WHERE cod_usuario = "+l_codUsuarioDonoDaLista+" "
                                                       + "AND aceito = 'S' "
                                                       + "AND bloqueado = 'S') "
                          + "AND (cod_usuario) NOT IN (SELECT cod_usuario "
                                                     + "FROM contato_de_usuario "
                                                     + "WHERE cod_amigo = "+l_codUsuarioDonoDaLista+" "
                                                     + "AND aceito = 'S' "
                                                     + "AND bloqueado = 'S') "
                         + "AND status_contato = 'conectado' "
                         + "AND ativo = 'S' "
                         + "AND cod_usuario != "+l_codEmissorDatagrama;
                }

                Dados.s_conexaoBanco.executeSELECT(sql);

                if(Dados.s_conexaoBanco.c_resultset.first()){

                    System.out.println("Enviando datagramas de aviso para "+l_quem+" conectados e não bloqueados...");//e que não tenham me bloqueado
                    do{
                            //PEGA HOST e PORTA
                            l_hostDestinatario = Dados.s_conexaoBanco.c_resultset.getString("host");
                            l_portaDestinatario = Dados.s_conexaoBanco.c_resultset.getInt("porta_udp");

                            //ENVIA AVISO
                            ClienteUDP.s_socketClienteDatagrama = new ClienteUDP(l_hostDestinatario, l_portaDestinatario, l_codAviso, l_codEmissorDatagrama, l_codUsuarioDonoDaLista);

                            l_contadorAvisos++;

                    }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto vai enviando datagramas de aviso

                    System.out.println("ok, "+l_contadorAvisos+" datagramas de aviso enviado(s)...");

                }//fim if
                else
                    System.out.println("    Nenhum amigo conectado para enviar datagrama de aviso!");
            }
            catch(Exception e){
                System.err.println("Erro ao tentar enviar datagrama de aviso à "+l_quem+".    \nErro: "+e);
            }
    }

    public static void enviarRecadoInstantaneo(String l_quem, String l_texto){

        String l_hostDestinatario;

        int l_portaDestinatario;
        int l_contadorAvisos = 0;
        String l_apelidoAmigo;
        String sql;
        String l_amigosEnv = "(";

        Data.atualizaDataHora();

        if(l_quem.equals("unico")){

            //prepara para enviar datagrama com dados de aviso o servidor
            ClienteUDP.s_socketClienteDatagrama = new ClienteUDP(LogAmigo.s_host, LogAmigo.s_portaUDP,2, LogUsuario.s_cod, l_texto);

            if(ClienteUDP.s_datagramaRecInstEnviado){
                Moniersn.tp_recadosInstantaneos.setText(Moniersn.tp_recadosInstantaneos.getText()+"\n\n("+Data.s_horaAtualHHMM+") - Eu disse para "+LogAmigo.s_moniersn+":\n"+
                                                        ">>"+l_texto);
            }
            else{
                Moniersn.tp_recadosInstantaneos.setText(Moniersn.tp_recadosInstantaneos.getText()+"\n\n("+Data.s_horaAtualHHMM+") - Eu disse para "+LogAmigo.s_moniersn+":\n"+
                                                        ">>"+l_texto+" ***(FALHA NO ENVIO)***");
            }
        }
            else if(l_quem.equals("varios")){

                try{

                    //CASO BATER PAPO SOMENTE COM AMIGOS
                    if(LogUsuario.s_quemBatePapoComigo.equals("amigos")){

                        sql = "SELECT u.moniersn, u.host, u.porta_udp "
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
                             + "AND c.quem_bate_papo_comigo != 'ninguem' "
                             + "AND u.status_contato = 'conectado' "
                             + "AND u.cod_usuario != "+LogUsuario.s_cod;

                    }

                    //CASO BATER PAPO CM TODOS
                    else{


                        sql = "SELECT u.moniersn, u.host, u.porta_udp "
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
                             + "AND u.cod_usuario = c.cod_usuario "
                             + "AND c.quem_bate_papo_comigo != 'ninguem' "
                             + "AND u.status_contato = 'conectado' "
                             + "AND u.cod_usuario != "+LogUsuario.s_cod;
                    }

                    Dados.s_conexaoBanco.executeSELECT(sql);

                    if(Dados.s_conexaoBanco.c_resultset.first()){

                        System.out.println("Enviando recado inst. para "+LogUsuario.s_quemBatePapoComigo+" conectados e não bloqueados...");//e que não tenham me bloqueado

                        do{
                            if(l_contadorAvisos > 0)
                                l_amigosEnv += ", ";

                                //a cada loop pega l_hostDestinatario e l_portaHostDestinatario do amigo para enviar o datagrama de aviso de saida
                                l_hostDestinatario = Dados.s_conexaoBanco.c_resultset.getString("u.host");
                                l_portaDestinatario = Dados.s_conexaoBanco.c_resultset.getInt("u.porta_udp");
                                l_apelidoAmigo = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");

                                //prepara para o envio do recado inst.
                                ClienteUDP.s_socketClienteDatagrama = new ClienteUDP(l_hostDestinatario, l_portaDestinatario,2 , LogUsuario.s_cod, l_texto);

                                        if(ClienteUDP.s_datagramaRecInstEnviado){
                                            l_amigosEnv += l_apelidoAmigo;
                                        }
                                        else{
                                            l_amigosEnv += l_apelidoAmigo+"(falhou)";
                                        }

                                l_contadorAvisos++;

                        }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto vai enviando datagramas de aviso

                        l_amigosEnv += ")";

                        Moniersn.tp_recadosInstantaneos.setText(Moniersn.tp_recadosInstantaneos.getText()+"\n\n("+Data.s_horaAtualHHMM+") - Eu disse para "+LogUsuario.s_quemBatePapoComigo+" conectados "+l_amigosEnv+
                                                       ":\n>>"+l_texto);
                        System.out.println("ok, "+l_contadorAvisos+" recados inst. enviado(s)...");

                }//fim if
                else
                    System.err.println("Nenhum amigo conectado para enviar recado inst. de aviso!");
            }
            catch(Exception e){
                System.err.println("Erro ao tentar enviar recado instantâneos.    \nErro: "+e);
            }

        }

        Moniersn.s_telaMsn.tf_recadoInst.setText("");
        Moniersn.s_telaMsn.tf_recadoInst.requestFocus();
        //Moniersn.tp_recadosInstantaneos.setCaretPosition(Moniersn.tp_recadosInstantaneos.getCaretPosition());

    }




}
