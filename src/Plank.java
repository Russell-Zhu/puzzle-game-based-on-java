
import java.awt.*;
import javax.swing.*;

/**
 * Models a plank.
 * This class represents a JButton object of a plank. When combined with the Create map class,
 * instances of the Plank class can be displayed on the window.
 * @author Shicheng Gao
 */
public class Plank extends JButton{
	
	//data definition
	private int plankX = -1;
	private int plankY = -1; // x,y are the position of the plank
	private int length = 0; // the length of the whole plank
	private boolean isHorizontal = true; // true: horizontal; false: vertical
	private boolean visible = false; // true: exist; false:not exist
	private int maxNumOfAdjacentPlank = 4;// max number of adjacent plank (
	private Plank[] adjacentPlank = new Plank[maxNumOfAdjacentPlank];// other plank in the whole plank
	private int numOfAdjacentPlank = 0;// the number of AdjacentPlank
	private int maxNumOfAdjacentStump = 2;
	private Stump[] adjacentStump = new Stump[maxNumOfAdjacentStump]; // stumps on the two ends
	private int numOfAdjacentStump = 0; // the number of adjacentStump
	private ImageIcon imageWater = new ImageIcon("img/null.png"); // the path of the picture for the water image
	private ImageIcon imageHorizontal = new ImageIcon("img/horizontal_plank.png"); //the path of the picture for the horizontal plank
	private ImageIcon imageVertical = new ImageIcon("img/vertical_plank.png"); //the path of the picture for the vertical plank
	// for complex plank
	private boolean isComplex = false;
	private int horizontalLength = 0;
	private int verticalLength = 0;
	private int numOfHorizontalAdjacentPlank = 0;
	private int numOfVerticalAdjacentPlank = 0;
	private int numOfHorizontalAdjacentStump = 0;
	private int numOfVerticalAdjacentStump = 0;
	private Stump[] horizontalAdjacentStump = new Stump[maxNumOfAdjacentStump];
	private Stump[] verticalAdjacentStump = new Stump[maxNumOfAdjacentStump];
	private Plank[] horizontalAdjacentPlank = new Plank[maxNumOfAdjacentPlank];
	private Plank[] verticalAdjacentPlank = new Plank[maxNumOfAdjacentPlank];
	private String warningString;
	
	/**
	 * Constructor. Create an instance of plank with default parameters
	 */
	public Plank() {};
	/**
	 * Constructor. Create an instance of plank with the given parameters
	 * @param x the x position of the plank in the map
	 * @param y the y position of the plank in the map
	 * @param length the length of the whole plank
	 * @param visible distinguish whether the plank is visible, which means there is a plank or not
	 * @param isHorizontal distinguish the condition of the plank
	 */
	public Plank(int x, int y, int length, boolean visible, boolean isHorizontal) {
		super();
		this.plankX = x;
		this.plankY = y;
		this.length = length;
		this.visible = visible;
		this.isHorizontal = isHorizontal;
		if(visible)
		{
			if(isHorizontal)
			{
				imageHorizontal.setImage(imageHorizontal.getImage().getScaledInstance(100, 30, Image.SCALE_DEFAULT));
				setIcon(imageHorizontal);
			}
			else
			{
				imageVertical.setImage(imageVertical.getImage().getScaledInstance(30, 100, Image.SCALE_DEFAULT));
				setIcon(imageVertical);
			}
		}
		else
		{
			setIcon(imageWater);
		}
	}
	/**
	 * 
	 * @param x the x position of the plank in the map
	 * @param y the y position of the plank in the map
	 * @param horizontalLength the length of the whole plank for the horizontal one
	 * @param verticalLength the length of the whole plank for the vertical one
	 * @param isHorizontal distinguish the condition of the plank
	 * @param visible distinguish whether the plank is visible, which means there is a plank or not
	 * @param isComplex distinguish whether the plank can be used in two planks
	 */
	public Plank(int x, int y, int horizontalLength, int verticalLength, boolean isHorizontal, boolean visible, boolean isComplex) {
		super();
		this.plankX = x;
		this.plankY = y;
		this.horizontalLength = horizontalLength;
		this.verticalLength = verticalLength;
		this.visible = visible;
		this.isHorizontal = isHorizontal;
		if(visible)
		{
			if(isHorizontal) {
				imageHorizontal.setImage(imageHorizontal.getImage().getScaledInstance(100, 30, Image.SCALE_DEFAULT));
				setIcon(imageHorizontal);
			}
			else {
				imageVertical.setImage(imageHorizontal.getImage().getScaledInstance(30, 100, Image.SCALE_DEFAULT));
				setIcon(imageVertical);
			}
		}
		else
			setIcon(imageWater);
		this.isComplex = isComplex;
	}
	
	//---------------------------------------methods-----------------
	/**
	 * Obtain the x-coordinate of the plank on the map.
	 * @return the x-coordinate of the plank on the map
	 */
	public int getPlankX() {
		return plankX;
	}
	/**
	 * Obtain the y-coordinate of the plank on the map.
	 * @return the y-coordinate of the plank on the map
	 */
	public int getPlankY() {
		return plankY;
	}
	/**
	 * Obtain the plank is visible or not
	 * @return the plank is visible or not
	 */
	public boolean getVisible() {
		return visible;
	}
	/**
	 * Obtain the plank is complex or not
	 * @return the plank is complex or not
	 */
	public boolean getIsComplex() {
		return isComplex;
	}
	/**
	 * Obtain the plank is horizontal or vertical
	 * @return the plank is horizontal or not
	 */
	public boolean getIsHorizontal() {
		return isHorizontal;
	}
	/**
	 * Obtain the whole array of adjacentStump 
	 * @return the whole array of adjacentStump
	 */
	public Stump[] getAdjacentStump() {
		return adjacentStump;
	}
	/**
	 * Obtain the x-th element of adjacentStump
	 * @param x the index of the adjacentStump want to get
	 * @return the x-th element of adjacentStump
	 */
	public Stump getAdjacentStump(int x) {
		if(x > numOfAdjacentStump)
			return null;
		return adjacentStump[x];
	}
	/**
	 * Obtain the whole array of adjacentStump
	 * @return the whole array of adjacentStump
	 */
	public Plank[] getAdjacentPlank() {
		return adjacentPlank;
	}
	/**
	 * Obtain the x-th element of adjacentStump
	 * @param x the index of the adjacentStump want to get
	 * @return
	 */
	public Plank getAdjacentPlank(int x) {
		if(x > numOfAdjacentPlank)
			return null;
		return adjacentPlank[x];
	}
	/**
	 * Obtain the number of the elements in the variable horizontalAdjacentPlank
	 * @return the number of the elements in the variable horizontalAdjacentPlank
	 */
	public int getNumOfHorizontalAdjacentPlank() {
		return numOfHorizontalAdjacentPlank;
	}
	/**
	 * Obtain the number of the elements in the variable verticalAdjacentPlank
	 * @return the number of the elements in the variable verticalAdjacentPlank
	 */
	public int getNumOfVerticalAdjacentPlank() {
		return numOfVerticalAdjacentPlank;
	}
	/**
	 * Obtain the number of the elements in the variable horizontalAdjacentStump
	 * @return the number of the elements in the variable horizontalAdjacentStump
	 */
	public int getNumOfHorizontalAdjacentStump() {
		return numOfHorizontalAdjacentStump;
	}
	/**
	 * Obtain the number of the elements in the variable verticalAdjacentStump
	 * @return the number of the elements in the variable verticalAdjacentStump
	 */
	public int getNumOfVerticalAdjacentStump() {
		return numOfVerticalAdjacentStump;
	}
	/**
	 * Obtain the whole array of the variable horizontalAdjacentPlank
	 * @return the whole array of the variable horizontalAdjacentPlank
	 */
	public Plank[] getHorizontalAdjacentPlank() {
		return horizontalAdjacentPlank;
	}
	/**
	 * Obtain the x-th element of the horizontalAdjacentPlank
	 * @param x the index of the horizongtalAdjacentPlank
	 * @return the x-th element of the horizontalAdjacentPlank
	 */
	public Plank getHorizontalAdjacentPlank(int x) {
		if(x > numOfHorizontalAdjacentPlank)
			return null;
		return horizontalAdjacentPlank[x];
	}
	/**
	 * Obtain the whole array of the variable verticalAdjacentPlank
	 * @return the whole array of the variable verticalAdjacentPlank
	 */
	public Plank[] getVerticalAdjacentPlank() {
		return verticalAdjacentPlank;
	}
	/**
	 * Obtain the x-th element of the verticalAdjacentPlank
	 * @param x the index of the verticalAdjacentPlank
	 * @return the x-th element of the verticalAdjacentPlank
	 */
	public Plank getVerticalAdjacentPlank(int x) {
		if(x > numOfVerticalAdjacentPlank)
			return null;
		return verticalAdjacentPlank[x];
	}
	/**
	 * Obtain the whole array of the variable horizontalAdjacentStump
	 * @return the whole array of the variable horizontalAdjacentStump
	 */
	public Stump[] getHorizontalAdjacentStump() {
		return  horizontalAdjacentStump;
	}
	/**
	 * Obtain the x-th element of the horizontalAdjacentStump
	 * @param x the index of the horizontalAdjacentStump
	 * @return the x-th element of the horizontalAdjacentStump
	 */
	public Stump getHorizontalAdjacentStump(int x) {
		if(x > numOfHorizontalAdjacentStump)
			return null;
		return  horizontalAdjacentStump[x];
	}
	/**
	 * Obtain the whole array of the variable verticalAdjacentStump
	 * @return the whole array of the variable verticalAdjacentStump
	 */
	public Stump[] getVerticalAdjacentStump() {
		return  verticalAdjacentStump;
	}
	/**
	 * Obtain the x-th element of the variable verticalAdjacentStump
	 * @param x the index of the variable verticalAdjacentStump
	 * @return the x-th element of the variable verticalAdjacentStump
	 */
	public Stump getVerticalAdjacentStump(int x) {
		if(x > numOfVerticalAdjacentStump)
			return null;
		return  verticalAdjacentStump[x];
	}
	//set method for the variables
	/**
	 * Set the variable visible
	 * @param visible the variable visible
	 */
	public void setVariableVisible(boolean visible) {
		this.visible = visible;
		if(visible)
		{
			if(this.isHorizontal) {
				imageHorizontal.setImage(imageHorizontal.getImage().getScaledInstance(100, 30, Image.SCALE_DEFAULT));
				setIcon(imageHorizontal);
			}
			else {
				imageVertical.setImage(imageVertical.getImage().getScaledInstance(30, 100, Image.SCALE_DEFAULT));
				setIcon(imageVertical);
			}
		}
		else
		{
			setIcon(imageWater);
		}
		
	}
	/**
	 * Set the variable isHorizontal
	 * @param isHorizontal the plank is horizontal or not
	 */
	public void setIsHorizontal(boolean isHorizontal) {
		this.isHorizontal = isHorizontal;
	}
	/**
	 * Set a new window
	 * @param i the condition of warning string;
	 * 1: player is not near the plank
	 * 2: the length of currentPlank does not fit this plank
	 * 3: User wants to put the plank but there has been a plank
	 * 4: User wants to pick this plank but he/she has carried a plank
	 * 5: User wants to pick this plank but there is no plank
	 * 6: User wants to put a plank but he/she does not carry a plank
	 */
	public void setWarningString(int i) {
		switch(i)
		{
		case 1:
			warningString = "Player should be near the plank";
			break;
		case 2:
			warningString = "The length does not fit ";
			break;
		case 3:
			warningString = "There has been a plank";
			break;
		case 4:
			warningString = "You have carried a plank";
			break;
		case 5:
			warningString = "There is no plank";
			break;
		case 6:
			warningString = "You do not carry a plank";
			break;
		default:
			warningString = "nothing";
			break;
		}
	}
	
	//set image paths
	/**
	 * Set the path of the water image
	 * @param path the path of the water image
	 */
	public void setWaterImagePath(String path) {
		imageWater = new ImageIcon(path);
	}
	/**
	 * Set the path of the image of the horizontal plank
	 * @param path the path of the image of the horizontal plank
	 */
	public void setHorizontalPath(String path) {
		imageHorizontal = new ImageIcon(path);
	}
	/**
	 * Set the path of the image of the vertical plank
	 * @param path the path of the image of the vertical plank
	 */
	public void setVerticalPath(String path) {
		imageVertical = new ImageIcon(path);
		
	}
	
	// ------------------------------initialization methods----
	
	//to be used after create a plank to add the adjacentPlank
	/**
	 * Add an element into the variable adjacentPlank
	 * @param inputPlank the plank which is added to the variable adjacentPlank
	 * @return whether the plank is added correctly or not
	 */
	public boolean addAdjacentPlank(Plank inputPlank) {
		if(numOfAdjacentPlank == maxNumOfAdjacentPlank)
			return false;
		adjacentPlank[numOfAdjacentPlank++] = inputPlank;
		return true;
	}
		
	// add two adjacent plank
	/**
	 * Add two elements into the variable adjacentPlank
	 * @param inputPlank1 the first plank which is added to the variable adjacentPlank
	 * @param inputPlank2 the second plank which is added to the variable adjacentPlank
	 * @return whether the planks are added correctly or not
	 */
	public boolean addAdjacentPlank(Plank inputPlank1, Plank inputPlank2) {
		if(numOfAdjacentPlank >= maxNumOfAdjacentPlank - 1)
			return false;
		adjacentPlank[numOfAdjacentPlank++] = inputPlank1;
		adjacentPlank[numOfAdjacentPlank++] = inputPlank2;
		return true;
	}
	// add three adjacent plank
	/**
	 * Add three elements into the variable adjacentPlank
	 * @param inputPlank1 the first plank which is added to the variable adjacentPlank
	 * @param inputPlank2 the second plank which is added to the variable adjacentPlank
	 * @param inputPlank3 the third plank which is added to the variable adjacentPlank
	 * @return whether the planks are added correctly or not
	 */
	public boolean addAdjacentPlank(Plank inputPlank1, Plank inputPlank2, Plank inputPlank3) {
		if(numOfAdjacentPlank >= maxNumOfAdjacentPlank - 2)
			return false;
		adjacentPlank[numOfAdjacentPlank++] = inputPlank1;
		adjacentPlank[numOfAdjacentPlank++] = inputPlank2;
		adjacentPlank[numOfAdjacentPlank++] = inputPlank3;
		return true;
	}
	// add four adjacent plank
	/**
	 * Add four elements into the variable adjacentPlank
	 * @param inputPlank1 the first plank which is added to the variable adjacentPlank
	 * @param inputPlank2 the second plank which is added to the variable adjacentPlank
	 * @param inputPlank3 the third plank which is added to the variable adjacentPlank
	 * @param inputPlank4 the fourth plank which is added to the variable adjacentPlank
	 * @return whether the planks are added correctly or not
	 */
	public boolean addAdjacentPlank(Plank inputPlank1, Plank inputPlank2, Plank inputPlank3, Plank inputPlank4) {
		if(numOfAdjacentPlank >= maxNumOfAdjacentPlank - 3)
			return false;
		adjacentPlank[numOfAdjacentPlank++] = inputPlank1;
		adjacentPlank[numOfAdjacentPlank++] = inputPlank2;
		adjacentPlank[numOfAdjacentPlank++] = inputPlank3;
		adjacentPlank[numOfAdjacentPlank++] = inputPlank4;		
		return true;
	}
	
	
	//to be used after create a plank to add the adjacentStump
	/**
	 * Add one element into the variable adjacentStump
	 * @param inputStump the stump which is added to the variable adjacentStump
	 * @return whether the stump is added correctly or not
	 */
	public boolean addAdjacentStump(Stump inputStump) {
		if(numOfAdjacentStump == maxNumOfAdjacentStump)
			return false;
		adjacentStump[numOfAdjacentStump++] = inputStump;
		return true;
	}
	
	/**
	 * Add two elements into the variable adjacentStump
	 * @param inputStump1 the first stump which is added to the variable adjacentStump
	 * @param inputStump2 the second stump which is added to the variable adjacentStump
	 * @return whether the stumps are added correctly or not
	 */
	public boolean addAdjacentStump(Stump inputStump1, Stump inputStump2) {
		if(numOfAdjacentStump >= maxNumOfAdjacentStump - 1)
			return false;
		adjacentStump[numOfAdjacentStump++] = inputStump1;
		adjacentStump[numOfAdjacentStump++] = inputStump2;
		return true;
	}
		
	/**
	 * Add one element into the variable horizontalAdjacentPlank(for complex plank)
	 * @param inputPlank the plank which is added to the variable horizontalAdjacentPlank
	 * @return whether the planks are added correctly or not
	 */
	public boolean addHorizontalAdjacentPlank(Plank inputPlank) {
		if(numOfHorizontalAdjacentPlank == maxNumOfAdjacentPlank)
			return false;
		horizontalAdjacentPlank[numOfHorizontalAdjacentPlank++] = inputPlank;
		return true;
	}
	
	/**
	 * Add two elements into the variable horizontalAdjacentPlank(for complex plank)
	 * @param inputPlank1 the first plank which is added to the variable horizontalAdjacentPlank
	 * @param inputPlank2 the second plank which is added to the variable horizontalAdjacentPlank
	 * @return whether the planks are added correctly or not
	 */
	public boolean addHorizontalAdjacentPlank(Plank inputPlank1, Plank inputPlank2) {
		if(numOfHorizontalAdjacentPlank >= maxNumOfAdjacentPlank - 1)
			return false;
		horizontalAdjacentPlank[numOfHorizontalAdjacentPlank++] = inputPlank1;
		horizontalAdjacentPlank[numOfHorizontalAdjacentPlank++] = inputPlank2;
		return true;
	}
	
	/**
	 * Add three elements into the variable horizontalAdjacentPlank(for complex plank)
	 * @param inputPlank1 the first plank which is added to the variable horizontalAdjacentPlank
	 * @param inputPlank2 the second plank which is added to the variable horizontalAdjacentPlank
	 * @param inputPlank3 the third plank which is added to the variable horizontalAdjacentPlank
	 * @return whether the planks are added correctly or not
	 */
	public boolean addHorizontalAdjacentPlank(Plank inputPlank1, Plank inputPlank2, Plank inputPlank3) {
		if(numOfHorizontalAdjacentPlank >= maxNumOfAdjacentPlank - 2)
			return false;
		horizontalAdjacentPlank[numOfHorizontalAdjacentPlank++] = inputPlank1;
		horizontalAdjacentPlank[numOfHorizontalAdjacentPlank++] = inputPlank2;
		horizontalAdjacentPlank[numOfHorizontalAdjacentPlank++] = inputPlank3;
		return true;
	}
	
	/**
	 * Add four elements into the variable horizontalAdjacentPlank(for complex plank)
	 * @param inputPlank1 the first plank which is added to the variable horizontalAdjacentPlank
	 * @param inputPlank2 the second plank which is added to the variable horizontalAdjacentPlank
	 * @param inputPlank3 the third plank which is added to the variable horizontalAdjacentPlank
	 * @param inputPlank4 the fourth plank which is added to the variable horizontalAdjacentPlank
	 * @return whether the planks are added correctly or not
	 */
	public boolean addHorizontalAdjacentPlank(Plank inputPlank1, Plank inputPlank2, Plank inputPlank3, Plank inputPlank4) {
		if(numOfHorizontalAdjacentPlank >= maxNumOfAdjacentPlank - 3)
			return false;
		horizontalAdjacentPlank[numOfHorizontalAdjacentPlank++] = inputPlank1;
		horizontalAdjacentPlank[numOfHorizontalAdjacentPlank++] = inputPlank2;
		horizontalAdjacentPlank[numOfHorizontalAdjacentPlank++] = inputPlank3;
		horizontalAdjacentPlank[numOfHorizontalAdjacentPlank++] = inputPlank4;
		return true;
	}
	
	/**
	 * Add one element into the variable verticalAdjacentPlank(for complex plank)
	 * @param inputPlank the plank which is added to the variable verticalAdjacentPlank
	 * @return whether the planks are added correctly or not
	 */
	public boolean addVerticalAdjacentPlank(Plank inputPlank) {
		if(numOfVerticalAdjacentPlank == maxNumOfAdjacentPlank)
			return false;
		verticalAdjacentPlank[numOfVerticalAdjacentPlank++] = inputPlank;
		return true;
	}
	
	/**
	 * Add two elements into the variable verticalAdjacentPlank(for complex plank)
	 * @param inputPlank1 the first plank which is added to the variable verticalAdjacentPlank
	 * @param inputPlank2 the second plank which is added to the variable verticalAdjacentPlank
	 * @return whether the planks are added correctly or not
	 */
	public boolean addVerticalAdjacentPlank(Plank inputPlank1, Plank inputPlank2) {
		if(numOfVerticalAdjacentPlank >= maxNumOfAdjacentPlank - 1)
			return false;
		verticalAdjacentPlank[numOfVerticalAdjacentPlank++] = inputPlank1;
		verticalAdjacentPlank[numOfVerticalAdjacentPlank++] = inputPlank2;
		return true;
	}
	
	/**
	 * Add three elements into the variable verticalAdjacentPlank(for complex plank)
	 * @param inputPlank1 the first plank which is added to the variable verticalAdjacentPlank
	 * @param inputPlank2 the second plank which is added to the variable verticalAdjacentPlank
	 * @param inputPlank3 the third plank which is added to the variable verticalAdjacentPlank
	 * @return whether the planks are added correctly or not
	 */
	public boolean addVerticalAdjacentPlank(Plank inputPlank1, Plank inputPlank2, Plank inputPlank3) {
		if(numOfVerticalAdjacentPlank >= maxNumOfAdjacentPlank - 2)
			return false;
		verticalAdjacentPlank[numOfVerticalAdjacentPlank++] = inputPlank1;
		verticalAdjacentPlank[numOfVerticalAdjacentPlank++] = inputPlank2;
		verticalAdjacentPlank[numOfVerticalAdjacentPlank++] = inputPlank3;
		return true;
	}
	
	/**
	 * Add four elements into the variable verticalAdjacentPlank(for complex plank)
	 * @param inputPlank1 the first plank which is added to the variable verticalAdjacentPlank
	 * @param inputPlank2 the second plank which is added to the variable verticalAdjacentPlank
	 * @param inputPlank3 the third plank which is added to the variable verticalAdjacentPlank
	 * @param inputPlank4 the fourth plank which is added to the variable verticalAdjacentPlank
	 * @return whether the planks are added correctly or not
	 */
	public boolean addVerticalAdjacentPlank(Plank inputPlank1, Plank inputPlank2, Plank inputPlank3, Plank inputPlank4) {
		if(numOfVerticalAdjacentPlank >= maxNumOfAdjacentPlank - 3)
			return false;
		verticalAdjacentPlank[numOfVerticalAdjacentPlank++] = inputPlank1;
		verticalAdjacentPlank[numOfVerticalAdjacentPlank++] = inputPlank2;
		verticalAdjacentPlank[numOfVerticalAdjacentPlank++] = inputPlank3;
		verticalAdjacentPlank[numOfVerticalAdjacentPlank++] = inputPlank4;		
		return true;
	}
		
	/**
	 * Add one element into the variable horizontalAdjacentStump(for complex plank)
	 * @param inputStump the stump which is added to the variable horizontalAdjacentStump
	 * @return whether the stump is added correctly or not
	 */
	public boolean addHorizontalAdjacentStump(Stump inputStump) {
		if(numOfHorizontalAdjacentStump == maxNumOfAdjacentStump)
			return false;
		horizontalAdjacentStump[numOfHorizontalAdjacentStump++] = inputStump;
		return true;
	}
	
	/**
	 * Add two elements into the variable horizontalAdjacentStump(for complex plank)
	 * @param inputStump1 the first stump which is added to the variable horizontalAdjacentStump
	 * @param inputStump2 the second stump which is added to the variable horizontalAdjacentStump
	 * @return whether the stumps are added correctly or not
	 */
	public boolean addHorizontalAdjacentStump(Stump inputStump1, Stump inputStump2) {
		if(numOfHorizontalAdjacentStump >= maxNumOfAdjacentStump - 1)
			return false;
		horizontalAdjacentStump[numOfHorizontalAdjacentStump++] = inputStump1;
		horizontalAdjacentStump[numOfHorizontalAdjacentStump++] = inputStump2;
		return true;
	}
	
	/**
	 * Add one element into the variable verticalAdjacentStump(for complex plank)
	 * @param inputStump the stump which is added to the variable verticalAdjacentStump
	 * @return whether the stump is added correctly or not
	 */
	public boolean addVerticalAdjacentStump(Stump inputStump) {
		if(numOfVerticalAdjacentStump == maxNumOfAdjacentStump)
			return false;
		verticalAdjacentStump[numOfVerticalAdjacentStump++] = inputStump;
		return true;
	}
	
	/**
	 * Add two elements into the variable verticalAdjacentStump(for complex plank)
	 * @param inputStump1 the first stump which is added to the variable verticcalAdjacentStump
	 * @param inputStump2 the second stump which is added to the variable verticalAdjacentStump
	 * @return whether the stumps are added correctly or not
	 */
	public boolean addVerticalAdjacentStump(Stump inputStump1, Stump inputStump2) {
		if(numOfVerticalAdjacentStump >= maxNumOfAdjacentStump - 1)
			return false;
		verticalAdjacentStump[numOfVerticalAdjacentStump++] = inputStump1;
		verticalAdjacentStump[numOfVerticalAdjacentStump++] = inputStump2;
		return true;
	}
	
	//----------------------------------action methods-----------------------------------------------------------
	/**
	 * Check whether the plank can be picked or not
	 * @param map the map of the game
	 * @return whether the plank can be picked or not
	 */
	private boolean canPick(CreateMap map) {
		for(int i = 0; i < numOfAdjacentPlank; ++i)
		{
			if(adjacentPlank[i].getIsComplex())
			{
				if(!adjacentPlank[i].canPickComplex(map))
				{
					//System.out.println("1");
					setWarningString(1);
					return false;
				}
			}
		}
		
		if(!adjacentStump[0].getHasPerson() && !adjacentStump[1].getHasPerson())
		{
			//System.out.println("100");
			setWarningString(1);//player should be near the plank;
			return false;
		}
		else if(map.getCurrentPlank() != 0)
		{
			setWarningString(4);//player has carried a plank
			return false;
		}
		else if(!visible)
		{
			setWarningString(5);//there is no plank
			return false;
		}
		else
			return true;
	}
	
	/**
	 * Check whether the plank can be picked or not(for complex plank)
	 * @param map the map of the game
	 * @return whether the plank can be picked or not
	 */
	private boolean canPickComplex(CreateMap map) {
		if((verticalAdjacentStump[0].getHasPerson() || verticalAdjacentStump[1].getHasPerson()) && map.getCurrentPlank() == 0 && visible && !isHorizontal)
			return true;
		else if((horizontalAdjacentStump[0].getHasPerson() || horizontalAdjacentStump[1].getHasPerson()) && map.getCurrentPlank() == 0 && visible && isHorizontal)
			return true;
		else
		{
			//System.out.println(map.getCurrentPlank());
			if(map.getCurrentPlank() != 0)
			{
				//System.out.println("4");
				setWarningString(4);//player has carried a plank
			}
			else if(!visible)
			{
				//System.out.println("5");
				setWarningString(5);//there is no plank
			}
			else if(isHorizontal)
			{
				setWarningString(1);
			}
			else
			{
				//System.out.println("1");
				setWarningString(1);
			}
			return false;
		}
	}
	
	/**
	 * Check whether the plank can be put or not
	 * @param map the map of the game
	 * @return whether the plank can be put or not
	 */
	private boolean canPut(CreateMap map) {
		for(int i = 0; i < numOfAdjacentPlank; ++i)
		{
			if(adjacentPlank[i].getVisible())
			{
				setWarningString(3);
				return false;
			}
		}
		if(!adjacentStump[0].getHasPerson()&& !adjacentStump[1].getHasPerson())
		{
			setWarningString(1); //player is not near the plank
			return false;
		}
		else if(map.getCurrentPlank() == 0)
		{
			setWarningString(6);//play does not carry a plank
			return false;
		}
		else if(map.getCurrentPlank() != length)
		{
			setWarningString(2);// the length does not fit
			return false;
		}
		else if(visible)
		{
			setWarningString(3);// there has been a plank now
			return false;
		}	
		else
			return true;
	}
	
	/**
	 * Check whether the plank can be put or not(for complex plank)
	 * @param map the map of the game
	 * @return whether the plank can be put or not
	 */
	private boolean canPutComplex(CreateMap map) {
		if((verticalAdjacentStump[0].getHasPerson() || verticalAdjacentStump[1].getHasPerson()) && map.getCurrentPlank() == verticalLength && !visible)
			return true;
		else if((horizontalAdjacentStump[0].getHasPerson() || horizontalAdjacentStump[1].getHasPerson()) && map.getCurrentPlank() == horizontalLength && !visible)
			return true;
		else
		{
			if(!verticalAdjacentStump[0].getHasPerson() && !verticalAdjacentStump[1].getHasPerson() && !horizontalAdjacentStump[0].getHasPerson() && !horizontalAdjacentStump[1].getHasPerson())
				setWarningString(1);//player is not near the plank
			else if(map.getCurrentPlank() == 0)
				setWarningString(6);//player does not carry a plank
			else if(map.getCurrentPlank() != length)
				setWarningString(2);// the length does not fit
			else if(visible)
				setWarningString(3);// there has been a plank now
			return false;
		}
	}
	//action of picking one single plank  
	/**
	 * the action that pick this plank
	 * @param map the map of the game
	 */
	private void pickOnePlank(CreateMap map) {
		setVariableVisible(false);
		setIcon(imageWater);
	}
	
	/**
	 * the action that pick the whole plank, including this plank and the adjacent plank
	 * @param map the map of the game
	 */	
	private void pick(CreateMap map) {
		this.pickOnePlank(map);
		for(int i = 0; i < numOfAdjacentPlank; ++i)
		{
			adjacentPlank[i].pickOnePlank(map);
		}
		if(isHorizontal){
			imageHorizontal.setImage(imageHorizontal.getImage().getScaledInstance(115, 50, Image.SCALE_DEFAULT));
			map.getPlankLabel().setHorizontalAlignment(CENTER);
			map.getPlankLabel().setIcon(imageHorizontal);
		}
		else{
			imageVertical.setImage(imageVertical.getImage().getScaledInstance(50, 115, Image.SCALE_DEFAULT));
			map.getPlankLabel().setHorizontalAlignment(CENTER);
			map.getPlankLabel().setIcon(imageVertical);
		}
		adjacentStump[0].removeConnected(map);
		adjacentStump[1].removeConnected(map);
		map.setCurrentPlank(length);
	}
	/**
	 * the action that pick the whole plank, including this plank and the adjacent plank(for complex plank)
	 * @param map the map of the game
	 */	
	private void pickComplex(CreateMap map) {
		this.pickOnePlank(map);
		if(horizontalAdjacentStump[0].getHasPerson() || horizontalAdjacentStump[1].getHasPerson())
		{
			for(int i = 0; i < numOfHorizontalAdjacentPlank; ++i)
			{
				horizontalAdjacentPlank[i].pickOnePlank(map);
			}
			map.setCurrentPlank(horizontalLength);
		}
		else
		{
			for(int i = 0; i < numOfVerticalAdjacentPlank; ++i)
			{
				verticalAdjacentPlank[i].pickOnePlank(map);
			}
			map.setCurrentPlank(verticalLength);
		}
		if(horizontalAdjacentStump[0].getHasPerson() || horizontalAdjacentStump[1].getHasPerson())
		{
			horizontalAdjacentStump[0].removeConnected(map);
			horizontalAdjacentStump[1].removeConnected(map);
		}
		else
		{
			verticalAdjacentStump[0].removeConnected(map);
			verticalAdjacentStump[1].removeConnected(map);
		}
	}
	
	/**
	 * the action that put this plank
	 * @param map the map of the game
	 */
	private void putOnePlank(CreateMap map) {
		setVariableVisible(true);
		if(isHorizontal) {
			imageHorizontal.setImage(imageHorizontal.getImage().getScaledInstance(100, 30, Image.SCALE_DEFAULT));
			setIcon(imageHorizontal);
		}
		else {
			imageVertical.setImage(imageVertical.getImage().getScaledInstance(30, 100, Image.SCALE_DEFAULT));
			setIcon(imageVertical);
		}
	}
	
	/**
	 * the action that put this plank(for complex plank)
	 * @param map the map of the game
	 * @param horizontal the direction of the plank should be put
	 */
	private void putOnePlank(CreateMap map, boolean horizontal) {
		setVariableVisible(true);
		if(horizontal) {
			imageHorizontal.setImage(imageHorizontal.getImage().getScaledInstance(100, 30, Image.SCALE_DEFAULT));
			setIcon(imageHorizontal);
			setIsHorizontal(horizontal);
			//System.out.println("1Hor");
		}
		else {
			imageVertical.setImage(imageVertical.getImage().getScaledInstance(30, 100, Image.SCALE_DEFAULT));
			setIcon(imageVertical);
			setIsHorizontal(horizontal);
			//System.out.println("2Ver");
		}
	}
	/**
	 * the action that put the whole plank, including this plank and the adjacent plank
	 * @param map the map of the game
	 */	
	private void put(CreateMap map) {
		if(this.isComplex)
		{
			if(this.getHorizontalAdjacentStump(0).getHasPerson() || this.getHorizontalAdjacentStump(1).getHasPerson())
			{
				this.putOnePlank(map, true);
				this.setIsHorizontal(true);
			}
			else
			{
				this.putOnePlank(map, false);
				this.setIsHorizontal(false);
			}
		}
		else
		{
			putOnePlank(map);
		}
		for(int i = 0; i < numOfAdjacentPlank; ++i)
		{
			if(!adjacentPlank[i].getIsComplex())
				
				
				adjacentPlank[i].putOnePlank(map);
			else
			{
				if(adjacentPlank[i].getHorizontalAdjacentStump(0).getHasPerson() || adjacentPlank[i].getHorizontalAdjacentStump(1).getHasPerson())
				{
					adjacentPlank[i].putOnePlank(map, true);
					adjacentPlank[i].setIsHorizontal(true);
					//System.out.println("1hor");
					//System.out.println(adjacentPlank[i].getHorizontalAdjacentStump(0).getStumpX() + " " + adjacentPlank[i].getHorizontalAdjacentStump(1).getStumpY());
				}
				else
				{
					adjacentPlank[i].putOnePlank(map, false);
					adjacentPlank[i].setIsHorizontal(false);
					//System.out.println("2ver");
				}
			}
			
		}
		adjacentStump[0].setConnected(map);
		adjacentStump[1].setConnected(map);
		map.setCurrentPlank(0);
		ImageIcon icon = new ImageIcon();
		map.getPlankLabel().setIcon(icon);
	}
	/**
	 * the action that put the whole plank, including this plank and the adjacent plank(for complex plank)
	 * @param map the map of the game
	 */	
	private void putComplex(CreateMap map) {
		if(horizontalAdjacentStump[0].getHasPerson() || horizontalAdjacentStump[1].getHasPerson())		
		{
			this.putOnePlank(map, true);	
			for(int i = 0; i < numOfHorizontalAdjacentPlank; ++i)
			{
				horizontalAdjacentPlank[i].putOnePlank(map, true);
			}
		}
		else	
		{
			this.putOnePlank(map, false);	
			for(int i = 0; i < numOfVerticalAdjacentPlank; ++i)
			{
				verticalAdjacentPlank[i].putOnePlank(map, false);
			}
		}
		if(this.getHorizontalAdjacentStump(0).getHasPerson() || this.getHorizontalAdjacentStump(1).getHasPerson())
		{
			horizontalAdjacentStump[0].setConnected(map);
			horizontalAdjacentStump[1].setConnected(map);				
		}
		else
		{
			verticalAdjacentStump[0].setConnected(map);
			verticalAdjacentStump[1].setConnected(map);
		}
		map.setCurrentPlank(0);
		
	}
	
	/**
	 * the method which should be called while clicking this class
	 * Implements the put or pick the plank when this plank is clicked
	 * @param map the map of the game
	 * @return whether the plank is picked or put or not
	 */
	public boolean click(CreateMap map) {
		//System.out.println("posx: " + map.getPersonX() + " posy: " + map.getPersonY());
//		for(int i = 0; i < numOfAdjacentPlank; ++i)
//		{
//			System.out.println(adjacentPlank[i].getPlankX() +" " + adjacentPlank[i].getPlankY());
//		}
		if(!isComplex)
		{
			if(visible)
			{
				if(canPick(map))
					pick(map);
				else
				{
					CreateStringFrame frameInPlank = new CreateStringFrame(warningString);
					return false;
				}
			}
			else
			{
				if(canPut(map))
					put(map);
				else
				{
					CreateStringFrame frameInPlank = new CreateStringFrame(warningString);
					return false;
				}
			}
			return true;
		}
		else
		{
			if(visible)
			{
				if(canPickComplex(map))
					pickComplex(map);
				else
				{
					CreateStringFrame frameInPlank = new CreateStringFrame(warningString);
					return false;
				}
			}
			else
			{
				if(canPutComplex(map))
					putComplex(map);
				else
				{
					CreateStringFrame frameInPlank = new CreateStringFrame(warningString);
					return false;
				}
			}
			return true;
		}
	}
	
}