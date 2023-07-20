package com.project.professor.allocation.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
	void findAll() {
		List<Course> course = courseRepository.findAll();
		System.out.println(course);
	}

	@Test
	void findById() {
		Long id = 3l;
		Optional<Course> course = courseRepository.findById(id);
		Course cour = course.orElse(null);
		System.out.println(cour);
	}
    
	@Test
	void creat() {
		Course course = new Course();
		course.setName("Curso de Cinema");
		
		course.setId(null);
		Course course1 = courseRepository.save(course);
		
		System.out.println(course1);
	}

	@Test
	void update() {
		Course course = new Course();
		course.setName("Curso de História");
		course.setId(22l);
		Course course1 = courseRepository.save(course);
		System.out.println(course1);

	}
	@Test
	void deleteById() {
		//Long id = 4l;
		courseRepository.deleteById(22l);
	}
	
	@Test
	void deleteAll() {
		
		courseRepository.deleteAllInBatch();
	}
}