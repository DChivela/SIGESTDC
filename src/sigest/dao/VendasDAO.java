/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sigest.jdbc.ConexaoBanco;
import sigest.model.Clientes;
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

public List<Vendas>historicoVendas(LocalDate dataInicial, LocalDate dataFinal){
    try {
        List<Vendas>lista = new ArrayList<>();
        String sql = "Select v.ID, c.nome, date_format(v.data_venda, '%d/%m/%Y')"
                + "as data_formatada, v.total_venda, v.observacoes from tb_vendas as v inner join"
                + " tb_clientes as c on (v.Cliente_ID=c.ID)"
                + " WHERE v.data_venda between ? and ?"; //O BETWEEN serve para selecionar valores em uma coluna dentro de um intervalo
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, dataInicial.toString());
        stmt.setString(2, dataFinal.toString());
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            Vendas v = new Vendas();
            Clientes c = new Clientes();
            v.setId(rs.getInt("v.id"));
            c.setNome(rs.getString("c.nome"));
            v.setClientes(c);
            v.setData_venda(rs.getString("data_formatada"));
            v.setTotal_venda(rs.getDouble("v.total_venda"));
            v.setObservacoes(rs.getString("v.observacoes"));
            lista.add(v);
            
        }
        return lista;
    } catch (Exception e) {
        throw new RuntimeException("Erro ao criar o histórico de vendas!"+e);
    }
}

public double ResumoDia(LocalDate dataVenda){
    try {
        double totalDia = 0; //Valor inicial.
        //Preparar a String baseando-se na data que estiver no nosso formulário.
        String sql = "SELECT SUM(total_venda) as total from tb_vendas where data_venda=?";
        //Pegando a conexão com o nosso banco de dados.
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, dataVenda.toString());
        // Executar a nossa String preparada acima.
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){ //Condição 
            totalDia = rs.getDouble("total" ); //Acima fizemos a alises para que o total_venda seja igual a total.
        }
        return totalDia;
    } catch (Exception e) {
        throw new RuntimeException("Erro ao retornar a posição do dia." +e);
    } 
}
}


