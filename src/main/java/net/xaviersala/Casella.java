package net.xaviersala;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class Casella {
	
	private double x;
	private double y;
	private Color equip;
	private boolean castell;
	private GImage castellimg;
	private double width;
	private double height;
	private GRect GRectCasella;


	public Casella(double x, double y, GRect GRectCasella){
		
		this.x = x;
		this.y = y;
		this.GRectCasella = GRectCasella;

	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public Color getEquip() {
		return equip;
	}


	public void setEquip(Color equip) {
		this.equip = equip;
	}
	
	public boolean isCastell() {
		return castell;
	}


	public void setCastell(boolean castell) {
		this.castell = castell;
	}
	
	public GImage getCastellimg() {
		return castellimg;
	}


	public void setCastellimg(GImage castellimg) {
		this.castellimg = castellimg;
		this.width = castellimg.getWidth();
		this.height = castellimg.getHeight();
	}
	
	
	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public GRectangle getGrectangleCastell(){
		
		GRectangle GRectangleCastell = this.castellimg.getBounds();
		
		return GRectangleCastell;
		
	}
	
	public GRectangle getGrectangleCasella(){
		
		GRectangle GRectangleCasella = new GRectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		
		return GRectangleCasella;
	}


	public GRect getGRectCasella() {
		return GRectCasella;
	}


	public void setGRectCasella(GRect gRectCasella) {
		GRectCasella = gRectCasella;
	}
	

}
