package com.example.CleaningService.Controllers;

import com.example.CleaningService.Models.CleaningRequest;
import com.example.CleaningService.Models.Employee;
import com.example.CleaningService.Models.Inventory;
import com.example.CleaningService.Repositories.CleaningRequestRepository;
import com.example.CleaningService.Repositories.EmployeeRepository;
import com.example.CleaningService.Repositories.InventoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RequestsController {
    private final CleaningRequestRepository cleaningRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final InventoryRepository inventoryRepository;

    public RequestsController(CleaningRequestRepository cleaningRequestRepository,
                              EmployeeRepository employeeRepository,
                              InventoryRepository inventoryRepository) {
        this.cleaningRequestRepository = cleaningRequestRepository;
        this.employeeRepository = employeeRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/table-requests")
    public String getAllRequests(Model model) {
        List<CleaningRequest> requests = cleaningRequestRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();
        List<Inventory> inventories = inventoryRepository.findAll();
        model.addAttribute("requests", requests);
        model.addAttribute("employees", employees);
        model.addAttribute("inventories", inventories);
        return "table-requests";
    }

    @PostMapping("/editRequest/{id}")
    public String editRequest(@PathVariable("id") int id, @ModelAttribute("request") CleaningRequest request) {
        CleaningRequest existingRequest = cleaningRequestRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        existingRequest.setStatus(request.getStatus());
        cleaningRequestRepository.save(existingRequest);
        return "redirect:/table-requests";
    }

    @GetMapping("/editRequest/{id}")
    public String getEditRequestForm(@PathVariable("id") int id, Model model) {
        CleaningRequest existingRequest = cleaningRequestRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        model.addAttribute("request", existingRequest);
        return "editRequest";
    }

    @GetMapping("/deleteRequest/{id}")
    public String deleteRequest(@PathVariable("id") int id) {
        CleaningRequest request = cleaningRequestRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        cleaningRequestRepository.delete(request);
        return "redirect:/table-requests";
    }
}
