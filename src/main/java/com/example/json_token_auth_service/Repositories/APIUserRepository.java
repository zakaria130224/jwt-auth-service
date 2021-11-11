package com.example.json_token_auth_service.Repositories;

import com.example.json_token_auth_service.Models.APIUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface APIUserRepository extends CrudRepository<APIUser, Integer>{
    List<APIUser> findByUserName(String username);
    List<APIUser> findByUserNameAndPassword(String username,String password);

}
