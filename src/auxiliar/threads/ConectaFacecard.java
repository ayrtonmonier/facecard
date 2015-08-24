/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;


import auxiliar.LogMoniersn;
import formularios.Inicio;
import formularios.Aguardando;


/**
 *
 * @author Ayrton Monier
 */
public class ConectaFacecard implements Runnable{

    private String c_senha = null;
    private String c_apelido = null;
    
    public ConectaFacecard(String l_apelido, String l_senha){
    
        c_apelido = l_apelido;
        c_senha = l_senha;
    
    }

    public void run(){
        
            LogMoniersn.s_LogMoniersn.conectar(c_apelido, c_senha);

    }
            
}
    
 