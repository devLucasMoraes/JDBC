import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual","root","ht1943");

        Statement statement = connection.createStatement();
        // metodo statement.execute() retorna true para SELECT e false para INSERT,DELETE e UPDATE
        statement.execute("SELECT id, nome, descricao FROM produtos");

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