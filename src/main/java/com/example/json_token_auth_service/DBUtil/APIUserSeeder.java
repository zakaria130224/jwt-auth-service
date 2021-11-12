package com.example.json_token_auth_service.DBUtil;


import com.example.json_token_auth_service.Models.APIUser;
import com.example.json_token_auth_service.Models.UserPermission;
import com.example.json_token_auth_service.Repositories.APIUserRepository;
import com.example.json_token_auth_service.Repositories.UserPermissionRepository;
import com.example.json_token_auth_service.Security.PasswordSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class APIUserSeeder implements ApplicationListener<ContextRefreshedEvent> {
    Logger logger= LoggerFactory.getLogger(APIUserSeeder.class);

    @Autowired
    private APIUserRepository apiUserRepository;

    @Autowired
    private PasswordSecurity passwordSecurity;

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    public APIUserSeeder(APIUserRepository apiUserRepository, PasswordSecurity passwordSecurity) {
        this.apiUserRepository = apiUserRepository;
        this.passwordSecurity = passwordSecurity;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("===========Start Seeding User=============");
        seedAPIUser();
        System.out.println("===========End Seeding User=============");
    }


    private void seedAPIUser()
    {
        List<APIUser> apiUsers = new ArrayList() {{
            add(new APIUser("admin",passwordSecurity.encryptThisString("admin@2021"),"admin@2021","admin@gmail.com","ADMIN"));
            add(new APIUser("test001",passwordSecurity.encryptThisString("test001@2021"),"test001@2021","test001@gmail.com"));

        }};

        try{
            apiUserRepository.saveAll(apiUsers);
            userPermissionRepository.save(new UserPermission(1,"Test","/adduser"));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }

    }
}
