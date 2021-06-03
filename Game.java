import javax.swing.*;
import java.awt.*;

public class Game {
    
    JFrame Map=new JFrame("Map");
    JPanel[][] slots=new JPanel[10][10];
    public Game() throws InterruptedException{
        Map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Map.setSize(500,500);
        Map.setResizable(false);
        Map.setLocation(900,150);
        Map.setLayout(new GridLayout(10,10));
        for(int i=9;i>-1;i--){
            for(int o=0;o<10;o++){
                slots[o][i]=new JPanel();
                slots[o][i].setBackground(Color.GRAY);
                slots[o][i].setLayout(new BorderLayout());
                Map.add(slots[o][i]);
            }
        }
        Map.setVisible(true);
        MapGenerator mapGen=new MapGenerator(slots, Map);
    }

    public JPanel[][] getSlots(){
        return slots;
    }
}