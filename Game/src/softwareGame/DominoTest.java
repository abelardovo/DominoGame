package softwareGame;

import static org.junit.Assert.*;

import org.junit.Test;

public class DominoTest {

	@Test
	public void testGetRightValue() {
		
		Domino d = new Domino(0,4);
		Domino d2 = new Domino(6,5);
		
		assertEquals(0,d.getRightValue());
		assertEquals(6,d2.getRightValue());
		assertNotEquals(4,d.getRightValue());
		assertNotEquals(5,d2.getRightValue());
		
	}

	@Test
	public void testGetLeftValue() {
		
		Domino d = new Domino(2,3);
		Domino d2 = new Domino(1,0);
		
		assertEquals(2,d.getRightValue());
		assertEquals(1,d2.getRightValue());
		assertNotEquals(3,d.getRightValue());
		assertNotEquals(0,d2.getRightValue());
	
	}

	@Test
	public void testIsThereDouble() {
		
		Domino d0 = new Domino(0,0);
		Domino d1 = new Domino(1,1);
		Domino d2 = new Domino(2,2);
		Domino d3 = new Domino(3,3);
		Domino d4 = new Domino(4,4);
		Domino d5 = new Domino(5,5);
		Domino d6= new Domino(6,6);
		
		Domino d7 = new Domino(5,1);
		Domino d8 = new Domino(2,4);

		assertTrue(d0.isThereDouble());
		assertTrue(d1.isThereDouble());
		assertTrue(d2.isThereDouble());
		assertTrue(d3.isThereDouble());
		assertTrue(d4.isThereDouble());
		assertTrue(d5.isThereDouble());
		assertTrue(d6.isThereDouble());
		
		assertFalse(d7.isThereDouble());
		assertFalse(d8.isThereDouble());
		
	}

}
