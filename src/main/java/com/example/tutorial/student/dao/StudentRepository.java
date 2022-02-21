package com.example.tutorial.student.dao;


import java.time.LocalDate;
import java.util.*;

import com.example.tutorial.student.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
//    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
    List<Student> findStudentByNameContains(String name);
//    List<Student> findStudentByNameContains(String name, Pageable pageable);

    @Query("SELECT st.name, st.email FROM Student st")
    List<Objects> findAllStudentPartialData();

    @Query("FROM Student WHERE name=:name")
    List<Student> findAllStudentByName(@Param("name") String name);

    @Query("FROM Student WHERE dateOfBirth>:min AND dateOfBirth<:max")
    List<Student> findAllStudentBetweenAge(@Param("min") LocalDate min, @Param("max") LocalDate max);

    @Modifying
    @Query("DELETE FROM Student WHERE name = :name")
    void deleteStudentByName(@Param("name") String name);
}
