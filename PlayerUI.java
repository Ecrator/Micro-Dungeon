import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayerUI extends JFrame implements KeyListener{
    
    ArrayList<String> orderedSlots;
    ArrayList<String> orderedSlotDirections;
    String roomSlot="4,4";
    String shopSlot;
    JPanel[][] Dslots;
    RoomGraph playerInterface=new RoomGraph();
    JPanel Sinterface=new JPanel();
    HotBar Binterface=new HotBar();
    JPanel player=new JPanel();
    int playerX=200;
    int playerY=200;
    Double playerHealth=3.00;
    StatVX healthVX=new StatVX("HEALTH");
    StatVX armorVX=new StatVX("ARMOR");
    StatVX coinVX=new StatVX("COIN");
    Double armor=0.00;
    char playerDirection='w';
    boolean inShopRoom=false;
    boolean inBossRoom=false;
    boolean helmetE, bootsE, shieldE=false;
    ArrayList<Enemy> enemies=new ArrayList<>();
    Boss boss=new Boss();
    JProgressBar bossBar=new JProgressBar(0,300);
    Random random=new Random();
    AttackVX attackVisual=new AttackVX();
    Shop shop=new Shop(Binterface, this);
    JPanel shopVX=new JPanel();
    JFrame map;
    EndScreen endScreen;

    public PlayerUI(ArrayList<String> IorderedSlots, ArrayList<String> IorderedSlotDirections, JPanel[][] IDslots, String IshopSlot, JFrame Imap) throws InterruptedException{

        map=Imap;
        orderedSlots=IorderedSlots;
        orderedSlotDirections=IorderedSlotDirections;
        Dslots=IDslots;
        shopSlot=IshopSlot;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setResizable(false);
        this.addKeyListener(this);
        this.setLocation(200,150);
        bossBar.setStringPainted(true);
        bossBar.setValue(300);
        bossBar.setBounds(90,330,180,20);
        bossBar.setForeground(Color.MAGENTA);
        player.setSize(new Dimension(10,10));
        shopVX.setSize(new Dimension(50,50));
        Sinterface.setPreferredSize(new Dimension(100,500));
        Binterface.setPreferredSize(new Dimension(500,100));
        player.setBackground(Color.GREEN);
        Sinterface.setBackground(Color.GRAY);
        Binterface.setBackground(Color.GRAY);
        shopVX.setBackground(Color.BLUE);
        playerInterface.setBackground(Color.DARK_GRAY);
        player.setLocation(200,200);
        shopVX.setLocation(160,145);
        playerInterface.setLayout(null);
        Sinterface.setLayout(new GridLayout(3,1,1,1));
        updateRoom();
        this.add(playerInterface, BorderLayout.CENTER);
        this.add(Sinterface, BorderLayout.EAST);
        this.add(Binterface, BorderLayout.SOUTH);
        playerInterface.add(bossBar);
        Sinterface.add(healthVX);
        Sinterface.add(armorVX);
        Sinterface.add(coinVX);
        playerInterface.add(player);   
        this.revalidate();
        this.setVisible(true);
    }

    public void updateRoom(String roomDirection){
        shop.setVisible(false);
        try{
            for(Enemy enemy: enemies){
                this.remove(enemy);
                enemies.remove(enemy);
            }
        }catch(Exception x){}
        
        switch(roomDirection){
            case "NORTH":
            roomSlot=roomSlot.split(",")[0]+","+String.valueOf(Integer.valueOf(roomSlot.split(",")[1])+1);
            break;
            case "EAST":
            roomSlot=String.valueOf(Integer.valueOf(roomSlot.split(",")[0])+1)+","+roomSlot.split(",")[1];
            break;
            case "SOUTH":
            roomSlot=roomSlot.split(",")[0]+","+String.valueOf(Integer.valueOf(roomSlot.split(",")[1])-1);
            break;
            case "WEST":
            roomSlot=String.valueOf(Integer.valueOf(roomSlot.split(",")[0])-1)+","+roomSlot.split(",")[1];
        }
        ArrayList<String> doors=new ArrayList<>();
        for(int i=0;i<orderedSlots.size()-1;i++){
            if(orderedSlots.get(i).equals(roomSlot)){
                doors.add(orderedSlotDirections.get(i).split(",")[0]);
                doors.add(orderedSlotDirections.get(i).split(",")[1]);
            }
        }
        if(!(roomSlot.equals("4,4"))&&!(roomSlot.equals(orderedSlots.get(orderedSlots.size()-1)))){
            for(int i=0;i<random.nextInt(5)+0;i++){
                enemies.add(new Enemy());
            }
        }
        if(roomSlot.equals(shopSlot)){
            inShopRoom=true;
            this.add(shopVX);
        }else{
            inShopRoom=false;
            try{
            this.remove(shopVX);
            }catch(Exception x){}
        }
        if(roomSlot.equals(orderedSlots.get(orderedSlots.size()-1))){
            inBossRoom=true;
            bossBar.setVisible(true);
            bossBar.setValue(boss.bossHealth);
            this.add(boss);
        }else{
            inBossRoom=false;
            try{
                bossBar.setVisible(false);
                this.remove(boss);
            }catch(Exception x){}
            boss.bossHealth=300;
        }
        for(Enemy enemy: enemies){
            this.add(enemy);
        }
        playerInterface.updateDoors(doors);
    }
    public void updateRoom(){
        updateRoom("NULL");
    }

    public boolean checkRoomDirections(String direction){
        for(int i=0;i<orderedSlots.size()-1;i++){
            if(roomSlot.equals(orderedSlots.get(i))){
                if(orderedSlotDirections.get(i).split(",")[1].equals(direction)){
                    return true;
                }
            }
        }
        if(checkReversedRoomDirections(direction)){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkReversedRoomDirections(String direction){
        String reversedDirection="NULL";
        switch(direction){
            case "NORTH": reversedDirection="SOUTH";
            break;
            case "EAST": reversedDirection="WEST";
            break;
            case "SOUTH": reversedDirection="NORTH";
            break;
            case "WEST": reversedDirection="EAST";
        }

        for(int i=0;i<orderedSlots.size()-1;i++){
            if(roomSlot.equals(orderedSlots.get(i))){
                if(orderedSlotDirections.get(i).split(",")[0].equals(reversedDirection)){
                    return true;
                }
            }
        }
        return false;
    }

    public void movePlayer(char direction){
        player.setBackground(Color.GREEN);
        try{
            switch(direction){
                case 'w':
                player.setLocation(playerX, playerY-20);
                playerY-=20;
                playerDirection='w';
                break;
                case 'a':
                player.setLocation(playerX-20, playerY);
                playerX-=20;
                playerDirection='a';
                break;
                case 's':
                player.setLocation(playerX, playerY+20);
                playerY+=20;
                playerDirection='s';
                break;
                case 'd':
                player.setLocation(playerX+20, playerY);
                playerX+=20;
                playerDirection='d';
            }
        }catch(Exception x){}

        try{
            this.remove(attackVisual);
            this.repaint();
        }catch(Exception x){}
        for(Enemy enemy: enemies){
            enemy.moveEnemy(playerX, playerY);
            if(enemy.attemptAttack(playerX, playerY)){
                playerAttacked();
            }
        }
        if(inBossRoom){
            boss.moveBoss(playerX, playerY);
            if(boss.attemptAttack(playerX, playerY)){
                playerAttacked();
            }
        }
    }

    public void playerAttacked(){
        player.setBackground(Color.RED);
        playerHealth-=1-armor;
        healthVX.updateHealth(playerHealth);
        if(playerHealth<=0){
            endScreen=new EndScreen("BAD");
            gameOver();
        }
    }

    public void gameOver(){
        System.out.println("Boss Has Been Defeated!");
        this.setVisible(false);
        map.setVisible(false);
        endScreen.setVisible(true);
        endScreen.setLocation(this.getLocation());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'w': 
            if(playerY>50){
                movePlayer('w');
            }
            if(playerX==180&&playerY==40){
                if(checkRoomDirections("NORTH")){
                    updateRoom("NORTH");
                    playerY+=240;
                    player.setLocation(playerX, playerY);
                }
            }
            break;
            case 'a': 
            if(playerX>50){
                 movePlayer('a');  
            }
            if(playerX==40&&playerY==160){
                 if(checkRoomDirections("WEST")){
                 updateRoom("WEST");
                 playerX+=260;
                 player.setLocation(playerX, playerY);
             }
            }
            break;
            case 's': 
            if(playerY<300){
                movePlayer('s');
            }
            if(playerX==180&&playerY==300){
                if(checkRoomDirections("SOUTH")){
                 updateRoom("SOUTH");
                 playerY-=240;
                 player.setLocation(playerX, playerY);
             }
            }
            break;
            case 'd': 
            if(playerX<320){
                movePlayer('d');
            }
            if(playerX==320&&playerY==160){
                if(checkRoomDirections("EAST")){
                    updateRoom("EAST");
                    playerX-=260;
                    player.setLocation(playerX, playerY);
             }
            }
            break;
            case 'c': //System.out.println(roomSlot);
                    //System.out.println(orderedSlots);       //helps debug
                    //System.out.println(orderedSlotDirections);
                    //System.out.println(enemies.size());
                    //System.out.println(playerHealth);
                    //System.out.println(shopSlot);
                    //System.out.println(armor);
                    System.out.println(boss.bossHealth);

            break;
            case 'e': Binterface.slot.get(Binterface.equiped).Use();
                    if(Binterface.slot.get(Binterface.equiped).Item.equals("SWORD")||Binterface.slot.get(Binterface.equiped).Item.equals("MACE")){
                        this.remove(attackVisual);
                        attackVisual=new AttackVX("SWORD");
                        this.add(attackVisual);
                        attackVisual.setLocation(playerX-15, playerY-15);
                      for(Enemy enemy: enemies){
                          enemy.playerAttackedSword(Binterface.slot.get(Binterface.equiped).damage, playerX, playerY);
                      }
                      if(inBossRoom){
                          boss.playerAttackedSword(Binterface.slot.get(Binterface.equiped).damage, playerX, playerY);
                          bossBar.setValue(boss.bossHealth);
                          if(boss.checkDead()){
                            endScreen=new EndScreen("GOOD");
                            gameOver();
                          }
                      }
                    }else if(Binterface.slot.get(Binterface.equiped).Item.equals("BOW")){
                        for(Enemy enemy: enemies){
                            enemy.playerAttackedBow(playerX, playerY, Binterface.slot.get(Binterface.equiped).damage);
                        }
                        if(inBossRoom){
                            boss.playerAttackedBow(playerX, playerY, Binterface.slot.get(Binterface.equiped).damage);
                            bossBar.setValue(boss.bossHealth);
                            if(boss.checkDead()){
                                endScreen=new EndScreen("GOOD");
                                gameOver();
                              }
                        }
                    }else if(Binterface.slot.get(Binterface.equiped).Item.equals("BOOTS")||Binterface.slot.get(Binterface.equiped).Item.equals("HELMET")||Binterface.slot.get(Binterface.equiped).Item.equals("SHIELD")){
                        if(Binterface.slot.get(Binterface.equiped).Item.equals("BOOTS")&&(bootsE)){
                            break;
                        }else if((Binterface.slot.get(Binterface.equiped).Item.equals("HELMET")&&(helmetE))){
                            break;
                        }else if((Binterface.slot.get(Binterface.equiped).Item.equals("SHIELD")&&(shieldE))){
                            break;
                        }else{
                            if(Binterface.slot.get(Binterface.equiped).Item.equals("BOOTS")){
                                bootsE=true;
                            }else if(Binterface.slot.get(Binterface.equiped).Item.equals("HELMET")){
                                helmetE=true;
                            }else if(Binterface.slot.get(Binterface.equiped).Item.equals("SHIELD")){
                                shieldE=true;
                            }
                            armor+=0.25;
                            armorVX.updateArmor(armor);
                            Binterface.slot.get(Binterface.equiped).setItem("NULL");
                            Binterface.slot.get(Binterface.equiped).removeAll();
                            Binterface.repaint();
                        }
                        
                    }else if(Binterface.slot.get(Binterface.equiped).Item.equals("BANDAGE")){
                        playerHealth+=1;
                        healthVX.updateHealth(playerHealth);
                        healthVX.repaint();
                        Binterface.slot.get(Binterface.equiped).setItem("NULL");
                        Binterface.slot.get(Binterface.equiped).removeAll();
                        Binterface.repaint();
                    }
                    
                    try{
                        for(Enemy enemy: enemies){
                            if(enemy.checkDead()){
                                enemies.remove(enemy);
                                this.remove(enemy);
                                coinVX.coins+=1;
                                coinVX.updateCoins();
                                this.remove(attackVisual);
                            }
                        }
                    }catch(Exception x){}
            break;
            case 'q': Binterface.slot.get(Binterface.equiped).setItem("NULL");
                      Binterface.slot.get(Binterface.equiped).removeAll();
                      Binterface.repaint();
                      System.out.println("You dropped your Item because your're clumsy.");
            break;
            case 'i': 
            if(inShopRoom){
                try{
                    for(int i=0;i<enemies.size();i++){
                        this.remove(enemies.get(i));
                        enemies.remove(i);
                    }
                
                }catch(Exception x){}
                shop.open();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case 39: Binterface.moveSlot(1);
            break;
            case 37: Binterface.moveSlot(-1);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}

