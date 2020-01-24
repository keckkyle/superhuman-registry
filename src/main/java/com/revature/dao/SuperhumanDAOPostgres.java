package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Superhuman;
import com.revature.util.ConnectionFactory;
import com.revature.util.LogUtil;

public class SuperhumanDAOPostgres implements SuperhumanDAO {
	
	private static LogUtil log = new LogUtil();	

	@Override
	public boolean createSuperhuman(Superhuman sh) {
		String sql = "insert into superhuman (human_name, alias, origin, abilities, alignment) values (?,?,?,?,?)";
		
		Connection conn = ConnectionFactory.getConnection();
		
		boolean isSuccessful = false;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, sh.getHumanName());
			stmt.setString(2, sh.getAlias());
			stmt.setString(3, sh.getOrigin());
			stmt.setString(4, sh.getAbilities());
			stmt.setInt(5, sh.getAlignment());
			
			stmt.executeUpdate();
			
			log.info("New superhuman, " + sh.getAlias() + ", has been added to registry.");
			isSuccessful = true;
		} catch (SQLException e) {
			log.debug(e.getMessage());
			isSuccessful = true;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
		return isSuccessful;
	}

	@Override
	public Superhuman readSuperhuman(String alias) {
		String sql = "select * from superhuman where alias = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		
		Superhuman sh = null;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, alias);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				sh = new Superhuman(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
			}
			
			log.info("Record for " + alias + " has been accessed.");
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
		
		return sh;
	}

	@Override
	public boolean updateSuperhuman(Superhuman sh) {
		String sql = "update superhuman set human_name = ?, alias = ?, origin = ?, abilities = ?, alignment = ? where id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		
		boolean isSuccessful = false;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, sh.getHumanName());
			stmt.setString(2, sh.getAlias());
			stmt.setString(3, sh.getOrigin());
			stmt.setString(4, sh.getAbilities());
			stmt.setInt(5, sh.getAlignment());
			stmt.setInt(6, sh.getId());
			
			stmt.executeUpdate();
			
			log.info("Record for " + sh.getAlias() + " has been updated.");
			isSuccessful = true;
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
				isSuccessful = false;
			}
		}
		return isSuccessful;
	}

	@Override
	public boolean deleteSuperhuman(Superhuman sh) {
		String sql = "update superhuman set is_active = false where id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		
		boolean isSuccessful = false;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, sh.getId());
			
			stmt.executeUpdate();
			
			log.info(sh.getAlias() + " has been removed from active registry.");
			isSuccessful = true;
		} catch (SQLException e) {
			log.debug(e.getMessage());
			isSuccessful = false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
		return isSuccessful;
	}

	@Override
	public List<Superhuman> readAllSuperhumans() {
		String sql = "select * from superhuman where is_active = true";
		
		Connection conn = ConnectionFactory.getConnection();
		
		List<Superhuman> superList = new ArrayList<Superhuman>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				superList.add(new Superhuman(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
			}
			
			log.info("All active records have been accessed.");
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.debug(e.getMessage());
			}
		}
		
		return superList;
	}

}
