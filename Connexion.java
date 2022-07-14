package application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion {

	// ======================================================
	// variables
	
	private static String user = "root";
    private static String db = "jdbc:mariadb://localhost/java_formation_bibliotheque";
    private static String pass = "rootmdp";
    //private static String driver = "com.maria.jdbc.Driver";
    private static String driver = "org.mariadb.jdbc.Driver";
    
    private static Connection con = null;
    
    // ======================================================
    
    // modif 01A
    // public static Connection getCon() throws SQLException, ClassNotFoundException {
    public static Connection getCon() {
        
    //
    try {
    	
    	// modif 01B
        // Class.forName(driver);
    	
        con = DriverManager.getConnection(db, user, pass);
        
        // System.out.println("Connexion effective.");
        
    // modif 01C
    // } catch (SQLException | ClassNotFoundException except) {
    } catch (SQLException except) {
    	
    	System.out.println("Erreur:");
    	System.out.println("Fichier: application.connexion.Connexion.java");
    	System.out.println("Methode: getCon()");
        
    	Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, except);
    	
    }
    
    return con;
 
    }
    
    // ======================================================
    
    public static void dbDisconnect() throws SQLException {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e){
           throw e;
        }
    }
    
    // ======================================================
    
}

