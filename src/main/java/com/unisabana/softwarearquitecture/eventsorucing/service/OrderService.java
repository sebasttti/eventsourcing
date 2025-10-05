package com.unisabana.softwarearquitecture.eventsorucing.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unisabana.softwarearquitecture.eventsorucing.entity.Order;
import com.unisabana.softwarearquitecture.eventsorucing.repository.OrderRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    public OrderService(OrderRepository orderRepository, ObjectMapper objectMapper){
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
    }

    // Guardar una orden
    public Order saveOrder(Order order) {
        try {
            Order orderCreated =  orderRepository.save(order);

            //Aca simulo un EventListener o un Observer para poblar el Real Model
            restoreRealModel();

            return orderCreated;


        } catch (DataAccessException e) {
            e.printStackTrace(); // imprime la traza completa en consola
            return null;
        }
    }

    // Obtener todas las órdenes del Event Store
    public List<Order> listOrdersFromEventStore() {
        try {
            return orderRepository.findAll();
        } catch (DataAccessException e) {
            e.printStackTrace(); // imprime la traza completa en consola
            return null;
        }
    }

    // Obtener todas las ordenes del Real Model
    public List<Order> listOrdersFromRealModel(){

        try {

            File jsonFile = new File("storage/data/real_model.json");

            // Aseguramos que el archivo exista
            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, List.of());
            }

            // Leer JSON y mapear a lista de Order
            List<Order> orders = objectMapper.readValue(jsonFile, new TypeReference<List<Order>>() {
            });

            return orders;

        }catch (IOException e){
            System.out.println("Paso por aca");
            e.printStackTrace();
            return List.of();
        }
    }

    public void deleteRealModel(){
        try{
            File jsonFile = new File("storage/data/real_model.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, List.of());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void restoreRealModel(){
        try {
            // 1. Obtener todas las órdenes desde la BD
            List<Order> orders = orderRepository.findAll();

            // 2. Definir la ruta del archivo JSON
            File jsonFile = new File("storage/data/real_model.json");

            // 3. Escribir la lista en formato JSON con formato bonito
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, orders);


        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
