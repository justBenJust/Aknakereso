package src;
import javax.swing.*;

import java.io.FileOutputStream;
  
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class PalyaAblak extends JPanel implements ActionListener{
    private JFrame ablak = new JFrame("Aknakereső");
    private JLabel bombaLabel = new JLabel();
    private JButton menube = new JButton("Menu");
    private JButton ujJatek = new JButton("Új játék");
    private JPanel panel = new JPanel();
    private Palya p;
    private Rekordok r;
    private long startIdo = 0;
    private float ido;
    private int kiirtBomba;
    private boolean vege = false;
    private boolean elsoKatt = true;
    private Timer timer;
  
    PalyaAblak(Szint nehezseg){    
        try{
          String a ="rekordok";
          ObjectInputStream iOOS = new ObjectInputStream(new FileInputStream(a));
          r = (Rekordok) iOOS.readObject();
          iOOS.close();
        }catch(Exception e){
          r = new Rekordok(999, 999, 999);
        }
        timer = new Timer(10, this); 
        timer.start();
       
      
        //visszagomb
        menube.setBounds(0,0,100,40);
        menube.setFocusable(false);
        menube.addActionListener(this);
        ablak.add(menube);
        
        ujJatek.setBounds(100,0,100,40);
        ujJatek.setFocusable(false);
        ujJatek.addActionListener(this);
        ablak.add(ujJatek);
        
        //az ablak
        ablak.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ablak.setSize(420,420);
        ablak.setLayout(null);
        ablak.add(panel);
        
        bombaLabel.setVerticalTextPosition(JLabel.TOP);
        bombaLabel.setHorizontalTextPosition(JLabel.RIGHT);
        ablak.add(bombaLabel);
        
        
        if(nehezseg == Szint.kezdo) {
          ablak.setSize(300, 400);
          
        }
        if(nehezseg==Szint.kozepes) {
          ablak.setSize(500, 500);
          
        }
        if(nehezseg==Szint.halado) {
          ablak.setSize(1000, 500);
        }
        p = new Palya(nehezseg);
        kiirtBomba = p.getBomb();
        
        ablak.add(this);
        this.setBounds(20,100,p.getW()*21+100,p.getH()*21);
        
        
        MouseAdapter palyaKatt = new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e){
            int x = (e.getX()-26)/21;//játék koordinátákra fordítás
            int y = (e.getY()-130)/21;         
            if (!vege){
              //bent katt
              if(e.getX()>26 && e.getX()<p.getW()*21+20 && e.getY()>130 && e.getY()<p.getH()*21+130){
                //balkatt
                if(e.getButton()== MouseEvent.BUTTON1){
                  if(elsoKatt){
                      p.general(x, y);
                      elsoKatt = false;
                      kiirtBomba = p.getBomb();
                      startIdo = System.currentTimeMillis();
                  }
                  if(p.getxy(y, x) == 9)
                  {
                      vege();
                  }else{
                    p.feltar(y, x);  
                  }

                 
                }else if(e.getButton()== MouseEvent.BUTTON3){//jobbkatt zászló
                  if(p.getFedo(y, x)==Fedo.zaszlos){
                      p.setFedo(y,x,Fedo.fedett);
                      kiirtBomba++;
                  }else if(p.getFedo(y, x)==Fedo.fedett){
                      p.setFedo(y,x,Fedo.zaszlos);
                      kiirtBomba--;
                  }
                }
              }
            
            }
            if(p.nyert()){
                ido = (System.currentTimeMillis()-startIdo)/1000F;
                vege = true;
                int i = Math.round(ido);  

                if(nehezseg == Szint.kezdo && r.getKezdo()>ido){
                  r.setKezdo(i);
                }
                if(nehezseg == Szint.kozepes && r.getKozepes()>ido){
                  r.setKozepes(i);
                }
                if(nehezseg == Szint.halado && r.getHalado()>ido){
                  r.setHalado(i);
                }
                try {
                  String a ="rekordok";
                  ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(a));
                  OOS.writeObject(r);
                  OOS.close();
                }catch (IOException o) {

                }
            }
          }
        };
        ablak.addMouseListener(palyaKatt);       
        ablak.setVisible(true);        
      }
    @Override
    public void paint(Graphics g){      
      g.setColor(Color.white);
      g.fillRect(0, 0, p.getW()*21,p.getH()*21);
      for(int x = 0; x < p.getW(); x++){
        for(int y = 0; y < p.getH(); y++){
          int x1 = x*21;
          int y1 = y*21;
          if(p.getFedo(y, x) == Fedo.fedett){//fedett
              g.setColor(Color.black);
              g.fillRect(x1, y1, 20,20);
          }else if(p.getFedo(y, x) == Fedo.zaszlos){//zászlos
              g.setColor(Color.pink);
              g.fillRect(x1, y1, 20,20);
          }else{//fedetelen
              if(p.getxy(y, x) == 9){//bomba
                g.setColor(Color.red);
                g.fillRect(x1, y1, 20,20);
              }else{//sima                
                g.setColor(Color.white);
                g.fillRect(x1, y1, 20,20);
                g.setColor(Color.darkGray);                
                g.drawString(Integer.toString(p.getxy(y, x)), x1, y1+15);
              }
          }
        }
      }
      g.setColor(Color.white);
      g.fillRect(p.getW()*21, 0,100 ,40);
      
      g.fillRect(p.getW()*21, 40,100 ,40);
      g.setColor(Color.black);
      g.setFont(new Font("Serif", Font.PLAIN, 24));
      g.drawString(Integer.toString(kiirtBomba), p.getW()*21+20, 30);
      if(!(vege || elsoKatt)){
        g.drawString(Float.toString((System.currentTimeMillis()-startIdo)/1000F), p.getW()*21+20, 70);
      }else{
        g.drawString("0", p.getW()*21+20, 70);
      }
    }
    public void vege(){
      vege = true;
      for(int i = 0; i < p.getH(); i++){
        for(int j = 0; j < p.getW(); j++){
          if(p.getFedo(i, j) == Fedo.fedett){
            p.setFedo(i, j, Fedo.sima);
          }
        }
      }
    }
    public void actionPerformed(ActionEvent e) {
        ablak.repaint();
        bombaLabel.setText(Integer.toString(kiirtBomba));
        if(e.getSource()==menube) {
          ablak.setVisible(false);
          new Menu();
        } else if(e.getSource()==ujJatek){
          for(int i = 0; i < p.getH(); i++){
            for(int j = 0; j < p.getW(); j++){
              p.setFedo(i, j, Fedo.fedett);
            }
          }
          elsoKatt = true;
          vege = false;
        }
    }
}