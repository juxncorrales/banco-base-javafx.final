package co.edu.uniquindio.banco.app;

import co.edu.uniquindio.banco.controlador.CiudadEventoController;
import co.edu.uniquindio.banco.controlador.LoginController;
import co.edu.uniquindio.banco.controlador.RegistroController;
import co.edu.uniquindio.banco.service.UniEventosService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private UniEventosService service = new UniEventosService();

    @Override
    public void start(Stage primaryStage) {
        mostrarPantallaRegistro(primaryStage);
    }

    public void mostrarPantallaRegistro(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistroView.fxml"));
            Scene scene = new Scene(loader.load());
            RegistroController controller = loader.getController();
            controller.setMainApp(this);
            controller.setService(service);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarPantallaLogin(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            Scene scene = new Scene(loader.load());
            LoginController controller = loader.getController();
            controller.setMainApp(this);
            controller.setService(service);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarPantallaSeleccionCiudad(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SeleccionCiudadEvento.fxml"));
            Scene scene = new Scene(loader.load());
            CiudadEventoController controller = loader.getController();
            controller.setService(service);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
