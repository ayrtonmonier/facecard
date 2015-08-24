/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;

import auxiliar.AtualizarDados;
import auxiliar.Dados;
import auxiliar.Diretorios;
import auxiliar.LogUsuario;
import auxiliar.MostrarDados;
import auxiliar.SolicitarDados;
import formularios.Moniersn;
import java.io.*;

/**
 *
 * @author Ayrton Monier
 */
public class EstabilizaArquivos implements Runnable{

    private int c_codUsuario;
    private String c_dirArquivoUsuario;


    public EstabilizaArquivos(int l_codUsuario, String l_urlDiretorioArquivos){

        c_codUsuario = l_codUsuario;
        c_dirArquivoUsuario = l_urlDiretorioArquivos;
        
    }

    public void run(){

        System.out.println("Iniciando verificação de arquivos...");
       
            //caso o usuário nao tenha definido o local dos arquivos
            if(c_dirArquivoUsuario.equals("*")){
                
                 //o diretorio do usuario esta ok
                 LogUsuario.s_localDeArquivosDoUsuarioConsistente = true;
                 
                 System.out.println("   O  diretório de arquivos do usuário ainda não definifido.");
                 System.out.println("   Verificação de arquivos finalizada.");
            
            //caso o usuário já tenha definido o local de arquivos
            }else{
                
                File l_dirAtual = new File(c_dirArquivoUsuario);
        
                    //caso o dir exista
                    if(l_dirAtual.exists()){
                        
                        //o diretorio do usuario esta ok
                        LogUsuario.s_localDeArquivosDoUsuarioConsistente = true;
                        
                        System.out.println("O  diretório de arquivos do usuário já foi definido e está ok!\n"
                                         + "Verificando arquivos enviados e baixados...");

                        //VERIFICA ARQUIVOS ENVIADOS
                        verificaDiretorioArquivos(c_dirArquivoUsuario, Diretorios.PASTA_ENVIADOS, c_codUsuario);

                        //VERIFICA ARQUIVOS BAIXADOS
                        verificaDiretorioArquivos(c_dirArquivoUsuario, Diretorios.PASTA_BAIXADOS, c_codUsuario);
                        
                    //verifica arquivos no dir
                    }else{
                        
                        //o diretorio do usuario NAO esta ok
                        LogUsuario.s_localDeArquivosDoUsuarioConsistente = false;
                        //esconde tela principal
                        System.err.println("    O  diretório de arquivos do usuário já foi definido, "
                                              + "mas não foi encontrado no local.");
                        
                    }
            }
   
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
                AtualizarDados.s_AtualizarDados.atualizaArquivosNoDiretorio(l_urlDirUsuario, l_pasta, l_codUsuario);
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

                            if(LogUsuario.s_listaArquivoSelecionada.equals("enviados") || LogUsuario.s_listaArquivoSelecionada.equals("baixados"))
                                MostrarDados.s_MostrarDados.preencherListaDeArquivos(l_codUsuario, Moniersn.jt_listaArquivosUsuario, LogUsuario.s_listaArquivoSelecionada);

                        //contagem de ítens
                        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(l_codUsuario);



                    }else{
                        System.out.println("    Nenhum arquivo "+l_pasta+" ainda!");

                    }
                }

   }
}
    


      
