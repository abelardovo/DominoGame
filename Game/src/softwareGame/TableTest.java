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

	@SuppressWarnings("static-access")
	@Test
	public void testGetRightValue() {
	
		Table t = Table.getTable();
		
		assertEquals(-1, t.getRightValue());
		assertNotEquals(1, t.getRightValue());
		
		t.setRight(1);
		assertEquals(1, t.getRightValue());
		
		t.tab=null;
	}

	@SuppressWarnings("static-access")
	@Test
	public void testGetLeftValue() {
		
		Table t = Table.getTable();
		
		assertEquals(-1, t.getLeftValue());
		assertNotEquals(1, t.getLeftValue());
		
		t.setLeft(1);
		
		assertEquals(1, t.getLeftValue());
		
		t.tab=null;
	}

	@SuppressWarnings("static-access")
	@Test
	public void testSetValue() {

		Table t = Table.getTable();
		Domino d = new Domino(0,6);
		
		assertEquals(-1, t.getLeftValue());
		assertEquals(-1, t.getRightValue());
		assertNotEquals(1, t.getLeftValue());
		
		
		t.setValue(d);
		
		assertEquals(6, t.getLeftValue());
		assertEquals(0, t.getRightValue());

		assertNotEquals(-1, t.getLeftValue());

		t.tab=null;
	}

	@SuppressWarnings("static-access")
	@Test
	public void testCanPlay() {

		Table t = Table.getTable();
		Domino d1 = new Domino(0,6);
		Domino d2 = new Domino(1,4);
		Domino d3 = new Domino(0,4);
		Domino d4 = new Domino(6,4);

		t.setValue(d1);
		
		assertEquals(true, t.canPlay(d1));
		assertEquals(true, t.canPlay(d3));
		assertEquals(true, t.canPlay(d4));
		
		assertEquals(false, t.canPlay(d2));

		t.tab=null;
	}

	@SuppressWarnings("static-access")
	@Test
	public void testInitialPlay() {

		Table t = Table.getTable();
		Domino d1 = new Domino(0,6);
		
		t.initialPlay(d1);
		
		assertEquals(6, t.board.get(0).getLeftValue());
		assertEquals(0, t.board.get(0).getRightValue());
		
		assertNotEquals(1,t.board.get(0).getLeftValue());
		
		assertEquals(6, t.getLeftValue());
		assertEquals(0, t.getRightValue());
		
		assertNotEquals(1,t.getLeftValue());
		
		t.tab=null;
	}

	@Test
	public void testPlay() {

		Table t = Table.getTable();
		
		System.out.println("First singletonA:  " + t);
		System.out.println("First singletonA data value =   " +t.getLeftValue());
		System.out.println("First singletonA data value =   " +t.getRightValue());
		
		Domino d1 = new Domino(0,6);
		Domino d2 = new Domino(0,1);

		t.setValue(d1);
		
		System.out.println("AfterSetValue:\nFirst singletonA:  " + t);
		System.out.println("First singletonA left value =   " +t.getLeftValue());
		System.out.println("First singletonA right value =   " +t.getRightValue());
		
		t.initialPlay(d1);

		System.out.println("AfterInitialPlay:\nFirst singletonA:  " + t);
		System.out.println("First singletonA data value =   " +t.getLeftValue());
		System.out.println("First singletonA data value =   " +t.getRightValue());
		
		assertEquals(6, t.board.get(0).getLeftValue());
		assertEquals(0, t.board.get(0).getRightValue());
		
		assertNotEquals(1,t.board.get(0).getLeftValue());		
		
		assertEquals(6, t.getLeftValue());
		assertEquals(0, t.getRightValue());
		
		assertNotEquals(1,t.getLeftValue());
		
		t.play(d2);

		System.out.println("AfterSetValue:\nFirst singletonA:  " + t);
		System.out.println("First singletonA left value =   " +t.getLeftValue());
		System.out.println("First singletonA right value =   " +t.getRightValue());
		
		assertEquals(6, t.getLeftValue());
		assertEquals(1, t.getRightValue());
		
		assertNotEquals(4,t.getLeftValue());

	}

}
