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

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository departmentRepository;

	@Test
	void findAll() {
		List<Department> department = departmentRepository.findAll();
		System.out.println(department);
	}

	@Test
	void findById() {
		Long id = 3l;
		Optional<Department> department = departmentRepository.findById(id);
		Department dpt = department.orElse(null);
		System.out.println(dpt);
	}

	@Test
	void creat() {
		Department dpt1 = new Department();
		dpt1.setName("Departamento de Ciências da Saúde");
		dpt1.setId(null);
		Department dpt2 = departmentRepository.save(dpt1);
		System.out.println(dpt2);
	}

	@Test
	void update() {
		Department dpt1 = new Department();
		dpt1.setName("Psicologia 2");
		dpt1.setId(2l);
		Department dpt2 = departmentRepository.save(dpt1);
		System.out.println(dpt2);

	}
	@Test
	void deleteById() {
		//Long id = 4l;
		departmentRepository.deleteById(4l);
	}
	
	@Test
	void deleteAll() {
		
		departmentRepository.deleteAllInBatch();
	}
}
