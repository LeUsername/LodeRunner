package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import contracts.EditableScreenContract;
import error.PreCondError;
import impl.EditableScreenImpl;
import service.Cell;
import service.EditableScreenService;

public class TestEditableScreen extends TestScreen {

	private EditableScreenService ecran;

	@Before
	public void beforeTests() {
		ecran = new EditableScreenContract(new EditableScreenImpl());
	}

	@Test
	public void setNaturePositif() {
		ecran.init(WIDTH, HEIGHT);
		ecran.setNature(WIDTH - 1, HEIGHT - 1, Cell.LAD);
		ecran.setNature(WIDTH / 2, HEIGHT / 2, Cell.HDR);
		ecran.setNature(WIDTH / 3, HEIGHT / 2, Cell.HOL);
		ecran.setNature(WIDTH / 10, HEIGHT / 10, Cell.PLT);
		assertEquals(Cell.LAD, ecran.getCellNature(WIDTH - 1, HEIGHT - 1));
		assertEquals(Cell.HDR, ecran.getCellNature(WIDTH / 2, HEIGHT / 2));
		assertEquals(Cell.HOL, ecran.getCellNature(WIDTH / 3, HEIGHT / 2));
		assertEquals(Cell.PLT, ecran.getCellNature(WIDTH / 10, HEIGHT / 10));
	}

	@Test(expected = PreCondError.class)
	public void setNatureNegatif() {
		ecran.init(WIDTH, HEIGHT);
		ecran.setNature(WIDTH * 2, HEIGHT * 2, Cell.LAD);
		assertEquals(Cell.LAD, ecran.getCellNature(WIDTH * 2, HEIGHT * 2));
	}

	@Test
	public void digPositif() {
		ecran.init(WIDTH, HEIGHT);
		ecran.setNature(1, 1, Cell.PLT);
		ecran.Dig(1, 1);
		assertEquals(Cell.HOL, ecran.getCellNature(1, 1));
	}

	@Test
	public void fillPositif() {
		ecran.init(HEIGHT, WIDTH);
		ecran.setNature(1, 1, Cell.HOL);
		ecran.Fill(1, 1);
		assertEquals(Cell.PLT, ecran.getCellNature(1, 1));
	}

	@Test
	public void isPlayablePositif() {
		ecran.init(WIDTH, HEIGHT);
		for (int i = 0; i < ecran.getWidth(); i++) {
			ecran.setNature(i, 0, Cell.MTL);
		}
		assertTrue(ecran.isPlayable());
		// ecran.setNature(5, 5, Cell.LAD);
		// ecran.setNature(5, 6, Cell.LAD);
		// ecran.setNature(5, 7, Cell.LAD);
		// ecran.setNature(6, 6, Cell.HDR);
		// for (int i = 0; i < ecran.getWidth(); i++) {
		// ecran.setNature(i, 1, Cell.PLT);
		// }
		// for (int y = ecran.getHeight() - 1; y >= 0; y--) {
		// for (int x = ecran.getWidth() - 1; x >= 0; x--) {
		// switch (ecran.getCellNature(x, y)) {
		// case EMP:
		// System.out.print(" ");
		// break;
		// case LAD:
		// System.out.print("H");
		// break;
		// case HOL:
		// System.out.print("U");
		// break;
		// case PLT:
		// System.out.print("-");
		// break;
		// case HDR:
		// System.out.print("T");
		// break;
		// case MTL:
		// System.out.print("M");
		// break;
		// }
		// }
		// System.out.print("\n");
		// }
	}

	@Test
	public void isPlayableNegatif() {
		ecran.init(WIDTH, HEIGHT);
		for (int i = 0; i < ecran.getWidth(); i++) {
			ecran.setNature(i, 0, Cell.HOL);
		}
		assertTrue(!(ecran.isPlayable()));
	}
}
