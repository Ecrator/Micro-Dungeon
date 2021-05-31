import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Shop extends JFrame implements KeyListener{
    
    ArrayList<Item> items=new ArrayList<>();
    String itemID;
    Random random=new Random();
    int selected=0;
    HotBar inv;
    PlayerUI playerUI;

    public Shop(HotBar Iinv, PlayerUI IplayerUI){
        playerUI=IplayerUI;
        inv=Iinv;
        this.setSize(500,150);
        this.addKeyListener(this);
        this.setTitle("Shop");
        this.setLayout(new GridLayout(1,5,1,1));
        addItems();
    }

    public void addItems(){
        for(int o=0;o<5;o++){
            itemID=randomItem();
            items.add(new Item(itemID));
            items.get(o).setBackground(Color.LIGHT_GRAY);
            items.get(o).showPrice();
            this.add(items.get(o));
        }
        items.get(0).setBackground(Color.YELLOW);
    }

    public void moveSlot(int direction){
        if(selected==4&&direction==1){
            selected=-1;
        }
        if(selected==0&&direction==-1){
            selected=5;
        }
        selected+=direction;
        for(JPanel item: items){
            item.setBackground(Color.LIGHT_GRAY);
        }
        items.get(selected).setBackground(Color.YELLOW);
    }

    public void buyItem(){
        if(items.get(selected).price<=playerUI.coinVX.coins){
            playerUI.coinVX.coins-=items.get(selected).price;
            playerUI.coinVX.updateCoins();
            inv.addItem(items.get(selected).Item);
            items.get(selected).setItem("NULL");
            items.get(selected).removeAll();
            this.revalidate();
            this.repaint();
        }
    }

    public String randomItem(){
        switch(random.nextInt(9)+1){
            case 1:
            return "BANDAGE";
            case 2:
            return "BOW";
            case 3:
            return "HELMET";
            case 4:
            return "BOOTS";
            case 5:
            return "SHIELD";
            case 6:
            return "SWORD";
            case 7:
            return "MACE";
            case 8:
            return "BANDAGE";
            case 9:
            return "BANDAGE";
        }
        return "BANDAGE";
    }

    public void open(){
        this.setVisible(true);
    }

    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'i':this.setVisible(false);
            break;
            case 'e': buyItem();
        }
        switch(e.getKeyCode()){
            case 37: this.moveSlot(-1);
            break;
            case 39: this.moveSlot(1);
        }
    }
    public void keyReleased(KeyEvent e){}
}
