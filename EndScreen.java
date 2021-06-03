import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EndScreen extends JFrame implements KeyListener{

    JPanel restart=new JPanel();
    JPanel quit=new JPanel();
    int selected=1;
    EndScreen(String ending){
        restart.add(new JLabel("RESTART"));
        restart.setLocation(200,250);
        restart.setSize(100,50);
        restart.setBackground(Color.YELLOW);
        quit.add(new JLabel("QUIT"));
        quit.setLocation(200,330);
        quit.setSize(100,50);
        quit.setBackground(Color.GRAY);
        this.setLayout(null);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("END");
        this.setResizable(false);
        this.add(restart);
        this.add(quit);
        this.addKeyListener(this);
    }

    public void newGame() throws InterruptedException{
        Game newGame=new Game();
    }

    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e) {
        restart.setBackground(Color.GRAY);
        quit.setBackground(Color.GRAY);
        switch(e.getKeyCode()){
            case 38:
            switch(selected){
                case 1:
                selected=2;
                quit.setBackground(Color.YELLOW);
                break;
                case 2:
                selected=1;
                restart.setBackground(Color.YELLOW);
            }
            break;
            case 40:
            switch(selected){
                case 1:
                selected=2;
                quit.setBackground(Color.YELLOW);
                break;
                case 2:
                selected=1;
                restart.setBackground(Color.YELLOW);
            }
        }
        switch(e.getKeyChar()){
            case 'e':
            if(selected==1){
                this.setVisible(false);
                try{
                    newGame();
                }catch(InterruptedException x){}
            }else{
                this.dispose();
                System.exit(0);
            }
        }
        
    }
    @Override
    public void keyReleased(KeyEvent e){}
}
