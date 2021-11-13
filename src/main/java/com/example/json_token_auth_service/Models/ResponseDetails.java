package com.example.json_token_auth_service.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

@Data
public class ResponseDetails{
    int Code;
    String Message;

    public ResponseDetails(){}
    public ResponseDetails(int code, String message) {
        Code = code;
        Message = message;
    }
}
