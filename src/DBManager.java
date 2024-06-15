import java.sql.*;

/**
 * Todo
 * - [] Tentar criar tabelas apenas se elas não existirem
 * - [] Preencher tabela de suppliers com registros
 * - [] Preencher tabela de categories com registros
 * - [] Tratar erros adequadamente
 */
public class DBManager {
  private Connection connection;

  private Statement statement;

  public void startDatabase() throws ClassNotFoundException, SQLException {

    Class.forName("org.hsql.jdbcDriver");
    String connectionString = "jdbc:HypersonicSQL:bd_sk8store";
    String username = "sa";
    String password = "";
    connection = DriverManager.getConnection(connectionString, username, password);

    statement = connection.createStatement();

    CreateTables(connection, statement);
  }

  public void closeDatabase() {

    try {
      statement.close();
      connection.close();
    } catch (Exception ex) {
      System.out.println("Falha ao encerrar conexão!");
    }
  }

  // workaround function since HSQLDB does not support IF NOT EXISTS
  private static boolean doesTableExist(Connection connection, String tableName) throws SQLException {
    boolean exists = false;
    ResultSet resultSet = null;

    try {
      resultSet = connection.getMetaData().getTables(null, null, tableName.toUpperCase(), null);
      exists = resultSet.next();
    } finally {
      if (resultSet != null) {
        resultSet.close();
      }
    }

    return exists;
  }

  private void CreateTables(Connection connection, Statement stmt) throws SQLException {

    if (!doesTableExist(connection, "Suppliers")) {
      String createTableSuppliers = "CREATE TABLE Suppliers (" +
          "id INT IDENTITY PRIMARY KEY, " +
          "name VARCHAR(255), " +
          "address VARCHAR(255), " +
          "phone VARCHAR(50), " +
          "email VARCHAR(255))";
      stmt.executeUpdate(createTableSuppliers);
    }

    if (!doesTableExist(connection, "Categories")) {
      String createTableCategories = "CREATE TABLE Categories (" +
          "id INT IDENTITY PRIMARY KEY, " +
          "name VARCHAR(255), " +
          "description VARCHAR(255))";
      stmt.executeUpdate(createTableCategories);
    }

    if (!doesTableExist(connection, "Products")) {
      String createTableProducts = "CREATE TABLE Products (" +
          "id INT IDENTITY PRIMARY KEY, " +
          "name VARCHAR(255), " +
          "description VARCHAR(255), " +
          "category_id INT, " +
          "price DECIMAL(10, 2), " +
          "quantity INT, " +
          "added_at VARCHAR(255), " +
          "supplier_id INT, " +
          "FOREIGN KEY (category_id) REFERENCES Categories(id), " +
          "FOREIGN KEY (supplier_id) REFERENCES Suppliers(id))";
      stmt.executeUpdate(createTableProducts);
    }

  }

  public Statement getStatement() {
    return statement;
  }

  public void setStatement(Statement statement) {
    this.statement = statement;
  }
}
