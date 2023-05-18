package com.example.CleaningService.Repositories;

import com.example.CleaningService.Models.CleaningRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CleaningRequestRepository extends JpaRepository<CleaningRequest, Long> {
}
