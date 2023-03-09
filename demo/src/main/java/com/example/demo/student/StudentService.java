package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // dependency injection
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return  studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        throwExceptionIfEmailAlreadyExists(student.getEmail());

        studentRepository.save(student);
    }

    private void throwExceptionIfEmailAlreadyExists(String email) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email already taken");
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));

        if (propertyIsNotNullOrEmptyAndIsDifferentThanCurrentValue(name, student.getName())) {
            student.setName(name);
        }

        if (propertyIsNotNullOrEmptyAndIsDifferentThanCurrentValue(email, student.getEmail())) {
            throwExceptionIfEmailAlreadyExists(email);
            student.setEmail(email);
        }

    }

    private boolean propertyIsNotNullOrEmptyAndIsDifferentThanCurrentValue(String property, String currentValue) {
        return property != null && property.length() > 0 && !Objects.equals(currentValue, property);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }

        studentRepository.deleteById(studentId);
    }

}
