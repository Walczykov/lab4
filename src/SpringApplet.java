import javax.swing.JApplet;
import java.awt.*;
import java.util.Timer;
import java.util.Vector;
import static java.lang.Math.sqrt;
public class SpringApplet extends JApplet {
    private static final long serialVersionUID = 1L;
    int tMS = 1;
    double tSEC = 0.01;
    SimEngine sEng;
    SimTask sTask;
    Timer tim;
    Graphics bufferGraphics;
    Image offscreen;
    double sprezyna;
    @Override public void init()
    {
        this.setSize(800,480);
        setBackground(Color.LIGHT_GRAY);
        offscreen = createImage(800,480);
        bufferGraphics = offscreen.getGraphics();
        sEng = new SimEngine(3, 0.5, 0.1, 100, 60, 50, 10);
        sTask = new SimTask(sEng, this, tSEC);
        tim = new Timer();
        tim.scheduleAtFixedRate(sTask, 1000, tMS);
    }
    @Override public void paint(Graphics g)
    {
        bufferGraphics.setColor(Color.lightGray);
        bufferGraphics.fillRect(0, 0, 800, 480);
        bufferGraphics.setColor(Color.black);
        bufferGraphics.drawLine(0, 50, 800, 50);
        for(int i=0; i<800; i+=20)
        {
            bufferGraphics.drawLine(i, 50, i+20, 30);
        }
        bufferGraphics.setColor(Color.gray);
        for(int i=0; i<800; i+=20)
        {
            bufferGraphics.drawLine(i, 51, i, 480);
            bufferGraphics.drawLine(0, i+70, 800, i+70);
        }
        bufferGraphics.setColor(Color.black);
        bufferGraphics.drawLine((int)sEng.x0.x, (int)sEng.x0.y,
                (int)sEng.x0.x, (int)sEng.x0.y+5);
        bufferGraphics.drawLine((int)sEng.x1.x, (int)sEng.x1.y-5,
                (int)sEng.x1.x, (int)sEng.x1.y);
        sprezyna=((sEng.x1.y-60)/16);
        bufferGraphics.drawLine((int)sEng.x0.x, (int)sEng.x0.y+5,
                (int)sEng.x0.x+20, (int)(sEng.x0.y+5+sprezyna));
        bufferGraphics.drawLine((int)sEng.x0.x,
                (int)(sEng.x0.y+5+(16)*sprezyna), (int)sEng.x0.x+20,
                (int)(sEng.x0.y+5+(15)*sprezyna));
        for(int i = 0; i<7; i++)
        {
            bufferGraphics.drawLine((int)sEng.x0.x+20,
                    (int)(sEng.x0.y+5+(2*i+1)*sprezyna), (int)sEng.x0.x-20,
                    (int)(sEng.x0.y+5+(2*i+2)*sprezyna));
            bufferGraphics.drawLine((int)sEng.x0.x-20,
                    (int)(sEng.x0.y+5+(2*i+2)*sprezyna), (int)sEng.x0.x+20,
                    (int)(sEng.x0.y+5+(2*i+3)*sprezyna));
        }
        bufferGraphics.fillOval((int)sEng.x1.x - 40,
                (int)sEng.x1.y,80, 80);
        bufferGraphics.setColor(Color.red);
        bufferGraphics.fillRect(100,100,40,20);
        bufferGraphics.drawString("Tłumienie", 150,115);
        bufferGraphics.drawLine((int)sEng.x1.x+2,
                (int)sEng.x1.y+40, (int)sEng.x1.x+2, (int)sEng.x1.y+40 +
                        (int)sEng.getDump()*4);
        if(sEng.getV() >2)
        {
            bufferGraphics.drawLine((int)(int)sEng.x1.x+7,
                    (int)sEng.x1.y+45 + (int)sEng.getDump()*4, (int)sEng.x1.x+2,
                    (int)sEng.x1.y+40 +(int)sEng.getDump()*4);
            bufferGraphics.drawLine((int)(int)sEng.x1.x-3,
                    (int)sEng.x1.y+45 + (int)sEng.getDump()*4, (int)sEng.x1.x+2,
                    (int)sEng.x1.y+40 +(int)sEng.getDump()*4);
        }
        else if(sEng.getV()<-2)
        {
            bufferGraphics.drawLine((int)(int)sEng.x1.x+7,
                    (int)sEng.x1.y+35 + (int)sEng.getDump()*4, (int)sEng.x1.x+2,
                    (int)sEng.x1.y+40 +(int)sEng.getDump()*4);
            bufferGraphics.drawLine((int)(int)sEng.x1.x-3,
                    (int)sEng.x1.y+35 + (int)sEng.getDump()*4, (int)sEng.x1.x+2,
                    (int)sEng.x1.y+40 +(int)sEng.getDump()*4);
        }
        bufferGraphics.setColor(Color.green);
        bufferGraphics.fillRect(100, 140, 40, 20);
        bufferGraphics.drawString("Grawitacja", 150,155);
        bufferGraphics.drawLine((int)sEng.x1.x, (int)sEng.x1.y+40,
                (int)sEng.x1.x, (int)sEng.x1.y+40 +(int)sEng.getGrav()*4 );
        if(sEng.getV() !=0)
        {
            bufferGraphics.drawLine((int)sEng.x1.x,
                    (int)sEng.x1.y+40 + (int)sEng.getGrav()*4, (int)sEng.x1.x-5,
                    (int)sEng.x1.y+35 +(int)sEng.getGrav()*4);
            bufferGraphics.drawLine((int)sEng.x1.x,
                    (int)sEng.x1.y+40 + (int)sEng.getGrav()*4, (int)sEng.x1.x+5,
                    (int)sEng.x1.y+35 +(int)sEng.getGrav()*4);
        }
        bufferGraphics.setColor(Color.blue);
        bufferGraphics.fillRect(100, 180, 40, 20);
        bufferGraphics.drawString("Sprezyna", 150, 195);
        bufferGraphics.drawLine((int)sEng.x1.x-2,
                (int)sEng.x1.y+40, (int)sEng.x1.x-2, (int)sEng.x1.x+40 +
                        (int)sEng.getSpr()*4);
        if(sEng.getSpr() > 3)
        {
            bufferGraphics.drawLine((int)sEng.x1.x-2, (int)sEng.x1.y+40
                    + (int)sEng.getSpr()*4, (int)sEng.x1.x-7, (int)sEng.x1.y+35 +
                    (int)sEng.getSpr()*4);
            bufferGraphics.drawLine((int)sEng.x1.x-2, (int)sEng.x1.y+40
                    + (int)sEng.getSpr()*4, (int)sEng.x1.x+3, (int)sEng.x1.y+35 +
                    (int)sEng.getSpr()*4);
        }
        else if(sEng.getSpr() < -3)
        {
            bufferGraphics.drawLine((int)sEng.x1.x-2,
                    (int)sEng.x1.y+40 + (int)sEng.getSpr()*4, (int)sEng.x1.x-7,
                    (int)sEng.x1.y+45 + (int)sEng.getSpr()*4);
            bufferGraphics.drawLine((int)sEng.x1.x-2,
                    (int)sEng.x1.y+40 + (int)sEng.getSpr()*4, (int)sEng.x1.x+3,
                    (int)sEng.x1.y+45 + (int)sEng.getSpr()*4);
        }
        g.drawImage(offscreen,0,0,this);
    }
}
