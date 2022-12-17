package br.alura.jdbc.dao;

import br.alura.jdbc.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO produtos (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());

            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                while (resultSet.next()) {
                    produto.setId(resultSet.getInt(1));
                }
            }
        }
    }
    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, descricao FROM produtos")){
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()){
                while (resultSet.next()){
                    Produto produto = new Produto(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }
}
