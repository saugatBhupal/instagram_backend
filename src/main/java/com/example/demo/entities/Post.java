package com.example.demo.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "post")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Content;

    @Column(nullable = false)
    private String caption;

    @Column(nullable = false)
    private Date postDate;

    @Column(nullable = true)
    private String location;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userID")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    @JsonManagedReference
    private List<Like> likes;

}
