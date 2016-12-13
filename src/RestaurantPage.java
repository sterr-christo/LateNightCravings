import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class RestaurantPage extends DatabaseRunner{
	private int HDimension = 750, VDimension = 400;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();
	private GridBagConstraints c = new GridBagConstraints();
	private JFrame frame;
	
	public RestaurantPage(int id, String name) {
		Setup(id,name);
	}

	private void Setup( int id, String name )
	{
		frame = new JFrame( name );
		JPanel p = new JPanel(new GridBagLayout());

		frame.setLayout(new BorderLayout());
		frame.add(p, BorderLayout.CENTER);
		
		String comments, itemName;
		int rating, price;
		
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 10, 2, 10);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		
		JTable table = new JTable(new DefaultTableModel(new Object[]{"Comments", "Rating" },0));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	    	
		ResultSet set = executeQuery("SELECT Comments, Rating FROM Review WHERE RestaurantID = " + id );
		
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
	    c.gridx=0;
	    c.gridy=7;
		p.add( pane, c );
		
		table = new JTable(new DefaultTableModel(new Object[]{"Item Name", "Price" },0));
		set = executeQuery("SELECT Name, Price "
									+ "FROM Serves NATURAL JOIN Items "
									+ "WHERE RestaurantID = " + id );
		model = (DefaultTableModel) table.getModel();
		
	    try 
	    {
	    	while( set.next() ) 
	    	{
	    		itemName = set.getString( "Name" );
	    		price = set.getInt( "Price" );
				model.addRow(new Object[]{ itemName, price });
	      }
	    }
	    catch (SQLException e) 
	    {
	      e.printStackTrace();
	    }
	    
	    //Setup JFrame so it's scrollable, has centered text in the rating column and text wrapping in review column
	    centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	    table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
	    
	    table.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
	    
	    pane = new JScrollPane( table );
	    pane.getViewport().setBackground( Color.white );
	    
	    c.gridx=0;
	    c.gridy=8;
		p.add( pane,c );
		
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		
		
		ResultSet rs = executeQuery("SELECT * FROM Restaurant WHERE RestaurantID = " + id);
		
		
		try {
			c.gridx=0;
			c.gridy=0;
			p.add(new JLabel(rs.getString("Name")),c );
			
			c.gridx=0;
			c.gridy=1;
			p.add(new JLabel(rs.getString("Phone")),c );
			
			c.gridx=0;
			c.gridy=2;
			p.add(new JLabel(rs.getString("Street")),c );
			
			c.gridx=0;
			c.gridy=3;
			p.add(new JLabel(rs.getString("City") + " " + rs.getString("State") + " "  + rs.getString("Zip")),c );
			
			c.gridx=0;
			c.gridy=4;
			p.add(new JLabel(rs.getString("Website")),c );
			
			c.gridx=0;
			c.gridy=5;
			p.add(new JLabel("Delivery: " +  rs.getInt("Delivery")),c );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		c.gridx=0;
		c.gridy=6;
		p.add(new JLabel("Menu: "),c);
		
		frame.pack();
		frame.setVisible(true);
		frame.setBounds( (width-HDimension)/2, (height-VDimension)/2, HDimension, VDimension );
		frame.getContentPane().setBackground( Color.white );
	
		frame.repaint();
	}
}
