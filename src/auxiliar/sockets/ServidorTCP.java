/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.sockets;

import auxiliar.SolicitarDados;
import java.net.*;
import java.io.*;

/**
 *
 * @author Ayrton Monier
 */
public class ServidorTCP implements Runnable{

    private int c_portaServidor = 0;
    public static ServerSocket s_socketServidor = null;

    public ServidorTCP(int l_porta){

        c_portaServidor = l_porta;

    }

    public void run(){

            try{

                s_socketServidor = new ServerSocket(c_portaServidor);
                System.out.println("Servidor TCP no ar na porta "+c_portaServidor);

                    while(s_socketServidor != null){
                        
                        System.out.println("Aguardando cliente TCP...");
                        Socket l_clienteArq = s_socketServidor.accept();

                        System.out.println("\n#NOVA CONEXÃO COM CLIENTE\n");

                        //saída do CLIENTE PARA O SERVIDOR
                        InputStream l_fluxoDoCliente = l_clienteArq.getInputStream();

                        //saída do SERIDOR PARA O CLIENTE
                        OutputStream l_fluxoParaCliente = l_clienteArq.getOutputStream();

                        System.out.println("    Obtendo informações do arquivo...");
                        
                        byte[] l_bufferInfoArq = new byte[512];
                        String l_infoArquivo = null;
                                                        
                            //trata a exceção
                            try{

                                l_fluxoDoCliente.read(l_bufferInfoArq);
                                l_infoArquivo = new String(l_bufferInfoArq);

                            }catch(Exception e){

                                System.err.println("Erro no tratamento do cod. do arquivo no servidor TCP.  \nErro: "+e);
                                l_infoArquivo = null;
                            }


                        int l_codArquivo = SolicitarDados.s_SolicitarDados.pegaCodArquivoClienteTCP(l_infoArquivo);

                        System.out.println("    Iniciando tratador de clienteTCP...");
                        TrataClienteTCP tcTCP = new TrataClienteTCP(l_codArquivo, l_fluxoParaCliente);

                        new Thread(tcTCP).start();

                    }

                s_socketServidor.close();
                
                s_socketServidor = null;


            }catch(Exception e){
                
                System.err.println("Erro ao tentar iniciar o servidor TCP.  \nErro: "+e);

            }
    }
}