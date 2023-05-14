package com.enset.hopital;

import com.enset.hopital.entities.Patient;
import com.enset.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class HopitalApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

//        patientRepository.save(new Patient(null,"Ali",new Date(),true,240));
//        patientRepository.save(new Patient(null,"Karim",new Date(),false,34));
//        patientRepository.save(new Patient(null,"Layla",new Date(),true,45));
//        patientRepository.save(new Patient(null,"Imane",new Date(),false,56));


        /*

        Patient patient1=new Patient();

        patient1.setId(null);
        patient1.setNom("Patient1");
        patient1.setMalade(false);
        patient1.setDateNaissance(new Date());
        patient1.setScore(23);

        Patient patient2=new Patient(null,"Patient2",new Date(),true,34);

        // le role de @Builder
        Patient patient3=Patient.builder()
                .nom("Patient3")
                .dateNaissance(new Date())
                .id(null)
                .score(45)
                .malade(true)
                .build();
       */

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
