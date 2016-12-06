import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInfo {
	private int HDimension = 300, VDimension = 300;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();
	private JFrame window;
	
	public static void main( String[] args )
	{
		UserInfo u = new UserInfo();
		u.buildWindow();
	}
	
	public UserInfo()
	{
		
	}
	public void buildWindow(){
		JFrame window = new JFrame("Stoner's Late Night Cravings - User Panel");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,10,2,10);

		window.add( p );
		
		//All components (except for the button) have the label made first, the field made second.
		
		//Name
		JLabel nameLabel = new JLabel("Username:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx=0;
		c.gridy=0;
		p.add(nameLabel,c);
		
		JTextField nameField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx=1;
		c.gridy=0;
		p.add( nameField,c);
		
		//Password
		JLabel passLabel = new JLabel("Password:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx=0;
		c.gridy=1;
		p.add(passLabel,c);
		
		JTextField passField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx=1;
		c.gridy=1;
		p.add( passField,c);
		
		//Lat
		JLabel latLabel = new JLabel("Latitude:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx=0;
		c.gridy=2;
		p.add(latLabel,c);
		
		JTextField latField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx=1;
		c.gridy=2;
		p.add( latField,c);
		
		//Long
		JLabel longLabel = new JLabel("Longitude:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx=0;
		c.gridy=3;
		p.add(longLabel,c);
		
		JTextField longField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx=1;
		c.gridy=3;
		p.add( longField,c);
		
		//State
		JLabel stateLabel = new JLabel("State:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx=0;
		c.gridy=4;
		p.add(stateLabel,c);
		
		JTextField stateField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx=1;
		c.gridy=4;
		p.add( stateField,c);
		
		//City
		JLabel cityLabel = new JLabel("City:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx=0;
		c.gridy=5;
		p.add(cityLabel,c);
		
		JTextField cityField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx=1;
		c.gridy=5;
		p.add( cityField,c);
		
		//Zip
		JLabel zipLabel = new JLabel("Zip:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx=0;
		c.gridy=6;
		p.add(zipLabel,c);
		
		JTextField zipField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx=1;
		c.gridy=6;
		p.add( zipField,c);
		
		//Street
		JLabel streetLabel = new JLabel("Street:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx=0;
		c.gridy=7;
		p.add(streetLabel,c);
		
		JTextField streetField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx=1;
		c.gridy=7;
		p.add( streetField,c);
		
		//Create button
		JButton createButton = new JButton("Create");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridwidth = 2;
		c.gridy = 8;
		p.add(createButton,c);
		
		window.setVisible( true );
		window.pack();
		window.setBounds( (width-HDimension)/2, (height-VDimension)/2, HDimension, VDimension );
		p.setBackground( Color.white );
		p.repaint();
		
	}
}
