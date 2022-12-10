package dao;
import java.util.ArrayList;
import java.util.List;

import application.Recette;
import application.RecetteRegime;
import application.RecetteUstensile;
import application.Regime;
import application.Ustensile;
import dao.Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		
		this.connect();
		String sql = "SELECT * from Recette";
		// Récupération infos générales d'une recette
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				EtapeDAO etapeDAO = new EtapeDAO();
				RecetteUstensileDAO ruDao = new RecetteUstensileDAO();
				List<RecetteUstensile> rustensiles = ruDao.getAll(rs.getInt(1));
				ArrayList<Ustensile> ustensiles = new ArrayList<>();
				for(RecetteUstensile ru : rustensiles) {
					ustensiles.add(ru.getUstensile());
				}
				QuantiteIngredientDAO qiDao = new QuantiteIngredientDAO();
				
				RecetteRegimeDAO rrDao = new RecetteRegimeDAO();
				List<RecetteRegime> rregimes = rrDao.getAll(rs.getInt(1));
				ArrayList<Regime> regimes = new ArrayList<>();
				for(RecetteRegime rr : rregimes) {
					regimes.add(rr.getRegime());
				}
				
				
				recettes.add(new Recette(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getString(6),rs.getString(7),
						etapeDAO.getAll(rs.getInt(1)),ustensiles, qiDao.getAll(rs.getInt(1)), regimes));

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
		String sql = "INSERT INTO Recette(nom,description,duree,difficulte,prix) VALUES(?,?,?,?,?)";
		
		this.connect();
		
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getDesc());
			pstmt.setInt(3, t.getDuree());
			pstmt.setString(4, t.getDifficulte());
			pstmt.setString(5, t.getPrix());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection();
		
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
