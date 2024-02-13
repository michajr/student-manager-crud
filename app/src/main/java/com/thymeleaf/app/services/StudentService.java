package com.thymeleaf.app.services;

import com.thymeleaf.app.models.Student;
import com.thymeleaf.app.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public Student getStudent(Integer id) {
    if (studentRepository.existsById(id)) {
      return studentRepository.findById(id).get();
    }
    return null;
  }

  public String addStudent(Student student) {
    studentRepository.save(student);

    return "Student Added";
  }

  public ResponseEntity<String> editStudent(Integer id, Student student) {
    if (studentRepository.existsById(id)) {
      Student studentFound = studentRepository.findById(id).get();

      studentFound.setName(student.getName());
      studentFound.setLastName(student.getLastName());
      studentFound.setEmail(student.getEmail());

      studentRepository.save(studentFound);

      return new ResponseEntity<>("Student Edited", HttpStatus.OK);
    }

    return new ResponseEntity<>("Student Not Found", HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<String> deleteStudent(Integer id) {
    if (studentRepository.existsById(id)) {
      studentRepository.deleteById(id);

      return new ResponseEntity<>("Student Edited", HttpStatus.OK);
    }

    return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
  }
}
