package model.vehicle;

import java.text.DecimalFormat;
import org.bson.types.ObjectId;

public class TeslaVehicle {

    // Formato miles, crear un DecimalFormat con el patrón de formato deseado
    DecimalFormat formatter = new DecimalFormat("#,###");    
    
    private ObjectId id;
    private String email; // Email
    private String paint;  // Paint
    private boolean enhancedAutopilot; // Enhanced Autopilot / Piloto automatico mejorado
    private boolean fullSelfDrivingCapability; // Full Self-Driving Capability / Capacidad total de conducción autónoma
    private boolean wallConnectorCharge; // Wall Connector Charge
    private double price; // Price
    private String modelo;

    // Constructor con modelo e id
    public TeslaVehicle(ObjectId id ,String email, String paint, boolean enhancedAutopilot,
            boolean fullSelfDrivingCapability, boolean wallConnectorCharge, double price, String modelo) {
        this.id = id;
        this.email = email;
        this.paint = paint;
        this.enhancedAutopilot = enhancedAutopilot;
        this.fullSelfDrivingCapability = fullSelfDrivingCapability;
        this.wallConnectorCharge = wallConnectorCharge;
        this.price = price;
        this.modelo = modelo;
    }
    
    // Constructor
    public TeslaVehicle(String email, String paint, boolean enhancedAutopilot,
            boolean fullSelfDrivingCapability, boolean wallConnectorCharge, double price, String modelo) {
        this.email = email;
        this.paint = paint;
        this.enhancedAutopilot = enhancedAutopilot;
        this.fullSelfDrivingCapability = fullSelfDrivingCapability;
        this.wallConnectorCharge = wallConnectorCharge;
        this.price = price;
        this.modelo = modelo;
    }

    public String getEmail() {
        return email;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters and Setters
    public String getPaint() {
        return paint;
    }

    public void setPaint(String paint) {
        this.paint = paint;
    }

    public boolean isEnhancedAutopilot() {
        return enhancedAutopilot;
    }

    public void setEnhancedAutopilot(boolean enhancedAutopilot) {
        this.enhancedAutopilot = enhancedAutopilot;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public boolean isFullSelfDrivingCapability() {
        return fullSelfDrivingCapability;
    }

    public void setFullSelfDrivingCapability(boolean fullSelfDrivingCapability) {
        this.fullSelfDrivingCapability = fullSelfDrivingCapability;
    }

    public boolean isWallConnectorCharge() {
        return wallConnectorCharge;
    }

    public void setWallConnectorCharge(boolean wallConnectorCharge) {
        this.wallConnectorCharge = wallConnectorCharge;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "- id = " + id 
                + "\n- email= " + email 
                + "\n- paint= " + paint 
                + "\n- enhancedAutopilot = " + enhancedAutopilot 
                + "\n- fullSelfDrivingCapability = " + fullSelfDrivingCapability 
                + "\n- wallConnectorCharge = " + wallConnectorCharge 
                + "\n- price = " + (formatter.format(price)) +"€"
                + "\n- modelo = " + modelo + "\n";
    }

}
