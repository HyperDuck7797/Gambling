import javax.swing.*;

public class Window extends JFrame{
    //Initializes a window
    public void settings(){
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    } 
    @Override
    public void setName(String title){
        setTitle(title);
    }
}