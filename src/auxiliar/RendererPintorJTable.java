/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import formularios.Comentario;
import formularios.Moniersn;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable; 
import javax.swing.table.*;

public class RendererPintorJTable extends DefaultTableCellRenderer{
    String c_tipoAmigo = null;
    String c_caminhoIcone = null;
    JLabel lb_icone;
    String c_tipoComents = null;
    int c_codStatus;
    String c_iconeDestArquivo = null;


    /** Creates a new instance of TarefasCellRenderer */
    public RendererPintorJTable(){
        super();
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){

        lb_icone = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            //LISTA DE AMIGOS DO USUÁRIO
            if(table == Moniersn.jt_listaAmigosUsuario || table == Moniersn.jt_listaAmigosConectados){

                //ICONE APELIDO|humor
                 if(column == 0){

                    c_caminhoIcone = "/imagens/humor/"+SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(), "humor")+".png";
                    lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));
                    lb_icone.setText(SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(), "apelido"));
                    
                 }

                 //STATUS
                 else if(column == 1){
                     
                    c_codStatus = SolicitarDados.s_SolicitarDados.pegaCodOuStatusListaUsuario(value.toString(), "status");
                    
                        if(c_codStatus == 1)  
                            c_caminhoIcone = Icones.STATUS_CONECTADO_16;
                        else
                            c_caminhoIcone = Icones.STATUS_DESCONECTADO_16;

                    lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone))); // NOI18N
                    lb_icone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    lb_icone.setText("");

                 }

                 //TIPO DE AMIGO
//                 else if(column == 2){
//
////                * (1) aceito-ok
////                * (2) aceito-bloqueei
////                * (3) aceito-bloqueou-me
////                * (4) aceito-bloqueados
////                * (5) nao-aceito-convidou-me
////                * (6) nao-aceito-convidei
////                * (7) nao-amigo
//
//                c_tipoAmigo = SolicitarDados.s_SolicitarDados.defineTipoAmigo(LogUsuario.s_cod, Integer.parseInt(value.toString()));
////                    c_tipoAmigo = "aceito-ok";
//                    
//                        if(c_tipoAmigo.equals("aceito-ok"))
//                            c_caminhoIcone = Icones.AMIGO_OK_16;
//                        else if(c_tipoAmigo.equals("aceito-bloqueei"))
//                            c_caminhoIcone = Icones.AMIGO_BLOQUEIO_16;
//                        else if(c_tipoAmigo.equals("aceito-bloqueou-me"))
//                            c_caminhoIcone = Icones.AMIGO_OK_16;
//                        else if (c_tipoAmigo.equals("aceito-bloqueados"))
//                            c_caminhoIcone = Icones.AMIGO_BLOQUEIO_16;
//                        else if (c_tipoAmigo.equals("nao-aceito-convidou-me"))
//                            c_caminhoIcone = Icones.AMIGO_NOVO;
//                        else if(c_tipoAmigo.equals("nao-aceito-convidei"))
//                            c_caminhoIcone = Icones.AGUARDANDO_AMIGO_16;
//                        else if(c_tipoAmigo.equals("nao-amigo"))
//                            c_caminhoIcone = Icones.AMIGO_DESCONHECIDO_16;
//
//                    lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));
//                    lb_icone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//                    lb_icone.setText("");
//                 }

            }
            
            //LISTA DE AMIGOS DO AMIGO
            if(table == Moniersn.jt_listaAmigosDoAmigo){

                //ICONE APELIDO|humor
                 if(column == 0){

                    c_caminhoIcone = "/imagens/humor/"+SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(), "humor")+".png";
                    lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));
                    lb_icone.setText(SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(), "apelido"));

                 }

                 //TIPO DE AMIGO
                 else if(column == 1){
                     
                    c_codStatus = Integer.parseInt(value.toString());

                        if(c_codStatus == 1)
                            c_caminhoIcone = Icones.AMIGO_OK_16;
                        
                        else
                            c_caminhoIcone = Icones.AMIGO_COMUM_16;

                    lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone))); // NOI18N
                    lb_icone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    lb_icone.setText("");

                 }


            }
            

        //LISTA DE OCORRÊNCIAS DO USUÁRIO
        //LISTA DE OCORRENCIAS DO AMIGO
        //LISTA DE REC. PÚBLICO DO AMIGO
        if(table == Moniersn.jt_listaOcorrenciaUsuario || table == Moniersn.jt_listaOcorrenciaAmigo   ||
           table == Moniersn.jt_listaRecadosPubAmigo   || table == Moniersn.jt_listaAtualizacoesUsuario || 
           table == Moniersn.jt_listaAtualizacoesAmigo){

                //TESTE DE TIPO DE TABELA PARA CONTAGEM DE COMENTÁRIOS
                //CASO LISTA DE OCORR. DO USUÁRIO ou DO AMIGO
                if(table == Moniersn.jt_listaOcorrenciaUsuario || table == Moniersn.jt_listaOcorrenciaAmigo)
                    c_tipoComents = "ocorrencia";
                else if(table == Moniersn.jt_listaAtualizacoesUsuario || table == Moniersn.jt_listaAtualizacoesAmigo)
                    c_tipoComents = "atualizacao";
                //CASO OUTRA LISTA...
                else
                    c_tipoComents = "recado";


             //apelido|humor
             if(column == 0){

                    c_caminhoIcone = "/imagens/humor/"+SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(), "humor")+".png";
                    lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));

                    lb_icone.setText(SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(), "apelido"));

             }
             
//             //TIPO DE AMIGO
//             else if(column == 1){
//
////                * - aceito-ok
////                * - aceito-bloqueei
////                * - aceito-bloqueou-me
////                * - aceito-bloqueados
////                * - nao-aceito-convidou-me
////                * - nao-aceito-convidei
////                * - nao-amigo
//
//                c_tipoAmigo = SolicitarDados.s_SolicitarDados.defineTipoAmigo(LogUsuario.s_cod, Integer.parseInt(value.toString()));
////                c_tipoAmigo = "aceito-ok";
//                
//                    if(c_tipoAmigo.equals("aceito-ok"))
//                        c_caminhoIcone = Icones.AMIGO_OK_16;
//                    else if(c_tipoAmigo.equals("aceito-bloqueei"))
//                        c_caminhoIcone = Icones.AMIGO_BLOQUEIO_16;
//                    else if(c_tipoAmigo.equals("aceito-bloqueou-me"))
//                        c_caminhoIcone = Icones.AMIGO_OK_16;
//                    else if (c_tipoAmigo.equals("aceito-bloqueados"))
//                        c_caminhoIcone = Icones.AMIGO_BLOQUEIO_16;
//                    else if (c_tipoAmigo.equals("nao-aceito-convidou-me"))
//                        c_caminhoIcone = Icones.AMIGO_NOVO;
//                    else if(c_tipoAmigo.equals("nao-aceito-convidei"))
//                        c_caminhoIcone = Icones.AGUARDANDO_AMIGO_16;
//                    else if(c_tipoAmigo.equals("nao-amigo"))
//                        c_caminhoIcone = Icones.AMIGO_DESCONHECIDO_16;
//
//                    
//                lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));
//                lb_icone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//                lb_icone.setText("");
//                
//             }
             
            
             //QTD. COMENTÁRIOS
             else if(column == 3){

                if(c_tipoComents.equals("ocorrencia"))
                    c_caminhoIcone = Icones.COMENT_OCO;
                else if(c_tipoComents.equals("atualizacao"))
                    c_caminhoIcone = Icones.COMENT_ATU;
                else if(c_tipoComents.equals("recado"))
                    c_caminhoIcone = Icones.COMENT_REC_PUB;


                lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone))); // NOI18N
                int l_qtdComentarios = SolicitarDados.s_SolicitarDados.qtdComentarioPorConteudo(c_tipoComents,Integer.parseInt(value.toString()));
                //faz contagem da qtd de comentarios da ocorrencia em linha
                lb_icone.setText("("+l_qtdComentarios+")");
             }
        }


        //LISTA DE RECADOS PÚBLICOS DO USUÁRIO
        //LISTA DE RECADOS PRIVADOS DO USUÁRIO
        else if(table == Moniersn.jt_listaRecadosPubUsuario || table == Moniersn.jt_listaRecadosPriv){
        
                //COLUNA 0, LIDO NAO LIDO
                if(column == 0){
                        //REC PUBLICO
                        if(table == Moniersn.jt_listaRecadosPubUsuario){
                            //visto de recado
                            if(value.equals("S"))
                                c_caminhoIcone = Icones.REC_PUB_OK_16;
                            //visto ao visto
                            else
                                c_caminhoIcone = Icones.REC_PUB_NOVO_16;
                        }
                        //REC. PRIVADO
                        else{
                            if(value.equals("S"))
                                c_caminhoIcone = Icones.REC_PRIV_OK_16;
                            else
                                c_caminhoIcone = Icones.REC_PRIV_NOVO_16;
                        }

                    lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));
                    lb_icone.setText("");
                }

                //coluna 1, apelido|humor
                if(column == 1){
                      c_caminhoIcone = "/imagens/humor/"+SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(), "humor")+".png";
                      lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));
                      lb_icone.setText(SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(), "apelido"));
                }

                //coluna 4, QTD. COMENTS
                else if(column == 4){

                        //REC PUBLICO
                        if(table == Moniersn.jt_listaRecadosPubUsuario)
                            //COMENTARIO REC. PUB
                            c_caminhoIcone = Icones.COMENT_REC_PUB;
                        //REC. PRIVADO
                        else
                            //COMENTARIO REC. PRIV
                            c_caminhoIcone = Icones.COMENT_REC_PRIV;

                    lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone))); // NOI18N

                    //faz contagem da qtd de comentarios da ocorrencia em linha
                    lb_icone.setText("("+SolicitarDados.s_SolicitarDados.qtdComentarioPorConteudo("recado",Integer.parseInt(value.toString()))+")");

            }
        }
        
        //LISTA DE COMENTÁRIOS
        if(table == Comentario.jt_listaComentarios){

            //ICONE COD
            if(column == 0){

                     c_caminhoIcone = Icones.COMENT;

                     lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));
                     lb_icone.setText("");
            }

            //ICONE HUMOR
            if(column == 1){
                  c_caminhoIcone = "/imagens/humor/"+SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(),"humor")+".png";
                  lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));

                  lb_icone.setText(SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(), "apelido"));
            }
        }

        //LISTA DE ARQUIVOS
        if(table == Moniersn.jt_listaArquivosUsuario || table == Moniersn.jt_listaArquivosAmigo){


            //QUEM
            if(column == 0){
                     c_caminhoIcone = "/imagens/humor/"+SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(), "humor")+".png";
                     lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));
                     lb_icone.setText(SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(value.toString(), "apelido"));
            }

//          EXTENSÃO (tipo)
            if(column == 1){

                c_caminhoIcone = SolicitarDados.s_SolicitarDados.pegaIconeOuTipoExtensao(value.toString(), "icone");

                lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));
                lb_icone.setText(SolicitarDados.s_SolicitarDados.pegaIconeOuTipoExtensao(value.toString(), "tipo"));
            }
//          QTD. COMENTÁRIOS
            if(column == 5){

                    c_caminhoIcone = Icones.COMENT_ARQ;
                    lb_icone.setIcon(new javax.swing.ImageIcon(getClass().getResource(c_caminhoIcone)));

                    int l_qtdComentarios = SolicitarDados.s_SolicitarDados.qtdComentarioPorConteudo("arquivo", Integer.parseInt(value.toString()));
                    //faz contagem da qtd de comentarios da ocorrencia em linha
                    lb_icone.setText("("+l_qtdComentarios+")");
            }


        }

        return lb_icone;
    }
   
}

