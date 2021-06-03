import javax.swing.*;

public class AttackVX extends JLabel{
    

    public AttackVX(String weapon){
        if(weapon.equals("SWORD")){
            this.setSize(40,40);
            ImageIcon icon=new ImageIcon(getClass().getResource("Sprites\\sword_swing.png"));
            this.setIcon(icon);
        }
    }
    public AttackVX(){
        this("NULL");
    }
}
