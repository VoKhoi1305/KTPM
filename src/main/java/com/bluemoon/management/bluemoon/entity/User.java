package com.bluemoon.management.bluemoon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(name = "fullname", nullable = false, length = 100)
    private String fullname;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private String userRole;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "avatar_url")
    private String avatarUrl;
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

/*
 TODO [Reverse Engineering] create field to map the 'user_role' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "user_role", columnDefinition = "user_role not null")
    private Object userRole;
*/
/*
 TODO [Reverse Engineering] create field to map the 'account_status' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("'Active'")
    @Column(name = "account_status", columnDefinition = "account_status not null")
    private Object accountStatus;
*/
}