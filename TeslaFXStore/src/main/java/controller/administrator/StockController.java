/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.administrator;

import dao.CrudVehicleStock;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.VehicleStock;

/**
 *
 * @author jhova
 */
public class StockController {
    // Stock
    private TextField field_BusquedaStock;
    private TextArea textArea_DatosStock;
    private TextField field_ModelYStock;
    private TextField field_ModelXStock;
    private TextField field_Model3Stock;
    private TextField field_ModelSStock;
    private Button button_siguienteStock;
    private HBox hbox_textAreaStock;
    private Button button_buscarStock;
    private VBox vbox_camposStock;
    private CrudVehicleStock crudStock;

    public StockController(TextField field_BusquedaStock, TextArea textArea_DatosStock, TextField field_ModelYStock, TextField field_ModelXStock, TextField field_Model3Stock, TextField field_ModelSStock, Button button_siguienteStock, HBox hbox_textAreaStock, Button button_buscarStock, VBox vbox_camposStock, CrudVehicleStock crudStock) {
        this.field_BusquedaStock = field_BusquedaStock;
        this.textArea_DatosStock = textArea_DatosStock;
        this.field_ModelYStock = field_ModelYStock;
        this.field_ModelXStock = field_ModelXStock;
        this.field_Model3Stock = field_Model3Stock;
        this.field_ModelSStock = field_ModelSStock;
        this.button_siguienteStock = button_siguienteStock;
        this.hbox_textAreaStock = hbox_textAreaStock;
        this.button_buscarStock = button_buscarStock;
        this.vbox_camposStock = vbox_camposStock;
        this.crudStock = crudStock;
    }
    
    // Mensaje Alert
    private void mensajeAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    //@FXML
    public void handle_modificarStock(MouseEvent event) {
        System.out.println("Button consulta");
        // Vaciar todo
        vaciarTodoStock();
        // Activar componentes, field buscar, button buscar
        activarBuscarComponentesStock();
        // Cambiar texto del button buscar
        button_buscarStock.setText("Modificar");
        // Cambiar texto del button
        button_siguienteStock.setText("Modificar");
    }

    //@FXML
    public void handle_mostrarListaStock(MouseEvent event) {
        // Vaciar todo
        vaciarTodoStock();
        // Mostrar el textArea
        hbox_textAreaStock.setVisible(true);
        // Mostrar los customers en el textArea
        textArea_DatosStock.setText(crudStock.listStock());
        textArea_DatosStock.setEditable(false);
    }

    //@FXML
    public void handle_siguienteStock(MouseEvent event) {
        System.out.println("Button Siguiente");
        if (button_siguienteStock.getText().equalsIgnoreCase("Modificar")) {
            updateStock();
        }
    }

    // Muestra los datos a modificar
    public void handle_buscarStock(MouseEvent event) {
        desactivarCamposStock();
        String stockField = buscarModelo(field_BusquedaStock.getText());
        
        VehicleStock stock = crudStock.findStock(stockField);
        System.out.println(stock);
        if (stock != null) {
            // Poner visible los campos
            vbox_camposStock.setVisible(true); 
            // Mostrar los datos del Customer en los campos
            mostrarDatosEnCamposStock(stock);
            if (stock.getModel().equalsIgnoreCase("ModelY")) {
                field_ModelYStock.setEditable(true);
                field_ModelYStock.setText("" + stock.getStock());
            } else if (stock.getModel().equalsIgnoreCase("Model3")) {
                field_Model3Stock.setEditable(true);
                field_Model3Stock.setText("" + stock.getStock());
            } else if (stock.getModel().equalsIgnoreCase("ModelX")) {
                field_ModelXStock.setEditable(true);
                field_ModelXStock.setText("" + stock.getStock());
            } else if (stock.getModel().equalsIgnoreCase("ModelS")) {
                field_ModelSStock.setEditable(true);
                field_ModelSStock.setText("" + stock.getStock());
            }
        } else {
            mensajeAlert("Modelo no registrado");
        }
    }

    //@FXML
    public void handle_AtrasStock(MouseEvent event) {
        vaciarTodoStock();
    }

    // Vaciar todo
    public void vaciarTodoStock() {
        // Vaciar campos
        vaciarCamposStock();
        // Poner button de buscar por defecto 
        button_buscarStock.setText("Buscar");
        // Desactivar componentes, field buscar, button buscar
        desactivarBuscarComponentesStock();
        // Poner invisible campos
        vbox_camposStock.setVisible(false);
        // Poner invisible textArea
        hbox_textAreaStock.setVisible(false);
        // Desactivar field buscar
        field_BusquedaStock.setEditable(false);
        // Cambiar texto del button
        button_siguienteStock.setText("Siguiente");
    }

    // Modificar customer
    private void updateStock() {
        String model = field_BusquedaStock.getText();
        model = buscarModelo(model);
        // Crear objeto Stock con los nuevos datos en los campos
        crudStock.updateStock(recogerDatosCamposStock(model));

        // Alert, nuevo cliente añadido
        mensajeAlert("Stock modificado");

        // Desactivar todo
        vaciarTodoStock();
    }
    
    public void handle_totalStock(MouseEvent event) {
        String cantidad = ((Long) crudStock.getTotalVehicleStock()).toString();
        mensajeAlert("Cantidad total de stock: " + cantidad);
    }

    // Recoger datos de los campos
    private VehicleStock recogerDatosCamposStock(String stock) {
        String modelY = field_ModelYStock.getText();
        String model3 = field_Model3Stock.getText();
        String modelX = field_ModelXStock.getText();
        String modelS = field_ModelSStock.getText();

        if (stock.equalsIgnoreCase("ModelY")) {
            return new VehicleStock("ModelY", Integer.parseInt(modelY));
        } else if (stock.equalsIgnoreCase("Model3")) {
            return new VehicleStock("Model3", Integer.parseInt(model3));
        } else if (stock.equalsIgnoreCase("ModelX")) {
            return new VehicleStock("ModelX", Integer.parseInt(modelX));
        } else if (stock.equalsIgnoreCase("ModelS")) {
            return new VehicleStock("ModelS", Integer.parseInt(modelS));
        }

        return null;
    }

    // Vaciar campos
    private void vaciarCamposStock() {
        field_BusquedaStock.setText("");
        field_ModelYStock.setText("");
        field_Model3Stock.setText("");
        field_ModelSStock.setText("");
        field_ModelXStock.setText("");
    }

    private void desactivarCamposStock() {
        field_ModelYStock.setEditable(false);
        field_Model3Stock.setEditable(false);
        field_ModelXStock.setEditable(false);
        field_ModelSStock.setEditable(false);
    }

    // Desactivar campos, para modificar y consulta
    private void desactivarBuscarComponentesStock() {
        // Poner invisible el Button buscar
        button_buscarStock.setVisible(false);

        // Desactivar field buscar
        field_BusquedaStock.setEditable(false);
    }

    // Activar campos, para modificar y consulta
    private void activarBuscarComponentesStock() {
        // Poner visible el Button buscar
        button_buscarStock.setVisible(true);

        // Activar field buscar
        field_BusquedaStock.setEditable(true);
    }

    // Mostrar datos en los campos
    private void mostrarDatosEnCamposStock(VehicleStock stock) {
        if (stock.getModel().equalsIgnoreCase("ModelY")) {
            field_ModelYStock.setText(stock.getModel());
        } else if (stock.getModel().equalsIgnoreCase("Model3")) {
            field_Model3Stock.setText(stock.getModel());
        } else if (stock.getModel().equalsIgnoreCase("ModelX")) {
            field_ModelXStock.setText(stock.getModel());
        } else if (stock.getModel().equalsIgnoreCase("ModelS")) {
            field_ModelSStock.setText(stock.getModel());
        }
    }
    
    private String buscarModelo(String model) {
        model = model.trim();

        String finalModel = (model.equalsIgnoreCase("modelx") || model.equalsIgnoreCase("model x"))
                ? "ModelX"
                : (model.equalsIgnoreCase("model3") || model.equalsIgnoreCase("model 3"))
                ? "Model3"
                : (model.equalsIgnoreCase("modely") || model.equalsIgnoreCase("model y"))
                ? "ModelY"
                : (model.equalsIgnoreCase("models") || model.equalsIgnoreCase("model s"))
                ? "ModelS"
                : "";

        return finalModel;
    }
}
