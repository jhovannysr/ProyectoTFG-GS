package model;

import org.bson.types.ObjectId;

public class Customer {

    /**
     * Atributos
     */
    private ObjectId id;
    private String customerName;
    private String country;
    private String age;
    private String email, password;

    /**
     * Constructores
     */
    public Customer() {
        super();
    }

    public Customer(String customerName, String country, String age, String email, String password) {
        super();
        this.customerName = customerName;
        this.country = country;
        this.age = age;
        this.email = email;
        this.password = password;

    }

    public Customer(ObjectId id, String customerName, String country, String age, String email, String password) {
        this.id = id;
        this.customerName = customerName;
        this.country = country;
        this.age = age;
        this.email = email;
        this.password = password;
    }


    /**
     * Getters & Setters
     *
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "- _id = " + id 
                + "\n- customerName = " + customerName 
                + "\n- country = " + country 
                + "\n- age = " + age 
                + "\n- email = " + email 
                + "\n- password = " + password;
    }

}
