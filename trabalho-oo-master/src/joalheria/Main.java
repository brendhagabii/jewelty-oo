package joalheria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import joalheria.control.JanelaBase;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = loadMainPane();
        primaryStage.setTitle("Jewelt 2019");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(NavegadorCenas.BASE));

        JanelaBase controller = loader.getController();

        NavegadorCenas.setControlador(controller);
        NavegadorCenas.loadJanela(NavegadorCenas.PRINCIPAL);

        return mainPane;
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}