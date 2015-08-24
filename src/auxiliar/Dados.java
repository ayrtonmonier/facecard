/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;
import formularios.Erro;
import formularios.Inicio;
import java.sql.*;

/**
 * @author Administrador
 */
public class Dados{
    
//guardará o banco de dados utilizado
final private String f_banco = "mysql";
//guardará o nome do computador que se encontra o banco de dados
final private String f_host = CliFC.S_PCS;
//gurdará  porta que o sgbd mysql escuta
final private String f_porta = "3306";
//guardará o nome da base de dados
final private String f_nomeBaseDados = "servercards";
//gurdará o driver responsável pela conexao java+mysql
final private String f_driver= "com.mysql.jdbc.Driver";
//guardará a url: endereço completo da base de dados
final private String f_url= "jdbc:"+f_banco+"://"+f_host+":"+f_porta+"/"+f_nomeBaseDados;
//guardara o usuário do bancp de dados
final private String f_usuario= "root";
//guardará a senha do usuário do banco de dados
final private String f_senha = "developer";

//objeto do tipo connection
private Connection c_linha;

//objeto do tipo statment
public Statement c_statement;
//objeto resultset
public ResultSet c_resultset;

//variável stática de classe de CONEXAO
public static Dados s_conexaoBanco = new Dados();

    //metodo que irá efetuar a conexão com o banco de dados
    public boolean conecta(){

        boolean l_conectado;

            try{

                //método foName vai carregar o f_driver de conexão
                Class.forName(f_driver);

                //guerda a conexao que é formada através dos parÂmetros do endereço do banco, usuário e senha
                c_linha = DriverManager.getConnection(f_url, f_usuario, f_senha);

                System.out.println("Conectado à base de dados com sucesso!");

                l_conectado = true;

            }

            //caso ocorra algum exceção de não encontrar a claase do driver de conexão...
            catch(ClassNotFoundException Driver){

                System.err.println("Erro ao tentar localizar o Driver de conexão com o banco.   \nErro: "+Driver);
                l_conectado = false;

                Inicio.s_telaInicio.dispose();
                Erro l_telaErro = new Erro(new javax.swing.JFrame(), true, "driver");
                l_telaErro.setVisible(true);

            }
            //caso tenha algum erro de sql...
            catch(SQLException erroSQL){

                Inicio.s_telaInicio.dispose();
                System.err.println("Servidor não encontrado.    \nErro: "+erroSQL);
                l_conectado = false;

                Erro l_telaErro = new Erro(new javax.swing.JFrame(), true, "conexao");
                l_telaErro.setVisible(true);

            }


          //caso não ocorra nenhuma exceção o resultset continuará true
          return l_conectado;

    }//fim da classe conecta


    //metro de que desconecta o usuário da base de dados
    public boolean desconecta(){

        boolean l_desconectado;

            try{
                //metodo close fecha a conexao
                c_linha.close();
                System.out.println("Conexão com banco de dados fechada com sucesso.");
                l_desconectado = true;
            }

            catch(SQLException e){
                l_desconectado = false;
                System.err.println("Não foi possível fechar a conexão com o banco de dados. \nErro: "+e);
            }

        return l_desconectado;

    }

    
    //executa a query
    public boolean executeSELECT(String l_sql){

        boolean l_executado;

            try{
                //no mundo real o statment é o cano da torneira e o resultset é o balde

                c_statement = c_linha.createStatement(c_resultset.TYPE_SCROLL_INSENSITIVE, c_resultset.CONCUR_READ_ONLY);
                c_resultset = c_statement.executeQuery(l_sql);

                l_executado = true;

            }
            
            //caso ocorra alguma exceção...
            catch(SQLException sqlx){
         
                System.err.println("A conexão tinha sido perdida.    \nreconectando...");

                //reconecta...
                conecta();    
                
                    try{

                        c_statement = c_linha.createStatement(c_resultset.TYPE_SCROLL_INSENSITIVE, c_resultset.CONCUR_READ_ONLY);
                        c_resultset = c_statement.executeQuery(l_sql);

                        l_executado = true;

                    }catch(Exception ee){
                        l_executado = false;
                        System.err.println("Não foi possível executar o comando sql.    \nsql: "+sqlx);
                    }
            
            }

        return l_executado;


    }

    //atualiza no banco os tipos de recado que posso mostrar a quantidade
    public boolean executeUPDATE(String l_tabela, String l_atributo, String l_valor, int cod_usuario){

            boolean l_atualizado;
            String sql = null;

            if(l_valor == null)
                sql = "UPDATE "+l_tabela+" SET "+l_atributo+" = "+l_valor+" where cod_usuario = "+cod_usuario;
            else
                sql = "UPDATE "+l_tabela+" SET "+l_atributo+" = '"+l_valor+"' where cod_usuario = "+cod_usuario;

                try{
                    
                    c_statement.executeUpdate(sql);
                    l_atualizado = true;

                }
                
                catch(Exception e){
                    
                    System.err.println("A conexão tinha sido perdida.    \nreconectando...");
                    
                    //reconecta...
                    conecta();  
                        
                            try{
                                    if(l_valor == null)
                                        sql = "UPDATE "+l_tabela+" SET "+l_atributo+" = "+l_valor+" where cod_usuario = "+cod_usuario;
                                    else
                                        sql = "UPDATE "+l_tabela+" SET "+l_atributo+" = '"+l_valor+"' where cod_usuario = "+cod_usuario;

                                c_statement = c_linha.createStatement(c_resultset.TYPE_SCROLL_INSENSITIVE, c_resultset.CONCUR_READ_ONLY);
                                c_statement.executeUpdate(sql);
                                l_atualizado = true;


                            }catch(Exception ee){
                                l_atualizado = false;

                                l_atualizado = false;
                                System.err.println("Erro ao tentar atualizar os seguintes valores no banco:\n\n"
                                                                     +"* Tabela: "+l_tabela+"\n"
                                                                     +"* Atributo: "+l_atributo+"\n"
                                                                     +"* Valor: "+l_valor+"\n\n"
                                                                     + "Erro tecnico: "+e);                    
                            }
                }

            return l_atualizado;
    }


    public boolean executeINSERT(String l_tabela, String l_atributo, String l_valor){

            Boolean l_inserido;

            String sql = "INSERT INTO "+l_tabela+" ("+l_atributo+") VALUES ('"+l_valor+"')";

            try{

                c_statement.executeUpdate(sql);
                l_inserido = true;

            }
            catch(Exception e){

                System.err.println("A conexão tinha sido perdida.    \nreconectando...");

                //reconecta...
                conecta();
                
                    try{

                            sql = "INSERT INTO "+l_tabela+" ("+l_atributo+") VALUES ('"+l_valor+"')";
                            
                            c_statement = c_linha.createStatement(c_resultset.TYPE_SCROLL_INSENSITIVE, c_resultset.CONCUR_READ_ONLY);
                            c_statement.executeUpdate(sql);
                            l_inserido = true;

                    }catch(Exception ee){

                        l_inserido = false;

                        System.err.println("Erro ao tentar inserir os seguintes valores no banco:\n\n"
                                         +"* Tabela: "+l_tabela+"\n"
                                         +"* Atributo: "+l_atributo+"\n"
                                         +"* Valor: "+l_valor+"\n\n"
                                         + "Erro técnico: "+e);
                    }
                


            }

            return l_inserido;
    }

}
