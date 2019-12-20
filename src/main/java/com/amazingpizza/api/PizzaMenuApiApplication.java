package com.amazingpizza.api;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main spring application class.
 */
@NoArgsConstructor
@SpringBootApplication
@EnableSwagger2
public class PizzaMenuApiApplication {

	/**
	 * Configures the bean for CORS support.
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(final CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

	/**
	 * Bean configuration for the SWAGGER documentation.
	 * @return
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.amazingpizza.api")).build();
	}

	/**
	 * Bean for the Model mapper that will be used to convert DTO <-> Entity.
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(final String[] args) { // NOPMD
		SpringApplication.run(PizzaMenuApiApplication.class, args);
	}

}
