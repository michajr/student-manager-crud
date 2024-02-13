package com.thymeleaf.app.controller;

import com.thymeleaf.app.models.Student;
// import org.springframework.web.bind.annotation.RequestParam;

import com.thymeleaf.app.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MvcController {

  @Autowired
  private StudentService studentService;

  @GetMapping("/students")
  public String index(Model model) {
    model.addAttribute("students", studentService.getStudents());
    return "index";
  }

  @GetMapping("/students/add")
  public String showFormAdd(Model model) {
    model.addAttribute("student", new Student());
    return "addstudent";
  }

  @PostMapping("/students")
  public String addStudent(@ModelAttribute("student") Student student) {
    studentService.addStudent(student);
    return "redirect:/students";
  }

  @GetMapping("/students/edit/{id}")
  public String showEditPage(@PathVariable Integer id, Model model) {
    model.addAttribute("student", studentService.getStudent(id));
    return "editpage";
  }

  @PostMapping("/students/{id}")
  public String updateStudent(
    @PathVariable Integer id,
    @ModelAttribute("student") Student student
  ) {
    studentService.editStudent(id, student);

    return "redirect:/students";
  }

  @GetMapping("/students/delete/{id}")
  public String deleteStudent(@PathVariable Integer id) {
    studentService.deleteStudent(id);

    return "redirect:/students";
  }
}
