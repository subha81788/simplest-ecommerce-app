package org.subhashis.simplestecommerceapp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class SimplestEcommerceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplestEcommerceAppApplication.class, args);
	}

}
