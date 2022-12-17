package br.alura.jdbc;

import br.alura.jdbc.dao.ProdutoDAO;
import br.alura.jdbc.model.Produto;

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
