package impl;

import java.util.ArrayList;

import contracts.PlayerContract;
import service.Cell;
import service.CharacterService;
import service.EnvironmentService;
import service.PlayerService;

public class CharacterImpl implements CharacterService {
	protected int x;
	protected int y;
	protected EnvironmentService envi;

	@Override
	public EnvironmentService getEnvi() {
		return envi;
	}

	@Override
	public int getHgt() {
		return y;
	}

	@Override
	public int getWdt() {
		return x;
	}

	@Override
	public void init(int w, int h, EnvironmentService envi) {
		this.x = w;
		this.y = h;
		this.envi = envi;
		CellContentImpl cellContent = envi.getCellContent(x, y);
		CharacterService a = (CharacterService) this;
//		if (cellContent.getCharacter().size() > 0)
//			System.out.println(cellContent.getCharacter().get(0));
//		System.out.println(this);
		cellContent.getCharacter().add(a);
	}

	@Override
	public void GoLeft() {
		// RAJOUTER CHUTE LIBRE
		// RAJOUTER CHECK CELL CONTENT EN DESSOU
		if (!(envi.getCellNature(x, y) == Cell.HDR || envi.getCellNature(x, y) == Cell.LAD)) {
			// System.out.println(envi.getCellContent(x, y - 1)==null);
			if (envi.getCellContent(x, y - 1).getCharacter().size() == 0 && (envi.getCellNature(x, y - 1) == Cell.FAK
					|| envi.getCellNature(x, y - 1) == Cell.EMP || envi.getCellNature(x, y - 1) == Cell.HOL)) {
				GoDown();
				return;
			}
		}
		if (x - 1 < 0) {
			return;
		}
		if (this instanceof PlayerImpl) {
			ArrayList<CharacterService> liste = envi.getCellContent(x, y).getCharacter();
			for (int i = 0; i < liste.size(); i++) {
				CharacterService courant = liste.get(i);
				if (courant instanceof PlayerService) {
					PlayerContract g = (PlayerContract) courant;
					if (this.equals(g.getDelegate())) {
						switch ((envi.getCellNature(x - 1, y))) {
						case PLT:
							return;
						case MTL:
							return;
						case FAK:
							return;
						default:
							switch (envi.getCellNature(x, y - 1)) {
							case MTL:
								x = x - 1;
								envi.getCellContent(x + 1, y).getCharacter().remove(i);
								envi.getCellContent(x, y).getCharacter().add(g);
								return;
							case PLT:
								x = x - 1;
								envi.getCellContent(x + 1, y).getCharacter().remove(i);
								envi.getCellContent(x, y).getCharacter().add(g);
								return;
							case LAD:
								x = x - 1;
								envi.getCellContent(x + 1, y).getCharacter().remove(i);
								envi.getCellContent(x, y).getCharacter().add(g);
								return;
							case HOL:
								x = x - 1;
								envi.getCellContent(x + 1, y).getCharacter().remove(i);
								envi.getCellContent(x, y).getCharacter().add(g);
								return;
							default:
								switch (envi.getCellNature(x, y)) {
								case EMP:
									if (!(envi.getCellContent(x, y - 1).getCharacter().size() == 0)) {
										x = x + 1;
										envi.getCellContent(x - 1, y).getCharacter().remove(i);
										envi.getCellContent(x, y).getCharacter().add(g);
										return;
									}
								case LAD:

									x = x - 1;
									envi.getCellContent(x + 1, y).getCharacter().remove(i);
									envi.getCellContent(x, y).getCharacter().add(g);
									return;
								case HDR:
									x = x - 1;
									envi.getCellContent(x + 1, y).getCharacter().remove(i);
									envi.getCellContent(x, y).getCharacter().add(g);
									return;
								default:
									return;
								}
							}

						}
					}
				}
			}
		}
		/*
		 * else if (this instanceof GuardImpl) { ArrayList<CharacterService> liste =
		 * envi.getCellContent(x, y).getCharacter(); for (int i = 0; i < liste.size();
		 * i++) { CharacterService courant = liste.get(i); if (courant instanceof
		 * GuardService) { GuardContract g = (GuardContract) courant; if
		 * (this.equals(g.getDelegate())) { switch ((envi.getCellNature(x - 1, y))) {
		 * case PLT: return; case MTL: return; case FAK: return; default: switch
		 * (envi.getCellNature(x, y - 1)) { case MTL: x = x - 1; envi.getCellContent(x +
		 * 1, y).getCharacter().remove(i); envi.getCellContent(x,
		 * y).getCharacter().add(g); return; case PLT: x = x - 1; envi.getCellContent(x
		 * + 1, y).getCharacter().remove(i); envi.getCellContent(x,
		 * y).getCharacter().add(g); return; case LAD: x = x - 1; envi.getCellContent(x
		 * + 1, y).getCharacter().remove(i); envi.getCellContent(x,
		 * y).getCharacter().add(g); return; case HOL: x = x - 1; envi.getCellContent(x
		 * + 1, y).getCharacter().remove(i); envi.getCellContent(x,
		 * y).getCharacter().add(g); return; default: switch (envi.getCellNature(x, y))
		 * { case EMP: if (!(envi.getCellContent(x, y - 1).getCharacter().size() == 0))
		 * { x = x + 1; envi.getCellContent(x - 1, y).getCharacter().remove(i);
		 * envi.getCellContent(x, y).getCharacter().add(g); return; } case LAD:
		 * 
		 * x = x - 1; envi.getCellContent(x + 1, y).getCharacter().remove(i);
		 * envi.getCellContent(x, y).getCharacter().add(g); return; case HDR: x = x - 1;
		 * envi.getCellContent(x + 1, y).getCharacter().remove(i);
		 * envi.getCellContent(x, y).getCharacter().add(g); return; default: return; } }
		 * 
		 * } } } } }
		 */
		else {
			switch ((envi.getCellNature(x - 1, y))) {
			case PLT:
				return;
			case MTL:
				return;
			case FAK:
				return;
			default:
				switch (envi.getCellNature(x, y - 1)) {
				case MTL:
					x = x - 1;
					envi.getCellContent(x + 1, y).getCharacter().remove(this);
					envi.getCellContent(x, y).getCharacter().add(this);
					return;
				case PLT:
					x = x - 1;
					envi.getCellContent(x + 1, y).getCharacter().remove(this);
					envi.getCellContent(x, y).getCharacter().add(this);
					return;
				case LAD:
					x = x - 1;
					envi.getCellContent(x + 1, y).getCharacter().remove(this);
					envi.getCellContent(x, y).getCharacter().add(this);
					return;
				case HOL:
					x = x - 1;
					envi.getCellContent(x + 1, y).getCharacter().remove(this);
					envi.getCellContent(x, y).getCharacter().add(this);
					return;
				default:
					switch (envi.getCellNature(x, y)) {
					case EMP:
						if (!(envi.getCellContent(x, y - 1).getCharacter().size() == 0)) {
							x = x + 1;
							envi.getCellContent(x - 1, y).getCharacter().remove(this);
							envi.getCellContent(x, y).getCharacter().add(this);
							return;
						}
					case LAD:

						x = x - 1;
						envi.getCellContent(x + 1, y).getCharacter().remove(this);
						envi.getCellContent(x, y).getCharacter().add(this);
						return;
					case HDR:
						x = x - 1;
						envi.getCellContent(x + 1, y).getCharacter().remove(this);
						envi.getCellContent(x, y).getCharacter().add(this);
						return;
					default:
						return;
					}
				}
			}
		}
	}

	@Override
	public void GoUp() {
		if (this instanceof PlayerImpl) {
			ArrayList<CharacterService> liste = envi.getCellContent(x, y).getCharacter();
			for (int i = 0; i < liste.size(); i++) {
				CharacterService courant = liste.get(i);
				if (courant instanceof PlayerService) {
					PlayerContract g = (PlayerContract) courant;
					if (this.equals(g.getDelegate())) {
						if (Cell.HOL.equals(envi.getCellNature(x, y - 1))
								&& !Cell.LAD.equals(envi.getCellNature(x, y))) {
							if (envi.getCellContent(x, y - 1).getCharacter().size() == 0) {
								GoDown();
								return;
							}
						}
						if (Cell.EMP.equals(envi.getCellNature(x, y - 1))
								&& !Cell.LAD.equals(envi.getCellNature(x, y))) {
							GoDown();
							return;
						}
						if (envi.getCellNature(x, y - 1) == Cell.FAK && !Cell.LAD.equals(envi.getCellNature(x, y))) {
							GoDown();
							return;
						}

						if (y + 1 > envi.getHeight()) {
							return;
						}

						if (Cell.LAD.equals(envi.getCellNature(x, y))) {
							switch ((envi.getCellNature(x, y + 1))) {
							case PLT:
								return;
							case MTL:
								return;
							default:
								y = y + 1;
								envi.getCellContent(x, y - 1).getCharacter().remove(i);
								envi.getCellContent(x, y).getCharacter().add(g);
								return;
							}
						}
					}
				}
			}
		}
		/*
		 * else if (this instanceof GuardImpl) { ArrayList<CharacterService> liste =
		 * envi.getCellContent(x, y).getCharacter(); for (int i = 0; i < liste.size();
		 * i++) { CharacterService courant = liste.get(i); if (courant instanceof
		 * GuardService) { GuardContract g = (GuardContract) courant; if
		 * (this.equals(g.getDelegate())) { if (Cell.HOL.equals(envi.getCellNature(x, y
		 * - 1))) { if (envi.getCellContent(x, y - 1).getCharacter().size() == 0) {
		 * GoDown(); return; } } if (Cell.EMP.equals(envi.getCellNature(x, y - 1))) {
		 * GoDown(); return; } if (envi.getCellNature(x, y - 1) == Cell.FAK) { GoDown();
		 * return; }
		 * 
		 * if (y + 1 > envi.getHeight()) { return; }
		 * 
		 * if (Cell.LAD.equals(envi.getCellNature(x, y))) { switch
		 * ((envi.getCellNature(x, y + 1))) { case PLT: return; case MTL: return;
		 * default: y = y + 1; envi.getCellContent(x, y - 1).getCharacter().remove(i);
		 * envi.getCellContent(x, y).getCharacter().add(g); return; } } } } } }
		 */
		else {
			if (Cell.HOL.equals(envi.getCellNature(x, y - 1)) && !Cell.LAD.equals(envi.getCellNature(x, y))) {
				if (envi.getCellContent(x, y - 1).getCharacter().size() == 0) {
					GoDown();
					return;
				}
			}
			if (Cell.EMP.equals(envi.getCellNature(x, y - 1)) && !Cell.LAD.equals(envi.getCellNature(x, y))) {
				GoDown();
				return;
			}
			if (envi.getCellNature(x, y - 1) == Cell.FAK && !Cell.LAD.equals(envi.getCellNature(x, y))) {
				GoDown();
				return;
			}

			if (y + 1 > envi.getHeight()) {
				return;
			}

			if (Cell.LAD.equals(envi.getCellNature(x, y))) {
				switch ((envi.getCellNature(x, y + 1))) {
				case PLT:
					return;
				case MTL:
					return;
				default:
					y = y + 1;
					envi.getCellContent(x, y - 1).getCharacter().remove(this);
					envi.getCellContent(x, y).getCharacter().add(this);
					return;
				}
			}
		}

	}

	@Override
	public void GoRight() {
		if (!(envi.getCellNature(x, y) == Cell.HDR || envi.getCellNature(x, y) == Cell.LAD)) {
			if (envi.getCellContent(x, y - 1).getCharacter().size() == 0 && (envi.getCellNature(x, y - 1) == Cell.EMP
					|| envi.getCellNature(x, y - 1) == Cell.HOL || envi.getCellNature(x, y - 1) == Cell.FAK)) {
				GoDown();
				return;
			}
		}
		if (x + 1 > envi.getWidth() - 1) {
			return;
		}
		switch ((envi.getCellNature(x + 1, y))) {
		case PLT:
			return;
		case MTL:
			return;
		case FAK:
			return;
		default:
			if (this instanceof PlayerImpl) {
				ArrayList<CharacterService> liste = envi.getCellContent(x, y).getCharacter();
				for (int i = 0; i < liste.size(); i++) {
					CharacterService courant = liste.get(i);
					if (courant instanceof PlayerService) {
						PlayerContract g = (PlayerContract) courant;
						if (this.equals(g.getDelegate())) {
							switch (envi.getCellNature(x, y - 1)) {
							case MTL:
								x = x + 1;
								envi.getCellContent(x - 1, y).getCharacter().remove(i);
								envi.getCellContent(x, y).getCharacter().add(g);
								return;
							case PLT:
								x = x + 1;
								envi.getCellContent(x - 1, y).getCharacter().remove(i);
								envi.getCellContent(x, y).getCharacter().add(g);
								return;
							case LAD:
								x = x + 1;
								envi.getCellContent(x - 1, y).getCharacter().remove(i);
								envi.getCellContent(x, y).getCharacter().add(g);
								return;
							default:
								switch (envi.getCellNature(x, y)) {
								case EMP:
									if (!(envi.getCellContent(x, y - 1).getCharacter().size() == 0)) {
										x = x + 1;
										envi.getCellContent(x - 1, y).getCharacter().remove(i);
										envi.getCellContent(x, y).getCharacter().add(g);
										return;
									}
								case HOL:
									x = x + 1;
									envi.getCellContent(x - 1, y).getCharacter().remove(i);
									envi.getCellContent(x, y).getCharacter().add(g);
									return;
								case LAD:
									x = x + 1;
									envi.getCellContent(x - 1, y).getCharacter().remove(i);
									envi.getCellContent(x, y).getCharacter().add(g);
									return;
								case HDR:
									x = x + 1;
									envi.getCellContent(x - 1, y).getCharacter().remove(i);
									envi.getCellContent(x, y).getCharacter().add(g);
								default:
									return;
								}
							}
						}
					}
				}
			}
			switch (envi.getCellNature(x, y - 1)) {
			case MTL:
				x = x + 1;
				envi.getCellContent(x - 1, y).getCharacter().remove(this);
				envi.getCellContent(x, y).getCharacter().add(this);
				return;
			case PLT:
				x = x + 1;
				envi.getCellContent(x - 1, y).getCharacter().remove(this);
				envi.getCellContent(x, y).getCharacter().add(this);
				return;
			case LAD:
				x = x + 1;
				envi.getCellContent(x - 1, y).getCharacter().remove(this);
				envi.getCellContent(x, y).getCharacter().add(this);
				return;
			default:
				switch (envi.getCellNature(x, y)) {
				case EMP:
					if (!(envi.getCellContent(x, y - 1).getCharacter().size() == 0)) {
						x = x + 1;
						envi.getCellContent(x - 1, y).getCharacter().remove(this);
						envi.getCellContent(x, y).getCharacter().add(this);
						return;
					}
				case HOL:
					x = x + 1;
					envi.getCellContent(x - 1, y).getCharacter().remove(this);
					envi.getCellContent(x, y).getCharacter().add(this);
					return;
				case LAD:
					x = x + 1;
					envi.getCellContent(x - 1, y).getCharacter().remove(this);
					envi.getCellContent(x, y).getCharacter().add(this);
					return;
				case HDR:
					x = x + 1;
					envi.getCellContent(x - 1, y).getCharacter().remove(this);
					envi.getCellContent(x, y).getCharacter().add(this);
				default:
				}
			}

		}

	}

	@Override
	public void GoDown() {
		// System.out.println(this);
		if (this instanceof PlayerImpl) {
			ArrayList<CharacterService> liste = envi.getCellContent(x, y).getCharacter();
			for (int i = 0; i < liste.size(); i++) {
				CharacterService courant = liste.get(i);
				if (courant instanceof PlayerService) {
					PlayerContract g = (PlayerContract) courant;
					if (this.equals(g.getDelegate())) {
						if (y - 1 < 0) {
							return;
						}
						switch ((envi.getCellNature(x, y - 1))) {
						case PLT:
							return;
						case MTL:
							return;
						case HOL:
							if (envi.getCellContent(x, y - 1).getCharacter().size() == 0) {
								y = y - 1;
								envi.getCellContent(x, y + 1).getCharacter().remove(i);
								envi.getCellContent(x, y).getCharacter().add(g);
								return;
							}
						default:
							y = y - 1;
							envi.getCellContent(x, y + 1).getCharacter().remove(i);
							envi.getCellContent(x, y).getCharacter().add(g);
							return;
						}
					}
				}
			}
		}
		// if (this instanceof GuardImpl) {
		// ArrayList<CharacterService> liste = envi.getCellContent(x, y).getCharacter();
		// for (int i = 0; i < liste.size(); i++) {
		// CharacterService courant = liste.get(i);
		// if (courant instanceof GuardService) {
		// GuardContract g = (GuardContract) courant;
		// if (this.equals(g.getDelegate())) {
		/*
		 * else if (this instanceof GuardImpl) { ArrayList<CharacterService> liste =
		 * envi.getCellContent(x, y).getCharacter(); for (int i = 0; i < liste.size();
		 * i++) { CharacterService courant = liste.get(i); if (courant instanceof
		 * GuardService) { GuardContract g = (GuardContract) courant; if
		 * (this.equals(g.getDelegate())) { if (y - 1 < 0) { return; } switch
		 * ((envi.getCellNature(x, y - 1))) { case PLT: return; case MTL: return; case
		 * HOL: if (envi.getCellContent(x, y - 1).getCharacter().size() == 0) { y = y -
		 * 1; envi.getCellContent(x, y + 1).getCharacter().remove(i);
		 * envi.getCellContent(x, y).getCharacter().add(g); return; } default: y = y -
		 * 1; envi.getCellContent(x, y + 1).getCharacter().remove(i);
		 * envi.getCellContent(x, y).getCharacter().add(g); return; } } } } }
		 */ else {
			if (y - 1 < 0) {
				return;
			}
			switch ((envi.getCellNature(x, y - 1))) {
			case PLT:
				return;
			case MTL:
				return;
			case HOL:
				if (envi.getCellContent(x, y - 1).getCharacter().size() == 0) {
					y = y - 1;
					envi.getCellContent(x, y + 1).getCharacter().remove(this);
					envi.getCellContent(x, y).getCharacter().add(this);
					return;
				}
			default:
				y = y - 1;
				envi.getCellContent(x, y + 1).getCharacter().remove(this);
				envi.getCellContent(x, y).getCharacter().add(this);
				return;
			}
		}
		// }
		// }
		// }
		// }
	}

}
