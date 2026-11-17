package com.flowershop.flowershop_web.web;



import com.flowershop.flowershop_web.controller.CustomerController;
import com.flowershop.flowershop_web.controller.OrderController;
import com.flowershop.flowershop_web.model.Customer;
import com.flowershop.flowershop_web.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainDashboardWebController {

    private final OrderController orderController = new OrderController();
    private final CustomerController customerController = new CustomerController();

    @GetMapping("/")
    public String showDashboard(Model model) {
        Staff currentStaff = new Staff("ST02", "Bob Florist", "bob", "FLORIST");
        Customer activeCustomer = customerController.getCustomerById("CUST01");
        if (activeCustomer == null) {
            activeCustomer = new Customer("CUST01", "Jane Doe", "0987654321", "SILVER", 150.00);
        }

        model.addAttribute("staff", currentStaff);
        model.addAttribute("customer", activeCustomer);
        model.addAttribute("flowers", orderController.loadAvailableInventory());
        return "dashboard";
    }
}