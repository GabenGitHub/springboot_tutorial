//package com.example.tutorial.student;
//
//import com.example.tutorial.student.dao.StudentRepository;
//import com.example.tutorial.student.entity.Student;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Configuration
//public class StudentConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
//        return args -> {
//            Student mariam = new Student(
//                    "Mariam",
//                    "mariam.jamal@gmail.com",
//                    LocalDate.of(2002, 2, 14),
//                    "+36301234567"
//            );
//
//            Student gaben = new Student(
//                    "Gaben",
//                    "Gaben@gmail.com",
//                    LocalDate.of(1992, 2, 14),
//                    "+36301234567"
//            );
//
//            studentRepository.saveAll(List.of(mariam, gaben));
//        };
//    }
//}
