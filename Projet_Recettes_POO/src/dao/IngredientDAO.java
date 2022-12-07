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
import application.Recette;

public class IngredientDAO implements Dao<Ingredient> {

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
	public List<Ingredient> getAll() {
		// TODO Auto-generated method stub
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		String sql = "SELECT * from Ingredient";
		
		this.connect();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ingredients.add(new Ingredient(rs.getString(2)));
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
	public void create(Ingredient t) {
		
		String sql = "INSERT INTO Ingredient(nom) VALUES(?)";
		
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
	public void update(Ingredient t, String[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Ingredient t) {
		// TODO Auto-generated method stub

	}

}
