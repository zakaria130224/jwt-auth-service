package com.example.json_token_auth_service.Services;


import com.example.json_token_auth_service.Models.*;
import com.example.json_token_auth_service.Repositories.APIUserRepository;
import com.example.json_token_auth_service.Repositories.UserPermissionRepository;
import com.example.json_token_auth_service.Security.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserPermissionDetailsService {
    @Autowired
    APIUserRepository apiUserRepository;
    @Autowired
    UserPermissionRepository userPermissionRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    public ResTokenVerify getUserPermissionDetail(ReqTokenVerify reqTokenVerify){
        ResTokenVerify resTokenVerify=new ResTokenVerify();
        resTokenVerify.setReqTokenVerify(reqTokenVerify);
        String jwtToken = reqTokenVerify.getToken();
        try {
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            APIUser apiUser= apiUserRepository.findByUserName(username).stream().findFirst().orElse(null);
            if(apiUser!=null)
            {
                if (username.equals(apiUser.getUserName())){
                    resTokenVerify.setValidUser(true);
                    UserPermission userPermission=userPermissionRepository.findByUserId(apiUser.getId())
                            .stream()
                            .filter(x->x.getUri().equals(reqTokenVerify.getContextUrl()))
                            .findFirst().orElse(null);
                    if (userPermission!=null)
                        resTokenVerify.setHasPermission(true);

                }
                if(resTokenVerify.isValidUser() && resTokenVerify.isHasPermission())
                    resTokenVerify.setResponseStatus(new ResponseDetails(1000,"Success"));
                else if(!resTokenVerify.isValidUser() && resTokenVerify.isHasPermission())
                    resTokenVerify.setResponseStatus(new ResponseDetails(1001,"Failed"));
                else if(resTokenVerify.isValidUser() && !resTokenVerify.isHasPermission())
                    resTokenVerify.setResponseStatus(new ResponseDetails(1002,"Failed"));
                else if(!resTokenVerify.isValidUser() && !resTokenVerify.isHasPermission())
                    resTokenVerify.setResponseStatus(new ResponseDetails(1003,"Failed"));
            }else {
                resTokenVerify.setResponseStatus(new ResponseDetails(9001,"User not found!"));
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
            resTokenVerify.setResponseStatus(new ResponseDetails(9002,"Unable to get JWT Token"));
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
            resTokenVerify.setResponseStatus(new ResponseDetails(9003,e.getMessage()));
        }
        return resTokenVerify;
    }

    public boolean isParmited(String username,String uri){
        APIUser apiUser= apiUserRepository.findByUserName(username).stream().findFirst().orElse(null);
        if(apiUser!=null) {
            if (username.equals(apiUser.getUserName())) {
                UserPermission userPermission = userPermissionRepository.findByUserId(apiUser.getId())
                        .stream()
                        .filter(x -> x.getUri().equals(uri))
                        .findFirst().orElse(null);
                if (userPermission != null)
                    return true;

            }
        }
        return false;
    }
}
