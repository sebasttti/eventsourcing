package com.unisabana.softwarearquitecture.eventsorucing.controller;

import com.unisabana.softwarearquitecture.eventsorucing.entity.Order;
import com.unisabana.softwarearquitecture.eventsorucing.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/list-orders-from-real-model")
    public String listOrdersFromRealModel(Model model){

        List<Order> orders = orderService.listOrdersFromRealModel();

        model.addAttribute("orders",orders);

        return "list-orders";
    }

    @GetMapping("/list-orders-from-event-store")
    public String listOrdersFromEventStore(Model model){

        List<Order> orders = orderService.listOrdersFromEventStore();

        model.addAttribute("orders",orders);

        return "list-orders";
    }

    @GetMapping("/add-order")
    public String showAddOrderForm(Model model) {
        model.addAttribute("order", new Order()); // objeto vacío para el formulario
        return "add-order"; // tu página Thymeleaf
    }

    @PostMapping("/add-order")
    public String addOrder(@ModelAttribute Order order, Model model) {
        Order createdOrder = orderService.saveOrder(order);
        model.addAttribute("order", createdOrder != null ? createdOrder : order);
        model.addAttribute("success", createdOrder != null);
        return "add-order";
    }

    @GetMapping("/delete-real-model")
    public String deleteRealModel(){

        orderService.deleteRealModel();

        return "delete-real-model";
    }

    @GetMapping("/restore-real-model")
    public String restoreRealModel(){

        orderService.restoreRealModel();

        return "restore-real-model";
    }
}
