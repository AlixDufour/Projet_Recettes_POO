package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.Profile;
import application.Ustensile;

public class ProfileDAO implements Dao<Profile> {
	
	private Connection conn;

	private void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:db/testDb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
	
	private void closeConnection() {
		try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	
	@Override
	public List<Profile> getAll() {
		
List<Profile> profiles = new ArrayList<Profile>();
		
		String sql = "SELECT Profile.id, Profile.prenom, Profile.nom, Profile.regimeId, Regime.libelle FROM "
				+ "Profile LEFT JOIN Regime ON Profile.regimeId = Regime.id ";
		
		this.connect();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				profiles.add(new Profile(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		
		return profiles;

	}

	@Override
	public void create(Profile t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Profile t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Profile t) {
		// TODO Auto-generated method stub
		
	}

}
