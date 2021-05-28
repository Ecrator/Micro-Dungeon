import javax.swing.*;
import java.awt.*;

public class MapGraph extends JPanel{

    String directions;

    public MapGraph(String Idirections){
        directions=Idirections;
        this.setOpaque(false);
        //this.setBackground(Color.GRAY);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.WHITE);
        g2d.setStroke(new BasicStroke(10));
        //g2d.fillRect(25,0,19,19);

        switch(getFirstDirection(directions)){
            case "NORTH": g2d.drawLine(25,25,25,50); 
            break;
            case "EAST": g2d.drawLine(0,25,25,25); 
            break;
            case "SOUTH": g2d.drawLine(25,0,25,25); 
            break;
            case "WEST": g2d.drawLine(25,25,50,25); 
        }

        switch(getSecondDirection(directions)){
            case "NORTH": g2d.drawLine(25,0,25,25); 
            break;
            case "EAST": g2d.drawLine(25,25,50,25); 
            break;
            case "SOUTH": g2d.drawLine(25,25,25,50); 
            break;
            case "WEST": g2d.drawLine(0,25,25,25);
        }
    }

    public String getFirstDirection(String directions){
        return directions.split(",")[0];
    }
    public String getSecondDirection(String directions){
        return directions.split(",")[1];
    }
}
