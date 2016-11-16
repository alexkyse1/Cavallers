package net.xaviersala;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Compte {
	
	private int num;
	private Color equip;
	private List<Cavaller> cavallers = new ArrayList<>();

	
	
	public Compte(int num, Color equip){
		
		this.num = num;
		this.equip = equip;
		
	}
	
	
	
	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public Color getEquip() {
		return equip;
	}



	public void setEquip(Color equip) {
		this.equip = equip;
	}



	public void afegircavallers(Cavaller cavaller){
		
		this.cavallers.add(cavaller);
	}



	public List<Cavaller> getCavallers() {
		return cavallers;
	}
	
	public boolean tenscavallersvius(){
		
		boolean check = false;
		
		for(Cavaller cavaller: cavallers){
			
			if(cavaller.isRespira()){
				check = true;
			}
			
		}
		
		return check;
	}

	
	

}
