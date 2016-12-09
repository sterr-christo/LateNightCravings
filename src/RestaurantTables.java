import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class RestaurantTables extends DatabaseRunner implements ActionListener	{
	private int HDimension = 500, VDimension = 400;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();

	public RestaurantTables() {
	    create();
	}
  
	private void create() {
		LinkedList<String> names = new LinkedList<String>();
		LinkedList<Integer> ids = new LinkedList<Integer>();
		String name, website;
		int latitude, genreId, phone, closingTime, restaurantId;
		
		JFrame frame = new JFrame("Stoner's Late Night Cravings - Restaurants");
		JTable table = new JTable(new DefaultTableModel(new Object[]{"Latitude", "Name", "GenreID","Phone","Website","ClosingTime"},0));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	        
	    ResultSet rs = super.executeQuery("SELECT count(*) as rows FROM Restaurant");
	    
	    rs = super.executeQuery("SELECT Latitude, Name, GenreID, RestaurantID, Phone, Website, ClosingTime FROM Restaurant");
	
	    try 
	    {
	    	while( rs.next() ) 
	    	{
				latitude = rs.getInt("Latitude");
				name = rs.getString("Name");
				genreId = rs.getInt("GenreID");
				restaurantId = rs.getInt("RestaurantID");
				phone = rs.getInt("Phone");
				website =  rs.getString("Website");
				closingTime = rs.getInt("ClosingTime");
				
				model.addRow(new Object[]{ latitude, name, genreId, phone, website, closingTime });
				
				names.add( name );
				ids.add( restaurantId );
	      }
	    }
	    catch (SQLException e) 
	    {
	      e.printStackTrace();
	    }
	
	    JScrollPane pane = new JScrollPane( table );
	    pane.getViewport().setBackground( Color.white );
		frame.add( pane );
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setBounds( (width-HDimension)/2, (height-VDimension)/2, HDimension, VDimension );
		frame.getContentPane().setBackground( Color.white );
	
		frame.repaint();
		
	    table.addMouseListener( new java.awt.event.MouseAdapter() {
	    	public void mouseClicked( MouseEvent e ){
	    		int row = table.rowAtPoint(e.getPoint());
	    		
	    		String name = names.get( row );
	    		int id = ids.get( row );
	    		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}