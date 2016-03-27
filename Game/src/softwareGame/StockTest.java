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

	@Test
	public void testIsEmpty() {
		Stock s = new Stock();
		Domino d;
		
		assertFalse(s.isEmpty());
		
		for(int i=0;i<28;i++){
			d = s.draw();
		}
		
		assertTrue(s.isEmpty());
	}

	@Test
	public void testDraw() {
		Stock s = new Stock();
		Domino d;
		int i =0;
		boolean b = false;
		
		assertEquals(28,s.pile.size());
		
		d = s.draw();
		
		assertEquals(27,s.pile.size());
		assertFalse(s.pile.contains(d));
		
		while(i<s.pile.size()){
			if((d.getLeftValue() == s.pile.get(i).getLeftValue() && d.getRightValue() == s.pile.get(i).getRightValue())
				|| (d.getLeftValue() == s.pile.get(i).getRightValue() && d.getRightValue() == s.pile.get(i).getLeftValue())){
				b = true;
			}
			i++;
		}

		assertFalse(b);

	}

}
