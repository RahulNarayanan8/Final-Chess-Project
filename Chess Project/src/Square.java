import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Square extends JComponent
{
	private Rectangle.Double square = new Rectangle.Double(0,0,50,50);
	private boolean light_square;
	public Square(int x,int y)
	{
		this.setLocation(x,y);
		this.setSize(new Dimension(50,50));
		
	}
	public void setColor(boolean color)
	{
		light_square = color;
	}
	public boolean getColor()
	{
		return light_square;
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		if (light_square)
		{
			g2.setColor(new Color(240,217,183));
			g2.fill(square);
		}
		else
		{
			g2.setColor(new Color(180,136,102));
			g2.fill(square);
		}
	}
	
}
