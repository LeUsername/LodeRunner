package impl;

import service.Cell;
import service.CharacterService;
import service.EnvironmentService;
import service.GuardService;
import service.Move;

public class GuardImpl extends CharacterImpl implements GuardService {
	private static int cptId = 0;
	private final int ID = cptId++;
	private CharacterService target;
	private int timeInHole;
	public final static int TIMEBEFOREOUT = 60;
	private int xPos;
	private int yPos;
	private boolean alive;

	@Override
	public int getId() {
		return ID;
	}
	public int getXPos() {
		return xPos;
	}
	public int getYPos() {
		return yPos;
	}
	@Override
	public CharacterService getTarget() {
		return target;
	}

	@Override
	public Move getBehaviour() {
		if (envi.getCellNature(x, y) == Cell.LAD) {
			if ((envi.getCellNature(x, y - 1) == Cell.PLT || envi.getCellNature(x, y - 1) == Cell.MTL)
					|| ((envi.getCellNature(x, y - 1) == Cell.EMP || envi.getCellNature(x, y - 1) == Cell.HOL
							|| envi.getCellNature(x, y - 1) == Cell.HDR || envi.getCellNature(x, y - 1) == Cell.LAD)
							&& (envi.getCellContent(x, y - 1).getCharacter().size() > 0))) {
				int diffX = x - getTarget().getWdt();
				int diffY = y - getTarget().getHgt();
				if (Math.abs(diffX) > Math.abs(diffY)) {
					if (diffX > 0) {
						return Move.LEFT;
					} else {
						return Move.RIGHT;
					}
				} else {
					if (diffY > 0) {
						return Move.DOWN;
					} else {
						return Move.UP;
					}
				}
			} else {
				if (getTarget().getHgt() < y) {
					return Move.DOWN;
				} else if (getTarget().getHgt() >= y) {
					return Move.UP;
				}
			}
		}
		if ((envi.getCellNature(x, y) == Cell.HOL || envi.getCellNature(x, y) == Cell.HDR)
				|| (envi.getCellNature(x, y - 1) == Cell.PLT || envi.getCellNature(x, y - 1) == Cell.MTL)
				|| ((envi.getCellNature(x, y - 1) == Cell.EMP || envi.getCellNature(x, y - 1) == Cell.HOL
						|| envi.getCellNature(x, y - 1) == Cell.HDR || envi.getCellNature(x, y - 1) == Cell.LAD)
						&& (envi.getCellContent(x, y - 1).getCharacter().size() > 0) )) {
//			System.out.println((envi.getCellNature(x, y - 1) == Cell.PLT || envi.getCellNature(x, y - 1) == Cell.MTL));
			if (getTarget().getWdt() < x) {
				
				return Move.LEFT;
			} else if (getTarget().getWdt() > x) {
				return Move.RIGHT;
			} else {
				return Move.NEUTRAL;
			}
		}
//		System.out.println((envi.getCellNature(x, y - 1) == Cell.PLT || envi.getCellNature(x, y - 1) == Cell.MTL));
//		System.out.println(x);
//		System.out.println(y);
		return Move.DOWN;
	}

	@Override
	public int getTimeInHole() {
		return timeInHole;
	}

	@Override
	public void init(int w, int h, EnvironmentService envi, CharacterService target,int xPos1,int yPos1) {
		super.init(w, h, envi);
		this.target = target;
		timeInHole = 0;
		this.xPos=xPos1;
		this.yPos=yPos1;
		alive=true;
	}

	@Override
	public void climbLeft() {
		if (envi.getCellNature(x - 1, y + 1) != Cell.MTL && envi.getCellNature(x - 1, y + 1) != Cell.PLT) {
			envi.getCellContent(x, y).getCharacter().remove(this);
			x = x - 1;
			y = y + 1;
			envi.getCellContent(x, y).getCharacter().add(this);
		}
	}

	@Override
	public void climbRight() {
		if (envi.getCellNature(x + 1, y + 1) != Cell.MTL && envi.getCellNature(x + 1, y + 1) != Cell.PLT) {
			envi.getCellContent(x, y).getCharacter().remove(this);
			x = x + 1;
			y = y + 1;
			envi.getCellContent(x, y).getCharacter().add(this);
		}
	}

	@Override
	public void step() {
		// System.out.println(getBehaviour());
		if (envi.getCellNature(x, y) == Cell.HOL) {
			if (timeInHole == TIMEBEFOREOUT) {
				switch (getBehaviour()) {
				case RIGHT:
					climbRight();
					break;
				case LEFT:
					climbLeft();
					break;
				default:
					break;
				}
				timeInHole = 0;
			} else {
				timeInHole++;
			}
		} else {
			switch (getBehaviour()) {
			case RIGHT:
				GoRight();
				break;
			case LEFT:
				GoLeft();
				break;
			case UP:
				GoUp();
				break;
			case DOWN:
				GoDown();
				break;
			default:
				break;
			}
		}
	}
	@Override
	public boolean Alive() {
		
		return alive;
	}
	@Override
	public void Dead() {
		alive=!alive;
	
	}
}
