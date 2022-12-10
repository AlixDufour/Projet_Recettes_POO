package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.RecetteRegime;
import application.RecetteUstensile;
import application.Regime;
import application.Ustensile;

public class RecetteRegimeDAO implements Dao<RecetteRegime> {

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
	public List<RecetteRegime> getAll() {
		return null;
	}

	public List<String> getRegimesNames(){
		String sql = "SELECT libelle FROM Regime";

		List<String> regimes = new ArrayList<String>();

		this.connect();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				regimes.add(rs.getString(1));
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

		this.closeConnection();
		return regimes;	
	}

	public List<RecetteRegime> getAll(int recetteId) {


		List<RecetteRegime> recettesRegime = new ArrayList<RecetteRegime>();

		this.connect();
		String sql = "SELECT RecetteRegime.recetteId, RecetteRegime.regimeId, Regime.libelle FROM RecetteRegime LEFT JOIN Regime ON "
				+ "RecetteRegime.regimeId = Regime.id WHERE recetteId = ?";
		// Récupération infos générales d'une recette
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recetteId);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				recettesRegime.add(new RecetteRegime(rs.getInt(1), new Regime(rs.getInt(2), rs.getString(3))));
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		this.closeConnection();
		// TODO Auto-generated method stub
		return recettesRegime;	

	}

	@Override
	public void create(RecetteRegime t) {

		String sql = "INSERT INTO RecetteRegime(recetteId,regimeId) VALUES(?,?)";

		this.connect();

		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, t.getRecetteId());
			pstmt.setInt(2, t.getRegime().getId());

			pstmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}

		this.closeConnection();

	}

	@Override
	public void update(RecetteRegime t, String[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(RecetteRegime t) {
		// TODO Auto-generated method stub

	}
	
	public Regime getRegimeId(String name){
		String sql = "SELECT id FROM Regime where libelle = ?";
		
		this.connect();
		
		Regime regime = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				regime = new Regime(rs.getInt(1), name);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

		this.closeConnection();
		return regime;
	}

}
