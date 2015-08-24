/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;

import formularios.Inicio;


/**
 *
 * @author Ayrton Monier
 */
public class LogoInicio implements Runnable{
    
    public LogoInicio(){

        Inicio.s_telaInicio = new Inicio();    
    }

    public void run(){
 
        //mostra tela de acrregando...
        Inicio.s_telaInicio.setVisible(true); 

    }
            
}
    
 