package com.howtodoinjava.demo.web;

import java.util.HashMap;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeQuestionService;
import com.howtodoinjava.demo.service.EmployeeService;


@Controller
@RequestMapping("/")
public class EmployeeMvcController 
{
	@Autowired
	EmployeeService service;
	@Autowired
	EmployeeQuestionService employeeQuestionService;

	@RequestMapping
	public String getAllEmployees(Model model) 
	{
		List<EmployeeEntity> list = service.getAllEmployees();

		model.addAttribute("employees", list);
		return "login";
	}
	@RequestMapping("/login")
	public String ProcessUserpage(@ModelAttribute("user")EmployeeEntity thecustomer) {
		boolean userexist=EmployeeService.checkuser(thecustomer.getUsername(),thecustomer.getPassword());
		if(userexist) {
			return "list-employees";
		}else {
			return "home";
		}

	}
	
	
	@RequestMapping(path = "/edit" )
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		if (id.isPresent()) {
			EmployeeEntity entity = service.getEmployeeById(id.get());
			model.addAttribute("employee", entity);
		} else {

			model.addAttribute("employee", new EmployeeEntity());
			model.addAttribute("listq",employeeQuestionService.getAllEmployeesQuestion());
		}
		return "add-edit-employee";
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		service.deleteEmployeeById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public String createOrUpdateEmployee(EmployeeEntity employee) 
	{
		service.createOrUpdateEmployee(employee);
		return "redirect:/";
	}
}
