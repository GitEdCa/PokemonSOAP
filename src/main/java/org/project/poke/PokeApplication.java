package org.project.poke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

@SpringBootApplication
public class PokeApplication implements CommandLineRunner {

	
	public static void main(String[] args) {
		SpringApplication.run(PokeApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
	    return (mapperBuilder) -> mapperBuilder.modulesToInstall(new JaxbAnnotationModule());
	}
	
	@Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("DROP TABLE origin IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE origin("
				+ "id INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "ip VARCHAR(50), "
				+ "created DATE default CURRENT_DATE, "
				+ "method VARCHAR(100))");
	}

}
