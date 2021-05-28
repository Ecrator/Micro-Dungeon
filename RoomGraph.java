import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class RoomGraph extends JPanel{

    ArrayList<String> doors;
    public RoomGraph(){
        this.setOpaque(false);
    }

    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.setPaint(Color.ORANGE);
        g2d.setStroke(new BasicStroke(10));
        for(int i=0;i<doors.size();i++){
            switch(doors.get(i)){
                case "NORTH": 
                if(i%2!=0){ //going
                    g2d.drawLine(205,40,165,40);
                }else{ //coming
                    g2d.drawLine(205,310,165,310);
                }
                continue;
                case "EAST":
                if(i%2==0){ //going
                    g2d.drawLine(40,185,40,145);
                }else{ //coming
                    g2d.drawLine(330,185,330,145);
                }
                continue;
                case "SOUTH":
                if(i%2!=0){ //going
                    g2d.drawLine(205,310,165,310);
                }else{ //coming
                    g2d.drawLine(205,40,165,40);
                }
                continue;
                case "WEST":
                if(i%2==0){ //going
                    g2d.drawLine(330,185,330,145);
                }else{ //coming
                    g2d.drawLine(40,185,40,145);
                }
            }
        }
        g2d.setPaint(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(75));
        g2d.drawLine(0,0,0,400);
        g2d.drawLine(0,0,400,0);
        g2d.drawLine(370,370,370,0);
        g2d.drawLine(350,350,0,350);
    }

    public void updateDoors(ArrayList<String> Idoors){
        doors=Idoors;
        this.repaint();
    }
}
