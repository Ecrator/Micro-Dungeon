import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HotBar extends JPanel{

    ArrayList<Item> slot=new ArrayList<>();
    int equiped=0;

    public HotBar(){
     this.setBackground(Color.GRAY);
     this.setLayout(new GridLayout(1,3));
     slot.add(new Item("SWORD"));
     for(int i=0;i<5;i++){
        slot.add(new Item());
        slot.get(i).setBackground(Color.LIGHT_GRAY);
        this.add(slot.get(i));
     }
     moveSlot(0);
    }

    public void moveSlot(int direction){
        if(equiped==4&&direction==1){
            equiped=-1;
        }
        if(equiped==0&&direction==-1){
            equiped=3;
        }
        equiped+=direction;
        for(JPanel slot: slot){
            slot.setBackground(Color.LIGHT_GRAY);
        }
        slot.get(equiped).setBackground(Color.YELLOW);
    }
    
}
