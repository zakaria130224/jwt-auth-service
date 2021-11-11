package com.example.json_token_auth_service.Repositories;

import com.example.json_token_auth_service.Models.UserPermission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserPermissionRepository extends CrudRepository<UserPermission, Integer> {
    List<UserPermission> findByUserId(int userid);
}
