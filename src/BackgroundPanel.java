
 import java.awt.*;  
 import javax.swing.JPanel;  
/**
 * BackgroundPanel extends from JPanel
 * This Jpanel can add background to it
 */
 public class BackgroundPanel extends JPanel {  
       
    private static final long serialVersionUID = -6352788025440244338L;  
       
    private Image image = null;  
   
    public BackgroundPanel(Image image) {  
        this.image = image;  
    }   
    protected void paintComponent(Graphics g) {  
         g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
    }  
}
 

