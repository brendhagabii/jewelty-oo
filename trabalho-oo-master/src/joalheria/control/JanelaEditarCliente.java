package joalheria.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import joalheria.NavegadorCenas;
import joalheria.model.Cliente;
import joalheria.model.Joalheria;

import java.sql.SQLException;

public class JanelaEditarCliente {
    @FXML
    private ComboBox<Cliente> cbCliente;
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

    public void initialize() {
        try {
            cbCliente.setItems(Joalheria.getInstance().listaClientes());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void salvar(ActionEvent actionEvent) {
        Cliente cliente = cbCliente.getSelectionModel().getSelectedItem();

        String nome = tfNome.getText();
        String cpf = tfCPF.getText();
        String email = tfEmail.getText();
        String telefone = tfTelefone.getText();
        String endereco = tfEndereco.getText();

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);

        try {
            Joalheria.getInstance().atualizaCliente(cliente);
            new Alert(Alert.AlertType.INFORMATION, "Dados do cliente atualizados com sucesso!").showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Não foi possível atualizar o cliente.").showAndWait();
        } finally {
            NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
        }
    }

    public void voltar(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);
    }
}
