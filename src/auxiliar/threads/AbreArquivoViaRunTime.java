/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;

/**
 *
 * @author Ayrton Monier
 */
public class AbreArquivoViaRunTime implements Runnable{

    private String c_caminhoArquivo;
    private String c_SO;

    public AbreArquivoViaRunTime(String l_caminhoArquivo, String l_SO){
//
//        System.err.println("Arquivo: "+l_caminhoArquivo);

        c_caminhoArquivo = l_caminhoArquivo;
        c_SO = l_SO;


    }

    public void run(){

        System.out.println("Tentando abrir via Runtime do "+c_SO+"...");
        if(c_SO.equals("MSWindows")){

            try{
                Runtime.getRuntime().exec("cmd /C \""+c_caminhoArquivo+"\"");

            }catch(Exception e){

                System.err.println("Erro ao tentar abrir o arquivo selecionado via Runtime MSWindows.\n"
                                 + "    Arquivo: "+c_caminhoArquivo+"\n"
                                 + "    Erro: "+e);
            }

        }

    }

}
