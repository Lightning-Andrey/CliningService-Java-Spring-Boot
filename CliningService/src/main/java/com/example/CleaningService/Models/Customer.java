package com.example.CleaningService.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    private List<CleaningRequest> requests;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private Discount discount;

    public List<CleaningRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<CleaningRequest> requests) {
        this.requests = requests;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}