/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.*;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author jhova
 */
public class CrudCustomerAdministrator {
    
     private static final String DATABASE_NAME = "TeslaFXStoreDB";
    private static final String COLLECTION_NAME = "customer_administrator";

    // Método privado para obtener la colección de MongoDB, en caso de que no cambie la coleccion
    private static MongoCollection<Document> getCollection() {
        MongoDatabase database = Conexion.getMongoClient().getDatabase(DATABASE_NAME);
        return database.getCollection(COLLECTION_NAME);
    }
    
    public CustomerAdministrator findCustomerAdministrator(String email) {
        MongoCollection<Document> collection = getCollection();

        // Crea una consulta para encontrar un documento basado en el campo email
        Document query = new Document("email", email);
        Document result = collection.find(query).first();

        if (result != null) {
            // Extrae los datos del Document y crea un objeto Customer
            ObjectId id = result.getObjectId("_id");
            String name = result.getString("name");
            String password = result.getString("password"); // Asegúrate de que el campo password también esté en el documento

            // Crear y devolver un objeto Customer
            CustomerAdministrator admin = new CustomerAdministrator(id, email, name, password);
            return admin;
        } else {
            return null; // O lanza una excepción si prefieres manejarlo de otra manera
        }
    }
}
