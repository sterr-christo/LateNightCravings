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

	public RestaurantTables(int Lat, int Longi) {
			Latitude = Lat;
			Longitude = Longi;
			System.out.println("DEBUG(RestaurauntTables): Your location is " + Latitude + ", " + Longitude);
	    create();
	}
  
	private void create() {
		LinkedList<String> names = new LinkedList<String>();
		LinkedList<Integer> ids = new LinkedList<Integer>();
		Hashtable<String,Integer> idHash = new Hashtable<String, Integer>();
		
		String name, website, phone, distanceDisplay, delivers, genre;
		int rowNum, latitude, longitude, closingTime, restaurantId, userLat, userLong, delivery;
		double distance;
		
		JFrame frame = new JFrame("Stoner's Late Night Cravings - Restaurants");
		JTable table = new JTable(new DefaultTableModel(new Object[]{"Distance", "Name","Delivery", "Genre","Phone","Website","ClosingTime"},0));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ResultSet userRs = null;
		
		if(LoggedInUsername != null) {
	    userRs = super.executeQuery("SELECT Latitude, Longitude FROM User WHERE Username = '" + LoggedInUsername +"'");
		}
	    ResultSet rs = super.executeQuery("SELECT Latitude, Longitude, Delivery, Restaurant.Name AS Name, Genre.Name AS Genre, RestaurantID, Phone, "
	    		+ "Website, ClosingTime FROM Restaurant LEFT OUTER JOIN Genre WHERE Genre.genreID = Restaurant.GenreID");
	
	    try 
	    {
	    	rowNum = 0;
	    	while( rs.next() ) 
	    	{
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
				distanceDisplay = (int)distance+"."+temp;
				
				//Format delivery
				delivery = rs.getInt( "Delivery" );
				if ( delivery == 1 )
					delivers = "No";
				else
					delivers = "Yes";
				
				//Format the rest of the parameters
				name = rs.getString("Name");
				genre = rs.getString("Genre");
				restaurantId = rs.getInt("RestaurantID");
				phone = rs.getString("Phone");
				website =  rs.getString("Website");
				closingTime = rs.getInt("ClosingTime");
				
				model.addRow(new Object[]{ distanceDisplay, name, delivers, genre, phone, website, closingTime });
				
//				names.add( name );
//				ids.add( restaurantId );
				idHash.put(name, restaurantId);
				rowNum++;
	      }
	    }
	    catch (SQLException e) 
	    {
	      e.printStackTrace();
	    }
	
	    
	    //Make the table sortable
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
	    table.setRowSorter(sorter);
	    LinkedList<RowSorter.SortKey> sortKeys = new LinkedList<RowSorter.SortKey>();
	    sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
	    sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
	    sorter.setSortKeys(sortKeys);
	    
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
	    		
	    		int id = idHash.get( name );
	    		System.out.println("" + name + " " + id);
	    		
	    		if ( col == 0 )
	    			SetupMenuTable( id, name );
	    		else
	    			SetupReviewTable( id, name );
	    		
	    	}
	    });
		
	  }
	
	/*
	 * Automatically sets up the table for Reviews/ratings using the restaurant id and name
	 * This method require the WordWrapCellRenderer class
	 */
	private void SetupReviewTable( int id, String name )
	{
		String comments;
		int rating;
		
		JFrame frame = new JFrame( name );
		JTable table = new JTable(new DefaultTableModel(new Object[]{"Comments", "Rating" },0));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	    	
		ResultSet set = executeQuery("SELECT Comments, Rating FROM Review WHERE RestaurantId = " + id );
		
	    try 
	    {
	    	while( set.next() ) 
	    	{
	    		comments = set.getString( "Comments" );
	    		rating = set.getInt( "Rating" );
				model.addRow(new Object[]{ comments, rating });
	      }
	    }
	    catch (SQLException e) 
	    {
	      e.printStackTrace();
	    }
	    
	    //Setup JFrame so it's scrollable, has centered text in the rating column and text wrapping in review column
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	    table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
	    
	    table.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
	    
	    JScrollPane pane = new JScrollPane( table );
	    pane.getViewport().setBackground( Color.white );
		frame.add( pane );
		frame.pack();
		frame.setVisible(true);
		frame.setBounds( (width-HDimension)/2, (height-VDimension)/2, HDimension, VDimension );
		frame.getContentPane().setBackground( Color.white );
	
		frame.repaint();
	}
	
	private void SetupMenuTable( int id, String restaurantName )
	{
		String name;
		int price;
		
		JFrame frame = new JFrame( restaurantName );
		JTable table = new JTable(new DefaultTableModel(new Object[]{"Item Name", "Price" },0));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
    	
		ResultSet set = executeQuery("SELECT Name, Price "
									+ "FROM Serves NATURAL JOIN Items "
									+ "WHERE RestaurantId = " + id );
		
	    try 
	    {
	    	while( set.next() ) 
	    	{
	    		name = set.getString( "Name" );
	    		price = set.getInt( "Price" );
				model.addRow(new Object[]{ name, price });
	      }
	    }
	    catch (SQLException e) 
	    {
	      e.printStackTrace();
	    }
	    
	    //Setup JFrame so it's scrollable, has centered text in the rating column and text wrapping in review column
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	    table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
	    
	    table.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
	    
	    JScrollPane pane = new JScrollPane( table );
	    pane.getViewport().setBackground( Color.white );
		frame.add( pane );
		frame.pack();
		frame.setVisible(true);
		frame.setBounds( (width-HDimension)/2, (height-VDimension)/2, HDimension, VDimension );
		frame.getContentPane().setBackground( Color.white );
	
		frame.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}