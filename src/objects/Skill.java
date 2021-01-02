package objects;

public class Skill {

	private int SkillID;
	private String SkillName;
	
	public Skill(int SkillID, String SkillName) {
		super();
		this.SkillID = SkillID;
		this.SkillName = SkillName;
	}

	public int getSkillID() {
		return SkillID;
	}

	public void setSkillID(int skillID) {
		SkillID = skillID;
	}

	public String getSkillName() {
		return SkillName;
	}

	public void setSkillName(String skillName) {
		SkillName = skillName;
	}
	
}
