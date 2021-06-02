import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HotBar extends JPanel{

    ArrayList<Item> slot=new ArrayList<>();
    int equiped=0;

    public HotBar(){
     this.setBackground(Color.GRAY);
     this.setLayout(new GridLayout(1,5));
     slot.add(new Item("BOW"));
     for(int i=0;i<4;i++){
        slot.add(new Item("NULL"));
        slot.get(i).setBackground(Color.LIGHT_GRAY);
        this.add(slot.get(i));
     }
     update();
     moveSlot(0);
    }

    public void moveSlot(int direction){
        if(equiped==4&&direction==1){
            equiped=-1;
        }
        if(equiped==0&&direction==-1){
            equiped=5;
        }
        equiped+=direction;
        for(JPanel slot: slot){
            slot.setBackground(Color.LIGHT_GRAY);
        }
        slot.get(equiped).setBackground(Color.YELLOW);
    }

    public void addItem(String itemID){
        for(int i=0;i<5;i++){
            if(slot.get(i).Item.equals("NULL")){
                slot.remove(i);
                slot.add(i, new Item(itemID));
                slot.get(i).setBackground(Color.LIGHT_GRAY);
                update();
                break;
            }
        }
    }
    
    public void update(){
        this.removeAll();
        for(int i=0;i<5;i++){
            this.add(slot.get(i));
        }
        this.revalidate();
        this.repaint();
    }
}
