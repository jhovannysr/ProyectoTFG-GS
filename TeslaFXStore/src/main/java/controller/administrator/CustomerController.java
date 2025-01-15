package controller.administrator;

import controller.AbrirVentanas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.MouseEvent;
import dao.CrudCustomer;
import java.util.ArrayList;
import model.Customer;
import org.bson.types.ObjectId;

public class CustomerController {

    // Instancia a la clase AbrirVentanas
    AbrirVentanas abrirVentanas;

    private TextField field_BusquedaCustomer;
    private TextField field_NameCustomer;
    private TextField field_CountryCustomer;
    private TextField field_AgeCustomer;
    private TextField field_EmailCustomer;
    private TextField field_PasswordCustomer;
    private TextArea textArea_DatosCustomer;
    private VBox vbox_camposCustomer;
    private HBox hbox_textAreaCustomer;
    private Button button_siguienteCustomer;
    private Button button_buscarCustomer;
    private CrudCustomer crudCustomer;
    private Label label_buscadorCustomer;
    private Label label_introduceDatosCustomer;
    private TextField field_idCustomer;

    // Auxiliares
    String emailOriginal;

    public CustomerController(TextField field_BusquedaCustomer, TextField field_NameCustomer, TextField field_CountryCustomer,
            TextField field_AgeCustomer, TextField field_EmailCustomer, TextField field_PasswordCustomer,
            TextArea textArea_DatosCustomer, VBox vbox_camposCustomer, HBox hbox_textAreaCustomer,
            Button button_siguienteCustomer, Button button_buscarCustomer, CrudCustomer crudCustomer, Label label_buscadorCustomer, Label label_introduceDatosCustomer, TextField field_idCustomer) {

        this.field_BusquedaCustomer = field_BusquedaCustomer;
        this.field_NameCustomer = field_NameCustomer;
        this.field_CountryCustomer = field_CountryCustomer;
        this.field_AgeCustomer = field_AgeCustomer;
        this.field_EmailCustomer = field_EmailCustomer;
        this.field_PasswordCustomer = field_PasswordCustomer;
        this.textArea_DatosCustomer = textArea_DatosCustomer;
        this.vbox_camposCustomer = vbox_camposCustomer;
        this.hbox_textAreaCustomer = hbox_textAreaCustomer;
        this.button_siguienteCustomer = button_siguienteCustomer;
        this.button_buscarCustomer = button_buscarCustomer;
        this.crudCustomer = crudCustomer;
        this.label_buscadorCustomer = label_buscadorCustomer;
        this.label_introduceDatosCustomer = label_introduceDatosCustomer;
        this.field_idCustomer = field_idCustomer;

        abrirVentanas = new AbrirVentanas();
    }

    public void handle_eliminarCustomer(MouseEvent event) {
        vaciarTodoCustomer();
        activarBuscarComponentesCustomer();
        button_buscarCustomer.setText("Eliminar");
        label_introduceDatosCustomer.setText("Introduce el email");
    }

    public void handle_anadirCustomer(MouseEvent event) {
        vaciarTodoCustomer();
        vbox_camposCustomer.setVisible(true);
        
        // Textos
        button_siguienteCustomer.setText("Añadir");
        label_buscadorCustomer.setText("Introduce el email");
        label_introduceDatosCustomer.setText("Introduce los datos");
    }

    public void handle_modificarCustomer(MouseEvent event) {
        vaciarTodoCustomer();
        activarBuscarComponentesCustomer();

        // Textos 
        button_buscarCustomer.setText("Modificar");
        button_siguienteCustomer.setText("Modificar");
        label_introduceDatosCustomer.setText("Introduce los datos");
        label_buscadorCustomer.setText("Introduce el email");
    }

    public void handle_consultaCustomer(MouseEvent event) {
        vaciarTodoCustomer();
        activarBuscarComponentesCustomer();

        // Textos
        button_buscarCustomer.setText("Buscar(Email)");
        label_introduceDatosCustomer.setText("Mis datos");
        label_buscadorCustomer.setText("Introduce el email");
    }

    public void handle_mostrarListaCustomer(MouseEvent event) {
        vaciarTodoCustomer();
        hbox_textAreaCustomer.setVisible(true);
        textArea_DatosCustomer.setText(crudCustomer.listCustomers());
        textArea_DatosCustomer.setEditable(false);

        // Mostrar Customers en la interfaz grafica
        ArrayList<Customer> customerList = crudCustomer.listCustomers2();
        abrirVentanas.abrirDataTableCustomer(event, customerList);
    }

    public void handle_consultaIdCustomer(MouseEvent event) {
        vaciarTodoCustomer();
        activarBuscarComponentesCustomer();
        // Textos
        button_buscarCustomer.setText("Buscar(ID)");
        label_introduceDatosCustomer.setText("Mis datos");
        label_buscadorCustomer.setText("Introduce el id");
    }

    public void handle_consultaPaisCustomer(MouseEvent event) {
        vaciarTodoCustomer();
        activarBuscarComponentesCustomer();
        // Textos
        button_buscarCustomer.setText("Buscar(Pais)");
        label_buscadorCustomer.setText("Introduce el pais");
    }

    public void handle_totalCustomer(MouseEvent event) {
        vaciarTodoCustomer();
        mensajeAlert("Cantidad de clientes: " + crudCustomer.countTotalCustomers() + " registros");
    }

    public void handle_consultaEdadCustomer(MouseEvent event) {
        vaciarTodoCustomer();
        activarBuscarComponentesCustomer();
        // Textos
        button_buscarCustomer.setText("Buscar(Edad)");
        label_buscadorCustomer.setText("Introduce la edad, formato: Edad1-Edad2");
    }

    public void handle_siguienteCustomer(MouseEvent event) {
        if (button_siguienteCustomer.getText().equalsIgnoreCase("Añadir")) {
            addCustomer();
        } else if (button_siguienteCustomer.getText().equalsIgnoreCase("Modificar")) {
            modificarCustomer();
        }
    }

    public void handle_AtrasCustomer(MouseEvent event) {
        vaciarTodoCustomer();
        abrirVentanas.cerrarDataTableCustomer();
    }

    public void handle_buscarCustomer(MouseEvent event) {
        String searchText = field_BusquedaCustomer.getText();
        if (button_buscarCustomer.getText().equalsIgnoreCase("Modificar")) {
            optionModificar(searchText);
        } else if (button_buscarCustomer.getText().equalsIgnoreCase("Buscar(Email)")) {
            optionBuscarEmail(searchText);
        } else if (button_buscarCustomer.getText().equalsIgnoreCase("Buscar(ID)")) {
            optionBuscarId(searchText);
        } else if (button_buscarCustomer.getText().equalsIgnoreCase("Buscar(Pais)")) {
            optionBuscarPais(event, searchText);
        } else if (button_buscarCustomer.getText().equalsIgnoreCase("Buscar(Edad)")) {
            optionBuscarEdad(event, searchText);
        } else if (button_buscarCustomer.getText().equalsIgnoreCase("Eliminar")) {
            optionEliminar(searchText);
        }
    }

    private void optionModificar(String searchText) {
        Customer c = crudCustomer.findCustomer(searchText);
        if (c != null) {
            emailOriginal = c.getEmail();
            vbox_camposCustomer.setVisible(true);
            mostrarDatosEnCamposCustomer(c);
        } else {
            mensajeAlert("Email no registrado");
        }
    }

    private void optionBuscarEmail(String searchText) {
        Customer c = crudCustomer.findCustomer(searchText);
        if (c != null) {
            camposConsultaCustomer(c);
        } else {
            mensajeAlert("Email no registrado");
        }
    }

    private void optionBuscarId(String searchText) {
        try {
            ObjectId id = new ObjectId(searchText);
            Customer c = crudCustomer.findCustomerById(id);
            if (c != null) {
                camposConsultaCustomer(c);
            } else {
                mensajeAlert("Id no registrado");
            }
        } catch (Exception e) {
            mensajeAlert("Id no registrado");
        }
    }

    private void optionBuscarPais(MouseEvent event, String searchText) {
        ArrayList<Customer> customerList = crudCustomer.findCustomersByCountry(searchText.trim());
        if (!customerList.isEmpty()) {
            abrirVentanas.abrirDataTableCustomer(event, customerList);
        } else {
            mensajeAlert("No hay clientes del pais introducido");
        }
    }

    private void optionBuscarEdad(MouseEvent event, String searchText) {
        ArrayList<Customer> customerList = crudCustomer.findCustomersByAgeRange(searchText.trim());
        if (!customerList.isEmpty()) {
            abrirVentanas.abrirDataTableCustomer(event, customerList);
        } else {
            mensajeAlert("No hay clientes del rango de edad introducido");
        }
    }

    private void optionEliminar(String searchText) {
        Customer c = crudCustomer.findCustomer(searchText);
        if (c != null) {
            crudCustomer.deleteCustomer(searchText);
            mensajeAlert("Cliente eliminado");
            vaciarTodoCustomer();
        } else {
            mensajeAlert("Email no registrado");
        }
    }

    private void addCustomer() {
        Customer c = recogerDatosCamposCustomer();
        if ((crudCustomer.findCustomer(c.getEmail())) == null) {
            crudCustomer.saveCustomer(c);
            mensajeAlert("Nuevo cliente añadido");
            vaciarTodoCustomer();
        } else {
            mensajeAlert("Correo ya registrado");
        }
    }

    private void modificarCustomer() {
        var customer = crudCustomer.findCustomer(field_EmailCustomer.getText());
        if (customer == null) {
            crudCustomer.updateCustomerAdmin(recogerDatosCamposCustomer(), emailOriginal);
            mensajeAlert("Cliente modificado");
            vaciarTodoCustomer();
        } else if (customer.getEmail().equalsIgnoreCase(emailOriginal)) {
            crudCustomer.updateCustomerAdmin(recogerDatosCamposCustomer(), emailOriginal);
            mensajeAlert("Cliente modificado");
            vaciarTodoCustomer();
        } else {
            mensajeAlert("Correo ya registrado");
        }
    }

    private void activarBuscarComponentesCustomer() {
        button_buscarCustomer.setVisible(true);
        field_BusquedaCustomer.setEditable(true);
    }

    private void desactivarBuscarComponentesCustomer() {
        button_buscarCustomer.setVisible(false);
        field_BusquedaCustomer.setEditable(false);
    }

    private Customer recogerDatosCamposCustomer() {
        String name = field_NameCustomer.getText();
        String country = field_CountryCustomer.getText();
        String age = field_AgeCustomer.getText();
        String email = field_EmailCustomer.getText();
        String password = field_PasswordCustomer.getText();
        return new Customer(name, country, age, email, password);
    }

    private void mostrarDatosEnCamposCustomer(Customer customer) {
        field_NameCustomer.setText(customer.getCustomerName());
        field_CountryCustomer.setText(customer.getCountry());
        field_AgeCustomer.setText(customer.getAge());
        field_EmailCustomer.setText(customer.getEmail());
        field_PasswordCustomer.setText(customer.getPassword());
    }

    private void vaciarCamposCustomer() {
        field_BusquedaCustomer.setText("");
        field_NameCustomer.setText("");
        field_CountryCustomer.setText("");
        field_AgeCustomer.setText("");
        field_EmailCustomer.setText("");
        field_PasswordCustomer.setText("");
        button_buscarCustomer.setText("Buscar");
    }

    private void mensajeAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void vaciarTodoCustomer() {
        vaciarCamposCustomer();
        desactivarBuscarComponentesCustomer();
        vbox_camposCustomer.setVisible(false);
        hbox_textAreaCustomer.setVisible(false);
        field_BusquedaCustomer.setEditable(false);
        button_siguienteCustomer.setText("Siguiente");
        field_idCustomer.setManaged(false);
        button_siguienteCustomer.setVisible(true);
        habilitarCampos();
    }

    private void deshabilitarCampos() {
        field_idCustomer.setEditable(false);
        field_NameCustomer.setEditable(false);
        field_CountryCustomer.setEditable(false);
        field_AgeCustomer.setEditable(false);
        field_EmailCustomer.setEditable(false);
        field_PasswordCustomer.setEditable(false);
    }

    private void habilitarCampos() {
        field_idCustomer.setEditable(true);
        field_NameCustomer.setEditable(true);
        field_CountryCustomer.setEditable(true);
        field_AgeCustomer.setEditable(true);
        field_EmailCustomer.setEditable(true);
        field_PasswordCustomer.setEditable(true);
    }

    private void camposConsultaCustomer(Customer customer) {
        // Estado componentes
        vbox_camposCustomer.setVisible(true);
        field_idCustomer.setManaged(true);
        button_siguienteCustomer.setVisible(false);

        // Deshabilitar datos
        deshabilitarCampos();

        // Mostrar datos
        field_idCustomer.setText("Id: " + customer.getId());
        field_NameCustomer.setText("Nombre: " + customer.getCustomerName());
        field_CountryCustomer.setText("Pais: " + customer.getCountry());
        field_AgeCustomer.setText("Edad: " + customer.getAge());
        field_EmailCustomer.setText("Email: " + customer.getEmail());
        field_PasswordCustomer.setText("Contraseña: " + customer.getPassword());
    }
}
