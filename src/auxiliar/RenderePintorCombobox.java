/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Ayrton Monier
 */
public class RenderePintorCombobox extends JLabel implements ListCellRenderer{

        private ImageIcon c_icones[] =  {new javax.swing.ImageIcon(getClass().getResource(Icones.TODOS_GERAL_16)),
                                         new javax.swing.ImageIcon(getClass().getResource(Icones.TODOS_CONECTADOS_16)),
                                         new javax.swing.ImageIcon(getClass().getResource(Icones.TODOS_CONVIDADOS_16)),
                                         new javax.swing.ImageIcon(getClass().getResource(Icones.TODOS_CONVITES_16)),
                                         new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGOS_GERAL_16)),
                                         new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGOS_CONECTADOS_16)),
                                         new javax.swing.ImageIcon(getClass().getResource(Icones.AMIGOS_BLOQUEADOS_16))};


        private String c_usuarios[] = {"Todos(geral)",
                                       "Todos(conectados)",
                                       "Todos(convidados)",
                                       "Todos(convites)",
                                       "Amigos(geral)",
                                       "Amigos(conectados)",
                                       "Amigos(bloqueados)"};


    public RenderePintorCombobox(){
        System.err.println("entrou no adones");
         setOpaque(true);
         setHorizontalAlignment(CENTER);
         setVerticalAlignment(CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        System.err.println("entrou");
         // Pega o índice selecionado.  (The param índice não é
         // Sempre válido, então é só usar o valor.)
         int selectedIndex = ((Integer) value).intValue();

         System.err.println("Indice: "+selectedIndex);

             if(isSelected){
                 setBackground(list.getSelectionBackground());
                 setForeground(list.getSelectionForeground());
             }else{
                 setBackground(list.getBackground ());
                 setForeground(list.getForeground ());
            }


         // Definir o ícone e texto.  Se o ícone foi nula, digamos assim.
         ImageIcon icon = c_icones[selectedIndex];
         String usuario = c_usuarios[selectedIndex];
         setIcon (icon);

            if (icon != null) {
                 setText(usuario);
                 setFont(list.getFont());
            }else{
                 setText(usuario + "(não há imagem disponível)");
            }



         return this;
     }
    
}



