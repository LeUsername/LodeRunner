package contracts;

import java.util.ArrayList;

import decorators.GuardDecorator;
import error.InvariantError;
import error.PostCondError;
import error.PreCondError;
import impl.CellContentImpl;
import impl.EditableScreenImpl;
import impl.EnvironmentImpl;
import impl.GuardImpl;
import service.Cell;
import service.CharacterService;
import service.EditableScreenService;
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

	public void init(int h, int w, EnvironmentService envi, CharacterService target, int x, int y) {
		((GuardService) delegate).init(h, w, envi, target, x, y);
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

	public void step() {
		checkInvariant();
		GuardService guardClone = new GuardImpl();
		EditableScreenService editableClone = new EditableScreenImpl();
		editableClone.init(((GuardService) delegate).getEnvi());
		EnvironmentService enviClone = new EnvironmentImpl();
		enviClone.init(editableClone, editableClone.getWidth(), editableClone.getHeight(),
				((GuardService) delegate).getEnvi().getAllCellContent());
		CellContentImpl celluleClone = new CellContentImpl(
				enviClone.getCellContent(guardClone.getWdt(), guardClone.getHgt()).getCharacter(), new ArrayList<>());
		celluleClone.getCharacter().remove(this);
		celluleClone.getCharacter().add(guardClone);
		enviClone.setCellContent(guardClone.getWdt(), guardClone.getHgt(), celluleClone);
		guardClone.init(((GuardService) delegate).getHgt(), ((GuardService) delegate).getWdt(), enviClone,
				((GuardService) delegate).getTarget(), ((GuardService) delegate).getXPos(),
				((GuardService) delegate).getYPos());
		int time_at_pre = ((GuardService) delegate).getTimeInHole();
		Cell cellNature_at_pre = ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt());
		Cell cellNatureEnBas_at_pre = ((GuardService) delegate).getEnvi().getCellNature(getWdt(), getHgt() - 1);
		int nbCharaEnBas_at_pre = ((GuardService) delegate).getEnvi().getCellContent(getWdt(), getHgt() - 1)
				.getCharacter().size();
		int x_at_pre = ((GuardService) delegate).getWdt();
		int y_at_pre = ((GuardService) delegate).getHgt();
		((GuardService) delegate).step();
		checkInvariant();
		if (cellNature_at_pre == Cell.HOL && time_at_pre < GuardImpl.TIMEBEFOREOUT) {
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
				guardClone.GoLeft();
				if (!(guardClone.getHgt() == ((GuardService) delegate).getHgt()
						&& guardClone.getWdt() == ((GuardService) delegate).getWdt())) {
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
//				System.out.println(guardClone.getEnvi().getCellNature(getWdt(), getHgt()));
//				System.out.println(guardClone.getEnvi().getCellNature(getWdt(), getHgt() - 1));
				if (!(guardClone.getHgt() == ((GuardService) delegate).getHgt()
						&& guardClone.getWdt() == ((GuardService) delegate).getWdt())) {
					 System.out.println(guardClone.getHgt() + " et " + guardClone.getWdt());
					 System.out.println(((GuardService) delegate).getHgt()+ " et "  +((GuardService) delegate).getWdt());
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
		}
	}
}
