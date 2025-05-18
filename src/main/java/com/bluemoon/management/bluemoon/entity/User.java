package com.bluemoon.management.bluemoon.entity;

import com.bluemoon.management.bluemoon.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import com.bluemoon.management.bluemoon.enums.UserRole;
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

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "user_role", columnDefinition = "user_role not null")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ColumnDefault("'Active'")
    @Column(name = "account_status", columnDefinition = "account_status not null")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

}