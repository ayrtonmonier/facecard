/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;
import javax.swing.*;
import formularios.*;
import javax.swing.table.TableModel;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import auxiliar.sockets.ClienteUDP;
import java.io.File;

/**
 *
 * @author f113cpd02
 */
public class AtualizarDados {

    public static AtualizarDados s_AtualizarDados = new AtualizarDados();

    
    public void atualizaValorTextFieldNoBanco(JTextField l_campo){

        String l_tipoPerfil = null;
        String l_msgAtualizacao = null;
        String l_atributoBanco = null;

        String l_valorBanco = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_campo.getText()).toString().toUpperCase();
        int l_qtdCaracteres = l_campo.getText().length();

        //CASO MAIS Q 50 CARACTERES...
        if(l_qtdCaracteres > 50){
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_EXCESSO_CARACT_50, "aviso");
            l_campo.requestFocus();
        }

        //TESTES PARA GRAVAÇÃO
        else{

        //INFORMAÇÕES BÁSICAS

            //TESTE DE VALOR DE CAMPO
            if(l_valorBanco.isEmpty()){

                l_valorBanco = "*";
                l_campo.setText(l_valorBanco);

            }

            //NOME
            if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_nome)){
                l_tipoPerfil = "basico";
                l_atributoBanco = "nome";
                l_msgAtualizacao = Avisos.MUDOU_NOME;
            }

            //SOBRENOME
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_sobrenome)){
                l_tipoPerfil = "basico";
                l_atributoBanco = "sobrenome";
                l_msgAtualizacao = Avisos.MUDOU_SOBRENOME;
            }

            //MINHA FRASE
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_frase)){
                l_tipoPerfil = "basico";
                l_atributoBanco = "minha_frase";
                l_msgAtualizacao = Avisos.MUDOU_FRASE;
            }

     //INFORMAÇÕES PESSOAIS


            //QUEM EU SOU
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_queEuSou)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "quem_eu_sou";
                l_msgAtualizacao = Avisos.MUDOU_QUEM_SOU;
            }
            //ADORO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_adoro)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "adoro";
                l_msgAtualizacao = Avisos.MUDOU_ADORO;
            }
            //ODEIO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_odeio)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "odeio";
                l_msgAtualizacao = Avisos.MUDOU_ODEIO;
            }
            //UM LUGAR
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_lugar)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "lugar";
                l_msgAtualizacao = Avisos.MUDOU_LUGAR;
            }
            //CONSELHO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_conselho)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "conselho";
                l_msgAtualizacao = Avisos.MUDOU_CONSELHO;
            }
            //CANTADA
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_cantada)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "cantada";
                l_msgAtualizacao = Avisos.MUDOU_CANTADA;
            }
            //MANIA
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_mania)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "mania";
                l_msgAtualizacao = Avisos.MUDOU_MANIA;
            }
            //SONHO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_sonho)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "sonho";
                l_msgAtualizacao = Avisos.MUDOU_SONHO;
            }
            //PESADELO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_pesadelo)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "pesadelo";
                l_msgAtualizacao = Avisos.MUDOU_PESADELO;
            }
            //PASSEIO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_passeio)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "passeio";
                l_msgAtualizacao = Avisos.MUDOU_PASSEIO;
            }
            //VIAGEM
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_viagem)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "viagem";
                l_msgAtualizacao = Avisos.MUDOU_VIAGEM;
            }
            //DESEJO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_desejo)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "desejo";
                l_msgAtualizacao = Avisos.MUDOU_DESEJO;
            }
            //VONTADE
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_vontade)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "vontade";
                l_msgAtualizacao = Avisos.MUDOU_VONTADE;
            }
            //ESPERANÇA
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_esperanca)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "esperanca";
                l_msgAtualizacao = Avisos.MUDOU_ESPERANCA;
            }

   //INFORMAÇÕES DE CONTATO

            //RUA
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_rua)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "rua";
                l_msgAtualizacao = Avisos.MUDOU_RUA;
            }
            //NUMERO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_numero)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "numero";
                l_msgAtualizacao = Avisos.MUDOU_NUMERO_CASA;
            }
            //FONE
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_fone)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "fone";
                l_msgAtualizacao = Avisos.MUDOU_FONE_FIXO;
            }
            //CELULAR OI
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_celOi)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "cel_oi";
                l_msgAtualizacao = Avisos.MUDOU_CELULAR_OI;
            }
            //CELULAR TIM
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_celTim)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "cel_tim";
                l_msgAtualizacao = Avisos.MUDOU_CELULAR_TIM;
            }
            //CELULAR VIVO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_celVivo)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "cel_vivo";
                l_msgAtualizacao = Avisos.MUDOU_CELULAR_VIVO;
            }
            //CELULAR CLARO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_celClaro)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "cel_claro";
                l_msgAtualizacao = Avisos.MUDOU_CELULAR_CLARO;
            }
            //CELULAR OUTRO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_celOutro)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "cel_outro";
                l_msgAtualizacao = Avisos.MUDOU_CELULAR_OUTRO;
            }
            //EMAIL HOTMAIL
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_emailHotmail)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "email_hotmail";
                l_msgAtualizacao = Avisos.MUDOU_EMAIL_HOTMAIL;
            }
            //EMAIL GMAIL
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_emailGmail)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "email_gmail";
                l_msgAtualizacao = Avisos.MUDOU_EMAIL_GMAIL;
            }
            //EMAIL YAHOO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_emailYahoo)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "email_yahoo";
                l_msgAtualizacao = Avisos.MUDOU_EMAIL_YAHOO;
             }
            //EMAIL IG
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_emailIg)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "email_ig";
                l_msgAtualizacao = Avisos.MUDOU_EMAIL_IG;
            }
            //EMAIL BOL
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_emailBol)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "email_bol";
                l_msgAtualizacao = Avisos.MUDOU_EMAIL_BOL;
            }
            //EMAIL OUTRO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_emailOutro)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "email_outro";
                l_msgAtualizacao = Avisos.MUDOU_EMAIL_OUTRO;
            }
            //ORKUT
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_orkut)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "orkut";
                l_msgAtualizacao = Avisos.MUDOU_ORKUT;
            }
            //TWITER
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_twitter)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "twitter";
                l_msgAtualizacao = Avisos.MUDOU_TWITTER;
            }
            //FACEBOOK
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_facebook)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "facebook";
                l_msgAtualizacao = Avisos.MUDOU_FACEBOOK;
            }
            //MYSPACE
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_myspace)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "myspace";
                l_msgAtualizacao = Avisos.MUDOU_MYSPACE;
            }
            //SKYPE
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_skype)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "skype";
                l_msgAtualizacao = Avisos.MUDOU_SKYPE;
            }
            //LINKEDIN
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_linkedin)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "linkedin";
                l_msgAtualizacao = Avisos.MUDOU_LINKEDIN;
            }
            //BLOG
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_blog)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "blog";
                l_msgAtualizacao = Avisos.MUDOU_BLOG;
            }

    //INFORMAÇÕES EDUCACIONAIS

            //NÃO GOSTO DE...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_naoGostoDeEduc)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "nao_gosto_inst";
                l_msgAtualizacao = Avisos.MUDOU_NAO_GOSTO_DE_EDUC;
            }
            //SEMPRE GOSTO DE...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_sempreGostoDeEduc)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "sempre_gosto_inst";
                l_msgAtualizacao = Avisos.MUDOU_SEMPRE_GOSTO_DE_EDUC;
            }
            //VOU MELHORAR EM...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_vouMelhorarEmEduc)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "vou_melhorar_inst";
                l_msgAtualizacao = Avisos.MUDOU_VOU_MELHORAR_EM_EDUC;
            }
            //A INSTITUIÇÃO É...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_aInstituicaoEh)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "a_instituicao_e";
                l_msgAtualizacao = Avisos.MUDOU_A_INSTITUICAO_E;
            }
            //ESTÁ FALTANDO SÓ...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_estaFaltandoEduc)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "esta_faltando_inst";
                l_msgAtualizacao = Avisos.MUDOU_ESTA_FALTANDO_SO_EDUC;
            }
            //NAS HORAS VAGAS...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_nasHorasVagas)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "nas_horas_vagas_inst";
                l_msgAtualizacao = Avisos.MUDOU_NAS_HORAS_VAGAS;
            }
            //MINHA TURMA É...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_minhaTurmaEh)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "minha_turma_e";
                l_msgAtualizacao = Avisos.MUDOU_MINHA_TURMA_E;
            }

    //INFORMAÇÕES PROFISSIONAIS

            //NÃO GOSTO DE...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_naoGostoDeEmp)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "nao_gosto_emp";
                l_msgAtualizacao = Avisos.MUDOU_NAO_GOSTO_DE_PROF;
            }
            //SEMPRE GOSTO DE...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_sempreGostoDeEmp)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "sempre_gosto_emp";
                l_msgAtualizacao = Avisos.MUDOU_SEMPRE_GOSTO_DE_PROF;
            }
            //VOU MELHORAR EM...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_vouMelhorarEmEmp)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "vou_melhorar_emp";
                l_msgAtualizacao = Avisos.MUDOU_VOU_MELHORAR_EM_PROF;
            }
            //A EMPRESA É...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_aEmpresaEh)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "a_empresa_e";
                l_msgAtualizacao = Avisos.MUDOU_A_EMPRESA_E;
            }
            //ESTÁ FALTANDO SÓ...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_estaFaltandoEmp)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "esta_faltando_emp";
                l_msgAtualizacao = Avisos.MUDOU_ESTA_FALTANDO_SO_PROF;
            }
            //EMAIL CORPORATIVO
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_emailCorp)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "email_corp_emp";
                l_msgAtualizacao = Avisos.MUDOU_EMAIL_CORP;
            }
            //FONE.
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_foneEmpresa)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "fone_emp";
                l_msgAtualizacao = Avisos.MUDOU_FONE_FIXO;
            }
            //RAMAL
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_ramalEmpresa)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "ramal_emp";
                l_msgAtualizacao = Avisos.MUDOU_RAMAL;
            }
            //FAX
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_faxEmpresa)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "fax_emp";
                l_msgAtualizacao = Avisos.MUDOU_FAX;
            }

            //MEU SETOR É...
            else if(l_campo.equals(Configuracoes.s_telaConfiguracoes.tf_meuSetorEh)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "meu_setor_e";
                l_msgAtualizacao = Avisos.MUDOU_MEU_SETOR_E;
            }

            //SE GRAVADO COM SUCESSO...
            if(Dados.s_conexaoBanco.executeUPDATE("usuario", l_atributoBanco, l_valorBanco, LogUsuario.s_cod)){

                    if(l_atributoBanco.equals("nome")){
                        //Atualiza variáveis estaticas da classe log
                        LogUsuario.s_nome = l_campo.getText();
                        //atualiza título da tela de configurações
                        Configuracoes.s_telaConfiguracoes.setTitle(Avisos.TIT_JAN_CONFIG+" "+LogUsuario.s_nome+" "+LogUsuario.s_sobrenome+" - "+Avisos.FACECARD_FULL);
                        //atualiza nome do usuário na tela principal
                        Moniersn.lb_nomeIconeHumorUsuario.setText(LogUsuario.s_nome+" "+LogUsuario.s_sobrenome);

                    }
                    if(l_atributoBanco.equals("sobrenome")){
                        //Atualiza variáveis estaticas da classe log
                        LogUsuario.s_sobrenome = l_campo.getText();
                        //atualiza título da tela de configurações
                        Configuracoes.s_telaConfiguracoes.setTitle(Avisos.TIT_JAN_CONFIG+" "+LogUsuario.s_nome+" "+LogUsuario.s_sobrenome+" - "+Avisos.FACECARD_FULL);
                        //atualiza nome do usuário na tela principal
                        Moniersn.lb_nomeIconeHumorUsuario.setText(LogUsuario.s_nome+" "+LogUsuario.s_sobrenome);
                    }

                    if(l_atributoBanco.equals("minha_frase")){
                        //Atualiza minha frase na tela principal
                        Moniersn.lb_fraseUsuario.setText(l_valorBanco);
                    }

                //ATUALIZA NOTIFICAÇÃO PARA AMIGOS
                //GRAVA A ATUALIZAÇÃO
                String l_msgAtu = l_msgAtualizacao+" "+l_valorBanco;

                    if(!l_valorBanco.equals("*"))
                        GravarDados.s_GravarDados.gravarAtualizacao(l_tipoPerfil, l_atributoBanco, l_msgAtu);

                l_campo.setText(l_valorBanco);



                //mostra aviso de atualização
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");

            }else{

                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");

            }
        }
    }



    public void atualizaValorComboBoxNoBanco(JComboBox l_combo, boolean l_editavel){

        String l_valorCombo = l_combo.getSelectedItem().toString().toUpperCase();
        String l_valorBanco = null;
        String l_atributoBanco = null;
        String l_tabela = null;
        String l_complementoAtributo = "";
        String l_msgAtualizacao = null;
        String l_tipoPerfil = null;

        int l_qtdCaracteres = l_combo.getSelectedItem().toString().length();


        //CASO MAIS Q 50 CARACTERES...
        if(l_qtdCaracteres > 50){
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_EXCESSO_CARACT_50, "aviso");
            //Moniersn.s_telaMsn.mostraAvisoProcedimento("MonierSN", "Só é permitido 50 Caracteres, tem "+l_qtdCaracteres+"!", "AVISO");
            l_combo.requestFocus();
        }

        else{

            //TESTE DE VALOR
            if(l_combo.getSelectedItem().toString().isEmpty() || l_combo.getSelectedItem().toString().equals("*")){
                     l_valorCombo = "*";
                     l_combo.setSelectedItem(l_valorCombo);
                     l_combo.setEditable(false);
                     l_editavel = false;
            }

      //INFORMAÇÕES BÁSICAS
            //SELEÇÃO DE ICONE HUMOR
            if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_humor)){

                    l_tipoPerfil = "basico";
                    l_atributoBanco = "icone_humor";
                    l_valorBanco = l_valorCombo.toLowerCase();
                    l_msgAtualizacao = Avisos.MUDOU_ICONE_HUMOR;

            }

     //INFORMAÇÕES PESSOAIS

            //FÍSICO
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_fisico)){

                String l_fisicoAtual = SolicitarDados.s_SolicitarDados.buscaFisico(LogUsuario.s_cod);

                l_tipoPerfil = "pessoal";
                l_atributoBanco = "fisico";

                l_valorBanco = l_valorCombo;

                    if(l_fisicoAtual.equals(l_valorCombo))
                        l_valorCombo = "*";
                    else if(l_fisicoAtual.equals("*"))
                        l_msgAtualizacao = Avisos.MUDOU_FISICO;
                    else
                        l_msgAtualizacao = Avisos.MUDOU_FISICO_DE+l_fisicoAtual+" "+Avisos.TEXTO_PARA;
            }

            //ESTADO CIVÍL
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_estadoCivil)){

                String l_estadoCivilAtual = SolicitarDados.s_SolicitarDados.buscaEstadoCivil(LogUsuario.s_cod);

                l_tipoPerfil = "pessoal";
                l_atributoBanco = "estado_civil";


                l_valorBanco = l_valorCombo;

                    if(l_estadoCivilAtual.equals(l_valorCombo))
                        l_valorCombo = "*";
                    else if (l_estadoCivilAtual.equals("*"))
                        l_msgAtualizacao = Avisos.MUDOU_ESTADO_CIVIL;
                    else
                        l_msgAtualizacao = Avisos.MUDOU_ESTADO_CIVIL_DE+l_estadoCivilAtual+" "+Avisos.TEXTO_PARA;
            }

            //NACIONALIDADE
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_nacionalidade)){

                l_tipoPerfil = "pessoal";
                l_atributoBanco = "nacionalidade";
                l_valorBanco = l_valorCombo;
                l_tabela = "nacionalidade";
                l_msgAtualizacao = Avisos.MUDOU_NACIONALIDADE;
            }
            //NATURALIDADE
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_naturalidade)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "naturalidade";
                l_valorBanco = l_valorCombo;
                l_tabela = "naturalidade";
                l_msgAtualizacao = Avisos.MUDOU_NATURALIDADE;
            }

            //FILME
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_filme)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "filme";
                l_valorBanco = l_valorCombo;
                l_tabela = "filme";
                l_msgAtualizacao = Avisos.MUDOU_FILME;
            }
            //MUSICA
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_musica)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "musica";
                l_valorBanco = l_valorCombo;
                l_tabela = "musica";
                l_msgAtualizacao = Avisos.MUDOU_MUSICA;
            }
            //ESPORTE
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_esporte)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "esporte";
                l_valorBanco = l_valorCombo;
                l_tabela = "esporte";
                l_msgAtualizacao = Avisos.MUDOU_ESPORTE;
            }
            //COR
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_cor)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "cor";
                l_valorBanco = l_valorCombo;
                l_tabela = "cor";
                l_msgAtualizacao = Avisos.MUDOU_COR;
            }
            //TIME FUTEBOL
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_timeFutebol)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "time_futebol";
                l_valorBanco = l_valorCombo;
                l_tabela = "time_futebol";
                l_msgAtualizacao = Avisos.MUDOU_TIME_FUTEBOL;
            }
            //BEBIDA
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_bebida)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "bebida";
                l_valorBanco = l_valorCombo;
                l_tabela = "bebida";
                l_msgAtualizacao = Avisos.MUDOU_BEBIDA;
            }
            //PRATO
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_prato)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "prato";
                l_valorBanco = l_valorCombo;
                l_tabela = "prato";
                l_msgAtualizacao = Avisos.MUDOU_PRATO;
            }
            //HOBBY
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_hobby)){
                l_tipoPerfil = "pessoal";
                l_atributoBanco = "hobby";
                l_valorBanco = l_valorCombo;
                l_tabela = "hobby";
                l_msgAtualizacao = Avisos.MUDOU_HOBBY;
            }


       //INFORMAÇÕES DE CONTATO

            //BAIRRO
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_bairro)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "bairro";
                l_valorBanco = l_valorCombo;
                l_tabela = "bairro";
                l_msgAtualizacao = Avisos.MUDOU_BAIRRO;
            }
            //CIDADE
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_cidade)){
                l_tipoPerfil = "contato";
                l_atributoBanco = "cidade";
                l_valorBanco = l_valorCombo;
                l_tabela = "cidade";
                l_msgAtualizacao = Avisos.MUDOU_CIDADE;
            }

       //INFORMAÇÕES EDUCACIONAIS

            //INSTITUIÇÃO DE ENSINO
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_instEnsino)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "instituicao_ensino";
                l_valorBanco = l_valorCombo;
                l_tabela = "instituicao_ensino";
                l_msgAtualizacao = Avisos.MUDOU_INST_ENSINO;
            }
            //CURSO
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_curso)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "curso";
                l_valorBanco = l_valorCombo;
                l_tabela = "curso";
                l_msgAtualizacao = Avisos.MUDOU_CURSO;
            }
            //TURMA
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_turma)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "turma";
                l_valorBanco = l_valorCombo;
                l_tabela = "turma";
                l_msgAtualizacao = Avisos.MUDOU_TURMA;
            }
            //MELHOR DISCIPLINA
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_melhorDisciplina)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "disciplina";
                l_complementoAtributo = "melhor_";
                l_valorBanco = l_valorCombo;
                l_tabela = "disciplina";
                l_msgAtualizacao = Avisos.MUDOU_MELHOR_DISCIPLINA;
            }
            //PIOR DISCIPLINA
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_piorDisciplina)){
                l_tipoPerfil = "educacional";
                l_atributoBanco = "disciplina";
                l_complementoAtributo = "pior_";
                l_valorBanco = l_valorCombo;
                l_tabela = "disciplina";
                l_msgAtualizacao = Avisos.MUDOU_PIOR_DISCIPLINA;
            }


       //INFORMAÇÕES PROFISSIONAIS

            //EMPRESA
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_empresa)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "empresa";
                l_valorBanco = l_valorCombo;
                l_tabela = "empresa";
                l_msgAtualizacao = Avisos.MUDOU_EMPRESA;
            }
            //CARGO
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_cargo)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "cargo";
                l_valorBanco = l_valorCombo;
                l_tabela = "cargo";
                l_msgAtualizacao = Avisos.MUDOU_CARGO;
            }
            //SETOR
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_setor)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "setor";
                l_valorBanco = l_valorCombo;
                l_tabela = "setor";
                l_msgAtualizacao = Avisos.MUDOU_SETOR;
            }
            //ATIVIDADE
            else if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_atividade)){
                l_tipoPerfil = "profissional";
                l_atributoBanco = "atividade_emp";
                l_valorBanco = l_valorCombo;
                l_tabela = "atividade_emp";
                l_msgAtualizacao = Avisos.MUDOU_ATIVIDADE;
            }

            l_valorBanco = SolicitarDados.s_SolicitarDados.trataAspasSimplesParaBD(l_valorBanco);

            //CASO NOVO REGISTRO
            if(l_editavel && SolicitarDados.s_SolicitarDados.verificaSeItemJaExiste(l_combo, l_tabela, l_atributoBanco, l_valorBanco)){
                if(Dados.s_conexaoBanco.executeINSERT(l_tabela, l_atributoBanco, l_valorBanco)){
                        if(Dados.s_conexaoBanco.executeUPDATE("usuario", l_complementoAtributo+l_atributoBanco, l_valorBanco, LogUsuario.s_cod)){
                            
                            //mostra aviso de atualização
                            MostrarDados.s_MostrarDados.carregaItensComboBox(l_combo, l_tabela, l_atributoBanco);
                            l_combo.setSelectedItem(l_valorCombo);
                            l_combo.setEditable(false);

                            String l_descricaoAtu = l_msgAtualizacao+" "+l_valorBanco;

                                if(!l_valorBanco.equals("*"))
                                    GravarDados.s_GravarDados.gravarAtualizacao(l_tipoPerfil, l_atributoBanco, l_descricaoAtu);

                            //mostra aviso de atualização
                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");

                        }else{
                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                            return;
                        }
                  }else{
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                        return;
                  }
             }
             //CASO REGISTRO JÁ EXISTENTE
             else{

                 //TESTE DE VALOR ANTES DE GRAVAR
                 //caso icone humor... salva em minusculo...
                 if(l_atributoBanco.equals("icone_humor"))
                     l_atributoBanco = l_atributoBanco.toLowerCase();

                 if(Dados.s_conexaoBanco.executeUPDATE("usuario", l_complementoAtributo+l_atributoBanco, l_valorBanco, LogUsuario.s_cod)){

                     String l_descricaoAtu = l_msgAtualizacao+" "+l_valorBanco;

                         //APÓS GRAVAR
                         //caso gravou ícone de humor... atualiza na tela principal
                         if(l_combo.equals(Configuracoes.s_telaConfiguracoes.cb_humor)){
                             atualizaIconeHumor(l_valorBanco);
                         }

                         if(!l_valorCombo.equals("*"))
                            GravarDados.s_GravarDados.gravarAtualizacao(l_tipoPerfil, l_atributoBanco, l_descricaoAtu);

                     //mostra aviso de atualização
                     MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");                 
                 }else{
                     //mostra aviso de atualização
                     MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                     return;
                 }
             }
        }//fim do else apos teste
    }

    public void atualizaValorCheckBoxNoBanco(JCheckBox l_check){

        String l_valorBanco = null;
        String l_atributoBanco = null;
        String l_decisao = null;
        String l_msgConfirmacao = null;
        String l_habDesab = null;

        boolean l_selecionado = l_check.isSelected();

        if(l_selecionado){
            l_decisao = "S";
            l_habDesab = Avisos.TEXTO_HABILITADO;
        }
        else{
            l_decisao = "N";
            l_habDesab = Avisos.TEXTO_DESABILITADO;
        }

        l_valorBanco = l_decisao;

    //INFORMAÇOES BASICAS
        if(l_check.equals(Configuracoes.s_telaConfiguracoes.cb_mostrarMeuNome)){
            l_atributoBanco = "mostrar_nome ";
            l_msgConfirmacao = Avisos.TEXTO_OK_MOSTRAR_NOME+l_habDesab;
        }

    //INFORMAÇÕES DE PRIVACIDADE

        //MOSTRAR MINHA FRASE
        if(l_check.equals(Configuracoes.s_telaConfiguracoes.cb_mostrarMinhaFrase)){
            l_atributoBanco = "mostrar_minha_frase";
            l_msgConfirmacao = Avisos.TEXTO_OK_MOSTRAR_FRASE+l_habDesab;
        }
        //MOSTRAR SEXO
        else if(l_check.equals(Configuracoes.s_telaConfiguracoes.cb_mostrarSexo)){
            l_atributoBanco = "mostrar_sexo";
            l_msgConfirmacao = Avisos.TEXTO_OK_MOSTRAR_SEXO+l_habDesab;
        }
        //MOSTRAR DATA DE ANIVERSARIO
        else if(l_check.equals(Configuracoes.s_telaConfiguracoes.cb_mostrarDataNiver)){
            l_atributoBanco = "mostrar_data_niver";
            l_msgConfirmacao = Avisos.TEXTO_OK_MOSTRAR_DATA_NIVER+l_habDesab;
        }
        //MOSTRAR DATA DE CADASTRO
        else if(l_check.equals(Configuracoes.s_telaConfiguracoes.cb_mostrarDataCadastro)){
            l_atributoBanco = "mostrar_data_cadastro";
            l_msgConfirmacao = Avisos.TEXTO_OK_MOSTRAR_DATA_CADASTRO+l_habDesab;
        }

        //MOSTRA BALAO DE AVISO
        else if(l_check.equals(Configuracoes.s_telaConfiguracoes.cb_mostraBalaoAviso)){

            //se selecionado
            if(l_selecionado && Configuracoes.s_telaConfiguracoes.cb_emiteAvisoSonoro.isSelected())
                Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.MOSTRA_AVISO_EMITE_AUDIO)));
                    else if(l_selecionado && !Configuracoes.s_telaConfiguracoes.cb_emiteAvisoSonoro.isSelected())
                        Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.MOSTRA_AVISO_N_EMITE_AUDIO)));
                            else if(!l_selecionado && Configuracoes.s_telaConfiguracoes.cb_emiteAvisoSonoro.isSelected())
                                Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.N_MOSTRA_AVISO_EMITE_AUDIO)));
                                      else
                                           Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.N_MOSTRA_AVISO_N_EMITE_AUDIO)));
            l_atributoBanco = "mostrar_balao_aviso";
            LogUsuario.s_mostrarBalaoAviso = l_decisao;
            l_msgConfirmacao = Avisos.TEXTO_OK_MOSTRAR_BALAO_AVISOS+l_habDesab;
        }

        //EMITIR AVISO SONORO
        else if(l_check.equals(Configuracoes.s_telaConfiguracoes.cb_emiteAvisoSonoro)){

            //se selecionado
            if(l_selecionado && Configuracoes.s_telaConfiguracoes.cb_mostraBalaoAviso.isSelected())
                Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.MOSTRA_AVISO_EMITE_AUDIO))); // NOI18N
                    else if(l_selecionado && !Configuracoes.s_telaConfiguracoes.cb_mostraBalaoAviso.isSelected())
                        Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.N_MOSTRA_AVISO_EMITE_AUDIO))); // NOI18N
                            else if(!l_selecionado && Configuracoes.s_telaConfiguracoes.cb_mostraBalaoAviso.isSelected())
                                Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.MOSTRA_AVISO_N_EMITE_AUDIO))); // NOI18N
                                      else
                                           Configuracoes.s_telaConfiguracoes.lb_imagemBalaoAviso.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.N_MOSTRA_AVISO_N_EMITE_AUDIO))); // NOI18N
            l_atributoBanco = "emitir_aviso_sonoro";
            LogUsuario.s_mostrarBalaoAviso = l_decisao;
            l_msgConfirmacao = Avisos.TEXTO_OK_EMITIR_AVISO_SON+l_habDesab;
        }

        //SOLICITAR SENHA AO MOSTRAR DA BANDEJA DO SISTEMA
        else if(l_check.equals(Configuracoes.s_telaConfiguracoes.cb_solicitarSenhaAoMostrar)){
            l_atributoBanco = "solicitar_senha_ao_mostrar";
            l_msgConfirmacao = Avisos.TEXTO_OK_SEGURANCA+l_habDesab;

        }
        //SOLICITAR SENHA AO CONFIGURAR
        else if(l_check.equals(Configuracoes.s_telaConfiguracoes.cb_solicitarSenhaAoConfig)){
            l_atributoBanco = "solicitar_senha_ao_config";
            l_msgConfirmacao = Avisos.TEXTO_OK_SOLIC_SENHA_CONFIG+l_habDesab;
        }

         if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", l_atributoBanco, l_valorBanco, LogUsuario.s_cod)){

             //TESTE DE ATRIBUTO ATUALIZADO
                if(l_atributoBanco.equals("solicitar_senha_ao_mostrar")){

                        LogUsuario.s_solicitarSenhaAoMostrar = l_decisao;
                        
                            //altera o icone para fechado
                            if(l_decisao.equals("S")){
                                Configuracoes.s_telaConfiguracoes.lb_habilitarSegSystray.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.FECHADO_SYSTRAY)));
                                new Thread(new Audio("seguranca-habilitada.wav")).start();
                            }else{
                                Configuracoes.s_telaConfiguracoes.lb_habilitarSegSystray.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LIBERADO_SYSTRAY)));
                                new Thread(new Audio("seguranca-desabilitada.WAV")).start();
                            }
                }

                     else if(l_atributoBanco.equals("solicitar_senha_ao_config")){

                         LogUsuario.s_solicitarSenhaAoConfig = l_decisao;
                           
                            //altera o icone para fechado
                            if(l_decisao.equals("S")){
                                Configuracoes.s_telaConfiguracoes.lb_habilitarSegConf.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.FECHADO_CONF)));
                                 new Thread(new Audio("seguranca-habilitada.wav")).start();
                            }
                            else{
                                Configuracoes.s_telaConfiguracoes.lb_habilitarSegConf.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icones.LIBERADO_CONF))); 
                                new Thread(new Audio("seguranca-desabilitada.WAV")).start();
                            }
                     }
                             else if(l_atributoBanco.equals("mostrar_balao_aviso"))
                                   LogUsuario.s_mostrarBalaoAviso = l_decisao;
                                         else if(l_atributoBanco.equals("emitir_aviso_sonoro")){
                                               LogUsuario.s_emitirAvisoSonovo = l_decisao;
                                          
                                               //CASO DECISAO SIM...
                                               if(l_decisao.equals("S"))
                                                    new Thread(new Audio("som-habilitado.wav")).start();
                                         
                                         }
                                                     else if(l_atributoBanco.equals("mostrar_nome")){
                                                           LogUsuario.s_mostrarNome = l_decisao;

                                                           if(l_decisao.equals("S"))
                                                                Configuracoes.s_telaConfiguracoes.lb_nomeCompleto.setText(LogUsuario.s_nome+" "+LogUsuario.s_sobrenome);
                                                                Configuracoes.s_telaConfiguracoes.lb_iconeApelido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/neutro.png")));
                                                                Configuracoes.s_telaConfiguracoes.lb_quemVisualizaInfoBasica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/neutro.png")));
                                                                Moniersn.lb_mostrarNaoMostrarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/neutro.png")));
                                                        }
                                                        else{
                                                                Configuracoes.s_telaConfiguracoes.lb_nomeCompleto.setText(LogUsuario.s_moniersn);
                                                                Configuracoes.s_telaConfiguracoes.lb_iconeApelido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/disfarce.png")));
                                                                Configuracoes.s_telaConfiguracoes.lb_quemVisualizaInfoBasica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/disfarce.png")));
                                                                Moniersn.lb_mostrarNaoMostrarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/smyles/disfarce.png")));

                                                        }


             //MOSTRAR INFORMAÇÕES DA GUIA INICIAL DO USUÁRIO
             MostrarDados.s_MostrarDados.carregaInfoBasicas(LogUsuario.s_cod, "inicio-usuario");

             //MOSTRA MSG ATUALIZAÇÃO
             MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(l_msgConfirmacao, "atu");

        }else{
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
            return;
        }
    }

    public void atualizaValorRadioButtonNoBanco(JRadioButton l_radio){

        String l_valorBanco = null;
        String l_atributoBanco = null;


        //INFORMAÇOES DE PRIVACIDADE
        //INFORMAÇÕES BÁSICAS
            //TODOS VEEM INFO BÁSICAS
            if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_todosVeemInfoBasica)){
                 l_valorBanco = "todos";
                 l_atributoBanco = "visualizar_info_basicas";
                 SolicitarDados.s_SolicitarDados.habilitaDesabilitaInfoBasicas(true);
            }
            //SO AMIGOS VEEM INFO BÁSICAS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemInfoBasica)){
                 l_valorBanco = "amigos";
                 l_atributoBanco = "visualizar_info_basicas";
                 SolicitarDados.s_SolicitarDados.habilitaDesabilitaInfoBasicas(true);
            }
            //NINGUEM VE INFO BÁSICAS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_ninguemVeInfoBasica)){
                 l_valorBanco = "ninguem";
                 l_atributoBanco = "visualizar_info_basicas";
                 SolicitarDados.s_SolicitarDados.habilitaDesabilitaInfoBasicas(false);
            }
        //OCORRÊNCIAS
            //TODOS VEEM MINHAS OCORRENCIAS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_todosVeemOcorrencia)){
                 l_valorBanco = "todos";
                 l_atributoBanco = "visualizar_ocorrencia";
            }
            //SO AMIGOS VEEM MINHAS OCORRENCIAS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemOcorrencia)){
                 l_valorBanco = "amigos";
                 l_atributoBanco = "visualizar_ocorrencia";
            }
            //NINGUEM VE MINHAS OCORRENCIAS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_ninguemVeOcorrencia)){
                 l_valorBanco = "ninguem";
                 l_atributoBanco = "visualizar_ocorrencia";
            }
        //INFO PESSOAL
            //TODOS VEEM INFO PESSOAL
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_todosVeemInfoPessoal)){
                 l_valorBanco = "todos";
                 l_atributoBanco = "visualizar_info_pessoal";
            }
            //SÓ AMIGOS VEEM INFO PESSOAL
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemInfoPessoal)){
                 l_valorBanco = "amigos";
                 l_atributoBanco = "visualizar_info_pessoal";
            }
            //NINGUEM VE INFO PESSOAL
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_ninguemVeInfoPessoal)){
                 l_valorBanco = "ninguem";
                 l_atributoBanco = "visualizar_info_pessoal";
            }
        //INFO CONTATO
            //TODOS VEEM INFO CONTATO
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_todosVeemInfoContato)){
                 l_valorBanco = "todos";
                 l_atributoBanco = "visualizar_info_contato";
            }
            //SÓ AMIGOS VEEM INFO CONTATO
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemInfoContato)){
                 l_valorBanco = "amigos";
                 l_atributoBanco = "visualizar_info_contato";
            }
            //NINGUEM VE INFO CONTATO
                else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_ninguemVeInfoContato)){
                 l_valorBanco = "ninguem";
                 l_atributoBanco = "visualizar_info_contato";
            }
        //INFO EDUCACIONAL
            //TODOS VEEM MINHAS INFORMAÇÕES EDUCACIONAIS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_todosVeemInfoEduc)){
                 l_valorBanco = "todos";
                 l_atributoBanco = "visualizar_info_educ";
            }//SO AMIGOS VEEM MINHAS INFORMAÇÕES EDUCACIONAIS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemInfoEduc)){
                 l_valorBanco = "amigos";
                 l_atributoBanco = "visualizar_info_educ";
            }//NINGUEM VÊ MINHAS INFORMAÇÕES EDUCACIONAIS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_ninguemVeInfoEduc)){
                 l_valorBanco = "ninguem";
                 l_atributoBanco = "visualizar_info_educ";
            }
        //INFO PROFISSIONAL
            //TODOS VEEM MINHAS IFORMAÇÕES PROFISSIONAIS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_todosVeemInfoProf)){
                 l_valorBanco = "todos";
                 l_atributoBanco = "visualizar_info_prof";
            }
            //SÓA AMIGOS VEEM MINHAS IFORMAÇÕES PROFISSIONAIS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemInfoProf)){
                 l_valorBanco = "amigos";
                 l_atributoBanco = "visualizar_info_prof";
            }
            //NINGUEM VÊ MINHAS IFORMAÇÕES PROFISSIONAIS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_ninguemVeInfoProf)){
                 l_valorBanco = "ninguem";
                 l_atributoBanco = "visualizar_info_prof";
            }
        //LISTA DE AMIGOS
            //TODOS VEEM MINHA LISTA DE AMIGOS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_todosVeemListaAmigos)){
                 l_valorBanco = "todos";
                 l_atributoBanco = "visualizar_lista_amigos";
            }
            //SÓ AMIGOS VEEM MINHA LISTA DE AMIGOS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemListaAmigos)){
                 l_valorBanco = "amigos";
                 l_atributoBanco = "visualizar_lista_amigos";
            }
            //NINGUEM VÊ MINHA LISTA DE AMIGOS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_ninguemVeListaAmigos)){
                 l_valorBanco = "ninguem";
                 l_atributoBanco = "visualizar_lista_amigos";
            }
        //REC. PÚBLICOS
            //TODOS VEEM MEUS RECADOS PÚBLICOS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_todosVeemRecPub)){
                 l_valorBanco = "todos";
                 l_atributoBanco = "visualizar_lista_rec_pub";
            }//SÓ AMIGOS VEEM MEUS RECADOS PÚBLICOS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_soAmigosVeemRecPub)){
                 l_valorBanco = "amigos";
                 l_atributoBanco = "visualizar_lista_rec_pub";
            }//NINGUEM VÊ MEUS RECADOS PÚBLICOS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_ninguemVeRecPub)){
                 l_valorBanco = "ninguem";
                 l_atributoBanco = "visualizar_lista_rec_pub";
            }
        //BATE PAPO
            //TODOS BATEM PAPO COMIGO
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_todosBatemPapoComigo)){
                 l_valorBanco = "todos";
                 l_atributoBanco = "quem_bate_papo_comigo";
            }//SO AMIGOS BATEM PAPO COMIGO
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_soAmigosBatemPapoComigo)){
                    l_valorBanco = "amigos";
                    l_atributoBanco = "quem_bate_papo_comigo";
            }
            //NINGUEM BATE PAPO COMIGO
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_ninguemBatePapoComigo)){
                 l_valorBanco = "ninguem";
                 l_atributoBanco = "quem_bate_papo_comigo";
            }

        //ARQUIVOS
            //TODOS ME ENVIAM ARQUIVOS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_todosEnviamArquivos)){
                 l_valorBanco = "todos";
                 l_atributoBanco = "quem_envia_arquivo";
            }//SÓ AMIGOS ME ENVIAM ARQUIVOS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_soAmigosEnviamArquivos)){
                 l_valorBanco = "amigos";
                 l_atributoBanco = "quem_envia_arquivo";
            }//NINGUEM ME ENVIA ARQUIVOS
            else if(l_radio.equals(Configuracoes.s_telaConfiguracoes.rb_ninguemEnviaArquivos)){
                 l_valorBanco = "ninguem";
                 l_atributoBanco = "quem_envia_arquivo";
            }

        //CASO SALVO
         if(Dados.s_conexaoBanco.executeUPDATE("configuracoes_usuario", l_atributoBanco, l_valorBanco, LogUsuario.s_cod)){

             if(l_atributoBanco.equals("quem_bate_papo_comigo")){
                LogUsuario.s_quemBatePapoComigo = l_valorBanco;

                    //CONTAGEM DE ÍTENS
                    SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                    //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                    if(LogUsuario.s_mostrandoPainelAmigo){
                        MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
                        MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod);
                    }
                    //ENVIA A VISO PARA TODOS USUÁRIOS CONECTADOS
                    ClienteUDP.enviaAvisoParaTodosConectados(25, "geral");
             }

             if(l_atributoBanco.equals("visualizar_info_basicas")){
                LogUsuario.s_quemVeInfoBasicas = l_valorBanco;
                //MOSTRAR INFORMAÇÕES DA GUIA INICIAL DO USUÁRIO                
                MostrarDados.s_MostrarDados.carregaInfoBasicas(LogUsuario.s_cod, "inicio-usuario");
             }
             if(l_atributoBanco.equals("visualizar_lista_amigos")){
                LogUsuario.s_quemVeListaAmigos = l_valorBanco;
                //envia aviso para geral fazer contagem
                ClienteUDP.enviaAvisoParaTodosConectados(7, "geral");
             }
             if(l_atributoBanco.equals("visualizar_info_contato"))
                LogUsuario.s_quemVeInfoContato = l_valorBanco;
             if(l_atributoBanco.equals("visualizar_info_pessoal"))
                LogUsuario.s_quemVeInfoPessoal = l_valorBanco;
             if(l_atributoBanco.equals("visualizar_info_educ"))
                LogUsuario.s_quemVeInfoEducacional = l_valorBanco;
             if(l_atributoBanco.equals("visualizar_info_prof"))
                LogUsuario.s_quemVeInfoProfissional = l_valorBanco;
             if(l_atributoBanco.equals("visualizar_ocorrencia")){
                 LogUsuario.s_quemVeOcorrencias = l_valorBanco;
                 //ENVIA A VISO PARA TODOS USUÁRIOS CONECTADOS
                 ClienteUDP.enviaAvisoParaTodosConectados(16, "geral");
             }
             if(l_atributoBanco.equals("visualizar_lista_rec_pub")){
                LogUsuario.s_quemVeRecPub = l_valorBanco;

                    //CONTAGEM DE ÍTENS
                    SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);
                        
                        //CASO SELECIONADO PREENCHER A LISTA DE RECADOS PÚBLICOS ENVIADOS
                        if(LogUsuario.s_listaRecPubSelecionada.equals("env"))
                            MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPubUsuario, "env"); 
                                        
                        //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                        if(LogUsuario.s_mostrandoPainelAmigo){
                            MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
                            MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod);
                        }
                    //ENVIA A VISO PARA TODOS USUÁRIOS CONECTADOS
                    ClienteUDP.enviaAvisoParaTodosConectados(25, "geral");

             }
             if(l_atributoBanco.equals("quem_envia_arquivo")){
                    
                    LogUsuario.s_quemMeEnviaArquivo = l_valorBanco;

                    //CONTAGEM DE ÍTENS
                    SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                    //CASO LISTA DE ARQUIVOS DISPONÍVEIS SELECIONADA
                    if(LogUsuario.s_listaArquivoSelecionada.equals("disponiveis"))
                       MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, Moniersn.jt_listaArquivosUsuario, "disponiveis");


                    //CASO ESTEJA VISUALIZANDO O AMIGO, DEFINE AS INFORMAÇÕES
                    if(LogUsuario.s_mostrandoPainelAmigo){
                        MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
                        MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod);
                    }

                    //ENVIA A VISO PARA TODOS USUÁRIOS CONECTADOS
                    ClienteUDP.enviaAvisoParaTodosConectados(25, "geral");

             }

             MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");

         }


    }

    public void atualizaValorDataDateChooserNoBanco(JDateChooser l_dateChooser){

            //pega data por extenso para mostrar dia de aniversário
            String l_dataExtensoComDiaSemAno = null;

            //pega data por extenso para mostrar dia de aniversário
            String l_dataExtensoSemDiaComAno = null;

            String l_dataExtensoDiaMesAno = null;

            //pega data para gravar no banco
            String l_dataAAAAMMDD = null;

            //valor no banco será a data do dateChooser
            String l_valorBanco = null;

            //vai depender do dateChooser
            String l_atributoBanco = null;

            String l_perfilAtu = null;

            String l_descricaoAtu = null;

            Date l_data = null;

                try{

                        //pega data
                        l_data = l_dateChooser.getDate();

                        //pega data por extenso
                        l_dataExtensoComDiaSemAno = Data.pegaDataPorExtenso(l_data, Data.DIA_MES);

                        //pega data por extenso para mostrar
                        l_dataExtensoSemDiaComAno = Data.pegaDataPorExtenso(l_data, Data.MES_ANO);

                        l_dataExtensoDiaMesAno  = Data.pegaDataPorExtenso(l_data, Data.DIA_MES_ANO);

                        //pega data para gravar no banco
                        l_dataAAAAMMDD = Data.ajustaDataParaAAAAMMDD(l_data);

                        //valor no banco será a data do dateChooser
                        l_valorBanco = l_dataAAAAMMDD;


                }catch (Exception e){

                        System.err.println(l_dateChooser.getDate());
                        l_dateChooser.setDate(null);
                        l_valorBanco = null;
                }
    

                try{


                //INFORMAÇÕES BÁSICAS
                    if(l_dateChooser.equals(Configuracoes.s_telaConfiguracoes.dc_dataNascimento)){

                        l_atributoBanco = "data_nascimento";
                        Configuracoes.s_telaConfiguracoes.lb_aniversario.setText(l_dataExtensoComDiaSemAno);

                    }

                //INFO EDUCACIONAL (data de entrada na instituição de ensino)
                    if(l_dateChooser.equals(Configuracoes.s_telaConfiguracoes.dc_dataEntradaCurso)){

                        l_descricaoAtu = Avisos.MUDOU_DATA_ENT_CURSO+" "+l_dataExtensoSemDiaComAno;
                        l_perfilAtu = "educacional";
                        l_atributoBanco = "data_entrada_curso";
                        Configuracoes.s_telaConfiguracoes.lb_dataEntradaCursoExtenso.setText(l_dataExtensoSemDiaComAno);

                    }
                //INFO. EDUCACIONAL (data de término da instituição de ensino)
                    else if(l_dateChooser.equals(Configuracoes.s_telaConfiguracoes.dc_dataTerminoCurso)){
                        
                        l_descricaoAtu = Avisos.MUDOU_DATA_FIM_CURSO+" "+l_dataExtensoSemDiaComAno;
                        l_perfilAtu = "educacional";
                        l_atributoBanco = "data_termino_curso";
                        Configuracoes.s_telaConfiguracoes.lb_dataTerminoCursoExtenso.setText(l_dataExtensoSemDiaComAno);
                    }

                //INFO. PROFISSIONAL (data de admissão na profissão)
                    else if(l_dateChooser.equals(Configuracoes.s_telaConfiguracoes.dc_dataAdmissaoEmp)){

                        l_descricaoAtu = Avisos.MUDOU_DATA_ADMISS_EMP+" "+l_dataExtensoDiaMesAno;
                        l_perfilAtu = "profissional";
                        l_atributoBanco = "data_admissao";
                        Configuracoes.s_telaConfiguracoes.lb_dataEntradaEmpExtenso.setText(l_dataExtensoSemDiaComAno);
                    }

            }
            catch(Exception e){
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_INFO_DATA_COR, "aviso");
                    return;
            }

                //atualiza no banco
                if(Dados.s_conexaoBanco.executeUPDATE("usuario", l_atributoBanco, l_valorBanco, LogUsuario.s_cod)){


                    try{
                    
                            if(l_atributoBanco.equals("data_nascimento")){
                                Configuracoes.s_telaConfiguracoes.dc_dataNascimento.setVisible(false);
                                Configuracoes.s_telaConfiguracoes.bt_atuDataNascimento.setVisible(false);

                                MostrarDados.s_MostrarDados.carregaInfoBasicas(LogUsuario.s_cod, "inicio-usuario");
                                MostrarDados.s_MostrarDados.carregaInfoBasicas(LogUsuario.s_cod, "conf-usuario");

                            }else if(l_atributoBanco.equals("data_entrada_curso") ||
                                    l_atributoBanco.equals("data_termino_curso")){

                                MostrarDados.s_MostrarDados.carregaInfoEducacional(LogUsuario.s_cod, "conf-usuario");

                            }else if(l_atributoBanco.equals("data_admissao")){

                                MostrarDados.s_MostrarDados.carregaInfoProfissional(LogUsuario.s_cod, "conf-usuario");
                            }


                                if(!l_valorBanco.isEmpty() &&
                                   !l_atributoBanco.equals("data_nascimento"))
                                    GravarDados.s_GravarDados.gravarAtualizacao(l_perfilAtu, l_atributoBanco, l_descricaoAtu);


                       MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");

                    }catch(Exception e){
                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");
                            return;
                    }

                }else{
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                    return;
                }



    }

    public void atualizaComboboxHoraMinNoBD(JComboBox l_hhIni, JComboBox l_mmIni, JComboBox l_hhFim, JComboBox l_mmFim){

        String l_sqlUpdate = null;
        boolean l_ok = false;

        String l_horaMinutoIni = null;
        String l_horaMinutoFim = null;

        String l_atributoHoraIni = null;
        String l_atributoHoraFim = null;

        String l_tipoPerfil = null;
        String l_atributo = null;
        String l_msgAtualizacao = null;

        //Pega horario hh:mm inicio
        String l_horaIni   = l_hhIni.getSelectedItem().toString();
        String l_minutoIni = l_mmIni.getSelectedItem().toString();
        //Pega horario hh:mm fim
        String l_horaFim   = l_hhFim.getSelectedItem().toString();
        String l_minutoFim = l_mmFim.getSelectedItem().toString();

            //Caso colocar hora igual a * é pq nao quer mostrar horário de aula...
            if(l_horaIni.equals("*")){
                l_ok = false;

                l_mmIni.setSelectedItem("*");
                l_hhFim.setSelectedItem("*");
                l_mmFim.setSelectedItem("*");

            }
            else if(l_minutoIni.equals("*")){
                l_ok = false;
                l_mmIni.requestFocus();
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_INFO_HR_INI_COR, "aviso");
                return;
            }
            else if(l_horaFim.equals("*")){
                l_ok = false;
                l_hhFim.requestFocus();
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_INFO_HR_FIM_COR, "aviso");
                return;
            }
            else if(l_minutoFim.equals("*")){
                l_ok = false;
                l_mmFim.requestFocus();
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_INFO_HR_FIM_COR, "aviso");
                return;

//          CASO TUDO OK
            }else{
                l_ok = true;
            }
        
                    if(l_ok){
                        l_horaMinutoIni = l_horaIni+":"+l_minutoIni;
                        l_horaMinutoFim = l_horaFim+":"+l_minutoFim;
                    }else{
                        l_horaMinutoIni = "00:00";
                        l_horaMinutoFim = "00:00";
                    }

                if(l_hhIni.equals(Configuracoes.s_telaConfiguracoes.cb_horaIniAula)){
                    l_atributoHoraIni = "hora_ini_aula";
                    l_atributoHoraFim = "hora_fim_aula";
                    l_atributo = "horario_aula";

                    l_tipoPerfil = "educacional";
                    l_msgAtualizacao = Avisos.MUDOU_HORARIO_AULA;
                }else if(l_hhIni.equals(Configuracoes.s_telaConfiguracoes.cb_horaIniIntervalo)){
                    l_atributoHoraIni = "hora_ini_intervalo_aula";
                    l_atributoHoraFim = "hora_fim_intervalo_aula";
                    l_atributo = "horario_intervalo";

                    l_tipoPerfil = "educacional";
                    l_msgAtualizacao = Avisos.MUDOU_HORARIO_INTERVALO_AULA;
                }else if(l_hhIni.equals(Configuracoes.s_telaConfiguracoes.cb_horaIniServico)){
                    l_atributoHoraIni = "hora_ini_emp";
                    l_atributoHoraFim = "hora_fim_emp";
                    l_atributo = "horario_servico";

                    l_tipoPerfil = "profissional";
                    l_msgAtualizacao = Avisos.MUDOU_HORARIO_SERVICO;
                }else if(l_hhIni.equals(Configuracoes.s_telaConfiguracoes.cb_horaIniRefeicao)){
                    l_atributoHoraIni = "hora_ini_refeicao_emp";
                    l_atributoHoraFim = "hora_fim_refeicao_emp";
                    l_atributo = "horario_refeicao";

                    l_tipoPerfil = "profissional";
                    l_msgAtualizacao = Avisos.MUDOU_HORARIO_REFEICAO;
                }
                
            l_msgAtualizacao += " das: ["+l_horaMinutoIni+"] às ["+l_horaMinutoFim+"]";

            l_sqlUpdate = "UPDATE usuario "
                        + "SET "+l_atributoHoraIni+" = '"+l_horaMinutoIni+"'"
                           + ","+l_atributoHoraFim+" = '"+l_horaMinutoFim+"' "
                           + "WHERE cod_usuario = "+LogUsuario.s_cod;


                try{
                    int l_atu = Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlUpdate);

                    if(l_atu == 1){

                       if(l_ok)
                            GravarDados.s_GravarDados.gravarAtualizacao(l_tipoPerfil, l_atributo, l_msgAtualizacao);

                       MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");

                    }else{
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                        return;
                    }

                }
                catch(Exception e){

                    System.err.println("Erro ao tentar atualizar hh:mm no banco.    \nErro: "+e);

                }


   }


    public void atualizaLogAmigo(int l_codAmigo){

        String l_sqlSelect = "SELECT u.*, c.* "
                            +"FROM usuario u, configuracoes_usuario c "
                            +"WHERE u.cod_usuario = c.cod_usuario "
                            +"AND u.cod_usuario = "+l_codAmigo;

        try{
                Dados.s_conexaoBanco.executeSELECT(l_sqlSelect);
                Dados.s_conexaoBanco.c_resultset.first();

                //INFORMAÇÕES BÁSICAS DO USUÁRIO
                String l_moniersn = Dados.s_conexaoBanco.c_resultset.getString("u.moniersn");
                String l_nome = Dados.s_conexaoBanco.c_resultset.getString("u.nome");
                String l_sobrenome = Dados.s_conexaoBanco.c_resultset.getString("u.sobrenome");
                String l_iconeHumor = Dados.s_conexaoBanco.c_resultset.getString("u.icone_humor");
                String l_status = Dados.s_conexaoBanco.c_resultset.getString("u.status_contato");
                String l_host = Dados.s_conexaoBanco.c_resultset.getString("u.host");
                int    l_portaUDP = Dados.s_conexaoBanco.c_resultset.getInt("u.porta_udp");
                int    l_portaTCP = Dados.s_conexaoBanco.c_resultset.getInt("u.porta_tcp");


                //CONFIGURAÇÕES DO USUÁRIO
                String l_quemBatePapoComigo = Dados.s_conexaoBanco.c_resultset.getString("c.quem_bate_papo_comigo");
                String l_quemVeRecPub = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_lista_rec_pub");
                String l_quemVeOco = Dados.s_conexaoBanco.c_resultset.getString("c.visualizar_ocorrencia");

                

                //GUARDA NO LOG DO AMIGO
                LogAmigo.s_cod = l_codAmigo;
                LogAmigo.s_moniersn = l_moniersn;
                LogAmigo.s_nome = l_nome;
                LogAmigo.s_sobrenome = l_sobrenome;
                LogAmigo.s_status = l_status;
                LogAmigo.s_host = l_host;
                LogAmigo.s_portaUDP = l_portaUDP;
                LogAmigo.s_portaTCP = l_portaTCP;
                LogAmigo.s_iconeHumor = l_iconeHumor;
                LogAmigo.s_quemBatePapoComigo = l_quemBatePapoComigo;
                LogAmigo.s_quemVeRecPub = l_quemVeRecPub;
                LogAmigo.s_quemVeOco = l_quemVeOco;

        }

        catch(Exception e){
                System.err.println("Erro ao tentar carregar informaçoes de log do amigo.    \nErro: "+e);
        }
    }


    public void atualizaIconeHumor(String l_humor){
        Moniersn.lb_nomeIconeHumorUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/humor/"+l_humor+".png")));
    }

    public void atualizaStatus(int l_codUsuario, String l_status){

        if(Dados.s_conexaoBanco.executeUPDATE("usuario", "status_contato", l_status, l_codUsuario)){
            
            if(l_codUsuario == LogUsuario.s_cod)
                LogUsuario.s_status = l_status;

        }
        else
            System.err.println("Erro ao tentar atualizar status.    \nErro: no executeUPDATE");
    
    }
    
    
    public void atualizaHostPortaNoBanco(int l_codUsuario, String l_host, int l_porta, String l_tipoServer){

        String sql = null;
        String l_atributoPorta = null;
        
            if(l_tipoServer.equals("UDP"))
                l_atributoPorta = "porta_udp";
            else
                l_atributoPorta = "porta_tcp";


                sql = "UPDATE usuario SET "+l_atributoPorta+" = "+l_porta+", host = '"+l_host+"' "
                    + "WHERE cod_usuario = "+l_codUsuario;

                
                try{
                    Dados.s_conexaoBanco.c_statement.executeUpdate(sql);

                }
                catch(Exception e){

                    System.err.println("Erro ao tentar atualizar porta do servidor "+l_tipoServer+" no banco.\n   Erro :"+e);
                }
        
            
            if(l_codUsuario == LogUsuario.s_cod){
            
                if(l_tipoServer.equals("UDP"))
                    LogUsuario.s_portaUDP = l_porta;
                else
                    LogUsuario.s_portaTCP = l_porta;
            
            }
    
    }

    public void atualizaDataAtuConteudo(String l_aaaaMMddHHmmSS, String l_tipoComentario, boolean l_anonimo, int l_codConteudo, JTable l_tabela, String l_emissor){

        /*
         TIPOS DE COMENTÁRIOS:
         * - RECADO
         * - OCORRENCIAS
         * - ATUALIZAÇÕES
         * - ARQUIVOS
         */

        int l_codEmissorConteudo;
        boolean l_comentarioAnonimo = l_anonimo;

        String l_tabelaBanco = null;
        String l_atributoCodConteudo = null;

        //OEGA COD. EMISSOR DO CONTEUDO
        l_codEmissorConteudo = SolicitarDados.pegaCodigoPeloApelido(l_emissor);


        if(l_tipoComentario.equals("recado")){
            l_tabelaBanco = "recado";
            l_atributoCodConteudo = "cod_recado";
        }
        if(l_tipoComentario.equals("ocorrencia")){
            l_tabelaBanco = "ocorrencia";
            l_atributoCodConteudo = "cod_ocorrencia";
            
        }
        
        if(l_tipoComentario.equals("atualizacao")){
            l_tabelaBanco = "atualizacoes_usuario";
            l_atributoCodConteudo = "cod_atu";
        }
        if(l_tipoComentario.equals("arquivo")){
            l_tabelaBanco = "arquivo";
            l_atributoCodConteudo = "cod_arquivo";
        }

        //SELECIONA A TABELA EM QUESTAO
        Dados.s_conexaoBanco.executeSELECT("SELECT * FROM "+l_tabelaBanco);

        //QUERY DE UPDATE
        String l_sqlAtualiza = "UPDATE "+l_tabelaBanco+" "
                             + "SET data_hora_atu = '"+l_aaaaMMddHHmmSS+"' "
                             + "WHERE "+l_atributoCodConteudo+" = "+l_codConteudo;

            try{                    

                int atualizado = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlAtualiza);

                    if(atualizado == 1){

                        //CASO  RECADO
                        if(l_tipoComentario.equals("recado")){
                            
                            //CASO  RECADO PUB ou PRIV DO USUÁRIO (MARCA COMO NAO LIDO)
                            if(l_tabela.equals(Moniersn.jt_listaRecadosPubUsuario)){
                                
                                marcarRecadoComoNaoLido(l_tabela, l_codConteudo);
                                MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogAmigo.s_cod, l_tabela, LogUsuario.s_listaRecPubSelecionada);
                                MostrarDados.s_MostrarDados.habilitaBotoesConteudo(l_tabela);

                                    //CASO MOSTRE REC PUB PRA ALGUEM...
                                    if(!LogUsuario.s_quemVeRecPub.equals("ninguem")){

                                            //CASO O USUÁRIO COMENTOU O PRÓPRIO RECADO...
                                            if(LogUsuario.s_cod == l_codEmissorConteudo){

                                                //verifica se o recado está gravado
                                                boolean l_recadoDestGravado = SolicitarDados.s_SolicitarDados.verificaSeRecadoGravado(l_codConteudo, "destinatario");

                                                if(l_recadoDestGravado)                                        
                                                    //ENVIA AVISO PARA (AMIGOS ou TODOS USUÁRIOS) da lista do usuario
                                                    ClienteUDP.enviaAvisoParaTodosConectados(20, LogUsuario.s_quemVeRecPub);
                                                else
                                                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.ERRO_RECADO_DEST_REMOVIDO, "aviso");
                                            }
                                            //CASO O USUÁRIO COMENTOU O RECADO DE OUTRA PESSOA (na lista do proprio usuario)
                                            else{

                                                //VERIFICA PRA QUEM O EMISSOR DO REC. MOSTRA RECADO.
                                                String l_quemVeRecPub = SolicitarDados.s_SolicitarDados.pegaConfPrivacidade(l_codEmissorConteudo, "visualizar_lista_rec_pub");

                                                if(!l_quemVeRecPub.equals("ninguem"))
                                                    //ENVIA AVISO PARA (AMIGOS ou TODOS USUÁRIOS) da lista do usuario, inclusive
                                                    ClienteUDP.enviaAvisoParaTodosConectados(l_codEmissorConteudo, 19, l_quemVeRecPub);
                                            }
                                    }

                            }

                            if(l_tabela.equals(Moniersn.jt_listaRecadosPriv)){
                                    marcarRecadoComoNaoLido(l_tabela, l_codConteudo);
                                    MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogAmigo.s_cod, l_tabela, LogUsuario.s_listaRecPrivSelecionada);
                                    MostrarDados.s_MostrarDados.habilitaBotoesConteudo(l_tabela);
                                    
                                    //PEGA COD DESTINATARIO
                                    int l_codDest = SolicitarDados.s_SolicitarDados.buscaCodDestinatarioRecado(l_codConteudo);

                                    
                                    //CASO O USUÁRIO COMENTOU O PRÓPRIO RECADO...
                                    if(LogUsuario.s_cod == l_codEmissorConteudo){
                                        
                                        //verifica se o recado está gravado
                                        boolean l_recadoDestGravado = SolicitarDados.s_SolicitarDados.verificaSeRecadoGravado(l_codConteudo, "destinatario");
                                        
                                        if(l_recadoDestGravado)                                        
                                            //ENVIA AVISO PARA (AMIGOS ou TODOS USUÁRIOS) da lista do usuario
                                            ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codDest, 22);
                                        else
                                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.ERRO_RECADO_DEST_REMOVIDO, "aviso");
                                    }
                                    //CASO O USUÁRIO COMENTOU O RECADO RECEBIDO 
                                    else{
                                        
                                        boolean l_recadoEmissorGravado = SolicitarDados.s_SolicitarDados.verificaSeRecadoGravado(l_codConteudo, "emissor");

                                        if(l_recadoEmissorGravado)                                        
                                            //ENVIA AVISO PARA (AMIGOS ou TODOS USUÁRIOS) da lista do usuario
                                            ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codEmissorConteudo, 21);
                                        else
                                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.ERRO_RECADO_EMIS_REMOVIDO, "aviso");
                                    }
                                    
                            }

                            //CASO  RECADO PUB DO AMIGO (PREENCHE LISTA)
                            if(l_tabela.equals(Moniersn.jt_listaRecadosPubAmigo))
                                    MostrarDados.s_MostrarDados.preencherListaRecadosPubAmigo(LogAmigo.s_cod);
                        
                        }

                        if(l_tipoComentario.equals("ocorrencia")){
                            
                            //CASO LISTA DE OCORRENCIAS DO USUARIO
                            if(l_tabela.equals(Moniersn.jt_listaOcorrenciaUsuario)){    
                                //PREENCHE A LISTA DE OCORRENCIAS
                                MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, l_tabela, LogUsuario.s_listaOcorrenciaSelecionada);
                                    
                                    //caso o comentador seja o emissor da ocorrencia... (ENVIA DATAGRAMA AVISO)   
                                    if(LogUsuario.s_cod == l_codEmissorConteudo && !LogUsuario.s_quemVeOcorrencias.equals("ninguem")){
                                        
                                        //caso o comentário seja anonimo...
                                        if(l_comentarioAnonimo)
                                            ClienteUDP.enviaAvisoParaTodosConectados(28, LogUsuario.s_quemVeOcorrencias);
                                        else
                                            ClienteUDP.enviaAvisoParaTodosConectados(23, LogUsuario.s_quemVeOcorrencias);
                                    
                                    }
                                    
                                    //caso o comentador nao seja o emissor da ocorrencia
                                    else if(LogUsuario.s_cod != l_codEmissorConteudo && !LogUsuario.s_quemVeOcorrencias.equals("ninguem")){
                                        ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codEmissorConteudo, 24);
                                    
                                    }
                                    
                                    //se comentou a sua propria oco. atualiza ultima oco na tela principal
                                    if(LogUsuario.s_cod == l_codEmissorConteudo) 
                                        MostrarDados.s_MostrarDados.setaUltimaOcorrencia(l_codEmissorConteudo, "usuario");
                            }

                            else if(l_tabela.equals(Moniersn.jt_listaOcorrenciaAmigo))
                                MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogAmigo.s_cod, l_tabela, "");

                        }

                        if(l_tipoComentario.equals("atualizacao")){
                            if(l_tabela.equals(Moniersn.jt_listaAtualizacoesUsuario))
                                MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, l_tabela, LogUsuario.s_listaAtualizacoesSelecionada);
                            else if(l_tabela.equals(Moniersn.jt_listaAtualizacoesAmigo))
                                MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogAmigo.s_cod, l_tabela, "");
                        }
                        if(l_tipoComentario.equals("arquivo")){

                        }
                    }
            }
            catch(Exception e){
                System.err.println("Erro ao tentar atualizar data de atualização de conteudo.   \nErro: "+e);
                return;
            }

    }

    public void atualizaDataAtuRecadoMarcado(int l_codRecado){

        Data.atualizaDataHora();

        String l_data = Data.s_dataAtualAAAAMMDDHHMMSSParaBD;

        //seleciona a tabela de contato de usuario
        Dados.s_conexaoBanco.executeSELECT("SELECT * FROM recado");

        //atualiza o atributo [amigo] informando que aceitou (no banco)
        String l_sqlAtualiza = "UPDATE recado "
                             + "SET data_hora_atu = '"+l_data+"' "
                             + "WHERE cod_recado = "+l_codRecado;

            try{

                auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlAtualiza);

            }
            catch(Exception e){
                System.err.println("Erro ao tentar atualizar data de marcação de recado.    \nErro: "+e);
                return;
            }
     }


//   atualiza no banco os tipos de recado que posso mostrar a quantidade
    public void bloqueiaDesbloqueiaAmigo(){

       String l_icone = null;
       String l_msgAtu = null;
       String l_bloqueio = null; 
       boolean l_confirma = false;
       int l_efetuado = 0;

       //caso seja bloqueado
       if(LogAmigo.s_tipoAmigo.equals("aceito-bloqueei") ||
          LogAmigo.s_tipoAmigo.equals("aceito-bloqueados")){

            l_confirma = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_DESBLOQUEIO_AMIGO, Avisos.DESBLOQUEIO_AMIGO, Icones.AMIGO_DESBLOQUEIO_16);

            if(l_confirma){

                l_msgAtu = Avisos.MSG_OK_AMIGO_DESBLOQUEADO;
                l_icone = "AmigoDesbloqueado";
                l_bloqueio = "N";
                l_confirma = true;
            }
            else
                l_confirma = false;

        }
            //caso não seja bloqueado
            else{

                l_confirma = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_BLOQUEIO_AMIGO, Avisos.BLOQUEIO_AMIGO, Icones.AMIGO_BLOQUEIO_16);

                if(l_confirma){
                    //altera no banco
                    l_bloqueio = "S";
                    l_msgAtu = Avisos.MSG_OK_AMIGO_BLOQUEADO;
                    l_icone = "AmigoBloqueado";
                }
                else
                    l_confirma = false;
            }


       if(l_confirma){

            String sql = "UPDATE contato_de_usuario "
                       + "SET bloqueado = '"+l_bloqueio+"' "
                       + "WHERE cod_usuario = "+LogUsuario.s_cod+" "
                       + "AND cod_amigo = "+LogAmigo.s_cod;

                try{
                    l_efetuado = Dados.s_conexaoBanco.c_statement.executeUpdate(sql);
                }
                catch(Exception e){
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                }

                if(l_efetuado == 1){
                    
                    //CONTAGEM DE ´TENS DO USUÁRIOS
                    SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                    //PREENCHER LISTA DE AMIGOS
                    MostrarDados.s_MostrarDados.preencherListaAmigosDoUsuario(LogUsuario.s_cod, LogUsuario.s_listaAmigosAtual);

                    //ESCONDE PAINEL e DEFINE INFORMAÇÕES DO AMIGO (mostra o painel novamente)
                    MostrarDados.s_MostrarDados.mostraPainelDoAmigo(false);
                    MostrarDados.s_MostrarDados.defineInformacoesDoAmigo(LogAmigo.s_cod);


                        //PREENCHER A LISTA DE ARQUIVOS DISPONIVEIS CASO VISIVEL
                        if(LogUsuario.s_listaArquivoSelecionada.equals("disponiveis"))
                            MostrarDados.s_MostrarDados.preencherListaDeArquivos(LogUsuario.s_cod, Moniersn.jt_listaArquivosUsuario, "disponiveis");

                        //PREENCHER LISTA DE OCORRENCIAS RECEBIDAS
                        if(LogUsuario.s_listaOcorrenciaSelecionada.equals("rec"))
                            MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(LogUsuario.s_cod, Moniersn.jt_listaOcorrenciaUsuario, "rec");

                        //PREENCHER LISTA DE RECADOS PÚBLICOS RECEBIDAS
                        if(LogUsuario.s_listaRecPubSelecionada.equals("rec") ||
                           LogUsuario.s_listaRecPubSelecionada.equals("naoLido"))
                            MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, Moniersn.jt_listaRecadosPubUsuario, LogUsuario.s_listaRecPubSelecionada);

                        //PREENCHER LISTA DE ATUALIZAÇÕES DE AMIGOS OU NAO AMIGOS
                        if(LogUsuario.s_listaAtualizacoesSelecionada.equals("recEstranhos") ||
                           LogUsuario.s_listaAtualizacoesSelecionada.equals("recAmigos"))
                            MostrarDados.s_MostrarDados.preencherListaAtualizacoes(LogUsuario.s_cod, Moniersn.jt_listaAtualizacoesUsuario, LogUsuario.s_listaAtualizacoesSelecionada);


                    //ENVIA AVISO PRA FAZER A CONTAGEM DE ÍTENS
                    ClienteUDP.enviaAvisoParaUnicoAmigoConectado(LogAmigo.s_cod, 26);

                    //MOSTRA MSG DE CONFIRMAÇÃO
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(l_msgAtu, "atu");
                }

        }
    }

 public void desativarFacecard(){

       boolean l_confirma = false;
       int l_efetuado = 0;

       l_confirma = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_DESATIVAR_FACECARD, Avisos.DESATIVAR_FACECARD, Icones.ATENCAO_16);

           if(l_confirma){

                String sql = "UPDATE usuario "
                           + "SET ativo = 'N' "
                           + "WHERE cod_usuario = "+LogUsuario.s_cod;

                    try{
                        l_efetuado = Dados.s_conexaoBanco.c_statement.executeUpdate(sql);
                    }
                    catch(Exception e){
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                    }

                        if(l_efetuado == 1){

                            //MOSTRA MSG DE CONFIRMAÇÃO
                            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FACECARD_DESATIVADO, "atu");

                            Configuracoes.s_telaConfiguracoes.dispose();
                            Configuracoes.s_telaConfiguracoes = null;
                            System.out.println("tela de configurações fechada e valor null");

                            //ENVIA A VISO PARA TODOS USUÁRIOS CONECTADOS
                            ClienteUDP.enviaAvisoParaTodosConectados(27, "geral");

                            //MOSTRA MSG DE CONFIRMAÇÃO
                            LogMoniersn.s_LogMoniersn.desconectar(0);

                        }
            }
    }


    //exclui um recado da lista (atualiza como situacao = "E" - excluido)
    public void removerConteudo(JTable l_tabela){


        int[] l_selecionadas = l_tabela.getSelectedRows();
        int[] l_codConteudo = new int[l_tabela.getSelectedRows().length];
        String [] l_nomeArquivos = new String[l_tabela.getSelectedRows().length];
        
        String l_textoConfirmacao = null;
        String l_iconeConteudo = null;
        String l_atributoDefineGravacao = null;
        String l_atributoCod = null;
        String l_tipoComentario = null;
        String l_listaSelecionada = null;
        String l_sqlSelect = null;
        String l_tabelaBanco = null;
        String l_caminhoRaizArquivo = null;
        String l_caminhoArquivo = null;
       
        int l_codUsuario = LogUsuario.s_cod;
        int l_qtdTopicosSelecionados = l_selecionadas.length;

        boolean l_remover;

            for(int i = 0; i < l_selecionadas.length; i++)
                l_codConteudo[i] = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_selecionadas[i], l_tabela);

            if(l_tabela.equals(Moniersn.jt_listaOcorrenciaUsuario)){
                l_textoConfirmacao = Avisos.TEXTO_PROSS_REMO_DESTAS+l_qtdTopicosSelecionados+Avisos.TEXTO_OCO_SELECIONADAS+"?";
                l_iconeConteudo = Icones.OCORRENCIA_REMOCAO_16;
                l_tabelaBanco = "ocorrencia";
                l_atributoDefineGravacao = "gravado = 'N'";
                l_atributoCod = "cod_ocorrencia";
                l_listaSelecionada = LogUsuario.s_listaOcorrenciaSelecionada;
                l_sqlSelect = "SELECT cod_ocorrencia FROM ocorrencia";
            }
            if(l_tabela.equals(Moniersn.jt_listaAtualizacoesUsuario)){
                l_textoConfirmacao = Avisos.TEXTO_PROSS_REMO_DESTAS+l_qtdTopicosSelecionados+Avisos.TEXTO_ATU_SELECIONADAS+"?";
                l_iconeConteudo = Icones.REMOCAO_ATUALIZACAO_16;
                l_tabelaBanco = "atualizacoes_usuario";
                l_atributoDefineGravacao = "gravado = 'N'";
                l_atributoCod = "cod_atu";
                l_listaSelecionada = LogUsuario.s_listaAtualizacoesSelecionada;
                l_sqlSelect = "SELECT cod_atu FROM atualizacoes_usuario";
            }
            if(l_tabela.equals(Moniersn.jt_listaRecadosPubUsuario)){
                l_textoConfirmacao = Avisos.TEXTO_PROSS_REMO_DESTES+l_qtdTopicosSelecionados+Avisos.TEXTO_REC_PUB_SELECIONADOS+"?";
                l_iconeConteudo = Icones.REC_PUB_REMOCAO_16;
                l_tabelaBanco = "recado";
                
                    //caso seja recado recebido (apaga somente o meu)
                    if(!LogUsuario.s_listaRecPubSelecionada.equals("env"))
                        l_atributoDefineGravacao = "recado_destinatario_gravado ='N'";
                    else//apaga o meu e o dele
                        l_atributoDefineGravacao = "recado_emissor_gravado ='N', recado_destinatario_gravado ='N'";
                    
                l_atributoCod = "cod_recado";
                l_listaSelecionada = LogUsuario.s_listaRecPubSelecionada;
                l_sqlSelect = "SELECT cod_recado FROM recado";
            }
            if(l_tabela.equals(Moniersn.jt_listaRecadosPriv)){

                l_textoConfirmacao = Avisos.TEXTO_PROSS_REMO_DESTES+l_qtdTopicosSelecionados+Avisos.TEXTO_REC_PRIV_SELECIONADOS+"?";

                    if(!LogUsuario.s_listaRecPrivSelecionada.equals("env"))
                        l_atributoDefineGravacao = "recado_destinatario_gravado = 'N'";
                    else
                        l_atributoDefineGravacao = "recado_emissor_gravado = 'N'";

                l_iconeConteudo = Icones.REC_PRIV_REMOCAO_16;
                l_tabelaBanco = "recado";
                l_atributoCod = "cod_recado";
                l_listaSelecionada = LogUsuario.s_listaRecPrivSelecionada;
                l_sqlSelect = "SELECT cod_recado FROM recado";
            }

            if(l_tabela.equals(Comentario.jt_listaComentarios)){
                    l_textoConfirmacao = Avisos.TEXTO_PROSS_REMO_DESTE_COM_SELE+"?";
                    l_iconeConteudo = Icones.COMENT_REMOCAO;
                    l_tabelaBanco = "comentario";
                    l_atributoDefineGravacao = "gravado = 'N'";
                    l_tipoComentario = Comentario.s_telaComentario.c_tipoComentario;
                    l_atributoCod = "tipo_comentario = '"+l_tipoComentario+"' AND cod_comentario";
                    l_sqlSelect = "SELECT cod_comentario FROM comentario";
            }


            if(l_tabela.equals(Moniersn.jt_listaArquivosUsuario)){

                l_textoConfirmacao = Avisos.TEXTO_PROSS_REMO_DESTES+l_qtdTopicosSelecionados+Avisos.TEXTO_ARQ_SELECIONADOS+"?";
                l_iconeConteudo = Icones.ARQUIVO_REMOCAO;
                l_listaSelecionada = LogUsuario.s_listaArquivoSelecionada;
                String l_subPastaArqOrigem = null;
               
                    if(l_listaSelecionada.equals(Diretorios.PASTA_BAIXADOS)){
                        l_tabelaBanco = "arquivo_usuario"; 
                        l_subPastaArqOrigem = Diretorios.PASTA_BAIXADOS;
                    }
                    else if(l_listaSelecionada.equals(Diretorios.PASTA_ENVIADOS)){
                        l_tabelaBanco = "arquivo";
                        l_subPastaArqOrigem = Diretorios.PASTA_ENVIADOS;
                    }
                    
                l_atributoDefineGravacao = "gravado = 'N'";
                l_atributoCod = "cod_arquivo";
                
                l_caminhoRaizArquivo = Diretorios.pegaDiretorio(Diretorios.PASTA_ARQUIVOS, l_subPastaArqOrigem);

                l_sqlSelect = "SELECT cod_arquivo FROM "+l_tabelaBanco;
            }

        l_remover = MostrarDados.s_MostrarDados.mostraAvisoConfirmacao(Avisos.TITULO_REMOCAO_CONTEUDO, Avisos.REMOCAO_CONTEUDO.concat("\n\n"+l_textoConfirmacao), l_iconeConteudo);

            if(l_remover){
                
                try{

                    Dados.s_conexaoBanco.c_statement.executeQuery(l_sqlSelect);

                    for(int i = 0; i < l_selecionadas.length; i++){

                        l_codConteudo[i] = SolicitarDados.s_SolicitarDados.pegaCodigoDoConteudoSelecionadoNaLista(l_selecionadas[i], l_tabela);

                             if(l_tabela.equals(Moniersn.jt_listaArquivosUsuario))
                                    l_nomeArquivos[i] = SolicitarDados.s_SolicitarDados.pegaNomeArquivoSelecionado(l_selecionadas[i]);

                        //query de atualização
                        String l_sqlUpdate = "UPDATE "+l_tabelaBanco
                                           + " SET "+l_atributoDefineGravacao
                                           + " WHERE "+l_atributoCod+" = "+l_codConteudo[i];

                        int l_atualizado = Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlUpdate);

                        //caso atualizado com sucesso retornará 1 e...
                        if(l_atualizado == 1){

                            if(l_tabela.equals(Moniersn.jt_listaArquivosUsuario)){
                                    l_caminhoArquivo = l_caminhoRaizArquivo+"\\"+l_nomeArquivos[i];

                                    SolicitarDados.s_SolicitarDados.manipulaArquivo(l_caminhoArquivo, null, Diretorios.PASTA_REMOVIDOS);
                            }

                            System.out.println("Conteúdo excluído com sucesso, cod.:"+l_codConteudo[i]);

                        }
                    }
                }//fim try

                //caso dê alguma exceção...
                catch(Exception e){
                        System.err.println("Erro ao tentar excluir o conteúdo.  \nErro: "+e);
                }

                if(l_tabela.equals(Moniersn.jt_listaOcorrenciaUsuario)){
                    MostrarDados.s_MostrarDados.preencherListaDeOcorrencias(l_codUsuario, l_tabela, l_listaSelecionada);
                    MostrarDados.s_MostrarDados.setaUltimaOcorrencia(l_codUsuario, "usuario");
                }

                if(l_tabela.equals(Moniersn.jt_listaAtualizacoesUsuario)){
                    MostrarDados.s_MostrarDados.preencherListaAtualizacoes(l_codUsuario, l_tabela, l_listaSelecionada);

                }
                if(l_tabela.equals(Moniersn.jt_listaRecadosPubUsuario)){
                    MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(l_codUsuario, l_tabela, l_listaSelecionada);

                }

                if(l_tabela.equals(Moniersn.jt_listaRecadosPriv)){
                    MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(l_codUsuario, l_tabela, l_listaSelecionada);
                }

                if(l_tabela.equals(Comentario.jt_listaComentarios)){
                    int l_cod = Comentario.s_telaComentario.c_codConteudo;
                    String l_tipo = Comentario.s_telaComentario.c_tipoComentario;
                    int l_qtd = SolicitarDados.s_SolicitarDados.qtdComentarioPorConteudo(l_tipo, l_cod);

                    MostrarDados.s_MostrarDados.preencherListaComentarios(l_cod, l_tipo);
                    Comentario.lb_qtdComentarios.setText("("+l_qtd+") "+Avisos.TEXTO_COMENTARIOS);
//                    Comentario.s_telaComentario.bt_removerComent.setEnabled(false);

                }

                if(l_tabela.equals(Moniersn.jt_listaArquivosUsuario)){
                    MostrarDados.s_MostrarDados.preencherListaDeArquivos(l_codUsuario, l_tabela, l_listaSelecionada);
                    MostrarDados.s_MostrarDados.habilitaBotoesConteudo(l_tabela);
                }

                //HABILITA . DESABILITA BOTOES
                MostrarDados.s_MostrarDados.habilitaBotoesConteudo(l_tabela);

                //CONTAGEM DE ITENS DO USUARIO
                SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(l_codUsuario);

                //mostra aviso de atualização
                MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_CONTEUDO_REMOVIDO,"atu");

            }
        else
            return;

    }//fim click grid

// antes do registro ser AtualizarDados.s_AtualizarDados.atualizado este método é chamado
   public boolean atualizaSenha(){

       boolean l_tudoOk = true;

       String l_msg = null;

                //se o campo de senha estiver vazio...
                if(Configuracoes.s_telaConfiguracoes.pf_senha.getText().isEmpty()){
                    l_msg = Avisos.MSG_INFORME_SENHA_ACESSO;
                    l_tudoOk = false;
                    Configuracoes.s_telaConfiguracoes.pf_senha.requestFocus();

                }
                //se o campo de confirmação de senha estiver habilitado
                else if(Configuracoes.s_telaConfiguracoes.pf_confSenha.getText().isEmpty()){
                    l_msg = Avisos.MSG_CONFIRME_SENHA_ACESSO;
                    l_tudoOk = false;
                    Configuracoes.s_telaConfiguracoes.pf_confSenha.requestFocus();

                }
                // o cambo de pergunta secreta não estiver setado...
                else if(Configuracoes.s_telaConfiguracoes.cb_perguntaSecreta.getSelectedItem().equals("")){
                    l_msg = Avisos.MSG_SELECIONE_PERG_SECRETA;
                    l_tudoOk = false;
                    Configuracoes.s_telaConfiguracoes.cb_perguntaSecreta.requestFocus();
                }
                //se o tf de resposta secreta não for preenchido...
                else if(Configuracoes.s_telaConfiguracoes.tf_respostaSecreta.getText().isEmpty()){
                    l_msg = Avisos.MSG_RESP_PERG_SECRET;
                    l_tudoOk = false;
                    Configuracoes.s_telaConfiguracoes.tf_respostaSecreta.requestFocus();
                }
                //Caso os campos acimas sejam validados vai testar as senhas
                else{
                        //testa se as senhas são iguais
                        if(Configuracoes.s_telaConfiguracoes.pf_senha.getText().equals(Configuracoes.s_telaConfiguracoes.pf_confSenha.getText()))
                            l_tudoOk = true;
                        //caso as senhas não estejam iguais...
                        else{
                            l_msg = Avisos.MSG_SENHAS_N_IGUAIS;
                            l_tudoOk = false;
                            Configuracoes.s_telaConfiguracoes.pf_senha.setText("");
                            Configuracoes.s_telaConfiguracoes.pf_confSenha.setText("");
                            Configuracoes.s_telaConfiguracoes.pf_senha.requestFocus();

                        }//fim else
                }//FIM if(c_podeGravar == true)

          if(l_tudoOk == false)
            //mostra a msg de aviso de campo não validado
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(l_msg, "aviso");

          //CASO OS CAMPOS ESTEJAM TODOS OK...
          else{

                  String l_pergSecreta = Configuracoes.s_telaConfiguracoes.cb_perguntaSecreta.getSelectedItem().toString();
                  String l_respSecreta = Configuracoes.s_telaConfiguracoes.tf_respostaSecreta.getText().toString().toLowerCase();
                  String l_senha = Configuracoes.s_telaConfiguracoes.pf_senha.getText().toLowerCase();

                  //Atualiza variáveis estaticas da classe log
                  LogUsuario.s_pergutaSecreta = l_pergSecreta;
                  LogUsuario.s_respostaSecreta = l_respSecreta;
                  LogUsuario.s_senha= l_senha;

                  //muda icone para padrão
                  Configuracoes.s_telaConfiguracoes.bt_trocarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/16/seguranca.png"))); 

                  //texto do botão padrão
                  Configuracoes.s_telaConfiguracoes.bt_trocarSenha.setText("Alterar");

                  //AtualizarDados.s_AtualizarDados.atualiza senha
                  Dados.s_conexaoBanco.executeUPDATE("usuario", "senha", l_senha, LogUsuario.s_cod);

                  //AtualizarDados.s_AtualizarDados.atualiza pergunta secreta
                  Dados.s_conexaoBanco.executeUPDATE("usuario", "pergunta_secreta", l_pergSecreta, LogUsuario.s_cod);

                  //AtualizarDados.s_AtualizarDados.atualiza resposta secreta
                  Dados.s_conexaoBanco.executeUPDATE("usuario", "resposta_secreta", l_respSecreta, LogUsuario.s_cod);

                  //metodo decide de acordo com o parametro desabilitar os campos
                  SolicitarDados.s_SolicitarDados.habilitaCamposSenha(false);

                 //mostra aviso de atualização
                 MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ATUALIZACAO, "atu");

          }

          return l_tudoOk;
   }//fim validar campos


       //decide se aceita ou não o amigo conforme parâmetro
    public void decideAceitacao(String l_decisao, int l_codAmigo){


            //CASO O USUÁRIO DESEJA REMOVER O AMIGO
            if(l_decisao.equals("remover")){

                   //remover o amigo em questao
                   GravarDados.s_GravarDados.removerAmigo(LogUsuario.s_cod, l_codAmigo);
            }

            //CASO O USUÁRIO DESEJA ACEITAR UMA SOLICITAÇÃO
            if(l_decisao.equals("aceitar")){

                   //remover o amigo em questao
                   GravarDados.s_GravarDados.aceitarSolicitacaoAmizade(l_codAmigo);
            }

            //CASO O USUÁRIO DESEJA IGNORAR UMA SOLICITAÇÃO
            if(l_decisao.equals("ignorar")){

                   //remover o amigo em questao
                   GravarDados.s_GravarDados.ignorarSolicitacaoAmizade(l_codAmigo);
            }

            //CASO O USUÁRIO DESEJA CANCELAR UMA SOLICITAÇÃO
            if(l_decisao.equals("cancelar")){

                   //remover o amigo em questao
                   GravarDados.s_GravarDados.cancelarSolicitacaoAmigo(l_codAmigo);
            }

            //CASO O USUÁRIO DESEJA CONVIDAR UMA PESSOA
            if(l_decisao.equals("convidar")){

                   //remover o amigo em questao
                   GravarDados.s_GravarDados.convidarPessoa(l_codAmigo);
            }

    }//fim decidir aceitação de contato

    public void marcarRecadoComoLidoNaoLido(JTable l_tabela){

        //pega a linha que foi clicada na TABELA
        int linha;
        //pega o modelo da tabela
        TableModel modelo;
        int l_codDestinatario;
        String l_valorMarcacaoAtual = null;
        String l_marcacaoNova = null;
        int l_codRecado;
        String l_msgConfirmacao = null;
        String l_listaRecSelecionada = null;
        String l_apelido = null;
        String l_statusAmigo;

        //pega a linha que foi clicada na TABELA
        linha = l_tabela.getSelectedRow();

        //pega o modelo da tabela
        modelo = (TableModel) l_tabela.getModel();

        l_valorMarcacaoAtual = (String)modelo.getValueAt(linha, 0);
        l_codRecado = Integer.parseInt((String)modelo.getValueAt(linha, 4));
        l_apelido = SolicitarDados.s_SolicitarDados.pegaApelidoOuHumor(modelo.getValueAt(linha, 1).toString(), "apelido");

        l_codDestinatario = SolicitarDados.pegaCodigoPeloApelido(l_apelido);
        l_statusAmigo     = SolicitarDados.s_SolicitarDados.buscaStatus(l_codDestinatario);

        //testa o valor atual
        if(l_valorMarcacaoAtual.equals("N")){
            l_marcacaoNova = "S";
            l_msgConfirmacao = Avisos.MSG_MARCADO_COMO_LIDO;
        }
        else{
            l_marcacaoNova = "N";
            l_msgConfirmacao = Avisos.MSG_MARCADO_COMO_N_LIDO;
        }

        //testa a lista de recados para ver qual tipo esta selecionado (rec, env...)
        if(l_tabela.equals(Moniersn.jt_listaRecadosPubUsuario))
            l_listaRecSelecionada = LogUsuario.s_listaRecPubSelecionada;
        else
            l_listaRecSelecionada = LogUsuario.s_listaRecPrivSelecionada;


        //seleciona a tabela de contato de usuario
        Dados.s_conexaoBanco.executeSELECT("SELECT * FROM contato_de_usuario");

        //atualiza o atributo [amigo] informando que aceitou (no banco)                                                   //quem me add                               //eu
        String l_sqlMarcacao = "UPDATE recado "
                             + "SET visto = '"+l_marcacaoNova+"' "
                             + "WHERE cod_recado = "+l_codRecado;

            try{                                                                   //QUEM ME ADD                           //EU

                int marcado = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlMarcacao);

                    if(marcado == 1){

                        //ATUALIZA DATA DE ATUALIZAÇÃO DO RECADO
                        atualizaDataAtuRecadoMarcado(l_codRecado);

                        //CONTAGEM DE ITENS DO USUÁRIO
                        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                        //PREENCHER LISTA DE RECADOS
                        MostrarDados.s_MostrarDados.preencherListaDeRecadosUsuario(LogUsuario.s_cod, l_tabela, l_listaRecSelecionada);

                        //CASO NAO SOBROU LINHA
                        if(l_tabela.getRowCount() <= 0)
                            //decisao de habilitacao de botoes(coment, exc, marc))
                            MostrarDados.s_MostrarDados.habilitaBotoesConteudo(l_tabela);
                        
                            //CASO AINDA TENHA LINHA...
                            else{
//                                //caso tenha selecionado a ultima linha...
//                                if(l_tabela.getRowCount() == linha)
//                                    //seleciona a linha anterior
//                                    l_tabela.setRowSelectionInterval(linha -1, linha -1);
//                                else
                                    //seleciona normal
                                    l_tabela.setRowSelectionInterval(0, 0);

                                //decisao de habilitacao de botoes(coment, exc, marc))
                                MostrarDados.s_MostrarDados.habilitaBotoesConteudo(l_tabela);

                                //muda o ícone do botao
                                SolicitarDados.s_SolicitarDados.defineIconeRecadoLidoNaoLido(l_tabela);
                            }
                        
                        //envio de datagrama de aviso
                        if(l_tabela.equals(Moniersn.jt_listaRecadosPubUsuario) && l_statusAmigo.equals("conectado")){
                            if(!l_listaRecSelecionada.equals("env"))
                                ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codDestinatario, 10);
                            if(l_listaRecSelecionada.equals("env"))
                                ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codDestinatario, 17);
                        }

                        if(l_tabela.equals(Moniersn.jt_listaRecadosPriv) && l_statusAmigo.equals("conectado")){
                            if(!l_listaRecSelecionada.equals("env"))
                                ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codDestinatario, 11);

                            if(l_listaRecSelecionada.equals("env"))
                                ClienteUDP.enviaAvisoParaUnicoAmigoConectado(l_codDestinatario, 18);
                        }

                        //mostra aviso de atualização
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(l_msgConfirmacao, "atu");

                    }else{

                        System.err.println("Não foi possível marcar conteúdo.");
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                        return;
                    }
            }
            catch(Exception e){
                System.err.println("Não foi possível marcar conteúdo.   \nErro: "+e);
            }
    }

    public void marcarRecadoComoNaoLido(JTable l_tabela, int l_codRecado){

        //seleciona a tabela de contato de usuario
        Dados.s_conexaoBanco.executeSELECT("SELECT * FROM contato_de_usuario");

        //atualiza o atributo [amigo] informando que aceitou (no banco)                                                   //quem me add                               //eu
        String l_sqlMarcacao = "UPDATE recado "
                             + "SET visto = 'N' "
                             + "WHERE cod_recado = "+l_codRecado;

            try{                                             

                int marcado = auxiliar.Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlMarcacao);

                    if(marcado == 1){

                        //CONTAGEM DE ITENS DO USUÁRIO
                        SolicitarDados.s_SolicitarDados.contagemDeItensDoUsuario(LogUsuario.s_cod);

                    }else{
                         MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                         return;
                    }
            }
            catch(Exception e){
                System.err.println("Não foi possível marcar conteúdo.   \nErro: "+e);
            }
    }

        //atualiza informações de socket
    public void atualizaHostPortaNoBanco(int l_codUsuario, String l_host, int l_portaUDP, int l_portaTCP, String l_status){

            //atualiza host e porta no banco
            try{
                Dados.s_conexaoBanco.executeSELECT("SELECT host, porta_udp, porta_tcp, status_contato "
                                                   + "FROM usuario "
                                                   + "WHERE cod_usuario = "+l_codUsuario);

                String l_sqlUpdate = "UPDATE usuario "
                                   + "SET host = '"+l_host+"', porta_udp = "+l_portaUDP+", porta_tcp = "+l_portaTCP+", status_contato = '"+l_status+"' "
                                   + "WHERE cod_usuario = "+l_codUsuario;

                int ok = Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlUpdate);

                    //caso 1 e porque a query foi executada com sucesso
                    if(ok == 1){
                        System.out.println("Atualizado HOST e PORTA no banco no codigo de usuário = "+l_codUsuario);
                    }
                    else{
                        System.err.println("Erro ao tentar aplicar padronização de HOST e PORTA no codigo de usuário  = "+l_codUsuario);
                    }

            }
            catch(Exception e){
                    System.err.println("O HOST e PORTA não poderam ser atualizados.    \nErro: "+e);
            }


    }//fim registro socket


    //atualiza infrmação do local dos arquivos
    public boolean atualizaLocalArquivos(String l_local){


        boolean l_atualizado = false;

        String l_localArquivoBD = SolicitarDados.s_SolicitarDados.trataBarraDiretorioParaBD(l_local);

            //atualiza host e porta no banco
            try{
                Dados.s_conexaoBanco.executeSELECT("SELECT local_arquivos "
                                                   + "FROM configuracoes_usuario "
                                                   + "WHERE cod_usuario = "+LogUsuario.s_cod);

                String l_sqlUpdate = "UPDATE configuracoes_usuario "
                                   + "SET local_arquivos = '"+l_localArquivoBD+"' "
                                   + "WHERE cod_usuario = "+LogUsuario.s_cod;

                int ok = Dados.s_conexaoBanco.c_statement.executeUpdate(l_sqlUpdate);

                    //caso 1 e porque a query foi executada com sucesso
                    if(ok == 1){
                        l_atualizado = true;
                        System.out.println("Local dos arquivos atualizado!");
                        LogUsuario.s_localDeArquivosDoUsuario = l_local;

                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_LOCAL_ARQ_DEFINIDO_EXITO, "atu");
                    }
                    else{
                        l_atualizado = false;
                        System.err.println("Erro ao tentar atualizar local dos arquivo no banco");
                        MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_DEF_LOCAL_ARQ, "erro");
                    }

            }
            catch(Exception e){
                    l_atualizado = false;
                    MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_FALHA_CONEXAO, "aviso");
                    System.err.println("Erro ao tentar atualizar local do sarquivos.    \nErro: "+e);
            }

            return l_atualizado;

    }//fim registro socket

    //atualiza no banco os tipos de recado que posso mostrar a quantidade
    public boolean atualizaArquivoRemovido(int codArquivo, String l_dir){

            boolean l_atualizado;
            String sql = null;

            if(l_dir.equals(Diretorios.PASTA_ENVIADOS))
                sql = "UPDATE arquivo SET gravado = 'N' WHERE cod_arquivo = "+codArquivo;
            else if (l_dir.equals(Diretorios.PASTA_BAIXADOS))
                sql = "UPDATE arquivo_usuario SET gravado = 'N' where cod_arquivo = "+codArquivo;

                try{
                    Dados.s_conexaoBanco.c_statement.executeUpdate(sql);
                    l_atualizado = true;
                }
                catch(Exception e){

                    l_atualizado = false;
                    System.err.println("Erro ao tentar atualizar arquivo como removido no BD.   \nErro: "+e);

                }

            return l_atualizado;
    }
    
    public void atualizaArquivosNoDiretorio(String l_urlDirUsuario, String l_pasta, int l_codUsuario){     

        File diretorio = new File(l_urlDirUsuario+"\\"+Diretorios.PASTA_ARQUIVOS+"\\"+l_pasta); 
        
            if(diretorio.exists()){
        
                File[] arquivos = diretorio.listFiles(); 

                if(arquivos != null){ 

                    int l_qtdArquivos = arquivos.length; 

                    for(int i = 0; i < l_qtdArquivos; ++i){ 

                        File arquivo = arquivos[i]; 
                        String l_nomeArquivo = arquivo.getName();

                        AtualizarDados.s_AtualizarDados.atualizaArquivoEncontrado(l_nomeArquivo, l_pasta);

                    } 
                }
            }else{
                System.out.println("A pasta "+l_pasta+" não foi criada ainda!");
            }

   }
    
    //atualiza no banco o arquivo encontrado na pasta
    public boolean atualizaArquivoEncontrado(String l_nomeArquivo, String l_pasta){

            boolean l_atualizado;
            String sql = null;

            
                if(l_pasta.equals(Diretorios.PASTA_ENVIADOS)){
                    sql = "UPDATE arquivo SET gravado = 'S' "
                        + "WHERE nome = '"+l_nomeArquivo+"' "
                        + "AND gravado = 'N' "
                        + "AND cod_emissor = "+LogUsuario.s_cod;
                }

                else if (l_pasta.equals(Diretorios.PASTA_BAIXADOS)){
                    sql = "UPDATE arquivo_usuario SET gravado = 'S' "
                        + "WHERE cod_arquivo IN (SELECT cod_arquivo "
                                              + "FROM arquivo "
                                              + "WHERE nome = '"+l_nomeArquivo+"') "
                        + "AND gravado = 'N' "
                        + "AND cod_usuario = "+LogUsuario.s_cod;
                }
                
                    try{
                        Dados.s_conexaoBanco.c_statement.executeUpdate(sql);
                        l_atualizado = true;
                    }
                    catch(Exception e){

                        l_atualizado = false;
                        System.err.println("Erro ao tentar atualizar arquivo como gravado no BD.   \nErro: "+e);

                    }

            return l_atualizado;
    }
    
    

}
