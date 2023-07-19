package com.project.professor.allocation.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;


	@Service
	public class ProfessorService {
		
		private ProfessorRepository repo;
		
		public ProfessorService(ProfessorRepository repo) {
			
			this.repo = repo;
		}
		
		public Professor findById(Long id) {
			
			
			Optional<Professor> findById = repo.findById(id);
			Professor professor = findById.orElse(null);
			return professor;
		}
	}
