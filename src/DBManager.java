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
      addDefaultSuppliers(connection, statement);
    }

    if (!doesTableExist(connection, "Categories")) {
      String createTableCategories = "CREATE TABLE Categories (" +
          "id INT IDENTITY PRIMARY KEY, " +
          "name VARCHAR(255), " +
          "description VARCHAR(255))";
      stmt.executeUpdate(createTableCategories);

      addDefaultCategories(connection, statement);

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

  private void addDefaultSuppliers(Connection connection, Statement stmt) throws SQLException {
    String nikeQuery = "INSERT INTO Suppliers (name, address, phone, email) VALUES " +
        "('NIKE', 'real nike address', '1199999999', 'nike@official.com') ";

    String santaCruzQuery = "INSERT INTO Suppliers (name, address, phone, email) VALUES " +
        "('SANTA CRUZ', 'REAL SANTA ADDRESS', '1299999999','santacruz@official.com') ";

    String hockeyQuery = "INSERT INTO Suppliers (name, address, phone, email) VALUES " +
        "('HOCKEY', 'real HOCKEY address', '1399999999', 'hockey@official.com') ";

    String ousQuery = "INSERT INTO Suppliers (name, address, phone, email) VALUES " +
        "('OUS', 'real OUS address', '14199999999', 'ous@official.com') ";

    String spitfireQuery = "INSERT INTO Suppliers (name, address, phone, email) VALUES " +
        "('SPITFIRE', 'real SPITFIRE address', '1599999999', 'spitfire@official.com')";

    stmt.executeUpdate(nikeQuery);
    stmt.executeUpdate(santaCruzQuery);
    stmt.executeUpdate(hockeyQuery);
    stmt.executeUpdate(ousQuery);
    stmt.executeUpdate(spitfireQuery);

  }

  private void addDefaultCategories(Connection connection, Statement stmt) throws SQLException {
    String[] categories = {
        "Skates montados",
        "Acessórios",
        "Equipamentos de proteção",
        "Vestuário",
        "Peças de reposição"
    };

    for (int i = 0; i < categories.length; i++) {
      String query = "INSERT INTO Categories (id, name, description) VALUES " +
          "(" + (i + 1) + ", '" + categories[i] + "', 'Descrição da categoria " + (i + 1) + "')";

      stmt.executeUpdate(query);
    }
  }

  public Connection getConnection() {
    return connection;
  }

}
