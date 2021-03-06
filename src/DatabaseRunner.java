import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.nio.file.*; //this is to use Path

public class DatabaseRunner {
	public static Path dbpath = FileSystems.getDefault().getPath("db", "LateNightCravings");
	public static String dirPath = Paths.get("").toAbsolutePath().toString();
	public static String LoggedInUsername;
	public static String databasepath = "jdbc:sqlite:" + dirPath + "/" + dbpath;
	public static Connection connect = null;
	public static Homepage Homepage1;
	public static DatabaseRunner db = new DatabaseRunner();
	public UserPage up;
	private static Homepage hp;

	public static void main(String[] args) {
		
		db.setupConnection();
		
//		 CreateProfile pr = new CreateProfile();
//		 RestaurantTables tr = new RestaurantTables(10,10);
		hp = new Homepage();
		
	}

	public DatabaseRunner() {
		
	}

	public static Connection setupConnection() {
		try {
			connect = DriverManager.getConnection(databasepath);
			System.out.println("DEBUG(setupConnection): Set up a new connection on: " + databasepath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}

	/*
	 * @param connect: the database connection.
	 *
	 * @param query: what SQL query to execute
	 */
	public ResultSet executeQuery(String SQLquery) {
		if (connect != null) {
			System.out.println("DEBUG(executeQuery): SQL Query: " + SQLquery);
			String type = queryType(SQLquery);
			PreparedStatement query;

			if (type.equals("SELECT")) {
				ResultSet resultSet = null;
				try {
					query = connect.prepareStatement(SQLquery);
					resultSet = query.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return resultSet;
			} else {
				try {
					query = connect.prepareStatement(SQLquery);
					query.execute();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Sorry, something went wrong." + e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}

	private String queryType(String SQLquery) {
		// System.out.println("DEBUG(queryType): " +
		// SQLquery.substring(0,SQLquery.indexOf(' ')));
		return SQLquery.substring(0, SQLquery.indexOf(' '));
	}
	public void setLoggedIn(String s) {
		LoggedInUsername = s;
		hp.setButton();
		hp.setLabel(s);
	}
	public String getLoggedIn() {
		return LoggedInUsername;
	}

}

