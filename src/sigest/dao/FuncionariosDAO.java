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
import sigest.model.Funcionarios;
import sigest.view.AreaTrabalho;
import sigest.view.FormularioLogin;

/**
 *
 * @author domin
 */
public class FuncionariosDAO {

    private Connection conn;

    public FuncionariosDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }

    public void Salvar(Funcionarios obj) {
        try {
            //1º Criar o SQL
            String sql = "insert into tb_funcionarios (nome, bi, nif, email,senha, cargo, nivel_acesso, telefone, telefone2, codPostal, provincia, numero, complemento, bairro, cidade, pais)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            //2ºPreparar a conexão SQL para se conectar com o Banco
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getBi());
            stmt.setString(3, obj.getNif());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getTelefone2());
            stmt.setString(10, obj.getCodPostal());
            stmt.setString(11, obj.getProvincia());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getPais());
            //3ºExecutar 
            stmt.execute();
            //4ºFechar conexão
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionarios cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o funcionarios" + e);
        }
    }

    public void Editar(Funcionarios obj) {
        try {
            //1º Criar o SQL
            String sql = "update tb_Funcionarios set nome=?, bi=?, nif=?, email=?, senha=?, cargo=?, nivel_acesso=?, telefone=?, telefone2=?, codPostal=?, provincia=?,"
                    + "numero=?, complemento=?, bairro=?, cidade=?, pais=? where id=?";
            //2ºPreparar a conexão SQL para se conectar com o Banco
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getBi());
            stmt.setString(3, obj.getNif());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getTelefone2());
            stmt.setString(10, obj.getCodPostal());
            stmt.setString(11, obj.getProvincia());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getPais());
            stmt.setInt(17, obj.getId());
            //3ºExecutar 
            stmt.execute();
            //4ºFechar conexão
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionario editado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar o funcionario" + e);
        }
    }

    public void Excluir(Clientes obj) {
        try {
            String sql = "delete from tb_Funcionarios where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionario exluido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o Funcionario" + e);
        }
    }

    public Funcionarios BuscarFuncionarios(String nome) {
        try {
            String sql = "select * from tb_Funcionarios where nome =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Funcionarios obj = new Funcionarios();
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("Nome"));
                obj.setBi(rs.getString("Bi"));
                obj.setNif(rs.getString("Nif"));
                obj.setEmail(rs.getString("Email"));
                obj.setSenha(rs.getString("Senha"));
                obj.setCargo(rs.getString("Cargo"));
                obj.setNivel_acesso(rs.getString("Nivel_Acesso"));
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
            JOptionPane.showMessageDialog(null, "Erro ao buscar o funcionario" + erro);
        }
        return null;
    }
    //Método para listar os clientes do Banco

    public List<Funcionarios> Listar() {
        List<Funcionarios> lista = new ArrayList<>();
        try {
            String sql = "Select * from tb_funcionarios";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("Nome"));
                obj.setBi(rs.getString("Bi"));
                obj.setNif(rs.getString("Nif"));
                obj.setEmail(rs.getString("Email"));
                obj.setSenha(rs.getString("Senha"));
                obj.setCargo(rs.getString("Cargo"));
                obj.setNivel_acesso(rs.getString("Nivel_acesso"));
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
            JOptionPane.showMessageDialog(null, "Erro ao criar a lista." + e);
        }
        return null;
    }

    public List<Funcionarios> Filtrar(String nome) {
        List<Funcionarios> lista = new ArrayList<>();
        try {
            String sql = "Select * from tb_Funcionarios where nome like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("Nome"));
                obj.setBi(rs.getString("Bi"));
                obj.setNif(rs.getString("Nif"));
                obj.setEmail(rs.getString("Email"));
                obj.setSenha(rs.getString("Senha"));
                obj.setCargo(rs.getString("Cargo"));
                obj.setNivel_acesso(rs.getString("Nivel_acesso"));
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
            JOptionPane.showMessageDialog(null, "Erro ao criar a lista." + e);
        }
        return null;
    }

    public void efectuarLogin(String email, String senha) {
        try {
            String sql = "select * from tb_funcionarios where email=? and senha=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("nivel_acesso").equals("Administrador")) {
                    AreaTrabalho at = new AreaTrabalho();
                    at.userLogado = rs.getString("nome"); //Para aparecer o nome do funcionário no label
                    JOptionPane.showMessageDialog(null, "Seja bem-vindo ao sistema!\n" + at.userLogado);
                    at.setVisible(true);
                } else if (rs.getString("nivel_acesso").equals("Normal")) {
                    AreaTrabalho at = new AreaTrabalho();
                    at.userLogado = rs.getString("nome"); //Para aparecer o nome do funcionário no label
                    at.FichaFornecedores.setVisible(false);
                    at.FichaFuncionarios.setEnabled(false);
                    at.MenuStock.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Seja bem-vindo ao sistema!\n" + at.userLogado);
                    at.setVisible(true);
                }
            } else {
                FormularioLogin login = new FormularioLogin();
                JOptionPane.showMessageDialog(null, "Dados Inválidos!");
                login.setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }
    }

    public void AdicionarStock(int StockNovo, int id) {
        try {
            String sql = "update tb_produtos set qtd_stock=? where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, StockNovo);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Adicionado com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar ao stock" + e);
        }
    }

}
