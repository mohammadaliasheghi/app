package com.mmad.oauth.controller;

import com.mmad.oauth.config.JwtConfig;
import com.mmad.oauth.config.Constant;
import com.mmad.oauth.model.UsersModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = Constant.JWT_CONTEXT)
@RequiredArgsConstructor
public class JwtController {

    private final AuthenticationManager authenticationManager;
    private JwtConfig jwtConfig;

    @Autowired
    public void setJwtConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @PostMapping(value = Constant.TOKEN, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getToken(@RequestBody UsersModel model, HttpServletResponse response) {
        if (model == null || model.getUsername() == null || model.getPassword() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        response.addHeader(Constant.AUTHORIZATION, jwtConfig.generateToken(model.getUsername()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
