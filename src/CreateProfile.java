import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CreateProfile extends DatabaseRunner implements ActionListener{
	private int HDimension = 400, VDimension = 300;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();
	private JTextField username = new JTextField(),
			password = new JTextField(),
			latit = new JTextField(),
			longi = new JTextField(),
			street = new JTextField(),
			city = new JTextField(),
			state = new JTextField(),
			zip = new JTextField();
	private JButton btnCreate = new JButton("Create");

	public CreateProfile() {
		setup();
	}

	private void setup() {
		JFrame j = new JFrame("Stoner's Late Night Cravings - Create User");
		JPanel p = new JPanel(new GridBagLayout());

		j.setLayout(new BorderLayout());
		j.add(p,BorderLayout.CENTER);

		GridBagConstraints c = new GridBagConstraints();
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth=1;
		c.gridheight=1;
		c.insets = new Insets(2,10,2,10);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;


		c.gridx=1;
		c.gridy=0;
		p.add(username,c);

		c.gridx=1;
		c.gridy=1;
		p.add(password,c);

		c.gridx=1;
		c.gridy=2;
		p.add(latit,c);

		c.gridx=1;
		c.gridy=3;
		p.add(longi,c);

		c.gridx=1;
		c.gridy=4;
		p.add(street,c);

		c.gridx=1;
		c.gridy=5;
		p.add(city,c);

		c.gridx=1;
		c.gridy=6;
		p.add(state,c);

		c.gridx=1;
		c.gridy=7;
		p.add(zip,c);


		c.weightx=0;
		c.weighty=0;
		JLabel labUsername = new JLabel("Username:");
		c.gridx=0;
		c.gridy=0;
		p.add(labUsername,c);

		JLabel labPassword = new JLabel("Password:");
		c.gridx=0;
		c.gridy=1;
		p.add(labPassword,c);

		JLabel labLatit = new JLabel("Latitude:");
		c.gridx=0;
		c.gridy=2;
		p.add(labLatit,c);


		JLabel labLongi = new JLabel("Longitude:");
		c.gridx=0;
		c.gridy=3;
		p.add(labLongi,c);

		JLabel labStreet = new JLabel("Street:");
		c.gridx=0;
		c.gridy=4;
		p.add(labStreet,c);

		JLabel labCity = new JLabel("City:");
		c.gridx=0;
		c.gridy=5;
		p.add(labCity,c);

		JLabel labState = new JLabel("State:");
		c.gridx=0;
		c.gridy=6;
		p.add(labState,c);


		JLabel labZip = new JLabel("ZIP Code:");
		c.gridx=0;
		c.gridy=7;
		p.add(labZip,c);

		//Add button
		c.gridx = 0;
		c.weightx = 1;
		c.gridwidth = 2;
		c.gridy = 9;
		p.add(btnCreate,c);
		btnCreate.addActionListener(this);


		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.pack();
		j.setVisible(true);
		j.setBounds( (width-HDimension)/2, (height-VDimension)/2, HDimension, VDimension );
		p.setBackground( Color.white );

		p.repaint();
	}

	public void CreateAccount() {
		/*	This is how you would create the last ID in a table
		 *
		 * ResultSet rsCount = super.executeQuery("SELECT Count(*) AS n FROM User");
		int intCount =0;
		try {
			intCount = rsCount.getInt("n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		super.executeQuery("INSERT INTO User VALUES (" + "'" + username.getText()  + "'" + ", " + "'" +  password.getText() + "'" + ", " + zip.getText() + ", " + "'" + state.getText() + "'" + ", " + "'" + city.getText() + "'" + ", "  + "'" + street.getText() + "'" + ", "  + latit.getText() + ", "  + longi.getText() + ")");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCreate)) {
			CreateAccount();
		}
	}
}
