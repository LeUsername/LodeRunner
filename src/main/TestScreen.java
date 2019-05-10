package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import contracts.ScreenContract;
import error.PreCondError;
import impl.ScreenImpl;
import service.Cell;
import service.ScreenService;

public class TestScreen {
	private ScreenService ecran;
	protected final static int HEIGHT = 10;
	protected final static int WIDTH = 50;

	public TestScreen() {
		ecran = new ScreenContract(new ScreenImpl());
	}

	@Before
	public void beforeTests() {
		ecran = new ScreenContract(new ScreenImpl());
	}

	@Test
	public void initPositif() {
		ecran.init(WIDTH, HEIGHT);
		assertEquals(WIDTH, ecran.getWidth());
		assertEquals(HEIGHT, ecran.getHeight());
		for (int i = 0; i < ecran.getWidth(); i++) {
			for (int j = 0; j < ecran.getHeight(); j++) {
				assertEquals(Cell.EMP, ecran.getCellNature(i, j));
			}
		}
	}

	@Test(expected = PreCondError.class)
	public void initNegatif() {
		ecran.init(0, HEIGHT);
	}

	@Test
	public void getCellNaturePositif() {
		ecran.init(WIDTH, HEIGHT);
		assertTrue(Cell.EMP == ecran.getCellNature(WIDTH / 5, HEIGHT / 5));
	}

	@Test(expected = PreCondError.class)
	public void getCellNatureNegatif() {
		ecran.init(WIDTH, HEIGHT);
		assertTrue(Cell.EMP == ecran.getCellNature(WIDTH + 5, HEIGHT / 5));
	}

	@Test
	public void digPositif() {
		ecran.init(WIDTH, HEIGHT);
		ecran.Fill(WIDTH / 2, HEIGHT / 2);
		assertEquals(Cell.PLT, ecran.getCellNature(WIDTH / 2, HEIGHT / 2));
		ecran.Dig(WIDTH / 2, HEIGHT / 2);
		assertEquals(Cell.HOL, ecran.getCellNature(WIDTH / 2, HEIGHT / 2));
	}

	@Test(expected = PreCondError.class)
	public void digNegatif() {
		ecran.init(WIDTH, HEIGHT);
		ecran.Dig(WIDTH / 2, HEIGHT / 2);
	}

	@Test
	public void fillPositif() {
		ecran.init(WIDTH, HEIGHT);
		ecran.Fill(WIDTH / 2, HEIGHT / 2);
		assertEquals(Cell.PLT, ecran.getCellNature(WIDTH / 2, HEIGHT / 2));
	}

	@Test(expected = PreCondError.class)
	public void fillNegatif() {
		ecran.init(WIDTH, HEIGHT);
		ecran.Fill(WIDTH / 2, HEIGHT / 2);
		assertEquals(Cell.PLT, ecran.getCellNature(WIDTH / 2, HEIGHT / 2));
		ecran.Fill(WIDTH / 2, HEIGHT / 2);
	}
}