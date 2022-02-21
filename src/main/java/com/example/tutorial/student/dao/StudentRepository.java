package com.example.tutorial.student.dao;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.tutorial.student.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
//    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
    List<Student> findStudentByNameContains(String name);
//    List<Student> findStudentByNameContains(String name, Pageable pageable);
}
