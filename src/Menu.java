import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The class of the game's initial interface
 * Click the corresponding button to jump to the corresponding level or rule
 * @author Fenwei Guo, Ruotong Wang
 */
public class Menu extends JFrame implements ActionListener{
    private JButton level1;
    private JButton level2;
    private JButton level3;
    private JButton rule;
    private JPanel  Container;
    MusicPlayer player = new MusicPlayer("music\\menu.wav"); //Define menu music 
	MusicPlayer clickMusicEffect = new MusicPlayer("music\\click.wav");//Define click music sound
    //----------------------------Constructor------------------------------------
    public Menu() {
    super();
    setBackground(new Color(108,207,247));
    Image image=new ImageIcon("img/Menu.png").getImage();
    Container = new BackgroundPanel(image);
    Container.setPreferredSize(new Dimension(536,460));//Set size of the JPanel
    Container.setLayout(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    level1 = new JButton("Level 3");
    level1.setBounds(220,190,90,30);//Set position and size of button
    Font f=new Font("Ink Free",Font.BOLD,18);//Set font of button
    level1.setFont(f);
    level1.setFocusPainted(false);
    level1.setBackground(new Color(247, 148, 29));
    level1.setBorderPainted(false);//Set the border of the button to be invisible
    level1.setMargin(new Insets(0,0,0,0));//Set the border of the button
    level1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    level2 = new JButton("Level 13");
    level2.setBounds(220, 250,90,30);
    level2.setFont(f);
    level2.setFocusPainted(false);
    level2.setBackground(new Color(247, 148, 29));
    level2.setBorderPainted(false);
    level2.setMargin(new Insets(0,0,0,0));
    level2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    level3 = new JButton("Level 23");
    level3.setBounds(220, 310,90,30);
    level3.setFont(f);
    level3.setFocusPainted(false);
    level3.setBackground(new Color(247, 148, 29));
    level3.setBorderPainted(false);
    level3.setMargin(new Insets(0,0,0,0));
    level3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    rule = new JButton("Rule");
    rule.setBounds(220, 370,90,30);
    rule.setFont(f);
    rule.setFocusPainted(false);
    rule.setBackground(new Color(247, 148, 29));
    rule.setBorderPainted(false);
    rule.setMargin(new Insets(0,0,0,0));
    rule.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    level1.addActionListener(this);
    level2.addActionListener(this);
    level3.addActionListener(this);
    rule.addActionListener(this);
    
    Container.add(level1);
    Container.add(level2);
    Container.add(level3);
    Container.add(rule);
    
    this.setResizable(false);
    this.setContentPane(Container);
    this.pack();
    this.setVisible(true);
    
    //play menu music on loop
    try {
    	player.setLoop(true);
		player.play();
	} catch (Exception e) {
		e.printStackTrace();
		}
    }
    
    /**
     * Click the corresponding button to jump to the corresponding level or rule
     */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == level1){
			this.dispose();
			CreateMap.currentlevel=1;
			new CreateMap(1);
			this.setVisible(false);
			clickMusicEffect.play();//Play clickMusicEffect
			}
		else if(e.getSource() == level2) {
			this.dispose();
			CreateMap.currentlevel=2;
			new CreateMap(2);
			this.setVisible(false);
			clickMusicEffect.play();//Play clickMusicEffect
			}
		else if(e.getSource() == level3) {
			this.dispose();
			CreateMap.currentlevel=3;
			new CreateMap(3);
			this.setVisible(false);
			clickMusicEffect.play();//Play clickMusicEffect
			}
		else if(e.getSource() == rule) {
			new Rule();
			clickMusicEffect.play();//Play clickMusicEffect
			}
		}
	
	/**
	   * Turn off menu music when closing the form
	   */
	@Override
	public void dispose() {
		player.over();//menu music off
	}
}