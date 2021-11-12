package com.example.json_token_auth_service.Controllers;

import com.example.json_token_auth_service.Models.APIUser;
import com.example.json_token_auth_service.Models.ReqAddUser;
import com.example.json_token_auth_service.Models.UserPermission;
import com.example.json_token_auth_service.Repositories.APIUserRepository;
import com.example.json_token_auth_service.Repositories.UserPermissionRepository;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
public class APIUserController {
    @Autowired
    APIUserService apiUserService;

    @Autowired
    APIUserRepository apiUserRepository;

    @Autowired
    private PasswordSecurity passwordSecurity;

    @Autowired
    private UserPermissionRepository userPermissionRepository;

@RequestMapping(value = "/adduser",method = RequestMethod.POST)
public ResponseEntity<?> addAPIUser( @RequestBody ReqAddUser reqAddUser) throws JSONException {
   // apiUser.setId(2);
    try {
        //Add user
        APIUser apiUser=reqAddUser.getUserdata();
        apiUser.setPlainPassword(apiUser.getPassword());
        apiUser.setPassword(passwordSecurity.encryptThisString(apiUser.getPassword()));
        APIUser resUser = apiUserRepository.save(apiUser);
        //Add Permissions
        List<UserPermission> permissionList=new ArrayList<>();
        reqAddUser.getPermissions().forEach(x->{
            permissionList.add(new UserPermission(resUser.getId(),"Test",x));
        });
        userPermissionRepository.saveAll(permissionList);
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
