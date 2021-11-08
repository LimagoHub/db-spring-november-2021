package de.application.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
//@Lazy
public class Hello {
	
	@Value("Hello Universe")
	private String message="Hello World" ;


	public Hello() {
		System.out.println("Ctor Hello " + message);
	}

	@PostConstruct
	public void init() {
		System.out.println("Init " + message);
	}

	@PreDestroy
	public void destroy() {
		System.out.println("destroy");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
