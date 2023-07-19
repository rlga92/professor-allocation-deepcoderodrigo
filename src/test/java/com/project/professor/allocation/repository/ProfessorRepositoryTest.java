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

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {

    @Autowired
    ProfessorRepository professorRepository;

    @Test
	void findAll() {
		List<Professor> professor = professorRepository.findAll();
		System.out.println(professor);
	}

	@Test
	void findById() {
		Long id = 3l;
		Optional<Professor> professor = professorRepository.findById(id);
		Professor prof = professor.orElse(null);
		System.out.println(prof);
	}
    @Test
	void create() {
		Department department = new Department();
		department.setId(2l);
		//department.setId(null);
		Professor professor = new Professor();
		professor.setId(null);
		professor.setName("João da Silva");
		professor.setCpf("33333333333");
		professor.setDepartment(department);
		professor = professorRepository.save(professor);
		System.out.println(professor);
	}
    
    @Test
	void update() {
		Department department = new Department();
		department.setId(2l);
		Professor professor = new Professor();
		professor.setId(1l);
		professor.setName("João Mário");
		professor.setCpf("33333333333");
		professor.setDepartment(department);
		professor = professorRepository.save(professor);
		System.out.println(professor);
	}
    
    @Test
	void deleteById() {
		//Long id = 4l;
		professorRepository.deleteById(4l);
	}
	
	@Test
	void deleteAll() {
		
		professorRepository.deleteAllInBatch();
	}
}