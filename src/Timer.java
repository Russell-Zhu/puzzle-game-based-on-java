
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
/**
 * Initialization the timer to count different time in different level  
 * initialize through opening a new thread 
 * implement an interface and override the run function
 * @author Xinchen Zhao
 */
public class Timer extends JLabel implements Runnable  {
	//using the type long to initialize the hour, minute and second
	private long hour;
	private long minute;
	private long second;
	//the variable isSuccessed is used to record whether the player success or not
	private boolean isSuccessed=false;
	//record the start time
	private final long start;
	//using this variable to sign if it is the time to finish the time
	private boolean flag=true;
	//open a new thread so that it is possible to  time the game while playing it
	private Thread thread;
	//record the time used in this level
	private long time;
	//using the frame,panel and label to create the warning window
	private JFrame frame;
	private JPanel panel;
	private JLabel label[]=new JLabel[2];
	MusicPlayer congratulationMusic= new MusicPlayer("music\\Congratulation.wav");//Define click music sound
	//----------------------------Constructor------------------------------------
    /**
     * Constructor. Creates an instance of Timer 
     */
	public Timer()
	{
		super("",JLabel.CENTER);
		thread=new Thread(this);
		start=System.currentTimeMillis();
		thread.start();
	}
	/**
     * @return a type long to record the time used in this level
     */
	public long getTime() {
		return time;
	}
	/**
     * using the function to record the current time and count the time in seconds in this level
     * @return a type long to record the time used in this level
     */
	public long elapsedTime() 
	{
		long now=System.currentTimeMillis();
		return ((now-start)/1000);
	}
	/**
     * using the function to calculate the corresponding hour,minute and second
     */
	public void timeCount() 
	{
			long gameTime=elapsedTime();
			hour=gameTime/60/60;
			minute=gameTime/60%60;
			second=gameTime%60;
		    setText(toString(hour,minute,second));
			setFont(new Font("Arial",Font.BOLD,20));
	}
	 /**
     * change the time whose type is long to a string
     * @param hour a variable whose type is long to record the hour in this level
     * @param minute a variable whose type is long to record the minute in this level
     * @param second a variable whose type is long to record the second in this level
     * @return a string to represent hour,minute and second
     */
	public String toString(long hour,long minute,long second) {
		 String str1=String.format("%02d", hour);
		 String str2=String.format("%02d", minute);
		 String str3=String.format("%02d", second);		 
		 return (str1+":"+str2+":"+str3);
	}
	/**
     * using the function to change the sign to success and pop a congratulation window
     */
	public void changeFlag() {
		if(gameIsPassed(isSuccessed)) {
			flag=false;
			popWindow();
		}
	}
	 /**
     * to change the state of player whether it is successful or not
     * @param isSuccessed a variable of boolean to change the state
     * @return a boolean, if true, the player is successful, else, the player is failed
     */
	public boolean gameIsPassed(boolean isSuccessed) {
		if(isSuccessed) {
			time=elapsedTime();
			this.isSuccessed=true;
			return true;
		}
		else {
			this.isSuccessed=false;
			return false;
		}
		
	}
	/**
     * to create a congratulation window to remind player
     */
	public void popWindow() {
		frame=new JFrame("Hint");
		frame.setSize(350,200);
		panel=new JPanel();
		panel.setLayout(new GridLayout(2,1));
		label[0]=new JLabel("Congratulation !",JLabel.CENTER);
		label[0].setFont(new Font("Arial",Font.BOLD,20));
		label[1]=new JLabel("Please click the quit button!",JLabel.CENTER);
		label[1].setFont(new Font("Arial",Font.BOLD,20));
		frame.add(panel);
		panel.add(label[0]);
		panel.add(label[1]);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		congratulationMusic.play();
	}
	@Override
	/**
     * to override the run function to realize the function of Timer
     */
	public void run() {
		// TODO Auto-generated method stub
		//using the function the count time and display every second
		//if there is an exception, it would be caught
		try {
			while (flag) {
				timeCount();
				changeFlag();
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException e) {
			System.out.println("Error");
		}
	}
	
}