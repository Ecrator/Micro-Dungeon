import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.util.ArrayList;

public class MapGenerator{
    
    int randomInt;
    Random random=new Random();
    JPanel[][] Dslots=new JPanel[10][10];
    JPanel[][] Uslots;
    int SX=4;
    int SY=4;
    String direction="NULL";
    String Pdirection="NULL";
    ArrayList<String> orderedSlots=new ArrayList<>();
    ArrayList<String> orderedSlotDirections=new ArrayList<>();
    JFrame map;

    public MapGenerator(JPanel[][] IUslots, JFrame Imap) throws InterruptedException{
        map=Imap;
        for(int o=0;o<10;o++){
            for(int k=0;k<10;k++){
                IUslots[o][k].setBackground(Color.GRAY);
                IUslots[o][k].removeAll();
                IUslots[o][k].revalidate();
            }
        }
        Uslots=IUslots;
        Dslots[4][4]=Uslots[4][4];

        for(int i=0;i<100;i++){
            try{
                Dslots[SX][SY]=Uslots[SX][SY];
                Dslots[SX][SY].setBackground(Color.RED);
                orderedSlots.add(String.valueOf(SX)+","+String.valueOf(SY));
                orderedSlotDirections.add(Pdirection+","+direction);
                
                switch(randomDirection()){
                    case "SOUTH": SY-=1;
                    break;
                    case "EAST": SX+=1;
                    break;
                    case "NORTH": SY+=1;
                    break;
                    case "WEST": SX-=1;
                }
            }catch(Exception x){}
        }
        orderedSlotDirections.remove(0);
        Dslots[4][4].setBackground(Color.GREEN); //start slot
        JPanel BossSlot=Dslots[Integer.valueOf(orderedSlots.get((orderedSlots.size()-1)).split(",")[0])][Integer.valueOf(orderedSlots.get((orderedSlots.size()-1)).split(",")[1])];
        BossSlot.setBackground(Color.ORANGE); //boss slot
        randomInt=random.nextInt(orderedSlots.size()-2)+1;
        while(orderedSlots.get(randomInt)==orderedSlots.get(orderedSlots.size()-1)||orderedSlots.get(randomInt)==orderedSlots.get(0)){
            randomInt=random.nextInt(orderedSlots.size()-2)+1; //shop slot
        }
        JPanel JShopSlot=Dslots[Integer.valueOf(orderedSlots.get(randomInt).split(",")[0])][Integer.valueOf(orderedSlots.get(randomInt).split(",")[1])];
        String ShopSlot=String.valueOf(orderedSlots.get(randomInt).split(",")[0])+","+String.valueOf(orderedSlots.get(randomInt).split(",")[1]);
        JShopSlot.setBackground(Color.CYAN);
        
        drawPaths();

        PlayerUI playerInterface=new PlayerUI(orderedSlots, orderedSlotDirections, Dslots, ShopSlot, map);
        //for(String slotD: orderedSlotDirections){
           // System.out.println(slotD);
        //}
       // System.out.println();                      //code to help debug generation
        //for(String slot: orderedSlots){
        //    System.out.println(slot);
        //}
    }

    public void drawPaths() throws InterruptedException{
        int i=0;
        for(String slot: orderedSlots){ //draws paths
            Thread.sleep(10);
            if(slot==orderedSlots.get(orderedSlots.size()-1)){
                Dslots[Integer.valueOf(slot.split(",")[0])][Integer.valueOf(slot.split(",")[1])].add(new MapGraph(orderedSlotDirections.get(orderedSlotDirections.size()-1).split(",")[1]+",NULL"));
                Dslots[Integer.valueOf(slot.split(",")[0])][Integer.valueOf(slot.split(",")[1])].revalidate();
            }else{
                Dslots[Integer.valueOf(slot.split(",")[0])][Integer.valueOf(slot.split(",")[1])].add(new MapGraph(orderedSlotDirections.get(i)));
                Dslots[Integer.valueOf(slot.split(",")[0])][Integer.valueOf(slot.split(",")[1])].revalidate();
            }
            if(i<orderedSlotDirections.size()-1){
             i++;
            }
        }
    }
    

    public String randomDirection(){
        Pdirection=direction;
        switch(random.nextInt(4)+1){
            case 1: 
            direction="NORTH";
            return "NORTH";
            
            case 2: 
            direction="EAST";
            return "EAST";
            
            case 3: 
            direction="SOUTH";
            return "SOUTH";
            
            case 4: 
            direction="WEST";
            return "WEST";
        }
        return "NULL";
    }
}
