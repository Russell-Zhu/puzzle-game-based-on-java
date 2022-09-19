

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * a class of rule photo
 * The specific rules are shown in the photo
 * Click quit to return to the initial interface
 * @author Fenwei Guo, Ruotong Wang
 */
public class Rule extends JFrame implements ActionListener{

	    private JButton quit;
	    private JPanel  Container;//Use JPanel to store the rule photo
		MusicPlayer clickMusicEffect = new MusicPlayer("music\\click.wav");//Define click music sound
		/**
		 * Constructor. Generate a rule frame.
		 */
	    public Rule() {
	    super();
	    Image image=new ImageIcon("img/rule.png").getImage();
	    Container = new BackgroundPanel(image);
	    Container.setPreferredSize(new Dimension(480,350));
	    Container.setLayout(null);
	    //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setLocation(30,70);//Set the position where the class appears
        
	    Font f=new Font("Ink Free",Font.BOLD,18);//Set font of button
	    quit = new JButton("QUIT");
	    quit.setBounds(210, 313,75,25);//Set the position and size of the button
	    quit.setFont(f);
	    quit.setFocusPainted(false);
	    quit.setBackground(new Color(247, 148, 29));//Set the color of background
	    quit.setBorderPainted(false);
	    quit.setMargin(new Insets(0,0,0,0)); //Set the border of the button
	    quit.addActionListener(this);
	    Container.add(quit);
	    this.setResizable(false);
	    this.setContentPane(Container);
	    this.pack();
	    this.setVisible(true);
	    }
		/**
		 * Click the quit button to return to the initial interface
		 *  */		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == quit){
				clickMusicEffect.play();//Play clickMusicEffect
				this.dispose();
		    }
		}
}

