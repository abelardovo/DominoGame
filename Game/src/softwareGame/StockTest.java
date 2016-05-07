package softwareGame;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * CLASS STOCK TEST
 * 
 * @author      Abelardo Valino <abelardo.valino_ovalle@telecom-sudparis.eu>
 * @author		Andres Gonzalez <andres.gonzalez_arria@telecom-sudparis.eu>
 * @version     1.0                 (current version number of program)
 * @since       2016-03-26          (the version of the package this class was first added to)
 */
public class StockTest {

	@SuppressWarnings("static-access")
	@Test
	public void testIsEmpty() {
		
		Stock s = Stock.getStock(0);
		
		assertFalse(s.isEmpty());

		for(int i=0;i<28;i++){
			s.draw();
			
		}
		
		assertTrue(s.isEmpty());
	
		s.sto = null;
	}

	@SuppressWarnings("static-access")
	@Test
	public void testDraw() {
		Stock s = Stock.getStock(0);
		Domino d;
		int i =0;
		boolean b = false;
		
		assertEquals(28,s.pile.size());
		
		d = s.draw();
		
		assertEquals(27,s.pile.size());
		assertFalse(s.pile.contains(d));
		
		while(i<s.pile.size()){
			if((d.getLeftValue().equals(((Domino) s.pile.get(i)).getLeftValue()) && d.getRightValue().equals(((Domino) s.pile.get(i)).getRightValue()))
				|| (d.getLeftValue().equals(((Domino) s.pile.get(i)).getRightValue()) && d.getRightValue().equals(((Domino) s.pile.get(i)).getLeftValue()))){
				b = true;
			}
			i++;
		}

		assertFalse(b);

		s.sto = null;

	}
}
