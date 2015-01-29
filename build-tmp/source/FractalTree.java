import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FractalTree extends PApplet {

private double fractionLength = .8f; 
private int smallestBranch = 10; 
private double branchAngle = .2f;  
private boolean isGrowing = true;
public void setup() 
{   
	background(0);
	size(640,480);    
	frameRate(10);
	// fill(0, 140);
	// rect(0,0,0,640,640,480,640,480);   

} 
public void draw() 
{   
	background(0, 140);
	stroke(240, 140);   
	line(320,480,320,380);   
	drawBranches(320,380,100,3*Math.PI/2);  //will add later
	System.out.println("smallestBranch: "+smallestBranch);
	System.out.println("branchAngle: "+branchAngle);
	// if (smallestBranch > 15)
	// {
	// 	smallestBranch -= 1;
	// }
	// if (branchAngle < 12)
	// {
	// 	branchAngle += 0.1;
	// }
	branchAngle += 0.1f;
	if(isGrowing == true)
	{
		smallestBranch ++;
		if (smallestBranch == 45)
		{
			isGrowing = false;
		}
	}
	if(isGrowing == false)
	{
		smallestBranch --;
		if (smallestBranch == 10)
		{
			isGrowing = true;
		}
	}
} 
public void drawBranches(int x,int y, double branchLength, double angle) 
{  
	double angle1 = angle + branchAngle;
	double angle2 = angle - branchAngle;
	branchLength = branchLength * fractionLength;
	int endX1 = (int)(branchLength*Math.cos(angle1) + x);
	int endY1 = (int)(branchLength*Math.sin(angle1) + y);
	int endX2 = (int)(branchLength*Math.cos(angle2) + x);
	int endY2 = (int)(branchLength*Math.sin(angle2) + y);
	line(x, y, endX1, endY1);
	line(x, y, endX2, endY2);

	if (branchLength > smallestBranch)
	{
		drawBranches(endX1, endY1, branchLength, angle1);
		drawBranches(endX2, endY2, branchLength, angle2);
	}
} 
public void growBranches()
{
	smallestBranch += 1;
}
public void ungrowBranches()
{
	smallestBranch -= 1;
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FractalTree" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
