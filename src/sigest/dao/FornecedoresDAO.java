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

/**
 *
 * @author domin
 */
public class FornecedoresDAO {
   private Connection conn;
   
   public FornecedoresDAO(){
       this.conn = new ConexaoBanco().pegarConexao();  
   }
   
   public void Salvar(Fornecedores obj){
       try {
           //1º Criar o SQL
           String sql = "insert into tb_Fornecedores (nome, nif, email, telefone, telefone2, codPostal, provincia, numero, complemento, bairro, cidade, pais)"
                   + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getNome());
           stmt.setString(2,obj.getNif());
           stmt.setString(3,obj.getEmail());
           stmt.setString(4,obj.getTelefone());
           stmt.setString(5,obj.getTelefone2());
           stmt.setString(6,obj.getCodPostal());
           stmt.setString(7,obj.getProvincia());
           stmt.setInt(8,obj.getNumero());
           stmt.setString(9,obj.getComplemento());
           stmt.setString(10,obj.getBairro());
           stmt.setString(11,obj.getCidade());
           stmt.setString(12,obj.getPais());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao salvar o fornecedores"+e);
       }
   }
   
      public void Editar(Fornecedores obj){
       try {
           //1º Criar o SQL
           String sql = "update tb_Fornecedores set nome=?, nif=?, email=?, telefone=?, telefone2=?, codPostal=?, provincia=?,"
                   +"numero=?, complemento=?, bairro=?, cidade=?, pais=? where id=?";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getNome());
           stmt.setString(2,obj.getNif());
           stmt.setString(3,obj.getEmail());
           stmt.setString(4,obj.getTelefone());
           stmt.setString(5,obj.getTelefone2());
           stmt.setString(6,obj.getCodPostal());
           stmt.setString(7,obj.getProvincia());
           stmt.setInt(8,obj.getNumero());
           stmt.setString(9,obj.getComplemento());
           stmt.setString(10,obj.getBairro());
           stmt.setString(11,obj.getCidade());
           stmt.setString(12,obj.getPais());
           stmt.setInt(13,obj.getId());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Fornecedor editado com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao editar o fornecedor"+e);
       }
   }
      
      public void Excluir(Clientes obj){
          try {
              String sql = "delete from tb_Fornecedores where id=?";
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt(1, obj.getId());
              stmt.execute();
              stmt.close();
              JOptionPane.showMessageDialog(null, "Fornecedor exluido com sucesso!");
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null,"Erro ao excluir o fornecedor"+e);
          }
      }
   
   public Fornecedores BuscarFornecedores(String nome){
       try {
           String sql = "select * from tb_Fornecedores where nome =?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, nome);
           ResultSet rs = stmt.executeQuery();
           Fornecedores obj = new Fornecedores();
           if(rs.next()){
               obj.setId(rs.getInt("id"));
               obj.setNome(rs.getString("Nome"));
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
           JOptionPane.showMessageDialog(null, "Erro ao buscar o fornecedor"+ erro);
       }
       return null;
    }
   //Método para listar os clientes do Banco
   public List<Fornecedores>Listar(){
       List<Fornecedores> lista = new ArrayList<>();
       try {
           String sql = "Select * from tb_fornecedores";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Fornecedores obj = new Fornecedores();
               obj.setId(rs.getInt("id"));
               obj.setNome(rs.getString("Nome"));
               obj.setNif(rs.getString("NIF"));
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
               lista.add(obj);//A variável lista servirá para adicionar o obj dentro da lista criada.
           }
           return lista; //Retorno do que estiver dentro da lista.
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao criar a lista de for."+e);
       }
       return null;
   }
      public List<Fornecedores>Filtrar(String nome){
       List<Fornecedores> lista = new ArrayList<>();
       try {
           String sql = "Select * from tb_Fornecedores where nome like ?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, nome);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Fornecedores obj = new Fornecedores();
               obj.setId(rs.getInt("id"));
               obj.setNome(rs.getString("Nome"));
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
               lista.add(obj);//A variável lista servirá para adicionar o obj dentro da lista criada.
           }
           return lista; //Retorno do que estiver dentro da lista.
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao criar a lista."+e);
       }
       return null;
   }


}

