/**
 * Módulo de la aplicación TeslaFXStore.
 * 
 * Este módulo define los paquetes que forman parte de la aplicación y sus dependencias.
 */
module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.base; 
    requires java.desktop;
    // Requiere el módulo del driver MongoDB
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;

    opens controller to javafx.fxml;
    opens controller.administrator to javafx.fxml;
    
    exports app;
    exports controller;
    exports controller.administrator;
    exports dao;
    exports model;
    exports model.vehicle;
    exports util;
    exports vehiclePrices;
}
