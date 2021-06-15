package com.moviereview;

import com.moviereview.model.User;
import com.moviereview.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements CommandLineRunner {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //check id user with username admin exists, if not create
        if (!userRepo.existsAllByUsername("admin")){
            User admin = new User("admin", passwordEncoder.encode("admin"), "admin@movie.com", "ROLE_ADMIN");
            userRepo.save(admin);
        }

        //check id user with username admin1 exists, if not create
        if (!userRepo.existsAllByUsername("admin1")){
            User admin1 = new User("admin1", passwordEncoder.encode("admin1"), "admin1@movie.com", "ROLE_ADMIN");
            userRepo.save(admin1);
        }

        //check id user with username admin2 exists, if not create
        if (!userRepo.existsAllByUsername("admin2")){
            User admin2 = new User("admin2", passwordEncoder.encode("admin2"), "admin2@movie.com", "ROLE_ADMIN");
            userRepo.save(admin2);
        }

        //check id user with username member exists, if not create
        if (!userRepo.existsAllByUsername("member")){
            User member = new User("member", passwordEncoder.encode("member"), "member@movie.com", "ROLE_MEMBER");
            userRepo.save(member);
        }

        //check id user with username member1 exists, if not create
        if (!userRepo.existsAllByUsername("member1")){
            User member1 = new User("member1", passwordEncoder.encode("member1"), "member1@movie.com", "ROLE_MEMBER");
            userRepo.save(member1);
        }

        //check id user with username member2 exists, if not create
        if (!userRepo.existsAllByUsername("member2")){
            User member2 = new User("member2", passwordEncoder.encode("member2"), "member2@movie.com", "ROLE_MEMBER");
            userRepo.save(member2);
        }
    }
}
