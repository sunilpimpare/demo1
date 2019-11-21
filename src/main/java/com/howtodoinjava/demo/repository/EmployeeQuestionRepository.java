package com.howtodoinjava.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.howtodoinjava.demo.model.EmployeeQuestion;

@Repository
public interface EmployeeQuestionRepository extends CrudRepository<EmployeeQuestion, Long> {

}
