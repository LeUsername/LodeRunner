package contracts;

import decorators.GuardDecorator;
import error.InvariantError;
import error.PostCondError;
import error.PreCondError;
import service.Cell;
import service.CharacterService;
import service.EnvironmentService;
import service.GuardService;
import service.Move;

public class GuardContract extends GuardDecorator {
	public GuardContract(GuardService cs) {
		super(cs);
	}

	public void checkInvariant() {
		if (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.LAD
				&& getTarget().getHgt() > getHgt()) {
			if (!(((GuardService) delegate).getBehaviour() == Move.UP)) {
				throw new InvariantError("Le déplacement du garde devrait être UP");
			}
		}
		if (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.LAD
				&& getTarget().getHgt() < getHgt()) {
			if (!(((GuardService) delegate).getBehaviour() == Move.DOWN)) {
				throw new InvariantError("Le déplacement du garde devrait être DOWN");
			}
		}
		if (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.LAD
				&& getTarget().getHgt() == getHgt()) {
			if (!(((GuardService) delegate).getBehaviour() == Move.NEUTRAL)) {
				throw new InvariantError("Le déplacement du garde devrait être NEUTRAL");
			}
		}

		if ((((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HOL
				|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HDR)
				|| (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.PLT
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.MTL)
				|| ((((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.EMP
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HOL
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HDR
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.LAD)
						&& (((GuardService) delegate).getEnvi().getCellContent(getWdt(), getHgt() - 1).getCharacter()
								.size() > 0))) {
			if (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.LAD
					&& getTarget().getWdt() > getWdt()) {
				if (!(((GuardService) delegate).getBehaviour() == Move.LEFT)) {
					throw new InvariantError("Le déplacement du garde devrait être LEFT");
				}
			}
		}
		if ((((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HOL
				|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HDR)
				|| (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.PLT
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.MTL)
				|| ((((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.EMP
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HOL
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HDR
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.LAD)
						&& (((GuardService) delegate).getEnvi().getCellContent(getWdt(), getHgt() - 1).getCharacter()
								.size() > 0))) {
			if (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.LAD
					&& getTarget().getWdt() < getWdt()) {
				if (!(((GuardService) delegate).getBehaviour() == Move.RIGHT)) {
					throw new InvariantError("Le déplacement du garde devrait être RIGHT");
				}
			}
		}
		if ((((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HOL
				|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HDR)
				|| (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.PLT
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.MTL)
				|| ((((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.EMP
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HOL
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HDR
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.LAD)
						&& (((GuardService) delegate).getEnvi().getCellContent(getWdt(), getHgt() - 1).getCharacter()
								.size() > 0))) {
			if (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.LAD
					&& getTarget().getWdt() == getWdt()) {
				if (!(((GuardService) delegate).getBehaviour() == Move.NEUTRAL)) {
					throw new InvariantError("Le déplacement du garde devrait être NEUTRAL");
				}
			}
		}
		if (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.LAD
				&& ((((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.PLT
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.MTL)
						|| (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.EMP
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HOL
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HDR
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(),
										getHgt() - 1) == Cell.LAD)
								&& (((GuardService) delegate).getEnvi().getCellContent(getWdt(), getHgt() - 1)
										.getCharacter().size() > 0))) {
			int x = ((GuardService) delegate).getWdt();
			int xTarget = ((GuardService) delegate).getTarget().getWdt();
			int y = ((GuardService) delegate).getHgt();
			int yTarget = ((GuardService) delegate).getTarget().getHgt();
			if (Math.abs(x - xTarget) > Math.abs(y - yTarget)) {
				if (x < xTarget) {
					if (!(((GuardService) delegate).getBehaviour() == Move.RIGHT)) {
						throw new InvariantError("Le déplacement du garde devrait être RIGHT");
					}
				}
			}
		}
		if (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.LAD
				&& ((((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.PLT
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.MTL)
						|| (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.EMP
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HOL
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HDR
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(),
										getHgt() - 1) == Cell.LAD)
								&& (((GuardService) delegate).getEnvi().getCellContent(getWdt(), getHgt() - 1)
										.getCharacter().size() > 0))) {
			int x = ((GuardService) delegate).getWdt();
			int xTarget = ((GuardService) delegate).getTarget().getWdt();
			int y = ((GuardService) delegate).getHgt();
			int yTarget = ((GuardService) delegate).getTarget().getHgt();
			if (Math.abs(x - xTarget) <= Math.abs(y - yTarget)) {
				if (x >= xTarget) {
					if (!(((GuardService) delegate).getBehaviour() == Move.LEFT)) {
						throw new InvariantError("Le déplacement du garde devrait être LEFT");
					}
				}
			}
		}
		if (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.LAD
				&& ((((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.PLT
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.MTL)
						|| (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.EMP
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HOL
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HDR
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(),
										getHgt() - 1) == Cell.LAD)
								&& (((GuardService) delegate).getEnvi().getCellContent(getWdt(), getHgt() - 1)
										.getCharacter().size() > 0))) {
			int x = ((GuardService) delegate).getWdt();
			int xTarget = ((GuardService) delegate).getTarget().getWdt();
			int y = ((GuardService) delegate).getHgt();
			int yTarget = ((GuardService) delegate).getTarget().getHgt();
			if (Math.abs(x - xTarget) < Math.abs(y - yTarget)) {
				if (y >= yTarget) {
					if (!(((GuardService) delegate).getBehaviour() == Move.DOWN)) {
						throw new InvariantError("Le déplacement du garde devrait être DOWN");
					}
				}
			}
		}
		if (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.LAD
				&& ((((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.PLT
						|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.MTL)
						|| (((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.EMP
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HOL
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1) == Cell.HDR
								|| ((GuardService) delegate).getEnvi().getCellNature(getWdt(),
										getHgt() - 1) == Cell.LAD)
								&& (((GuardService) delegate).getEnvi().getCellContent(getWdt(), getHgt() - 1)
										.getCharacter().size() > 0))) {
			int x = ((GuardService) delegate).getWdt();
			int xTarget = ((GuardService) delegate).getTarget().getWdt();
			int y = ((GuardService) delegate).getHgt();
			int yTarget = ((GuardService) delegate).getTarget().getHgt();
			if (Math.abs(x - xTarget) <= Math.abs(y - yTarget)) {
				if (y < yTarget) {
					if (!(((GuardService) delegate).getBehaviour() == Move.UP)) {
						throw new InvariantError("Le déplacement du garde devrait être UP");
					}
				}
			}
		}
	}

	public void init(int w, int h, EnvironmentService envi, CharacterService target, int x, int y) {
		((GuardService) delegate).init(w, h, envi, target, x, y);
		checkInvariant();
	}

	public void climbLeft() {
		// pre: Environment::CellNature(getEnvi(G), getWdt(G), getHgt(G))==HOL
		// post: (Environment::CellNature(getEnvi(G), getWdt(G)@pre-1,
		// getHgt(G)@pre+1)\in {LAD, EMP, HDR, HOL}) => getWdt(G)==getWdt(G)@pre-1 \and
		// getHgt(G)==getHgt(G)@pre+1
		if (!(((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HOL)) {
			throw new PreCondError("Le garde n'est pas dans un trou");
		}
		int x_at_pre = ((GuardService) delegate).getWdt();
		int y_at_pre = ((GuardService) delegate).getHgt();
		checkInvariant();
		((GuardService) delegate).climbLeft();
		checkInvariant();
		if (((GuardService) delegate).getEnvi().getCellNature(x_at_pre - 1, y_at_pre + 1) == Cell.LAD
				|| ((GuardService) delegate).getEnvi().getCellNature(x_at_pre - 1, y_at_pre + 1) == Cell.EMP
				|| ((GuardService) delegate).getEnvi().getCellNature(x_at_pre - 1, y_at_pre + 1) == Cell.HOL
				|| ((GuardService) delegate).getEnvi().getCellNature(x_at_pre - 1, y_at_pre + 1) == Cell.HDR) {
			if (!(((GuardService) delegate).getWdt() == x_at_pre - 1
					&& ((GuardService) delegate).getHgt() == y_at_pre + 1)) {
				throw new PostCondError("Le garde n'est pas sur la bonne case");
			}
		}
	}

	public void climbRight() {
		// pre: Environment::CellNature(getEnvi(G), getWdt(G), getHgt(G))==HOL
		// post: (Environment::CellNature(getEnvi(G), getWdt(G)@pre+1,
		// getHgt(G)@pre+1)\in {LAD, EMP, HDR, HOL}) => getWdt(G)==getWdt(G)@pre+1 \and
		// getHgt(G)==getHgt(G)@pre+1
		if (!(((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HOL)) {
			throw new PreCondError("Le garde n'est pas dans un trou");
		}
		int x_at_pre = ((GuardService) delegate).getWdt();
		int y_at_pre = ((GuardService) delegate).getHgt();
		checkInvariant();
		((GuardService) delegate).climbLeft();
		checkInvariant();
		if (((GuardService) delegate).getEnvi().getCellNature(x_at_pre + 1, y_at_pre + 1) == Cell.LAD
				|| ((GuardService) delegate).getEnvi().getCellNature(x_at_pre + 1, y_at_pre + 1) == Cell.EMP
				|| ((GuardService) delegate).getEnvi().getCellNature(x_at_pre + 1, y_at_pre + 1) == Cell.HOL
				|| ((GuardService) delegate).getEnvi().getCellNature(x_at_pre + 1, y_at_pre + 1) == Cell.HDR) {
			if (!(((GuardService) delegate).getWdt() == x_at_pre + 1
					&& ((GuardService) delegate).getHgt() == y_at_pre + 1)) {
				throw new PostCondError("Le garde n'est pas sur la bonne case");
			}
		}
	}

	public void Dead() {
		checkInvariant();
		boolean statu_at_pre = Alive();
		if (!(((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt()) == Cell.PLT)) {
			throw new PreCondError("Le garde n'est pas dans un PLT");
		}
		((GuardService) delegate).Dead();
		if (!(Alive() != statu_at_pre)) {
			throw new PostCondError("Le garde n'est pas mort");
		}

		if (Alive() == false) {
			{
				for (int i = 0; i < getEnvi().getWidth(); i++) {
					for (int j = 0; j < getEnvi().getHeight(); j++) {
						if (((GuardService) delegate).getEnvi().getCellContent(i, j).getCharacter().contains(this)) {
							throw new PostCondError("Le garde est mort mais est toujours présent");
						}
					}
				}
			}

		}
	}

	// La comparaison avec un clone ne se fait pas bien
	public void step() {
		checkInvariant();
		/*Cell cellNature_at_pre = ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt());
		Cell cellNatureEnBas_at_pre = ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1);
		int nbCharaEnBas_at_pre = ((GuardService) delegate).getEnvi().getCellContent(getWdt(), getHgt() - 1)
				.getCharacter().size();
		int x_at_pre = ((GuardService) delegate).getWdt();
		int y_at_pre = ((GuardService) delegate).getHgt();
		int time_at_pre = ((GuardService) delegate).getTimeInHole();
		GuardService guardClone = new GuardImpl();
		EditableScreenService editableClone = new EditableScreenImpl();
		editableClone.init(((GuardService) delegate).getEnvi());
		EnvironmentService enviClone = new EnvironmentImpl();
		enviClone.init(editableClone, editableClone.getWidth(), editableClone.getHeight(),
				((GuardService) delegate).getEnvi().getAllCellContent());
		ArrayList<CharacterService> listeClone = new ArrayList<>();
		listeClone.add(guardClone);
		CellContentImpl celluleClone = new CellContentImpl(listeClone, new ArrayList<>());
		// System.out.println("Clonae de " + (delegate));
		// if (celluleClone.getCharacter().size() > 0) {
		// celluleClone.getCharacter().size();
		// celluleClone.getCharacter().remove(delegate);
		// celluleClone.getCharacter().size();
		// }
		System.out.println(celluleClone.getCharacter().size());
		// celluleClone.getCharacter().add(guardClone);
		enviClone.getCellContent(guardClone.getWdt(), guardClone.getHgt()).getCharacter().add(guardClone);
		guardClone.init(((GuardService) delegate).getWdt(), ((GuardService) delegate).getHgt(), enviClone,
				((GuardService) delegate).getTarget(), ((GuardService) delegate).getXPos(),
				((GuardService) delegate).getYPos());
		// System.out.println("y " + guardClone.getYPos() + " x " +
		// guardClone.getXPos());
		// System.out.println(" x " + guardClone.getWdt() + " y " +
		// guardClone.getHgt());
		System.out.println(celluleClone.getCharacter().size());
		System.out.println(this.getDelegate());*/
		((GuardService) delegate).step();
		checkInvariant();
		/*if (cellNature_at_pre == Cell.HOL && time_at_pre < GuardImpl.TIMEBEFOREOUT) {
			if (!(((GuardService) delegate).getTimeInHole() == time_at_pre + 1)) {
				throw new PostCondError("Le temps dans le trou aurait du être incrementé");
			}
		}
		if (cellNature_at_pre == Cell.HOL && time_at_pre == GuardImpl.TIMEBEFOREOUT
				&& ((GuardService) delegate).getBehaviour() == Move.LEFT) {
			if (!(((GuardService) delegate).getWdt() == x_at_pre - 1
					&& ((GuardService) delegate).getHgt() == y_at_pre + 1)) {
				throw new PostCondError("Le garde n'est pas remonté à gache comme il aurait du");
			}
		}
		if (cellNature_at_pre == Cell.HOL && time_at_pre == GuardImpl.TIMEBEFOREOUT
				&& ((GuardService) delegate).getBehaviour() == Move.RIGHT) {
			if (!(((GuardService) delegate).getWdt() == x_at_pre + 1
					&& ((GuardService) delegate).getHgt() == y_at_pre + 1)) {
				throw new PostCondError("Le garde n'est pas remonté à droite comme il aurait du");
			}
		}
		if (((cellNature_at_pre == Cell.LAD || cellNature_at_pre == Cell.HDR || cellNature_at_pre == Cell.EMP)
				|| (cellNatureEnBas_at_pre == Cell.PLT || cellNatureEnBas_at_pre == Cell.MTL
						|| cellNatureEnBas_at_pre == Cell.LAD)
				|| (nbCharaEnBas_at_pre > 0))) {
			if (((GuardService) delegate).getBehaviour() == Move.LEFT) {
				System.out.println("y " + guardClone.getHgt() + " x " + guardClone.getWdt());
				guardClone.GoLeft();
				if (!(guardClone.getHgt() == ((GuardService) delegate).getHgt()
						&& guardClone.getWdt() == ((GuardService) delegate).getWdt())) {
					System.out.println("y " + guardClone.getHgt() + " x " + guardClone.getWdt());
					throw new PostCondError("Le garde n'a pas bien fait l'opération goLeft");
				}
			}
			if (((GuardService) delegate).getBehaviour() == Move.RIGHT) {
				guardClone.GoRight();
				if (!(guardClone.getHgt() == ((GuardService) delegate).getHgt()
						&& guardClone.getWdt() == ((GuardService) delegate).getWdt())) {
					throw new PostCondError("Le garde n'a pas bien fait l'opération goRight");
				}
			}
			if (((GuardService) delegate).getBehaviour() == Move.DOWN) {
				// System.out.println(guardClone.getHgt() + " et " + guardClone.getWdt());
				guardClone.GoDown();
				// System.out.println(guardClone.getEnvi().getCellNature(getWdt(), getHgt()));
				// System.out.println(guardClone.getEnvi().getCellNature(getWdt(), getHgt() -
				// 1));
				if (!(guardClone.getHgt() == ((GuardService) delegate).getHgt()
						&& guardClone.getWdt() == ((GuardService) delegate).getWdt())) {
					System.out.println(guardClone.getHgt() + " et " + guardClone.getWdt());
					System.out
							.println(((GuardService) delegate).getHgt() + " et " + ((GuardService) delegate).getWdt());
					// System.out
					// .println(((GuardService) delegate).getHgt() + " et " + ((GuardService)
					// delegate).getWdt());
					throw new PostCondError("Le garde n'a pas bien fait l'opération goDown");
				}
			}
			if (((GuardService) delegate).getBehaviour() == Move.UP) {
				guardClone.GoUp();
				if (!(guardClone.getHgt() == ((GuardService) delegate).getHgt()
						&& guardClone.getWdt() == ((GuardService) delegate).getWdt())) {
					throw new PostCondError("Le garde n'a pas bien fait l'opération goUp");
				}
			}
		}*/
	}
}
