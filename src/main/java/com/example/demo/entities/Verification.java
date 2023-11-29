package com.example.demo.entities;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "verification")
public class Verification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean verified;

    private Date verifiedDate;

    private String token;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userID")
    @JsonBackReference(value = "user-verification")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}
