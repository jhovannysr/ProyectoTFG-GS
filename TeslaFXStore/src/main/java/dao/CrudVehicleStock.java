package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import model.VehicleStock;
import org.bson.Document;
import java.util.List;

public class CrudVehicleStock {

    private static final String DATABASE_NAME = "TeslaFXStoreDB";
    private static final String COLLECTION_NAME = "vehicle_stock";

    // Método privado para obtener la colección de MongoDB
    private static MongoCollection<Document> getCollection() {
        MongoDatabase database = Conexion.getMongoClient().getDatabase(DATABASE_NAME);
        return database.getCollection(COLLECTION_NAME);
    }

    // Método para leer el stock de todos los modelos
    public String listStock() {
        StringBuilder result = new StringBuilder();
        Document doc = getCollection().find().first();

        // Si el documento tiene contenido
        if (doc != null) {
            List<Document> models = (List<Document>) doc.get("models");
            // Verifica si la lista de modelos no es nula, es decir, si contiene datos.
            if (models != null) {
                for (Document modelDoc : models) {
                    String model = modelDoc.getString("model");
                    Long stockLong = modelDoc.getLong("stock");
                    int stock = (stockLong != null) ? stockLong.intValue() : 0;
                    result.append("- ").append(model).append(" = ").append(stock).append("\n");
                }
            }
        }

        return result.toString();
    }

    public VehicleStock findStock(String model) {
        // Obtiene el primer documento de la colección mediante una consulta 'find()'.
        Document doc = getCollection().find().first();

        // Verifica si el documento no es nulo, es decir, si se encontró algún documento.
        if (doc != null) {
            // Extrae la lista de documentos anidada bajo la clave "models".
            List<Document> models = (List<Document>) doc.get("models");
            // Verifica si la lista de modelos no es nula, es decir, si contiene datos.
            if (models != null) {
                // Itera sobre cada documento en la lista de modelos.
                for (Document modelDoc : models) {
                    // Obtiene el nombre del modelo como una cadena desde el documento actual.
                    String modelName = modelDoc.getString("model");
                    // Compara el nombre del modelo actual con el modelo que se busca.
                    if (modelName.equals(model)) {
                        // Obtiene el stock como un valor Long desde el documento actual.
                        Long stockLong = modelDoc.getLong("stock");
                        // Convierte el stock de Long a int, manejando el caso en el que el stock podría ser nulo.
                        int stock = (stockLong != null) ? stockLong.intValue() : 0;
                        // Crea y devuelve un objeto VehicleStock con el modelo y el stock encontrados.
                        return new VehicleStock(modelName, stock);
                    }
                }
            }
        }

        // Si no se encuentra el modelo, devuelve null o podría lanzar una excepción.
        return null;
    }

    // Método para actualizar el stock de un modelo específico
    public void updateStock(VehicleStock vehicleStock) {
        Document doc = getCollection().find().first();
        if (doc != null) {
            List<Document> models = (List<Document>) doc.get("models");
            if (models != null) {
                boolean updated = false;
                for (Document modelDoc : models) {
                    String model = modelDoc.getString("model");
                    if (model.equals(vehicleStock.getModel())) {
                        modelDoc.put("stock", (long) vehicleStock.getStock());
                        updated = true;
                        break;
                    }
                }
                if (updated) {
                    Document updatedDoc = new Document("models", models);
                    getCollection().replaceOne(Filters.eq("_id", doc.getObjectId("_id")), updatedDoc);
                    System.out.println("Stock actualizado para el modelo: " + vehicleStock.getModel());
                } else {
                    System.out.println("El modelo " + vehicleStock.getModel() + " no se encuentra en el stock.");
                }
            }
        } else {
            System.out.println("No se encontró el documento de stock.");
        }
    }

    // Método para agregar un nuevo modelo al stock
    public void addVehicleModel(VehicleStock vehicleStock) {
        Document doc = getCollection().find().first();
        if (doc != null) {
            List<Document> models = (List<Document>) doc.get("models");
            if (models != null) {
                boolean modelExists = false;
                for (Document modelDoc : models) {
                    String model = modelDoc.getString("model");
                    if (model.equals(vehicleStock.getModel())) {
                        modelDoc.put("stock", (long) vehicleStock.getStock());
                        modelExists = true;
                        break;
                    }
                }
                if (!modelExists) {
                    models.add(new Document("model", vehicleStock.getModel())
                            .append("stock", (long) vehicleStock.getStock()));
                }

                Document updatedDoc = new Document("models", models);
                getCollection().replaceOne(Filters.eq("_id", doc.getObjectId("_id")), updatedDoc);

                System.out.println("Modelo agregado o actualizado: " + vehicleStock.getModel());
            }
        } else {
            System.out.println("No se encontró el documento de stock.");
        }
    }

    // Método para eliminar un modelo del stock
    public void deleteVehicleModel(String modelName) {
        Document doc = getCollection().find().first();
        if (doc != null) {
            List<Document> models = (List<Document>) doc.get("models");
            if (models != null) {
                models.removeIf(modelDoc -> modelDoc.getString("model").equals(modelName));

                Document updatedDoc = new Document("models", models);
                getCollection().replaceOne(Filters.eq("_id", doc.getObjectId("_id")), updatedDoc);

                System.out.println("Modelo eliminado: " + modelName);
            } else {
                System.out.println("No se encontró el documento de modelos.");
            }
        } else {
            System.out.println("No se encontró el documento de stock.");
        }
    }

    public long getTotalVehicleStock() {
        MongoCollection<Document> collection = getCollection();

        // Encuentra el único documento de vehicle_stock
        Document stockDocument = collection.find().first();

        if (stockDocument != null) {
            // Extrae el array de modelos
            ArrayList<Document> models = (ArrayList<Document>) stockDocument.get("models");

            long totalStock = 0;

            // Suma el stock de cada modelo en el array
            for (Document model : models) {
                Long stock = model.get("stock", Long.class); // Obtiene el stock como Long
                if (stock != null) {
                    totalStock += stock; // Suma el stock al total
                }
            }

            return totalStock;
        } else {
            System.err.println("No se encontró el documento de stock.");
            return 0;
        }
    }

    // Método para cerrar la conexión - puede estar en la clase Conexion si se gestiona ahí
    public void close() {
        Conexion.getMongoClient().close();
    }
}
