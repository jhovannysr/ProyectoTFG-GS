package controller.administrator;

import controller.AbrirVentanas;
import dao.CrudCustomer;
import dao.CrudVehicle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Customer;
import model.vehicle.Model3;
import model.vehicle.ModelS;
import model.vehicle.ModelX;
import model.vehicle.ModelY;
import model.vehicle.TeslaVehicle;
import org.bson.types.ObjectId;
import vehiclePrices.VehiclesPrices;

/**
 *
 * @author jhova
 */
public class VehicleController {

    // Formato miles, crear un DecimalFormat con el patrón de formato deseado
    DecimalFormat formatter = new DecimalFormat("#,###");

    // Instancia a la clase AbrirVentanas;
    AbrirVentanas abrirVentanas;

    private TextField field_BusquedaVehicle;
    private TextArea textArea_DatosVehicle;
    private Button button_buscarVehicle;
    private VBox vbox_camposVehicle;
    private Button button_siguienteVehicle;
    private HBox hbox_textAreaVehicle;
    private TextField field_EmailVehicle;
    private TextField field_PaintVehicle;
    private TextField field_PriceVehicle;
    private VBox vbox_Tarifa1Vehicle;
    private VBox vbox_Tarifa2PlaidDualMotorVehicle;
    private RadioButton rButton_EnhancedAutopilot;
    private RadioButton rButton_selfDrivingCapability;
    private CheckBox check_wallConnector;
    private RadioButton rButton_rearWheelDrive;
    private RadioButton rButton_DualMotor;
    private RadioButton rButton_Plaid;
    private RadioButton rButton_highAutonomy;
    private RadioButton rButton_performance;
    private ToggleGroup grupoOpciones1Modely3;
    private ToggleGroup grupoOpciones2Modelsx;
    private ToggleGroup grupoOpciones0AutonomousDriving;
    private Label label_buscadorVehicle;
    private TextField field_ModeloVehicle;
    private CrudVehicle crudVehicle;
    private CrudCustomer crudCustomer;
    private Label label_introduceDatosVehicle;
    private TextField field_idVehicle;
    private Button button_actualizarPrecioVehicle;
    private Button button_desactivarCamposVehicle;
    private VBox vBox_caracteristicasVehicle;

    public VehicleController(TextField field_BusquedaVehicle, TextArea textArea_DatosVehicle, Button button_buscarVehicle, VBox vbox_camposVehicle, Button button_siguienteVehicle, HBox hbox_textAreaVehicle, TextField field_EmailVehicle, TextField field_PaintVehicle, TextField field_PriceVehicle, VBox vbox_Tarifa1Vehicle, VBox vbox_Tarifa2PlaidDualMotorVehicle, RadioButton rButton_EnhancedAutopilot, RadioButton rButton_selfDrivingCapability, CheckBox check_wallConnector, RadioButton rButton_rearWheelDrive, RadioButton rButton_DualMotor, RadioButton rButton_Plaid, RadioButton rButton_highAutonomy, RadioButton rButton_performance, ToggleGroup grupoOpciones1Modely3, ToggleGroup grupoOpciones2Modelsx, ToggleGroup grupoOpciones0AutonomousDriving, Label label_buscadorVehicle, TextField field_ModeloVehicle, CrudVehicle crudVehicle, CrudCustomer crudCustomer, Label label_introduceDatosVehicle, TextField field_idVehicle, Button button_actualizarPrecioVehicle, Button button_desactivarCamposVehicle, VBox vBox_caracteristicasVehicle) {
        this.field_BusquedaVehicle = field_BusquedaVehicle;
        this.textArea_DatosVehicle = textArea_DatosVehicle;
        this.button_buscarVehicle = button_buscarVehicle;
        this.vbox_camposVehicle = vbox_camposVehicle;
        this.button_siguienteVehicle = button_siguienteVehicle;
        this.hbox_textAreaVehicle = hbox_textAreaVehicle;
        this.field_EmailVehicle = field_EmailVehicle;
        this.field_PaintVehicle = field_PaintVehicle;
        this.field_PriceVehicle = field_PriceVehicle;
        this.vbox_Tarifa1Vehicle = vbox_Tarifa1Vehicle;
        this.vbox_Tarifa2PlaidDualMotorVehicle = vbox_Tarifa2PlaidDualMotorVehicle;
        this.rButton_EnhancedAutopilot = rButton_EnhancedAutopilot;
        this.rButton_selfDrivingCapability = rButton_selfDrivingCapability;
        this.check_wallConnector = check_wallConnector;
        this.rButton_rearWheelDrive = rButton_rearWheelDrive;
        this.rButton_DualMotor = rButton_DualMotor;
        this.rButton_Plaid = rButton_Plaid;
        this.rButton_highAutonomy = rButton_highAutonomy;
        this.rButton_performance = rButton_performance;
        this.grupoOpciones1Modely3 = grupoOpciones1Modely3;
        this.grupoOpciones2Modelsx = grupoOpciones2Modelsx;
        this.grupoOpciones0AutonomousDriving = grupoOpciones0AutonomousDriving;
        this.label_buscadorVehicle = label_buscadorVehicle;
        this.field_ModeloVehicle = field_ModeloVehicle;
        this.crudVehicle = crudVehicle;
        this.crudCustomer = crudCustomer;
        this.label_introduceDatosVehicle = label_introduceDatosVehicle;
        this.field_idVehicle = field_idVehicle;
        this.button_actualizarPrecioVehicle = button_actualizarPrecioVehicle;
        this.button_desactivarCamposVehicle = button_desactivarCamposVehicle;
        this.vBox_caracteristicasVehicle = vBox_caracteristicasVehicle;

        abrirVentanas = new AbrirVentanas();
    }

    // Mensaje Alert
    private void mensajeAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    //@FXML
    public void handle_buscarVehicle(MouseEvent event) {
        String model = field_BusquedaVehicle.getText();
        model = buscarModelo(model);
        if (button_buscarVehicle.getText().equalsIgnoreCase("Añadir")) {
            optionAnadir(model);
        } else if (button_buscarVehicle.getText().equalsIgnoreCase("Modificar")) {
            optionModificar();
        } else if (button_buscarVehicle.getText().equalsIgnoreCase("Buscar(ID)")) {
            optionBuscarId();
        } else if (button_buscarVehicle.getText().equalsIgnoreCase("Buscar(Model)")) {
            optionBuscarModel(event);
        } else if (button_buscarVehicle.getText().equalsIgnoreCase("Buscar(Cliente)")) {
            optionBuscarCustomerVehicle(event);
        } else if (button_buscarVehicle.getText().equalsIgnoreCase("Total(Model)")) {
            optionTotalModel();
        } else if (button_buscarVehicle.getText().equalsIgnoreCase("Eliminar")) {
            optionEliminar();
        }
    }

    private void optionAnadir(String model) {
        // Recoger datos y abrir las tarifas acorde al modelo
        if (model.equalsIgnoreCase("Model Y") || model.equalsIgnoreCase("Model 3")) {
            // Poner visible los campos del vehicule y invisible la tarifa no requerida
            activarTarifa1();
            // Introducir el nombre del modelo 
            field_ModeloVehicle.setText((model.equalsIgnoreCase("model y") ? "Model Y" : (model.equalsIgnoreCase("model 3") ? "Model 3" : "")));
        } else if (model.equalsIgnoreCase("Model S") || model.equalsIgnoreCase("Model X")) {
            // Poner visible los campos del vehicule y invisible la tarifa no requerida
            activarTarifa2PlaidDualMotorVehicle();
            // Introducir el nombre del modelo 
            field_ModeloVehicle.setText((model.equalsIgnoreCase("model s") ? "Model S" : (model.equalsIgnoreCase("model x") ? "Model X" : "")));
        } else {
            mensajeAlert("No existe el módelo");
        }
    }

    private void optionModificar() {
        // Recoger datos
        ObjectId id = guardarId();
        TeslaVehicle v = crudVehicle.findVehicle(id);
        if (!(v == null) && (id != null)) {
            // Poner visible los campos
            vbox_camposVehicle.setVisible(true);
            // Mostrar los datos del Vehicle en los campos
            mostrarDatosEnCamposVehicle(v);
            // Desactivar campo email
            field_EmailVehicle.setEditable(false);
            // Introducir el nombre del modelo 
            field_ModeloVehicle.setText(buscarModelo(v.getModelo()));

            if (v.getModelo().equalsIgnoreCase("Model Y") || v.getModelo().equalsIgnoreCase("Model 3")) {
                activarTarifa1();
            } else if (v.getModelo().equalsIgnoreCase("Model S") || v.getModelo().equalsIgnoreCase("Model X")) {
                activarTarifa2PlaidDualMotorVehicle();
            }
        } else {
            mensajeAlert("Id no registrado");
        }
    }

    private void optionBuscarId() {
        ObjectId id = guardarId();
        TeslaVehicle v = crudVehicle.findVehicle(id);
        if (!(v == null) && (id != null)) {
            camposConsultaVehicle(v);
        } else {
            mensajeAlert("Id no registrado");
        }
    }

    private void optionBuscarModel(MouseEvent event) {
        String model = buscarModelo(field_BusquedaVehicle.getText());
        ArrayList<TeslaVehicle> vehicleList = crudVehicle.findVehiclesByModel(model);
        if (!vehicleList.isEmpty()) {
            abrirVentanas.abrirDataTableVehicle(event, vehicleList);
        } else {
            mensajeAlert("Modelo introducido no existe");
        }
    }

    private void optionBuscarCustomerVehicle(MouseEvent event) {
        String email = field_BusquedaVehicle.getText();
        Customer customer = crudCustomer.findCustomer(email);

        if (customer != null) {
            ArrayList<TeslaVehicle> vehicleList = crudVehicle.findVehiclesByCustomerEmail(email);
            if (!vehicleList.isEmpty()) {
                abrirVentanas.abrirDataTableVehicle(event, vehicleList);
            } else {
                if (customer != null) {
                    mensajeAlert("Correo introducido no tiene compras");
                }
            }
        } else {
            mensajeAlert("Correo introducido no existe");
        }
    }

    private void optionTotalModel() {
        String model = buscarModelo(field_BusquedaVehicle.getText());
        String cantidad = ((Long) crudVehicle.countVehiclesByModel(model)).toString();
        mensajeAlert("Cantidad total de vehiculos comprados: " + cantidad + " registros");
    }

    private void optionEliminar() {
        ObjectId id = guardarId();
        TeslaVehicle v = crudVehicle.findVehicle(id);
        if (!(v == null) && (id != null)) {
            crudVehicle.deleteVehicle(id);
            mensajeAlert("Vehiculo eliminado");
            vaciarTodoVehicle();
        } else {
            mensajeAlert("Id no registrado");
        }
        vaciarTodoVehicle();
    }

    //@FXML
    public void handle_anadirVehicle(MouseEvent event) {
        // Vaciar todo
        vaciarTodoVehicle();
        // Activar componentes, field buscar, button buscar
        activarBuscarComponentesVehicle();

        // Textos
        button_buscarVehicle.setText("Añadir");
        button_siguienteVehicle.setText("Añadir");
        label_buscadorVehicle.setText("Introduce el Modelo(ModelY/Model3/ModelS/ModelX)");
        label_introduceDatosVehicle.setText("Introduce los datos");
    }

    //@FXML
    public void handle_modificarVehicle(MouseEvent event) {
        // Vaciar todo
        vaciarTodoVehicle();
        // Activar componentes, field buscar, button buscar
        activarBuscarComponentesVehicle();
        // Cambiar texto del button buscar
        button_buscarVehicle.setText("Modificar");

        // Textos
        button_siguienteVehicle.setText("Modificar");
        label_buscadorVehicle.setText("Introduce el id del vehiculo");
        label_introduceDatosVehicle.setText("Introduce los datos");
    }

    //@FXML
    public void handle_consultaVehicle(MouseEvent event) {
        // Vaciar todo
        vaciarTodoVehicle();
        // Activar componentes, field buscar, button buscar
        activarBuscarComponentesVehicle();

        // Textos
        button_buscarVehicle.setText("Buscar(ID)");
        label_buscadorVehicle.setText("Introduce el id del vehiculo");
        label_introduceDatosVehicle.setText("Mis datos");
    }

    //@FXML
    public void handle_mostrarListaVehicle(MouseEvent event) {
        // Vaciar todo
        vaciarTodoVehicle();
        hbox_textAreaVehicle.setVisible(true);
        textArea_DatosVehicle.setText(crudVehicle.listVehicles());
        textArea_DatosVehicle.setEditable(false);

        // Mostrar Customers en la interfaz grafica
        ArrayList<TeslaVehicle> vehicleList = crudVehicle.listVehicles2();
        abrirVentanas.abrirDataTableVehicle(event, vehicleList);
    }

    //@FXML
    public void handle_eliminarVehicle(MouseEvent event) {
        // Vaciar todo
        vaciarTodoVehicle();
        // Activar componentes, field buscar, button buscar
        activarBuscarComponentesVehicle();
        // Cambiar texto del button buscar
        button_buscarVehicle.setText("Eliminar");

        // Textos
        label_buscadorVehicle.setText("Introduce el id del vehiculo");
    }

    //@FXML
    public void handle_AtrasVehicle(MouseEvent event) {
        vaciarTodoVehicle();
        abrirVentanas.cerrarDataTableVehicle();
    }

    public void handle_consultaModelVehicle(MouseEvent event) {
        // Vaciar todo
        vaciarTodoVehicle();
        // Activar componentes, field buscar, button buscar
        activarBuscarComponentesVehicle();

        // Textos
        button_buscarVehicle.setText("Buscar(Model)");
        label_buscadorVehicle.setText("Introduce el Modelo(ModelY/Model3/ModelS/ModelX)");
    }

    public void handle_consultaCustomerVehicle(MouseEvent event) {
        // Vaciar todo
        vaciarTodoVehicle();
        // Activar componentes, field buscar, button buscar
        activarBuscarComponentesVehicle();

        // Textos
        button_buscarVehicle.setText("Buscar(Cliente)");
        label_buscadorVehicle.setText("Introduce el correo del cliente");
    }

    public void handle_totalVehicles(MouseEvent event) {
        String cantidad = ((Long) crudVehicle.countTotalVehicles()).toString();
        mensajeAlert("Cantidad total de vehiculos comprados: " + cantidad + " registros");
    }

    public void handle_totalModelVehicles(MouseEvent event) {
        // Vaciar todo
        vaciarTodoVehicle();
        // Activar componentes, field buscar, button buscar
        activarBuscarComponentesVehicle();

        // Textos
        button_buscarVehicle.setText("Total(Model)");
        label_buscadorVehicle.setText("Introduce el Modelo(ModelY/Model3/ModelS/ModelX)");
    }

    //@FXML
    public void handle_siguienteVehicle(MouseEvent event) {
        boolean completado = false;
        if (button_siguienteVehicle.getText().equalsIgnoreCase("Añadir")) {
            if (!(addVehicle(field_BusquedaVehicle.getText()) == null)) {
                completado = true;
            }
        } else if (button_siguienteVehicle.getText().equalsIgnoreCase("Modificar")) {
            modificarVehicle();
            completado = true;
        }

        if (completado) {
            // Vaciar campos    
            vaciarCamposVehicle();
        }
    }

    //@FXML
    public void handle_actualizarPrecioVehicle(MouseEvent event) {
        // En caso de que sea Añadir
        if (field_BusquedaVehicle.getText().equalsIgnoreCase("ModelY")
                || field_BusquedaVehicle.getText().equalsIgnoreCase("Model3")
                || field_BusquedaVehicle.getText().equalsIgnoreCase("ModelS")
                || field_BusquedaVehicle.getText().equalsIgnoreCase("ModelX")) {
            field_PriceVehicle.setText(formatter.format(updatePrices(field_BusquedaVehicle.getText())) + "€");
        } // En caso de que sea Modificar
        else if (field_ModeloVehicle.getText().equalsIgnoreCase("Model Y")
                || field_ModeloVehicle.getText().equalsIgnoreCase("Model 3")
                || field_ModeloVehicle.getText().equalsIgnoreCase("Model S")
                || field_ModeloVehicle.getText().equalsIgnoreCase("Model X")) {
            field_PriceVehicle.setText(formatter.format(updatePrices(field_ModeloVehicle.getText())) + "€");
        }
    }

    //@FXML
    public void handle_desactivarCamposVehicle(MouseEvent event) {
        rButton_EnhancedAutopilot.setSelected(false);
        rButton_selfDrivingCapability.setSelected(false);
        check_wallConnector.setSelected(false);
        // para modelos S y X
        rButton_DualMotor.setSelected(true);
        rButton_Plaid.setSelected(false);
        // para modelos Y y 3
        rButton_rearWheelDrive.setSelected(true);
        rButton_highAutonomy.setSelected(false);
        rButton_performance.setSelected(false);
    }

    // Vaciar todo Vehicle
    public void vaciarTodoVehicle() {
        // Campos consulta por defecto
        camposConsultaPorDefecto();
        // Vaciar campos
        vaciarCamposVehicle();
        // Desactivar Buscar componentes
        desactivarBuscarComponentesVehicle();
        // Poner invisible campos
        vbox_camposVehicle.setVisible(false);
        // Poner invisible textArea
        hbox_textAreaVehicle.setVisible(false);
        // Desactivar field buscar
        field_BusquedaVehicle.setEditable(false);
        // Cambiar texto del button
        button_siguienteVehicle.setText("Siguiente");
        // Poner invisible tarifa 1, Model X, S
        vbox_Tarifa1Vehicle.setVisible(false);
        // Poner invisible tarifa 1, Model Y, 3
        vbox_Tarifa2PlaidDualMotorVehicle.setVisible(false);
        // Poner texto del label del buscador por defecto
        label_buscadorVehicle.setText("Introduce el Modelo(ModelY/Model3/ModelS/ModelX)");
        // Quitar texto del modelo
        field_ModeloVehicle.setText("");
        // Activar campo email
        field_EmailVehicle.setEditable(true);
    }

    // Vaciar campos
    private void vaciarCamposVehicle() {
        // Campos por defecto de vehicle
        field_BusquedaVehicle.setText("");
        field_EmailVehicle.setText("");
        field_PaintVehicle.setText("");
        field_PriceVehicle.setText("");
        rButton_EnhancedAutopilot.setSelected(false);
        rButton_selfDrivingCapability.setSelected(false);
        check_wallConnector.setSelected(false);
        // para modelos S y X
        rButton_DualMotor.setSelected(true);
        rButton_Plaid.setSelected(false);
        // para modelos Y y 3
        rButton_rearWheelDrive.setSelected(true);
        rButton_highAutonomy.setSelected(false);
        rButton_performance.setSelected(false);
    }

    // Desactivar campos, para modificar y consulta
    private void desactivarBuscarComponentesVehicle() {
        // Poner invisible el Button buscar
        button_buscarVehicle.setVisible(false);

        // Desactivar field buscar
        field_BusquedaVehicle.setEditable(false);
    }

    // Añadir customer
    private TeslaVehicle addVehicle(String model) {
        if (crudCustomer.findCustomer(field_EmailVehicle.getText()) == null) {
            mensajeAlert("Correo no registrado");
            return null;
        }
        if (model.equalsIgnoreCase("ModelY")) {
            // Recoger datos
            ModelY vehicle = (ModelY) recogerDatosCamposVehicle(model);
            // Crear objeto Customer
            crudVehicle.saveTeslaVehicle(vehicle);
            // Alert, nuevo cliente añadido
            mensajeAlert("Nuevo Vehiculo añadido");
            // Vaciar todo
            vaciarTodoVehicle();
            return vehicle;
        } else if (model.equalsIgnoreCase("Model3")) {
            // Recoger datos
            Model3 vehicle = (Model3) recogerDatosCamposVehicle(model);
            // Crear objeto Customer
            crudVehicle.saveTeslaVehicle(vehicle);
            // Alert, nuevo cliente añadido
            mensajeAlert("Nuevo Vehiculo añadido");
            // Vaciar todo
            vaciarTodoVehicle();
            return vehicle;
        } else if (model.equalsIgnoreCase("ModelS")) {
            // Recoger datos
            ModelS vehicle = (ModelS) recogerDatosCamposVehicle(model);
            // Crear objeto Customer
            crudVehicle.saveTeslaVehicle(vehicle);
            // Alert, nuevo cliente añadido
            mensajeAlert("Nuevo Vehiculo añadido");
            // Vaciar todo
            vaciarTodoVehicle();
            return vehicle;
        } else if (model.equalsIgnoreCase("ModelX")) {
            // Recoger datos
            ModelX vehicle = (ModelX) recogerDatosCamposVehicle(model);
            // Crear objeto Customer
            crudVehicle.saveTeslaVehicle(vehicle);
            // Alert, nuevo cliente añadido
            mensajeAlert("Nuevo Vehiculo añadido");
            // Vaciar todo
            vaciarTodoVehicle();
            return vehicle;
        }

        return null;
    }

    private double updatePrices(String model) {
        if (model.equalsIgnoreCase("ModelY") || model.equalsIgnoreCase("Model Y")) {
            VehiclesPrices vehicleModelYPrices = new VehiclesPrices(
                    rButton_EnhancedAutopilot.isSelected(),
                    rButton_selfDrivingCapability.isSelected(),
                    check_wallConnector.isSelected(),
                    rButton_rearWheelDrive.isSelected(),
                    rButton_highAutonomy.isSelected(),
                    rButton_performance.isSelected()
            );
            vehicleModelYPrices.updatePriceModelY();
            return vehicleModelYPrices.finalPriceModelY3();
        } else if (model.equalsIgnoreCase("Model3") || model.equalsIgnoreCase("Model 3")) {
            VehiclesPrices vehicleModel3Prices = new VehiclesPrices(
                    rButton_EnhancedAutopilot.isSelected(),
                    rButton_selfDrivingCapability.isSelected(),
                    check_wallConnector.isSelected(),
                    rButton_rearWheelDrive.isSelected(),
                    rButton_highAutonomy.isSelected(),
                    rButton_performance.isSelected()
            );
            vehicleModel3Prices.updatePriceModel3();
            return vehicleModel3Prices.finalPriceModelY3();
        } else if (model.equalsIgnoreCase("ModelS") || model.equalsIgnoreCase("Model S")) {
            VehiclesPrices vehicleModelSPrices = new VehiclesPrices(
                    rButton_EnhancedAutopilot.isSelected(),
                    rButton_selfDrivingCapability.isSelected(),
                    check_wallConnector.isSelected(),
                    rButton_DualMotor.isSelected(),
                    rButton_Plaid.isSelected()
            );
            vehicleModelSPrices.updatePriceModelS();
            return vehicleModelSPrices.finalPriceModelSX();
        } else if (model.equalsIgnoreCase("ModelX") || model.equalsIgnoreCase("Model X")) {
            VehiclesPrices vehicleModelXPrices = new VehiclesPrices(
                    rButton_EnhancedAutopilot.isSelected(),
                    rButton_selfDrivingCapability.isSelected(),
                    check_wallConnector.isSelected(),
                    rButton_DualMotor.isSelected(),
                    rButton_Plaid.isSelected()
            );
            vehicleModelXPrices.updatePriceModelX();
            return vehicleModelXPrices.finalPriceModelSX();
        }
        return 0;
    }

    // Recoger datos de los campos
    private TeslaVehicle recogerDatosCamposVehicle(String model) {
        ObjectId id = null;
        boolean bModificar = false;
        // Campo buscador
        if (button_buscarVehicle.getText().equalsIgnoreCase("Modificar")) {
            id = new ObjectId(field_BusquedaVehicle.getText());
            bModificar = true;
        }

        // Campos por defecto
        String email = field_EmailVehicle.getText();
        String paint = field_PaintVehicle.getText();
        boolean enhancedAutopilot = rButton_EnhancedAutopilot.isSelected();
        boolean fullSelfDrivingCapability = rButton_selfDrivingCapability.isSelected();
        boolean wallConnectorCharge = check_wallConnector.isSelected();
        double price = updatePrices(model);

        // Modelos de vehiculo
        if (model.equalsIgnoreCase("ModelY") || model.equalsIgnoreCase("Model Y")) {
            boolean bRearWheelDrive = rButton_rearWheelDrive.isSelected();
            boolean bHighAutonomy = rButton_highAutonomy.isSelected();
            boolean bPerformance = rButton_performance.isSelected();
            if (bModificar) {
                bModificar = false;
                return new ModelY(bRearWheelDrive, bHighAutonomy, bPerformance, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, "Model Y");
            } else {
                return new ModelY(bRearWheelDrive, bHighAutonomy, bPerformance, email, paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, "Model Y");
            }
        } else if (model.equalsIgnoreCase("Model3") || model.equalsIgnoreCase("Model 3")) {
            System.out.println("Recoger datos campos model 3");
            boolean bRearWheelDrive = rButton_rearWheelDrive.isSelected();
            boolean bHighAutonomy = rButton_highAutonomy.isSelected();
            boolean bPerformance = rButton_performance.isSelected();
            if (bModificar) {
                bModificar = false;
                return new Model3(bRearWheelDrive, bHighAutonomy, bPerformance, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, "Model 3");
            } else {
                return new Model3(bRearWheelDrive, bHighAutonomy, bPerformance, email, paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, "Model 3");
            }
        } else if (model.equalsIgnoreCase("ModelS") || model.equalsIgnoreCase("Model S")) {
            boolean bDualMotor = rButton_DualMotor.isSelected();
            boolean bPlaid = rButton_Plaid.isSelected();
            if (bModificar) {
                bModificar = false;
                return new ModelS(bDualMotor, bPlaid, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, "Model S");
            } else {
                return new ModelS(bDualMotor, bPlaid, email, paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, "Model S");
            }
        } else if (model.equalsIgnoreCase("ModelX") || model.equalsIgnoreCase("Model X")) {
            boolean bDualMotor = rButton_DualMotor.isSelected();
            boolean bPlaid = rButton_Plaid.isSelected();
            if (bModificar) {
                bModificar = false;
                return new ModelX(bDualMotor, bPlaid, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, "Model X");
            } else {
                return new ModelX(bDualMotor, bPlaid, email, paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, "Model X");
            }
        }

        return null;
    }

    // Activar campos, para modificar y consulta
    private void activarBuscarComponentesVehicle() {
        // Poner visible el Button buscar
        button_buscarVehicle.setVisible(true);

        // Activar field buscar
        field_BusquedaVehicle.setEditable(true);
    }

    // Mostrar datos en los campos
    private void mostrarDatosEnCamposVehicle(TeslaVehicle vehicle) {
        if (vehicle instanceof ModelY) {
            ModelY modelY = (ModelY) vehicle;
            field_EmailVehicle.setText(modelY.getEmail());
            field_PaintVehicle.setText(modelY.getPaint());
            field_PriceVehicle.setText((formatter.format(modelY.getPrice()) + "€"));
            rButton_EnhancedAutopilot.setSelected(modelY.isEnhancedAutopilot());
            rButton_selfDrivingCapability.setSelected(modelY.isFullSelfDrivingCapability());
            check_wallConnector.setSelected(modelY.isWallConnectorCharge());
            rButton_rearWheelDrive.setSelected(modelY.isRearWheelDrive());
            rButton_highAutonomy.setSelected(modelY.isHighAutonomy());
            rButton_performance.setSelected(modelY.isPerformance());
        } else if (vehicle instanceof Model3) {
            Model3 model3 = (Model3) vehicle;
            field_EmailVehicle.setText(model3.getEmail());
            field_PaintVehicle.setText(model3.getPaint());
            field_PriceVehicle.setText((formatter.format(model3.getPrice()) + "€"));
            rButton_EnhancedAutopilot.setSelected(model3.isEnhancedAutopilot());
            rButton_selfDrivingCapability.setSelected(model3.isFullSelfDrivingCapability());
            check_wallConnector.setSelected(model3.isWallConnectorCharge());
            rButton_rearWheelDrive.setSelected(model3.isRearWheelDrive());
            rButton_highAutonomy.setSelected(model3.isHighAutonomy());
            rButton_performance.setSelected(model3.isPerformance());
        } else if (vehicle instanceof ModelS) {
            ModelS modelS = (ModelS) vehicle;
            field_EmailVehicle.setText(modelS.getEmail());
            field_PaintVehicle.setText(modelS.getPaint());
            field_PriceVehicle.setText((formatter.format(modelS.getPrice()) + "€"));
            rButton_EnhancedAutopilot.setSelected(modelS.isEnhancedAutopilot());
            rButton_selfDrivingCapability.setSelected(modelS.isFullSelfDrivingCapability());
            check_wallConnector.setSelected(modelS.isWallConnectorCharge());
            rButton_DualMotor.setSelected(modelS.isDualMotor());
            rButton_Plaid.setSelected(modelS.isPlaid());
        } else if (vehicle instanceof ModelX) {
            ModelX modelX = (ModelX) vehicle;
            field_EmailVehicle.setText(modelX.getEmail());
            field_PaintVehicle.setText(modelX.getPaint());
            field_PriceVehicle.setText((formatter.format(modelX.getPrice()) + "€"));
            rButton_EnhancedAutopilot.setSelected(modelX.isEnhancedAutopilot());
            rButton_selfDrivingCapability.setSelected(modelX.isFullSelfDrivingCapability());
            check_wallConnector.setSelected(modelX.isWallConnectorCharge());
            rButton_DualMotor.setSelected(modelX.isDualMotor());
            rButton_Plaid.setSelected(modelX.isPlaid());
        }
    }

    // Modificar vehicle
    private void modificarVehicle() {
        // Crear objeto Customer con los nuevos datos en los campos
        String model = field_ModeloVehicle.getText();
        crudVehicle.updateVehicle(recogerDatosCamposVehicle(model));

        // Alert, nuevo cliente añadido
        mensajeAlert("Vehiculo modificado");

        // Desactivar todo
        vaciarTodoVehicle();
    }

    private void activarTarifa1() {
        vbox_camposVehicle.setVisible(true);
        vbox_Tarifa1Vehicle.setVisible(true);
        vbox_Tarifa2PlaidDualMotorVehicle.setVisible(false);
    }

    private void activarTarifa2PlaidDualMotorVehicle() {
        vbox_camposVehicle.setVisible(true);
        vbox_Tarifa2PlaidDualMotorVehicle.setVisible(true);
        vbox_Tarifa1Vehicle.setVisible(false);
    }

    private ObjectId guardarId() {
        ObjectId id = null;
        try {
            id = new ObjectId(field_BusquedaVehicle.getText());
        } catch (Exception e) {
            id = null;
        }

        return id;
    }

    private String buscarModelo(String model) {
        model = model.trim();

        String finalModel = (model.equalsIgnoreCase("modelx") || model.equalsIgnoreCase("model x"))
                ? "Model X"
                : (model.equalsIgnoreCase("model3") || model.equalsIgnoreCase("model 3"))
                ? "Model 3"
                : (model.equalsIgnoreCase("modely") || model.equalsIgnoreCase("model y"))
                ? "Model Y"
                : (model.equalsIgnoreCase("models") || model.equalsIgnoreCase("model s"))
                ? "Model S"
                : "";

        return finalModel;
    }

    private void habilitarCamposConsulta() {
        // Otros
        field_idVehicle.setManaged(true);
        vbox_camposVehicle.setVisible(true);
        vBox_caracteristicasVehicle.setVisible(true);
        vbox_Tarifa1Vehicle.setVisible(false);
        vbox_Tarifa2PlaidDualMotorVehicle.setVisible(false);

        // Editable
        field_EmailVehicle.setEditable(false);
        field_PaintVehicle.setEditable(false);

        // Buttons
        button_siguienteVehicle.setVisible(false);
        button_actualizarPrecioVehicle.setVisible(false);
        button_desactivarCamposVehicle.setVisible(false);

        // Deshabilitar tarifas y otras caracteristicas
        vBox_caracteristicasVehicle.setDisable(true);
        vbox_Tarifa1Vehicle.setDisable(true);
        vbox_Tarifa2PlaidDualMotorVehicle.setDisable(true);
    }

    private void camposConsultaPorDefecto() {
        field_idVehicle.setManaged(false);
        button_siguienteVehicle.setVisible(true);
        button_actualizarPrecioVehicle.setVisible(true);
        button_desactivarCamposVehicle.setVisible(true);

        // Editable
        field_PaintVehicle.setEditable(true);

        // Tarirfas
        vBox_caracteristicasVehicle.setDisable(false);
        vbox_Tarifa2PlaidDualMotorVehicle.setDisable(false);
        vbox_Tarifa1Vehicle.setDisable(false);
        vbox_Tarifa2PlaidDualMotorVehicle.setVisible(false);
        vbox_Tarifa1Vehicle.setVisible(true);
    }

    private void camposConsultaVehicle(TeslaVehicle vehicle) {
        System.out.println("1");
        // Deshabilitar datos
        habilitarCamposConsulta();

        // Mostrar datos comunes
        field_idVehicle.setText("Id: " + vehicle.getId());
        field_ModeloVehicle.setText("Modelo: " + vehicle.getModelo());
        field_EmailVehicle.setText("Email: " + vehicle.getEmail());
        field_PaintVehicle.setText("Pintura: " + vehicle.getPaint());
        field_PriceVehicle.setText(formatter.format(vehicle.getPrice()) + "€");

        rButton_EnhancedAutopilot.setSelected(vehicle.isEnhancedAutopilot());
        rButton_selfDrivingCapability.setSelected(vehicle.isFullSelfDrivingCapability());
        check_wallConnector.setSelected(vehicle.isWallConnectorCharge());

        // Tarifas según el modelo
        if (vehicle.getModelo().equalsIgnoreCase("Model X") || vehicle.getModelo().equalsIgnoreCase("Model S")) {
            vbox_Tarifa2PlaidDualMotorVehicle.setVisible(true);

            if (vehicle instanceof ModelX) {
                ModelX modelx = (ModelX) vehicle;
                rButton_Plaid.setSelected(modelx.isPlaid());
                rButton_DualMotor.setSelected(modelx.isDualMotor());
            } else if (vehicle instanceof ModelS) {
                ModelS models = (ModelS) vehicle;
                rButton_Plaid.setSelected(models.isPlaid());
                rButton_DualMotor.setSelected(models.isDualMotor());
            }

        } else if (vehicle.getModelo().equalsIgnoreCase("Model Y") || vehicle.getModelo().equalsIgnoreCase("Model 3")) {
            vbox_Tarifa1Vehicle.setVisible(true);

            if (vehicle instanceof ModelY) {
                ModelY modely = (ModelY) vehicle;
                rButton_rearWheelDrive.setSelected(modely.isRearWheelDrive());
                rButton_highAutonomy.setSelected(modely.isHighAutonomy());
                rButton_performance.setSelected(modely.isPerformance());
            } else if (vehicle instanceof Model3) {
                Model3 model3 = (Model3) vehicle;
                rButton_rearWheelDrive.setSelected(model3.isRearWheelDrive());
                rButton_highAutonomy.setSelected(model3.isHighAutonomy());
                rButton_performance.setSelected(model3.isPerformance());
            }
        }
        System.out.println("2");
    }
}
