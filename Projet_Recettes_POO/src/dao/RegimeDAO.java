package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Regime;

public class RegimeDAO implements Dao<Regime> {

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

	public List<String> getNames() {
		String sql = "SELECT libelle FROM Regime";

		List<String> typeQte = new ArrayList<String>();

		this.connect();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				typeQte.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.closeConnection();
		return typeQte;
	}

	public Regime getRegimeId(String name) {
		String sql = "SELECT id FROM Regime where libelle = ?";

		this.connect();

		Regime regime = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				regime = new Regime(rs.getInt(1), name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.closeConnection();
		return regime;
	}

	@Override
	public void create(Regime r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Regime r, String[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Regime r) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Regime> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}