package br.com.alura.jdbc;

import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.model.Produto;

import java.sql.*;
import java.util.List;

public class TestaInsercaoEListagemComProduto {
    public static void main(String[] args) throws SQLException {
        Produto produto = new Produto("xiaomi","redmi note 7");

        try(Connection connection = new ConnectionFactory().recuperarConexao()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(produto);
            List<Produto> produtoList = produtoDAO.listar();
            produtoList.forEach(pl -> System.out.println(pl));
        }
        System.out.println(produto);
    }
}
