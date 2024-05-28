/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigest.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author domin
 */


public class ConexaoBanco {
    final private String url = "jdbc:mysql://localhost/sigest";
    final private String usuario = "root";
    final private String senha = "";

    public Connection pegarConexao(){
        try {
            return DriverManager.getConnection(url,usuario,senha);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar o banco de dados"+e);
        }
        return null;
    }
}
/*
public class ConexaoBanco{
    final private String url = "jdbc:mysql://localhost/sigest";
    final private String usuario = "root";
    final private String senha = "";
    
    public Connection pegarConexao(){
        try {
            return DriverManager.getConnection(url,usuario,senha);
        } catch (Exception e) {
        }
    }
    
}*/
