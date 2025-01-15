package model.vehicle;

import org.bson.types.ObjectId;

public class ModelX extends TeslaVehicle {

    private boolean dualMotor; // Dual Motor
    private boolean plaid; // Plaid

    // Constructor con id
    public ModelX(boolean dualMotor, boolean plaid, ObjectId id ,String email, String paint, boolean enhancedAutopilot, boolean fullSelfDrivingCapability, boolean wallConnectorCharge, double price,String model) {
        super(id, email,paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, model);
        this.dualMotor = dualMotor;
        this.plaid = plaid;
    }
    
    // Constructor sin id
    public ModelX(boolean dualMotor, boolean plaid, String email, String paint, boolean enhancedAutopilot, boolean fullSelfDrivingCapability, boolean wallConnectorCharge, double price,String model) {
        super(email,paint, enhancedAutopilot, fullSelfDrivingCapability, wallConnectorCharge, price, model);
        this.dualMotor = dualMotor;
        this.plaid = plaid;
    }

    public boolean isDualMotor() {
        return dualMotor;
    }

    public void setDualMotor(boolean dualMotor) {
        this.dualMotor = dualMotor;
    }

    public boolean isPlaid() {
        return plaid;
    }

    public void setPlaid(boolean plaid) {
        this.plaid = plaid;
    }

    @Override
    public String toString() {
        return super.toString() + "- dualMotor = " + dualMotor 
                + "\n- plaid = " + plaid;
    }
}
