package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Student;
import com.example.demo.exceptionHandler.StudentNotFoundException;
import com.example.demo.exceptionHandler.StudentServiceException;
import com.example.demo.exceptionHandler.StudentAlreadyExistsException;
import com.example.demo.repository.StudentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepositry;
    StudentService(StudentRepository studentRepositry){
        this.studentRepositry=studentRepositry;
    }
    public CreateStudentResponseDto saveStudent(CreateStudentRequestDto student){
        if (studentRepositry.existsByEmail(student.getEmail())) {
            throw new StudentAlreadyExistsException("Email '" + student.getEmail() + "' is already registered!");
        }
        Student studentDetails=getStudentDtoToEntity(student);

       try{
           studentDetails= studentRepositry.save(studentDetails);
       }catch (DataIntegrityViolationException e){
           throw new StudentAlreadyExistsException("Student with these details already exists in the system.");
       }catch (Exception ex) {
           throw new StudentServiceException("Failed to save student due to an internal error.");
       }
        return setStudentDtoToEntity(studentDetails);
    }
    public  GetStudentDetailsResponseDto getStudent(Long id){

       Optional<Student> result= studentRepositry.findByIdAndIsDeletedFalse(id);
//
       return result.map(
               this::getStudentEntityToDto
       ).orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
    }
    public List<GetStudentDetailsResponseDto>getAllStudent(){
        List<Student> studentList= studentRepositry.findAllByIsDeletedFalse();
        return studentList.stream()
                .map(this::getStudentEntityToDto)
                .collect(Collectors.toList());
    }
    public UpdateStudentResponseDto updatedStudent(Long id, UpdateStudentRequestDto student){
        Student foundStudent=studentRepositry.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new StudentNotFoundException("User not Found with ID: " + id));



        foundStudent.setName(student.getName());
        foundStudent.setRollNo(student.getRollNo());
        foundStudent.setSubject(student.getSubject());
        foundStudent.setUpdatedAt(LocalDateTime.now());
        foundStudent.setAge(student.getAge());
        return getUpdateStudentEntityToDto(studentRepositry.save(foundStudent));
    }
    public void softDelete(Long id){
      Student student=  studentRepositry.findByIdAndIsDeletedFalse(id)
              .orElseThrow(() -> new StudentNotFoundException("User not Found with ID: " + id));

        student.setDeleted(true);
        studentRepositry.save(student);

    }
    private Student getStudentDtoToEntity(CreateStudentRequestDto student){
        Student studentRes=new Student();
        studentRes.setAge(student.getAge());
        studentRes.setName(student.getName());
        studentRes.setSubject(student.getSubject());
        studentRes.setEmail(student.getEmail());
        studentRes.setRollNo(student.getRollNo());
        studentRes.setDeleted(false);
        studentRes.setCreatedAt(LocalDateTime.now());
        studentRes.setUpdatedAt(LocalDateTime.now());
        return studentRes;
    }
    private CreateStudentResponseDto setStudentDtoToEntity(Student student){
        CreateStudentResponseDto studentRes = new CreateStudentResponseDto();
        studentRes.setId(student.getId());
        studentRes.setAge(student.getAge());
        studentRes.setCreatedAt(student.getCreatedAt());
        studentRes.setEmail(student.getEmail());
        studentRes.setSubject(student.getSubject());
        studentRes.setName(student.getName());
        studentRes.setRollNo(student.getRollNo());

        return studentRes;
    }
    private GetStudentDetailsResponseDto getStudentEntityToDto(Student student){
        GetStudentDetailsResponseDto studentRes=new GetStudentDetailsResponseDto();

        /*
     private Long id;
    private String name;
    private Integer age;
    private String email;
    private Integer rollNo;
    private String subject;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;*/
        studentRes.setId(student.getId());
        studentRes.setName(student.getName());
        studentRes.setAge(student.getAge());
        studentRes.setEmail(student.getEmail());
        studentRes.setRollNo(student.getRollNo());
        studentRes.setSubject(student.getSubject());
        studentRes.setCreatedAt(student.getCreatedAt());
        studentRes.setUpdatedAt(student.getUpdatedAt());

        return studentRes;
    }
    private  UpdateStudentResponseDto getUpdateStudentEntityToDto(Student student){
        UpdateStudentResponseDto studentRes=new UpdateStudentResponseDto();

        /*
     private Long id;
    private String name;
    private Integer age;
    private String email;
    private Integer rollNo;
    private String subject;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;*/
        studentRes.setId(student.getId());
        studentRes.setName(student.getName());
        studentRes.setAge(student.getAge());
        studentRes.setEmail(student.getEmail());
        studentRes.setRollNo(student.getRollNo());
        studentRes.setSubject(student.getSubject());
        studentRes.setUpdatedAt(student.getUpdatedAt());

        return studentRes;
    }
}
