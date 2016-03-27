package softwareGame;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testGetName() {
		
		Player p = new Player("Name");
		String s = "Name";
		
		assertEquals(s, p.getName());
	}

	@Test
	public void testGetDomino() {
		
		Player p = new Player("Name");
		
		Domino d1 = new Domino(6,6);
		Domino d2 = new Domino(0,0);
		Domino d3 = new Domino(1,4);
		
		Domino d4 = new Domino(6,6);
		Domino d5 = new Domino(0,0);
		Domino d6 = new Domino(1,4);
		
		p.hand.add(d1);
		p.hand.add(d2);
		p.hand.add(d3);
		
		assertEquals(d4.getLeftValue(), p.getDomino(0).getLeftValue());
		assertEquals(d4.getRightValue(), p.getDomino(0).getRightValue());

		assertEquals(d5.getLeftValue(), p.getDomino(1).getLeftValue());
		assertEquals(d5.getRightValue(), p.getDomino(1).getRightValue());

		assertEquals(d6.getLeftValue(), p.getDomino(2).getLeftValue());
		assertEquals(d6.getRightValue(), p.getDomino(2).getRightValue());

		assertNotEquals(d4.getLeftValue(), p.getDomino(1).getLeftValue());
		
	}

	@Test
	public void testAddDomino() {

		Player p = new Player("Name");
		
		Domino d1 = new Domino(6,6);
		Domino d2 = new Domino(0,0);
		Domino d3 = new Domino(1,4);
		
		Domino d4 = new Domino(6,6);
		Domino d5 = new Domino(0,0);
		Domino d6 = new Domino(1,4);
		
		p.addDomino(d1);
		p.addDomino(d2);
		p.addDomino(d3);
		
		assertEquals(d4.getLeftValue(), p.getDomino(0).getLeftValue());
		assertEquals(d4.getRightValue(), p.getDomino(0).getRightValue());

		assertEquals(d5.getLeftValue(), p.getDomino(1).getLeftValue());
		assertEquals(d5.getRightValue(), p.getDomino(1).getRightValue());

		assertEquals(d6.getLeftValue(), p.getDomino(2).getLeftValue());
		assertEquals(d6.getRightValue(), p.getDomino(2).getRightValue());

		assertNotEquals(d4.getLeftValue(), p.getDomino(1).getLeftValue());
	
	}

	@Test
	public void testRemoveDominoInt() {

		Player p = new Player("Name");
		
		Domino d1 = new Domino(6,6);
		Domino d2 = new Domino(0,0);
		
		Domino d3 = new Domino(0,0);
		Domino d4 = new Domino(6,6);
		
		p.addDomino(d1);
		p.addDomino(d2);

		p.removeDomino(0);
		
		assertEquals(d3.getLeftValue(), p.getDomino(0).getLeftValue());
		assertEquals(d3.getRightValue(), p.getDomino(0).getRightValue());

		assertNotEquals(d4.getLeftValue(), p.getDomino(0).getLeftValue());
		
	}

	@Test
	public void testRemoveDominoDomino() {

		Player p = new Player("Name");
		
		Domino d1 = new Domino(6,6);
		Domino d2 = new Domino(0,0);
		
		Domino d3 = new Domino(0,0);
		Domino d4 = new Domino(6,6);
		
		p.addDomino(d1);
		p.addDomino(d2);

		p.removeDomino(d1);
		
		assertEquals(d3.getLeftValue(), p.getDomino(0).getLeftValue());
		assertEquals(d3.getRightValue(), p.getDomino(0).getRightValue());

		assertNotEquals(d4.getLeftValue(), p.getDomino(0).getLeftValue());
		
	}

	@Test
	public void testNoMoreDominos() {

		Player p = new Player("Name");
		
		assertEquals(true, p.noMoreDominos());

		Domino d1 = new Domino(6,6);
		
		p.addDomino(d1);
		
		assertEquals(false, p.noMoreDominos());

		p.removeDomino(d1);

		assertEquals(true, p.noMoreDominos());
		
	}

	@Test
	public void testSearchDomino() {

		Player p = new Player("Name");
		
		Domino d1 = new Domino(6,6);
		Domino d2 = new Domino(0,0);
		Domino d3 = new Domino(1,4);
		
		p.addDomino(d1);
		p.addDomino(d2);
		p.addDomino(d3);

		assertEquals(true, p.searchDomino(6, 6));
		assertEquals(true, p.searchDomino(1, 4));
		assertEquals(true, p.searchDomino(0, 0));
		
		assertEquals(false, p.searchDomino(0, 6));
		
	}

	@Test
	public void testSearchForDouble() {
		Player p = new Player("Name");
		
		Domino d1 = new Domino(6,6);
		Domino d2 = new Domino(0,0);
		Domino d3 = new Domino(1,4);
		
		p.addDomino(d1);
		p.addDomino(d2);
		p.addDomino(d3);

		assertEquals(0, p.searchForDouble(6));
		assertEquals(1, p.searchForDouble(0));
		
		assertEquals(-1, p.searchForDouble(1));
		assertEquals(-1, p.searchForDouble(5));
		assertEquals(-1, p.searchForDouble(-6));

	}

}
