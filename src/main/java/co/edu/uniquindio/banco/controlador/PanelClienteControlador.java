package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.Banco;
import co.edu.uniquindio.banco.modelo.Transaccion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView; // Aquí corregimos la importación
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
/**
 * Clase que se encarga de gestionar las acciones de la interfaz gráfica del panel del cliente.
 * @author caflorezvi
 */
public class PanelClienteControlador implements Initializable {
        @FXML
        public Label id;
        @FXML
        private Label lblNumero;
        @FXML
        private Label lblNombre;
        @FXML
        private TableView<Transaccion> tablaTransacciones;

        private final Banco banco = Banco.getInstancia();

        // Método para inicializar los valores en la ventana del panel de
        String  numeroIdent ;
        String contrase;
        public void inicializarValores(String identi,String contra) {
                String numero= String.valueOf(banco.obtenerNumeroCuenta(identi));
                String nombre = String.valueOf(banco.obtenerNombre(identi));
                numeroIdent=identi;
                contrase=contra;
                id.setText(numero);
                lblNombre.setText(nombre + " Bienvenido a su cuenta" );
                lblNumero.setText("N° de cuenta: " + numero);

        }
        public void actualizarNombre(String nuevoNombre) {
                lblNombre.setText(nuevoNombre + " Bienvenido a su cuenta");
        }
        public void consultar(ActionEvent actionEvent)throws Exception{
                try{
                        String consulta = String.valueOf(banco.consultarCuentasUsario(numeroIdent,contrase));
                        crearAlerta( consulta, Alert.AlertType.INFORMATION);

                } catch (Exception e) {
                        e.printStackTrace(); // Manejar el error adecuadamente, por ejemplo, mostrando una alerta
                }
        }
        @FXML
        public void transferir(ActionEvent actionEvent) {
                try {
                        // Cargar el archivo FXML de la nueva ventana
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/transferencia.fxml"));
                        Parent root = loader.load();
                        TransferenciaControlador controller = loader.getController();
                        System.out.println("Valor de tablaTransacciones: " + tablaTransacciones);
                        controller.valorTransfe(id.getText(), tablaTransacciones);
                        // Configurar la escena con la nueva vista
                        Scene scene = new Scene(root);

                        // Obtener el escenario actual
                        Stage stage = (Stage) lblNumero.getScene().getWindow();

                        // Configurar la nueva ventana y mostrarla
                        Stage nuevaVentana = new Stage();
                        nuevaVentana.setScene(scene);
                        nuevaVentana.show();
                } catch (Exception e) {
                        e.printStackTrace(); // Manejar el error adecuadamente, por ejemplo, mostrando una alerta
                }
        }

        @FXML
        public void actualizar(ActionEvent actionEvent) {
                try {
                        // Cargar el archivo FXML de la nueva ventana
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/actualizar.fxml"));
                        Parent root = loader.load();
                        Actualizar controller = loader.getController();
                        controller.valorTransfe(numeroIdent);
                        // Configurar la referencia al PanelClienteControlador
                        controller.setPanelClienteControlador(this);
                        // Configurar la escena con la nueva vista
                        Scene scene = new Scene(root);

                        // Obtener el escenario actual
                        Stage stage = (Stage) id.getScene().getWindow();

                        // Configurar la nueva ventana y mostrarla
                        Stage nuevaVentana = new Stage();
                        nuevaVentana.setScene(scene);
                        nuevaVentana.show();
                } catch (Exception e) {
                        e.printStackTrace(); // Manejar el error adecuadamente, por ejemplo, mostrando una alerta
                }
        }
        @FXML
        public TableColumn<Transaccion, String> Tipo;
        @FXML
        public TableColumn <Transaccion, String> Fecha;
        @FXML
        public TableColumn <Transaccion, String> Valor;
        @FXML
        public TableColumn <Transaccion, String> Usuario;
        @FXML
        public TableColumn <Transaccion, String> Categoria;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                Tipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo().toString()));
                Fecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
                Valor.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getMonto()));
                Usuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsuario().getNombre()));
                Categoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria().toString()));
        }
        @FXML
        public void actualizarPagina(ActionEvent event) {
            try {
                    String identificacion = id.getText();
                    if (tablaTransacciones != null) {
                            tablaTransacciones.setItems(FXCollections.observableArrayList(banco.obtenerCuentaAhorros(identificacion).getTransacciones()));
                    } else {
                            crearAlerta("Usuario registrado correctamente", Alert.AlertType.ERROR);
                    }
            } catch (Exception e) {
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

        public void cerrarSessión(ActionEvent actionEvent) {
                try {
                        // Cargar el archivo FXML de la nueva ventana
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/inicio.fxml"));
                        Parent root = loader.load();
                        InicioControlador controller = loader.getController();
                        String numeroIdenti=null;
                        controller.cerrarSesion(numeroIdenti);
                        // Configurar la escena con la nueva vista
                        Scene scene = new Scene(root);

                        // Obtener el escenario actual
                        Stage stage = (Stage) id.getScene().getWindow();

                        // Configurar la nueva ventana y mostrarla
                        Stage nuevaVentana = new Stage();
                        nuevaVentana.setScene(scene);
                        nuevaVentana.show();
                        cerrarVentana();
                } catch (Exception e) {
                        e.printStackTrace(); // Manejar el error adecuadamente, por ejemplo, mostrando una alerta
                }

        }
        public void cerrarVentana(){
                Stage stage = (Stage) id.getScene().getWindow();
                stage.close();
        }
}
