import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory criaConexcao = new ConnectionFactory();
        Connection connection = criaConexcao.recuperarConexao();

        System.out.println("Fechando conexao");
        connection.close();
    }
}
