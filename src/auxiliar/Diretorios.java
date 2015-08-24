/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import java.io.File;

/**
 *
 * @author Ayrton Monier
 */
public class Diretorios {

    public final static String PASTA_ARQUIVOS = "arquivos";
    public final static String PASTA_REMOVIDOS = "removidos";
    public final static String PASTA_BAIXADOS = "baixados";
    public final static String PASTA_ENVIADOS = "enviados";

    public Diretorios(){}

    public static String pegaDiretorio(String l_pasta, String l_subPasta){

        String l_diretorioUsuario = LogUsuario.s_localDeArquivosDoUsuario;

        String l_diretorio = l_diretorioUsuario+"\\"+l_pasta+"\\"+l_subPasta;

        // referencia o diretorio
        File l_dir = new File(l_diretorio);

            // caso nao exista
            if(!l_dir.exists()){
                System.out.println("Estrutura de diretorios inexistente.\nCriando...");
                if(SolicitarDados.s_SolicitarDados.criarPastasFacecard(l_diretorio))
                    System.out.println("Estrutura de diretorios criada.");
            }

        return l_diretorio;

    }

    public static String pegaCaminhoArquivo(String l_subPasta, String l_nomeArquivo){

        String l_localArquivos = LogUsuario.s_localDeArquivosDoUsuario;

        String l_caminho = l_localArquivos+"\\"+Diretorios.PASTA_ARQUIVOS+"\\"+l_subPasta+"\\"+l_nomeArquivo;
        
       
        return l_caminho;
    
    }


}
