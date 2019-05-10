package main;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import contracts.EditableScreenContract;
import contracts.EngineContract;
import contracts.EnvironmentContract;
import contracts.PlayerContract;
import impl.CellContentImpl;
import impl.EditableScreenImpl;
import impl.EngineImpl;
import impl.EnvironmentImpl;
import impl.ItemImpl;
import impl.PlayerImpl;
import service.Cell;
import service.Cle;
import service.EditableScreenService;
import service.EngineService;
import service.EnvironmentService;
import service.ItemType;
import service.PlayerService;

public class TestPlayer extends TestCharacter {
	private PlayerService player;
	private EngineService engine;
	private EnvironmentService ecran;
	private HashMap<Cle, CellContentImpl> cellulesContent;
	private final static int HEIGHT = 10;
	private final static int WIDTH = 10;
	private final int NB_TRESORS = 3;

	public TestPlayer() {
		player = new PlayerContract(new PlayerImpl());
	}

	@Before
	public void beforeTests() {
		player = new PlayerContract(new PlayerImpl());
	}

	public void setEcran(EngineService engine) {
		this.engine = engine;
	}

	@Test
	public void initPositif() {
		engine = new EngineContract(new EngineImpl());
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
		player.init(2, 6, ecran, engine);
		assertEquals(engine, player.getEngine());
	}
}
