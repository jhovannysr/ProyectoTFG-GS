package model.vehicle;

import org.bson.types.ObjectId;

public class ModelY extends TeslaVehicle {

    private boolean rearWheelDrive; // Rear Wheel Drive, Tracion trasera
    private boolean highAutonomy; // High Autonomy, Gran Autonomía
    private boolean performance; // Performance, Performance

    // Constructor con id
    public ModelY(boolean rearWheelDrive, boolean highAutonomy, boolean performance, ObjectId id ,String email, String paint, boolean enhancedAutopilot, boolean fullSelfDrivingCapability, boolean wallConnectorCharge, double price, String model) {
        super(id, email, paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, model);
        this.rearWheelDrive = rearWheelDrive;
        this.highAutonomy = highAutonomy;
        this.performance = performance;
    }
    
    // Constructor sin id
    public ModelY(boolean rearWheelDrive, boolean highAutonomy, boolean performance, String email, String paint, boolean enhancedAutopilot, boolean fullSelfDrivingCapability, boolean wallConnectorCharge, double price, String model) {
        super(email, paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, model);
        this.rearWheelDrive = rearWheelDrive;
        this.highAutonomy = highAutonomy;
        this.performance = performance;
    }

    public boolean isRearWheelDrive() {
        return rearWheelDrive;
    }

    public void setRearWheelDrive(boolean rearWheelDrive) {
        this.rearWheelDrive = rearWheelDrive;
    }

    public boolean isHighAutonomy() {
        return highAutonomy;
    }

    public void setHighAutonomy(boolean highAutonomy) {
        this.highAutonomy = highAutonomy;
    }

    public boolean isPerformance() {
        return performance;
    }

    public void setPerformance(boolean performance) {
        this.performance = performance;
    }

    @Override
    public String toString() {
        return super.toString() + "- rearWheelDrive = " + rearWheelDrive 
                + "\n- highAutonomy = " + highAutonomy 
                + "\n- performance = " + performance;
    }
}
