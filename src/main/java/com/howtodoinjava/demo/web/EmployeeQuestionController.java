package com.howtodoinjava.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.model.EmployeeQuestion;
import com.howtodoinjava.demo.service.EmployeeQuestionService;
import com.howtodoinjava.demo.service.EmployeeService;

@Controller
@RequestMapping("/")
public class EmployeeQuestionController {
	
	
		@Autowired
		EmployeeQuestionService service;

		@RequestMapping(path = {"/qst"})
		public String editEmployeeById(Model model) 
								throws RecordNotFoundException 
		{
			List<EmployeeQuestion> list = service.getAllEmployeesQuestion();
			model.addAttribute("employeeQuestion", list);
			return "list-employees-Question";
		}
		
		@RequestMapping(path = "/createquestion", method = RequestMethod.POST)
		public String createOrUpdateEmployee(EmployeeQuestion employeeQuestion) 
		{
			service.createOrUpdateEmployee(employeeQuestion);
			return "redirect:/qst";
		}
		
		
		@RequestMapping(path = {"/addqst", "/editquestion/{id}"})
		public String editEmployeeQuestionById(Model model, @PathVariable("id") Optional<Long> id) 
								throws RecordNotFoundException 
		{
			if (id.isPresent()) {
				EmployeeQuestion entity = service.getEmployeeQuestionById(id.get());
				model.addAttribute("question", entity);
			} else {
				
				model.addAttribute("question", new EmployeeQuestion());
			}
			return "add-security-question";
		}
		@RequestMapping(path = "/deletequestion/{id}")
		public String deleteEmployeeById(Model model, @PathVariable("id") Long id) 
								throws RecordNotFoundException 
		{
			service.deleteEmployeeQuestionById(id);
			return "redirect:/";
		}	
}


		
