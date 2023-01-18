
package src;
import javax.swing.*;



import java.util.*;  

public class Palya extends JPanel{
    
	//Pálya adatok
	private int width;
	private int height;
	private int bomba = 10;
	private List<List<Mezo>> field = new ArrayList<List<Mezo>>();
	
    public Palya(Szint nehezseg){
		if(nehezseg == Szint.kezdo){
			width = 8;
			height = 8;
			bomba = 10;

		}else if (nehezseg == Szint.kozepes){
			width = 16;
			height = 16;
			bomba = 40;
			
		}else{
			width = 40;
			height = 16;
			bomba = 99;
		}
		field = new ArrayList<List<Mezo>>();
		for(int i = 0; i<height; i++){
			List<Mezo> row = new ArrayList<Mezo>();
			for(int j = 0; j < width; j++){
				row.add(new Mezo());
			}
			field.add(row);
		}
      	this.setBounds(0, 0, 1000, 1000);  
        
	}
	public int getH(){return height;}
	public int getW(){return width;}
	public int getBomb(){return bomba;}
	public int getxy(int y, int x){
		if(x < width && -1<x && y <height && -1<y){
			return field.get(y). get(x).getErtek();
		}
		return -1;
	}
	public void setxy(int y, int x, int z){
		if(x < width && -1<x && y <height && -1<y){
			field.get(y). get(x).setErtek(z);
		}
		
	}
	public Fedo getFedo(int y, int x){
		if(x < width && -1<x && y <height && -1<y){
		return field.get(y). get(x).getCover();
		}
		return Fedo.fedett;		
	}
	public void setFedo(int y, int x, Fedo z){
		if(x < width && -1<x && y <height && -1<y){
		field.get(y). get(x).setCover(z);
		}
	}	
	///
	public void general(int x, int y){
		
		field = new ArrayList<List<Mezo>>();
		for(int i = 0; i<height; i++){
			List<Mezo> row = new ArrayList<Mezo>();
			for(int j = 0; j < width; j++){
				row.add(new Mezo());
			}
			field.add(row);
		}
		Random random = new Random();
		int b = bomba;
		while(b>0){			
			int x0 = random.nextInt(width); 
			int y0 = random.nextInt(height);
			
			if((x0 == x && y0 == y) || (this.getxy(y0, x0) == 9)){//van kezdo katt vagy már van ott bomba
				continue;
			}			
			this.setxy(y0,x0,9);			
			for(int i = -1; i<2; i++){
				for(int j = -1; j<2; j++){
					if(!(i==0 && j==0)){//nem maga a kattintott							
							try{															
								if(this.getxy(y0+i, x0+j) !=9){									
									this.setxy(y0+i,x0+j,this.getxy(y0+i, x0+j)+1);																								
								}								
							}catch(java.lang.ArrayIndexOutOfBoundsException c){	
							}
					}
				}
			}
			b--;
		} 
	}	
	public void feltar(int y, int x){
		try{
				if (this.getxy(y, x) == 0 && this.getFedo(y, x) == Fedo.fedett){
					this.setFedo(y, x,Fedo.sima);
					
					for(int i = -1; i<2; i++){
						for(int j = -1; j<2; j++){
							if(!(i==0 && j==0)){//nem maga a kattintott								
								try{									
									feltar(y+j, x+i);										
								}catch(java.lang.ArrayIndexOutOfBoundsException c){
		
								}
							}
						}
					}
					
				}else{
					if(this.getFedo(y, x) == Fedo.fedett){
						this.setFedo(y, x,Fedo.sima);
					}
				}
			}catch(java.lang.ArrayIndexOutOfBoundsException e){
			}

			
	}
	public boolean nyert(){
		int z = 0;
		for (int i=0; i<height; i++){
			for (int j=0; j<width; j++){
				if (this.getFedo(i, j) == Fedo.sima){
					z++;
				}
			}
		}

		return z == width*height-bomba;
			
			

		
	}

}