package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.Banco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;

/**
 * Clase que representa el controlador de la vista de login
 * @author caflorezvi
 */
public class LoginControlador {
    @FXML
    private TextField txtNuIdenti;
    @FXML
    private  PasswordField txtPasswordLogin;

    private final Banco banco = Banco.getInstancia();


    public void login(ActionEvent actionEvent) throws Exception {
        try {
            banco.validarUsuario(txtNuIdenti.getText(), txtPasswordLogin.getText());

            crearAlerta("Usuario registrado correctamente", Alert.AlertType.INFORMATION);
            cargarPanelCliente(txtNuIdenti.getText(),txtPasswordLogin.getText());
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/inicio.fxml"));
            Parent root = loader.load();
            InicioControlador inicioControlador = loader.getController();

            // Configura el escenario actual con la nueva vista
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Muestra la nueva vista
            stage.show();*/
            //cerrarVentana();
        } catch (Exception e) {
            crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    private void cargarPanelCliente(String ident, String contrase) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PanelCliente.fxml"));
            Parent root = loader.load();


            PanelClienteControlador controller = loader.getController();
            controller.inicializarValores(ident,contrase);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Cierra la ventana actual de inicio de sesión
            Stage currentStage = (Stage) txtNuIdenti.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public void cerrarVentana(){
        Stage stage = (Stage) txtNuIdenti.getScene().getWindow();
        stage.close();
    }
}
