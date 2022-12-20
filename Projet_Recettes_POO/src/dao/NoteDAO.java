package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.Datas.Note;

public class NoteDAO implements Dao<Note> {

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
	public List<Note> getAll() {
		
		List<Note> notes = new ArrayList<>();
		
		String sql = "SELECT * FROM profileNote";
		
		this.connect();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				notes.add(new Note(rs.getInt(1),rs.getInt(2), rs.getInt(3)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		return notes;
		
	}

	public Note getNoteByIDs(int profileId, int recetteId) {
		Note note = null;
		
		String sql = "SELECT * FROM profileNote WHERE profileId = ? AND recetteId = ?";
		
		this.connect();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, profileId);
			pstmt.setInt(2, recetteId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				note = new Note(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.closeConnection();
		// TODO Auto-generated method stub
		return note;
	}
	
	@Override
	public void create(Note t) {
		
		String sql = "INSERT INTO profileNote(profileId, recetteId, note) VALUES(?,?,?)";
		
		this.connect();
		
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, t.getProfileId());
			pstmt.setInt(2, t.getRecetteId());
			pstmt.setInt(3, t.getNote());

			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection();
		
	}

	@Override
	public void update(Note t, String[] params) {
		

		this.connect();
		
		String sql = "UPDATE profileNote SET note = ? "

                + "WHERE profileId = ? AND recetteId = ? ";
		
		//Maj des infos générales
		try {
			PreparedStatement pstmt = this.conn.prepareStatement(sql);

			pstmt.setInt(1, t.getNote());
			pstmt.setInt(2, t.getProfileId());
			pstmt.setInt(3, t.getRecetteId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		this.closeConnection();
		
	}

	@Override
	public void delete(Note t) {
		// TODO Auto-generated method stub
		
	}

}
