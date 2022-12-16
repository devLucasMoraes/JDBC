import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();

        // eu vou controlar o momento do Commit da minha aplicação, no momento da minha transação.
        connection.setAutoCommit(false);

        try {
            // evitando SQL Injection
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO produtos (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            adicinarVariavel("SmartTv","45",statement);
            adicinarVariavel("radio","radio de bateria",statement);

            connection.commit();
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Rollback execultado");
            connection.rollback();
        }
        connection.close();


    }

    private static void adicinarVariavel(String nome, String descricao, PreparedStatement statement) throws SQLException {
        statement.setString(1, nome);
        statement.setString(2, descricao);


        statement.execute();

        ResultSet resultSet = statement.getGeneratedKeys();
        while (resultSet.next()){
            Integer id = resultSet.getInt(1);
            System.out.println("O id criado foi: " + id);
        }
    }
}
