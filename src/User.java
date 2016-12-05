import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class User {
	
	public static void main(String[] args) throws IOException{
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		j.setContentPane(new JPanel() {
	      
	       	BufferedImage image = ImageIO.read(new File("FAST_FOOD.jpg"));
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(image, 0, 0, 500, 500, this);
	        }
	    });
	    */
	    
		
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth=1;
		c.gridheight=1;	
		c.fill = GridBagConstraints.HORIZONTAL;
		j.pack();
		//p.setBackground( Color.white );
		
		
		//BufferedImage myImage = ImageIO.read( new File( "FAST_FOOD.jpg"));
		//JFrame myJFrame = new JFrame("Image pane");
		//j.setContentPane(new ImagePanel(myImage));
		
		
		
		j.setTitle("Stoner's Late Night Cravings - User Panel");
		j.setVisible(true);
		j.setSize(500,500);
		
		j.add(p);
		
		JTextField username = new JTextField();
		c.weightx = 1;
		c.gridx=1;
		c.gridy=0;

		p.add(username,c);
		
		JTextField password = new JTextField();
		
		c.weightx = 1;
		c.gridx=1;
		c.gridy=1;

		p.add(password,c);
		
		JButton login = new JButton("Login");

		c.gridwidth = 2;
		c.gridx=0;
		c.gridy=3;

		p.add(login,c);
		
		JButton register = new JButton("Register");

		c.gridwidth = 2;
		c.gridx=0;
		c.gridy=4;

		p.add(register,c);
		
		JLabel labUsername = new JLabel("Username:");
		
		c.gridwidth = 1;
		c.weightx = 0;
		c.gridx=0;
		c.gridy=0;

		p.add(labUsername,c);
		
		JLabel labPassword = new JLabel("Password:");
		c.gridwidth = 1;
		c.weightx = 0;
		c.gridx=0;
		c.gridy=1;

		p.add(labPassword,c);
		
		
		p.repaint();
		
		
		
	}
	public void home() {
		
	}
}


/*class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
*/
