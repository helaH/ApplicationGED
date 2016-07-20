package fr.ged.data.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextApplicationFactory {
	public static ApplicationContext context = new ClassPathXmlApplicationContext
																("applicationContext.xml");


	
}