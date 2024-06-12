/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import sigest.jdbc.ConexaoBanco;
import sigest.model.Vendas;

/**
 *
 * @author domin
 */
public class VendasDAO {
       private Connection conn;
   
   public VendasDAO(){
       this.conn = new ConexaoBanco().pegarConexao(); 
   }  
   
   public void Salvar(Vendas obj){ //CRTL + SHIFT + i para corrigir as importações
       try {
           String sql = "insert into tb_vendas (cliente_id, data_venda, total_venda, observacoes)"
                   + "values(?, ?, ?, ? )";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setInt(1, obj.getClientes().getId());
           stmt.setString(2, obj.getData_venda());
           stmt.setDouble(3, obj.getTotal_venda());
           stmt.setString(4, obj.getObservacoes());
           stmt.execute();
           stmt.close();
           JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
       } catch (Exception e) {
           throw new RuntimeException("Erro ao realizar a venda!"+e);
           
       } 
   }
public int retornaUltimoIDVenda(){
    try {
        int ultimoID = 0;
        String sql = "Select max(id) id from tb_vendas";
        PreparedStatement stmt = conn.prepareStatement(sql); //Para pegar a consulta sql que criamos 
        ResultSet rs = stmt.executeQuery(); //Para executar a query criada.
        while(rs.next()){ //Para percorrer a tabela no banco de dados.
            Vendas v = new Vendas(); //Instanciando a classe vendas.
            v.setId(rs.getInt("id")); //Percorrendo a coluna do ID.
            ultimoID = v.getId(); //Recebendo o valor do ID
        }
        return ultimoID;
    } catch (Exception e) {
        throw new RuntimeException("Erro ao retornar o último ID da venda!"+e);
    }
}

}


