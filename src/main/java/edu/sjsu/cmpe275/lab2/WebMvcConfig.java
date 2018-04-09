package edu.sjsu.cmpe275.lab2;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter{
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer){
        configurer.favorPathExtension(false).
                favorParameter(true).
                parameterName("xml").
                ignoreAcceptHeader(true).
                useJaf(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("true", MediaType.APPLICATION_XML);
    }
}
