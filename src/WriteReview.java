import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class WriteReview extends DatabaseRunner implements ActionListener {
	private int HDimension = 400, VDimension = 300;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();
	private JTextField txtComments = new JTextField();
	private JSlider sliRating = new JSlider();
	private JButton btnSubmit = new JButton("Submit");
	private String NAME="";
	private int ID=0;
	private JFrame j = new JFrame("Write Review - " + NAME);;

	public WriteReview(int RestaurantID, String RestaurantName) {
		ID = RestaurantID;
		NAME = RestaurantName;
		create();
	}

	private void create() {
		JPanel p = new JPanel(new GridBagLayout());

		j.setLayout(new BorderLayout());
		j.add(p, BorderLayout.CENTER);

		GridBagConstraints c = new GridBagConstraints();
		// c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(2, 10, 2, 10);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		p.add(new JLabel("Please enter comments about your experience below."), c);
		
		c.gridy = 1;
		p.add(txtComments, c);
		
		c.gridwidth = 1;
		c.gridy = 2;
		p.add(new JLabel("Rating: " + sliRating.getValue()),c);
		c.gridx = 1;
		sliRating.setMaximum(5);
		p.add(sliRating,c);
		
		c.gridwidth = 2;
		c.gridy = 3;
		p.add(btnSubmit,c);
		btnSubmit.addActionListener(this);
		
		j.pack();
		j.setVisible(true);
		j.setBounds(100, 100, 500, 500);
		p.setBackground(Color.white);

		p.repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnSubmit)) {
			executeQuery("INSERT INTO Review VALUES (" + ID + ", " + LoggedInUsername + ",'" + txtComments.getText() + "'," + sliRating.getValue() + ")" );
		}
	}
}
