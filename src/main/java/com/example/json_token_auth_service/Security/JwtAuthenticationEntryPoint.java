package com.example.json_token_auth_service.Security;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		final String expired = (String) request.getAttribute("expired");
		System.out.println(expired);
		if (expired!=null){
			//response.sendError(1324,expired);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,expired);
		}else{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		}

	}
}
