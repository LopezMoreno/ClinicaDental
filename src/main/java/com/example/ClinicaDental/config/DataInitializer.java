package com.example.ClinicaDental.config;

import com.example.ClinicaDental.model.Role;
import com.example.ClinicaDental.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    private DataInitializer(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        if(roleRepository.findByName("USER").isEmpty()){
            roleRepository.save(new Role("USER"));
        }
        if (roleRepository.findByName("ADMIN").isEmpty()){
            roleRepository.save(new Role("ADMIN"));
        }
    }
}
