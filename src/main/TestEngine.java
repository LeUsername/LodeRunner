package main;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import contracts.EditableScreenContract;
import contracts.EngineContract;
import impl.EditableScreenImpl;
import impl.EngineImpl;
import impl.ItemImpl;
import service.Cell;
import service.CharacterService;
import service.Cle;
import service.EditableScreenService;
import service.EngineService;
import service.GuardService;
import service.PlayerService;
import service.Status;

public class TestEngine {

	private EngineService engine;
	private final int H = 10;
	private final int W = 10;
	private final int NB_TRESORS = 2;
	private ArrayList<Cle> guards;
	private ArrayList<Cle> items;
	private ArrayList<Cle> guards2;
	private ArrayList<Cle> items2;

	@Before
	public void beforeTests() {
		engine = /*new EngineContract(*/new EngineImpl()/*)*/;
		EditableScreenContract ecran = new EditableScreenContract(new EditableScreenImpl());
		guards = new ArrayList<>();
		items = new ArrayList<>();
		ArrayList<EditableScreenService> listeE =new ArrayList<>();
		for (int nb = 0; nb < NB_TRESORS; nb++) {
			items.add(new Cle(nb, 2));
		}
		for (int nb = 0; nb < 1; nb++) {
			guards.add(new Cle(6, 3));
		}
		ecran.init(W, H, 5, 2, guards, items);
		for (int i = 0; i < ecran.getWidth(); i++) {
			ecran.setNature(i, 0, Cell.MTL);
		}
		for (int i = 0; i < ecran.getWidth(); i++) {
			ecran.setNature(i, 1, Cell.PLT);
		}
		for (int y = 2; y < ecran.getHeight() - 2; y++) {
			ecran.setNature(8, y, Cell.LAD);
		}
		for (int x = 0; x < ecran.getWidth(); x++) {
			ecran.setNature(x, ecran.getHeight() - 2, Cell.HDR);
		}
		ecran.setNature(ecran.getWidth()-3, 3, Cell.FAK);
		listeE.add(ecran);
		EditableScreenContract ecran2 = new EditableScreenContract(new EditableScreenImpl());
		guards2 = new ArrayList<>();
		items2 = new ArrayList<>();
		for (int nb = 0; nb < NB_TRESORS; nb++) {
			items2.add(new Cle(nb, 2));
		}
		for (int nb = 0; nb < 0; nb++) {
			guards2.add(new Cle(6, 3));
			
		}
		ecran2.init(W, H,  5, 2, guards2, items2);
		for (int i = 0; i < ecran.getWidth(); i++) {
			ecran2.setNature(i, 0, Cell.MTL);
		}
		for (int i = 0; i < ecran.getWidth(); i++) {
			ecran2.setNature(i, 1, Cell.PLT);
		}
		for (int y = 2; y < ecran.getHeight() - 2; y++) {
			ecran2.setNature(8, y, Cell.LAD);
		}
		for (int x = 0; x < ecran.getWidth(); x++) {
			ecran2.setNature(x, ecran.getHeight() - 2, Cell.HDR);
		}
		ecran2.setNature(ecran.getWidth()-3, 5, Cell.FAK);
		listeE.add(ecran2);
		engine.init(listeE);
//		System.out.println(engine.getGuards().size());
	}

	@Test
	public void testInitPositif() {
//		for (int y = engine.getEnvironment().getHeight() - 1; y >= 0; y--) {
//			for (int x = 0; x < engine.getEnvironment().getWidth(); x++) {
//				switch (engine.getEnvironment().getCellNature(x, y)) {
//				case EMP:
//					System.out.print(" ");
//					break;
//				case LAD:
//					System.out.print("H");
//					break;
//				case HOL:
//					System.out.print("U");
//					break;
//				case PLT:
//					System.out.print("-");
//					break;
//				case HDR:
//					System.out.print("T");
//					break;
//				case MTL:
//					System.out.print("M");
//					break;
//				}
//			}
//			System.out.print("\n");
//		}
//		System.out.println("Fin init");
	}

	@Test
	public void testStepPositif() {
		String[][] terrain = new String[engine.getEnvironment().getWidth()][engine.getEnvironment().getHeight()];
		for (int y = engine.getEnvironment().getHeight() - 1; y >= 0; y--) {
			for (int x = 0; x < engine.getEnvironment().getWidth(); x++) {
				switch (engine.getEnvironment().getCellNature(x, y)) {
				case EMP:
					terrain[x][y] = " ";
					break;
				case LAD:
					terrain[x][y] = ("H");
					break;
				case HOL:
					terrain[x][y] = ("U");
					break;
				case PLT:
					terrain[x][y] = ("-");
					break;
				case FAK:
					terrain[x][y] = ("-");
					break;
				case HDR:
					terrain[x][y] = ("T");
					break;
				case MTL:
					terrain[x][y] = ("M");
					break;
				}
			}
			// System.out.print("\n");
		}
		for (int y = engine.getEnvironment().getHeight() - 1; y >= 0; y--) {
			for (int x = 0; x < engine.getEnvironment().getWidth(); x++) {
				ArrayList<ItemImpl> listeItem = engine.getEnvironment().getCellContent(x, y).getItem();
				for (int i = 0; i < listeItem.size(); i++) {
					terrain[x][y] = "+";
				}
			}
		}
		for (int y = engine.getEnvironment().getHeight() - 1; y >= 0; y--) {
			for (int x = 0; x < engine.getEnvironment().getWidth(); x++) {
				ArrayList<CharacterService> listeChara = engine.getEnvironment().getCellContent(x, y).getCharacter();
				for (int i = 0; i < listeChara.size(); i++) {
					CharacterService c = listeChara.get(i);
					if (c instanceof PlayerService) {
						terrain[x][y] = "@";
					} else {
						GuardService g = (GuardService) c;
						terrain[x][y] = "" + g.getId();
					}
				}
			}
		}
		for (int y = engine.getEnvironment().getHeight() - 1; y >= 0; y--) {
			for (int x = 0; x < engine.getEnvironment().getWidth(); x++) {
				System.out.print(terrain[x][y]);
			}
			System.out.print("\n");
		}
		Scanner scan = new Scanner(System.in);
		while (engine.getStatus() != Status.END && engine.getStatus() != Status.GAME_OVER ) {
			System.out.println(engine.getStatus());
			engine.step(scan);
			for (int y = engine.getEnvironment().getHeight() - 1; y >= 0; y--) {
				for (int x = 0; x < engine.getEnvironment().getWidth(); x++) {
					switch (engine.getEnvironment().getCellNature(x, y)) {
					case EMP:
						terrain[x][y] = " ";
						break;
					case LAD:
						terrain[x][y] = ("H");
						break;
					case FAK:
						terrain[x][y] = ("-");
						break;
						
					case HOL:
						terrain[x][y] = ("U");
						break;
					case PLT:
						terrain[x][y] = ("-");
						break;
					case HDR:
						terrain[x][y] = ("T");
						break;
					case MTL:
						terrain[x][y] = ("M");
						break;
					}
				}
				// System.out.print("\n");
			}
			for (int y = engine.getEnvironment().getHeight() - 1; y >= 0; y--) {
				for (int x = 0; x < engine.getEnvironment().getWidth(); x++) {
					ArrayList<ItemImpl> listeItem = engine.getEnvironment().getCellContent(x, y).getItem();
					for (int i = 0; i < listeItem.size(); i++) {
						terrain[x][y] = "+";
					}
				}
			}
			for (int y = engine.getEnvironment().getHeight() - 1; y >= 0; y--) {
				for (int x = 0; x < engine.getEnvironment().getWidth(); x++) {
					ArrayList<CharacterService> listeChara = engine.getEnvironment().getCellContent(x, y)
							.getCharacter();
					for (int i = 0; i < listeChara.size(); i++) {
						CharacterService c = listeChara.get(i);
						if (c instanceof PlayerService) {
							terrain[x][y] = "@";
						} else {
							GuardService g = (GuardService) c;
							terrain[x][y] = "" + g.getId();
						}
					}
				}
			}
			for (int y = engine.getEnvironment().getHeight() - 1; y >= 0; y--) {
				for (int x = 0; x < engine.getEnvironment().getWidth(); x++) {
					System.out.print(terrain[x][y]);
				}
				System.out.print("\n");
			}
			System.out.println("_________________________________________");
		}
		scan.close();
		System.out.println(engine.getStatus());
		System.out.println("Fin step");
	}
}
