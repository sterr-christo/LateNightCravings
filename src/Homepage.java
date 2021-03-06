import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Homepage extends DatabaseRunner implements ActionListener, KeyListener {
	private JFrame j = new JFrame("Stoner's Late Night Cravings - Feed your wild side");
	private int HDimension = 500, VDimension = 400;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();
	private JButton btnSearch = new JButton("Find food near me!"), btnLogin = new JButton("Login / Register"),
			btnLocation = new JButton("Get Location");
	private JLabel labUser = new JLabel();
	private JComboBox cboSearch = new JComboBox();
	private JTextField txtSearch = (JTextField) cboSearch.getEditor().getEditorComponent();
	private final Vector<String> v = new Vector<String>();
	private boolean hide_flag = false;


	public Homepage() {
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
		c.weightx = 0;
		c.weighty = 1;

		c.anchor = GridBagConstraints.WEST;
		c.gridx = 2;
		c.gridy = 0;
		p.add(labUser, c);
		labUser.setToolTipText("Your currently logged in username.");

		c.gridx = 3;
		c.gridy = 0;
		
		p.add(btnLogin, c);
		btnLogin.addActionListener(this);
		btnLogin.setToolTipText("Click here to open a login window.");

		c.gridx = 2;
		c.gridy = 1;
		p.add(new JLabel("Enter the name of a restaurant", SwingConstants.CENTER), c);
		
		c.gridx = 2;
		c.gridy = 2;
		p.add(cboSearch, c);
		cboSearch.setEditable(true);
		txtSearch.addKeyListener(this);
		cboSearch.setToolTipText("Start typing the name of a restauraunt for one to be suggested.");

		c.gridx = 3;
		c.gridy = 2;
		p.add(btnLocation, c);
		btnLocation.addActionListener(this);
		btnLocation.setToolTipText("Click here to get your location and find restaurants near you.");

		c.gridx = 2;
		c.gridy = 3;
		p.add(btnSearch, c);
		btnSearch.setEnabled(false);
		btnSearch.setToolTipText("Please login to find restaurants near you.");


		populateAL();
		setModel(new DefaultComboBoxModel(v), "");

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.pack();
		j.setVisible(true);
		j.setBounds((width - HDimension) / 2, (height - VDimension) / 2, HDimension, VDimension);
		p.setBackground(Color.white);

		p.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnSearch)) {
			System.out.println("Clicking Button");
			int lat = 0;
			int longi = 0;
			
			try {
				ResultSet userRs = super.executeQuery("SELECT Latitude, Longitude FROM User WHERE Username = '" + LoggedInUsername + "'");
				lat = userRs.getInt("Latitude");
				longi = userRs.getInt("Longitude");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new RestaurantTables(lat, longi);
		}
		if (e.getSource().equals(btnLogin)) {
			System.out.println(LoggedInUsername);
			new UserLogin();
			
		}
		if (e.getSource().equals(btnLocation)) {
			int lat = new Random().nextInt(60);
			int longi = new Random().nextInt(95);
			new RestaurantTables(lat, longi);
		}

	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		String text = txtSearch.getText();
		if (text.length() == 0) {
			cboSearch.hidePopup();
			setModel(new DefaultComboBoxModel(v), "");
		} else {
			DefaultComboBoxModel m = getSuggestedModel(v, text);
			if (m.getSize() == 0 || hide_flag) {
				cboSearch.hidePopup();
				hide_flag = false;
			} else {
				setModel(m, text);
				cboSearch.showPopup();
			}
		}
	}

	private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
		DefaultComboBoxModel m = new DefaultComboBoxModel();
		for (String s : list) {
			if (s.startsWith(text))
				m.addElement(s);
		}
		return m;
	}

	private void setModel(DefaultComboBoxModel mdl, String str) {
		cboSearch.setModel(mdl);
		cboSearch.setSelectedIndex(-1);
		txtSearch.setText(str);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String text = txtSearch.getText();
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_ESCAPE) {
			hide_flag = true;
		} else if (code == KeyEvent.VK_RIGHT) {
			for (int i = 0; i < v.size(); i++) {
				String str = v.elementAt(i);
				if (str.startsWith(text)) {
					cboSearch.setSelectedIndex(-1);
					txtSearch.setText(str);
					return;
				}
			}
		} else if (code == KeyEvent.VK_ENTER) {
			int ID=0;
			String str = txtSearch.getText();
			ResultSet set = executeQuery("SELECT RestaurantID"
					+ 					" FROM Restaurant "
					+					" WHERE Restaurant.Name = '" +  str + "'");
			try {
				ID = set.getInt("RestaurantID");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(str);
			new RestaurantPage(ID,str);
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void populateAL() {
		ArrayList<String> keywords = new ArrayList<>(),
				type = new ArrayList();
		ResultSet Index = super.executeQuery("SELECT Name FROM Restaurant");
		try {
			while (Index.next()) {
				keywords.add(Index.getString("Name"));
				type.add("Restaurant");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < keywords.size(); i++) {
			v.addElement(keywords.get(i));
		}
	}
	public void setLabel(String s) {
		labUser.setText(s);
		btnLogin.setText("Logout");
		}
	public void setVisible(boolean b) {
			j.setVisible(b);
	}

	public void setButton()
	{
		btnSearch.setEnabled( true );
		btnSearch.setToolTipText("Click here to get restaurants near you! (According to your User Profile)");
		btnSearch.addActionListener( this );
	}
}
