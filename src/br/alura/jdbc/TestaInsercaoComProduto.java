package br.alura.jdbc;

import br.alura.jdbc.dao.ProdutoDAO;
import br.alura.jdbc.model.Produto;

import java.sql.*;

public class TestaInsercaoComProduto {
    public static void main(String[] args) throws SQLException {
        Produto produto = new Produto("xiaomi","redmi note 7");

        try(Connection connection = new ConnectionFactory().recuperarConexao()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(produto);
        }
        System.out.println(produto);
    }
}
