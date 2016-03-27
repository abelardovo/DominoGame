package softwareGame;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * CLASS TABLE TEST
 * 
 * @author      Abelardo Valino <abelardo.valino_ovalle@telecom-sudparis.eu>
 * @author		Andres Gonzalez <andres.gonzalez_arria@telecom-sudparis.eu>
 * @version     1.0                 (current version number of program)
 * @since       2016-03-26          (the version of the package this class was first added to)
 */
public class TableTest {

	@Test
	public void testGetRightValue() {
		
		Table t = new Table();
		
		assertEquals(-1, t.getRightValue());
		assertNotEquals(1, t.getRightValue());
		
		t.right = 1;
		
		assertEquals(1, t.getRightValue());
		
	}

	@Test
	public void testGetLeftValue() {
	
		Table t = new Table();
		
		assertEquals(-1, t.getLeftValue());
		assertNotEquals(1, t.getLeftValue());
		
		t.left = 1;
		
		assertEquals(1, t.getLeftValue());
		
	}

	@Test
	public void testSetValue() {

		Table t = new Table();
		Domino d = new Domino(0,6);
		
		assertEquals(-1, t.getLeftValue());
		assertEquals(-1, t.getRightValue());
		assertNotEquals(1, t.getLeftValue());
		
		
		t.setValue(d);
		
		assertEquals(6, t.getLeftValue());
		assertEquals(0, t.getRightValue());

		assertNotEquals(-1, t.getLeftValue());

		
	}

	@Test
	public void testCanPlay() {

		Table t = new Table();
		Domino d1 = new Domino(0,6);
		Domino d2 = new Domino(1,4);
		Domino d3 = new Domino(0,4);
		Domino d4 = new Domino(6,4);

		t.setValue(d1);
		
		assertEquals(true, t.canPlay(d1));
		assertEquals(true, t.canPlay(d3));
		assertEquals(true, t.canPlay(d4));
		
		assertEquals(false, t.canPlay(d2));

	}

	@Test
	public void testInitialPlay() {

		Table t = new Table();
		Domino d1 = new Domino(0,6);
		
		t.initialPlay(d1);
		
		assertEquals(6, t.board.get(0).getLeftValue());
		assertEquals(0, t.board.get(0).getRightValue());
		
		assertNotEquals(1,t.board.get(0).getLeftValue());
		
		assertEquals(6, t.getLeftValue());
		assertEquals(0, t.getRightValue());
		
		assertNotEquals(1,t.getLeftValue());
		
	}

	@Test
	public void testPlay() {

		Table t = new Table();
		Domino d1 = new Domino(0,6);
		Domino d2 = new Domino(0,1);

		t.setValue(d1);
		t.play(d1);
		
		assertEquals(6, t.board.get(0).getLeftValue());
		assertEquals(0, t.board.get(0).getRightValue());
		
		assertNotEquals(1,t.board.get(0).getLeftValue());
		
		assertEquals(6, t.getLeftValue());
		assertEquals(0, t.getRightValue());
		
		assertNotEquals(1,t.getLeftValue());
		
		t.play(d2);

		assertEquals(6, t.board.get(0).getLeftValue());
		assertEquals(1, t.board.get(0).getRightValue());
		
		assertNotEquals(4,t.board.get(0).getLeftValue());
		
		assertEquals(6, t.getLeftValue());
		assertEquals(1, t.getRightValue());
		
		assertNotEquals(4,t.getLeftValue());

	}

}
