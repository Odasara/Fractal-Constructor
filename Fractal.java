//E/17/153 Karunachandra R.H.I.O.
//Project1 Fractal class

import java.awt.*; /* java abstract window toolkit */
import java.awt.event.*; 
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.Random;


public class Fractal extends JPanel{  //Inherit JPanel

	private int width=800 ,height =800 ,iterations,exist,mandb;   //Define variables
	private double real_start,real_end,imag_start,imag_end;
	
	
	Mandelbrot mand=null;   
	Julia jul=null;
	
	
	//Constructor for Mandelbrot
	public Fractal(double real_s,double real_e,double imag_s,double imag_e,int iterations)
	{
		this.real_start= real_s;
		this.real_end= real_e;
		this.imag_start= imag_s;
		this.imag_end= imag_e;
		this.iterations=iterations;
		mandb=1;
		mand = new Mandelbrot(real_start,real_end,imag_start,imag_end);
		// set the panel size 
		setPreferredSize(new Dimension(width,height));
	}
	
	//Constructor for Julia
	public Fractal(double real,double imag,int iterations)
	{
		this.real_start=real;
		this.imag_start=imag;
		this.iterations=iterations;
		mandb=0;
		jul = new Julia(real,imag);
		// set the panel size 
		setPreferredSize(new Dimension(width,height));
	}



	public static void main (String [] args){
	
		double real_start =0 ,real_end =0 ,imag_start =0 ,imag_end =0;
		int iterations=0;
		JFrame frame=null;
		  
		if (args.length >= 1){               //command line arguments handling
			if (args[0].equals ("Mandelbrot")){   
				if(args.length==1){
					real_start=-1;
		    		real_end=1;
		    		imag_start=-1;
		    		imag_end=1;
		    		iterations=1000;
				}
				else if(args.length==5 ){
		    		real_start = Double.parseDouble(args[1]);
		    		real_end = Double.parseDouble(args[2]);
		    		imag_start = Double.parseDouble(args[3]);
		    		imag_end = Double.parseDouble(args[4]);
		    		iterations = 1000;
		    	}
				else if(args.length==6){
		    		real_start = Double.parseDouble(args[1]);
		    		real_end = Double.parseDouble(args[2]);
		    		imag_start = Double.parseDouble(args[3]);
		    		imag_end = Double.parseDouble(args[4]);
		    		iterations = Integer.parseInt(args[5]);
		    	}
				else{
					System.out.println("\nUsage:");
	    			System.out.println("java Fractal Mandelbrot real_start real_end imag_start imag_end (iterations)");
					System.exit(0);
				}
				frame = new JFrame("Mandelbrot Set");     
		    		// set the content of the frame
		    	frame.setContentPane(new Fractal(real_start,real_end,imag_start,imag_end,iterations));
				
			}
			
			else if(args[0].equals("Julia")){
				if(args.length==1){
		    			real_start=-0.4;
		    			imag_start=0.6;
		    			iterations=1000;
		    	}	
				
				else if(args.length==4){		
		    			real_start = Double.parseDouble(args[1]);
		    			imag_start = Double.parseDouble(args[2]);
		    			iterations = Integer.parseInt(args[3]);
		    	}
				else{
					System.out.println("\nUsage:");
					System.out.println("java Fractal Julia Real Imaginary Iterations");
	    			System.exit(0);
				}
				frame = new JFrame("Julia Set"); 
	    		// set the content of the frame as one of this panel
	    		frame.setContentPane(new Fractal(real_start,imag_start,iterations));	
			}
			
			else{
				System.out.println("\nUsage:");
				System.out.println("Fractal type is incorrect");
				System.exit(0);
			}
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack(); 
			frame.setLocationRelativeTo(null); 
			frame.setVisible(true); 	
		}
		
		else{
			System.out.println("\nUsage:");
			System.out.println("No enough arguments");
		}
	}
	
	
	
	//method to print a point on the given x,y cordinates with the given color
	private static void printPoint(Graphics2D frame, Color col,double real,double imag) {
	
	    frame.setColor(col); 
	    frame.draw(new Line2D.Double(real,800-imag,real,800-imag)); 
	}
	
	public void paintComponent(Graphics g) {
	 
		// call paintComponent from parent class 
		super.paintComponent(g); 
		Color col= null;
		for(int i=0;i<=800;i++) {   //loops till 800 x 800
			
			for(int j=0;j<=800;j++){
				
				if(mandb==1) { //checks the type of fractal
					
					mand.complex(i,j);
					this.exist = mand.ifExist(mand.getX(), mand.getY(), iterations);
				}
				else if(mandb==0) { //checks the type of fractal
					
					jul.complex(i,j);
					this.exist = jul.ifExist( jul.getX(), jul.getY(), iterations);
				}	
				//checks the point exists in or out of the fractal set
				if(exist==1){	 
					printPoint((Graphics2D)g,Color.BLACK,i,j); 
				}
					 
				else if(exist==0){
				
					if(mandb==1){
						//find different colors for different points according to the number of iterations
						col = Color.getHSBColor((float)mand.getIterations()*20.0f/(float)iterations, 1.0f, 1.0f);
					}  //found those f values by several trials
					else if(mandb==0){
						//find different colors for different points according to the number of iterations
						col = Color.getHSBColor((float)jul.getIterations()*10.0f/(float)iterations, 1.0f, 1.0f);
					}  //found those f values by several trials
					printPoint((Graphics2D)g,col,i,j);  //call printPoint method to print the point
				}
			}	
		}		
	}		
	
}	