package com.revature.dao;

import java.util.List;

import com.revature.pojos.Superhuman;

public interface SuperhumanDAO {
	
	public boolean createSuperhuman(Superhuman sh);
	
	public Superhuman readSuperhuman(String name);
	
	public boolean updateSuperhuman(Superhuman sh);
	
	public boolean deleteSuperhuman(Superhuman sh);
	
	public List<Superhuman> readAllSuperhumans();
}
