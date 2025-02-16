package dao;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

// Singletone
public class Conexion {

    /**
     * Ejemplo de URI (reemplazar con variables de entorno en proyectos reales)
     */
    static final String URI = "mongodb+srv://usuario:contraseña@cluster.mongodb.net/";
    private static final MongoClient mongoClient;

    static {
        ConnectionString connectionString = new ConnectionString(URI);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .retryWrites(true)
                .build();
        mongoClient = MongoClients.create(settings);
    }
    
    public static MongoClient getMongoClient() {
        return mongoClient;
    }
    
    // Cierra la conexión a la base de datos
    public static void close() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
                System.out.println("Conexión a MongoDB cerrada.");
            } catch (Exception e) {
                e.printStackTrace(); // Manejo de excepciones
            }
        }
    }
}
