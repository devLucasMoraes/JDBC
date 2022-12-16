import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();

        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM produtos WHERE id > 2");

        Integer linhasModificadas = statement.getUpdateCount();

        System.out.println("Quantidade de linhas modificadas foram: " + linhasModificadas);
    }
}
