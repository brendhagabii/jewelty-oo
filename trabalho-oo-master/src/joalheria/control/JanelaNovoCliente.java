package joalheria.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import joalheria.NavegadorCenas;
import joalheria.model.Cliente;
import joalheria.model.Joalheria;

import java.sql.SQLException;

public class JanelaNovoCliente {
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfCPF;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfTelefone;
    @FXML
    private TextField tfEndereco;

    public void cadastrar(ActionEvent actionEvent) {
        String nome = tfNome.getText();
        String cpf = tfCPF.getText();
        String email = tfEmail.getText();
        String telefone = tfTelefone.getText();
        String endereco = tfEndereco.getText();

        try {
            Joalheria.getInstance().cadastraCliente(new Cliente(nome, cpf, email, telefone, endereco));
            new Alert(Alert.AlertType.INFORMATION, "Cliente cadastrado com sucesso!").showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Não foi possível cadastrar o cliente.").showAndWait();
        } finally {
            NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
        }
    }

    public void voltar(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
    }
}
