package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.Ingredient;
import application.Ustensile;

public class UstensileDAO implements Dao<Ustensile> {

	Connection conn;
	
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
	public List<Ustensile> getAll() {
		
		List<Ustensile> ingredients = new ArrayList<Ustensile>();
		
		String sql = "SELECT * from Ustensile";
		
		this.connect();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ingredients.add(new Ustensile(rs.getInt(1),rs.getString(2)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		return ingredients;

	}

	@Override
	public void create(Ustensile t) {
		
		
		String sql = "INSERT INTO Ustensile(nom) VALUES(?)";
		
		this.connect();
		
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, t.getNom());

			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection();

	}

	@Override
	public void update(Ustensile t, String[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Ustensile t) {
		// TODO Auto-generated method stub
	}
	
	public List<String> getUstensilesNames(){
		String sql = "SELECT nom FROM Ustensile";

		List<String> ustensiles = new ArrayList<String>();
		this.connect();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				ustensiles.add(rs.getString(1));
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

		this.closeConnection();
		return ustensiles;	
	}
	
	public Ustensile getUstensileId(String name){
		String sql = "SELECT id FROM Ustensile where nom = ?";
		
		this.connect();
		
		Ustensile ustensile = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				ustensile = new Ustensile(rs.getInt(1), name);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

		this.closeConnection();
		return ustensile;
	}

}
