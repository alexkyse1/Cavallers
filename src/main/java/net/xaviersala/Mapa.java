package net.xaviersala;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import acm.graphics.*;
import java.util.Random;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Mapa {
	
	/*private GImage background;*/
	private GRectangle dimensions;
	
	List<Cavaller> cavallers;	
	List<Compte> comptes;
	List<Casella> caselles;
	List<Casella> castells;
	
	private Random rand = new Random ();
	private static int SLEEP = 10;
	private double [] statistics = new double [3]; 
	private int dimensionsXY;
	boolean generalcheck = true;
	
	public Mapa(double w, double h,int dimensionsXY){
		

		
		this.dimensions = new GRectangle(100,100,w-200, h-200);
		this.cavallers = new ArrayList<>();
		this.comptes = new ArrayList<>();
		this.caselles = new ArrayList<>();
		this.dimensionsXY = dimensionsXY;
		
	}
	
	
	public void afegirCavallers(List<Cavaller> cavallers){
		
		this.cavallers = cavallers;
		
	}
	
	public void afegirComptes(List<Compte> comptes){
		
		this.comptes = comptes;
		
	}
	
	public void afegirCaselles(List<Casella> caselles){
		
		this.caselles = caselles;
	}
	
	public void afegirCastells(List<Casella> castells){
		
		this.castells = castells;
	}
	
	public void calcularcaselles(){
		
		int vermell = 0;
		int groc = 0;
		int verd = 0;
		
		for(Casella casella: caselles){
			
			if(casella.getEquip()==Color.RED){
				vermell++;
			}
			else if(casella.getEquip()==Color.YELLOW){
				
				groc++;
			}
			else if(casella.getEquip()==Color.GREEN){
				
				verd++;
			}
			
		
			
			//Calcul percentatges
			if(vermell>0){
				statistics[0]=(dimensionsXY/vermell)*100;
			}
			if(groc>0){
				statistics[1]=(dimensionsXY/groc)*100;
			}
			if(verd>0){
				statistics[2]=(dimensionsXY/verd)*100;
			}
			
			
			
			
		}
		
	}
	
	public double getpercentatge(Color team){
		
		double percentatge = 0;
		
		if(team == Color.RED){
			percentatge = statistics[0];
		}
		else if(team == Color.YELLOW){
			percentatge = statistics[1];
		}
		else if(team == Color.GREEN){
			percentatge = statistics[2];
		}
		
		return percentatge;
	}
	
	public boolean isfinish(){
		
		boolean check = true;
		
		int alive = 0;
		
		for(Compte compte: comptes){
			
			
			
			if(compte.tenscavallersvius()){
				alive++;
			}
			
		}

		if(alive==1){
			check = false;
		}
			
		

		return check;
	}
	
	public boolean Start(App app){
		
		boolean check = true;

    	
    	while(check){
    		

            for(Cavaller cavaller: cavallers) {
            	
            	if(!asignarcastellcavaller(cavaller)){
            		
            		if(cavaller.isRespira()){
                		
                		
                		if(!cavallerintersectscastell(cavaller)){
                			
                			
                			//COMPROVEM INTERSECTS AMB CASELLES
                			
                			cavallerintersectscaselles(cavaller);
                			
                			//END COMPROVEM INTERSECTS AMB CASELLES
                			
                			
                			//COMPROVEM INTERSECTS AMB ALTRES CAVALLERS TAMBÉ
                			
                			cavallerintersectscavallers(cavaller);
                			
                			//END COMPROVEM INTERSECTS AMB ALTRES CAVALLERS
                	
                			//MOVEM EL CAVALLER
                			cavaller.moveknight();
                			//DOMEN UN XIC DE TEMPS
                			app.pause(SLEEP);
                		}

                	}
            	

            	}
            	//COMPROVEM SI HA FINALITZAT
            	 check = isfinish();
             	
            }
            
    }
    	
    	
		return false;
		

	}
	
	
	public void cavallerintersectscaselles(Cavaller cavaller){
		
		GRectangle GRectangleCavaller = cavaller.getGRectangleCavaller();
		
		//COMPROVEM INTERSECTS AMB CASELLES
		
		for(Casella casella: caselles){
			
			
			GRectangle GRectangleCasella = casella.getGrectangleCasella();
			
			//MODIFIQUEM GRECTANGLE CASELLA
			
			GRectangleCasella.setBounds(GRectangleCasella.getX(),GRectangleCasella.getY(), GRectangleCasella.getWidth()+20,GRectangleCasella.getHeight()+20);
			
			
			//END MODIFIQUEM GRECTANGLE CASELLA
			
			if(GRectangleCasella.intersects(GRectangleCavaller)){
				
				GRect GRectCasella = casella.getGRectCasella();

				
				Color team = cavaller.getEquip();
				GRectCasella.setFilled(true);
				GRectCasella.setFillColor(team);
				casella.setEquip(team);
				calcularcaselles();
			}
			
		}
		
	}
	
	public void cavallerintersectscavallers(Cavaller cavaller){
		
		GRectangle GRectangleCavaller = cavaller.getGRectangleCavaller();
		
		for(Cavaller cavaller1: cavallers){
			
			if(!cavaller.equals(cavaller1)&&cavaller1.isRespira()){
				
				GRectangle GRectangleCavallercheck = cavaller1.getGRectangleCavaller();
				
				GRectangleCavallercheck.setBounds(GRectangleCavallercheck.getX(),GRectangleCavallercheck.getY(),GRectangleCavallercheck.getWidth()-20,GRectangleCavallercheck.getHeight()-20);
				
				if(GRectangleCavaller.intersects(GRectangleCavallercheck) && cavaller.getEquip()!=cavaller1.getEquip()){
					
					
					double percentatgecavaller = getpercentatge(cavaller.getEquip());
					double percentatgecavaller1 = getpercentatge(cavaller1.getEquip());
					double total = percentatgecavaller+percentatgecavaller1;
					
					double random = Math.floor(Math.random() * total);
					
					if(percentatgecavaller<=random){
						
						//Cavaller wins
						
						cavaller1.setRespira(false);
						cavaller1.getImatge().setVisible(false);
					}
					else{
						//Cavaller1 wins
						cavaller.setRespira(false);
						cavaller.getImatge().setVisible(false);
						
					}
				}
				
			}
			
		}
	}
	
	public boolean asignarcastellcavaller(Cavaller cavaller){
		
		if(cavaller.isTravelling()==false&&cavaller.isRespira()){
    		
    		int random =  rand.nextInt(castells.size()-1);
    		//System.out.println("Random: "+random);
        	double x = castells.get(random).getX();
        	double y = castells.get(random).getY();
        	
        	Casella castell = castells.get(random);
        	
        	cavaller.setDestinationCastle(x,y,castell);
        	
        	return true;

    	}
		else{
			return false;
		}
		
	}
	
	public boolean cavallerintersectscastell(Cavaller cavaller){
		
		GRectangle GRectangleCavaller = cavaller.getGRectangleCavaller();
		GRectangle GRectangleCastell = cavaller.getCastellDesti().getGrectangleCastell();
		
		/*MODIFY GRECTANGLE CASTELL*/
		
		GRectangleCastell.setBounds(GRectangleCastell.getX(), GRectangleCastell.getY(), GRectangleCastell.getWidth()-20, GRectangleCastell.getHeight()-20);
		
		/*END MODIFY GRECTANGLE CASTELL*/
		
		if(GRectangleCavaller.intersects(GRectangleCastell)){
			
			cavaller.setTravelling(false);
			return true;
		}else{
			return false;
		}
		
	}
}
