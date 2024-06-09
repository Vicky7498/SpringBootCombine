package com.infy.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.infy.dto.ProjectDTO;
import com.infy.dto.TeamMemberDTO;
import com.infy.exception.AbcException;
import com.infy.repository.ProjectRepository;
import com.infy.validator.Validator;

@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository repository;

	@Autowired
	Environment environment;

	@Override
	public Integer addProject(ProjectDTO project) throws AbcException {
		for (TeamMemberDTO teamMenber : project.getMemberList()) {
			Validator.validate(teamMenber);
		}
		return repository.addProject(project);
	}

	@Override
	public List<ProjectDTO> getProjectDetails(String technology) throws AbcException {
		List<ProjectDTO> projectDTO = new ArrayList<ProjectDTO>();
		projectDTO = repository.getProjectDetails();
		Iterator<ProjectDTO> project = projectDTO.iterator();
		while (project.hasNext()) {
			if (!project.next().getTechnologyUsed().equals(technology)) {
				project.remove();
			}
		}
		if (projectDTO.isEmpty()) {
			throw new AbcException(environment.getProperty("Service.PROJECTS_NOT_FOUND"));
		} else {
			return projectDTO;
		}
	}
}
