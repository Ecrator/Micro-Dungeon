import javax.swing.*;

public class Item extends JPanel{
    
    String Item;

    public Item(String ItemID){
        if(ItemID.equals("SWORD")){
            this.add(new JLabel("SWORD"));
        }
        if(ItemID.equals("BOW")){
            this.add(new JLabel("BOW"));
        }
        Item=ItemID;
    }
    public Item(){
        this("NULL");
    }

    public void setItem(String Iid){
        Item=Iid;
    }

    public void Use(){
        try{
         if(Item.equals("SWORD")){      //sword
             System.out.println("YOU USED YOUR SWORD!");
         }else if(Item.equals("BOW")){      //bow
            System.out.println("YOU USED YOUR BOW!");
        }
        }catch(Exception x){System.out.println(x.getStackTrace());}
    }
}
