/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 O servidor deverá tratar as msg recebidas que serão solicitações
 *
 * -- aviso de novo l_codAviso no banco *
 * -- aviso de conversa instantanea
 * -- envio de animações
 * -- etc
 */


package auxiliar.sockets;

import auxiliar.threads.trataAvisoUDP;
import java.io.*;
import java.net.*;
import auxiliar.*;


public class ServidorUDP implements Runnable{

    private int c_porta;

    int c_codDestinatariAviso = 0;
    String c_destinatarioAviso;

    private String c_dadosRecebidos;
    public String c_recadoInstantaneo = "";
    public String c_apelidoEmissorRecInst = null;
    public String c_apelidoEmissorArquivo = null;
    public String c_tipoAmigoDestinatario = null;

//  ARQUIVOS
    public String c_solicitacaoArquivo = null;

    public static DatagramSocket s_socketServidor = null;
    DatagramPacket c_pacoteRecebido;

    public ServidorUDP(int l_porta){

        c_porta = l_porta;
                
    }

    public void run(){

        try
        {

          System.out.println("Instanciando o servidor UDP na porta "+c_porta+"...");
          s_socketServidor = new DatagramSocket(c_porta);

          System.out.println("Servidor UDP escutando na porta "+c_porta+".");

          //recebe um pacote de 512 bytes
          c_pacoteRecebido = new DatagramPacket(new byte[512], 512);

              while(s_socketServidor!= null)
              {
                    //esperando por pacotes de clientes
                    s_socketServidor.receive(c_pacoteRecebido);

                    //trasnforma bytes para string
                    c_dadosRecebidos = new String(c_pacoteRecebido.getData(),0,c_pacoteRecebido.getLength());

                    //trata o aviso recebido dando instruções para app
                    trataAvisoUDP tUDP = new trataAvisoUDP(c_dadosRecebidos);
                    new Thread(tUDP).start();
              }

        }

            catch(SocketException se)
            {
              System.err.println("Erro no Servidor UDP: "+se);
            }
            catch(IOException ioe)
            {
              System.err.println("Erro no Servidor UDP: "+ioe);
            }

        s_socketServidor.close();

        s_socketServidor = null;

        System.out.print("Seridor fechado e null"); 

    }

}

