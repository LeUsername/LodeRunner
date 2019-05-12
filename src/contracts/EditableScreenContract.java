package contracts;

import java.util.ArrayList;

import error.InvariantError;
import error.PostCondError;
import error.PreCondError;
import service.Cell;
import service.Cle;
import service.EditableScreenService;
import service.EnvironmentService;

public class EditableScreenContract extends ScreenContract implements EditableScreenService {

	public EditableScreenContract(EditableScreenService delegate) {
		super(delegate);
	}

	public void checkInvariant() {
		super.checkInvariant();
		if (((EditableScreenService) getDelegate()).isPlayable()) {
			for (int i = 0; i < ((EditableScreenService) getDelegate()).getWidth(); i++) {
				for (int j = 0; j < ((EditableScreenService) getDelegate()).getHeight(); j++) {
					if (!(((EditableScreenService) getDelegate()).getCellNature(i, j) != Cell.HOL)) {
						throw new InvariantError("Au moins une case est HOL");
					}
					if (j == 0) {
						if (!(((EditableScreenService) getDelegate()).getCellNature(i, j) == Cell.MTL)) {
							throw new InvariantError("Au moins une des cases les plus basses n'est pas MTL");
						}
					}
				}
			}
		}
	}

	public void setNature(int x, int y, Cell C) {
		if (!(0 <= y && y < ((EditableScreenService) getDelegate()).getHeight())) {
			throw new PreCondError("Dépassement des bornes sur l'axe des ordonnées");
		}
		if (!(0 <= x && x < ((EditableScreenService) getDelegate()).getWidth())) {
			throw new PreCondError("Dépassement des bornes sur l'axe des abscisses");
		}
		checkInvariant();
		ArrayList<ArrayList<Cell>> cellNature = new ArrayList<>();
		for (int i = 0; i < ((EditableScreenService) getDelegate()).getWidth(); i++) {
			cellNature.add(new ArrayList<>());
			for (int j = 0; j < ((EditableScreenService) getDelegate()).getHeight(); j++) {
				cellNature.get(i).add(((EditableScreenService) getDelegate()).getCellNature(i, j));
			}
		}
		((EditableScreenService) getDelegate()).setNature(x, y, C);
		checkInvariant();
		for (int i = 0; i < ((EditableScreenService) getDelegate()).getWidth(); i++) {
			if (i == x) {
				continue;
			}
			for (int j = 0; j < ((EditableScreenService) getDelegate()).getHeight(); j++) {
				if (j == y) {
					continue;
				}
				if (!(((EditableScreenService) getDelegate()).getCellNature(i, j) == cellNature.get(i).get(j))) {
					throw new PostCondError("La nature d'une case a changé alors qu'elle ne devrait pas");
				}
			}
		}
	}

	public void init(int w, int h, int xJoueur, int yJoueur, ArrayList<Cle> posGardes, ArrayList<Cle> posItems) {
		((EditableScreenService) getDelegate()).init(w, h, xJoueur, yJoueur, posGardes, posItems);
		checkInvariant();
		if (!(GetxJoueur() == xJoueur && GetyJoueur() == yJoueur && GetposGardes().equals(posGardes)
				&& GetposItems().equals(posItems))) {
//			System.out.println(GetxJoueur() == xJoueur);
//			System.out.println(GetxJoueur());
//			System.out.println(xJoueur);
//			System.out.println(GetyJoueur() == yJoueur);
//			System.out.println(GetposGardes().equals(posGardes));
//			System.out.println(GetposItems().equals(posItems));
			throw new PostCondError("mauvaise initialisation");
		}
	}

	@Override
	public boolean isPlayable() {
		checkInvariant();
		boolean res = ((EditableScreenService) getDelegate()).isPlayable();
		checkInvariant();
		return res;
	}

	@Override
	public int GetxJoueur() {
		checkInvariant();
		int x = ((EditableScreenService) getDelegate()).GetxJoueur();
		checkInvariant();
		return x;
	}

	@Override
	public int GetyJoueur() {
		checkInvariant();
		int x = ((EditableScreenService) getDelegate()).GetyJoueur();
		checkInvariant();
		return x;
	}

	@Override
	public ArrayList<Cle> GetposGardes() {
		checkInvariant();
		ArrayList<Cle> p = ((EditableScreenService) getDelegate()).GetposGardes();
		checkInvariant();
		return p;
	}

	@Override
	public ArrayList<Cle> GetposItems() {
		checkInvariant();
		ArrayList<Cle> p = ((EditableScreenService) getDelegate()).GetposItems();
		checkInvariant();
		return p;
	}

	@Override
	public void init(EnvironmentService e) {
		((EditableScreenService) getDelegate()).init(e);
		checkInvariant();
	}
}
