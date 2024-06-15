import java.awt.*;
import javax.swing.*;

/**
 * Todo
 * - [X] Criar UI
 * - [] Dados de categoriesComboBox precisam ser carregados do bd
 * - [] Dados de suppliersComboBox precisam ser carregados do bd
 * - [] Implementar funcionalidade de inserção no banco de dados com tratamento
 * de errors
 * - [] Exibir mensagem de sucesso ou erro após a inserção.
 * - [] Implementar navegação em menu.
 */

class InsertWindow extends JFrame {
  private JLabel nameLabel = new JLabel("Nome: ");
  private JTextField nameTextField = new JTextField(20);

  private JLabel descriptionLabel = new JLabel("Descrição:");
  private JTextArea descriptionTextArea = new JTextArea(5, 20);

  private String[] categories = { "1 - Skates montados", "2 - acessórios", "3 - equipamentos de proteção",
      "4 - Vestuário", "5 - Peças de reposição" };

  private JLabel categoryLabel = new JLabel("Categoria: ");
  private JComboBox<String> categoriesComboBox = new JComboBox<>(categories);

  private JLabel priceLabel = new JLabel("Preço: ");
  private JTextField priceTextField = new JTextField(80);

  private JLabel quantityLabel = new JLabel("Quantidade: ");
  private JTextField quantityTextField = new JTextField(80);

  private JLabel addedAtLabel = new JLabel("Adicionado em: ");
  private JTextField addedAtTextField = new JTextField(20);

  private String[] suppliers = { "1 - NIKE", "2 - SANTA CRUZ", "3 - HOCKEY", "4 - OUS", "5 - SPITFIRE",
  };

  private JLabel supplierLabel = new JLabel("Fornecedor: ");
  private JComboBox<String> supplierComboBox = new JComboBox<>(suppliers);

  private JButton insertButton = new JButton("Inserir registro");

  public InsertWindow() {
    setTitle("Inserir Registros");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    JPanel mainPanel = new JPanel(new GridLayout(8, 2, 5, 5));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    mainPanel.add(nameLabel);
    mainPanel.add(nameTextField);

    mainPanel.add(descriptionLabel);
    mainPanel.add(new JScrollPane(descriptionTextArea)); // Adicionando JTextArea em um JScrollPane

    mainPanel.add(categoryLabel);
    mainPanel.add(categoriesComboBox);

    mainPanel.add(priceLabel);
    mainPanel.add(priceTextField);

    mainPanel.add(quantityLabel);
    mainPanel.add(quantityTextField);

    mainPanel.add(addedAtLabel);
    mainPanel.add(addedAtTextField);

    mainPanel.add(supplierLabel);
    mainPanel.add(supplierComboBox);

    add(mainPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(insertButton);

    add(buttonPanel, BorderLayout.SOUTH);
    setVisible(true);
  }
}