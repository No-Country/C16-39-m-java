package com.c1639.backend.config.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class to solve CORS problems
 * */

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
          .allowedOrigins("*") // Puedes querer restringir esto a orígenes específicos en un entorno de producción
          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
          .allowedHeaders("Authorization", "Content-Type")
          .exposedHeaders("Authorization") // Si estás utilizando un encabezado personalizado
          .allowCredentials(false)
          .maxAge(3600); // Máximo tiempo de almacenamiento en caché de la respuesta de pre-vuelo CORS
    }
}
