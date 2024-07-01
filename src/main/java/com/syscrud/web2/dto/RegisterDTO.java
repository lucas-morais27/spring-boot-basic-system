package com.syscrud.web2.dto;

import com.syscrud.web2.enuns.UserRole;

public record RegisterDTO(
        String login,
        String senha,
        UserRole role) {
}
