package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.Datas.Ingredient;

public class IngredientDAO implements Dao<Ingredient> {

	Connection conn;

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
	public List<Ingredient> getAll() {
		// TODO Auto-generated method stub

		List<Ingredient> ingredients = new ArrayList<Ingredient>();

		String sql = "SELECT * from Ingredient";

		this.connect();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				ingredients.add(new Ingredient(rs.getInt(1),rs.getString(2)));
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

	public List<String> getIngredientsNames(){
		String sql = "SELECT nom FROM Ingredient";

		List<String> ingredients = new ArrayList<String>();

		this.connect();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				ingredients.add(rs.getString(1));
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

		this.closeConnection();
		return ingredients;	
	}

	public Ingredient getIngredientId(String name){
		String sql = "SELECT id FROM Ingredient where nom = ?";
		
		this.connect();
		
		Ingredient ingredient = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				ingredient = new Ingredient(rs.getInt(1), name);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

		this.closeConnection();
		return ingredient;
	}
}
