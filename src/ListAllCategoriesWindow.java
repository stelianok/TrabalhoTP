import java.awt.*;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListAllCategoriesWindow extends JPanel {

  JLabel title = new JLabel("Lista de todas as categorias cadastradas");

  ListAllCategoriesWindow() {
    setLayout(new BorderLayout());

    title.setHorizontalAlignment(SwingConstants.CENTER);
    add(title, BorderLayout.NORTH);

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("Nome");

    CategoriesRepository categoriesRepository = new CategoriesRepository();

    List<CategoryObject> categories = categoriesRepository.getAllCategories();

    for (CategoryObject category : categories) {
      model.addRow(new Object[] {
          category.id, category.name
      });
    }

    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    add(scrollPane, BorderLayout.CENTER);
  }
}
