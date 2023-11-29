package com.example.demo.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "user")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    @Size(max = 100)
    private String contact;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;

    private String sex;

    private String bio;

    private String link;

    private String profileImage;

    private Date joinedDate;


    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonManagedReference(value = "user-post")
    private List<Post> post;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonManagedReference(value = "user-comments")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonManagedReference(value = "user-likes")
    private List<Like> likes;

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    @JsonManagedReference(value = "user-followers")
    private List<Follow> followers;

    @OneToMany(mappedBy = "follower", orphanRemoval = true)
    @JsonManagedReference(value = "user-following")
    private List<Follow> following;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    @JsonManagedReference(value = "user-verification")
    private Verification verification;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
