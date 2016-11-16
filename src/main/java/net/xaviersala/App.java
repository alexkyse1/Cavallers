package net.xaviersala;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.io.*;

/**
 * Plantilla base per fer un programa fent servir les llibreries
 * ACM.
 *
 */
public class App extends GraphicsProgram{
	
	
	private static int sizex = 0;
	private static int sizey = 0;
	private static final int numcomptes = 3; 
	private static final int numcavallers = 2*numcomptes;
	private static Color[] equips = {Color.red,Color.yellow,Color.green};
	private static final int imageX = 25;
	private static final int imageY = 25;
	private static final int maxX = 50;
	private static final int maxY = 50;
	private static final int velocitatCavaller = 2;
	
	List<Casella> castell = new ArrayList<>();
	List<Compte> comptes = new ArrayList<>();
	
	Random rand = new Random ();
	

	public final void run() {
		// TODO Auto-generated method stub
		App app = new App();
		getsize();
		
		
		
		
		addMouseListeners(); 

		
		/*GImage background = getbackground();*/
		
		double w = getcanvasX();
		double h = getcanvasY();
		int width = (int) w;
		int height = (int) h;
		
		
		setSize(width+10, height+10);
		
		Mapa mapa = new Mapa (w,h,(sizex*sizey));
		
		List<Casella> caselles = generarcaselles();
		List<Cavaller> cavallers = generarcavallers();
		List<Compte> comptes = generarcomptes(cavallers);
		
		
		mapa.afegirComptes(comptes);
		mapa.afegirCaselles(caselles);
		mapa.afegirCavallers(cavallers);
		mapa.afegirCastells(castell);
		
		clicaPerComencar();
	
		while(mapa.Start(app)){
			
		
		};
		
		System.out.println("Hey");
		endprogram();
		
		
		
		
		
	}
	
	/**
	 * Funció que genera els comptes
	 * @return
	 */
	
	private List<Compte> generarcomptes(List<Cavaller> cavallers){
		
		
		
		for(int i = 0; i<numcomptes;i++){
			
			comptes.add(new Compte(i,equips[i]));
		
			}
		
		for(Compte compte: comptes){
			
			for(Cavaller cavaller: cavallers){
				
				if(compte.getEquip()==cavaller.getEquip()){
					compte.afegircavallers(cavaller);
				}
			}
			
		}
		
		return comptes;
		
	}
	
	private List<Cavaller> generarcavallers(){
		
		List<Cavaller> cavallers = new ArrayList<>();
		
		int red = 0;
		int green = 0;
		int yellow = 0;
		
	
			while(red==green || green==yellow || yellow==red){
				green = rand.nextInt(castell.size()-1);
				yellow = rand.nextInt(castell.size()-1);
				red = rand.nextInt(castell.size()-1);
			}
			
		

		
		
		
		for(int i = 0; i<numcavallers;i++){
			
				cavallers.add(new Cavaller(i,equips[i/2],velocitatCavaller));
				
				if(i/2==0){
					GImage redknight = getredknight();
					redknight.setLocation(castell.get(red).getX(), castell.get(red).getY());
					cavallers.get(i).setImatge(redknight);

				}
				else if(i/2==1){
					GImage yellowknight = getyellowknight();
					yellowknight.setLocation(castell.get(yellow).getX(),castell.get(yellow).getY());
					cavallers.get(i).setImatge(yellowknight);
					
					
				}
				else if(i/2==2){
					GImage greenknight = getgreenknight();
					greenknight.setLocation(castell.get(green).getX(), castell.get(green).getY());
					cavallers.get(i).setImatge(greenknight);
					
					
				}
			
		}
		
		return cavallers;

	}
	
	
	public void getsize(){
		
		IODialog lector = new IODialog ();
		boolean check = false;
		while(!check){
			
			String text = lector.readLine("Quina Mida? Format: numeroXnumero (Max "+maxX+"x"+maxY+")");
			
			int x = Integer.parseInt(text.substring(0, text.indexOf("x")));
			int y = Integer.parseInt(text.substring(text.indexOf("x")+1));
			
					
			if(x<=maxX&&y<=maxY){
				
				sizex = x;
				sizey = y;
				check = true;
				
			}
			
		}
		
	}
	
	public double getcanvasX(){
		
		double x = sizex*imageX;
		
		return x;
		
		
	}
	
	public double getcanvasY(){
		
		double y = sizey*imageY;
		
		return y;
		
	}
	
	public List<Casella> generarcaselles(){


		GRect[][]grect = new GRect[sizey][sizex];
		
		
		List<Casella> caselles = new ArrayList<>();

		double x = 0;
		double y = 0;
	
		for(int i=0; i<sizey; i++){
	
		for (int j=0; j<sizex; j++){
    	
		        grect[i][j] = new GRect(x, y, imageX, imageY);
		        
		        caselles.add( new Casella (x,y,grect[i][j]));
		        add(grect[i][j]); 
		        if(j==(rand.nextInt(sizey)-3)){
		        
		        	 GImage background = getcastle();
				     background.setLocation(grect[i][j].getLocation());
				     
				     caselles.get(caselles.size()-1).setCastell(true);
				     caselles.get(caselles.size()-1).setCastellimg(background);
				     
				     castell.add(caselles.get(caselles.size()-1));
				     
				     add(background);
		        	
		        }

		        
		        x = x+imageX;

    	
    		}
    
    		x = 0;
    		y = y+imageY;

		}
	
	return caselles;

	}
	
	

	private GImage getcastle(){
		
		GImage castle = new GImage("castle.png");
		
		castle.setSize(imageX, imageY);
		
		add(castle);
		
		return castle;
		
	}
	
	private GImage getredknight(){
		
		GImage redknight = new GImage("redknight.png");
		
		redknight.setSize(imageX,imageY);
		
		add(redknight);
		
		return redknight;
	}
	
	private GImage getyellowknight(){
		
		GImage yellowknight = new GImage("yellowknight.png");
		
		yellowknight.setSize(imageX,imageY);
		
		add(yellowknight);
		
		return yellowknight;
		
	}
	
	private GImage getgreenknight(){
		
		GImage greenknight = new GImage("greenknight.png");
		
		greenknight.setSize(imageX,imageY);
		
		add(greenknight);
		
		return greenknight;
		
	}
	
	private double poscastellx(int random){
		
		double x = castell.get(random).getX();
		
		return x;
		
	}
	
	private double poscastelly(int random){

		
		double y = castell.get(random).getY();
		
		return y;
	}

	/**
	 * Clica per començar.
	 */
	private void clicaPerComencar() {
	    GLabel label = new GLabel("Clica per començar");
	    double x = (getWidth() - label.getWidth()) / 2;
	    double y = (getHeight() + label.getAscent()) / 2;
	    add(label, x, y);
	    waitForClick();
	    remove(label);
	}
	
	private void endprogram(){
		
	    IODialog dialog = new IODialog();
	    
	    String winner = getwinner();
	    
	    dialog.println("El compte "+winner+" ha guanyat!");
	
		
	}
	
	private String getwinner(){
		
	Color winner = Color.BLACK;
	
	String color = "";
	
		for(Compte compte: comptes){
			
			List<Cavaller> cavallerscompte = compte.getCavallers();
			
			for(Cavaller cavallerscomptelist: cavallerscompte){
				
				if(cavallerscomptelist.isRespira()){
					
					winner = compte.getEquip();
				}
			}
		
	}
		
		if(winner == Color.RED){
			color = "Vermell";
		}
		else if(winner == Color.YELLOW){
			color = "Groc";
		}
		else if(winner == Color.GREEN){
			color = "Verd";
		}
		
		return color;
	
	}
	
	 
	
}

