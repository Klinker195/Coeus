package dao;

import java.util.ArrayList;

import entities.Skill;

public interface SkillDAO {

	ArrayList<String> getAllSkillNames();

	void insertNewSkills(ArrayList<Skill> SkillList);
	
}