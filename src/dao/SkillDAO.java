package dao;

import java.util.ArrayList;

import entities.Skill;

public interface SkillDAO {

	boolean existsByName(String SkillName);
	
	ArrayList<String> getAllSkillNames();

	void insertNewSkills(ArrayList<Skill> SkillList);

	ArrayList<Skill> getEmployeeSkillsByCF(String EmployeeCF);

	void deleteSkillByName(String SkillName);

	void insertNewSkill(String SkillName);
	
}