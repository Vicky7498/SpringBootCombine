package com.infy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import com.infy.dto.ProjectDTO;
import com.infy.dto.TeamMemberDTO;
import com.infy.exception.AbcException;
import com.infy.service.ProjectService;

@SpringBootApplication
public class AbcToTraineeApplication implements CommandLineRunner {

	public static final Log LOGGER = LogFactory.getLog(AbcToTraineeApplication.class);

	@Autowired
	ProjectService projectService;
	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(AbcToTraineeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		addProject();
		getProjectDetails();
	}

	public void addProject() {
		ProjectDTO project = new ProjectDTO();
		TeamMemberDTO member1 = new TeamMemberDTO();
		TeamMemberDTO member2 = new TeamMemberDTO();
		List<TeamMemberDTO> memberList = new ArrayList<TeamMemberDTO>();
		project.setProjectName("FSADM8");
		project.setCost(200000);
		project.setTeamSize(5);
		project.setTechnologyUsed("Java");
		member1.setEmployeeId(722009);
		member1.setEmployeeName("Robin");
		member1.setDesignation("SSC");
		member1.setSkills("Java , Oracle");
		member2.setEmployeeId(722019);
		member2.setEmployeeName("Vicky");
		member2.setDesignation("SSC");
		member2.setSkills("Java, Python");
		memberList.add(member1);
		memberList.add(member2);
		project.setMemberList(memberList);
		try {
			Integer projectId = projectService.addProject(project);
			LOGGER.info(environment.getProperty("UserInterface.PROJECT_ADDED_SUCCESS") + projectId);
		} catch (AbcException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
	}

	public void getProjectDetails() {
		try {
			LOGGER.info(projectService.getProjectDetails("Angular"));
		} catch (AbcException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
	}

}