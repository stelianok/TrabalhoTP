import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository {
  private Connection connection;
  private Statement statement;
  private DBManager dbManager = new DBManager();

  public List<CategoryObject> getAllCategories() {
    List<CategoryObject> categories = new ArrayList<>();

    try {
      dbManager.startDatabase();
      this.connection = dbManager.getConnection();

      this.statement = this.connection.createStatement();

      String query = "SELECT * FROM CATEGORIES";

      ResultSet rs = this.statement.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        CategoryObject categoryObject = new CategoryObject(id, name);
        categories.add(categoryObject);
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

    return categories;

  }

}
