package com.project.professor.allocation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Professor {
	@JsonProperty(access = Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false, length = 11)
	private String cpf;
	
	@Column(nullable = false)
	private String name;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "department_id", nullable = false)
    private Long departmentId;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnoreProperties({"professors"})
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id", nullable = false, insertable = false, updatable = false)
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", cpf=" + cpf + ", name=" + name + ", department=" + department + "]";
	}

	
}
