package com.revature.pojos;

public class Superhuman {

	private int id;
	private String humanName;
	private String alias;
	private String origin;
	private String abilities;
	private int alignment;

	public Superhuman(String humanName, String alias, int alignment) {
		super();
		this.humanName = humanName;
		this.alias = alias;
		this.alignment = alignment;
	}

	public Superhuman(String humanName, String alias, String origin, String abilities, int alignment) {
		super();
		this.humanName = humanName;
		this.alias = alias;
		this.origin = origin;
		this.abilities = abilities;
		this.alignment = alignment;
	}
	
	public Superhuman(int id, String humanName, String alias, String origin, String abilities, int alignment) {
		super();
		this.id = id;
		this.humanName = humanName;
		this.alias = alias;
		this.origin = origin;
		this.abilities = abilities;
		this.alignment = alignment;
	}

	public String getHumanName() {
		return humanName;
	}

	public String getAlias() {
		return alias;
	}

	public String getOrigin() {
		return origin;
	}

	public String getAbilities() {
		return abilities;
	}

	public int getAlignment() {
		return alignment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setAbilities(String abilities) {
		this.abilities = abilities;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}
	
	public String getAlignmentString() {
		String align;
		switch(alignment) {
		case 1:
			align = "Hero";
			break;
		case 2:
			align = "Villain";
			break;
		case 3:
			align = "Vigilante";
			break;
		default:
			align = "Unknown";
		}
		return align;
	}

	@Override
	public String toString() {
		return "Superhuman [humanName=" + humanName + ", alias=" + alias + ", origin=" + origin + ", abilities="
				+ abilities + ", alignment=" + this.getAlignmentString() + "]";
	}
	
}
