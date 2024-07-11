import java.awt.*;
import java.awt.geom.*;
import javax.swing.ImageIcon;

public class Shapes {
    //shapes so you don't have to use as many lines in draw
    public void background(Graphics2D g2d,int width, int height,Color color){
        Rectangle2D.Double b = new Rectangle2D.Double(0,0,width,height);
        g2d.setColor(color);
        g2d.fill(b);
    }

    public void line(Graphics2D g2d,int startX,int startY,int endX,int endY,Color color){
        Line2D.Double line = new Line2D.Double(startX,startY,endX,endY);
        g2d.setColor(color);
        g2d.draw(line);
    }

    public void rect(Graphics2D g2d,int posX,int posY,int width,int height,Color color){
        Rectangle2D.Double r = new Rectangle2D.Double(posX, posY, width, height);
        g2d.setColor(color);
        g2d.fill(r);
    }
    public void circle(Graphics2D g2d,int posX,int posY,int size,Color color){
        Ellipse2D.Double e = new Ellipse2D.Double(posX,posY,size,size);
        g2d.setColor(color);
        g2d.fill(e);
    }

    public void cloud(Graphics2D g2d,int posX, int posY,int size,Color color) {
        Ellipse2D.Double e1 = new Ellipse2D.Double(posX,posY,size,size);
        Ellipse2D.Double e2 = new Ellipse2D.Double(posX+size*.35,posY-size*.2,size*1.75,size*1.4);
        Ellipse2D.Double e3 = new Ellipse2D.Double(posX+size*1.5,posY+size*.15,size*.9,size*.9);
        Ellipse2D.Double e4 = new Ellipse2D.Double(posX+size*1.8,posY+size*.05,size*.3,size*.3);
        g2d.setColor(color);
        g2d.fill(e1);
        g2d.fill(e2);
        g2d.fill(e3);
        g2d.fill(e4);
    }

    public void card(Graphics2D g2d, int posX, int posY, int size, String num, String suite){
        Rectangle2D.Double back = new Rectangle2D.Double(posX-(size/1.5), posY-(size), size*1.5, size*2);
        g2d.setColor(Color.white);
        g2d.fill(back);
        switch (num) {
            case "1":
                num = "A";
                break;
            case "11":
                num = "J";
                break;
            case "12":
                num = "Q";
                break;
            case "13":
                num = "K";
                break;
            default:
                break;
        }
        switch (suite){
            case "spade":
                g2d.setColor(Color.black);
                ImageIcon spade = new ImageIcon("images/spade.png");
                Image imgs = spade.getImage();
                g2d.drawImage(imgs, posX-size/6, posY-size/4, size/2, size/2, null);
                break;
            case "club":
                g2d.setColor(Color.black);
                ImageIcon club = new ImageIcon("images/club.png");
                Image imgc = club.getImage();
                g2d.drawImage(imgc, posX-size/6, posY-size/4, size/2, size/2, null);
                break;
            case "heart":
                g2d.setColor(Color.red);
                ImageIcon heart = new ImageIcon("images/heart.png");
                Image imgh = heart.getImage();
                g2d.drawImage(imgh, posX-size/6, posY-size/4, size/2, size/2, null);
                break;
            case "diamond":
                g2d.setColor(Color.red);
                ImageIcon diamond = new ImageIcon("images/diamond.png");
                Image imgd = diamond.getImage();
                g2d.drawImage(imgd, posX-size/6, posY-size/4, size/2, size/2, null);
                break;
            default:
                g2d.setColor(Color.red);
                g2d.fill(back);
                break;
        }
        text(g2d, num, posX-size/2, posY-size/2, g2d.getColor(), size/2);
    }

    public void image(Graphics2D g2d,String fileName,int width,int height){
        ImageIcon mage = new ImageIcon(fileName);
        Image img = mage.getImage();
        g2d.drawImage(img, width, height, null);
    }

    public void text(Graphics2D g2d,String txt,int x,int y,Color color,int size){
        g2d.setColor(color);
        g2d.setFont(new Font("ComicSansMS", Font.PLAIN, size));
        g2d.drawString(txt, x, y);
    }
}
