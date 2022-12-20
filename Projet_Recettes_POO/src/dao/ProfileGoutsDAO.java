package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Datas.Gout;
import application.Datas.Ingredient;

public class ProfileGoutsDAO implements Dao<Gout> {

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
	public List<Gout> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Gout> getAll(int profileId) {
		
		List<Gout> gouts = new ArrayList<Gout>();
		
		this.connect();
		String sql = "SELECT profileGouts.profileId, profileGouts.ingredientId, Ingredient.nom FROM profileGouts LEFT JOIN Ingredient ON "
				+ "profileGouts.ingredientId = Ingredient.id WHERE profileId = ?";
		// Récupération infos générales d'une recette
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, profileId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				gouts.add(new Gout(rs.getInt(1), new Ingredient(rs.getInt(2), rs.getString(3))));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		return gouts;	
		
	}
	

	@Override
	public void create(Gout t) {
		
		String sql = "INSERT INTO profileGouts(profileId,ingredientId) VALUES(?,?)";
		
		this.connect();
		
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, t.getProfileId());
			pstmt.setInt(2, t.getIngredient().getId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection();

	}

	@Override
	public void update(Gout t, String[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Gout t) {
		// TODO Auto-generated method stub

	}

}
