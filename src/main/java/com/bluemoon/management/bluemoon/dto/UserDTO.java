package com.bluemoon.management.bluemoon.dto;


import com.bluemoon.management.bluemoon.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.bluemoon.management.bluemoon.enums.UserRole;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

    public class UserDTO { // For API responses, excluding password
        private Integer userId;
        private String username;
        private String fullName;
        private UserRole role;
        private String email;
        private String phoneNumber;
        private String password;
        private String avatarURL;
        private AccountStatus accountStatus;

    }
