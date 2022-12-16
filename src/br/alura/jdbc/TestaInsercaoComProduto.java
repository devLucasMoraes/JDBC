package br.alura.jdbc;

import br.alura.jdbc.model.Produto;

import java.sql.*;

public class TestaInsercaoComProduto {
    public static void main(String[] args) throws SQLException {
        Produto produto = new Produto("xiaomi","redmi note 7");

        try(Connection connection = new ConnectionFactory().recuperarConexao()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produtos (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)){
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
        System.out.println(produto);
    }
}
