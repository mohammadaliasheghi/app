package com.mmad.oauth.controller;

import com.mmad.oauth.config.Constant;
import com.mmad.oauth.model.UsersModel;
import com.mmad.oauth.service.UsersService;
import com.mmad.oauth.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constant.USER_CONTEXT)
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody UsersModel model) {
        if (service.findUsersByUsername(model.getUsername()).isEmpty())
            return new ResponseEntity<>(
                    new ResponseDto<>(Constant.USER_CREATED,
                            service.create(model)),
                    HttpStatus.CREATED);
        else
            return new ResponseEntity<>(
                    new ResponseDto<>(Constant.USERNAME_CONFLICT),
                    HttpStatus.CONFLICT);
    }

    @PutMapping(value = "/updateUsername", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUsername(@RequestBody UsersModel model) {
        if (service.findUsersByUsernameAndIdNot(model).isEmpty())
            return new ResponseEntity<>(
                    new ResponseDto<>(Constant.USER_UPDATED,
                            service.updateUsername(model)),
                    HttpStatus.OK);
        else
            return new ResponseEntity<>(
                    new ResponseDto<>(Constant.USERNAME_CONFLICT),
                    HttpStatus.CONFLICT);
    }

    @PutMapping(value = "/updatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePassword(@RequestBody UsersModel model) {
        return new ResponseEntity<>(
                new ResponseDto<>(Constant.USER_UPDATED,
                        service.updatePassword(model)),
                HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseDto<>(Constant.SUCCESS, service.get(id)), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseDto<>(service.delete(id)), HttpStatus.OK);
    }

}
