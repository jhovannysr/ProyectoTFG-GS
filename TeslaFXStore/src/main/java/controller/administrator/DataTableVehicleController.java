package controller.administrator;

import dao.Conexion;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.vehicle.*;
import org.bson.types.ObjectId;

/**
 * FXML Controller class
 *
 * @author jhova
 */
public class DataTableVehicleController implements Initializable {

    // Permite mover la ventana manteniendo pulsado el cursor
    private double xOffset = 0;
    private double yOffset = 0;

    private List<TeslaVehicle> vehicleList;
    private ObservableList<TeslaVehicle> vehicles;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableView<TeslaVehicle> tableView_vehicle;

    @FXML
    private TableColumn<TeslaVehicle, ObjectId> tableColumn_id;
    @FXML
    private TableColumn<?, ?> tableColumn_paint;
    @FXML
    private TableColumn<TeslaVehicle, Boolean> tableColumn_enhancedAutopilot;
    @FXML
    private TableColumn<TeslaVehicle, Boolean> tableColumn_fullSelfDriving;
    @FXML
    private TableColumn<TeslaVehicle, Boolean> tableColumn_wallConnector;
    @FXML
    private TableColumn<TeslaVehicle, Boolean> tableColumn_dualMotor;
    @FXML
    private TableColumn<TeslaVehicle, Boolean> tableColumn_plaid;
    @FXML
    private TableColumn<TeslaVehicle, String> tableColumn_price;
    @FXML
    private TableColumn<?, ?> tableColumn_model;
    @FXML
    private TableColumn<TeslaVehicle, String> tableColumn_email;
    @FXML
    private TableColumn<TeslaVehicle, Boolean> tableColumn_rearWheelDrive;
    @FXML
    private TableColumn<TeslaVehicle, Boolean> tableColumn_highAutonomy;
    @FXML
    private TableColumn<TeslaVehicle, Boolean> tableColumn_performance;

    // Getters y setters
    public List<TeslaVehicle> getListTeslaVehicles() {
        return vehicleList;
    }

    public void setVehicleList(List<TeslaVehicle> vehicleList) {
        this.vehicleList = vehicleList;
        addVehicles();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.vehicles = FXCollections.observableArrayList();

        this.tableColumn_id.setCellValueFactory(new PropertyValueFactory("id"));
        this.tableColumn_email.setCellValueFactory(new PropertyValueFactory("email"));
        this.tableColumn_paint.setCellValueFactory(new PropertyValueFactory("paint"));
        this.tableColumn_enhancedAutopilot.setCellValueFactory(new PropertyValueFactory("enhancedAutopilot"));
        this.tableColumn_fullSelfDriving.setCellValueFactory(new PropertyValueFactory("fullSelfDrivingCapability"));
        this.tableColumn_wallConnector.setCellValueFactory(new PropertyValueFactory("wallConnectorCharge"));
        this.tableColumn_model.setCellValueFactory(new PropertyValueFactory("modelo"));
        this.tableColumn_price.setCellValueFactory(new PropertyValueFactory("price"));

        // Estilos para celdas comunes
        setEmptyCellHighlight(tableColumn_enhancedAutopilot);
        setEmptyCellHighlight(tableColumn_fullSelfDriving);
        setEmptyCellHighlight(tableColumn_wallConnector);

        // Agregar columnas específicas para ModelX y ModelS
        this.tableColumn_price.setCellValueFactory(cellData -> {
            TeslaVehicle vehicle = (TeslaVehicle) cellData.getValue();
            if (vehicle != null) 
                return new SimpleStringProperty(formatPrice(vehicle.getPrice()));
             else
            return null; // En caso de que el vehículo sea nulo
        });

        // Agregar columnas específicas para ModelX y ModelS
        this.tableColumn_dualMotor.setCellValueFactory(cellData -> {
            TeslaVehicle vehicle = (TeslaVehicle) cellData.getValue();
            if (vehicle instanceof ModelX) {
                ModelX mx = (ModelX) vehicle;
                return new SimpleBooleanProperty((mx).isDualMotor()).asObject();
            } else if (vehicle instanceof ModelS) {
                return new SimpleBooleanProperty(((ModelS) vehicle).isDualMotor()).asObject();
            } else {
                return null; 
            }
        });
        setEmptyCellHighlight(tableColumn_dualMotor);

        this.tableColumn_plaid.setCellValueFactory(cellData -> {
            TeslaVehicle vehicle = cellData.getValue();
            if (vehicle instanceof ModelX) {
                return new SimpleBooleanProperty(((ModelX) vehicle).isPlaid()).asObject();
            } else if (vehicle instanceof ModelS) {
                return new SimpleBooleanProperty(((ModelS) vehicle).isPlaid()).asObject();
            } else {
                return null; // Devolver null si no tiene el atributo DualMotor (vacío)
            }
        });
        setEmptyCellHighlight(tableColumn_plaid);

        // Columnas específicas para Model3 y ModelY
        this.tableColumn_rearWheelDrive.setCellValueFactory(cellData -> {
            TeslaVehicle vehicle = cellData.getValue();
            if (vehicle instanceof Model3) {
                return new SimpleBooleanProperty(((Model3) vehicle).isRearWheelDrive()).asObject();
            } else if (vehicle instanceof ModelY) {
                return new SimpleBooleanProperty(((ModelY) vehicle).isRearWheelDrive()).asObject();
            } else {
                return null; // Devolver null si no tiene el atributo DualMotor (vacío)
            }
        });
        setEmptyCellHighlight(tableColumn_rearWheelDrive);

        this.tableColumn_highAutonomy.setCellValueFactory(cellData -> {
            TeslaVehicle vehicle = cellData.getValue();
            if (vehicle instanceof Model3) {
                return new SimpleBooleanProperty(((Model3) vehicle).isHighAutonomy()).asObject();
            } else if (vehicle instanceof ModelY) {
                return new SimpleBooleanProperty(((ModelY) vehicle).isHighAutonomy()).asObject();
            } else {
                return null; // Devolver null si no tiene el atributo DualMotor (vacío)
            }
        });
        setEmptyCellHighlight(tableColumn_highAutonomy);

        this.tableColumn_performance.setCellValueFactory(cellData -> {
            TeslaVehicle vehicle = cellData.getValue();
            if (vehicle instanceof Model3) {
                return new SimpleBooleanProperty(((Model3) vehicle).isPerformance()).asObject();
            } else if (vehicle instanceof ModelY) {
                return new SimpleBooleanProperty(((ModelY) vehicle).isPerformance()).asObject();
            } else {
                return null; // Devolver null si no tiene el atributo DualMotor (vacío)
            }
        });
        setEmptyCellHighlight(tableColumn_performance);

        // Habilitar copiar y pegar para la columna "id"
        this.tableColumn_id.setCellFactory(column -> {
            return new TableCell<TeslaVehicle, ObjectId>() {
                private TextField textField;

                @Override
                protected void updateItem(ObjectId item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        if (textField == null) {
                            textField = new TextField();
                            textField.setEditable(false); // Evita la edición directa
                        }
                        textField.setText(item.toString()); // Establece el valor de la celda en el TextField
                        setGraphic(textField);
                        setText(null); // Evita que se dibuje el texto de forma predeterminada
                    }
                }
            };
        });

        // Habilitar copiar y pegar para la columna "id"
        this.tableColumn_email.setCellFactory(column -> {
            return new TableCell<TeslaVehicle, String>() {
                private TextField textField;

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        if (textField == null) {
                            textField = new TextField();
                            textField.setEditable(false); // Evita la edición directa
                        }
                        textField.setText(item.toString()); // Establece el valor de la celda en el TextField
                        setGraphic(textField);
                        setText(null); // Evita que se dibuje el texto de forma predeterminada
                    }
                }
            };
        });

    }

    // Agregar todos los vehicles a la table
    public void addVehicles() {
        Iterator<TeslaVehicle> it = vehicleList.iterator();
        while (it.hasNext()) {
            TeslaVehicle v = it.next();
            if (!this.vehicles.contains(v)) {
                this.vehicles.add(v);
            } else {
                mensajeAlert("TeslaVehicle does not exit");
            }
        }
        this.tableView_vehicle.setItems(vehicles);
    }

    // Mensaje tipo Alert
    private void mensajeAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Desplaza la ventana según la posición que tenga la escena 
    @FXML
    private void handleMouseDragged(MouseEvent event) {
        // Obtiene la ventana actual (Stage) y la mueve a la nueva posición
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    // Almacena la posición de la escena
    @FXML
    private void handleMousePressed(MouseEvent event) {
        // Captura la posición inicial del mouse en la ventana
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    // Minimiza la ventana
    @FXML
    void minimizarVentana(MouseEvent event) {
        // Obtener la Stage actual a partir del evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Minimizar la ventana
        stage.setIconified(true);
    }

    // Cierra la ventana
    @FXML
    void cerrarVentana(MouseEvent event) {
        // Obtener la Stage actual a partir del evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Cerrar la ventana
        stage.close();
    }

    // Método para configurar la fábrica de celdas y resaltar celdas vacías
    private <T> void setEmptyCellHighlight(TableColumn<TeslaVehicle, T> column) {
        column.setCellFactory(col -> new TableCell<TeslaVehicle, T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-background-color: #7994ff;"); // Estilo para celdas vacías
                } else {
                    setText(item.toString());
                    // Cambiar el color del texto basado en el contenido
                    if ("true".equalsIgnoreCase(item.toString())) {
                        setStyle("-fx-text-fill: blue;"); // Texto azul para "true"
                    } else if ("false".equalsIgnoreCase(item.toString())) {
                        setStyle("-fx-text-fill: red;"); // Texto rojo para "false"
                    } else {
                        setStyle(""); // Restablecer estilo para otros valores
                    }
                }
            }
        });
    }

    // Formato de precio
    private String formatPrice(Double price) {
        if (price == null) {
            return ""; // Manejo de valores nulos
        }

        // Crear un formato para el precio
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.0€");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(','); // Usar coma como separador decimal
        symbols.setGroupingSeparator('.'); // Usar punto como separador de miles
        decimalFormat.setDecimalFormatSymbols(symbols);

        // Devolver el precio formateado
        return decimalFormat.format(price);
    }

}
