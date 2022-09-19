import javax.swing.*;
import java.awt.*;

/**
 * Models a stump.
 * This class represents a JButton object of a stump. When combined with the Create map class,
 * instances of the Stump class can be displayed on the window.
 * @author Yuhao Zhu
 */
public class Stump extends JButton {
    private boolean hasMan=false;
    private boolean isEnd=false;
    private int stumpX=-1;
    private int stumpY=-1;
    private boolean isConnected = false;
    private ImageIcon iconStump = new ImageIcon("img/stump.png");
    private ImageIcon iconStumpMan = new ImageIcon("img/stump_man1.png");

//----------------------------Constructor------------------------------------
    /**
     * Constructor. Creates an instance of Stump with the default character.
     */
    public Stump() {}
    /**
     * Constructor. Creates an instance of Stump with the given character
     * 
     * @param stumpX The x-coordinate of the stump on the screen.
     * @param stumpY The y-coordinate of the stump on the screen.
     * @param hasMan The flag to indicate whether there is a man on the stump.
     * @param isEnd The flag to indicate whether reach the end.
     */
    public Stump(int stumpX, int stumpY, boolean hasMan, boolean isEnd)
    {
        this.stumpX = stumpX;
        this.stumpY = stumpY;
        this.hasMan = hasMan;
        this.setVisible(true);
        if (hasMan) 
        {
            iconStumpMan.setImage(iconStumpMan.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            this.setIcon(iconStumpMan);
            CreateMap.personX=stumpX;
            CreateMap.personY=stumpY;
        }    
        else 
        {
            iconStump.setImage(iconStump.getImage().getScaledInstance(77, 77, Image.SCALE_DEFAULT));
            this.setIcon(iconStump);
        }   
        this.isEnd=isEnd;
    }
    /**
     * Constructor. Creates an instance of Stump 
     * 
     * @param stumpX The x-coordinate of the stump on the screen.
     * @param stumpY The y-coordinate of the stump on the screen.
     * @param hasMan The flag to indicate whether there is a man on the stump.
     */
    public Stump(int stumpX, int stumpY, boolean hasMan) {
        this(stumpX, stumpY, hasMan, false);
    }


//-----------------------------------------Get attribute method-----------------------------------------
    /**
     * Obtains the x-coordinate of the stump on the screen.
     * @return The x-coordinate.
     */
    public int getStumpX() {
        return stumpX;
    }
    /**
     * Obtains the y-coordinate of the stump on the screen.
     * @return The y-coordinate.
     */
    public int getStumpY() {
        return stumpY;
    }
    /**
     * Obtains whether the stump has a person on it.
     * @return A boolean flag whether has a person.
     */
    public boolean getHasPerson() {
        return hasMan;
    }
    /**
     * Obtains the connected stumps of this stump. Offset on x and y aixs represent the direction of the stump.
     * @param offsetX The offset on x direction, 1 represent the stump above, -1 represent the stump below.
     * @param offsetY The offset on y direction, 1 represent the stump on right, -1 represent the stump on left.
     * @param map The corresponding level of map.
     * @return The stump on specified direction.
     */
    private Stump getConnectedStump(int offsetX, int offsetY, CreateMap map)
    {
        if (((Plank)map.getGrid(stumpX+offsetX, stumpY+offsetY)).getAdjacentStump(0).equals(this)) 
            return ((Plank)map.getGrid(stumpX+offsetX, stumpY+offsetY)).getAdjacentStump(1);
        else 
            return ((Plank)map.getGrid(stumpX+offsetX, stumpY+offsetY)).getAdjacentStump(0);
    }

//------------------------------------------Logical judgement method------------------------------------------

    /**
     * Determine whether there is a plank above this stump.
     * @param map The corresponding level of map.
     * @return Return true if there is a plank above, else return false.
     */
    private boolean hasPlankAbove(CreateMap map)
    {
        if (stumpY-1>=0 && map.getGrid(stumpX, stumpY-1) instanceof Plank && ((Plank)map.getGrid(stumpX, stumpY-1)).getVisible())
            return true;
        else
            return false;
    }
    /**
     * Determine whether there is a plank below this stump.
     * @param map The corresponding level of map.
     * @return Return true if there is a plank below, else return false.
     */
    private boolean hasPlankBelow(CreateMap map)
    {
        if (stumpY+1<=12 && map.getGrid(stumpX, stumpY+1) instanceof Plank && ((Plank)map.getGrid(stumpX, stumpY+1)).getVisible())
            return true;
        else
            return false;
    }
    /**
     * Determine whether there is a plank on the left of this stump.
     * @param map The corresponding level of map.
     * @return Return true if there is a plank on the left, else return false.
     */
    private boolean hasPlankleft(CreateMap map)
    {
        if (stumpX-1 >=0 && map.getGrid(stumpX-1, stumpY) instanceof Plank && ((Plank)map.getGrid(stumpX-1, stumpY)).getVisible())
            return true;
        else
            return false;
    }
    /**
     * Determine whether there is a plank on the right of this stump.
     * @param map The corresponding level of map.
     * @return Return true if there is a plank on the right, else return false.
     */
    private boolean hasPlankRight(CreateMap map)
    {
        if (stumpX+1<=8 && map.getGrid(stumpX+1, stumpY) instanceof Plank && ((Plank)map.getGrid(stumpX+1, stumpY)).getVisible())
            return true;
        else
            return false;
    }
    /**
     * To judge whether the location of given stump equal this stump.
     * @param stump The given stump.
     * @return Return ture if two stumps equal, else return false.
     */
    public boolean equals(Stump stump)
    {
        if (stumpX == stump.getStumpX() && stumpY == stump.getStumpY()) return true;
        else return false;
    }

//-----------------------------------------Set attribute method------------------------------------------
    /**
     * Set the hasMan flag of this stump.
     * @param hasMan The given hasMan flag.
     */
    public void setHasPerson(boolean hasMan){
        this.hasMan=hasMan;
        if (hasMan) {
            iconStumpMan.setImage(iconStumpMan.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            this.setIcon(iconStumpMan);
        }
        else {
            iconStump.setImage(iconStump.getImage().getScaledInstance(77, 77, Image.SCALE_DEFAULT));
            this.setIcon(iconStump);
        }
    }
    /**
     * This method will be invoked when a plank is put. 
     * The adjacent stumps of the plank will be set to connected.
     * @param map The corresponding level of map
     */
    public void setConnected(CreateMap map)
    {
        if (hasMan) return;
        if (isConnected) return;
        initialConnected(map);
        return;
    }
    /**
     * Initialize the isConnected flag of this stump and the stumps connected to it.
     * This method will be invoked in setConnected and in the initialization of starting point.
     * @param map The corresponding level of map
     */
    public void initialConnected(CreateMap map)
    {
        isConnected=true;
        if (hasPlankAbove(map))
        {
            getConnectedStump(0, -1, map).setConnected(map);
        }
        if (hasPlankBelow(map))
        {
            getConnectedStump(0, 1, map).setConnected(map);
        }
        if (hasPlankleft(map))
        {
            getConnectedStump(-1, 0, map).setConnected(map);
        }
        if (hasPlankRight(map))
        {
            getConnectedStump(1, 0, map).setConnected(map);
        }   
    }
    /**
     * This method will be invoked when a plank is picked. 
     * The adjacent stumps of the plank will be set to not connected.     
     * @param map The corresponding level of map
     */
    public void removeConnected(CreateMap map)
    {
        if (hasMan) return;
        if (!isConnected) return;
        isConnected=false;
        if (hasPlankAbove(map))
        {
            getConnectedStump(0, -1, map).removeConnected(map);
        }
        if (hasPlankBelow(map))
        {
            getConnectedStump(0, 1, map).removeConnected(map);
        }
        if (hasPlankleft(map))
        {
            getConnectedStump(-1, 0, map).removeConnected(map);
        }
        if (hasPlankRight(map))
        {
            getConnectedStump(1, 0, map).removeConnected(map);
        }
    }

//-------------------------------------------Executive method-----------------------------------
    /**
     * Move person to this stump
     * @param map The corresponding level of map
     */
    public void move(CreateMap map)
    {
        ((Stump)map.getGrid(map.getPersonX(), map.getPersonY())).setHasPerson(false);
        setHasPerson(true);
        map.setPersonX(stumpX);
        map.setPersonY(stumpY);
        if (isEnd)
        {
            map.getTimer().gameIsPassed(true);
            if (CreateMap.currentlevel==1&&map.getTimer().getTime() < CreateMap.level3minimum) {
                CreateMap.level3minimum = map.getTimer().getTime();
                CreateMap.shortestTime.setText(CreateMap.shortestTime.toString(CreateMap.level3minimum));
            }
            else if (CreateMap.currentlevel==2&&map.getTimer().getTime() < CreateMap.level13minimum) {
                CreateMap.level13minimum = map.getTimer().getTime();
                CreateMap.shortestTime.setText(CreateMap.shortestTime.toString(CreateMap.level13minimum));
            }
            else if (CreateMap.currentlevel==3&&map.getTimer().getTime() < CreateMap.level23minimum) {
                CreateMap.level23minimum = map.getTimer().getTime();
                CreateMap.shortestTime.setText(CreateMap.shortestTime.toString(CreateMap.level23minimum));
            }
        }
    }
    /**
     * Implements the movement of person when this stump is clicked
     * @param map The corresponding level of map
     */
    public void clickEvent(CreateMap map)
    {
        if (isConnected) move(map);
    }
}
