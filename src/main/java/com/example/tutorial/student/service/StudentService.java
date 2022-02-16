package com.example.tutorial.student.service;

import com.example.tutorial.student.dao.StudentRepository;
import com.example.tutorial.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> existingStudent = studentRepository.findStudentByEmail(student.getEmail());
        if (existingStudent.isEmpty()) {
            studentRepository.save(student);
        } else {
            System.out.println("Student already exist");
            throw new IllegalStateException("Email is taken");
        }
    }
}
