/**
 * 
 */
package impl;

import java.util.ArrayList;

import service.Cell;
import service.Cle;
import service.EditableScreenService;
import service.EnvironmentService;

/**
 * @author 3408751
 *
 */
public class EditableScreenImpl extends ScreenImpl implements EditableScreenService, Cloneable {
	private int xJoueur;
	private int yJoueur;
	private ArrayList<Cle> posGardes;
	private ArrayList<Cle> posItems;

	public void init(int w, int h) {
		super.init(w, h);

	}

	public void init(EnvironmentService e) {
		super.init(e.getWidth(), e.getHeight());
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				carte.put(new Cle(i, j), e.getCellNature(i, j));
			}
		}
	}

	@Override
	public boolean isPlayable() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 1; j < getHeight(); j++) {
				if (carte.get(new Cle(i, j)) == Cell.HOL) {
					return false;
				}
			}
		}
		for (int i = 0; i < getWidth(); i++) {
			if (carte.get(new Cle(i, 0)) != Cell.MTL) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void setNature(int x, int y, Cell C) {
		// Marche ou pas ?
		if ((0 <= x && x < getWidth()) && (0 <= y && y < getHeight())) {
			carte.replace(new Cle(x, y), C);
		}
	}

	@Override
	public int GetxJoueur() {

		return xJoueur;
	}

	@Override
	public int GetyJoueur() {
		// TODO Auto-generated method stub
		return yJoueur;
	}

	@Override
	public ArrayList<Cle> GetposGardes() {
		// TODO Auto-generated method stub
		return posGardes;
	}

	@Override
	public ArrayList<Cle> GetposItems() {
		// TODO Auto-generated method stub
		return posItems;
	}

	@Override
	public void init(int w, int h, int xJoueur, int yJoueur, ArrayList<Cle> posGardes, ArrayList<Cle> posItems) {
		super.init(w, h);
		this.xJoueur = xJoueur;
		this.yJoueur = yJoueur;
		this.posGardes = posGardes;
		this.posItems = posItems;

	}

}
