/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar.threads;

/**
 *
 * @author Ayrton Monier
 */
/*
 Este exemplo mostra como usar a classe URL e
 seu método openStream para efetuar o download
 do conteúdo de uma URL e então copiá-lo para
 um arquivo ou para o console.
*/

import formularios.BannerFC;
import java.io.*;
import java.net.*;

public class FCBannerWeb implements Runnable{

    InputStream in = null;
    OutputStream out = null;
    File c_arquivoTemp = null;  
    boolean c_baixado = false;
    
    public FCBannerWeb() {}

    public void run(){

    try{
        String arquivo = "http://1.bp.blogspot.com/-BdnJFy_AYBA/T9j2tnQo7XI/AAAAAAAAAJ0/GxjT-lOr3_8/s1600/topo_fino.png";

        c_arquivoTemp = File.createTempFile("banner" , ".png");
        c_arquivoTemp.deleteOnExit();

        // Define os streams
        URL url = new URL(arquivo); // Cria a URL
        in = url.openStream(); // Abre o stream
        // Obtém um fluxo de saída (output stream)
        out = new FileOutputStream(c_arquivoTemp);

        // Copia bytes a partir da URL para fluxo de saída
        byte[] buffer = new byte[1024];
        int bytes_read;

        while((bytes_read = in.read(buffer)) != -1){
            out.write(buffer, 0, bytes_read);
        }
        
        c_baixado = true;    
        
    }

    // Houve exceção? Vamos exibir o erro
    catch(Exception e){
      c_baixado = false;    
      System.err.println(e);
    }
    
    finally{  // Temos sempre que fechar os stream.
      try{
          
        out.flush();
        in.close();
        out.close();
        
        c_baixado = true; 
        
      }
      catch(Exception e){
        c_baixado = false; 
        System.err.println("Falha ao tentar baixar o banner");
      }
      
      try{
          if(c_baixado){
            new BannerFC(c_arquivoTemp.getAbsolutePath()).setVisible(true);;
          }
      
      }catch(Exception e){
      
          System.err.println("Erro ao tentar carregar banner");
          
      }
      
      
      
      
      
      
      
//        if(Desktop.isDesktopSupported()){
//
//            try{
//
//                Desktop.getDesktop().open(c_arquivoTemp);
//                System.out.println("Arquivo aberto com sucesso via API Desktop!");
//
//            }catch(Exception e){
//
//                System.err.println("Erro ao tentar abrir o arquivo selecionado via API Desktop.\n"
//                                + "    Erro:"+e);
//            }
//
//        }else{
//            System.err.println("Erro ao tentar abrir o arquivo selecionado via API Desktop");
//        }
      
    }
  }
}
