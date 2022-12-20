package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Datas.RecetteUstensile;
import application.Datas.Ustensile;

public class RecetteUstensileDAO implements Dao<RecetteUstensile> {
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
	public List<RecetteUstensile> getAll() {
		
		
		return null;	

	}
	
	public List<RecetteUstensile> getAll(int recetteId){
		List<RecetteUstensile> recettesUst = new ArrayList<RecetteUstensile>();
		
		this.connect();
		String sql = "SELECT RecetteUstensile.recetteId, RecetteUstensile.ustensileId, Ustensile.nom FROM RecetteUstensile LEFT JOIN Ustensile ON "
				+ "RecetteUstensile.ustensileId = Ustensile.id WHERE recetteId = ?";
		// Récupération infos générales d'une recette
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recetteId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				recettesUst.add(new RecetteUstensile(rs.getInt(1), new Ustensile(rs.getInt(2), rs.getString(3))));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		return recettesUst;	
	}

	@Override
	public void create(RecetteUstensile t) {
		String sql = "INSERT INTO RecetteUstensile(recetteId,ustensileId) VALUES(?,?)";
		
		this.connect();
		
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, t.getRecetteId());
			pstmt.setInt(2, t.getUstensile().getId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection();

	}

	@Override
	public void update(RecetteUstensile t, String[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(RecetteUstensile t) {
		// TODO Auto-generated method stub

	}

}
