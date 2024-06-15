import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class App {
    Connection connection;
    Statement statement;
    InsertWindow iw;

    void startDatabase() {
        try {
            Class.forName("org.hsql.jdbcDriver");
            String connectionString = "jdbc:HypersonicSQL:bd_teste";
            String username = "sa";
            String password = "";
            connection = DriverManager.getConnection(connectionString, username, password);

            statement = connection.createStatement();

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "O driver do banco de dados não foi encontrado.\n" + ex, "Erro",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na iniciação do acesso ao banco de dados\n" + ex, "Erro",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void closeDatabase() {
        try {
            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println("Falha ao encerrar conexão!");
        }
    }

    public App() {
        startDatabase();
        iw = new InsertWindow();
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}
