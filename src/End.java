import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * The ending interface includes two buttons and a background image
 * Click the "Quit to menu" button to enter the menu interface, and click the end button to end the game
 * @author Ruotong Wang
 */
public class End  extends  JFrame   implements ActionListener{
	
		//Define the form 
		JFrame frame2=new JFrame(); 
		//Define interface buttons 
		JButton reStartButton=new  JButton("Quit to menu");
		JButton  closeButton =new JButton("End");
		//Define music 
		MusicPlayer player = new MusicPlayer("music\\end.wav");
		MusicPlayer clickMusicEffect = new MusicPlayer("music\\click.wav");//Define click music sound
		/**
		 * Constructor. Generate an end frame.
		 */
		public  End() { 	
			frame2.setSize(600,500);
			frame2.setLocation(0,0);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//What the program does when the user clicks the close button of the window 
			frame2.setLayout(null);	
			
			//Set the button color 
			Color  color=new  Color(152,251,152);
			reStartButton.setBackground(color);				
			closeButton.setBackground(color);

			//Set the button size and position 		 
			reStartButton.setBounds(350, 340, 150, 70);
			closeButton.setBounds(20,340,150,70);

			//Set the button color and font 
			reStartButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
			closeButton.setFont(new Font("Times New Roman", Font.BOLD, 20));

			//Add a button to the container 
			frame2.add(reStartButton);
			frame2.add(closeButton);
		    addPicture();
		    frame2.setVisible(true);
		    reStartButton.addActionListener(this);
		    closeButton.addActionListener(this);
		    
		    //Play the music in loop
		    try {
		    	player.setLoop(true);
				player.play();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		   /**
			* Set background image 
		    */
			 public  void  addPicture() {//The background of the interface that pops up at the end of the game 
			    ImageIcon  deadPicture  =new  ImageIcon("img/restartPicture.png");
			    JLabel  pictureLabel  =new  JLabel(deadPicture);
			    deadPicture.getImage();
				pictureLabel.setBounds(0, 0,deadPicture.getIconWidth(), deadPicture.getIconHeight());
				frame2.getLayeredPane().add(pictureLabel,new Integer(Integer.MIN_VALUE));
				JPanel  jp1=(JPanel)frame2.getContentPane();
				jp1.setOpaque(false);
			}
			 
		 //Button to join the listener event 
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(e.getSource()==reStartButton) {
						
						 closeThis();
			             new  Menu();	//If click reStartButton, open the Menu 
			             clickMusicEffect.play();//Play clickMusicEffect
			  	            }				 			
					if(e.getSource()==closeButton) {
						clickMusicEffect.play();//Play clickMusicEffect
						System.exit(0);
						
		 	        }	
				} catch (Exception exc) {
					exc.printStackTrace();
				}
					
		     }
			
			/**
			 * Close the current form 
			 */
			private void closeThis() {
				player.over();//Turn off the music
				frame2.dispose();
		}
}
