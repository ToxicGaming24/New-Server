package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import lombok.Getter;
import lombok.Setter;

public class MySQL {
	@Getter
	@Setter
	private static String user;
	@Getter
	@Setter
	private static String pass;
	@Getter
	@Setter
	private static String host;
	@Getter
	@Setter
	private static String database;
	@Getter
	@Setter
	private static int port;
	@Getter
	@Setter
	private static Connection connection;
	
	public void connect() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database,user,pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void close() {
		try{
			if(connection != null) {
				connection.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void Update(String qry) {
		  try {
		      java.sql.Statement stmt = connection.createStatement();
		      stmt.executeUpdate(qry);
		      stmt.close();
		  } catch (Exception ex) {
		      System.out.println(ex.getMessage());
		  }
		 }
		 public static ResultSet Query(String qry) {
		  ResultSet rs = null;
		  try {
		      java.sql.PreparedStatement stmt = connection.prepareStatement(qry);
		      rs = stmt.executeQuery(qry);
		      return rs;
		  } catch (Exception ex) {
		      System.out.println(ex.getMessage());
		  }
		return rs;
	}
}
