package decorators;

import java.util.ArrayList;

import service.Cell;
import service.Cle;
import service.EditableScreenService;
import service.EnvironmentService;

public class EditableScreenDecorator implements EditableScreenService {
	protected final EditableScreenService delegate;

	public EditableScreenDecorator(EditableScreenService ed) {
		delegate = ed;
	}

	@Override
	public int getHeight() {
		return delegate.getHeight();
	}

	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	public void init(EnvironmentService e) {
		delegate.init(e);
	}

	@Override
	public Cell getCellNature(int x, int y) {
		return delegate.getCellNature(x, y);
	}

	@Override
	public void init(int h, int w, int xJoueur, int yJoueur, ArrayList<Cle> posGardes, ArrayList<Cle> posItems) {
		delegate.init(w, h, xJoueur, yJoueur, posGardes, posItems);
	}

	@Override
	public void Dig(int x, int y) {
		delegate.Dig(x, y);

	}

	@Override
	public void Fill(int x, int y) {
		delegate.Fill(x, y);
	}

	@Override
	public boolean isPlayable() {
		return delegate.isPlayable();
	}

	@Override
	public void setNature(int x, int y, Cell C) {
		delegate.setNature(x, y, C);
	}

	@Override
	public int GetxJoueur() {

		return delegate.GetxJoueur();
	}

	@Override
	public int GetyJoueur() {
		// TODO Auto-generated method stub
		return delegate.GetyJoueur();
	}

	@Override
	public ArrayList<Cle> GetposGardes() {
		// TODO Auto-generated method stub
		return delegate.GetposGardes();
	}

	@Override
	public ArrayList<Cle> GetposItems() {
		// TODO Auto-generated method stub
		return delegate.GetposItems();
	}

	@Override
	public void init(int w, int h) {
		delegate.init(w, h);

	}

	@Override
	public void Lad(int x, int y) {
		delegate.Lad(x, y);
		
	}

}
