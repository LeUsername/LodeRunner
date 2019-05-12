package contracts;

import java.util.HashMap;

import error.InvariantError;
import error.PostCondError;
import error.PreCondError;
import impl.CellContentImpl;
import impl.ItemImpl;
import service.Cell;
import service.CharacterService;
import service.Cle;
import service.EditableScreenService;
import service.EnvironmentService;
import service.GuardService;
import service.ItemType;

public class EnvironmentContract extends ScreenContract implements EnvironmentService {

	public EnvironmentContract(EnvironmentService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		super.checkInvariant();
		for (int x = 0; x < ((EnvironmentService) getDelegate()).getWidth(); x++) {
			for (int y = 0; y < ((EnvironmentService) getDelegate()).getHeight(); y++) {
				CellContentImpl content = ((EnvironmentService) getDelegate()).getCellContent(x, y);
				if (content != null) {
					for (CharacterService c1 : content.getCharacter()) {
						for (CharacterService c2 : content.getCharacter()) {
							if (c1 instanceof GuardService && c2 instanceof GuardService) {
								if (!(((GuardService) c1).getId() == ((GuardService) c2).getId())) {
									System.out.println(c1);
									System.out.println(c2);
									throw new InvariantError("Il y a plus d'un personnage dans une case");
								}
							}
						}
					}
				}
			}
		}
		for (int x = 0; x < ((EnvironmentService) getDelegate()).getWidth(); x++) {
			for (int y = 0; y < ((EnvironmentService) getDelegate()).getHeight(); y++) {
				if (((EnvironmentService) getDelegate()).getCellNature(x, y) == Cell.MTL
						|| ((EnvironmentService) getDelegate()).getCellNature(x, y) == Cell.PLT) {
					// FAIRE ATTENTION ICI QUAND ON CODE LE CONTRAT
					CellContentImpl content = ((EnvironmentService) getDelegate()).getCellContent(x, y);

					if (!(/* content.getCharacter().size() == 0 && */ (content.getItem().size() == 0))) {
						throw new InvariantError("Une case MTL/PLT contient un objet");
					}
				}
			}
		}
		// for (int x = 0; x < ((EnvironmentService) getDelegate()).getWidth(); x++) {
		// for (int y = 0; y < ((EnvironmentService) getDelegate()).getHeight(); y++) {
		// CellContentImpl content = ((EnvironmentService)
		// getDelegate()).getCellContent(x, y);
		// if (content.getItem().size() > 0) {
		// for (ItemImpl i : content.getItem()) {
		// if (i.getType() == ItemType.TRESOR) {
		// if (!(((EnvironmentService) getDelegate()).getCellNature(x, y) == Cell.EMP
		// && (((EnvironmentService) getDelegate()).getCellNature(x, y - 1) == Cell.MTL
		// || ((EnvironmentService) getDelegate()).getCellNature(x, y-1) == Cell.LAD
		// || ((EnvironmentService) getDelegate()).getCellNature(x,
		// y - 1) == Cell.PLT))) {
		// // System.out.println(((EnvironmentService) getDelegate()).getCellNature(x,
		// y));
		// // System.out.println(((EnvironmentService) getDelegate()).getCellNature(x,
		// y));
		// // System.out.println("x : " + x + " y : " + y);
		// // System.out.println(
		// // ((EnvironmentService) getDelegate()).getCellContent(x,
		// y).getItem().size());
		//// System.out.println(((EnvironmentService) getDelegate()).getCellNature(x,
		// y));
		//// System.out.println(((EnvironmentService) getDelegate()).getCellNature(x, y
		// - 1));
		// throw new InvariantError(
		// "Une case contenant un trésor n'est pas vide et/ou n'est pas au-dessus d'une
		// case en métal ou pleine");
		// }
		// }
		// }
		// }
		// }
		// }
	}

	@Override
	public void init(EditableScreenService e, int h, int w, HashMap<Cle, CellContentImpl> cellules) {
		((EnvironmentService) getDelegate()).init(e, h, w, cellules);
		checkInvariant();
	}

	@Override
	public CellContentImpl getCellContent(int x, int y) {
		if (!(0 <= x && x < ((EnvironmentService) getDelegate()).getWidth())) {
			System.out.println(x);
			System.out.println(((EnvironmentService) getDelegate()).getWidth());
			throw new PreCondError("L'abscisse demandée n'est pas valide");
		}
		if (!(0 <= y && y < ((EnvironmentService) getDelegate()).getHeight())) {
			throw new PreCondError("L'ordonnée demandée n'est pas valide");
		}
		checkInvariant();
		CellContentImpl res = ((EnvironmentService) getDelegate()).getCellContent(x, y);
		checkInvariant();
		return res;
	}

	@Override
	public void setCellContent(int x, int y, CellContentImpl c) {
		if (!(0 <= x && x < ((EnvironmentService) getDelegate()).getWidth())) {
			throw new PreCondError("L'abscisse demandée n'est pas valide");
		}
		if (!(0 <= y && y < ((EnvironmentService) getDelegate()).getHeight())) {
			throw new PreCondError("L'ordonnée demandée n'est pas valide");
		}
		HashMap<Cle, CellContentImpl> cellulesContent_at_pre = ((EnvironmentService) getDelegate()).getAllCellContent();
		checkInvariant();
		((EnvironmentService) getDelegate()).setCellContent(x, y, c);
		checkInvariant();
		for (int i = 0; i < ((EnvironmentService) getDelegate()).getWidth(); i++) {
			for (int j = 0; j < ((EnvironmentService) getDelegate()).getHeight(); j++) {
				if (i != x && j != y) {
					if (!(cellulesContent_at_pre.get(new Cle(i, j)) == ((EnvironmentService) getDelegate())
							.getCellContent(i, j))) {
						throw new PostCondError("Une case qui n'aura pas du changer de contenu a changé");
					}
				}
			}
		}
	}

	@Override
	public HashMap<Cle, CellContentImpl> getAllCellContent() {
		checkInvariant();
		HashMap<Cle, CellContentImpl> res = ((EnvironmentService) getDelegate()).getAllCellContent();
		checkInvariant();
		return res;
	}

	@Override
	public void Lad(int x, int y) {
		checkInvariant();
		super.Lad(x, y);
		checkInvariant();
		for (int w = 0; w < ((EnvironmentService) getDelegate()).getWidth(); w++) {
			for (int h = 0; h < ((EnvironmentService) getDelegate()).getHeight(); h++) {
				CellContentImpl content = ((EnvironmentService) getDelegate()).getCellContent(w, h);
				if (content.getItem().size() > 0) {
					for (ItemImpl item : content.getItem()) {
						if (item.getType() == ItemType.TRESOR) {
							if (!(((EnvironmentService) getDelegate()).getCellNature(w, h) == Cell.EMP
									&& (((EnvironmentService) getDelegate()).getCellNature(w, h - 1) == Cell.MTL
											|| ((EnvironmentService) getDelegate()).getCellNature(w, h - 1) == Cell.LAD
											|| ((EnvironmentService) getDelegate()).getCellNature(w,
													h - 1) == Cell.PLT))) {
								// System.out.println(((EnvironmentService) getDelegate()).getCellNature(x, y));
								// System.out.println(((EnvironmentService) getDelegate()).getCellNature(x, y));
								// System.out.println("x : " + x + " y : " + y);
								// System.out.println(
								// ((EnvironmentService) getDelegate()).getCellContent(x, y).getItem().size());
								// System.out.println(((EnvironmentService) getDelegate()).getCellNature(x, y));
								// System.out.println(((EnvironmentService) getDelegate()).getCellNature(x, y -
								// 1));
								throw new PostCondError(
										"Une case contenant un trésor n'est pas vide et/ou n'est pas au-dessus d'une case reglementaire");
							}
						}
					}
				}
			}
		}
	}
}
