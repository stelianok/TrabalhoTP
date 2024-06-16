import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class ProductWithSupplierAndCategoryInfo {
  int id;
  String name;
  String description;
  String categoryName;
  double price;
  int quantity;
  String addedAt;
  String supplierName;

  public ProductWithSupplierAndCategoryInfo(int id, String name, String description, String categoryName, double price,
      int quantity,
      String addedAt, String supplierName) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.categoryName = categoryName;
    this.price = price;
    this.quantity = quantity;
    this.addedAt = addedAt;
    this.supplierName = supplierName;
  }
}

public class ListAllProductsWindow extends JPanel {
  /*
   * Listar todos os produtos em uma tabela
   * 
   */

  JLabel label = new JLabel("Lista de todos os produtos cadastrados");

  ListAllProductsWindow() {
    // setSize(800, 800);
    setLayout(new BorderLayout());

    label.setHorizontalAlignment(SwingConstants.CENTER);
    add(label, BorderLayout.NORTH);

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("Nome");
    model.addColumn("Descrição");
    model.addColumn("Categoria");
    model.addColumn("Preço");
    model.addColumn("Quantidade");
    model.addColumn("Adicionado em");
    model.addColumn("Fornecedor");

    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    add(scrollPane, BorderLayout.CENTER);

  }
}
