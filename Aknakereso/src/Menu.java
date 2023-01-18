package src;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;


public class Menu implements ActionListener{

    private JFrame frame = new JFrame("Menu");
    private JButton Kezdo = new JButton("Kezdo");
    private JButton Kozepes = new JButton("Kozepes");
    private JButton Halado = new JButton("Halado");
    private JButton Rekordok = new JButton("Rekordok");
    private JLabel Szoveg = new JLabel();
    private Szint s;
    
    Menu(){
        

        Szoveg.setText("AknaKereső Játék");
        
        Szoveg.setBounds(100,0,100,50);
        Szoveg.setText("Aknakereső");
        Szoveg.setFont(new Font("Serif", Font.PLAIN, 20));
        frame.add(Szoveg);

        frame.add(Szoveg);
        Kezdo.setBounds(100,120,100,40);
        Kezdo.setFocusable(false);
        Kezdo.addActionListener(this);
        
        frame.add(Kezdo);

        Kozepes.setBounds(100,160,100,40);
        Kozepes.setFocusable(false);
        Kozepes.addActionListener(this);
        frame.add(Kozepes);

        Halado.setBounds(100,200,100,40);
        Halado.setFocusable(false);
        Halado.addActionListener(this);
        
        frame.add(Halado);
        
        Rekordok.setBounds(100,280,100,40);
        Rekordok.setFocusable(false);
        Rekordok.addActionListener(this);
        frame.add(Rekordok);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,420);
        frame.setLayout(null);
        frame.setVisible(true);
  
    }

 @Override
 public void actionPerformed(ActionEvent e) {
  
    if(e.getSource()==Kezdo) {
      s = Szint.kezdo;
      frame.setVisible(false);
      new PalyaAblak(s);
    
    }
    if(e.getSource()==Kozepes) {
      s = Szint.kozepes;
      frame.setVisible(false);
      new PalyaAblak(s);
      
    }
    if(e.getSource()==Halado) {
      s = Szint.halado;
      frame.setVisible(false);
      new PalyaAblak(s);

    }
    if(e.getSource()==Rekordok) {
      frame.setVisible(false);
      new RekordokAblak();
    }
  }
}