import javax.swing.*;
import java.util.Random;

public class Item extends JPanel{
    
    String Item;
    int damage;
    int price;
    Random random=new Random();

    public Item(String ItemID){
        if(ItemID.equals("SWORD")){
            this.add(new JLabel("SWORD"));
            price=1;
        }else if(ItemID.equals("BOW")){
            this.add(new JLabel("BOW"));
            price=1;
        }else if(ItemID.equals("BOOTS")){
            this.add(new JLabel("BOOTS"));
            price=3;
        }else if(ItemID.equals("HELMET")){
            this.add(new JLabel("HELMET"));
            price=5;
        }else if(ItemID.equals("SHIELD")){
            this.add(new JLabel("SHIELD"));
            price=7;
        }else if(ItemID.equals("BANDAGE")){
            this.add(new JLabel("BANDAGE"));
            price=4;
        }else if(ItemID.equals("MACE")){
            this.add(new JLabel("MACE"));
            price=10;
        }
        Item=ItemID;
    }
    public Item(){
        this("NULL");
    }

    public void setItem(String Iid){
        Item=Iid;
    }

    public void showPrice(){
        this.add(new JLabel("$"+price));
    }

    public void Use(){
        try{
         if(Item.equals("SWORD")){      //sword
            damage=random.nextInt(5)+3;
         }else if(Item.equals("BOW")){      //bow
            damage=random.nextInt(5)+3;
        }else if(Item.equals("MACE")){      //mace
            damage=random.nextInt(12)+7;
        }
        }catch(Exception x){System.out.println(x.getStackTrace());}
    }
}
