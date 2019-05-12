package main;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import contracts.CharacterContract;
import contracts.EditableScreenContract;
import contracts.EnvironmentContract;
import error.PreCondError;
import impl.CellContentImpl;
import impl.CharacterImpl;
import impl.EditableScreenImpl;
import impl.EnvironmentImpl;
import impl.ItemImpl;
import service.Cell;
import service.CharacterService;
import service.Cle;
import service.EditableScreenService;
import service.EnvironmentService;
import service.ItemType;

public class TestCharacter {
	private CharacterService character;
	protected EnvironmentService ecran;
	protected HashMap<Cle, CellContentImpl> cellulesContent;
	protected final static int HEIGHT = 10;
	protected final static int WIDTH = 10;
	private final int NB_TRESORS = 3;

	public TestCharacter() {
		character = new CharacterContract(new CharacterImpl());
	}

	@Before
	public void beforeTests() {
		character = new CharacterContract(new CharacterImpl());
	}

	public void setEcran(EnvironmentService ecran) {
		this.ecran = ecran;
	}

	@Test
	public void initPositif() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		EditableScreenService editableScreen = new EditableScreenContract(new EditableScreenImpl());
		editableScreen.init(WIDTH, HEIGHT);
		for (int i = 0; i < editableScreen.getWidth(); i++) {
			editableScreen.setNature(i, 0, Cell.MTL);
			editableScreen.setNature(i, 1, Cell.PLT);
		}
		cellulesContent = new HashMap<>();
		for (int i = 0; i < NB_TRESORS; i++) {
			CellContentImpl cellule = new CellContentImpl(new ArrayList<>(), new ArrayList<>());
			cellule.getItem().add(new ItemImpl(ItemType.TRESOR, WIDTH / 2, HEIGHT / 2));
			cellulesContent.put(new Cle(i, 2), cellule);
		}
		ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
		((CharacterContract) character).init(2, 5, ecran);
		assertEquals(2, character.getWdt());
		assertEquals(5, character.getHgt());
		assertEquals(ecran, character.getEnvi());
	}

	/**
	 * Init dans MTL
	 */
	@Test(expected = PreCondError.class)
	public void initNegatif() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		EditableScreenService editableScreen = new EditableScreenContract(new EditableScreenImpl());
		editableScreen.init(WIDTH, HEIGHT);
		for (int i = 0; i < editableScreen.getWidth(); i++) {
			editableScreen.setNature(i, 0, Cell.MTL);
			editableScreen.setNature(i, 1, Cell.PLT);
		}
		cellulesContent = new HashMap<>();
		for (int i = 0; i < NB_TRESORS; i++) {
			CellContentImpl cellule = new CellContentImpl(new ArrayList<>(), new ArrayList<>());
			cellule.getItem().add(new ItemImpl(ItemType.TRESOR, WIDTH / 2, HEIGHT / 2));
			cellulesContent.put(new Cle(i, 2), cellule);
		}
		ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
		((CharacterContract) character).init(5, 0, ecran);
	}

	@Test
	public void goLeftPositif1() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		EditableScreenService editableScreen = new EditableScreenContract(new EditableScreenImpl());
		editableScreen.init(WIDTH, HEIGHT);
		for (int i = 0; i < editableScreen.getWidth(); i++) {
			editableScreen.setNature(i, 0, Cell.MTL);
			editableScreen.setNature(i, 1, Cell.PLT);
		}
		cellulesContent = new HashMap<>();
		for (int i = 0; i < NB_TRESORS; i++) {
			CellContentImpl cellule = new CellContentImpl(new ArrayList<>(), new ArrayList<>());
			cellule.getItem().add(new ItemImpl(ItemType.TRESOR, WIDTH / 2, HEIGHT / 2));
			cellulesContent.put(new Cle(i, 2), cellule);
		}
		ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
		((CharacterContract) character).init(WIDTH / 2, 2, ecran);
		character.GoLeft();
		assertEquals((WIDTH / 2) - 1, character.getWdt());
		assertEquals(2, character.getHgt());
	}

	/**
	 * Test goLeft chute libre EMP->EMP
	 */
	@Test
	public void goLeftPositif2() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		EditableScreenService editableScreen = new EditableScreenContract(new EditableScreenImpl());
		editableScreen.init(WIDTH, HEIGHT);
		for (int i = 0; i < editableScreen.getWidth(); i++) {
			editableScreen.setNature(i, 0, Cell.MTL);
			editableScreen.setNature(i, 1, Cell.PLT);
		}
		cellulesContent = new HashMap<>();
		for (int i = 0; i < NB_TRESORS; i++) {
			CellContentImpl cellule = new CellContentImpl(new ArrayList<>(), new ArrayList<>());
			cellule.getItem().add(new ItemImpl(ItemType.TRESOR, WIDTH / 2, HEIGHT / 2));
			cellulesContent.put(new Cle(i, 2), cellule);
		}
		ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
		((CharacterContract) character).init(5, 6, ecran);
		 for (int y = ecran.getHeight() - 1; y >= 0; y--) {
		 for (int x = 0; x < ecran.getWidth(); x++) {
		 switch (ecran.getCellNature(x, y)) {
		 case EMP:
		 if (character.getWdt() == x && character.getHgt() == y)
		 System.out.print("@");
		 else
		 System.out.print(" ");
		 break;
		 case LAD:
		 if (character.getWdt() == x && character.getHgt() == y)
		 System.out.print("@");
		 else
		 System.out.print("H");
		 break;
		 case HOL:
		 if (character.getWdt() == x && character.getHgt() == y)
		 System.out.print("@");
		 else
		 System.out.print("U");
		 break;
		 case PLT:
		 if (character.getWdt() == x && character.getHgt() == y)
		 System.out.print("@");
		 else
		 System.out.print("-");
		 break;
		 case HDR:
		 if (character.getWdt() == x && character.getHgt() == y)
		 System.out.print("@");
		 else
		 System.out.print("T");
		 break;
		 case MTL:
		 if (character.getWdt() == x && character.getHgt() == y)
		 System.out.print("@");
		 else
		 System.out.print("M");
		 break;
		 case FAK:
		 if (character.getWdt() == x && character.getHgt() == y)
		 System.out.print("@");
		 else
		 System.out.print("-");
		 break;
		 }
		 }
		 System.out.print("\n");
		 }
		// System.out.println("Fin init");
		character.GoLeft();
		assertEquals(5, character.getWdt());
		assertEquals(6 - 1, character.getHgt());
	}

	/**
	 * GoLeft dans MTL
	 */
	@Test
	public void goLeftPositif3() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		EditableScreenService editableScreen = new EditableScreenContract(new EditableScreenImpl());
		editableScreen.init(WIDTH, HEIGHT);
		for (int i = 0; i < editableScreen.getWidth(); i++) {
			editableScreen.setNature(i, 0, Cell.MTL);
			editableScreen.setNature(i, 1, Cell.PLT);
		}
		editableScreen.setNature(0, 2, Cell.MTL);
		cellulesContent = new HashMap<>();
		// for (int i = 0; i < NB_TRESORS; i++) {
		// CellContent cellule = new CellContent(new ArrayList<>(), new ArrayList<>());
		// cellule.getItem().add(new Item(ItemType.TRESOR, WIDTH / 2, HEIGHT / 2));
		// cellulesContent.put(new Cle(i, 2), cellule);
		// }
		ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
		((CharacterContract) character).init(1, 2, ecran);
		character.GoLeft();
		assertEquals(1, character.getWdt());
		assertEquals(2, character.getHgt());
	}

	@Test
	public void goRightPositif1() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		EditableScreenService editableScreen = new EditableScreenContract(new EditableScreenImpl());
		editableScreen.init(WIDTH, HEIGHT);
		for (int i = 0; i < editableScreen.getWidth(); i++) {
			editableScreen.setNature(i, 0, Cell.MTL);
			editableScreen.setNature(i, 1, Cell.PLT);
		}
		cellulesContent = new HashMap<>();
		for (int i = 0; i < NB_TRESORS; i++) {
			CellContentImpl cellule = new CellContentImpl(new ArrayList<>(), new ArrayList<>());
			cellule.getItem().add(new ItemImpl(ItemType.TRESOR, WIDTH / 2, HEIGHT / 2));
			cellulesContent.put(new Cle(i, 2), cellule);
		}
		ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
		((CharacterContract) character).init(WIDTH / 2, 2, ecran);
		
		character.GoRight();
		assertEquals((WIDTH / 2) + 1, character.getWdt());
		assertEquals(2, character.getHgt());
	}
	@Test
	public void goRightPositif2() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		EditableScreenService editableScreen = new EditableScreenContract(new EditableScreenImpl());
		editableScreen.init(WIDTH, HEIGHT);
		for (int i = 0; i < editableScreen.getWidth(); i++) {
			editableScreen.setNature(i, 0, Cell.MTL);
			editableScreen.setNature(i, 1, Cell.PLT);
		}
		cellulesContent = new HashMap<>();
		for (int i = 0; i < NB_TRESORS; i++) {
			CellContentImpl cellule = new CellContentImpl(new ArrayList<>(), new ArrayList<>());
			cellule.getItem().add(new ItemImpl(ItemType.TRESOR, WIDTH / 2, HEIGHT / 2));
			cellulesContent.put(new Cle(i, 2), cellule);
		}
		ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
		((CharacterContract) character).init(5, 6, ecran);
		character.GoRight();
		assertEquals(5, character.getWdt());
		assertEquals(5, character.getHgt());
	}

	@Test
	public void goRightPositif3() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		EditableScreenService editableScreen = new EditableScreenContract(new EditableScreenImpl());
		editableScreen.init(WIDTH, HEIGHT);
		for (int i = 0; i < editableScreen.getWidth(); i++) {
			editableScreen.setNature(i, 0, Cell.MTL);
			editableScreen.setNature(i, 1, Cell.PLT);
		}
		editableScreen.setNature(3, 2, Cell.MTL);
		cellulesContent = new HashMap<>();
		// for (int i = 0; i < NB_TRESORS; i++) {
		// CellContent cellule = new CellContent(new ArrayList<>(), new ArrayList<>());
		// cellule.getItem().add(new Item(ItemType.TRESOR, WIDTH / 2, HEIGHT / 2));
		// cellulesContent.put(new Cle(i, 2), cellule);
		// }
		ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
		((CharacterContract) character).init(2, 2, ecran);
		
		character.GoRight();
		assertEquals(2, character.getWdt());
		assertEquals(2, character.getHgt());
	}

	@Test
	// Transisitions EMP->LAD Right
	public void goRightPositif4() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		EditableScreenService editableScreen = new EditableScreenContract(new EditableScreenImpl());
		editableScreen.init(WIDTH, HEIGHT);
		for (int i = 0; i < editableScreen.getWidth(); i++) {
			editableScreen.setNature(i, 0, Cell.MTL);
			editableScreen.setNature(i, 1, Cell.PLT);
		}
		editableScreen.setNature(2, 2, Cell.LAD);
		cellulesContent = new HashMap<>();
		// for (int i = 0; i < NB_TRESORS; i++) {
		// CellContent cellule = new CellContent(new ArrayList<>(), new ArrayList<>());
		// cellule.getItem().add(new Item(ItemType.TRESOR, WIDTH / 2, HEIGHT / 2));
		// cellulesContent.put(new Cle(i, 2), cellule);
		// }
		ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
		((CharacterContract) character).init(1, 2, ecran);
		
		character.GoRight();
		character.GoRight();
		assertEquals(3, character.getWdt());
		assertEquals(2, character.getHgt());
	}

	@Test
	// Transisitions LAD<-EMP Left
	public void goLeftPositif4() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		EditableScreenService editableScreen = new EditableScreenContract(new EditableScreenImpl());
		editableScreen.init(WIDTH, HEIGHT);
		for (int i = 0; i < editableScreen.getWidth(); i++) {
			editableScreen.setNature(i, 0, Cell.MTL);
			editableScreen.setNature(i, 1, Cell.PLT);
		}
		editableScreen.setNature(2, 2, Cell.LAD);
		cellulesContent = new HashMap<>();
		// for (int i = 0; i < NB_TRESORS; i++) {
		// CellContent cellule = new CellContent(new ArrayList<>(), new ArrayList<>());
		// cellule.getItem().add(new Item(ItemType.TRESOR, WIDTH / 2, HEIGHT / 2));
		// cellulesContent.put(new Cle(i, 2), cellule);
		// }
		ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
		((CharacterContract) character).init(3, 2, ecran);
		
		character.GoLeft();
		character.GoLeft();
		assertEquals(1, character.getWdt());
		assertEquals(2, character.getHgt());
	}

	// Transisition EMP->(GoRight)LAD->(GoUp)HDR->(GoRight)HDR->(GoLeft)HDR
	public void goTransition() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		EditableScreenService editableScreen = new EditableScreenContract(new EditableScreenImpl());
		editableScreen.init(WIDTH, HEIGHT);
		for (int i = 0; i < editableScreen.getWidth(); i++) {
			editableScreen.setNature(i, 0, Cell.MTL);
			editableScreen.setNature(i, 1, Cell.PLT);
		}
		editableScreen.setNature(2, 2, Cell.LAD);
		editableScreen.setNature(2, 3, Cell.HDR);
		editableScreen.setNature(3, 3, Cell.HDR);
		cellulesContent = new HashMap<>();
		// for (int i = 0; i < NB_TRESORS; i++) {
		// CellContent cellule = new CellContent(new ArrayList<>(), new ArrayList<>());
		// cellule.getItem().add(new Item(ItemType.TRESOR, WIDTH / 2, HEIGHT / 2));
		// cellulesContent.put(new Cle(i, 2), cellule);
		// }
		ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
		((CharacterContract) character).init(1, 2, ecran);
		
		character.GoRight();
		character.GoUp();
		character.GoRight();
		character.GoLeft();
		assertEquals(2, character.getWdt());
		assertEquals(3, character.getHgt());
	}

}
