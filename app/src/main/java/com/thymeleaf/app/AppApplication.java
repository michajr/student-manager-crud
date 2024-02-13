package com.thymeleaf.app;

import com.thymeleaf.app.models.Student;
import com.thymeleaf.app.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

  public static void main(String[] args) {
    SpringApplication.run(AppApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(
    StudentRepository studentRepository
  ) {
    return args -> {
      Student student = new Student();
      student.setId(1);
      student.setName("Michael");
      student.setLastName("Campusano");
      student.setEmail("mc@gmail.com");

      studentRepository.save(student);
    };
  }
}
