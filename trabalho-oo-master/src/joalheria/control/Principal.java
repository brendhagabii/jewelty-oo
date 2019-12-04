package joalheria.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import joalheria.NavegadorCenas;
import joalheria.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    @FXML
    private TextField tfBuscaCliente;
    @FXML
    private TextField tfBuscaProduto;
    @FXML
    private TextField tfQuantidade;
    @FXML
    private Button btnAbrirCompra;
    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnFinalizar;
    @FXML
    private ListView<Cliente> ltvClientes;
    @FXML
    private ListView<Produto> ltvProdutos;

    private Compra compra;
    private ArrayList<Item> carrinho;

    public void initialize() {
        try {
            carrinho = new ArrayList<Item>();
            ltvClientes.setItems(Joalheria.getInstance().listaClientes());
            ltvProdutos.setItems(Joalheria.getInstance().listaProdutos());
            tfQuantidade.setDisable(true);
            btnAdicionar.setDisable(true);
            btnFinalizar.setDisable(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscaClientes(ActionEvent actionEvent) {
        try {
            ltvClientes.setItems(Joalheria.getInstance().listaClientes(tfBuscaCliente.getText()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscaProdutos(ActionEvent actionEvent) {
        try {
            ltvProdutos.setItems(Joalheria.getInstance().listaProdutos(tfBuscaProduto.getText()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean abrirCompra(ActionEvent actionEvent) {
        Cliente cliente = ltvClientes.getSelectionModel().getSelectedItem();
        if (cliente == null) {
            new Alert(Alert.AlertType.ERROR, "Nenhum cliente selecionado.").showAndWait();
            return false;
        }

        btnAbrirCompra.setDisable(true);
        tfQuantidade.setDisable(false);
        btnAdicionar.setDisable(false);
        btnFinalizar.setDisable(false);

        try {
            compra = Joalheria.getInstance().abrirCompra(new Compra(cliente.getId(), LocalDate.now()));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir compra.").showAndWait();
            System.out.println(e.getMessage());
        } finally {
            new Alert(Alert.AlertType.INFORMATION, "Compra aberta com sucesso! Adicione os itens e finalize-a.").showAndWait();
        }

        return true;
    }

    public boolean adicionarProduto(ActionEvent actionEvent) {
        Produto produto = ltvProdutos.getSelectionModel().getSelectedItem();
        if (produto == null) {
            new Alert(Alert.AlertType.ERROR, "Nenhum produto selecionado.").showAndWait();
            return false;
        }

        int quantidade = Integer.parseInt(tfQuantidade.getText());

        carrinho.add(new Item(compra.getId(), produto.getId(), quantidade));
        new Alert(Alert.AlertType.INFORMATION, "Item adicionado com sucesso!").showAndWait();

        return true;
    }

    public void finalizarCompra(ActionEvent actionEvent) {
        try {
            Joalheria.getInstance().realizarCompra(carrinho);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao finalizar compra.").showAndWait();
            System.out.println(e.getMessage());
        } finally {
            new Alert(Alert.AlertType.INFORMATION, "Compra finalizada com sucesso!").showAndWait();
        }

        compra = null;
        carrinho.clear();

        btnAbrirCompra.setDisable(false);
        tfQuantidade.setDisable(true);
        btnAdicionar.setDisable(true);
        btnFinalizar.setDisable(true);
    }

    public boolean gerarRelatorio(ActionEvent actionEvent) {
        Cliente cliente = ltvClientes.getSelectionModel().getSelectedItem();
        if (cliente == null) {
            new Alert(Alert.AlertType.ERROR, "Nenhum cliente selecionado.").showAndWait();
            return false;
        }

        try {
            Joalheria.getInstance().gerarHistoricoDeCompras(cliente);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao gerar o PDF.").showAndWait();
            e.printStackTrace();
            return false;
        } finally {
            new Alert(Alert.AlertType.INFORMATION, "PDF gerado com sucesso!").showAndWait();
        }

        return true;
    }

    public void testePDF(ActionEvent actionEvent) {
        try {
            Joalheria.getInstance().pdf();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Não foi possível criar o PDF.").showAndWait();
            System.out.println(e.getMessage());
        }
        new Alert(Alert.AlertType.INFORMATION, "PDF criado!").showAndWait();
    }

    public void abrirJanelaNovoCliente(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.JANELA_NOVO_CLIENTE);
    }

    public void abrirJanelaNovoProduto(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.JANELA_NOVO_PRODUTO);
    }

    public void abrirJanelaEditarCliente(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.JANELA_EDITAR_CLIENTE);
    }

    public void abrirJanelaEditarProduto(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.JANELA_EDITAR_PRODUTO);
    }

    public void abrirJanelaRemoverCliente(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.JANELA_REMOVER_CLIENTE);
    }

    public void abrirJanelaRemoverProduto(ActionEvent actionEvent) {
        NavegadorCenas.loadJanela(NavegadorCenas.JANELA_REMOVER_PRODUTO);
    }
}
