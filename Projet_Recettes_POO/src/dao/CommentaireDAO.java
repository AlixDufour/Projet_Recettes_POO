package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.Commentaire;
import application.Note;

public class CommentaireDAO implements Dao<Commentaire> {

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
	public List<Commentaire> getAll() {
		
		List<Commentaire> comms = new ArrayList<>();
		
		String sql = "SELECT * FROM Commentaires";
		
		this.connect();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
				
			while(rs.next()) {
				comms.add(new Commentaire(rs.getInt(1),rs.getInt(2), rs.getString(3)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		return comms;
	}


	public Commentaire getCommentaireByIDs(int profileId, int recetteId) {
		Commentaire comm = null;
		
		String sql = "SELECT * FROM Commentaires WHERE profileId = ? AND recetteId = ?";
		
		this.connect();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, profileId);
			pstmt.setInt(2, recetteId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				comm = new Commentaire(rs.getInt(1), rs.getInt(2), rs.getString(3));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		return comm;
	}
	
	@Override
	public void create(Commentaire t) {
		
		String sql = "INSERT INTO Commentaires(profileId, recetteId, commentaire) VALUES(?,?,?)";
		
		this.connect();
		
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, t.getProfileId());
			pstmt.setInt(2, t.getRecetteId());
			pstmt.setString(3, t.getCommentaire());

			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection();
	
	}

	@Override
	public void update(Commentaire t, String[] params) {
		
		this.connect();
		
		String sql = "UPDATE Commentaires SET commentaire = ? "

                + "WHERE profileId = ? AND recetteId = ? ";
		
		//Maj des infos générales
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			pstmt.setString(1, t.getCommentaire());
			pstmt.setInt(2, t.getProfileId());
			pstmt.setInt(3, t.getRecetteId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		this.closeConnection();
		
	}

	@Override
	public void delete(Commentaire t) {
		// TODO Auto-generated method stub
		
	}

}
