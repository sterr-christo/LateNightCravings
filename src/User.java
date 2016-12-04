import java.awt.*;

import javax.swing.*;

public class User {
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth=2;
		c.gridheight=5;
		c.insets = new Insets(0,0,0,0);
		
		
		j.setTitle("Stoner's Late Night Cravings - User Panel");
		j.setVisible(true);
		j.setSize(500,500);
		
		j.add(p);
		
		JTextField username = new JTextField();
		username.setBounds(40, 15, (int)(j.getWidth()*.80), 14);
		c.gridx=1;
		c.gridy=0;
		p.add(username,c);
		
		JTextField password = new JTextField();
		password.setBounds(40, 35, (int)(j.getWidth()*.80), 14);
		c.gridx=1;
		c.gridy=1;
		p.add(password,c);
		
		JButton login = new JButton();
		login.setBounds(60 ,51, (int)(j.getWidth()*.5), 16);
		c.gridx=1;
		c.gridy=3;
		p.add(login,c);
		
		JButton register = new JButton();
		register.setBounds(20 ,70, (int)(j.getWidth()*.8), 16);
		c.gridx=1;
		c.gridy=4;
		p.add(register,c);
		
		JLabel labUsername = new JLabel();
		labUsername.setText("User:");
		labUsername.setBounds(5, 15, labUsername.getWidth(), labUsername.getHeight());
		c.gridx=0;
		c.gridy=0;
		p.add(labUsername,c);
		
		JLabel labPassword = new JLabel();
		labUsername.setText("Pass:");
		labUsername.setBounds(5, 15, labUsername.getWidth(), labUsername.getHeight());
		c.gridx=0;
		c.gridy=1;
		p.add(labPassword,c);
		
		p.repaint();
		
		
		
	}
	public void home() {
		
	}
}
