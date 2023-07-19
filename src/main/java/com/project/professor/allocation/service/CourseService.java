package com.project.professor.allocation.service;

import java.util.Optional;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

public class CourseService {

	private CourseRepository repo;
	
	public CourseService(CourseRepository repo) {
		
		this.repo = repo;
	}
	
	public Course findById(Long id) {
		
		
		Optional<Course> coursefind = repo.findById(id);
		Course course = coursefind.orElse(null);
		return course;
	}
}
