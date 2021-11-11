package com.example.json_token_auth_service.Controllers;

import com.example.json_token_auth_service.Models.APIUser;
import com.example.json_token_auth_service.Repositories.APIUserRepository;
import com.example.json_token_auth_service.Security.PasswordSecurity;
import com.example.json_token_auth_service.Services.APIUserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class APIUserController {
    @Autowired
    APIUserService apiUserService;

    @Autowired
    APIUserRepository apiUserRepository;

    @Autowired
    private PasswordSecurity passwordSecurity;

@RequestMapping(value = "/adduser",method = RequestMethod.POST)
public ResponseEntity<?> addAPIUser( @RequestBody APIUser apiUser) throws JSONException {
   // apiUser.setId(2);
    try {
        apiUser.setPlainPassword(apiUser.getPassword());
        apiUser.setPassword(passwordSecurity.encryptThisString(apiUser.getPassword()));
        APIUser resUser = apiUserRepository.save(apiUser);
        return ResponseEntity.ok(resUser);
    }
    catch (DataIntegrityViolationException Ex){
        HashMap<String, String> json = new HashMap<>();
        json.put("code","9001");
        json.put("errorMsg",Ex.getRootCause().getMessage());
        return ResponseEntity.ok(json);
    }
    catch (Exception ex){
        HashMap<String, String> json = new HashMap<>();
        json.put("code","9001");
        json.put("errorMsg",ex.getMessage());
        return ResponseEntity.ok(json);
    }
}

	
}
