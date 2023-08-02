package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Department>> findAll(@RequestParam(name = "name", required = false)  String name){
		List<Department> departments = departmentService.findAll(name);
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}
	
	 @Operation(summary = "Find a department")
	    @ApiResponses({
	    	@ApiResponse(responseCode = "200", description = "OK"),
	    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
	    	@ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
	    })
	    @GetMapping(path = "/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id) {
	        Department department = departmentService.findById(id);
	        if (department == null) {
	        	//já subentende que o BODY é "department" (implícito), não é necessário informar
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } else {
	            return new ResponseEntity<>(department, HttpStatus.OK);
	        }
	    }

	    @Operation(summary = "Save a department")
	    @ApiResponses({
	    	@ApiResponse(responseCode = "201", description = "Created"),
	    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
	    })
	    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<Department> save(@RequestBody Department department) {
	        try {
	            department = departmentService.save(department);
	            return new ResponseEntity<>(department, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }

	    @Operation(summary = "Update a department")
	    @ApiResponses({
	    	@ApiResponse(responseCode = "200", description = "OK"),
	    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
	    	@ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
	    })
	    @PutMapping(path = "/{department_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<Department> update(@PathVariable(name = "department_id") Long id,
	                                             @RequestBody Department department) {
	        department.setId(id);
	        try {
	            department = departmentService.update(department);
	            if (department == null) {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            } else {
	                return new ResponseEntity<>(department, HttpStatus.OK);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }

	    @Operation(summary = "Delete a department")
	    @ApiResponses({
	    	@ApiResponse(responseCode = "204", description = "No Content"),
	    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
	    })
	    @DeleteMapping(path = "/{department_id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id) {
	        departmentService.deleteById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    @Operation(summary = "Delete all departments")
	    @ApiResponses({
	    	@ApiResponse(responseCode = "204", description = "No Content")
	    })
	    @DeleteMapping
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public ResponseEntity<Void> deleteAll() {
	        departmentService.deleteAll();
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	}

