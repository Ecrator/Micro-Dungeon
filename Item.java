import javax.swing.*;

public class Item extends JPanel{
    
    String Item;

    public Item(String ItemID){
        if(ItemID.equals("SWORD")){
            this.add(new JLabel("SWORD"));
        }
        Item=ItemID;
    }
    public Item(){
        this("NULL");
    }

    public void Use(){
        try{
         if(Item.equals("SWORD")){
             System.out.println("YOU USED YOUR SWORD!");
         }
        }catch(Exception x){System.out.println(x.getStackTrace());}
    }
}
