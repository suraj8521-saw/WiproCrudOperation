package com.example.demo.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDto {
    @NotBlank(message = "Name cant be blank!")
    @Size(min = 2, max = 50, message = "Must contain at least 2 and at most 50 characters")
    private String name;

    @NotNull(message = "Age cant be blank")
    @Min(value = 18, message = "Age must be greater than or equal to 18")
    private Integer age;

    @NotBlank(message = "Email cant be blank")
    @Email(message = "Invalid email format") // Fixed typo ("image" -> "email")
    private String email;

    @NotNull(message = "Roll cant be blank")
    private Integer rollNo;

    @NotBlank(message = "Subject cant be blank")
    private String subject;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}
