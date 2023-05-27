package com.example.CleaningService.Repositories;

import com.example.CleaningService.Models.CleaningRequest;
import com.example.CleaningService.Models.Employee;
import com.example.CleaningService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
