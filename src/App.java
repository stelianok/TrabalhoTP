import java.sql.SQLException;
import javax.swing.*;

public class App {
    InsertWindow iw = new InsertWindow();
    DBManager dbManager = new DBManager();

    void connectToDatabase() {
        try {
            dbManager.startDatabase();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "O driver do banco de dados não foi encontrado.\n" + ex, "Erro",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na iniciação do acesso ao banco de dados\n" + ex, "Erro",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void closeDatabase() {
        try {
            dbManager.closeDatabase();
        } catch (Exception ex) {
            System.out.println("Falha ao encerrar conexão!");
        }
    }

    public App() {
        connectToDatabase();
        iw = new InsertWindow();
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}
