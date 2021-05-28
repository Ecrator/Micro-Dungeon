import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy extends JPanel{
    
    int playerX;
    int playerY;
    Random random=new Random();
    int enemyX=random.nextInt(160)+120;
    int enemyY=random.nextInt(160)+120;

    public Enemy(){
        this.setSize(10,10);
        this.setBackground(Color.PINK);
        this.setLocation(enemyX, enemyY);
    }
}
