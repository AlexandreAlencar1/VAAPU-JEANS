package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * conexao
 */
public class Conexao {


    //conexoes estaticas ou sejam nao mudam, escolhido assim porque é melhor para trabalhar
    private static final String url = "jdbc:mysql://localhost:3306/vaapu_bd";
    private static final String user = "root"; //caso nao de certo o user e o pass tem que ver qual coloquei na instalçao do mysql
    private static final String password = "root"; 

    private static Connection conexao;

    public static Connection getConexao() {
        
        try {

               if(conexao == null) {
                conexao = DriverManager.getConnection(url, user, password); //nunca feita cai aqui
                return conexao;
            }else{
                return conexao; //ja feita cai aqui
            }
            
        } catch (SQLException e) {

            e.printStackTrace(); //qualquer erro cai aqui e vai me da a arvore para achar o erro
            return null;

        }
     

        
        
        
    
    }
}