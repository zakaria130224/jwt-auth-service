package com.example.json_token_auth_service.Controllers;

import com.example.json_token_auth_service.Models.ReqTokenVerify;
import com.example.json_token_auth_service.Security.JwtResponse;
import com.example.json_token_auth_service.Security.JwtTokenUtil;
import com.example.json_token_auth_service.Security.PasswordSecurity;
import com.example.json_token_auth_service.Services.APIUserService;
import com.example.json_token_auth_service.Services.UserPermissionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin
public class AuthController {

//	@Autowired
//	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private APIUserService apiUserService;
	
	@Autowired
	private PasswordSecurity passwordSecurity;

	@Autowired
	private UserPermissionDetailsService userDetailsService;


	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public ResponseEntity<?> createAuthenticationToken(@RequestHeader String username, @RequestHeader String password) throws Exception {

		//authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = apiUserService
				.loadUserByUsernameAndPassword(username,passwordSecurity.encryptThisString(password) );
	
        
		final String token = jwtTokenUtil.generateToken(userDetails);
		final String expTime = jwtTokenUtil.getExpirationDateFromToken(token).toString();

		return ResponseEntity.ok(new JwtResponse(token,expTime));
	}

	@PostMapping("/verify")
	public ResponseEntity<?> verifyToken(HttpServletRequest request,@RequestBody ReqTokenVerify reqTokenVerify)
	{
//		ResTokenVerify resTokenVerify=new ResTokenVerify();
//		resTokenVerify.setReqTokenVerify(reqTokenVerify);
//		userDetailsService.getUserPermissionDetail(reqTokenVerify);
		return ResponseEntity.ok(userDetailsService.getUserPermissionDetail(reqTokenVerify));
	}

}
