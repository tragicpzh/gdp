package org.t01.gdp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.t01.gdp.domain.TimeAxis;

@SpringBootApplication
@EnableScheduling
@MapperScan({"org.t01.gdp.mapper"})
public class GdpApplication {

	public static void main(String[] args) {
		SpringApplication.run(GdpApplication.class, args);
		TimeAxis.initialTime();
	}

}
