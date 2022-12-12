package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Gout;
import application.Ingredient;
import application.ProfileUstensile;
import application.Ustensile;

public class ProfileUstensileDAO implements Dao<ProfileUstensile> {


private Connection conn;
	
	private void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:db/testDb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            
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
	public List<ProfileUstensile> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProfileUstensile> getAll(int profileId) {

		List<ProfileUstensile> equipement = new ArrayList<ProfileUstensile>();
		
		this.connect();
		
		String sql = "SELECT profileUstensile.profileId, profileUstensile.ustensileId, Ustensile.nom  FROM profileUstensile LEFT JOIN Ustensile ON "
				+ "profileUstensile.ustensileId = Ustensile.id WHERE profileId = ?";
		// Récupération infos générales d'une recette
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, profileId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				equipement.add(new ProfileUstensile(rs.getInt(1), new Ustensile(rs.getInt(2), rs.getString(3))));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		return equipement;	
		
	}
	
	@Override
	public void create(ProfileUstensile t) {
		

		String sql = "INSERT INTO profileUstensile(profileId,ustensileId) VALUES(?,?)";
		
		this.connect();
		
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, t.getProfileId());
			pstmt.setInt(2, t.getUstensile().getId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection();
		
	}

	@Override
	public void update(ProfileUstensile t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProfileUstensile t) {
		
		
	}

}
