package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.Etape;
import application.Ingredient;

public class EtapeDAO implements Dao<Etape> {
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
	public List<Etape> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Etape> getAll(int recetteId){
		
		List<Etape> etapes = new ArrayList<Etape>();

		String sql = "SELECT * FROM Etape WHERE Etape.recetteId = ?";
		
		this.connect();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recetteId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				etapes.add(new Etape(recetteId, rs.getInt(2), rs.getString(3)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		return etapes;
	}

	@Override
	public void create(Etape t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Etape t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Etape t) {
		// TODO Auto-generated method stub
		
	}

}
