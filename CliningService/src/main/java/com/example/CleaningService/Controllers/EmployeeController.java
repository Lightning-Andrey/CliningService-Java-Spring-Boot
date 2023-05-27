package com.example.CleaningService.Controllers;

import com.example.CleaningService.Models.Employee;
import com.example.CleaningService.Models.Service;
import com.example.CleaningService.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/table-employees")
    public String showEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "table-employees";
    }

    @GetMapping("/addEmployee")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/table-employees";
    }

    @GetMapping("/edit-employee/{id}")
    public String showEditEmployeeForm(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "edit-employee";
    }

    @PostMapping("/edit-employee")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/table-employees";
    }

    @GetMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid service Id:" + id));
        employeeRepository.delete(employee);
        return "redirect:/table-employees";
    }
}
