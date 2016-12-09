import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class RestaurantTables extends DatabaseRunner implements ActionListener	{
	private int HDimension = 400, VDimension = 300;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();

  public RestaurantTables() {
    create();
  }

  private void create() {
    JFrame j = new JFrame("Stoner's Late Night Cravings - Restaurants");
    JPanel p = new JPanel(new GridBagLayout());
    
    
    GridBagConstraints c = new GridBagConstraints();



	

    
    ResultSet rs = super.executeQuery("SELECT count(*) as rows FROM Restaurant");
    int columns = 6;
    JLabel dummyLabel = new JLabel("empty");
    JButton dummyButton = new JButton("empty");

    c.fill = GridBagConstraints.BOTH;
	c.weightx = 1;
	c.weighty = 1;
	c.gridwidth=1;
	c.gridheight=1;
	c.insets = new Insets(2,10,2,10);
    
    rs = super.executeQuery("SELECT Latitude, Name, GenreID, Phone, Website, ClosingTime FROM Restaurant");

    try {
      int i = 0;
      int r = 0;
      while(rs.next()) {
        if(i>= columns) {
          i=0;
        }
        c.gridx = i;
        c.gridy = r;
        p.add(new JLabel(""+ rs.getInt("Latitude")), c);
        i++;
        p.add(dummyButton = new JButton(""+rs.getString("Name")), c);
        i++;
        p.add(new JLabel(""+ rs.getInt("GenreID")), c);
        i++;
        p.add(new JLabel(""+ rs.getInt("Phone")), c);
        i++;
        p.add(new JLabel(""+ rs.getInt("Website")), c);
        i++;
        p.add(new JLabel(""+ rs.getInt("ClosingTime")), c);
        i++;
        r++;
      }
    } catch (SQLException e) {
      e.printStackTrace();

    }
    
	j.add(p);

    j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	j.pack();
	j.setVisible(true);
	j.setBounds( (width-HDimension)/2, (height-VDimension)/2, HDimension, VDimension );
	p.setBackground( Color.white );

	p.repaint();

  }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}



}
