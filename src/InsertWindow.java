import java.awt.*;
import javax.swing.*;
import java.util.List;

/**
 * Todo
 * - [X] Criar UI
 * - [X] Dados de categoriesComboBox precisam ser carregados do bd
 * - [X] Dados de suppliersComboBox precisam ser carregados do bd
 * - [] Implementar funcionalidade de inserção no banco de dados com tratamento
 * de errors
 * - [] Exibir mensagem de sucesso ou erro após a inserção.
 * - [] Implementar navegação em menu.
 */

class InsertWindow extends JPanel {
  private JLabel nameLabel = new JLabel("Nome: ");
  private JTextField nameTextField = new JTextField(20);

  private JLabel descriptionLabel = new JLabel("Descrição:");
  private JTextArea descriptionTextArea = new JTextArea(5, 20);

  private JLabel categoryLabel = new JLabel("Categoria: ");

  private JLabel priceLabel = new JLabel("Preço: ");
  private JTextField priceTextField = new JTextField(80);

  private JLabel quantityLabel = new JLabel("Quantidade: ");
  private JTextField quantityTextField = new JTextField(80);

  private JLabel addedAtLabel = new JLabel("Adicionado em: ");
  private JTextField addedAtTextField = new JTextField(20);

  private JLabel supplierLabel = new JLabel("Fornecedor: ");

  private JButton insertButton = new JButton("Inserir registro");

  private CategoriesRepository categoriesRepository = new CategoriesRepository(new DBManager().getConnection());
  private SuppliersRepository suppliersRepository = new SuppliersRepository(new DBManager().getConnection());

  public InsertWindow() {
    setLayout(new BorderLayout());

    JPanel mainPanel = new JPanel(new GridLayout(8, 2, 5, 5));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    mainPanel.add(nameLabel);
    mainPanel.add(nameTextField);

    mainPanel.add(descriptionLabel);
    mainPanel.add(new JScrollPane(descriptionTextArea)); // Adicionando JTextArea em um JScrollPane

    mainPanel.add(categoryLabel);

    List<CategoryObject> categoriesObjects = categoriesRepository.getAllCategories();

    JComboBox<String> categoriesComboBox = new JComboBox<>(getFormattedCategories(categoriesObjects));

    mainPanel.add(categoriesComboBox);

    mainPanel.add(priceLabel);
    mainPanel.add(priceTextField);

    mainPanel.add(quantityLabel);
    mainPanel.add(quantityTextField);

    mainPanel.add(addedAtLabel);
    mainPanel.add(addedAtTextField);

    mainPanel.add(supplierLabel);

    List<SupplierObject> supplierObjects = suppliersRepository.getAllSuppliers();

    JComboBox<String> supplierComboBox = new JComboBox<>(getFormattedSuppliers(supplierObjects));

    mainPanel.add(supplierComboBox);

    add(mainPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(insertButton);

    add(buttonPanel, BorderLayout.SOUTH);
    // setVisible(true);
  }

  private String[] getFormattedCategories(List<CategoryObject> categoriesObjects) {
    String[] categoriesStr = new String[categoriesObjects.size()];

    for (int i = 0; i < categoriesObjects.size(); i++) {
      CategoryObject category = categoriesObjects.get(i);
      int id = category.id;
      String name = category.name;
      String categoryStr = id + " - " + name;

      categoriesStr[i] = categoryStr;
    }

    return categoriesStr;
  }

  private String[] getFormattedSuppliers(List<SupplierObject> supplierObjects) {
    String[] suppliersStr = new String[supplierObjects.size()];

    for (int i = 0; i < supplierObjects.size(); i++) {
      SupplierObject category = supplierObjects.get(i);
      int id = category.id;
      String name = category.name;
      String categoryStr = id + " - " + name;

      suppliersStr[i] = categoryStr;
    }

    return suppliersStr;
  }
}