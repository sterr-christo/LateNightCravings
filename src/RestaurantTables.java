import java.awt.*;
import java.sql.*;

import javax.swing.*;

public class RestaurantTables extends DatabaseRunner {


  public RestaurantTables() {
    create();
  }

  private void create() {
    JFrame window = new JFrame("Stoner's Late Night Cravings - Restaurants");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel p = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(2,10,2,10);

    window.add( p );


    JTable table = new JTable();
    populateTable();

    p.add(table);

  }
  private void populateTable() {
	String[] columnNames = {"Distance", "Name", "Genre", "Phone", "Website", "Closing Time"};
	
    ResultSet rs = super.executeQuery("SELECT count(*) as rows FROM Restaurant");
    int rows =0;
	try {
		rows = rs.getInt("rows");
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    System.out.println("DEBUG(populateTable): Number of rows in Restaurant: " + rows);
    int columns = 6;
    Object[][] data = new Object[rows][columns];


    rs = super.executeQuery("SELECT Latitude, Name, GenreID, Phone, Website, ClosingTime");

    try {
      int i = 0;
      int r = 0;
      while(rs.next()) {
        if(i>= columns) {
          i=0;
        }
        data[r][i] = rs.getInt("Latitude");
        i++;
        data[r][i] = rs.getString("Name");
        i++;
        data[r][i] = rs.getInt("GenreID");
        i++;
        data[r][i] = rs.getString("Phone");
        i++;
        data[r][i] = rs.getString("Website");
        i++;
        data[r][i] = rs.getInt("ClosingTime");
        i++;
      }
    } catch (SQLException e) {
      e.printStackTrace();

    }
  }

}
