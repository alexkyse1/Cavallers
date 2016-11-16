package net.xaviersala;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Cavaller implements MouseListener{
	
	
	private Color equip;
	private int num;
	private GImage imatge;
	private double destinationX;
	private double destinationY;
	private Casella castelldesti;
	private boolean travelling;
	private double velocitat;
	private double deg = 0;
	private boolean respira;


	public Cavaller(int num, Color equip, int velocitat){
		
		this.num = num;
		this.equip = equip;
		this.velocitat = velocitat;
		this.travelling = false;
		this.respira = true;
		
		if(imatge != null){
			imatge.addMouseListener(this);
			
		}
		
		
	}

	public Color getEquip() {
		return equip;
	}


	public void setEquip(Color equip) {
		this.equip = equip;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}
	
	public GImage getImatge() {
		return imatge;
	}


	public void setImatge(GImage imatge) {
		this.imatge = imatge;
	}


	public double getDestinationX() {
		return destinationX;
	}

	public double getDestinationY() {
		return destinationY;
	}


	public boolean isTravelling() {
		return travelling;
	}

	public void setTravelling(boolean travelling) {
		this.travelling = travelling;
	}
	
	
	
	public double getVelocitat() {
		return velocitat;
	}


	public void setVelocitat(double velocitat) {
		this.velocitat = velocitat;
	}
	
	
	
	public boolean isRespira() {
		return respira;
	}


	public void setRespira(boolean respira) {
		this.respira = respira;
		
	}


	public GRectangle getGRectangleCavaller(){
		
		GRectangle GRectCavaller = this.imatge.getBounds();
		
		return GRectCavaller;
		
		
	}
	
	
	public Casella getCastellDesti() {
		return castelldesti;
	}


	public void setDestinationCastle(double x, double y, Casella castell){
	
		this.travelling = true;
		
		this.castelldesti = castell;
		
		double deltax = (castelldesti.getX() + castelldesti.getWidth()*0.5) - (this.imatge.getX() + imatge.getWidth()*0.5);
		double deltay = (this.imatge.getY() + this.imatge.getHeight()*0.5) - (castelldesti.getY() + castelldesti.getHeight()*0.5);

		double rad = Math.toDegrees(Math.atan2(deltay, deltax));

		deg = (rad<0) ? (360d + rad) : rad;
		
	}


	public void moveknight(){
		
		
		imatge.movePolar(velocitat,deg);

	}
	
	public void mousePressed(MouseEvent ev) {
		System.out.println("1click!");
	  }
 
	public void mouseExited(MouseEvent event) {System.out.println("2click!");}
	public void mouseReleased(MouseEvent event) {System.out.println("3click!");}
	public void mouseClicked(MouseEvent event) {System.out.println("4click!");}
	public void mouseEntered(MouseEvent event) {System.out.println("5click!");}
	
	
	
	


}
