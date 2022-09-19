
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Initialization of levels 3, 13, and 23 using grid layout 
 * initialize specific grids as plank and stump
 * The interface is divided into a game area and a functional area
 * @author Xinchen Zhao, Ziqiang Zhang, Fenwei Guo, Ruotong Wang
 */
public class CreateMap extends JFrame implements ActionListener {
	//initialize the map
	private int Rows = 13;
    private int Columns = 9;
	public static int personX=0, personY=0;
	//using JButton array to create map
	private JButton[][] mapGrid = new JButton[Rows][Columns];
	//gamePanel is game area, functionPanel is functional area
    public static JPanel gamePanel,functionPanel,mainPanel;
    //click the backButton and appear the final page
    public JButton backButton,timer;
    //using label array to describe functionPanel
    public static JLabel[] label=new JLabel[6];
    public static ShortestTimeLabel shortestTime;
    //the initial shortest time of level3
    public static long level3minimum=100;
	//the initial shortest time of level13
	public static long level13minimum=100;
	//the initial shortest time of leve23
	public static long level23minimum=100;
	//current level
    public static int currentlevel;
    private int mapData[][]=new int[Rows][Columns];
    private int currentPlank;
    //Define the music of Level3, Level13, Leve23 and adding a plank
	MusicPlayer playerLevel3 = new MusicPlayer("music\\Level_3.wav");
	MusicPlayer playerLevel13 = new MusicPlayer("music\\Level_13.wav");
	MusicPlayer playerLevel23 = new MusicPlayer("music\\Level_23.wav");
	MusicPlayer plankMusicEffect = new MusicPlayer("music\\plank.wav");
	//MusicPlayer waterMusicEffect = new MusicPlayer("music\\water.wav");
	
	//----------------------------Constructor------------------------------------
	/**
	 * default constructor 
	 */
    public CreateMap() {}
    /**
	 * Initialize the map for each level
	 * @param level the level number
	 */
    public CreateMap(int level) {
    	this.setTitle("CourseWork");
		this.setVisible(true);
        this.setLayout(null);
    	mainPanel=new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//give different scenes to different levels
		if(level==1) {
			gamePanel = new BackgroundPanel(new ImageIcon("img/background_level3.png").getImage());
		}
		else if(level==2) {
			gamePanel = new BackgroundPanel(new ImageIcon("img/background_level13.png").getImage());
		}
		else if(level==3) {
			gamePanel = new BackgroundPanel(new ImageIcon("img/background_level23.png").getImage());
		}
		gamePanel.setPreferredSize(new Dimension(428,600));
		gamePanel.setLayout(new GridLayout(13,9));
		functionPanel=new JPanel();
		functionPanel.setPreferredSize(new Dimension(200,600));
        functionPanel.setLayout(new GridLayout(8,1));
		mainPanel.add(gamePanel);
		mainPanel.add(functionPanel);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        switch(level) 
        {
        case 1:
        	//map 3
			initMapData_3();
			createGamePanel3();
        	break;
        case 2:
		    //map13
        	initMapData_13();
        	createGamePanel13();
        	break;
        case 3:
		    //map23
        	initMapData_23();
        	createGamePanel23();
        	break;
        }
    }
    /**
     * Initialize the map level 3
     */
    public void createGamePanel3(){
		for(int i=0; i<Rows;i++) {
			for(int j=0;j<Columns;j++) {
				switch(mapData[i][j])
				{
					case 1://person is on the stump
						mapGrid[i][j] = new Stump(j, i, true);
						personX=4;
						personY=12;
						mapGrid[i][j].addActionListener(this);
						break;
					case 2://stump
						if(i==0&&j==6) {//the end stump
							mapGrid[i][j] = new Stump(j, i, false, true);
						}
						else {//the other stumps which initially don't have person
							mapGrid[i][j] = new Stump(j, i, false);
						}
						mapGrid[i][j].addActionListener(this);
						break;
					case 3://the position which initially has plank
						//plank isHori false length is 1
						if((i==11&&j==4)) {//visible
							mapGrid[i][j] = new Plank(j, i, 1, true, false);
							mapGrid[i][j].addActionListener(this);
						}
						//plank isHori false length is 3
						else if ((i==7&&j==2) || (i==8&& j==2) || (i==9 && j==2))//visible
						{
							mapGrid[i][j] = new Plank(j, i, 3, true, false);
							mapGrid[i][j].addActionListener(this);
						}
						break;
					case 4://the position which can have plank later
						//plank isHori false length is 3
						if( (i==3&& j==6)|| (i==4&& j==6)|| (i==5&& j==6)) {
							mapGrid[i][j] = new Plank(j, i, 3, false, false);
							mapGrid[i][j].addActionListener(this);
						}
						//plank isHori true length is 3
						else if ((i==6&& j==3)|| (i==6&& j==4)|| (i==6&& j==5)){
							mapGrid[i][j] = new Plank(j, i, 3, false, true);
							mapGrid[i][j].addActionListener(this);
						}
						//plank isHori false length is 1
						else if( i==1&&j==6) {
							mapGrid[i][j] = new Plank(j, i, 1, false, false);
							mapGrid[i][j].addActionListener(this);
						}
						//plank isHori true length is 1
						else if((i==10&&j==3)||(i==6&&j==1)||(i==6&&j==7)) {
							mapGrid[i][j] = new Plank(j, i, 1, false, true);
							mapGrid[i][j].addActionListener(this);
						}
						break;
					case 5://water
						mapGrid[i][j]=new JButton();
						break;
					default:
						break;
				}
				mapGrid[i][j].setBorder(null);
				mapGrid[i][j].setContentAreaFilled(false);
				gamePanel.add(mapGrid[i][j]);
			}
		}
		addStump_3();
		addPlank_3();
		((Stump)mapGrid[personY][personX]).initialConnected(this);
		this.setResizable(false);
		addLabel();
		this.setContentPane(mainPanel);
		this.pack();
	}

    /**
	 * Initialize the map level 13
	 */
    public void createGamePanel13()
    {
    	for(int i=0; i<Rows;i++) {
            for(int j=0;j<Columns;j++) {
             switch(mapData[i][j])
             {
              case 1:
              mapGrid[i][j] = new Stump(j, i, true);
              personX=2;
              personY=12;
              mapGrid[i][j].addActionListener(this);
              break;
             case 2://
              if(i==0&&j==6) {
              mapGrid[i][j] = new Stump(j, i, false, true);
              }
              else {
              mapGrid[i][j] = new Stump(j, i, false);
              }
              mapGrid[i][j].addActionListener(this);
              break;
             case 3://
              if((i==7&&j==8)) {
              mapGrid[i][j] = new Plank(j, i, 1, true, false);
              mapGrid[i][j].addActionListener(this);
              }
              else if ((i==11&&j==2) || (i==10&& j==2) || (i==9 && j==2)){
              mapGrid[i][j] = new Plank(j, i, 3, true, false);
              mapGrid[i][j].addActionListener(this);
              }
              else if ((i==8&&j==4)) {
              mapGrid[i][j] = new Plank(j, i, 5, 5, true, true, true);
              mapGrid[i][j].addActionListener(this);
              }
              else if ((i==8&&j==3)|| (i==8&& j==5)|| (i==8&& j==6)|| (i==8&& j==7)) {
              mapGrid[i][j] = new Plank(j, i, 5, true, true);
              mapGrid[i][j].addActionListener(this);
              }
              break;
             case 4:
              if( (i==9&& j==8)|| (i==5&& j==0)|| (i==3&& j==4)) {
              mapGrid[i][j] = new Plank(j, i, 1, false, false);
              mapGrid[i][j].addActionListener(this);
              }
              else if((i==6&&j==7)) {
              mapGrid[i][j] = new Plank(j, i, 1, false, true);
              mapGrid[i][j].addActionListener(this);
              }
              else if ((i==10&&j==5) || (i==10&& j==6) || (i==10 && j==7)|| (i==4 && j==1)|| (i==4 && j==2)|| (i==4 && j==3))
              {
               mapGrid[i][j] = new Plank(j, i, 3, false, true);
               mapGrid[i][j].addActionListener(this);
              }
              else if ((i==1&&j==6)|| (i==2&& j==6)|| (i==3&& j==6)|| (i==4&& j==6)|| (i==5&& j==6)) {
               mapGrid[i][j] = new Plank(j, i, 5, false, false);
               mapGrid[i][j].addActionListener(this);
              }
              else if ((i==5&&j==4)|| (i==7&& j==4)|| (i==9&& j==4)) {
               mapGrid[i][j] = new Plank(j, i, 5, false, false);
               mapGrid[i][j].addActionListener(this);
              }
              else if ((i==6&&j==4)) {
               mapGrid[i][j] = new Plank(j, i, 5, 5,false, false,true);
               mapGrid[i][j].addActionListener(this);
              }
              else if ((i==6&&j==1)|| (i==6&& j==2)|| (i==6&& j==3)|| (i==6&& j==5)) {
               mapGrid[i][j] = new Plank(j, i, 5, false, true);
               mapGrid[i][j].addActionListener(this);
              }
              break;
            case 5:
                mapGrid[i][j]=new JButton();
              break;
            default:
              break;
                } 
            }
        }
        for(int i=0; i<Rows;i++) {
             for(int j=0;j<Columns;j++) {
             mapGrid[i][j].setBorder(null);
             mapGrid[i][j].setContentAreaFilled(false);
             gamePanel.add(mapGrid[i][j]);
            }
        }
           addStump_13();
           addPlank_13();
           ((Stump)mapGrid[personY][personX]).initialConnected(this);
           this.setResizable(false);
           addLabel();
           this.setContentPane(mainPanel);
           this.pack();
    }
    /**
	 * Initialize the map level 23
	 */
    public void createGamePanel23()
    {
    	for(int i=0; i<Rows;i++) {
        	for(int j=0;j<Columns;j++) {
        		switch(mapData[i][j])
        		{
				//1:people on stump, 2:original stump, 3:original plank 4:Where planks may appear 5: null(water)
        		case 1:
        			//call the constructor of Stump class to init
        			mapGrid[i][j] = new Stump(j, i, true);
					personX=4;
					personY=12;
					mapGrid[i][j].addActionListener(this);
        			break;
        		case 2:
        			if(i==0&&j==4) {
        				mapGrid[i][j]=new Stump(j,i,false,true);
        			}
        			else {
        				mapGrid[i][j] = new Stump(j, i, false);
        			}
        			mapGrid[i][j].addActionListener(this);
        			break;
        		case 3:
        			//call the constructor of Plank to init, the length of plank is 1
        			if((i==11&&j==4)) {
						mapGrid[i][j] = new Plank(j, i, 1, true, false);
						mapGrid[i][j].addActionListener(this);
					}
        			//the length of plank is 3
					else if ((i==9&&j==4)||(i==8&&j==4)||(i==7&&j==4))
					{
						mapGrid[i][j] = new Plank(j, i, 3, true, false);
						mapGrid[i][j].addActionListener(this);
					}
        			//the length of plank is 3
					else if((i==4&&j==3)||(i==4&&j==5)) {
						mapGrid[i][j] = new Plank(j, i, 3, true, true);
						mapGrid[i][j].addActionListener(this);
					}
        			//call the constructor of Plank to init the complex plank
					else if(i==4&&j==4) {
						mapGrid[i][j]=new  Plank(j, i, 3, 3, true,true,true);
						mapGrid[i][j].addActionListener(this);
					}
        			break;
        		case 4:
        			//call the constructor as above
        			if((i==9&&j==0)||(i==9&&j==8)||(i==1&&j==4)) {
        				mapGrid[i][j] = new Plank(j, i, 1, false, false);
        				mapGrid[i][j].addActionListener(this);
        			}
        			else if((i==4&&j==1)||(i==4&&j==7)) {
        				mapGrid[i][j] = new Plank(j, i, 1, false, true);
        				mapGrid[i][j].addActionListener(this);
        			}
        			else if((i==10&&j==1)||(i==10&&j==2)||(i==10&&j==3)||(i==10&&j==5)||(i==10&&j==6)||(i==10&&j==7)) {
        				mapGrid[i][j] = new Plank(j, i, 3, false, true);
        				mapGrid[i][j].addActionListener(this);
        			}
        			else if((i==5&&j==0)||(i==6&&j==0)||(i==7&&j==0)||(i==7&&j==8)||(i==6&&j==8)||(i==5&&j==8)||(i==3&&j==4)||(i==5&&j==4)) {
        				mapGrid[i][j] = new Plank(j, i, 3, false, false);
        				mapGrid[i][j].addActionListener(this);
        			}
        			else if((i==6&&j==5)||(i==6&&j==4)||(i==6&&j==3)) {
        				mapGrid[i][j] = new Plank(j, i, 3, false, true);
        				mapGrid[i][j].addActionListener(this);
        			}
        			break;
        		case 5:
        			mapGrid[i][j] = new JButton();
        			break;
        		default:
        			break;
        		}	
           }
        }
		for(int i=0; i<Rows;i++) {
        	for(int j=0;j<Columns;j++) {
        		//describe the character of buttons, no border and transparent
				mapGrid[i][j].setBorder(null);
				mapGrid[i][j].setContentAreaFilled(false);
				gamePanel.add(mapGrid[i][j]);
			}
		}
		addStump_23();
	    addPlank_23();
	    ((Stump)mapGrid[personY][personX]).initialConnected(this);
		this.setResizable(false);
	    addLabel();
		this.setContentPane(mainPanel);
		this.pack();
    }
    
    /**
	 * Store the information of map3 in a two-dimensional array
	 */
    public void initMapData_3(){
		for(int i=0;i<Rows;i++) {
			for(int j=0;j<Columns;j++) {
				//the stump which initially has person
				if(i==12&&j==4) {
					mapData[i][j]=1;
				}
				//the stump which initially doesn't have person
				else if((i==10&&j==4)||(i==10&&j==2)||(i==6&&j==0)||(i==6&&j==2)||(i==6&&j==6)||(i==6&&j==8)||(i==2&&j==6)||(i==0&&j==6)) {
					mapData[i][j]=2;
				}
				//the position which initially has plank
				else if((i==11&&j==4)||(i==9&&j==2)||(i==8&&j==2)||(i==7&&j==2)){
					mapData[i][j]=3;
				}
				//the position which initially doesn't have plank
				else if((i==10&&j==3)||(i==6&&j==1)||(i==6&&j==3)||(i==6&&j==4)||(i==6&&j==5)||(i==6&&j==7)||(i==5&&j==6)||(i==4&&j==6)||(i==3&&j==6)||(i==1&&j==6)) {
					mapData[i][j]=4;
				}
				//the water position
				else mapData[i][j]=5;
			}
		}
		//play level3 music on loop
		try {
			playerLevel3.setLoop(true);
			playerLevel3.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * Store the information of map13 in a two-dimensional array
	 */
    public void initMapData_13()
    {
    	for(int i=0;i<Rows;i++) {
            for(int j=0;j<Columns;j++) {
             if(i==12&&j==2) {
              mapData[i][j]=1;
             }
             else if((i==8&&j==2)||(i==8&&j==8)||(i==10&&j==8)||(i==10&&j==4)||(i==6&&j==8)||(i==6&&j==6)||(i==6&&j==0)||(i==4&&j==0)||(i==4&&j==4)||(i==2&&j==4)||(i==0&&j==6)) {
              mapData[i][j]=2;
             }
             else if((i==11&&j==2)||(i==10&&j==2)||(i==9&&j==2)||(i==7&&j==8)||(i==8&&j==3)||(i==8&&j==4)||(i==8&&j==5)||(i==8&&j==6)||(i==8&&j==7)) {//����û��
              mapData[i][j]=3;
             }
             else if((i==10&&j==5)||(i==10&&j==6)||(i==10&&j==7)||(i==9&&j==8)||(i==6&&j==7)||(i==5&&j==0)||(i==4&&j==1)||(i==4&&j==2)||(i==4&&j==3)||(i==3&&j==4)||(i==5&&j==6)||(i==4&&j==6)||(i==3&&j==6)||(i==2&&j==6)||(i==1&&j==6)||(i==6&&j==1)||(i==6&&j==2)||(i==6&&j==3)||(i==6&&j==4)||(i==6&&j==5)||(i==5&&j==4)||(i==6&&j==4)||(i==7&&j==4)||(i==9&&j==4)) {
              mapData[i][j]=4;
             }
             else mapData[i][j]=5;
            }
          }
    	//play level13 music on loop
	    try {
	    	playerLevel13.setLoop(true);
	    	playerLevel13.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
	 * Store the information of map23 in a two-dimensional array
	 */
    public void initMapData_23()
    {
    	//1:people on stump, 2:original stump, 3:original plank 4:Where planks may appear 5: null (water)
    	for(int i=0;i<Rows;i++) {
        	for(int j=0;j<Columns;j++) {
        		if(i==12&&j==4) {
        			mapData[i][j]=1;
        		}
        		else if((i==10&&j==0)||(i==10&&j==4)||(i==10&&j==8)||(i==8&&j==0)||(i==8&&j==8)||(i==6&&j==4)||(i==4&&j==0)||(i==4&&j==2)||(i==4&&j==6)||(i==4&&j==8)||(i==2&&j==4)||(i==0&&j==4)) {
        			mapData[i][j]=2;
        		}
        		else if((i==11&&j==4)||(i==9&&j==4)||(i==8&&j==4)||(i==7&&j==4)||(i==4&&j==3)||(i==4&&j==4)||(i==4&&j==5)) {
        			mapData[i][j]=3;
        		}
        		else if((i==10&&j==1)||(i==10&&j==2)||(i==10&&j==3)||(i==10&&j==5)||(i==10&&j==6)||(i==10&&j==7)||(i==9&&j==0)||(i==9&&j==8)||(i==7&&j==0)||(i==6&&j==0)||(i==5&&j==0)||(i==7&&j==8)||(i==6&&j==8)||(i==5&&j==8)||(i==4&&j==1)||(i==4&&j==7)||(i==5&&j==4)||(i==3&&j==4)||(i==1&&j==4)) {
        			mapData[i][j]=4;
        		}
        		else
        			mapData[i][j]=5;
        		}
        	}
    	//play level23 music on loop
	    try {
	    	playerLevel23.setLoop(true);
	    	playerLevel23.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
   	 * Store the relation which is the adjacent stump of each plank in map 3
   	 */
    public void addStump_3(){
    	////call the function in Plank class to init the adjacent stump of a plank
		((Plank)mapGrid[11][4]).addAdjacentStump((Stump)mapGrid[12][4],(Stump)mapGrid[10][4]);
		((Plank)mapGrid[10][3]).addAdjacentStump((Stump)mapGrid[10][2],(Stump)mapGrid[10][4]);
		((Plank)mapGrid[9][2]).addAdjacentStump((Stump)mapGrid[10][2],(Stump)mapGrid[6][2]);
		((Plank)mapGrid[8][2]).addAdjacentStump((Stump)mapGrid[10][2],(Stump)mapGrid[6][2]);
		((Plank)mapGrid[7][2]).addAdjacentStump((Stump)mapGrid[10][2],(Stump)mapGrid[6][2]);
		((Plank)mapGrid[6][1]).addAdjacentStump((Stump)mapGrid[6][0],(Stump)mapGrid[6][2]);
		((Plank)mapGrid[6][3]).addAdjacentStump((Stump)mapGrid[6][2],(Stump)mapGrid[6][6]);
		((Plank)mapGrid[6][4]).addAdjacentStump((Stump)mapGrid[6][2],(Stump)mapGrid[6][6]);
		((Plank)mapGrid[6][5]).addAdjacentStump((Stump)mapGrid[6][2],(Stump)mapGrid[6][6]);
		((Plank)mapGrid[6][7]).addAdjacentStump((Stump)mapGrid[6][6],(Stump)mapGrid[6][8]);
		((Plank)mapGrid[5][6]).addAdjacentStump((Stump)mapGrid[6][6],(Stump)mapGrid[2][6]);
		((Plank)mapGrid[4][6]).addAdjacentStump((Stump)mapGrid[6][6],(Stump)mapGrid[2][6]);
		((Plank)mapGrid[3][6]).addAdjacentStump((Stump)mapGrid[6][6],(Stump)mapGrid[2][6]);
		((Plank)mapGrid[1][6]).addAdjacentStump((Stump)mapGrid[2][6],(Stump)mapGrid[0][6]);
	}
    /**
   	 * Store the relation which is the adjacent stump of each plank in map 13
   	 */
    public void addStump_13()
    {
    	////call the function in Plank class to init the adjacent stump of a plank
		((Plank)mapGrid[11][2]).addAdjacentStump((Stump)mapGrid[12][2],(Stump)mapGrid[8][2]);
		((Plank)mapGrid[10][2]).addAdjacentStump((Stump)mapGrid[12][2],(Stump)mapGrid[8][2]);
		((Plank)mapGrid[9][2]).addAdjacentStump((Stump)mapGrid[12][2],(Stump)mapGrid[8][2]);
		((Plank)mapGrid[7][8]).addAdjacentStump((Stump)mapGrid[8][8],(Stump)mapGrid[6][8]);
		((Plank)mapGrid[9][8]).addAdjacentStump((Stump)mapGrid[8][8],(Stump)mapGrid[10][8]);
		((Plank)mapGrid[10][5]).addAdjacentStump((Stump)mapGrid[10][4],(Stump)mapGrid[10][8]);
		((Plank)mapGrid[10][6]).addAdjacentStump((Stump)mapGrid[10][4],(Stump)mapGrid[10][8]);
		((Plank)mapGrid[10][7]).addAdjacentStump((Stump)mapGrid[10][4],(Stump)mapGrid[10][8]);
		((Plank)mapGrid[6][7]).addAdjacentStump((Stump)mapGrid[6][6],(Stump)mapGrid[6][8]);
		((Plank)mapGrid[5][0]).addAdjacentStump((Stump)mapGrid[6][0],(Stump)mapGrid[4][0]);
		((Plank)mapGrid[4][1]).addAdjacentStump((Stump)mapGrid[4][0],(Stump)mapGrid[4][4]);
		((Plank)mapGrid[4][2]).addAdjacentStump((Stump)mapGrid[4][0],(Stump)mapGrid[4][4]);
		((Plank)mapGrid[4][3]).addAdjacentStump((Stump)mapGrid[4][0],(Stump)mapGrid[4][4]);
		((Plank)mapGrid[3][4]).addAdjacentStump((Stump)mapGrid[4][4],(Stump)mapGrid[2][4]);
		((Plank)mapGrid[5][6]).addAdjacentStump((Stump)mapGrid[6][6],(Stump)mapGrid[0][6]);
		((Plank)mapGrid[4][6]).addAdjacentStump((Stump)mapGrid[6][6],(Stump)mapGrid[0][6]);
		((Plank)mapGrid[3][6]).addAdjacentStump((Stump)mapGrid[6][6],(Stump)mapGrid[0][6]);
		((Plank)mapGrid[2][6]).addAdjacentStump((Stump)mapGrid[6][6],(Stump)mapGrid[0][6]);
		((Plank)mapGrid[1][6]).addAdjacentStump((Stump)mapGrid[6][6],(Stump)mapGrid[0][6]);
		((Plank)mapGrid[8][4]).addHorizontalAdjacentStump((Stump)mapGrid[8][2],(Stump)mapGrid[8][8]);
		((Plank)mapGrid[8][4]).addVerticalAdjacentStump((Stump)mapGrid[10][4],(Stump)mapGrid[4][4]);
		((Plank)mapGrid[8][3]).addAdjacentStump((Stump)mapGrid[8][2],(Stump)mapGrid[8][8]);
		((Plank)mapGrid[8][5]).addAdjacentStump((Stump)mapGrid[8][2],(Stump)mapGrid[8][8]);
		((Plank)mapGrid[8][6]).addAdjacentStump((Stump)mapGrid[8][2],(Stump)mapGrid[8][8]);
		((Plank)mapGrid[8][7]).addAdjacentStump((Stump)mapGrid[8][2],(Stump)mapGrid[8][8]);
		((Plank)mapGrid[6][4]).addHorizontalAdjacentStump((Stump)mapGrid[6][0],(Stump)mapGrid[6][6]);
		((Plank)mapGrid[6][4]).addVerticalAdjacentStump((Stump)mapGrid[10][4],(Stump)mapGrid[4][4]);
		((Plank)mapGrid[6][1]).addAdjacentStump((Stump)mapGrid[6][0],(Stump)mapGrid[6][6]);
		((Plank)mapGrid[6][2]).addAdjacentStump((Stump)mapGrid[6][0],(Stump)mapGrid[6][6]);
		((Plank)mapGrid[6][3]).addAdjacentStump((Stump)mapGrid[6][0],(Stump)mapGrid[6][6]);
		((Plank)mapGrid[6][5]).addAdjacentStump((Stump)mapGrid[6][0],(Stump)mapGrid[6][6]);
		((Plank)mapGrid[5][4]).addAdjacentStump((Stump)mapGrid[4][4],(Stump)mapGrid[10][4]);
		((Plank)mapGrid[7][4]).addAdjacentStump((Stump)mapGrid[4][4],(Stump)mapGrid[10][4]);
		((Plank)mapGrid[9][4]).addAdjacentStump((Stump)mapGrid[4][4],(Stump)mapGrid[10][4]);
    }
    /**
   	 * Store the relation which is the adjacent stump of each plank in map 23
   	 */
    public void addStump_23()
    {
    	//call the function in Plank class to init the adjacent stump of a plank
    	((Plank)mapGrid[11][4]).addAdjacentStump((Stump)mapGrid[12][4],(Stump)mapGrid[10][4]);
		((Plank)mapGrid[10][1]).addAdjacentStump((Stump)mapGrid[10][0],(Stump)mapGrid[10][4]);
		((Plank)mapGrid[10][2]).addAdjacentStump((Stump)mapGrid[10][0],(Stump)mapGrid[10][4]);
		((Plank)mapGrid[10][3]).addAdjacentStump((Stump)mapGrid[10][0],(Stump)mapGrid[10][4]);
		((Plank)mapGrid[10][5]).addAdjacentStump((Stump)mapGrid[10][4],(Stump)mapGrid[10][8]);
		((Plank)mapGrid[10][6]).addAdjacentStump((Stump)mapGrid[10][4],(Stump)mapGrid[10][8]);
		((Plank)mapGrid[10][7]).addAdjacentStump((Stump)mapGrid[10][4],(Stump)mapGrid[10][8]);
		((Plank)mapGrid[9][0]).addAdjacentStump((Stump)mapGrid[10][0],(Stump)mapGrid[8][0]);
		((Plank)mapGrid[9][8]).addAdjacentStump((Stump)mapGrid[8][8],(Stump)mapGrid[10][8]);
		((Plank)mapGrid[7][0]).addAdjacentStump((Stump)mapGrid[8][0],(Stump)mapGrid[4][0]);
		((Plank)mapGrid[6][0]).addAdjacentStump((Stump)mapGrid[8][0],(Stump)mapGrid[4][0]);
		((Plank)mapGrid[5][0]).addAdjacentStump((Stump)mapGrid[8][0],(Stump)mapGrid[4][0]);
		((Plank)mapGrid[7][8]).addAdjacentStump((Stump)mapGrid[4][8],(Stump)mapGrid[8][8]);
		((Plank)mapGrid[6][8]).addAdjacentStump((Stump)mapGrid[4][8],(Stump)mapGrid[8][8]);
		((Plank)mapGrid[5][8]).addAdjacentStump((Stump)mapGrid[4][8],(Stump)mapGrid[8][8]);
		((Plank)mapGrid[4][1]).addAdjacentStump((Stump)mapGrid[4][0],(Stump)mapGrid[4][2]);
		((Plank)mapGrid[4][7]).addAdjacentStump((Stump)mapGrid[4][6],(Stump)mapGrid[4][8]);
		((Plank)mapGrid[5][4]).addAdjacentStump((Stump)mapGrid[6][4],(Stump)mapGrid[2][4]);
		((Plank)mapGrid[3][4]).addAdjacentStump((Stump)mapGrid[6][4],(Stump)mapGrid[2][4]);
		((Plank)mapGrid[4][3]).addAdjacentStump((Stump)mapGrid[4][2],(Stump)mapGrid[4][6]);
		((Plank)mapGrid[4][5]).addAdjacentStump((Stump)mapGrid[4][2],(Stump)mapGrid[4][6]);
		((Plank)mapGrid[4][4]).addHorizontalAdjacentStump((Stump)mapGrid[4][2],(Stump)mapGrid[4][6]);
		((Plank)mapGrid[4][4]).addVerticalAdjacentStump((Stump)mapGrid[6][4],(Stump)mapGrid[2][4]);
		((Plank)mapGrid[7][4]).addAdjacentStump((Stump)mapGrid[6][4],(Stump)mapGrid[10][4]);
		((Plank)mapGrid[8][4]).addAdjacentStump((Stump)mapGrid[6][4],(Stump)mapGrid[10][4]);
		((Plank)mapGrid[9][4]).addAdjacentStump((Stump)mapGrid[6][4],(Stump)mapGrid[10][4]);
		((Plank)mapGrid[1][4]).addAdjacentStump((Stump)mapGrid[0][4],(Stump)mapGrid[2][4]);
    }
    /**
   	 * Store the relation which is the adjacent plank of each plank in map 3
   	 */
    public void addPlank_3(){
    	//call the function in Plank class to init the adjacent planks of a plank
		((Plank)mapGrid[9][2]).addAdjacentPlank((Plank)mapGrid[8][2],(Plank)mapGrid[7][2]);
		((Plank)mapGrid[8][2]).addAdjacentPlank((Plank)mapGrid[9][2],(Plank)mapGrid[7][2]);
		((Plank)mapGrid[7][2]).addAdjacentPlank((Plank)mapGrid[8][2],(Plank)mapGrid[9][2]);
		((Plank)mapGrid[6][3]).addAdjacentPlank((Plank)mapGrid[6][4],(Plank)mapGrid[6][5]);
		((Plank)mapGrid[6][4]).addAdjacentPlank((Plank)mapGrid[6][3],(Plank)mapGrid[6][5]);
		((Plank)mapGrid[6][5]).addAdjacentPlank((Plank)mapGrid[6][4],(Plank)mapGrid[6][3]);
		((Plank)mapGrid[5][6]).addAdjacentPlank((Plank)mapGrid[4][6],(Plank)mapGrid[3][6]);
		((Plank)mapGrid[4][6]).addAdjacentPlank((Plank)mapGrid[5][6],(Plank)mapGrid[3][6]);
		((Plank)mapGrid[3][6]).addAdjacentPlank((Plank)mapGrid[4][6],(Plank)mapGrid[5][6]);
	}
    /**
   	 * Store the relation which is the adjacent plank of each plank in map 13
   	 */
    public void addPlank_13()
    {
    	//call the function in Plank class to init the adjacent planks of a plank
    	((Plank)mapGrid[11][2]).addAdjacentPlank((Plank)mapGrid[10][2],(Plank)mapGrid[9][2]);
        ((Plank)mapGrid[10][2]).addAdjacentPlank((Plank)mapGrid[9][2],(Plank)mapGrid[11][2]);
        ((Plank)mapGrid[9][2]).addAdjacentPlank((Plank)mapGrid[10][2],(Plank)mapGrid[11][2]);
        ((Plank)mapGrid[10][5]).addAdjacentPlank((Plank)mapGrid[10][6],(Plank)mapGrid[10][7]);
        ((Plank)mapGrid[10][6]).addAdjacentPlank((Plank)mapGrid[10][5],(Plank)mapGrid[10][7]);
        ((Plank)mapGrid[10][7]).addAdjacentPlank((Plank)mapGrid[10][5],(Plank)mapGrid[10][6]);
        ((Plank)mapGrid[4][1]).addAdjacentPlank((Plank)mapGrid[4][2],(Plank)mapGrid[4][3]);
        ((Plank)mapGrid[4][2]).addAdjacentPlank((Plank)mapGrid[4][1],(Plank)mapGrid[4][3]);
        ((Plank)mapGrid[4][3]).addAdjacentPlank((Plank)mapGrid[4][1],(Plank)mapGrid[4][2]);
        ((Plank)mapGrid[5][6]).addAdjacentPlank((Plank)mapGrid[4][6],(Plank)mapGrid[3][6],(Plank)mapGrid[2][6],(Plank)mapGrid[1][6]);
        ((Plank)mapGrid[4][6]).addAdjacentPlank((Plank)mapGrid[5][6],(Plank)mapGrid[3][6],(Plank)mapGrid[2][6],(Plank)mapGrid[1][6]);
        ((Plank)mapGrid[3][6]).addAdjacentPlank((Plank)mapGrid[4][6],(Plank)mapGrid[5][6],(Plank)mapGrid[2][6],(Plank)mapGrid[1][6]);
        ((Plank)mapGrid[2][6]).addAdjacentPlank((Plank)mapGrid[3][6],(Plank)mapGrid[4][6],(Plank)mapGrid[5][6],(Plank)mapGrid[1][6]);
        ((Plank)mapGrid[1][6]).addAdjacentPlank((Plank)mapGrid[2][6],(Plank)mapGrid[3][6],(Plank)mapGrid[4][6],(Plank)mapGrid[5][6]);
        ((Plank)mapGrid[8][3]).addAdjacentPlank((Plank)mapGrid[8][4],(Plank)mapGrid[8][5],(Plank)mapGrid[8][6],(Plank)mapGrid[8][7]);
        ((Plank)mapGrid[8][4]).addHorizontalAdjacentPlank((Plank)mapGrid[8][3],(Plank)mapGrid[8][5],(Plank)mapGrid[8][6],(Plank)mapGrid[8][7]);
        ((Plank)mapGrid[8][4]).addVerticalAdjacentPlank((Plank)mapGrid[5][4],(Plank)mapGrid[6][4],(Plank)mapGrid[7][4],(Plank)mapGrid[9][4]);
        ((Plank)mapGrid[8][5]).addAdjacentPlank((Plank)mapGrid[8][4],(Plank)mapGrid[8][3],(Plank)mapGrid[8][6],(Plank)mapGrid[8][7]);
        ((Plank)mapGrid[8][6]).addAdjacentPlank((Plank)mapGrid[8][4],(Plank)mapGrid[8][5],(Plank)mapGrid[8][3],(Plank)mapGrid[8][7]);
        ((Plank)mapGrid[8][7]).addAdjacentPlank((Plank)mapGrid[8][4],(Plank)mapGrid[8][5],(Plank)mapGrid[8][6],(Plank)mapGrid[8][3]);
        ((Plank)mapGrid[6][1]).addAdjacentPlank((Plank)mapGrid[6][2],(Plank)mapGrid[6][3],(Plank)mapGrid[6][4],(Plank)mapGrid[6][5]);
        ((Plank)mapGrid[6][2]).addAdjacentPlank((Plank)mapGrid[6][1],(Plank)mapGrid[6][3],(Plank)mapGrid[6][4],(Plank)mapGrid[6][5]);
        ((Plank)mapGrid[6][3]).addAdjacentPlank((Plank)mapGrid[6][2],(Plank)mapGrid[6][1],(Plank)mapGrid[6][4],(Plank)mapGrid[6][5]);
        ((Plank)mapGrid[6][4]).addHorizontalAdjacentPlank((Plank)mapGrid[6][1],(Plank)mapGrid[6][2],(Plank)mapGrid[6][3],(Plank)mapGrid[6][5]);
        ((Plank)mapGrid[6][4]).addVerticalAdjacentPlank((Plank)mapGrid[5][4],(Plank)mapGrid[7][4],(Plank)mapGrid[8][4],(Plank)mapGrid[9][4]);
        ((Plank)mapGrid[6][5]).addAdjacentPlank((Plank)mapGrid[6][2],(Plank)mapGrid[6][3],(Plank)mapGrid[6][4],(Plank)mapGrid[6][1]);
        ((Plank)mapGrid[5][4]).addAdjacentPlank((Plank)mapGrid[6][4],(Plank)mapGrid[7][4],(Plank)mapGrid[8][4],(Plank)mapGrid[9][4]);
        ((Plank)mapGrid[7][4]).addAdjacentPlank((Plank)mapGrid[6][4],(Plank)mapGrid[5][4],(Plank)mapGrid[8][4],(Plank)mapGrid[9][4]);
        ((Plank)mapGrid[8][4]).addAdjacentPlank((Plank)mapGrid[6][4],(Plank)mapGrid[7][4],(Plank)mapGrid[5][4],(Plank)mapGrid[9][4]);
        ((Plank)mapGrid[9][4]).addAdjacentPlank((Plank)mapGrid[6][4],(Plank)mapGrid[7][4],(Plank)mapGrid[8][4],(Plank)mapGrid[5][4]);
        
    }
    /**
   	 * Store the relation which is the adjacent plank of each plank in map 23
   	 */
    public void addPlank_23() 
    {
    	//call the function in Plank class to init the adjacent planks of a plank
    	((Plank)mapGrid[10][1]).addAdjacentPlank((Plank)mapGrid[10][2],(Plank)mapGrid[10][3]);
    	((Plank)mapGrid[10][2]).addAdjacentPlank((Plank)mapGrid[10][1],(Plank)mapGrid[10][3]);
    	((Plank)mapGrid[10][3]).addAdjacentPlank((Plank)mapGrid[10][1],(Plank)mapGrid[10][2]);
    	((Plank)mapGrid[10][5]).addAdjacentPlank((Plank)mapGrid[10][6],(Plank)mapGrid[10][7]);
    	((Plank)mapGrid[10][6]).addAdjacentPlank((Plank)mapGrid[10][5],(Plank)mapGrid[10][7]);
    	((Plank)mapGrid[10][7]).addAdjacentPlank((Plank)mapGrid[10][6],(Plank)mapGrid[10][5]);
    	((Plank)mapGrid[9][4]).addAdjacentPlank((Plank)mapGrid[8][4],(Plank)mapGrid[7][4]);
    	((Plank)mapGrid[8][4]).addAdjacentPlank((Plank)mapGrid[9][4],(Plank)mapGrid[7][4]);
    	((Plank)mapGrid[7][4]).addAdjacentPlank((Plank)mapGrid[9][4],(Plank)mapGrid[8][4]);
    	((Plank)mapGrid[7][0]).addAdjacentPlank((Plank)mapGrid[6][0],(Plank)mapGrid[5][0]);
    	((Plank)mapGrid[6][0]).addAdjacentPlank((Plank)mapGrid[7][0],(Plank)mapGrid[5][0]);
    	((Plank)mapGrid[5][0]).addAdjacentPlank((Plank)mapGrid[6][0],(Plank)mapGrid[7][0]);
    	((Plank)mapGrid[7][8]).addAdjacentPlank((Plank)mapGrid[6][8],(Plank)mapGrid[5][8]);
    	((Plank)mapGrid[6][8]).addAdjacentPlank((Plank)mapGrid[7][8],(Plank)mapGrid[5][8]);
    	((Plank)mapGrid[5][8]).addAdjacentPlank((Plank)mapGrid[6][8],(Plank)mapGrid[7][8]);	
    	((Plank)mapGrid[4][3]).addAdjacentPlank((Plank)mapGrid[4][4],(Plank)mapGrid[4][5]);
    	((Plank)mapGrid[4][5]).addAdjacentPlank((Plank)mapGrid[4][4],(Plank)mapGrid[4][3]);
    	((Plank)mapGrid[4][4]).addHorizontalAdjacentPlank((Plank)mapGrid[4][3],(Plank)mapGrid[4][5]);
    	((Plank)mapGrid[4][4]).addVerticalAdjacentPlank((Plank)mapGrid[3][4],(Plank)mapGrid[5][4]);
    	((Plank)mapGrid[3][4]).addAdjacentPlank((Plank)mapGrid[4][4],(Plank)mapGrid[5][4]);
    	((Plank)mapGrid[5][4]).addAdjacentPlank((Plank)mapGrid[4][4],(Plank)mapGrid[3][4]);
    }
    /**
     * set the type of current plank
     * @param curPlank the given plank in specific size
     */
    public void setCurrentPlank(int curPlank)
    {
    	plankMusicEffect.play();//Play plankMusicEffect music when click or put the plank
    	currentPlank=curPlank;
    }
    /**
     * get the type currentPlank
     * @return An integer represent the type of currentPlank
     */
    public int getCurrentPlank(){
        return currentPlank;
    }
    /**
   	 * get the x position of the people
   	 */
    public int getPersonX(){
        return personX;
    }
    /**
   	 * get the y position of the people
   	 */
    public int getPersonY(){
        return personY;
    }
    /**
     * @param x The given x postion
   	 * set the x position of the people
   	 */
	public void setPersonX(int x)
	{
		personX=x;
		return;
	}
	/**
	 * @param y The given y postion
   	 * set the y position of the people
   	 */
	public void setPersonY(int y)
	{
		personY=y;
		return;
	}
	/**
   	 * get a specific button
   	 * @return return a button
   	 */
	public JButton getGrid(int x, int y)
	{
		return mapGrid[y][x];
	}
	/**
   	 * initialize the function panel by adding the label array and a button
   	 */
	public void addLabel()
	{
		//Because every label has its own character and position, it is possible to list them clearly
		label[0]=new JLabel();
		label[0].setHorizontalAlignment(SwingConstants.CENTER);
		label[0].setFont(new Font("Arial",Font.BOLD,20));
		label[0].setPreferredSize(new Dimension(200,75));
		label[0].setBorder(BorderFactory.createLineBorder(Color.black, 3));
		functionPanel.add(label[0]);
		label[1]=new JLabel("Timer",JLabel.CENTER);
		label[1].setFont(new Font("Arial",Font.BOLD,20));
		label[1].setPreferredSize(new Dimension(200,75));
		label[1].setBorder(BorderFactory.createLineBorder(Color.black, 3));
		functionPanel.add(label[1]);
		label[2]=new Timer();
		label[2].setPreferredSize(new Dimension(200,75));
		label[2].setBorder(BorderFactory.createLineBorder(Color.black, 3));
		functionPanel.add(label[2]);
		label[3]=new JLabel("Shortest Time",JLabel.CENTER);
		label[3].setFont(new Font("Arial",Font.BOLD,20));
		label[3].setPreferredSize(new Dimension(200,75));
		label[3].setBorder(BorderFactory.createLineBorder(Color.black, 3));
		functionPanel.add(label[3]);
		shortestTime=new ShortestTimeLabel();
		shortestTime.setPreferredSize(new Dimension(200,75));
		shortestTime.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		//Based on different level, show the different level label and the shortest time for each level
		if(currentlevel==1) {
			label[0].setText("Level 3");
			shortestTime.setText(shortestTime.toString(level3minimum));
		}
		else if(currentlevel==2){
			label[0].setText("Level 13");
			shortestTime.setText(shortestTime.toString(level13minimum));
		}
		else if(currentlevel==3){
			label[0].setText("Level 23");
			shortestTime.setText(shortestTime.toString(level23minimum));
		}
		shortestTime.setHorizontalAlignment(SwingConstants.CENTER);
		shortestTime.setFont(new Font("Arial",Font.BOLD,20));
		functionPanel.add(shortestTime);
		label[4]=new JLabel("The current plank",JLabel.CENTER);
		label[4].setFont(new Font("Arial",Font.BOLD,20));
		label[4].setPreferredSize(new Dimension(200,75));
		label[4].setBorder(BorderFactory.createLineBorder(Color.black, 3));
		functionPanel.add(label[4]);
		label[5]=new JLabel();
		label[5].setPreferredSize(new Dimension(200,75));
		functionPanel.add(label[5]);
		label[5].setBorder(BorderFactory.createLineBorder(Color.black, 3));
		backButton=new JButton("Quit");
		backButton.setPreferredSize(new Dimension(200,75));
		backButton.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		backButton.setFont(new Font("Arial",Font.BOLD,20));
		backButton.addActionListener(this);
		functionPanel.add(backButton);
	}
	/**
   	 * get a timer label
   	 * @return return a label whose type is a label
   	 */
	public Timer getTimer() 
	{
		return (Timer)label[2];
	}

	public JLabel getPlankLabel(){
    	return label[5];
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof Stump)
		{
			Stump target = (Stump)e.getSource();
			target.clickEvent(this);
		}
		else if (e.getSource() instanceof Plank)
		{
			Plank target = (Plank)e.getSource();
			target.click(this);
		}
		else if(e.getSource()== backButton) {
			playerLevel3.over();//Turn off Level3 music
			playerLevel13.over();//Turn off Level13 music
			playerLevel23.over();//Turn off Level23 music
			this.dispose();
			new End();//Click "Quit" to return to the End page, click restart on the End page to enter the Menu 
		}
	}

}
