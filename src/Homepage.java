import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Homepage implements ActionListener {
	private JTextField txtSearch = new JTextField(),
			txtUsername = new JTextField(),
			txtPassword = new JTextField();
	private JButton btnSearch = new JButton("Find food near me!"),
			btnLogin = new JButton("Login");
	
	
	public static void main(String[] args) {
		Homepage hp = new Homepage();
	}
	
	public Homepage() {
		create();
	}
	
	private void create() {
		JFrame j = new JFrame("Stoner's Late Night Cravings - Create User");
		JPanel p = new JPanel(new GridBagLayout());

		j.setLayout(new BorderLayout());
		j.add(p,BorderLayout.CENTER);

		GridBagConstraints c = new GridBagConstraints();
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth=1;
		c.gridheight=1;
		c.insets = new Insets(2,10,2,10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		
		c.gridx=0;
		c.gridy=0;
		p.add(new JLabel("Username: "),c);
		
		c.gridx=1;
		c.gridy=0;
		p.add(txtUsername,c);
		
		c.gridx=2;
		c.gridy=0;
		p.add(new JLabel("Password: "),c);
		
		c.gridx=3;
		c.gridy=0;
		p.add(txtPassword,c);
		
		c.gridx=4;
		c.gridy=0;
		p.add(btnLogin,c);
		
		c.gridx=2;
		c.gridy=2;
		p.add(txtSearch,c);
		
		c.gridx=2;
		c.gridy=3;
		p.add(btnSearch,c);
		
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.pack();
		j.setVisible(true);
		j.setBounds(0,0,500,500);
		p.setBackground( Color.white );

		p.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
