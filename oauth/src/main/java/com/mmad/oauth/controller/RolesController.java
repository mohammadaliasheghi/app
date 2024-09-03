package com.mmad.oauth.controller;

import com.mmad.oauth.config.Constant;
import com.mmad.oauth.model.RolesModel;
import com.mmad.oauth.service.RolesService;
import com.mmad.oauth.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constant.ROLES_CONTEXT)
@RequiredArgsConstructor
public class RolesController {

    private final RolesService rolesService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody RolesModel model) throws Exception {
        return new ResponseEntity<>(
                new ResponseDto<>(Constant.ROLE_CREATED,
                        rolesService.createRoles(model)),
                HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody RolesModel model) throws Exception {
        return new ResponseEntity<>(
                new ResponseDto<>(Constant.ROLE_UPDATED,
                        rolesService.updateRoles(model)),
                HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseDto<>(Constant.SUCCESS, rolesService.getRoles(id)), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(new ResponseDto<>(rolesService.deleteRoles(id)), HttpStatus.OK);
    }
}
