package com.example.CleaningService.Repositories;

import com.example.CleaningService.Models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}

