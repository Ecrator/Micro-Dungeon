import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy extends JPanel{
    
    Random random=new Random();
    int enemyX=random.nextInt(160)+120;
    int enemyY=random.nextInt(160)+120;
    int health;

    public Enemy(){
        switch(random.nextInt(2)+1){
            case 1:
            this.setSize(new Dimension(10,10));
            this.setBackground(Color.PINK);
            this.setLocation(enemyX, enemyY);
            health=10;
            break;
            case 2:
            this.setSize(new Dimension(10,10));
            this.setBackground(Color.MAGENTA);
            this.setLocation(enemyX, enemyY);
            health=20;
        }
    }

    public void moveEnemy(int playerX, int playerY){
        switch(random.nextInt(2)+1){
            case 1:
            if(playerX<enemyX){
                enemyX-=10;
                this.setLocation(enemyX, enemyY);
            }else{
                enemyX+=10;
                this.setLocation(enemyX, enemyY);
            }
            break;
            case 2:
            if(playerY<enemyY){
                enemyY-=10;
                this.setLocation(enemyX, enemyY);
            }else{
                enemyY+=10;
                this.setLocation(enemyX, enemyY);
            }
        }
    }

    public void playerAttackedSword(int damage, int playerX, int playerY){
        if(!(enemyX<playerX-30||enemyX>playerX+30||enemyY<playerY-30||enemyY>playerY+30)){
            health-=damage;
            if(enemyX>playerX){
                this.setLocation(enemyX+30, enemyY);
                enemyX+=30;
            }else{
                this.setLocation(enemyX-30, enemyY);
                enemyX-=30;
            }

            if(enemyY>playerY){
                this.setLocation(enemyX, enemyY+30);
                enemyY+=30;
            }else{
                this.setLocation(enemyX, enemyY-30);
                enemyY-=30;
            }
        }
    }

    public boolean checkDead(){
        if(health<=0){
            return true;
        }
        return false;
    }

    public boolean attemptAttack(int playerX, int playerY){
        if(!(enemyX<playerX-20||enemyX>playerX+20||enemyY<playerY-20||enemyY>playerY+20)){
            return true;
        }
        return false;
    }
}
