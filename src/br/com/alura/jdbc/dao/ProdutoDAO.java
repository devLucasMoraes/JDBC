package br.com.alura.jdbc.dao;

import br.com.alura.jdbc.model.Categoria;
import br.com.alura.jdbc.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvarComCategoria(Produto produto) {
        String sql = "INSERT INTO produtos (nome, descricao, categoria_id) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setInt(3, produto.getCategoriaId());

            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    produto.setId(rst.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Produto> listar() {
        try { List<Produto> produtos = new ArrayList<>();

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Produto> buscar(Categoria ct) {
        try { List<Produto> produtos = new ArrayList<>();

            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, descricao FROM produtos WHERE categoria_id = ?")){
                preparedStatement.setInt(1,ct.getId());
                preparedStatement.execute();

                try (ResultSet resultSet = preparedStatement.getResultSet()){
                    while (resultSet.next()){
                        Produto produto = new Produto(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                        produtos.add(produto);
                    }
                }
            }
            return produtos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deletar(Integer id) {
        try (PreparedStatement stm = connection.prepareStatement("DELETE FROM produtos WHERE id = ?")) {
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(String nome, String descricao, Integer id) {
        try (PreparedStatement stm = connection
                .prepareStatement("UPDATE produtos p SET p.nome = ?, p.descricao = ? WHERE id = ?")) {
            stm.setString(1, nome);
            stm.setString(2, descricao);
            stm.setInt(3, id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
