//E/17/153 Karunachandra R.H.I.O.
//Project 1 : Julia Class

public class Julia{

	protected int exist;
	private double real_input;
	private double imag_input;
	private double real;
	private double imag;
	private double znow_x;
	private double znow_y;
	private int iter;
	
	public double getX() { return real; } //methods to extract x and y
    public double getY() { return imag; } 
	public int getIterations() { return iter; }  //method to return the number of iterations took on the ifExist method
	
	
	//constructor for Julia
	public Julia(double real,double imag)
	{
		real_input=real;
		imag_input=imag;
	}
	
	//Maps the x,y coordinates into complex number within the region of interest 
	public void complex(double i,double j)
	{
		znow_x=(((double)i*2)/800)-1;
		znow_y=(((double)j*2)/800)-1;
	}
	//method to find whether a value is in the set or not
	public int ifExist(double real,double imag,int iterations)		
	{
		iter=0;
		real=real_input;
		imag=imag_input;
		
		while(iterations!=0)  //loop till maximum no of iterations given
		{
			double znext_x=((znow_x)*(znow_x))-((znow_y)*(znow_y))+real;   //find z(n+1)
			double znext_y=(2*(znow_x)*(znow_y))+imag;
			znow_x=znext_x;
			znow_y=znext_y;
			iter++;
			if((Math.pow(znow_x,2)+Math.pow(znow_y,2))>4)    //check whether if abs(zn^2)>4
			{	
				return exist=0;							//if so, return exist=0(it is not in the set)
			}	
			iterations--;
		}	
		return exist= 1;
	}
		
}