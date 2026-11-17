package com.flowershop.flowershop_web.web;


import com.flowershop.flowershop_web.controller.InventoryController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventory")
public class InventoryWebController {

    private final InventoryController inventoryController = new InventoryController();

    @GetMapping
    public String viewInventoryReport(Model model) {
        model.addAttribute("allFlowers", inventoryController.getAllFlowers());
        model.addAttribute("lowStockFlowers", inventoryController.getLowStockFlowers(20));
        model.addAttribute("expiredFlowers", inventoryController.getExpiredFlowers());
        return "dashboard";
    }
}