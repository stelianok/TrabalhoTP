import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuppliersRepository {
  private Connection connection;
  private Statement statement;
  private DBManager dbManager = new DBManager();

  SuppliersRepository(Connection connection) {
    this.connection = connection;
  }

  public List<SupplierObject> getAllSuppliers() {
    List<SupplierObject> suppliers = new ArrayList<>();

    try {
      dbManager.startDatabase();
      this.connection = dbManager.getConnection();

      this.statement = this.connection.createStatement();

      String query = "SELECT * FROM Suppliers";

      ResultSet rs = this.statement.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        SupplierObject supplierObject = new SupplierObject(id, name);
        suppliers.add(supplierObject);
      }

    } catch (ClassNotFoundException e) {
      System.out.println("Class not found" + e);
    } catch (SQLException e) {
      System.out.println("Error" + e);
    } finally {
      this.dbManager.closeDatabase();
      this.connection = null;
      this.statement = null;
    }

    return suppliers;

  }

}
