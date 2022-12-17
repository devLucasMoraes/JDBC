package br.alura.jdbc;

import br.alura.jdbc.dao.CategoriaDAO;
import br.alura.jdbc.model.Categoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemDeCategorias {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = new ConnectionFactory().recuperarConexao()){
            CategoriaDAO categoriaDao = new CategoriaDAO(connection);
            List<Categoria> categoriaList = categoriaDao.listar();
            categoriaList.forEach(ct -> System.out.println(ct.getNome()));
        }
    }
}
