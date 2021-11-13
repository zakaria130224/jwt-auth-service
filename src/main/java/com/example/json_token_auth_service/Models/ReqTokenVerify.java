package com.example.json_token_auth_service.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class ReqTokenVerify {
    String token,contextUrl;
}
