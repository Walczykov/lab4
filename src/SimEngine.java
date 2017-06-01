public class SimEngine
{
    private double m;
    private double k;
    private double c;
    private double length;
    public Vector2D x1;
    public Vector2D x0;
    private double g;
    private double tempX;
    private double v;
    private double a;
    private Vector2D grawitacja;
    private Vector2D tlumienie;
    private Vector2D sprezystosc;
    public void modMasa(double m)
    { this.m = m;}
    public void modK(double k)
    {this.k = k;}
    public void modC(double c)
    {this.c = c;}
    public void modlength(double length)
    {this.length = length;}
    public void modx1(double x1)
    { x1 = x1;}
    public void modx0(double x0)
    { x0 = x0;}
    public void modg(double g)
    {this.g = g;}
    public double getDump()
    { double c= this.c;
        double v = this.v;
        double m = this.m;
        double tlumienie = c * v * m;
        return tlumienie;}
    public double getSpr()
    { double m = this.m;
        double a = this.a;
        double k = this.k;
        double sprezystosc = k * m *a;
        return sprezystosc;}
    public double getGrav()
    {double m = this.m;
        double g = this.g;
        double grawitacja = m * g;
        return grawitacja;}
    public double getV()
    {return this.v;}
    public double getMasa()
    {return this.m;}
    public double getK()
    {return this.k;}
    public double getC()
    {return this.c;}
    public double getlength()
    {return this.length;}
    public double getx1()
    {double x1 = 0.0;
        return x1;}
    public double getx0()
    {double x0 = 0.0;
        return x0;}
    public double getg()
    {return this.g;}
    public SimEngine(double m, double k, double c, double length, double
            x1, double x0, double g)
    {
        this.m = m;
        this.k = k;
        this.c = c;
        this.length = length;
        this.x1 = new Vector2D(400,x1);
        this.x0 = new Vector2D(400, x0);
        this.g = g;
        v=0;
        tempX = 0;
        a = 0;
        grawitacja = new Vector2D(0,0);
        tlumienie = new Vector2D(0,0);
        sprezystosc = new Vector2D(0,0);
    }
    public void timeStep(double t)
    {
        grawitacja.y = m*g;
        grawitacja.x = 400;
        tlumienie.y = -v*c;
        tlumienie.x = 400;
        sprezystosc.y = -(x1.y-(length + x0.y))*k;
        sprezystosc.x = 400;
        a = (grawitacja.y + tlumienie.y +sprezystosc.y)/m;
        v = v + a*t;
        tempX = v*t;
        x1.y = x1.y + tempX;
    }
    public void reset()
    {
        v=0;
        a=0;
    }
}