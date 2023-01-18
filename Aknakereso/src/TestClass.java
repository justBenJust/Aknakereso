package src;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
public class TestClass {
    @Test
    public void PalyaInit(){
        Palya p = new Palya(Szint.kezdo);
        assertNotNull(p);
        assertEquals(10, p.getBomb());
        assertEquals(8, p.getW());
        p = new Palya(Szint.kozepes);
        assertEquals(16, p.getH());
        assertEquals(p.getFedo(5, 5), Fedo.fedett);
        p.setFedo(5, 5, Fedo.sima);
        assertEquals(p.getFedo(5, 5), Fedo.sima);
        assertEquals(p.getxy(1, 1), 0);
        p.setxy(1,1, 123);
        assertEquals(p.getxy(1, 1), 123);
        
        Mezo m = new Mezo();
        assertEquals(m.getCover(), Fedo.fedett);
        m.setCover(Fedo.zaszlos);
        assertEquals(m.getCover(), Fedo.zaszlos);
        assertEquals(m.getErtek(), 0);
        m.setErtek(5);
        assertEquals(m.getErtek(), 5);
    }
    @Test
    public void Generalas(){
        Palya p = new Palya(Szint.kezdo);
        p.general(0, 0);
        assertNotEquals(p.getxy(0, 0), 9);
        p.general(5, 7);
        assertEquals(p.getFedo(5, 7), Fedo.fedett);
        
    }

    @Test
    public void Menu(){
       Menu me = new Menu();
       assertNotNull(me);
      
    }
    @Test
    public void Nyert(){
        Palya p = new Palya(Szint.kezdo);
        p.general(0, 0);
       assertFalse(p.nyert());
    }
    
       
     
}
