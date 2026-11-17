package com.flowershop.flowershop_web.web;

import com.flowershop.flowershop_web.controller.*;
import com.flowershop.flowershop_web.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderWebController {

    private final OrderController orderController = new OrderController();
    private final CustomerController customerController = new CustomerController();

    @PostMapping("/checkout")
    public String handleCheckout(@RequestParam("flowerId") String flowerId,
                                 @RequestParam("quantity") int quantity,
                                 RedirectAttributes redirectAttributes) {

        List<Flower> inventory = orderController.loadAvailableInventory();
        Flower selectedFlower = inventory.stream()
                .filter(f -> f.getId().equals(flowerId))
                .findFirst()
                .orElse(null);

        if (selectedFlower == null || quantity <= 0) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid flower item or quantity.");
            return "redirect:/";
        }

        if (quantity > selectedFlower.getStockQty()) {
            redirectAttributes.addFlashAttribute("errorMessage",
                "Requested quantity exceeds available stock (" + selectedFlower.getStockQty() + ").");
            return "redirect:/";
        }

        Customer activeCustomer = customerController.getCustomerById("CUST01");
        if (activeCustomer == null) {
            activeCustomer = new Customer("CUST01", "Jane Doe", "0987654321", "SILVER", 150.00);
        }
        Staff currentStaff = new Staff("ST02", "Bob Florist", "bob", "FLORIST");

        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(selectedFlower, quantity));

        boolean success = orderController.submitOrder(activeCustomer, currentStaff, items);

        if (success) {
            redirectAttributes.addFlashAttribute("successMessage",
                "Order completed successfully! Deducted " + quantity + " stems of " + selectedFlower.getName() + ".");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Transaction failed during database execution.");
        }

        return "redirect:/";
    }
}