package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.Banco;
import co.edu.uniquindio.banco.modelo.Transaccion;
import co.edu.uniquindio.banco.modelo.enums.CategoriaTransaccion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Clase que se encarga de controlar la creación de transferencias entre cuentas
 * @author caflorezvi
 */
public class TransferenciaControlador {
    @FXML
    public ChoiceBox selectCategory;
    @FXML
    private Label lblNumerossss;
    @FXML
    private TextField txtIdentificacion;
    @FXML
    private TextField txtValueTransfe;
    @FXML
    private TableView<Transaccion> tablaTransacciones;
    private final Banco banco = Banco.getInstancia();
    public void valorTransfe(String identi, TableView<Transaccion> tablaTransacciones) {
        lblNumerossss.setText(identi);
        this.tablaTransacciones=tablaTransacciones;
        System.out.println("Valor de tablaTransacciones: " + this.tablaTransacciones);
    }
    public void transferir(ActionEvent actionEvent)throws Exception{
        try{

            float value = Float.parseFloat(txtValueTransfe.getText());
            String categoriaSeleccionadaStr = (String) selectCategory.getValue();
            CategoriaTransaccion categoria = CategoriaTransaccion.valueOf(categoriaSeleccionadaStr); // Ejemplo: asigna la categoría VIAJES
            banco.realizarTransferencia(lblNumerossss.getText(), txtIdentificacion.getText(), value, categoria);
            crearAlerta("Transferencia exitosa", Alert.AlertType.INFORMATION);
            if (tablaTransacciones != null) {
                tablaTransacciones.setItems(FXCollections.observableArrayList(banco.obtenerCuentaAhorros(lblNumerossss.getText()).getTransacciones()));
            } else {
                System.out.println("tablaTransacciones es null"+lblNumerossss.getText()); // Manejar el caso en que tablaTransacciones sea null
            }
                cerrarVentana();
        } catch (Exception e){
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
