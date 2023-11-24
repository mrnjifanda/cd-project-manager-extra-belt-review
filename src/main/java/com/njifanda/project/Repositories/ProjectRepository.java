package com.njifanda.project.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.njifanda.project.Models.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	List<Project> findAll();
}
