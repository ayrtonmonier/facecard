/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;


import auxiliar.MostrarDados;
import formularios.Inicio;
import formularios.Aguardando;


/**
 *
 * @author Ayrton Monier
 */
public class SobreFacecard implements Runnable{

    public SobreFacecard(){}

    public void run(){
         
        MostrarDados.s_MostrarDados.mostrarSobreFacecard();

    }
            
}