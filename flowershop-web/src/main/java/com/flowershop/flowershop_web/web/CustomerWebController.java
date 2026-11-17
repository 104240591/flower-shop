package com.flowershop.flowershop_web.web;

import com.flowershop.flowershop_web.controller.CustomerController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customers")
public class CustomerWebController {

    private final CustomerController customerController = new CustomerController();

    @PostMapping("/register")
    public String registerCustomer(@RequestParam("customerId") String customerId,
                                   @RequestParam("fullName") String fullName,
                                   @RequestParam("phone") String phone,
                                   @RequestParam(value = "tier", defaultValue = "STANDARD") String tier,
                                   RedirectAttributes redirectAttributes) {
        boolean created = customerController.registerCustomer(customerId, fullName, phone, tier);
        if (created) {
            redirectAttributes.addFlashAttribute("successMessage", "Customer " + fullName + " registered successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to register customer. ID or phone may already exist.");
        }
        return "redirect:/";
    }
}