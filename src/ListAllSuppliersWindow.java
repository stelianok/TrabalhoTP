import java.awt.*;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListAllSuppliersWindow extends JPanel {

  JLabel title = new JLabel("Lista de todos os fornecedores");

  ListAllSuppliersWindow() {
    setLayout(new BorderLayout());

    title.setHorizontalAlignment(SwingConstants.CENTER);
    add(title, BorderLayout.NORTH);

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("Nome");
    model.addColumn("Endereço");
    model.addColumn("Contato");
    model.addColumn("Email");

    SuppliersRepository suppliersRepository = new SuppliersRepository();

    List<SupplierObject> suppliers = suppliersRepository.getAllSuppliers();

    for (SupplierObject supplier : suppliers) {
      model.addRow(new Object[] {
          supplier.id, supplier.name, supplier.address, supplier.address, supplier.email
      });
    }

    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    add(scrollPane, BorderLayout.CENTER);

  }
}
