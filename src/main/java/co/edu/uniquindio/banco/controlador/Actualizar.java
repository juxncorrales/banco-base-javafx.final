package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.Banco;
import co.edu.uniquindio.banco.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Actualizar {
    @FXML
    public Label id;
    @FXML
    public TextField txtIdentificacion;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtCorreo;
    @FXML
    public TextField txtDireccion;
    @FXML
    public PasswordField txtPassword;

    private final Banco banco = Banco.getInstancia();

    private PanelClienteControlador panelClienteControlador;

    public void setPanelClienteControlador(PanelClienteControlador panelClienteControlador) {
        this.panelClienteControlador = panelClienteControlador;
    }
    @FXML
    public void valorTransfe(String ident) {
        id.setText(ident);
        cargarUser(ident);
    }
    public void cargarUser(String identi){
        Usuario usuario = banco.obtenerUsuario(identi);
        if (usuario != null) {
            txtIdentificacion.setText(usuario.getNumeroIdentificacion());
            txtNombre.setText(usuario.getNombre());
            txtCorreo.setText(usuario.getCorreoElectronico());
            txtDireccion.setText(usuario.getDireccion());
            txtPassword.setText(usuario.getContrasena());
        }
        else{
            System.out.println("no encontradi" + identi);
        }

    }
    public void actualizar(ActionEvent actionEvent)throws Exception {
        try {
            banco.actualizarUsuario(txtNombre.getText(), txtDireccion.getText(), txtIdentificacion.getText(), txtCorreo.getText(), txtPassword.getText());
            crearAlerta("Usuario actualizo correctamente", Alert.AlertType.INFORMATION);
            cargarUser(txtIdentificacion.getText());
            // Solicitar al PanelClienteControlador que actualice el nombre
            if (panelClienteControlador != null) {
                panelClienteControlador.actualizarNombre(txtNombre.getText());

            }
            cerrarVentana();
        }catch (Exception e){
            crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
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
        Stage stage = (Stage) txtIdentificacion.getScene().getWindow();
        stage.close();
    }
}
