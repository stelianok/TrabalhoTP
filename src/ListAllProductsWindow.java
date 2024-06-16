import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListAllProductsWindow extends JPanel {
  /*
   * Listar todos os produtos em uma tabela
   * 
   */

  JLabel title = new JLabel("Lista de todos os produtos cadastrados");

  ListAllProductsWindow() {
    setLayout(new BorderLayout());

    title.setHorizontalAlignment(SwingConstants.CENTER);
    add(title, BorderLayout.NORTH);

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("Nome");
    model.addColumn("Descrição");
    model.addColumn("Categoria");
    model.addColumn("Preço");
    model.addColumn("Quantidade");
    model.addColumn("Adicionado em");
    model.addColumn("Fornecedor");

    ProductsRepository productsRepository = new ProductsRepository();

    List<ProductWithSupplierAndCategoryInfo> products = productsRepository.listAll();

    for (ProductWithSupplierAndCategoryInfo product : products) {
      model.addRow(new Object[] {
          product.id, product.name, product.description, product.categoryName,
          product.price, product.quantity, product.addedAt, product.supplierName
      });
    }

    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    add(scrollPane, BorderLayout.CENTER);

  }
}
