package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.example.demo.model.Employee;

import jakarta.websocket.server.PathParam;

@RestController
public class EmployeeController {
	
	private List<Employee> data =  new ArrayList<Employee>(); 
	
	@GetMapping("/employee")
	public List<Employee> getEmployee() {
		return data;
	    }
	
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee body) {
		
		for (int i = 0; i < data.size();i++) {
			if (body.getEmployeeId() == data.get(i).getEmployeeId())
				
            return null;
	        }
	
		data.add(body);
		return body;
		
	}
	
	@GetMapping("/employee/{employeeId}")
	private Employee getEmployeeDetail(@PathVariable Integer employeeId) {
		System.out.println("employeeid = "+employeeId);
		
			
		for (int i = 0; i < data.size();i++) {
			if (employeeId == data.get(i).getEmployeeId()) {
					
	        return data.get(i);
		    }
		}
		
		return null;
	}
	
	@PutMapping("/employee/{employeeId}")
	public Employee updateEmployee(@PathVariable Integer employeeId ,@RequestBody Employee body) {
		
		
		for (int i = 0; i < data.size();i++) {
			if (employeeId == data.get(i).getEmployeeId()) {
					
	        data.get(i).setFirstName(body.getFirstName());
	        data.get(i).setLastName(body.getLastName());
	        data.get(i).setSalary(body.getSalary());
	        return data.get(i);
		    }
		}
		
		return null;
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public String deleteEmployee(@PathVariable Integer employeeId) {
		
		for (int i = 0; i < data.size();i++) {
			if (employeeId == data.get(i).getEmployeeId()) {
				data.remove(i);
				return "delete sucess";
			}
		}
				
		return "employee not found";
	}


}
