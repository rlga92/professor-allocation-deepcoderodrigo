package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {

	private AllocationRepository repo;
	private ProfessorService profService;
	private CourseService courseService;

	public AllocationService(AllocationRepository repo, ProfessorService profService, CourseService courseService) {

		this.repo = repo;
		this.profService = profService;
		this.courseService = courseService;
	}

	public Allocation findById(Long id) {

		Optional<Allocation> allocationfind = repo.findById(id);
		Allocation allocation = allocationfind.orElse(null);
		return allocation;
	}

	public List<Allocation> findAll() {

		List<Allocation> allocationList = repo.findAll();

		return allocationList;
	}

	public Allocation create(Allocation alloc) {

		alloc.setId(null);

		return saveInternal(alloc);
	}

	public Allocation update(Allocation alloc) {

		Long allocId = alloc.getId();

		if (repo.existsById(allocId)) {
			return saveInternal(alloc);

		} else {
			return null;
		}
	}

	private Allocation saveInternal(Allocation alloc) {

		if (alloc.getStart().compareTo(alloc.getEnd()) > 0) {

			throw new RuntimeException();
		} else {

			Allocation alloc1 = repo.save(alloc);

			Long professorId = alloc1.getProfessor().getId();
			Professor professor = profService.findById(professorId);
			alloc1.setProfessor(professor);

			Long courseId = alloc1.getCourse().getId();
			Course course = courseService.findById(courseId);
			alloc1.setCourse(course);
			return alloc1;
		}

	}

	public void deleteById(Long id) {

		if (repo.existsById(id)) {

			repo.deleteById(id);

		}

	}

	public void deleteAll() {

		repo.deleteAllInBatch();

	}
}
