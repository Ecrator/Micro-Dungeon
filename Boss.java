import javax.swing.*;
import java.util.Random;
import java.awt.*;

public class Boss extends JPanel{
    
    Random random=new Random();
    int bossX=random.nextInt(140)+120;
    int bossY=random.nextInt(140)+120;
    int bossHealth=300;
    public Boss(){
        this.setSize(50,50);
        this.setBackground(Color.CYAN);
        this.setLocation(bossX, bossY);
    }

    public void moveBoss(int playerX, int playerY){
        this.setSize(new Dimension(50,50));
        switch(random.nextInt(2)+1){
            case 1:
            if(playerX<bossX){
                bossX-=15;
                this.setLocation(bossX, bossY);
            }else{
                bossX+=15;
                this.setLocation(bossX, bossY);
            }
            break;
            case 2:
            if(playerY<bossY){
                bossY-=15;
                this.setLocation(bossX, bossY);
            }else{
                bossY+=15;
                this.setLocation(bossX, bossY);
            }
        }
    }

    public void playerAttackedSword(int damage, int playerX, int playerY){
        if(!(bossX<playerX-60||bossX>playerX+30||bossY<playerY-60||bossY>playerY+30)){
            bossHealth-=damage;
            if(bossX>playerX){
                this.setLocation(bossX+15, bossY);
                bossX+=15;
            }else{
                this.setLocation(bossX-15, bossY);
                bossX-=15;
            }

            if(bossY>playerY){
                this.setLocation(bossX, bossY+15);
                bossY+=15;
            }else{
                this.setLocation(bossX, bossY-15);
                bossY-=15;
            }
        }
    }

    public void playerAttackedBow(int playerX, int playerY, int damage){
        if(bossX>playerX-50&&bossX<playerX+10||bossY>playerY-55&&bossY<playerY+10){
            bossHealth-=damage;
            if(bossX>playerX){
                bossX+=10;
                this.setLocation(bossX, bossY);
            }else{
                bossX-=10;
                this.setLocation(bossX, bossY);
            }
            if(bossY>playerY){
                bossY+=10;
                this.setLocation(bossX, bossY);
            }else{
                bossY-=10;
                this.setLocation(bossX, bossY);
            }
        }
    }

    public boolean attemptAttack(int playerX, int playerY){
        if(!(bossX<playerX-50||bossX>playerX+10||bossY<playerY-50||bossY>playerY+10)){
            return true;
        }
        return false;
    }

    public boolean checkDead(){
        if(bossHealth<=0){
            return true;
        }
        return false;
    }
}
