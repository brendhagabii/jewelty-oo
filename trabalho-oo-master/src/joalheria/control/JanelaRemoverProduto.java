package joalheria.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import joalheria.NavegadorCenas;
import joalheria.model.Joalheria;
import joalheria.model.Produto;

import java.sql.SQLException;

public class JanelaRemoverProduto {
    @FXML
    private ComboBox<Produto> cbProduto;

    public void initialize() {
        try {
            cbProduto.setItems(Joalheria.getInstance().listaProdutos());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(ActionEvent actionEvent) {
        Produto produto = cbProduto.getSelectionModel().getSelectedItem();

        try {
            Joalheria.getInstance().removeProduto(produto);
            new Alert(Alert.AlertType.INFORMATION, "Produto excluído com sucesso!").showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Não foi possível excluir o produto.").showAndWait();
        } finally {
            NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
        }
    }

    public void voltar(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
    }
}
