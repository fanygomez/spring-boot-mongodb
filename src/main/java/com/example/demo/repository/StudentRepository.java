package com.example.demo.repository;

/**
 * @fangomez
 */
import com.example.demo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {
}
