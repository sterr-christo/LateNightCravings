import java.awt.*;

import javax.swing.*;

public class CreateProfile extends DatabaseRunner{
	
	
	public CreateProfile() {
		setup();
		runthis();
	}

	private void setup() {
		JFrame j = new JFrame();
		JPanel p = new JPanel(new GridBagLayout());
		
		j.setLayout(new BorderLayout());
		j.add(p,BorderLayout.CENTER);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth=1;
		c.gridheight=1;	
		c.fill = GridBagConstraints.BOTH;
		
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setTitle("Stoner's Late Night Cravings - Create User");
		j.setSize(500,500);
		j.setLocation(500, 500);
		
		
		
		JTextField username = new JTextField("Name");
		c.gridx=1;
		c.gridy=0;
		p.add(username,c);
		
		JTextField password = new JTextField();
		c.gridx=1;
		c.gridy=1;
		p.add(password,c);
		
		JTextField latit = new JTextField();
		c.gridx=1;
		c.gridy=2;
		p.add(latit,c);
		
		JTextField longi = new JTextField();
		c.gridx=1;
		c.gridy=3;
		p.add(longi,c);
		
		JTextField street = new JTextField();
		c.gridx=1;
		c.gridy=4;
		p.add(street,c);
		
		JTextField city = new JTextField();
		c.gridx=1;
		c.gridy=5;
		p.add(city,c);
		
		JTextField state = new JTextField();
		c.gridx=1;
		c.gridy=6;
		p.add(state,c);
		
		JTextField zip = new JTextField();
		c.gridx=1;
		c.gridy=7;
		p.add(zip,c);
		
		JLabel labUsername = new JLabel("Username:");
		c.gridx=0;
		c.gridy=0;
		p.add(labUsername,c);
		
		JLabel labPassword = new JLabel("Password:");
		c.gridx=0;
		c.gridy=1;
		p.add(labPassword,c);

		j.pack();
		j.setVisible(true);

		p.repaint();
	}
	
	private void runthis() {
		//super.executeQuery("INSERT INTO Items VALUES (1,'cats',2.2,'a tasty cat')");
	}
}

