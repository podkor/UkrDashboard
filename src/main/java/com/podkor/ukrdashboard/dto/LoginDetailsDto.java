package com.podkor.ukrdashboard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDetailsDto {
    private String username;
    private String password;
}