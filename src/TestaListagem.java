import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory criaConexcao = new ConnectionFactory();
        Connection connection = criaConexcao.recuperarConexao();

        Statement statement = connection.createStatement();
        // metodo statement.execute() retorna true para SELECT (porque retorna uma lista) e false para INSERT,DELETE e UPDATE
        statement.execute("SELECT id, nome, descricao FROM produtos");

        //pega os resultados(lista) do statement.execute()
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            System.out.println(id);
            String nome = resultSet.getString("nome");
            System.out.println(nome);
            String descricao = resultSet.getString("descricao");
            System.out.println(descricao);
        }

        connection.close();

    }
}
