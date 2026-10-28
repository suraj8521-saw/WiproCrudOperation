package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/students")
public class StudentController {
private final StudentService studentService;
    StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(@Valid @RequestBody CreateStudentRequestDto student){
       return  ResponseEntity.status(201).body(studentService.saveStudent(student));
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetStudentDetailsResponseDto> getStudent(@Valid @PathVariable Long id){
        GetStudentDetailsResponseDto resStudent=studentService.getStudent(id);
        //return resStudent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        return ResponseEntity.ok(resStudent);
    }
    @GetMapping
    public ResponseEntity<List<GetStudentDetailsResponseDto>> getAllStudents(){
        List<GetStudentDetailsResponseDto> resStudent=studentService.getAllStudent();
        return  ResponseEntity.ok(resStudent);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable @NotNull @Min(1) Long id , @Valid @RequestBody UpdateStudentRequestDto student){
       return  ResponseEntity.ok(studentService.updatedStudent(id,student));
    }
    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id){
      studentService.softDelete(id);
      return ResponseEntity.noContent().build();
}
}
