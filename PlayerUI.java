import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayerUI extends JFrame implements KeyListener{
    
    ArrayList<String> orderedSlots;
    ArrayList<String> orderedSlotDirections;
    String roomSlot="4,4";
    JPanel[][] Dslots;
    RoomGraph playerInterface=new RoomGraph();
    JPanel Sinterface=new JPanel();
    HotBar Binterface=new HotBar();
    JPanel player=new JPanel();
    int playerX=200;
    int playerY=200;
    boolean p=true;
    ArrayList<Enemy> enemies=new ArrayList<>();
    Random random=new Random();

    public PlayerUI(ArrayList<String> IorderedSlots, ArrayList<String> IorderedSlotDirections, JPanel[][] IDslots) throws InterruptedException{

        orderedSlots=IorderedSlots;
        orderedSlotDirections=IorderedSlotDirections;
        Dslots=IDslots;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setResizable(false);
        this.addKeyListener(this);
        player.setSize(new Dimension(10,10));
        Sinterface.setPreferredSize(new Dimension(100,500));
        Binterface.setPreferredSize(new Dimension(500,100));
        player.setBackground(Color.RED);
        Sinterface.setBackground(Color.GRAY);
        Binterface.setBackground(Color.GRAY);
        playerInterface.setBackground(Color.DARK_GRAY);
        player.setLocation(200,200);
        playerInterface.setLayout(null);
        updateRoom();
        this.add(playerInterface, BorderLayout.CENTER);
        this.add(Sinterface, BorderLayout.EAST);
        this.add(Binterface, BorderLayout.SOUTH);
        playerInterface.add(player);   
        this.revalidate();
        this.setVisible(true);
    }

    public void updateRoom(String roomDirection){
        try{
            for(Enemy enemy: enemies){
                this.remove(enemy);
                enemies.remove(enemy);
            }
        }catch(Exception x){}
        for(int i=0;i<random.nextInt(3)+0;i++){
            enemies.add(new Enemy());
            this.add(enemies.get(i));
        }
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
        try{

            switch(direction){
                case 'w':
                player.setLocation(playerX, playerY-20);
                playerY-=20;
                break;
                case 'a':
                player.setLocation(playerX-20, playerY);
                playerX-=20;
                break;
                case 's':
                player.setLocation(playerX, playerY+20);
                playerY+=20;
                break;
                case 'd':
                player.setLocation(playerX+20, playerY);
                playerX+=20;
            }
        }catch(Exception x){}
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
                    for(int i=0;i<13;i++){
                        movePlayer('s');
                 }
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
                 for(int i=0;i<13;i++){
                    movePlayer('d');
                 }
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
                 for(int i=0;i<13;i++){
                     movePlayer('w');
                 }
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
                    for(int i=0;i<13;i++){
                        movePlayer('a');
                    }
             }
            }
            break;
            case 'c': System.out.println(roomSlot);
                    System.out.println(orderedSlots);
                    System.out.println(orderedSlotDirections);
            break;
            case 'e': Binterface.slot.get(Binterface.equiped).Use();
            break;
            case 'q': Binterface.slot.get(Binterface.equiped).setItem("NULL");
                      Binterface.slot.get(Binterface.equiped).removeAll();
                      System.out.println("You dropped your Item, because your're clumsy.");
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

