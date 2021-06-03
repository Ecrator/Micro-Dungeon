import javax.swing.*;
import java.awt.*;

public class StatVX extends JLabel{
    
    int coins=0;
    JLabel coinCount=new JLabel("              x 0");
    JLabel armorCount=new JLabel("                "+"0");
    JLabel healthCount=new JLabel("                "+"3");

    public StatVX(String type){
        if(type.equals("HEALTH")||type.equals("ARMOR")){
            switch(type){
                case "HEALTH":
                this.setLayout(new GridLayout(1,1));
                this.add(healthCount);
                ImageIcon Hicon=new ImageIcon(getClass().getResource("Sprites\\heart.png"));
                this.setIcon(Hicon);
                break;
                case "ARMOR":
                this.setLayout(new GridLayout(1,1));
                this.add(armorCount);
                ImageIcon Aicon=new ImageIcon(getClass().getResource("Sprites\\shield.png"));
                this.setIcon(Aicon);
            }
        }else{ //Coin type
            this.setLayout(new GridLayout(1,1));
            this.add(coinCount);
            ImageIcon Cicon=new ImageIcon(getClass().getResource("Sprites\\coin.png"));
                this.setIcon(Cicon);
        }
    }

    public void updateHealth(Double health){
        this.remove(healthCount);
        String space="                 ";
        for(int i=0;i<(String.valueOf(health)).length();i++){
            space=space.replaceFirst(" ", "");
        }
        healthCount.setText(space+String.valueOf(health));
        this.add(healthCount);
    }

    public void updateCoins(){
         this.remove(coinCount);
         String space="               ";
        for(int i=0;i<(String.valueOf(coins)).length();i++){
            space=space.replaceFirst(" ", "");
        }
         coinCount.setText(space+"x "+coins);
         this.add(coinCount);
    }

    public void updateArmor(Double armor){
        this.remove(armorCount);
        if(armor>0.50){
            armorCount.setText("                3");
        }else if(armor>0.25){
            armorCount.setText("                2");
        }else if(armor>0.00){
            armorCount.setText("                1");
        }
        this.add(armorCount);
    }
}
