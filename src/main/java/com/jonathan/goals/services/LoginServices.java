package com.jonathan.goals.services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jonathan.goals.Utils.JWTUtil;
import com.jonathan.goals.models.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {

    @Autowired
    JWTUtil jwtUtil;

    public void getLogin(LoginRequest loginRequest, HttpServletResponse response) {
        if(methodIlustrativeOfSearchUser(loginRequest))
            jwtUtil.addCokieWithJWT(loginRequest.getUsername(),response);
    }

    public void  verificateToken (HttpServletRequest request) throws JWTVerificationException {
        jwtUtil.validateCokieWithJWT(request);
    }

    public boolean methodIlustrativeOfSearchUser(LoginRequest loginRequest){
        return Boolean.TRUE;
    }

}
