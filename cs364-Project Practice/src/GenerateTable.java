import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenerateTable {
		
	public static void main(String[] args) {
		String url = "jdbc:sqlite:C:/Users/Matt/sqlite/testdataentry11.db";
		Connection connect = connect(url);
		if (connect != null ) {
			System.out.println("Successfull connection! \n");
		}
		else {
			System.out.println("Connection failed.\n");
		}
		
		
		
		ResultSet resultSet = retrieveAllRestaurants(connect);

		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter (new FileWriter ("results.html")));
			
			writer.println("<!DOCTYPE html>");
			writer.println("<html lang='en'>");
			writer.println("<head>");
			writer.println("<title>Bootstrap Example</title>");
			writer.println("<meta charset='utf-8'>");
			writer.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
			writer.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>");
			writer.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script>");
			writer.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
			writer.println("</head>");
			writer.println("<div class='container'>");
			writer.println("<h2>Basic Table</h2>");
			writer.println("<table class='table'>");
			writer.println("<tbody>");
					
			try {
				while(resultSet.next()) {
					writer.println("<tr>");
					int restaurantID = resultSet.getInt("RestaurantID");
					writer.println("<td>" + restaurantID + "</td>");
					int genreID = resultSet.getInt("GenreID");
					writer.println("<td>" + genreID + "</td>");
					String name = resultSet.getString("Name");
					writer.println("<td>" + name + "</td>");
					int zip = resultSet.getInt("Zip");
					writer.println("<td>" + zip + "</td>");
					String state = resultSet.getString("State");
					writer.println("<td>" + state + "</td>");
					String city = resultSet.getString("City");
					writer.println("<td>" + city + "</td>");
					String street = resultSet.getString("Street");
					writer.println("<td>" + street + "</td>");
					int latitude = resultSet.getInt("Latitude");
					writer.println("<td>" + latitude + "</td>");
					int longitude = resultSet.getInt("Longitude");
					writer.println("<td>" + longitude + "</td>");
					int delivery = resultSet.getInt("Delivery");
					writer.println("<td>" + delivery + "</td>");
					String phone = resultSet.getString("Phone");
					writer.println("<td>" + phone + "</td>");
					String website = resultSet.getString("Website");
					writer.println("<td>" + website + "</td>");
					String email = resultSet.getString("Email");
					writer.println("<td>" + email + "</td>");
					int closingTime = resultSet.getInt("ClosingTime");
					writer.println("<td>" + closingTime + "</td>");
					writer.println("</tr>");
				}
				
				writer.println("</tbody>");
				writer.println("</table>");
				writer.println("</div>");
				writer.println("</body>");
				writer.println("</html>");
				
				writer.flush();
				writer.close();
			}
			catch (SQLException e) {
				System.err.println("Error in writing word & value file");
				System.err.print("Program terminated.");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static ResultSet retrieveAllRestaurants(Connection connect) {
		String queryStr = "SELECT * FROM Restaurant";
		
		ResultSet resultSet = null;
		
		PreparedStatement query;
		try {
			query = connect.prepareStatement(queryStr);
			resultSet = query.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	private static Connection connect(String url) {
		Connection connect = null;
		
		try {
			connect = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connect;
		
	}

}
