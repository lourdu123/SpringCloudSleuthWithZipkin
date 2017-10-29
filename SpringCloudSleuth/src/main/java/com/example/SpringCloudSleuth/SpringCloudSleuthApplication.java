package com.example.SpringCloudSleuth;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringCloudSleuthApplication {
	
	private static final Logger LOG = Logger.getLogger(SpringCloudSleuthApplication.class.getName()); 
	
	@Autowired 
	private RestTemplate restTemplate; 

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSleuthApplication.class, args);
	}
	
	@Bean 
	public AlwaysSampler defaultSampler() { 
	  return new AlwaysSampler(); 
	}
	
	@Bean 
	public RestTemplate getRestTemplate() { 
		  return new RestTemplate(); 
	} 
	
	@RequestMapping("/") 
	public String home() { 
	  LOG.log(Level.INFO, "you called home"); 
	  return "Hello World"; 
	} 
		
	@RequestMapping("/callhome") public String callHome() { 
	  LOG.log(Level.INFO, "calling home"); 
	  return restTemplate.getForObject("http://localhost:8080", String.class); 
	}
}
