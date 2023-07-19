package com.project.professor.allocation.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	private DepartmentRepository repo;
	
	public DepartmentService(DepartmentRepository repo) {
		
		this.repo = repo;
	}
	
	public Department findById(Long id) {
		
		
		Optional<Department> dptfind = repo.findById(id);
		Department dpt = dptfind.orElse(null);
		return dpt;
	}

}
