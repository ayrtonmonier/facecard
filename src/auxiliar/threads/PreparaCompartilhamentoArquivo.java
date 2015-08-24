/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;

import auxiliar.Avisos;
import auxiliar.Diretorios;
import auxiliar.GravarDados;
import auxiliar.LogUsuario;
import auxiliar.MostrarDados;
import auxiliar.SolicitarDados;
import formularios.CompartilhaArquivo;

/**
 *
 * @author Ayrton Monier
 */

public class PreparaCompartilhamentoArquivo implements Runnable{

        private String c_comQuem;
        private int c_codDestinatario;
        private int c_codEmissor = LogUsuario.s_cod;
        private String c_extensao;
        private String  c_nomeArquivo;
        private String c_novoNome;
        private String c_diretorioArquivoSis;
        long c_tamanho;

    public PreparaCompartilhamentoArquivo(String l_cQuem, int l_codDest, String l_nomeArq, String l_novoNome, String l_extArq, String l_dirArq, long l_tamArq){

        this.c_comQuem = l_cQuem;
        this.c_codDestinatario = l_codDest;
        this.c_extensao = l_extArq;
        this.c_nomeArquivo = l_nomeArq;
        this.c_novoNome = l_novoNome;
        this.c_diretorioArquivoSis = l_dirArq;
        this.c_tamanho = l_tamArq; 

    }

    public void run(){

        try{
            System.out.println("Thread de preparação de compartilhamento de arquivo iniciada.");

            //manipula o arquivo para o diretorio de destino
            if(SolicitarDados.s_SolicitarDados.manipulaArquivo(c_diretorioArquivoSis, c_novoNome, Diretorios.PASTA_ENVIADOS)){

                //grava dados o arquivo a ser compartilhado
                GravarDados.s_GravarDados.gravarCompartilhamentoArquivo(c_codEmissor, c_comQuem, c_codDestinatario, c_nomeArquivo, c_novoNome, c_extensao, c_tamanho, c_diretorioArquivoSis);

            }else{

                System.err.println("Aconteceu um erro ao tentar manipular o arquivo.");

            }

        }catch(Exception e){

            CompartilhaArquivo.s_telaCompArquivo.dispose();
            CompartilhaArquivo.s_telaCompArquivo = null;

            System.err.println("Erro ao tentar preparar o arquivo para compartilhamento.    \nErro "+e);
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_ERRO_COMPART, "erro");

        }
    
    }
    
}


