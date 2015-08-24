/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import auxiliar.threads.AbreArquivoViaRunTime;
import auxiliar.threads.LogoCarregando;
import java.util.Date;
import formularios.Configuracoes;
import formularios.Login;
import formularios.Moniersn;
import formularios.MensagemDialogo;
import formularios.Comentario;
import formularios.CadastroUsuario;
import formularios.Aguardando;
import formularios.CompartilhaArquivo;
import formularios.ConfirmaSenha;
import formularios.SolicitacaoDeAmizade;
import formularios.Desconectar;
import formularios.DecisaoDeSolicitacaoDeAmizade;
import formularios.EscolhaDiretorio;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.TrayIcon;
import formularios.MensagemAviso;
import java.awt.Color;
import java.awt.Desktop;
import java.io.*;
import org.omg.CORBA.Current;



/**
 *
 * @author Ayrton Monier
 */
public class MostrarDados {

    public static MostrarDados s_MostrarDados = new MostrarDados();

    public void carregaInfoBasicas(int l_codUsuario, String l_tela){


        String l_sqlSelect = "SELECT u.*, c.* "
                            +"FROM usuario u, configuracoes_usuario c "
                            +"WHERE u.cod_usuario = c.cod_usuario "
                            +"AND u.cod_usuario = "+l_codUsuario;

        try{
            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            //INFORMAÇÕES BÁSICAS DO USUÁRIO
            String l_moniersn = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
            String l_nome = Dados.s_conexaoBanco.c_resultset.getString("u.nome");
            String l_sobrenome = Dados.s_conexaoBanco.c_resultset.getString("u.sobrenome");
            String l_frase = Dados.s_conexaoBanco.c_resultset.getString("u.minha_frase");
            String l_iconeHumor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
            Date   l_dataNascimento = Data.pegaDataTipoDateDDMMAAAA(Dados.s_conexaoBanco.c_resultset.getString("u.data_nascimento"), Data.AAAA_MM_DD);
            String l_dtNascimento = Data.pegaDataPorExtenso(Data.pegaDataTipoDateDDMMAAAA(Dados.s_conexaoBanco.c_resultset.getString("u.data_nascimento"), Data.AAAA_MM_DD), Data.DIA_MES_ANO);
            String l_dataNiver = Data.pegaDataPorExtenso(Data.pegaDataTipoDateDDMMAAAA(Dados.s_conexaoBanco.c_resultset.getString("u.data_nascimento"), Data.AAAA_MM_DD), Data.DIA_MES);
            String l_sexo = Dados.s_conexaoBanco.c_resultset.getString("u.sexo");
            String l_dataCadastro = Data.pegaDataPorExtenso(Data.pegaDataTipoDateDDMMAAAA(Dados.s_conexaoBanco.c_resultset.getString("u.data_hora_cadastro"), Data.AAAA_MM_DD_HH_MM_SS), Data.DIA_MES_ANO);
            String l_status = Dados.s_conexaoBanco.c_resultset.getString("u.status_contato");
            String l_host = Dados.s_conexaoBanco.c_resultset.getString("u.host");
            int    l_portaUDP = Dados.s_conexaoBanco.c_resultset.getInt("u.porta_udp");
            int    l_portaTCP = Dados.s_conexaoBanco.c_resultset.getInt("u.porta_tcp");

            //CONFIGURAÇÕES
            String l_mostrarNome = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_nome");
            String l_solicitarSenhaSystray = Dados.s_conexaoBanco.c_resultset.getString("c.solicitar_senha_ao_mostrar");
            String l_emitirAvisoSonoro = Dados.s_conexaoBanco.c_resultset.getString("c.emitir_aviso_sonoro");
            String l_quemVisuInfoBasicas = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_basicas");
            String l_quemVisuRecPub = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_lista_rec_pub");
            String l_quemBatePapoComigo = Dados.s_conexaoBanco.c_resultset.getString("c.quem_bate_papo_comigo");
            String l_mostrarMinhaFrase = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_minha_frase");
            String l_mostrarSexo = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_sexo");
            String l_mostrarDataNiver = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_data_niver");
            String l_mostrarDataCadastro = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_data_cadastro");


            if(l_tela.equals("conf-usuario")){

                    if(l_mostrarNome.equals("S"))
                        Configuracoes.s_telaConfiguracoes.lb_iconeApelido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/neutro.png")));
                    else
                        Configuracoes.s_telaConfiguracoes.lb_iconeApelido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/disfarce.png")));

                //deixa invisível o date chooser data de nascimento por segurança
                Configuracoes.s_telaConfiguracoes.dc_dataNascimento.setVisible(false);
                Configuracoes.s_telaConfiguracoes.bt_atuDataNascimento.setVisible(false);

                //seta os valores do banco nos campos
                Configuracoes.s_telaConfiguracoes.lb_codUsuario.setText(String.valueOf(LogUsuario.s_cod));
                Configuracoes.s_telaConfiguracoes.lb_apelido.setText(l_moniersn);
                Configuracoes.s_telaConfiguracoes.lb_dataCadastro.setText(l_dataCadastro);
                Configuracoes.s_telaConfiguracoes.tf_nome.setText(l_nome);
                Configuracoes.s_telaConfiguracoes.tf_sobrenome.setText(l_sobrenome);
                Configuracoes.s_telaConfiguracoes.lb_nomeCompleto.setText(l_nome+" "+l_sobrenome);
                Configuracoes.s_telaConfiguracoes.lb_dataNascimento.setText(l_dtNascimento);
                Configuracoes.s_telaConfiguracoes.dc_dataNascimento.setDate(l_dataNascimento);
                SolicitarDados.s_SolicitarDados.setaIconeHumor(l_iconeHumor);
                Configuracoes.s_telaConfiguracoes.cb_humor.setSelectedItem(l_iconeHumor);
                Configuracoes.s_telaConfiguracoes.lb_aniversario.setText(l_dataNiver);
                SolicitarDados.s_SolicitarDados.setarSexo(l_sexo);
                Configuracoes.s_telaConfiguracoes.tf_frase.setText(l_frase);
                Configuracoes.s_telaConfiguracoes.lb_iconMinhaFrase.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.FRASE)));
                Configuracoes.s_telaConfiguracoes.lb_hostPorta.setText(Avisos.TEXTO_CONECTADO_DE+"\\\\"+l_host+":"+l_portaUDP);

            }

            else if(l_tela.equals("inicio-usuario")){

                //NOME / ÍCONE HUMOR
                Moniersn.lb_nomeIconeHumorUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+l_iconeHumor+".png")));
                Moniersn.lb_nomeIconeHumorUsuario.setText(l_nome+" "+l_sobrenome);

                    //ICONE DE MOSTRAR  NAO MOSTRAR NOME
                    if(l_mostrarNome.equals("S")){
                        Moniersn.lb_mostrarNaoMostrarNome.setToolTipText(Avisos.TIP_TEXT_MOSTRAR_NOME_HABILITADO);
                        Moniersn.lb_mostrarNaoMostrarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/neutro.png")));
                    }
                        else{
                            Moniersn.lb_mostrarNaoMostrarNome.setToolTipText(Avisos.TIP_TEXT_MOSTRAR_NOME_DESABILITADO);
                            Moniersn.lb_mostrarNaoMostrarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/disfarce.png")));
                        }

                    //ICONE DE HABILITAR  DESABILITAR SEGURANÇA
                    if(l_solicitarSenhaSystray.equals("S")){
                        Moniersn.lb_seguranca.setToolTipText(Avisos.TIP_TEXT_SEGURANCA_HABILITADA);
                        Moniersn.lb_seguranca.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.FECHADO_SYSTRAY)));
                    }
                        else{
                            Moniersn.lb_seguranca.setToolTipText(Avisos.TIP_TEXT_SEGURANCA_DESABILITADA);
                            Moniersn.lb_seguranca.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LIBERADO_SYSTRAY)));
                        }

                    //ICONE DE HABILITAR  DESABILITAR AUDIO
                    if(l_emitirAvisoSonoro.equals("S")){
                        Moniersn.lb_habilitarAudio.setToolTipText(Avisos.TIP_TEXT_AVISO_SON_HABILITADO);
                        Moniersn.lb_habilitarAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AUDIO_ON)));
                    }
                        else{
                            Moniersn.lb_habilitarAudio.setToolTipText(Avisos.TIP_TEXT_AVISO_SON_DESABILITADO);
                            Moniersn.lb_habilitarAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AUDIO_OFF)));
                        }
                    
                    

                if(!l_quemVisuInfoBasicas.equals("ninguem")){

                        //FRASE
                        if(l_mostrarMinhaFrase.equals("S")){
                            Moniersn.lb_fraseUsuario.setVisible(true);
                            Moniersn.lb_fraseUsuario.setText(l_frase);
                            Moniersn.lb_fraseUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.FRASE))); // NOI18N
                        }
                            else{
                                Moniersn.lb_fraseUsuario.setVisible(false);
                            }

                        //SEXO 
                         if(l_mostrarSexo.equals("S")){

                           Moniersn.lb_sexoUsuario.setVisible(true);

                            //teste ícone de sexo
                            if(l_sexo.equals("M")){
                                //sexo masculino
                                Moniersn.lb_sexoUsuario.setText(Avisos.TEXTO_MASCULINO);
                                Moniersn.lb_sexoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.SEX_MASC_16))); // NOI18N
                            }
                                else{
                                    //sexo feminino
                                    Moniersn.lb_sexoUsuario.setText(Avisos.TEXTO_FEMININO);
                                    Moniersn.lb_sexoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.SEX_FEM_16))); // NOI18N
                                }
                         }
                            else{
                                Moniersn.lb_sexoUsuario.setVisible(false);
                            }

                        //DATA DE ANIVERSÁRIO
                        if(l_mostrarDataNiver.equals("S")){

                            Data.atualizaDataHora();
                            String l_diaMesHoje = Data.pegaDataPorExtenso(Data.pegaDataTipoDateDDMMAAAA(Data.s_dataAtualDDMMAAAA, Data.DD_MM_AAAA), Data.DIA_MES);

                                System.out.println(l_diaMesHoje+" "+l_dataNiver);
                                Moniersn.lb_dataNiverUsuario.setToolTipText(Avisos.TEXTO_HOJE+l_diaMesHoje);

                                if(l_diaMesHoje.equals(l_dataNiver)){

                                    Moniersn.lb_dataNiverUsuario.setText(Avisos.TEXTO_FACO_NIVER_HOJE);
                                }
                                else
                                    Moniersn.lb_dataNiverUsuario.setText(Avisos.TEXTO_FACO_NIVER_EM+l_dataNiver+"!");

                            Moniersn.lb_dataNiverUsuario.setVisible(true);
                            
                        }
                            else{
                                Moniersn.lb_dataNiverUsuario.setVisible(false);
                            }

                        if(l_mostrarDataCadastro.equals("S")){
                            Moniersn.lb_dataCadastroUsuario.setVisible(true);
                            Moniersn.lb_dataCadastroUsuario.setText(Avisos.TEXTO_CADASTREI_ME_EM+l_dataCadastro+"!");
                        }
                            else{
                                Moniersn.lb_dataCadastroUsuario.setVisible(false);
                            }
                }
                    //CASO TENHA ESCOLHIDO NINGUEM VER O INFO. BÁSICAS
                    else{

                        Moniersn.lb_fraseUsuario.setVisible(false);
                        Moniersn.lb_sexoUsuario.setVisible(false);
                        Moniersn.lb_dataNiverUsuario.setVisible(false);
                        Moniersn.lb_dataCadastroUsuario.setVisible(false);

                    }

            }//FIM inicio-usuario
                    
            if(l_tela.equals("inicio-amigo")){

                //GUARDA NO LOG
                LogAmigo.s_cod = l_codUsuario;
                LogAmigo.s_moniersn = l_moniersn;
                LogAmigo.s_nome = l_nome;
                LogAmigo.s_sobrenome = l_sobrenome;
                LogAmigo.s_status = l_status;
                LogAmigo.s_host = l_host;
                LogAmigo.s_portaUDP = l_portaUDP;
                LogAmigo.s_portaTCP = l_portaTCP;
                LogAmigo.s_iconeHumor = l_iconeHumor;
                LogAmigo.s_quemBatePapoComigo = l_quemBatePapoComigo;
                LogAmigo.s_quemVeRecPub = l_quemVisuRecPub;


                //TESTA VISUALIZAÇÃO PELOS TIPOS DE USUÁRIOS
                //como o amigo será conhecido
                String l_nomeAmigo = null;
                String l_nomeApelidoAmigo = null;
                String l_iconeStatus = null;
                String l_caminhoIcone = null;
                String l_textoTipoAmigo = null;


                //DECIDE SE O AMIGO MOSTRA NOME OU APELIDO
                    if(l_mostrarNome.equals("S")){
                        l_nomeApelidoAmigo = l_nome+" "+l_sobrenome+" ("+l_moniersn+")";
                        l_nomeAmigo = l_nome+" "+l_sobrenome;
                        Moniersn.lb_mostrarNaoMostrarNomeAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/neutro.png"))); // NOI18N
                        Moniersn.lb_mostrarNaoMostrarNomeAmigo.setToolTipText(Avisos.TIP_TEXT_MOSTRAR_NOME_HABILITADO);
                    }
                    else{
                        l_nomeApelidoAmigo = l_moniersn;
                        l_nomeAmigo = l_moniersn;
                        Moniersn.lb_mostrarNaoMostrarNomeAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/disfarce.png"))); // NOI18N
                        Moniersn.lb_mostrarNaoMostrarNomeAmigo.setToolTipText(Avisos.TIP_TEXT_MOSTRAR_NOME_DESABILITADO);
                    }
                
                //atualizar informações de... nome do amigo
                Moniersn.bt_atualizarInfoAmigo.setToolTipText(Avisos.TIP_TEXT_ATU_INFO+l_nomeApelidoAmigo);

                
                //define ícone do tipo de amigo
                    if(LogAmigo.s_tipoAmigo.equals("aceito-ok")){
                        l_caminhoIcone = Icones.AMIGO_OK_16;
                        l_textoTipoAmigo = Avisos.TIP_TEXT_ACEITO;
                    }
                    else if(LogAmigo.s_tipoAmigo.equals("aceito-bloqueei")){
                        l_caminhoIcone = Icones.AMIGO_BLOQUEIO_16;
                        l_textoTipoAmigo = Avisos.TIP_TEXT_BLOQUEADO;
                    }
                    else if(LogAmigo.s_tipoAmigo.equals("aceito-bloqueou-me")){
                        l_caminhoIcone = Icones.AMIGO_OK_16;
                        l_textoTipoAmigo = Avisos.TIP_TEXT_ACEITO;
                    }
                    else if (LogAmigo.s_tipoAmigo.equals("aceito-bloqueados")){
                        l_caminhoIcone = Icones.AMIGO_BLOQUEIO_16;
                        l_textoTipoAmigo = Avisos.TIP_TEXT_BLOQUEADO;
                    }
                    else if (LogAmigo.s_tipoAmigo.equals("nao-aceito-convidou-me")){
                        l_caminhoIcone = Icones.AMIGO_NOVO;
                        l_textoTipoAmigo = Avisos.TIP_TEXT_CONVITE;
                    }
                    else if(LogAmigo.s_tipoAmigo.equals("nao-aceito-convidei")){
                        l_caminhoIcone = Icones.AGUARDANDO_AMIGO_16;
                        l_textoTipoAmigo = Avisos.TIP_TEXT_CONVIDADO;
                    }
                    else if(LogAmigo.s_tipoAmigo.equals("nao-amigo")){
                        l_caminhoIcone = Icones.AMIGO_DESCONHECIDO_16;
                        l_textoTipoAmigo = Avisos.TIP_TEXT_DESCONHECIDO;
                    }

                Moniersn.lb_tipoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_caminhoIcone))); // NOI18N
                Moniersn.lb_tipoUsuario.setToolTipText(l_nomeApelidoAmigo+l_textoTipoAmigo);
                
                
                    //TESTE ICONE STATUS
                    if(l_status.equals("conectado"))
                        l_iconeStatus = Icones.STATUS_CONECTADO_16;
                    else
                        l_iconeStatus = Icones.STATUS_DESCONECTADO_16;
                
                //TESTA SE ESTÁ BLOQUEADO OU SE ME BLOQUEOU
                if(LogAmigo.s_tipoAmigo.equals("aceito-bloqueei") ||
                   LogAmigo.s_tipoAmigo.equals("aceito-bloqueou-me") ||
                   LogAmigo.s_tipoAmigo.equals("aceito-bloqueados")){

                    //define nome/apelido
                    Moniersn.lb_nomeIconeHumorAmigo.setText(l_nomeAmigo);

                    
                        //CASO ME BLOQUEOU
                        if(LogAmigo.s_tipoAmigo.equals("aceito-bloqueou-me")){


                            //ADD ABA PRINCIPAL
                            adicionaPainelDeInformacoes(l_nomeApelidoAmigo, Moniersn.jp_inicioAmigo, l_iconeStatus);

                            //SETA O ICONE DE HUMOR
                            Moniersn.lb_nomeIconeHumorAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+l_iconeHumor+".png")));

                            //ÍCONE E TIPTEXT DE REMOVER AMIGO
                            Moniersn.bt_addExcAceEspAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_REMOCAO_16))); // NOI18N
                            Moniersn.bt_addExcAceEspAmigo.setToolTipText(Avisos.TIP_TEXT_REMOVER_AMIGO+l_nomeApelidoAmigo+"...");
                            Moniersn.bt_addExcAceEspAmigo.setVisible(true);

                            //ÍCONE E TIPTEXT DE BLOQUEIO
                            Moniersn.bt_bloqDesblocAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_BLOQUEIO_16))); // NOI18N
                            Moniersn.bt_bloqDesblocAmigo.setToolTipText(Avisos.TIP_TEXT_BLOQUEAR_AMIGO+l_nomeApelidoAmigo+"...");
                            Moniersn.bt_bloqDesblocAmigo.setVisible(true);

                        }

                        //CASO EU O BLOQUEEI
                        if(LogAmigo.s_tipoAmigo.equals("aceito-bloqueei")){

                            //ADD ABA PRINCIPAL
                            adicionaPainelDeInformacoes(l_nomeApelidoAmigo, Moniersn.jp_inicioAmigo, l_iconeStatus);

                            //SETA ÍCONE DE HUMOR (bloqueado)
                            Moniersn.lb_nomeIconeHumorAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+l_iconeHumor+".png")));

                            //ÍCONE E TIPTEXT DE REMOVER AMIGO
                            Moniersn.bt_addExcAceEspAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_REMOCAO_16))); // NOI18N
                            Moniersn.bt_addExcAceEspAmigo.setToolTipText(Avisos.TIP_TEXT_REMOVER_AMIGO+l_nomeApelidoAmigo+"...");
                            Moniersn.bt_addExcAceEspAmigo.setVisible(true);

                            //ICONE E TIPTEXT DE DESBLOQUEIO
                            Moniersn.bt_bloqDesblocAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_DESBLOQUEIO_16))); // NOI18N
                            Moniersn.bt_bloqDesblocAmigo.setToolTipText(Avisos.TIP_TEXT_DESBLOQUEAR_AMIGO+l_nomeApelidoAmigo+"...");
                            Moniersn.bt_bloqDesblocAmigo.setVisible(true);
                        
                        }

                        //CASO BLOQUEEI E ELE ME BLOQUEOU TAMBEM
                        if(LogAmigo.s_tipoAmigo.equals("aceito-bloqueados")){
                           
                            //add aba principal com icone da guia como desconectado
                            adicionaPainelDeInformacoes(l_nomeApelidoAmigo, Moniersn.jp_inicioAmigo, l_iconeStatus);

                            //SETA ÍCONE DE HUMOR (bloqueado)
                            Moniersn.lb_nomeIconeHumorAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+l_iconeHumor+".png")));

                            //ÍCONE E TIPTEXT DE REMOVER AMIGO
                            Moniersn.bt_addExcAceEspAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_REMOCAO_16))); // NOI18N
                            Moniersn.bt_addExcAceEspAmigo.setToolTipText(Avisos.TIP_TEXT_REMOVER_AMIGO+l_nomeApelidoAmigo+"...");
                            Moniersn.bt_addExcAceEspAmigo.setVisible(true);

                            //ICONE E TIPTEXT DE DESBLOQUEIO
                            Moniersn.bt_bloqDesblocAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_DESBLOQUEIO_16))); // NOI18N
                            Moniersn.bt_bloqDesblocAmigo.setToolTipText(Avisos.TIP_TEXT_DESBLOQUEAR_AMIGO+l_nomeApelidoAmigo+"...");
                            Moniersn.bt_bloqDesblocAmigo.setVisible(true);
                        }


                    //NAO E POSSÍVEL VISUALIZAR INFORMAÇÕES BÁSICAS
                    Moniersn.lb_fraseAmigo.setVisible(false);
                    Moniersn.lb_ultOcorrenciaAmigo.setVisible(false);
                    Moniersn.lb_qtdComentarioUltOcoAmigo.setVisible(false);
                    Moniersn.lb_sexoAmigo.setVisible(false);
                    Moniersn.lb_niverAmigo.setVisible(false);
                    Moniersn.lb_dataCadastroAmigo.setVisible(false);

                    //NAO E POSSÍVEL VISUALIZAR LISTA DE AMIGOS
                    Moniersn.sp_listaAmigosAmigo.setVisible(false);
                    Moniersn.jp_opcoesAmigosAmigo.setVisible(false);
                    Moniersn.lb_qtdUsuariosListaAmigo.setVisible(false);
                    Moniersn.tf_pesquisaListaAmigo.setVisible(false);
                    Moniersn.bt_verMaisUsuariosListaAmigo.setVisible(false);
                    Moniersn.bt_atuUsuariosListaAmigo.setVisible(false);
                    
                    //NAO E POSSÍVEL COMPRIMENTAR
                    Moniersn.s_telaMsn.bt_comprimento.setVisible(false);

                    //NAO É POSSÍVEL BATER PAPO
                    Moniersn.s_telaMsn.bt_batePapo.setVisible(false);

                    //NAO É POSSÍVEL ENVIAR ARQUIVO
                    Moniersn.s_telaMsn.bt_enviarArquivo.setVisible(false);

                }//fim teste de bloqueio de amigo

                    //CASO NÃO ESTEJA BLOQUEADO OU NAO ME BLOQUEOU...
                    else{                      

                            //add aba principal com icone da guia com o status atual
                            adicionaPainelDeInformacoes(l_nomeApelidoAmigo, Moniersn.jp_inicioAmigo, l_iconeStatus);

                            //SETA NOME e ICONE DE HUMOR DO AMIGO (nome ou apelido)
                            Moniersn.lb_nomeIconeHumorAmigo.setText(l_nomeAmigo);
                            Moniersn.lb_nomeIconeHumorAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+l_iconeHumor+".png")));

                                //DEFINIÇÕES DO PAINEL DO AMIGO
                                //INFORMAÇÕES BÁSICAS
                                if(l_quemVisuInfoBasicas.equals("todos") ||
                                  (l_quemVisuInfoBasicas.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){

                                    //VISUALIZAÇAO FRASE
                                        //todos veem a minha frase
                                        if(l_mostrarMinhaFrase.equals("S")){
                                            //icone de status da frase do amigo
                                            Moniersn.lb_fraseAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.FRASE))); // NOI18N
                                            Moniersn.lb_fraseAmigo.setText(l_frase);
                                            Moniersn.lb_fraseAmigo.setVisible(true);
                                        }        //não quis mostrar...
                                                 else
                                                     Moniersn.lb_fraseAmigo.setVisible(false);

                                    //VISUALIZAÇAO SEXO
                                        //todos veem o sexo
                                        if(l_mostrarSexo.equals("S")){

                                            if(l_sexo.equals("M")){
                                                Moniersn.lb_sexoAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.SEX_MASC_16))); // NOI18N
                                                Moniersn.lb_sexoAmigo.setText(Avisos.TEXTO_MASCULINO);
                                            }
                                                else{
                                                    Moniersn.lb_sexoAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.SEX_FEM_16))); // NOI18N
                                                    Moniersn.lb_sexoAmigo.setText(Avisos.TEXTO_FEMININO);
                                                }
                                            //mostra o sexo
                                            Moniersn.lb_sexoAmigo.setVisible(true);
                                        }
                                                 //não quis mostrar o sexo...
                                                 else
                                                     Moniersn.lb_sexoAmigo.setVisible(false);

                                    //VISUALIZAÇAO DO ANIVERSÁRIO DO AMIGO
                                        if(l_mostrarDataNiver.equals("S")){
                                            
                                            Data.atualizaDataHora();
                                            String l_diaMesHoje = Data.pegaDataPorExtenso(Data.pegaDataTipoDateDDMMAAAA(Data.s_dataAtualDDMMAAAA, Data.DD_MM_AAAA), Data.DIA_MES);

                                                if(l_diaMesHoje.equals(l_dataNiver))
                                                    Moniersn.lb_niverAmigo.setText(Avisos.TEXTO_FAZ_NIVER_HOJE);
                                                else
                                                    Moniersn.lb_niverAmigo.setText(Avisos.TEXTO_FAZ_NIVER_EM+l_dataNiver);

                                            Moniersn.lb_niverAmigo.setVisible(true);
                                        }
                                                //não quis mostrar a data de aniversário...
                                                else
                                                    Moniersn.lb_niverAmigo.setVisible(false);

                                    //VISUALIZAÇAO DA DATA DE CADASTRO
                                        if(l_mostrarDataCadastro.equals("S")){

                                            Moniersn.lb_dataCadastroAmigo.setText(Avisos.TEXTO_CADASTROU_SE_EM+l_dataCadastro);
                                            Moniersn.lb_dataCadastroAmigo.setVisible(true);
                                        }
                                                //não quis mostrar data de cadastro...
                                                else
                                                    Moniersn.lb_dataCadastroAmigo.setVisible(false);

                                }//fim IF INFO BÁSICAS

                                    //ninguem vê informações básicas
                                    else{
                                         Moniersn.lb_fraseAmigo.setVisible(false);
                                         Moniersn.lb_sexoAmigo.setVisible(false);
                                         Moniersn.lb_niverAmigo.setVisible(false);
                                         Moniersn.lb_dataCadastroAmigo.setVisible(false);
                                    }

                  }

                //RECADO PRIVADO SEMPRE PODERÁ SER EMITIDO
                Moniersn.lb_escrevaUmRecPriv.setText(Avisos.TEXTO_ESCREVA_REC_PRIV+l_nomeAmigo+"¬");

              }//FIM inicio-amigo
        }
        catch(Exception e){
                System.err.println("Erro ao tentar carregar informações básicas do usuário.     \nErro: "+e);
        }

    }

   public void carregaInfoPessoal(int l_codUsuario, String l_tela){

        String sqlSelect = "SELECT u.*, c.* "+
                           "FROM usuario u, configuracoes_usuario c "+
                           "WHERE u.cod_usuario = c.cod_usuario "+
                           "AND u.cod_usuario = "+l_codUsuario;

        try{
            Dados.s_conexaoBanco.executeSELECT(sqlSelect);
            Dados.s_conexaoBanco.c_resultset.first();

            //INFORMAÇÕES PESSOAIS DO USUÁRIO
            String l_naturalidade = Dados.s_conexaoBanco.c_resultset.getString("u.naturalidade");
            String l_nacionalidade = Dados.s_conexaoBanco.c_resultset.getString("u.nacionalidade");
            String l_fisico = Dados.s_conexaoBanco.c_resultset.getString("u.fisico");
            String l_estadoCivil = Dados.s_conexaoBanco.c_resultset.getString("u.estado_civil");
            String l_quemEuSou = Dados.s_conexaoBanco.c_resultset.getString("u.quem_eu_sou");
            String l_adoro = Dados.s_conexaoBanco.c_resultset.getString("u.adoro");
            String l_odeio = Dados.s_conexaoBanco.c_resultset.getString("u.odeio");
            String l_filme = Dados.s_conexaoBanco.c_resultset.getString("u.filme");
            String l_musica = Dados.s_conexaoBanco.c_resultset.getString("u.musica");
            String l_esporte = Dados.s_conexaoBanco.c_resultset.getString("u.esporte");
            String l_lugar = Dados.s_conexaoBanco.c_resultset.getString("u.lugar");
            String l_conselho = Dados.s_conexaoBanco.c_resultset.getString("u.conselho");
            String l_cantada = Dados.s_conexaoBanco.c_resultset.getString("u.cantada");
            String l_mania = Dados.s_conexaoBanco.c_resultset.getString("u.mania");
            String l_cor = Dados.s_conexaoBanco.c_resultset.getString("u.cor");
            String l_timeFutebol = Dados.s_conexaoBanco.c_resultset.getString("u.time_futebol");
            String l_sonho = Dados.s_conexaoBanco.c_resultset.getString("u.sonho");
            String l_pesadelo = Dados.s_conexaoBanco.c_resultset.getString("u.pesadelo");
            String l_bebida = Dados.s_conexaoBanco.c_resultset.getString("u.bebida");
            String l_prato = Dados.s_conexaoBanco.c_resultset.getString("u.prato");
            String l_passeio = Dados.s_conexaoBanco.c_resultset.getString("u.passeio");
            String l_viagem = Dados.s_conexaoBanco.c_resultset.getString("u.viagem");
            String l_desejo = Dados.s_conexaoBanco.c_resultset.getString("u.desejo");
            String l_vontade = Dados.s_conexaoBanco.c_resultset.getString("u.vontade");
            String l_esperanca = Dados.s_conexaoBanco.c_resultset.getString("u.esperanca");
            String l_hobby = Dados.s_conexaoBanco.c_resultset.getString("u.hobby");

                if(l_tela.equals("conf-usuario")){


                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_nacionalidade,"nacionalidade","nacionalidade");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_naturalidade,"naturalidade","naturalidade");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_filme,"filme","filme");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_musica,"musica","musica");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_esporte,"esporte","esporte");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_cor,"cor","cor");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_timeFutebol,"time_futebol","time_futebol");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_bebida,"bebida","bebida");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_prato,"prato","prato");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_hobby,"hobby","hobby");

                    Configuracoes.s_telaConfiguracoes.cb_naturalidade.setSelectedItem(l_naturalidade);
                    Configuracoes.s_telaConfiguracoes.cb_nacionalidade.setSelectedItem(l_nacionalidade);
                    Configuracoes.s_telaConfiguracoes.cb_fisico.setSelectedItem(l_fisico);
                    Configuracoes.s_telaConfiguracoes.cb_estadoCivil.setSelectedItem(l_estadoCivil);
                    Configuracoes.s_telaConfiguracoes.tf_queEuSou.setText(l_quemEuSou);
                    Configuracoes.s_telaConfiguracoes.tf_adoro.setText(l_adoro);
                    Configuracoes.s_telaConfiguracoes.tf_odeio.setText(l_odeio);
                    Configuracoes.s_telaConfiguracoes.cb_filme.setSelectedItem(l_filme);
                    Configuracoes.s_telaConfiguracoes.cb_musica.setSelectedItem(l_musica);
                    Configuracoes.s_telaConfiguracoes.cb_esporte.setSelectedItem(l_esporte);
                    Configuracoes.s_telaConfiguracoes.tf_lugar.setText(l_lugar);
                    Configuracoes.s_telaConfiguracoes.tf_conselho.setText(l_conselho);
                    Configuracoes.s_telaConfiguracoes.tf_cantada.setText(l_cantada);
                    Configuracoes.s_telaConfiguracoes.tf_mania.setText(l_mania);
                    Configuracoes.s_telaConfiguracoes.cb_cor.setSelectedItem(l_cor);
                    Configuracoes.s_telaConfiguracoes.cb_timeFutebol.setSelectedItem(l_timeFutebol);
                    Configuracoes.s_telaConfiguracoes.tf_sonho.setText(l_sonho);
                    Configuracoes.s_telaConfiguracoes.tf_pesadelo.setText(l_pesadelo);
                    Configuracoes.s_telaConfiguracoes.cb_bebida.setSelectedItem(l_bebida);
                    Configuracoes.s_telaConfiguracoes.cb_prato.setSelectedItem(l_prato);
                    Configuracoes.s_telaConfiguracoes.tf_passeio.setText(l_passeio);
                    Configuracoes.s_telaConfiguracoes.tf_viagem.setText(l_viagem);
                    Configuracoes.s_telaConfiguracoes.tf_desejo.setText(l_desejo);
                    Configuracoes.s_telaConfiguracoes.tf_vontade.setText(l_vontade);
                    Configuracoes.s_telaConfiguracoes.tf_esperanca.setText(l_esperanca);
                    Configuracoes.s_telaConfiguracoes.cb_hobby.setSelectedItem(l_hobby);
                }
                if(l_tela.equals("pessoal-amigo")){

                    Moniersn.lb_nacionalidade.setText(l_nacionalidade);
                    Moniersn.lb_naturalidade.setText(l_naturalidade);
                    Moniersn.lb_fisico.setText(l_fisico);
                    Moniersn.lb_estadoCivil.setText(l_estadoCivil);
                    Moniersn.lb_quemSou.setText(l_quemEuSou);
                    Moniersn.lb_adoro.setText(l_adoro);
                    Moniersn.lb_odeio.setText(l_odeio);
                    Moniersn.lb_filme.setText(l_filme);
                    Moniersn.lb_musica.setText(l_musica);
                    Moniersn.lb_esporte.setText(l_esporte);
                    Moniersn.lb_lugar.setText(l_lugar);
                    Moniersn.lb_cantada.setText(l_cantada);
                    Moniersn.lb_mania.setText(l_mania);
                    Moniersn.lb_cor.setText(l_cor);
                    Moniersn.lb_conselho.setText(l_conselho);
                    Moniersn.lb_time.setText(l_timeFutebol);
                    Moniersn.lb_sonho.setText(l_sonho);
                    Moniersn.lb_pesadelo.setText(l_pesadelo);
                    Moniersn.lb_bebida.setText(l_bebida);
                    Moniersn.lb_prato.setText(l_prato);
                    Moniersn.lb_passeio.setText(l_passeio);
                    Moniersn.lb_viagem.setText(l_viagem);
                    Moniersn.lb_desejo.setText(l_desejo);
                    Moniersn.lb_vontade.setText(l_vontade);
                    Moniersn.lb_esperanca.setText(l_esperanca);
                    Moniersn.lb_hobby.setText(l_hobby);
                }
       }
       catch(Exception e){
            System.err.println("Erro ao tentar carregar informações pessoais.    \nErro: "+e);
       }
   }

   public void carregaInfoContato(int l_codUSuario, String l_tela){

        String sqlSelect = "SELECT u.*, c.* "+
                           "FROM usuario u, configuracoes_usuario c "+
                           "WHERE u.cod_usuario = c.cod_usuario "+
                           "AND u.cod_usuario = "+l_codUSuario;

        try{
                Dados.s_conexaoBanco.executeSELECT(sqlSelect);
                Dados.s_conexaoBanco.c_resultset.first();

                //INFORMAÇÕES DE CONTATO DO USUÁRIO
                String l_rua = Dados.s_conexaoBanco.c_resultset.getString("u.rua");
                String l_numero = Dados.s_conexaoBanco.c_resultset.getString("u.numero");
                String l_bairro = Dados.s_conexaoBanco.c_resultset.getString("u.bairro");
                String l_cidade = Dados.s_conexaoBanco.c_resultset.getString("u.cidade");
                String l_fone = Dados.s_conexaoBanco.c_resultset.getString("u.fone");
                //celular
                String l_celOi = Dados.s_conexaoBanco.c_resultset.getString("u.cel_oi");
                String l_celTim = Dados.s_conexaoBanco.c_resultset.getString("u.cel_tim");
                String l_celVivo = Dados.s_conexaoBanco.c_resultset.getString("u.cel_vivo");
                String l_celClaro = Dados.s_conexaoBanco.c_resultset.getString("u.cel_claro");
                String l_celOutro = Dados.s_conexaoBanco.c_resultset.getString("u.cel_outro");
                //email
                String l_EmailHotmail = Dados.s_conexaoBanco.c_resultset.getString("u.email_hotmail");
                String l_EmailGmail = Dados.s_conexaoBanco.c_resultset.getString("u.email_gmail");
                String l_EmailYahoo = Dados.s_conexaoBanco.c_resultset.getString("u.email_yahoo");
                String l_EmailIg = Dados.s_conexaoBanco.c_resultset.getString("u.email_ig");
                String l_EmailBol = Dados.s_conexaoBanco.c_resultset.getString("u.email_bol");
                String l_EmailOutro = Dados.s_conexaoBanco.c_resultset.getString("u.email_outro");
                //rede social
                String l_orkut = Dados.s_conexaoBanco.c_resultset.getString("u.orkut");
                String l_twitter = Dados.s_conexaoBanco.c_resultset.getString("u.twitter");
                String l_facebook = Dados.s_conexaoBanco.c_resultset.getString("u.facebook");
                String l_myspace = Dados.s_conexaoBanco.c_resultset.getString("u.myspace");
                String l_skype = Dados.s_conexaoBanco.c_resultset.getString("u.skype");
                String l_linkedin = Dados.s_conexaoBanco.c_resultset.getString("u.linkedin");
                String l_blog = Dados.s_conexaoBanco.c_resultset.getString("u.blog");

            if(l_tela.equals("conf-usuario")){

                carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_bairro,"bairro","bairro");
                carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_cidade,"cidade","cidade");

                Configuracoes.s_telaConfiguracoes.tf_rua.setText(l_rua);
                Configuracoes.s_telaConfiguracoes.tf_numero.setText(l_numero);
                Configuracoes.s_telaConfiguracoes.cb_bairro.setSelectedItem(l_bairro);
                Configuracoes.s_telaConfiguracoes.cb_cidade.setSelectedItem(l_cidade);
                Configuracoes.s_telaConfiguracoes.tf_fone.setText(l_fone);
                Configuracoes.s_telaConfiguracoes.tf_celOi.setText(l_celOi);
                Configuracoes.s_telaConfiguracoes.tf_celTim.setText(l_celTim);
                Configuracoes.s_telaConfiguracoes.tf_celVivo.setText(l_celVivo);
                Configuracoes.s_telaConfiguracoes.tf_celClaro.setText(l_celClaro);
                Configuracoes.s_telaConfiguracoes.tf_celOutro.setText(l_celOutro);
                Configuracoes.s_telaConfiguracoes.tf_emailHotmail.setText(l_EmailHotmail);
                Configuracoes.s_telaConfiguracoes.tf_emailGmail.setText(l_EmailGmail);
                Configuracoes.s_telaConfiguracoes.tf_emailYahoo.setText(l_EmailYahoo);
                Configuracoes.s_telaConfiguracoes.tf_emailIg.setText(l_EmailIg);
                Configuracoes.s_telaConfiguracoes.tf_emailBol.setText(l_EmailBol);
                Configuracoes.s_telaConfiguracoes.tf_emailOutro.setText(l_EmailOutro);
                Configuracoes.s_telaConfiguracoes.tf_orkut.setText(l_orkut);
                Configuracoes.s_telaConfiguracoes.tf_twitter.setText(l_twitter);
                Configuracoes.s_telaConfiguracoes.tf_facebook.setText(l_facebook);
                Configuracoes.s_telaConfiguracoes.tf_myspace.setText(l_myspace);
                Configuracoes.s_telaConfiguracoes.tf_skype.setText(l_skype);
                Configuracoes.s_telaConfiguracoes.tf_linkedin.setText(l_linkedin);
                Configuracoes.s_telaConfiguracoes.tf_blog.setText(l_blog);
            }

            if(l_tela.equals("contato-amigo")){

                Moniersn.lb_rua.setText(l_rua);
                Moniersn.lb_numero.setText(l_numero);
                Moniersn.lb_bairro.setText(l_bairro);
                Moniersn.lb_cidade.setText(l_cidade);
                Moniersn.lb_fone.setText(l_fone);
                Moniersn.lb_celOi.setText(l_celOi);
                Moniersn.lb_celTim.setText(l_celTim);
                Moniersn.lb_celVivo.setText(l_celVivo);
                Moniersn.lb_celClaro.setText(l_celClaro);
                Moniersn.lb_celOutro.setText(l_celOutro);
                Moniersn.lb_emailHotmail.setText(l_EmailHotmail);
                Moniersn.lb_emailGmail.setText(l_EmailGmail);
                Moniersn.lb_emailYahoo.setText(l_EmailYahoo);
                Moniersn.lb_emailIg.setText(l_EmailIg);
                Moniersn.lb_emailBol.setText(l_EmailBol);
                Moniersn.lb_emailOutro.setText(l_EmailOutro);
                Moniersn.lb_orkut.setText(l_orkut);
                Moniersn.lb_twitter.setText(l_twitter);
                Moniersn.lb_facebook.setText(l_facebook);
                Moniersn.lb_myspace.setText(l_myspace);
                Moniersn.lb_skype.setText(l_skype);
                Moniersn.lb_linkedin.setText(l_linkedin);
                Moniersn.lb_blog.setText(l_blog);
            }
       }
       catch(Exception e){
            System.err.println("Erro ao tentar carregar as informações de contato.    \nErro: "+e);
       }
   }

   public void carregaInfoEducacional(int l_codUsuario, String l_tela){

        String l_dataEntradaInstEnsino = null;
        String l_dataTerminoInstEnsino = null;
        String l_hhMMssIniAula = null;
        String l_hhMMssFimAula = null;
        String l_hhMMssIniIntervalo = null;
        String l_hhMMssFimIntervalo = null;
        String l_instEnsino = null;
        String l_curso = null;
        String l_turma = null;
        String l_naoGostoDeNaInst = null;
        String l_sempreGostoDeNaInst = null;
        String l_vouMelhorarEmNaInst = null;
        String l_aInstEh = null;
        String l_estaFaltandoNaInst = null;
        String l_nasHorasVagas = null;
        String l_melhorDisciplina = null;
        String l_piorDisciplina = null;
        String l_minhaTurmaEh = null;

        int l_horaIniAula = 0;
        int l_minutoIniAula = 0;

        int l_horaFimAula = 0;
        int l_minutoFimAula = 0;

        int l_horaIniIntervalo  = 0;
        int l_minutoIniIntervalo  = 0;

        int l_horaFimIntervalo = 0;
        int l_minutoFimIntervalo = 0;


        String sqlSelect = "SELECT u.*, c.* "+
                           "FROM usuario u, configuracoes_usuario c "+
                           "WHERE u.cod_usuario = c.cod_usuario "+
                           "AND u.cod_usuario = "+l_codUsuario;


                //INFORMAÇÕES SIMPLES EDUCACIONAIS DO USUÁRIO
                try{
                        Dados.s_conexaoBanco.executeSELECT(sqlSelect);
                        Dados.s_conexaoBanco.c_resultset.first();

                        //INFORMAÇÕES EDUCACIONAIS DO USUÁRIO
                        l_instEnsino = Dados.s_conexaoBanco.c_resultset.getString("u.instituicao_ensino");
                        l_curso = Dados.s_conexaoBanco.c_resultset.getString("u.curso");
                        l_turma = Dados.s_conexaoBanco.c_resultset.getString("u.turma");
                        l_naoGostoDeNaInst = Dados.s_conexaoBanco.c_resultset.getString("u.nao_gosto_inst");
                        l_sempreGostoDeNaInst = Dados.s_conexaoBanco.c_resultset.getString("u.sempre_gosto_inst");
                        l_vouMelhorarEmNaInst = Dados.s_conexaoBanco.c_resultset.getString("u.vou_melhorar_inst");
                        l_aInstEh = Dados.s_conexaoBanco.c_resultset.getString("u.a_instituicao_e");
                        l_estaFaltandoNaInst = Dados.s_conexaoBanco.c_resultset.getString("u.esta_faltando_inst");
                        l_nasHorasVagas = Dados.s_conexaoBanco.c_resultset.getString("u.nas_horas_vagas_inst");
                        l_melhorDisciplina = Dados.s_conexaoBanco.c_resultset.getString("u.melhor_disciplina");
                        l_piorDisciplina = Dados.s_conexaoBanco.c_resultset.getString("u.pior_disciplina");
                        l_minhaTurmaEh = Dados.s_conexaoBanco.c_resultset.getString("u.minha_turma_e");

               }
               catch(Exception e){
                    System.err.println("Erro ao tentar carregar as informações educacionais.    \nErro: "+e);
               }


                    //INFORMAÇÕES de data e hora EDUCACIONAIS DO USUÁRIO
                    try{
                        l_dataEntradaInstEnsino = Dados.s_conexaoBanco.c_resultset.getString("u.data_entrada_curso");
                    }
                    catch(Exception e){
                        l_dataEntradaInstEnsino = null;
                    }

                    try{
                        l_dataTerminoInstEnsino = Dados.s_conexaoBanco.c_resultset.getString("u.data_termino_curso");
                    }
                    catch(Exception e){
                        l_dataTerminoInstEnsino = null;
                    }

                    try{
                        l_hhMMssIniAula = Dados.s_conexaoBanco.c_resultset.getString("u.hora_ini_aula");
                        l_horaIniAula = Data.pegaHoraOuMinuto(l_hhMMssIniAula, "hora");
                        l_minutoIniAula = Data.pegaHoraOuMinuto(l_hhMMssIniAula, "minuto");

                    }catch(Exception e){

                        l_horaIniAula = 0;
                        l_minutoIniAula = 0;

                    }
                    try{
                        l_hhMMssFimAula = Dados.s_conexaoBanco.c_resultset.getString("u.hora_fim_aula");
                        l_horaFimAula = Data.pegaHoraOuMinuto(l_hhMMssFimAula, "hora");
                        l_minutoFimAula = Data.pegaHoraOuMinuto(l_hhMMssFimAula, "minuto");

                    }catch(Exception e){
                        
                        l_horaFimAula = 0;
                        l_minutoFimAula = 0;
                    }

                    try{
                        l_hhMMssIniIntervalo = Dados.s_conexaoBanco.c_resultset.getString("u.hora_ini_intervalo_aula");
                        l_horaIniIntervalo = Data.pegaHoraOuMinuto(l_hhMMssIniIntervalo, "hora");
                        l_minutoIniIntervalo = Data.pegaHoraOuMinuto(l_hhMMssIniIntervalo, "minuto");
                    }catch(Exception e){

                        l_horaIniIntervalo = 0;
                        l_minutoIniIntervalo = 0;
                    }

                    try{
                        l_hhMMssFimIntervalo = Dados.s_conexaoBanco.c_resultset.getString("u.hora_fim_intervalo_aula");
                        l_horaFimIntervalo = Data.pegaHoraOuMinuto(l_hhMMssFimIntervalo, "hora");
                        l_minutoFimIntervalo = Data.pegaHoraOuMinuto(l_hhMMssFimIntervalo, "minuto");
                    }catch(Exception e){

                        l_horaFimIntervalo = 0;
                        l_minutoFimIntervalo = 0;
                    }
                

            if(l_tela.equals("conf-usuario")){

                carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_instEnsino,"instituicao_ensino","instituicao_ensino");
                carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_curso,"curso","curso");
                carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_turma,"turma","turma");
                carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_melhorDisciplina,"disciplina","disciplina");
                carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_piorDisciplina,"disciplina","disciplina");

                Configuracoes.s_telaConfiguracoes.cb_instEnsino.setSelectedItem(l_instEnsino);

                    //tratamento de data de entrada no curso
                    if(l_dataEntradaInstEnsino == null){
                        Configuracoes.s_telaConfiguracoes.lb_dataEntradaCursoExtenso.setText(Avisos.TEXTO_EX_DD_MM_AAAA);
                    }else{
                        Configuracoes.s_telaConfiguracoes.dc_dataEntradaCurso.setDate(Data.pegaDataTipoDateDDMMAAAA(l_dataEntradaInstEnsino, Data.AAAA_MM_DD));
                        Configuracoes.s_telaConfiguracoes.lb_dataEntradaCursoExtenso.setText(Data.pegaDataPorExtenso(Data.pegaDataTipoDateDDMMAAAA(l_dataEntradaInstEnsino, Data.AAAA_MM_DD), Data.MES_ANO));
                    }

                    //tratamento de data de termino do curso
                    if(l_dataTerminoInstEnsino == null){
                        Configuracoes.s_telaConfiguracoes.lb_dataTerminoCursoExtenso.setText(Avisos.TEXTO_EX_DD_MM_AAAA);
                    }else{
                        Configuracoes.s_telaConfiguracoes.dc_dataTerminoCurso.setDate(Data.pegaDataTipoDateDDMMAAAA(l_dataTerminoInstEnsino, Data.AAAA_MM_DD));
                        Configuracoes.s_telaConfiguracoes.lb_dataTerminoCursoExtenso.setText(Data.pegaDataPorExtenso(Data.pegaDataTipoDateDDMMAAAA(l_dataTerminoInstEnsino, Data.AAAA_MM_DD), Data.MES_ANO));
                    }

                Configuracoes.s_telaConfiguracoes.cb_curso.setSelectedItem(l_curso);
                Configuracoes.s_telaConfiguracoes.cb_turma.setSelectedItem(l_turma);

                //testes de hora e minuto = 0
                    if(l_horaIniAula == 0 && l_minutoIniAula == 0 &&
                       l_horaFimAula == 0 && l_minutoFimAula == 0){

                        Configuracoes.s_telaConfiguracoes.cb_horaIniAula.setSelectedItem("*");
                        Configuracoes.s_telaConfiguracoes.cb_minIniAula.setSelectedItem("*");
                        Configuracoes.s_telaConfiguracoes.cb_horaFimAula.setSelectedItem("*");
                        Configuracoes.s_telaConfiguracoes.cb_minFimAula.setSelectedItem("*");

                    }else{
                        Configuracoes.s_telaConfiguracoes.cb_horaIniAula.setSelectedItem(String.valueOf(l_horaIniAula));
                        Configuracoes.s_telaConfiguracoes.cb_minIniAula.setSelectedItem(String.valueOf(l_minutoIniAula));
                        Configuracoes.s_telaConfiguracoes.cb_horaFimAula.setSelectedItem(String.valueOf(l_horaFimAula));
                        Configuracoes.s_telaConfiguracoes.cb_minFimAula.setSelectedItem(String.valueOf(l_minutoFimAula));
                    }

                //testes de hora e minuto = 0
                    if(l_horaIniIntervalo == 0 && l_minutoIniIntervalo == 0 &&
                       l_horaFimIntervalo == 0 && l_minutoFimIntervalo == 0){

                        Configuracoes.s_telaConfiguracoes.cb_horaIniIntervalo.setSelectedItem("*");
                        Configuracoes.s_telaConfiguracoes.cb_minIniIntervalo.setSelectedItem("*");
                        Configuracoes.s_telaConfiguracoes.cb_horaFimIntervalo.setSelectedItem("*");
                        Configuracoes.s_telaConfiguracoes.cb_minFimIntervalo.setSelectedItem("*");

                    }else{
                        Configuracoes.s_telaConfiguracoes.cb_horaIniIntervalo.setSelectedItem(String.valueOf(l_horaIniAula));
                        Configuracoes.s_telaConfiguracoes.cb_minIniIntervalo.setSelectedItem(String.valueOf(l_minutoIniAula));
                        Configuracoes.s_telaConfiguracoes.cb_horaFimIntervalo.setSelectedItem(String.valueOf(l_horaFimAula));
                        Configuracoes.s_telaConfiguracoes.cb_minFimIntervalo.setSelectedItem(String.valueOf(l_minutoFimAula));
                    }

                Configuracoes.s_telaConfiguracoes.tf_naoGostoDeEduc.setText(l_naoGostoDeNaInst);
                Configuracoes.s_telaConfiguracoes.tf_sempreGostoDeEduc.setText(l_sempreGostoDeNaInst);
                Configuracoes.s_telaConfiguracoes.tf_vouMelhorarEmEduc.setText(l_vouMelhorarEmNaInst);
                Configuracoes.s_telaConfiguracoes.tf_aInstituicaoEh.setText(l_aInstEh);
                Configuracoes.s_telaConfiguracoes.tf_estaFaltandoEduc.setText(l_estaFaltandoNaInst);
                Configuracoes.s_telaConfiguracoes.tf_nasHorasVagas.setText(l_nasHorasVagas);
                Configuracoes.s_telaConfiguracoes.cb_melhorDisciplina.setSelectedItem(l_melhorDisciplina);
                Configuracoes.s_telaConfiguracoes.cb_piorDisciplina.setSelectedItem(l_piorDisciplina);
                Configuracoes.s_telaConfiguracoes.tf_minhaTurmaEh.setText(l_minhaTurmaEh);
            }

            if(l_tela.equals("educ-amigo")){

                String l_dataEntradaCursoExtenso;
                String l_dataTerminoCursoExtenso;

                String l_horarioAula = null;
                String l_horarioIntervalo = null;


                //TRATAMENTO DE DATA DE ENTRADA NO CURSO

                if(l_dataEntradaInstEnsino == null)
                    l_dataEntradaCursoExtenso = "*";
                else
                    l_dataEntradaCursoExtenso = Data.pegaDataPorExtenso(Data.pegaDataTipoDateDDMMAAAA(l_dataEntradaInstEnsino, Data.AAAA_MM_DD), Data.MES_ANO);

                //TRATAMENTO DE DATA DE TERMINO DO CURSO
                if(l_dataTerminoInstEnsino == null)
                    l_dataTerminoCursoExtenso = "*";
                else
                    l_dataTerminoCursoExtenso = Data.pegaDataPorExtenso(Data.pegaDataTipoDateDDMMAAAA(l_dataTerminoInstEnsino, Data.AAAA_MM_DD), Data.MES_ANO);
                


                //testes de hora e minuto = 0
                    if(l_horaIniAula == 0 && l_minutoIniAula == 0 &&
                       l_horaFimAula == 0 && l_minutoFimAula == 0){

                        l_horarioAula = "*";

                    }else{
                        l_horarioAula = Avisos.TEXTO_DAS+" ["+l_horaIniAula+":"+l_minutoIniAula+"] "+Avisos.TEXTO_AS+" ["+l_horaFimAula+":"+l_minutoFimAula+"]";
                    }

                //testes de hora e minuto = 0
                    if(l_horaIniIntervalo == 0 && l_minutoIniIntervalo == 0 &&
                       l_horaFimIntervalo == 0 && l_minutoFimIntervalo == 0){

                        l_horarioIntervalo = "*";

                    }else{
                        l_horarioIntervalo = Avisos.TEXTO_DAS+" ["+l_horaIniIntervalo+":"+l_minutoIniIntervalo+"] "+Avisos.TEXTO_AS+" ["+l_horaFimIntervalo+":"+l_minutoFimIntervalo+"]";
                    }


                Moniersn.lb_instituicaoEnsino.setText(l_instEnsino);
                Moniersn.lb_dataEntradaCurso.setText(l_dataEntradaCursoExtenso);
                Moniersn.lb_dataTerminoCurso.setText(l_dataTerminoCursoExtenso);
                Moniersn.lb_curso.setText(l_curso);
                Moniersn.lb_turma.setText(l_turma);
                Moniersn.lb_horarioAula.setText(l_horarioAula);
                Moniersn.lb_horarioIntervalo.setText(l_horarioIntervalo);
                Moniersn.lb_naoGostoDeInst.setText(l_naoGostoDeNaInst);
                Moniersn.lb_sempreGostoDeInst.setText(l_sempreGostoDeNaInst);
                Moniersn.lb_vouMelhorarEmInst.setText(l_vouMelhorarEmNaInst);
                Moniersn.lb_aInstituicaoEh.setText(l_aInstEh);
                Moniersn.lb_estaFaltandoInst.setText(l_estaFaltandoNaInst);
                Moniersn.lb_nasHorasVagas.setText(l_nasHorasVagas);
                Moniersn.lb_disciplinaPreferida.setText(l_melhorDisciplina);
                Moniersn.lb_piorDisciplina.setText(l_piorDisciplina);
                Moniersn.lb_minhaTurmaEh.setText(l_minhaTurmaEh);

            }

   }

   public void carregaInfoProfissional(int l_codUsuario, String l_tela){


        //INFORMAÇÕES PROFISSIONAIS
        String l_empresa = null;
        String l_cargo = null;
        String l_setor = null;

        String l_atividade = null;
        String l_naoGostoDeNaEmp = null;
        String l_sempreGostoDeNaEmp = null;
        String l_vouMelhorarEmNaEmp = null;
        String l_aEmpresaEh = null;
        String l_estaFaltandoNaEmp = null;
        String l_emailCorp = null;
        String l_foneEmp = null;
        String l_ramalEmp = null;
        String l_faxEmp = null;
        String l_meuSetorEh = null;
        String l_dataAdmissao = null;
        String l_hhMMssIniServico = null;
        String l_hhMMssFimServico = null;
        String l_hhMMssIniRefeicaoServivo = null;
        String l_hhMMssFimRefeicaoServivo  = null;

        int l_horaIniServico = 0;
        int l_minutoIniServico = 0;

        int l_horaFimServico = 0;
        int l_minutoFimServico = 0;

        int l_horaIniRefeicaoServico = 0;
        int l_minutoIniRefeicaoServico = 0;

        int l_horaFimRefeicaoServico = 0;
        int l_minutoFimRefeicaoServico = 0;


        String sqlSelect = "SELECT u.*, c.* "+
                           "FROM usuario u, configuracoes_usuario c "+
                           "WHERE u.cod_usuario = c.cod_usuario "+
                           "AND u.cod_usuario = "+l_codUsuario;

           try{

                Dados.s_conexaoBanco.executeSELECT(sqlSelect);
                Dados.s_conexaoBanco.c_resultset.first();
           
                //INFORMAÇÕES PROFISSIONAIS
                l_empresa = Dados.s_conexaoBanco.c_resultset.getString("u.empresa");
                l_cargo = Dados.s_conexaoBanco.c_resultset.getString("u.cargo");
                l_setor = Dados.s_conexaoBanco.c_resultset.getString("u.setor");

                l_atividade = Dados.s_conexaoBanco.c_resultset.getString("u.atividade_emp");
                l_naoGostoDeNaEmp = Dados.s_conexaoBanco.c_resultset.getString("u.nao_gosto_emp");
                l_sempreGostoDeNaEmp = Dados.s_conexaoBanco.c_resultset.getString("u.sempre_gosto_emp");
                l_vouMelhorarEmNaEmp = Dados.s_conexaoBanco.c_resultset.getString("u.vou_melhorar_emp");
                l_aEmpresaEh = Dados.s_conexaoBanco.c_resultset.getString("u.a_empresa_e");
                l_estaFaltandoNaEmp = Dados.s_conexaoBanco.c_resultset.getString("u.esta_faltando_emp");
                l_emailCorp = Dados.s_conexaoBanco.c_resultset.getString("u.email_corp_emp");
                l_foneEmp = Dados.s_conexaoBanco.c_resultset.getString("u.fone_emp");
                l_ramalEmp = Dados.s_conexaoBanco.c_resultset.getString("u.ramal_emp");
                l_faxEmp = Dados.s_conexaoBanco.c_resultset.getString("u.fax_emp");
                l_meuSetorEh = Dados.s_conexaoBanco.c_resultset.getString("u.meu_setor_e");
           
           }
           catch(Exception e){
                System.err.println("Erro ao tentar carregar as informações profissionais.    \nErro: "+e);
           }

                //trata data de admissão
                try{
                    l_dataAdmissao = Dados.s_conexaoBanco.c_resultset.getString("u.data_admissao");
                }
                catch(Exception e){
                    l_dataAdmissao = null;
                }

                //trata hora de serviço e de refeição...
                try{
                    l_hhMMssIniServico = Dados.s_conexaoBanco.c_resultset.getString("u.hora_ini_emp");
                    l_horaIniServico = Data.pegaHoraOuMinuto(l_hhMMssIniServico, "hora");
                    l_minutoIniServico = Data.pegaHoraOuMinuto(l_hhMMssIniServico, "minuto");
                }catch (Exception e) {
                    l_horaIniServico = 0;
                    l_minutoIniServico = 0;

                }

                try{
                    l_hhMMssFimServico = Dados.s_conexaoBanco.c_resultset.getString("u.hora_fim_emp");
                    l_horaFimServico = Data.pegaHoraOuMinuto(l_hhMMssFimServico, "hora");
                    l_minutoFimServico = Data.pegaHoraOuMinuto(l_hhMMssFimServico, "minuto");
                }catch (Exception e) {
                    l_horaFimServico = 0;
                    l_minutoFimServico = 0;

                }

                try{
                    l_hhMMssIniRefeicaoServivo = Dados.s_conexaoBanco.c_resultset.getString("u.hora_ini_refeicao_emp");
                    l_horaIniRefeicaoServico   = Data.pegaHoraOuMinuto(l_hhMMssIniRefeicaoServivo, "hora");
                    l_minutoIniRefeicaoServico = Data.pegaHoraOuMinuto(l_hhMMssIniRefeicaoServivo, "minuto");
                }catch (Exception e){
                    l_horaIniRefeicaoServico = 0;
                    l_minutoIniRefeicaoServico = 0;

                }

                try{
                    l_hhMMssFimRefeicaoServivo = Dados.s_conexaoBanco.c_resultset.getString("u.hora_fim_refeicao_emp");
                    l_horaFimRefeicaoServico = Data.pegaHoraOuMinuto(l_hhMMssFimRefeicaoServivo, "hora");
                    l_minutoFimRefeicaoServico = Data.pegaHoraOuMinuto(l_hhMMssFimRefeicaoServivo, "minuto");
                }catch (Exception e) {
                    l_horaFimRefeicaoServico = 0;
                    l_minutoFimRefeicaoServico = 0;
                }


                if(l_tela.equals("conf-usuario")){

                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_empresa,"empresa","empresa");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_cargo,"cargo","cargo");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_setor,"setor","setor");
                    carregaItensComboBox(Configuracoes.s_telaConfiguracoes.cb_atividade,"atividade_emp","atividade_emp");

                    //TRATAMENTO DE DATA
                    if(l_dataAdmissao == null){
                        Configuracoes.s_telaConfiguracoes.lb_dataEntradaEmpExtenso.setText(Avisos.TEXTO_EX_DD_MM_AAAA);
                    }
                    else{
                        Configuracoes.s_telaConfiguracoes.dc_dataAdmissaoEmp.setDate(Data.pegaDataTipoDateDDMMAAAA(l_dataAdmissao, Data.AAAA_MM_DD));
                        Configuracoes.s_telaConfiguracoes.lb_dataEntradaEmpExtenso.setText(Data.pegaDataPorExtenso(Data.pegaDataTipoDateDDMMAAAA(l_dataAdmissao, Data.AAAA_MM_DD), Data.DIA_MES_ANO));
                    }


                    //testes de hora e minuto = 0
                        if((l_horaIniServico == 0 && l_minutoIniServico == 0) &&
                           (l_horaFimServico == 0 && l_minutoFimServico == 0)){

                            Configuracoes.s_telaConfiguracoes.cb_horaIniServico.setSelectedItem("*");
                            Configuracoes.s_telaConfiguracoes.cb_minIniServico.setSelectedItem("*");
                            Configuracoes.s_telaConfiguracoes.cb_horaFimServico.setSelectedItem("*");
                            Configuracoes.s_telaConfiguracoes.cb_minFimServico.setSelectedItem("*");

                        }else{
                            Configuracoes.s_telaConfiguracoes.cb_horaIniServico.setSelectedItem(String.valueOf(l_horaIniServico));
                            Configuracoes.s_telaConfiguracoes.cb_minIniServico.setSelectedItem(String.valueOf(l_minutoIniServico));
                            Configuracoes.s_telaConfiguracoes.cb_horaFimServico.setSelectedItem(String.valueOf(l_horaFimServico));
                            Configuracoes.s_telaConfiguracoes.cb_minFimServico.setSelectedItem(String.valueOf(l_minutoFimServico));
                        }

                    //testes de hora e minuto = 0
                        if(l_horaIniRefeicaoServico == 0 && l_minutoIniRefeicaoServico == 0 &&
                           l_horaFimRefeicaoServico == 0 && l_minutoFimRefeicaoServico == 0){

                            Configuracoes.s_telaConfiguracoes.cb_horaIniRefeicao.setSelectedItem("*");
                            Configuracoes.s_telaConfiguracoes.cb_minIniRefeicao.setSelectedItem("*");
                            Configuracoes.s_telaConfiguracoes.cb_horaFimRefeicao.setSelectedItem("*");
                            Configuracoes.s_telaConfiguracoes.cb_minFimRefeicao.setSelectedItem("*");

                        }else{
                            Configuracoes.s_telaConfiguracoes.cb_horaIniRefeicao.setSelectedItem(String.valueOf(l_horaIniRefeicaoServico));
                            Configuracoes.s_telaConfiguracoes.cb_minIniRefeicao.setSelectedItem(String.valueOf(l_minutoIniRefeicaoServico));
                            Configuracoes.s_telaConfiguracoes.cb_horaFimRefeicao.setSelectedItem(String.valueOf(l_horaFimRefeicaoServico));
                            Configuracoes.s_telaConfiguracoes.cb_minFimRefeicao.setSelectedItem(String.valueOf(l_minutoFimRefeicaoServico));
                        }


                    Configuracoes.s_telaConfiguracoes.cb_empresa.setSelectedItem(l_empresa);
                    Configuracoes.s_telaConfiguracoes.cb_cargo.setSelectedItem(l_cargo);
                    Configuracoes.s_telaConfiguracoes.cb_setor.setSelectedItem(l_setor);
                    Configuracoes.s_telaConfiguracoes.cb_atividade.setSelectedItem(l_atividade);
                    Configuracoes.s_telaConfiguracoes.tf_naoGostoDeEmp.setText(l_naoGostoDeNaEmp);
                    Configuracoes.s_telaConfiguracoes.tf_sempreGostoDeEmp.setText(l_sempreGostoDeNaEmp);
                    Configuracoes.s_telaConfiguracoes.tf_vouMelhorarEmEmp.setText(l_vouMelhorarEmNaEmp);
                    Configuracoes.s_telaConfiguracoes.tf_aEmpresaEh.setText(l_aEmpresaEh);
                    Configuracoes.s_telaConfiguracoes.tf_estaFaltandoEmp.setText(l_estaFaltandoNaEmp);
                    Configuracoes.s_telaConfiguracoes.tf_emailCorp.setText(l_emailCorp);
                    Configuracoes.s_telaConfiguracoes.tf_foneEmpresa.setText(l_foneEmp);
                    Configuracoes.s_telaConfiguracoes.tf_ramalEmpresa.setText(l_ramalEmp);
                    Configuracoes.s_telaConfiguracoes.tf_faxEmpresa.setText(l_faxEmp);
                    Configuracoes.s_telaConfiguracoes.tf_meuSetorEh.setText(l_meuSetorEh);
                }

                if(l_tela.equals("prof-amigo")){

                    String l_dataAdmissaoExtenso;

                    String l_horarioServico = null;
                    String l_horarioRefeicaoServico = null;

                    //TRATAMENTO DE DATA DE ADMISSAO
                    if(l_dataAdmissao == null)
                        l_dataAdmissaoExtenso = "*";
                    else
                        l_dataAdmissaoExtenso = Data.pegaDataPorExtenso(Data.pegaDataTipoDateDDMMAAAA(l_dataAdmissao, Data.AAAA_MM_DD), Data.DIA_MES_ANO);


                    //testes de hora e minuto = 0
                        if(l_horaIniServico == 0 && l_minutoIniServico == 0 &&
                           l_horaFimServico == 0 && l_minutoFimServico == 0){

                            l_horarioServico = "*";

                        }else{
                            l_horarioServico = Avisos.TEXTO_DAS+" ["+l_horaIniServico+":"+l_minutoIniServico+"] "+Avisos.TEXTO_AS+" ["+l_horaFimServico+":"+l_minutoFimServico+"]";
                        }

                    //testes de hora e minuto = 0
                        if(l_horaIniRefeicaoServico == 0 && l_minutoIniRefeicaoServico == 0 &&
                           l_horaFimRefeicaoServico == 0 && l_minutoFimRefeicaoServico == 0){

                            l_horarioRefeicaoServico = "*";

                        }else{
                            l_horarioRefeicaoServico = Avisos.TEXTO_DAS+" ["+l_horaIniRefeicaoServico+":"+l_minutoIniRefeicaoServico+"] "+Avisos.TEXTO_AS+" ["+l_horaFimRefeicaoServico+":"+l_minutoFimRefeicaoServico+"]";
                        }


                    Moniersn.lb_nomeEmpresa.setText(l_empresa);
                    Moniersn.lb_dataAdmissao.setText(l_dataAdmissaoExtenso);
                    Moniersn.lb_cargo.setText(l_cargo);
                    Moniersn.lb_setor.setText(l_setor);
                    Moniersn.lb_horarioServico.setText(l_horarioServico);
                    Moniersn.lb_horarioRefeicao.setText(l_horarioRefeicaoServico);
                    Moniersn.lb_atividadeEmp.setText(l_atividade);
                    Moniersn.lb_naoGostoDeEmp.setText(l_naoGostoDeNaEmp);
                    Moniersn.lb_sempreGostoDeEmp.setText(l_sempreGostoDeNaEmp);
                    Moniersn.lb_vouMelhorarEmEmp.setText(l_vouMelhorarEmNaEmp);
                    Moniersn.lb_aEmpresaEh.setText(l_aEmpresaEh);
                    Moniersn.lb_soEstaFaltandoEmp.setText(l_estaFaltandoNaEmp);
                    Moniersn.lb_emailCorpEmp.setText(l_emailCorp);
                    Moniersn.lb_foneEmp.setText(l_foneEmp);
                    Moniersn.lb_ramalEmp.setText(l_ramalEmp);
                    Moniersn.lb_faxEmp.setText(l_faxEmp);

                }

   }
   
   public void carregaConfPrivacidade(int l_codUsuario, String l_tela){

       
        String sqlSelect = "SELECT u.*, c.* "+
                           "FROM usuario u, configuracoes_usuario c "+
                           "WHERE u.cod_usuario = c.cod_usuario "+
                           "AND u.cod_usuario = "+l_codUsuario;
        try{

            Dados.s_conexaoBanco.executeSELECT(sqlSelect);

            Dados.s_conexaoBanco.c_resultset.first();

            //CONFIGURAÇÕES
            //privacidade
           String l_visualizarInfoBasicas = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_basicas");
           String l_mostrarNome = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_nome");
           String l_mostrarMinhaFrase = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_minha_frase");
           String l_mostrarSexo = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_sexo");
           String l_mostrarDataNiver = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_data_niver");
           String l_mostrarDataCadastro = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_data_cadastro");
           String l_visualizarOcorrencia = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_ocorrencia");
           String l_visualizarInfoPessoal = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_pessoal");
           String l_visualizarInfoContato = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_contato");
           String l_visualizarInfoEduc = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_educ");
           String l_visualizarInfoProf = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_info_prof");
           String l_visualizarListaAmigos = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_lista_amigos");
           String l_visualizarListaRecPub = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_lista_rec_pub");
           String l_quemBatePapoComigo = Dados.s_conexaoBanco.c_resultset.getString("c.quem_bate_papo_comigo");
           String l_quemEnviaArquivos = Dados.s_conexaoBanco.c_resultset.getString("c.quem_envia_arquivo");
           String l_solicitarSenhaAoMostrar = Dados.s_conexaoBanco.c_resultset.getString("c.solicitar_senha_ao_mostrar");
           String l_solicitarSenhAoConfig = Dados.s_conexaoBanco.c_resultset.getString("c.solicitar_senha_ao_config");
           String l_mostrarBalaoAviso = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_balao_aviso");
           String l_emitirAvisoSonoro = Dados.s_conexaoBanco.c_resultset.getString("c.emitir_aviso_sonoro");

           //pessoal
           String l_nome = Dados.s_conexaoBanco.c_resultset.getString("u.nome");
           String l_sobrenome = Dados.s_conexaoBanco.c_resultset.getString("u.sobrenome");
           String l_moniersn = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
           String l_status = Dados.s_conexaoBanco.c_resultset.getString("u.status_contato");

            //como o amigo será conhecido
            String l_amigo = null;

            //DECIDE QUAL NOME MOSTRAR (nome / apelido)
            if(l_mostrarNome.equals("S"))
                l_amigo = l_nome+" "+l_sobrenome+" ("+l_moniersn+")";
            else
                l_amigo = l_moniersn;


           //pessoal
           String l_sexo = Dados.s_conexaoBanco.c_resultset.getString("u.sexo");

               if(l_tela.equals("conf-usuario")){

                    //QUEM VÊ INFORMAÇÕES BASICAS
                    //TODOS VEEM
                    if(l_visualizarInfoBasicas.equals("todos")){

                        Configuracoes.s_telaConfiguracoes.rb_todosVeemInfoBasica.setSelected(true);
                        Configuracoes.s_telaConfiguracoes.lb_quaisInfoBasicaVisualizar.setEnabled(true);

                        Configuracoes.s_telaConfiguracoes.cb_mostrarMinhaFrase.setEnabled(true);
                        Configuracoes.s_telaConfiguracoes.cb_mostrarSexo.setEnabled(true);
                        Configuracoes.s_telaConfiguracoes.cb_mostrarDataNiver.setEnabled(true);
                        Configuracoes.s_telaConfiguracoes.cb_mostrarDataCadastro.setEnabled(true);

                        Configuracoes.s_telaConfiguracoes.lb_iconeMinhaFrase.setEnabled(true);
                        Configuracoes.s_telaConfiguracoes.lb_iconeMeuSexo.setEnabled(true);
                        Configuracoes.s_telaConfiguracoes.lb_iconeMeuNiver.setEnabled(true);
                        Configuracoes.s_telaConfiguracoes.lb_iconeDataCadastro.setEnabled(true);

                    }
                            //SÓ AMIGOS VEEM
                            else if(l_visualizarInfoBasicas.equals("amigos")){
                                    Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemInfoBasica.setSelected(true);
                                    Configuracoes.s_telaConfiguracoes.lb_quaisInfoBasicaVisualizar.setEnabled(true);

                                    Configuracoes.s_telaConfiguracoes.cb_mostrarMinhaFrase.setEnabled(true);
                                    Configuracoes.s_telaConfiguracoes.cb_mostrarSexo.setEnabled(true);
                                    Configuracoes.s_telaConfiguracoes.cb_mostrarDataNiver.setEnabled(true);
                                    Configuracoes.s_telaConfiguracoes.cb_mostrarDataCadastro.setEnabled(true);

                                    Configuracoes.s_telaConfiguracoes.lb_iconeMinhaFrase.setEnabled(true);
                                    Configuracoes.s_telaConfiguracoes.lb_iconeMeuSexo.setEnabled(true);
                                    Configuracoes.s_telaConfiguracoes.lb_iconeMeuNiver.setEnabled(true);
                                    Configuracoes.s_telaConfiguracoes.lb_iconeDataCadastro.setEnabled(true);
                            }
                                    //NIGUEM V
                                    else{
                                            Configuracoes.s_telaConfiguracoes.rb_ninguemVeInfoBasica.setSelected(true);
                                            Configuracoes.s_telaConfiguracoes.lb_quaisInfoBasicaVisualizar.setEnabled(false);

                                            Configuracoes.s_telaConfiguracoes.cb_mostrarMinhaFrase.setEnabled(false);
                                            Configuracoes.s_telaConfiguracoes.cb_mostrarSexo.setEnabled(false);
                                            Configuracoes.s_telaConfiguracoes.cb_mostrarDataNiver.setEnabled(false);
                                            Configuracoes.s_telaConfiguracoes.cb_mostrarDataCadastro.setEnabled(false);

                                            Configuracoes.s_telaConfiguracoes.lb_iconeMinhaFrase.setEnabled(false);
                                            Configuracoes.s_telaConfiguracoes.lb_iconeMeuSexo.setEnabled(false);
                                            Configuracoes.s_telaConfiguracoes.lb_iconeMeuNiver.setEnabled(false);
                                            Configuracoes.s_telaConfiguracoes.lb_iconeDataCadastro.setEnabled(false);
                                    }

                        //MOSTRAR MEU NOME
                        if(l_mostrarNome.equals("S")){
                            Configuracoes.s_telaConfiguracoes.cb_mostrarMeuNome.setSelected(true);
                            Configuracoes.s_telaConfiguracoes.lb_quemVisualizaInfoBasica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/neutro.png")));
                        }
                                else{
                                    Configuracoes.s_telaConfiguracoes.cb_mostrarMeuNome.setSelected(false);
                                    Configuracoes.s_telaConfiguracoes.lb_quemVisualizaInfoBasica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/disfarce.png")));
                                }

                        //MOSTRAR MINHA FRASE?
                        if(l_mostrarMinhaFrase.equals("S"))
                            Configuracoes.s_telaConfiguracoes.cb_mostrarMinhaFrase.setSelected(true);
                                else
                                    Configuracoes.s_telaConfiguracoes.cb_mostrarMinhaFrase.setSelected(false);

                        //DEFINIÇÃO DE ICONE DE SEXO
                        if(l_sexo.equals("M")){
                            Configuracoes.s_telaConfiguracoes.lb_iconeMeuSexo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.SEX_MASC_16)));
                            Configuracoes.lb_sexoConf.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.SEX_MASC_16)));
                        }
                        else{
                            Configuracoes.s_telaConfiguracoes.lb_iconeMeuSexo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.SEX_FEM_16)));
                            Configuracoes.lb_sexoConf.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.SEX_FEM_16)));
                        }


                        //MOSTRAR SEXO?
                        if(l_mostrarSexo.equals("S"))
                            Configuracoes.s_telaConfiguracoes.cb_mostrarSexo.setSelected(true);
                                else
                                    Configuracoes.s_telaConfiguracoes.cb_mostrarSexo.setSelected(false);

                        //MOSTRAR DATA DE ANIVERSARIO?
                        if(l_mostrarDataNiver.equals("S"))
                            Configuracoes.s_telaConfiguracoes.cb_mostrarDataNiver.setSelected(true);
                                else
                                    Configuracoes.s_telaConfiguracoes.cb_mostrarDataNiver.setSelected(false);

                        //MOSTRAR DATA DE CADASTRO?
                        if(l_mostrarDataCadastro.equals("S"))
                            Configuracoes.s_telaConfiguracoes.cb_mostrarDataCadastro.setSelected(true);
                                else
                                    Configuracoes.s_telaConfiguracoes.cb_mostrarDataCadastro.setSelected(false);


                        //QUEM VÊ OCORRÊNCIAS?
                        //TODOS
                        if(l_visualizarOcorrencia.equals("todos"))
                            Configuracoes.s_telaConfiguracoes.rb_todosVeemOcorrencia.setSelected(true);
                                //SÓ AMIGOS
                                else if(l_visualizarOcorrencia.equals("amigos"))
                                            Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemOcorrencia.setSelected(true);
                                        //NIGUEM
                                        else
                                            Configuracoes.s_telaConfiguracoes.rb_ninguemVeOcorrencia.setSelected(true);

                        //QUEM VÊ INFORMAÇOES PESSOAIS?
                        //TODOS
                        if(l_visualizarInfoPessoal.equals("todos"))
                            Configuracoes.s_telaConfiguracoes.rb_todosVeemInfoPessoal.setSelected(true);

                                //AMIGOS
                                else if(l_visualizarInfoPessoal.equals("amigos"))
                                    Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemInfoPessoal.setSelected(true);

                                        //NINGUEM
                                        else
                                            Configuracoes.s_telaConfiguracoes.rb_ninguemVeInfoPessoal.setSelected(true);


                        //QUEM VÊ INFORMAÇÕES DE CONTATO?
                        //TODOS
                        if(l_visualizarInfoContato.equals("todos"))
                            Configuracoes.s_telaConfiguracoes.rb_todosVeemInfoContato.setSelected(true);
                               //SÓ AMIGOS
                                else if(l_visualizarInfoContato.equals("amigos"))
                                        Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemInfoContato.setSelected(true);
                                        //NIGUEM
                                        else
                                            Configuracoes.s_telaConfiguracoes.rb_ninguemVeInfoContato.setSelected(true);

                        //QUEM VÊ INFORMAÇÕES EDUCACIONAIS?
                        //TODOS
                        if(l_visualizarInfoEduc.equals("todos"))
                            Configuracoes.s_telaConfiguracoes.rb_todosVeemInfoEduc.setSelected(true);
                               //SÓ AMIGOS
                                else if(l_visualizarInfoEduc.equals("amigos"))
                                        Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemInfoEduc.setSelected(true);
                                        //NIGUEM
                                        else
                                            Configuracoes.s_telaConfiguracoes.rb_ninguemVeInfoEduc.setSelected(true);

                        //QUEM VÊ INFORMAÇÕES PROFISSIONAIS?
                        //TODOS
                        if(l_visualizarInfoProf.equals("todos"))
                            Configuracoes.s_telaConfiguracoes.rb_todosVeemInfoProf.setSelected(true);
                               //SÓ AMIGOS
                                else if(l_visualizarInfoProf.equals("amigos"))
                                        Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemInfoProf.setSelected(true);
                                        //NIGUEM
                                        else
                                            Configuracoes.s_telaConfiguracoes.rb_ninguemVeInfoProf.setSelected(true);
                        //QUEM VÊ MINHA LISTA DE AMIGOS?
                        //TODOS
                        if(l_visualizarListaAmigos.equals("todos")){
                            Configuracoes.s_telaConfiguracoes.rb_todosVeemListaAmigos.setSelected(true);
                        }
                                //SÓ AMIGOS
                                else if(l_visualizarListaAmigos.equals("amigos")){
                                        Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemListaAmigos.setSelected(true);
                                }
                                        //NIGUEM
                                        else{
                                              Configuracoes.s_telaConfiguracoes.rb_ninguemVeListaAmigos.setSelected(true);
                                        }


                        //QUEM V RECADOS PUBLICOS?
                        if(l_visualizarListaRecPub.equals("todos"))
                            Configuracoes.s_telaConfiguracoes.rb_todosVeemRecPub.setSelected(true);
                                else if (l_visualizarListaRecPub.equals("amigos"))
                                    Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemRecPub.setSelected(true);
                                        else
                                            Configuracoes.s_telaConfiguracoes.rb_ninguemVeRecPub.setSelected(true);

                        //QUEM PODERÁ BATER PAPO
                        if(l_quemBatePapoComigo.equals("todos"))
                            Configuracoes.s_telaConfiguracoes.rb_todosBatemPapoComigo.setSelected(true);
                                else if(l_quemBatePapoComigo.equals("amigos"))
                                    Configuracoes.s_telaConfiguracoes.rb_soAmigosBatemPapoComigo.setSelected(true);
                                        else
                                            Configuracoes.s_telaConfiguracoes.rb_ninguemBatePapoComigo.setSelected(true);

                        //QUEM PODERÁ ENVIAR ARQUIVOS
                        if(l_quemEnviaArquivos.equals("todos"))
                            Configuracoes.s_telaConfiguracoes.rb_todosEnviamArquivos.setSelected(true);
                                else if(l_quemEnviaArquivos.equals("amigos"))
                                    Configuracoes.s_telaConfiguracoes.rb_soAmigosEnviamArquivos.setSelected(true);
                                        else
                                            Configuracoes.s_telaConfiguracoes.rb_ninguemEnviaArquivos.setSelected(true);


                        //MOSTRAR AVISO DE CONEXAO / DESCONEXAO DO AMIGO
                        if(l_mostrarBalaoAviso.equals("S") && l_emitirAvisoSonoro.equals("S")){
                            Configuracoes.s_telaConfiguracoes.cb_mostraBalaoAviso.setSelected(true);
                            Configuracoes.s_telaConfiguracoes.cb_emiteAvisoSonoro.setSelected(true);
                            Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.MOSTRA_AVISO_EMITE_AUDIO))); // NOI18N
                        }
                                else if(l_mostrarBalaoAviso.equals("S") && l_emitirAvisoSonoro.equals("N")){
                                    Configuracoes.s_telaConfiguracoes.cb_mostraBalaoAviso.setSelected(true);
                                    Configuracoes.s_telaConfiguracoes.cb_emiteAvisoSonoro.setSelected(false);
                                    Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.MOSTRA_AVISO_N_EMITE_AUDIO))); // NOI18N
                                }
                                    else if(l_mostrarBalaoAviso.equals("N") && l_emitirAvisoSonoro.equals("S")){
                                        Configuracoes.s_telaConfiguracoes.cb_mostraBalaoAviso.setSelected(false);
                                        Configuracoes.s_telaConfiguracoes.cb_emiteAvisoSonoro.setSelected(true);
                                        Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.N_MOSTRA_AVISO_EMITE_AUDIO))); // NOI18N
                                    }
                                        else{
                                            Configuracoes.s_telaConfiguracoes.cb_mostraBalaoAviso.setSelected(false);
                                            Configuracoes.s_telaConfiguracoes.cb_emiteAvisoSonoro.setSelected(false);
                                            Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.N_MOSTRA_AVISO_N_EMITE_AUDIO))); // NOI18N
                                        }
                        
                        //SOLICITAR SENHA AO MOSTRAR DA BANDEJA
                        if(l_solicitarSenhaAoMostrar.equals("S")){
                            Configuracoes.s_telaConfiguracoes.cb_solicitarSenhaAoMostrar.setSelected(true);
                            Configuracoes.s_telaConfiguracoes.lb_habilitarSegSystray.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.FECHADO_SYSTRAY))); // NOI18N

                        }
                                else{
                                    Configuracoes.s_telaConfiguracoes.cb_solicitarSenhaAoMostrar.setSelected(false);
                                    Configuracoes.s_telaConfiguracoes.lb_habilitarSegSystray.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LIBERADO_SYSTRAY))); // NOI18N
                                }

                        //SOLICITAR SENHA AO CONFIGURAR
                        if(l_solicitarSenhAoConfig.equals("S")){
                            Configuracoes.s_telaConfiguracoes.cb_solicitarSenhaAoConfig.setSelected(true);
                            Configuracoes.s_telaConfiguracoes.lb_habilitarSegConf.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.FECHADO_CONF))); // NOI18N
                        }
                                else{
                                    Configuracoes.s_telaConfiguracoes.cb_solicitarSenhaAoConfig.setSelected(false);
                                    Configuracoes.s_telaConfiguracoes.lb_habilitarSegConf.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LIBERADO_CONF))); // NOI18N
                               }
             }//fim conf-privacidade

            if(l_tela.equals("painel-amigo")){

                /*
                 tipos de amigos possíveis
                 *
                 * MOSTRA ABAS E BOTOES DE ACORDO COM A CONF. PRIVACIDADE DO AMIGO

                 * - aceito-ok -
                 * - aceito-bloqueei
                 * - aceito-bloqueou-me
                 * - aceito-bloqueados
                 * - nao-aceito-convidou-me
                 * - nao-aceito-convidei
                 * - nao-amigo
                 */

                   if(!LogAmigo.s_tipoAmigo.equals("aceito-bloqueei") &&
                      !LogAmigo.s_tipoAmigo.equals("aceito-bloqueou-me") &&
                      !LogAmigo.s_tipoAmigo.equals("aceito-bloqueados")){


                        LogAmigo.s_qtdUsuMostrar = 4;
                        Moniersn.tf_pesquisaListaAmigo.setText(""); 

                        LogAmigo.s_qtdOcoAMostrar = 4;
                        Moniersn.tf_conteudoOcoPesquisadoAmigo.setText(""); 

                        LogAmigo.s_qtdAtuAMostrar = 4;
                        Moniersn.tf_conteudoAtuPesquisadoAmigo.setText(""); 

                        LogAmigo.s_qtdRecPubAMostrar = 4;
                        Moniersn.tf_conteudoRecPubPesquisadoAmigo.setText("");         

                        LogAmigo.s_qtdArqAMostrar = 4;
                        Moniersn.tf_nomeArqPesquisadoAmigo.setText("");    
                       
                        //inabilitação de botao de comentário de ocorrencia
                        Moniersn.s_telaMsn.bt_comentarOcorrenciaAmigo.setEnabled(false);

                        //inabilitação de botao de comentário de atualização
                        Moniersn.s_telaMsn.bt_comentarAtuAmigo.setEnabled(false);

                        //inabilitação de botao de comentário de rec público
                        Moniersn.s_telaMsn.bt_comentarRecPubAmigo.setEnabled(false);

                        //inabilitação de botao de comentário de arquivo
                        Moniersn.s_telaMsn.bt_comentarArquivoSelecionadoAmigo.setEnabled(false);

                        //inabilitação de botao de baixar arquivo selecionado do amigo
                        Moniersn.s_telaMsn.bt_baixarArquivoSelecionadoAmigo.setEnabled(false);


                       //ENVIO DE ARQUIVOS
                       if(l_quemEnviaArquivos.equals("todos") ||
                           (l_quemEnviaArquivos.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){
                                 //dexia o botão de bate papo visível e com tooltiptext
                                 Moniersn.s_telaMsn.bt_enviarArquivo.setToolTipText(Avisos.TIP_TEXT_COMPARTILHE_ARQ+l_amigo);
                                 Moniersn.s_telaMsn.bt_enviarArquivo.setVisible(true);
                       }
                             //ninguem envia arquivos para o amigo
                             else
                                Moniersn.s_telaMsn.bt_enviarArquivo.setVisible(false);


                        //OCORRÊNCIAS
                        //TODOS
                        if(l_visualizarOcorrencia.equals("todos") ||
                          (l_visualizarOcorrencia.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){

                                LogAmigo.s_qtdTotOcoEnviadas = SolicitarDados.s_SolicitarDados.defineTotalDeOcorrencias(l_codUsuario, "envAmigo", "geral");

                                //preencher lista de ocorrências do amigo
                                preencherListaDeOcorrencias(l_codUsuario, Moniersn.jt_listaOcorrenciaAmigo, "");

                                //ultima ocorrência
                                setaUltimaOcorrencia(l_codUsuario, "amigo");
                        }
                             //ninguem v ocorrÊncias
                             else{
                                  Moniersn.lb_ultOcorrenciaAmigo.setVisible(false);
                                  Moniersn.lb_qtdComentarioUltOcoAmigo.setVisible(false);
                                  Moniersn.jtp_painelTabuladoAmigo.remove(Moniersn.jp_ocorrenciaAmigo);
                             }

                        //ATUALIZACOES
                        //TODOS
//                        if((l_visualizarInfoBasicas.equals("todos") || (l_visualizarInfoBasicas.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))) ||
//                           (l_visualizarInfoPessoal.equals("todos") || (l_visualizarInfoPessoal.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))) ||
//                           (l_visualizarInfoContato.equals("todos") || (l_visualizarInfoContato.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))) ||
//                           (l_visualizarInfoEduc.equals("todos")    || (l_visualizarInfoEduc.equals("amigos")    && LogAmigo.s_tipoAmigo.equals("aceito-ok"))) ||
//                           (l_visualizarInfoProf.equals("todos")    || (l_visualizarInfoProf.equals("amigos")    && LogAmigo.s_tipoAmigo.equals("aceito-ok"))) ||
//                           (l_visualizarListaAmigos.equals("todos") || (l_visualizarListaAmigos.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok")))){

                            
                                String l_icone = null;
                                //ADD GUIA DE ATU
                                l_icone = Icones.ADD_ATU_VISITADO_16;


                                //conta atu enviadasAtualizacoes(l_codUsuario, "env", "geral");d
                                LogAmigo.s_qtdTotAtuEnviadas = SolicitarDados.s_SolicitarDados.defineTotalDeAtualizacoes(l_codUsuario, "", "geral");
                                                                
                                //ADD GUIA DE ATU
                                adicionaPainelDeInformacoes("("+LogAmigo.s_qtdTotAtuEnviadas+")", Moniersn.jp_atualizacoesAmigo, l_icone);
                                
                                //PREENCHE LISTA DE ATU DO AMIGO
                                preencherListaAtualizacoes(l_codUsuario, Moniersn.jt_listaAtualizacoesAmigo, "");
//                        }
//                           else
//                              Moniersn.jtp_painelTabuladoAmigo.remove(Moniersn.jp_atualizacoesAmigo);

                        
                        //RECADOS PÚBLICOS
                        if((l_visualizarListaRecPub.equals("todos") &&  LogUsuario.s_quemVeRecPub.equals("todos")) ||
                           (l_visualizarListaRecPub.equals("todos") &&  LogUsuario.s_quemVeRecPub.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok")) ||
                           (l_visualizarListaRecPub.equals("amigos") && LogUsuario.s_quemVeRecPub.equals("todos") && LogAmigo.s_tipoAmigo.equals("aceito-ok")) ||
                           (l_visualizarListaRecPub.equals("amigos") && LogUsuario.s_quemVeRecPub.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){

                                LogAmigo.s_qtdTotRecPubEnviados = SolicitarDados.s_SolicitarDados.defineTotalDeRecPubEnvAmigo(l_codUsuario);
                            
                                //preecher lista de recados públicos do amigo
                                preencherListaRecadosPubAmigo(l_codUsuario);

                                //adiciona guia de recados públicos
                                adicionaPainelDeInformacoes("("+LogAmigo.s_qtdTotRecPubEnviados+")", Moniersn.jp_recPublicosAmigo, Icones.REC_PUB_REC_16);
                        }
//                            //ninguem v recados públicos
                            else{
                                Moniersn.jtp_painelTabuladoAmigo.remove(Moniersn.jp_recPublicosAmigo);
                            }

                        
                        //ARQUIVOS
                        //TODOS ou AMIGOS
//                        if(LogUsuario.s_quemMeEnviaArquivo.equals("todos") ||
//                          (LogUsuario.s_quemMeEnviaArquivo.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){

                                LogAmigo.s_qtdTotArqEnviados = SolicitarDados.s_SolicitarDados.defineTotalDeArqEnvAmigo(l_codUsuario);                                   

                                //ADD GUIA DE ARQUIVOS
                                adicionaPainelDeInformacoes("("+LogAmigo.s_qtdTotArqEnviados+")", Moniersn.jp_arquivosAmigo, Icones.ARQUIVO_ADD);

                                //PREENCHE LISTA DE ATU DO AMIGO
                                preencherListaDeArquivos(l_codUsuario, Moniersn.jt_listaArquivosAmigo, "");
//                        }
//                           else
//                              Moniersn.jtp_painelTabuladoAmigo.remove(Moniersn.jp_arquivosAmigo);
                        
                        
                        //PARA AMIGOS CONECTADOS...
                        if(l_status.equals("conectado")){

                                //BATE PAPO
                                if(LogUsuario.s_quemBatePapoComigo.equals("todos") ||
                                  (LogUsuario.s_quemBatePapoComigo.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){

                                         if(l_quemBatePapoComigo.equals("todos") ||
                                           (l_quemBatePapoComigo.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){
                                                 //dexia o botão de bate papo visível e com tooltiptext
                                                 Moniersn.s_telaMsn.bt_batePapo.setToolTipText(Avisos.TIP_TEXT_CONVERSE_AGORA_BP+l_amigo);
                                                 Moniersn.s_telaMsn.bt_batePapo.setVisible(true);
                                         }
                                                //ninguem bate papo com o amigo
                                                else
                                                     Moniersn.s_telaMsn.bt_batePapo.setVisible(false);
                                }
                                    //ninguem bate papo com o usuário
                                    else
                                        Moniersn.s_telaMsn.bt_batePapo.setVisible(false);


                                //COMPRIMENTO DE AMIGO
                                //tipText do bt de comprimento de amigo
                                Moniersn.s_telaMsn.bt_comprimento.setVisible(true);
                                Moniersn.s_telaMsn.bt_comprimento.setToolTipText(Avisos.TIP_TEXT_CHAMAR_ATENCAO+" "+l_amigo+" "+Avisos.TEXTO_AGORA+"!");


                        }//fim de DEFINIÇÕES PARA AMIGOS CONECTADOS

                                //caso amigo esteja DESCONECTADO...
                                else{

                                    //BATE PAPO
                                    //Não pode bater papo quando desconectado
                                    Moniersn.s_telaMsn.bt_batePapo.setVisible(false);

                                    //COMPRIMENTO DE AMIGO
                                    //bt de chamar atenção fica invisível quando desconectado
                                    Moniersn.s_telaMsn.bt_comprimento.setVisible(false);

                                }//fim de DEFINIÇ~EOS PARA AMIGOS DESCONECTADOS
                        
                        
                        

                        //INFORMAÇÕES PESSOAIS
                        //TODOS (amigos, convidados, novatos) vêm minhas informações pessoais
                        if(l_visualizarInfoPessoal.equals("todos") ||
                          (l_visualizarInfoPessoal.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){
                                //preencher informaçoes pessoais
                                carregaInfoPessoal(l_codUsuario, "pessoal-amigo");

                                //add guia de informaçoes pessoais
                                adicionaPainelDeInformacoes("", Moniersn.jp_pessoal, Icones.INFO_PESSOAL_16);
                       }
                            //ninguem v o minhas informações pessoais
                            else{
                                  Moniersn.jtp_painelTabuladoAmigo.remove(Moniersn.jp_pessoal);
                            }

                        //INFORMAÇÕES DE CONTATO
                        //TODOS (amigos, convidados, novatos) vêm minhas informações de contato
                        if(l_visualizarInfoContato.equals("todos") ||
                          (l_visualizarInfoContato.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){

                                //preencher informações de contato
                                carregaInfoContato(l_codUsuario, "contato-amigo");

                                //add guia de informações de contato
                                adicionaPainelDeInformacoes("", Moniersn.jp_contato, Icones.INFO_CONTATO_16);

                        }
                            //ninguem v informações de contato
                            else{
                                  Moniersn.jtp_painelTabuladoAmigo.remove(Moniersn.jp_contato);
                            }

                        //INFORMAÇÕES EDUCACIONAIS
                        //TODOS (amigos, convidados, novatos) vêm minhas informações de contato
                        if(l_visualizarInfoEduc.equals("todos") ||
                          (l_visualizarInfoEduc.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){

                                //preecher informações educacionais
                                carregaInfoEducacional(l_codUsuario, "educ-amigo");

                                //add guia de informações educacionais
                                adicionaPainelDeInformacoes("", Moniersn.jp_educacional, Icones.INFO_EDUC_16);

                       }
                            //ninguem v informações de contato
                            else{
                                  Moniersn.jtp_painelTabuladoAmigo.remove(Moniersn.jp_educacional);
                            }

                        //INFORMAÇÕES PROFISSIONAIS
                        //TODOS (amigos, convidados, novatos) vêm minhas informações de contato
                        if(l_visualizarInfoProf.equals("todos") ||
                          (l_visualizarInfoProf.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){

                                //preencher informações profissionais
                                carregaInfoProfissional(l_codUsuario, "prof-amigo");

                                //adiciona guia de informações profissionais
                                adicionaPainelDeInformacoes("", Moniersn.jp_profissional, Icones.INFO_PROF_16);
                        }
                            //ninguem v informações profissionais
                            else{
                                  Moniersn.jtp_painelTabuladoAmigo.remove(Moniersn.jp_profissional);
                            }

                        //LISTA DE AMIGOS
                        if(l_visualizarListaAmigos.equals("todos") ||
                          (l_visualizarListaAmigos.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok"))){

                            //contagem de amigos (todos/comuns)
                            SolicitarDados.s_SolicitarDados.contaAmigosAmigos(l_codUsuario);

                            //preenche a lista dde amigos dos meus amigos
                            preencherListaAmigosDoAmigo(l_codUsuario, LogAmigo.s_listaAmigoSelecionada);

                            Moniersn.sp_listaAmigosAmigo.setVisible(true);
                            Moniersn.jp_opcoesAmigosAmigo.setVisible(true);
                            Moniersn.lb_qtdUsuariosListaAmigo.setVisible(true);
                            Moniersn.tf_pesquisaListaAmigo.setVisible(true);
                            Moniersn.bt_verMaisUsuariosListaAmigo.setVisible(true);
                            Moniersn.bt_atuUsuariosListaAmigo.setVisible(true);      
                            
                            
                        }
                            //ninguem v minha lista de amigos
                            else{

                                Moniersn.sp_listaAmigosAmigo.setVisible(false);
                                Moniersn.jp_opcoesAmigosAmigo.setVisible(false);
                                Moniersn.lb_qtdUsuariosListaAmigo.setVisible(false);
                                Moniersn.tf_pesquisaListaAmigo.setVisible(false);
                                Moniersn.bt_verMaisUsuariosListaAmigo.setVisible(false);
                                Moniersn.bt_atuUsuariosListaAmigo.setVisible(false);
                                        
                                
                                
                            }


                        
                        //PARA SOLICITANTES DE AMIZADE
                        if(LogAmigo.s_tipoAmigo.equals("nao-aceito-convidou-me")){

                            //BLOQUEIO DE AMIGO
                            //deixa invisível o botão de "bloqueio de amigo"
                            Moniersn.bt_bloqDesblocAmigo.setVisible(false);

                            //COMPRIMENTO DE AMIGO
                            //deixa invisível o botão de chamar atenção
                            Moniersn.s_telaMsn.bt_comprimento.setVisible(false);

                            //MOSTRA O BOTÃO DE ACEITAR AMIGO
                            //ícone e tooltiptext de add/remover/aguardar amigo
                            Moniersn.bt_addExcAceEspAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_NOVO))); // NOI18N
                            Moniersn.bt_addExcAceEspAmigo.setToolTipText(Avisos.TIP_TEXT_ACEITAR_COMO_AMIGO+l_amigo+"...");
                            Moniersn.bt_addExcAceEspAmigo.setVisible(true);


                       }
                        //PARA CONVIDADOS
                       else if(LogAmigo.s_tipoAmigo.equals("nao-aceito-convidei")){


                            //BLOQUEIO DE AMIGO
                            //deixa invisível o botão de "bloqueio de amigo"
                            Moniersn.bt_bloqDesblocAmigo.setVisible(false);

                            //COMPRIMENTO DE AMIGO
                            //deixa invisível o botão de chamar atenção
                            Moniersn.s_telaMsn.bt_comprimento.setVisible(false);

                            //MOSTRA O BOTÃO DE ACEITAR AMIGO
                            //ícone e tooltiptext de add/remover/aguardar amigo
                            Moniersn.bt_addExcAceEspAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_ADD_CANCELAR_16))); // NOI18N
                            Moniersn.bt_addExcAceEspAmigo.setToolTipText(Avisos.TIP_TEXT_CANCELAR_SOLIC_ENV+l_amigo+"...");
                            Moniersn.bt_addExcAceEspAmigo.setVisible(true);

                       }
                        //PARA DESCONHECIDO GERAL
                       else if(LogAmigo.s_tipoAmigo.equals("nao-amigo")){


                            //BLOQUEIO DE AMIGO
                            //deixa invisível o botão de "bloqueio de amigo"
                            Moniersn.bt_bloqDesblocAmigo.setVisible(false);

                            //COMPRIMENTO DE AMIGO
                            //deixa invisível o botão de chamar atenção
                            Moniersn.s_telaMsn.bt_comprimento.setVisible(false);

                            //MOSTRA O BOTÃO DE ACEITAR AMIGO
                            //ícone e tooltiptext de add/remover/aguardar amigo
                            Moniersn.bt_addExcAceEspAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_ADD_16))); // NOI18N
                            Moniersn.bt_addExcAceEspAmigo.setToolTipText(Avisos.TIP_TEXT_ENV_SOLICITACAO+l_amigo+"...");
                            Moniersn.bt_addExcAceEspAmigo.setVisible(true);

                       }
                            //PARA AMIGOS JÁ CONFIRMADOS
                            else{

                                //BLOQUEAR AMIGO
                                Moniersn.bt_bloqDesblocAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_BLOQUEIO_16))); // NOI18N
                                Moniersn.bt_bloqDesblocAmigo.setToolTipText(Avisos.TIP_TEXT_BLOQUEAR_AMIGO+l_amigo+"...");
                                Moniersn.bt_bloqDesblocAmigo.setVisible(true);

                                //REMOVER AMIGO
                                Moniersn.bt_addExcAceEspAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGO_REMOCAO_16))); // NOI18N
                                Moniersn.bt_addExcAceEspAmigo.setToolTipText(Avisos.TIP_TEXT_REMOVER_AMIGO+l_amigo+"...");
                                Moniersn.bt_addExcAceEspAmigo.setVisible(true);
                            }
                    }//FIM if(Moniersn.s_amigoBloqueado == false && Moniersn.s_amigoMeBloqueou == false && Moniersn.s_solicitanteDeAmizade == false)
            }//FIM painel-amigo
        }
        catch(Exception e){
                System.err.println("Erro ao tentar definir as configurações de privacidade do amigo.      \nErro: "+e);
        }
    }

    public void carregaItensComboBox(JComboBox l_combo, String l_tabela, String l_atributo){

      try {

         String l_sqlSelect = "SELECT "+l_atributo+
                              " FROM "+l_tabela+
                              " ORDER BY "+l_atributo;

        //caso recado publico recebido...
        Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

        l_combo.removeAllItems();
        l_combo.addItem("*");

           if(Dados.s_conexaoBanco.c_resultset.first()){
                do{
                    l_combo.addItem(Dados.s_conexaoBanco.c_resultset.getString(l_atributo));
                }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto tiver dados preenche o jtable com os valores
           }
     }
     catch (Exception e) {
            System.err.println("Erro ao tentar preencher a lista de ítens do combobox!     \nErro: "+e);
            return;
     }

     }//FIM PREENCHER LISTA

        public void carregalistaAmigosEnvArquivo(){

          String l_sqlSelect = null;
          String l_apelido = null;
               
              //todos
              //q não seja bloqueado por mim, q nao tenham me bloqueado e desconhecido q so amigos dele enviam arquivos pra ele.
              l_sqlSelect = "SELECT u.moniersn "
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
                         + "AND c.quem_envia_arquivo != 'ninguem' "
                         + "AND u.cod_usuario != "+LogUsuario.s_cod;
              
              
              
              

         try {
            //executa query
            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

               if(Dados.s_conexaoBanco.c_resultset.first()){
                   //remove todos os itens
                   CompartilhaArquivo.cb_listaAmigos.removeAllItems();

                    do{
                        l_apelido = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");

                        CompartilhaArquivo.cb_listaAmigos.addItem(l_apelido);

                    }while(Dados.s_conexaoBanco.c_resultset.next());


             }
         }
         catch (Exception e) {

                CompartilhaArquivo.s_telaCompArquivo.dispose();
                mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                System.err.println("Erro ao tentar preencher a lista de amigos para enviar arquivo!     \nErro: "+e);
                return;
         }

     }//FIM PREENCHER LISTA

         //imagem de humor
    public void iconeHumor(String l_iconeHumor, JLabel l_label){
        l_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+l_iconeHumor+".png")));
    }

    //preenche a lista de amigos do usuário
    public void preencherListaAmigosDoUsuario(int l_codUsuario, String l_listaAmigos){
        //listaAmigos: todos, online, convidados, convites;

        String l_sqlSelect = null;
        String l_apelidoPesquisado = Moniersn.tf_pesquisaUsuario.getText().toLowerCase();
               l_apelidoPesquisado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_apelidoPesquisado);

        String l_apelidoAmigo = null;
        String l_humor = null;
        String l_apelidoHumor = null;
        String l_tipText = null;
        String l_Header = null;
        String l_codAmigo = null;
        String l_statusContato = null;
        String l_codStatus = null;
        String l_codigoUsuarioStatus = null;
        int l_qtdUsuarioLista = 0;
        int l_qtdTotalUsuarios = 0;
        String l_iconeUsuario = null;

        LogUsuario.s_listaAmigosAtual = l_listaAmigos;

        //seta o nome da coluna referente a visuslização (todos amigos, amigos online, amigos convidados)
        Moniersn.jt_listaAmigosUsuario.getColumnModel().getColumn(0).setHeaderValue(l_listaAmigos);
        //repinta o modelo do hearder
        Moniersn.jt_listaAmigosUsuario.getTableHeader().resizeAndRepaint();

        //ajusta a largura das colunas 0 e 1
        Moniersn.jt_listaAmigosUsuario.getColumnModel().getColumn(0).setPreferredWidth(110);
        Moniersn.jt_listaAmigosUsuario.getColumnModel().getColumn(1).setPreferredWidth(30);
//        Moniersn.jt_listaAmigosUsuario.getColumnModel().getColumn(2).setPreferredWidth(30);

        //seta o renderer na coluna 0... (icone de status)
        Moniersn.jt_listaAmigosUsuario.getColumnModel().getColumn(0).setCellRenderer(new RendererPintorJTable());
        //seta o renderer na coluna 1... (icone de status)
        Moniersn.jt_listaAmigosUsuario.getColumnModel().getColumn(1).setCellRenderer(new RendererPintorJTable());
        //seta o renderer na coluna 1... (icone de status)
//        Moniersn.jt_listaAmigosUsuario.getColumnModel().getColumn(2).setCellRenderer(new RendererPintorJTable());

        //pega o model (dados) da minha tabela lista de amigos
        DefaultTableModel modelo = (DefaultTableModel) Moniersn.jt_listaAmigosUsuario.getModel();
        //toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados
        modelo.setNumRows(0);

            //TODOS CADASTRADOS EM GERAL(SEM EXCESSOES)
            if(l_listaAmigos.equals("Todos(geral)")){

                l_tipText = Avisos.TIP_TEXT_LISTA_USU;
                l_qtdTotalUsuarios = LogUsuario.s_qtdTotUsu - 1;//(MENOS EU)
                l_iconeUsuario = Icones.TODOS_GERAL_16;

                l_sqlSelect = "SELECT u.moniersn, u.icone_humor, u.cod_usuario, u.status_contato "
                            + "FROM usuario u "
                            + "WHERE u.cod_usuario != "+l_codUsuario+" "
                            + "AND u.ativo = 'S' "
                            + "AND u.moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                            + "ORDER BY u.status_contato ASC, u.moniersn ASC "
                            + "LIMIT 0, "+LogUsuario.s_qtdUsuMostrar;      
            }
       
            //TODOS CONECTADOS
            if(l_listaAmigos.equals("Todos(conectados)")){

                l_tipText = Avisos.TIP_TEXT_LISTA_USU_CON;
                l_qtdTotalUsuarios = LogUsuario.s_qtdTotUsuConect;
                l_iconeUsuario = Icones.TODOS_CONECTADOS_16;

                l_sqlSelect = "SELECT u.moniersn, u.icone_humor, u.cod_usuario, u.status_contato "
                            + "FROM usuario u "
                            + "WHERE u.status_contato = 'conectado' "
                            + "AND u.ativo = 'S' "
                            + "AND u.cod_usuario != "+l_codUsuario+" "
                            + "AND u.moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                            + "ORDER BY u.status_contato ASC, u.moniersn ASC "
                            + "LIMIT 0, "+LogUsuario.s_qtdUsuMostrar;  
            }     

            //TODOS CONVIDADOS
            if(l_listaAmigos.equals("Todos(convidados)")){

                l_tipText = Avisos.TIP_TEXT_LISTA_USU_CONVIDADOS;
                l_qtdTotalUsuarios = LogUsuario.s_qtdTotConv;
                l_iconeUsuario = Icones.TODOS_CONVIDADOS_16;

                l_sqlSelect = "SELECT u.moniersn, u.icone_humor, u.cod_usuario, u.status_contato, c.* "
                            + "FROM usuario u, contato_de_usuario c "
                            + "WHERE u.cod_usuario = c.cod_amigo "
                            + "AND u.ativo = 'S' "
                            + "AND c.aceito = 'N' "
                            + "AND c.cod_usuario = "+l_codUsuario+" "
                            + "AND u.moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                            + "ORDER BY u.status_contato ASC, u.moniersn ASC "
                            + "LIMIT 0, "+LogUsuario.s_qtdUsuMostrar;      
            }

            //TODOS CONVITES RECEBIDOS
            if(l_listaAmigos.equals("Todos(convites)")){

                l_tipText = Avisos.TIP_TEXT_LISTA_USU_CONVITES;
                l_qtdTotalUsuarios = LogUsuario.s_qtdTotConvRec;
                l_iconeUsuario = Icones.TODOS_CONVITES_16;

                l_sqlSelect = "SELECT u.moniersn, u.icone_humor, u.cod_usuario, u.status_contato, c.* "
                            + "FROM usuario u, contato_de_usuario c "
                            + "WHERE u.cod_usuario = c.cod_usuario "
                            + "AND u.ativo = 'S' "
                            + "AND c.aceito = 'N' "
                            + "AND c.cod_amigo = "+l_codUsuario+" "
                            + "AND u.moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                            + "ORDER BY u.status_contato ASC, u.moniersn ASC "
                            + "LIMIT 0, "+LogUsuario.s_qtdUsuMostrar;                      
            }  
        
            //TODOS SOMENTE OS AMIGOS
            if(l_listaAmigos.equals("Amigos(geral)")){

                l_tipText = Avisos.TIP_TEXT_LISTA_AMIGOS;
                l_qtdTotalUsuarios = LogUsuario.s_qtdTotAmi;
                l_iconeUsuario = Icones.AMIGOS_GERAL_16;

                l_sqlSelect = "SELECT u.moniersn, u.icone_humor, u.cod_usuario, u.status_contato, c.* "
                            + "FROM usuario u, contato_de_usuario c "
                            + "WHERE u.cod_usuario = c.cod_amigo "
                            + "AND u.ativo = 'S' "
                            + "AND c.aceito = 'S' "
                            + "AND c.bloqueado = 'N' "
                            + "AND c.cod_usuario = "+l_codUsuario+" "
                            + "AND u.moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                            + "ORDER BY u.status_contato ASC, u.moniersn ASC "
                            + "LIMIT 0, "+LogUsuario.s_qtdUsuMostrar;                       
            }

            //TODOS SOMENTE OS AMIGOS CONECTADOS
            if(l_listaAmigos.equals("Amigos(conectados)")){

                l_tipText = Avisos.TIP_TEXT_LISTA_AMIGOS_CON;
                l_qtdTotalUsuarios = LogUsuario.s_qtdTotAmiConect;
                l_iconeUsuario = Icones.AMIGOS_CONECTADOS_16;

                l_sqlSelect = "SELECT u.moniersn, u.icone_humor, u.cod_usuario, u.status_contato, c.* "
                            + "FROM usuario u, contato_de_usuario c "
                            + "WHERE u.cod_usuario = c.cod_amigo "
                            + "AND u.ativo = 'S' "
                            + "AND u.status_contato = 'conectado' "
                            + "AND c.aceito = 'S' "
                            + "AND c.bloqueado = 'N' "
                            + "AND c.cod_usuario = "+l_codUsuario+" "
                            + "AND u.moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                            + "ORDER BY u.status_contato ASC, u.moniersn ASC "
                            + "LIMIT 0, "+LogUsuario.s_qtdUsuMostrar;    
                
            }

            //TODOS SOMENTE OS AMIGOS BLOQUEADOS
            if(l_listaAmigos.equals("Amigos(bloqueados)")){

                l_tipText = Avisos.TIP_TEXT_LISTA_AMIGOS_BLOQ;
                l_qtdTotalUsuarios = LogUsuario.s_qtdTotAmiBloq;
                l_iconeUsuario = Icones.AMIGOS_BLOQUEADOS_16;

                l_sqlSelect = "SELECT u.moniersn, u.icone_humor, u.cod_usuario, u.status_contato, c.* "
                            + "FROM usuario u, contato_de_usuario c "
                            + "WHERE u.cod_usuario = c.cod_amigo "
                            + "AND u.ativo = 'S' "
                            + "AND c.aceito = 'S' "
                            + "AND c.bloqueado = 'S' "
                            + "AND c.cod_usuario = "+l_codUsuario+" "
                            + "AND u.moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                            + "ORDER BY u.status_contato ASC, u.moniersn ASC "
                            + "LIMIT 0, "+LogUsuario.s_qtdUsuMostrar;                     
            }


                try {
                    Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                        //se conseguir ir para a primeira linha do c_resultset...
                        if(Dados.s_conexaoBanco.c_resultset.first()){

                            Moniersn.tf_pesquisaUsuario.setForeground(new java.awt.Color(0, 0, 255));

                            do{

                                l_apelidoAmigo = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                                l_humor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
                                l_codAmigo = Dados.s_conexaoBanco.c_resultset.getString("u.cod_usuario");
                                l_statusContato = Dados.s_conexaoBanco.c_resultset.getString("u.status_contato");

                                    //testa status
                                    if(l_statusContato.equals("conectado"))
                                        l_codStatus = "1";
                                    else
                                        l_codStatus = "0";
                                    
                                l_apelidoHumor = l_apelidoAmigo+"|"+l_humor;  
                                l_codigoUsuarioStatus = l_codAmigo+"|"+l_codStatus;

                                //add linhas
                                modelo.addRow(new Object[]{l_apelidoHumor, l_codigoUsuarioStatus});
                                l_qtdUsuarioLista++;

                            //enquanto o c_resultset apresentar dados...
                            }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto tiver dados preenche o jtable com os valores
                        
                            
                        }else{
                            Moniersn.tf_pesquisaUsuario.setForeground(new java.awt.Color(255, 0, 0));
                            l_qtdUsuarioLista = 0;
                        }

                    Moniersn.jt_listaAmigosUsuario.setToolTipText(l_tipText);  
                    Moniersn.lb_qtdUsuarioListaUsuario.setText("("+l_qtdUsuarioLista+")");
                    Moniersn.lb_qtdUsuarioListaUsuario.setToolTipText("("+l_qtdUsuarioLista+") "+Avisos.TEXTO_PESSOAS_NA_LISTA);
                    Moniersn.lb_qtdTotalUsuario.setText("("+l_qtdTotalUsuarios+") "+Avisos.TEXTO_PESSOAS_NO_GERAL);
                    Moniersn.lb_qtdTotalUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_iconeUsuario)));


                }
                //caso ocorra uma exceçao...
                catch (Exception e) {
                    System.err.println("Erro ao tentar preencher lista de amigos.     \nErro: "+e);
                }

    }//fim preencher lista de amigos


    //aqui preencherá a tabela com os amigos do meu amigo
    public void preencherListaAmigosDoAmigo(int l_codAmigo, String l_listaAmigosAmigo){
        //guardará a qtd de voltas do loop que será a qtd de amigos
        int l_codStatusAmigo = 0;
        String l_sqlSelect = null;
//        String l_statusContato = null;
        String l_apelido = null;
        String l_humor = null;
        String l_apelidoHumor = null;
        String l_codUsuario = null;
        int l_qtdUsuarioLista = 0;
        String l_tipoAmigo = null;
        
        String l_apelidoPesquisado = Moniersn.tf_pesquisaListaAmigo.getText().toLowerCase();
               l_apelidoPesquisado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_apelidoPesquisado);
        
        LogAmigo.s_listaAmigoSelecionada = l_listaAmigosAmigo;

        //ajusta a largura das colunas 0 e 1
        Moniersn.jt_listaAmigosDoAmigo.getColumnModel().getColumn(0).setPreferredWidth(130);
        Moniersn.jt_listaAmigosDoAmigo.getColumnModel().getColumn(1).setPreferredWidth(20);

//        //seta o renderer na coluna 0... (icone de status)
        Moniersn.jt_listaAmigosDoAmigo.getColumnModel().getColumn(0).setCellRenderer(new RendererPintorJTable());
        //seta o renderer na coluna 1... (icone de status)
        Moniersn.jt_listaAmigosDoAmigo.getColumnModel().getColumn(1).setCellRenderer(new RendererPintorJTable());

        //instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
        DefaultTableModel modelo = (DefaultTableModel) Moniersn.jt_listaAmigosDoAmigo.getModel();
        modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

        Moniersn.jt_listaAmigosDoAmigo.setToolTipText(Avisos.TIP_TEXT_LISTA_AMIGOS_DE_AMIGO+LogAmigo.s_moniersn); 

        if(l_listaAmigosAmigo.equals(Avisos.TEXTO_AMIGOS)){
                l_sqlSelect = "SELECT u.moniersn, u.cod_usuario, u.status_contato, u.icone_humor, c.* "
                             +"FROM usuario u, contato_de_usuario c "
                             +"WHERE u.cod_usuario = c.cod_amigo "
                             + "AND u.moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                             + "AND u.ativo = 'S' "
                             + "AND c.cod_usuario = "+l_codAmigo+" "
                             + "AND c.aceito = 'S' "
                             + "ORDER BY u.status_contato ASC, u.moniersn ASC "
                             + "LIMIT 0, "+LogAmigo.s_qtdUsuMostrar;  
        }

            else if(l_listaAmigosAmigo.equals(Avisos.TEXTO_COMUNS)){
                l_sqlSelect = "SELECT uUsuario.cod_usuario, uUsuario.moniersn, uUsuario.icone_humor, uUsuario.status_contato, uAmigo.cod_usuario, uAmigo.moniersn, uAmigo.status_contato, cUsuario.cod_usuario, cUsuario.cod_amigo, cAmigo.cod_usuario, cAmigo.cod_amigo "
                             + "FROM usuario uUsuario, usuario uAmigo, contato_de_usuario cUsuario, contato_de_usuario cAmigo "
                             + "WHERE uUsuario.cod_usuario = cUsuario.cod_amigo "
                             + "AND uUsuario.moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                             + "AND uAmigo.cod_usuario = cAmigo.cod_amigo "
                             + "AND uUsuario.ativo = 'S' "
                             + "AND uAmigo.ativo = 'S' "
                             + "AND cUsuario.aceito = 'S' "
                             + "AND cAmigo.aceito = 'S' "
                             + "AND cAmigo.cod_amigo = cUsuario.cod_amigo "
                             + "AND cUsuario.cod_usuario = "+LogUsuario.s_cod+" "
                             + "AND cAmigo.cod_usuario = "+l_codAmigo+" "
                             + "AND cAmigo.cod_usuario != "+LogUsuario.s_cod+" "
                             + "ORDER BY uUsuario.status_contato ASC, uUsuario.moniersn ASC "
                             + "LIMIT 0, "+LogAmigo.s_qtdUsuMostrar;  
            }

        //atualiza o c_resultset do amigos em questão
        Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

            try {


                //se conseguir ir para a primeira linha do c_resultset...
                if(Dados.s_conexaoBanco.c_resultset.first()){
                    Moniersn.tf_pesquisaListaAmigo.setForeground(new java.awt.Color(0, 0, 255));
                    

                    do{

                            if(l_listaAmigosAmigo.equals(Avisos.TEXTO_AMIGOS)){
                                //l_statusContato = Dados.s_conexaoBanco.c_resultset.getString("u.status_contato");
                                l_apelido = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                                l_humor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
//                                l_statusContato = Dados.s_conexaoBanco.c_resultset.getString("u.status_contato");
                                
                                
                                
                            }
                                else if(l_listaAmigosAmigo.equals(Avisos.TEXTO_COMUNS)){
                                    //l_statusContato = Dados.s_conexaoBanco.c_resultset.getString("uUsuario.status_contato");
                                    l_apelido = Dados.s_conexaoBanco.c_resultset.getString("uUsuario.moniersn");
                                    l_humor = Dados.s_conexaoBanco.c_resultset.getString("uUsuario.icone_humor");
//                                    l_statusContato = Dados.s_conexaoBanco.c_resultset.getString("uUsuario.status_contato");
                                }

                            //Teste de status
                            if(l_listaAmigosAmigo.equals(Avisos.TEXTO_AMIGOS))
                                l_codStatusAmigo = 1;
                            else
                                l_codStatusAmigo = 0;


                        l_apelidoHumor = l_apelido+"|"+l_humor;
                        
//                        System.err.println("apelido humor: "+l_apelidoHumor+"\ncod status: "+l_codStatusAmigo);

                        //add na lista de amigos
                        modelo.addRow(new Object[]{l_apelidoHumor, l_codStatusAmigo});

                        //contagem de amigos para mstrar no header da tabela
                        l_qtdUsuarioLista++;

                    //enquanto  c_resultset apresentar dados
                    }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto tiver dados preenche o jtable com os valores
                }
                else{
                        l_qtdUsuarioLista = 0;
                        Moniersn.tf_pesquisaListaAmigo.setForeground(new java.awt.Color(255, 0, 0));
                }

                //SETA TOTAL DE AMIGOS DO AMIGO
                Moniersn.jt_listaAmigosDoAmigo.getColumnModel().getColumn(0).setHeaderValue(Avisos.TEXTO_AMIGOS_DE+LogAmigo.s_moniersn);
            
                Moniersn.lb_qtdUsuariosListaAmigo.setText("("+l_qtdUsuarioLista+")");
                Moniersn.lb_qtdUsuariosListaAmigo.setToolTipText("("+l_qtdUsuarioLista+") "+Avisos.TEXTO_PESSOAS_NA_LISTA);

                //repinta o modelo da tabela
                Moniersn.jt_listaAmigosDoAmigo.getTableHeader().resizeAndRepaint();
                
            }
            //caso ocorra alguma exceção...
            catch (Exception e){
                System.err.println("Erro ao tentar preencher lista de amigos do seu amigo.     \nErro: "+e);
            }

    }//fim preencherJtable

    public void preencherListaDeRecadosUsuario(int l_codUsuario, JTable l_listaRecadoPubPriv,  String l_listaRecadoSelecionada){

            int l_codRecado;
            String l_apelido = null;
            String l_humor = null;
            String l_conteudo = null;
            String l_visto = null;
            String l_quando = null;
            String l_dataHoraAtu = null;
            String l_apelidoHumor;
            String l_dePara = null;
            String l_tipTextLista = null;
            String l_textoPesquisa = null;
            int l_contadorRec = 0;

            boolean l_podeExeutar = false;

            String l_conteudoPesquisado = null;

                //caso lista de rec pub
                if(l_listaRecadoPubPriv.equals(Moniersn.jt_listaRecadosPubUsuario)){

                       l_conteudoPesquisado = Moniersn.tf_conteudoRecPubPesquisado.getText();
                
                //caso lista de rec priv
                }else{
                       l_conteudoPesquisado = Moniersn.tf_conteudoRecPrivPesquisado.getText();
                }
            
            l_conteudoPesquisado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_conteudoPesquisado);
            
            Data.atualizaDataHora();

            String l_sqlSelect = null;

            l_listaRecadoPubPriv.getColumnModel().getColumn(0).setPreferredWidth(5);
            l_listaRecadoPubPriv.getColumnModel().getColumn(1).setPreferredWidth(60);
            l_listaRecadoPubPriv.getColumnModel().getColumn(2).setPreferredWidth(200);
            l_listaRecadoPubPriv.getColumnModel().getColumn(3).setPreferredWidth(70);
            l_listaRecadoPubPriv.getColumnModel().getColumn(4).setPreferredWidth(30);

            //RENDERER (icone de humor)
            l_listaRecadoPubPriv.getColumnModel().getColumn(0).setCellRenderer(new RendererPintorJTable());

            //RENDERER (icone de humor)
            l_listaRecadoPubPriv.getColumnModel().getColumn(1).setCellRenderer(new RendererPintorJTable());

            //RENDERER (qtd de recados)
            l_listaRecadoPubPriv.getColumnModel().getColumn(4).setCellRenderer(new RendererPintorJTable());

            //instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
            DefaultTableModel modelo = (DefaultTableModel) l_listaRecadoPubPriv.getModel();
            modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados
            //cb_lista_amigos.removeAllItems();

            try {


                //CASO LISTA DE RECADOS PÚBLICOS
                if(l_listaRecadoPubPriv.equals(Moniersn.jt_listaRecadosPubUsuario)){

                    //tipo recado sendo visualizado atualmente na lista
                    LogUsuario.s_listaRecPubSelecionada = l_listaRecadoSelecionada;


                    if(!LogUsuario.s_quemVeRecPub.equals("ninguem")){


                        //RECEBIDO
                        if(l_listaRecadoSelecionada.equals("rec")){

                            l_dePara = Avisos.TEXTO_DE;
                            l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_REC_PUB_REC;

                            //QUEM_VE_REC_PUB = TODOS
                            if(LogUsuario.s_quemVeRecPub.equals("todos")){

                              //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM e Ñ AMIGOS Q SÓ AMIGOS VEEM LISTA REC. PUB.
                              l_sqlSelect = "SELECT u.moniersn, u.icone_humor, r.* "
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
                                         + "AND r.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                         + "AND u.ativo = 'S' "
                                         + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                         + "AND r.cod_destinatario = "+LogUsuario.s_cod+" "
                                         + "AND r.recado_destinatario_gravado = 'S' "
                                         + "AND r.tipo_recado = 'pub' "
                                         + "AND u.cod_usuario != "+LogUsuario.s_cod+" "
                                         + "ORDER BY r.data_hora_atu "
                                         + "DESC "
                                         + "LIMIT 0, "+LogUsuario.s_qtdRecPubAMostrar; 
                            }

                            //QUEM_VE_REC_PUB = AMIGOS
                            if(LogUsuario.s_quemVeRecPub.equals("amigos")){

                                  //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM 
                                  l_sqlSelect = "SELECT u.moniersn, u.icone_humor, r.* "
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
                                             + "AND r.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                             + "AND u.ativo = 'S' "
                                             + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                             + "AND r.cod_destinatario = "+LogUsuario.s_cod+" "
                                             + "AND r.recado_destinatario_gravado = 'S' "
                                             + "AND r.tipo_recado = 'pub' "
                                             + "AND u.cod_usuario != "+LogUsuario.s_cod+" "
                                             + "ORDER BY r.data_hora_atu "
                                             + "DESC "
                                             + "LIMIT 0, "+LogUsuario.s_qtdRecPubAMostrar;
                            }
                        }

                        //NÃO LIDO
                        if(l_listaRecadoSelecionada.equals("naoLido")){

                            l_dePara = Avisos.TEXTO_DE;
                            l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_REC_PUB_N_LIDO;

                            //QUEM_VE_REC_PUB = TODOS
                            if(LogUsuario.s_quemVeRecPub.equals("todos")){


                              //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM e Ñ AMIGOS Q SÓ AMIGOS VEEM LISTA REC. PUB.
                              l_sqlSelect = "SELECT u.moniersn, u.icone_humor, r.* "
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
                                         + "AND r.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                         + "AND u.ativo = 'S' "
                                         + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                         + "AND r.cod_destinatario = "+LogUsuario.s_cod+" "
                                         + "AND r.recado_destinatario_gravado = 'S' "
                                         + "AND r.tipo_recado = 'pub' "
                                         + "AND r.visto = 'N' "
                                         + "AND u.cod_usuario != "+LogUsuario.s_cod+" "
                                         + "ORDER BY r.data_hora_atu "
                                         + "DESC "
                                         + "LIMIT 0, "+LogUsuario.s_qtdRecPubAMostrar;

                            }

                            //QUEM_VE_REC_PUB = AMIGOS
                            if(LogUsuario.s_quemVeRecPub.equals("amigos")){


                              //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM
                              l_sqlSelect = "SELECT u.moniersn, u.icone_humor, r.* "
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
                                         + "AND r.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                         + "AND u.ativo = 'S' "
                                         + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                         + "AND r.cod_destinatario = "+LogUsuario.s_cod+" "
                                         + "AND r.recado_destinatario_gravado = 'S' "
                                         + "AND r.tipo_recado = 'pub' "
                                         + "AND r.visto = 'N' "
                                         + "AND u.cod_usuario != "+LogUsuario.s_cod+" "
                                         + "ORDER BY r.data_hora_atu "
                                         + "DESC "
                                         + "LIMIT 0, "+LogUsuario.s_qtdRecPubAMostrar;
                            }
                        }

                        //ENVIADO
                        if(l_listaRecadoSelecionada.equals("env")){ 

                            l_dePara = Avisos.TEXTO_PARA;
                            l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_REC_PUB_ENV;

                            //QUEM_VE_REC_PUB = TODOS
                            if(LogUsuario.s_quemVeRecPub.equals("todos")){

                              //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM e Ñ AMIGOS Q SÓ AMIGOS VEEM LISTA REC. PUB.
                              l_sqlSelect = "SELECT u.moniersn, u.icone_humor, r.* "
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
                                         + "AND r.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                         + "AND u.ativo = 'S' "
                                         + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                         + "AND r.cod_emissor = "+LogUsuario.s_cod+" "
                                         + "AND r.recado_emissor_gravado = 'S' "
                                         + "AND r.tipo_recado = 'pub' "
                                         + "AND u.cod_usuario != "+LogUsuario.s_cod+" "
                                         + "ORDER BY r.data_hora_atu "
                                         + "DESC "
                                         + "LIMIT 0, "+LogUsuario.s_qtdRecPubAMostrar;
                              
                            }

                            //QUEM_VE_REC_PUB = AMIGOS
                            if(LogUsuario.s_quemVeRecPub.equals("amigos")){

                              //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM
                              l_sqlSelect = "SELECT u.moniersn, u.icone_humor, r.* "
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
                                         + "AND r.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                         + "AND u.ativo = 'S' "
                                         + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                         + "AND r.cod_emissor = "+LogUsuario.s_cod+" "
                                         + "AND r.recado_emissor_gravado = 'S' "
                                         + "AND r.tipo_recado = 'pub' "
                                         + "AND u.cod_usuario != "+LogUsuario.s_cod+" "
                                         + "ORDER BY r.data_hora_atu "
                                         + "DESC "
                                         + "LIMIT 0, "+LogUsuario.s_qtdRecPubAMostrar;
                            }
                        }

                    //caso != ninguem, pode executar query
                    l_podeExeutar = true;

                    }
                    //FIM CASO QUEM_VE_REC_PUB == ninguem
                    else{

                        //caso = ninguem, não executa query
                        l_podeExeutar = false;

                        //ZERA AS LINHAS
                        modelo.setNumRows(0);

                        //SETA 0 EM TODOS RECADOS
                        Moniersn.rb_todosRecadosPubRecebidos.setText("Recebido(s) (0)");
                        Moniersn.rb_recadosPubNaoLidos.setText("Não lido(s) (0)");
                        Moniersn.rb_recadosPubEnviados.setText("Enviado(s) (0)");


                    }

                }//FIM CASO LISTA REC. PUB


                //RECADO PRIVADO
                else if(l_listaRecadoPubPriv.equals(Moniersn.jt_listaRecadosPriv)){

                    l_podeExeutar = true;

                    //tipo recado sendo visualizado atualmente na lista
                    LogUsuario.s_listaRecPrivSelecionada = l_listaRecadoSelecionada;

                        //RECEBIDO
                        if(l_listaRecadoSelecionada.equals("rec")){

                            l_dePara = Avisos.TEXTO_DE;
                            l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_REC_PRIV_REC;

                              l_sqlSelect = "SELECT u.moniersn, u.icone_humor, r.* "
                                          + "FROM usuario u, recado r "
                                         + "WHERE u.cod_usuario = r.cod_emissor "
                                         + "AND r.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                         + "AND u.ativo = 'S' "
                                         + "AND r.cod_destinatario = "+LogUsuario.s_cod+" "
                                         + "AND r.recado_destinatario_gravado = 'S' "
                                         + "AND r.tipo_recado = 'priv' "
                                         + "ORDER BY r.data_hora_atu "
                                         + "DESC "
                                         + "LIMIT 0, "+LogUsuario.s_qtdRecPrivAMostrar;
                        }

                        //NÃO LIDO
                        else if(l_listaRecadoSelecionada.equals("naoLido")){

                            l_dePara = Avisos.TEXTO_DE;
                            l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_REC_PRIV_N_LIDO;

                              l_sqlSelect = "SELECT u.moniersn, u.icone_humor, r.* "
                                          + "FROM usuario u, recado r "
                                         + "WHERE u.cod_usuario = r.cod_emissor "
                                         + "AND r.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                         + "AND u.ativo = 'S' "
                                         + "AND r.cod_destinatario = "+LogUsuario.s_cod+" "
                                         + "AND r.recado_destinatario_gravado = 'S' "
                                         + "AND r.tipo_recado = 'priv' "
                                         + "AND r.visto = 'N' "
                                         + "ORDER BY r.data_hora_atu "
                                         + "DESC "
                                         + "LIMIT 0, "+LogUsuario.s_qtdRecPrivAMostrar;

                        }
                        //ENVIADO
                        else{

                            l_dePara = Avisos.TEXTO_PARA;
                            l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_REC_PRIV_ENV;

                              l_sqlSelect = "SELECT u.moniersn, u.icone_humor, r.* "
                                          + "FROM usuario u, recado r "
                                         + "WHERE u.cod_usuario =  r.cod_destinatario "
                                         + "AND r.conteudo LIKE '%"+l_conteudoPesquisado+"%' "  
                                         + "AND u.ativo = 'S' "
                                         + "AND r.cod_emissor = "+LogUsuario.s_cod+" "
                                         + "AND r.recado_emissor_gravado = 'S' "
                                         + "AND r.tipo_recado = 'priv' "
                                         + "ORDER BY r.data_hora_atu "
                                         + "DESC "
                                         + "LIMIT 0, "+LogUsuario.s_qtdRecPrivAMostrar;
                        }
                }


                if(l_podeExeutar){

                Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                    //CASO TENHA RESULTADO
                    if(Dados.s_conexaoBanco.c_resultset.first()){

                        if(l_listaRecadoPubPriv.equals(Moniersn.jt_listaRecadosPubUsuario))
                            Moniersn.tf_conteudoRecPubPesquisado.setForeground(new java.awt.Color(0, 0, 255));
                        else
                            Moniersn.tf_conteudoRecPrivPesquisado.setForeground(new java.awt.Color(0, 0, 255));
                        
                            do{

                                //Trata data para mostrar a quanto tempo foi enviado
                                l_visto = Dados.s_conexaoBanco.c_resultset.getString("r.visto");
                                l_apelido = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                                l_humor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
                                l_conteudo = Dados.s_conexaoBanco.c_resultset.getString("r.conteudo");
                                l_dataHoraAtu = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("r.data_hora_atu"));
                                l_quando =  Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraAtu);
                                l_codRecado = Dados.s_conexaoBanco.c_resultset.getInt("r.cod_recado");

                                l_apelidoHumor = l_apelido+"|"+l_humor;

                                //linha de recado
                                modelo.addRow(new Object[]{l_visto, l_apelidoHumor, l_conteudo, l_quando, String.valueOf(l_codRecado)});

                                l_contadorRec++;

                            }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto tiver dados preenche o jtable com os valores

                    }else{
                    
                        l_contadorRec = 0;
                        
                            if(l_listaRecadoPubPriv.equals(Moniersn.jt_listaRecadosPubUsuario))
                                Moniersn.tf_conteudoRecPubPesquisado.setForeground(new java.awt.Color(255, 0, 0));
                            else
                                Moniersn.tf_conteudoRecPrivPesquisado.setForeground(new java.awt.Color(255, 0, 0));
               
                    }

                        //caso lista do usuario de rec pub
                        if(l_listaRecadoPubPriv.equals(Moniersn.jt_listaRecadosPubUsuario)){
                            
                                if(l_conteudoPesquisado.isEmpty()){

                                    if(l_listaRecadoSelecionada.equals("rec"))
                                        l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_REC;
                                    if(l_listaRecadoSelecionada.equals("naoLido"))
                                        l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_N_LIDA;
                                    if(l_listaRecadoSelecionada.equals("env"))
                                        l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_ENV;

                                }else{
                                      l_textoPesquisa = Avisos.TEXTO_VISU_PESQ;
                                }
                            
                            Moniersn.lb_qtdRecPubEncontrado.setText("("+l_contadorRec+") "+l_textoPesquisa);

                        }//caso lista do usuario de rec pub
                        if(l_listaRecadoPubPriv.equals(Moniersn.jt_listaRecadosPriv)){
                            
                                if(l_conteudoPesquisado.isEmpty()){

                                    if(l_listaRecadoSelecionada.equals("rec"))
                                        l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_REC;
                                    if(l_listaRecadoSelecionada.equals("naoLido"))
                                        l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_N_LIDA;
                                    if(l_listaRecadoSelecionada.equals("env"))
                                        l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_ENV;

                                }else{
                                      l_textoPesquisa = Avisos.TEXTO_VISU_PESQ;
                                }
                            
                            Moniersn.lb_qtdRecPrivEncontrado.setText("("+l_contadorRec+") "+l_textoPesquisa);

                        //caso lista do amigo
                        }else{
                              if(l_conteudoPesquisado.isEmpty())
                                  l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_ENV;
                              else
                                  l_textoPesquisa = Avisos.TEXTO_VISU_PESQ;
                              
                              Moniersn.lb_qtdAtuEncontradaAmigo.setText("("+l_contadorRec+") "+l_textoPesquisa);
                        }

                      //seta valor do header de ou para
                      l_listaRecadoPubPriv.setToolTipText(l_tipTextLista);
                      //seta valor do header de ou para na lista do usuario
                      l_listaRecadoPubPriv.getColumnModel().getColumn(1).setHeaderValue(l_dePara);
                      //repinta o modelo da tabela
                      l_listaRecadoPubPriv.getTableHeader().resizeAndRepaint();  

                }
            }
            catch (Exception e) {

                if(l_listaRecadoPubPriv.equals(Moniersn.jt_listaRecadosPubUsuario)){
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setEnabled(false);
                    Moniersn.s_telaMsn.bt_excluirRecPub.setEnabled(false);
                    Moniersn.s_telaMsn.bt_comentarRecPublico.setEnabled(false);
                }
                if(l_listaRecadoPubPriv.equals(Moniersn.jt_listaRecadosPriv)){
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setEnabled(false);
                    Moniersn.s_telaMsn.bt_excluirRecPriv.setEnabled(false);
                    Moniersn.s_telaMsn.bt_comentarRecPriv.setEnabled(false);
                }

                if(l_listaRecadoPubPriv.equals(Moniersn.jt_listaRecadosPubAmigo)){
                    Moniersn.s_telaMsn.bt_comentarRecPublico.setEnabled(false);
                }

                System.err.println("Erro ao tentar preencher a lista de recados.    \nErro: "+e);
            }

    }//fim preencherJtable

 public void preencherListaDeOcorrencias(int l_codUsuario, JTable l_listaOcorrencia, String l_listaOcorrenciaSelecionada){

     
        String l_dataHoraAtu = null;
        String l_quando = null;
        String l_anonimo = null;
        String l_apelido = null;
        String l_humor = null;
        String l_apelidoHumor = null;
        String l_conteudo = null;
        String l_DePara = null;
        String l_tipTextLista = null;
        String l_textoPesquisa = null;

        String l_dataHoraInicio = null;

        Data.atualizaDataHora();
        l_dataHoraInicio = LogUsuario.s_dataHoraCadastroBD;
        String l_dataHoraFim = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;
        
        String l_conteudoPesquisado = null;
        
            if(l_listaOcorrenciaSelecionada.equals(""))
               l_conteudoPesquisado = Moniersn.tf_conteudoOcoPesquisadoAmigo.getText();
            else
               l_conteudoPesquisado = Moniersn.tf_conteudoOcoPesquisado.getText();

        l_conteudoPesquisado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_conteudoPesquisado);
            
        int l_codOcorrencia = 0;
        int l_contadorOco = 0;

        String l_sqlSelect = null;

        boolean l_podeMostrar = true;

        l_listaOcorrencia.getColumnModel().getColumn(0).setPreferredWidth(50);
        l_listaOcorrencia.getColumnModel().getColumn(1).setPreferredWidth(220);
        l_listaOcorrencia.getColumnModel().getColumn(2).setPreferredWidth(70);
        l_listaOcorrencia.getColumnModel().getColumn(3).setPreferredWidth(15);

        //RENDERER (ícone humor)
        l_listaOcorrencia.getColumnModel().getColumn(0).setCellRenderer(new RendererPintorJTable());

        //RENDERER (qtd. comentários)
        l_listaOcorrencia.getColumnModel().getColumn(3).setCellRenderer(new RendererPintorJTable());

        //instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
        DefaultTableModel modelo = (DefaultTableModel) l_listaOcorrencia.getModel();
        modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados
        //cb_lista_amigos.removeAllItems();

        try {

            //do usuario
            if(l_listaOcorrencia.equals(Moniersn.jt_listaOcorrenciaUsuario)){

                LogUsuario.s_listaOcorrenciaSelecionada = l_listaOcorrenciaSelecionada;

                //TODAS RECEBIDAS
                if(l_listaOcorrenciaSelecionada.equals("rec")){

                          l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_OCO_REC;
                          l_DePara = Avisos.TEXTO_DE;

                          //SELECIONA TODAS OCORRÊNCIAS, EXCETO(DE AMIGOS QUE NAO BLOQ., Q NÃO ME BLOQ e OS NÃO AMIGOS Q SÓ MOSTRAM OCO. PRA AMIGOS)
                          l_sqlSelect = "SELECT u.moniersn, u.icone_humor, o.* "
                                      + "FROM usuario u, ocorrencia o, configuracoes_usuario c "
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
                                                                 + "AND c.visualizar_ocorrencia = 'amigos') "
//                                     + "AND o.data_hora_post BETWEEN '"+l_dataHoraInicio+"' AND '"+l_dataHoraFim+"' "
                                     + "AND o.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.cod_usuario = o.cod_emissor "
                                     + "AND u.ativo = 'S' "
                                     + "AND o.gravado = 'S' "
                                     + "AND c.visualizar_ocorrencia != 'ninguem' "
                                     + "AND u.cod_usuario != "+LogUsuario.s_cod+" "
                                     + "ORDER BY o.data_hora_atu "
                                     + "DESC "
                                     + "LIMIT 0, "+LogUsuario.s_qtdOcoAMostrar;
                }

                    //OCORRENCIAS ENVIADAS
                    else if(l_listaOcorrenciaSelecionada.equals("env")){

                      l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_OCO_ENV;
                      l_DePara = Avisos.TEXTO_PARA;

                                l_sqlSelect = "SELECT u.moniersn, u.icone_humor, o.*, c.visualizar_ocorrencia "
                                             + "FROM usuario u, ocorrencia o, configuracoes_usuario c "
                                             + "WHERE u.cod_usuario = o.cod_emissor "
                                             + "AND o.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                             + "AND u.ativo = 'S' "
                                             + "AND c.cod_usuario = u.cod_usuario "
                                             + "AND u.cod_usuario = "+l_codUsuario+" "
                                             + "AND o.gravado = 'S' "
                                             + "ORDER BY o.data_hora_atu "
                                             + "DESC "
                                             + "LIMIT 0, "+LogUsuario.s_qtdOcoAMostrar;
                    }
            }

            //ENVIADAS do amigo
             else if(l_listaOcorrencia.equals(Moniersn.jt_listaOcorrenciaAmigo)){

                        l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_OCO_ENV_AMIGO+LogAmigo.s_moniersn;
                        l_DePara = Avisos.TEXTO_PARA;

                        l_sqlSelect = "SELECT u.moniersn, u.icone_humor, o.*, c.visualizar_ocorrencia "
                                     + "FROM usuario u, ocorrencia o, configuracoes_usuario c "
                                     + "WHERE u.cod_usuario = o.cod_emissor "
                                     + "AND o.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.ativo = 'S' "
                                     + "AND u.cod_usuario = "+l_codUsuario+" "
                                     + "AND o.anonimo = 'N' "
                                     + "AND o.gravado = 'S' "
                                     + "ORDER BY o.data_hora_atu "
                                     + "DESC "
                                     + "LIMIT 0, "+LogAmigo.s_qtdOcoAMostrar;

             }


          Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                //CASO TENHA RESULTADO
                if(Dados.s_conexaoBanco.c_resultset.first()){

                    if(l_listaOcorrencia.equals(Moniersn.jt_listaOcorrenciaUsuario))
                        Moniersn.tf_conteudoOcoPesquisado.setForeground(new java.awt.Color(0, 0, 255));
                    else
                        Moniersn.tf_conteudoOcoPesquisadoAmigo.setForeground(new java.awt.Color(0, 0, 255));
                    
                    //atualiza datahora
                    Data.atualizaDataHora();

                    do{

                        if(l_listaOcorrencia.equals(Moniersn.jt_listaOcorrenciaUsuario)){

                            l_podeMostrar = true;

                                if(l_listaOcorrenciaSelecionada.equals("env")){
                                    l_apelido = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_ocorrencia");

                                        if(l_apelido.equals("todos") || l_apelido.equals("amigos"))
                                            l_humor = l_apelido+"(geral)";
                                        else
                                            l_humor = l_apelido;

                                }else{

                                    //TESTE DE OCORRENCIA ANONIMA
                                    l_anonimo = Dados.s_conexaoBanco.c_resultset.getString("o.anonimo");

                                        if(l_anonimo.equals("S")){
                                            l_apelido = "anonimo";
                                            l_humor = "anonimo";
                                        }
                                            else{
                                                l_apelido = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                                                l_humor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
                                            }
                                }

                        }

                        //caso na lista de ocorrecnias do amigo
                        if(l_listaOcorrencia.equals(Moniersn.jt_listaOcorrenciaAmigo)){


                            l_apelido = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_ocorrencia");

                                if(l_apelido.equals("todos") || l_apelido.equals("amigos"))
                                    l_humor = l_apelido+"(geral)";
                                else
                                    l_humor = l_apelido;

                            l_podeMostrar = true;

                        }

                        if(l_podeMostrar){

                            l_codOcorrencia = Dados.s_conexaoBanco.c_resultset.getInt("o.cod_ocorrencia");
                            l_conteudo =      Dados.s_conexaoBanco.c_resultset.getString("o.conteudo");
                            l_dataHoraAtu =   Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("o.data_hora_atu"));
                            l_quando      =   Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraAtu);
                            l_apelidoHumor =  l_apelido+"|"+l_humor;

                            //add linha de ocorrencia
                            modelo.addRow(new Object[]{l_apelidoHumor, l_conteudo, l_quando, l_codOcorrencia});

                            l_contadorOco++;
                            
                            
                        }


                    }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto tiver dados preenche o jtable com os valores

                }else{
                    
                    l_contadorOco = 0;

                        if(l_listaOcorrencia.equals(Moniersn.jt_listaOcorrenciaUsuario))
                            Moniersn.tf_conteudoOcoPesquisado.setForeground(new java.awt.Color(255, 0, 0));
                        else
                            Moniersn.tf_conteudoOcoPesquisadoAmigo.setForeground(new java.awt.Color(255, 0, 0));
                    
               
                }

                        //caso lista do usuario
                        if(l_listaOcorrencia.equals(Moniersn.jt_listaOcorrenciaUsuario)){
                            
                            if(l_conteudoPesquisado.isEmpty()){
                            
                                if(l_listaOcorrenciaSelecionada.equals("rec"))
                                    l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_REC;
                                if(l_listaOcorrenciaSelecionada.equals("env"))
                                    l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_ENV;
                                
                            }else{
                                  l_textoPesquisa = Avisos.TEXTO_VISU_PESQ;
                            }
                            
                                 Moniersn.lb_qtdOcoEncontrada.setText("("+l_contadorOco+") "+l_textoPesquisa);

                        //caso lista do amigo
                        }else{
                              if(l_conteudoPesquisado.isEmpty())
                                  l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_ENV;
                              else
                                  l_textoPesquisa = Avisos.TEXTO_VISU_PESQ;
                              
                              Moniersn.lb_qtdOcoEncontradaAmigo.setText("("+l_contadorOco+") "+l_textoPesquisa);
                              
                        }
                
                
                  //seta valor do header de ou para
                  l_listaOcorrencia.setToolTipText(l_tipTextLista);
                  //seta valor do header de ou para
                  l_listaOcorrencia.getColumnModel().getColumn(0).setHeaderValue(l_DePara);
                  //repinta o modelo da tabela
                  l_listaOcorrencia.getTableHeader().resizeAndRepaint();
                  

        }

        catch (Exception e) {

            if(l_listaOcorrencia.equals(Moniersn.jt_listaOcorrenciaUsuario)){
                Moniersn.s_telaMsn.bt_excluirOcorrencia.setEnabled(false);
                Moniersn.s_telaMsn.bt_comentarOcorrencia.setEnabled(false);
            }
            if(l_listaOcorrencia.equals(Moniersn.jt_listaOcorrenciaAmigo)){
                 Moniersn.s_telaMsn.bt_comentarOcorrenciaAmigo.setEnabled(false);
            }

            System.err.println("Erro ao tentar preencher a lista de ocorrências.    \nErro: "+e);
        }


    }//fim preenche lista de ocorrências do usuário


    public void preencherListaAtualizacoes(int l_codUsuario, JTable l_listaAtualizacao, String l_listaAtualizacaoSelecionada){


        String l_quando = null;
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
        String l_apelido = null;
        String l_humor = null;
        String l_apelidoHumor = null;
        String l_dataHoraAtu = null;
        String l_conteudoAtu = null;
        String l_DePara = null;
        String l_tipTextLista = null;
        String l_textoPesquisa = null;
        
        int l_qtdTotAtuAMostrar = 0;


        Data.atualizaDataHora();
        String l_dataHoraInicio = "2012/01/01 00:00:00";
        String l_dataHoraFim = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;

        String l_conteudoPesquisado = null;
        
            if(l_listaAtualizacaoSelecionada.equals(""))
               l_conteudoPesquisado = Moniersn.tf_conteudoAtuPesquisadoAmigo.getText();
            else
               l_conteudoPesquisado = Moniersn.tf_conteudoAtuPesquisado.getText();

        l_conteudoPesquisado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_conteudoPesquisado);
            
        int l_codAtu = 0;
        int l_contadorAtu  = 0;

        boolean l_podeMostrarLinha = false;
        String l_sqlSelect = null;

        l_listaAtualizacao.getColumnModel().getColumn(0).setPreferredWidth(50);
        l_listaAtualizacao.getColumnModel().getColumn(1).setPreferredWidth(220);
        l_listaAtualizacao.getColumnModel().getColumn(2).setPreferredWidth(70);
        l_listaAtualizacao.getColumnModel().getColumn(3).setPreferredWidth(15);

        //RENDERER (ícone humor)
        l_listaAtualizacao.getColumnModel().getColumn(0).setCellRenderer(new RendererPintorJTable());

        //RENDERER (qtd. comentários)
        l_listaAtualizacao.getColumnModel().getColumn(3).setCellRenderer(new RendererPintorJTable());

        //instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
        DefaultTableModel modelo = (DefaultTableModel) l_listaAtualizacao.getModel();
        modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados
        //cb_lista_amigos.removeAllItems();

        Data.atualizaDataHora();

        try {

            //do usuario
            if(l_listaAtualizacao.equals(Moniersn.jt_listaAtualizacoesUsuario)){

                LogUsuario.s_listaAtualizacoesSelecionada = l_listaAtualizacaoSelecionada;
                l_qtdTotAtuAMostrar = LogUsuario.s_qtdAtuAMostrar;

                
                if(l_listaAtualizacaoSelecionada.equals("recEstranhos")){

                    l_DePara = Avisos.TEXTO_DE;
                    l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_ATU_REC_EST;
                    

                      //ATUALIZAÇÕES DE ESTRANHOS
                      l_sqlSelect = "SELECT u.moniersn, u.icone_humor, a.*, c.* "//SELECIONA TODAS ATU
                                  + "FROM usuario u, configuracoes_usuario c, atualizacoes_usuario a "
                                  + "WHERE (u.cod_usuario) NOT IN (SELECT cod_amigo "//EXCETO OS AMIGOS
                                                                 + "FROM contato_de_usuario "
                                                                 + "WHERE cod_usuario = "+l_codUsuario+" " 
                                                                 + "AND aceito = 'S') "
                                  + "AND a.data_hora_post BETWEEN '"+l_dataHoraInicio+"' AND '"+l_dataHoraFim+"' "
                                  + "AND a.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                  + "AND u.cod_usuario = c.cod_usuario "
                                  + "AND u.cod_usuario = a.cod_usuario "
                                  + "AND u.ativo = 'S' "
                                  + "AND a.gravado = 'S' "
                                  + "AND u.cod_usuario != "+l_codUsuario+" "
                                  + "ORDER BY a.data_hora_atu "
                                  + "DESC ";
//                                  + "LIMIT 0, "+LogUsuario.s_qtdAtuAMostrar;
                      
                      
                }

                else if(l_listaAtualizacaoSelecionada.equals("recAmigos")){

                    l_DePara = Avisos.TEXTO_DE;
                    l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_ATU_REC_AMI;

                      //ATUALIZAÇÕES DE AMIGOS
                      l_sqlSelect = "SELECT u.moniersn, u.icone_humor, a.*, c.* "//SELECIONA TODAS ATU
                                  + "FROM usuario u, configuracoes_usuario c, atualizacoes_usuario a "
                                  + "WHERE (u.cod_usuario) IN (SELECT cod_amigo "//SOMENTE DE AMIGOS NÃO BLOQUEADOS
                                                             + "FROM contato_de_usuario "
                                                             + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                                                             + "AND aceito = 'S' "
                                                             + "AND bloqueado = 'N') "
                                  + "AND (u.cod_usuario) NOT IN (SELECT cod_usuario "//E QUE NÃO ME BLOQUEARAM
                                                             + "FROM contato_de_usuario "
                                                             + "WHERE cod_amigo = "+LogUsuario.s_cod+" "
                                                             + "AND aceito = 'S' "
                                                             + "AND bloqueado = 'S') "
                                 + "AND a.data_hora_post BETWEEN '"+l_dataHoraInicio+"' AND '"+l_dataHoraFim+"' "
                                 + "AND a.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                 + "AND u.cod_usuario = c.cod_usuario "
                                 + "AND u.cod_usuario = a.cod_usuario "
                                 + "AND u.ativo = 'S' "
                                 + "AND a.gravado = 'S' "
                                 + "AND u.cod_usuario != "+LogUsuario.s_cod+" "
                                 + "ORDER BY a.data_hora_atu "
                                 + "DESC ";
//                                 + "LIMIT 0, "+LogUsuario.s_qtdAtuAMostrar;
                }

                //MINHAS ATUALIZACOES
                else if(l_listaAtualizacaoSelecionada.equals("env")){

                    l_DePara = Avisos.TEXTO_PARA;
                    l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_ATU_MINHAS;

                        l_sqlSelect = "SELECT u.moniersn, u.icone_humor, a.*, c.* "//SELECIONA TODAS ATU
                                     + "FROM usuario u, atualizacoes_usuario a, configuracoes_usuario c "
                                     + "WHERE u.cod_usuario = a.cod_usuario "
                                     + "AND a.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                     + "AND u.ativo = 'S' "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.cod_usuario = "+l_codUsuario+" "
                                     + "AND a.gravado = 'S' "
                                     + "ORDER BY a.data_hora_atu "
                                     + "DESC ";
//                                     + "LIMIT 0, "+LogUsuario.s_qtdAtuAMostrar;
                }
                
            }

                //ENVIADAS do amigo
                 else if(l_listaAtualizacao.equals(Moniersn.jt_listaAtualizacoesAmigo)){

                    l_DePara = Avisos.TEXTO_PARA;
                    l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_ATU_AMIGO+LogAmigo.s_moniersn;
                    l_qtdTotAtuAMostrar = LogAmigo.s_qtdAtuAMostrar;

                    l_sqlSelect = "SELECT u.moniersn, u.icone_humor, a.*, c.* "
                                 + "FROM usuario u, atualizacoes_usuario a, configuracoes_usuario c "
                                 + "WHERE u.cod_usuario = a.cod_usuario "
                                 + "AND a.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                 + "AND u.cod_usuario = c.cod_usuario "
                                 + "AND u.ativo = 'S' "
                                 + "AND u.cod_usuario = "+l_codUsuario+" "
                                 + "AND a.data_hora_post BETWEEN '"+l_dataHoraInicio+"' AND '"+l_dataHoraFim+"' "
                                 + "AND a.gravado = 'S' "
                                 + "ORDER BY a.data_hora_atu "
                                 + "DESC ";
//                                 + "LIMIT 0, "+LogAmigo.s_qtdAtuAMostrar;
                 }

            Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                //CASO TENHA RESULTADO
                if(Dados.s_conexaoBanco.c_resultset.first()){

                    if(l_listaAtualizacao.equals(Moniersn.jt_listaAtualizacoesUsuario))
                        Moniersn.tf_conteudoAtuPesquisado.setForeground(new java.awt.Color(0, 0, 255));
                    else
                        Moniersn.tf_conteudoAtuPesquisadoAmigo.setForeground(new java.awt.Color(0, 0, 255));
                    
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

                        if(l_listaAtualizacao.equals(Moniersn.jt_listaAtualizacoesUsuario)){

                                if(l_listaAtualizacaoSelecionada.equals("recEstranhos")){

                                        if(l_perfil.equals("basico") && l_mostraInfoBasica.equals("todos")){

                                                if((l_atributo.equals("nome") || l_atributo.equals("sobrenome"))  && l_mostraNome.equals("S"))
                                                    l_podeMostrarLinha = true;
                                                else if(l_atributo.equals("icone_humor"))
                                                    l_podeMostrarLinha = true;
                                                else if(l_atributo.equals("sexo") && l_mostraSexo.equals("S"))
                                                    l_podeMostrarLinha = true;
                                                else if(l_atributo.equals("data_niver") && l_mostraDataNiver.equals("S"))
                                                    l_podeMostrarLinha = true;
                                                else if(l_atributo.equals("minha_frase") && l_mostraFrase.equals("S"))
                                                    l_podeMostrarLinha = true;
                                                else
                                                    l_podeMostrarLinha = false;
                                        }

                //                      TESTE PRA MOSTRAR ATU DE NOVA AMIZADE
                                        else if (l_perfil.equals("amigos") && l_mostraListaAmigos.equals("todos"))
                                                l_podeMostrarLinha = true;

                //                       TESTE PRA MOSTRAR ATU DE INFO. PESSOAL
                                        else if(l_perfil.equals("pessoal") && l_mostraInfoPessoal.equals("todos"))
                                                l_podeMostrarLinha = true;

                //                       TESTE PRA MOSTRAR ATU DE INFO. CONTATO
                                        else if(l_perfil.equals("contato") && l_mostraInfoContato.equals("todos"))
                                                l_podeMostrarLinha = true;

                //                       TESTE PRA MOSTRAR ATU DE INFO. EDUCACIONAL
                                        else if(l_perfil.equals("educacional") && l_mostraInfoEduc.equals("todos"))
                                                l_podeMostrarLinha = true;

                //                       TESTE PRA MOSTRAR ATU DE INFO. PROFISSIONAL
                                        else if(l_perfil.equals("profissional") && l_mostraInfoProf.equals("todos"))
                                                l_podeMostrarLinha = true;

                                        else{
                                                l_podeMostrarLinha = false;
                                        }
                                }


        //                      TESTE PRA MOSTRAR ATU DE INFO. BASICA DE AMIGOS

                                else if (l_listaAtualizacaoSelecionada.equals("recAmigos") ||
                                         l_listaAtualizacaoSelecionada.equals("env")){

                                        if(l_perfil.equals("basico") && !l_mostraInfoBasica.equals("ninguem")){

                                                if((l_atributo.equals("nome") || l_atributo.equals("sobrenome")) && l_mostraNome.equals("S"))
                                                    l_podeMostrarLinha = true;
                                                else if(l_atributo.equals("icone_humor"))
                                                    l_podeMostrarLinha = true;
                                                else if(l_atributo.equals("sexo") && l_mostraSexo.equals("S"))
                                                    l_podeMostrarLinha = true;
                                                else if(l_atributo.equals("data_niver") && l_mostraDataNiver.equals("S"))
                                                    l_podeMostrarLinha = true;
                                                else if(l_atributo.equals("minha_frase") && l_mostraFrase.equals("S"))
                                                    l_podeMostrarLinha = true;
                                                else
                                                    l_podeMostrarLinha = false;

                                                l_apelido = l_mostraInfoBasica;

                                        }

                //                      TESTE PRA MOSTRAR ATU DE NOVA AMIZADE
                                        else if (l_perfil.equals("amigos") && !l_mostraListaAmigos.equals("ninguem")){
                                                l_podeMostrarLinha = true;
                                                l_apelido = l_mostraListaAmigos;
                                        }

                //                       TESTE PRA MOSTRAR ATU DE INFO. PESSOAL
                                        else if(l_perfil.equals("pessoal") && !l_mostraInfoPessoal.equals("ninguem")){
                                                l_podeMostrarLinha = true;
                                                l_apelido = l_mostraInfoPessoal;
                                        }
                //                       TESTE PRA MOSTRAR ATU DE INFO. CONTATO
                                        else if(l_perfil.equals("contato") && !l_mostraInfoContato.equals("ninguem")){
                                                l_podeMostrarLinha = true;
                                                l_apelido = l_mostraInfoContato;
                                        }
                //                       TESTE PRA MOSTRAR ATU DE INFO. EDUCACIONAL
                                        else if(l_perfil.equals("educacional") && !l_mostraInfoEduc.equals("ninguem")){
                                                l_podeMostrarLinha = true;
                                                l_apelido = l_mostraInfoEduc;
                                        }
                //                       TESTE PRA MOSTRAR ATU DE INFO. PROFISSIONAL
                                        else if(l_perfil.equals("profissional") && !l_mostraInfoProf.equals("ninguem")){
                                                l_podeMostrarLinha = true;
                                                l_apelido = l_mostraInfoProf;
                                        }
                                        else{
                                                l_podeMostrarLinha = false;
                                        }
                                }
                        }

//                      TESTE PRA MOSTRAR ATU DE INFO. DO AMIGOS
                        else if(l_listaAtualizacao.equals(Moniersn.jt_listaAtualizacoesAmigo)){

                                if(l_perfil.equals("basico") && (l_mostraInfoBasica.equals("todos") ||
                                  (l_mostraInfoBasica.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok")))){

                                        if((l_atributo.equals("nome") || l_atributo.equals("sobrenome")) && l_mostraNome.equals("S"))
                                            l_podeMostrarLinha = true;
                                        else if(l_atributo.equals("icone_humor"))
                                            l_podeMostrarLinha = true;
                                        else if(l_atributo.equals("sexo") && l_mostraSexo.equals("S"))
                                            l_podeMostrarLinha = true;
                                        else if(l_atributo.equals("data_niver") && l_mostraDataNiver.equals("S"))
                                            l_podeMostrarLinha = true;
                                        else if(l_atributo.equals("minha_frase") && l_mostraFrase.equals("S"))
                                            l_podeMostrarLinha = true;
                                        else
                                            l_podeMostrarLinha = false;

                                    l_apelido = l_mostraInfoBasica;
                                }

        //                      TESTE PRA MOSTRAR ATU DE NOVA AMIZADE
                                else if(l_perfil.equals("amigos") && (l_mostraListaAmigos.equals("todos") ||
                                       (l_mostraListaAmigos.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok")))){
                                        l_podeMostrarLinha = true;
                                        l_apelido = l_mostraListaAmigos;
                                }

        //                       TESTE PRA MOSTRAR ATU DE INFO. PESSOAL
                                else if(l_perfil.equals("pessoal") && (l_mostraInfoPessoal.equals("todos") ||
                                       (l_mostraInfoPessoal.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok")))){
                                        l_podeMostrarLinha = true;
                                        l_apelido = l_mostraInfoPessoal;
                                }
        //                      TESTE PRA MOSTRAR ATU DE INFO. CONTATO
                                else if(l_perfil.equals("contato") && (l_mostraInfoContato.equals("todos") ||
                                       (l_mostraInfoContato.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok")))){
                                        l_podeMostrarLinha = true;
                                        l_apelido = l_mostraInfoContato;
                                }
        //                      TESTE PRA MOSTRAR ATU DE INFO. EDUCACIONAL
                                else if(l_perfil.equals("educacional") && (l_mostraInfoEduc.equals("todos") ||
                                       (l_mostraInfoEduc.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok")))){
                                        l_podeMostrarLinha = true;
                                        l_apelido = l_mostraInfoEduc;
                                }
        //                       TESTE PRA MOSTRAR ATU DE INFO. PROFISSIONAL
                                else if(l_perfil.equals("profissional") && (l_mostraInfoProf.equals("todos") ||
                                       (l_mostraInfoProf.equals("amigos") && LogAmigo.s_tipoAmigo.equals("aceito-ok")))){
                                        l_podeMostrarLinha = true;
                                        l_apelido = l_mostraInfoProf;
                                }
                                else{
                                        l_podeMostrarLinha = false;
                                }
                        }

//                        System.err.println("Perfil: "+l_perfil+". Mostra lista de amigos?: "+l_mostraListaAmigos+". Pode mostrar?:"+l_podeMostrarLinha);

                        if(l_podeMostrarLinha){

                                if(l_listaAtualizacao.equals(Moniersn.jt_listaAtualizacoesAmigo) ||
                                   ((l_listaAtualizacao.equals(Moniersn.jt_listaAtualizacoesUsuario) &&
                                    l_listaAtualizacaoSelecionada.equals("env")))){

                                        if(l_apelido.equals("amigos"))
                                            l_humor = Avisos.TEXTO_AMIGOS_GERAL;
                                        if(l_apelido.equals("todos"))
                                            l_humor = Avisos.TEXTO_TODOS_GERAL;

                                }else{

                                    l_apelido = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                                    l_humor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");

                                }

                           

                            //Pega informações para mostrar na tabela
                            l_apelidoHumor = l_apelido+"|"+l_humor.toLowerCase();
                            l_conteudoAtu = Dados.s_conexaoBanco.c_resultset.getString("a.conteudo");
                            l_codAtu= Dados.s_conexaoBanco.c_resultset.getInt("a.cod_atu");
                            //Trata data para mostrar a quanto tempo foi enviado
                            l_dataHoraAtu = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("a.data_hora_atu"));
                            
                            l_quando = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraAtu);
                            
                            //add linha na tabela
                            modelo.addRow(new Object[]{l_apelidoHumor, l_conteudoAtu, l_quando, l_codAtu});

                            l_contadorAtu++;

                                
                                if(l_contadorAtu == l_qtdTotAtuAMostrar)
                                    Dados.s_conexaoBanco.c_resultset.last();
                            
                        }

                    }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto tiver dados preenche o jtable com os valores

                  //seta valor do header de ou para
                  l_listaAtualizacao.setToolTipText(l_tipTextLista);
                  //seta valor do header de ou para
                  l_listaAtualizacao.getColumnModel().getColumn(0).setHeaderValue(l_DePara);
                  //repinta o modelo da tabela
                  l_listaAtualizacao.getTableHeader().resizeAndRepaint();

                  
                }else{
                    
                    l_contadorAtu = 0;
                            
                        if(l_listaAtualizacao.equals(Moniersn.jt_listaAtualizacoesUsuario))
                            Moniersn.tf_conteudoAtuPesquisado.setForeground(new java.awt.Color(255, 0, 0));
                        else
                            Moniersn.tf_conteudoAtuPesquisadoAmigo.setForeground(new java.awt.Color(255, 0, 0));
               
                }

                        //caso lista do usuario
                        if(l_listaAtualizacao.equals(Moniersn.jt_listaAtualizacoesUsuario)){
                            
                            if(l_conteudoPesquisado.isEmpty()){
                            
                                if(l_listaAtualizacaoSelecionada.equals("recEstranhos"))
                                    l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_REC_ESTR;
                                if(l_listaAtualizacaoSelecionada.equals("recAmigos"))
                                    l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_REC_AMI;
                                if(l_listaAtualizacaoSelecionada.equals("env"))
                                    l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_ENV;
                                
                            }else{
                                  l_textoPesquisa = Avisos.TEXTO_VISU_PESQ;
                            }
                            
                                 Moniersn.lb_qtdAtuEncontrada.setText("("+l_contadorAtu+") "+l_textoPesquisa);

                        //caso lista do amigo
                        }else{
                              if(l_conteudoPesquisado.isEmpty())
                                  l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_ENV;
                              else
                                  l_textoPesquisa = Avisos.TEXTO_VISU_PESQ;
                              
                              Moniersn.lb_qtdAtuEncontradaAmigo.setText("("+l_contadorAtu+") "+l_textoPesquisa);
                        }


        }

        catch (Exception e) {


            System.err.println("Erro ao tentar preencher a lista de atualizacoes.   \nErro: "+e);

                if(l_listaAtualizacao.equals(Moniersn.jt_listaAtualizacoesUsuario)){
                    Moniersn.s_telaMsn.bt_excluirAtualizacoes.setEnabled(false);
                    Moniersn.s_telaMsn.bt_comentarAtuUsuario.setEnabled(false);
                }

                if(l_listaAtualizacao.equals(Moniersn.jt_listaAtualizacoesAmigo)){
                    Moniersn.s_telaMsn.bt_comentarAtuAmigo.setEnabled(false);
                }

            
        }

    }//fim preenche lista de ocorrências do usuário


public void preencherListaDeArquivos(int l_codUsuario, JTable l_listaArquivos, String l_listaArquivoSelecionada){

        String l_dataHora = null;
        String l_quando = null;
        String l_extensao = null;
        String l_apelido = null;
        String l_humor = null;
        String l_apelidoHumor = null;
        String l_tipTextLista = null;
        String l_dePara = null;
        String l_nomeArquivo = null;
        int l_tamanho = 0;
        int l_codArquivo = 0;
        int l_codDestinatario = 0;
        int l_contadorArq = 0;
        boolean l_podeExecutar = false;

        String l_sqlSelect = null;
        String l_textoPesquisa = null;
        String l_conteudoPesquisado = null;
        
            if(l_listaArquivoSelecionada.equals(""))
               l_conteudoPesquisado = Moniersn.tf_nomeArqPesquisadoAmigo.getText();
            else
               l_conteudoPesquisado = Moniersn.tf_nomeArqPesquisado.getText();
        
        l_conteudoPesquisado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_conteudoPesquisado);        
        
        l_listaArquivos.getColumnModel().getColumn(0).setPreferredWidth(40);
        l_listaArquivos.getColumnModel().getColumn(1).setPreferredWidth(10);
        l_listaArquivos.getColumnModel().getColumn(2).setPreferredWidth(100);
        l_listaArquivos.getColumnModel().getColumn(3).setPreferredWidth(40);
        l_listaArquivos.getColumnModel().getColumn(4).setPreferredWidth(80);
        l_listaArquivos.getColumnModel().getColumn(5).setPreferredWidth(40);

        //RENDERER (ícone humor)
        l_listaArquivos.getColumnModel().getColumn(0).setCellRenderer(new RendererPintorJTable());

        //RENDERER (Extensao)
        l_listaArquivos.getColumnModel().getColumn(1).setCellRenderer(new RendererPintorJTable());

        //RENDERER (qtd. comentários)
        l_listaArquivos.getColumnModel().getColumn(5).setCellRenderer(new RendererPintorJTable());

        //instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
        DefaultTableModel modelo = (DefaultTableModel) l_listaArquivos.getModel();
        modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

        //cb_lista_amigos.removeAllItems();
        Data.atualizaDataHora();

        try {

            //do usuario
            if(l_listaArquivos.equals(Moniersn.jt_listaArquivosUsuario)){

                LogUsuario.s_listaArquivoSelecionada = l_listaArquivoSelecionada;
           
                
                if(!LogUsuario.s_quemMeEnviaArquivo.equals("ninguem")){
                
                    if(l_listaArquivoSelecionada.equals("disponiveis")){

                        l_dePara = Avisos.TEXTO_DE;
                        l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_ARQ_DISP;

                        l_podeExecutar = true;

                        if(LogUsuario.s_quemMeEnviaArquivo.equals("amigos")){

                            //ARQUIVOS DISPONIVEIS
                            l_sqlSelect = "SELECT u.moniersn, u.icone_humor, a.* "
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
                                     + "AND a.nome LIKE '%"+l_conteudoPesquisado+"%' "
                                     + "AND u.ativo = 'S' "
                                     + "AND u.status_contato = 'conectado' "
                                     + "AND a.gravado = 'S' "
                                     + "AND a.cod_emissor != "+LogUsuario.s_cod+" "
                                     + "GROUP BY a.nome "
                                     + "ORDER BY a.data_hora_atu "
                                     + "DESC "
                                     + "LIMIT 0, "+LogUsuario.s_qtdArqAMostrar;
                        }

                        else if (LogUsuario.s_quemMeEnviaArquivo.equals("todos")){

                          //ARQUIVOS DISPONIVEIS
                          l_sqlSelect = "SELECT u.moniersn, u.icone_humor, a.* "
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
                                                                +"FROM arquivo "
                                                                +"WHERE cod_destinatario = "+LogUsuario.s_cod+" "
                                                                +"OR cod_destinatario = 0) "
                                      + "AND u.cod_usuario = a.cod_emissor "
                                      + "AND a.nome LIKE '%"+l_conteudoPesquisado+"%' "
                                      + "AND u.ativo = 'S' "
                                      + "AND u.status_contato = 'conectado' "
                                      + "AND a.gravado = 'S' "
                                      + "AND a.cod_emissor != "+LogUsuario.s_cod+" "
                                      + "GROUP BY a.nome "
                                      + "ORDER BY a.data_hora_atu "
                                      + "DESC "
                                      + "LIMIT 0, "+LogUsuario.s_qtdArqAMostrar;
                        }
                    }
                }   //FIM CASO QUEM ME ENVIA ARQUIVO == ninguem
                    else{

                        //caso = ninguem, não executa query
                        l_podeExecutar = false;

                        //ZERA AS LINHAS
                        modelo.setNumRows(0);

                        //SETA 0 EM TODOS RECADOS
                        Moniersn.rb_todosArquivosDisponiveis.setText("Disponíveis (0)");

                    }

                 //ARQUIVOS BAIXADOS
                if(l_listaArquivoSelecionada.equals("baixados")){

                    l_dePara = Avisos.TEXTO_DE;
                    l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_ARQ_BAIX;

                     l_podeExecutar = true;

                      l_sqlSelect = "SELECT u.moniersn, u.icone_humor, a.*, au.data_hora_recebido "
                                  + "FROM usuario u, arquivo a, arquivo_usuario au "
                                  + "WHERE u.cod_usuario = a.cod_emissor "
                                  + "AND a.cod_arquivo = au.cod_arquivo "
                                  + "AND a.nome LIKE '%"+l_conteudoPesquisado+"%' "
                                  + "AND au.gravado = 'S' "
                                  + "AND au.cod_usuario = "+LogUsuario.s_cod+" "
                                  + "AND a.cod_emissor != "+LogUsuario.s_cod+" "
                                  + "GROUP BY a.nome "
                                  + "ORDER BY a.data_hora_atu "
                                  + "DESC "
                                  + "LIMIT 0, "+LogUsuario.s_qtdArqAMostrar;

                }

                //ARQUIVOS ENVIADOS
                else if(l_listaArquivoSelecionada.equals("enviados")){

                    l_dePara = Avisos.TEXTO_PARA;
                    l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_ARQ_ENV;

                    l_podeExecutar = true;

                    l_sqlSelect = "SELECT u.moniersn, u.icone_humor, a.* "
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
                             + "AND a.nome LIKE '%"+l_conteudoPesquisado+"%' "
                             + "AND u.ativo = 'S' "
                             + "AND a.gravado = 'S' "
                             + "AND a.cod_emissor = "+LogUsuario.s_cod+" "
                             + "GROUP BY a.nome "
                             + "ORDER BY a.data_hora_atu "
                             + "DESC "
                             + "LIMIT 0, "+LogUsuario.s_qtdArqAMostrar;
                   }
             }


             //ARQUIVOS COMPARTILHADOS DO AMIGO
             else if(l_listaArquivos.equals(Moniersn.jt_listaArquivosAmigo)){


                if(!LogUsuario.s_quemMeEnviaArquivo.equals("ninguem")){

                    l_dePara = Avisos.TEXTO_PARA;
                    l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_ARQ_AMIGO+LogAmigo.s_moniersn;

                        l_podeExecutar = true;

                        if(LogUsuario.s_quemMeEnviaArquivo.equals("amigos")){

                            
                            //ARQUIVOS DISPONIVEIS
                            l_sqlSelect = "SELECT u.moniersn, u.icone_humor, a.* "
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
                                     + "AND a.nome LIKE '%"+l_conteudoPesquisado+"%' "
                                     + "AND u.ativo = 'S' "
                                     + "AND u.status_contato = 'conectado' "
                                     + "AND a.gravado = 'S' "
                                     + "AND a.cod_emissor = "+LogAmigo.s_cod+" "
                                     + "GROUP BY a.nome "
                                     + "ORDER BY a.data_hora_atu "
                                     + "DESC "
                                     + "LIMIT 0, "+LogAmigo.s_qtdArqAMostrar;
                            
                        }

                        else if (LogUsuario.s_quemMeEnviaArquivo.equals("todos")){

                          //ARQUIVOS DISPONIVEIS
                          l_sqlSelect = "SELECT u.moniersn, u.icone_humor, a.* "
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
                                                                +"FROM arquivo "
                                                                +"WHERE cod_destinatario = "+LogUsuario.s_cod+" "
                                                                +"OR cod_destinatario = 0) "
                                      + "AND u.cod_usuario = a.cod_emissor "
                                      + "AND a.nome LIKE '%"+l_conteudoPesquisado+"%' "
                                      + "AND u.ativo = 'S' "
                                      + "AND u.status_contato = 'conectado' "
                                      + "AND a.gravado = 'S' "
                                      + "AND a.cod_emissor = "+LogAmigo.s_cod+" "
                                      + "GROUP BY a.nome "
                                      + "ORDER BY a.data_hora_atu "
                                      + "DESC "
                                      + "LIMIT 0, "+LogAmigo.s_qtdArqAMostrar;
                        }

                }   //FIM CASO QUEM ME ENVIA ARQUIVO == ninguem
                    else{
                        //caso = ninguem, não executa query
                        l_podeExecutar = false;

                    }
            }

            if(l_podeExecutar){

                Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                    //CASO TENHA RESULTADO
                    if(Dados.s_conexaoBanco.c_resultset.first()){

                        
                       if(l_listaArquivos.equals(Moniersn.jt_listaArquivosUsuario))
                            Moniersn.tf_nomeArqPesquisado.setForeground(new java.awt.Color(0, 0, 255));
                       else
                            Moniersn.tf_nomeArqPesquisadoAmigo.setForeground(new java.awt.Color(0, 0, 255));


                        do{
                                //caso disponiveis ou baixados
                                if(l_listaArquivoSelecionada.equals("disponiveis") ||
                                   l_listaArquivoSelecionada.equals("baixados")){
                                                                        
                                   l_apelido = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                                   l_humor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
                                }
                                    //caso enviados do usuário ou do amigo
                                   else{

                                    l_codDestinatario = Dados.s_conexaoBanco.c_resultset.getInt("a.cod_destinatario");

                                        if(l_codDestinatario == 0){
                                            l_apelido = Dados.s_conexaoBanco.c_resultset.getString("a.quem_baixa_arquivo");
                                            l_humor = l_apelido+"(geral)";
                                        }
                                        else{
                                            l_apelido = Dados.s_conexaoBanco.c_resultset.getString("a.quem_baixa_arquivo");
                                            l_humor = SolicitarDados.s_SolicitarDados.pegaHumorDoArrayHumorUsuarios(l_apelido);
                                        }

                                   }

                            l_codArquivo  = Dados.s_conexaoBanco.c_resultset.getInt("a.cod_arquivo");
                            l_nomeArquivo = Dados.s_conexaoBanco.c_resultset.getString("a.nome");
                            l_extensao    = Dados.s_conexaoBanco.c_resultset.getString("a.extensao");
                            l_tamanho     = Dados.s_conexaoBanco.c_resultset.getInt("a.tamanho");

                                if(!l_listaArquivoSelecionada.equals("baixados"))
                                    l_dataHora = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("a.data_hora_atu"));
                                else
                                    l_dataHora = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("au.data_hora_recebido"));

                            //Trata data para mostrar a quanto tempo foi enviado
                            l_quando      = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHora);

                            l_apelidoHumor = l_apelido+"|"+l_humor;

                            //add linha de ocorrencia
                            modelo.addRow(new Object[]{ l_apelidoHumor, l_extensao, l_nomeArquivo, l_tamanho, l_quando, String.valueOf(l_codArquivo)});

                            l_contadorArq++;

                        }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto tiver dados preenche o jtable com os valores
                    
                    }else{
                        
                        l_contadorArq = 0;
                        
                               if(l_listaArquivos.equals(Moniersn.jt_listaArquivosUsuario))
                                    Moniersn.tf_nomeArqPesquisado.setForeground(new java.awt.Color(255, 0, 0));
                               else
                                    Moniersn.tf_nomeArqPesquisadoAmigo.setForeground(new java.awt.Color(255, 0, 0));

               
                    }

            }else{
                 l_contadorArq = 0;

            }
            
                //caso lista do usuario
                if(l_listaArquivos.equals(Moniersn.jt_listaArquivosUsuario)){

                        if(l_conteudoPesquisado.isEmpty()){

                            if(l_listaArquivoSelecionada.equals("disponiveis"))
                                l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMOS_DISP;
                            if(l_listaArquivoSelecionada.equals("baixados"))
                                l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMOS_BAIX;
                            if(l_listaArquivoSelecionada.equals("enviados"))
                                l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_ENV;

                        }else{
                              l_textoPesquisa = Avisos.TEXTO_VISU_PESQ;
                        }

                    Moniersn.lb_qtdArqEncontrado.setText("("+l_contadorArq+") "+l_textoPesquisa);

                //caso lista do amigo
                }else{
                      if(l_conteudoPesquisado.isEmpty())
                          l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMOS_DISP;
                      else
                          l_textoPesquisa = Avisos.TEXTO_VISU_PESQ;

                      Moniersn.lb_qtdArqEncontradoAmigo.setText("("+l_contadorArq+") "+l_textoPesquisa);

                }
            

          //seta valor do header de ou para
          l_listaArquivos.setToolTipText(l_tipTextLista);
          //seta valor do header de ou para
          l_listaArquivos.getColumnModel().getColumn(0).setHeaderValue(l_dePara);
          //repinta o modelo da tabela
          l_listaArquivos.getTableHeader().resizeAndRepaint();


        }
        catch (Exception e) {

            if(l_listaArquivos.equals(Moniersn.jt_listaArquivosUsuario)){
                Moniersn.s_telaMsn.bt_encaminharArquivo.setEnabled(false);
                Moniersn.s_telaMsn.bt_abrirArquivoSelecionado.setEnabled(false);
                Moniersn.s_telaMsn.bt_baixarArquivoSelecionado.setEnabled(false);
                Moniersn.s_telaMsn.bt_removerArquivoSelecionado.setEnabled(false);
                Moniersn.s_telaMsn.bt_comentarArquivoSelecionadoUsuario.setEnabled(false);
            }
            if(l_listaArquivos.equals(Moniersn.jt_listaArquivosAmigo)){
                Moniersn.s_telaMsn.bt_baixarArquivoSelecionadoAmigo.setEnabled(false);
                 Moniersn.s_telaMsn.bt_comentarArquivoSelecionadoAmigo.setEnabled(false);
            }

            System.err.println("Erro ao tentar preencher a lista de arquivos.     \nErro: "+e);
        }

    }//fim preenche lista de ocorrências do usuário


    //preenche a lista de recados de acordo com parametro
    public void preencherListaRecadosPubAmigo(int l_codAmigo){

            String l_apelido = null;
            String l_humor = null;
            String l_apelidoHumor = null;
            String l_tipTextLista = null;
            String l_dePara = null;
            String l_conteudo = null;
            String l_dataHoraAtu = null;
            int l_codRecado = 0;
            int l_contadorRec = 0;
            String l_textoPesquisa = null;
            

            String l_quando = null;
            String l_sqlSelect = null;
            String l_conteudoPesquisado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(Moniersn.tf_conteudoRecPubPesquisadoAmigo.getText());

           
            Moniersn.jt_listaRecadosPubAmigo.getColumnModel().getColumn(1).setHeaderValue(Avisos.HEADER_LISTA_REC_PUB+LogAmigo.s_moniersn);

            //repinta o header (atualiza os novos títulos das colunas)
            Moniersn.jt_listaRecadosPubAmigo.getTableHeader().resizeAndRepaint();

            //ajusta a largura das colunas
            Moniersn.jt_listaRecadosPubAmigo.getColumnModel().getColumn(0).setPreferredWidth(50);
            Moniersn.jt_listaRecadosPubAmigo.getColumnModel().getColumn(1).setPreferredWidth(220);
            Moniersn.jt_listaRecadosPubAmigo.getColumnModel().getColumn(2).setPreferredWidth(70);
            Moniersn.jt_listaRecadosPubAmigo.getColumnModel().getColumn(3).setPreferredWidth(15);


            //RENDERER (quem)
            Moniersn.jt_listaRecadosPubAmigo.getColumnModel().getColumn(0).setCellRenderer(new RendererPintorJTable());

            //RENDERER (qtd. coments)
            Moniersn.jt_listaRecadosPubAmigo.getColumnModel().getColumn(3).setCellRenderer(new RendererPintorJTable());

            //instancia do objeto modelo da classe defaultTableModel para receber o modelo da minha tabela para manuzear-mos
            DefaultTableModel modelo = (DefaultTableModel) Moniersn.jt_listaRecadosPubAmigo.getModel();
            modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

            Data.atualizaDataHora();

                try {

                    l_tipTextLista = Avisos.TIP_TEXT_LISTA_DE_REC_PUB_ENV_POR+LogAmigo.s_moniersn;
                    l_dePara = Avisos.TEXTO_DE;

                            //QUEM_VE_REC_PUB = TODOS
                            if(LogUsuario.s_quemVeRecPub.equals("todos")){

                              //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM e Ñ AMIGOS Q SÓ AMIGOS VEEM LISTA REC. PUB.
                              l_sqlSelect = "SELECT u.moniersn, u.icone_humor, r.* "
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
                                         + "AND r.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                         + "AND u.ativo = 'S' "
                                         + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                         + "AND r.cod_destinatario = "+l_codAmigo+" "
                                         + "AND r.recado_destinatario_gravado = 'S' "
                                         + "AND r.tipo_recado = 'pub' "
                                         + "AND u.cod_usuario != "+l_codAmigo+" "
                                         + "ORDER BY r.data_hora_atu "
                                         + "DESC "
                                         + "LIMIT 0, "+LogAmigo.s_qtdRecPubAMostrar; 
                            }

                            //QUEM_VE_REC_PUB = AMIGOS
                            if(LogUsuario.s_quemVeRecPub.equals("amigos")){

                                  //SELECIONA RECADOS DE AMIGOS Q Ñ BLOQUEEI, Q Ñ ME BLOQUEARAM 
                                  l_sqlSelect = "SELECT u.moniersn, u.icone_humor, r.* "
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
                                             + "AND r.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                             + "AND u.ativo = 'S' "
                                             + "AND c.visualizar_lista_rec_pub != 'ninguem' "
                                             + "AND r.cod_destinatario = "+l_codAmigo+" "
                                             + "AND r.recado_destinatario_gravado = 'S' "
                                             + "AND r.tipo_recado = 'pub' "
                                             + "AND u.cod_usuario != "+l_codAmigo+" "
                                             + "ORDER BY r.data_hora_atu "
                                             + "DESC "
                                             + "LIMIT 0, "+LogAmigo.s_qtdRecPubAMostrar;
                            }


                    //caso recado publico recebido...
                    Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                        if(Dados.s_conexaoBanco.c_resultset.first()){

                            Moniersn.tf_conteudoRecPubPesquisadoAmigo.setForeground(new java.awt.Color(0, 0, 255));

                            
                            
                            do{
                                l_apelido = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                                l_humor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
                                l_conteudo = Dados.s_conexaoBanco.c_resultset.getString("r.conteudo");
                                l_dataHoraAtu = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("r.data_hora_atu"));
                                l_quando = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraAtu);
                                l_codRecado = Dados.s_conexaoBanco.c_resultset.getInt("r.cod_recado");

                                l_apelidoHumor = l_apelido+"|"+l_humor;

                                //vai add linhas
                                modelo.addRow(new Object[]{l_apelidoHumor, l_conteudo, l_quando, String.valueOf(l_codRecado)});

                                l_contadorRec++;

                                //adciona amigo na lista enquanto tiver
                                //cb_lista_amigos.addItem(Dados.s_conexaoBanco.c_resultset.getString("moniersn"));
                            }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto tiver dados preenche o jtable com os valores
                       
                        }else{
                    
                            l_contadorRec = 0;
                            Moniersn.tf_conteudoRecPubPesquisadoAmigo.setForeground(new java.awt.Color(255, 0, 0));
               
                        }

                                if(l_conteudoPesquisado.isEmpty()){

                                       l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_REC;

                                }else{
                                      l_textoPesquisa = Avisos.TEXTO_VISU_PESQ;
                                }

                  Moniersn.lb_qtdRecPubEncontradoAmigo.setText("("+l_contadorRec+") "+l_textoPesquisa);
                 
                  //seta valor do header de ou para
                  Moniersn.jt_listaRecadosPubAmigo.setToolTipText(l_tipTextLista);
                  //seta valor do header de ou para
                  Moniersn.jt_listaRecadosPubAmigo.getColumnModel().getColumn(0).setHeaderValue(l_dePara);
                  //repinta o modelo da tabela
                  Moniersn.jt_listaRecadosPubAmigo.getTableHeader().resizeAndRepaint();

                }
                catch (Exception e) {

                        System.err.println("Erro ao tentar mostrar recados públicos do amigo.     \nErro: "+e);
                        return;
                }

     }//fim preencher lista de recados

 public void preencherListaComentarios(int l_codConteudo, String l_tipoComentario){

            String l_anonimo = null;
            String l_apelido = null;
            String l_humor = null;
            String l_apelidoHumor = null;
            String l_comentario = null;
            String l_tipTextLista = null;
            String l_header = null;
            String l_quando;
            String l_dataHoraAtual;
            String l_dataHoraPost;
            int l_codComent;
            int l_contadorComents = 0;
            String l_textoPesquisa = null;
            String l_conteudoPesquisado = Comentario.tf_conteudoComentsPesquisado.getText();
            String l_sqlSelect = null;
            
            
            //ATUALIZA DATA
            Data.atualizaDataHora();

            //DATA ATUAL
            l_dataHoraAtual = Data.s_dataHoraAtualDDMMAAAAHHMMSS;

            //HEADER DA COLUNA 1
            Comentario.jt_listaComentarios.getColumnModel().getColumn(2).setHeaderValue("Comentários");

            //REPINTA O HEADER
            Comentario.jt_listaComentarios.getTableHeader().resizeAndRepaint();

            //AJUSTE DE LARGURA DAS COLUNAS
            Comentario.jt_listaComentarios.getColumnModel().getColumn(0).setPreferredWidth(10);
            Comentario.jt_listaComentarios.getColumnModel().getColumn(1).setPreferredWidth(70);
            Comentario.jt_listaComentarios.getColumnModel().getColumn(2).setPreferredWidth(250);
            Comentario.jt_listaComentarios.getColumnModel().getColumn(3).setPreferredWidth(90);


            //RENDERER (quem) e (cod)
            Comentario.jt_listaComentarios.getColumnModel().getColumn(0).setCellRenderer(new RendererPintorJTable());
            Comentario.jt_listaComentarios.getColumnModel().getColumn(1).setCellRenderer(new RendererPintorJTable());

            //MODELO DA TABELA
            DefaultTableModel modelo = (DefaultTableModel) Comentario.jt_listaComentarios.getModel();

            //NENHUMA LINHA NA TABELA
            modelo.setNumRows(0);//toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados

            try {

                                
                l_tipTextLista = Avisos.TIP_TEXT_LISTA_COMENT_DE+l_tipoComentario;

                l_sqlSelect = "SELECT u.moniersn, u.icone_humor, c.* "
                                                    +"FROM usuario u, comentario c "
                                                    + "WHERE u.cod_usuario = c.cod_usuario "
                                                    + "AND u.ativo = 'S' "
                                                    + "AND c.conteudo LIKE '%"+l_conteudoPesquisado+"%' "
                                                    + "AND c.cod_conteudo = "+l_codConteudo+" "
                                                    + "AND c.tipo_comentario = '"+l_tipoComentario+"' "
                                                    + "AND c.gravado = 'S' "
                                                    + "ORDER BY c.data_hora_post "
                                                    + "DESC "
                                                    + "LIMIT 0, "+LogUsuario.s_qtdComentAMostrar;
                
                
                Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);

                    //CASO EXISTA UMA LINHA DE COMENTÁRIO  NO BANCO...
                    if(Dados.s_conexaoBanco.c_resultset.first()){

                        Comentario.tf_conteudoComentsPesquisado.setForeground(new java.awt.Color(0, 0, 255));
                        mostraComentarios(true);

                        do{
                                //APELIDO
                                //TRATAMENTO DE COMENTÁRIO ANÔNIMO (determinação de apelido)
                                //CASO COMENTS DE OCORRÊCIA...
                                if(l_tipoComentario.equals("ocorrencia")){
                                    l_anonimo = Dados.s_conexaoBanco.c_resultset.getString("c.anonimo");

                                    //CASO ANÔNIMO...
                                    if(l_anonimo.equals("S")){
                                        l_apelido = "anonimo";
                                        l_humor = l_apelido;
                                    }
                                        //CASO NÃO ANÔNIMO...
                                        else{
                                            l_apelido = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                                            l_humor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
                                        }
                                }
                                    //CASO COMENTS RECADO, atu ou arq...
                                    else{
                                        l_apelido = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                                        l_humor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
                                    }

                                //COMENTÁRIO
                                l_comentario = Dados.s_conexaoBanco.c_resultset.getString("c.conteudo");

                                //TRATAMENTO DE DATA (quando)
                                l_dataHoraPost = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("c.data_hora_post"));
                                l_quando = Data.pegaIntervaloTempo(l_dataHoraAtual, l_dataHoraPost);

                                //COD_COMENTÁRIO
                                l_codComent = Dados.s_conexaoBanco.c_resultset.getInt("c.cod_comentario");

                                l_apelidoHumor = l_apelido+"|"+l_humor;

                                //ADD LINHA NA TABELA
                                modelo.addRow(new Object[]{l_codComent, l_apelidoHumor, l_comentario, l_quando});
                               
                                l_contadorComents++;
                                
                        //CASO CONTENHA UMA PROXIMA LINHA DE REGISTRO... (novo loop)
                        }while(Dados.s_conexaoBanco.c_resultset.next());
                    
                    }else{
                    
                        l_contadorComents = 0;
                        Comentario.tf_conteudoComentsPesquisado.setForeground(new java.awt.Color(255, 0, 0));
               
                    }

                            if(l_conteudoPesquisado.isEmpty())
                                 l_textoPesquisa = Avisos.TEXTO_VISU_ULTIMAS_COMENTS_REC;
                            else
                                 l_textoPesquisa = Avisos.TEXTO_VISU_PESQ;
                            
                        Comentario.lb_qtdComentsEncontrada.setText("("+l_contadorComents+") "+l_textoPesquisa);
                        Comentario.jt_listaComentarios.setToolTipText(l_tipTextLista); 


            }

            catch (Exception e) {
                    System.err.println("Erro ao tentar mostrar comentários. Cod: rec/oco/atu: "+l_codConteudo+".     \nErro: "+e);
                    return;
            }
        

    }

    //preenche a lista de amigos do usuário
    public void preencherListaDeAmigosBatePapo(){

        
            
        
            int l_contadorAmigos = 0;
            String sql = null;

            String l_apelido = null;
            String l_humor = null;
            int l_codUsuario = 0;
            String l_status = null;
            int l_codStatus = 0;
            String l_apelidoHumor = null;
            String l_codUsuarioStatus = null;
            
            
            if(!LogUsuario.s_quemBatePapoComigo.equals("ninguem")){
            
                String l_apelidoPesquisado = Moniersn.tf_pesquisaUsuarioBatePapo.getText().toLowerCase();
                       l_apelidoPesquisado = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_apelidoPesquisado);

                //RENDERER (quem)
                Moniersn.jt_listaAmigosConectados.getColumnModel().getColumn(0).setCellRenderer(new RendererPintorJTable());

                //RENDERER (TIPO AMIGO)
                Moniersn.jt_listaAmigosConectados.getColumnModel().getColumn(1).setCellRenderer(new RendererPintorJTable());

                //ajusta a largura das colunas 0 e 1
                Moniersn.jt_listaAmigosConectados.getColumnModel().getColumn(0).setPreferredWidth(130);
                Moniersn.jt_listaAmigosConectados.getColumnModel().getColumn(1).setPreferredWidth(20);

                //pega o model (dados) da minha tabela lista de amigos
                DefaultTableModel modelo = (DefaultTableModel) Moniersn.jt_listaAmigosConectados.getModel();

                //toda vez que o metodo é chamado ele zera o num de lihas para preecher logo abaixo com novos dados
                modelo.setNumRows(0);


            //          SELECIONA TODOS AMIGOS, EXCETO(OS QUE BLOQUEEI E OS QUE ME BLOQUEARAM)
                        if(LogUsuario.s_quemBatePapoComigo.equals("amigos")){


                                sql = "SELECT u.cod_usuario, u.moniersn, u.icone_humor, u.status_contato "
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
                                     + "AND u.moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.ativo = 'S' "
                                     + "AND c.quem_bate_papo_comigo != 'ninguem' "
                                     + "AND u.status_contato = 'conectado' "
                                     + "AND u.cod_usuario != "+LogUsuario.s_cod+" "
                                     + "ORDER BY u.status_contato ASC, u.moniersn ASC "
                                     + "LIMIT 0, "+LogUsuario.s_qtdUsuBPMostrar;  


                        }
            //          SELECIONA TODOS USUARIO , EXCETO(OS Q BLOQUEEI, OS QUE ME BLOQUEARAM E OS NÃO AMIGOS QUE SO BATEM PAPO COM AMIGOS)
                        else if(LogUsuario.s_quemBatePapoComigo.equals("todos")){

                                sql = "SELECT u.cod_usuario, u.moniersn, u.icone_humor, u.status_contato "
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
                                     + "AND u.moniersn LIKE '%"+l_apelidoPesquisado+"%' "
                                     + "AND u.cod_usuario = c.cod_usuario "
                                     + "AND u.ativo = 'S' "
                                     + "AND c.quem_bate_papo_comigo != 'ninguem' "
                                     + "AND u.status_contato = 'conectado' "
                                     + "AND u.cod_usuario != "+LogUsuario.s_cod+" "
                                     + "ORDER BY u.status_contato ASC, u.moniersn ASC "
                                     + "LIMIT 0, "+LogUsuario.s_qtdUsuBPMostrar;
                        }

                        Dados.s_conexaoBanco.executeSELECT(sql);

                            try{
                                //se conseguir ir para a primeira linha do c_resultset...
                                if(Dados.s_conexaoBanco.c_resultset.first()){

                                    Moniersn.tf_pesquisaUsuarioBatePapo.setForeground(new java.awt.Color(0, 0, 255));


                                        do{

                                                l_apelido = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                                                l_humor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
                                                l_codUsuario = Dados.s_conexaoBanco.c_resultset.getInt("u.cod_usuario");
                                                l_status  =  Dados.s_conexaoBanco.c_resultset.getString("u.status_contato");

                                                    //testa status
                                                    if(l_status.equals("conectado"))
                                                        l_codStatus = 1;
                                                    else
                                                        l_codStatus = 0;
                                                    
                                                
                                                l_apelidoHumor = l_apelido+"|"+l_humor;
                                                l_codUsuarioStatus = l_codUsuario+"|"+l_codStatus;

                                                //add linha ao modelo da minha tabela
                                                modelo.addRow(new Object[]{l_apelidoHumor, l_codUsuarioStatus});

                                                //incrementando 1
                                                l_contadorAmigos++;

                                        //enquanto o c_resultset apresentar dados...
                                        }while(Dados.s_conexaoBanco.c_resultset.next());//enquanto tiver dados preenche o jtable com os valores

                                    //habilita campos de envio de rec inst
                                    Moniersn.tf_recadoInst.setEditable(true); 
                                    Moniersn.bt_enviarRecInst.setEnabled(true);
                                    Moniersn.bt_limpaCampoRecInst.setEnabled(true); 

                                 }
                                    //CASO NAO TENHA RESULTSET
                                    else{

                                        l_contadorAmigos = 0;
                                        Moniersn.tf_pesquisaUsuarioBatePapo.setForeground(new java.awt.Color(255, 0, 0));

                                        //desabilita campos de envio de rec inst
                                        Moniersn.tf_recadoInst.setEditable(false); 
                                        Moniersn.bt_enviarRecInst.setEnabled(false);
                                        Moniersn.bt_limpaCampoRecInst.setEnabled(false); 

                                    }

                                    Moniersn.lb_qtdUsuarioListaBatePapo.setText("("+l_contadorAmigos+")");
                                    Moniersn.lb_qtdUsuarioListaBatePapo.setToolTipText("("+l_contadorAmigos+") "+Avisos.TEXTO_PESSOAS_NA_LISTA); 

                            }
                            //caso ocorra uma exceçao...
                            catch (Exception e){

                                Moniersn.lb_qtdRecInst.setVisible(false);
                                LogUsuario.s_qtdRecInstRec = 0;

                                //desabilita campos de envio de rec inst
                                Moniersn.tf_recadoInst.setEditable(false); 
                                Moniersn.bt_enviarRecInst.setEnabled(false);
                                Moniersn.bt_limpaCampoRecInst.setEnabled(false); 

                                System.err.println("Erro ao tentar preencher lista de amigos do bate papo.    \nErro: "+e);
                            }

            }else{

                l_contadorAmigos = 0;
                Moniersn.tf_pesquisaUsuarioBatePapo.setForeground(new java.awt.Color(255, 0, 0));

                //desabilita campos de envio de rec inst
                Moniersn.tf_recadoInst.setEditable(false); 
                Moniersn.bt_enviarRecInst.setEnabled(false);
                Moniersn.bt_limpaCampoRecInst.setEnabled(false); 


                Moniersn.lb_qtdUsuarioListaBatePapo.setText("("+l_contadorAmigos+")");
                Moniersn.lb_qtdUsuarioListaBatePapo.setToolTipText("("+l_contadorAmigos+") "+Avisos.TEXTO_PESSOAS_NA_LISTA); 

            }  
    }

    public void mostraTelaComentarios(int l_codConteudo, String l_tipoConteudo, JTable l_tabela){ 

        
            //logo carregando...
            new Thread(new LogoCarregando()).start();
        
            int l_qtdComents = 0;


            //EMISSOR
            int l_codEmissor = 0;
            String l_apelidoEmissor      = null;
            String l_nomeEmissor         = null;
            String l_sobrenomeEmissor    = null;
            String l_mostrarNomeEmissor  = null;
            String l_mostrarNomeDestinatario = null;
            String l_iconeHumorEmissor   = null;

            //DESTINATARIO
            int l_codDestinatario = 0;
            String l_nomeDestinatario = null;
            String l_apelidoDestinatario  = null;
            String l_iconeHumorDestinatario  = null;

             //RECADO
            String l_tipoRecado = null;

            //CONTEUDO
            String l_conteudo= null;
            String l_comenteSobreConteudo = null;
            String l_tituloBordaEmissor = null;
            String l_tituloBordaDestinatario = null;
            String l_iconeConteudo = null;
            String l_ocorrenciaAnonima = "N";
            String l_caminhoTitulo = LogUsuario.s_nome+" "+LogUsuario.s_sobrenome+" | ";
            String l_iconeQtdComentario = null;
            String l_iconeBtVoltar = null;


            //PRIVACIDADE(quem visu conteudo, )
            String l_quemVisu = null;
            String l_descricaoPrivConteudo = null;

            //ATUALIZACAO(perfil atualizado, atributo atualizado)
            String l_perfilAtu = null;
            String l_atributoAtu = null;
            String l_atu_de = null;


            //ARQUIVOS
            String l_quemBaixaArquivo = null;
            String l_extensao = null;
            int l_tamanho = 0;
            String l_tipoArquivo = null;

            //ANONIMO(caso true habilita o cb_anonimo)
            boolean l_comentarioAnonimo = false;

            //DATA (dt postagem conteudo, data ult. atu. conteudo)
            String l_dataHoraPost = null;
            String l_dataHoraAtu = null;

            String l_data = null;
            String l_hora = null;
            String l_dataHora = null;
            String l_dataPostagem = null;
            String l_dataUltimaAtu = null;

            //CONSULTA SQL
            String l_sql = null;


                //DEFINE A TABELA QUE SERA CONSULTADA
                if(l_tipoConteudo.equals("recado")){

                    //SQL
                    l_sql = "SELECT u.cod_usuario, u.moniersn, u.nome, u.sobrenome, u.icone_humor, r.* "
                          + "FROM usuario u, recado r "
                          + "WHERE u.cod_usuario = r.cod_emissor "
                          + "AND u.ativo = 'S' "
                          + "AND r.cod_recado = "+l_codConteudo;

                    Dados.s_conexaoBanco.executeSELECT(l_sql);

                    try{

                        Dados.s_conexaoBanco.c_resultset.first();

//                      ATALIZA DATA
                        Data.atualizaDataHora();

//                      EMISSOR
                        l_codEmissor          = Dados.s_conexaoBanco.c_resultset.getInt("u.cod_usuario");
                        l_apelidoEmissor      = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                        l_nomeEmissor         = Dados.s_conexaoBanco.c_resultset.getString("u.nome");
                        l_sobrenomeEmissor    = Dados.s_conexaoBanco.c_resultset.getString("u.sobrenome");
                        l_iconeHumorEmissor   = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");

//                      COD. DESTINATARIO
                        l_codDestinatario     = Dados.s_conexaoBanco.c_resultset.getInt("r.cod_destinatario");

                        l_tipoRecado          = Dados.s_conexaoBanco.c_resultset.getString("r.tipo_recado");
                        l_conteudo            = Dados.s_conexaoBanco.c_resultset.getString("r.conteudo");

                        //POSTAGEM
                        l_dataHoraPost        = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("r.data_hora_post"));
                        l_dataPostagem        = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraPost);

                        //ATUALIZAÇÃO
                        l_dataHoraAtu         = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("r.data_hora_atu"));
                        l_dataUltimaAtu       = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraAtu);

                        //DESTINATARIO
                        l_apelidoDestinatario = SolicitarDados.pegaApelidoPeloCodigo(l_codDestinatario);
                        l_iconeHumorDestinatario = SolicitarDados.s_SolicitarDados.buscaIconeHumorBD(l_apelidoDestinatario);

                        //RECADO NAO PODE SER ANONIMO
                        l_comentarioAnonimo = false;

                        //MOSTRAR NOME
                        l_mostrarNomeEmissor =  SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "mostrar_nome");
                        l_mostrarNomeDestinatario =  SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codDestinatario, "mostrar_nome");

                        if(l_mostrarNomeDestinatario.equals("S"))
                            l_nomeDestinatario = SolicitarDados.s_SolicitarDados.pegaNomeCompleto(l_codDestinatario);

                            if(l_tipoRecado.equals("pub")){

                                l_caminhoTitulo += Avisos.TIT_JAN_COMENT_REC_PUB;
                                l_iconeQtdComentario = Icones.COMENT_REC_PUB;
                                l_tituloBordaEmissor = Avisos.TEXTO_EMISSOR_REC_PUB;
                                l_tituloBordaDestinatario = Avisos.TEXTO_PARA;
                                
                                //CONTEUDO
                                l_comenteSobreConteudo = Avisos.COMENTE_REC_PUB;
                                l_iconeConteudo = Icones.REC_PUB_16;
                                l_tituloBordaEmissor = Avisos.TEXTO_EMISSOR_REC_PUB;
                                l_iconeBtVoltar = Icones.REC_PUB_NOVO_16;

                                //PRIVACIDADE
                                l_quemVisu    = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codDestinatario, "visualizar_lista_rec_pub");

                                    if(l_quemVisu.equals("todos"))
                                        l_descricaoPrivConteudo = Avisos.TEXTO_TODOS_COMENT;
                                    if(l_quemVisu.equals("amigos"))
                                        l_descricaoPrivConteudo = Avisos.TEXTO_AMIGOS_COMENT_REC_PUB;

                            }

                            if(l_tipoRecado.equals("priv")){

                                l_caminhoTitulo += Avisos.TIT_JAN_COMENT_REC_PRIV;
                                l_iconeQtdComentario = Icones.COMENT_REC_PRIV;
                                l_tituloBordaEmissor = Avisos.TEXTO_EMISSOR_REC_PRIV;
                                l_tituloBordaDestinatario = Avisos.TEXTO_PARA;
                                
                                //CONTEUDO
                                l_comenteSobreConteudo = Avisos.COMENTE_REC_PRIV;
                                l_iconeConteudo = Icones.REC_PRIV_16;
                                l_tituloBordaEmissor = Avisos.TEXTO_EMISSOR_REC_PRIV;
                                l_iconeBtVoltar = Icones.REC_PRIV_NOVO_16;

                                //PRIVACIDADE
                                l_descricaoPrivConteudo = Avisos.TEXTO_EMIS_DEST_VISU_E_COMENT;

                            }

                    }catch(Exception e){
                        System.err.println("Erro ao tentar mostrar a tela de comentario de recado.    \nErro: "+e);
                        //esconde tela carregando...
                        Aguardando.s_telaAguardando.setVisible(false);         
                        //TELA FACECARD
                        Moniersn.s_telaMsn.setVisible(true);

                    }
                }


                //DEFINE A TABELA QUE SERA CONSULTADA
                if(l_tipoConteudo.equals("ocorrencia")){

                    
                    l_caminhoTitulo += Avisos.TIT_JAN_COMENT_OCO;
                    l_tituloBordaEmissor = Avisos.TEXTO_EMISSOR_OCO;
                    l_tituloBordaDestinatario = Avisos.TEXTO_PARA;

                    //SQL
                    l_sql = "SELECT u.cod_usuario, u.moniersn, u.nome, u.sobrenome, u.icone_humor, o.* "
                          + "FROM usuario u, ocorrencia o "
                          + "WHERE u.cod_usuario = o.cod_emissor "
                          + "AND u.ativo = 'S' "
                          + "AND o.cod_ocorrencia = "+l_codConteudo;

                    Dados.s_conexaoBanco.executeSELECT(l_sql);

                    try{

                        Dados.s_conexaoBanco.c_resultset.first();

//                      ATALIZA DATA
                        Data.atualizaDataHora();

//                      EMISSOR
                        l_codEmissor          = Dados.s_conexaoBanco.c_resultset.getInt("u.cod_usuario");
                        l_apelidoEmissor      = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                        l_nomeEmissor         = Dados.s_conexaoBanco.c_resultset.getString("u.nome");
                        l_sobrenomeEmissor    = Dados.s_conexaoBanco.c_resultset.getString("u.sobrenome");
                        l_iconeHumorEmissor   = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");

                        l_conteudo            = Dados.s_conexaoBanco.c_resultset.getString("o.conteudo");
                        l_ocorrenciaAnonima   = Dados.s_conexaoBanco.c_resultset.getString("o.anonimo");

                        //POSTAGEM
                        l_dataHoraPost        = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("o.data_hora_post"));
                        l_dataPostagem        = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraPost);

                        //ATUALIZAÇÃO
                        l_dataHoraAtu        = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("o.data_hora_atu"));
                        l_dataUltimaAtu       = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraAtu);


                        //OCORRENCIA PODE SER ANONIMO
                        l_comentarioAnonimo = true;

                        //MOSTRAR NOME
                        l_mostrarNomeDestinatario = "N";

                        if(l_ocorrenciaAnonima.equals("S"))
                            l_apelidoEmissor = Avisos.TEXTO_ANONIMO;


                        //CONTEUDO
                        l_comenteSobreConteudo = Avisos.COMENTE_OCO;
                        l_iconeConteudo = Icones.OCORRENCIA_16;
                        l_iconeQtdComentario = Icones.COMENT_OCO;
                        l_iconeBtVoltar = Icones.OCORRENCIA_NOVO_16;

                        //PRIVACIDADE
                        l_mostrarNomeEmissor =  SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "mostrar_nome");
                        l_quemVisu = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "visualizar_ocorrencia");

                            if(l_quemVisu.equals("todos")){
                                l_apelidoDestinatario = Avisos.TEXTO_TODOS_GERAL;
                                l_descricaoPrivConteudo  = Avisos.TEXTO_TODOS_COMENT;
                            }
                            if(l_quemVisu.equals("amigos")){
                                l_apelidoDestinatario = Avisos.TEXTO_AMIGOS_GERAL;
                                l_descricaoPrivConteudo  = Avisos.TEXTO_AMIGOS_COMENT;
                            }
                            if(l_quemVisu.equals("ninguem")){
                                l_apelidoDestinatario = Avisos.TEXTO_NINGUEM;
                                l_descricaoPrivConteudo  = Avisos.TEXTO_NINGUEM_VISU_COMENT;;
                            }

                            
                    }catch(Exception e){
                        System.err.println("Erro ao tentar mostrar a tela de comentario de ocorrência.     \nErro: "+e);
                        //esconde tela carregando...
                        Aguardando.s_telaAguardando.setVisible(false);         
                        //TELA FACECARD
                        Moniersn.s_telaMsn.setVisible(true); 
                    }
                }

                //DEFINE A TABELA QUE SERA CONSULTADA
                if(l_tipoConteudo.equals("atualizacao")){

                    l_caminhoTitulo += Avisos.TIT_JAN_COMENT_ATU;
                    l_tituloBordaEmissor = Avisos.TEXTO_EMISSOR_ATU;
                    l_tituloBordaDestinatario = Avisos.TEXTO_PARA;

                    //SQL
                    l_sql = "SELECT u.cod_usuario, u.moniersn, u.nome, u.sobrenome, u.icone_humor, a.* "
                          + "FROM usuario u, atualizacoes_usuario a "
                          + "WHERE u.cod_usuario = a.cod_usuario "
                          + "AND u.ativo = 'S' "
                          + "AND a.cod_atu = "+l_codConteudo;

                    Dados.s_conexaoBanco.executeSELECT(l_sql);

                    try{

                        Dados.s_conexaoBanco.c_resultset.first();

//                      ATALIZA DATA
                        Data.atualizaDataHora();

//                      EMISSOR
                        l_codEmissor          = Dados.s_conexaoBanco.c_resultset.getInt("u.cod_usuario");
                        l_apelidoEmissor      = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                        l_nomeEmissor         = Dados.s_conexaoBanco.c_resultset.getString("u.nome");
                        l_sobrenomeEmissor    = Dados.s_conexaoBanco.c_resultset.getString("u.sobrenome");
                        l_iconeHumorEmissor   = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");

                        l_conteudo            = Dados.s_conexaoBanco.c_resultset.getString("a.conteudo");

                        //PERIL E ATRIBUTO
                        l_perfilAtu           = Dados.s_conexaoBanco.c_resultset.getString("a.perfil");
                        l_atributoAtu         = Dados.s_conexaoBanco.c_resultset.getString("a.atributo");

                        //POSTAGEM
                        l_dataHoraPost        = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("a.data_hora_post"));
                        l_dataPostagem        = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraPost);

                        //ATUALIZAÇÃO
                        l_dataHoraAtu         = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("a.data_hora_atu"));
                        l_dataUltimaAtu       = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraAtu);

                        //RECADO NAO PODE SER ANONIMO
                        l_comentarioAnonimo = false;

                        //MOSTRAR NOME
                        l_mostrarNomeEmissor = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "mostrar_nome");
                        l_mostrarNomeDestinatario = "N";

                        //ICONE DO CONTEUDO
                        l_iconeConteudo = Icones.ATUALIZAR_16;
                        l_iconeQtdComentario = Icones.COMENT_ATU;
                        l_iconeBtVoltar = Icones.NOVA_ATUALIZACAO_16;


                                //CASO PERFIL BÁSICO ATUALIZADO
                                if(l_perfilAtu.equals("basico")){

                                    //CONTEUDO
                                    l_comenteSobreConteudo = Avisos.COMENTE_ATU_BASICO;

                                    //PRIVACIDADE
                                    l_quemVisu = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "visualizar_info_basicas");


                                        if(l_atributoAtu.equals("nome") || l_atributoAtu.equals("sobrenome")){
                                            l_atu_de = "nome";
                                        }
                                        if(l_atributoAtu.equals("minha_frase")){
                                            l_atu_de = "frase";
                                        }
                                        if(l_atributoAtu.equals("icone_humor")){
                                            l_atu_de = "humor";
                                        }

                                            if(l_quemVisu.equals("todos")){
                                                l_apelidoDestinatario = Avisos.TEXTO_TODOS_GERAL;
                                                l_descricaoPrivConteudo  = Avisos.TEXTO_TODOS_COMENT;
                                            }
                                            else {
                                                l_apelidoDestinatario = Avisos.TEXTO_AMIGOS_GERAL;
                                                l_descricaoPrivConteudo  = Avisos.TEXTO_AMIGOS_COMENT;

                                            }
                                }

                                //CASO COMEÇOU AMIZADE COM ALGUEM
                                if(l_perfilAtu.equals("amigos")){

                                    l_comenteSobreConteudo = Avisos.COMENTE_NOVA_AMIZADE;
                                    l_tituloBordaEmissor = Avisos.TEXTO_NOVA_AMIZADE_DE;
                                    l_tituloBordaDestinatario = Avisos.TEXTO_NOVA_AMIZADE_COM;
 
                                    l_apelidoDestinatario = SolicitarDados.s_SolicitarDados.pegaApelidoDeAtuNovaAmizade(l_conteudo);
                                    l_codDestinatario = SolicitarDados.pegaCodigoPeloApelido(l_apelidoDestinatario);

                                    //modifica conteudo add apelido de quem add
                                    l_conteudo = l_apelidoEmissor+l_conteudo;

                                    //PRIVACIDADE
                                    l_mostrarNomeDestinatario = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codDestinatario, "mostrar_nome");
                                        if(l_mostrarNomeDestinatario.equals("S"))
                                            l_nomeDestinatario = SolicitarDados.s_SolicitarDados.pegaNomeCompleto(l_codDestinatario);

                                    l_quemVisu = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "visualizar_lista_amigos");

                                        if(l_quemVisu.equals("todos")){
                                            l_descricaoPrivConteudo  = Avisos.TEXTO_TODOS_COMENT;
                                        }
                                        else{
                                            l_descricaoPrivConteudo  = Avisos.TEXTO_AMIGOS_COMENT;
                                        }
                                }

                                if(l_perfilAtu.equals("pessoal")){


                                    l_comenteSobreConteudo = Avisos.COMENTE_ATU_PESSOAL;

                                    //PRIVACIDADE
                                    l_quemVisu = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "visualizar_info_pessoal");

                                        if(l_quemVisu.equals("todos")){
                                            l_apelidoDestinatario = Avisos.TEXTO_TODOS_GERAL;
                                            l_descricaoPrivConteudo  = Avisos.TEXTO_TODOS_COMENT;
                                        }
                                        else{
                                            l_apelidoDestinatario = Avisos.TEXTO_AMIGOS_GERAL;
                                            l_descricaoPrivConteudo  = Avisos.TEXTO_AMIGOS_COMENT;
                                        }


                                }
                                if(l_perfilAtu.equals("contato")){

                                    l_comenteSobreConteudo = Avisos.COMENTE_ATU_CONTATO;

                                    //PRIVACIDADE
                                    l_quemVisu = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "visualizar_info_contato");

                                        if(l_quemVisu.equals("todos")){
                                            l_apelidoDestinatario = Avisos.TEXTO_TODOS_GERAL;
                                            l_descricaoPrivConteudo  = Avisos.TEXTO_TODOS_COMENT;
                                        }
                                        else{
                                            l_apelidoDestinatario = Avisos.TEXTO_AMIGOS_GERAL;
                                            l_descricaoPrivConteudo  = Avisos.TEXTO_AMIGOS_COMENT;
                                        }

                                }
                                if(l_perfilAtu.equals("educacional")){

                                    l_comenteSobreConteudo = Avisos.COMENTE_ATU_EDUC;

                                    //PRIVACIDADE
                                    l_quemVisu = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "visualizar_info_educ");

                                        if(l_quemVisu.equals("todos")){
                                            l_apelidoDestinatario = Avisos.TEXTO_TODOS_GERAL;
                                            l_descricaoPrivConteudo  = Avisos.TEXTO_TODOS_COMENT;
                                        }
                                        else{
                                            l_apelidoDestinatario = Avisos.TEXTO_AMIGOS_GERAL;
                                            l_descricaoPrivConteudo  = Avisos.TEXTO_AMIGOS_COMENT;
                                        }

                                }
                                if(l_perfilAtu.equals("profissional")){

                                    l_comenteSobreConteudo = Avisos.COMENTE_ATU_PROF;

                                    //PRIVACIDADE
                                    l_quemVisu = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "visualizar_info_prof");

                                        if(l_quemVisu.equals("todos")){
                                            l_apelidoDestinatario = Avisos.TEXTO_TODOS_GERAL;
                                            l_descricaoPrivConteudo  =  Avisos.TEXTO_TODOS_COMENT;
                                        }
                                        else{
                                            l_apelidoDestinatario = Avisos.TEXTO_AMIGOS_GERAL;
                                            l_descricaoPrivConteudo  = Avisos.TEXTO_AMIGOS_COMENT;
                                        }
                                }

                    }catch(Exception e){
                        System.err.println("Erro ao tentar mostrar a tela de comentario de atualização de asuário.     \nErro: "+e);
                        //esconde tela carregando...
                        Aguardando.s_telaAguardando.setVisible(false);         
                        //TELA FACECARD
                        Moniersn.s_telaMsn.setVisible(true);    
                    }
            }

                //DEFINE A TABELA QUE SERA CONSULTADA
                if(l_tipoConteudo.equals("arquivo")){

                    l_caminhoTitulo += Avisos.TIT_JAN_COMENT_ARQ;
                    l_tituloBordaEmissor = Avisos.TEXTO_EMISSOR_ARQ;
                    l_tituloBordaDestinatario = Avisos.TEXTO_PARA;

                    //SQL
                    l_sql = "SELECT u.cod_usuario, u.moniersn, u.nome, u.sobrenome, u.icone_humor, a.* "
                          + "FROM usuario u, arquivo a "
                          + "WHERE u.cod_usuario = a.cod_emissor "
                          + "AND u.ativo = 'S' "
                          + "AND a.cod_arquivo = "+l_codConteudo;

                    Dados.s_conexaoBanco.executeSELECT(l_sql);

                    try{

                        Dados.s_conexaoBanco.c_resultset.first();

//                      ATALIZA DATA
                        Data.atualizaDataHora();

//                      EMISSOR
                        l_codEmissor          = Dados.s_conexaoBanco.c_resultset.getInt("u.cod_usuario");
                        l_apelidoEmissor      = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                        l_nomeEmissor         = Dados.s_conexaoBanco.c_resultset.getString("u.nome");
                        l_sobrenomeEmissor    = Dados.s_conexaoBanco.c_resultset.getString("u.sobrenome");
                        l_iconeHumorEmissor   = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
//                      DESTINATARIO
                        l_codDestinatario     = Dados.s_conexaoBanco.c_resultset.getInt("a.cod_destinatario");

                        l_quemBaixaArquivo    = Dados.s_conexaoBanco.c_resultset.getString("a.quem_baixa_arquivo");
                        l_extensao            = Dados.s_conexaoBanco.c_resultset.getString("a.extensao");
                        l_tamanho             = Dados.s_conexaoBanco.c_resultset.getInt("a.tamanho");
                        l_conteudo            = Dados.s_conexaoBanco.c_resultset.getString("a.nome");


                        //POSTAGEM
                        l_dataHoraPost        = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("a.data_hora_post"));
                        l_dataPostagem        = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraPost);

                        //ATUALIZAÇÃO
                        l_dataHora            = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("a.data_hora_atu"));
                        l_dataUltimaAtu       = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHora);

                        //ARQUIVO NAO PODE SER ANONIMO
                        l_comentarioAnonimo = false;

                        //MOSTRAR NOME
                        l_mostrarNomeEmissor      =  SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "mostrar_nome");

                        //CONTEUDO
                        l_comenteSobreConteudo = Avisos.COMENTE_ARQUIVO;
                        l_iconeConteudo = SolicitarDados.s_SolicitarDados.pegaIconeOuTipoExtensao(l_extensao, "icone");
                        l_tipoArquivo =  SolicitarDados.s_SolicitarDados.pegaIconeOuTipoExtensao(l_extensao, "tipo");
                        l_iconeQtdComentario = Icones.COMENT_ARQ;
                        l_iconeBtVoltar = Icones.ARQUIVO_NOVO;

                        l_conteudo = SolicitarDados.s_SolicitarDados.trataNomeArquivoPara50caracteres(l_conteudo);
                        
                            //teste tamanho de arquivo
                            String l_tamanhoMostrar = null;
                            double l_tamanhoemMB = SolicitarDados.s_SolicitarDados.pegaTamanhoArquivoEmMB(l_tamanho);
                            
                            if(l_tamanhoemMB <= 0)
                                l_tamanhoMostrar = l_tamanho+" bytes";
                            else
                                l_tamanhoMostrar = l_tamanhoemMB+" MB";
                            
                            
                        
                        l_conteudo = l_tipoArquivo+" • ["+l_conteudo+"] • ["+l_tamanhoMostrar+"]";

                        //PRIVACIDADE
                        l_mostrarNomeEmissor =  SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "mostrar_nome");
                        l_quemVisu = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissor, "quem_envia_arquivo");
                        
                            //caso todos baixam
                            if(l_quemBaixaArquivo.equals("todos")){


                                l_apelidoDestinatario = Avisos.TEXTO_TODOS_GERAL;
                                l_descricaoPrivConteudo  = Avisos.TEXTO_TODOS_COMENT;
                                l_mostrarNomeDestinatario = "N";

                            }
                            //caso só amigos baixam
                            else if(l_quemBaixaArquivo.equals("amigos")){

                                l_apelidoDestinatario = Avisos.TEXTO_AMIGOS_GERAL;
                                l_descricaoPrivConteudo  = Avisos.TEXTO_AMIGOS_COMENT;
                                l_mostrarNomeDestinatario = "N";
                            }

                            //caso alguem em específico baixe
                            else{

                                l_apelidoDestinatario = l_quemBaixaArquivo;
                                l_descricaoPrivConteudo  = Avisos.TEXTO_SOMENTE+l_apelidoDestinatario+Avisos.TEXTO_PODE_BAIXAR_E_COMENT_COM+l_apelidoEmissor;

                                l_mostrarNomeDestinatario =  SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codDestinatario, "mostrar_nome");

                                    if(l_mostrarNomeDestinatario.equals("S"))
                                        l_nomeDestinatario = SolicitarDados.s_SolicitarDados.pegaNomeCompleto(l_codDestinatario);
                            }

                    }catch(Exception e){
                        System.err.println("Erro ao tentar mostrar a tela de comentario de arquivos.      \nErro: "+e);
                        //esconde tela carregando...
                        Aguardando.s_telaAguardando.setVisible(false);         
                        //TELA FACECARD
                        Moniersn.s_telaMsn.setVisible(true);
                    }
                }
            

            try{    
                
                //CONTAGEM DE COMENTÁRIOS
                l_qtdComents = SolicitarDados.s_SolicitarDados.qtdComentarioPorConteudo(l_tipoConteudo, l_codConteudo);

                //PEGA ÍCONE HUMOR EMISSOR
                l_iconeHumorEmissor = SolicitarDados.s_SolicitarDados.buscaIconeHumorBD(l_apelidoEmissor);

                //PEGA ÍCONE HUMOR DESTINATARIO
                l_iconeHumorDestinatario = SolicitarDados.s_SolicitarDados.buscaIconeHumorBD(l_apelidoDestinatario);

                        
                //INSTANCIA DA TELA DE COMENTÁRIOS
                Comentario.s_telaComentario = new Comentario(new javax.swing.JFrame(), true);
                Comentario.s_telaComentario.setTitle(l_caminhoTitulo);

                
                //DADOS PRA SEREM UTILIZADOS NA GRAVAÇÃO DE COMENTÁRIOS
                Comentario.s_telaComentario.c_tabela = l_tabela;
                Comentario.s_telaComentario.c_codConteudo = l_codConteudo;
                Comentario.s_telaComentario.c_tipoComentario = l_tipoConteudo;
                Comentario.s_telaComentario.c_apelidoEmissor = l_apelidoEmissor;

                //INSERE DADOS NO PAINEL
                //icone conteuo
    //            Comentario.lb_iconeDescricaoConteudo.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_iconeDescricaoConteudo)));

                     //APELIDO/NOME
                    if(l_mostrarNomeEmissor.equals("S") && l_ocorrenciaAnonima.equals("N"))
                        l_apelidoEmissor = l_nomeEmissor+" "+l_sobrenomeEmissor+" ("+l_apelidoEmissor+")";


                Comentario.lb_emissorConteudo.setText(l_apelidoEmissor);
                
                
                Comentario.lb_emissorConteudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+l_iconeHumorEmissor+".png")));
                Comentario.lb_emissorConteudo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, LogUsuario.s_tituloBordaEmissorConteudoComent, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), null)); // NOI18N

                    if(l_mostrarNomeDestinatario.equals("S"))
                        l_apelidoDestinatario = l_nomeDestinatario+" ("+l_apelidoDestinatario+")";

                LogUsuario.s_tituloBordaEmissorConteudoComent = l_tituloBordaEmissor;
                LogUsuario.s_tituloBordaDestinatarioConteudoComent = l_tituloBordaDestinatario;  

                Comentario.lb_destinatarioConteudo.setText(l_apelidoDestinatario);
                Comentario.lb_destinatarioConteudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+l_iconeHumorDestinatario+".png")));
                Comentario.lb_destinatarioConteudo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, LogUsuario.s_tituloBordaDestinatarioConteudoComent, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), null)); // NOI18N

                Comentario.bt_voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_iconeBtVoltar)));
                Comentario.lb_conteudo.setText("“"+l_conteudo+"”");
                Comentario.lb_conteudo.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_iconeConteudo)));
                Comentario.lb_descricaoConteudo.setText(l_comenteSobreConteudo);
                Comentario.lb_descricaoPrivConteudo.setText(l_descricaoPrivConteudo);
                Comentario.lb_dataPostagem.setText(Avisos.TEXTO_QUANDO+" » "+l_dataPostagem);
                Comentario.lb_ultimaAtu.setText(Avisos.TEXTO_ULT_ATU+" » "+l_dataUltimaAtu);
                Comentario.lb_qtdComentarios.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_iconeQtdComentario)));
                Comentario.lb_qtdComentarios.setText("("+l_qtdComents+") "+Avisos.TEXTO_COMENTARIOS);
                //DECIDE SE PERMITE COMENTÁRIO ANÔNIMO
                Comentario.cb_comentarioAnonimo.setVisible(l_comentarioAnonimo);
                
                Comentario.lb_emissorConteudo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, LogUsuario.s_tituloBordaEmissorConteudoComent, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 12), Color.WHITE));

                Comentario.lb_destinatarioConteudo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, LogUsuario.s_tituloBordaDestinatarioConteudoComent, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 12), Color.WHITE));  
                
                    //PREENCHER LISTA DE COMENTÁRIOS, CASO TENHA...
                    if(l_qtdComents > 0){
                        preencherListaComentarios(l_codConteudo, l_tipoConteudo);
                        mostraComentarios(true);
                    }
                    else
                        mostraComentarios(false);

                //SETA CORES
//                Cores.s_Cores.atualizaCores(LogUsuario.s_cod, "comentario");

                //TELA FACECARD
                Moniersn.s_telaMsn.setVisible(false);

                //esconde tela carregando...
                Aguardando.s_telaAguardando.setVisible(false);

                //MOSTRA A TELA DE COMENTÁRIOS
                Comentario.s_telaComentario.setVisible(true);
            
            }catch(Exception e){
                
                    System.err.println("Erro ao tentar mostrar a tela de comentarios.      \nErro: "+e);
                    
                    //esconde tela carregando...
                    Aguardando.s_telaAguardando.setVisible(false);         
                    
                    //TELA FACECARD
                    Moniersn.s_telaMsn.setVisible(true);

                    //MOSTRA MSG ATUALIZAÇÃO
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ERRO_AO_VISU_INFO_USU, "erro");
                    
            }
            
 }


    //mostra avisos recebidos
    public void mostraAvisoRecebido(int l_codEmissor, String l_aviso){

        String l_nomeDefinido = null;

            if(l_codEmissor != 0){

                    if(LogUsuario.s_mostrarBalaoAviso.equals("S")){

                        String l_nome = null;
                        String l_sobrenome = null;
                        String l_apelido = null;
                        String l_mostrarNome = null;


                        String sql = "SELECT c.mostrar_nome, u.nome, u.sobrenome, u.moniersn "
                                   + "FROM usuario u, configuracoes_usuario c "
                                   + "WHERE u.cod_usuario = c.cod_usuario "
                                   + "AND u.ativo = 'S' "
                                   + "AND u.cod_usuario = "+l_codEmissor;

                        Dados.s_conexaoBanco.executeSELECT(sql);

                            try{
                                Dados.s_conexaoBanco.c_resultset.first();

                                //pega nome do amigo
                                l_nome = Dados.s_conexaoBanco.c_resultset.getString("u.nome");
                                //pega sobrenome do amigo
                                l_sobrenome = Dados.s_conexaoBanco.c_resultset.getString("u.sobrenome");
                                //pega apelido do amigo
                                l_apelido = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                                //v se amigo mostra nome ou não
                                l_mostrarNome = Dados.s_conexaoBanco.c_resultset.getString("c.mostrar_nome");

                            }
                            catch(Exception e){
                                    System.err.println("Erro ao tentar buscar nome de usuário para mostrar aviso.    \nErro: "+e);
                            }


                                //teste de mostra nome do amigo
                                if(l_mostrarNome.equals("S")){
                                    l_nomeDefinido = l_nome+" "+l_sobrenome;
                                }
                                     else
                                         l_nomeDefinido = l_apelido;
                    }
            }else
                l_nomeDefinido = "Anônimo";

        //mostra aviso no balão
        mostraAvisoProcedimento(l_nomeDefinido, l_aviso+"!", "NADA");

    }

    public void mostraAvisoProcedimento(String l_aviso, String l_descricao, String l_tipoAviso){

        TrayIcon.MessageType l_icone = null;

        if(l_tipoAviso.equals("ERRO"))
            l_icone = TrayIcon.MessageType.ERROR;
        else if(l_tipoAviso.equals("AVISO"))
            l_icone = TrayIcon.MessageType.WARNING;
        else if(l_tipoAviso.equals("INFO"))
            l_icone = TrayIcon.MessageType.INFO;
        else if(l_tipoAviso.equals("NADA"))
            l_icone = TrayIcon.MessageType.NONE;

        //mostra a telinha de l_aviso por 8 segundos
        AreaNotificacao.trayIcon.displayMessage(l_aviso, l_descricao, l_icone);
    }

    //verifica quem me adicionou
    public void mostraSolicitacaoDeAmizade(int l_codAmigo){

        String l_amigo = null;
        String l_eleEla = null;

        try{

           //executa query pra pegar informações de quem me add
           Dados.s_conexaoBanco.executeSELECT("SELECT u.moniersn, u.nome, u.sobrenome, u.icone_humor, u.sexo, c.*, cu.* "
                                               + "FROM usuario u, contato_de_usuario c, configuracoes_usuario cu "
                                               + "WHERE u.cod_usuario = c.cod_usuario "
                                               + "AND u.ativo = 'S' "
                                               + "AND u.cod_usuario = cu.cod_usuario "
                                               + "AND c.aceito = 'N' "
                                               + "AND c.cod_amigo = "+LogUsuario.s_cod+" "
                                               + "AND c.cod_usuario = "+l_codAmigo);


            if(Dados.s_conexaoBanco.c_resultset.first()){

                    //atualiza data
                    Data.atualizaDataHora();

                    //dados de quem me dd
                    String l_mostrarNome = Dados.s_conexaoBanco.c_resultset.getString("cu.mostrar_nome");
                    String l_nome =  Dados.s_conexaoBanco.c_resultset.getString("u.nome");
                    String l_sobrenome =  Dados.s_conexaoBanco.c_resultset.getString("u.sobrenome");
                    String l_sexo = Dados.s_conexaoBanco.c_resultset.getString("u.sexo");
                    String l_moniersn = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                    String l_iconeHumor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
                    String l_dataHoraAdd = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("c.data_hora_add"));
                    String l_quandoMeAdd = Data.pegaIntervaloTempo(Data.s_dataHoraAtualDDMMAAAAHHMMSS, l_dataHoraAdd);

                        //teste para saber se quem convidou mostra o seu nome
                            if(l_mostrarNome.equals("S"))
                                l_amigo = l_nome+" "+l_sobrenome+" ("+l_moniersn+")";
                                    else
                                        l_amigo = l_moniersn;

                        //teste para ícone de sexo e gênero
                            if(l_sexo.equals("M")){
                                l_eleEla = Avisos.TEXTO_ELE;
                            }
                                    else{
                                        l_eleEla = Avisos.TEXTO_ELA;
                                    }

  
                    //instancia a telinha de adicionamento
                    DecisaoDeSolicitacaoDeAmizade.s_telaSolicitacaoAmizade = new DecisaoDeSolicitacaoDeAmizade(new javax.swing.JFrame(), true);

                    LogUsuario.s_mostrandoSolicitacaoAmizade = true;
                    LogUsuario.s_codAmigoSolicitacao = l_codAmigo;

                    DecisaoDeSolicitacaoDeAmizade.s_telaSolicitacaoAmizade.lb_comprimento.setText(Data.comprimentacao()+", "+LogUsuario.s_nome+"!!!");
                    DecisaoDeSolicitacaoDeAmizade.s_telaSolicitacaoAmizade.c_codAmigo = l_codAmigo;
                    DecisaoDeSolicitacaoDeAmizade.s_telaSolicitacaoAmizade.lb_nome.setText(l_amigo);
                    DecisaoDeSolicitacaoDeAmizade.s_telaSolicitacaoAmizade.lb_nome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+l_iconeHumor+".png")));
                    DecisaoDeSolicitacaoDeAmizade.s_telaSolicitacaoAmizade.lb_tempoQueAdd.setText(l_eleEla+Avisos.TEXTO_SOLICITOU_SUA_AMIZADE+l_quandoMeAdd);
                    DecisaoDeSolicitacaoDeAmizade.s_telaSolicitacaoAmizade.setVisible(true);
            }
        }
        catch(Exception e){
            System.err.println("Não foi possível mostrar a tela de solicitação de amigo.     \nErro: "+e);
        }

    }

    //chama a tela de cadastro de novo usuário
    public void mostraTelaConvite(String l_nome, Icon l_icone, int l_codUsuario){

        SolicitacaoDeAmizade.s_telaConvite = new SolicitacaoDeAmizade(new javax.swing.JFrame(), true, l_nome, l_icone, l_codUsuario);
        SolicitacaoDeAmizade.s_telaConvite.setVisible(true);

    }
    

    public void mostraMensagemAtualizacao(String l_texto, String l_tipo){

        MensagemDialogo l_msg = new MensagemDialogo(new javax.swing.JFrame(), true, l_texto, l_tipo);
        l_msg.setVisible(true);

    }

    public boolean mostraAvisoConfirmacao(String l_tituloAviso, String l_texto, String l_icone){

        boolean l_confirmado;

        MensagemAviso.s_telaAvisoConfirmacao = new MensagemAviso(new javax.swing.JFrame(), true);
        MensagemAviso.s_telaAvisoConfirmacao.lb_iconeTituloAviso.setText(l_tituloAviso);
        MensagemAviso.s_telaAvisoConfirmacao.tp_textoAviso.setText(l_texto);
        MensagemAviso.s_telaAvisoConfirmacao.lb_iconeTituloAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_icone))); // NOI18N
        MensagemAviso.s_telaAvisoConfirmacao.setVisible(true);

        l_confirmado = MensagemAviso.s_confirmacao;
        
        MensagemAviso.s_telaAvisoConfirmacao = null;

        return l_confirmado;
    }


    public boolean mostraAvisoDesconectar(){

        boolean l_confirmado;

        Desconectar.s_telaDesconectar = new Desconectar(new javax.swing.JFrame(), true);
        Desconectar.s_telaDesconectar.setVisible(true);

        l_confirmado = Desconectar.s_confirmacao;

        Desconectar.s_telaDesconectar = null;

        return l_confirmado;
    }

    //chama a tela de cadastro de novo usuário
    public void mostraTelaCadastro(){

        //esconde a tela de login
        Login.s_telaLogin.setVisible(false);
        
            if(Login.l_cadIniciado){
                
                CadastroUsuario.s_telaCadastroMoniersn.setVisible(true);
            }else{

                CadastroUsuario.s_telaCadastroMoniersn = new CadastroUsuario(new javax.swing.JFrame(), true);
                CadastroUsuario.s_telaCadastroMoniersn.setVisible(true);
            }

    }

    public boolean mostraTelaEscolhaDiretorio(String l_tipoEscolha){
    
        boolean l_podeContinuar = false;
        Moniersn.s_telaMsn.setVisible(false);
        
        EscolhaDiretorio escolhaDir = new EscolhaDiretorio(new javax.swing.JFrame(), true);

        
            if(l_tipoEscolha.equals("definir")){
                    
                escolhaDir.bt_redefinirPasta.setVisible(false);

            } 

            if(l_tipoEscolha.equals("localizar")){

                File l_localArqUsuario = new File(LogUsuario.s_localDeArquivosDoUsuario);
                String l_nomePastaUsuario = l_localArqUsuario.getName();    

                escolhaDir.lb_logoArquivos.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LOGO_LOCAL_ARQUIVOS_PERDIDOS)));
                escolhaDir.lb_textoLocalEscolhido.setText(Avisos.TEXTO_PASTA_N_ENCONTRADA_NO_LOCAL+":");
                escolhaDir.lb_local.setText(LogUsuario.s_localDeArquivosDoUsuario);
                escolhaDir.lb_local.setForeground(Color.red);
                escolhaDir.tf_pasta.setText(l_nomePastaUsuario);
                escolhaDir.tf_pasta.setEditable(false);
                escolhaDir.bt_continuar.setText(Avisos.TEXTO_ENCONTREI_MINHA_PASTA);
                escolhaDir.lb_iconeInfo.setText(Avisos.TEXTO_PASTA_MODIFICADA);
                escolhaDir.lb_iconeInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.ATENCAO_16)));
                escolhaDir.lb_iconeInfo.setForeground(Color.red);
                escolhaDir.bt_escolherLocal.setText(Avisos.TEXTO_PROCURAR_MINHA_PASTA);
                  

            }
            
        escolhaDir.setVisible(true);   
        l_podeContinuar = escolhaDir.c_continuar;
        
        return l_podeContinuar;
   
    }
    
    public void mostraTelaCompartilhamentoArquivo(String l_praQuem){

        boolean l_podeContinuar = false;
        Moniersn.s_telaMsn.setVisible(false);
        
        
        SolicitarDados.s_SolicitarDados.verificaLocalArquivos(LogUsuario.s_cod, LogUsuario.s_localDeArquivosDoUsuario, "compartilhar");
        
            if(LogUsuario.s_localDeArquivosDoUsuario.equals("*")){

                l_podeContinuar = mostraTelaEscolhaDiretorio("definir");

            //caso o local de arquivos do usuário nao esteja no lugar...
            }else if(!LogUsuario.s_localDeArquivosDoUsuarioConsistente){
                
                l_podeContinuar = mostraTelaEscolhaDiretorio("localizar");
                
            }else
                l_podeContinuar = true;

        if(l_podeContinuar){

            
                //logo carregando...
                new Thread(new LogoCarregando()).start();
            
                CompartilhaArquivo.s_telaCompArquivo = new CompartilhaArquivo();

                    if(l_praQuem.equals("unico")){

                        CompartilhaArquivo.s_telaCompArquivo.lb_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LOGO_DISPO_ARQ_UMA_PESSOA)));
                        CompartilhaArquivo.s_telaCompArquivo.rb_unicoAmigo.setSelected(true);
                        CompartilhaArquivo.s_telaCompArquivo.rb_unicoAmigo.setEnabled(false);
                        CompartilhaArquivo.s_telaCompArquivo.rb_amigosGeral.setEnabled(false);
                        CompartilhaArquivo.s_telaCompArquivo.rb_todosGeral.setEnabled(false);
                        CompartilhaArquivo.s_telaCompArquivo.lb_iconePessoa.setVisible(true);
                        CompartilhaArquivo.s_telaCompArquivo.lb_iconeConfirmarDestinatario.setVisible(true);
                        CompartilhaArquivo.s_telaCompArquivo.lb_iconeConfirmarDestinatario.setEnabled(false);
                        CompartilhaArquivo.s_telaCompArquivo.bt_selecionarArquivo.setEnabled(true);
                        CompartilhaArquivo.s_telaCompArquivo.lb_arquivoTipo.setText(Avisos.TEXTO_SELECIONE_O_ARQUIVO);
                        CompartilhaArquivo.s_telaCompArquivo.bt_compartilhar.setText(Avisos.TEXTO_COMPARTILHAR_ARQUIVO_COM+LogAmigo.s_moniersn);
                        CompartilhaArquivo.s_telaCompArquivo.c_comQuemCompartilha = LogAmigo.s_moniersn;
                        CompartilhaArquivo.s_telaCompArquivo.c_codDestinatario = LogAmigo.s_cod;
                        CompartilhaArquivo.cb_listaAmigos.setVisible(true);
                        CompartilhaArquivo.cb_listaAmigos.setEnabled(false);
                        CompartilhaArquivo.cb_listaAmigos.setSelectedItem(LogAmigo.s_moniersn);

                    }

                    else if(l_praQuem.equals("varios")){

                        CompartilhaArquivo.s_telaCompArquivo.lb_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LOGO_DISPO_ARQ)));
                        CompartilhaArquivo.s_telaCompArquivo.lb_iconePessoa.setVisible(false);
                        CompartilhaArquivo.cb_listaAmigos.setVisible(false);
                        CompartilhaArquivo.s_telaCompArquivo.lb_iconeConfirmarDestinatario.setVisible(false);
                        CompartilhaArquivo.s_telaCompArquivo.lb_iconePessoa.setVisible(false);

                    }

               //esconde tela carregando
               Aguardando.s_telaAguardando.setVisible(false);  
                    
               CompartilhaArquivo.s_telaCompArquivo.setVisible(true);
        }else
            Moniersn.s_telaMsn.setVisible(true); 
    }
    
    public void mostraTelaCompartilhamentoArquivo(String l_nomeArq, int l_tamanho){

        boolean l_podeContinuar = false;
        Moniersn.s_telaMsn.setVisible(false);
        SolicitarDados.s_SolicitarDados.verificaLocalArquivos(LogUsuario.s_cod, LogUsuario.s_localDeArquivosDoUsuario, "compartilhar");


            if(LogUsuario.s_localDeArquivosDoUsuario.equals("*")){

                l_podeContinuar = mostraTelaEscolhaDiretorio("definir");

            //caso o local de arquivos do usuário nao esteja no lugar...
            }else if(!LogUsuario.s_localDeArquivosDoUsuarioConsistente){
                
                l_podeContinuar = mostraTelaEscolhaDiretorio("localizar");
                
            }else
                l_podeContinuar = true;

        if(l_podeContinuar){
            
                //logo carregando...
                new Thread(new LogoCarregando()).start();
            
                String l_localArquivo = LogUsuario.s_listaArquivoSelecionada;
                
                String l_nomeArqTratado = SolicitarDados.s_SolicitarDados.trataNomeArquivoPara50caracteres(l_nomeArq);
                String l_localArquivos = LogUsuario.s_localDeArquivosDoUsuario+"\\"+Diretorios.PASTA_ARQUIVOS+"\\"+l_localArquivo;
                String l_extensaoArquivo = SolicitarDados.s_SolicitarDados.pegaExtensaoOuNomeArquivo(l_nomeArq, "extensao");
                double l_tamanhoEmMB = SolicitarDados.s_SolicitarDados.pegaTamanhoArquivoEmMB(l_tamanho);
                String l_iconeArquivoOriginal = SolicitarDados.s_SolicitarDados.pegaIconeOuTipoExtensao(l_extensaoArquivo, "icone");
                String l_tipoArquivoOriginal = SolicitarDados.s_SolicitarDados.pegaIconeOuTipoExtensao(l_extensaoArquivo, "tipo");
                
                
                CompartilhaArquivo.s_telaCompArquivo = new CompartilhaArquivo();

                //NOME COM EXTENSAO
                CompartilhaArquivo.s_telaCompArquivo.c_nomeExtensaoArquivoSelecionado = l_nomeArq;
                
                CompartilhaArquivo.s_telaCompArquivo.lb_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LOGO_DISPO_ARQ)));
                
                
                //INABILITA COMPONENTES DE COMPART. PARA UMA PESSOA
                CompartilhaArquivo.s_telaCompArquivo.lb_iconePessoa.setVisible(false);
                CompartilhaArquivo.cb_listaAmigos.setVisible(false);
                CompartilhaArquivo.s_telaCompArquivo.lb_iconeConfirmarDestinatario.setVisible(false);
                CompartilhaArquivo.s_telaCompArquivo.lb_iconePessoa.setVisible(false);

                //HABILITA BT CONFIRMAR NOME 
                CompartilhaArquivo.s_telaCompArquivo.bt_confirmarRenomear.setEnabled(true); 
                
                
                //SETA NOME ORIGINAL DO ARQUIVO
                CompartilhaArquivo.s_telaCompArquivo.lb_nomeOriginal.setText("("+l_tipoArquivoOriginal+") - "+l_nomeArqTratado);

                //SETA ICONE DO TIPO ORIGINAL DO ARQUIVO
                CompartilhaArquivo.s_telaCompArquivo.lb_iconeTipoArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_iconeArquivoOriginal)));

                
                //HABILITA O CAMPO NOME DO ARQUIVO
                CompartilhaArquivo.s_telaCompArquivo.tf_arquivo.setEditable(true); 

                //SETA O LOCAL
                CompartilhaArquivo.s_telaCompArquivo.tf_diretorio.setText(l_localArquivos); 
                
                //SETA EXTENSAO
                CompartilhaArquivo.s_telaCompArquivo.tf_extensao.setText(l_extensaoArquivo); 
                
                //SETA TAMANHO EM BYTES
                CompartilhaArquivo.s_telaCompArquivo.tf_tamanho.setText(l_tamanho+""); 

                //SETA TAMANHO EM MB
                CompartilhaArquivo.s_telaCompArquivo.lb_tamanhoMb.setText(l_tamanhoEmMB+" MB"); 
                
                //esconde tela de carregando...
               Aguardando.s_telaAguardando.setVisible(false);    
                
               CompartilhaArquivo.s_telaCompArquivo.setVisible(true);
        }else
            Moniersn.s_telaMsn.setVisible(true); 
    }
    
    public boolean mostraConfirmacaoSenha(String l_tipoSeg){
    
        boolean l_confirmado = false;
        String l_icone = null;
        
            //testand icone de esegurança
            if(l_tipoSeg.equals("config")){

                l_icone = Icones.LOGO_SEG_HAB_CONF;

            }else if(l_tipoSeg.equals("mostrar")){

                l_icone = Icones.LOGO_SEG_HAB_TRAY;

            }else if(l_tipoSeg.equals("confirma-senha")){

                l_icone = Icones.LOGO_SEG_CONF_SENHA;

            }
        
        ConfirmaSenha.s_telaConfirmaSenha = new ConfirmaSenha(new javax.swing.JFrame(), true);
        ConfirmaSenha.lb_imagemAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(l_icone))); // NOI18N
        ConfirmaSenha.s_telaConfirmaSenha.lb_nome.setText(LogUsuario.s_nome+" "+LogUsuario.s_sobrenome);
        ConfirmaSenha.s_telaConfirmaSenha.setVisible(true);
        l_confirmado = ConfirmaSenha.s_telaConfirmaSenha.c_senhaCorreta;
        ConfirmaSenha.s_telaConfirmaSenha = null;

    
        return l_confirmado;
            
    }

    public void habilitaBotoesConteudo(JTable l_tabelaLocalizacaoBotao){
        int l_linha = 0;
        int l_codConteudoSelecionado = 0;
        int l_codEmissorConteudo = 0;
        String l_valorMarcado = null;

        //CASO LISTA DE OCORRENCIAS
        if(l_tabelaLocalizacaoBotao.equals(Moniersn.jt_listaOcorrenciaUsuario)){

            //caso unica linha selecionada (habilita botoes de comentrar e excluir ocorrencia)
            if(l_tabelaLocalizacaoBotao.getSelectedRows().length == 1){

                l_linha = l_tabelaLocalizacaoBotao.getSelectedRow();
                l_codConteudoSelecionado = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, l_tabelaLocalizacaoBotao);
                l_codEmissorConteudo = SolicitarDados.s_SolicitarDados.buscaCodEmissorOcorrencia(l_codConteudoSelecionado);

//              se usuário é o cod emissor da ocorrencia
                if(LogUsuario.s_cod == l_codEmissorConteudo){
                    Moniersn.s_telaMsn.bt_excluirOcorrencia.setEnabled(true);
                }
                else{
                    Moniersn.s_telaMsn.bt_excluirOcorrencia.setEnabled(false);
                }

                Moniersn.s_telaMsn.bt_comentarOcorrencia.setEnabled(true);

            }

            //caso mais de uma linha selecionada (inabilita botao de comentrar e habilita botao de excluir ocorrencia)
            else if(l_tabelaLocalizacaoBotao.getSelectedRows().length > 1){

                if(LogUsuario.s_listaOcorrenciaSelecionada.equals("rec"))
                    Moniersn.s_telaMsn.bt_excluirOcorrencia.setEnabled(false);
                else
                    Moniersn.s_telaMsn.bt_excluirOcorrencia.setEnabled(true);

                Moniersn.s_telaMsn.bt_comentarOcorrencia.setEnabled(false);

            }

            //caso nenhuma linha selecionada inabilita botao de comentar e excluir ocorrencia
                else{
                    Moniersn.s_telaMsn.bt_comentarOcorrencia.setEnabled(false);
                    Moniersn.s_telaMsn.bt_excluirOcorrencia.setEnabled(false);
                }
        }

        //CASO LISTA DE ATUALIZAÇÕES
        if(l_tabelaLocalizacaoBotao.equals(Moniersn.jt_listaAtualizacoesUsuario)){

            //caso unica linha selecionada (habilita botoes de comentrar e excluir ATU)
            if(l_tabelaLocalizacaoBotao.getSelectedRows().length == 1){

                l_linha = l_tabelaLocalizacaoBotao.getSelectedRow();
                l_codConteudoSelecionado = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, l_tabelaLocalizacaoBotao);
                l_codEmissorConteudo = SolicitarDados.s_SolicitarDados.buscaCodEmissorAtualizacao(l_codConteudoSelecionado);

                if(LogUsuario.s_cod == l_codEmissorConteudo)
                    Moniersn.s_telaMsn.bt_excluirAtualizacoes.setEnabled(true);
                else
                    Moniersn.s_telaMsn.bt_excluirAtualizacoes.setEnabled(false);

                Moniersn.s_telaMsn.bt_comentarAtuUsuario.setEnabled(true);
            }

            //caso mais de uma linha selecionada (inabilita botao de comentrar e habilita botao de excluir ATU
            else if(l_tabelaLocalizacaoBotao.getSelectedRows().length > 1){

                if(LogUsuario.s_listaAtualizacoesSelecionada.equals("recEstranhos") ||
                   LogUsuario.s_listaAtualizacoesSelecionada.equals("recAmigos"))
                    Moniersn.s_telaMsn.bt_excluirAtualizacoes.setEnabled(false);
                else
                    Moniersn.s_telaMsn.bt_excluirAtualizacoes.setEnabled(true);

                Moniersn.s_telaMsn.bt_comentarAtuUsuario.setEnabled(false);

            }

            //caso nenhuma linha selecionada inabilita botao de comentar e excluir ATU
                else{
                    Moniersn.s_telaMsn.bt_comentarAtuUsuario.setEnabled(false);
                    Moniersn.s_telaMsn.bt_excluirAtualizacoes.setEnabled(false);
                }
        }

        //CASO LISTA DE RECADOS PÚBLICOS
        if(l_tabelaLocalizacaoBotao.equals(Moniersn.jt_listaRecadosPubUsuario)){

            //caso unica linha selecionada (habilita botoes de comentrar e excluir rec pub)
            if(l_tabelaLocalizacaoBotao.getSelectedRows().length == 1){

                   //pega valor (recadovisto ou nao)
                   l_valorMarcado = SolicitarDados.s_SolicitarDados.pegaValorMarcacaoRecado(l_tabelaLocalizacaoBotao);

                   //caso esteja na lista de enviados e o recado ainda nao foi lido...
                   if(LogUsuario.s_listaRecPubSelecionada.equals("env") && l_valorMarcado.equals("N")){
                        Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setEnabled(false);
                        Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AGUARDANDO_16)));
                        Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setText("");
                   }
                   else{
                        Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setEnabled(true);
                        SolicitarDados.s_SolicitarDados.defineIconeRecadoLidoNaoLido(l_tabelaLocalizacaoBotao);
                   }

                   Moniersn.s_telaMsn.bt_excluirRecPub.setEnabled(true);
                   Moniersn.s_telaMsn.bt_comentarRecPublico.setEnabled(true);
            }

            //caso mais de uma linha selecionada (inabilita botao de comentrar e marcar e habilita botao de excluir rec pub
            else if(l_tabelaLocalizacaoBotao.getSelectedRows().length > 1){

                Moniersn.s_telaMsn.bt_comentarRecPublico.setEnabled(false);
                Moniersn.s_telaMsn.bt_excluirRecPub.setEnabled(true);
                Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setEnabled(false);
                Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setIcon(null); 
                Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setText("*");

            }

            //caso nenhuma linha selecionada inabilita botao de comentar e excluir ATU
                else{
                    Moniersn.s_telaMsn.bt_comentarRecPublico.setEnabled(false);
                    Moniersn.s_telaMsn.bt_excluirRecPub.setEnabled(false);
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setEnabled(false);
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setIcon(null);
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPub.setText("*");
                }
        }

        //CASO LISTA DE RECADOS PRIVADOS
        if(l_tabelaLocalizacaoBotao.equals(Moniersn.jt_listaRecadosPriv)){

            //caso unica linha selecionada (habilita botoes de comentrar e excluir rec pub)
            if(l_tabelaLocalizacaoBotao.getSelectedRows().length == 1){

               //pega valor (recadovisto ou nao)
               l_valorMarcado = SolicitarDados.s_SolicitarDados.pegaValorMarcacaoRecado(l_tabelaLocalizacaoBotao);
                    //caso esteja na lista de enviados e o recado ainda nao foi lido...
                   if(LogUsuario.s_listaRecPrivSelecionada.equals("env") && l_valorMarcado.equals("N")){
                        Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setEnabled(false);
                        Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.AGUARDANDO_16)));
                        Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setText("");
                   }
                   else{
                        Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setEnabled(true);
                        SolicitarDados.s_SolicitarDados.defineIconeRecadoLidoNaoLido(l_tabelaLocalizacaoBotao);
                   }

                Moniersn.s_telaMsn.bt_excluirRecPriv.setEnabled(true);
                Moniersn.s_telaMsn.bt_comentarRecPriv.setEnabled(true);
            }

            //caso mais de uma linha selecionada (inabilita botao de comentrar e marcar e habilita botao de excluir rec pub
            else if(l_tabelaLocalizacaoBotao.getSelectedRows().length > 1){
                Moniersn.s_telaMsn.bt_comentarRecPriv.setEnabled(false);
                Moniersn.s_telaMsn.bt_excluirRecPriv.setEnabled(true);
                Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setEnabled(false);
                Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setIcon(null);
                Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setText("*");
            }

                //caso nenhuma linha selecionada inabilita botao de comentar e excluir ATU
                else{
                    Moniersn.s_telaMsn.bt_comentarRecPriv.setEnabled(false);
                    Moniersn.s_telaMsn.bt_excluirRecPriv.setEnabled(false);
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setEnabled(false);
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setIcon(null);
                    Moniersn.s_telaMsn.bt_marcarComoLidoRecPriv.setText("*");
                }
        }


        //CASO LISTA DE ARQUIVOS
        if(l_tabelaLocalizacaoBotao.equals(Moniersn.jt_listaArquivosUsuario)){

            //caso unica linha selecionada (habilita botoes de comentrar e excluir ATU)
            if(l_tabelaLocalizacaoBotao.getSelectedRows().length == 1){

                l_linha = l_tabelaLocalizacaoBotao.getSelectedRow();
                l_codConteudoSelecionado = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, l_tabelaLocalizacaoBotao);
                l_codEmissorConteudo = SolicitarDados.s_SolicitarDados.buscaCodEmissorArquivo(l_codConteudoSelecionado);


                //caso o cod emissor for igual ao cod do usuario
                if(LogUsuario.s_cod == l_codEmissorConteudo){

                    //ABRIR  ARQUIVO (TRUE)
                    Moniersn.s_telaMsn.bt_abrirArquivoSelecionado.setEnabled(true);
                    
                    //COMPARTILHAR ENVIADO
                    Moniersn.s_telaMsn.bt_encaminharArquivo.setEnabled(true);

                    //BAIXAR ARQUIVO (FALSE)
                    Moniersn.s_telaMsn.bt_baixarArquivoSelecionado.setEnabled(false);

                    //REMOVER ARQUIVO (TRUE)
                    Moniersn.s_telaMsn.bt_removerArquivoSelecionado.setEnabled(true);
                    
                }

                //caso o cod. emissor não for igual ao cod. do usuário
                else{

                        //caso a lista selecionada seja a lista
                        if(LogUsuario.s_listaArquivoSelecionada.equals("disponiveis")){

                            //ABRIR  ARQUIVO (FALSE)
                            Moniersn.s_telaMsn.bt_abrirArquivoSelecionado.setEnabled(false);

                            //BAIXAR ARQUIVO (TRUE)
                            Moniersn.s_telaMsn.bt_baixarArquivoSelecionado.setEnabled(true);

                            //REMOVER ARQUIVO (FALSE)
                            Moniersn.s_telaMsn.bt_removerArquivoSelecionado.setEnabled(false);
                            
                            //ENCAMINHAR ARQUIVO 
                            Moniersn.s_telaMsn.bt_encaminharArquivo.setEnabled(false);

                        }

                        else if(LogUsuario.s_listaArquivoSelecionada.equals("baixados")){

                            //ABRIR  ARQUIVO (TRUE)
                            Moniersn.s_telaMsn.bt_abrirArquivoSelecionado.setEnabled(true);

                            //BAIXAR ARQUIVO (FALSE)
                            Moniersn.s_telaMsn.bt_baixarArquivoSelecionado.setEnabled(false);

                            //REMOVER ARQUIVO (TRUE)
                            Moniersn.s_telaMsn.bt_removerArquivoSelecionado.setEnabled(true);
                            
                            //ENCAMINHAR ARQUIVO 
                            Moniersn.s_telaMsn.bt_encaminharArquivo.setEnabled(true);

                        }

                }

                     //COMENTAR ARQUIVO (TRUE)
                    Moniersn.s_telaMsn.bt_comentarArquivoSelecionadoUsuario.setEnabled(true);
            }
            
            //caso mais de uma linha selecionada
            else if(l_tabelaLocalizacaoBotao.getSelectedRows().length > 1 &
                    !LogUsuario.s_listaArquivoSelecionada.equals("disponiveis")){

                        //ENCAMINHAR ARQUIVO 
                        Moniersn.s_telaMsn.bt_encaminharArquivo.setEnabled(false);

                        //ABRIR  ARQUIVO (FALSE)
                        Moniersn.s_telaMsn.bt_abrirArquivoSelecionado.setEnabled(false);

                        //BAIXAR ARQUIVO (FALSE)
                        Moniersn.s_telaMsn.bt_baixarArquivoSelecionado.setEnabled(false);

                        //REMOVER ARQUIVO (TRUE)
                        Moniersn.s_telaMsn.bt_removerArquivoSelecionado.setEnabled(true);

                        //COMENTAR ARQUIVO (FALSE)
                        Moniersn.s_telaMsn.bt_comentarArquivoSelecionadoUsuario.setEnabled(false);

            }

                //caso nenhuma linha selecionada inabilita botao de comentar e excluir arq
                else{

                    //ENCAMINHAR ARQUIVO 
                    Moniersn.s_telaMsn.bt_encaminharArquivo.setEnabled(false);

                    //ABRIR  ARQUIVO (TRUE)
                    Moniersn.s_telaMsn.bt_abrirArquivoSelecionado.setEnabled(false);

                    //BAIXAR ARQUIVO (FALSE)
                    Moniersn.s_telaMsn.bt_baixarArquivoSelecionado.setEnabled(false);

                    //REMOVER ARQUIVO (TRUE)
                    Moniersn.s_telaMsn.bt_removerArquivoSelecionado.setEnabled(false);

                    //COMENTAR ARQUIVO (TRUE)
                    Moniersn.s_telaMsn.bt_comentarArquivoSelecionadoUsuario.setEnabled(false);

               }
        }
        
        //CASO LISTA DE COMENTÁRIOS
        if(l_tabelaLocalizacaoBotao.equals(Comentario.jt_listaComentarios)){

            //caso unica linha selecionada (habilita botoes de comentrar e excluir ATU)
            if(l_tabelaLocalizacaoBotao.getSelectedRows().length == 1){

                l_linha = l_tabelaLocalizacaoBotao.getSelectedRow();
                l_codConteudoSelecionado = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_linha, l_tabelaLocalizacaoBotao);
                l_codEmissorConteudo = SolicitarDados.s_SolicitarDados.buscaCodEmissorComentario(l_codConteudoSelecionado);

                if(LogUsuario.s_cod == l_codEmissorConteudo)
                    Comentario.s_telaComentario.bt_removerComent.setEnabled(true);
                else
                    Comentario.s_telaComentario.bt_removerComent.setEnabled(false);
            }
        }
    }


    public void setaUltimaOcorrencia(int l_codUsuario, String l_quem){

        String l_ocorrencia = null;
        int l_qtdComents;
        String sqlSelect;
        String l_dataHoraPost;
    

        int l_codConteudo;

        sqlSelect = "SELECT * "
                  + "FROM ocorrencia "
                  + "WHERE cod_emissor = "+l_codUsuario+" "
                  + "AND anonimo = 'N' "
                  + "AND gravado = 'S' "
                  + "ORDER BY cod_ocorrencia "
                  + "ASC";

        Dados.s_conexaoBanco.executeSELECT(sqlSelect);

        try {
                //caso o usuario tenha ocorrênicias...
                if(Dados.s_conexaoBanco.c_resultset.last()){

                    l_ocorrencia = Dados.s_conexaoBanco.c_resultset.getString("conteudo");
                    l_codConteudo = Dados.s_conexaoBanco.c_resultset.getInt("cod_ocorrencia");
                    l_dataHoraPost = Data.ajustaDataParaDDMMAAAAHHMMSS(Dados.s_conexaoBanco.c_resultset.getString("data_hora_post"));
                    l_qtdComents = SolicitarDados.s_SolicitarDados.qtdComentarioPorConteudo("ocorrencia", l_codConteudo);


                    if(l_quem.equals("usuario")){
                        LogUsuario.s_codUltimaOcorrencia = l_codConteudo;
                        LogUsuario.s_dataHoraUltimaOcorrencia = l_dataHoraPost;
                        Moniersn.lb_ultimaOcorrenciaUsuario.setVisible(true);
                        Moniersn.lb_qtdComentarioUltOcoUsuario.setVisible(true);
                        Moniersn.lb_ultimaOcorrenciaUsuario.setText(l_ocorrencia);
                        Moniersn.lb_qtdComentarioUltOcoUsuario.setText("("+l_qtdComents+")");
                    }
                    else{
                        //add guia de ocorrencias
                        adicionaPainelDeInformacoes("("+LogAmigo.s_qtdTotOcoEnviadas+")", Moniersn.jp_ocorrenciaAmigo, Icones.OCORRENCIA_ADD_16);
                        LogAmigo.s_codUltimaOcorrencia = l_codConteudo;
                        LogAmigo.s_dataHoraUltimaOcorrencia = l_dataHoraPost;
                        Moniersn.lb_ultOcorrenciaAmigo.setVisible(true);
                        Moniersn.lb_qtdComentarioUltOcoAmigo.setVisible(true);
                        Moniersn.lb_ultOcorrenciaAmigo.setText(l_ocorrencia);
                        Moniersn.lb_qtdComentarioUltOcoAmigo.setText("("+l_qtdComents+")");
                    }
                }
                        //caso não tenha ocorrênicias...
                        else{

                            if(l_quem.equals("usuario")){
                                Moniersn.lb_ultimaOcorrenciaUsuario.setVisible(false);
                                Moniersn.lb_qtdComentarioUltOcoUsuario.setVisible(false);
                            }
                            else{
                                Moniersn.jtp_painelTabuladoAmigo.remove(Moniersn.jp_ocorrenciaAmigo);
                                Moniersn.lb_ultOcorrenciaAmigo.setVisible(false);
                                Moniersn.lb_qtdComentarioUltOcoAmigo.setVisible(false);
                            }
                        }
        }
        catch(Exception e){
            System.err.println("Não foi possível selecionar a última o ocorrência.    \nErro: "+e);
        }
    }

   public void adicionaPainelDeInformacoes(String l_nomeGuia, JPanel l_painel, String l_iconeGuia){

      
       Moniersn.jtp_painelTabuladoAmigo.addTab(l_nomeGuia, new javax.swing.ImageIcon(getClass().getResource(l_iconeGuia)), l_painel); // NOI18N

   }

   public void setaTituloFrame(String l_guia, String l_janela){

        String l_nomeCompleto = LogUsuario.s_nome+" "+LogUsuario.s_sobrenome;

//            if(l_janela.equals("moniersn"))
//                Moniersn.s_telaMsn.setTitle(l_nomeCompleto+" | "+l_guia+" - "+Avisos.FACECARD_FULL);
            if(l_janela.equals("config"))
                Configuracoes.s_telaConfiguracoes.setTitle(Avisos.TIT_JAN_CONFIG+" "+l_nomeCompleto+" | "+l_guia+" - "+Avisos.FACECARD_FULL);

   }

        //pega as informações do amigo no banco e ja define algumas configurações
    public void defineInformacoesDoAmigo(int l_codAmigo){

//          remove todas as abas toda vez q o usuário seleciona um amigo na lista
            try{

                //extende o o form para aparecer o painel do amigo
                mostraPainelDoAmigo(false);
                
                LogAmigo.s_cod = l_codAmigo;

                LogAmigo.s_tipoAmigo = SolicitarDados.s_SolicitarDados.defineTipoAmigo(LogUsuario.s_cod, l_codAmigo);

                Moniersn.jtp_painelTabuladoAmigo.removeAll();

                carregaInfoBasicas(l_codAmigo, "inicio-amigo");

                carregaConfPrivacidade(l_codAmigo, "painel-amigo");

                //configura as cores do painel de amigo
                Cores.s_Cores.atualizaCores(l_codAmigo, "amigo");
                
                //extende o form para aparecer o painel do amigo
                mostraPainelDoAmigo(true);   
                
                //esconde tela carregando
                Aguardando.s_telaAguardando.setVisible(false); 
                       
                    if(LogAmigo.s_tipoAmigo.equals("nao-aceito-convidou-me")){

                        //CHAMA A TELA DE SOLICITAÇÃO DE AMIZADE
                        mostraSolicitacaoDeAmizade(l_codAmigo);
                    }

            }
            catch(Exception e){
                
                //esconde tela carregando
                Aguardando.s_telaAguardando.setVisible(false); 
                //esconde painel do amigo
                mostraPainelDoAmigo(false);
                //remove todas as abas
                Moniersn.jtp_painelTabuladoAmigo.removeAll();
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ERRO_AO_VISU_INFO_USU, "erro");
            }
    }
    

    public void mostraPainelDoAmigo(boolean l_extende){

        int l_alturaPainelUser = Moniersn.jtp_painelTabuladoUsuario.getSize().height;
        int l_larguraPAinelUser = Moniersn.jtp_painelTabuladoUsuario.getSize().width;
        int l_alturaPainelAmigo= Moniersn.jtp_painelTabuladoAmigo.getSize().height;
        int l_larguraPAinelAmigo = Moniersn.jtp_painelTabuladoAmigo.getSize().width;
        
        int l_larguraPaineis = Moniersn.jtp_painelTabuladoUsuario.getSize().width;
        
        int l_alturaGeral = l_alturaPainelUser+l_alturaPainelAmigo+6;
        
        if(l_extende){

            if(Moniersn.s_telaMsn.getSize().getHeight() == l_alturaPainelUser && 
               LogUsuario.s_emitirAvisoSonovo.equals("S"))
                new Audio("mostra-pessoa.wav").start();

                Moniersn.s_telaMsn.setSize(l_larguraPaineis, l_alturaGeral);
                LogUsuario.s_mostrandoPainelAmigo = true;

        }
        else{
            if(Moniersn.s_telaMsn.getSize().getHeight() >= l_alturaGeral && 
               LogUsuario.s_emitirAvisoSonovo.equals("S"))
                new Audio("mostra-pessoa.wav").start();
           
                Moniersn.s_telaMsn.setSize(l_larguraPaineis, l_alturaPainelUser);
                LogUsuario.s_mostrandoPainelAmigo = false;
           
        }
    }

    public void mostraComentarios(boolean l_extende){

        if(l_extende){

            if(Comentario.s_telaComentario.getSize().getHeight() <= 325  && 
               LogUsuario.s_emitirAvisoSonovo.equals("S"))
                new Audio("mostra-pessoa.wav").run();

          Comentario.s_telaComentario.setSize(610, 545);
          Comentario.s_mostrandoComentarios = true;
          Comentario.lb_qtdComentarios.setToolTipText(Avisos.TEXTO_CLIQUE_ESCONDER_COMENTS);
        }
        else{
            if(Comentario.s_telaComentario.getSize().getHeight() >= 545 && 
               LogUsuario.s_emitirAvisoSonovo.equals("S"))
                new Audio("mostra-pessoa.wav").run();

           Comentario.s_telaComentario.setSize(610, 325);
           Comentario.s_mostrandoComentarios = false;
           Comentario.lb_qtdComentarios.setToolTipText(Avisos.TEXTO_CLIQUE_MOSTRAR_COMENTS);
        }
    }

    public void mostraInfoSeguranca(boolean l_extende){

        if(l_extende){
            CadastroUsuario.s_telaCadastroMoniersn.setSize(468, 647);
            CadastroUsuario.pf_senha.requestFocus();
        }
        else
            CadastroUsuario.s_telaCadastroMoniersn.setSize(468, 452);

    }

    public void mostrarLogoGuiaConf(JPanel l_guia){

        int l_qtdGuias = Configuracoes.jtp_principal.getTabCount();
        int l_guiaSelecionada = Configuracoes.jtp_principal.getSelectedIndex();
        int l_contador = 0;

        String l_nomeGuia = null;
        String l_icone = null;

        if(l_guia.equals(Configuracoes.jp_infoBasica)){
            l_icone = Icones.LOGO_INICIO_R;
            l_nomeGuia = "INÍCIO";
        }
        if(l_guia.equals(Configuracoes.jp_infoPessoal)){
            l_icone = Icones.LOGO_INFO_PESSOAL_R;
            l_nomeGuia = "INFORMAÇÕES PESSOAIS";
        }
        if(l_guia.equals(Configuracoes.jp_infoContato)){
            l_icone = Icones.LOGO_INFO_CONTATO_R;
            l_nomeGuia = "INFORMAÇÕES DE CONTATO";
        }
        if(l_guia.equals(Configuracoes.jp_infoEducacional)){
            l_icone = Icones.LOGO_INFO_EDUC_R;
            l_nomeGuia = "INFOMAÇÕES EDUCACIONAIS";
        }
        if(l_guia.equals(Configuracoes.jp_infoProfissional)){
            l_icone = Icones.LOGO_INFO_PROF_R;
            l_nomeGuia = "INFORMAÇÕES PROFISSIONAIS";
        }
        if(l_guia.equals(Configuracoes.jp_cores)){
            l_icone = Icones.LOGO_CONF_CORES_R;
            l_nomeGuia = "CONFIGURAÇÕES DE CORES";
        }
        if(l_guia.equals(Configuracoes.jp_privacidade)){
            l_icone = Icones.LOGO_CONF_PRIV_R;
            l_nomeGuia = "CONFIGURAÇÕES DE PRIVACIDADE";
        }
        if(l_guia.equals(Configuracoes.jp_sobre)){
            l_icone = Icones.LOGO_SOBRE_FACE_R;
            l_nomeGuia = "SOBRE O FACECARD";
        }

            do{

                if(l_contador == l_guiaSelecionada)
                    Configuracoes.jtp_principal.setIconAt(l_contador, new javax.swing.ImageIcon(getClass().getResource(l_icone))); // NOI18N

                else{

                    if(l_guiaSelecionada != 0)
                       Configuracoes.jtp_principal.setIconAt(0, new javax.swing.ImageIcon(getClass().getResource(Icones.LOGO_INICIO_B))); // NOI18N
                    
                    if(l_contador == 1)
                       Configuracoes.jtp_principal.setIconAt(l_contador, new javax.swing.ImageIcon(getClass().getResource(Icones.INFO_PESSOAL_16))); // NOI18N
                    if(l_contador == 2)
                       Configuracoes.jtp_principal.setIconAt(l_contador, new javax.swing.ImageIcon(getClass().getResource(Icones.INFO_CONTATO_16))); // NOI18N
                    if(l_contador == 3)
                       Configuracoes.jtp_principal.setIconAt(l_contador, new javax.swing.ImageIcon(getClass().getResource(Icones.INFO_EDUC_16))); // NOI18N
                    if(l_contador == 4)
                       Configuracoes.jtp_principal.setIconAt(l_contador, new javax.swing.ImageIcon(getClass().getResource(Icones.INFO_PROF_16))); // NOI18N
                    if(l_contador == 5)
                       Configuracoes.jtp_principal.setIconAt(l_contador, new javax.swing.ImageIcon(getClass().getResource(Icones.CONF_CORES_16))); // NOI18N
                    if(l_contador == 6)
                       Configuracoes.jtp_principal.setIconAt(l_contador, new javax.swing.ImageIcon(getClass().getResource(Icones.CONF_PRIV_16))); // NOI18N
                    if(l_contador == 7)
                       Configuracoes.jtp_principal.setIconAt(l_contador, new javax.swing.ImageIcon(getClass().getResource(Icones.SOBRE_FACE_16))); // NOI18N
               
                }

                l_contador++;

            }while(l_contador <= l_qtdGuias);


            //seta título
            MostrarDados.s_MostrarDados.setaTituloFrame(l_nomeGuia, "config");

    }
    
    public void abrirArquivoSelecionado(String l_nomeArquivo, String l_pasta){

        String l_diretorio = null;
        String l_caminhoArquivo = null;

        l_diretorio = Diretorios.pegaCaminhoArquivo(l_pasta, l_nomeArquivo);

        l_caminhoArquivo = l_diretorio;

        File l_arquivo = new File(l_caminhoArquivo);

            if(l_arquivo.exists()){

                //MINIMIZA A TELA PARA VISU ARQUIVO ABERTO
                Moniersn.s_telaMsn.setVisible(false);
                MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
                MostrarDados.s_MostrarDados.mostraAvisoProcedimento(Avisos.FACECARD_FULL, Avisos.AVISO_EXECUCAO_SYSTRAY+"\n\n"+Avisos.AVISO_ABRINDO_ARQUIVO+l_arquivo.getName(), "INFO");
                
                if(Desktop.isDesktopSupported()){

                    try{

                        System.out.println("caminho: "+l_arquivo);
                        Desktop.getDesktop().open(l_arquivo);
                        System.out.println("Arquivo aberto com sucesso via API Desktop!");

                    }catch(Exception e){

                        System.err.println("Erro ao tentar abrir o arquivo selecionado via API Desktop.\n"
                                         + "    Erro:"+e);
                        
                        AbreArquivoViaRunTime aRT = new AbreArquivoViaRunTime(l_caminhoArquivo, "MSWindows");
                        new Thread(aRT).start();

                    }

                }else{
                    System.err.println("API Desktop não tem suporte neste S.O.");
                    System.out.println("Tentando abrir arquivo via Runtime...");
                    AbreArquivoViaRunTime aRT = new AbreArquivoViaRunTime(l_caminhoArquivo, "MSWindows");
                    new Thread(aRT).start();
                }
                
            }else{

                boolean l_localArquivosOk = SolicitarDados.s_SolicitarDados.verificaLocalArquivos(LogUsuario.s_cod, LogUsuario.s_localDeArquivosDoUsuario, "atu"); 
                
                    if(l_localArquivosOk){
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_INEXISTENTE, "erro");
                        //habilita botoes de arquivos
                        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(Moniersn.jt_listaArquivosUsuario);
                    }else{
                        Moniersn.s_telaMsn.setVisible(false); 
                        MostrarDados.s_MostrarDados.mostraTelaEscolhaDiretorio("localizar");
                    }

            }
    }
    
    
 public void abreDoc(String l_nomeDoc){
            
            String l_caminhoArquivo = "/docs/"+l_nomeDoc+".pdf";
                    
            InputStream in = this.getClass().getResourceAsStream(l_caminhoArquivo);  
            File l_arquivoTemp = null;
            
            
                    byte[] buf = new byte[1024];
                    int len;

                        try{

                            l_arquivoTemp = File.createTempFile(l_nomeDoc+"-facecard-by-monier" , ".pdf"); 
                            l_arquivoTemp.deleteOnExit(); 
                                 
                            OutputStream out = new FileOutputStream(l_arquivoTemp);

                                while ((len = in.read(buf)) > 0){
                                    out.write(buf, 0, len);
                                }

                            out.flush();
                            in.close();
                            out.close();

                        }catch(Exception e){

                        }

                            if(Desktop.isDesktopSupported()){

                                try{

                                    Desktop.getDesktop().open(l_arquivoTemp);
                                    System.out.println("Arquivo aberto com sucesso via API Desktop!");

                                }catch(Exception e){

                                    System.err.println("Erro ao tentar abrir o arquivo selecionado via API Desktop.\n"
                                                    + "    Erro:"+e);

                                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.ERRO_ABRIR_DOCS, "erro");

                                }

                            }else{
                                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.ERRO_API_GET_DESKTOP, "erro");
                            }

    }

    public void abrirLocalArquivos(){

    
        String l_diretorio = null;
        String l_caminhoDir = null;

        l_diretorio = LogUsuario.s_localDeArquivosDoUsuario+"\\"+Diretorios.PASTA_ARQUIVOS;

        l_caminhoDir = l_diretorio;

        File l_dir = new File(l_caminhoDir);

            if(l_dir.exists()){

                //MINIMIZA A TELA PARA VISU ARQUIVO ABERTO
                Moniersn.s_telaMsn.setVisible(false);
                MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
                MostrarDados.s_MostrarDados.mostraAvisoProcedimento(Avisos.FACECARD_FULL, Avisos.AVISO_EXECUCAO_SYSTRAY+"\n\n"+Avisos.AVISO_ABRINDO_LOCAL_ARQ+l_diretorio, "INFO");
                
                if(Desktop.isDesktopSupported()){

                    try{

                        System.out.println("caminho: "+l_caminhoDir);
                        Desktop.getDesktop().open(l_dir);
                        System.out.println("Diretório aberto com sucesso via API Desktop!");

                    }catch(Exception e){

                        System.err.println("Erro ao tentar abrir o diretório selecionado via API Desktop.\n"
                                         + "    Erro:"+e);
                        
                        AbreArquivoViaRunTime aRT = new AbreArquivoViaRunTime(l_caminhoDir, "MSWindows");
                        new Thread(aRT).start();

                    }

                }else{
                    System.err.println("API Desktop não tem suporte neste S.O.");
                    System.out.println("Tentando abrir arquivo via Runtime...");
                    AbreArquivoViaRunTime aRT = new AbreArquivoViaRunTime(l_caminhoDir, "MSWindows");
                    new Thread(aRT).start();
                }
                
            }else{

                boolean l_localArquivosOk = SolicitarDados.s_SolicitarDados.verificaLocalArquivos(LogUsuario.s_cod, LogUsuario.s_localDeArquivosDoUsuario, "atu"); 
                
                    if(l_localArquivosOk){
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ARQ_INEXISTENTE, "erro");
                        //habilita botoes de arquivos
                        MostrarDados.s_MostrarDados.habilitaBotoesConteudo(Moniersn.jt_listaArquivosUsuario);
                    }else{
                        Moniersn.s_telaMsn.setVisible(false); 
                        MostrarDados.s_MostrarDados.mostraTelaEscolhaDiretorio("localizar");
                    }
            }
    }
    
    public void mostrarSobreFacecard(){
    
        try{
           
            
            Login.s_telaLogin.setVisible(false); 

            Configuracoes.s_telaConfiguracoes = new Configuracoes();
            Configuracoes.s_telaConfiguracoes.c_desabilitaCompShownGuiasConf = true;

            Configuracoes.jtp_principal.remove(Configuracoes.jp_infoBasica);
            Configuracoes.jtp_principal.remove(Configuracoes.jp_infoPessoal);
            Configuracoes.jtp_principal.remove(Configuracoes.jp_infoContato);
            Configuracoes.jtp_principal.remove(Configuracoes.jp_infoEducacional);
            Configuracoes.jtp_principal.remove(Configuracoes.jp_infoProfissional);
            Configuracoes.jtp_principal.remove(Configuracoes.jp_privacidade);
            Configuracoes.jtp_principal.remove(Configuracoes.jp_cores);

            //seta título
            Configuracoes.s_telaConfiguracoes.setTitle("Sobre o Facecard™");

            Configuracoes.jtp_principal.setIconAt(0, new javax.swing.ImageIcon(getClass().getResource(Icones.LOGO_SOBRE_FACE_R)));
            
            Configuracoes.s_telaConfiguracoes.setVisible(true); 
            
            Aguardando.s_telaAguardando.setVisible(false); 
            
        }catch(Exception e){
            
            Aguardando.s_telaAguardando.setVisible(false); 
            System.err.println("Erro ao tentar carregar informações Sobre o Facecard.   \nErro: "+e);
            Login.s_telaLogin.setVisible(true); 
            
        }
    
    }



}
