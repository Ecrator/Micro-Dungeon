import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatVX extends JPanel{
    
    int wholeValue=3;
    int coins;
    JLabel coinCount=new JLabel("x 0");
    ArrayList<JPanel> units=new ArrayList<>();

    public StatVX(String type){
        if(type.equals("HEALTH")||type.equals("ARMOR")){
            this.setLayout(new GridLayout(1,3));
            switch(type){
                case "HEALTH":
                for(int i=0;i<3;i++){
                    units.add(new JPanel());
                    units.get(i).setBackground(Color.RED);
                    this.add(units.get(i));
                }
                break;
                case "ARMOR":
                for(int i=0;i<3;i++){
                    units.add(new JPanel());
                    units.get(i).setBackground(Color.LIGHT_GRAY);
                }
            }
        }else{ //Coin type
            this.setLayout(new GridLayout(1,2));
            units.add(new JPanel());
            units.get(0).setBackground(Color.YELLOW);
            this.add(units.get(0));
            this.add(coinCount);
        }
    }

    public void updateHealth(Double health){
        int count=0;
        for(JPanel unit: units){
            this.remove(unit);
        }
        if(health>2){
            count=3;
        }else if(health>1){
            count=2;
        }else if(health>0){
            count=1;
        }
        for(int i=0;i<count;i++){
            this.add(units.get(i));
        }
    }
}
