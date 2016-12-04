import java.awt.*;

import javax.swing.*;

public class User {
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth=1;
		c.gridheight=1;	
		c.fill = GridBagConstraints.CENTER	;
		
		j.setTitle("Stoner's Late Night Cravings - User Panel");
		j.setVisible(true);
		j.setSize(500,500);
		
		j.add(p);
		
		JTextField username = new JTextField();

		c.gridx=1;
		c.gridy=0;

		p.add(username,c);
		
		JTextField password = new JTextField();

		c.gridx=1;
		c.gridy=1;

		p.add(password,c);
		
		JButton login = new JButton("Login");

		c.gridx=1;
		c.gridy=3;

		p.add(login,c);
		
		JButton register = new JButton("Register");

		c.gridx=1;
		c.gridy=4;

		p.add(register,c);
		
		JLabel labUsername = new JLabel("User:");

		c.gridx=0;
		c.gridy=0;

		p.add(labUsername,c);
		
		JLabel labPassword = new JLabel("Pass:");
		c.gridx=0;
		c.gridy=1;

		p.add(labPassword,c);
		
		
		p.repaint();
		
		
		
	}
	public void home() {
		
	}
}
