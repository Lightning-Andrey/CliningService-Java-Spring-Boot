package com.example.CleaningService.Repositories;

import com.example.CleaningService.Models.Employee;
import com.example.CleaningService.Models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
