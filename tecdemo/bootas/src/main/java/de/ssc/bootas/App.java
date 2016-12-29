package de.ssc.bootas;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan({"de.ssc.bootas","asset.pipeline.springboot"})
public class App 
{
    public static void main( String[] args )
    {
    	new SpringApplicationBuilder(App.class)
    		.web(true)
    		.run(args);
//        SpringApplication.run(App.class, args);
    }
}
