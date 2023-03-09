package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// T -> Student : object being worked with for repository
// id -> Long : the type of object for the id of the reference object T

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // The name of the method is used to generate sql to run on invocation of said method.
    // You can also add the following annotation to do the same thing:
    // @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
