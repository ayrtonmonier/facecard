/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.sockets;

import auxiliar.Diretorios;
import auxiliar.SolicitarDados;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.OutputStream;

/**
 *
 * @author Ayrton Monier
 */
public class TrataClienteTCP implements Runnable {

    private OutputStream c_fluxoParaCliente;
    
    private String c_apelidoEmissor;
    private String c_nomeArquivo;

    private int c_codArquivo;
    private int c_codEmissor;
    private int c_tamanhoArquivo;
    private String c_statusArquivoEmpacotado;

    public TrataClienteTCP(int l_codArquivo, OutputStream l_fluxoParaCliente){

        c_codArquivo = l_codArquivo;

        if(c_codArquivo != 0){

            c_nomeArquivo = SolicitarDados.s_SolicitarDados.pegaInfoArquivo("nome", l_codArquivo);
            System.out.println("Nome do arq.: "+c_nomeArquivo);

            c_tamanhoArquivo = Integer.parseInt(SolicitarDados.s_SolicitarDados.pegaInfoArquivo("tamanho", l_codArquivo));
            System.out.println("Tam.: "+c_tamanhoArquivo+" byte(s)");

            c_codEmissor = Integer.parseInt(SolicitarDados.s_SolicitarDados.pegaInfoArquivo("cod_emissor", l_codArquivo));
            c_apelidoEmissor = SolicitarDados.pegaApelidoPeloCodigo(c_codEmissor);
            System.out.println("Emissor: "+c_apelidoEmissor);

            c_fluxoParaCliente = l_fluxoParaCliente;

        }else{

            try{
                //envia para o cliente (erro na leitura de cod do arquivo no servidor)
                c_statusArquivoEmpacotado = "0|";

                l_fluxoParaCliente.write(c_statusArquivoEmpacotado.getBytes());

            }catch(Exception e){

                System.err.println("Erro ao tentar mandar resposta do arquivo para o cliente.   \nErro: "+e);
            }
        }

    }

    
    public void run(){


        if(c_codArquivo != 0){

            try{

                System.out.println(c_apelidoEmissor+" irá baixar o arquivo...");

                System.out.println("Localizando arquivo...");
                String  l_diretorioArq = Diretorios.pegaCaminhoArquivo(Diretorios.PASTA_ENVIADOS, c_nomeArquivo);
                
                File l_arquivo = new File(l_diretorioArq);


                    if(l_arquivo.exists()){

                        c_statusArquivoEmpacotado = "1|";

                        c_fluxoParaCliente.write(c_statusArquivoEmpacotado.getBytes());

                        System.out.println("    Tamanho: "+l_arquivo.length());
                        byte [] l_bufferArquivo = new byte[c_tamanhoArquivo];

                        FileInputStream fis = new FileInputStream(l_arquivo);

                        BufferedInputStream bis = new BufferedInputStream(fis);

                        bis.read(l_bufferArquivo, 0, l_bufferArquivo.length);

                        System.out.println("Enviando arquivo para "+c_apelidoEmissor+"...");

                        c_fluxoParaCliente.write(l_bufferArquivo, 0, l_bufferArquivo.length);

                        c_fluxoParaCliente.flush();

                        System.out.println("Arquivo enviado!");

                    //caso o arquivo não exista...
                    }else{

                        System.err.println("Arquivo não encontrado!");

                        c_statusArquivoEmpacotado = "2|";

                        c_fluxoParaCliente.write(c_statusArquivoEmpacotado.getBytes());

                    }

    //            c_fluxoParaCliente.close();

            }catch(Exception e){
                System.err.println("Erro no cliente TCP ao tentar enviar o arquivo.   \nErro: "+e);
            }
        }
    }

}
