package br.alura.jdbc.dao;

import br.alura.jdbc.model.Produto;

import java.sql.*;

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
}
