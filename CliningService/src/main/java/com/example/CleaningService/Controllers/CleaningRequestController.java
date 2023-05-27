package com.example.CleaningService.Controllers;

import com.example.CleaningService.Models.*;
import com.example.CleaningService.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CleaningRequestController {

    @Autowired
    private CleaningRequestRepository cleaningRequestRepository;

    @GetMapping("/table-requests")
    public String showCleaningRequests(Model model) {

        List<CleaningRequest> requests = cleaningRequestRepository.findAll();
        model.addAttribute("requests", requests);
        return "table-requests";
    }

    @PostMapping("/edit-request")
    public String updateRequest(@ModelAttribute CleaningRequest request) {
        cleaningRequestRepository.save(request);
        return "redirect:/table-requests";
    }

    @GetMapping("/delete-request/{id}")
    public String deleteRequest(@PathVariable("id") long id) {
        CleaningRequest request = cleaningRequestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid request Id:" + id));
        cleaningRequestRepository.delete(request);
        return "redirect:/table-requests";
    }
}
