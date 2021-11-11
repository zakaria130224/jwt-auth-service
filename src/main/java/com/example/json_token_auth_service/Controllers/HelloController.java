package com.example.json_token_auth_service.Controllers;


import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	Logger logger= LoggerFactory.getLogger(HelloController.class);
	@Autowired
	HikariDataSource dataSource;

	@RequestMapping("/hello")
	public String seyHello()
	{
		//dataSource.getPoolName();
		logger.info("Hello Controller Called");
		//logger.error(dataSource.getHealthCheckRegistry());
		return "Hello "+dataSource.getPoolName()+" "+dataSource.getHikariPoolMXBean().getActiveConnections()+" "+dataSource.getHikariPoolMXBean().getIdleConnections()+" "+dataSource.getHikariPoolMXBean().getTotalConnections();
	}
}
