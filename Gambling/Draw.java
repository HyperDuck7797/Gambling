import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Draw extends Canvas{
    private int width;
    private int height;
    public Shapes shape = new Shapes();
    public Home home = new Home();
    public int x;
    public int y;
    public int level = 0;
    private Graphics2D g2d;

    public Draw(int w,int h){
        width = w;
        height = h;
        setBackground(new Color(0, 100, 0));
        addMouseListener(new MouseAdapter() { 
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY(); 
                switch (level) {
                    case 0:
                        if ((x >= 232 && x <= 568)&&(y >= 256 && y <= 304)) {
                            level = 1;
                            repaint();
                        }
                        break;
                    case 1:
                        if(home.bust == false && home.win == false && home.stand == false){
                            if ((x >= 232 && x <= 390)&&(y >= 450 && y <= 498)) {
                                home.hit();
                                repaint();
                            }
                            else if ((x >= 432 && x <= 590)&&(y >= 450 && y <= 498)) {
                                home.stand();
                                repaint();
                            }
                        }
                        else{
                            if ((x >= 232 && x <= 390)&&(y >= 450 && y <= 498)) {
                                home = new Home();
                                repaint();
                            }
                            else if ((x >= 432 && x <= 590)&&(y >= 450 && y <= 498)) {
                                home = new Home();
                                level = 0;
                                repaint();
                            }
                        }
                        break;
                    default:
                        System.out.println(x + " "+ y);
                        break;
                }
            } 
        }); 
    }
    //draws the shapes
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public void paint(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //working as intended
        /*shape.card(g2d, 64, 64, 32, "spade", "K");
        shape.card(g2d, 128, 64, 32, "club", "10");
        shape.card(g2d, 192, 64, 32, "heart", "3");
        shape.card(g2d, 256, 64, 32, "diamond", "J");*/
        switch (level) {
            case 0:
                home.SceneHome(g2d);
                break;
            case 1:
                home.SceneBlackJack(g2d);
                break;
            default:
                home.SceneHome(g2d);
                level = 0;
                break;
        }
    }
}