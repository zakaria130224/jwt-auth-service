package com.example.json_token_auth_service.Models;

import lombok.Data;

import java.util.List;

@Data
public class ReqAddUser {
    private APIUser userdata;
    private List<String> permissions;
}
