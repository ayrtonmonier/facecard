/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;

import auxiliar.GravarDados;

/**
 *
 * @author Ayrton Monier
 */
public class CadastroUsuario implements Runnable{
    
    public CadastroUsuario(){}

    public void run(){
        
        GravarDados.s_GravarDados.cadastrarUsuario();

    }
            
}
    
 