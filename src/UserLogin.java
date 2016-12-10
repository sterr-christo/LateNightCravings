import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class UserLogin extends DatabaseRunner implements ActionListener {
	private JButton btnLogin = new JButton("Login"),
			btnRegister = new JButton("Register");
	private JTextField txtUsername = new JTextField(), txtPassword = new JTextField();

	public UserLogin() {
		create();
	}

	private void create() {
		JFrame j = new JFrame("Stoner's Late Night Cravings - Login");
		JPanel p = new JPanel(new GridBagLayout());

		j.setLayout(new BorderLayout());
		j.add(p, BorderLayout.CENTER);

		GridBagConstraints c = new GridBagConstraints();
		// c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 10, 2, 10);
		c.fill =  GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		p.add(new JLabel("Username"), c);

		c.gridx = 0;
		c.gridy = 2;
		p.add(new JLabel("Password"), c);

		c.gridx = 0;
		c.gridy = 1;
		p.add(txtUsername, c);

		c.gridx = 0;
		c.gridy = 3;
		p.add(txtPassword, c);

		c.gridx = 0;
		c.gridy = 4;
		p.add(btnLogin, c);
		btnLogin.addActionListener(this);
		
		c.gridx = 0;
		c.gridy = 5;
		p.add(btnRegister, c);
		btnRegister.setOpaque(false);
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setBorderPainted(false);
		btnRegister.setForeground(new Color(0,0,238));
		btnRegister.addActionListener(this);

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.pack();
		j.setVisible(true);
		j.setBounds(100, 100, 500, 500);
		p.setBackground(Color.white);

		p.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnLogin)) {
			attemptLogin();
		}
		if(e.getSource().equals(btnRegister)) {
			new CreateProfile();
		}
	}

	private boolean attemptLogin() {
		System.out.println("DEBUG(attemptLogin): attempting login");
		ResultSet rs = super.executeQuery("SELECT * FROM User WHERE Username = '" + txtUsername.getText()
				+ "' AND Password = '" + txtPassword.getText() + "'");

		try {
			if (!(rs.isClosed())) {
				if (rs.getString("Username").contains(txtUsername.getText())) {
					System.out.println("DEBUG(attemptLogin): Login credentials OK");
					return true;
				} else {
					System.out.println("DEBUG(attemptLogin): ResultSet is Null");
					return false;

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
