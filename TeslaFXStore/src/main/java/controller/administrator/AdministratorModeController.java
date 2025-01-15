package controller.administrator;

import controller.AbrirVentanas;
import dao.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;

/**
 * FXML Controller class
 *
 * @author jhova
 */
public class AdministratorModeController implements Initializable {

    // Permite mover la ventana manteniendo pulsado el cursor
    private double xOffset = 0;
    private double yOffset = 0;

    // Controlador y servicio de Customer
    private CustomerController customerController;
    private CrudCustomer crudCustomer;

    //  Administrator Customer
    CustomerAdministrator customerAdministrator;

    // Controlador y servicio de Stock
    private StockController stockController;
    private CrudVehicleStock crudStock;

    // Controlador y servicio de Vehicle
    private VehicleController vehicleController;
    private CrudVehicle crudVehicle;

    // Formato miles, crear un DecimalFormat con el patrón de formato deseado
    DecimalFormat formatter = new DecimalFormat("#,###");

    // Pantalla completa
    boolean bFullScreen = false;

    @FXML
    private AnchorPane mainPane;

    // Customer
    @FXML
    private TextField field_BusquedaCustomer;
    @FXML
    private TextField field_NameCustomer;
    @FXML
    private TextField field_CountryCustomer;
    @FXML
    private TextField field_AgeCustomer;
    @FXML
    private TextField field_EmailCustomer;
    @FXML
    private TextField field_PasswordCustomer;
    @FXML
    private TextArea textArea_DatosCustomer;
    @FXML
    private VBox vbox_camposCustomer;
    @FXML
    private HBox hbox_textAreaCustomer;
    @FXML
    private Button button_siguienteCustomer;
    @FXML
    private Button button_buscarCustomer;
    @FXML
    private Label label_buscadorCustomer;
    @FXML
    private Label label_introduceDatosCustomer;
    @FXML
    private TextField field_idCustomer;

    // Stock
    @FXML
    private TextField field_BusquedaStock;
    @FXML
    private TextArea textArea_DatosStock;
    @FXML
    private TextField field_ModelYStock;
    @FXML
    private TextField field_ModelXStock;
    @FXML
    private TextField field_Model3Stock;
    @FXML
    private TextField field_ModelSStock;
    @FXML
    private Button button_siguienteStock;
    @FXML
    private HBox hbox_textAreaStock;
    @FXML
    private Button button_buscarStock;
    @FXML
    private VBox vbox_camposStock;

    // Vehicle
    @FXML
    private TextField field_BusquedaVehicle;
    @FXML
    private TextArea textArea_DatosVehicle;
    @FXML
    private Button button_buscarVehicle;
    @FXML
    private VBox vbox_camposVehicle;
    @FXML
    private Button button_siguienteVehicle;
    @FXML
    private HBox hbox_textAreaVehicle;
    @FXML
    private TextField field_EmailVehicle;
    @FXML
    private TextField field_PaintVehicle;
    @FXML
    private TextField field_PriceVehicle;
    @FXML
    private VBox vbox_Tarifa1Vehicle;
    @FXML
    private VBox vbox_Tarifa2PlaidDualMotorVehicle;
    @FXML
    private RadioButton rButton_EnhancedAutopilot;
    @FXML
    private RadioButton rButton_selfDrivingCapability;
    @FXML
    private CheckBox check_wallConnector;
    @FXML
    private RadioButton rButton_rearWheelDrive;
    @FXML
    private RadioButton rButton_DualMotor;
    @FXML
    private RadioButton rButton_Plaid;
    @FXML
    private RadioButton rButton_highAutonomy;
    @FXML
    private RadioButton rButton_performance;
    @FXML
    private ToggleGroup grupoOpciones1Modely3;
    @FXML
    private ToggleGroup grupoOpciones2Modelsx;
    @FXML
    private ToggleGroup grupoOpciones0AutonomousDriving;
    @FXML
    private Label label_buscadorVehicle;
    @FXML
    private TextField field_ModeloVehicle;
    @FXML
    private Button btn_miSesion;
    @FXML
    private Label label_introduceDatosVehicle;
    @FXML
    private TextField field_idVehicle;
    @FXML
    private Button button_actualizarPrecioVehicle;
    @FXML
    private Button button_desactivarCamposVehicle;
    @FXML
    private VBox vBox_caracteristicasVehicle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar el controlador de Customer pasando los componentes de la interfaz gráfica
        crudCustomer = new CrudCustomer();
        customerController = new CustomerController(field_BusquedaCustomer, field_NameCustomer, field_CountryCustomer,
                field_AgeCustomer, field_EmailCustomer, field_PasswordCustomer, textArea_DatosCustomer,
                vbox_camposCustomer, hbox_textAreaCustomer, button_siguienteCustomer, button_buscarCustomer, crudCustomer, label_buscadorCustomer, label_introduceDatosCustomer, field_idCustomer);

        // Inicializar el controlador de Vehicle pasando los componentes de la interfaz gráfica
        crudVehicle = new CrudVehicle();
        vehicleController = new VehicleController(field_BusquedaVehicle, textArea_DatosVehicle, button_buscarVehicle, vbox_camposVehicle, button_siguienteVehicle, hbox_textAreaVehicle, field_EmailVehicle, field_PaintVehicle, field_PriceVehicle, vbox_Tarifa1Vehicle, vbox_Tarifa2PlaidDualMotorVehicle, rButton_EnhancedAutopilot, rButton_selfDrivingCapability, check_wallConnector, rButton_rearWheelDrive, rButton_DualMotor, rButton_Plaid, rButton_highAutonomy, rButton_performance, grupoOpciones1Modely3, grupoOpciones2Modelsx, grupoOpciones0AutonomousDriving, label_buscadorVehicle, field_ModeloVehicle, crudVehicle, crudCustomer, label_introduceDatosVehicle, field_idVehicle, button_actualizarPrecioVehicle, button_desactivarCamposVehicle, vBox_caracteristicasVehicle);

        // CrudStock
        crudStock = new CrudVehicleStock();
        stockController = new StockController(field_BusquedaStock, textArea_DatosStock, field_ModelYStock, field_ModelXStock, field_Model3Stock, field_ModelSStock, button_siguienteStock, hbox_textAreaStock, button_buscarStock, vbox_camposStock, crudStock);

        // Estado de los componentes por defecto
        customerController.vaciarTodoCustomer();
        vehicleController.vaciarTodoVehicle();
        stockController.vaciarTodoStock();

        // Recorte con esquinas
        recorteConEsquinas();
    }

    // Crear un recorte con esquinas redondeadas y aplicarlo al AnchorPane
    private void recorteConEsquinas() {
        Rectangle clip = new Rectangle();
        clip.setWidth(mainPane.getPrefWidth());
        clip.setHeight(mainPane.getPrefHeight());
        clip.setArcWidth(20); // Radio de las esquinas
        clip.setArcHeight(20);
        mainPane.setClip(clip);
    }

    @FXML
    private void abrirHomeSinSesion(MouseEvent event) {
        new AbrirVentanas().abrirHomeSinSesion(event);
    }

    @FXML
    private void minimiarVentana(MouseEvent event) {
        // Obtener la Stage actual a partir del evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Minimizar la ventana
        stage.setIconified(true);
    }

    @FXML
    private void fullVentana(MouseEvent event) {
        // Obtener la Stage actual a partir del evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Pantalla completa / full Ventana
        if (bFullScreen) {
            stage.setFullScreen(true);
            bFullScreen = false;
        } else {
            stage.setFullScreen(false);
            stage.setWidth(1500);       // Ancho por defecto (ajusta según tu diseño)
            stage.setHeight(850);      // Altura por defecto (ajusta según tu diseño)
            stage.centerOnScreen();    // Opcional: centra la ventana en la pantalla
            bFullScreen = true;
        }

    }

    @FXML
    private void cerrarVentana(MouseEvent event) {
        // Obtener la Stage actual a partir del evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Cerrar la ventana
        stage.close();
        // Cerrar conexión
        Conexion.close();
    }

    // Almacena la posición de la scena
    @FXML
    private void handleMousePressed(MouseEvent event) {
        // Captura la posición inicial del mouse en la ventana
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    // Permite mover la ventana según la posición de la scena
    @FXML
    private void handleMouseDragged(MouseEvent event) {
        // Obtiene la ventana actual (Stage) y la mueve a la nueva posición
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    // Mensaje Alert
    private void mensajeAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // ********** Inicio Customer Métodos **********
    // Delegación de eventos de Customer
    @FXML
    private void handle_eliminarCustomer(MouseEvent event) {
        customerController.handle_eliminarCustomer(event);
    }

    @FXML
    private void handle_anadirCustomer(MouseEvent event) {
        customerController.handle_anadirCustomer(event);
    }

    @FXML
    private void handle_modificarCustomer(MouseEvent event) {
        customerController.handle_modificarCustomer(event);
    }

    @FXML
    private void handle_consultaCustomer(MouseEvent event) {
        customerController.handle_consultaCustomer(event);
    }

    @FXML
    private void handle_mostrarListaCustomer(MouseEvent event) {
        customerController.handle_mostrarListaCustomer(event);
    }

    @FXML
    private void handle_siguienteCustomer(MouseEvent event) {
        customerController.handle_siguienteCustomer(event);
    }

    @FXML
    private void handle_AtrasCustomer(MouseEvent event) {
        customerController.handle_AtrasCustomer(event);
    }

    @FXML
    private void handle_buscarCustomer(MouseEvent event) {
        customerController.handle_buscarCustomer(event);
    }

    @FXML
    private void handle_consultaIdCustomer(MouseEvent event) {
        customerController.handle_consultaIdCustomer(event);
    }

    @FXML
    private void handle_consultaPaisCustomer(MouseEvent event) {
        customerController.handle_consultaPaisCustomer(event);
    }

    @FXML
    private void handle_totalCustomer(MouseEvent event) {
        customerController.handle_totalCustomer(event);
    }

    @FXML
    private void handle_consultaEdadCustomer(MouseEvent event) {
        customerController.handle_consultaEdadCustomer(event);
    }

    // ********** Fin Customer Métodos **********
    // ********** Inicio Vehicle Métodos **********
    // Delegación de eventos de Vehicle
    @FXML
    private void handle_eliminarVehicle(MouseEvent event) {
        vehicleController.handle_eliminarVehicle(event);
    }

    @FXML
    private void handle_anadirVehicle(MouseEvent event) {
        vehicleController.handle_anadirVehicle(event);
    }

    @FXML
    private void handle_modificarVehicle(MouseEvent event) {
        vehicleController.handle_modificarVehicle(event);
    }

    @FXML
    private void handle_consultaVehicle(MouseEvent event) {
        vehicleController.handle_consultaVehicle(event);
    }

    @FXML
    private void handle_mostrarListaVehicle(MouseEvent event) {
        vehicleController.handle_mostrarListaVehicle(event);
    }

    @FXML
    private void handle_siguienteVehicle(MouseEvent event) {
        vehicleController.handle_siguienteVehicle(event);
    }

    @FXML
    private void handle_AtrasVehicle(MouseEvent event) {
        vehicleController.handle_AtrasVehicle(event);
    }

    @FXML
    private void handle_buscarVehicle(MouseEvent event) {
        vehicleController.handle_buscarVehicle(event);
    }

    @FXML
    private void handle_actualizarPrecioVehicle(MouseEvent event) {
        vehicleController.handle_actualizarPrecioVehicle(event);
    }

    @FXML
    private void handle_desactivarCamposVehicle(MouseEvent event) {
        vehicleController.handle_desactivarCamposVehicle(event);
    }

    @FXML
    private void handle_consultaModelVehicle(MouseEvent event) {
        vehicleController.handle_consultaModelVehicle(event);
    }

    @FXML
    private void handle_consultaCustomerVehicle(MouseEvent event) {
        vehicleController.handle_consultaCustomerVehicle(event);
    }

    @FXML
    private void handle_totalVehicles(MouseEvent event) {
        vehicleController.handle_totalVehicles(event);
    }

    @FXML
    private void handle_totalModelVehicles(MouseEvent event) {
        vehicleController.handle_totalModelVehicles(event);
    }

    // ********** Fin Vehicle Métodos **********
    // ********** Inicio Stock Métodos **********
    @FXML
    private void handle_modificarStock(MouseEvent event) {
        stockController.handle_modificarStock(event);
    }

    @FXML
    private void handle_mostrarListaStock(MouseEvent event) {
        stockController.handle_mostrarListaStock(event);
    }

    @FXML
    private void handle_siguienteStock(MouseEvent event) {
        System.out.println("Admin siguiente button");
        stockController.handle_siguienteStock(event);
    }

    @FXML
    private void handle_buscarStock(MouseEvent event) {
        stockController.handle_buscarStock(event);
    }

    @FXML
    private void handle_AtrasStock(MouseEvent event) {
        stockController.handle_AtrasStock(event);
    }

    @FXML
    private void handle_totalStock(MouseEvent event) {
        stockController.handle_totalStock(event);
    }
    
    // ********** Fin Stock Métodos **********

    // otros metodos 
    public CustomerAdministrator getCustomerAdministrator() {
        return customerAdministrator;
    }

    public void setCustomerAdministrator(CustomerAdministrator customerAdministrator) {
        this.customerAdministrator = customerAdministrator;
        btn_miSesion.setText(customerAdministrator.getName());
    }

}
