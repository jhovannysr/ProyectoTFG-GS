/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dao.Conexion;
import dao.CrudCustomer;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Customer;

/**
 * FXML Controller class
 *
 * @author jhova
 */
public class MisDatosController implements Initializable {

    // Permite mover la ventana manteniendo pulsado el cursor
    private double xOffset = 0;
    private double yOffset = 0;

    private CrudCustomer crudCustomer;
    private Customer customer;

    private boolean bValidarPassword = false;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField textfield_campoNombre;
    @FXML
    private TextField textfield_campoPais;
    @FXML
    private TextField textfield_campoEdad;
    @FXML
    private TextField textfield_campoCorreo;
    @FXML
    private TextField textfield_campoPassword;
    @FXML
    private Button btn_Modificar;
    @FXML
    private Label label_tooltip;
    @FXML
    private ImageView img_ToolTip;
    @FXML
    private Label label_passwordIncorrecto;
    @FXML
    private PasswordField textfield_campoHidePassword;
    @FXML
    private ImageView img_eyeNoVisible;
    @FXML
    private ImageView img_eyeVisible;
    @FXML
    private Label label_misDatos;
    @FXML
    private Rectangle overlay;
    @FXML
    private ImageView img_loading;
    @FXML
    private Label label_loading;
    @FXML
    private ImageView img_confirmacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Crud customer
        crudCustomer = new CrudCustomer();

        // Radius de esquinas
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

    // Minimiar ventana
    @FXML
    private void minimiarVentana(MouseEvent event) {
        // Obtener la Stage actual a partir del evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Minimizar la ventana
        stage.setIconified(true);
    }

    // Cerrar ventana
    @FXML
    private void cerrarVentana(MouseEvent event) {
        // Obtener la Stage actual a partir del evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        // Cerrar la ventana
        stage.close();
        // Cerrar conexión
        Conexion.close();
    }

    // Desactivar el focus del campo al dar clic fuera
    private void desactivarFocusField(MouseEvent event) {
        textfield_campoPassword.setFocusTraversable(false);
        textfield_campoCorreo.setFocusTraversable(false);
        mainPane.requestFocus();
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

    // Cambiar de ventana a la principal Home Sin Sesion
    @FXML
    private void abrirHomeConSesion(MouseEvent event) {
        new AbrirVentanas().abrirHomeConSesion(event, customer);
    }

    // Abrir HomeConSesion
    @FXML
    private void updateCustomer(MouseEvent event) {
        if (btn_Modificar.getText().equals("Modificar")) {
            // Mostrar componentes
            textfield_campoHidePassword.setDisable(false);
            textfield_campoPassword.setDisable(false);
            img_eyeVisible.setVisible(true);
            img_eyeNoVisible.setVisible(false);
            mostrarToolTip();
            btn_Modificar.setText("Siguiente");
            label_misDatos.setText("Confirmación de usuario");
        } else if (btn_Modificar.getText().equals("Siguiente")) {
            mostrarLoading(event);
        } else if (btn_Modificar.getText().equals("Guardar")) {
            mostrarLoading(event);
        }
    }

    private void optionUpdate(MouseEvent event) {

    }

    private void validarContraseña(MouseEvent event) {

        String password = textoPassword();
        customer = crudCustomer.findCustomer(textfield_campoCorreo.getText());
        label_misDatos.setText("Actualizar mis datos");

        if (customer != null) {
            if (customer.getPassword().equals(password)) {
                btn_Modificar.setText("Guardar");
                textfield_campoPassword.setText(password);
                camposDeshabilitados(false);
                label_passwordIncorrecto.setVisible(false);
                label_tooltip.setText("Si introduces una nueva contraseña, esta reemplazará la contraseña actual."
                        + "Si prefieres mantener la contraseña existente, no modifiques el campo");
                bValidarPassword = true;
            } else {
                label_passwordIncorrecto.setVisible(true);
            }
        }
    }

    private void camposDeshabilitados(Boolean habilitar) {
        textfield_campoNombre.setDisable(habilitar);
        textfield_campoPais.setDisable(habilitar);
        textfield_campoEdad.setDisable(habilitar);
//        textfield_campoCorreo.setDisable(habilitar);
        textfield_campoPassword.setDisable(habilitar);
        textfield_campoHidePassword.setDisable(habilitar);
    }

    // Mensaje Alert
    private void mensajeAlert(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // WEb Aviso de privacidad
    @FXML
    private void webAvisoDePrivacidad(MouseEvent event) {
        openWebAvisoDePrivacidad("https://www.tesla.com/es_es/legal/privacy");
    }

    // Método para abrir el enlace en el navegador
    private void openWebAvisoDePrivacidad(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (URISyntaxException ex) {
        } catch (IOException ex) {
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        actualizarInterfazConCustomer();
    }

    // Método para actualizar la interfaz cuando customer está configurado
    private void actualizarInterfazConCustomer() {
        if (customer != null) {
            textfield_campoNombre.setText(customer.getCustomerName());
            textfield_campoPais.setText(customer.getCountry());
            textfield_campoEdad.setText(customer.getAge());
            textfield_campoCorreo.setText(customer.getEmail());
//            textfield_campoPassword.setText(customer.getPassword());

        } else {
            System.err.println("Customer aún no está asignado.");
        }
    }

    @FXML
    private void eliminarCuenta(MouseEvent event) {
        if (bValidarPassword != true) {
            // Mostrar componentes
            textfield_campoHidePassword.setDisable(false);
            textfield_campoPassword.setDisable(false);
            img_eyeVisible.setVisible(true);
            mostrarToolTip();
            btn_Modificar.setText("Siguiente");
            label_misDatos.setText("Confirmación de usuario");
        } else {
            mostrarLoadingEliminar(event);
        }
    }

    @FXML
    private void handle_exitedToolTip(MouseEvent event) {
        label_tooltip.setVisible(false);
    }

    @FXML
    private void handle_enteredToolTip(MouseEvent event) {
        label_tooltip.setVisible(true);
    }

    private void mostrarToolTip() {
        // Crear un nuevo hilo para realizar la espera sin bloquear el hilo principal
        new Thread(() -> {
            try {
                // Mostrar el Label en el hilo principal
                Platform.runLater(() -> label_tooltip.setVisible(true));

                // Esperar 4 segundos
                Thread.sleep(4000);

                // Ocultar el Label en el hilo principal
                Platform.runLater(() -> label_tooltip.setVisible(false));

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start(); // Iniciar el hilo
    }

    @FXML
    private void handle_eyeVisible(MouseEvent event) {
        System.out.println("eye visible");
        img_eyeNoVisible.setVisible(true);
        img_eyeVisible.setVisible(false);
        textfield_campoPassword.setVisible(true);
        textfield_campoHidePassword.setVisible(false);

        textfield_campoPassword.setText(textfield_campoHidePassword.getText());
    }

    @FXML
    private void handle_eyeNoVisible(MouseEvent event) {
        System.out.println("eye no visible");
//        textfield_campoPassword.setManaged(false);
        img_eyeNoVisible.setVisible(false);
        img_eyeVisible.setVisible(true);
        textfield_campoPassword.setVisible(false);
        textfield_campoHidePassword.setVisible(true);

        textfield_campoHidePassword.setText(textfield_campoPassword.getText());
    }

    private String textoPassword() {
        String password = null;
        if (textfield_campoPassword.isVisible()) {
            password = textfield_campoPassword.getText();
            return password;
        } else if (textfield_campoHidePassword.isVisible()) {
            password = textfield_campoHidePassword.getText();
            return password;
        }
        return null;
    }

    private void mostrarLoading(MouseEvent mEvent) {
        // Mostrar el overlay y el ícono de loading
        overlay.setVisible(true);
        img_loading.setVisible(true);
        label_loading.setVisible(true);
        label_loading.setText("Cargando datos");

        // Pausar durante 3 segundos
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            // Ocultar el overlay y el ícono después de 3 segundos
            overlay.setVisible(false);
            img_loading.setVisible(false);
            label_loading.setVisible(false);

            if (btn_Modificar.getText().equals("Siguiente")) {
                // Validar contraseña
                validarContraseña(mEvent);
                
            } else if (btn_Modificar.getText().equals("Guardar")) {
                btn_Modificar.setText("Modificar");

                customer = new Customer(textfield_campoNombre.getText(),
                        textfield_campoPais.getText(),
                        textfield_campoEdad.getText(),
                        textfield_campoCorreo.getText(),
                        textoPassword());
                crudCustomer.updateCustomer(customer);
                
                // Campos por defecto
                img_eyeNoVisible.setVisible(false);
                img_eyeVisible.setVisible(false);
                textfield_campoHidePassword.setVisible(true);
                textfield_campoPassword.setVisible(false);
                
                camposDeshabilitados(true);
                bValidarPassword = false;
                label_tooltip.setText("Introduce su contraseña para confirmar que esta cuenta le pertenece");
                textfield_campoPassword.setText("");
                textfield_campoHidePassword.setText("");
            }
        });
        pause.play();
    }

    private void mostrarLoadingEliminar(MouseEvent mEvent) {
        // Mostrar el overlay y el ícono de loading
        overlay.setVisible(true);
        img_confirmacion.setVisible(true);
        label_loading.setVisible(true);
        label_loading.setText("Cuenta eliminada exitosamente");

        // Pausar durante 3 segundos
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            // Ocultar el overlay y el ícono después de 3 segundos
            overlay.setVisible(false);
            img_confirmacion.setVisible(false);
            label_loading.setVisible(false);

            if (bValidarPassword == true) {
                crudCustomer.deleteCustomer(customer.getEmail());
                new AbrirVentanas().abrirHomeSinSesion(mEvent);
            }
        });
        pause.play();
    }

}
