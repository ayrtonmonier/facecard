/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;


import formularios.Inicio;
import formularios.Aguardando;


/**
 *
 * @author Ayrton Monier
 */
public class LogoCarregando implements Runnable{

    public LogoCarregando(){}

    public void run(){
         
            Aguardando.s_telaAguardando.setVisible(true); 

    }
            
}
    
 