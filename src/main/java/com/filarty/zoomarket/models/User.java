package com.filarty.zoomarket.models;

import com.filarty.zoomarket.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    @Column(name = "enabled")
    private boolean isEnabled;
    @Column(name = "role")
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> userRole = new HashSet<>();

    public User() {
        super();
        this.isEnabled = false;
    }


    boolean isAdmin() {
        return userRole.contains(UserRole.ADMIN);
    }

}
