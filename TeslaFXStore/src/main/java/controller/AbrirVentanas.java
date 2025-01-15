/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.administrator.AdministratorModeController;
import controller.administrator.DataTableCustomerController;
import controller.administrator.DataTableVehicleController;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Customer;
import model.CustomerAdministrator;
import model.vehicle.TeslaVehicle;

/**
 *
 * @author jhova
 */
public class AbrirVentanas {

    // Abre HomeSinSesion
    public void abrirHomeSinSesion(MouseEvent event) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeSinSesion.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del archivo FXML
            HomeSinSesionController homeSinSesionController = loader.getController();

            // Crear una nueva ventana (Stage) para la ventana de Login
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("HomeSinSesion");
            loginStage.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            loginStage.show();

            // Cerrar la ventana actual
            // Obtener el Stage actual a partir del nodo que disparó el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Abrir Login
    public void abrirLogin(MouseEvent event) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del archivo FXML
            LoginController loginController = loader.getController();

            // Crear una nueva ventana (Stage) para la ventana de Login
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Login");
            loginStage.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            loginStage.show();

            // Cerrar la ventana actual
            // Obtener el Stage actual a partir del nodo que disparó el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrando un mensaje de error
        }
    }

    // Abrir Login
    public void abrirCrearCuenta(MouseEvent event) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CrearCuenta.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del archivo FXML
            CrearCuentaController crearCuenta = loader.getController();

            // Crear una nueva ventana (Stage) para la ventana de Login
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Crear Cuenta");
            loginStage.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            loginStage.show();

            // Cerrar la ventana actual
            // Obtener el Stage actual a partir del nodo que disparó el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrando un mensaje de error
        }
    }

    // Abrir HomeConSesion
    public void abrirHomeConSesion(MouseEvent event, Customer customer) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeConSesion.fxml"));
            Parent root = loader.load();

            HomeConSesionController homeconsesioncontroller = loader.getController();

            // Buscar el customer y establecerlo en el controlador
            homeconsesioncontroller.setCustomer(customer);

            // Crear una nueva ventana (Stage) para la ventana de Login
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Mi sesión");
            loginStage.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            loginStage.show();

            // Cerrar la ventana actual
            // Obtener el Stage actual a partir del nodo que disparó el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrando un mensaje de error
        }
    }

    public void abrirMisDatos(MouseEvent event, Customer customer) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MisDatos.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del archivo FXML
            MisDatosController misDatos = loader.getController();
            // Buscar el customer y establecerlo en el controlador
            misDatos.setCustomer(customer);

            // Crear una nueva ventana (Stage) para la ventana de Login
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Mis Datos");
            loginStage.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            loginStage.show();

            // Cerrar la ventana actual
            // Obtener el Stage actual a partir del nodo que disparó el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrando un mensaje de error
        }
    }

    // Abrir Abrir Encarga Model S
    public void abrirVehicleModelS(MouseEvent event, Customer customer) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VehicleModelS.fxml"));
            Parent root = loader.load();

            VehicleModelSController vehiclemodelscontroller = loader.getController();

            // Buscar el customer y establecerlo en el controlador
            vehiclemodelscontroller.setCustomer(customer);

            // Crear una nueva ventana (Stage) para la ventana de Login
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Model S");
            loginStage.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            loginStage.show();

            // Cerrar la ventana actual
            // Obtener el Stage actual a partir del nodo que disparó el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrando un mensaje de error
        }
    }

    // Abrir Abrir Encarga Model X
    public void abrirVehicleModelX(MouseEvent event, Customer customer) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VehicleModelX.fxml"));
            Parent root = loader.load();

            VehicleModelXController vehiclemodelxcontroller = loader.getController();

            // Buscar el customer y establecerlo en el controlador
            vehiclemodelxcontroller.setCustomer(customer);

            // Crear una nueva ventana (Stage) para la ventana de Login
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Model X");
            loginStage.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            loginStage.show();

            // Cerrar la ventana actual
            // Obtener el Stage actual a partir del nodo que disparó el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrando un mensaje de error
        }
    }

    // Abrir Abrir Encarga Model Y
    public void abrirVehicleModelY(MouseEvent event, Customer customer) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VehicleModelY.fxml"));
            Parent root = loader.load();

            VehicleModelYController vehiclemodelycontroller = loader.getController();

            // Buscar el customer y establecerlo en el controlador
            vehiclemodelycontroller.setCustomer(customer);

            // Crear una nueva ventana (Stage) para la ventana de Login
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Model Y");
            loginStage.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            loginStage.show();

            // Cerrar la ventana actual
            // Obtener el Stage actual a partir del nodo que disparó el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrando un mensaje de error
        }
    }

    // Abrir Abrir Encarga Model 3
    public void abrirVehicleModel3(MouseEvent event, Customer customer) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VehicleModel3.fxml"));
            Parent root = loader.load();

            VehicleModel3Controller vehiclemodel3ycontroller = loader.getController();

            // Buscar el customer y establecerlo en el controlador
            vehiclemodel3ycontroller.setCustomer(customer);

            // Crear una nueva ventana (Stage) para la ventana de Login
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Model 3");
            loginStage.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            loginStage.show();

            // Cerrar la ventana actual
            // Obtener el Stage actual a partir del nodo que disparó el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrando un mensaje de error
        }
    }

    // Abrir Modo Administrador
    public void abrirAdministratorMode(MouseEvent event, CustomerAdministrator administrator) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdministratorMode.fxml"));
            Parent root = loader.load();

            AdministratorModeController administratorModeController = loader.getController();
            administratorModeController.setCustomerAdministrator(administrator);

            // Crear una nueva ventana (Stage) para la ventana de Login
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Administrator Mode");
            loginStage.setMaximized(true);
            loginStage.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            loginStage.show();

            // Cerrar la ventana actual
            // Obtener el Stage actual a partir del nodo que disparó el evento
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrando un mensaje de error
        }
    }

    // Abrir tabla de los Clientes/Customer
    private Stage stageDataTableCustomer;

    public void abrirDataTableCustomer(MouseEvent event, ArrayList<Customer> customerList) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DataTableCustomer.fxml"));
            Parent root = loader.load();

            DataTableCustomerController dataTableController = loader.getController();
            dataTableController.setListCustomers(customerList);

            // Crear una nueva ventana (Stage) para la ventana de Login
            stageDataTableCustomer = new Stage();

            // Posicionar la ventana en coordenadas específicas
            stageDataTableCustomer.setX(20); // Cambia 300 por la posición X deseada
            stageDataTableCustomer.setY(280); // Cambia 200 por la posición Y deseada

            stageDataTableCustomer.setScene(new Scene(root));
            stageDataTableCustomer.setTitle("Data Table Customer");
//            stageDataTableCustomer.setMaximized(true);
            stageDataTableCustomer.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            stageDataTableCustomer.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrando un mensaje de error
        }
    }

    public void cerrarDataTableCustomer() {
        if (stageDataTableCustomer != null && stageDataTableCustomer.isShowing()) {
            stageDataTableCustomer.close();
            stageDataTableCustomer = null;
        } else {
            System.out.println("No se ha cerrado o no esta ventana no está abierta");
        }
    }

    // Abrir tabla de los Vehiculos/Vehicle
    private Stage stageDataTableVehicle;

    public void abrirDataTableVehicle(MouseEvent event, ArrayList<TeslaVehicle> vehicleList) {
        try {
            // Cargar el diseño de la nueva ventana de Login desde el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DataTableVehicle.fxml"));
            Parent root = loader.load();

            DataTableVehicleController dataTableController = loader.getController();
            dataTableController.setVehicleList(vehicleList);

            // Crear una nueva ventana (Stage) para la ventana de Login
            stageDataTableVehicle = new Stage();
            
            // Posicionar la ventana en coordenadas específicas
            stageDataTableVehicle.setX(20); // Cambia 300 por la posición X deseada
            stageDataTableVehicle.setY(280); // Cambia 200 por la posición Y deseada
            
            stageDataTableVehicle.setScene(new Scene(root));
            stageDataTableVehicle.setTitle("Data Table Vehicle");
//            loginStage.setMaximized(true);
            stageDataTableVehicle.initStyle(StageStyle.UNDECORATED); // Puedes elegir otro estilo de ventana
            stageDataTableVehicle.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrando un mensaje de error
        }
    }

    public void cerrarDataTableVehicle() {
        if (stageDataTableVehicle != null && stageDataTableVehicle.isShowing()) {
            stageDataTableVehicle.close();
            stageDataTableVehicle = null;
        } else {
            System.out.println("No se ha cerrado o no esta ventana no está abierta");
        }
    }

}
