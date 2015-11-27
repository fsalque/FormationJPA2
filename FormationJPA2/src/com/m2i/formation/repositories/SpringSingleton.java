package com.m2i.formation.repositories;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringSingleton {
	private SpringSingleton() {
		// TODO Auto-generated constructor stub
	}
	private static ApplicationContext factory;

	public static ApplicationContext getFactory(){
		if(factory == null){
			factory = new ClassPathXmlApplicationContext("spring.xml");
		}
		return factory;
	}
}
