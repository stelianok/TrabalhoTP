import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

  private CategoriesRepository categoriesRepository = new CategoriesRepository();
  private SuppliersRepository suppliersRepository = new SuppliersRepository();

  private JComboBox<String> categoriesComboBox;
  private JComboBox<String> supplierComboBox;

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

    categoriesComboBox = new JComboBox<>(getFormattedCategories(categoriesObjects));

    mainPanel.add(categoriesComboBox);

    mainPanel.add(priceLabel);
    mainPanel.add(priceTextField);

    mainPanel.add(quantityLabel);
    mainPanel.add(quantityTextField);

    mainPanel.add(addedAtLabel);
    mainPanel.add(addedAtTextField);

    mainPanel.add(supplierLabel);

    List<SupplierObject> supplierObjects = suppliersRepository.getAllSuppliers();

    supplierComboBox = new JComboBox<>(getFormattedSuppliers(supplierObjects));

    mainPanel.add(supplierComboBox);

    add(mainPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(insertButton);

    add(buttonPanel, BorderLayout.SOUTH);

    insertButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Insert();
      }
    });
  }

  private void Insert() {
    /*
     * Resgatar dados dos campos escritos pelo usuário
     * Pegar ids de cada combobox
     * Formatar dados em Product
     * Enviar dados através de productsRepository
     * Mostrar mensagem de erro ou sucesso
     * Em caso de sucesso, limpar os campos.
     */
    try {
      Integer selectedCategoryId = this.getIdFrom(categoriesComboBox.getSelectedItem().toString());
      Integer selectedSupplierId = this.getIdFrom(supplierComboBox.getSelectedItem().toString());

      Product product = new Product(
          nameTextField.getText(),
          descriptionTextArea.getText(),
          selectedCategoryId,
          Double.parseDouble(priceTextField.getText()),
          Integer.parseInt(quantityTextField.getText()),
          addedAtTextField.getText(),
          selectedSupplierId);

      ProductsRepository productsRepository = new ProductsRepository();

      productsRepository.create(product);

      JOptionPane.showMessageDialog(null, "Inserção realizada com sucesso! \n", ":D",
          JOptionPane.INFORMATION_MESSAGE);
      this.CleanFields();

    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Erro ao inserir registro no banco de dados\n" + e, "Erro",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private Integer getIdFrom(String str) {
    return Integer.parseInt(str.split("-")[0].trim());
  }

  private void CleanFields() {
    nameTextField.setText("");
    descriptionTextArea.setText("");
    priceTextField.setText("");
    quantityTextField.setText("");
    addedAtTextField.setText("");
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
      SupplierObject supplier = supplierObjects.get(i);
      int id = supplier.id;
      String name = supplier.name;
      String supplierStr = id + " - " + name;

      suppliersStr[i] = supplierStr;
    }

    return suppliersStr;
  }
}