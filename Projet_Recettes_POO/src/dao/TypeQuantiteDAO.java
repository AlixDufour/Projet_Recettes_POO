package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Datas.TypeQuantite;

public class TypeQuantiteDAO implements Dao<TypeQuantite> {

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
		String sql = "SELECT libelle FROM TypeQuantite";

		List<String> typeQte = new ArrayList<String>();

		this.connect();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				typeQte.add(rs.getString(1));
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

		this.closeConnection();
		return typeQte;	
	}
	
	public TypeQuantite gettypeQteId(String name){
		String sql = "SELECT id FROM TypeQuantite where libelle = ?";
		
		this.connect();
		
		TypeQuantite typeQte = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				typeQte = new TypeQuantite(rs.getInt(1), name);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

		this.closeConnection();
		return typeQte;
	}

	@Override
	public void create(TypeQuantite t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TypeQuantite t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TypeQuantite t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TypeQuantite> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
