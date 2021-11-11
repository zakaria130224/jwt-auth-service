package com.example.json_token_auth_service.DBUtil;


import com.example.json_token_auth_service.Models.APIUser;
import com.example.json_token_auth_service.Repositories.APIUserRepository;
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
            add(new APIUser("zaka1324",passwordSecurity.encryptThisString("zaka1324"),"zaka1324","zakaria@gmail.com"));
            add(new APIUser("jeba",passwordSecurity.encryptThisString("jeba2021"),"jeba2021","jeba@gmail.com"));

        }};
        try{
            apiUserRepository.saveAll(apiUsers);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }

    }
}
