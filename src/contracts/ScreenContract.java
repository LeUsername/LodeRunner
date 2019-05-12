package contracts;

import java.util.ArrayList;

import decorators.ScreenDecorator;
import error.PostCondError;
import error.PreCondError;
import service.Cell;
import service.ScreenService;

public class ScreenContract extends ScreenDecorator {
	public ScreenContract(ScreenService s) {
		super(s);
	}

	public void checkInvariant() {
		return;
	}

	@Override
	public int getHeight() {
		checkInvariant();
		int res = super.getHeight();
		checkInvariant();
		return res;
	}

	@Override
	public int getWidth() {
		checkInvariant();
		int res = super.getWidth();
		checkInvariant();
		return res;
	}

	@Override
	public Cell getCellNature(int x, int y) {
		if (!(x >= 0 && x < getWidth())) {
			throw new PreCondError("Abscisse non valide");
		}
		if (!(y >= 0 && y < getHeight())) {
			throw new PreCondError("Ordonnée non valide");
		}
		checkInvariant();
		Cell res = super.getCellNature(x, y);
		checkInvariant();
		return res;
	}

	@Override
	public void init(int w, int h) {
		if (!(0 < w && 0 < h)) {
			throw new PreCondError("Abscisse et/ou ordonnée négative(s) ou nulle(s)");
		}
		checkInvariant();
		super.init(w, h);
		checkInvariant();
		if (!(super.getWidth() == w && super.getHeight() == h)) {
			// System.out.println(super.getWidth());
			// System.out.println(w);
			// System.out.println(super.getHeight());
			// System.out.println(h);
			throw new PostCondError("Abscisse et/ou ordonnée mal initialisée(s)");
		}
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				if (!(getCellNature(i, j) == Cell.EMP)) {
					throw new PostCondError("La nature d'une case n'est pas EMP");
				}
			}
		}
	}

	@Override
	public void Dig(int x, int y) {
		if (!(getCellNature(x, y) == Cell.PLT)) {
			// System.out.println(getCellNature(x, y));
			throw new PreCondError("La case n'est pas PLT");
		}
		checkInvariant();
		ArrayList<ArrayList<Cell>> cellNature = new ArrayList<>();
		for (int i = 0; i < getWidth(); i++) {
			cellNature.add(new ArrayList<>());
			for (int j = 0; j < getHeight(); j++) {
				cellNature.get(i).add(getCellNature(i, j));
			}
		}
		super.Dig(x, y);
		checkInvariant();
		if (!(getCellNature(x, y) == Cell.HOL)) {
			throw new PostCondError("La case n'est pas HOL");
		}
		for (int i = 0; i < getWidth(); i++) {
			if (i == x) {
				continue;
			}
			for (int j = 0; j < getHeight(); j++) {
				if (j == y) {
					continue;
				}
				if (!(getCellNature(i, j) == cellNature.get(i).get(j))) {
					throw new PostCondError("La nature d'une case a changé alors qu'elle ne devrait pas");
				}
			}
		}
		// System.out.println("-------------------------------------");
	}

	@Override
	public void Fill(int x, int y) {
		if (!(getCellNature(x, y) == Cell.HOL || getCellNature(x, y) == Cell.EMP)) {
			// System.out.println(getCellNature(x, y));
			throw new PreCondError("La case n'est pas HOL ou EMP");
		}
		checkInvariant();
		ArrayList<ArrayList<Cell>> cellNature = new ArrayList<>();
		for (int i = 0; i < getWidth(); i++) {
			cellNature.add(new ArrayList<>());
			for (int j = 0; j < getHeight(); j++) {
				cellNature.get(i).add(getCellNature(i, j));
			}
		}
		super.Fill(x, y);
		checkInvariant();
		if (!(getCellNature(x, y) == Cell.PLT)) {
			throw new PostCondError("La case n'est pas PLT");
		}
		for (int i = 0; i < getWidth(); i++) {
			if (i == x) {
				continue;
			}
			for (int j = 0; j < getHeight(); j++) {
				if (j == y) {
					continue;
				}
				if (!(getCellNature(i, j) == cellNature.get(i).get(j))) {
					throw new PostCondError("La nature d'une case a changé alors qu'elle ne devrait pas");
				}
			}
		}
	}

	public void Lad(int x, int y) {
		if (!(getCellNature(x, y) == Cell.PLT || getCellNature(x, y) == Cell.EMP)) {
			// System.out.println(getCellNature(x, y));
			throw new PreCondError("La case n'est pas PLT ou EMP");
		}
		checkInvariant();
		ArrayList<ArrayList<Cell>> cellNature = new ArrayList<>();
		for (int i = 0; i < getWidth(); i++) {
			cellNature.add(new ArrayList<>());
			for (int j = 0; j < getHeight(); j++) {
				cellNature.get(i).add(getCellNature(i, j));
			}
		}
		super.Lad(x, y);
		checkInvariant();
		if (!(getCellNature(x, y) == Cell.LAD)) {
			throw new PostCondError("La case n'est pas LAD");
		}
		for (int i = 0; i < getWidth(); i++) {
			if (i == x) {
				continue;
			}
			for (int j = 0; j < getHeight(); j++) {
				if (j == y) {
					continue;
				}
				if (!(getCellNature(i, j) == cellNature.get(i).get(j))) {
					throw new PostCondError("La nature d'une case a changé alors qu'elle ne devrait pas");
				}
			}
		}
	}
}
