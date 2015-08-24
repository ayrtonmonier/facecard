/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;

import auxiliar.*;
import formularios.Aguardando;
import formularios.Configuracoes;
import formularios.Moniersn;





/**
 *
 * @author Ayrton Monier
 */
public class MostrarConfiguracoes implements Runnable{

    public MostrarConfiguracoes(){
        
    }

    public void run(){


        try{
            
            //esconde tela moniersn
            Moniersn.s_telaMsn.setVisible(false); 

            Configuracoes.s_telaConfiguracoes = new Configuracoes();

            //carrega as informações básicas
            MostrarDados.s_MostrarDados.carregaInfoBasicas(LogUsuario.s_cod, "conf-usuario");

            //carrega as informações pessoais
            MostrarDados.s_MostrarDados.carregaInfoPessoal(LogUsuario.s_cod, "conf-usuario");

            //carrega as informações de contato
            MostrarDados.s_MostrarDados.carregaInfoContato(LogUsuario.s_cod, "conf-usuario");

            //carrega as informações de contato
            MostrarDados.s_MostrarDados.carregaInfoEducacional(LogUsuario.s_cod, "conf-usuario");

            //carrega as informações de contato
            MostrarDados.s_MostrarDados.carregaInfoProfissional(LogUsuario.s_cod, "conf-usuario");

            //carrega as configuracoes de seleção
            System.out.println("Carregando configurações do usuário...");
            //carrega as informações de contato
            MostrarDados.s_MostrarDados.carregaConfPrivacidade(LogUsuario.s_cod, "conf-usuario");

            //carrega as configurações de cor
            Cores.s_Cores.atualizaCores(LogUsuario.s_cod, "conf-usuario");

            //seta título
            MostrarDados.s_MostrarDados.setaTituloFrame("INÍCIO", "config");

            //habilita evento component shown das guias de configuraçõees
            Configuracoes.s_telaConfiguracoes.c_desabilitaCompShownGuiasConf = false;
            
            //esconde tela carregando
            Aguardando.s_telaAguardando.setVisible(false); 

            //mostra a tela de configurações
            Configuracoes.s_telaConfiguracoes.setVisible(true);
            

            
           
            
        
        }catch(Exception e){
            
            System.err.println("Ocorreu um erro ao tentar atualizar informações.    \nErro: "+e);
            
            //esconde tela carregando
            Aguardando.s_telaAguardando.setVisible(false); 
            
            Moniersn.s_telaMsn.setVisible(true);
            
            MostrarDados.s_MostrarDados.mostraMensagemAtualizacao(Avisos.MSG_ERRO_AO_VISU_INFO_USU, "erro");
        
        }
    }
            
}
    
 