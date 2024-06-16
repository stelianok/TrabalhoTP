import java.sql.*;

class ProductsRepository {
  /**
   * @Todo
   *       - [X] Implement insert product feature.
   *       - [] Implement update product feature.
   *       - [] Implement list all products feature.
   */

  /*
   * findById()
   * create()
   * listAll()
   */
  private Connection connection;
  private DBManager dbManager = new DBManager();

  void create(Product product) {
    String query = "INSERT INTO Products (name, description, category_id, price, quantity, added_at, supplier_id)" +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
      dbManager.startDatabase();
      this.connection = dbManager.getConnection();

      PreparedStatement stmt = connection.prepareStatement(query);

      stmt.setString(1, product.name);
      stmt.setString(2, product.description);
      stmt.setInt(3, product.category_id);
      stmt.setDouble(4, product.price);
      stmt.setInt(5, product.quantity);
      stmt.setString(6, product.added_at);
      stmt.setInt(7, product.supplier_id);

      stmt.executeUpdate();

    } catch (ClassNotFoundException e) {
      System.out.println("Class not found" + e);
    } catch (SQLException e) {
      System.out.println("Error" + e);
    } finally {
      this.dbManager.closeDatabase();
      this.connection = null;
    }
  }
}