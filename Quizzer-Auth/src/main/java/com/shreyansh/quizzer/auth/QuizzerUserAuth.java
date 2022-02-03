package com.shreyansh.quizzer.auth;

import com.shreyansh.quizzer.auth.entity.AuditRecord;
import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.entity.UserRoles;
import com.shreyansh.quizzer.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Quizzer Auth JWT", version = "2.0", description = "Implementation of JWT Auth"))
//@SecurityScheme(name = "quizzer-authentication", scheme = "Bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class QuizzerUserAuth implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

//    @PostConstruct
//    public void initUsers() {
//        List<User> users = Stream.of(
//                new User(101, "javatechie", "password", "javatechie@gmail.com"),
//                new User(102, "user1", "pwd1", "user1@gmail.com"),
//                new User(103, "user2", "pwd2", "user2@gmail.com"),
//                new User(104, "user3", "pwd3", "user3@gmail.com")
//        ).collect(Collectors.toList());
//        repository.saveAll(users);
//    }

    public static void main(String[] args) {
        SpringApplication.run(QuizzerUserAuth.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(!userRepository.existsByEmail("admin@gmail.com")){
            User admin = User.builder()
                    .firstName("Admin")
                    .lastName("User-1")
                    .email("admin@gmail.com")
                    .password("Admin@123")
                    .isLocked(false)
                    .active(true)
                    .userRoles(Set.of(new UserRoles("Admin")))
                    .build();

            admin = userRepository.save(admin);
            AuditRecord auditRecord = AuditRecord.builder()
                    .createdBy(admin.getFullName())
                    .createdById(admin.getUserId())
                    .lastModifiedBy(admin.getUserId())
                    .lastModifiedById(admin.getUserId())
                    .createdDateTime(LocalDateTime.now())
                    .lastModifiedDateTime(LocalDateTime.now())
                    .build();
            admin.setAuditRecord(auditRecord);
            userRepository.save(admin);
        }
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}

