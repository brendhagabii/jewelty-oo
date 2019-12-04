package joalheria.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import joalheria.NavegadorCenas;
import joalheria.model.Joalheria;
import joalheria.model.Produto;

import java.sql.SQLException;

public class JanelaNovoProduto {
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfDescricao;
    @FXML
    private TextField tfPreco;

    public void cadastrar(ActionEvent actionEvent) {
        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        double preco = Double.parseDouble(tfPreco.getText());

        try {
            Joalheria.getInstance().cadastraProduto(new Produto(nome, descricao, preco));
            new Alert(Alert.AlertType.INFORMATION, "Produto cadastrado com sucesso!").showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Não foi possível cadastrar o produto.").showAndWait();
        } finally {
            NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
        }
    }

    public void voltar(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
    }
}