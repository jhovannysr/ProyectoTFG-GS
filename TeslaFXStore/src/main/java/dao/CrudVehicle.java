package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.VehicleStock;
import model.vehicle.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class CrudVehicle {

    // Formato miles, crear un DecimalFormat con el patrón de formato deseado
    DecimalFormat formatter = new DecimalFormat("#,###");

    private static final String DATABASE_NAME = "TeslaFXStoreDB";
    private static final String COLLECTION_NAME = "vehicle";

    // Método privado para obtener la colección de MongoDB
    private static MongoCollection<Document> getCollection() {
        MongoDatabase database = Conexion.getMongoClient().getDatabase(DATABASE_NAME);
        return database.getCollection(COLLECTION_NAME);
    }

    // Método para añadir un TeslaVehicle a MongoDB
    public void saveTeslaVehicle(TeslaVehicle vehicle) {
        MongoCollection<Document> collection = getCollection();

        Document document = new Document("email", vehicle.getEmail())
                .append("paint", vehicle.getPaint())
                .append("enhancedAutopilot", vehicle.isEnhancedAutopilot())
                .append("fullSelfDrivingCapability", vehicle.isFullSelfDrivingCapability())
                .append("wallConnectorCharge", vehicle.isWallConnectorCharge());

        // Dependiendo del tipo de vehículo, añade atributos específicos
        if (vehicle instanceof ModelS) {
            ModelS modelS = (ModelS) vehicle;
            document.append("dualMotor", modelS.isDualMotor())
                    .append("plaid", modelS.isPlaid())
                    .append("price", vehicle.getPrice())
                    .append("model", "Model S");
            // Reducir Stock
            reducirStock("ModelS");
        } else if (vehicle instanceof ModelX) {
            ModelX modelX = (ModelX) vehicle;
            document.append("dualMotor", modelX.isDualMotor())
                    .append("plaid", modelX.isPlaid())
                    .append("price", vehicle.getPrice())
                    .append("model", "Model X");
            reducirStock("ModelX");
        } else if (vehicle instanceof ModelY) {
            ModelY modelY = (ModelY) vehicle;
            document.append("rearWheelDrive", modelY.isRearWheelDrive())
                    .append("highAutonomy", modelY.isHighAutonomy())
                    .append("performance", modelY.isPerformance())
                    .append("price", vehicle.getPrice())
                    .append("model", "Model Y");
            reducirStock("ModelY");
        } else if (vehicle instanceof Model3) {
            Model3 model3 = (Model3) vehicle;
            document.append("rearWheelDrive", model3.isRearWheelDrive())
                    .append("highAutonomy", model3.isHighAutonomy())
                    .append("performance", model3.isPerformance())
                    .append("price", vehicle.getPrice())
                    .append("model", "Model 3");
            reducirStock("Model3");
        }

        // Insertar el documento en la colección
        collection.insertOne(document);
        System.out.println("Vehículo añadido a MongoDB: " + document.toJson());
    }

    public void reducirStock(String model) {
        // Reducir Stock
        CrudVehicleStock crudStock = new CrudVehicleStock();
        VehicleStock stock = crudStock.findStock(model);
        crudStock.updateStock(new VehicleStock(stock.getModel(), stock.getStock() - 1));
    }

    // Método para buscar un Vehiculo
    public TeslaVehicle findVehicle(ObjectId id) {
        MongoCollection<Document> collection = getCollection();

        // Buscar el documento basado en el email del vehículo
        Document document = collection.find(eq("_id", id)).first();

        // Si no se encuentra el documento, devolver null o lanzar una excepción
        if (document == null) {
            System.out.println("No se encontró ningún vehículo con el email proporcionado.");
            return null;
        }

        // Obtener el modelo del vehículo para determinar la clase específica de TeslaVehicle
        String model = document.getString("model");
        TeslaVehicle vehicle = null;

        // Crear la instancia adecuada del vehículo según el modelo
        switch (model) {
            case "Model S":
                vehicle = new ModelS(
                        document.getBoolean("dualMotor"),
                        document.getBoolean("plaid"),
                        document.getObjectId("_id"),
                        document.getString("email"),
                        document.getString("paint"),
                        document.getBoolean("enhancedAutopilot"),
                        document.getBoolean("fullSelfDrivingCapability"),
                        document.getBoolean("wallConnectorCharge"),
                        document.getDouble("price"),
                        document.getString("model"));
                break;
            case "Model X":
                vehicle = new ModelX(
                        document.getBoolean("dualMotor"),
                        document.getBoolean("plaid"),
                        document.getObjectId("_id"),
                        document.getString("email"),
                        document.getString("paint"),
                        document.getBoolean("enhancedAutopilot"),
                        document.getBoolean("fullSelfDrivingCapability"),
                        document.getBoolean("wallConnectorCharge"),
                        document.getDouble("price"),
                        document.getString("model"));
                break;

            case "Model Y":
                vehicle = new ModelY(
                        document.getBoolean("rearWheelDrive"),
                        document.getBoolean("highAutonomy"),
                        document.getBoolean("performance"),
                        document.getObjectId("_id"),
                        document.getString("email"),
                        document.getString("paint"),
                        document.getBoolean("enhancedAutopilot"),
                        document.getBoolean("fullSelfDrivingCapability"),
                        document.getBoolean("wallConnectorCharge"),
                        document.getDouble("price"),
                        document.getString("model"));
                break;
            case "Model 3":
                vehicle = new Model3(
                        document.getBoolean("rearWheelDrive"),
                        document.getBoolean("highAutonomy"),
                        document.getBoolean("performance"),
                        document.getObjectId("_id"),
                        document.getString("email"),
                        document.getString("paint"),
                        document.getBoolean("enhancedAutopilot"),
                        document.getBoolean("fullSelfDrivingCapability"),
                        document.getBoolean("wallConnectorCharge"),
                        document.getDouble("price"),
                        document.getString("model"));
                break;
            default:
                System.out.println("Modelo de vehículo desconocido.");
                break;
        }
        return vehicle;
    }

    public ArrayList<TeslaVehicle> findVehiclesByModel(String model) {
        MongoCollection<Document> collection = getCollection();

        ArrayList<TeslaVehicle> vehicleList = new ArrayList<>();

        // Itera sobre cada documento y lo convierte en el objeto adecuado
        for (Document doc : collection.find(new Document("model", model))) {
            // Atributos comunes a todos los TeslaVehicle
            ObjectId id = doc.getObjectId("_id");
            String email = doc.getString("email");
            String paint = doc.getString("paint");
            boolean enhancedAutopilot = doc.getBoolean("enhancedAutopilot", false);
            boolean fullSelfDrivingCapability = doc.getBoolean("fullSelfDrivingCapability", false);
            boolean wallConnectorCharge = doc.getBoolean("wallConnectorCharge", false);
            double price = doc.getDouble("price");

            // Variables para los atributos específicos
            boolean dualMotor = doc.getBoolean("dualMotor", false); // ModelS y ModelX
            boolean plaid = doc.getBoolean("plaid", false);         // ModelS y ModelX

            boolean rearWheelDrive = doc.getBoolean("rearWheelDrive", false); // Model3 y ModelY
            boolean highAutonomy = doc.getBoolean("highAutonomy", false);     // Model3 y ModelY
            boolean performance = doc.getBoolean("performance", false);       // Model3 y ModelY

            // Crear la instancia correcta según el modelo
            TeslaVehicle vehicle;
            switch (model) {
                case "Model X":
                    vehicle = new ModelX(dualMotor, plaid, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                case "Model S":
                    vehicle = new ModelS(dualMotor, plaid, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                case "Model 3":
                    vehicle = new Model3(rearWheelDrive, highAutonomy, performance, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                case "Model Y":
                    vehicle = new ModelY(rearWheelDrive, highAutonomy, performance, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                default:
                    // Si no se reconoce el modelo, salta al siguiente documento
                    System.out.println("Modelo no reconocido: " + model);
                    continue;
            }

            // Agregar el vehículo a la lista
            vehicleList.add(vehicle);
        }

        return vehicleList;
    }

    // Muestra tabla de vehiculos comprados filtrado por cliente
    public ArrayList<TeslaVehicle> findVehiclesByCustomerEmail(String email) {
        MongoCollection<Document> collection = getCollection();

        ArrayList<TeslaVehicle> vehicleList = new ArrayList<>();

        // Itera sobre cada documento y lo convierte en el objeto adecuado
        for (Document doc : collection.find(new Document("email", email))) {
            // Atributos comunes a todos los TeslaVehicle
            ObjectId id = doc.getObjectId("_id");
            String paint = doc.getString("paint");
            boolean enhancedAutopilot = doc.getBoolean("enhancedAutopilot", false);
            boolean fullSelfDrivingCapability = doc.getBoolean("fullSelfDrivingCapability", false);
            boolean wallConnectorCharge = doc.getBoolean("wallConnectorCharge", false);
            double price = doc.getDouble("price");
            String model = doc.getString("model");

            // Variables para los atributos específicos
            boolean dualMotor = doc.getBoolean("dualMotor", false); // ModelS y ModelX
            boolean plaid = doc.getBoolean("plaid", false);         // ModelS y ModelX

            boolean rearWheelDrive = doc.getBoolean("rearWheelDrive", false); // Model3 y ModelY
            boolean highAutonomy = doc.getBoolean("highAutonomy", false);     // Model3 y ModelY
            boolean performance = doc.getBoolean("performance", false);       // Model3 y ModelY

            // Crear la instancia correcta según el modelo
            TeslaVehicle vehicle;
            switch (model) {
                case "Model X":
                    vehicle = new ModelX(dualMotor, plaid, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                case "Model S":
                    vehicle = new ModelS(dualMotor, plaid, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                case "Model 3":
                    vehicle = new Model3(rearWheelDrive, highAutonomy, performance, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                case "Model Y":
                    vehicle = new ModelY(rearWheelDrive, highAutonomy, performance, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                default:
                    // Si no se reconoce el modelo, salta al siguiente documento
                    System.out.println("Modelo no reconocido: " + model);
                    continue;
            }

            // Agregar el vehículo a la lista
            vehicleList.add(vehicle);
        }

        return vehicleList;
    }

    // Método para modificar un documento en la colección usando un objeto Vehicle
    public void updateVehicle(TeslaVehicle vehicle) {
        MongoCollection<Document> collection = getCollection();

        // Crea una consulta para encontrar el documento basado en el email
        Bson filter = eq("_id", vehicle.getId());

        // Crea una actualización combinada para modificar los campos del documento
        Bson update = null;
        // Dependiendo del tipo de vehículo, añade atributos específicos
        if (vehicle instanceof ModelY) {
            ModelY modelY = (ModelY) vehicle;
            update = combine(
                    set("email", modelY.getEmail()),
                    set("paint", modelY.getPaint()),
                    set("enhancedAutopilot", modelY.isEnhancedAutopilot()),
                    set("fullSelfDrivingCapability", modelY.isFullSelfDrivingCapability()),
                    set("wallConnectorCharge", modelY.isWallConnectorCharge()),
                    set("rearWheelDrive", modelY.isRearWheelDrive()),
                    set("highAutonomy", modelY.isHighAutonomy()),
                    set("performance", modelY.isPerformance()),
                    set("price", modelY.getPrice()));
        } else if (vehicle instanceof Model3) {
            Model3 model3 = (Model3) vehicle;
            update = combine(
                    set("email", model3.getEmail()),
                    set("paint", model3.getPaint()),
                    set("enhancedAutopilot", model3.isEnhancedAutopilot()),
                    set("fullSelfDrivingCapability", model3.isFullSelfDrivingCapability()),
                    set("wallConnectorCharge", model3.isWallConnectorCharge()),
                    set("rearWheelDrive", model3.isRearWheelDrive()),
                    set("highAutonomy", model3.isHighAutonomy()),
                    set("performance", model3.isPerformance()),
                    set("price", model3.getPrice()));
        } else if (vehicle instanceof ModelX) {
            ModelX modelX = (ModelX) vehicle;
            update = combine(
                    set("email", modelX.getEmail()),
                    set("paint", modelX.getPaint()),
                    set("enhancedAutopilot", modelX.isEnhancedAutopilot()),
                    set("fullSelfDrivingCapability", modelX.isFullSelfDrivingCapability()),
                    set("wallConnectorCharge", modelX.isWallConnectorCharge()),
                    set("dualMotor", modelX.isDualMotor()),
                    set("plaid", modelX.isPlaid()),
                    set("price", modelX.getPrice()));
        } else if (vehicle instanceof ModelS) {
            ModelS modelS = (ModelS) vehicle;
            update = combine(
                    set("email", modelS.getEmail()),
                    set("paint", modelS.getPaint()),
                    set("enhancedAutopilot", modelS.isEnhancedAutopilot()),
                    set("fullSelfDrivingCapability", modelS.isFullSelfDrivingCapability()),
                    set("wallConnectorCharge", modelS.isWallConnectorCharge()),
                    set("dualMotor", modelS.isDualMotor()),
                    set("plaid", modelS.isPlaid()),
                    set("price", modelS.getPrice()));
        }

        // Actualiza el documento
        collection.updateOne(filter, update);

        System.out.println("Datos actualizados exitosamente en MongoDB.");
    }

    // Muestra la lista de todos los vehiculos
    public String listVehicles() {
        MongoCollection<Document> collection = getCollection();

        int contVehicle = 1;
        StringBuilder listaVehiculos = new StringBuilder();
        // Itera sobre cada documento y muestra cada campo en una línea separada
        for (Document doc : collection.find()) {
            listaVehiculos.append("-------------- Vehicle " + contVehicle + " --------------\n");
            for (String key : doc.keySet()) {
                if (key.equalsIgnoreCase("price")) {
                    listaVehiculos
                            .append("- ")
                            .append(key)
                            .append(" = ")
                            .append(formatter.format(doc.get(key)))
                            .append("€\n");
                } else {
                    listaVehiculos
                            .append("- ")
                            .append(key)
                            .append(" = ")
                            .append(doc.get(key))
                            .append("\n");
                }
            }
            contVehicle++;
            listaVehiculos.append("\n"); // Añade una línea en blanco entre documentos para mayor claridad
        }
        return listaVehiculos.toString();
    }

    // Muestra la lista de todos los vehiculos
    public ArrayList<TeslaVehicle> listVehicles2() {
        MongoCollection<Document> collection = getCollection();

        ArrayList<TeslaVehicle> vehicleList = new ArrayList<>();

        // Itera sobre cada documento y lo convierte en el objeto adecuado
        for (Document doc : collection.find()) {
            // Atributos comunes a todos los TeslaVehicle
            ObjectId id = doc.getObjectId("_id");
            String email = doc.getString("email");
            String paint = doc.getString("paint");
            boolean enhancedAutopilot = doc.getBoolean("enhancedAutopilot", false);
            boolean fullSelfDrivingCapability = doc.getBoolean("fullSelfDrivingCapability", false);
            boolean wallConnectorCharge = doc.getBoolean("wallConnectorCharge", false);
            double price = doc.getDouble("price");
            String model = doc.getString("model");

            // Variables para los atributos específicos
            boolean dualMotor = doc.getBoolean("dualMotor", false); // ModelS y ModelX
            boolean plaid = doc.getBoolean("plaid", false);         // ModelS y ModelX

            boolean rearWheelDrive = doc.getBoolean("rearWheelDrive", false); // Model3 y ModelY
            boolean highAutonomy = doc.getBoolean("highAutonomy", false);     // Model3 y ModelY
            boolean performance = doc.getBoolean("performance", false);       // Model3 y ModelY

            // Crear la instancia correcta según el modelo
            TeslaVehicle vehicle;
            switch (model) {
                case "Model X":
                    vehicle = new ModelX(dualMotor, plaid, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                case "Model S":
                    vehicle = new ModelS(dualMotor, plaid, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                case "Model 3":
                    vehicle = new Model3(rearWheelDrive, highAutonomy, performance, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                case "Model Y":
                    vehicle = new ModelY(rearWheelDrive, highAutonomy, performance, id, email, paint, enhancedAutopilot, fullSelfDrivingCapability,
                            wallConnectorCharge, price, model);
                    break;
                default:
                    // Si no se reconoce el modelo, salta al siguiente documento
                    System.out.println("Modelo no reconocido: " + model);
                    continue;
            }

            // Agregar el vehículo a la lista
            vehicleList.add(vehicle);
        }

        return vehicleList;
    }

    // Método para eliminar un documento de la colección usando un objeto Vehicle
    public void deleteVehicle(ObjectId id) {
        MongoCollection<Document> collection = getCollection();

        // Elimina el documento que coincida con el email del Customer
        Document query = new Document("_id", id);
        collection.deleteOne(query);

        System.out.println("Datos eliminados exitosamente de MongoDB.");
    }

    public long countTotalVehicles() {
        MongoCollection<Document> collection = getCollection();

        // Cuenta el total de documentos en la colección
        return collection.countDocuments();
    }

    public long countVehiclesByModel(String model) {
        MongoCollection<Document> collection = getCollection();

        // Realiza la consulta para contar los documentos que coincidan con el modelo especificado
        long count = collection.countDocuments(new Document("model", model));

        return count;
    }

}
