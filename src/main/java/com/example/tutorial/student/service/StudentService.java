package com.example.tutorial.student.service;

import com.example.tutorial.student.dao.StudentRepository;
import com.example.tutorial.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    public List<Student> getStudents() {
//        return studentRepository.findAll();
//    }
    public Page<Student> getStudents() {
//        Pageable pageable = PageRequest.of(0, 2);
        var order1 = new Sort.Order(Sort.Direction.ASC ,"name");
        var order2 = new Sort.Order(Sort.Direction.ASC ,"email");
        Sort sort = Sort.by(order1, order2);
        Pageable pageable = PageRequest.of(0, 2, sort);

        return studentRepository.findAll(pageable);
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

    public void deleteStudent(UUID studentId) {
        boolean existingStudent = studentRepository.existsById(studentId);
        if (!existingStudent) {
            throw new IllegalStateException("Student not found");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(UUID studentId, Student student) {
        Student studentToUpdate = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student doesn't exist"));
        if (student.getName() != null && student.getName().length() > 0 && !Objects.equals(studentToUpdate.getName(), student.getName())) {
            studentToUpdate.setName(student.getName());
        }
        if (student.getEmail() != null && student.getEmail().length() > 0 && !Objects.equals(studentToUpdate.getEmail(), student.getEmail())) {
            studentToUpdate.setEmail(student.getEmail());
        }
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findStudentByNameContains(name);
    }
}
