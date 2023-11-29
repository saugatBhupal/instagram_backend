package com.example.demo.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.val;

@Entity
@Data
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date followedDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference(value = "user-following")
    private User follower; 

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference(value = "user-followers")
    private User owner;


}
