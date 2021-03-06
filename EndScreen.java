import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EndScreen extends JFrame implements KeyListener{

    JPanel restart=new JPanel();
    JPanel quit=new JPanel();
    int selected=1;
    JLabel art=new JLabel("art");
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
        art.setSize(500,500);
        if(ending.equals("BAD")){
            ImageIcon Bicon=new ImageIcon(getClass().getResource("Sprites\\bad_ending.png"));
            art.setIcon(Bicon);
        }else{
            ImageIcon Gicon=new ImageIcon(getClass().getResource("Sprites\\good_ending.png"));
            art.setIcon(Gicon);
        }
        this.add(art);
    }

    public void newGame() throws InterruptedException{
        Game newGame=new Game();
    }

    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case 38:
            switch(selected){
                case 1:
                selected=2;
                quit.setBackground(Color.YELLOW);
                restart.setBackground(Color.GRAY);
                break;
                case 2:
                selected=1;
                restart.setBackground(Color.YELLOW);
                quit.setBackground(Color.GRAY);
            }
            break;
            case 40:
            switch(selected){
                case 1:
                selected=2;
                quit.setBackground(Color.YELLOW);
                restart.setBackground(Color.GRAY);
                break;
                case 2:
                selected=1;
                restart.setBackground(Color.YELLOW);
                quit.setBackground(Color.GRAY);
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
            break;
            case 'w':
            switch(selected){
                case 1:
                selected=2;
                quit.setBackground(Color.YELLOW);
                restart.setBackground(Color.GRAY);
                break;
                case 2:
                selected=1;
                restart.setBackground(Color.YELLOW);
                quit.setBackground(Color.GRAY);
            }
            break;
            case 's':
            switch(selected){
                case 1:
                selected=2;
                quit.setBackground(Color.YELLOW);
                restart.setBackground(Color.GRAY);
                break;
                case 2:
                selected=1;
                restart.setBackground(Color.YELLOW);
                quit.setBackground(Color.GRAY);
            }
        }
        
    }
    @Override
    public void keyReleased(KeyEvent e){}
}
