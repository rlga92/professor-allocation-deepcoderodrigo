package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;


@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final DepartmentService departmentService;

    public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) {
        super();
        this.professorRepository = professorRepository;
        this.departmentService = departmentService;
    }

    // Esse método retorna uma lista de professores. Se o argumento name for null, ele chamará o método findAll() do repositório professorRepository 
    //para buscar todos os professores. Caso contrário, chamará o método findByNameContainingIgnoreCase(name) do repositório para buscar os professores
    //cujo nome contenha a string passada como argumento.
    public List<Professor> findAll(String name) {
        if (name == null) {
            return professorRepository.findAll();
        } else {
            return professorRepository.findByNameContainingIgnoreCase(name);
        }
    }
    
    //findById(Long id): Este método recebe um ID de professor como argumento e retorna o professor correspondente se encontrado no banco de dados. 
    //Caso contrário, retorna null

    public Professor findById(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    //findByDepartment(Department departmentId): Esse método recebe um objeto Department como argumento e retorna uma lista de professores 
    //associados a esse departamento. O método chama o método findByDepartment(departmentId) do repositório professorRepository
    public List<Professor> findByDepartment(Department departmentId) {
        return professorRepository.findByDepartment(departmentId);
    }
    
    //save(Professor professor): Esse método salva um novo professor no banco de dados. Antes de salvar, define o ID como null 
    //para garantir que seja criada uma nova entrada no banco de dados. Em seguida, chama o método saveInternal(professor) para realizar o salvamento.

    public Professor save(Professor professor) {
        professor.setId(null);
        return saveInternal(professor);
    }
    
    //update(Professor professor): Este método atualiza um professor existente no banco de dados. Verifica se o ID do professor não é null e se existe no banco de dados.
    //Se sim, chama o método saveInternal(professor) para efetuar a atualização. Caso contrário, retorna null.

    public Professor update(Professor professor) {
        Long id = professor.getId();
        if (id != null && professorRepository.existsById(id)) {
            return saveInternal(professor);
        } else {
            return null;
        }
    }

    //deleteById(Long id): Esse método recebe um ID de professor como argumento e exclui o professor correspondente no banco de dados, se existir.
    public void deleteById(Long id) {
        if (id != null && professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
        }
    }
    
    //deleteAll(): Este método exclui todos os professores do banco de dados usando o método deleteAllInBatch() do repositório professorRepository

    public void deleteAll() {
        professorRepository.deleteAllInBatch();
    }

    private Professor saveInternal(Professor professor) {
        professor = professorRepository.save(professor);

        Department department = departmentService.findById(professor.getDepartment().getId());
        professor.setDepartment(department);

        return professor;
    }

	public List<Professor> findByDepartment(Long id) {
		
		return null;
	}
}