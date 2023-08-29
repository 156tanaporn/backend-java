package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;


@RestController
public class StudentController {
		
		@Autowired
		StudentRepository studentRepository;
		
		private List<Student> data =  new ArrayList<Student>(); 
		
		
		@GetMapping("/student")
		public ResponseEntity<Object> getStudent() {
			
			try {
				
				List<Student> students = studentRepository.findAll();
				
				return new ResponseEntity<>(students, HttpStatus.OK);
				
				
			} catch (Exception e) {
				
			
				return new ResponseEntity<>("Internal server error" , HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		 }
		
		public StudentRepository getStudentRepository() {
			return studentRepository;
		}

		public void setStudentRepository(StudentRepository studentRepository) {
			this.studentRepository = studentRepository;
		}

		@PostMapping("/student")
		public ResponseEntity<Object> addStudent(@RequestBody Student body) {
	           
			try {
				
				 
				 
				  Student student = studentRepository.save(body);
				
				  return new ResponseEntity<>(student, HttpStatus.CREATED);
				
				
			} catch (Exception e) {
				
			
				return new ResponseEntity<>("Internal server error" , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@PutMapping("/student/{id}")
		public ResponseEntity<Object> updateStudent(@PathVariable Integer id ,@RequestBody Student body) {
			
	         try {
				
	        	 Optional<Student> student = studentRepository.findById(id);
	     		
	     	
				if (student.isPresent()) {
	     			
	     		    student.get().setFirstName(body.getFirstName());
	     	        student.get().setLastName(body.getLastName());
	     	        student.get().setEmail(body.getEmail());
	     	        student.get().setStudentid(body.getStudentid());
	     	        
	     	        studentRepository.save(body);
	     	        return new ResponseEntity<>(student, HttpStatus.OK);
			    }else {
					return new ResponseEntity<>("Student not found", HttpStatus.BAD_REQUEST);
			    }
					
			} catch (Exception e) {
				
			
				return new ResponseEntity<>("Internal server error" , HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
		}
		
		@DeleteMapping("/student/{id}")
		public ResponseEntity<Object> deleteEmployee(@PathVariable Integer id) {
			
			try {
				
				Optional<Student> student =studentRepository.findById(id);
				if (student.isPresent()) {
					studentRepository.delete(student.get());
					
		            return new ResponseEntity<>("DELETE SUCSESS" , HttpStatus.OK);
			    }else {
					return new ResponseEntity<>("Employee not found", HttpStatus.BAD_REQUEST);
			    }
					
			} catch (Exception e) {
				
			
				return new ResponseEntity<>("Internal server error" , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		

		public List<Student> getData() {
			return data;
		}

		public void setData(List<Student> data) {
			this.data = data;
		}
		
}
