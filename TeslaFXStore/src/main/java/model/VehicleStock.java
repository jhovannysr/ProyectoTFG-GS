package model;

public class VehicleStock {

    private String model;
    private int stock;

    // Constructor vacío (necesario para Spring Data y MongoDB)
    public VehicleStock() {
    }

    // Constructor completo
    public VehicleStock(String model, int stock) {
        this.model = model;
        this.stock = stock;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Método toString para imprimir la información del modelo
    @Override
    public String toString() {
        return "VehicleStock{" + "model=" + model + ", stock=" + stock + '}';
    }
    
}
