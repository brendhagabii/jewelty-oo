package joalheria;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import joalheria.control.JanelaBase;
import java.io.IOException;

public class NavegadorCenas {
    public static final String BASE = "/views/base.fxml";
    public static final String PRINCIPAL = "/views/principal.fxml";
    public static final String JANELA_NOVO_CLIENTE = "/views/janela_novo_cliente.fxml";
    public static final String JANELA_NOVO_PRODUTO = "/views/janela_novo_produto.fxml";
    public static final String JANELA_EDITAR_CLIENTE = "/views/janela_editar_cliente.fxml";
    public static final String JANELA_EDITAR_PRODUTO = "/views/janela_editar_produto.fxml";
    public static final String JANELA_REMOVER_CLIENTE = "/views/janela_remover_cliente.fxml";
    public static final String JANELA_REMOVER_PRODUTO = "/views/janela_remover_produto.fxml";

    private static JanelaBase controlador;

    public static void setControlador(JanelaBase controlador) {
        NavegadorCenas.controlador = controlador;
    }

    public static void loadJanela(String fxml) {
        try {
            controlador.setJanelaBase((Node) FXMLLoader.load(NavegadorCenas.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
