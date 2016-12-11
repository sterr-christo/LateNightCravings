import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class UserPage extends DatabaseRunner implements ActionListener {
	private String username;
	private JTextField txtStreet = new JTextField(), txtCity = new JTextField(), txtState = new JTextField(),
			txtZip = new JTextField(), txtLatitude = new JTextField(), txtLongitude = new JTextField();
	private JToggleButton tglEdit = new JToggleButton("Edit Info");
	private JTable tblReviews = new JTable();

	public UserPage(String Username) {
		username = Username;
		create();
	}

	private void create() {
		JFrame j = new JFrame("Stoner's Late Night Cravings - User Panel");
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

		ResultSet rs = super.executeQuery(
				"SELECT Username, Street, City, State, Zip, Latitude, Longitude FROM User WHERE Username = '" + username
						+ "'");
		try {
			while (rs.next()) {
				c.gridx = 0;
				c.gridy = 0;
				p.add(new JLabel(rs.getString("Username")), c);

				c.gridx = 0;
				c.gridy = 1;
				p.add(new JLabel("Address: "), c);

				c.gridx = 1;
				c.gridy = 1;
				txtStreet.setText(rs.getString("Street"));
				txtStreet.setEnabled(false);
				p.add(txtStreet, c);

				c.gridx = 0;
				c.gridy = 2;
				txtCity.setText(rs.getString("City"));
				txtCity.setEnabled(false);
				p.add(txtCity, c);

				c.gridx = 1;
				c.gridy = 2;
				txtState.setText(rs.getString("State"));
				txtState.setEnabled(false);
				p.add(txtState, c);

				c.gridx = 2;
				c.gridy = 2;
				txtZip.setText(rs.getString("Zip"));
				txtZip.setEnabled(false);
				p.add(txtZip, c);

				c.gridx = 0;
				c.gridy = 3;
				p.add(new JLabel("Latitude:"), c);

				c.gridx = 1;
				c.gridy = 3;
				txtLatitude.setText(rs.getString("Latitude"));
				txtLatitude.setEnabled(false);
				p.add(txtLatitude, c);

				c.gridx = 3;
				c.gridy = 3;
				p.add(new JLabel("Longitude:"), c);

				c.gridx = 4;
				c.gridy = 3;
				txtLongitude.setText(rs.getString("Longitude"));
				txtLongitude.setEnabled(false);
				p.add(txtLongitude, c);

				c.gridx = 4;
				c.gridy = 0;
				tglEdit.addActionListener(this);
				p.add(tglEdit, c);
				
				c.gridx=0;
				c.gridy = 4;
				c.gridwidth=4;
				populateTable();
				p.add(tblReviews, c);
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.pack();
		j.setVisible(true);
		j.setBounds(100, 100, 500, 500);
		p.setBackground(Color.white);

		p.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(tglEdit)) {
			if (!(txtStreet.isEnabled())) {
				switchEnableTextBoxes();
			} else if (txtStreet.isEnabled()) {
				super.executeQuery("UPDATE User SET (Street, City, State, Zip, Latitude, Longitude) = ('"
						+ txtStreet.getText() + "', '"
						+ txtCity.getText() + "', '"
						+ txtState.getText() + "', "
						+ txtZip.getText() + ", "
						+ txtLatitude.getText() + ", "
						+ txtLongitude.getText() + ")"
						+ " WHERE Username = '" + username + "'");
				switchEnableTextBoxes();
			}
		}

	}

	private void switchEnableTextBoxes() {
		txtStreet.setEnabled(!(txtStreet.isEnabled()));
		txtCity.setEnabled(!(txtCity.isEnabled()));
		txtState.setEnabled(!(txtState.isEnabled()));
		txtZip.setEnabled(!(txtZip.isEnabled()));
		txtLatitude.setEnabled(!(txtLatitude.isEnabled()));
		txtLongitude.setEnabled(!(txtLongitude.isEnabled()));
	}
	private void populateTable() {
		ResultSet rs = super.executeQuery("SELECT * FROM Review WHERE Username = '" + username + "'");
		try {
			while(rs.next()) {
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
