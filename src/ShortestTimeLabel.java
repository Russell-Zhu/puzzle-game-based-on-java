
import javax.swing.*;

/**
 * the label used to display the shortest time
 */
public class ShortestTimeLabel extends JLabel {
    private long hour;
    private long minute;
    private long second;

    /**
     * Convert the time to hour:minute:second format
     * @param time The shortest time
     */
    public String toString(long time){
        hour=time/60/60;
        minute=time/60%60;
        second=time%60;
        String str1=String.format("%02d", hour);
        String str2=String.format("%02d", minute);
        String str3=String.format("%02d", second);
        return (str1+":"+str2+":"+str3);
    }
}
