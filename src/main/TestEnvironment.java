package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import contracts.EditableScreenContract;
import contracts.EnvironmentContract;
import error.PreCondError;
import impl.CellContentImpl;
import impl.EditableScreenImpl;
import impl.EnvironmentImpl;
import impl.ItemImpl;
import service.Cell;
import service.Cle;
import service.EditableScreenService;
import service.EnvironmentService;
import service.ItemType;

public class TestEnvironment extends TestScreen {

	private EnvironmentService ecran;
	private EditableScreenService editableScreen;
	private HashMap<Cle, CellContentImpl> cellulesContent;
	private final int NB_TRESORS = 3;

	@Before
	public void beforeTests() {
		ecran = new EnvironmentContract(new EnvironmentImpl());
		editableScreen = new EditableScreenContract(new EditableScreenImpl());
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
	}

	@Test
	public void initPositif() {
		ecran.init(editableScreen, WIDTH, HEIGHT, cellulesContent);
		assertTrue(HEIGHT == ecran.getHeight());
		assertTrue(WIDTH == ecran.getWidth());
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				assertTrue(ecran.getCellNature(x, y) == editableScreen.getCellNature(x, y));
			}
		}
	}

	@Test
	public void getCellContentPositif() {
		ecran.init(editableScreen, WIDTH, HEIGHT, cellulesContent);
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				CellContentImpl content = ecran.getCellContent(x, y);
				if (y == 2 && (x == 0 || x == 1 || x == 2)) {
					assertTrue(content.getItem().size() == 1);
				}
				if (content != null)
					assertTrue(content.getCharacter().size() == 0);
			}
		}
	}

	// @Test
	// public void getCellContentNegatif() {
	// ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
	// for (int x = 0; x < WIDTH; x++) {
	// for (int y = 0; y < HEIGHT; y++) {
	// CellContent content = ecran.getCellContent(x, y);
	// if (y == 2 && (x == 0 || x == 1 || x == 2)) {
	// assertTrue(content.getItem().size() == 1);
	// }
	// if (content != null)
	// assertTrue(content.getCharacter().size() == 0);
	// }
	// }
	// }

	@Test
	public void setCellContentPositif() {
		ecran.init(editableScreen, WIDTH, HEIGHT, cellulesContent);
		assertTrue(ecran.getCellContent(5, 2).getCharacter().size() == 0);
		assertTrue(ecran.getCellContent(5, 2).getItem().size() == 0);
		CellContentImpl content = new CellContentImpl(new ArrayList<>(), new ArrayList<>());
		ItemImpl i = new ItemImpl(ItemType.TRESOR, 5, 2);
		content.getItem().add(i);
		ecran.setCellContent(5, 2, content);
		assertTrue(ecran.getCellContent(5, 2) != null);
		assertTrue(ecran.getCellContent(5, 2).getItem().get(0) == i);
	}

	@Test(expected = PreCondError.class)
	public void setCellContentNegatif() {
		ecran.init(editableScreen, WIDTH, HEIGHT, cellulesContent);
		assertTrue(ecran.getCellContent(5, 2).getCharacter().size() == 0);
		assertTrue(ecran.getCellContent(5, 2).getItem().size() == 0);
		CellContentImpl content = new CellContentImpl(new ArrayList<>(), new ArrayList<>());
		ItemImpl i = new ItemImpl(ItemType.TRESOR, -5, 2);
		content.getItem().add(i);
		ecran.setCellContent(-5, 2, content);
		// assertTrue(ecran.getCellContent(-5, 2) != null);
		// assertTrue(ecran.getCellContent(-5, 2).getItem().get(0) == i);
	}

	@Test
	public void getAllCellContentPositif() {
		ecran.init(editableScreen, WIDTH, HEIGHT, cellulesContent);
		HashMap<Cle, CellContentImpl> cellsContent = ecran.getAllCellContent();
		Iterator<Entry<Cle, CellContentImpl>> iterateur = cellsContent.entrySet().iterator();
		while (iterateur.hasNext()) {
			Entry<Cle, CellContentImpl> courant = iterateur.next();
			Cle cleCourante = courant.getKey();
			CellContentImpl celluleCourante = courant.getValue();
			assertEquals(ecran.getCellContent(cleCourante.x, cleCourante.y), celluleCourante);
		}
	}

	// @Test
	// public void getAllCellContentNegatif() {
	// ecran.init(editableScreen, HEIGHT, WIDTH, cellulesContent);
	// HashMap<Cle, CellContent> cellsContent = ecran.getAllCellContent();
	// Iterator<Entry<Cle, CellContent>> iterateur =
	// cellsContent.entrySet().iterator();
	// while (iterateur.hasNext()) {
	// Entry<Cle, CellContent> courant = iterateur.next();
	// Cle cleCourante = courant.getKey();
	// CellContent celluleCourante = courant.getValue();
	// assertEquals(ecran.getCellContent(cleCourante.x, cleCourante.y),
	// celluleCourante);
	// }
	// }
}