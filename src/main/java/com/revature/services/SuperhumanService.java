package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.SuperhumanDAOPostgres;
import com.revature.pojos.Superhuman;

public class SuperhumanService {
	
	private SuperhumanDAOPostgres shDao = new SuperhumanDAOPostgres();
	
	public String getSuperhumans() {
		List<Superhuman> supers = shDao.readAllSuperhumans();
		StringBuilder superString = new StringBuilder();
		superString.append("<html>"
				+ "<head>"
				+ "<meta charset=\"UTF-8\">"
				+ "<link rel=\"stylesheet\" href=\"styles/style.css\">"
				+ "<title>Superhuman Registry</title>"
				+ "</head>"
				+ "<body>"
				+ "<div class=\"table-container\">"
				+ "<h2>View Superhuman Registry</h2>"
				+ "<table>"
				+ "<thead>"
				+ "<tr><td>Name</td><td>Alias</td><td>Origin</td><td>Abilities</td><td>Alignment</td></tr>"
				+ "</thead><tbody>");
		for(Superhuman s: supers) {
			superString.append("<tr>");
			superString.append("<td>"+s.getHumanName()+"</td>");
			superString.append("<td>"+s.getAlias()+"</td>");
			superString.append("<td>"+s.getOrigin()+"</td>");
			superString.append("<td>"+s.getAbilities()+"</td>");
			superString.append("<td>"+s.getAlignmentString()+"</td>");
			superString.append("</tr>");
		}
		superString.append("</tbody></table><div class=\"button-group\">"
				+ "<a class=\"button-link\" href=\"/superhuman/create-super-human\">New</a>"
				+ "<a class=\"button-link\" href=\"/superhuman/update-super-human\">Update</a>"
				+ "<a class=\"button-link\" href=\"/superhuman/delete-super-human\">Delete</a>"
				+ "</div></div></body></html>");
		return superString.toString();
	}
	
	public boolean addSuperhuman(HttpServletRequest req) {
		String name = req.getParameter("human-name");
		String alias = req.getParameter("alias");
		String origin = req.getParameter("origin");
		String abilities = req.getParameter("abilities");
		String alignment = req.getParameter("alignment");
		
		int align = Integer.parseInt(alignment);
		
		Superhuman sh = new Superhuman(name, alias, origin, abilities, align);
		
		return shDao.createSuperhuman(sh);
	}
	
	public boolean updateSuperhuman(HttpServletRequest req) {
		String alias = req.getParameter("alias");
		
		Superhuman supHum = verifySuperhuman(alias);

		String alignment = req.getParameter("alignment");
		String humName = req.getParameter("human-name");
		String newAlias = req.getParameter("update-alias");
		String origin = req.getParameter("origin");
		String abilities = req.getParameter("abilities");
		
		int align = -1;
		if(alignment != null) {
			align = Integer.parseInt(alignment);
		}
		
		if(supHum != null) {
			if(!"".equals(humName)) {
				supHum.setHumanName(humName);
			}
			if(!"".equals(newAlias)) {
				supHum.setAlias(newAlias);
			}
			if(!"".equals(origin)) {
				supHum.setOrigin(origin);
			}
			if(!"".equals(abilities)) {
				supHum.setAbilities(abilities);
			}
			if(align != -1) {
				supHum.setAlignment(align);
			}
			
			return shDao.updateSuperhuman(supHum);
		}
		return false;
	}
	
	public boolean deleteSuperhuman(HttpServletRequest req) {
		
		String alias = req.getParameter("alias");
		
		Superhuman supHum = verifySuperhuman(alias);

		if(supHum != null) {
			return shDao.deleteSuperhuman(supHum);
		}
		return false;
	}
	
	private Superhuman verifySuperhuman(String alias) {
		List<Superhuman> supers = shDao.readAllSuperhumans();
		Superhuman found = null;
		for(Superhuman s: supers) {
			if(alias.equalsIgnoreCase(s.getAlias())){
				found = s;
			}
		}
		return found;
	}
}
