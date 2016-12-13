import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class UserLogin extends DatabaseRunner implements ActionListener, WindowListener {
	private JButton btnLogin = new JButton("Login"), btnRegister = new JButton("Register");
	private JTextField txtUsername = new JTextField();
	private JPasswordField pwdPassword = new JPasswordField();
	private JFrame j = new JFrame("Stoner's Late Night Cravings - Login");
	
	public UserLogin() {
		create();
	}

	private void create() {
		
		JPanel p = new JPanel(new GridBagLayout());

		j.setLayout(new BorderLayout());
		j.add(p, BorderLayout.CENTER);

		GridBagConstraints c = new GridBagConstraints();
		// c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 10, 2, 10);
		c.fill = GridBagConstraints.BOTH;
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
		p.add(pwdPassword, c);

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
		btnRegister.setForeground(new Color(0, 0, 238));
		btnRegister.addActionListener(this);

		j.addWindowListener(this);
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
		if (e.getSource().equals(btnRegister)) {
			String password = new String(pwdPassword.getPassword());
			new CreateProfile(txtUsername.getText(), password);
		}
	}

	private void attemptLogin() {
		System.out.println("DEBUG(attemptLogin): attempting login");
		String password = new String(pwdPassword.getPassword());
		ResultSet rs = super.executeQuery("SELECT * FROM User WHERE Username = '" + txtUsername.getText()
				+ "' AND Password = '" + password + "'");

		try {
			if (!(rs.isClosed())) {
				if (rs.getString("Username").contains(txtUsername.getText())) {
					System.out.println("DEBUG(attemptLogin): Login credentials OK");
					LoggedInUsername = txtUsername.getText();
					System.out.println("DEBUG(attemptLogin); Assigned user as " + LoggedInUsername);
					up = new UserPage(txtUsername.getText());
					super.Homepage1.setUsername(txtUsername.getText());
					j.dispose();
				} else {
					

				}
			} else {
			JOptionPane.showMessageDialog(null, "Sorry, that was not a match.","Error",JOptionPane.ERROR_MESSAGE);
			System.out.println("DEBUG(attemptLogin): ResultSet is Null");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		Homepage1.setVisible(false);
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		Homepage1.setVisible(true);
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
