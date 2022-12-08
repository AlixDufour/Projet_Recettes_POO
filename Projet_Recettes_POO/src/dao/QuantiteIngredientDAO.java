package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Ingredient;
import application.QuantiteIngredient;
import application.RecetteUstensile;
import application.TypeQuantite;
import application.Ustensile;

public class QuantiteIngredientDAO implements Dao<QuantiteIngredient> {

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
	public List<QuantiteIngredient> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<QuantiteIngredient> getAll(int recetteId) {

		List<QuantiteIngredient> quantiteIngredients = new ArrayList<QuantiteIngredient>();
		
		this.connect();
		String sql = "SELECT QuantiteIngredient.recetteId, QuantiteIngredient.ingredientId, Ingredient.nom, QuantiteIngredient.quantite, "
				+ "QuantiteIngredient.typeQuantiteId, TypeQuantite.libelle FROM QuantiteIngredient LEFT JOIN Ingredient ON "
				+ "QuantiteIngredient.ingredientId = Ingredient.id LEFT JOIN TypeQuantite ON QuantiteIngredient.typeQuantiteId = TypeQuantite.id"
				+ " WHERE recetteId = ?";
		// Récupération infos générales d'une recette
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recetteId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				quantiteIngredients.add(new QuantiteIngredient(rs.getInt(1), new Ingredient(rs.getInt(2),rs.getString(3)), rs.getInt(4),
						new TypeQuantite(rs.getInt(5), rs.getString(6))));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		return quantiteIngredients;	

	}

	@Override
	public void create(QuantiteIngredient t) {
		
		
String sql = "INSERT INTO QuantiteIngredient(recetteId,ingredientId, quantite, typeQuantiteId) VALUES(?,?,?,?)";
		
		this.connect();
		
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.setInt(2, t.getIngredient().getId());
			pstmt.setInt(3, t.getQuantite());
			pstmt.setInt(4, t.getTypeQuantite().getId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection();
	}

	@Override
	public void update(QuantiteIngredient t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(QuantiteIngredient t) {
		// TODO Auto-generated method stub
		
	}

}
