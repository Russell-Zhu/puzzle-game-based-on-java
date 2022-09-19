

import java.awt.Font;

import javax.swing.*;
/**
 * Create a pop-up window
 * @author Shicheng Gao
 */
public class CreateStringFrame{
	private String str = "nothing";
	private JPanel strPanel;
	private JFrame strFrame;
	private JLabel strLabel;
	private int frameSizeX = 350;
	private int frameSizeY = 200;
	//set music
	MusicPlayer WaterSoundEffect = new MusicPlayer("music\\water.wav");
	/**
	 * Default constructor.
	 */
	public CreateStringFrame() {};
	
	/**
	 * Constructor with given string.
	 * @param str The give String.
	 */
	public CreateStringFrame(String str){
		this.str = str;
		strFrame = new JFrame("Warning");
		strPanel = new JPanel();
		strLabel = new JLabel(str);
		strLabel.setFont(new Font("Arial",Font.BOLD,20));
		strPanel.add(strLabel);		
		strFrame.add(strPanel);
		strFrame.setSize(frameSizeX, frameSizeY);
		strFrame.setLocationRelativeTo(null);
		//strFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		strFrame.setVisible(true);
		WaterSoundEffect.play();
	}
}
