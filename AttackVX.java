import javax.swing.*;
import java.awt.*;

public class AttackVX extends JPanel{
    

    public AttackVX(String weapon){
        if(weapon.equals("SWORD")){
            this.setSize(40,40);
            this.setBackground(Color.BLUE);
        }
    }
    public AttackVX(){
        this("NULL");
    }
}
