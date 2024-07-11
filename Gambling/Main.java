import javax.swing.JPanel;

public class Main extends JPanel{
    static Window win;
    static Draw background;

    public static void main(String[] args){
        //new drawing object
        win = new Window();
        win.add(new Draw(800, 600));
        win.settings();
        win.setName("Gabling");
    }
}
