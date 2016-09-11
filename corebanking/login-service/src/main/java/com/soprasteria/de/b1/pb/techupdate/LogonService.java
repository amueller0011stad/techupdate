package com.soprasteria.de.b1.pb.techupdate;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 */
@SpringBootApplication
public class LogonService
extends SpringBootServletInitializer
{

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder)
    {
        return builder;
    }

}