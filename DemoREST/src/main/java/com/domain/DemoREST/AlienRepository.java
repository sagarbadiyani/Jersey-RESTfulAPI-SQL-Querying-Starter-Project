package com.domain.DemoREST;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {
	
	
	Connection con = null;
	
	public AlienRepository() {
		String url = "jdbc:mysql://localhost:3306/mario";
		String uname = "root";
		String pass = "password";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, uname, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Alien> getAliens() throws SQLException{
		List<Alien> aliens = new ArrayList<Alien>();
		String sql = "select * from alien";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			Alien a = new Alien();
			a.setId(rs.getInt("id"));
			a.setName(rs.getString("name"));
			a.setPoint(rs.getInt("point"));
			
			aliens.add(a);
		}
		return aliens;
	}
	
	public Alien getAlien(int id) throws SQLException {
		Alien a = new Alien();
		String sql = "select * from alien where id = " + id;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) {
			a.setId(rs.getInt("id"));
			a.setName(rs.getString("name"));
			a.setPoint(rs.getInt("point"));
		}
		
		return a;
	}
	
	public void createAlien(Alien a) throws SQLException {
		String sql = "insert into alien value (?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, a.getId());
		st.setString(2, a.getName());
		st.setInt(3, a.getPoint());
		st.executeUpdate();
	}
	
	public void updateAlien(Alien a) throws SQLException {
		String sql = "update alien set name = ? , point = ? where id = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(3, a.getId());
		st.setString(1, a.getName());
		st.setInt(2, a.getPoint());
		st.executeUpdate();
	}

	public void deleteAlien(int id) throws SQLException {
		String sql = "delete from alien where id = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		st.executeUpdate();
	}
}




