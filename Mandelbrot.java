//E/17/153 Karunachandra R.H.I.O.
//Project1 Mandelbrot class


public class Mandelbrot
{
	protected  static int exist;
	private double real_start;
	private double real_end;
	private double imag_start;
	private double imag_end;
	private static double real;
	private static double imag;
	private int iter;
	

	
	public double getX() { return real; }   //methods to extract x and y
    public double getY() { return imag; } 
	public int getIterations(){ return iter; }//method to return the number of iterations took on the ifExist method
	
	
	//constructor for Mandelbrot
	public Mandelbrot(double real_s,double real_e,double imag_s,double imag_e)
	{
		this.real_start=real_s;
		this.real_end=real_e;
		this.imag_start=imag_s;
		this.imag_end=imag_e;
	}
	
	//Maps the x,y coordinates into complex number within the region of interest 
	public void complex(int i,int j)
	{
		real=(((double)i*(real_end- real_start))/800)-Math.abs(real_start);
		imag=(((double)j*(imag_end-imag_start))/800)-Math.abs(imag_start);
	}
	
	//method to find whether a value is in the set or not
	public int ifExist(double real,double imag,int iterations)		
	{
		iter=0;
		double znow_x=0;    //start from 0,0
		double znow_y=0;
	
		while(iterations!=0)
		{
			double znext_x=((znow_x)*(znow_x))-((znow_y)*(znow_y))+real;  //find z(n+1)
			double znext_y=(2*(znow_x)*(znow_y))+imag;
			znow_x=znext_x;
			znow_y=znext_y;
			iter++;
			if((Math.pow(znow_x,2)+Math.pow(znow_y,2))>4)   //check whether if abs(zn^2)>4
			{	
				return exist=0;                             //if so, return exist=0(it is not in the set)
			}	
			iterations--;
		}	
		return exist=1;                 //otherwise it is in the set
	}

	
}	