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
import sigest.jdbc.ConexaoBanco;
import sigest.model.ItensVendas;
import sigest.model.Produtos;

/**
 *
 * @author domin
 */
public class ItensVendasDAO {

    private Connection conn;

    public ItensVendasDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }

    public void Salvar(ItensVendas obj) {
        try {
            String sql = "insert into tb_itensvendas  (venda_id, produto_id, qtd, subtotal) values(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getVendas().getId());
            stmt.setInt(2, obj.getProdutos().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar os itens da venda!");
        }
    }
    //Criar lista dos itens baseados no ID da venda

    public List<ItensVendas>listaItens(int vendaID){
        try {
            List<ItensVendas>lista = new ArrayList<>();
            String sql = "SELECT p.id, p.descricao, i.qtd, p.preco, i.subtotal FROM tb_ItensVendas AS i INNER JOIN"
                    + " tb_produtos AS p ON (i.produto_id = p.id) WHERE i.venda_ID =  ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vendaID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ItensVendas item = new ItensVendas();
                Produtos p = new Produtos();
                p.setId(rs.getInt("p.ID"));
                item.setProdutos(p);
                p.setDescricao(rs.getString("p.descricao"));
                item.setProdutos(p);
                item.setQtd(rs.getInt("i.qtd"));
                p.setPreco(rs.getDouble("p.preco"));
                item.setProdutos(p);
                item.setSubtotal(rs.getInt("i.subtotal"));
                lista.add(item);
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a lista!"+e);
        }
    }
    
}
