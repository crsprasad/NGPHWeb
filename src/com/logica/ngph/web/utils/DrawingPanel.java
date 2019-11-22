package com.logica.ngph.web.utils;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class DrawingPanel  extends JPanel {
	  private int fontSize = 30;
	  private String message = "";
	  private int messageWidth;
	  
	  
	  
	  public DrawingPanel( String message) {
		  this.message=message;
		  System.out.println(message);
	    setBackground(Color.white);
	    Font font = new Font("Serif", Font.PLAIN, fontSize);
	    setFont(font);
	    FontMetrics metrics = getFontMetrics(font);
	    messageWidth = metrics.stringWidth(message);
	    int width = messageWidth;
	    int height = fontSize;
	    setPreferredSize(new Dimension(width, height));
	  }

	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D)g;
	    int x = messageWidth/100;
	    int y = fontSize;
	    g2d.translate(x, y);
	    g2d.setPaint(Color.lightGray);
	    AffineTransform origTransform = g2d.getTransform();
	    g2d.shear(-0.95, 0);
	    g2d.scale(1, 3);
	   /* g2d.drawString("HI PRINT THIS TEST.JAVA", 0, 0);*/
	    g2d.setTransform(origTransform);
	    g2d.setPaint(Color.black);
	    g2d.drawString(message, 0, 0);
	  }

}
