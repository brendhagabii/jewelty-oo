package joalheria.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import joalheria.NavegadorCenas;
import joalheria.model.Joalheria;
import joalheria.model.Produto;

import java.sql.SQLException;

public class JanelaEditarProduto {
    @FXML
    private ComboBox<Produto> cbProduto;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfDescricao;
    @FXML
    private TextField tfPreco;

    public void initialize() {
        try {
            cbProduto.setItems(Joalheria.getInstance().listaProdutos());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void salvar(ActionEvent actionEvent) {
        Produto produto = cbProduto.getSelectionModel().getSelectedItem();

        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        double preco = Double.parseDouble(tfPreco.getText());

        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);

        try {
            Joalheria.getInstance().atualizaProduto(produto);
            new Alert(Alert.AlertType.INFORMATION, "Dados do produto atualizados com sucesso!").showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Não foi possível atualizar o produto.").showAndWait();
        } finally {
            NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
        }
    }

    public void voltar(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
    }
}
