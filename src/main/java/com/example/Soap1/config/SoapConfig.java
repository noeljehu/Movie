package com.example.Soap1.config;

import com.example.Soap1.service.MovieEndpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapConfig {

    private final Bus bus;
    private final MovieEndpoint movieEndpoint;

    // Constructor para inyección de dependencias
    public SoapConfig(Bus bus, MovieEndpoint movieEndpoint) {
        this.bus = bus;
        this.movieEndpoint = movieEndpoint;
    }

    // Método que crea y publica el endpoint de Movie
    @Bean
    public EndpointImpl endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, movieEndpoint);
        endpoint.publish("/movie"); // Publica el endpoint en la URL '/movie'
        return endpoint;
    }
}

