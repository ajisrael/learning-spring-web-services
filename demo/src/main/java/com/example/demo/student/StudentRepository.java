package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// T -> Student : object being worked with for repository
// id -> Long : the type of object for the id of the reference object T

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
