package br.com.alura.jdbc.dao;

import br.com.alura.jdbc.model.Categoria;
import br.com.alura.jdbc.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome FROM categorias")){
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()){
                while (resultSet.next()) {
                    Categoria categoria = new Categoria(resultSet.getInt(1), resultSet.getString(2));
                    categorias.add(categoria);
                }
            }
        }
        return categorias;
    }

    public List<Categoria> listarComProdutos() throws SQLException {
        Categoria ultima = null;
        List<Categoria> categorias = new ArrayList<>();
        System.out.println("Execultando a query de listar categoria");
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT c.id, c.nome, p.id, p.nome, p.descricao FROM categorias c INNER JOIN produtos p ON c.id = p.categoria_id")){
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()){
                while (resultSet.next()) {
                    if(ultima == null || ultima.getNome().equals(resultSet.getString(2))){
                        Categoria categoria = new Categoria(resultSet.getInt(1), resultSet.getString(2));
                        ultima = categoria;
                        categorias.add(categoria);
                    }
                    Produto produto = new Produto(resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5));
                    ultima.adicionar(produto);

                }
            }
        }
        return categorias;
    }
}
