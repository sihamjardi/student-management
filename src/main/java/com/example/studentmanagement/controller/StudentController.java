package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Récupérer tous les étudiants")
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @Operation(summary = "Ajouter un étudiant")
    @PostMapping("/students/save")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @Operation(summary = "Supprimer un étudiant par ID")
    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean deleted = studentService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Récupérer tous les étudiants")
    @GetMapping("/students/all")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Operation(summary = "Compter le nombre total d'étudiants")
    @GetMapping("/students/count")
    public ResponseEntity<Long> countStudent() {
        long count = studentService.countStudents();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @Operation(summary = "Récupérer le nombre d'étudiants par année")
    @GetMapping("/students/byYear")
    public ResponseEntity<Collection<?>> findByYear() {
        Collection<?> studentsByYear = studentService.findNbrStudentByYear();
        return new ResponseEntity<>(studentsByYear, HttpStatus.OK);
    }
}