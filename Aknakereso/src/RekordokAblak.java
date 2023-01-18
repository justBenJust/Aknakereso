
package src;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class RekordokAblak implements ActionListener{
    JFrame ablak = new JFrame("Rekordok");
    JButton menube = new JButton("Menu");
    Rekordok r;
    
    RekordokAblak(){
        
        try{
            String a ="rekordok";
            ObjectInputStream iOOS = new ObjectInputStream(new FileInputStream(a));
             r = (Rekordok) iOOS.readObject();
             iOOS.close();
            }catch(Exception e){
                r = new Rekordok(999, 999, 999);
            }
        
        //visszagomb
        menube.setBounds(0,0,100,40);
        menube.setFocusable(false);
        menube.addActionListener(this);
        
        ablak.add(menube);
        ablak.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ablak.setSize(420,420);
        ablak.setLayout(null);
        ablak.setVisible(true);
        
        JLabel ke = new JLabel();
        ke.setBounds(100,100,100,50);
        ke.setText("Kezdo");
        ablak.add(ke);

        JLabel ke1 = new JLabel();
        ke1.setBounds(150,100,100,50);
        ke1.setText(Integer.toString(r.getKezdo()));
        ablak.add(ke1);

        JLabel ko = new JLabel();
        ko.setBounds(100,150,100,50);
        ko.setText("Kozepes");
        ablak.add(ko);

        JLabel ko1 = new JLabel();
        ko1.setBounds(150,150,100,50);
        ko1.setText(Integer.toString(r.getKozepes()));
        ablak.add(ko1);

        JLabel ha = new JLabel();
        ha.setBounds(100,200,100,50);
        ha.setText("halado");
        ablak.add(ha);
        JLabel ha1 = new JLabel();
        ha1.setBounds(150,200,100,50);
        ha1.setText(Integer.toString(r.getHalado()));
        ablak.add(ha1);
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==menube) {
          ablak.setVisible(false);
          new Menu();
        } 
    }
}   