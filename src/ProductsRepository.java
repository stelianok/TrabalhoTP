import java.util.List;
import java.util.ArrayList;

import java.sql.*;

class ProductsRepository {
  /**
   * @Todo
   *       - [X] Implement insert product feature.
   *       - [] Implement update product feature.
   *       - [] Implement list all products feature.
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

  void update(Product product) {
    String query = "UPDATE Products " +
        "SET name = ?, description = ?, category_id = ?, price = ?, quantity = ?, added_at = ?, supplier_id = ? " +
        "WHERE id = ?;";

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
      stmt.setInt(8, product.id);

      stmt.executeUpdate();

    } catch (ClassNotFoundException e) {
      System.out.println("Class not found: " + e);
    } catch (SQLException e) {
      System.out.println("Error: " + e);
    } finally {
      this.dbManager.closeDatabase();
      this.connection = null;
    }
  }

  public List<ProductWithSupplierAndCategoryInfo> listAll() {
    List<ProductWithSupplierAndCategoryInfo> products = new ArrayList<>();

    try {
      dbManager.startDatabase();

      this.connection = dbManager.getConnection();

      String query = "SELECT " +
          "p.id, " +
          "p.name AS product_name, " +
          "p.description, " +
          "c.name AS category_name, " +
          "p.price, " +
          "p.quantity, " +
          "p.added_at, " +
          "s.name AS supplier_name " +
          "FROM Products p " +
          "LEFT JOIN Categories c ON p.category_id = c.id " +
          "LEFT JOIN Suppliers s ON p.supplier_id = s.id";

      Statement stmt = this.connection.createStatement();

      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        ProductWithSupplierAndCategoryInfo product = new ProductWithSupplierAndCategoryInfo(
            rs.getInt("id"),
            rs.getString("product_name"),
            rs.getString("description"),
            rs.getString("category_name"),
            rs.getDouble("price"),
            rs.getInt("quantity"),
            rs.getString("added_at"),
            rs.getString("supplier_name"));
        products.add(product);
      }

    } catch (ClassNotFoundException e) {
      System.out.println("Class not found" + e);
    } catch (SQLException e) {
      System.out.println("Error" + e);
    } finally {
      this.dbManager.closeDatabase();
      this.connection = null;
    }

    return products;

  }

  public List<Product> findByName(String searchQuery) {
    List<Product> products = new ArrayList<>();

    try {
      dbManager.startDatabase();

      this.connection = dbManager.getConnection();

      String query = "SELECT id, name, description, category_id, price, quantity, added_at, supplier_id " +
          "FROM Products " +
          "WHERE name LIKE '%" + searchQuery + "%'";

      Statement stmt = this.connection.createStatement();

      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        Product product = new Product(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getInt("category_id"),
            rs.getDouble("price"),
            rs.getInt("quantity"),
            rs.getString("added_at"),
            rs.getInt("supplier_id"));

        products.add(product);
      }

    } catch (ClassNotFoundException e) {
      System.out.println("Class not found" + e);
    } catch (SQLException e) {
      System.out.println("Error" + e);
    } finally {
      this.dbManager.closeDatabase();
      this.connection = null;
    }

    return products;
  }
}