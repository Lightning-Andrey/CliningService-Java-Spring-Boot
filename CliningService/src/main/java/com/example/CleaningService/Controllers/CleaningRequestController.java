package com.example.CleaningService.Controllers;

import com.example.CleaningService.Models.*;
import com.example.CleaningService.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class CleaningRequestController {

    @Autowired
    private CleaningRequestRepository cleaningRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/table-requests")
    public String showCleaningRequests(Model model) {

        List<CleaningRequest> requests = cleaningRequestRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();
        List<Inventory> inventories = inventoryRepository.findAll();
        List<String> statusList = Arrays.asList("В обработке", "На согласовании", "Назначено", "Завершён");

        model.addAttribute("requests", requests);
        model.addAttribute("employees", employees);
        model.addAttribute("inventories", inventories);
        model.addAttribute("statusList", statusList);

        return "table-requests";
    }

    @GetMapping("/edit-request/{id}")
    public String editRequest(@PathVariable("id") long id, Model model) {
        CleaningRequest request = cleaningRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        List<Employee> employees = employeeRepository.findAll();
        List<Inventory> inventories = inventoryRepository.findAll();
        List<String> statusList = Arrays.asList("В обработке", "На согласовании", "Назначено", "Завершён");

        model.addAttribute("request", request);
        model.addAttribute("employees", employees);
        model.addAttribute("inventories", inventories);
        model.addAttribute("statusList", statusList);

        return "edit-request";
    }

    @GetMapping("/delete-request/{id}")
    public String deleteRequest(@PathVariable("id") long id) {
        CleaningRequest request = cleaningRequestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        cleaningRequestRepository.delete(request);
        return "redirect:/table-requests";
    }
}
