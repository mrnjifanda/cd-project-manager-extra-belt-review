package com.njifanda.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.njifanda.project.Models.Project;
import com.njifanda.project.Models.User;
import com.njifanda.project.Repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project create(User user, Project project, BindingResult result) {

		
		return this.projectRepository.save(project);
	}
}
