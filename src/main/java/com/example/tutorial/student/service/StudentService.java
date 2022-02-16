package com.example.tutorial.student.service;

import com.example.tutorial.student.dao.StudentRepository;
import com.example.tutorial.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    public void deleteStudent(Long studentId) {
        boolean existingStudent = studentRepository.existsById(studentId);
        if (!existingStudent) {
            throw new IllegalStateException("Student not found");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, Student student) {
        Student studentToUpdate = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student doesn't exist"));
        if (student.getName() != null && student.getName().length() > 0 && !Objects.equals(studentToUpdate.getName(), student.getName())) {
            studentToUpdate.setName(student.getName());
        }
        if (student.getEmail() != null && student.getEmail().length() > 0 && !Objects.equals(studentToUpdate.getEmail(), student.getEmail())) {
            studentToUpdate.setEmail(student.getEmail());
        }
    }
}
