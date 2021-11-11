package com.example.json_token_auth_service.Services;

import com.example.json_token_auth_service.Models.APIUser;
import com.example.json_token_auth_service.Repositories.APIUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class APIUserService implements UserDetailsService {
    @Autowired
    private APIUserRepository apiUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        APIUser apiUser=  apiUserRepository.findByUserName(username)
                            .stream()
                            .findFirst()
                            .orElse(null);
//
//        APIUser apiUser= (APIUser) apiUserlist.stream()
//                .filter(e-> e.getUsername().equals(username))
//                .findFirst()
//                .orElse(null);
        if (apiUser!=null)
            return new User(apiUser.getUserName(), apiUser.getPassword(), new ArrayList<>());
        else
            throw new UsernameNotFoundException("User not found with username: " + username);

    }

    public UserDetails loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {

        APIUser apiUser= apiUserRepository.findByUserNameAndPassword(username,password)
                .stream()
                .findFirst()
                .orElse(null);
//        APIUser apiUser= (APIUser) apiUserlist.stream()
//                .filter(e-> e.getUsername().equals(username)&& e.getPassword().equals(password))
//                .findFirst()
//                .orElse(null);

        if (apiUser!=null)
            return new User(apiUser.getUserName(), apiUser.getPassword(), new ArrayList<>());
        else
            throw new UsernameNotFoundException("User not found with username: " + username);


    }

}
