package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import ch.qos.logback.core.joran.conditional.ElseAction;
import jakarta.websocket.server.PathParam;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	private List<Employee> data =  new ArrayList<Employee>(); 
	
	@GetMapping("/employee")
	public ResponseEntity<Object> getEmployee() {
		
		try {
			
			List<Employee> employees = employeeRepository.findAll();
			
			return new ResponseEntity<>(employees, HttpStatus.OK);
			
			
		} catch (Exception e) {
			
		
			return new ResponseEntity<>("Internal server error" , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	 }
	
	
	@PostMapping("/employee")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee body) {
           
		try {
			
			  Employee employee = employeeRepository.save(body);
			 
			  return new ResponseEntity<>(employee, HttpStatus.CREATED);
			
			
		} catch (Exception e) {
			
		
			return new ResponseEntity<>("Internal server error" , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

		
	
	
	@GetMapping("/employee/{employeeId}")
	private ResponseEntity<Object> getEmployeeDetail(@PathVariable Integer employeeId) {
		
		try {
			
			Optional<Employee> employee = employeeRepository.findById(employeeId);
			if (employee.isPresent()) {
				return new ResponseEntity<>(employee, HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>("Employee not found", HttpStatus.BAD_REQUEST);
			}
			 
			 	
		} catch (Exception e) {
			
		     return new ResponseEntity<>("Internal server error" , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/employee/{employeeId}")
	public ResponseEntity<Object> updateEmployee(@PathVariable Integer employeeId ,@RequestBody Employee body) {
		
         try {
			
        	 Optional<Employee> employee =employeeRepository.findById(employeeId);
     		
     		if (employee.isPresent()) {
     			
     		    employee.get().setFirstName(body.getFirstName());
     	        employee.get().setLastName(body.getLastName());
     	        employee.get().setSalary(body.getSalary());
     	        employee.get().setEmployeeId(body.getEmployeeId());
     	        
     	        employeeRepository.save(body);
     	        return new ResponseEntity<>(employee, HttpStatus.OK);
		    }else {
				return new ResponseEntity<>("Employee not found", HttpStatus.BAD_REQUEST);
		    }
				
		} catch (Exception e) {
			
		
			return new ResponseEntity<>("Internal server error" , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable Integer employeeId) {
		
		try {
			
			Optional<Employee> employee =employeeRepository.findById(employeeId);
			if (employee.isPresent()) {
				employeeRepository.delete(employee.get());
				
	            return new ResponseEntity<>("DELETE SUCSESS" , HttpStatus.OK);
		    }else {
				return new ResponseEntity<>("Employee not found", HttpStatus.BAD_REQUEST);
		    }
				
		} catch (Exception e) {
			
		
			return new ResponseEntity<>("Internal server error" , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		

	}
}
