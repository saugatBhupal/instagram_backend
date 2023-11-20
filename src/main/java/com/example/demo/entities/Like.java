package com.example.demo.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LikeDate")
    private Date likeDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "postID")
    @JsonBackReference
    private Post post;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userID")
    @JsonBackReference
    private User user;

}
