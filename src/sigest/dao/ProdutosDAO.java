/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sigest.jdbc.ConexaoBanco;
import sigest.model.Clientes;
import sigest.model.Fornecedores;
import sigest.model.Produtos;

/**
 *
 * @author domin
 */
public class ProdutosDAO {
   private Connection conn;
   
   public ProdutosDAO(){
       this.conn = new ConexaoBanco().pegarConexao();  
   }
   
   public void Salvar(Produtos obj){
       try {
           //1º Criar o SQL
           String sql = "insert into tb_produtos (descricao, preco, qtd_stock, for_id)"
                   + "values(?,?,?,?)";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getDescricao());
           stmt.setDouble(2,obj.getPreco());
           stmt.setInt(3,obj.getStock());
           stmt.setInt(4,obj.getFornecedor().getId());

           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Produto registado com sucesso!");
       } catch (SQLException erro) {
           JOptionPane.showMessageDialog(null, "Erro ao salvar o produto"+erro);
       }
   }
   
      public void Editar(Produtos obj){
       try {
           //1º Criar o SQL
           String sql = "update tb_produtos set descricao=?, preco=?, qtd_stock=?, for_id=? where id=?";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getDescricao());
           stmt.setDouble(2,obj.getPreco());
           stmt.setInt(3,obj.getStock());
           stmt.setInt(4,obj.getFornecedor().getId());
           stmt.setInt(5,obj.getId());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao editar o produto"+e);
       }
   }
      
      public void Excluir(Produtos obj){
          try {
              String sql = "delete from tb_produtos where id=?";
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt(1, obj.getId());
              stmt.execute();
              stmt.close();
              JOptionPane.showMessageDialog(null, "Produto exluido com sucesso!");
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null,"Erro ao excluir o produto"+e);
          }
      }
   
   public Clientes BuscarCliente(String nome){
       try {
           String sql = "select * from tb_clientes where nome =?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, nome);
           ResultSet rs = stmt.executeQuery();
           Clientes obj = new Clientes();
           if(rs.next()){
               obj.setId(rs.getInt("id"));
               obj.setNome(rs.getString("Nome"));
               obj.setBi(rs.getString("Bi"));
               obj.setNif(rs.getString("Nif"));
               obj.setEmail(rs.getString("Email"));
               obj.setTelefone(rs.getString("Telefone"));
               obj.setTelefone2(rs.getString("Telefone2"));
               obj.setCodPostal(rs.getString("codPostal"));
               obj.setProvincia(rs.getString("Provincia"));
               obj.setNumero(rs.getInt("numero"));
               obj.setComplemento(rs.getString("Complemento"));
               obj.setBairro(rs.getString("Bairro"));
               obj.setCidade(rs.getString("Cidade"));
               obj.setPais(rs.getString("Pais"));  
           }//Fechamento do preechimento automático
           return obj; //Retornar o objecto Cliente após a busca
           
       } catch (SQLException erro) { //Caso alguma coisa deia errado
           JOptionPane.showMessageDialog(null, "Erro ao buscar o cliente"+ erro);
       }
       return null;
    }
   //Método para listar os clientes do Banco
   public List<Produtos>Listar(){
       List<Produtos> lista = new ArrayList<>();
       try {
           String sql = "Select p.id, p.descricao, p.preco, p.qtd_stock, f.nome from tb_Produtos as p inner join tb_fornecedores as f on(p.for_id=f.id)";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Produtos obj = new Produtos();
               Fornecedores f = new Fornecedores();
               obj.setId(rs.getInt("id"));
               obj.setDescricao(rs.getString("Descricao"));
               obj.setPreco(rs.getDouble("Preco"));
               obj.setStock(rs.getInt("Qtd_Stock"));
               f.setNome(rs.getString("f.nome")); 
               obj.setFornecedor(f);
               lista.add(obj);//A variável lista servirá para adicionar o obj dentro da lista criada.
           }
           return lista; //Retorno do que estiver dentro da lista.
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao criar a lista."+e);
       }
       return null;
   }
      public List<Produtos>Filtrar(String nome){
       List<Produtos> lista = new ArrayList<>();
       try {
           String sql = "Select p.id, p.descricao, p.preco, p.qtd_stock, f.nome from tb_Produtos as p inner join "
                   + "tb_fornecedores as f on(p.for_id = f.id) where p.descricao like ?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, nome);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Produtos obj = new Produtos();
               Fornecedores f = new Fornecedores();
               obj.setId(rs.getInt("id"));
               obj.setDescricao(rs.getString("descricao"));
               obj.setPreco(rs.getDouble("preco"));
               obj.setStock(rs.getInt("qtd_stock"));
               f.setNome(rs.getString("f.nome"));
               obj.setFornecedor(f);
               lista.add(obj);//A variável lista servirá para adicionar o obj dentro da lista criada.
           }
           return lista; //Retorno do que estiver dentro da lista.
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao criar a lista."+e);
       }
       return null;
   }


      public void adicionarStock(int id, int StockNovo){
          try {
              String sql = "update tb_produtos set qtd_stock=? where id=?";
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt(1, StockNovo);
              stmt.setInt(2, id);
              stmt.execute();
              stmt.close();
              JOptionPane.showMessageDialog(null, "Nova quantidade adicionada ao stock!");
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Erro ao adicionar ao stock"+e);
          }
      }
      
            public void baixarStock(int id, int StockNovo){
          try {
              String sql = "update tb_produtos set qtd_stock=? where id=?";
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt(1, StockNovo);
              stmt.setInt(2, id);
              stmt.execute();
              stmt.close();
              JOptionPane.showMessageDialog(null, "Baixa no stock efectuada com sucesso!");
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Erro na baixa do stock");
          }
      }
      
}

