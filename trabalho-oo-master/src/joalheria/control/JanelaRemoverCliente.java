package joalheria.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import joalheria.NavegadorCenas;
import joalheria.model.Cliente;
import joalheria.model.Joalheria;

import java.sql.SQLException;

public class JanelaRemoverCliente {
    @FXML
    private ComboBox<Cliente> cbCliente;

    public void initialize() {
        try {
            cbCliente.setItems(Joalheria.getInstance().listaClientes());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(ActionEvent actionEvent) {
        Cliente cliente = cbCliente.getSelectionModel().getSelectedItem();

        try {
            Joalheria.getInstance().removeCliente(cliente);
            new Alert(Alert.AlertType.INFORMATION, "Cliente excluído com sucesso!").showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Não foi possível excluir o cliente.").showAndWait();
        } finally {
            NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
        }
    }

    public void voltar(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
    }
}
