import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public DataSource dataSource;
    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("ht1943");

        // maximo de conexoes abertas
        comboPooledDataSource.setMaxPoolSize(15);

        this.dataSource = comboPooledDataSource;
    }
    public Connection recuperarConexao() throws SQLException {
        return this.dataSource.getConnection();
    }

}
