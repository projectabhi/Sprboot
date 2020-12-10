package com.example.persitence.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.persitence.model.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {

}
