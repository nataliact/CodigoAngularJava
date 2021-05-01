package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.example.demo.service.InicioSesion;

@Configuration
public class SoapConfig {
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
		marshaller.setContextPath("com.example.demo.soap.api");
		return marshaller;
	}
	
	@Bean
	public InicioSesion soapConnector(Jaxb2Marshaller marshaller) {
		InicioSesion client = new InicioSesion();
		client.setDefaultUri("https://tech-test.getsandbox.com:443/back/auth");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
