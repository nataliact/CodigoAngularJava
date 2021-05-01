package com.example.demo;

import javax.enterprise.inject.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.InicioSesion;
import com.example.demo.soap.api.Backend;
import com.example.demo.soap.api.BackendResponse;
import com.example.demo.soap.api.ObjectFactory;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ServiceRest {
	
	@Autowired
	InicioSesion inicioSesion;
	
	@PostMapping("/inf")
	
	public BackendResponse getinfo(@RequestBody Backend bk) {
				
		ObjectFactory objectFactory = new ObjectFactory();
		

		BackendResponse response = inicioSesion.getBank(objectFactory.createBackend(bk));
		return response;
		
		
	}
	
	
	@PostMapping("/infoUser")
	@Consumes("application/json")
	public BackendResponse getinfoUsuario(@RequestBody Backend bk) {
		
		BackendResponse response = inicioSesion.getInfo(bk);
		return response;
		
	}

}
