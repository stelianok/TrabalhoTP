import java.sql.*;

public class DBManager {
  private Connection connection;
  private Statement statement;

  void startDatabase() throws ClassNotFoundException, SQLException {

    Class.forName("org.hsql.jdbcDriver");
    String connectionString = "jdbc:HypersonicSQL:bd_teste";
    String username = "sa";
    String password = "";
    connection = DriverManager.getConnection(connectionString, username, password);

    statement = connection.createStatement();

  }

  public void closeDatabase() {
    try {
      statement.close();
      connection.close();
    } catch (Exception ex) {
      System.out.println("Falha ao encerrar conexão!");
    }
  }
}
