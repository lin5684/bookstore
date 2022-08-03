package com.example.bishe.tol;

import com.example.bishe.domain.User;
import com.example.bishe.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class GetM {

    public static String getUserName(){
        SecurityContext context= SecurityContextHolder.getContext();
        Authentication authentication=context.getAuthentication();
        UserDetails principal= (UserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }
}
