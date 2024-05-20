package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.app.MainApp;
import co.edu.uniquindio.banco.modelo.Usuario;
import co.edu.uniquindio.banco.service.UniEventosService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField contraseñaField;
    @FXML
    private Button loginButton;

    private UniEventosService service;
    private MainApp mainApp;

    @FXML
    public void initialize() {
        loginButton.setOnAction(event -> iniciarSesion());
    }

    public void setService(UniEventosService service) {
        this.service = service;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void iniciarSesion() {
        String email = emailField.getText();
        String contraseña = contraseñaField.getText();
        Usuario usuario = service.iniciarSesion(email, contraseña);
        if (usuario != null) {
            // Redirigir a la pantalla de selección de ciudad
            Stage stage = (Stage) loginButton.getScene().getWindow();
            mainApp.mostrarPantallaSeleccionCiudad(stage);
        } else {
            // Mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Autenticación");
            alert.setHeaderText(null);
            alert.setContentText("Email o contraseña incorrectos.");
            alert.showAndWait();
        }
    }
}
