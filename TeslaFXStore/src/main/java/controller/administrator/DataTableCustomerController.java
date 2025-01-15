/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.administrator;

import dao.Conexion;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
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
import model.*;
import model.vehicle.TeslaVehicle;
import org.bson.types.ObjectId;

/**
 * FXML Controller class
 *
 * @author jhova
 */
public class DataTableCustomerController implements Initializable {

    // Permite mover la ventana manteniendo pulsado el cursor
    private double xOffset = 0;
    private double yOffset = 0;

    private List<Customer> customerList;
    private ObservableList<Customer> customers;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableView<Customer> tableView_customer;
    @FXML
    private TableColumn<Customer, ObjectId> tableColumn_id;
    @FXML
    private TableColumn<?, ?> tableColumn_customerName;
    @FXML
    private TableColumn<?, ?> tableColumn_country;
    @FXML
    private TableColumn<?, ?> tableColumn_age;
    @FXML
    private TableColumn<Customer, String> tableColumn_email;
    @FXML
    private TableColumn<?, ?> tableColumn_password;

    // Getters y setters
    public List<Customer> getListCustomers() {
        return customerList;
    }

    public void setListCustomers(List<Customer> listCustomers) {
        this.customerList = listCustomers;
        addCustomers();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.customers = FXCollections.observableArrayList();

        this.tableColumn_id.setCellValueFactory(new PropertyValueFactory("id"));
        this.tableColumn_customerName.setCellValueFactory(new PropertyValueFactory("CustomerName"));
        this.tableColumn_country.setCellValueFactory(new PropertyValueFactory("Country"));
        this.tableColumn_age.setCellValueFactory(new PropertyValueFactory("Age"));
        this.tableColumn_email.setCellValueFactory(new PropertyValueFactory("Email"));
        this.tableColumn_password.setCellValueFactory(new PropertyValueFactory("Password"));

        // Habilitar copiar y pegar para la columna "id"
        this.tableColumn_id.setCellFactory(column -> {
            return new TableCell<Customer, ObjectId>() {
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
        
        // Habilitar copiar y pegar para la columna "email"
        this.tableColumn_email.setCellFactory(column -> {
            return new TableCell<Customer, String>() {
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

    // Agregar todos los customers a la table
    public void addCustomers() {
        Iterator<Customer> it = customerList.iterator();
        while (it.hasNext()) {
            Customer c = it.next();
            if (!this.customers.contains(c)) {
                this.customers.add(c);
            } else {
                mensajeAlert("Customer does not exit");
            }
        }
        this.tableView_customer.setItems(customers);
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
}
