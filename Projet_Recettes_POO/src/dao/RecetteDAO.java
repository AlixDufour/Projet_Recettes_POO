package dao;
import java.util.ArrayList;
import java.util.List;

import application.Recette;
import dao.Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecetteDAO implements Dao<Recette>{

	
	private Connection conn;
	
	public RecetteDAO() {
		conn = null;
	}
	
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
	public List<Recette> getAll() {
		
		List<Recette> recettes = new ArrayList<Recette>();
		
		String sql = "SELECT * from Recette";
		
		this.connect();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				recettes.add(new Recette(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getString(6)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		return recettes;
	}

	@Override
	public void create(Recette t) {
		// TODO Auto-generated method stub
		
	}	

	@Override
	public void update(Recette t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Recette t) {
		// TODO Auto-generated method stub
		
	}

	
}
