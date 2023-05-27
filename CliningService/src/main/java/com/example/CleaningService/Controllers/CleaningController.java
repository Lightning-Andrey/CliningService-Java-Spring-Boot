package com.example.CleaningService.Controllers;

import com.example.CleaningService.Models.CleaningRequest;
import com.example.CleaningService.Models.Comment;
import com.example.CleaningService.Models.Service;
import com.example.CleaningService.Models.User;
import com.example.CleaningService.Repositories.CleaningRequestRepository;
import com.example.CleaningService.Repositories.CommentRepository;
import com.example.CleaningService.Repositories.ServiceRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("user")
public class CleaningController {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CleaningRequestRepository cleaningRequestRepository;

    @GetMapping("/request-cleaning")
    public String showRequestCleaningForm(@RequestParam(value = "serviceId", required = false) Integer serviceId, Model model) {
        List<Service> services = serviceRepository.findAll();
        model.addAttribute("services", services);
        if (serviceId != null) {
            Service selectedService = serviceRepository.findById(serviceId).orElse(null);
            model.addAttribute("selectedService", selectedService);
        }
        return "/request-cleaning";
    }

    @PostMapping("/submitRequest")
    public String submitCleaningRequest(@RequestParam("serviceId") int serviceId,
                                        @RequestParam("dateTime") String dateTime,
                                        @ModelAttribute("user") User user,
                                        HttpSession session) {

        CleaningRequest request = new CleaningRequest();
        Service service = serviceRepository.findById(serviceId).orElse(null);
        if (service == null) {
            return "redirect:/request-cleaning";
        }

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }

        request.setService(service);
        request.setDateTime(dateTime);
        request.setUser(sessionUser);
        request.setStatus("Принят");
        cleaningRequestRepository.save(request);

        return "redirect:/user-requests";
    }

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/user-requests")
    public String showPreviousRequests(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        } else {
            List<CleaningRequest> userRequests = cleaningRequestRepository.findByUser(user);
            model.addAttribute("requests", userRequests);

            Map<CleaningRequest, List<Comment>> commentsForRequests = new HashMap<>();
            for (CleaningRequest request : userRequests) {
                List<Comment> comments = commentRepository.findByCleaningRequest(request);
                commentsForRequests.put(request, comments);
            }
            model.addAttribute("commentsForRequests", commentsForRequests);

            return "user-requests";
        }
    }

    @PostMapping("/add-comment")
    public String addComment(@RequestParam("requestId") int requestId,
                             @RequestParam("content") String content,
                             @ModelAttribute("user") User user) {

        CleaningRequest request = cleaningRequestRepository.findById((long) requestId).orElse(null);
        if (request == null) {
            return "redirect:/user-requests";
        }

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setDateTime(LocalDateTime.now());
        comment.setUser(user);
        comment.setCleaningRequest(request);
        commentRepository.save(comment);

        return "redirect:/user-requests";
    }

}
