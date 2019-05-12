package impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import contracts.EnvironmentContract;
import contracts.PlayerContract;
import service.Cell;
import service.CharacterService;
import service.Cle;
import service.Command;
import service.EditableScreenService;
import service.EngineService;
import service.EnvironmentService;
import service.GuardService;
import service.ItemType;
import service.PlayerService;
import service.Status;

public class EngineImpl implements EngineService {
	private EnvironmentService environment;
	private PlayerService player;
	private ArrayList<GuardService> guards;
	private HashMap<Cle, ItemImpl> treasures;
	private Status status;
	private Command nextCommand;
	private int nbTresors = 0;
	private ArrayList<HoleImpl> holes;
	private Boolean t;
	private static int life = 3;
	private static int lvl = 0;
	private static int lvl_max;
	private EditableScreenService e;
	private ArrayList<EditableScreenService> ez;

	public EnvironmentService getEnvironment() {
		return environment;
	}

	public PlayerService getPlayer() {
		return player;
	}

	public ArrayList<GuardService> getGuards() {
		return guards;
	}

	public HashMap<Cle, ItemImpl> getTreasures() {
		return treasures;
	}

	public Status getStatus() {
		return status;
	}

	public Command getNextCommand() {
		return nextCommand;
	}

	@Override
	public void step(Scanner scan) {
		if (status == Status.WIN || status == Status.LOSE) {
			this.init(ez);

		}
		if (scan.hasNextLine()) {
			String commande = scan.nextLine();
			switch (commande) {
			case "q":
				nextCommand = Command.LEFT;
				break;
			case "d":
				nextCommand = Command.RIGHT;
				break;
			case "z":
				nextCommand = Command.UP;
				break;
			case "s":
				nextCommand = Command.DOWN;
				break;
			case "1":
				nextCommand = Command.DigL;
				break;
			case "2":
				nextCommand = Command.DigR;
				break;
			case "4":
				nextCommand = Command.FillL;
				break;
			case "5":
				nextCommand = Command.FillR;
				break;
			case "7":
				nextCommand = Command.LadL;
				break;
			case "8":
				nextCommand = Command.LadR;
				break;
			default:
				nextCommand = Command.NEUTRAL;
				break;
			}
		} else {
			nextCommand = Command.NEUTRAL;
		}
		player.step();
		t = true;
		if (nextCommand == Command.DigL) {
			if (environment.getCellNature(player.getWdt() - 1, player.getHgt() - 1) == Cell.HOL) {
				for (HoleImpl h : getHoles()) {
					if (h.getX() == player.getWdt() - 1 && h.getY() == player.getHgt() - 1) {
						t = false;
						break;
					}
				}
				if ((t || holes.size() == 0))
					holes.add(new HoleImpl(player.getWdt() - 1, player.getHgt() - 1, -1));
			}

		}
		t = true;
		if (nextCommand == Command.DigR) {
			if (environment.getCellNature(player.getWdt() + 1, player.getHgt() - 1) == Cell.HOL) {
				for (HoleImpl h : getHoles()) {
					if (h.getX() == player.getWdt() + 1 && h.getY() == player.getHgt() - 1) {
						t = false;
						break;
					}
				}
				if ((t || holes.size() == 0))
					holes.add(new HoleImpl(player.getWdt() + 1, player.getHgt() - 1, -1));
			}
		}
		if (nextCommand == Command.FillL) {
			if (environment.getCellNature(player.getWdt() - 1, player.getHgt() - 1) == Cell.HOL) {
				System.out.println("ici1");
				int i = 0;
				while (i < holes.size()) {
					// System.out.println(holes.get(i).getTemps());
					HoleImpl courant = holes.get(i);
					System.out.println("ici3");
					if (courant.getX() == player.getWdt() - 1 && courant.getY() == player.getHgt() - 1) {
						System.out.println("ici7");
						System.out.println(holes.size());
						holes.remove(courant);
						environment.Fill(player.getWdt() - 1, player.getHgt() - 1);

						System.out.println("ici8");
						System.out.println(holes.size());
						// Un seul trou creusé à la fois => un seul trou qui se rebouche à la fois
					}
					i++;
				}
			}
		}
		if (nextCommand == Command.FillR) {
			if (environment.getCellNature(player.getWdt() + 1, player.getHgt() - 1) == Cell.HOL) {
				int i = 0;
				while (i < holes.size()) {
					// System.out.println(holes.get(i).getTemps());
					HoleImpl courant = holes.get(i);
					if (courant.getX() == player.getWdt() + 1 && courant.getY() == player.getHgt() - 1) {
						holes.remove(courant);
						environment.Fill(player.getWdt() + 1, player.getHgt() - 1);

						// Un seul trou creusé à la fois => un seul trou qui se rebouche à la fois
					}
					i++;
				}
			}
		}
		for (GuardService g : guards) {
			g.step();
		}
		for (int i = 0; i < holes.size(); i++) {
			holes.get(i).setTemps(holes.get(i).getTemps() + 1);
		}
		int i = 0;
		while (i < holes.size()) {
			// System.out.println(holes.get(i).getTemps());
			HoleImpl courant = holes.get(i);
			if (courant.getTemps() == 15) {
				environment.Fill(courant.getX(), courant.getY());
				holes.remove(courant);
				// Un seul trou creusé à la fois => un seul trou qui se rebouche à la fois
			}
			i++;
		}
		ArrayList<ItemImpl> caseCourante = environment.getCellContent(player.getWdt(), player.getHgt()).getItem();
		if (caseCourante.size() > 0) {
			caseCourante.clear();
			treasures.remove(new Cle(player.getWdt(), player.getHgt()));
			nbTresors--;
		}
		for (int index = 0; index < guards.size(); index++) {
			GuardService g = guards.get(index);
			// System.out.println(guards.size());
			// System.out.println(getEnvironment().getCellNature(g.getWdt(), g.getHgt()));
			if (g.Alive() == false) {

				g.init(g.getXPos(), g.getYPos(), environment, player, g.getXPos(), g.getYPos());

				//getEnvironment().getCellContent(g.getXPos(), g.getYPos()).getCharacter().add(g);
			
		
			} else if (getEnvironment().getCellNature(g.getWdt(), g.getHgt()) == Cell.PLT) {

				getEnvironment().getCellContent(g.getWdt(), g.getHgt()).getCharacter().remove((CharacterService) g);
				g.Dead();
				// System.out.println("AVANT");

			}
		}
		if (nbTresors == 0) {
			status = Status.WIN;
			Winning();
			System.out.println("fin du niveau");
			System.out.println(Nb_LevelMax());
			System.out.println(GetLevel());
			System.out.println(Nb_LevelMax() == GetLevel());
			if (Nb_LevelMax() == GetLevel()) {
				System.out.println("fin du jeu");
				status = Status.END;
			}
		} else if (getEnvironment().getCellNature(getPlayer().getWdt(), getPlayer().getHgt()) == Cell.PLT) {
			status = Status.LOSE;
			Loose();
			if (getLife() == 0) {
				status = Status.GAME_OVER;
			}
		} else {
			for (CharacterService c : getEnvironment().getCellContent(player.getWdt(), player.getHgt())
					.getCharacter()) {
				if (c instanceof GuardService) {
					status = Status.LOSE;
					Loose();
					if (getLife() == 0) {
						status = Status.GAME_OVER;
					}
				}
			}
		}
	}

	@Override
	public void init(ArrayList<EditableScreenService> listeE) {
		this.ez = listeE;
		this.e = listeE.get(lvl);
		this.lvl_max = listeE.size();
		int xJoueur = e.GetxJoueur();
		int yJoueur = e.GetyJoueur();
		ArrayList<Cle> posGardes = e.GetposGardes();
		ArrayList<Cle> posItems = e.GetposItems();
		status = Status.PLAYING;
		nextCommand = Command.NEUTRAL;
		player = new PlayerContract(new PlayerImpl());
		holes = new ArrayList<>();
		HashMap<Cle, CellContentImpl> cellules = new HashMap<>();
		environment = new EnvironmentContract(new EnvironmentImpl());
		environment.init(e, e.getHeight(), e.getWidth(), cellules);
		player.init(xJoueur, yJoueur, environment, this);
		guards = new ArrayList<>();

		for (int i = 0; i < e.getHeight(); i++) {
			for (int j = 0; j < e.getWidth(); j++) {
				cellules.put(new Cle(i, j), new CellContentImpl(new ArrayList<>(), new ArrayList<>()));
			}
		}
		for (Cle c : posGardes) {
			GuardService g = /*new GuardContract(*/new GuardImpl()/*)*/;
			g.init(c.x, c.y, environment, player, c.x, c.y);
//			cellules.get(new Cle(c.x, c.y)).getCharacter().add(g);
			guards.add(g);
		}
		cellules.get(new Cle(xJoueur, yJoueur)).getCharacter().add(player);
		treasures = new HashMap<Cle, ItemImpl>();
		for (Cle c : posItems) {
			ItemImpl t = new ItemImpl(ItemType.TRESOR, c.x, c.y);
			treasures.put(c, t);
			cellules.get(c).getItem().add(t);
			nbTresors++;
		}

	}

	@Override
	public ArrayList<HoleImpl> getHoles() {
		return holes;
	}

	@Override
	public int getLife() {
		return life;
	}

	@Override
	public void Loose() {
		life = life - 1;

	}

	@Override
	public void Winning() {
		lvl++;

	}

	@Override
	public int GetLevel() {
		return lvl;
	}

	@Override
	public int Nb_LevelMax() {
		return lvl_max;
	}

}
