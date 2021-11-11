package com.example.json_token_auth_service.Security;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String expireTime;

	public String getExpireTime() {
		return expireTime;
	}

	public JwtResponse(String jwttoken,String expireTime) {
		this.jwttoken = jwttoken;
		this.expireTime = expireTime;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
