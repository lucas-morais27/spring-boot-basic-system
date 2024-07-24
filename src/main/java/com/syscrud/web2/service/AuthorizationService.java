package com.syscrud.web2.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthorizationService {
    UserDetails loadUserByUsername(String username);
}
