package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.model.EmployeeQuestion;
import com.howtodoinjava.demo.repository.EmployeeQuestionRepository;


@Service
public class EmployeeQuestionService {

	
	@Autowired
	EmployeeQuestionRepository repository;
	public List<EmployeeQuestion> getAllEmployeesQuestion()
	{
		List<EmployeeQuestion> result = (List<EmployeeQuestion>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<EmployeeQuestion>();
		}
	}
	
	public EmployeeQuestion createOrUpdateEmployee(EmployeeQuestion employeeQuestion) 
	{
		if(employeeQuestion.getId()  == null) 
		{
			employeeQuestion = repository.save(employeeQuestion);
			
			return employeeQuestion;
		} 
		else 
		{
			Optional<EmployeeQuestion> employee = repository.findById(employeeQuestion.getId());
			
			if(employee.isPresent()) 
			{
//				EmployeeEntity newEntity = employee.get();
//				newEntity.setEmail(entity.getEmail());
//				newEntity.setFirstName(entity.getFirstName());
//				newEntity.setLastName(entity.getLastName());
//
//				newEntity = repository.save(newEntity);
				
				//return newEntity;
			} else {
				employeeQuestion = repository.save(employeeQuestion);
				
				return employeeQuestion;
			}
		}
		return null;
	} 
	public EmployeeQuestion getEmployeeQuestionById(Long id) throws RecordNotFoundException 
	{
		Optional<EmployeeQuestion> employeeQuestion = repository.findById(id);
		
		if(employeeQuestion.isPresent()) {
			return employeeQuestion.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}
	public void deleteEmployeeQuestionById(Long id) throws RecordNotFoundException 
	{
		Optional<EmployeeQuestion> employee = repository.findById(id);
		
		if(employee.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
}
}