package vehiclePrices;

/**
 * Clase para asignar precios a los vehiculos y tarifas
 * @author Henry Jhovanny Aucapiña Tapia
 */
public class VehiclesPrices {
    
    // Precios por defecto con valores
    private final double priceEnhancedAutoPilot = 3800;
    private final double priceSelfDrivingCapability = 7500;
    private final double priceWallConnector = 535;

    // Atributos modelos S y X
    private double priceDualMotor;
    private double pricePlaid;

    // Atributos modelos Y y 3
    private double priceRearWheelDrive;
    private double priceHighAutonomy;
    private double pricePerformance;
    
    // Activos o no activos por defecto
    private boolean bEnhancedAutoPilot;
    private boolean bSelfDrivingCapability;
    private boolean bWallConnector;
    
    // Activo o no activos modelos Y y 3
    private boolean bRearWheelDrive;
    private boolean bHighAutonomy;
    private boolean bPerformance;
    
    // Activo o no activos modelos S y X
    private boolean bDualMotor;
    private boolean bPlaid;

    // Constructor para modelos Y y 3
    public VehiclesPrices(boolean bEnhancedAutoPilot, boolean bSelfDrivingCapability, boolean bWallConnector, boolean bRearWheelDrive, boolean bHighAutonomy, boolean bPerformance) {
        this.bEnhancedAutoPilot = bEnhancedAutoPilot;
        this.bSelfDrivingCapability = bSelfDrivingCapability;
        this.bWallConnector = bWallConnector;
        this.bRearWheelDrive = bRearWheelDrive;
        this.bHighAutonomy = bHighAutonomy;
        this.bPerformance = bPerformance;
    }
    
    // Constructor para modelos X y S
    public VehiclesPrices(boolean bEnhancedAutoPilot, boolean bSlefDrivingCapability, boolean bWallConnector, boolean bDualMotor, boolean bPlaid) {
        this.bEnhancedAutoPilot = bEnhancedAutoPilot;
        this.bSelfDrivingCapability = bSlefDrivingCapability;
        this.bWallConnector = bWallConnector;
        this.bDualMotor = bDualMotor;
        this.bPlaid = bPlaid;
    }

    public VehiclesPrices() {
    }

    // Precios Modelo Y
    public void updatePriceModelY() {
        priceRearWheelDrive = 44490;
        priceHighAutonomy = 46990;
        pricePerformance = 57990;
    }
    
    // Precios Modelo 3
    public void updatePriceModel3() {
        priceRearWheelDrive = 41490;
        priceHighAutonomy = 51490;
        pricePerformance = 57490;
    }
    
    // Precios Modelo s
    public void updatePriceModelS() {
        priceDualMotor = 92990;
        pricePlaid = 107990;
    }
    
    // Precios Modelo X
    public void updatePriceModelX() {
        priceDualMotor = 99990;
        pricePlaid = 114990;
    }

    // Precio final de los modelos Y y 3
    public double finalPriceModelY3() {
        double price = 0;
        if (bRearWheelDrive == true) {
            price += priceRearWheelDrive;
        }
        if (bHighAutonomy == true) {
            price += priceHighAutonomy;
        }
        if (bPerformance == true) {
            price += pricePerformance;
        }
        if (bWallConnector == true) {
            price += priceWallConnector;
        }
        if (bEnhancedAutoPilot == true) {
            price += priceEnhancedAutoPilot;
        }
        if (bSelfDrivingCapability == true) {
            price += priceSelfDrivingCapability;
        }
        return price;
    }
    
    // Precio final de los modelos S y X
    public double finalPriceModelSX() {
        double price = 0;
        if (bWallConnector == true) {
            price += priceWallConnector;
        }
        if (bEnhancedAutoPilot == true) {
            price += priceEnhancedAutoPilot;
        }
        if (bSelfDrivingCapability == true) {
            price += priceSelfDrivingCapability;
        }
        if (bDualMotor == true) {
            price += priceDualMotor;
        }
        if (bPlaid == true) {
            price += pricePlaid;
        }
        return price;
    }

    // Prices
    public double getPriceEnhancedAutoPilot() {
        return priceEnhancedAutoPilot;
    }

    public double getPriceSelfDrivingCapability() {
        return priceSelfDrivingCapability;
    }

    public double getPriceWallConnector() {
        return priceWallConnector;
    }

    public double getPriceDualMotor() {
        return priceDualMotor;
    }

    public double getPricePlaid() {
        return pricePlaid;
    }

    public double getPriceRearWheelDrive() {
        return priceRearWheelDrive;
    }

    public double getPriceHighAutonomy() {
        return priceHighAutonomy;
    }

    public double getPricePerformance() {
        return pricePerformance;
    }

    public boolean isbEnhancedAutoPilot() {
        return bEnhancedAutoPilot;
    }

    public boolean isbSelfDrivingCapability() {
        return bSelfDrivingCapability;
    }

    public boolean isbWallConnector() {
        return bWallConnector;
    }

    public boolean isbRearWheelDrive() {
        return bRearWheelDrive;
    }

    public boolean isbHighAutonomy() {
        return bHighAutonomy;
    }

    public boolean isbPerformance() {
        return bPerformance;
    }

    public boolean isbDualMotor() {
        return bDualMotor;
    }

    public boolean isbPlaid() {
        return bPlaid;
    }
    
    

}
