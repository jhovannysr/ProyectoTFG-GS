/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dao.Conexion;
import dao.CrudCustomer;
import dao.CrudCustomerAdministrator;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.CustomerAdministrator;

/**
 * FXML Controller class
 *
 * @author jhova
 */
public class LoginController implements Initializable {

    // Permite mover la ventana manteniendo pulsado el cursor
    private double xOffset = 0;
    private double yOffset = 0;

    // Customer
    private CrudCustomer crudCustomer;
    private Customer customer;

    // Customer Administrator
    private CrudCustomerAdministrator crudCustomerAdministrator = new CrudCustomerAdministrator();
    private CustomerAdministrator customerAdministrator;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView img_eyeNoVisible;
    @FXML
    private ImageView img_eyeVisible;
    @FXML
    private PasswordField textfield_campoHidePassword;
    @FXML
    private ImageView img_loading;
    @FXML
    private Label label_loading;
    @FXML
    private Rectangle overlay;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @FXML
    private TextField textfield_campoCorreo;
    @FXML
    private TextField textfield_campoPassword;
    @FXML
    private Button btn_Siguiente;
    @FXML
    private Label label_emailIncorrecto;
    @FXML
    private Label label_passwordIncorrecto;
    @FXML
    private Label label_camposVacios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Abrir conexión
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
    @FXML
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
    private void abrirHomeSinSesion(MouseEvent event) {
        new AbrirVentanas().abrirHomeSinSesion(event);
    }

    @FXML
    private void abrirHomeConSesion(MouseEvent event) {
        mostrarLoading(event);
    }
    
    private void verificacionDeDatos(MouseEvent event){
        // Datos de los campos
        String email = textfield_campoCorreo.getText();
        String password = textoPassword();

        // Cuentas
        Customer customer = crudCustomer.findCustomer(email);
        CustomerAdministrator admin = crudCustomerAdministrator.findCustomerAdministrator(email);
        
        // Abrir modo administrador
        if (admin != null) {
            if (admin.getPassword().equals(password)) {
                new AbrirVentanas().abrirAdministratorMode(event, admin);
                return;
            }
        }

        //Los 2 campos con datos
        if (!email.trim().equalsIgnoreCase("") && !password.trim().equalsIgnoreCase("")) {
            label_camposVacios.setVisible(false);
            // Verificar si el customer existe
            if (customer != null) {
                label_emailIncorrecto.setVisible(false);
                // Verificar que la contraseña coincida con el correo
                if (customer.getPassword().equals(password)) {
                    label_passwordIncorrecto.setVisible(false);
                    // Abrir HomeConSesion
                    new AbrirVentanas().abrirHomeConSesion(event, customer);
                } else {
                    System.err.println("Login: Contraseña Incorrecta");
                    label_passwordIncorrecto.setVisible(true);
                }
            } else {
                System.err.println("Login: Email Incorrecto");
                label_emailIncorrecto.setVisible(true);
            }
        } else {
            label_camposVacios.setVisible(true);
        }
    }
    
    private void mostrarLoading(MouseEvent mEvent) {
        // Mostrar el overlay y el ícono de loading
        overlay.setVisible(true);
        img_loading.setVisible(true);
        label_loading.setVisible(true);

        // Pausar durante 3 segundos
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            // Ocultar el overlay y el ícono después de 3 segundos
            verificacionDeDatos(mEvent);
            overlay.setVisible(false);
            img_loading.setVisible(false);
            label_loading.setVisible(false);
        });
        pause.play();
    }

    @FXML
    private void webProblemasIniciarSesion(MouseEvent event) {
        openWebProblemasIniciarSesion("https://www.tesla.com/es_ES/support/account-support?redirect=no");
    }

    // Método para abrir el enlace en el navegador
    private void openWebProblemasIniciarSesion(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (URISyntaxException ex) {
        } catch (IOException ex) {
        }
    }

    @FXML
    private void abrirCrearCuenta(MouseEvent event) {
        new AbrirVentanas().abrirCrearCuenta(event);
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
        textfield_campoPassword.setManaged(false);
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
}
