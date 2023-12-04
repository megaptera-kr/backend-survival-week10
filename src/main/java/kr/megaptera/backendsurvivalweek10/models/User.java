package kr.megaptera.backendsurvivalweek10.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "users")
public class User {
    @EmbeddedId
    private UserId userId;
    private String username;
    private String password;
    private String role;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User(UserId userId) {
        this.userId = userId;
    }

    public User() {

    }

    public static User create() {
        return new User(UserId.generate());
    }
}
