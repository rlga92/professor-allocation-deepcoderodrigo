package com.project.professor.allocation.repository;

import java.sql.Time;
import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

    @Autowired
    AllocationRepository allocationRepository;
    
    @Test
	void create() {
    	Professor professor = new Professor();
		professor.setId(2l);
		//department.setId(null);
		Course course = new Course();
		course.setId(3l);
		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setDay(DayOfWeek.MONDAY);
		allocation.setStart(Time.valueOf ("18:30:00"));
		allocation.setEnd(Time.valueOf ("22:00:00"));
		allocation.setCourse(course);
		allocation.setProfessor(professor);
		allocation = allocationRepository.save(allocation);
		System.out.println(allocation);
	}
    
    @Test
    void update() {
    	
    	Professor professor = new Professor();
		professor.setId(2l);
		//department.setId(null);
		Course course = new Course();
		course.setId(3l);
		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setDay(DayOfWeek.MONDAY);
		allocation.setStart(Time.valueOf ("18:30:00"));
		allocation.setEnd(Time.valueOf ("22:00:00"));
		allocation.setCourse(course);
		allocation.setProfessor(professor);
		allocation = allocationRepository.save(allocation);
		System.out.println(allocation);
    	
    }
}