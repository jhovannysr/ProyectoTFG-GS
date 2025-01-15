package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class CrudCustomer {

    // Formato miles, crear un DecimalFormat con el patrón de formato deseado
    DecimalFormat formatter = new DecimalFormat("#,###");

    private static final String DATABASE_NAME = "TeslaFXStoreDB";
    private static final String COLLECTION_NAME = "customer";

    // Método privado para obtener la colección de MongoDB, en caso de que no cambie la coleccion
    private static MongoCollection<Document> getCollection() {
        MongoDatabase database = Conexion.getMongoClient().getDatabase(DATABASE_NAME);
        return database.getCollection(COLLECTION_NAME);
    }

    // Método para añadir datos a la colección usando un objeto Customer
    public void saveCustomer(Customer customer) {
        MongoCollection<Document> collection = getCollection();

        // Crea un documento a partir del objeto Customer y lo inserta en la colección
        Document document = new Document("customerName", customer.getCustomerName())
                .append("country", customer.getCountry())
                .append("age", customer.getAge())
                .append("email", customer.getEmail())
                .append("password", customer.getPassword());
        collection.insertOne(document);

        System.out.println("Datos guardados exitosamente en MongoDB.");
    }

    // Método para buscar un documento en la colección usando un objeto Customer
    public Customer findCustomer(String email) {
        MongoCollection<Document> collection = getCollection();

        // Crea una consulta para encontrar un documento basado en el campo email
        Document query = new Document("email", email);
        Document result = collection.find(query).first();

        if (result != null) {
            // Extrae los datos del Document y crea un objeto Customer
            ObjectId id = result.getObjectId("_id");
            String customerName = result.getString("customerName");
            String country = result.getString("country");
            String age = result.getString("age");
            String password = result.getString("password"); // Asegúrate de que el campo password también esté en el documento

            // Crear y devolver un objeto Customer
            return new Customer(id, customerName, country, age, email, password);
        } else {
            return null; // O lanza una excepción si prefieres manejarlo de otra manera
        }
    }

    // Método para buscar un documento en la colección usando el _id
    public Customer findCustomerById(ObjectId id) {
        MongoCollection<Document> collection = getCollection();

        // Crea una consulta para encontrar un documento basado en el campo _id
        Document query = new Document("_id", id);
        Document result = collection.find(query).first();

        if (result != null) {
            // Extrae los datos del Document y crea un objeto Customer
            String customerName = result.getString("customerName");
            String country = result.getString("country");
            String age = result.getString("age");
            String email = result.getString("email"); // Campo email en el documento
            String password = result.getString("password"); // Campo password en el documento

            // Crear y devolver un objeto Customer
            return new Customer(id, customerName, country, age, email, password);
        } else {
            return null; // O lanza una excepción si prefieres manejarlo de otra manera
        }
    }

    // Método para eliminar un documento de la colección usando un objeto Customer
    public void deleteCustomer(String email) {
        MongoCollection<Document> collection = getCollection();

        // Elimina el documento que coincida con el email del Customer
        Document query = new Document("email", email);
        collection.deleteOne(query);

        System.out.println("Datos eliminados exitosamente de MongoDB.");
    }

    public String listCustomers() {
        MongoCollection<Document> collection = getCollection();

        int contCustomer = 1;
        StringBuilder listaBuilder = new StringBuilder();

        // Itera sobre cada documento y lo convierte en un objeto Customer
        for (Document doc : collection.find()) {
            listaBuilder.append("------------- Customer " + contCustomer + " -------------\n");
            for (String key : doc.keySet()) {
                listaBuilder
                        .append("- ")
                        .append(key)
                        .append(" = ")
                        .append(doc.get(key))
                        .append("\n");
            }
            listaBuilder.append("\n");
            contCustomer++;
//            listaBuilder.append("------------- Customer "+contCustomer+" -------------\n");
        }
        return listaBuilder.toString();
    }

    public ArrayList<Customer> listCustomers2() {
        MongoCollection<Document> collection = getCollection();

        ArrayList<Customer> customerList = new ArrayList<>();

        // Itera sobre cada documento y lo convierte en un objeto Customer
        for (Document doc : collection.find()) {
            ObjectId id = doc.getObjectId("_id"); // Obtén el ObjectId del documento
            String customerName = doc.getString("customerName");
            String country = doc.getString("country");
            String age = doc.getString("age");
            String email = doc.getString("email");
            String password = doc.getString("password");

            // Crea una nueva instancia de Customer y agrégala a la lista
            Customer customer = new Customer(id, customerName, country, age, email, password);
            customerList.add(customer);
        }

        return customerList;
    }

    // Método para modificar un documento en la colección usando un objeto Customer
    public void updateCustomer(Customer customer) {
        MongoCollection<Document> collection = getCollection();

        // Crea una consulta para encontrar el documento basado en el email
        Bson filter = eq("email", customer.getEmail());

        // Crea una actualización combinada para modificar los campos del documento
        Bson update = combine(
                set("customerName", customer.getCustomerName()),
                set("country", customer.getCountry()),
                set("age", customer.getAge()),
                set("email", customer.getEmail()),
                set("password", customer.getPassword())
        );

        // Actualiza el documento
        collection.updateOne(filter, update);

        System.out.println("Datos actualizados exitosamente en MongoDB.");
    }

    // Método para modificar un documento en la colección usando un objeto Customer
    public void updateCustomerAdmin(Customer customer, String email) {
        MongoCollection<Document> collection = getCollection();

        // Crea una consulta para encontrar el documento basado en el email
        Bson filter = eq("email", email);

        // Crea una actualización combinada para modificar los campos del documento
        Bson update = combine(
                set("customerName", customer.getCustomerName()),
                set("country", customer.getCountry()),
                set("age", customer.getAge()),
                set("email", customer.getEmail()),
                set("password", customer.getPassword())
        );

        // Actualiza el documento
        collection.updateOne(filter, update);

        System.out.println("Datos actualizados exitosamente en MongoDB.");
    }

    // Muestra tabla de clienetes por pais
    public ArrayList<Customer> findCustomersByCountry(String country) {
        MongoCollection<Document> collection = getCollection();

        // Crea una consulta insensible a mayúsculas y minúsculas
        Document query = new Document("country", new Document("$regex", "^" + country + "$").append("$options", "i"));
        ArrayList<Customer> customers = new ArrayList<>();

        for (Document result : collection.find(query)) {
            // Extrae los datos del Document y crea un objeto Customer
            ObjectId id = result.getObjectId("_id");
            String customerName = result.getString("customerName");
            String email = result.getString("email");
            String age = result.getString("age");
            String password = result.getString("password");

            // Agrega el objeto Customer a la lista
            customers.add(new Customer(id, customerName, country, age, email, password));
        }

        return customers;
    }

    // Muestra tabla de los clientes por rango de edad
    public ArrayList<Customer> findCustomersByAgeRange(String ageRangeInput) {
        // Valida el formato del rango de edades
//        if (!ageRangeInput.matches("\\d+\\s*-\\s*\\d+")) {
//            // Retorna un ArrayList vacío si el formato no es válido
//            return new ArrayList<>();
//        }
        String[] ages;
        int minAge;
        int maxAge;

        // Extrae los valores del rango de edades
        try {
            ages = ageRangeInput.replaceAll("\\s", "").split("-");
            minAge = Integer.parseInt(ages[0]);
            maxAge = Integer.parseInt(ages[1]);
        } catch (Exception e) {
            return new ArrayList<Customer>();
        }
        System.out.println("MinAge: " + minAge);
        System.out.println("MaxAge: " + maxAge);

        MongoCollection<Document> collection = getCollection();

        // Crea una consulta para encontrar documentos con un rango de edad
        Document query = new Document("$expr", new Document("$and", List.of(
                new Document("$gte", List.of(new Document("$toInt", "$age"), minAge)),
                new Document("$lte", List.of(new Document("$toInt", "$age"), maxAge))
        )));

        System.out.println(query);
        ArrayList<Customer> customers = new ArrayList<>();

        for (Document result : collection.find(query)) {
            // Extrae los datos del Document y crea un objeto Customer
            ObjectId id = result.getObjectId("_id");
            String customerName = result.getString("customerName");
            String country = result.getString("country");
            String email = result.getString("email");
            String age = result.getString("age"); // Obtiene la edad como un entero, valor por defecto 0
            String password = result.getString("password");

            // Agrega el objeto Customer a la lista
            customers.add(new Customer(id, customerName, country, String.valueOf(age), email, password));
        }

        return customers;
    }

    public long countTotalCustomers() {
        MongoCollection<Document> collection = getCollection();

        // Utiliza el método countDocuments para obtener el número total de documentos
        return collection.countDocuments();
    }

}
