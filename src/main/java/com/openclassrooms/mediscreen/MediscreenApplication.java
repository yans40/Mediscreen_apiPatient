package com.openclassrooms.mediscreen;

import com.openclassrooms.mediscreen.Entities.Patient;
import com.openclassrooms.mediscreen.Services.PatientService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class MediscreenApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediscreenApplication.class, args);
    }

}
