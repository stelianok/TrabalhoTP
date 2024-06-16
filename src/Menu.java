import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Menu extends JFrame {
  private CardLayout cardLayout;
  private JPanel mainPanel;

  public Menu() {
    // Configura a janela principal
    setTitle("Gerenciamento de estoque loja sk8");
    setSize(1200, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Cria a barra de menu
    JMenuBar menuBar = new JMenuBar();

    // Cria um menu
    JMenu menu = new JMenu("Trocar Janela");
    menuBar.add(menu);

    // Cria itens de menu
    JMenuItem insertProdutMenuItem = new JMenuItem("Inserir um produto");
    JMenuItem updateProductMenuItem = new JMenuItem("Atualizar um produto");
    JMenuItem listProductsMenuItem = new JMenuItem("Listar todos os produtos");
    JMenuItem listSuppliersMenuItem = new JMenuItem("Listar todos os fornecedores");
    JMenuItem listProductCategories = new JMenuItem("Listar todas as categorias de produtos");

    // Adiciona itens ao menu
    menu.add(insertProdutMenuItem);
    menu.add(updateProductMenuItem);
    menu.add(listProductsMenuItem);
    menu.add(listSuppliersMenuItem);
    menu.add(listProductCategories);

    // Configura a barra de menu na janela
    setJMenuBar(menuBar);

    // Cria o painel principal com CardLayout
    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);

    // Painéis que o usuário terá acesso.
    InsertWindow insertWindow = new InsertWindow();
    UpdateProductWindow updateProductWindow = new UpdateProductWindow();

    // Adiciona os painéis ao painel principal
    mainPanel.add(insertWindow, "1");
    mainPanel.add(updateProductWindow, "2");

    /*
     * O código que gera a tabela está escrito no constructor desses JPanels.
     * Para garantir que a tabela gerada sempre esteja atualizada, o painel é
     * reconstruído sempre que o usuário "recarrega" a página.
     */
    mainPanel.add(new JPanel(), "3");
    mainPanel.add(new JPanel(), "4");
    mainPanel.add(new JPanel(), "5");

    // Adiciona o painel principal à janela
    add(mainPanel);

    // Adiciona ação aos itens de menu
    insertProdutMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cardLayout.show(mainPanel, "1");
      }
    });

    updateProductMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cardLayout.show(mainPanel, "2");
      }
    });

    listProductsMenuItem.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        ListAllProductsWindow listAllProductsWindow = new ListAllProductsWindow();
        mainPanel.add(listAllProductsWindow, "3");
        cardLayout.show(mainPanel, "3");
      }
    });

    listSuppliersMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ListAllSuppliersWindow listAllSuppliersWindow = new ListAllSuppliersWindow();
        mainPanel.add(listAllSuppliersWindow, "4");
        cardLayout.show(mainPanel, "4");
      }
    });

    listProductCategories.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ListAllCategoriesWindow listAllCategoriesWindow = new ListAllCategoriesWindow();
        mainPanel.add(listAllCategoriesWindow, "5");
        cardLayout.show(mainPanel, "5");
      }
    });

    setVisible(true);
  }
}