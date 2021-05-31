import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatVX extends JPanel{
    
    int coins=0;
    JLabel coinCount=new JLabel("x 0");
    JLabel armorCount=new JLabel("0");
    JLabel healthCount=new JLabel("3");
    ArrayList<JPanel> units=new ArrayList<>();

    public StatVX(String type){
        if(type.equals("HEALTH")||type.equals("ARMOR")){
            switch(type){
                case "HEALTH":
                this.setLayout(new FlowLayout());
                this.setBackground(Color.RED);
                this.add(healthCount);
                break;
                case "ARMOR":
                this.setLayout(new FlowLayout());
                this.setBackground(Color.LIGHT_GRAY);
                this.add(armorCount);
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
     healthCount.setText(String.valueOf(health));
    }

    public void updateCoins(){
        this.remove(coinCount);
         coinCount.setText("x "+coins);
         this.add(coinCount);
    }

    public void updateArmor(Double armor){
        if(armor>0.50){
            armorCount.setText("3");
        }else if(armor>0.25){
            armorCount.setText("2");
        }else if(armor>0.00){
            armorCount.setText("1");
        }
    }
}
