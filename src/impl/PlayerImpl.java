package impl;

import error.PostCondError;
import service.Cell;
import service.Cle;
import service.Command;
import service.EngineService;
import service.EnvironmentService;
import service.ItemService;
import service.PlayerService;

public class PlayerImpl extends CharacterImpl implements PlayerService {
	private EngineService engine;
	// private int score;

	@Override
	public EnvironmentService getEnvi() {
		return engine.getEnvironment();
	}

	@Override
	public int getHgt() {
		return super.getHgt();
	}

	@Override
	public int getWdt() {
		return super.getWdt();
	}

	@Override
	public void init(int w, int h, EnvironmentService envi, EngineService e) {
		super.init(w, h, envi);
		this.engine = e;
		// score = 0;
	}

	@Override
	public EngineService getEngine() {
		return engine;
	}

	@Override
	public void step() {
		Command next = engine.getNextCommand();
		switch (next) {
		case RIGHT:
			GoRight();
			break;
		case LEFT:
			GoLeft();
			break;
		case DOWN:
			GoDown();
			break;
		case UP:
			GoUp();
			break;
		case DigL:
			if ((envi.getCellNature(x, y - 1).equals(Cell.EMP) || envi.getCellNature(x, y - 1).equals(Cell.HOL))) {
				GoDown();
			} else {
				if (envi.getCellNature(x - 1, y - 1).equals(Cell.PLT)) {
					engine.getEnvironment().Dig(getWdt() - 1, getHgt() - 1);
				}
			}
			break;
		case DigR:
			if ((envi.getCellNature(x, y - 1).equals(Cell.EMP) || envi.getCellNature(x, y - 1).equals(Cell.HOL))) {
				GoDown();
			} else {
				if (envi.getCellNature(x + 1, y - 1).equals(Cell.PLT)) {
					engine.getEnvironment().Dig(getWdt() + 1, getHgt() - 1);
				}
			}
			break;
		case FillR:
			if ((envi.getCellNature(x, y - 1).equals(Cell.EMP) || envi.getCellNature(x, y - 1).equals(Cell.HOL))) {
				GoDown();
			} else {
				if ((envi.getCellNature(x + 1, y - 1).equals(Cell.EMP)
						|| envi.getCellNature(x + 1, y - 1).equals(Cell.HOL))) {

					if (envi.getCellNature(x + 1, y - 1).equals(Cell.HOL)) {
						// System.out.println("ici1");
						int i = 0;
						while (i < engine.getHoles().size()) {
							// System.out.println(holes.get(i).getTemps());
							HoleImpl courant = engine.getHoles().get(i);
							// System.out.println("ici3");
							if (courant.getX() == x + 1 && courant.getY() == y - 1) {
								// System.out.println("ici7");
								// System.out.println(engine.getHoles().size());
								engine.getHoles().remove(courant);
								engine.getEnvironment().Fill(getWdt() + 1, getHgt() - 1);
								break;
								// System.out.println("ici8");
								// Un seul trou creusé à la fois => un seul trou qui se rebouche à la fois
							}
							i++;
						}
					}
					if ((envi.getCellNature(x + 1, y - 1).equals(Cell.EMP))) {
						engine.getEnvironment().Fill(getWdt() + 1, getHgt() - 1);
					}
				}
			}
			break;

		case FillL:
			if ((envi.getCellNature(x, y - 1).equals(Cell.EMP) || envi.getCellNature(x, y - 1).equals(Cell.HOL))) {
				GoDown();
			} else {
				if ((envi.getCellNature(x - 1, y - 1).equals(Cell.EMP)
						|| envi.getCellNature(x - 1, y - 1).equals(Cell.HOL))) {

					if (envi.getCellNature(x - 1, y - 1).equals(Cell.HOL)) {
						System.out.println("ici1");
						int i = 0;
						while (i < engine.getHoles().size()) {
							// System.out.println(holes.get(i).getTemps());
							HoleImpl courant = engine.getHoles().get(i);
							// System.out.println("ici3");
							if (courant.getX() == x - 1 && courant.getY() == y - 1) {
								// System.out.println("ici7");
								// System.out.println(engine.getHoles().size());
								engine.getHoles().remove(courant);
								engine.getEnvironment().Fill(getWdt() - 1, getHgt() - 1);
								break;
								// System.out.println("ici8");
								// Un seul trou creusé à la fois => un seul trou qui se rebouche à la fois
							}
							i++;
						}
					}
					if ((envi.getCellNature(x - 1, y - 1).equals(Cell.EMP))) {
						engine.getEnvironment().Fill(getWdt() - 1, getHgt() - 1);
					}
				}
			}
			break;
		case LadL:
			if ((envi.getCellNature(x, y - 1).equals(Cell.EMP) || envi.getCellNature(x, y - 1).equals(Cell.HOL))) {
				GoDown();
			}
			else {
				if (envi.getCellNature(x, y - 1) == Cell.LAD || envi.getCellNature(x, y - 1) == Cell.MTL
						|| envi.getCellNature(x, y - 1) == Cell.PLT
						|| envi.getCellContent(x, y - 1).getCharacter().size() > 0) {
					if (envi.getCellNature(x - 1, y + 1) == Cell.EMP) {
						if (/*envi.getCellContent(x - 1, y).getItem().size() == 0
								&&*/ envi.getCellContent(x - 1, y).getCharacter().size() == 0) {
							if (envi.getCellNature(x - 1, y) == Cell.EMP || envi.getCellNature(x - 1, y) == Cell.PLT) {
								if (envi.getCellContent(x - 1, y).getItem().size() > 0) {
									envi.getCellContent(getWdt() - 1, getHgt() + 1).getItem()
											.addAll(envi.getCellContent(x - 1, y).getItem());
									envi.getCellContent(getWdt() - 1, getHgt()).getItem().clear();

								}
								envi.Lad(x - 1, y);
							}

						}
					}
				}
			}
			break;
		case LadR:
			if ((envi.getCellNature(x, y - 1).equals(Cell.EMP) || envi.getCellNature(x, y - 1).equals(Cell.HOL))) {
				GoDown();
			} else {
				if (envi.getCellNature(x, y - 1) == Cell.LAD || envi.getCellNature(x, y - 1) == Cell.MTL
						|| envi.getCellNature(x, y - 1) == Cell.PLT
						|| envi.getCellContent(x, y - 1).getCharacter().size() > 0) {
					if (envi.getCellNature(x + 1, y + 1) == Cell.EMP) {
						if (/*envi.getCellContent(x + 1, y).getItem().size() == 0
								&&*/ envi.getCellContent(x + 1, y).getCharacter().size() == 0) {
							if (envi.getCellNature(x + 1, y) == Cell.EMP || envi.getCellNature(x + 1, y) == Cell.PLT) {
								if (envi.getCellContent(x + 1, y).getItem().size() > 0) {
									envi.getCellContent(getWdt() + 1, getHgt() + 1).getItem()
											.addAll(envi.getCellContent(x + 1, y).getItem());
									envi.getCellContent(getWdt() + 1, getHgt()).getItem().clear();

								}
								envi.Lad(x + 1, y);
							}

						}
					}
				}
			}
		case NEUTRAL:
			if ((envi.getCellNature(x, y - 1).equals(Cell.EMP) || envi.getCellNature(x, y - 1).equals(Cell.HOL))
					&& (!envi.getCellNature(x, y).equals(Cell.LAD)
							|| !envi.getCellNature(x, y).equals(Cell.HDR))) {
				GoDown();
			}
			break;
		default:
			if ((envi.getCellNature(x, y - 1).equals(Cell.EMP) || envi.getCellNature(x, y - 1).equals(Cell.HOL))
					&& (!envi.getCellNature(x, y).equals(Cell.LAD)
							|| !envi.getCellNature(x, y ).equals(Cell.HDR))) {
				GoDown();
			}
			break;
		}
		if (engine.getTreasures().get(new Cle(getWdt(), getHgt())) != null) {
			engine.getTreasures().replace(new Cle(getWdt(), getHgt()), null);
			// score++;
		}
	}

	// @Override
	// public int getScore() {
	// return score;
	// }

}
