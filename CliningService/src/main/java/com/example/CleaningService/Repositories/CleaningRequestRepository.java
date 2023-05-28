package com.example.CleaningService.Repositories;

import com.example.CleaningService.Models.CleaningRequest;
import com.example.CleaningService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CleaningRequestRepository extends JpaRepository<CleaningRequest, Long> {

    List<CleaningRequest> findByUserAndStatus(User user, String status);

    List<CleaningRequest> findByUserAndStatusNot(User user, String status);


}
