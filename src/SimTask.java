import java.util.TimerTask;
public class SimTask extends TimerTask {
    private SimEngine a;
    public SpringApplet b;
    private double t;
    SimTask(SimEngine E, SpringApplet S, double time)
    {
        b = S;
        a = E;
        t = time;
    }
    @Override public void run()
    {a.timeStep(t);
        b.repaint();
    }
}