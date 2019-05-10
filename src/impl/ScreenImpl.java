package impl;

import java.util.HashMap;

import service.Cell;
import service.Cle;
import service.ScreenService;

public class ScreenImpl implements ScreenService {

	protected int height;
	protected int width;
	protected HashMap<Cle, Cell> carte;

	public ScreenImpl() {
		carte = new HashMap<>();
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public Cell getCellNature(int x, int y) {
		return carte.get(new Cle(x, y));
	}

	@Override
	public void init(int w, int h) {
		height = h;
		width = w;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				carte.put(new Cle(i, j), Cell.EMP);
			}
		}
	}

	@Override
	public void Dig(int x, int y) {
		// Mettre dans la spec que si on dig en étant sur un HDR, on tombe
		if (getCellNature(x, y) == Cell.PLT) {
			carte.replace(new Cle(x, y), Cell.HOL);
		}
	}
	public void Lad(int x, int y) {
	
		if (getCellNature(x, y) == Cell.EMP || getCellNature(x, y) == Cell.PLT) {
			carte.replace(new Cle(x, y), Cell.LAD);
		}
	}
	@Override
	public void Fill(int x, int y) {
		if (getCellNature(x, y) == Cell.HOL || getCellNature(x, y) == Cell.EMP) {
			carte.replace(new Cle(x, y), Cell.PLT);
		}
	}
}
