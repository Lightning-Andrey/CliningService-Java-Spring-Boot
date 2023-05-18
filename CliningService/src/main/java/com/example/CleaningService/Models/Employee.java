package com.example.CleaningService.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "employee")
    private List<CleaningRequest> assignedRequests;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CleaningRequest> getAssignedRequests() {
        return assignedRequests;
    }

    public void setAssignedRequests(List<CleaningRequest> assignedRequests) {
        this.assignedRequests = assignedRequests;
    }
}