package dao;
import java.util.List;

import application.Recette;
import dao.Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RecetteDAO implements Dao<Recette>{

	public RecetteDAO() {
		connect();
	}
	
	public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:testDb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
	
	@Override
	public List<Recette> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Recette t) {
		// TODO Auto-generated method stub
		
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
