package org.t01.gdp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"org.t01.gdp.mapper"})
public class GdpApplication {

	public static void main(String[] args) {
		SpringApplication.run(GdpApplication.class, args);
	}

}
