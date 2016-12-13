import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class RestaurantTables extends DatabaseRunner implements ActionListener	{
	private int HDimension = 750, VDimension = 400;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();
	private int Latitude, Longitude;
	private Hashtable<String,Integer> idHash = new Hashtable<String, Integer>();

	public RestaurantTables(int Lat, int Longi) {
			Latitude = Lat;
			Longitude = Longi;
			System.out.println("DEBUG(RestaurauntTables): Your location is " + Latitude + ", " + Longitude);
	    create();
	}
	/* @param RestaurantID: The RestaurantID to create the review table for.
	 * @param RestaurantName: The Restaurant name to title the table for.
	 * This method creates a review table for the received parameter. Good for accessing the method outside of the class.
	 */
  
	private void create() {
		LinkedList<String> names = new LinkedList<String>();
		LinkedList<Integer> ids = new LinkedList<Integer>();
		
		String name, website, phone, distanceString, delivers, genre;
		int rowNum, colNum, latitude, longitude, closingTime, restaurantId, userLat, userLong, delivery;
		double distance, distanceDisplay;
		Object[][] rows = null;
		
		JFrame frame = new JFrame("Stoner's Late Night Cravings - Restaurants");
//		JTable table = new JTable(new DefaultTableModel(new Object[]{"Distance", "Name","Delivery", "Genre","Phone","Website","ClosingTime"},0));
//		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		ResultSet userRs = null;
		
		if(LoggedInUsername != null) {
	    userRs = super.executeQuery("SELECT Latitude, Longitude FROM User WHERE Username = '" + LoggedInUsername +"'");
		}
		
		ResultSet countSet = super.executeQuery("SELECT count(*) from Restaurant" );
		
	    ResultSet rs = super.executeQuery("SELECT Latitude, Longitude, Delivery, Restaurant.Name AS Name, Genre.Name AS Genre, RestaurantID, Phone, "
	    		+ "Website, ClosingTime FROM Restaurant LEFT OUTER JOIN Genre WHERE Genre.genreID = Restaurant.GenreID");
	
	    String[] columns = {"Distance","Name","Delivery","Genre","Phone","Website","Closing Time"};
	    
	    try 
	    {
	    	int count = countSet.getInt("count(*)");
	    	rowNum = 0;
	    	
		    rows = new Object[count][7];
		    
	    	while( rs.next() ) 
	    	{
	    		colNum = 0;
	    	    if(LoggedInUsername != null) {
	    		userLat = userRs.getInt( "Latitude" );
	    	    userLong = userRs.getInt( "Longitude" );
	    	    } else {
	    	    	userLat = Latitude;
	    	    	userLong= Longitude;
	    	    }
	    		
	    	    //Calculate and format distance
				latitude = rs.getInt("Latitude");
				longitude = rs.getInt("Latitude");
				distance = Math.sqrt( Math.pow(userLat-latitude,2) + Math.pow(userLong-longitude, 2) );
				int temp = (int)(10*distance);
				temp = temp%10;
				distanceString = (int)distance+"."+temp;
				distanceDisplay = Double.parseDouble( distanceString );
				rows[rowNum][colNum] = distanceDisplay;
				colNum++;
				
				//Format the rest of the parameters
				name = rs.getString("Name");
				rows[rowNum][colNum] = name;
				colNum++;
				
				//Format delivery
				delivery = rs.getInt( "Delivery" );
				if ( delivery == 1 )
					delivers = "No";
				else
					delivers = "Yes";
				rows[rowNum][colNum] = delivers;
				colNum++;
					
				genre = rs.getString("Genre");
				rows[rowNum][colNum] = genre;
				colNum++;
				
				restaurantId = rs.getInt("RestaurantID");
				phone = rs.getString("Phone");
				rows[rowNum][colNum] = phone;
				colNum++;
					
				website =  rs.getString("Website");
				rows[rowNum][colNum] = website;
				colNum++;
				
				closingTime = rs.getInt("ClosingTime");
				rows[rowNum][colNum] = closingTime;
				colNum++;
				
				
//				model.addRow(new Object[]{ distanceDisplay, name, delivers, genre, phone, website, closingTime });
				
				names.add( name );
				ids.add( restaurantId );
				idHash.put(name, restaurantId);
				rowNum++;
	      }
	    }
	    catch (SQLException e) 
	    {
	      e.printStackTrace();
	    }
	
	    DefaultTableModel model = new DefaultTableModel(rows, columns) {
	        public Class getColumnClass(int column) {
	          Class returnValue;
	          if ((column >= 0) && (column < getColumnCount())) {
	            returnValue = getValueAt(0, column).getClass();
	          } else {
	            returnValue = Object.class;
	          }
	          return returnValue;
	        }
	      };
	      JTable table = new JTable(model);

	      RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);

	      table.setRowSorter(sorter);
	    
	    //Make the table sortable
//	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
//	    table.setRowSorter(sorter);
//	    LinkedList<RowSorter.SortKey> sortKeys = new LinkedList<RowSorter.SortKey>();
//	    sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
//	    sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//	    sorter.setSortKeys(sortKeys);
	    
	    //Setup the JFrame
	    JScrollPane pane = new JScrollPane( table );
	    pane.getViewport().setBackground( Color.white );
		frame.add( pane );
		frame.pack();
		frame.setVisible(true);
		frame.setBounds( (width-HDimension)/2, (height-VDimension)/2, HDimension, VDimension );
		frame.getContentPane().setBackground( Color.white );
	
		frame.repaint();
		
	
	    table.addMouseListener( new java.awt.event.MouseAdapter() {
	    	public void mouseClicked( MouseEvent e ){
	    		int row = table.rowAtPoint(e.getPoint());
	    		int col = table.columnAtPoint(e.getPoint());
	    		
	    		//this is hardcoded to the name column
	    		String name = table.getValueAt(row, 1).toString();
	    		System.out.println("NAME IS: " + name);
	    		int id = idHash.get( name );
	    		System.out.println("" + name + " " + id);
	    		
	    		new RestaurantPage(id, name);
	    		
	    	}
	    });
		
	  }
	
	/*
	 * Automatically sets up the table for Reviews/ratings using the restaurant id and name
	 * This method require the WordWrapCellRenderer class
	 */
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}